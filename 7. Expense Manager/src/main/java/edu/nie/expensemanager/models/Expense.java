package edu.nie.expensemanager.models;

import android.support.annotation.NonNull;

import com.turbomanage.storm.api.Entity;

/**
 * Expense
 *
 * @author adityasharat
 */
@Entity
public class Expense {

    public String title;

    public String description;

    public double amount;

    public long date;

    private long id;

    public Expense() {
    }

    public Expense(@NonNull String title, @NonNull String description, double amount, long date) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
