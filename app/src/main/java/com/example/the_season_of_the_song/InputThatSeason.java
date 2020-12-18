package com.example.the_season_of_the_song;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InputThatSeason extends AppCompatActivity {
    ArrayList<String> years = new ArrayList<String>();
    public ArrayList<Song> songList ;
    Button submit;
    Spinner spinnerYear;
    Spinner spinnerSeason;
    int birthYear;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_that_day);
        birthYear = getIntent().getIntExtra("birthYear", 2010);
        spinnerYear = findViewById(R.id.spinner_date);
        for (int i = 1990; i <= 2020; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, years
        );

        spinnerSeason = findViewById(R.id.spinner_season);
        String[] seasons = {"봄", "여름", "가을", "겨울"};

        ArrayAdapter<String> adapterSeason = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, seasons
        );

        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapterYear);

        adapterSeason.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeason.setAdapter(adapterSeason);

        submit = (Button) findViewById(R.id.submit_date);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = Integer.parseInt(spinnerYear.getSelectedItem().toString());
                int m = 0;
                String season = spinnerSeason.getSelectedItem().toString();
                int userage = year - birthYear + 1;
                if(userage<0)
                    userage = 0;
                if(season.equals("봄")){
                    m = 3;
                }
                else if(season.equals("여름")) {
                    m = 6;
                }
                else if(season.equals("가을")) {
                    m = 9;
                }
                else {
                    year = year+1;
                    m = 1;
                }
                Intent intent = new Intent(getApplicationContext(), GiveTheTitle.class);
                intent.putExtra("age", userage);
                intent.putExtra("year", year);
                intent.putExtra("month", m);
                startActivityForResult(intent, 101);
            }
        });

    }
}
