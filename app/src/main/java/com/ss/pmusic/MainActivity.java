package com.ss.pmusic;

import android.graphics.drawable.ColorDrawable;
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

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    private ViewPager viewPager;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.main_view_pager);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText("Album").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Songs").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Genre").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Playlist").setTabListener(this));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu,menu);
        return true;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

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
    }
}
