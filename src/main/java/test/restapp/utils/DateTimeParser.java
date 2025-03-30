package test.restapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeParser {
    public static final String dateFormat = "yyyy-MM-dd";
    public static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    public static Date strToDate(String date) {
        Date date1 = null;

        // first, try to parse with dateTimeFormat
        try {
            date1 = new SimpleDateFormat(dateTimeFormat).parse(date);
        }
        catch (Exception e) {}

        // if dateTimeFormat fails, try to parse with dateFormat
        if (date1 == null) {
            try {
                date1 = new SimpleDateFormat(dateFormat).parse(date);
            }
            catch (Exception e) {}
        }

        return date1;
    }
}
