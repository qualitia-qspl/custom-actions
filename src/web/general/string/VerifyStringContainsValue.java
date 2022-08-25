package web.general.string;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;

public class VerifyStringContainsValue extends General {

    /**
     * This action replaces all types of spaces present in the mainString with single normal
     * space and then checks if the given substring is present in it or not.
     *
     * @param mainString parameter cannot be null or empty as it is mandatory
     * @param subString parameter cannot be null or empty as it is mandatory
     * @param caseSensitive specifies whether the comparison is case-sensitive or not
     *                      if true: comparison is case-sensitive
     *                      if false: comparison is case-insensitive
     * @return
     */
    public ActionResponse CustomVerifyStringContainsValue(String mainString, String subString, String caseSensitive) {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();

        applicationLogger.writeToInfoLog("Main String is : " + mainString);
        applicationLogger.writeToInfoLog("Sub String is : " + subString);

        /*
         * Below line replaces all types of empty spaces with a single normal space
         * */
        mainString = mainString.replaceAll("[\\s?]+", " ");
        applicationLogger.writeToInfoLog("MainString after removing all type of space's with normal space : " + mainString);

        /*
         * If the comparison is not case-sensitive
         * convert the strings to lower case to have a common base for comparison
         * can be converted to upper case as well
         * */
        Boolean isCaseSensitive = Boolean.valueOf(caseSensitive);
        if (!isCaseSensitive) {
            mainString = mainString.toLowerCase();
            subString = subString.toLowerCase();
        }

        /*
         * Below statement checks if the substring is present inside the main string
         * if it is present, the result is passed
         * else the result is defect
         * */
        Boolean res = mainString.contains(subString);
        if (res) {
            actionResponse.setMessage("The mainString " + mainString + " contains the substring " + subString + " with CaseSensitive comparison as : " + caseSensitive);
            actionResponse.setExecutionResult(ExecutionResult.PASSED);
            return actionResponse;
        }
        actionResponse.setMessage("The mainString " + mainString + " does not contain the substring " + subString + " with CaseSensitive comparison as : " + caseSensitive + ".Please verify the values.", FailureCategories.Category.UNEXPECTED_RESULT);
        actionResponse.setExecutionResult(ExecutionResult.DEFECT);
        return actionResponse;
    }

}
