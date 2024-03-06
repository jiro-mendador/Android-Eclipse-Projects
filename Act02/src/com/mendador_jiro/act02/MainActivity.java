package com.mendador_jiro.act02;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	Button resetBtn, goBtn;
	TextView primeCompTv, binaryTv, divTv;
	EditText inputEt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initComponents();
	}

	private void initComponents() {
		resetBtn = (Button) findViewById(R.id.btnReset);
		goBtn = (Button) findViewById(R.id.btnGo);
		primeCompTv = (TextView) findViewById(R.id.tvPrimeComp);
		binaryTv = (TextView) findViewById(R.id.tvBinary);
		divTv = (TextView) findViewById(R.id.tvDivisibles);
		inputEt = (EditText) findViewById(R.id.etNumInput);
		
		goBtn.setOnClickListener(this);
		resetBtn.setOnClickListener(this);
	}

	private void reset() {
		inputEt.setText("");
		primeCompTv.setText("Prime Or Composite");
		binaryTv.setText("Binary Equivalent");
		divTv.setText("Divisible Numbers");
	}
	
	private boolean isPrime(int n) {
		if (n <= 1) {
	        return false;
	    }
	    for (int i = 2; i <= Math.sqrt(n); i++) {
	        if (n % i == 0) {
	            return false;
	        }
	    }
	    return true;
	}
	
	private String toBinary(int num) {
		return String.format("%8s", Integer.toBinaryString(num)).replace(' ', '0');
	}
	
	private String divisibles(int num) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= num; i++) {
		    if (num % i == 0) {
		        if (sb.length() > 0) {
		            sb.append(", ");
		        }
		        sb.append(i);
		    }
		}
		return sb.toString();
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btnGo:
			String number = inputEt.getText().toString();
			if(!number.isEmpty()) {
				int num = Integer.valueOf(number);
				primeCompTv.setText(isPrime(num) ? number+" is a Prime" : number+ " is a Composite");
				binaryTv.setText(toBinary(num));
				divTv.setText(divisibles(num));
			} else {
				reset();
			}
			break;
		case R.id.btnReset:
			reset();
			break;
		default:
			break;
		}
	}
}
