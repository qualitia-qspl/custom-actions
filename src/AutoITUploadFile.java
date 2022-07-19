import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;

public class AutoITUploadFile extends General {

    public ActionResponse AutoITUploadFile(String ExecutablePath, String UploadFile) {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();
        actionResponse.setExecutionResult(ExecutionResult.FAILED);

        try {

            String AutoITPath = ExecutablePath + " " + UploadFile;

            Runtime.getRuntime().exec(AutoITPath);

            Thread.sleep(10000);

            actionResponse.setMessage("Action executed successfully");
            actionResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Exception e) {
            actionResponse.setExecutionResult(ExecutionResult.FAILED);
            actionResponse.setMessage("Exception occurred  : " + e.getMessage(),  FailureCategories.Category.ACTION_FAILURE);
            applicationLogger.writeToErrorLog(e);

        }
        return actionResponse;

    }

}
