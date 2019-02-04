package com.example.android.rssreader;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.rssreader.dto.FeedDto;
import com.example.android.rssreader.model.RSSModel;
import com.example.android.rssreader.viewmodel.RssViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_RSS_ACTIVITY_REQUEST_CODE = 1;
    private RssViewModel rssViewModel;
    private Button rbAddNewFeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        rbAddNewFeed = findViewById(R.id.rbAddNewFeed);
        rssViewModel = ViewModelProviders.of(this).get(RssViewModel.class);

        final rvRssAdapter adapter = new rvRssAdapter(this,rssViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rssViewModel.getRssList().observe(this, new Observer<List<RSSModel>>() {
            @Override
            public void onChanged(@Nullable List<RSSModel> rssModels) {
                adapter.insertRss(rssModels);
            }
        });



        rbAddNewFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewFeed.class);
                startActivityForResult(intent, NEW_RSS_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_RSS_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            FeedDto feedDto = (FeedDto) data.getSerializableExtra(NewFeed.EXTRA_REPLY);
            RSSModel rssModel = new RSSModel(feedDto.getLink(),feedDto.getName());
            rssViewModel.insert(rssModel);
        } else {
            Toast.makeText(this, "No se guardo", Toast.LENGTH_SHORT).show();
        }
    }
}
