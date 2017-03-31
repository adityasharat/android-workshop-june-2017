package edu.nie.expensemanager.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nilesh.tiwari on 31-03-2017.
 */

public class Utils {
    public static String getDateString(long dateLong){
        Date date=new Date(dateLong);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        String dateText = df2.format(date);
        return dateText;
    }
}
