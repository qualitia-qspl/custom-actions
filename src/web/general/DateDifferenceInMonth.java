package web.general;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.failureclassifications.FailureCategories;
import com.webdriverfw.Wrappers.General;

public class DateDifferenceInMonth extends General {
    /**
     * The action calculates the difference in months between the two
     * dates provided by the user and store it in the key provided.
     * User can also specify the format to be used to parse the date.
     *
     * @param startDate   - Date to be deducted
     * @param startFormat
     * @param endDate     - Date from which to deduct
     * @param endFormat
     * @param key
     * @return
     */
    public ActionResponse CalculateDateDifferenceInMonth(String startDate, String startFormat, String endDate, String endFormat, String key) {
        ActionResponse actionResponse = getActionResponse();

        try {
            /*
             * The following block of code is used to parse the given string inputs
             * to get the respective dates as per the given format
             * */
            Date startDate1 = new SimpleDateFormat(startFormat).parse(startDate);
            Date endDate1 = new SimpleDateFormat(endFormat).parse(endDate);
            Calendar startCalendar = new GregorianCalendar();
            startCalendar.setTime(startDate1);
            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(endDate1);

            /*
             * First the difference in year is calculated to find out how far apart the
             * given dates are. After calculating this, total difference in months is equal to
             * difference in months + 12 * difference in year
             * */
            int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
            int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
            String result = Integer.toString(diffMonth);

            /*
             * Below API storeData is used to store data in a key.
             * */
            storeData(key, result);

            actionResponse.setMessage("The Difference in Month is '" + result + "' and it is stored in " + key);
            actionResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Exception e) {
            actionResponse.setMessage("NumberFormatException:" + e.getMessage(), FailureCategories.Category.ACTION_FAILURE);
            actionResponse.setExecutionResult(ExecutionResult.FAILED);
        }
        return actionResponse;
    }
}
