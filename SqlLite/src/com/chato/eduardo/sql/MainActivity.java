package com.chato.eduardo.sql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	public Intent DisplayForm;
	public SQLiteDatabase Conn;
	public EditText Fname, Mname, Lname;
	public String FNAME=null, MNAME=null, LNAME=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Fname = (EditText)findViewById(R.id.Fname);
		Mname = (EditText)findViewById(R.id.Mname);
		Lname = (EditText)findViewById(R.id.Lname);
		Conn = new SQLiteDatabase(this);
	}
	public void AddRecord(View view) {
		FNAME = Fname.getText().toString();
		MNAME = Mname.getText().toString();
		LNAME = Lname.getText().toString();
		if(FNAME.equals("")) {
			Fname.setError("Please Enter Your First Name");
			Fname.requestFocus();
		}else if(MNAME.equals("")) {
			Mname.setError("Please Enter Your Middle Name");
			Mname.requestFocus();
		}else if(LNAME.equals("")) {
			Lname.setError("Please Enter Your Last Name");
			Lname.requestFocus();
		}else {
			if(Conn.AddRecords(FNAME, MNAME, LNAME)) {
				Fname.setText("");
				Mname.setText("");
				Lname.setText("");
				Toast.makeText(getApplicationContext(), "RECORD SAVED!", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(getApplicationContext(), "SAVING INFORMATION FAILED!", Toast.LENGTH_SHORT).show();
			}
			}
		}
	public void ViewRecords(View view) {
		DisplayForm= new Intent("com.sqllite.app.RECORDSACTIVITY");
		startActivity(DisplayForm);
	}
	public void ClearRecords(View view) {
		try {
			Conn.ClearRecord();
			Toast.makeText(getApplicationContext(), "RECORDS CLEAR", Toast.LENGTH_SHORT).show();
		} catch(Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
}
