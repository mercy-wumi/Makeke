package com.example.makeke;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.ListIterator;

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResource;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResource){

        super(context, 0, words);
        mColorResource= colorResource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView= convertView;
        if (listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
            Word currentWord= getItem(position);

            TextView makakeTextView= listItemView.findViewById(R.id.makeke_text_view);
            makakeTextView.setText(currentWord.getMakekeTranslation());

            TextView defaultTextView= listItemView.findViewById(R.id.default_text_view);
            defaultTextView.setText(currentWord.getDefaultTranslation());

            ImageView imageResource= listItemView.findViewById(R.id.makeke_image);
            if (currentWord.hasImage()){
            imageResource.setImageResource(currentWord.getImageResource());
            imageResource.setVisibility(View.VISIBLE);
            }
            else {
                imageResource.setVisibility(View.GONE);
            }

            View textContainer= listItemView.findViewById(R.id.text_container);
            int color = ContextCompat.getColor(getContext(), mColorResource);
            textContainer.setBackgroundColor(color);
            return listItemView;
    }
}
