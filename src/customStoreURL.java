import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;

public class customStoreURL extends General {

    public ActionResponse storeURL(String strKey) {
        ActionResponse actionResponse = getActionResponse();

        String strData = getWebDriver().getCurrentUrl().toString().toLowerCase();

        if (storeData(strKey, strData)) {
            actionResponse.setMessage("The Page URL '" + strData + "' is stored successfuly in varibale: " + strKey + ".");
            actionResponse.setExecutionResult(ExecutionResult.PASSED);
        }
        else {
            actionResponse.setMessage("The Page URL '" + strData + "' is stored successfuly in varibale: " + strKey + ".", FailureCategories.Category.FAILED_TO_STORE_DATA);
            actionResponse.setExecutionResult(ExecutionResult.FAILED);
        }
        return actionResponse;
    }

}
