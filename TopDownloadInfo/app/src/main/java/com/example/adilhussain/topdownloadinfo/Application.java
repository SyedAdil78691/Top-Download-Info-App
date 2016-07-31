package com.example.adilhussain.topdownloadinfo;

import android.app.ListActivity;

import java.io.Serializable;


/**
 * Created by Adil Hussain on 7/5/2016.
 */
public class Application implements Serializable {
    private String name;
    private String artist;
    private String releaseDate;

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getArtist() {

        return artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {
        return "name='" + getName()  +
                ", artist='" + getArtist() +
                ", releaseDate='" + getReleaseDate() ;
    }




}
