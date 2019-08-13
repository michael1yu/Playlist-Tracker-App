package com.michaelyu.playlisttrackerapp.database;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.michaelyu.playlisttrackerapp.models.Song;

import java.util.List;

@Dao
public interface PlaylistDao {
    @Query("SELECT * FROM songs")
    public List<Song> getAll();

    @Query("SELECT * FROM songs WHERE playlist_name =:playlistName")
    public List<Song> getSongs(String playlistName);

    @Query("SELECT * FROM songs WHERE track_name=:trackName")
    public Song getSong(String trackName);

    @Insert
    public void insert(Song song);

    @Update
    public void update(Song song);

    @Delete
    public void delete(Song song);

}
