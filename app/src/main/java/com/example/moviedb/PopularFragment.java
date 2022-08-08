package com.example.moviedb;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PopularFragment extends Fragment {
    String Popular_url = "https://api.themoviedb.org/3/movie/popular?api_key=b56475967d11d8351a1ba2b3c22b5ad4";
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_popular, container, false);
        Asynctask asynctask = new Asynctask(Popular_url);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        asynctask.execute();
        return view;
    }




    class Asynctask extends AsyncTask<String,String,String> {
        List<ModelView> movielist;
        String API_url;
        StringBuilder stringBuilder = new StringBuilder();
        public Asynctask(String API_url) {
            this.API_url = API_url;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(API_url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while (true){
                    String data = bufferedReader.readLine();
                    if (data == null){
                        break;
                    }
                    stringBuilder.append(data);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                movielist = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i<jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    ModelView modelView = new ModelView();
                    modelView.setId(jsonObject1.getInt("id"));
                    modelView.setOriginal_language(jsonObject1.getString("original_language"));
                    modelView.setTitle(jsonObject1.getString("title"));
                    modelView.setOriginal_title(jsonObject1.getString("original_title"));                    modelView.setOverview(jsonObject1.getString("overview"));
                    modelView.setRelease_date(jsonObject1.getString("release_date"));
                    modelView.setPoster_path(jsonObject1.getString("poster_path"));
                    modelView.setPopularity((float) jsonObject1.getDouble("popularity"));
                    modelView.setVote_average((float) jsonObject1.getDouble("vote_average"));

                    movielist.add(modelView);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            putdatainrecycler(movielist);
        }
    }
    public void putdatainrecycler(List<ModelView> movielist) {
        CustomAdapter customAdapter = new CustomAdapter(movielist);
        recyclerView.setAdapter(customAdapter);
    }
}