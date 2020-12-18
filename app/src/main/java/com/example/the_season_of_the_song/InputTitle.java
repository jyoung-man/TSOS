package com.example.the_season_of_the_song;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InputTitle extends AppCompatActivity {
    public ArrayList<Song> listOfSong ;
    private EditText inputText;
    Button search;
    Intent intent;
    InputMethodManager imm;
    int birthYear;
    int thatYear;
    int thatMonth;
    String songTitle;
    SongAdapter adapter;
    private ListView listView;
    private void initLoadMDB(String t) {

        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        listOfSong = mDbHelper.getMusicByTitle(t);
        //weatherList = mDbHelper.getWeatherData(y, m);

        // db 닫기
        mDbHelper.close();
    }

    private void searchTitle() {
        songTitle = inputText.getText().toString();
        if(songTitle.length() < 101) {
            //이 액티비티에서 보여줘야 해
            initLoadMDB(songTitle);
        }
        else
            Toast.makeText(getApplicationContext(),"100자 이내로 작성해주세요.",Toast.LENGTH_SHORT).show();
    }

    public void makeSongsList(ArrayList<Song> userItemList) {
        adapter = new SongAdapter(getApplicationContext());

        for(int i = 0; i<userItemList.size(); i++) {
            adapter.addSongs(userItemList.get(i));
        }
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_song_title);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        birthYear = getIntent().getIntExtra("birthYear", 0);
        listView = (ListView) findViewById(R.id.songList);
        inputText = (EditText) findViewById(R.id.inputTitle);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH :
                        //액션 버튼 눌렸을 때
                        searchTitle();
                        makeSongsList(listOfSong);
                        imm.hideSoftInputFromWindow(inputText.getWindowToken(), 0);
                        break;
                }

                return true;
            }
        });
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //화면 상단 돋보기 버튼 눌렸을 때
                searchTitle();
                makeSongsList(listOfSong);
            }
        });


        intent = new Intent(this, TellTheSeason.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                thatYear = listOfSong.get(position).getYear();
                thatMonth = listOfSong.get(position).getMonth();
                intent.putExtra("thatSong", listOfSong.get(position).getTitle());
                intent.putExtra("thatSinger", listOfSong.get(position).getSinger());
                intent.putExtra("birthYear", birthYear);
                startActivityForResult(intent, 101);
            }
        });
    }
}
