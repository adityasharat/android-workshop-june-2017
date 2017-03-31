package edu.nie.expensemanager.database;

/**
 * Created by nilesh.tiwari on 29-03-2017.
 */

import android.content.Context;

import com.turbomanage.storm.DatabaseHelper;
import com.turbomanage.storm.api.Database;
import com.turbomanage.storm.api.DatabaseFactory;

@Database(name = ExpenseDBManager.DB_NAME, version = ExpenseDBManager.NEW_DB_VERSION)
public class ExpenseDBManager extends DatabaseHelper {

    public static final String DB_NAME = "expensedb";
    public static final int NEW_DB_VERSION = 1;


    public ExpenseDBManager(Context ctx, DatabaseFactory dbFactory) {
        super(ctx, dbFactory);
    }

    @Override
    public UpgradeStrategy getUpgradeStrategy() {
        return UpgradeStrategy.BACKUP_RESTORE;
    }

}

