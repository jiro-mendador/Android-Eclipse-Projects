package com.tutorialp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements OnClickListener {
	Button startBtn, startForResBtn;
	EditText sendEt;
	TextView answerTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initVars();
	}

	private void initVars() {
		startBtn = (Button) findViewById(R.id.startActivityBtn);
		startForResBtn = (Button) findViewById(R.id.startActivityForResultBtn);
		sendEt = (EditText) findViewById(R.id.sendEt);
		answerTv = (TextView) findViewById(R.id.gotTv);
		startBtn.setOnClickListener(this);
		startForResBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.startActivityBtn:
			String dataToBepassed = /*getString(sendEt)*/ sendEt.getText().toString();
			Bundle holder = new Bundle(); 
			holder.putString("dataPassed", dataToBepassed);
			Intent openClass = new Intent(Data.this, OpenedClass.class);
			openClass.putExtras(holder);
			startActivity(openClass);
			break;
			
		case R.id.startActivityForResultBtn:
			Intent OpenClass = new Intent(Data.this, OpenedClass.class);
			startActivityForResult(OpenClass,0);
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK) {
			Bundle holder = data.getExtras();
			String repData = holder.getString("replyData");
			answerTv.setText(repData);
		}
	}
	
	//u can cast the edit text to textview tv and et comes from it
	/*private String getString(Object component) {
		return ((TextView) component).getText().toString();
	}*/

}
