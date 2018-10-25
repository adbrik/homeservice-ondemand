package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.seg2015.group.homeserviceondemand.EXTRA_TEXT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openWelcomeActivity();

            }
        });

    }

    public void openWelcomeActivity(){

        EditText userName_Field = (EditText) findViewById(R.id.userName_Field);

        String text = userName_Field.getText().toString();

        Intent intent = new Intent(this, WelcomeActivity.class);

        intent.putExtra(EXTRA_TEXT, text);

        startActivity(intent);

    }
}
