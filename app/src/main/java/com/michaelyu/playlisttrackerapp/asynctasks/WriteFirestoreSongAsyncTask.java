package com.michaelyu.playlisttrackerapp.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.michaelyu.playlisttrackerapp.apicalls.ApiCalls;
import com.michaelyu.playlisttrackerapp.controllers.PlaylistDatabaseController;
import com.michaelyu.playlisttrackerapp.models.Song;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class WriteFirestoreSongAsyncTask extends AsyncTask<Void, Void, Void> {
    private static final String baseUrl = "https://playlist-tracker.herokuapp.com/";
    Song song;
    Callback<ResponseBody> callback;
    Context context;

    public WriteFirestoreSongAsyncTask(Song song, Callback<ResponseBody> callback, Context context){
        this.song = song;
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        PlaylistDatabaseController playlistDatabaseController = new PlaylistDatabaseController(context);
        if(playlistDatabaseController.getSong(song.getTrackName()) == null){
            playlistDatabaseController.writeSong(song);
        } else {
            playlistDatabaseController.updateSong(song);
        }
        playlistDatabaseController.close();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();
        ApiCalls api = retrofit.create(ApiCalls.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), song.getJson());
        Call<ResponseBody> response = api.writeTrack(requestBody);
        response.enqueue(callback);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(context,"Sending...", Toast.LENGTH_SHORT).show();
    }
}
