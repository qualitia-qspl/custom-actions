package web.general.click;


import com.helpers.ElementFinderUtil;
import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.qualitia.utilities.CommonUtil;
import com.qualitia.utilities.NumberUtil;
import com.qualitia.utilities.StringUtil;
import com.webdriverfw.Wrappers.General;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickNTimes extends General {

    /**
     * The action clicks on an object specified number of times. The element
     * can be found using the provided locator type and its value.
     *
     * @param numberOfClicks - number of times to perform the click action
     * @param locatorType   - locator type to be used
     * @param locatorValue  - locator value to be used
     * @return
     */
    public ActionResponse CustomClickNTimes(String numberOfClicks, String locatorType, String locatorValue) {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();

        if(!NumberUtil.isInteger(numberOfClicks)) {
            actionResponse.setMessage("Invalid parameter value provided for numberOfClicks: "+numberOfClicks, FailureCategories.Category.INVALID_TEST_DATA);
            actionResponse.setExecutionResult(ExecutionResult.FAILED);
            return actionResponse;
        }

        try {
            int noOfTimesToClick = Integer.parseInt(numberOfClicks);

            /*
            * Below getWebElement API is used to check if the element exists
            * and then return a reference to that element object
            * */
            WebElement elementToClick = ElementFinderUtil.getWebElement(getWebDriver(), applicationLogger, locatorValue, locatorType);
            
            for (int i = 0; i < noOfTimesToClick; i++) {
                /*
                 * Below pageLoadWait API is used to wait for the web page to
                 * load before finding the element and performing the click action.
                 * */
                CommonUtil.pageLoadWait(10, getWebDriver());
                elementToClick.click();
            }
            
			actionResponse.setMessage("The element was clicked "+noOfTimesToClick+" times, successfully.");
			actionResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Exception ex) {
            actionResponse.setMessage("Exception occurred : " + ex.getMessage(), FailureCategories.Category.ACTION_FAILURE);
            actionResponse.setExecutionResult(ExecutionResult.FAILED);
            applicationLogger.writeToErrorLog(ex);
        }
        return actionResponse;
    }
}