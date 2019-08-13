package com.michaelyu.playlisttrackerapp.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.michaelyu.playlisttrackerapp.controllers.PlaylistDatabaseController;
import com.michaelyu.playlisttrackerapp.models.Song;

import java.util.List;

public class GetAllSongsAsyncTask extends AsyncTask <Void, Void, Void>{

    Context context;
    List<Song> songs;
    String playlistName;
    GetAllSongsListener listener;

    public interface GetAllSongsListener{
        public void onGetSongsFinished(List<Song> result);
    }

    public GetAllSongsAsyncTask(GetAllSongsListener getAllSongsListener, String playlistName, Context context){
        this.context = context;
        this.listener = getAllSongsListener;
        this.playlistName = playlistName;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        PlaylistDatabaseController playlistDatabaseController = new PlaylistDatabaseController(context);
        if(playlistName == null)
            songs = playlistDatabaseController.getAll();
        else
            songs = playlistDatabaseController.getPlaylist(playlistName);
        playlistDatabaseController.close();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onGetSongsFinished(songs);
    }
}
