package edu.nie.expensemanager.browse;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.nie.expensemanager.R;
import edu.nie.expensemanager.models.Expense;
import edu.nie.expensemanager.provider.ExpenseProvider;
import edu.nie.expensemanager.utility.Utils;

/**
 * ExpenseListAdapter
 *
 * @author adityasharat
 */

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ExpenseBaseViewHolder> {

    private static final int TYPE_EXPENSE = 0;
    private static final int TYPE_NO_EXPENSE = 1;
    private static final String ERROR_NULL_EXPENSES = "expenses is null but bind called for position ";
    private static final String ERROR_INVALID_VIEW_TYPE = " is an invalid view type";

    @Nullable
    private Cursor expenses;

    private LayoutInflater inflater;

    @NonNull
    private BrowseExpenseListener listener;

    ExpenseListAdapter(@NonNull BrowseExpenseListener listener) {
        this.listener = listener;
    }

    @Override
    public ExpenseBaseViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        ExpenseBaseViewHolder holder;

        switch (type) {
            case TYPE_EXPENSE:
                holder = ExpenseViewHolder.create(parent, inflater, listener);
                break;
            case TYPE_NO_EXPENSE:
                holder = EmptyExpenseListViewHolder.create(parent, inflater, listener);
                break;
            default:
                throw new IllegalArgumentException(type + ERROR_INVALID_VIEW_TYPE);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(ExpenseBaseViewHolder holder, int position) {
        if (holder instanceof ExpenseViewHolder) {
            if (expenses != null) {
                expenses.moveToPosition(position);
                ((ExpenseViewHolder) holder).bind(ExpenseProvider.from(expenses));
            } else {
                throw new IllegalStateException(ERROR_NULL_EXPENSES + position);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (expenses == null || expenses.getCount() == 0) {
            return 1;
        }
        return expenses.getCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (expenses == null || expenses.getCount() == 0) {
            return TYPE_NO_EXPENSE;
        }
        return TYPE_EXPENSE;
    }

    public void setExpenses(@Nullable Cursor expenses) {
        this.expenses = expenses;
        notifyDataSetChanged();
    }

    static class ExpenseBaseViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        protected final BrowseExpenseListener listener;

        ExpenseBaseViewHolder(@NonNull View view, @NonNull BrowseExpenseListener listener) {
            super(view);
            this.listener = listener;
        }
    }

    private static class ExpenseViewHolder extends ExpenseBaseViewHolder {

        @NonNull
        private TextView title;

        @NonNull
        private TextView amt;

        @NonNull
        private TextView date;

        private Expense expense;

        ExpenseViewHolder(@NonNull View view, @NonNull final BrowseExpenseListener listener) {
            super(view, listener);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.openExpense(expense);
                }
            });
            title = (TextView) view.findViewById(R.id.expense_title);
            amt = (TextView) view.findViewById(R.id.expense_amount);
            amt.setTypeface(null, Typeface.BOLD);
            date = (TextView) view.findViewById(R.id.expense_date);
        }

        static ExpenseViewHolder create(@NonNull ViewGroup parent, @NonNull LayoutInflater inflater, final @NonNull BrowseExpenseListener listener) {
            View view = inflater.inflate(R.layout.expense_list_item, parent, false);
            return new ExpenseViewHolder(view, listener);
        }

        private void bind(@NonNull Expense expense) {
            this.expense = expense;
            title.setText(expense.description);
            amt.setText("₹ " + String.valueOf((int) expense.amount));
            date.setText(Utils.toDateString(expense.date, null));
        }
    }

    private static class EmptyExpenseListViewHolder extends ExpenseBaseViewHolder {

        EmptyExpenseListViewHolder(@NonNull View view, @NonNull BrowseExpenseListener listener) {
            super(view, listener);
        }

        static EmptyExpenseListViewHolder create(@NonNull ViewGroup parent, @NonNull LayoutInflater inflater, @NonNull final BrowseExpenseListener listener) {
            View view = inflater.inflate(R.layout.no_expenses, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.openExpense(null);
                }
            });
            return new EmptyExpenseListViewHolder(view, listener);
        }
    }
}
