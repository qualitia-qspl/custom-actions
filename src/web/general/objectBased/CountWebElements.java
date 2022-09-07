package web.general.objectBased;

import java.util.List;
import com.helpers.ElementFinderUtil;
import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CountWebElements extends General {

    /**
     * This action will count the no. of web elements present on page based on the locator type and value given in parameter.
     *
     * @param locatorValue locator Value of the object
     * @param locatorType respective locatorType of the locator Value
     * @param countKey key to store the count of the elements
     * @return
     */
    public ActionResponse CountWebElement(String locatorValue, String locatorType, String countKey) {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();

        /*
            It uses getWebElements to find elements and store them list
            LocatorType can have : XPATH, LINK, ID, CSS, NAME
         */
        List<WebElement> webElements = ElementFinderUtil.getWebElements(getWebDriver(), applicationLogger, locatorValue, locatorType);

        // Storing count of WebElements
        String elementCount = Integer.toString(webElements.size());

        // It stores the count in key given as parameter
        if(storeData(countKey, elementCount)) {
            actionResponse.setMessage("Web elements count '" + elementCount + "' is stored successfully in key: " + countKey + ".");
            actionResponse.setExecutionResult(ExecutionResult.PASSED);
        } else {
            actionResponse.setMessage("Failed to store web elements count in key: " + countKey + ".", FailureCategories.Category.FAILED_TO_STORE_DATA);
            actionResponse.setExecutionResult(ExecutionResult.FAILED);
        }

        return actionResponse;
    }
}