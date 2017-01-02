package com.ss.pmusic;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class SongsFrag extends Fragment {
    int count;
    ListView songsListView;
    SongItem[] songs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_songs, container, false);

        songsListView = (ListView) vw.findViewById(R.id.songs_list_view);
        //Toast.makeText(getContext(), "Number of songs = " + Integer.toString(count), Toast.LENGTH_SHORT).show();
        makeSongsList();
        createSongsListView();
        return vw;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void makeSongsList() {
        Cursor cursor = getActivity().managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM_ID,
                        MediaStore.Audio.Media.DURATION
                },
                null,
                null,
                "LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC"
        );

        songs = new SongItem[cursor.getCount()];
        int pos = 0;
        if (cursor.moveToFirst()) {
            do {
                SongItem song = new SongItem(getContext());
                song.setSongTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
                song.setSongAlbumId(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)));
                song.setSongArtists(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                song.setSongDuration(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                songs[pos++] = song;
            } while (cursor.moveToNext());
        }
    }

    void createSongsListView() {
        ArrayAdapter<Long> songsListViewArrayAdapter = new SongsListViewAdapter(getContext(), R.layout.songs_listview_row);
        songsListView.setAdapter(songsListViewArrayAdapter);
    }

    class SongsListViewAdapter extends ArrayAdapter<Long> {
        public SongsListViewAdapter(@NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.songs_listview_row, parent, false);
            }
            ImageView albumArt = (ImageView) convertView.findViewById(R.id.song_album_art);
            TextView title = (TextView) convertView.findViewById(R.id.song_title);
            TextView artists = (TextView) convertView.findViewById(R.id.song_artists);
            TextView duration = (TextView) convertView.findViewById(R.id.song_duration);

            SongItem song = songs[position];
            albumArt.setImageBitmap(song.getSongAlbumArt());
            title.setText(song.getSongTitle());
            artists.setText(song.getSongArtists());
            duration.setText(song.getSongDuration());

            return convertView;
        }

            @Override
            public int getCount () {
                return songs.length;
            }


        }
    }
