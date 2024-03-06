package com.loginapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends Activity {
	
	TextView username, password;
	Button login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_page);
		
		username = (TextView) findViewById(R.id.username);
		password = (TextView) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		System.out.println("hahahahaha");
		login.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(username.getText().toString().equals("admin") &&
						password.getText().toString().equals("admin123")) {
					Toast.makeText(LoginPage.this,"LOGIN SUCCESS!!!",Toast.LENGTH_SHORT).show();

				} else {
					Toast.makeText(LoginPage.this,"INCORRECT USERNAME OR PASSWORD...",Toast.LENGTH_SHORT).show();
				}
				System.out.println("hahahahaha");
			}
		});
		
	}
}
