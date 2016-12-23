package com.example.clientcomm.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.InetAddress;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Rahul on 12/12/2016.
 */

/**  DB Handler class for managing
 * */
public class Properties extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "cc_properties";
    private static final String FIELD_PROPERTY = "PROPERTY";
    private static final String FIELD_VALUE = "VALUE";

    /* Properties */
    public static final String PROP_CURRENT_IP = "CURRENT_IP";
    public static final String PROP_CURRENT_PORT = "CURRENT_PORT";

    public Properties(Context context, String dbName) {
        super(context, dbName, null, constants.VERSION);
    }

    public Properties(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, constants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + FIELD_PROPERTY + " TEXT PRIMARY KEY, " + FIELD_VALUE + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    /* Private Methods */
    /** */
    private String getCountQuery(String aField) {
        return "Select count(" + aField + ") from " + TABLE_NAME + " where " + FIELD_PROPERTY + " = '" + aField +"'";
    }

    /** SELECT QUERY*/
    private String getSelectQuery(String aField) {
        return "Select " + FIELD_VALUE + " from " + TABLE_NAME + " where " + FIELD_PROPERTY + " = '" + aField + "'";
    }

    /** INSERT QUERY */
    private String getInsertQuery(String aProperty, String aValue) {
        return "Insert into " +TABLE_NAME + " (" + FIELD_PROPERTY + ", " + FIELD_VALUE + ") values ('" + aProperty + "', '" + aValue + "')";
    }

    /** UPDATE QUERY */
    private String getUpdateQuery(String aProperty, String aValue) {
        return "Update " +TABLE_NAME + " set " + FIELD_VALUE + " = '" + aValue + "' where " + FIELD_PROPERTY + " = '" + aProperty +"'";
    }

    /** SET PROPERTY */
    private void setProperty(String aProperty, String aValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(getSelectQuery(aProperty), null);
        if (cursor.moveToFirst()) {
            SQLiteDatabase write = this.getWritableDatabase();
            write.execSQL(getUpdateQuery(aProperty, aValue));
        } else {
            SQLiteDatabase write = this.getWritableDatabase();
            write.execSQL(getInsertQuery(aProperty, ""));
        }
    }

    /** GET PROPERTY */
    private String getProperty(String aProperty) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(getSelectQuery(aProperty), null);
        String aValue;
        if (cursor.moveToFirst()) {
            aValue = cursor.getString(0);
        } else {
            db.execSQL(getInsertQuery(aProperty, ""));
            aValue = "";
        }
        return aValue;
    }

    /* End of private methods */

    /* Public Methods */
    /** sets the current IP address of the Device */
    public void setCurrentIpAddress(String anAddress) {
        this.setProperty(PROP_CURRENT_IP, anAddress);
    }

    /** returns the current IP address of the device */
    public String getCurrentIpAddress() {
        return this.getProperty(PROP_CURRENT_IP);
    }

    /** sets the current IP address of the Device */
    public void setCurrentPort(int aPort) {
        this.setProperty(PROP_CURRENT_PORT, Integer.toString(aPort));
    }

    /** returns the current IP address of the device */
    public int getCurrentPort() {
        this.setCurrentPort(123);
        if("".equals(this.getProperty(PROP_CURRENT_PORT))) {
            return 0;
        }
        return Integer.parseInt(this.getProperty(PROP_CURRENT_PORT));
    }

}
