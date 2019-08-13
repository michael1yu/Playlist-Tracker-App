package com.michaelyu.playlisttrackerapp.views;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.michaelyu.playlisttrackerapp.R;

import java.util.ArrayList;
import java.util.List;

public class PlaylistFragment extends Fragment {


    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private PlaylistAdapter playlistAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playlist_fragment, container, false);
        buildUi(view);
        return view;
    }

    private void buildUi(View view) {


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_playlist);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        playlistAdapter = new PlaylistAdapter(getPlaylists(), new PlaylistAdapter.onItemClickListener() {
            @Override
            public void onClick(String playlist) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Bundle args = new Bundle();
                args.putString("playlist_name", playlist);
                SongFragment songFragment = new SongFragment();
                songFragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, songFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        recyclerView.setAdapter(playlistAdapter);


//        SongAdapter songAdapter = new SongAdapter(new ArrayList<Song>());
//        GetAllSongsAsyncTask getAllSongsAsyncTask = new GetAllSongsAsyncTask(new GetAllSongsAsyncTask.GetAllSongsListener() {
//            @Override
//            public void onGetSongsFinished(List<Song> result) {
//                         = new SongAdapter(result);
//                recyclerView.setAdapter(songAdapter);
//            }
//        }, getApplicationContext());
//        getAllSongsAsyncTask.execute();
//        songAdapter.notifyDataSetChanged();
    }



    private List<String> getPlaylists(){
        String[] proj = new String[]{"*"};
        Uri uri = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
        Cursor cursor = getContext().getContentResolver().query(uri , proj, null, null, null);

        final int NAME_INDEX = cursor.getColumnIndex(MediaStore.Audio.Playlists.NAME);

        cursor.moveToFirst();
        List<String> playlists = new ArrayList<>();
        for(int i = 0; i < cursor.getCount(); i++){
            playlists.add(cursor.getString(NAME_INDEX));
        }
        return playlists;
    }
}
