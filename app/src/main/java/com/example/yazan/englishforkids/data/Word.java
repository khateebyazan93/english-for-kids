package com.example.yazan.englishforkids.data;

/**
 * Created by yazan on 1/20/17.
 */

/**
 * {@link Word} represent a vocabulary word that the user familiar with and the usr want to learn
 * it's contain resource IDs  for the user language, english translation, audio file, and optional image file
 * for that word .
 */

public class Word {

    /**
     * Constant value that represent no image resource ID provided for this word
     */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * String resource ID for english word
     */
    private int mEnglishWordId;

    /**
     * String resource ID for arabic word
     */
    private int mArabicWordID;

    /**
     * Audio resource id for english word
     */
    private int mAudioResourceId;
    /**
     * Image resource ID for the word
     */
    private int mImageResourceId = NO_IMAGE_PROVIDED;


    private int mPlayIconResourceId;


    /**
     * create new {@link Word} object without image resource ID
     *
     * @param englishWordId   is string resource ID for english word
     * @param arabicWordID    is String resource ID for arabic word
     * @param audioResourceId is resource ID for audio file  associated with this word
     */
    public Word( int englishWordId,int arabicWordID, int audioResourceId, int playIconResourceId) {
        this.mEnglishWordId = englishWordId;
        this.mArabicWordID = arabicWordID;
        this.mAudioResourceId = audioResourceId;
        this.mPlayIconResourceId = playIconResourceId;
    }

    /**
     * create new {@link Word} object with image resource ID
     *
     * @param englishWordId   is string resource ID for english word
     * @param arabicWordId   is String resource ID for arabic word
     * @param audioResourceId is resource ID for audio file  associated with this word
     * @param imageResourceId is the drawable resource ID for the image associated with this word
     */
    public Word( int englishWordId,int arabicWordId, int audioResourceId, int imageResourceId, int playIconResourceId) {
        this.mEnglishWordId = englishWordId;
        this.mArabicWordID = arabicWordId;
        this.mAudioResourceId = audioResourceId;
        this.mImageResourceId = imageResourceId;
        this.mPlayIconResourceId = playIconResourceId;
    }


    /**
     * Get image or not
     *
     * @return there is image or not for this word
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Get arabic word
     *
     * @return String resource ID for the arabic word
     */
    public int getArabicWordID() {
        return mArabicWordID;
    }

    /**
     * Get english word
     *
     * @return String resource ID for english word
     */
    public int getEnglishWordId() {
        return mEnglishWordId;
    }

    /**
     * Get Audio file
     *
     * @return resource ID for the Audio file associated with this word
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    /**
     * Get image
     *
     * @return Image resource ID associated with this word
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getPlayIconResourceId() {
        return mPlayIconResourceId;
    }

}
