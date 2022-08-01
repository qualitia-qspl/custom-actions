package web;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;

public class stringOperation extends General {

    public ActionResponse removeAllTypesOfSpacesWithNormalSpaceAndVerifyStringContainsValue(String mainString, String subString, String caseSensitive) {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();

        applicationLogger.writeToInfoLog("Main String is : " + mainString);
        applicationLogger.writeToInfoLog("Sub String is : " + subString);

        if (mainString == null || subString == null) {
            actionResponse.setMessage("One / Both of the strings send are either Empty, Null or is not a string.Please verify.", FailureCategories.Category.INVALID_TEST_DATA);
            actionResponse.setExecutionResult(ExecutionResult.FAILED);
            return actionResponse;
        }
        String mainstringTemp = ""; char[] arrayOfChar = null; byte b; int i;
        for (i = mainString.toCharArray().length, b = 0; b < i; ) { char c = arrayOfChar[b];

            if (c == '?' || c == ' ') {
                c = ' ';
            }
            mainstringTemp = String.valueOf(String.valueOf(mainstringTemp)) + c; b = (byte)(b + 1); }

        mainString = mainstringTemp;

        mainString = mainString.replaceAll("\\s+", " ");
        subString = subString.replaceAll("\\s+", " ");

        applicationLogger.writeToInfoLog("MainString after removing all type of space's with normal space : " + mainString);



        Boolean isCaseSensitive = Boolean.valueOf(caseSensitive);
        if (isCaseSensitive.booleanValue()) {

            Boolean res = Boolean.valueOf(mainString.contains(subString));
            if (res.booleanValue()) {

                actionResponse.setMessage("Action executed successfully");
                actionResponse.setExecutionResult(ExecutionResult.PASSED);
                return actionResponse;
            }
            actionResponse.setMessage("Please verify the values.", FailureCategories.Category.UNEXPECTED_RESULT);
            actionResponse.setExecutionResult(ExecutionResult.DEFECT);
            return actionResponse;
        }
        mainString = mainString.toLowerCase();
        subString = subString.toLowerCase();
        Boolean res = Boolean.valueOf(mainString.contains(subString));
        if (res.booleanValue()) {

            actionResponse.setMessage("Action executed successfully");
            actionResponse.setExecutionResult(ExecutionResult.PASSED);
            return actionResponse;
        }
        actionResponse.setMessage("Please verify the values.", FailureCategories.Category.UNEXPECTED_RESULT);
        actionResponse.setExecutionResult(ExecutionResult.DEFECT);
        return actionResponse;
    }

}
