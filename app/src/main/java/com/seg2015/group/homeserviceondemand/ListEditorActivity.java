package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ListEditorActivity extends AppCompatActivity {

    ServiceManager manager = ServiceManager.getInstance();
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_editor);

        final Service newService = new Service("","");

        Intent intent = getIntent();
        //int serviceIndex = intent.getIntExtra(intent.EXTRA_PHONE_NUMBER,0);
        //final Service editService = ServiceManager.getInstance().getServiceAt(serviceIndex);
        final EditText serviceName = (EditText) findViewById(R.id.line02);
        final EditText serviceRate = (EditText) findViewById(R.id.line04);

        Button saveButton = (Button) findViewById(R.id.buttonSave);
        Button cancelButton = (Button) findViewById(R.id.buttonCancel);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newService.setService(serviceName.getText().toString());
                newService.setRate(serviceRate.getText().toString());

                String sName = newService.getService();
                String sRate = newService.getRate();
                if (sName.length() == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "No service name entered", Toast.LENGTH_SHORT);
                    toast.show();
                    flag = false;
                }
                if (sRate.length() == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "No service rate entered", Toast.LENGTH_SHORT);
                    toast.show();
                    flag = false;
                }
                if (!(sRate.matches("[0-9]+"))) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Rate can only contain numbers", Toast.LENGTH_SHORT);
                    toast.show();
                    flag = false;
                }
                int counter = 0;
                while ((flag == true) && (counter < manager.getServiceList().size())) {
                    String x = manager.getServiceAt(counter).getService();
                    if (sName.equals(x)) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Service already added", Toast.LENGTH_SHORT);
                        toast.show();
                        flag = false;
                    }
                    counter++;
                }
                if(flag==true){
                    //if (isAdd)
                        ServiceManager.getInstance().getServiceList().add(newService);
                    //else{
                        //editService.setService(serviceName.getText().toString());
                        //editService.setRate(serviceRate.getText().toString());
                    //}
                }
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
