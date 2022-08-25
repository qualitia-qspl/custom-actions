package web.general.number;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;

import java.util.ArrayList;

public class SumOfElements extends General {

    /**
     * The action is used to calculate the sum of all the elements present in an array
     * and store it in the resultKey provided.
     * Provide the arrayObject parameter to the action in Qualitia, as string
     * separated by '~^'.
     * The data type for this action is array in Qualitia.
     * The action fails if any character other than number is passed in the array
     *
     * @param arrayObject array of elements
     * @param resultKey key to store the result in
     * @return
     */
    public ActionResponse CalculateSumOfElements(Object arrayObject, String resultKey) {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();

        applicationLogger.writeToInfoLog("Input array: "+arrayObject.toString());
        ArrayList<Object> arrayElements = (ArrayList<Object>)arrayObject;
        double sum = 0.0D;

        /*
         * Below loop is used to validate the elements and add them
         * */
        for (int i = 0; i < arrayElements.size(); i++) {
            String element = (String)arrayElements.get(i);
            try {
                sum += Double.parseDouble(element);
            } catch (NumberFormatException numExc) {
                applicationLogger.writeToErrorLog(numExc);
                actionResponse.setExecutionResult(ExecutionResult.FAILED);
                actionResponse.setMessage("Data for variable '" + arrayObject + "'is not valid. Exception : " + numExc.getMessage(), FailureCategories.Category.ACTION_FAILURE);
                return actionResponse;
            }
        }

        /*
         * Below API storeData is used to store data in a key.
         * */
        storeData(resultKey, Double.toString(sum));
        actionResponse.setExecutionResult(ExecutionResult.PASSED);
        actionResponse.setMessage("Data '" + sum + "' is stored in variable '" + resultKey + "' successfully.");
        return actionResponse;
    }
}
