package com.example.android.rssreader.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface BaseDao <M> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    <M> void insert(M Model);
    @Update
    <M> void update(M Model);
    @Delete
    <M> void delete(int id);

}
