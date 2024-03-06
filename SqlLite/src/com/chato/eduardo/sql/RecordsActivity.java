package com.chato.eduardo.sql;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RecordsActivity extends ListActivity {
	public SQLiteDatabase Conn;
	public Intent DispForm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Conn = new SQLiteDatabase(this);
		try {
			ArrayList<String> Records = Conn.GetAllData();
			if(Records.size()>0) {
				setListAdapter ( new ArrayAdapter<String>(RecordsActivity.this,android.R.layout.simple_list_item_1, Records));			
			}else {
				Toast.makeText(getApplicationContext(), "No Records Found!", Toast.LENGTH_SHORT).show();
			}
		}
			catch(Exception e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
			}
		}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		UpdateActivity.ID=Conn.RecordsId.get(position);
		DispForm=new Intent("com.sqllite.app.UPDATEACTIVITY");
		startActivity(DispForm);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
