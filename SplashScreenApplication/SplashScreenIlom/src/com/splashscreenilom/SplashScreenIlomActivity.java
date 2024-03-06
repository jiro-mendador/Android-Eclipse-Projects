package com.splashscreenilom;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;

public class SplashScreenIlomActivity extends Activity{
Intent CallMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//connect xml layout 
		setContentView(R.layout.activity_splashscreenilom);
		
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2000);
				}	catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						CallMain =  new Intent(".MainActivity");
						startActivity(CallMain);
						finish();
					}
				}
			};
			timer.start();
		}
		
	} 


