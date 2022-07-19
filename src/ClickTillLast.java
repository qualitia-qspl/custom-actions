/**
* Recommendations and Rules for Custom Actions
* 1. The parameter data types supported are String and Array. 
* 2. All custom actions must return following integer values only.
*    a. If action gets passed, return value should be 0.
*    b. If action gets failed, return value should be 1.
*    c. If action has defect, return value should be 2.
*/



import java.io.IOException;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClickTillLast extends General {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// CountSubStepsInstances("D:\\Neelam\\project\\Digital_Lab_projectdb\\Upload\\Xpath.properties");
		//CountSubStep("D:\\Neelam\\project\\Digital_Lab_projectdb\\Upload\\Xpath.properties");
		ClickTillLast clickTillLast = new ClickTillLast();
		clickTillLast.ClickTillLastPage("3","(//a[@class=\"page-link\"])[2]/i");
	}


public ActionResponse ClickTillLastPage(String Number, String XpathofArrow) throws InterruptedException {
	ActionResponse actionResponse = getActionResponse();
	ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();
	actionResponse.setExecutionResult(ExecutionResult.FAILED);

	try {
	WebDriver driver = getWebDriver();
	 
	Thread.sleep(10000);	
	
	int no = Integer.parseInt(Number);
	
		for(int i=0;i<no;i++)
		{
			driver.findElement(By.xpath(XpathofArrow)).click();
			actionResponse.setMessage("Action executed successfully");
			actionResponse.setExecutionResult(ExecutionResult.PASSED);
			System.out.println("clicked "+i);
			applicationLogger.writeToInfoLog("clicked "+i+" times");
		}

		actionResponse.setMessage("Clicked to the last page");
	} catch(Exception ex) {
		actionResponse.setMessage("Exception occurred : " + ex.getMessage(), FailureCategories.Category.ACTION_FAILURE);
		applicationLogger.writeToErrorLog(ex);
		ex.printStackTrace();
	}

	return actionResponse;
}   
}