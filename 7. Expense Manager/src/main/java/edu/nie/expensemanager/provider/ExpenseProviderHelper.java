package edu.nie.expensemanager.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * ExpenseProviderHelper
 *
 * @author adityasharat
 */

public class ExpenseProviderHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Expenses.db";

    private static final String[] projection = {
            ExpenseProviderConstants._ID,
            ExpenseProviderConstants.TITLE,
            ExpenseProviderConstants.DESCRIPTION,
            ExpenseProviderConstants.AMOUNT,
            ExpenseProviderConstants.DATE,
    };

    public ExpenseProviderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ExpenseProviderConstants.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(ExpenseProviderConstants.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public Cursor query() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(ExpenseProviderConstants.TABLE_NAME, projection, null, null, null, null, "_ID ASC");
    }

    public Cursor query(@NonNull String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = ExpenseProviderConstants._ID + " = ?";
        String[] selectionArgs = {id};
        return db.query(ExpenseProviderConstants.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
    }

    public long insert(@NonNull ContentValues values) {
        return this.getWritableDatabase().insert(ExpenseProviderConstants.TABLE_NAME, null, values);
    }

    public int delete() {
        return getWritableDatabase().delete(ExpenseProviderConstants.TABLE_NAME, null, null);
    }

    public int delete(@NonNull String id) {
        String whereClause = ExpenseProviderConstants._ID + " = ?";
        String[] whereArgs = {id};
        return getWritableDatabase().delete(ExpenseProviderConstants.TABLE_NAME, whereClause, whereArgs);
    }

    public int update(@NonNull String id, @NonNull ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = ExpenseProviderConstants._ID + " = ?";
        String[] whereArgs = {id};
        return db.update(ExpenseProviderConstants.TABLE_NAME, values, whereClause, whereArgs);
    }
}
