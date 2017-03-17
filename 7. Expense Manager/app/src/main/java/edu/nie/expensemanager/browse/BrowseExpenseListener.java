package edu.nie.expensemanager.browse;

import android.support.annotation.Nullable;

import edu.nie.expensemanager.models.ExpenseSummary;

/**
 * BrowseExpenseListener
 *
 * @author adityasharat
 */

public interface BrowseExpenseListener {

    /**
     * This callback is invoked with the {@link ExpenseSummary} to open
     *
     * @param expense the expense to open
     */
    void openExpense(@Nullable ExpenseSummary expense);

}
