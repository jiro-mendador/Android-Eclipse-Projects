package com.example.matchinggame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	ImageView[] images = new ImageView[4];
	boolean[] imgClicked = new boolean[4];
	Button btn1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		images[0] = (ImageView) findViewById(R.id.imageView1);
		images[1] = (ImageView) findViewById(R.id.imageView2);
		images[2] = (ImageView) findViewById(R.id.imageView3);
		images[3] = (ImageView) findViewById(R.id.imageView4);
		btn1 = (Button) findViewById(R.id.b1);
		for (ImageView i : images) {
			i.setOnClickListener(this);
		}
		btn1.setOnClickListener(this);
		for (boolean i : imgClicked) {
			i = false;
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.imageView1:
			images[0].setBackground(null);
			if (imgClicked[0]) {
				imgClicked[0] = false;
				images[0].setImageResource(R.drawable.image1);
			} else {
				imgClicked[0] = true;
				images[0].setImageResource(R.drawable.ic_launcher);
			}
			if (checkPair()) {
				Toast.makeText(MainActivity.this, "Match!!!", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.imageView2:
			images[0].setBackground(null);
			if (imgClicked[1]) {
				imgClicked[1] = false;
				images[1].setBackground(null);
				images[1].setImageResource(R.drawable.image1);
			} else {
				imgClicked[1] = true;
				images[1].setImageResource(R.drawable.ic_launcher);
			}
			if (checkPair()) {
				Toast.makeText(MainActivity.this, "Match!!!", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.imageView3:
			images[2].setBackground(null);
			images[2].setImageResource(R.drawable.image1);
			break;
		case R.id.imageView4:
			images[3].setBackground(null);
			images[3].setImageResource(R.drawable.image1);
			break;
		case R.id.b1:
			btn1.setText("nakaharap");
			break;
		default:
			break;
		}
	}

	boolean checkPair() {
		if (imgClicked[0] && imgClicked[1]) {
			return true;
		} else if (imgClicked[2] && imgClicked[3]) {
			return true;
		}
		return false;
	}
}
