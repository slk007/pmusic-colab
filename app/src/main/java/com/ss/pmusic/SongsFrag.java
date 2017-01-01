package com.ss.pmusic;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SongsFrag extends Fragment
{
    int count;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_songs, container, false);

        Toast.makeText(getContext(), "Number of songs = " + Integer.toString(count), Toast.LENGTH_SHORT).show();

        return vw;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cursor cursor = getActivity().managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] {MediaStore.Audio.Media.DISPLAY_NAME},
                null,
                null,
                "LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC"
        );

        count = cursor.getCount();
    }
}
