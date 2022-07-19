import java.util.List;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CountHashTags extends General {
	
public ActionResponse CountHashTags(String elementXpath, String count) {
	ActionResponse actionResponse = getActionResponse();
	ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();
	actionResponse.setExecutionResult(ExecutionResult.PASSED);

	try {
		applicationLogger.writeToInfoLog("Entered method to find no of tags added");
		WebDriver driver = null;
		driver = getWebDriver();
		List<WebElement> hashtags = driver.findElements(By.xpath(elementXpath));
		for(WebElement ele: hashtags) {
			applicationLogger.writeToInfoLog(ele.getText());
		}
		storeData(count, Integer.toString(hashtags.size()));
		actionResponse.setMessage("Finished counting");
		actionResponse.setExecutionResult(ExecutionResult.PASSED);
	} catch (Exception ex) {
		applicationLogger.writeToErrorLog(ex);
		actionResponse.setMessage("Exception occurred : " + ex.getMessage(), FailureCategories.Category.ACTION_FAILURE);
		ex.printStackTrace();
	}
	return actionResponse;
}   
}