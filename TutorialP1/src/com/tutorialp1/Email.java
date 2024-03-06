package com.tutorialp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Email extends Activity implements OnClickListener {

	EditText emailaddEt, introEt, nameEt, descEt, actionEt, outroEt;
	String email, intro, name, desc, action, outro;
	Button sendEmailBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);

		initializeVars();
		sendEmailBtn.setOnClickListener(this);
	}

	private void initializeVars() {
		// TODO Auto-generated method stub
		emailaddEt = (EditText) findViewById(R.id.emailAddeT);
		introEt = (EditText) findViewById(R.id.introEt);
		nameEt = (EditText) findViewById(R.id.nameEt);
		descEt = (EditText) findViewById(R.id.descEt);
		actionEt = (EditText) findViewById(R.id.actionEt);
		outroEt = (EditText) findViewById(R.id.outroEt);
		sendEmailBtn = (Button) findViewById(R.id.sendEmailBtn);
	}
	
	private void convertEditTextIntoStrings() {
		// TODO Auto-generated method stub
		email = emailaddEt.getText().toString();
		intro = introEt.getText().toString();
		name = nameEt.getText().toString();
		desc = descEt.getText().toString();
		action = actionEt.getText().toString();
		outro = outroEt.getText().toString();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.sendEmailBtn:
				convertEditTextIntoStrings();
				String emailAddresses[] = { email };
				String message = "Hello " + 
								 name 
								 + "I just wanted to say " 
								 + intro 
								 + ". Not only that but also your " 
								 + desc
								 + "And also you are " 
								 + action 
								 + ". I guess that's all " 
								 + outro
								 + ". Dont forget to check out this link.";
				
				// u can use intent to start up an applications the phone has
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				
				//making the intent unique
				//first param refers to what email were sending
				//2nd param will take string array
				emailIntent.putExtra(
						android.content.Intent.EXTRA_EMAIL, 
						emailAddresses);
				//1st param is the subject
				//2nd the string subject
				emailIntent.putExtra(
						android.content.Intent.EXTRA_SUBJECT, 
						"THIS IS A SUBJECT");
				//before the last setup put what kind of typeface we'll send
				emailIntent.setType("plain/text");
				//the actual message
				emailIntent.putExtra(
						android.content.Intent.EXTRA_TEXT,
						message);
				startActivity(emailIntent);
			default:
				break;
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
