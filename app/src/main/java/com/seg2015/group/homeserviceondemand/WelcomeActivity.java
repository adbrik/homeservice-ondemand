package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        TextView textView_name = (TextView) findViewById(R.id.textView_name);

        textView_name.setText(text + "!");


    }
}
