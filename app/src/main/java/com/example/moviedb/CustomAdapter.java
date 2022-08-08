package com.example.moviedb;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    static List<ModelView> movie_data;
    public CustomAdapter(List<ModelView> movielist) {
        this.movie_data = movielist;
    }
    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(view);
    }
    String url = "https://image.tmdb.org/t/p/original";
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        holder.List_title.setText(movie_data.get(position).getTitle());
        Glide.with(holder.itemView).load(url+movie_data.get(position).getPoster_path()).into(holder.titleimage);
    }

    @Override
    public int getItemCount() {
        return movie_data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView List_title;
        ImageView titleimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            List_title = itemView.findViewById(R.id.List_title);
            titleimage = itemView.findViewById(R.id.titleimage);
            List_title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String loc = String.valueOf(getAdapterPosition());
            Intent intent = new Intent(itemView.getContext(), MainActivity2.class);
            intent.putExtra("com.example.moviedb.loc", loc);
            intent.putExtra("com.example.moviedb.data", (Serializable) movie_data);
            itemView.getContext().startActivity(intent);
        }
    }
}
