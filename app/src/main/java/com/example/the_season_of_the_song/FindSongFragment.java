package com.example.the_season_of_the_song;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FindSongFragment extends Fragment {
    SelectMenu activity;
    Button findSong;
    int year;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.find_song, container, false);
        //RequestActivity에서 전달한 번들 가져옴
        Bundle bundle = getArguments(); //번들 안의 텍스트 불러오기
        year = bundle.getInt("birthYear");

        activity = (SelectMenu) getActivity();
        findSong = rootView.findViewById(R.id.find_song_button);
        findSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), InputThatSeason.class);
                intent.putExtra("birthYear", year);
                getActivity().startActivityForResult(intent, 101);
            }
        });
        return rootView;
    }
}
