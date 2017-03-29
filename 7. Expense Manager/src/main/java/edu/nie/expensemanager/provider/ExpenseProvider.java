package edu.nie.expensemanager.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import edu.nie.expensemanager.models.Expense;

/**
 * ExpenseProvider
 *
 * @author adityasharat
 */

public class ExpenseProvider extends ContentProvider {

    public static Expense from(Cursor cursor) {
        String title = cursor.getString(ExpenseProvider.Constants.TITLE);
        String description = cursor.getString(ExpenseProvider.Constants.DESCRIPTION);
        double amount = cursor.getDouble(ExpenseProvider.Constants.AMOUNT);
        long date = cursor.getLong(ExpenseProvider.Constants.AMOUNT);

        return new Expense(title, description, amount, date);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public static final class Constants {

        public static final Uri QUERY_URI;

        public static final int TITLE = 1;
        public static final int DESCRIPTION = 2;
        public static final int AMOUNT = 3;
        public static final int DATE = 4;

        static {
            QUERY_URI = new Uri.Builder().path("/expenses").build();
        }

    }
}
