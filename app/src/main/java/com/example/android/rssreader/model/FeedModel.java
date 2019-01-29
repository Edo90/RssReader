package com.example.android.rssreader.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "FEED_TABLE")
public class FeedModel {
    @PrimaryKey(autoGenerate = true)
    int id;

    String link;
    String title;
    String description;
    @ForeignKey(entity = FeedModel.class, parentColumns = "id", childColumns ="RssModelId")
    int RssModelId;

    public FeedModel(String link, String title, String description) {
        this.link = link;
        this.title = title;
        this.description = description;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
