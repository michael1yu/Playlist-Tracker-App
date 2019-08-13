package com.michaelyu.playlisttrackerapp.views;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.michaelyu.playlisttrackerapp.R;
import com.michaelyu.playlisttrackerapp.controllers.PlaylistController;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton refreshFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new PlaylistFragment());
        fragmentTransaction.commit();

        refreshFAB = (FloatingActionButton) findViewById(R.id.refresh_btn);
        refreshFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClick();
            }
        });
    }

    private void handleClick() {
        PlaylistController playlistController = new PlaylistController(this);
        playlistController.traversePlaylists();
    }


}
