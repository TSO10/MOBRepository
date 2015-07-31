package theme.mediaplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
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

import java.io.IOException;


public class MainActivity extends Activity {

    ImageButton playBtn;
    ImageButton pauseBtn;
    ImageButton stopBtn;
    private  boolean isPaused = false;
    SeekBar sb;
    MediaPlayer mediaPlayer;
    String songPath;
    int length;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = (ImageButton) findViewById(R.id.playButton);
        pauseBtn =(ImageButton) findViewById(R.id.pauseButton);
        stopBtn =(ImageButton) findViewById(R.id.stopButton);
        sb = (SeekBar) findViewById(R.id.seekBar);


        songPath = "http://mobi.randomsort.net/wp-content/uploads/2015/07/filetoplay.mp3";
        mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(songPath));
        length = mediaPlayer.getDuration();

        PlaySong();
        PauseSong();
        StopSong();
        forwardSong();

        sb.setMax(length);
        sb.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPosition = mediaPlayer.getCurrentPosition();
                sb.postDelayed(this, 100);
                sb.setProgress(currentPosition);
            }
        }, 100);

            seekbarStatus();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.seekTo(0);
            }
        });
    }

    public void seekbarStatus ()
    {
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                {
                    sb.setProgress(progress);
                    mediaPlayer.seekTo(progress);
                    currentPosition = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void PlaySong ()
    {
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!mediaPlayer.isPlaying())
                {
                    mediaPlayer.seekTo(currentPosition);
                    mediaPlayer.start();
                }
                if(isPaused)
                {
                    mediaPlayer.start();
                    isPaused = false;
                }
            else
                {
                    mediaPlayer.reset();
                    try {

                        mediaPlayer.setDataSource(songPath);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.seekTo(currentPosition);
                    mediaPlayer.start();
                }

            }
        });
    }


    public void PauseSong ()
    {
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                isPaused = true;
            }
        });
    }

    public void StopSong ()
    {
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(0);
                mediaPlayer.stop();
                isPaused = false;
            }
        });
    }


    public void forwardSong()
    {
        sb.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int sbProgress = sb.getProgress();

                    sb.setProgress(sbProgress);
                    mediaPlayer.seekTo(sbProgress);
                 return false;
            }
        });
    }
}
