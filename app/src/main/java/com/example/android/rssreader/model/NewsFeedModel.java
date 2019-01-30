package com.example.android.rssreader.model;

public class NewsFeedModel {
    String link;
    String title;
    String description;

    public NewsFeedModel(String link, String title, String description) {
        this.link = link;
        this.title = title;
        this.description = description;
    }

    public NewsFeedModel() {
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
