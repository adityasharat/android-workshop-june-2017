package edu.nie.expensemanager.browse;

import android.support.annotation.Nullable;

import edu.nie.expensemanager.models.Expense;

/**
 * BrowseExpenseListener
 *
 * @author adityasharat
 */

public interface BrowseExpenseListener {

    /**
     * This callback is invoked with the {@link Expense} to open
     *
     * @param expense the expense to open
     */
    void openExpense(@Nullable Expense expense);

}
