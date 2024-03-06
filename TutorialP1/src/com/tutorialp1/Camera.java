package com.tutorialp1;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity {

	ImageButton captureBtn;
	Button setBgBtn;
	ImageView image;
	Intent takingImage;
	final static int imageTaken = 0;
	Bitmap bitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialize();
		
		//setting the default value of the bitmap that will be use by imageview and wallpaper
		//InputStream - access the resource folder and gets the default image
		InputStream inputStream = getResources().openRawResource(R.drawable.owlbg);
		//BitmapFactory converts/decodes the inpustream value into bitmap
		bitmap = BitmapFactory.decodeStream(inputStream);
	}

	public void initialize() {
		captureBtn = (ImageButton) findViewById(R.id.capPhotoIb);
		setBgBtn = (Button) findViewById(R.id.setBgBtn);
		image = (ImageView) findViewById(R.id.bgPicIv);
		
		captureBtn.setOnClickListener(new CustomClickListener());
		setBgBtn.setOnClickListener(new CustomClickListener());
	}
	
	//on eclipse click source > override > onActivityResult
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK) {
			Bundle extraValues = data.getExtras();
			bitmap = (Bitmap) extraValues.get("data");
			image.setImageBitmap(bitmap);
		}
	}

	private class CustomClickListener implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0.getId() == R.id.capPhotoIb) {
				//this intent is for accessing the camera 
				takingImage = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				
				//this kind of activity is when were expecting a result 
				startActivityForResult(takingImage, imageTaken);
				
			} else if(arg0.getId() == R.id.setBgBtn) {
				//changing bg of an image view as lng as the image is in res folder
				//image.setImageResource(R.drawable.ic_launcher);
				
				//setting the wallpaper of the actual phone but this way is deprecated
				try {
					getApplicationContext().setWallpaper(bitmap);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				
				//dont forget to add permission on android manifest because 
				//were accessing the phone wallpaper
			}
		}
		
	}
}
