package net.caesarlegion.drugimpact.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Scowl Gulch on 2017-12-11.
 */

public class HistoryTable extends Table<History> {


    private static SimpleDateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");

    private static final String TABLE_NAME = "history";

    private static final String COLUMN_DRUG_ID = "drug_id";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_TIME_OF_CONSUMPTION = "toc";

    public HistoryTable(SQLiteOpenHelper dbh) {
        super(dbh, TABLE_NAME);
        addColumn((new Column(COLUMN_DRUG_ID, "INTEGER").notNull()));
        addColumn((new Column(COLUMN_AMOUNT, "DOUBLE").notNull()));
        addColumn((new Column(COLUMN_TIME_OF_CONSUMPTION, "TEXT").notNull()));
    }


    @Override
    public ContentValues toContentValues(History element) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DRUG_ID, element.getDrugId());
        values.put(COLUMN_AMOUNT, element.getAmount());
        values.put(COLUMN_TIME_OF_CONSUMPTION, iso8601.format(element.getTimeOfConsumption()));
        return values;
    }

    @Override
    public History fromCursor(Cursor cursor) throws DatabaseException {
        History his = null;
        try{
            his = new History((cursor.getLong(0)))
                    .setDrugId(cursor.getLong(1))
                    .setAmount(cursor.getDouble(2))
                    .setTimeOfConsumption(iso8601.parse(cursor.getString(3)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return his;
    }

    @Override
    public String getId(History element) {
        return String.valueOf(element.getReminderId());
    }

    @Override
    public Long create(History element) throws DatabaseException {
        long id = super.create(element);
        element.setReminderId(id);
        return id;
    }

    @Override
    public boolean update(History element) throws DatabaseException {
        return super.update(element);
    }
}
