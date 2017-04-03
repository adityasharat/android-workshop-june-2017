package edu.nie.expensemanager.models;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import edu.nie.expensemanager.provider.ExpenseProvider;

import com.turbomanage.storm.api.Entity;

/**
 * Expense
 *
 * @author adityasharat
 */
@Entity
public class Expense {

    private long id;

    @NonNull
    public String title;

    @NonNull
    public String description;

    public double amount;

    public long date;

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

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
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
