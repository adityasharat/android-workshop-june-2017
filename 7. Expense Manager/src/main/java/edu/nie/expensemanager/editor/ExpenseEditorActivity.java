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
import android.widget.Toast;

import java.util.Calendar;

import edu.nie.expensemanager.R;
import edu.nie.expensemanager.models.Expense;
import edu.nie.expensemanager.provider.ExpenseProvider;
import edu.nie.expensemanager.provider.ExpenseProviderConstants;
import edu.nie.expensemanager.utility.Utils;

public class ExpenseEditorActivity extends AppCompatActivity {

    public static final String KEY_EXPENSE = "exp";

    EditText descriptionView;
    EditText amountView;
    EditText dateView;
    EditText titleView;

    private long id = Expense.NO_ID;
    private boolean isEditFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_expense_editor);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id = getIntent().getLongExtra(KEY_EXPENSE, Expense.NO_ID);

        titleView = (EditText) findViewById(R.id.title);
        descriptionView = (EditText) findViewById(R.id.description);
        amountView = (EditText) findViewById(R.id.amount);
        dateView = (EditText) findViewById(R.id.date);

        long time = System.currentTimeMillis();
        dateView.setText(Utils.toDateString(time, null));
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(KEY_EXPENSE, id);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            id = savedInstanceState.getLong(KEY_EXPENSE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) throws IllegalArgumentException {
        switch (item.getItemId()) {
            case R.id.delete:
                // TODO: delete expense
                Toast.makeText(this, "Expense deleted", Toast.LENGTH_LONG).show();
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onDateSelect(View v) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(ExpenseEditorActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar temp = Calendar.getInstance();
                temp.set(year, monthOfYear, dayOfMonth);
                long value = temp.getTimeInMillis();
                dateView.setText(Utils.toDateString(value, null));
                dateView.setTag(value);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void onAddExpense(View v) {
        if (amountView.getText().toString().trim().length() == 0) {
            amountView.setError("Amount can't be empty");
            return;
        }

        String title = titleView.getText().toString();
        String description = descriptionView.getText().toString();
        double amount = Double.parseDouble(amountView.getText().toString());
        long date = (long) dateView.getTag();

        Expense expense;
        if (isEditFlow) {
            expense = new Expense(id, title, description, amount, date);
        } else {
            expense = new Expense(title, description, amount, date);
        }
        save(expense);
    }

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
                resolver.insert(ExpenseProviderConstants.EXPENSE_URI, values);
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

    private void setExpense(@NonNull Expense expense) {
        titleView.setText(expense.title);
        descriptionView.setText(expense.description);
        amountView.setText(String.valueOf(expense.amount));
        dateView.setText(Utils.toDateString(expense.date, null));
    }
}
