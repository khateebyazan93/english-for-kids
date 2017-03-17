package com.example.yazan.englishforkids.fragments;

import android.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yazan.englishforkids.R;
import com.example.yazan.englishforkids.adapters.WordAdapter;
import com.example.yazan.englishforkids.data.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yazan on 1/26/17.
 */

public class FamilyFragment extends Fragment {

    /**
     * Handles playback of all sound files
     */
    private MediaPlayer mMediaPlayer;

    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManager;

    private ImageView mPlayIcon;

    /**
     * this listener gets triggered whenever the audio focus changes
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            /* check if the app lost audio focus for short moment of time , or the app  allowed
            to continue playing sound but at a lower volume. */
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT
                    || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                // Pause playback and reset player to the start of the file.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);

                //check if audio focus gained/regained then app can can start/resume playback.
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                //start playback sound file
                mMediaPlayer.start();

                //check if the app loss audio focus
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                //stop and clean up MediaPlayer resources
                releaseMediaPlayer();
            }


        }
    };

    /**
     * this listener gets triggered when {@link MediaPlayer} object has completed
     * playing the audio file
     */
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            //release the media player resources.
            releaseMediaPlayer();

            mPlayIcon.setImageResource(R.drawable.ic_play_circle);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate word list
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //create and setup the {@link android.media.AudioManager} to request  audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        //create and setup list of words
        final List<Word> wordList = new ArrayList<Word>();
        wordList.add(new Word(R.string.english_grandfather, R.string.arabic_grandfather, R.raw.grandfather, R.drawable.family_grandfather, R.drawable.ic_play_circle));
        wordList.add(new Word(R.string.english_grandmother, R.string.arabic_grandmother, R.raw.grandmother, R.drawable.family_grandmother, R.drawable.ic_play_circle));
        wordList.add(new Word(R.string.english_father, R.string.arabic_father, R.raw.father, R.drawable.family_father, R.drawable.ic_play_circle));
        wordList.add(new Word(R.string.english_mother, R.string.arabic_mother, R.raw.mother, R.drawable.family_mother, R.drawable.ic_play_circle));
        wordList.add(new Word(R.string.english_older_brother, R.string.arabic_older_brother, R.raw.older_brother, R.drawable.family_older_brother, R.drawable.ic_play_circle));
        wordList.add(new Word(R.string.english_older_sister, R.string.arabic_older_sister, R.raw.older_sister, R.drawable.family_older_sister, R.drawable.ic_play_circle));
        wordList.add(new Word(R.string.english_younger_brother, R.string.arabic_younger_brother, R.raw.younger_brother, R.drawable.family_younger_brother, R.drawable.ic_play_circle));
        wordList.add(new Word(R.string.english_younger_sister, R.string.arabic_younger_sister, R.raw.younger_sister, R.drawable.family_younger_sister, R.drawable.ic_play_circle));
        wordList.add(new Word(R.string.english_son, R.string.arabic_son, R.raw.son, R.drawable.family_son, R.drawable.ic_play_circle));

        //create an {@link WordAdapter} object whose data source is a list of {@link Word}s object
        WordAdapter wordAdapter = new WordAdapter(getActivity(), wordList, R.color.category_family);

        // find the {@link RecyclerView} object in the view hierarchy of the {@link Activity}.
        RecyclerView wordRecyclerView = (RecyclerView) rootView.findViewById(R.id.list);

        //setup Word RecyclerView
        wordRecyclerView.setAdapter(wordAdapter);
        wordRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //displays dividers between each item within the wordRecyclerView
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        wordRecyclerView.addItemDecoration(itemDecoration);

        //setup listener for each row in wordRecyclerView
        wordAdapter.setOnItemClickListener(new WordAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Word wordItems, ImageView playIcon) {
                   /* release media player if it currently exist because
                 we are to play different sound file */
                releaseMediaPlayer();

                //get play icon in the correct position that user clicked on
                mPlayIcon = playIcon;

                // Get audio focus request
                int audioFocusResult = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // Check if the app granted audio focus
                if (audioFocusResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    /* setup and create {@link MediaPlayer} object for the audio resource
                    associated with current {@link Word} object */
                    mMediaPlayer = MediaPlayer.create(getActivity(), wordItems.getAudioResourceId());

                    //start playback audio file
                    mMediaPlayer.start();
                    playIcon.setImageResource(R.drawable.ic_pause_circle);

                    /* Setup a listener on the media player, to stop and release the
                    media player resources once the sound has finished playing. */
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }


    /**
     * Clean up media player by releasing it's resources
     */
    public void releaseMediaPlayer() {

        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            mMediaPlayer.release();

            // Set the media player back to null.
            mMediaPlayer = null;

            mPlayIcon.setImageResource(R.drawable.ic_play_circle);

            //unregisters the OnAudioFocusChangeListener
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }


}
