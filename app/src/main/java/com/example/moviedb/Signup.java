package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Signup extends AppCompatActivity {
    Button signup;
    TextView Login;
    TextView diff_pass;
    EditText signup_id;
    EditText signup_password;
    EditText conf_pass;
    dbHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        handler = new dbHandler(this);

        signup = findViewById(R.id.signup);
        signup_id = findViewById(R.id.editTextPhone);
        signup_password = findViewById(R.id.editPassword);
        conf_pass = findViewById(R.id.confirm_Password);
        diff_pass = findViewById(R.id.diff_pass);
        Login = findViewById(R.id.login_from_singup);

        signup.setOnClickListener(v -> {
            Log.d("oppsss", "before if statement");
            Log.d("oppsss",signup_id.getText().toString() );
            Log.d("oppsss",signup_password.getText().toString() );
            if (signup_id.getText().toString().isEmpty() && signup_password.getText().toString().isEmpty()){
                diff_pass.setText("Invalid Phone no. and password");
            }
            else{
                if (signup_password.getText().toString().equals(conf_pass.getText().toString()) && handler.check_id(signup_id.getText().toString())){
                    Log.d("oppsss","if statemnt entered");
                    handler.Add_user(signup_id.getText().toString(), signup_password.getText().toString());
                    Intent intent = new Intent(this,LoginActivity.class);
                    Log.d("oppsss","entering login");
                    startActivity(intent);
                }else{
                    diff_pass.setText("Password and confirm password is not same");
                }
            }
        });

        Login.setOnClickListener(v -> {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        });
    }
}