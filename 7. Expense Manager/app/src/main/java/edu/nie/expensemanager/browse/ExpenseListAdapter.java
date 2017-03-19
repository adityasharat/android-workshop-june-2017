package edu.nie.expensemanager.browse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.nie.expensemanager.R;
import edu.nie.expensemanager.models.Expense;

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
    private List<Expense> expenses;

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
                ((ExpenseViewHolder) holder).bind(expenses.get(position));
            } else {
                throw new IllegalStateException(ERROR_NULL_EXPENSES + position);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (expenses == null || expenses.size() == 0) {
            return 1;
        }
        return expenses.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (expenses == null || expenses.size() == 0) {
            return TYPE_NO_EXPENSE;
        }
        return TYPE_EXPENSE;
    }

    public void setExpenses(@Nullable List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void addExprense(@NonNull Expense expense) {
        if (this.expenses == null) {
            this.expenses = new ArrayList<>();
        }
        this.expenses.add(expense);
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

        private Expense expense;

        ExpenseViewHolder(@NonNull View view, @NonNull final BrowseExpenseListener listener) {
            super(view, listener);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.openExpense(expense);
                }
            });
        }

        static ExpenseViewHolder create(@NonNull ViewGroup parent, @NonNull LayoutInflater inflater, final @NonNull BrowseExpenseListener listener) {
            View view = inflater.inflate(R.layout.expense_list_item, parent, false);
            return new ExpenseViewHolder(view, listener);
        }

        private void bind(@Nullable Expense expense) {
            this.expense = expense;
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
