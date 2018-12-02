package com.seg2015.group.homeserviceondemand;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeOwnerActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private HomeOwnerAdapter serviceAdapter;
    private FullService service;
    private ArrayList<FullService> services;
    private ArrayList<String> names = new ArrayList<>();
    private HashMap<FullService,ArrayList<String>> listHashMap;
    private DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner);

        databaseUsers = FirebaseDatabase.getInstance().getReference("services");


        listView = (ExpandableListView)findViewById(R.id.allList);
        services = new ArrayList<>();
        listHashMap = new HashMap<>();

        serviceAdapter = new HomeOwnerAdapter(HomeOwnerActivity.this, services, listHashMap);
        listView.setAdapter(serviceAdapter);

    }

    protected void onStart(){
        super.onStart();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    FullService service = postSnapshot.getValue(FullService.class);
                    Toast toast = Toast.makeText(getApplicationContext(), service.getProvider() + service.getServiceName()+service.getServiceRate()+service.getServiceRating(), Toast.LENGTH_SHORT);
                    toast.show();
                    services.add(service);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
