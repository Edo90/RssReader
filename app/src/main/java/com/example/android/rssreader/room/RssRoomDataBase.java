package com.example.android.rssreader.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.rssreader.dao.RssDao;
import com.example.android.rssreader.model.RSSModel;

@Database(entities = {RSSModel.class}, version = 2)
public abstract class RssRoomDataBase extends RoomDatabase {
  //  public abstract FeedModel feedModel();
    public abstract RssDao rssDao();

    private static volatile RssRoomDataBase INSTANCE;

    public static RssRoomDataBase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (RssRoomDataBase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),RssRoomDataBase.class,"rss_database")
                            .addCallback(sRoomDataBaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDataBaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            if(db.query("SELECT * FROM RSS_Table").getCount() == 0){
                new PopulateDbAsync(INSTANCE).execute();
            }
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void> {

        private final RssDao mRssDao;

        public PopulateDbAsync(RssRoomDataBase db) {
            mRssDao = db.rssDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //mRssDao.delete();
            RSSModel rssModel = new RSSModel("https://www.diariolibre.com/rss/portada.xml","Diario Libre");
            mRssDao.insert(rssModel);

            rssModel = new RSSModel("http://feed.androidauthority.com","Android Authority");
            mRssDao.insert(rssModel);

            return null;
        }
    }

}
