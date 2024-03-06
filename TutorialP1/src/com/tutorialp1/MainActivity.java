package com.tutorialp1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	int counter;
	Button add, sub;
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		counter = 0;
		add = (Button) findViewById(R.id.addBtn);
		sub = (Button) findViewById(R.id.subBtn);
		display = (TextView) findViewById(R.id.displayTV);

		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				counter++;
				display.setText("Your Total Is "+counter);
			}
		});

		sub.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(counter != 0) {
					counter--;
					display.setText("Your Total Is "+counter);
				}
			}
		});
	}
}
