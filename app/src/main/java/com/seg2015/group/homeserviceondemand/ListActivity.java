package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    ServiceAdapter adapter;
    ServiceManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView)findViewById(R.id.list);

        manager = ServiceManager.getInstance();

        adapter = new ServiceAdapter(this, manager.getServiceList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,final View view, int position, long id){
                final String item = (String)parent.getItemAtPosition(position);
            }
        });
    }

    public void addToList(View view){
        Intent launchListEditor = new Intent(getApplicationContext(), ListEditorActivity.class);
        startActivityForResult(launchListEditor, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        adapter.notifyDataSetChanged();
    }
}
