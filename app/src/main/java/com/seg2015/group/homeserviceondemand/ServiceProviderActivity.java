package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import java.util.ArrayList;

public class ServiceProviderActivity extends AppCompatActivity {

    ListView listView;
    ServiceProviderAdapter serviceAdapter;
    Service service;
    ArrayList<Service> services;
    ArrayList<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);

        listView = (ListView)findViewById(R.id.service_provider);
        services = new ArrayList<>();
        serviceAdapter = new ServiceProviderAdapter(this, services);
        listView.setAdapter(serviceAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id){
                final String item = (String)parent.getItemAtPosition(position);
                final int pos = parent.getPositionForView(view);

                PopupMenu popup = new PopupMenu(ServiceProviderActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.delete_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals("Delete")){
                            Service currentService = getServiceAt(pos);
                            serviceAdapter.delete(currentService);
                            serviceAdapter.notifyDataSetChanged();
                        }
                        return true;
                    }
                });
                popup.show();

            }
        });

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
                Intent i = new Intent(this, AddService.class);
                startActivityForResult(i, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1 && resultCode == 1 && intent != null) {
            service = new Service("","");
            String x = intent.getStringExtra("name");
            String y = intent.getStringExtra("rate");

            if(names.contains(x)){
                Toast toast = Toast.makeText(getApplicationContext(), "Service already added. Try again!", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                names.add(x);
                service.setService(x);
                service.setRate(y);
                services.add(service);
                serviceAdapter.notifyDataSetChanged();
            }
        }
    }

    public Service getServiceAt(int index) {
        return services.get(index);
    }

}
