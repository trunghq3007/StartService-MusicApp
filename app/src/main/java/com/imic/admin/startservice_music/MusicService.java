package com.imic.admin.startservice_music;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.util.List;

public class MusicService extends Service {
    private static final String TAG = "MusicService";
    MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            if (mediaPlayer != null) {
                //stops the playback
                mediaPlayer.stop();
                //releases any resource attached with MediaPlayer object
                mediaPlayer.release();
            }

            mediaPlayer = new MediaPlayer();
            //sets the data source of audio file
            if (intent != null) {
                mediaPlayer.setDataSource(intent.getStringExtra("path"));
                // open view
                if (!isActivityRunning(ListOfSongsActivity.class)) {
                    Log.d(TAG, "onStartCommand: ");
                    Intent intent1 = new Intent(getApplicationContext(), ListOfSongsActivity.class);
                    intent1.putExtra("name", intent.getStringExtra("name"));
                    startActivity(intent1);
                }


            }

            //prepares the player for playback synchronously
            mediaPlayer.prepare();
            //sets the player for looping
            mediaPlayer.setLooping(true);
            //starts or resumes the playback
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("show", "Error: " + e.toString());
        }

        return START_REDELIVER_INTENT;
    }

    public void onDestroy() {
        //stops the playback
        mediaPlayer.stop();
        //releases any resource attached with MediaPlayer object
        mediaPlayer.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected Boolean isActivityRunning(Class activityClass) {
        ActivityManager activityManager = (ActivityManager) getBaseContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (activityClass.getCanonicalName().equalsIgnoreCase(task.baseActivity.getClassName()))
                return true;
        }

        return false;
    }
}
