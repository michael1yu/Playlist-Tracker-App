package com.michaelyu.playlisttrackerapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.michaelyu.playlisttrackerapp.models.Song;

@Database(entities = {Song.class}, version = 1, exportSchema = false)
public abstract class PlaylistDatabase extends RoomDatabase {
    public abstract PlaylistDao playlistDao();
}
