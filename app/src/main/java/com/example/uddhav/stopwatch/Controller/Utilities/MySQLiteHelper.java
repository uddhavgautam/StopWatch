package com.example.uddhav.stopwatch.Controller.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.uddhav.stopwatch.Model.POJO.StopWatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uddhav on 8/7/17.
 */

public class MySQLiteHelper extends SQLiteOpenHelper { //CRUD operation

    public static final String TABLE_NAME = "stopWatch";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER_NAME = "_user";
    public static final String COLUMN_TIME_VALUE = "timevalue";
    private static final String DATABASE_NAME = "stopwatch.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table if not exists "
            + TABLE_NAME + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_USER_NAME
            + " text not null, " + COLUMN_TIME_VALUE
            + " text not null);";

    private static final String DATABASE_DELETE =
            "drop table if exists " + TABLE_NAME;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }

    @Override
    public void setWriteAheadLoggingEnabled(boolean enabled) {
        super.setWriteAheadLoggingEnabled(enabled);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    //create
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    //delete
    public void deleteDB(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_DELETE);
    }


    //add a StopWatch record in table
    public void addStopWatch(StopWatch stopWatch) {
        if (getStopWatchesCount() == 10) {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, stopWatch.getUser());
            values.put(COLUMN_TIME_VALUE, stopWatch.getTotalTime());
            sqLiteDatabase.insert(TABLE_NAME, null, values);
//            sqLiteDatabase.close(); // Closing database connection
            deleteStopWatch();
            return;
        }

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, stopWatch.getUser());
        values.put(COLUMN_TIME_VALUE, stopWatch.getTotalTime());
        sqLiteDatabase.insert(TABLE_NAME, null, values);
//        sqLiteDatabase.close(); // Closing database connection
        deleteStopWatch();
    }

    public int getStopWatchesCount() {
        int count;
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        count = cursor.getCount();
//        cursor.close();
        return cursor.getCount();
    }

    public void deleteStopWatch() { //always delete first record, which is the oldest one
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(1)});
//        sqLiteDatabase.close();
    }

    //read all StopWatch and show each StopWatch in each row of StopWatchHistoryFragment's recyclerview.
    public List<StopWatch> getAllStopWatch() { //always returns latest 10 records
        List<StopWatch> StopWatchList = new ArrayList<StopWatch>();

        String selectQuery1 = "delete\n" +
                "from " + TABLE_NAME + "\n" +
                "where " + COLUMN_ID + " not in (\n" +
                "    select " + COLUMN_ID + "\n" +
                "    from " + TABLE_NAME + "\n" +
                "    order by " + COLUMN_ID + " desc\n" +
                "    limit 10\n" +
                ")";

        SQLiteDatabase sqLiteDatabase1 = this.getWritableDatabase();
        Cursor cursor1 = sqLiteDatabase1.rawQuery(selectQuery1, null);


        String selectQuery = "select * from " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                StopWatch stopWatch = new StopWatch();
                stopWatch.setUser(cursor.getString(1));
                stopWatch.setTotalTime(cursor.getString(2));
                StopWatchList.add(stopWatch);
            } while (cursor.moveToNext());
        }
        return StopWatchList;
    }


}
