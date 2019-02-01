package com.example.android.rssreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.rssreader.model.FeedModel;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        RecyclerView rvNews = findViewById(R.id.rvNews);
        rvNewsAdapter adapter = new rvNewsAdapter(this);
        //adapter.insert(generateFakeData());
        //rvNews.setAdapter(adapter);
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        new FetchFeedTask(String.valueOf(link),rvNews,adapter, this.getApplicationContext()).execute((Void) null);


    }

    private List<FeedModel> generateFakeData() {
        List<FeedModel> fakeData = new ArrayList<>();
        FeedModel feedModel = new FeedModel("http://google.com","this is a google title","this is a big ass description");

        for(int i = 0; i < 5; i++){
            fakeData.add(feedModel);
        }
        return fakeData;
    }
}
