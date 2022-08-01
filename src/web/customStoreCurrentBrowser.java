package web;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class customStoreCurrentBrowser extends General {

    public ActionResponse storeCurrentBrowser(String browserType, String version) {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();

        try {
            if (browserType == null || browserType.trim().equalsIgnoreCase("")) {

                actionResponse.setMessage("The key passed is either null/empty , please verify.", FailureCategories.Category.INVALID_TEST_DATA);
                actionResponse.setExecutionResult(ExecutionResult.FAILED);
                return actionResponse;
            }
            String browser = getBrowserName().toLowerCase();

            if (storeData(browserType, browser)) {
                applicationLogger.writeToInfoLog("The browser type '" + browser + "' is stored successfully in the key '" + browserType + "'");
                actionResponse.setExecutionResult(ExecutionResult.PASSED);
            }
            else {
                actionResponse.setMessage("Failed to store the browser type '" + browser + "' in the key '" + browserType + "'");
                actionResponse.setExecutionResult(ExecutionResult.FAILED);
                return actionResponse;
            }
            if (!version.trim().equalsIgnoreCase("")) {

                WebDriver driver = getWebDriver();
                Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
                String browserVersion = cap.getVersion();
                if (storeData(version, browserVersion)) {

                    actionResponse.setMessage("The browser version '" + browserVersion + "' is stored successfully in the key '" + version + "'");
                    actionResponse.setExecutionResult(ExecutionResult.PASSED);
                    return actionResponse;
                }
                actionResponse.setMessage("Failed to store the browser version '" + browserVersion + "' in the key '" + version + "'", FailureCategories.Category.FAILED_TO_STORE_DATA);
                actionResponse.setExecutionResult(ExecutionResult.FAILED);
                return actionResponse;
            }
            return actionResponse;
        }
        catch (Exception e) {
            applicationLogger.writeToErrorLog(e);
            actionResponse.setMessage("please refer debug and error log. Exception : " + e.getMessage(), FailureCategories.Category.ACTION_FAILURE);
            actionResponse.setExecutionResult(ExecutionResult.FAILED);
            return actionResponse;
        }
    }


}
