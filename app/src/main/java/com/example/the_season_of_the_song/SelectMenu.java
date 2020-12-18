package com.example.the_season_of_the_song;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class SelectMenu extends AppCompatActivity {
    ViewPager pager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_menu);
        pager = (ViewPager) findViewById(R.id.activity_pager);
        pager.setOffscreenPageLimit(2);

        Intent intent = getIntent();
        int year = intent.getIntExtra("birthYear",0);
        //번들객체 생성, year값 저장
        Bundle bundle = new Bundle();
        bundle.putInt("birthYear", year); //fragment1로 번들 전달

        FunctionPagerAdapter adapter = new FunctionPagerAdapter(getSupportFragmentManager());

        FindYearFragment findYearFragment = new FindYearFragment();
        adapter.addItem(findYearFragment);
        findYearFragment.setArguments(bundle);

        FindSongFragment findSongFragment = new FindSongFragment();
        adapter.addItem(findSongFragment);
        findSongFragment.setArguments(bundle);

        pager.setAdapter(adapter);
    }

    class FunctionPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items;

        public FunctionPagerAdapter(FragmentManager fm) {
            super(fm);
            this.items = new ArrayList<Fragment>();
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "페이지 " + position;
        }
    }


}

