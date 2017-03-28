package edu.nie.expensemanager.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Expense
 *
 * @author adityasharat
 */

public class Expense implements Parcelable {

    @NonNull
    public final String title;

    @NonNull
    public final String description;

    public final double amount;

    public final long date;

    public Expense(@NonNull String title, @NonNull String description, double amount, long date) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
