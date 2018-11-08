package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.*;


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
                startSignIn();
            }
        });

    }

    public void openWelcomeActivity(User user){

        EditText userName_Field = (EditText) findViewById(R.id.userName_Field);

        String text = userName_Field.getText().toString();

        Intent intent = new Intent(this, WelcomeActivity.class);

        intent.putExtra("userClass", user);

        startActivity(intent);

    }

    public void onClick(View v){
        Intent i = new Intent(MainActivity.this,CreateAccount.class);
        startActivity(i);
    }

    private void startSignIn(){
        final String username= ((EditText) findViewById(R.id.userName_Field)).getText().toString();
        final String password= ((EditText) findViewById(R.id.password_Field)).getText().toString();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_LONG).show();
        }
        else{
            final DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");

            database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                        User user = postSnapshot.getValue(User.class);
                        if (username.equals(user.getName())&&password.equals(user.getPassword())){
                            openWelcomeActivity(user);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}
