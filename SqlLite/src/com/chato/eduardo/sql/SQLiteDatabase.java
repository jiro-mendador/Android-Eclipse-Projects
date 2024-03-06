package com.chato.eduardo.sql;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabase extends SQLiteOpenHelper{
	public static final String DATABASE_NAME = "records.db";
	public static final String PROFILE = "profile";
	public final String PROFILE_ID = "id", PROFILE_FNAME = "fname", PROFILE_MNAME = "mname", PROFILE_LNAME = "lname";
	public ArrayList<String> Records;
	public ArrayList<Integer> RecordsId;
	public String sql = null;
	public Cursor rs;
	public ContentValues Values;
	public SQLiteDatabase(Context context) {
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(android.database.sqlite.SQLiteDatabase conn) {
		conn.execSQL("CREATE TABLE" + PROFILE + "("+ PROFILE_ID + " Integer PRIMARY KEY AUTOINCREMENT," + PROFILE_FNAME + " text, " + PROFILE_MNAME + " text, " + PROFILE_LNAME + "text)");
	}

	@Override
	public void onUpgrade(android.database.sqlite.SQLiteDatabase conn, int OldVersion, int NewVersion) {
		// TODO Auto-generated method stub
		conn.execSQL("DROP TABLE IF EXIST " + DATABASE_NAME);
		onCreate(conn);
	}
	public boolean AddRecords(String fname, String mname, String lname) {
		android.database.sqlite.SQLiteDatabase conn = this.getWritableDatabase();
		Values = new ContentValues();
		Values.put(PROFILE_FNAME, fname);
		Values.put(PROFILE_MNAME, mname);
		Values.put(PROFILE_LNAME, lname);
		conn.insert(PROFILE, null, Values);
		return true;
	}
	public boolean UpdateRecords(String fname, String mname, String lname, Integer id) {
		android.database.sqlite.SQLiteDatabase conn = this.getWritableDatabase();
		sql = "UPDATE " + PROFILE + " SET " + PROFILE_FNAME + " = '" +fname+ "', " + PROFILE_MNAME + " = '" + mname + "'," + PROFILE_LNAME + " = '" +lname+ " ' WHERE " + PROFILE_ID + "=" + id;
		conn.execSQL(sql);
		return true;
	}
	public Cursor getData(Integer id) {
		android.database.sqlite.SQLiteDatabase conn = this.getReadableDatabase();
		sql = "SELECT * FROM " + PROFILE + " WHERE " + PROFILE_ID+ "="+id;
		rs=conn.rawQuery(sql, null);
		return rs;
	}
	public boolean ClearRecord() {
		android.database.sqlite.SQLiteDatabase conn = this.getReadableDatabase();
		conn.execSQL("DELETE FROM " + PROFILE);
		return true;
	}
	public boolean DeleteRecord(Integer id) {
		android.database.sqlite.SQLiteDatabase conn = this.getReadableDatabase();
		conn.execSQL("DELETE FROM " + PROFILE + " WHERE " + PROFILE_ID + " = " + id);
		return true;
	}
	public ArrayList<String>GetAllData(){
		android.database.sqlite.SQLiteDatabase conn=this.getReadableDatabase();
		Records = new ArrayList<String>(); 
		RecordsId = new ArrayList<Integer>();
		sql = "SELECT * FROM " + PROFILE;
		rs = conn.rawQuery(sql, null);
		rs.moveToFirst();
		while(rs.isAfterLast()==false) {
			RecordsId.add(rs.getInt(rs.getColumnIndex(PROFILE_ID)));
			Records.add(rs.getString(rs.getColumnIndex(PROFILE_LNAME)) + ", " + rs.getString(rs.getColumnIndex(PROFILE_FNAME)) + "" + rs.getString(rs.getColumnIndex(PROFILE_MNAME)));
			rs.moveToNext();
		}return Records;
	
	}
}
