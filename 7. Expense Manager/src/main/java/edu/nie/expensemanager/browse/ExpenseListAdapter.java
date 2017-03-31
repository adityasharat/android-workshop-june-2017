package edu.nie.expensemanager.browse;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    private List<Expense> expenseList;

    @Nullable
    private Cursor expenses;

    private LayoutInflater inflater;

    @NonNull
    private BrowseExpenseListener listener;

    ExpenseListAdapter(@NonNull BrowseExpenseListener listener) {
        this.listener = listener;
    }

    public void setExpenseList(List<Expense> expenseList){
        this.expenseList = expenseList;
        notifyDataSetChanged();
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
            /*if (expenses != null) {
                expenses.moveToPosition(position);
                ((ExpenseViewHolder) holder).bind(ExpenseProvider.from(expenses));
            } else {
                throw new IllegalStateException(ERROR_NULL_EXPENSES + position);
            }*/
            ((ExpenseViewHolder) holder).bind(expenseList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        /*if (expenses == null || expenses.getCount() == 0) {
            return 1;
        }*/
        if (expenseList == null || expenseList.size() == 0) {
            return 1;
        }
        return expenseList.size();
    }

    @Override
    public int getItemViewType(int position) {
        /*if (expenses == null || expenses.getCount() == 0) {
            return TYPE_NO_EXPENSE;
        }*/
        if (expenseList == null || expenseList.size() == 0) {
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

        private static TextView expenseDescription;
        private static TextView expenseAmount;
        private static TextView expenseDate;
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
            expenseDescription = (TextView) view.findViewById(R.id.expense_description);
            expenseAmount = (TextView) view.findViewById(R.id.expense_amount);
            expenseDate = (TextView) view.findViewById(R.id.expense_date);
            return new ExpenseViewHolder(view, listener);
        }

        private void bind(@Nullable Expense expense) {
            this.expense = expense;
            expenseDescription.setText(expense.getDescription());
            expenseAmount.setText(expense.getAmount()+"");
            expenseDate.setText(Utils.getDateString(expense.getDate()));
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
