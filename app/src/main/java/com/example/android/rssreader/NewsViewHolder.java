package com.example.android.rssreader;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class NewsViewHolder extends RecyclerView.ViewHolder {

    final TextView tvTitle,tvDescription,tvLink;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.titleText);
        tvDescription = itemView.findViewById(R.id.descriptionText);
        tvLink = itemView.findViewById(R.id.tvLink);


    }
}
