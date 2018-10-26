package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText inputUsername;
    private EditText inputPassword;
    public static final String EXTRA_TEXT = "com.seg2015.group.homeserviceondemand.EXTRA_TEXT";
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListnener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        inputUsername = (EditText) findViewById(R.id.userName_Field);
        inputPassword = (EditText) findViewById(R.id.password_Field);
        Button button = (Button) findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
                openWelcomeActivity();

            }
        });

    }

    public void openWelcomeActivity(){

        EditText userName_Field = (EditText) findViewById(R.id.userName_Field);

        String text = userName_Field.getText().toString();

        Intent intent = new Intent(this, WelcomeActivity.class);

        intent.putExtra(EXTRA_TEXT, text);

        startActivity(intent);

    }

    protected void onClick(View v){
        Intent i = new Intent(MainActivity.this,CreateAccount.class);
        startActivity(i);
    }

    private void startSignIn(){
        String username=inputUsername.getText().toString();
        String password=inputPassword.getText().toString();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_LONG).show();
        }
        else{
            auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Sign-In Problem", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}
