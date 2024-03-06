package com.mendador.numberpairinggame;

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
    TextView score, multiplier, playerName;
    Button[] buttons;
    Button[] possiblePairs;
    ArrayList<String> numberList;
    ArrayList<Button> alreadyPaired;
    int clickCounter;
    boolean cheatClicked;
    int pairsCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addClickListners();
        setScoreAndMultiplier(0, 1);
        playerName.setText("Player : " + Player.getName());
    }

    void init() {
        buttons = new Button[53];
        numberList = new ArrayList<String>();
        alreadyPaired = new ArrayList<Button>();

        possiblePairs = new Button[2];

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
        buttons[10] = (Button) findViewById(R.id.btn11);
        buttons[11] = (Button) findViewById(R.id.btn12);
        buttons[12] = (Button) findViewById(R.id.btn13);
        buttons[13] = (Button) findViewById(R.id.btn14);
        buttons[14] = (Button) findViewById(R.id.btn15);
        buttons[15] = (Button) findViewById(R.id.btn16);
        buttons[16] = (Button) findViewById(R.id.btn17);
        buttons[17] = (Button) findViewById(R.id.btn18);
        buttons[18] = (Button) findViewById(R.id.btn19);
        buttons[19] = (Button) findViewById(R.id.btn20);
        buttons[20] = (Button) findViewById(R.id.btn21);
        buttons[21] = (Button) findViewById(R.id.btn22);
        buttons[22] = (Button) findViewById(R.id.btn23);
        buttons[23] = (Button) findViewById(R.id.btn24);
        buttons[24] = (Button) findViewById(R.id.btn25);
        buttons[25] = (Button) findViewById(R.id.btn26);
        buttons[26] = (Button) findViewById(R.id.btn27);
        buttons[27] = (Button) findViewById(R.id.btn28);
        buttons[28] = (Button) findViewById(R.id.btn29);
        buttons[29] = (Button) findViewById(R.id.btn30);
        buttons[30] = (Button) findViewById(R.id.btn31);
        buttons[31] = (Button) findViewById(R.id.btn32);
        buttons[32] = (Button) findViewById(R.id.btn33);
        buttons[33] = (Button) findViewById(R.id.btn34);
        buttons[34] = (Button) findViewById(R.id.btn35);
        buttons[35] = (Button) findViewById(R.id.btn36);
        buttons[36] = (Button) findViewById(R.id.btn37);
        buttons[37] = (Button) findViewById(R.id.btn38);
        buttons[38] = (Button) findViewById(R.id.btn39);
        buttons[39] = (Button) findViewById(R.id.btn40);
        buttons[40] = (Button) findViewById(R.id.btn41);
        buttons[41] = (Button) findViewById(R.id.btn42);
        buttons[42] = (Button) findViewById(R.id.btn43);
        buttons[43] = (Button) findViewById(R.id.btn44);
        buttons[44] = (Button) findViewById(R.id.btn45);
        buttons[45] = (Button) findViewById(R.id.btn46);
        buttons[46] = (Button) findViewById(R.id.btn47);
        buttons[47] = (Button) findViewById(R.id.btn48);
        buttons[48] = (Button) findViewById(R.id.btn49);
        buttons[49] = (Button) findViewById(R.id.btn50);

        buttons[50] = (Button) findViewById(R.id.btnCheat);
        buttons[51] = (Button) findViewById(R.id.btnReset);
        buttons[52] = (Button) findViewById(R.id.btnExit);

        playerName = (TextView) findViewById(R.id.tv1);
        score = (TextView) findViewById(R.id.tv2);
        multiplier = (TextView) findViewById(R.id.tv3);

        for (int i = 0; i < 2; i++) {
            for (int j = 1; j < 26; j++) {
                numberList.add(String.valueOf(j));
            }
        }
        resetButtonOrder();
    }

    void addClickListners() {
        for (Button i : buttons) {
            i.setOnClickListener(this);
        }
    }

    void showHideAllButtonsValue() {
        cheatClicked = cheatClicked == false ? true : false;
        for (int i = 0; i < 50; i++) {
            if (cheatClicked) {
                buttons[i].setText(numberList.get(i));
                buttons[i].setEnabled(false);
            } else {
                buttons[i].setText(alreadyPaired.contains(buttons[i]) ? numberList.get(i) : "");
                buttons[i].setEnabled(!alreadyPaired.contains(buttons[i]));
            }
        }
    }

    void showIndividualButtonsValue(int i) {
        buttons[i].setText(numberList.get(i));
    }

    void resetButtonOrder() {
        Collections.shuffle(numberList);
        alreadyPaired.clear();
        cheatClicked = true;
        clickCounter = 0;
        showHideAllButtonsValue();
        setScoreAndMultiplier(0, 1);
        pairsCounter = 0;
    }

    void checkPairs(Button btn) {
        int scr = Integer.parseInt(score.getText().toString().replaceAll("Score : ", ""));
        int mult = Integer.parseInt(multiplier.getText().toString().replaceAll("Multiplier : ", ""));

        if (clickCounter == 1) {

            possiblePairs[0] = btn;
            possiblePairs[0].setEnabled(false);

        } else if (clickCounter == 2) {

            possiblePairs[1] = btn;
            possiblePairs[1].setEnabled(false);

            if (possiblePairs[0].getText().toString().equals(possiblePairs[1].getText().toString())) {

                alreadyPaired.add(possiblePairs[0]);
                alreadyPaired.add(possiblePairs[1]);

                setScoreAndMultiplier((scr + 5) * mult, ++mult);
                pairsCounter++;

                if (pairsCounter == numberList.size()/2) {
                    new AlertDialog.Builder(this)
                            .setMessage("Congratulations You Win!\n"+score.getText().toString())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    alreadyPaired.clear();
                                    resetButtonOrder();
                                }
                            })
                            .setCancelable(false)
                            .create()
                            .show();
                }
                clickCounter = 0;

            } else {

                setScoreAndMultiplier(scr, 1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (Button i : possiblePairs) {
                            i.setText("");
                            i.setEnabled(true);
                        }
                        clickCounter = 0;
                    }
                }, 500);
            }
        }
    }

    void setScoreAndMultiplier(int scr, int mult) {
        score.setText("Score : " + scr);
        multiplier.setText("Multiplier : " + mult);
    }

    @Override
    public void onClick(View view) {
        if (buttons[50] == (Button) view) {
            showHideAllButtonsValue();
        } else if (buttons[51] == (Button) view) {
            resetButtonOrder();
        } else if (buttons[52] == (Button) view) {
            finish();
        }

        for (int i = 0; i < 50; i++) {
            if (buttons[i] == (Button) view && clickCounter != 2) {
                clickCounter++;
                showIndividualButtonsValue(i);
                checkPairs(buttons[i]);
            }
        }
    }
}