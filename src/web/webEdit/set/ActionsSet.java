package web.webEdit.set;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.qualitia.libraries.ObjectInfo;
import com.webdriverfw.Wrappers.WebEdit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsSet extends WebEdit {

	/**
	 * This action will set the desired text which is passed as parameter in the text field
	 * i.e. the object that has been passed as the parameter.
	 * The action uses selenium's Action class to perform the set operation.
	 * Even if the action fails the Selenium's Action class does not throw any exception and
	 * hence the action is considered to be passed.
	 *
	 * @param object Text Field object
	 * @param text Text you wish to set in the edit field
	 * @return
	 */
	public ActionResponse SetUsingActionsClass(ObjectInfo object, String text) {
		ActionResponse actionResponse = getActionResponse();
		ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();

		/*
			The below exist API is used to get the desired object reference.
			The API returns null if the object is not found. So consider adding the null check.
		*/
		WebElement editField = exist(object, applicationLogger);
		if(editField == null) {
			actionResponse.setMessage("The intended object is not found. Please check locator type and value.",
					FailureCategories.Category.OBJECT_NOT_FOUND);
			actionResponse.setExecutionResult(ExecutionResult.FAILED);
		}

		/*
			The below line is to perform click operation on intended object.
			The operation is needed to bring the element in focus.
		*/
		editField.click();

		/*
			Below implementation is used to set the given text.
			If the field is not empty then the text will be appended to previous text.
			The set operation is performed on the element that is in focus.
		*/
		Actions builder = new Actions(getWebDriver());
		builder.sendKeys(text).build().perform();

		actionResponse.setMessage("Value " + text + " has been set successfully.");
		actionResponse.setExecutionResult(ExecutionResult.PASSED);
		return actionResponse;
	}
}