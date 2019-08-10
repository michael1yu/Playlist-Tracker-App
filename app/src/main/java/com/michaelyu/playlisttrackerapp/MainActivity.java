package com.michaelyu.playlisttrackerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.michaelyu.playlisttrackerapp.controllers.PlaylistController;

public class MainActivity extends AppCompatActivity {
    private Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildUi();
    }

    private void buildUi() {
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
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
