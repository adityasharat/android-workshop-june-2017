package edu.nie.expensemanager.browse;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
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
import edu.nie.expensemanager.provider.ExpenseProvider;

public class BrowseExpenseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

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

            }
        });

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.expense_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BrowseExpenseListener listener = new BrowseExpenseListener() {
            @Override
            public void openExpense(@Nullable Expense expense) {
                Intent intent = new Intent(BrowseExpenseActivity.this, ExpenseEditorActivity.class);
                intent.putExtra(ExpenseEditorActivity.KEY_EXPENSE, expense);
                startActivity(intent);
            }
        };

        adapter = new ExpenseListAdapter(listener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, ExpenseProvider.Constants.QUERY_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.setExpenses(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
