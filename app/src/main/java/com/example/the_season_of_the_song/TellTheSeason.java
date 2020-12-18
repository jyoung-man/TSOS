package com.example.the_season_of_the_song;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TellTheSeason extends AppCompatActivity {
    float[] t_standard;
    float[] p_standard;
    String t1, t2, t3, t4, t5, t6;
    String p1, p2, p3, p4, p5;
    TextView artist;
    TextView title;
    TextView year;
    TextView weather;
    TextView rain;
    ImageView image;
    Button home;
    String thatSong;
    String thatSinger;
    int thatAge;
    int thatYear;
    int thatMonth;
    public ArrayList<Song> listOfSong;
    public ArrayList<Weather> weatherList;

    private void initLoadMDB(String t, String a) {

        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        listOfSong = mDbHelper.getDatebyInfo(t, a);

        // db 닫기
        mDbHelper.close();
    }

    private void initLoadWDB(int y, int m) {

        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        weatherList = mDbHelper.getWeatherData(y, m);

        // db 닫기
        mDbHelper.close();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_season);
        Intent intent = getIntent(); //인텐트로 받은 것 : 노래 제목, 가수, 사용자 생년
        thatSong = intent.getStringExtra("thatSong");
        thatSinger = intent.getStringExtra("thatSinger");
        initLoadMDB(thatSong, thatSinger);

        final int birthYear = intent.getIntExtra("birthYear",1999);
        thatYear = listOfSong.get(0).getYear();
        thatMonth = listOfSong.get(0).getMonth();

        thatAge = thatYear - birthYear + 1;
        //날씨 정보 여기서 불러와야 함
        title = (TextView) findViewById(R.id.title_season);
        artist = (TextView) findViewById(R.id.artist_season);
        year = (TextView) findViewById(R.id.year_season);
        weather = (TextView)findViewById(R.id.temp_season);
        rain = (TextView)findViewById(R.id.rain_season);
        image = (ImageView) findViewById(R.id.image_season);
        home = (Button) findViewById(R.id.home_season);
        artist.setText(thatSinger);
        title.setText(thatSong);
        year.setText(putAge(thatAge));

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), SelectMenu.class);
                intent1.putExtra("birthYear", birthYear);
                startActivity(intent1);
                //ActivityCompat.finishAffinity(getParent());
            }
        });

        t_standard = new float[12];
        float[] t_standard = {0.23f, -2.02f, 0.73f, 6.13f, 12.53f, 18.18f, 22.65f, 25.29f, 26.09f, 21.64f, 15.08f, 7.54f};
        p_standard = new float[12];
        float[] p_standard =  {23.32f, 18.29f, 29.36f, 38.65f, 73.54f, 104.21f, 141.40f, 416.76f, 346.13f, 154.48f, 52.22f, 48.94f};

        int target_month = 3;
        if(thatMonth<3){
            thatMonth = 1;
        }
        else if(thatMonth<6) {
            thatMonth = 3;
        }
        else if(thatMonth < 9) {
            thatMonth = 6;
        }
        else if(thatMonth < 12){
            thatMonth = 9;
        }
        else {
            thatMonth = 1;
            thatYear += 1;
        }
        initLoadWDB(thatYear, thatMonth);
        howsTheWeather(thatMonth);
        //title.setText("시차");
        if(thatMonth==1)
            target_month--;
        float temp_season = (t_standard[target_month] + t_standard[target_month+1] + t_standard[target_month+2])/3;
        float temp_avg = (Float.parseFloat(weatherList.get(0).getTemp()) + Float.parseFloat(weatherList.get(1).getTemp()) + Float.parseFloat(weatherList.get(2).getTemp()))/3;

        if(temp_season - temp_avg <= -2) {
            weather.setText(t1+t6);
        }
        else if(temp_season - temp_avg <= -1) {
            weather.setText(t2+t6);
        }
        else if(temp_season - temp_avg <= 1) {
            weather.setText(t3+t6);
        }
        else if(temp_season - temp_avg<= 2) {
            weather.setText(t4+t6);
        }
        else {
            weather.setText(t5+t6);
        }

        float result = 0f;
        for(int i = 0; i<3; i++) {
            result += (p_standard[target_month++] / Float.parseFloat(weatherList.get(i).getPrecipitation()));
        }
        if(result/3 <= 0.1) {
            rain.setText(p1);
        }

        else if(result/3 <= 0.6) {
            rain.setText(p2);
        }

        else if(result/3 <= 1.4) {
            rain.setText(p3);
        }

        else if(result/3 <= 1.9) {
            rain.setText(p4);
        }
        else
            rain.setText(p5);
    }

    public void howsTheWeather(int month) {
        String rain;
        if(month==1)
            rain = "눈이";
        else
            rain = "비가";
        p1 = "가물었고";
        p2 = rain+ " 잘 안오던";
        p3 = "때때로 " + rain + " 오고";
        p4 = rain + " 자주 오고";
        p5 = rain + " 많이 내리고";
        switch (month) {
            case 1:
                image.setImageResource(R.drawable.winter);
                t1 = "얼음장 같았던";
                t2 = "추위가 매서웠던";
                t3 = "조금 추웠던";
                t4 = "평소보다 따뜻했던";
                t5 = "유난히 따뜻했던";
                t6 = " 겨울이었지요";
                break;
            case 3:
                image.setImageResource(R.drawable.spring);
                t1 = "추웠던";
                t2 = "쌀쌀했던";
                t3 = "평소와 다름없던";
                t4 = "평소보다 따뜻했던";
                t5 = "더웠던";
                t6 = " 봄이었지요";
                break;
            case 6:
                image.setImageResource(R.drawable.summer);
                t1 = "조금 서늘했던";
                t2 = "조금 덜 더웠던";
                t3 = "언제나처럼 더웠던";
                t4 = "유난히 더웠던";
                t5 = "찜통같았던";
                t6 = " 여름이었지요";
                break;
            case 9:
                image.setImageResource(R.drawable.autumn);
                t1 = "추웠던";
                t2 = "쌀쌀했던";
                t3 = "평소와 다름없던";
                t4 = "평소보다 따뜻했던";
                t5 = "더웠던";
                t6 = " 가을이었지요";
                break;
        }
    }
    public String putAge(int age) {
        String message;
        if(age>0)
            message = "고객님이 "+Integer.toString(age)+"살 때였어요";
        else
            message = "고객님이 태어나기 전이었어요";

        return message;
    }
}
