package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.PopupMenu;
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
    private boolean flag = true;

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

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener(){
            @Override
            public boolean onGroupClick(ExpandableListView parent, final View view, final int position, long id){
                final String item = (String)parent.getItemAtPosition(position);
                final int pos = parent.getPositionForView(view);

                PopupMenu popup = new PopupMenu(HomeOwnerActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.rate_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals("Rate")){
                            Intent i = new Intent(HomeOwnerActivity.this, RatingActivity.class);
                            i.putExtra("position",String.valueOf(pos));
                            startActivityForResult(i, 1);
                        }
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });

    }

    protected void onStart(){
        super.onStart();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    FullService service = postSnapshot.getValue(FullService.class);
                    if(flag ==true){
                        services.add(service);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public FullService getFullServiceAt(int position){
        return services.get(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1 && resultCode == 1 && intent != null) {
            String rates = intent.getStringExtra("rating");
            String comments = intent.getStringExtra("comment");
            String positions = intent.getStringExtra("position");
            int result = Integer.parseInt(positions);
            getFullServiceAt(result).setRate(rates);
            getFullServiceAt(result).setComment(comments);
            flag = false;
            serviceAdapter.notifyDataSetChanged();
            listView.refreshDrawableState();
        }
    }

}
