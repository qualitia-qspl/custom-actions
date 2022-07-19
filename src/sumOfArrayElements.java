
import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;

public class sumOfArrayElements extends General {

    public ActionResponse sumationOfArrayElements(String arrayInStringFormat, String strKey) {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();
        actionResponse.setExecutionResult(ExecutionResult.FAILED);

        String[] tempArray = arrayInStringFormat.split("\\^");
        double sum = 0.0D; String[] arrayOfString = null; byte b; int i;
        for (i = tempArray.length, b = 0; b < i; ) {
            String str = tempArray[b];

            try {
                sum += Double.parseDouble(str);
            }
            catch (NumberFormatException numExc) {
                applicationLogger.writeToErrorLog(numExc);
                actionResponse.setExecutionResult(ExecutionResult.FAILED);
                actionResponse.setMessage("Data for variable '" + arrayInStringFormat + "'is not valid. Exception : " + numExc.getMessage() , FailureCategories.Category.ACTION_FAILURE);
                System.out.println("Exception : " + numExc);
                return actionResponse;
            }  b = (byte)(b + 1);
        }
        actionResponse.setExecutionResult(ExecutionResult.PASSED);
        actionResponse.setMessage("Data '" + sum + "' is stored in variable '" + strKey + "' successfully.");
        storeData(strKey, Double.toString(sum));
        System.out.println(sum);
        return actionResponse;
    }

}
