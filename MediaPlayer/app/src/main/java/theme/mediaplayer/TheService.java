package theme.mediaplayer;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TheService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private boolean isPaused = false;
    private boolean isStopped = false;

    MediaPlayer mPlayer;
    static String songPath;
    static boolean songStatus = false;
    static int currentPlayerPosition;
    SeekBar seekbar;



    public TheService() {

    }

    public class LocalBinder extends Binder {

        TheService getService() {

            return TheService.this;
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        seekbar = MainActivity.getSeekbar();

        return Service.START_NOT_STICKY;

    }

    public static void setData(int data) {
        currentPlayerPosition = data;
    }

    public static int getData() {
        return currentPlayerPosition;
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

   /* public void myNoti() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_noti)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());

    }*/

    public void PlaySong() {

        if(MainActivity.songStatus)
        {
            StopSong();
            currentPlayerPosition= 0;
        }

        songPath = SongListActivity.getSong();
        mPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(songPath));
        final Handler handler = new Handler();
        final Runnable r = new Runnable() {

            public void run() {
                currentPlayerPosition = mPlayer.getCurrentPosition();
                setData(currentPlayerPosition);
                Log.d("time", Integer.toString(currentPlayerPosition));

                handler.postDelayed(this, 100);
            }
        };


        handler.postDelayed(r, 1000);

        if (!mPlayer.isPlaying()) {

            mPlayer.seekTo(currentPlayerPosition);
            mPlayer.start();
            songStatus = true;

        }

        if (isPaused) {

            mPlayer.start();

            isPaused = false;
            songStatus = true;

        }

        if (mPlayer.isPlaying()) {
           // mPlayer.seekTo(currentPlayerPosition);
            songStatus = true;
            Log.d("xxxx", "Again called");
        }

        if (isStopped)

        {
            mPlayer.reset();
            try {

                mPlayer.setDataSource(songPath);
                mPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mPlayer.seekTo(currentPlayerPosition);
            mPlayer.start();
            isStopped = false;
            isPaused = false;
            songStatus = true;
        }

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayer.seekTo(0);
                songStatus = false;
            }
        });

    }

    public void PauseSong() {
        mPlayer.pause();
        isPaused = true;
        isStopped = false;
        songStatus = false;
    }

    public void StopSong() {
        mPlayer.seekTo(0);
        mPlayer.stop();


        isPaused = false;
        isStopped = true;
        songStatus = false;
    }

    public void RewardForward() {
        seekbar.setProgress(seekbar.getProgress());
        mPlayer.seekTo(seekbar.getProgress());
    }
}
