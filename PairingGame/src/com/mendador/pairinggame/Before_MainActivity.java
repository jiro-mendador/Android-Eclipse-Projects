package com.mendador.pairinggame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Before_MainActivity extends Activity {

    EditText playerName;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.before_main_layout);

        playerName = (EditText) findViewById(R.id.etPlayerName);
        save = (Button) findViewById(R.id.btnSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!playerName.getText().toString().isEmpty()) {
                    Player player = new Player(playerName.getText().toString());
                    Intent mainIntent = new Intent(Before_MainActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        });

    }
}