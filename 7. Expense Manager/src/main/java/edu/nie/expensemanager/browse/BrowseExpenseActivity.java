package edu.nie.expensemanager.browse;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.nie.expensemanager.R;
import edu.nie.expensemanager.editor.ExpenseEditorActivity;
import edu.nie.expensemanager.models.Expense;
import edu.nie.expensemanager.provider.ExpenseProviderConstants;

public class BrowseExpenseActivity extends AppCompatActivity {

    private ExpenseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_expense_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExpenseEditor(null);
            }
        });

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.expense_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BrowseExpenseListener listener = new BrowseExpenseListener() {
            @Override
            public void openExpense(@Nullable Expense expense) {
                openExpenseEditor(expense);
            }
        };

        adapter = new ExpenseListAdapter(listener);
        recyclerView.addItemDecoration(new VerticalSpace(12));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        load(adapter);
    }

    private void openExpenseEditor(@Nullable Expense expense) {
        Intent intent = new Intent(BrowseExpenseActivity.this, ExpenseEditorActivity.class);
        intent.putExtra(ExpenseEditorActivity.KEY_EXPENSE, expense != null ? expense.id : Expense.NO_ID);
        startActivity(intent);
    }

    private void load(final ExpenseListAdapter adapter) {
        new AsyncTask<Context, Void, Cursor>() {

            @Override
            protected Cursor doInBackground(Context... params) {
                return params[0].getContentResolver().query(ExpenseProviderConstants.EXPENSE_URI, null, null, null, null);
            }

            @Override
            protected void onPostExecute(Cursor expenses) {
                super.onPostExecute(expenses);
                adapter.setExpenses(expenses);
            }
        }.execute(this);
    }
}
