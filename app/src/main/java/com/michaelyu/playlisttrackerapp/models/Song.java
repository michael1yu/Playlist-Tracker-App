package com.michaelyu.playlisttrackerapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "songs")
public class Song {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "track_name")
    @SerializedName("track_name")
    private String trackName;

    @ColumnInfo(name = "artist")
    @SerializedName("artist")
    private String artist;

    @ColumnInfo(name = "playlist_name")
    @SerializedName("playlist_name")
    private String playlistName;

    public Song(){}

    @Ignore
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

    public void setTrackName(@NonNull String trackName) {
        this.trackName = trackName;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
}
