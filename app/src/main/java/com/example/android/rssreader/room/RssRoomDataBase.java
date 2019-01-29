package com.example.android.rssreader.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.android.rssreader.dao.RssDao;
import com.example.android.rssreader.model.FeedModel;
import com.example.android.rssreader.model.RSSModel;

@Database(entities = {FeedModel.class, RSSModel.class}, version = 1)
public abstract class RssRoomDataBase extends RoomDatabase {
  //  public abstract FeedModel feedModel();
    public abstract RssDao rssDao();

    private static volatile RssRoomDataBase INSTANCE;

    public static RssRoomDataBase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (RssRoomDataBase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),RssRoomDataBase.class,"rss_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
