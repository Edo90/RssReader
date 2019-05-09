package com.example.android.rssreader.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "RSS_Table")
public class RSSModel implements Parcelable {
    public static final Creator<RSSModel> CREATOR = new Creator<RSSModel>() {
        @Override
        public RSSModel createFromParcel(Parcel in) {
            return new RSSModel(in);
        }

        @Override
        public RSSModel[] newArray(int size) {
            return new RSSModel[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String link;

    public RSSModel(String link, String name) {
        this.link = link;
        this.name = name;
    }

    private String name;

    protected RSSModel(Parcel in) {
        id = in.readInt();
        link = in.readString();
        name = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(link);
        dest.writeString(name);
    }
}
