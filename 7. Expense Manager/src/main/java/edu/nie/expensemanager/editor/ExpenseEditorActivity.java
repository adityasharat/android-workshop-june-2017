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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.nie.expensemanager.R;
import edu.nie.expensemanager.database.DBHelper;
import edu.nie.expensemanager.models.Expense;
import edu.nie.expensemanager.utility.Utils;

public class ExpenseEditorActivity extends AppCompatActivity {

    public static final String KEY_EXPENSE = "exp";
    EditText description;
    EditText amount;
    EditText date;
    EditText title;
    private DatePickerDialog datePickerDialog;
    private Expense expense;
    private long id;
    private boolean isEditFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_editor);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id = getIntent().getLongExtra(KEY_EXPENSE, -1);
        date = (EditText) findViewById(R.id.date);
        title = (EditText) findViewById(R.id.title);
        amount = (EditText) findViewById(R.id.amount);
        description = (EditText) findViewById(R.id.description);

        if (id != -1) {
            isEditFlow = true;
            setTitle("Edit Expense");
            expense = DBHelper.getExpense(this, id);
            setUpExpense(expense);
        }
    }

    private void setUpExpense(Expense expense) {
        date.setText(Utils.toDateString(expense.getDate(), null));
        description.setText(expense.getDescription());
        title.setText(expense.getTitle());
        amount.setText(String.valueOf(expense.getAmount()));
    }

    public void onDateSelect(View v) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(ExpenseEditorActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        date.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);

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
                DBHelper.deleteExpense(this, id);
                Toast.makeText(this, "Expense deleted", Toast.LENGTH_LONG).show();
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddExpense(View v) {
        if (amount.getText().toString().trim().length() == 0) {
            amount.setError("Amount can't be empty");
            return;
        }
        if (date.getText().toString().trim().length() == 0) {
            date.setError("Date can't be empty");
            return;
        }
        if (description.getText().toString().trim().length() == 0) {
            description.setError("Description can't be empty.");
            return;
        }
        Expense expense = new Expense();
        if (isEditFlow) {
            expense.setId(id);
        }
        expense.setAmount(Double.parseDouble(amount.getText().toString()));
        expense.setDescription(description.getText().toString());
        expense.setTitle(title.getText().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            Date parsedDate = simpleDateFormat.parse(date.getText().toString());
            expense.setDate(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.oops_msg, Toast.LENGTH_LONG).show();
            return;
        }
        if (isEditFlow) {
            DBHelper.update(this, expense);
        } else {
            DBHelper.saveExpense(this, expense);
        }
        Toast.makeText(this, R.string.expense_saved_msg, Toast.LENGTH_LONG).show();
        this.finish();
    }
}
