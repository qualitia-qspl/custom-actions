package web; /**
* Recommendations and Rules for Custom Actions
* 1. The parameter data types supported are String and Array. 
* 2. All custom actions must return following integer values only.
*    a. If action gets passed, return value should be 0.
*    b. If action gets failed, return value should be 1.
*    c. If action has defect, return value should be 2.
*/

import java.text.SimpleDateFormat;
import java.util.Date;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;

import bsh.ParseException;


@SuppressWarnings("rawtypes")
public class DateComparision extends General {
	
	public ActionResponse compareDate(String dateFormat, String firstDate, String secondDate, String strKey) throws java.text.ParseException {
		ActionResponse actionResponse = getActionResponse();
		ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();
		actionResponse.setExecutionResult(ExecutionResult.FAILED);

		String strData="";
			try{
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				Date date1 = sdf.parse(firstDate);
				applicationLogger.writeToInfoLog("First Date is " + firstDate);
				Date date2 = sdf.parse(secondDate);
				applicationLogger.writeToInfoLog("Second Date is " + secondDate);

				System.out.println("date1 : " + sdf.format(date1));
				System.out.println("date2 : " + sdf.format(date2));

				if (date1.compareTo(date2) > 0) {
					applicationLogger.writeToInfoLog("First Date is after Second Date");
					System.out.println("Date1 is after Date2");
					strData="First Date is after Second Date";
				} else if (date1.compareTo(date2) < 0) {
					applicationLogger.writeToInfoLog("First Date is before Second Date");
					System.out.println("Date1 is before Date2");
					 strData="First Date is before Second Date";
				} else if (date1.compareTo(date2) == 0) {
					applicationLogger.writeToInfoLog("First Date is equal to Second Date");
					System.out.println("Date1 is equal to Date2");
				 	strData="First Date is equal to Second Date";
				}
			} catch (Exception e) {
				applicationLogger.writeToErrorLog(e);
				actionResponse.setMessage("Please provide required information to the function.... Exception : " + e.getMessage(), FailureCategories.Category.ACTION_FAILURE);
				return actionResponse;
			}
		if (storeData(strKey, strData))
		{
			actionResponse.setMessage("Date Comparision Information '" + strData + "' is stored successfuly in varibale: " + strKey + ".");
			actionResponse.setExecutionResult(ExecutionResult.PASSED);
			return actionResponse;
		} else {
			actionResponse.setMessage("Date Comparision Information '" + strData + "' is stored successfuly in varibale: " + strKey + ".", FailureCategories.Category.FAILED_TO_STORE_DATA);
			return actionResponse;
		}
	}

	public static void main(String[] args) throws ParseException, java.text.ParseException {

		DateComparision dateComparision= new DateComparision();
		dateComparision.compareDate("MMMM dd yyyy", "May 12 2020", "Jan 12 2020","strKey");
		}
	
}