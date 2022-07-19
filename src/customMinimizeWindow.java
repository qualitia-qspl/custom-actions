import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;
import java.awt.*;
import java.awt.event.KeyEvent;

public class customMinimizeWindow extends General {

    public ActionResponse MinimizeWindow() {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();
        actionResponse.setExecutionResult(ExecutionResult.FAILED);

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_WINDOWS);
            actionResponse.setMessage("Action executed successfully");
            actionResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Exception e) {
            applicationLogger.writeToErrorLog(e);
            actionResponse.setMessage("exception occurred : "  + e.getMessage(), FailureCategories.Category.ACTION_FAILURE);
            actionResponse.setExecutionResult(ExecutionResult.FAILED);
        }

        return actionResponse;

    }
}
