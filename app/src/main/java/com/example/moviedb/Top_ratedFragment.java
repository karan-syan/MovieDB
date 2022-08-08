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

public class Top_ratedFragment extends Fragment {
    String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=b56475967d11d8351a1ba2b3c22b5ad4";
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_top_rated, container, false);
        Asynctask_toprated Asynctask_toprated = new Asynctask_toprated(url);
        recyclerView = view.findViewById(R.id.recyclerView_toprated);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        Asynctask_toprated.execute();
        return view;
    }

    class Asynctask_toprated extends AsyncTask<String,String,String> {
        List<ModelView> movielist;
        String url;
        StringBuilder stringBuilder = new StringBuilder();
        public Asynctask_toprated(String top_rated_url) {
            this.url = top_rated_url;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url_toprated = new URL(url);
                HttpURLConnection connection_toprated = (HttpURLConnection) url_toprated.openConnection();
                connection_toprated.setRequestMethod("GET");
                connection_toprated.connect();
                BufferedReader bufferedReader_toprated = new BufferedReader(new InputStreamReader(connection_toprated.getInputStream()));
                while (true){
                    String data2 = bufferedReader_toprated.readLine();
                    if (data2 == null){
                        break;
                    }
                    stringBuilder.append(data2);
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
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
            putdatainrecycler_toprated(movielist);
        }
    }
    public void putdatainrecycler_toprated(List<ModelView> movielist) {
        CustomAdapter customAdapter = new CustomAdapter(movielist);

        recyclerView.setAdapter(customAdapter);
    }
}
