package com.example.android.rssreader.dto;

import java.io.Serializable;

public class FeedDto implements Serializable {
    String name;
    String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public FeedDto(String name, String link) {
        this.name = name;
        this.link = link;
    }
}
