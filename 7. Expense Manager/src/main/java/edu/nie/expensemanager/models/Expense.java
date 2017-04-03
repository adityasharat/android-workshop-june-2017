package edu.nie.expensemanager.models;

import android.support.annotation.NonNull;

/**
 * Expense
 *
 * @author adityasharat
 */

public class Expense {

    public static final long NO_ID = -1;

    public final long id;

    @NonNull
    public String title;

    @NonNull
    public String description;

    public double amount;

    public long date;

    public Expense(@NonNull String title, @NonNull String description, double amount, long date) {
        this.id = -1;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Expense(long id, @NonNull String title, @NonNull String description, double amount, long date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public static Expense setId(Expense expense, long id) {
        return new Expense(id, expense.title, expense.description, expense.amount, expense.date);
    }

}
