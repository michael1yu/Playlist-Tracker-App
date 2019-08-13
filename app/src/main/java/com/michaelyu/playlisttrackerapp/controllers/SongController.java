package com.michaelyu.playlisttrackerapp.controllers;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.michaelyu.playlisttrackerapp.asynctasks.WriteFirestoreSongAsyncTask;
import com.michaelyu.playlisttrackerapp.models.Playlist;
import com.michaelyu.playlisttrackerapp.models.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongController {

    Context context;
    Playlist playlist;

    public SongController(Playlist playlist, Context context) {
        this.context = context;
        this.playlist = playlist;
    }

    public void traversePlaylist() {
        String[] proj = {
                MediaStore.Audio.Playlists.Members.ARTIST,
                MediaStore.Audio.Playlists.Members.TITLE
        };

        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Playlists.Members.getContentUri("external", playlist.getId()),
                proj,
                null,
                null,
                null);

        final int ARTIST_INDEX = cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.ARTIST);
        final int TITLE_INDEX = cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.TITLE);
        Log.i("NUM_SONGS", cursor.getCount() + "");
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            String title = cursor.getString(TITLE_INDEX);
            String artist = cursor.getString(ARTIST_INDEX);
            String playlistName = playlist.getName();
            Song song = new Song(title, artist, playlistName);


            WriteFirestoreSongAsyncTask writeFirestoreSongAsyncTask = new WriteFirestoreSongAsyncTask(song, new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            }, context);
            writeFirestoreSongAsyncTask.execute();
            cursor.moveToNext();
        }
    }
}
