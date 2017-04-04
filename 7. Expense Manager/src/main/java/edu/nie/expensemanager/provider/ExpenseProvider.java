package edu.nie.expensemanager.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import edu.nie.expensemanager.BuildConfig;
import edu.nie.expensemanager.models.Expense;

/**
 * ExpenseProvider
 *
 * @author adityasharat
 */
public class ExpenseProvider extends ContentProvider {

    // Creates a UriMatcher.
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        /*
         * The calls to addURI() go here, for all of the content URI
         * patterns that the provider should recognize.
         */

        /*
         * Sets the integer value for multiple rows in expense table to 1.
         * Notice that no wildcard is used in the path
         */
        sUriMatcher.addURI(BuildConfig.APPLICATION_ID, "expenses", 1);
        /*
         * Sets the code for a single row to 2. In this case, the "#" wildcard is
         * used. "content://com.example.app.provider/expenses/3" matches, but
         * "content://com.example.app.provider/expenses doesn't.
         */
        sUriMatcher.addURI(BuildConfig.APPLICATION_ID, "expenses/#", 2);
    }

    private ExpenseProviderHelper helper;

    public static Expense from(Cursor cursor) {
        long id = cursor.getLong(ExpenseProviderConstants.INDEX_ID);
        String title = cursor.getString(ExpenseProviderConstants.INDEX_TITLE);
        String description = cursor.getString(ExpenseProviderConstants.INDEX_DESCRIPTION);
        double amount = cursor.getDouble(ExpenseProviderConstants.INDEX_AMOUNT);
        long date = cursor.getLong(ExpenseProviderConstants.INDEX_DATE);

        return new Expense(id, title, description, amount, date);
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
        switch (sUriMatcher.match(uri)) {
            // If the incoming URI was for all of expenses
            case 1:
                return helper.query();
            // If the incoming URI was for a single row
            case 2:
                /*
                 * Because this URI was for a single row, the _ID value part is
                 * present. Get the last path segment from the URI; this is the _ID value.
                 * Then, append the value to the WHERE clause for the query
                 */
                return helper.query(uri.getLastPathSegment());
            default:
                throw new IllegalArgumentException("invalid uri passed: " + uri.toString());
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        helper.insert(values);
        return ExpenseProviderConstants.EXPENSE_URI;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            // If the incoming URI was for all of expenses
            case 1:
                return helper.delete();
            // If the incoming URI was for a single row
            case 2:
                /*
                 * Because this URI was for a single row, the _ID value part is
                 * present. Get the last path segment from the URI; this is the _ID value.
                 * Then, append the value to the WHERE clause for the query
                 */
                return helper.delete(uri.getLastPathSegment());
            default:
                throw new IllegalArgumentException("invalid uri passed: " + uri.toString());
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (values != null && sUriMatcher.match(uri) == 2) {
            return helper.update(uri.getLastPathSegment(), values);
        }
        return 0;
    }

}
