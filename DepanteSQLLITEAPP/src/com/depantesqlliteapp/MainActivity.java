package com.depantesqlliteapp;

import com.depantesqlliteapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Intent displayForm;
	SQLiteDBHelper conn;
	EditText firstName, middleName, lastName;
	String fName = "";
	String mName = "";
	String lName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	void init() {
		firstName = (EditText) findViewById(R.id.etAddFirstName);
		middleName = (EditText) findViewById(R.id.etAddMidName);
		lastName = (EditText) findViewById(R.id.etAddLastName);
		conn = new SQLiteDBHelper(MainActivity.this);
		
		//this.deleteDatabase(SQLiteDBHelper.DB_NAME); for testing
	}

	public void addRecord(View view) {
		fName = firstName.getText().toString();
		mName = middleName.getText().toString();
		lName = lastName.getText().toString();

		if (fName.equals("")) {
			firstName.setError("Enter Your First Name...");
			firstName.requestFocus();
			return;
		}

		if (mName.equals("")) {
			middleName.setError("Enter Your Middle Name...");
			middleName.requestFocus();
			return;
		}

		if (lName.equals("")) {
			lastName.setError("Enter Your Last Name...");
			lastName.requestFocus();
			return;
		}
		
		if(conn.hasDuplicate(fName, mName, lName, -1)) {
			Toast.makeText(getApplicationContext(), "DATA HAS DUPLICATE!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (!conn.addRecords(fName, mName, lName)) {
			Toast.makeText(getApplicationContext(), "SAVING FAILED!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		firstName.setText("");
		middleName.setText("");
		lastName.setText("");
		Toast.makeText(getApplicationContext(), "RECORD SAVED!", Toast.LENGTH_SHORT).show();

	}

	public void viewRecords(View view) {
		displayForm = new Intent(MainActivity.this, RecordsActivity.class);
		startActivity(displayForm);
	}

	public void clearRecords(View view) {
		try {
			conn.clearRecord();
			Toast.makeText(getApplicationContext(), "Records Cleared!", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
}
