package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button popularbtn;
    Button topratebtn;
    Button latestbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popularbtn = findViewById(R.id.popular);
        latestbtn = findViewById(R.id.latest);
        topratebtn = findViewById(R.id.toprated);

        topratebtn.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.mainfrag, new Top_ratedFragment()).commit();
            topratebtn.setBackgroundColor(topratebtn.getContext().getResources().getColor(R.color.primecolor));
            popularbtn.setBackgroundColor(topratebtn.getContext().getResources().getColor(R.color.normcolor));
            latestbtn.setBackgroundColor(latestbtn.getContext().getResources().getColor(R.color.normcolor));

        });
        popularbtn.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.mainfrag, new PopularFragment()).commit();
            popularbtn.setBackgroundColor(topratebtn.getContext().getResources().getColor(R.color.primecolor));
            topratebtn.setBackgroundColor(topratebtn.getContext().getResources().getColor(R.color.normcolor));
            latestbtn.setBackgroundColor(latestbtn.getContext().getResources().getColor(R.color.normcolor));
        });
        latestbtn.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.mainfrag, new LatestFragment()).commit();
            popularbtn.setBackgroundColor(topratebtn.getContext().getResources().getColor(R.color.normcolor));
            topratebtn.setBackgroundColor(topratebtn.getContext().getResources().getColor(R.color.normcolor));
            latestbtn.setBackgroundColor(latestbtn.getContext().getResources().getColor(R.color.primecolor));
        });
    }
}