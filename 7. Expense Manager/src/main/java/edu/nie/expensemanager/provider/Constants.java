package edu.nie.expensemanager.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Constants
 *
 * @author adityasharat
 */
public final class Constants implements BaseColumns {



    public static final int INDEX_TITLE = 1;
    public static final int INDEX_DESCRIPTION = 2;
    public static final int INDEX_AMOUNT = 3;
    public static final int INDEX_DATE = 4;

    public static final String TABLE_NAME = "expenses";

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "desc";
    public static final String AMOUNT = "amt";
    public static final String DATE = "date";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    TITLE + " TEXT," +
                    DESCRIPTION + " TEXT" +
                    AMOUNT + "REAL," +
                    DATE + "REAL" +
                    ")";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    /**
     * To prevent someone from accidentally instantiating
     * the contract class,  make the constructor private.
     */
    private Constants() {
    }

}
