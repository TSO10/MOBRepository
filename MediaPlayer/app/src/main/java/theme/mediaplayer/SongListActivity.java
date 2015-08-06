package theme.mediaplayer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SongListActivity extends Activity {

    private String[] STAR = {"*"};
    Cursor cursor;
    ArrayList<Song> songArrayList = new ArrayList<>();
    static String chosedSongPath;
    TheService mService;
    boolean sBound = false;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        final Intent i = new Intent(this, TheService.class);
        startService(i);

        getSdCardSongs();
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        final Intent intent = new Intent(this, MainActivity.class);




        final ArrayList<String> songNameList = new ArrayList<>();
        //final ArrayList<String> songPathList = new ArrayList<>();

        for (final Song song : songArrayList) {

            songNameList.add(song.getName());

        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, songNameList);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                for (final Song song : songArrayList) {


                    if (itemValue.equals(song.getName())) {
                        chosedSongPath = song.getPath();
                    }

                }
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

                startActivity(intent);
                mService.PlaySong();

            }

        });


    }

    public static String getSong() {
        return chosedSongPath;
    }

    private void getSdCardSongs() {

        Uri allsongsuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        if (isSdPresent()) {
            cursor = managedQuery(allsongsuri, STAR, selection, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Song song = new Song();
                        String songname = cursor
                                .getString(cursor
                                        .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        song.setName(songname);
                        int song_id = cursor.getInt(cursor
                                .getColumnIndex(MediaStore.Audio.Media._ID));
                        song.setId(song_id);

                        String fullpath = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DATA));
                        song.setPath(fullpath);

                        String albumname = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ALBUM));

                        song.setAlbumName(albumname);

                        Log.d("songPath", song.getName());
                        songArrayList.add(song);

                    } while (cursor.moveToNext());
                }
            }
        }
    }

    public static boolean isSdPresent() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    public void onDestroy() {
        super.onDestroy();
        if (cursor != null) {
            cursor.close();
        }
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
}
