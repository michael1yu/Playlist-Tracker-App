package com.michaelyu.playlisttrackerapp.controllers;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.michaelyu.playlisttrackerapp.models.Playlist;
import com.michaelyu.playlisttrackerapp.models.Song;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PlaylistController {

    Context context;

    public PlaylistController(Context context){
        this.context = context;
    }


    public void traversePlaylists() {
        String[] proj = new String[]{"*"};
        Uri uri = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
        Cursor cursor = context.getContentResolver().query(uri , proj, null, null, null);

        final int NAME_INDEX = cursor.getColumnIndex(MediaStore.Audio.Playlists.NAME);
        final int ID_INDEX = cursor.getColumnIndex(MediaStore.Audio.Playlists._ID);

        cursor.moveToFirst();
        Queue<Playlist> queue = new LinkedList<>();
        for(int i = 0; i < cursor.getCount(); i++){
            String name = cursor.getString(NAME_INDEX);
            int id = cursor.getInt(ID_INDEX);
            queue.add(new Playlist(name, id));
            Log.i("PLAYLIST", cursor.getString(NAME_INDEX));
            cursor.moveToNext();
        }
        while(!queue.isEmpty()){
            Playlist playlist = queue.poll();
            SongController songController = new SongController(playlist, context);
            songController.traversePlaylist();
        }
        cursor.close();
    }
}
