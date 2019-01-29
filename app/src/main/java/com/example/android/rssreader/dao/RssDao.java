package com.example.android.rssreader.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.rssreader.model.RSSModel;

import java.util.List;
@Dao
public interface RssDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RSSModel Model);
    @Update
    void update(RSSModel Model);

    @Query("DELETE FROM RSS_Table")
    void delete();


    @Query("SELECT * FROM RSS_Table")
    LiveData<List<RSSModel>> getAll();

    @Query("SELECT * FROM RSS_Table WHERE ID = :id LIMIT 1")
    LiveData<RSSModel> getById(int id);

}
