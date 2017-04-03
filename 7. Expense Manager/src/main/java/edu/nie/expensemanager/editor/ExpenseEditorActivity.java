package edu.nie.expensemanager.editor;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
import edu.nie.expensemanager.utility.Utils;

public class ExpenseEditorActivity extends AppCompatActivity {

    public static final String KEY_EXPENSE = "exp";

    EditText descriptionView;
    EditText amountView;
    EditText dateView;
    EditText titleView;

    private long id;
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

        if (id != Expense.NO_ID) {
            isEditFlow = true;
            // TODO: read expense
            Expense expense = new Expense(-1, "", "", 0, System.currentTimeMillis());
            setTitle("Edit Expense");
            setUpExpense(expense);
        }
    }

    private void setUpExpense(Expense expense) {
        titleView.setText(expense.title);
        descriptionView.setText(expense.description);
        amountView.setText(String.valueOf(expense.amount));
        dateView.setText(Utils.toDateString(expense.date, null));
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

        //TODO: write expense

        Toast.makeText(this, R.string.expense_saved_msg, Toast.LENGTH_LONG).show();
        this.finish();
    }
}
