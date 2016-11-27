package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    //
    private AudioManager mAudioManager;
     private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
         @Override
         public void onAudioFocusChange(int focusChange) {
             if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                                                                                      mediaPlayer.pause();
                               mediaPlayer.seekTo(0);
             }
             else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                                        mediaPlayer.start();
                           }
             else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                 // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                         //+                // Stop playback and clean up resources
                                               releaseMediaPlayer();
             }
         }
     };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
               @Override
               public void onCompletion(MediaPlayer mediaPlayer) {
                   // Now that the sound file has finished playing, release the media player resources.
                               releaseMediaPlayer();
                   }
      };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("red","weteetti",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("green","chokoki",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("brown","takaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("gray","topoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("black","kululii",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        words.add(new Word("dusty yellow","topisse",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow","chiwitta",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));


        ////////
        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=words.get(position);
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(Colors.this, word.getSongId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }
    private void releaseMediaPlayer() {
        // setting the media player to null is an easy way to tell that the media player
        // is not configured to play an audio file at the moment.
        mediaPlayer = null;

                //+            // Regardless of whether or not we were granted audio focus, abandon it. This also
                        //+            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
                mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }


}