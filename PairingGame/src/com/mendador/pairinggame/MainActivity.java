package com.mendador.pairinggame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity implements View.OnClickListener {

	Button[] buttons = new Button[10];
	Button reset, cheat;
	TextView name, score;
	ArrayList<String> numbersList;
	boolean cheatClicked = false;
	int clickCounter = 0;
	int scoreCounter = 0;
	Button prevButton;
	int counterOfPairs = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		name.setText("Player : " + Player.getPlayerName());
	}

	void init() {
		name = (TextView) findViewById(R.id.tvPlayerName);
		score = (TextView) findViewById(R.id.tvScore);

		buttons[0] = (Button) findViewById(R.id.btn1);
		buttons[1] = (Button) findViewById(R.id.btn2);
		buttons[2] = (Button) findViewById(R.id.btn3);
		buttons[3] = (Button) findViewById(R.id.btn4);
		buttons[4] = (Button) findViewById(R.id.btn5);
		buttons[5] = (Button) findViewById(R.id.btn6);
		buttons[6] = (Button) findViewById(R.id.btn7);
		buttons[7] = (Button) findViewById(R.id.btn8);
		buttons[8] = (Button) findViewById(R.id.btn9);
		buttons[9] = (Button) findViewById(R.id.btn10);

		reset = (Button) findViewById(R.id.btnReset);
		cheat = (Button) findViewById(R.id.btnCheat);

		for (Button i : buttons) {
			i.setOnClickListener(this);
		}

		reset.setOnClickListener(this);
		cheat.setOnClickListener(this);

		numbersList = new ArrayList<String>();
		for (int i = 1; i <= 5; i++) {
			numbersList.add(String.valueOf(i));
			numbersList.add(String.valueOf(i));
		}
		reset();
	}

	void reset() {
		Collections.shuffle(numbersList);
		for (Button i : buttons) {
			i.setEnabled(true);
		}
		openAllButtons();
		scoreCounter = 0;
		score.setText("Score : 0");
	}

	@Override
	public void onClick(View view) {
		int prev;
		if (reset == (Button) view) {
			reset();
		} else if (cheat == (Button) view) {
			cheatClicked = cheatClicked == true ? false : true;
			openAllButtons();
		} else {
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i] == (Button) view) {
					buttons[i].setText(numbersList.get(i));
					if (prevButton != buttons[i]) {
						clickCounter++;
					}
					checkClickCount(prevButton, buttons[i]);
					prevButton = buttons[i];
					checkPairs();
				}
			}
		}
	}

	void openAllButtons() {
		for (int i = 0; i < buttons.length; i++) {
			if (!cheatClicked) {
				buttons[i].setText("");
				buttons[i].setEnabled(true);
			} else {
				buttons[i].setText(numbersList.get(i));
				buttons[i].setEnabled(false);
			}
		}
		scoreCounter = 0;
		score.setText("Score : " + scoreCounter);
	}

	void checkClickCount(final Button prev, final Button curr) {
		if (clickCounter == 2) {
			if (prev.getText().toString().equals(curr.getText().toString())) {
				Toast.makeText(MainActivity.this, "+1", Toast.LENGTH_SHORT).show();
				scoreCounter++;
				score.setText("Score : " + scoreCounter);
				buttonEnabled(false, prev, curr);
			} else {
				buttonEnabled(false, prev, curr);
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						prev.setText("");
						curr.setText("");
						buttonEnabled(true, prev, curr);
					}
				}, 1000);
			}
			clickCounter = 0;
		}
	}

	void buttonEnabled(boolean action, Button b1, Button b2) {
		b1.setEnabled(action);
		b2.setEnabled(action);
	}

	void checkPairs() {
		if (scoreCounter == 5) {
			reset.setEnabled(false);
			cheat.setEnabled(false);
			new AlertDialog.Builder(this).setMessage("Congratulations You Win")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							reset.setEnabled(true);
							cheat.setEnabled(true);
							reset();
						}
					}).setCancelable(false).create().show();
		}
	}
}