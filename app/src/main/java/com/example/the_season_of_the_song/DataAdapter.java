package com.example.the_season_of_the_song;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class DataAdapter
{
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public ArrayList<Song> getMusicByTitle(String t) {

        try
        {
            // 제목에 t의 문자열 포함하는 노래 모두 불러오기
            String sql ="SELECT DISTINCT title, artist, album FROM musicdb WHERE title LIKE '%" + t + "%'";

            // 모델 넣을 리스트 생성
            ArrayList<Song> songList = new ArrayList<Song>();

            // TODO : 모델 선언
            Song song = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    song = new Song();

                    // TODO : Record 기술
                    // title, artist, album, year, month
                    song.setTitle(mCur.getString(0));
                    song.setSinger(mCur.getString(1));
                    song.setAlbum(mCur.getString(2));
                    song.setYear(1999);
                    song.setMonth(01);

                    // 리스트에 넣기
                    songList.add(song);
                }

            }
            return songList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<Song> getMusicByDate(int y, int m)
    {
        try
        {
            //겨울일 경우 y,m을 이듬해 1월로 넣었다고 가정
            int n = m+2;
            if(m==1) {
                n = m+1;
            }
            String sqlP = " WHERE rank <30 AND year=" + Integer.toString(y-1) + " AND month = " + Integer.toString(12);
            String sqlPlus = " WHERE rank <30 AND year=" + Integer.toString(y) + " AND month BETWEEN " + Integer.toString(m) + " AND " + Integer.toString(n);

            // Table musicdb에서 연, 월 정보로 노래 불러오기
            String sql ="SELECT * FROM musicdb";

            // 모델 넣을 리스트 생성
            ArrayList<Song> songList = new ArrayList<Song>();

            // TODO : 모델 선언
            Song song = null;

            Cursor mCur = mDb.rawQuery(sql+sqlPlus, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    song = new Song();

                    // TODO : Record 기술
                    // title, artist, album, year, month
                    song.setRank(mCur.getInt(0));
                    song.setTitle(mCur.getString(1));
                    song.setSinger(mCur.getString(2));
                    song.setAlbum(mCur.getString(3));
                    song.setYear(mCur.getInt(4));
                    song.setMonth(mCur.getInt(5));

                    // 리스트에 넣기
                    songList.add(song);
                }

            }
            if(m==1) {
                mCur = mDb.rawQuery(sql+sqlP, null);
                if (mCur!=null)
                {
                    // 칼럼의 마지막까지
                    while( mCur.moveToNext() ) {

                        // TODO : 커스텀 모델 생성
                        song = new Song();

                        // TODO : Record 기술
                        // title, artist, album, year, month
                        song.setRank(mCur.getInt(0));
                        song.setTitle(mCur.getString(1));
                        song.setSinger(mCur.getString(2));
                        song.setAlbum(mCur.getString(3));
                        song.setYear(mCur.getInt(4));
                        song.setMonth(mCur.getInt(5));

                        // 리스트에 넣기
                        songList.add(song);
                    }

                }
            }
            return songList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public ArrayList<Song> getDatebyInfo (String title, String artist) {
        try
        {
            // 제목에 t의 문자열 포함하는 노래 모두 불러오기
            String sql ="SELECT DISTINCT * FROM musicdb WHERE title = '" + title + "' AND artist = '" + artist + "'";

            // 모델 넣을 리스트 생성
            ArrayList<Song> songList = new ArrayList<Song>();

            // TODO : 모델 선언
            Song song = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    song = new Song();

                    // TODO : Record 기술
                    // title, artist, album, year, month
                    song.setRank(mCur.getInt(0));
                    song.setTitle(mCur.getString(1));
                    song.setSinger(mCur.getString(2));
                    song.setAlbum(mCur.getString(3));
                    song.setYear(mCur.getInt(4));
                    song.setMonth(mCur.getInt(5));

                    // 리스트에 넣기
                    songList.add(song);
                }

            }
            return songList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<Weather> getWeatherData(int y, int m)
    {
        try
        {
            //겨울일 경우 y,m을 이듬해 1월로 넣음
            int n = m+2;
            if(m==1) {
                n = m+1;
            }
            String sqlP = " WHERE year=" + Integer.toString(y-1) + " AND month = " + Integer.toString(12);
            String sqlPlus = " WHERE year=" + Integer.toString(y) + " AND month BETWEEN " + Integer.toString(m) + " AND " + Integer.toString(n);

            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM weatherdb";

            // 모델 넣을 리스트 생성
            ArrayList<Weather> weatherList = new ArrayList<Weather>();

            // TODO : 모델 선언
            Weather weather = null;

            Cursor mCur = mDb.rawQuery(sql+sqlPlus, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    weather = new Weather();

                    // TODO : Record 기술
                    weather.setYear(mCur.getInt(0));
                    weather.setMonth(mCur.getInt(1));
                    weather.setTemp(mCur.getString(2));
                    weather.setPrecipitation(mCur.getString(3));

                    // 리스트에 넣기
                    weatherList.add(weather);
                }

            }
            if(m==1) {
                mCur = mDb.rawQuery(sql+sqlP, null);
                if (mCur!=null)
                {
                    // 칼럼의 마지막까지
                    while( mCur.moveToNext() ) {

                        // TODO : 커스텀 모델 생성
                        weather = new Weather();

                        // TODO : Record 기술
                        weather.setYear(mCur.getInt(0));
                        weather.setMonth(mCur.getInt(1));
                        weather.setTemp(mCur.getString(2));
                        weather.setPrecipitation(mCur.getString(3));

                        // 리스트에 넣기
                        weatherList.add(weather);
                    }

                }

            }
            return weatherList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

}