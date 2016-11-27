package com.example.android.miwok;

/**
 * Created by user on 7/24/2016.
 */
public class Word {

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;
    //Variable for Image Name//
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mSongId;
    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;
    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     */

    public Word(String defaultTranslation, String miwokTranslation,int imageId,int songId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId=imageId;
        mSongId=songId;
    }
    public Word(String defaultTranslation,String miwokTranslation, int songId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mSongId=songId;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }


    public int getImageResourceId(){return mImageResourceId;}

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
    public int getSongId(){return mSongId;}

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mSongId=" + mSongId +
                '}';
    }
}
