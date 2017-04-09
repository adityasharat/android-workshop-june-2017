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

        // the layout to inflate
        setContentView(R.layout.activity_main);

        // find the tool bar from the above layout and assign it to toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // this method is from AppCompatActivity
        // used to support older Android OS versions
        // which did not have this fancy new toolbar
        // pre bundled
        setSupportActionBar(toolbar);

        // find the floating `+` button and assign it to fab
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_expense_button);

        // set a click listener so you can do
        // something when the fab button is clicked
        // in this case open the editor
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // open a new editor
                // for a new expense
                // hence null
                openExpenseEditor(null);
            }
        });

        // FORGET THIS FOR NOW
        // This is a super optimised View Group
        // to render scrollable views with millions
        // of rows.
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.expense_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // This is a listener that we made, which we will use
        // with the above Recycler View
        // its is an interface. Like public class Car in C++
        BrowseExpenseListener listener = new BrowseExpenseListener() {
            @Override
            public void openExpense(@Nullable Expense expense) {

                // When the user taps on any row or expense
                // We want to open that expense for editing
                // The callback is giving you that expense
                // Just open the activity to edit
                openExpenseEditor(expense);

            }
        };

        // IGNORE ALL THIS FOR NOW
        adapter = new ExpenseListAdapter(listener);
        recyclerView.addItemDecoration(new VerticalSpace(12));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // This load method starts an Async Task
        // Reads all the Expenses from the DB
        // and sets it in that Recycler View thing.
        load(adapter);
    }

    /**
     * This Method navigates to the expense editor page
     * If you pass null as the arguments it will open an empty page
     * If you pass a valid Expense, It will open that expense to edit
     */
    private void openExpenseEditor(@Nullable Expense expense) {
        // ExpenseEditorActivity is the page we want to open
        Intent intent = new Intent(BrowseExpenseActivity.this, ExpenseEditorActivity.class);

        // we are adding some data to the navigation intent
        // This is the id of you expense if it is not null
        // Else Expense.NO_ID == -1;
        // ExpenseEditorActivity.KEY_EXPENSE is just a string identifier
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
