package com.ss.pmusic;

import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ActionBar actionBar;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.main_view_pager);
        tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        viewPager.setOffscreenPageLimit(3);

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu,menu);
        return true;
    }

    class PageAdapter extends FragmentPagerAdapter
    {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 : return new AlbumFrag();
                case 1 : return new SongsFrag();
                case 2 : return new GenreFrag();
                case 3 : return new PlaylistFrag();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return "Album";
                case 1: return "Songs";
                case 2: return "Genre";
                case 3: return "Playlist";
            }
            return null;
        }
    }
}
