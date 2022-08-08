package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    TextView title;
    TextView org_title;
    TextView org_lang;
    TextView id;
    TextView overview;
    TextView date;
    TextView popularity;
    TextView vote;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String loc = intent.getStringExtra("com.example.moviedb.loc");
        List<ModelView> modelViews = (List<ModelView>) intent.getSerializableExtra("com.example.moviedb.data");
        int i=Integer.parseInt(loc);

        poster = findViewById(R.id.poster);
        org_title = findViewById(R.id.original_title);
        title = findViewById(R.id.title);
        org_lang = findViewById(R.id.orignal_language);
        vote = findViewById(R.id.vote_average);
        popularity = findViewById(R.id.popularity);
        overview = findViewById(R.id.overview);
        id = findViewById(R.id.movie_id);
        date = findViewById(R.id.release_date);

        String url = "https://image.tmdb.org/t/p/original"+modelViews.get(i).getPoster_path();
        Glide.with(this).load(url).into(poster);
        org_lang.setText(": " + modelViews.get(i).getOriginal_language());
        id.setText(": " + modelViews.get(i).getId());
        title.setText(": " + modelViews.get(i).getTitle());
        org_title.setText(": " + modelViews.get(i).getOriginal_title());
        org_lang.setText(": " + modelViews.get(i).getOriginal_language());
        date.setText(": " + modelViews.get(i).getRelease_date());
        vote.setText(": " + modelViews.get(i).getVote_average());
        popularity.setText(": " + modelViews.get(i).getPopularity());
        overview.setText(": " + modelViews.get(i).getOverview());
    }
}