
import java.util.HashMap;
import java.util.Map;
import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.qualitia.platform.PlatformType;
import com.webdriverfw.Wrappers.General;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class quitDriver extends General {

    public ActionResponse quitDriverReinstantiate() {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();
        actionResponse.setExecutionResult(ExecutionResult.FAILED);

        try {
            //Commented the below code as it is handled internally in createWebDriver()
//	      if (QualitiaSelenium.getWebDriverSessionId() != null)
//	      {
//	        getWebDriver().quit();
//	      }

            if (getBrowserName().equalsIgnoreCase("firefox")) {

                createWebDriver(PlatformType.FIREFOX);
            }
            else if (getBrowserName().equalsIgnoreCase("iexplore")) {

                createWebDriver(PlatformType.INTERNET_EXPLORER);
            }
            else if (getBrowserName().equalsIgnoreCase("googlechrome")) {

                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments(new String[] { "--disable-extensions" });
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", Boolean.valueOf(false));
                prefs.put("profile.password_manager_enabled", Boolean.valueOf(false));
                options.setExperimentalOption("prefs", prefs);
                capabilities.setCapability("goog:chromeOptions", options);

                createWebDriver(PlatformType.CHROME, capabilities);
            }
            else if (getBrowserName().equalsIgnoreCase("safari")) {

                createWebDriver(PlatformType.SAFARI);
            }

            actionResponse.setMessage("Dirver Re-intanstiated");
            actionResponse.setExecutionResult(ExecutionResult.PASSED);
        }
        catch (Exception e) {
            String errMsg = "Exception while Launching browser";
            actionResponse.setExecutionResult(ExecutionResult.FAILED);
            actionResponse.setMessage(errMsg + e.getMessage(), FailureCategories.Category.ACTION_FAILURE);
            applicationLogger.writeToErrorLog(e);
        }

        return actionResponse;
    }
}
