package com.example.android.rssreader;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.rssreader.model.RSSModel;
import com.example.android.rssreader.viewmodel.RssViewModel;

import java.util.List;

public class rvRssAdapter extends RecyclerView.Adapter<rvRssAdapter.rssViewHolder>{

    private final LayoutInflater layoutInflater;
    private List<RSSModel> rssModelList;
    private Context mainContext;
    private RssViewModel rssViewModel;


    rvRssAdapter(Context context, RssViewModel rssViewModel){
        mainContext = context;
        this.rssViewModel = rssViewModel;
        layoutInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public rssViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.recycler_view_rss_item,viewGroup,false);
        return new rssViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull rssViewHolder rssViewHolder, int i) {
        final int position = i;
        setListener(rssViewHolder, position);
        if(rssModelList != null){
            RSSModel current = rssModelList.get(i);
            rssViewHolder.rssTitleView.setText(current.getName());
        } else{
            rssViewHolder.rssTitleView.setText("Sin Titulo");
        }
    }

    private void setListener(@NonNull rssViewHolder rssViewHolder, final int position) {
        rssViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainContext,NewsActivity.class);
                intent.putExtra("link",rssModelList.get(position).getLink());
                mainContext.startActivity(intent);
            }
        });

        rssViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final int id = position + 1;
                rssViewModel.delete(id);
                return false;
            }
        });
    }

    void insertRss(List<RSSModel> model){
        rssModelList = model;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(rssModelList != null){
            return rssModelList.size();
        }
        else {
            return 0;
        }
    }

    class rssViewHolder extends RecyclerView.ViewHolder{
        private final TextView rssTitleView;

        private rssViewHolder(View itemView){
            super(itemView);
            rssTitleView = itemView.findViewById(R.id.rssTitle);
        }
    }


}
