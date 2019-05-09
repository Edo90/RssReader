package com.example.android.rssreader;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.rssreader.Utils.StringUtils;
import com.example.android.rssreader.model.FeedModel;

import java.util.List;

public class rvNewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<FeedModel> newsList;
    private Context context;

    rvNewsAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

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
        setListener(newsViewHolder);
        if(!newsList.isEmpty()){
            FeedModel current = newsList.get(i);

            String limitString = current.getDescription().length() > 300 ? current.getDescription().substring(0,300) : current.getDescription();
            newsViewHolder.tvTitle.setText(current.getTitle());
            newsViewHolder.tvDescription.setText(limitString);
            newsViewHolder.tvLink.setText(current.getLink());
            if (StringUtils.isNullOrEmpty(current.getPicture()))
                newsViewHolder.ivNewsImage.setVisibility(View.GONE);
            else
                Glide.with(this.context).asBitmap().load(current.getPicture()).override(600, 200).into(newsViewHolder.ivNewsImage);
        }else {
            newsViewHolder.tvTitle.setText("No Data");
            newsViewHolder.tvDescription.setText("No Data");
            newsViewHolder.tvLink.setText("No Data");
        }
    }

    private void setListener(NewsViewHolder newsViewHolder) {

        newsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView link = v.findViewById(R.id.tvLink);
                Uri uri = Uri.parse("googlechrome://navigate?url="+link.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                if(intent.resolveActivity(context.getPackageManager()) == null){
                    intent.setData(Uri.parse(link.getText().toString()));
                }

                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
