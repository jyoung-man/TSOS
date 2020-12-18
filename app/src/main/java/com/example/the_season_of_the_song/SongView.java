package com.example.the_season_of_the_song;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SongView extends ConstraintLayout {

    TextView view_title;
    TextView view_artist;
    TextView view_album;

    public SongView(Context context) {
        super(context);
        init(context);
    }

    public SongView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(Context context) {

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.title_singer, this, true);

        view_title = (TextView) findViewById(R.id.its_title);
        view_artist = (TextView) findViewById(R.id.its_artist);
        view_album = (TextView) findViewById(R.id.its_album);

    }

    public void setView_title(String title) {
        this.view_title.setText(title);
    }

    public void setView_artist(String artist) {
        this.view_artist.setText(artist);
    }

    public void setView_album(String album) {
        this.view_album.setText(album);
    }

}
