package net.caesarlegion.drugimpact.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Gabriel Charlebois on 2017-12-12.
 * Purpose: We want to create and access a database in the device.
 */

public class HistoryDatabaseHandler extends SQLiteOpenHelper {


    private static final int VERSION = 1;

    private static final String DATABASE_PREFIX = "history";
    private static final String DATABASE_SUFFIX = ".db";

    public HistoryTable historyTable;

    public HistoryDatabaseHandler(Context context, String userId, String key) {

        //Using the given user id, we construct a specific name for the database.

        //super(context, DATABASE_PREFIX + userId + DATABASE_SUFFIX, null, VERSION);

        //But that causes errors upon changing user, so we use the same database for any user
        super(context, DATABASE_PREFIX + DATABASE_SUFFIX, null, VERSION);

        //We pass into the history table, the key that will encrypt all its data.

        //historyTable = new HistoryTable(this, key);

        //But that causes errors upon changing user, so we use the same key for any user
        historyTable = new HistoryTable(this, "invalidkey");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(historyTable.getCreateTableStatement());

        if (historyTable.hasInitialData())
                historyTable.initialize(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(historyTable.getCreateTableStatement());
        onCreate(db);
    }
}
