package com.ss.pmusic;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by bradley on 02-01-2017.
 */

public class SongItem {
    private String songTitle, songArtists, songDuration, songAlbumId;
    private Bitmap songAlbumArt;
    private Context context;

    public SongItem(Context context) {
        this.context = context;
    }

    public void setSongAlbumId(String songAlbumId) {
        this.songAlbumId = songAlbumId;
        Uri artwork = Uri.parse("content://media/external/audio/albumart");
        Uri albumart = ContentUris.withAppendedId(artwork, Long.parseLong(songAlbumId));
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), albumart);
            songAlbumArt = bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSongArtists(String songArtists) {
        this.songArtists = songArtists;
    }

    public void setSongDuration(String songDuration) {
        long time = Long.parseLong(songDuration);
        time = time / 1000;

        String d = (time / 60) + ":" + (time % 60) + " min";
        this.songDuration = d;

    }


    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public Bitmap getSongAlbumArt() {
        return songAlbumArt;
    }

    public String getSongAlbumId() {
        return songAlbumId;
    }

    public String getSongArtists() {
        return songArtists;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public String getSongTitle() {
        return songTitle;
    }
}
