package com.seg2015.group.homeserviceondemand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ServiceProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.service_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile:
                Toast toast = Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            case R.id.service:
                Toast toast1 = Toast.makeText(getApplicationContext(), "Service", Toast.LENGTH_SHORT);
                toast1.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
