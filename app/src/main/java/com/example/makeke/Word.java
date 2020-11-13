package com.example.makeke;

public class Word {

    private String mMakekeTranslation;
    private String mDefaultTranslation;
    private int mAudioResource;
    private int mImageResource= N0_IMAGE_PROVIDED;
    private static final  int N0_IMAGE_PROVIDED = -1;

    public Word(String makekeTranslation, String defaultTranslation, int audioResource){
        mDefaultTranslation= defaultTranslation;
        mMakekeTranslation= makekeTranslation;
        mAudioResource= audioResource;
    }
    public Word(String makekeTranslation, String defaultTranslation, int imageResource, int audioResource){
        mDefaultTranslation= defaultTranslation;
        mMakekeTranslation= makekeTranslation;
        mImageResource= imageResource;
        mAudioResource= audioResource;
    }

    public String getMakekeTranslation() {
        return mMakekeTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getImageResource(){
        return mImageResource;
    }

    public int getAudioResource(){
        return mAudioResource;
    }


    public boolean hasImage(){
        return mImageResource != N0_IMAGE_PROVIDED;
    }
}
