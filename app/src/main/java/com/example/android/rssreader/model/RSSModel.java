package com.example.android.rssreader.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "RSS_Table")
public class RSSModel {
    @PrimaryKey(autoGenerate = true)
    int id;
    String link;
    String name;

    public RSSModel(String link, String name) {
        this.link = link;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
