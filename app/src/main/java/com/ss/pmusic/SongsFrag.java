package com.ss.pmusic;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SongsFrag extends Fragment
{
    int count;
    ListView songsListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_songs, container, false);

        songsListView = (ListView) vw.findViewById(R.id.songs_list_view);
        //Toast.makeText(getContext(), "Number of songs = " + Integer.toString(count), Toast.LENGTH_SHORT).show();
        createSongsList(getSongsList());
        return vw;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    String[] getSongsList () {
        Cursor cursor = getActivity().managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] {MediaStore.Audio.Media.TITLE},
                null,
                null,
                "LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC"
        );

        String list[];
        list = new String [cursor.getCount()];
        int pos = 0;
        if (cursor.moveToFirst()) {
            do {
                list [pos++] = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return list;
    }

    void createSongsList (String[] list) {
        ArrayAdapter<String> songsListViewArrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.temp_res, list);
        songsListView.setAdapter(songsListViewArrayAdapter);
    }

    class SongsListViewAdapter extends ArrayAdapter<String> {
        String songsList[];
        public SongsListViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String[] objects) {
            super(context, resource, objects);
            songsList = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.temp_res, parent, false);
            TextView name = (TextView) v.findViewById(R.id.song_name);
            name.setText(songsList[position]);
            return v;
        }

        @Override
        public int getCount() {
            return songsList.length;
        }
    }
}
