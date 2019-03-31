
package test.dto ; 
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
 
public class DateUtils {
    
    // The date formatter
    // - dd:   day in month (number)
    // - MM:   month in year (number)
    // - yyyy: year
    //
    // See this link for details: https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html
    //
    //
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
   // private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    
    // read a date string and parse/convert to a date
    public static LocalDate parseDate(String dateStr) throws ParseException {
    	LocalDate localDate = LocalDate.parse(dateStr, formatter);
        
        return localDate;        
    }
    
    // read a date and format/convert to a string
    public static String formatDate(LocalDate theDate) {
        
        String result = null;
        
        if (theDate != null) {
            result = formatter.format(theDate);
        }
        
        return result;
    }
}
