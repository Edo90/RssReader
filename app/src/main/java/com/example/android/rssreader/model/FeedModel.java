package com.example.android.rssreader.model;

public class FeedModel {

    int id;

    String link;
    String title;
    String description;
    String picture;


    public FeedModel(String link, String title, String description) {
        this.link = link;
        this.title = title;
        this.description = description;
    }

    public FeedModel(String link, String title, String description, String picture) {
        this.link = link;
        this.title = title;
        this.description = description;
        this.picture = picture;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
