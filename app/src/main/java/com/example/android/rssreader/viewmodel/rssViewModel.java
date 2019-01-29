package com.example.android.rssreader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.rssreader.model.RSSModel;
import com.example.android.rssreader.repository.rssRepository;

import java.util.List;

public class rssViewModel extends AndroidViewModel {
    private rssRepository repository;
    private LiveData<List<RSSModel>> rssList;

    public rssViewModel(@NonNull Application application) {
        super(application);
        this.repository = new rssRepository(application);
        this.rssList = repository.getListRssModel();
    }

    public LiveData<List<RSSModel>> getRssList() {
        return rssList;
    }

    public void insert(RSSModel rssModel){ repository.insert(rssModel);}
}
