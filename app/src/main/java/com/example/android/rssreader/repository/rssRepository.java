package com.example.android.rssreader.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.android.rssreader.dao.RssDao;
import com.example.android.rssreader.model.RSSModel;
import com.example.android.rssreader.room.RssRoomDataBase;

import java.util.List;

public class rssRepository {
    private RssDao rssDao;
    private LiveData<List<RSSModel>> listRssModel;

    public rssRepository(Application application) {
        RssRoomDataBase dataBase = RssRoomDataBase.getDatabase(application);
        rssDao = dataBase.rssDao();
        listRssModel = rssDao.getAll();
    }

    public LiveData<List<RSSModel>> getListRssModel(){
        return listRssModel;
    }

    public void insert(RSSModel rssModel){
        new insertASyncTask(rssDao).execute(rssModel);
    }

    private static class insertASyncTask extends AsyncTask<RSSModel,Void,Void> {

        private RssDao aSyncTaskDao;

        @Override
        protected Void doInBackground(RSSModel... rssModels) {
            aSyncTaskDao.insert(rssModels[0]);
            return null;
        }

        insertASyncTask(RssDao aSyncTaskDao){
            this.aSyncTaskDao = aSyncTaskDao;
        }
    }

}
