package edu.nie.expensemanager.utility;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author nilesh.tiwari
 */

public class Utils {

    @SuppressLint("SimpleDateFormat")
    public static String toDateString(long value, @Nullable String format) {
        Date date = new Date(value);
        return new SimpleDateFormat(format != null ? format : "d MM yy").format(date);
    }

}
