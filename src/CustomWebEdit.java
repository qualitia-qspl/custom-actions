import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.qualitia.libraries.ObjectInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import com.webdriverfw.Wrappers.WebObject;

public class CustomWebEdit extends WebObject {

	public ActionResponse JSCustomSet(ObjectInfo object, String valuedata) {
		ActionResponse actionResponse = getActionResponse();
		ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();
		actionResponse.setExecutionResult(ExecutionResult.FAILED);

		applicationLogger.writeToInfoLog("CurrentDate 1" + CurrentDate());
		getWebDriver().manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
		applicationLogger.writeToInfoLog("CurrentDate 2" + CurrentDate());
		String locatorValue = object.getEvaluatedLocatorValue();
		applicationLogger.writeToInfoLog("CurrentDate 3" + CurrentDate());
		String locatorType = object.getLocatorType();
		applicationLogger.writeToInfoLog("CurrentDate 4" + CurrentDate());
		WebElement locator = null;
		applicationLogger.writeToInfoLog("CurrentDate 5" + CurrentDate());
		if (locatorType.equalsIgnoreCase("ID")) {
			locator = getWebDriver().findElement(By.id(locatorValue));
		} else if (locatorType.equalsIgnoreCase("XPATH")) {
			locator = getWebDriver().findElement(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("NAME")) {
			locator = getWebDriver().findElement(By.name(locatorValue));
		}
		applicationLogger.writeToInfoLog("CurrentDate 6" + CurrentDate());
		try {
			applicationLogger.writeToInfoLog("1");

			applicationLogger.writeToInfoLog("2");
			applicationLogger.writeToInfoLog("CurrentDate 7" + CurrentDate());
			Actions builder = new Actions(getWebDriver());
			applicationLogger.writeToInfoLog("CurrentDate 8" + CurrentDate());
			Action test = builder.sendKeys(new CharSequence[] { valuedata }).build();
			applicationLogger.writeToInfoLog("CurrentDate 9" + CurrentDate());
			test.perform();

			applicationLogger.writeToInfoLog("CurrentDate 10" + CurrentDate());
			applicationLogger.writeToInfoLog(" value " + valuedata);
		} catch (Exception e) {
			actionResponse.setMessage("Exception occurred : " + e.getMessage(), FailureCategories.Category.ACTION_FAILURE);
			applicationLogger.writeToErrorLog(e);
		}
		applicationLogger.writeToInfoLog("New custon action again");
		actionResponse.setMessage("Clicked successfully");
		actionResponse.setExecutionResult(ExecutionResult.PASSED);
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return actionResponse;
	}

	private static String CurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		return dateFormat.format(date);
	}
}