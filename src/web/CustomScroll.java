package web;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.webdriverfw.Wrappers.General;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CustomScroll extends General {

    /**
     * This action will scroll the webpage according to the given parameter i.e. option
     *
     * @param option
     * @return
     */
    public ActionResponse CustomScroll(String option) {
        ActionResponse actionResponse = getActionResponse();

        // use scrollTo method for scrolling to top and bottom
        WebDriver driver = getWebDriver();
        if (option.equalsIgnoreCase("top")) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        } else if (option.equalsIgnoreCase("bottom")) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        } else {
            /* use scrollBy method for other custom options
                Option includes "top", "left" and "behaviour" parameters
                used to scroll at any point on page
                Example : window.scrollBy({top: 100, left: 100, behavior: 'smooth'});
             */
            ((JavascriptExecutor) driver).executeScript("window.scrollBy({" + option + "})");
        }

        actionResponse.setExecutionResult(ExecutionResult.PASSED);
        actionResponse.setMessage("Scrolled successfully");
        return actionResponse;
    }
}