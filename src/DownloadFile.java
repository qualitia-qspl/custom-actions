/**
* Recommendations and Rules for Custom Actions
* 1. The parameter data types supported are String and Array. 
* 2. All custom actions must return following integer values only.
*    a. If action gets passed, return value should be 0.
*    b. If action gets failed, return value should be 1.
*    c. If action has defect, return value should be 2.
*/



import java.io.File;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;
public class DownloadFile extends General {
	
    public ActionResponse verifyDownloadedFile(String fileName, String strKey) {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();

        String home = System.getProperty("user.home");

        boolean flag = false;
        File dir = new File(home + "/Downloads/");
        File[] files = dir.listFiles();
        if ((files == null) || (files.length == 0)) {
          flag = false;
        }
        for (int i = 1; i < files.length; i++) {
          if (files[i].getName().contains(fileName)) {
            flag = true;
          }
        }
        String strData = String.valueOf(flag);
        System.out.println(strData);
        if (storeData(strKey, strData))
        {
          actionResponse.setMessage("File Presence'" + strData + "' is stored successfuly in variable:" + strKey + ".");
          actionResponse.setExecutionResult(ExecutionResult.PASSED);
          return actionResponse;
        }
        actionResponse.setMessage("File Presence '" + strData + "' is stored successfuly in variable:" + strKey + ".", FailureCategories.Category.FAILED_TO_STORE_DATA);
        actionResponse.setExecutionResult(ExecutionResult.FAILED);
        return actionResponse;
    }
}