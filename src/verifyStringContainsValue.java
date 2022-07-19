
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;

public class verifyStringContainsValue extends General {

    public ActionResponse customVerifyStringContainsValue(String mainString, String subStringInRegExpForm, String caseSensitive, String comparisonResultIsStoredInStrKey) {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();
        actionResponse.setExecutionResult(ExecutionResult.FAILED);

        if (mainString == null || subStringInRegExpForm == null) {
            actionResponse.setMessage("One / Both of the strings send are either Empty, Null or is not a string.Please verify.", FailureCategories.Category.INVALID_TEST_DATA);
            return actionResponse;
        }
        if (caseSensitive.equalsIgnoreCase("")) {
            caseSensitive = "true";
            applicationLogger.writeToInfoLog("The CaseSensitive data passed is empty. Hence its set to the default value 'True'");
        }
        else if (caseSensitive.equalsIgnoreCase("true") || caseSensitive.equalsIgnoreCase("false")) {
            applicationLogger.writeToInfoLog("Case Sensitive Parameter's value is : " + caseSensitive);
        }
        else {

            applicationLogger.writeToInfoLog("The CaseSensitive data passed is '" + caseSensitive + "'. Hence its set to the default value 'True'");
            caseSensitive = "true";
        }
        Boolean isCaseSensitive = Boolean.valueOf(caseSensitive);
        if (isCaseSensitive.booleanValue()) {

            String pattern = subStringInRegExpForm;
            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(mainString);
            if (m.find())
            {
                actionResponse.setExecutionResult(ExecutionResult.PASSED);
                storeData(comparisonResultIsStoredInStrKey, "true");
                applicationLogger.writeToInfoLog("The 'true' is stored in a key " + comparisonResultIsStoredInStrKey);
                actionResponse.setMessage("The String '" + mainString + "' contains  the substring '" + subStringInRegExpForm + "' With CaseSensitive comparison as : " + caseSensitive);

                System.out.println("Match found.");
            }
            else
            {
                actionResponse.setExecutionResult(ExecutionResult.PASSED);
                storeData(comparisonResultIsStoredInStrKey, "false");
                applicationLogger.writeToInfoLog("The 'false' is stored in a key " + comparisonResultIsStoredInStrKey);
                applicationLogger.writeToInfoLog("Failed, Please check the DebugLogsForDetails. The String '" + mainString + "' does not contains  the substring '" + subStringInRegExpForm + "' With CaseSensitive comparison as : " + isCaseSensitive + ", Please Verify.");
                System.out.println("NO MATCH");
            }
        } else {

            mainString = mainString.toLowerCase();
            subStringInRegExpForm = subStringInRegExpForm.toLowerCase();

            String pattern = subStringInRegExpForm;
            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(mainString);
            if (m.find()) {
                actionResponse.setExecutionResult(ExecutionResult.PASSED);
                storeData(comparisonResultIsStoredInStrKey, "true");
                applicationLogger.writeToInfoLog("The 'true' is stored in a key " + comparisonResultIsStoredInStrKey);
                actionResponse.setMessage("The String '" + mainString + "' contains  the substring '" + subStringInRegExpForm + "' With CaseSensitive comparison as : " + caseSensitive);
                System.out.println("Found value: " + m.group(0));
                System.out.println("Match found.");
            }
            else {
                actionResponse.setExecutionResult(ExecutionResult.PASSED);
                storeData(comparisonResultIsStoredInStrKey, "false");
                applicationLogger.writeToInfoLog("The 'false' is stored in a key " + comparisonResultIsStoredInStrKey);
                actionResponse.setMessage("Failed, Please check the DebugLogsForDetails. The String '" + mainString + "' does not contains  the substring '" + subStringInRegExpForm + "' With CaseSensitive comparison as : " + isCaseSensitive + ", Please Verify.");
                System.out.println("NO MATCH");
            }
        }
        return actionResponse;
    }

}

