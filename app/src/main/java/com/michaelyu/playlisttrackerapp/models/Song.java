package com.michaelyu.playlisttrackerapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Song {
    @SerializedName("track_name")
    private String trackName;
    @SerializedName("artist")
    private String artist;
    @SerializedName("playlist_name")
    private String playlistName;

    public Song(String trackName, String artist, String playlistName) {
        this.trackName = trackName;
        this.artist = artist;
        this.playlistName = playlistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getArtist() {
        return artist;
    }

    public String getPlaylistName(){
        return playlistName;
    }

    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
