package web;
/**
* Recommendations and Rules for Custom Actions
* 1. The parameter data types supported are String and Array. 
* 2. All custom actions must return following integer values only.
*    a. If action gets passed, return value should be 0.
*    b. If action gets failed, return value should be 1.
*    c. If action has defect, return value should be 2.
*/

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;

public class ConvertRGBtoHex extends General {

	public ActionResponse ConvertRGBtoHex(String RGBString, String HexString) {

		ActionResponse actionResponse = getActionResponse();
		ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();
		actionResponse.setExecutionResult(ExecutionResult.FAILED);
		try {
			applicationLogger.writeToInfoLog("Entered method Converting RGB to Hex");
			String str = RGBString.substring(5, RGBString.length() - 1);
			applicationLogger.writeToInfoLog(str);
			String str1[] = str.split(", ");
			applicationLogger.writeToInfoLog(str1[0] + " " + str1[1] + " " + str1[2]);

			String hex = "";
			for (String a : str1) {
				applicationLogger.writeToInfoLog(a);
				String b = calculateHex(a);
				hex = hex + b;
			}
			hex = hex.substring(0, hex.length()-2);
			applicationLogger.writeToInfoLog(hex);
			storeData(HexString, hex.toLowerCase());

			actionResponse.setExecutionResult(ExecutionResult.PASSED);
			actionResponse.setMessage("Action executed successfully");
		} catch (Exception ex) {
			applicationLogger.writeToErrorLog(ex);
			actionResponse.setMessage("Exception occurred : " + ex.getMessage(), FailureCategories.Category.ACTION_FAILURE);

			ex.printStackTrace();
		}
		return actionResponse;
	}

	private String calculateHex(String a) {
		ApplicationLogger applicationLogger = getActionResponse().getApplicationLogger();

		applicationLogger.writeToInfoLog("Entered into calculation method");
		String str = "";
		String arr[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
		int i = Integer.parseInt(a);
//		DataLogger.writeToDebugAndInfoLogs(Integer.toString(i));
		int j = i / 16;
		float f = decimalCalculation(i,j);
//		DataLogger.writeToDebugAndInfoLogs(Integer.toString(j));
		float k = (f-(float)j) * 16;
//		DataLogger.writeToDebugAndInfoLogs(Float.toString(f));
//		DataLogger.writeToDebugAndInfoLogs(Float.toString(k));
		int m = (int) k;
//		DataLogger.writeToDebugAndInfoLogs(Integer.toString(m));
		str = str + arr[j] + arr[m];
		applicationLogger.writeToInfoLog(str);
		return str;
		// TODO Auto-generated method stub

	}
	
	private float decimalCalculation(int x, int d) {
		float sum = d;
		float k = 0;
		for (int i = 1; i <= 5; i++) { 
            x = x - (16 * d); 
            if (x == 0) 
                break; 
            x = x * 10; 
            d = x / 16;
            k = (float)d;
            double l = Math.pow(10,i);
//            DataLogger.writeToDebugAndInfoLogs(Integer.toString(d));
            sum = sum+(float)(k/l);
//            DataLogger.writeToDebugAndInfoLogs(Float.toString(sum));
        } 
		return sum;
	}
}