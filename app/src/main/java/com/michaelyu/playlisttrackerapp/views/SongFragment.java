package com.michaelyu.playlisttrackerapp.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.michaelyu.playlisttrackerapp.R;
import com.michaelyu.playlisttrackerapp.asynctasks.GetAllSongsAsyncTask;
import com.michaelyu.playlisttrackerapp.models.Song;

import java.util.ArrayList;
import java.util.List;

public class SongFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SongAdapter songAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.song_fragment, container, false);
        Bundle args = getArguments();
        buildUi(view, args);
        return view;
    }

    private void buildUi(View view, Bundle args) {


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_song);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        songAdapter = new SongAdapter(new ArrayList<Song>());
        GetAllSongsAsyncTask getAllSongsAsyncTask = new GetAllSongsAsyncTask(new GetAllSongsAsyncTask.GetAllSongsListener() {
            @Override
            public void onGetSongsFinished(List<Song> result) {
                songAdapter = new SongAdapter(result);
                recyclerView.setAdapter(songAdapter);
            }
        }, args.getString("playlist_name"), getContext());
        getAllSongsAsyncTask.execute();
        songAdapter.notifyDataSetChanged();
    }

}
