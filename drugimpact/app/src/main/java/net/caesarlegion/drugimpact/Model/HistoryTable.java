package net.caesarlegion.drugimpact.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Gabriel Charlebois on 2017-12-11.
 *
 * Purpose: Allow the storage of the user's current
 *  consumptions.
 */

public class HistoryTable extends Table<History> {

    //Declare some variables
    private static SimpleDateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");

    private static final String TABLE_NAME = "history";

    private static final String COLUMN_DRUG_ID = "drug_id";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_TIME_OF_CONSUMPTION = "toc";

    /**
     * Creates a table that will store drug intake
     * @param dbh
     */
    public HistoryTable(SQLiteOpenHelper dbh) {
        super(dbh, TABLE_NAME);
        addColumn((new Column(COLUMN_DRUG_ID, "INTEGER").notNull()));
        addColumn((new Column(COLUMN_AMOUNT, "DOUBLE").notNull()));
        addColumn((new Column(COLUMN_TIME_OF_CONSUMPTION, "TEXT").notNull()));
    }


    /**
     * Transforms a history object into values to be put in the database
     * @param element
     * @return
     */
    @Override
    public ContentValues toContentValues(History element) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DRUG_ID, element.getDrugId());
        values.put(COLUMN_AMOUNT, element.getAmount());
        values.put(COLUMN_TIME_OF_CONSUMPTION, iso8601.format(element.getTimeOfConsumption()));
        return values;
    }

    /**
     * Transforms a database cursor into a history object
     * @param cursor
     * @return
     * @throws DatabaseException
     */
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

    /**
     * Purpose: Get the id of a specific element
     * @param element
     * @return
     */
    @Override
    public String getId(History element) {
        return String.valueOf(element.getReminderId());
    }

    /**
     *  Create a new entry in the database given a history object.
     * @param element
     * @return
     * @throws DatabaseException
     */
    @Override
    public Long create(History element) throws DatabaseException {
        long id = super.create(element);
        element.setReminderId(id);
        return id;
    }

    /**
     *
     * @param element
     * @return
     * @throws DatabaseException
     */
    @Override
    public boolean update(History element) throws DatabaseException {
        return super.update(element);
    }
}
