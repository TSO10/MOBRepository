package theme.mediaplayer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {

    ImageButton playBtn;
    ImageButton pauseBtn;
    ImageButton stopBtn;

    static SeekBar sb;
    MediaPlayer mediaPlayer;
    String songPath;
    int length;
    int currentPlayerPosition;

    TheService mService;
    boolean sBound = false;
    static boolean songStatus = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getSdCardSongs();

        playBtn = (ImageButton) findViewById(R.id.playButton);
        pauseBtn = (ImageButton) findViewById(R.id.pauseButton);
        stopBtn = (ImageButton) findViewById(R.id.stopButton);
        sb = (SeekBar) findViewById(R.id.seekBar);

        final Intent intent = new Intent(this, TheService.class);
        startService(intent);

        PlayButtonClick();
        PauseButtonClick();
        StopButtonClick();
        SeekbarTouch();


        songPath = SongListActivity.getSong();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(songPath));
        length = mediaPlayer.getDuration();
        sb.setMax(length);


        sb.postDelayed(new Runnable() {

            @Override

            public void run() {

                {
                    currentPlayerPosition = TheService.getData();
                    sb.postDelayed(this, 100);
                    sb.setProgress(currentPlayerPosition);

                }


                Log.d("tagename", Integer.toString(currentPlayerPosition));
            }
        }, 100);

    }

    public static SeekBar getSeekbar() {
        return sb;
    }

    // method for playing song taking care of all scenarios
    public void PlayButtonClick() {


        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sBound && TheService.songStatus == false) {

                    Log.d("IntentService", Integer.toString(TheService.getData()));
                    Toast t = Toast.makeText(getApplicationContext(), "IntentService has been started.", Toast.LENGTH_SHORT);
                    t.show();
                    mService.PlaySong();
                } else {
                    Toast t = Toast.makeText(getApplicationContext(), "No connection to the service", Toast.LENGTH_SHORT);
                    t.show();
                }

            }
        });
    }

    // method for pause the song
    public void PauseButtonClick() {
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sBound) {

                    Log.d("IntentService", "IntentService has been started.");
                    Toast t = Toast.makeText(getApplicationContext(), "IntentService has been started.", Toast.LENGTH_SHORT);
                    t.show();
                    mService.PauseSong();
                } else {
                    Toast t = Toast.makeText(getApplicationContext(), "No connection to the service", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }

    // method for stop the song
    public void StopButtonClick() {
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sBound) {

                    Log.d("IntentService", "IntentService has been started.");
                    Toast t = Toast.makeText(getApplicationContext(), "IntentService has been started.", Toast.LENGTH_SHORT);
                    t.show();
                    mService.StopSong();
                } else {
                    Toast t = Toast.makeText(getApplicationContext(), "No connection to the service", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }

    // method for forward or reward to the song on user touch on seekbar
    public void SeekbarTouch() {
        sb.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mService.RewardForward();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, TheService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TheService.LocalBinder binder = (TheService.LocalBinder) service;
            mService = binder.getService();
            sBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            sBound = false;
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        if (sBound) {
            unbindService(serviceConnection);
        }
    }

    public void backToPlaylist(View v) {
        Intent i = new Intent(this, SongListActivity.class);
        startActivity(i);
        songStatus = true;


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        songStatus = true;

    }
}
