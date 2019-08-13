package com.michaelyu.playlisttrackerapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michaelyu.playlisttrackerapp.R;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    List<String> data;

    onItemClickListener onItemClickListener;

    public interface onItemClickListener{
        void onClick(String playlist);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }

    public PlaylistAdapter (List<String> data, onItemClickListener onItemClickListener){
        this.data = data;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlist_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        TextView playlistName = (TextView) holder.view.findViewById(R.id.playlist_name);
        playlistName.setText(data.get(position));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(data.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
