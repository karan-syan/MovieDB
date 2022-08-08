package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity{
    Button login;
    TextView signup;
    TextView invalid;
    EditText loginid;
    EditText loginpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.Login);
        invalid = findViewById(R.id.invalid);
        loginid = findViewById(R.id.editTextPhone);
        loginpassword = findViewById(R.id.editPassword);
        signup = findViewById(R.id.signup);


        login.setOnClickListener(v -> {
            dbHandler db = new dbHandler(this);
            Log.d("newimp", "getserialstart");
            Log.d("newimp", "getserialcompleted");
            Log.d("newimp", loginid.getText().toString());
            Log.d("newimp", loginpassword.getText().toString());
            if(loginid.getText().toString().isEmpty() && loginpassword.getText().toString().isEmpty()){
                invalid.setText("Invalid Phone no. and Password");
            }
            else{
                if(db.read_user(loginid.getText().toString(), loginpassword.getText().toString())) {
                    Log.d("newimp", "login_if_has_entere");
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    invalid.setText("Inavlid username or password");
                }
            }

        });

        signup.setOnClickListener(v -> {
            Intent intent = new Intent(this,Signup.class);
            startActivity(intent);
        });
    }
}