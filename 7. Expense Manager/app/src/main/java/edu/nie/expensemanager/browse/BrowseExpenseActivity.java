package edu.nie.expensemanager.browse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.nie.expensemanager.R;
import edu.nie.expensemanager.models.Expense;

public class BrowseExpenseActivity extends AppCompatActivity {

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

            }
        });

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.expense_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ExpenseListAdapter adapter = new ExpenseListAdapter(new BrowseExpenseListener() {
            final private Snackbar snack = Snackbar.make(recyclerView, "open/create an expense", BaseTransientBottomBar.LENGTH_SHORT);

            @Override
            public void openExpense(@Nullable Expense expense) {
                snack.show();
            }

        });
        recyclerView.setAdapter(adapter);
    }
}
