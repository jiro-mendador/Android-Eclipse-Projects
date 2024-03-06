package com.mendadorsqlliteapp;

import com.mendadorsqliteapp.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends Activity {

	EditText firstName, middleName, lastName;
	String fName, mName, lName;
	Intent dispForm;
	public SQLiteDBHelper conn;
	Cursor rs;
	static Integer ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_layout);
		init();

		try {
			rs = conn.getData(ID);
			rs.moveToFirst();

			firstName.setText(rs.getString(rs.getColumnIndex(conn.COL_FNAME)));
			middleName.setText(rs.getString(rs.getColumnIndex(conn.COL_MNAME)));
			lastName.setText(rs.getString(rs.getColumnIndex(conn.COL_LNAME)));

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	void init() {
		firstName = (EditText) findViewById(R.id.etUpdateFirstName);
		middleName = (EditText) findViewById(R.id.etUpdateMidName);
		lastName = (EditText) findViewById(R.id.etUpdateLastName);
		conn = new SQLiteDBHelper(UpdateActivity.this);
	}

	public void back(View view) {
		dispForm = new Intent(UpdateActivity.this, RecordsActivity.class);
		startActivity(dispForm);
	}

	public void updateRecord(View view) {
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

		try {

			conn.updateRecords(fName, mName, lName, ID);
			firstName.setText("");
			middleName.setText("");
			lastName.setText("");
			Toast.makeText(getApplicationContext(), "RECORD UPDATED!", Toast.LENGTH_SHORT).show();

			dispForm = new Intent(UpdateActivity.this, RecordsActivity.class);
			startActivity(dispForm);

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "ERROR : " + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	public void deleteRecord(View view) {
		try {

			conn.deleteRecord(ID);
			Toast.makeText(getApplicationContext(), "RECORD DELETED!", Toast.LENGTH_SHORT).show();

			dispForm = new Intent(UpdateActivity.this, RecordsActivity.class);
			startActivity(dispForm);

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "ERROR : " + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
