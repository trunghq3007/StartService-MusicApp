package com.imic.admin.startservice_music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class MusicService extends Service {

    MediaPlayer mediaPlayer;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {

            mediaPlayer = new MediaPlayer();
            //sets the data source of audio file
            mediaPlayer.setDataSource(ListOfSongsActivity.absolutePath);
            //prepares the player for playback synchronously
            mediaPlayer.prepare();
            //sets the player for looping
            mediaPlayer.setLooping(true);
            //starts or resumes the playback
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("show","Error: "+e.toString());
        }

        return START_STICKY;
    }

    public void onDestroy(){
        //stops the playback
        mediaPlayer.stop();
        //releases any resource attached with MediaPlayer object
        mediaPlayer.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
