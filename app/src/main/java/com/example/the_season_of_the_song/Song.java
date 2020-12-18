package com.example.the_season_of_the_song;

import java.io.Serializable;

public class Song implements Serializable {

    public int rank;
    public String title; // 노래 제목
    public String singer; // 가수명
    public String album; // 앨범명
    public int year; // 발표한 연도
    public int month; // 발표한 월

    // TODO : get,set 함수 생략

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}