package com.example.makeke;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocuusChangeListener= new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i== AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (i== AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            } else if (i== AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.word_list, container,false);
        mAudioManager= (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Bonjour", "Hello", R.raw.song));
        words.add(new Word("Merci", "Thank you", R.raw.loyal));
        words.add(new Word("S'il vous plait", "please", R.raw.lyric));
        words.add(new Word("Comment allez-vous?", "How are you?", R.raw.music));
        words.add(new Word("Je m'appelle...", "My name is ...", R.raw.song));
        words.add(new Word("Je ne sais pas", "I don't know", R.raw.loyal));
        words.add(new Word("pouvez-vus m'aider?", "Can you help me?", R.raw.lyric));
        words.add(new Word("Ou pouvons-nous manger?", "Where can we eat?", R.raw.music));
        words.add(new Word("va-t'en!", "Go away!", R.raw.lyric));
        words.add(new Word("Avez-vous faim?", "are you hungry?", R.raw.music));

        //        Word w= new Word("Deux", "Two");
        //        words.add(w);

        WordAdapter adapter= new WordAdapter(getActivity(), words, R.color.phraseColor);

        ListView listView= rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word= words.get(i);

                releaseMediaPlayer();
                int result= mAudioManager.requestAudioFocus(mOnAudioFocuusChangeListener,AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                if (result== AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer= MediaPlayer.create(getActivity(), word.getAudioResource());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completionListener);
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

    private void releaseMediaPlayer(){
        if (mediaPlayer!= null){
            mediaPlayer.release();
            mediaPlayer= null;
        }
        mAudioManager.abandonAudioFocus(mOnAudioFocuusChangeListener);
    }

}
