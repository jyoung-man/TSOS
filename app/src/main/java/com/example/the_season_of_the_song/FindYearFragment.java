package com.example.the_season_of_the_song;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FindYearFragment extends Fragment {
    SelectMenu activity;
    Button findSeason;
    int year;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.find_year, container, false);

        //RequestActivity에서 전달한 번들 가져옴
        Bundle bundle = getArguments(); //번들 안의 텍스트 불러오기
        year = bundle.getInt("birthYear");

        activity = (SelectMenu) getActivity();
        findSeason = rootView.findViewById(R.id.find_year_button);
        findSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), InputTitle.class);
                intent.putExtra("birthYear", year);
                getActivity().startActivityForResult(intent, 101);
            }
        });

        return rootView;
    }
}
