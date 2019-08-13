
package com.michaelyu.playlisttrackerapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michaelyu.playlisttrackerapp.R;
import com.michaelyu.playlisttrackerapp.models.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    List<Song> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }

    public SongAdapter(List<Song> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = data.get(position);
        TextView songName = (TextView) holder.view.findViewById(R.id.song_name);
        songName.setText(song.getTrackName());
        TextView artist = (TextView) holder.view.findViewById(R.id.artist);
        artist.setText(song.getArtist());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
