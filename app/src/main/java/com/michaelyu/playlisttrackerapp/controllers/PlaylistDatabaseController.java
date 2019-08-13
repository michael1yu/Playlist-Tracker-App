package com.michaelyu.playlisttrackerapp.controllers;


import android.content.Context;

import androidx.room.Room;

import com.michaelyu.playlisttrackerapp.database.PlaylistDatabase;
import com.michaelyu.playlisttrackerapp.models.Song;

import java.util.List;

public class PlaylistDatabaseController {
    private PlaylistDatabase db;
    public PlaylistDatabaseController(Context context){
        db = Room.databaseBuilder(context,
                PlaylistDatabase.class, "database-name").build();
    }

    public PlaylistDatabase getDb(){
        return db;
    }

    public void writeSong(Song song){
        db.playlistDao().insert(song);
    }

    public void updateSong(Song song){
        db.playlistDao().update(song);
    }

    public List<Song> getAll(){
        return db.playlistDao().getAll();
    }

    public List<Song> getPlaylist(String playlistName){
        return db.playlistDao().getSongs(playlistName);
    }

    public Song getSong(String trackName){
        return db.playlistDao().getSong(trackName);
    }

    public void close(){
        db.close();
    }
}
