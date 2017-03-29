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

    public static final Uri QUERY_URI;

    static {
        QUERY_URI = new Uri.Builder().path("/expenses").build();
    }

    private ExpenseProviderHelper helper;

    public static Expense from(Cursor cursor) {
        String title = cursor.getString(Constants.INDEX_TITLE);
        String description = cursor.getString(Constants.INDEX_DESCRIPTION);
        double amount = cursor.getDouble(Constants.INDEX_AMOUNT);
        long date = cursor.getLong(Constants.INDEX_DATE);

        return new Expense(title, description, amount, date);
    }

    @Override
    public boolean onCreate() {
        helper = new ExpenseProviderHelper(getContext());
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

}
