import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.webdriverfw.Wrappers.General;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;  
import java.io.FileReader;  

public class customReadExcelFile extends General {

	public ActionResponse readCSVFileByIndex(String filepath, String index, String key) {
		ActionResponse actionResponse = getActionResponse();
		ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();

		String line = "";
		String splitBy = ",";
		String data = "";
		int i = Integer.parseInt(index);

		getWebDriver().manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);

		if ((filepath.equals("")) || (filepath == null) || (filepath.isEmpty())) {
			applicationLogger.writeToInfoLog("The Key passed is not valid. Please Verify.");
		}
		if ((key.equals("")) || (key == null) || (key.isEmpty())) {
			applicationLogger.writeToInfoLog("The Key passed is not valid. Please Verify.");
		}
		try {
			//parsing a CSV file into BufferedReader class constructor
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			if ((line = br.readLine()) != null)   //returns a Boolean value
			{
				String[] file = line.split(splitBy);    // use comma as separator
				applicationLogger.writeToInfoLog(file[i]);
				data = file[i];
			}
			String result = data;
			storeData(key, result);
			applicationLogger.writeToInfoLog("Execution reached check point 2");
			actionResponse.setMessage("The data is '" + result + "' and it is stored in " + key);
		} catch (IOException e) {
			actionResponse.setMessage("Exception reading textFile." + e.getMessage());
			e.printStackTrace();
		}
		actionResponse.setExecutionResult(ExecutionResult.PASSED);
		return actionResponse;
	}
}
