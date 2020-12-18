package com.example.the_season_of_the_song;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class SongAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Song> Songs;
    public SongAdapter(Context context) {
        this.context = context;
        Songs = new ArrayList<Song>();
    }

    public void addSongs(Song songs) {
        Songs.add(songs);
    }

    @Override
    public int getCount() {
        return Songs.size();
    }

    @Override
    public Object getItem(int position) {
        return Songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SongView view = null;
        if(convertView == null) {
            view = new SongView(context);
        } else {
            view = (SongView) convertView;
        }

        Song s_ong = Songs.get(position);
        view.setView_title(s_ong.title);
        view.setView_album(s_ong.album);
        view.setView_artist(s_ong.singer);

        return view;
    }
}