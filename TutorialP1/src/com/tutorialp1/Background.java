package com.tutorialp1;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Background extends Activity {
	MediaPlayer bgSound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.background);
		
		//classes for sounds/music
		//soundPool and MediaPlayer
		bgSound = MediaPlayer.create(Background.this, R.raw.soundforapp);
		bgSound.start();
		
		
		//Thread class for delaying the time before other class is called
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(1000); //5000ms == 5s
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally { // means after try and catch whatever happens it'll run this
					//calling the intent in manifest and it's app name
					Intent mainActivity = new Intent("com.tutorialP1.Menu");
					//starting it
					startActivity(mainActivity);
				}
			}
		};
		timer.start(); //starting the thread
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//finishing the current class
		bgSound.release();
		finish();
	}
	
}
