package com.assignment.individual.settlelah.activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.assignment.individual.settlelah.activity.Model.Result;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "split_bill.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "split_results";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TOTAL_AMOUNT = "total_amount";
    public static final String COLUMN_NUM_PEOPLE = "num_people";
    public static final String COLUMN_INDIVIDUAL_SHARE = "individual_share";
    private static final String COLUMN_SHARE_AMOUNT = "share_amount";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TOTAL_AMOUNT + " REAL NOT NULL, " +
                    COLUMN_NUM_PEOPLE + " INTEGER NOT NULL, " +
                    COLUMN_INDIVIDUAL_SHARE + " REAL NOT NULL);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertShare(double shareAmount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SHARE_AMOUNT, shareAmount);

        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public List<Result> getAllResults() {
        List<Result> results = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                COLUMN_ID,
                COLUMN_TOTAL_AMOUNT,
                COLUMN_NUM_PEOPLE,
                COLUMN_INDIVIDUAL_SHARE
        };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
            double totalAmount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_AMOUNT));
            int numPeople = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUM_PEOPLE));
            double individualShare = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_INDIVIDUAL_SHARE));

            results.add(new Result(id, totalAmount, numPeople, individualShare));
        }

        cursor.close();

        return results;
    }

}
