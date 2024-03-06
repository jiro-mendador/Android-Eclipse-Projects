package com.depantesqlliteapp;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RecordsActivity extends ListActivity {
	
	public SQLiteDBHelper conn;
	public Intent dispForm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		conn = new SQLiteDBHelper(RecordsActivity.this);
		try {
			ArrayList<String> Records = conn.getAllData();
			
			if(Records.size() <= 0) {
				Toast.makeText(getApplicationContext(), "No Records Found", 
						Toast.LENGTH_SHORT).show();
				return;
			}
			
			setListAdapter(new ArrayAdapter<String>(RecordsActivity.this, 
					android.R.layout.simple_list_item_1, Records));
			
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.toString(), 
					Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		UpdateActivity.ID = conn.RecordsID.get(position);
		dispForm = new Intent(RecordsActivity.this, UpdateActivity.class);
		startActivity(dispForm);
		finish();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
