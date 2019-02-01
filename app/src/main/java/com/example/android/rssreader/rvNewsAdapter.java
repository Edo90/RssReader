package com.example.android.rssreader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.rssreader.model.FeedModel;

import java.util.List;

public class rvNewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<FeedModel> newsList;

    rvNewsAdapter(Context context){layoutInflater = LayoutInflater.from(context);}

    void insert(List<FeedModel> model){
        newsList = model;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.feed_view_item,viewGroup,false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, final int i) {
        if(!newsList.isEmpty()){
            FeedModel current = newsList.get(i);
            newsViewHolder.tvTitle.setText(current.getTitle());
            newsViewHolder.tvDescription.setText(current.getDescription());
            newsViewHolder.tvLink.setText(current.getLink());
        }else {
            newsViewHolder.tvTitle.setText("No Data");
            newsViewHolder.tvDescription.setText("No Data");
            newsViewHolder.tvLink.setText("No Data");
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
