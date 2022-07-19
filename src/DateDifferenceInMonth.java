import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;

public class DateDifferenceInMonth extends General {
	public ActionResponse CalculateDateDifferenceInMonth(String startDate, String startFormat, String endDate, String endFormat, String key)
		    throws ParseException {
		ActionResponse actionResponse = getActionResponse();
		ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();

		if ((startDate == null) || (startDate.trim().equals("")) || (endDate == null) || (endDate.trim().equals(""))) {
		  applicationLogger.writeToInfoLog("The User Start Date '" + startDate + "' or End Date '" + endDate + "' is invalid. Please verify.");
		}
		try {
		  Date startDate1 = new SimpleDateFormat(startFormat).parse(startDate);
		  Date endDate1 = new SimpleDateFormat(endFormat).parse(endDate);
		  Calendar startCalendar = new GregorianCalendar();
		  startCalendar.setTime(startDate1);
		  Calendar endCalendar = new GregorianCalendar();
		  endCalendar.setTime(endDate1);

		  int diffYear = endCalendar.get(1) - startCalendar.get(1);
		  int diffMonth = diffYear * 12 + endCalendar.get(2) - startCalendar.get(2);
		  String result = Integer.toString(diffMonth);
		  storeData(key, result);

		  actionResponse.setMessage("The Difference in Month is '" + result + "' and it is stored in " + key);
		} catch (Exception e) {
			actionResponse.setMessage("NumberFormatException:" + e.getMessage());
		}
		actionResponse.setExecutionResult(ExecutionResult.PASSED);
		return actionResponse;
	  }
}
