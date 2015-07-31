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
    private boolean isStopped = false;
    SeekBar sb;
    MediaPlayer mediaPlayer;
    String songPath;
    int length;
    int currentPlayerPosition;



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

                    currentPlayerPosition = mediaPlayer.getCurrentPosition();
                    sb.postDelayed(this, 100);
                    sb.setProgress(currentPlayerPosition);
                    Log.d("tagename", "string i want");
       /*             Toast t = Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_SHORT);
                    t.show();*/
            }
        }, 100);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.seekTo(0);
            }
        });

    }


    // method for playing song taking care of all scenarios
    public void PlaySong ()
    {
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(!mediaPlayer.isPlaying())
                    {
                        mediaPlayer.seekTo(currentPlayerPosition);
                        mediaPlayer.start();
                    }


                if(isPaused)
                    {
                        mediaPlayer.start();
                        isPaused = false;

                    }

                if(mediaPlayer.isPlaying())
                     {
                         mediaPlayer.start();
                    }

                if(isStopped)

                    {
                        mediaPlayer.reset();
                        try {

                            mediaPlayer.setDataSource(songPath);
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.seekTo(currentPlayerPosition);
                        mediaPlayer.start();
                        isStopped = false;
                        isPaused = false;
                    }

            }
        });
    }

    // method for pause the song
    public void PauseSong ()
    {
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                isPaused = true;
                isStopped = false;
            }
        });
    }

    // method for stop the song
    public void StopSong ()
    {
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(0);
                mediaPlayer.stop();

                isPaused = false;
                isStopped = true;
            }
        });
    }

    // method for forward or reward to the song on user touch on seekbar
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
