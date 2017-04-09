package edu.nie.expensemanager.editor;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import edu.nie.expensemanager.R;
import edu.nie.expensemanager.models.Expense;
import edu.nie.expensemanager.provider.ExpenseProvider;
import edu.nie.expensemanager.provider.ExpenseProviderConstants;
import edu.nie.expensemanager.utility.Utils;

public class ExpenseEditorActivity extends AppCompatActivity {

    public static final String KEY_EXPENSE = "exp";

    // These are the fields we are talking
    // about below. Just like C++ class memebers
    private EditText descriptionView;
    private EditText amountView;
    private TextView dateView;
    private EditText titleView;

    private long id = Expense.NO_ID;
    private boolean isEditFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_expense_editor);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the id we passed in the intent
        // Use the same String KEY_EXPENSE.
        // the second argument is basically
        // what should be returned if KEY_EXPENSE
        // was not even present in the intent data
        id = getIntent().getLongExtra(KEY_EXPENSE, Expense.NO_ID);

        // Find all the view we need from the activity_expense_editor layout
        // These are fields not variables
        titleView = (EditText) findViewById(R.id.title);
        descriptionView = (EditText) findViewById(R.id.description);
        amountView = (EditText) findViewById(R.id.amount);
        dateView = (TextView) findViewById(R.id.date);

        // Lets get the current time in ms
        // see https://en.wikipedia.org/wiki/Unix_time to understand
        // which is a long value
        long time = System.currentTimeMillis();

        // Lets convert that into a human read able form
        // and set it on the data view.
        dateView.setText(Utils.toDateString(time, null));

        // Also lets save the epoch time inside the dateView
        // So that we can get it when we are going to save the expense.
        dateView.setTag(time);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (id != Expense.NO_ID) {
            isEditFlow = true;
            setTitle("Edit Expense");
            load(id);
        }
    }

    // IGNORE
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(KEY_EXPENSE, id);
        super.onSaveInstanceState(outState);
    }

    // IGNORE
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            id = savedInstanceState.getLong(KEY_EXPENSE);
        }
    }

    /**
     * Lets declare what should be the menu on the top
     * Right of the screen.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor_menu, menu);
        return true;
    }

    /**
     * Lets define what we will do when user clicks
     * on any item of the menu.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // If user clicks on the delete button
            case R.id.delete:
                if (isEditFlow) {
                    delete(id);
                } else {
                    Toast.makeText(this, "Expense deleted", Toast.LENGTH_LONG).show();
                    this.finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when the user clicks on the Calendar Button
     * @param v
     */
    public void onDateSelect(View v) {
        // Get the current date, month and year
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        // This is an in build class from Android which can be used to
        // show a floating dialog to pick a date. It takes 2 arguments
        // The page where you want to show the dialog (the activity)
        // and a listener which has a method `onDateSet` which is called
        // when user clicks on the `OK` button on the dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(ExpenseEditorActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                // Lets convert the human readable date
                // back to epoch time in milliseconds.
                Calendar temp = Calendar.getInstance();
                temp.set(year, monthOfYear, dayOfMonth);
                long value = temp.getTimeInMillis();

                // Lets set the human readable form into the dateView
                dateView.setText(Utils.toDateString(value, null));

                // Also lets save the epoch time inside the dateView
                // So that we can get it when we are going to save the expense.
                dateView.setTag(value);
            }
        }, mYear, mMonth, mDay);

        // Show this dialog to the user
        datePickerDialog.show();
    }

    /**
     * Called when the user clicks on the
     * save button.
     * @param v
     */
    public void onAddExpense(View v) {
        String title = titleView.getText().toString().trim();
        if (title.length() == 0) {
            titleView.setError("Title can't be empty");
        }

        String string = amountView.getText().toString().trim();
        if (string.length() == 0) {
            amountView.setError("Amount can't be empty");
            return;
        }
        String description = descriptionView.getText().toString().trim();
        double amount = Double.parseDouble(string);

        // Get the epoch time from the date view
        // we saved it earlier
        long date = (long) dateView.getTag();

        Expense expense;
        if (isEditFlow) {
            expense = new Expense(id, title, description, amount, date);
        } else {
            expense = new Expense(title, description, amount, date);
        }
        save(expense);
    }

    /**
     * Starts an Async Task to load a single Expense
     * with the id passed in the arguments
     * @param id
     */
    private void load(final long id) {
        new AsyncTask<Context, Void, Cursor>() {

            @Override
            protected Cursor doInBackground(Context... params) {
                return params[0].getContentResolver().query(ExpenseProviderConstants.build(id), null, null, null, null);
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                cursor.moveToFirst();
                setExpense(ExpenseProvider.from(cursor));
            }
        }.execute(this);
    }

    /**
     * Starts an Async Task to save a single Expense
     * @param expense
     */
    private void save(@NonNull final Expense expense) {
        new AsyncTask<Context, Void, Void>() {
            @Override
            protected Void doInBackground(Context... params) {

                ContentResolver resolver = params[0].getContentResolver();

                ContentValues values = new ContentValues();
                values.put(ExpenseProviderConstants.TITLE, expense.title);
                values.put(ExpenseProviderConstants.DESCRIPTION, expense.description);
                values.put(ExpenseProviderConstants.AMOUNT, expense.amount);
                values.put(ExpenseProviderConstants.DATE, expense.date);

                if (expense.id == Expense.NO_ID) {
                    resolver.insert(ExpenseProviderConstants.EXPENSE_URI, values);
                } else {
                    resolver.update(ExpenseProviderConstants.build(expense.id), values, null, null);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(ExpenseEditorActivity.this, R.string.expense_saved_msg, Toast.LENGTH_LONG).show();
                ExpenseEditorActivity.this.finish();
            }
        }.execute(this);
    }

    /**
     * Starts an Async Task to delete a single Expense with
     * passed id
     * @param id
     */
    private void delete(final long id) {
        new AsyncTask<Context, Void, Void>() {

            @Override
            protected Void doInBackground(Context... params) {
                params[0].getContentResolver().delete(ExpenseProviderConstants.build(id), null, null);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(ExpenseEditorActivity.this, "Expense deleted", Toast.LENGTH_LONG).show();
                ExpenseEditorActivity.this.finish();
            }
        }.execute(this);
    }

    /**
     * Given an expense
     * Set it on a editor page
     * @param expense
     */
    private void setExpense(@NonNull Expense expense) {
        titleView.setText(expense.title);
        descriptionView.setText(expense.description);
        amountView.setText(String.valueOf(expense.amount));
        dateView.setText(Utils.toDateString(expense.date, null));
    }
}
