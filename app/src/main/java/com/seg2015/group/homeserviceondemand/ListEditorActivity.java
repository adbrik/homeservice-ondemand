package com.seg2015.group.homeserviceondemand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ListEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_editor);

        final Service newService = new Service("","");


        final EditText serviceName = (EditText) findViewById(R.id.line02);
        final EditText seviceRate = (EditText) findViewById(R.id.line04);

        Button saveButton = (Button) findViewById(R.id.buttonSave);
        Button cancelButton = (Button) findViewById(R.id.buttonCancel);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newService.setService(serviceName.getText().toString());
                newService.setRate(seviceRate.getText().toString());
                ServiceManager.getInstance().getServiceList().add(newService);
                finish();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
