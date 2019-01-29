package com.example.android.rssreader.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.rssreader.model.FeedModel;

import java.util.List;

@Dao
public interface FeedDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FeedModel Model);
    @Update
    void update(FeedModel Model);
    @Delete
    void delete();

    @Query("SELECT * FROM FEED_TABLE")
    List<FeedModel> getAllFeeds();

    @Query("SELECT * FROM FEED_TABLE WHERE RssModelId = :RssModelId")
    List<FeedModel> getAllFeedsByFeedId(int RssModelId);

}
