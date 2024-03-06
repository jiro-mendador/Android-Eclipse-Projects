package com.tutorialp1;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * shortcut for setting a button onClickListener 
 * is to implement it directly to the class 
 * using implements keyword then View.OnClickListener
 * 
 */

public class Texts extends Activity implements View.OnClickListener {

	// declare it as global variable if you dont like making it final
	ToggleButton togglePw;
	Button exeBtn;
	TextView result;
	EditText commands;

	/*
	 * u can also make a constructor here to initialize components ex. public
	 * Texts() {
	 * 
	 * //initialize }
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// set ContentView just sets the gui that will be shown
		setContentView(R.layout.text_layout);

		// final keyword is needed when you reference the object inside the anonymous
		// method ex final EditText commands;
		exeBtn = (Button) findViewById(R.id.executeBtn);
		togglePw = (ToggleButton) findViewById(R.id.tbPassword);
		commands = (EditText) findViewById(R.id.etCommands);
		// when declaring objects you need to typecast it
		result = (TextView) findViewById(R.id.tvResult);

		// setting onClickListener manually for actions
		togglePw.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (togglePw.isChecked()) {
					commands.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
					togglePw.setBackgroundColor(Color.GREEN);
				} else {
					commands.setInputType(InputType.TYPE_CLASS_TEXT);
					togglePw.setBackgroundColor(Color.RED);
				}
				togglePw.setTextColor(Color.WHITE);
			}
		});

		// now since we already implement the onClickListener in this class
		// we now have to add or set it using 'this' keyword the listener to the button
		exeBtn.setOnClickListener(this);
	}

	// implementing onclick listener to the class so u dont have to
	// set it every time theres a button
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		// to know which button is click use the .getId() method
		switch (v.getId()) {
		// to refer to the button we need to use R.id because were using the .getId()
		// method
		case R.id.executeBtn:
			// here in android dev java u need to convert the getText() into String
			String userInput = commands.getText().toString();
			result.setText(userInput);
			// contentEquals() checks if 2 objects have the same content
			// equals() checks both the content and their reference or type
			if (userInput.contentEquals("left")) {
				result.setGravity(Gravity.LEFT);
			} else if (userInput.contentEquals("center")) {
				result.setGravity(Gravity.CENTER);
			} else if (userInput.contentEquals("right")) {
				result.setGravity(Gravity.RIGHT);
			} else if (userInput.contentEquals("blue")) {
				result.setTextColor(Color.BLUE);
			} else if (userInput.contains("random")) {
				// random class for random outputs
				Random crazy = new Random();
				result.setText("WTF!!!");
				// so .next will be use to determine the datatype of random obj
				// then the parameter will be the limit ex 75 so 0-75 will be the random num
				result.setTextSize(crazy.nextInt(75));
				result.setTextColor(Color.rgb(crazy.nextInt(265), crazy.nextInt(265), crazy.nextInt(265)));
				switch (crazy.nextInt(3)) {
				case 0:
					result.setGravity(Gravity.LEFT);
					break;
				case 1:
					result.setGravity(Gravity.CENTER);
					break;
				case 2:
					result.setGravity(Gravity.RIGHT);
					break;
				default:
					break;
				}
			} else {
				result.setText("Invalid");
				result.setGravity(Gravity.CENTER);
			}
			break;
		default:
			break;
		}
	}

}
