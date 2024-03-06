package com.tutorialp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

//when implementing 2 classes separate it with comma
//but can only extends 1 class
public class OpenedClass extends Activity implements OnClickListener, OnCheckedChangeListener{
	TextView quesTv, outputTv;
	Button returnBtn;
	RadioGroup choicesRg;
	String dataReceived, replyData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initVars();
		/*Bundle getValueOnHolder = getIntent().getExtras();
		dataReceived = getValueOnHolder.getString("dataPassed");
		quesTv.setText(dataReceived);*/
	}
	
	private void initVars() {
		quesTv = (TextView) findViewById(R.id.tVquestion);
		outputTv = (TextView) findViewById(R.id.tVoutput);
		returnBtn = (Button) findViewById(R.id.btnReturn);
		choicesRg = (RadioGroup) findViewById(R.id.rGchoices);
		choicesRg.setOnCheckedChangeListener(this);
		returnBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent dataClass = new Intent();
		Bundle holder = new Bundle();
		holder.putString("replyData",replyData);
		dataClass.putExtras(holder);
		setResult(RESULT_OK, dataClass);
		finish();
	}
	
	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		// this listener takes the radioGroup and the id of the button
		switch (arg1) {
			case R.id.rBchoice1:
				replyData = "Well Yeah!";
				break;
			case R.id.rBchoice2:
				replyData = "Ofcouse Man!";
				break;
			case R.id.rBchoice3:
				replyData = "Spot On!";
				break;
			default:
				break;
		}	
		outputTv.setText(replyData);
	}
}
