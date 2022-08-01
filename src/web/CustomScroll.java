package web; /**
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CustomScroll extends General {
	
	public ActionResponse CustomScroll(String whereto) {
		ActionResponse actionResponse = getActionResponse();
		ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();
		actionResponse.setExecutionResult(ExecutionResult.FAILED);

		try {
				applicationLogger.writeToInfoLog("Entered");
				WebDriver driver = null;
				driver = getWebDriver();
				if(whereto=="top") {
					((JavascriptExecutor) driver)
					.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
				}
				else if(whereto=="end") {
					((JavascriptExecutor) driver)
					.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				}
				else {
					((JavascriptExecutor) driver)
					.executeScript("window.scrollBy("+whereto+")");
				}
				actionResponse.setExecutionResult(ExecutionResult.PASSED);
				actionResponse.setMessage("Action executed successfully");
			} catch(Exception ex) {
				actionResponse.setMessage("Exception occurred : " + ex.getMessage(), FailureCategories.Category.ACTION_FAILURE);
				applicationLogger.writeToErrorLog(ex);
				ex.printStackTrace();
			}
		return actionResponse;
	}
}