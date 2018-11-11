package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

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
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id){
                final String item = (String)parent.getItemAtPosition(position);
                int pos = parent.getPositionForView(view);
                Toast.makeText(getApplicationContext(),pos+"",Toast.LENGTH_SHORT).show();

                PopupMenu popup = new PopupMenu(ListActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.list_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals("Modify")){
                            Intent intent = new Intent(listView.getContext(),ListEditorActivity.class);
                            intent.putExtra("POSITION",position);
                            intent.putExtra("ADD",false);
                            startActivityForResult(intent,0);
                        }
                        if (item.getTitle().toString().equals("Delete")) {
                            Service currentService = manager.getServiceAt(position);
                            adapter.delete(currentService);
                        }
                        return true;
                    }
                });
                popup.show();

            }
        });
    }

    public void addToList(View view){
        Intent launchListEditor = new Intent(getApplicationContext(), ListEditorActivity.class);
        //launchListEditor.putExtra(Intent.EXTRA_TEXT,true);
        //launchListEditor.putExtra(Intent.EXTRA_PHONE_NUMBER, 0);
        startActivityForResult(launchListEditor, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        adapter.notifyDataSetChanged();
        listView.refreshDrawableState();
    }
}