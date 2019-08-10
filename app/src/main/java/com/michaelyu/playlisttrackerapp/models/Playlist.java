package com.michaelyu.playlisttrackerapp.models;

import android.net.Uri;
import android.provider.MediaStore;

public class Playlist {
    private String name;
    private int id;
    private Uri uri;

    public Playlist(String name, int id){
        this.name = name;
        this.id = id;
        this.uri = MediaStore.Audio.Playlists.Members.getContentUri("external", id);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Uri getUri(){
        return uri;
    }
}
