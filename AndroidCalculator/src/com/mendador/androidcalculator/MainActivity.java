package com.mendador.androidcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tvOutput, tvPreview;
	int operation;
	double firstNumber, secondNumber;
	Button[] buttons = new Button[4];

	// utils
	boolean hasFirstNum = false;
	boolean hasSecondNum = false;
	double answer = 0;
	int lastOper = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
	}

	void initialize() {
		tvOutput = (TextView) findViewById(R.id.tvOutput);
		tvPreview = (TextView) findViewById(R.id.tvPreview);
		buttons[0] = (Button) findViewById(R.id.btnPlus);
		buttons[1] = (Button) findViewById(R.id.btnMinus);
		buttons[2] = (Button) findViewById(R.id.btnTimes);
		buttons[3] = (Button) findViewById(R.id.btnDivide);
		operation = 0;
		firstNumber = 0;
		secondNumber = 0;
	}

	public void clear(View v) {
		tvOutput.setText("");
		tvPreview.setText("");
		operation = 0;
		firstNumber = 0;
		secondNumber = 0;
		answer = 0;
		hasFirstNum = false;
		hasSecondNum = false;
	}

	// opers
	public void add(View v) {
		operation = 1;

		String disp = tvOutput.getText().toString();

		if (!hasFirstNum) {
			if (disp.isEmpty()) {
				firstNumber = 0;
			} else {
				firstNumber = Double.parseDouble(disp);
			}
			hasFirstNum = true;
		} else {
			if (disp.isEmpty()) {
				secondNumber = 0;
				hasSecondNum = false;
			} else {
				secondNumber = Double.parseDouble(disp);
				hasSecondNum = true;
			}
		}

		tvPreview.setText(firstNumber + " " + dispOper() + " ");

		calculateResult();
		tvOutput.setText("");

		lastOper = 1;
	}

	public void sub(View v) {
		operation = 2;

		String disp = tvOutput.getText().toString();

		if (!hasFirstNum) {
			if (disp.isEmpty()) {
				firstNumber = 0;
			} else {
				firstNumber = Double.parseDouble(disp);
			}
			hasFirstNum = true;
		} else {
			if (disp.isEmpty()) {
				secondNumber = 0;
				hasSecondNum = false;
			} else {
				secondNumber = Double.parseDouble(disp);
				hasSecondNum = true;
			}
		}

		tvPreview.setText(firstNumber + " " + dispOper() + " ");

		calculateResult();
		tvOutput.setText("");

		lastOper = 2;
	}

	public void multiply(View v) {
		operation = 3;

		String disp = tvOutput.getText().toString();

		if (!hasFirstNum) {
			if (disp.isEmpty()) {
				firstNumber = 0;
			} else {
				firstNumber = Double.parseDouble(disp);
			}
			hasFirstNum = true;
		} else {
			if (disp.isEmpty()) {
				secondNumber = 0;
				hasSecondNum = false;
			} else {
				secondNumber = Double.parseDouble(disp);
				hasSecondNum = true;
			}
		}

		tvPreview.setText(firstNumber + " " + dispOper() + " ");

		calculateResult();
		tvOutput.setText("");

		lastOper = 3;
	}

	public void divide(View v) {
		operation = 4;

		String disp = tvOutput.getText().toString();

		if (!hasFirstNum) {
			if (disp.isEmpty()) {
				firstNumber = 0;
			} else {
				firstNumber = Double.parseDouble(disp);
			}
			hasFirstNum = true;
		} else {
			if (disp.isEmpty()) {
				secondNumber = 0;
				hasSecondNum = false;
			} else {
				secondNumber = Double.parseDouble(disp);
				hasSecondNum = true;
			}
		}

		tvPreview.setText(firstNumber + " " + dispOper() + " ");

		calculateResult();
		tvOutput.setText("");

		lastOper = 4;
	}

	public void calculate(View v) {
		if (!tvOutput.getText().toString().isEmpty()) {
			secondNumber = Double.parseDouble(tvOutput.getText().toString());
			switch (operation) {
			case 1:
				tvOutput.setText(String.valueOf(firstNumber + secondNumber));
				break;
			case 2:
				tvOutput.setText(String.valueOf(firstNumber - secondNumber));
				break;
			case 3:
				tvOutput.setText(String.valueOf(firstNumber * secondNumber));
				break;
			case 4:
				tvOutput.setText(String.valueOf(firstNumber / secondNumber));
				break;
			default:
				break;
			}
			
			hasFirstNum = false;
			
			tvPreview.setText(
					operation == 0 ? tvPreview.getText().toString() : tvPreview.getText().toString() + secondNumber);
			operation = 0;
		} else {
			tvOutput.setText("" + firstNumber);
		}
	}

	public void calculateResult() {

		if (!(hasFirstNum && hasSecondNum)) {
			return;
		}

		switch (lastOper) {
		case 1:
			answer = firstNumber + secondNumber;
			break;
		case 2:
			answer = firstNumber - secondNumber;
			break;
		case 3:
			answer = firstNumber * secondNumber;
			break;
		case 4:
			answer = firstNumber / secondNumber;
			break;
		default:
			break;
		}

		firstNumber = answer;
		tvPreview.setText(firstNumber + " " + dispOper() + " ");
		hasSecondNum = false;
	}

	public String dispOper() {
		String dispOper = "";
		switch (operation) {
		case 1:
			dispOper = "+";
			break;
		case 2:
			dispOper = "-";
			break;
		case 3:
			dispOper = "*";
			break;
		case 4:
			dispOper = "/";
			break;
		default:
			break;
		}
		return dispOper;
	}

	boolean hasOper(String oper) {
		return oper.matches(".*[+\\-*/]\\s*$");
	}

	// numbers
	public void pressedPoint(View v) {
		tvOutput.setText(!tvOutput.getText().toString().contains(".")
				? tvOutput.getText().toString().isEmpty() ? "0." : tvOutput.getText().toString() + "."
				: tvOutput.getText().toString());
	}

	public void pressed0(View v) {
		tvOutput.setText(tvOutput.getText().toString() + "0");
	}

	public void pressed1(View v) {
		tvOutput.setText(tvOutput.getText().toString() + "1");
	}

	public void pressed2(View v) {
		tvOutput.setText(tvOutput.getText().toString() + "2");
	}

	public void pressed3(View v) {
		tvOutput.setText(tvOutput.getText().toString() + "3");
	}

	public void pressed4(View v) {
		tvOutput.setText(tvOutput.getText().toString() + "4");
	}

	public void pressed5(View v) {
		tvOutput.setText(tvOutput.getText().toString() + "5");
	}

	public void pressed6(View v) {
		tvOutput.setText(tvOutput.getText().toString() + "6");
	}

	public void pressed7(View v) {
		tvOutput.setText(tvOutput.getText().toString() + "7");
	}

	public void pressed8(View v) {
		tvOutput.setText(tvOutput.getText().toString() + "8");
	}

	public void pressed9(View v) {
		tvOutput.setText(tvOutput.getText().toString() + "9");
	}
}
