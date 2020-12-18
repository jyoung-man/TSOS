package com.example.the_season_of_the_song;

import java.io.Serializable;

public class Weather implements Serializable {

    private int year; // 연도
    private int month; // 월
    private String temp; // 온도
    private String precipitation; // 강수량

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

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }
}

