package edu.nie.expensemanager.database;

import android.content.Context;

import java.util.List;

import edu.nie.expensemanager.models.Expense;
import edu.nie.expensemanager.models.dao.ExpenseDao;

/**
 * Created by nilesh.tiwari on 29-03-2017.
 */

public class DBHelper {
    public static boolean saveExpense(Context context, Expense expense){
        ExpenseDao expenseDao = new ExpenseDao(context);
        long id = expenseDao.insert(expense);
        if(id>0){
            return true;
        }
        return false;
    }

    public static boolean update(Context context, Expense expense){
        ExpenseDao expenseDao = new ExpenseDao(context);
        long id = expenseDao.update(expense);
        if(id>0){
            return true;
        }
        return false;
    }

    public static Expense getExpense(Context context, long id){
        ExpenseDao expenseDao = new ExpenseDao(context);
        Expense expense = expenseDao.get(id);
        return expense;
    }

    public static void deleteExpense(Context context, long id){
        ExpenseDao expenseDao = new ExpenseDao(context);
        expenseDao.delete(id);
    }

    public static List<Expense> getAllExpense(Context context) {
        ExpenseDao expenseDao = new ExpenseDao(context);
        return expenseDao.listAll();
    }
}
