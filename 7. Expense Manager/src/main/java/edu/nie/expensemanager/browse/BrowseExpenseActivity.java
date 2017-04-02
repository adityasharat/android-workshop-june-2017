package edu.nie.expensemanager.browse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.nie.expensemanager.R;
import edu.nie.expensemanager.database.DBHelper;
import edu.nie.expensemanager.editor.ExpenseEditorActivity;
import edu.nie.expensemanager.models.Expense;

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
        recyclerView.addItemDecoration(new VerticalSpace(5));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setExpenses(DBHelper.getAllExpense(this));
    }

    private void openExpenseEditor(@Nullable Expense expense) {
        Intent intent = new Intent(BrowseExpenseActivity.this, ExpenseEditorActivity.class);
        intent.putExtra(ExpenseEditorActivity.KEY_EXPENSE, expense != null ? expense.getId() : -1);
        startActivity(intent);
    }
}
