package com.depantesqlliteapp;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDBHelper extends SQLiteOpenHelper {

	static final String DB_NAME = "records.db";
	static final String TBL_NAME = "profile";

	final String COL_ID = "id";
	final String COL_FNAME = "fName";
	final String COL_MNAME = "mName";
	final String COL_LNAME = "lName";

	ArrayList<String> Records;
	ArrayList<Integer> RecordsID;
	String sql = "";
	Cursor rs;
	ContentValues values;

	final String CREATE_TBL = "CREATE TABLE " + TBL_NAME + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COL_FNAME + " TEXT NOT NULL, " + COL_MNAME + " TEXT NOT NULL, " + COL_LNAME + " TEXT NOT NULL);";
	
	final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TBL_NAME; //for testing
	
	public SQLiteDBHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase conn) {
		// TODO Auto-generated method stub
		//conn.execSQL(DROP_TABLE); for testing
		conn.execSQL(CREATE_TBL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase conn, int oldVer, int newVer) {
		// TODO Auto-generated method stub
		conn.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
		onCreate(conn);
	}

	boolean addRecords(String fName, String mName, String lName) {
		SQLiteDatabase conn = this.getWritableDatabase();
		
		values = new ContentValues();
		values.put(COL_FNAME, fName);
		values.put(COL_MNAME, mName);
		values.put(COL_LNAME, lName);
		conn.insert(TBL_NAME, null, values);

		return true;
	}

	boolean updateRecords(String fName, String mName, String lName,Integer id) {

		SQLiteDatabase conn = this.getWritableDatabase();
		sql = "UPDATE " + TBL_NAME + " SET " + COL_FNAME + " = '" + fName + "', " + COL_MNAME + " = '" + mName
				+ "', " + COL_LNAME + " = '" + lName + "' WHERE id = " + id;
		conn.execSQL(sql);
		return true;
	}
	
	Cursor getData(Integer id) {
		SQLiteDatabase conn = this.getReadableDatabase();
		sql = "SELECT * FROM " +TBL_NAME+ " WHERE " + COL_ID + " = " + id;
		rs = conn.rawQuery(sql, null);
		return rs;
	}
	
	boolean clearRecord() {
		SQLiteDatabase conn = this.getReadableDatabase();
		conn.execSQL("DELETE FROM "+ TBL_NAME);
		return true;
	}
	
	boolean deleteRecord(Integer id) {
		SQLiteDatabase conn = this.getReadableDatabase();
		conn.execSQL("DELETE FROM "+ TBL_NAME + " WHERE " +COL_ID + " = " + id);
		return true;
	}
	
	boolean hasDuplicate(String fName, String mName, String lName, int currentID) {
	    SQLiteDatabase db = this.getReadableDatabase();
	    String selectQuery = "SELECT * FROM " + TBL_NAME +
	            " WHERE " + COL_FNAME + " = ? AND " + COL_MNAME + " = ? AND " + COL_LNAME + " = ? AND " + COL_ID + " != ?";
	    Cursor cursor = db.rawQuery(selectQuery, new String[]{fName, mName, lName, Integer.toString(currentID)});
	    boolean isDuplicate = cursor.getCount() > 0;
	    cursor.close();
	    db.close();
	    return isDuplicate;
	}


	
	ArrayList<String> getAllData() {
		SQLiteDatabase conn = this.getReadableDatabase();
		
		Records = new ArrayList<String>();
		RecordsID = new ArrayList<Integer>();
		sql = "SELECT * FROM  " + TBL_NAME;
		
		rs = conn.rawQuery(sql, null);
		rs.moveToFirst();
		
		while(rs.isAfterLast() == false) {
			RecordsID.add(rs.getInt(rs.getColumnIndex(COL_ID)));
			Records.add(rs.getString(rs.getColumnIndex(COL_LNAME)) + ", " + 
					rs.getString(rs.getColumnIndex(COL_FNAME)) + " " 
					+ rs.getString(rs.getColumnIndex(COL_MNAME)));
			rs.moveToNext();
		}
		return Records;
	}

}
