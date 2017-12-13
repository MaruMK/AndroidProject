package net.caesarlegion.drugimpact.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gabriel Charlebois on 2017-12-12.
 */

public class HistoryDatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "history.db";
    private static final int VERSION = 1;

    public HistoryTable historyTable;

    public HistoryDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        historyTable = new HistoryTable(this);
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
