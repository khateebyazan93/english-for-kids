package com.example.yazan.englishforkids.adapters;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yazan.englishforkids.R;
import com.example.yazan.englishforkids.data.Word;

import java.util.List;

/**
 * Created by yazan on 1/21/17.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    /**
     * TAG FOR LOG MESSAGES
     */
    public static final String LOG_TAG = WordAdapter.class.getName();


    /**
     * Define listener
     */
    private static OnItemClickListener mListener;

    /**
     * list of words
     */
    private List<Word> mWordList;

    /**
     * color resource id for background of this list of words
     */
    private int mColorResourceId;

    /**
     * context Of App
     */
    private Context mContext;


    /**
     * construct new {@link WordAdapter} object
     *
     * @param wordList is words list
     */
    public WordAdapter(Context context, List<Word> wordList, int colorResourceId) {


        this.mContext = context;
        this.mWordList = wordList;
        this.mColorResourceId = colorResourceId;
    }

    /**
     * Define method that allows to define listener in activities class
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    /**
     * Create new {@link WordViewHolder}
     *
     * @param parent   ViewGroup into which the new View will be added after it is bound to an adapter position.
     *                 RecyclerView always passes itself as the parent view
     * @param viewType The view type of the new View.
     * @return {@link WordViewHolder}
     */
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(LOG_TAG, "TEST : onCreateViewHolder() called ...");

        //get Context from ViewGroup
        Context context = parent.getContext();

        //inflate word item and passing to WordViewHolder
        View wordView = LayoutInflater.from(context).inflate(R.layout.word_item, parent, false);
        WordViewHolder wordViewHolder = new WordViewHolder(wordView);

        return wordViewHolder;
    }

    /**
     * populating data into the list item through WordViewHolder
     *
     * @param holder   is a {@link WordViewHolder} object
     * @param position the item within the adapter's data set
     */
    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Log.i(LOG_TAG, "TEST : onBindViewHolder() called ...");

        //bind data position && play icon into listener
        holder.bind(mWordList.get(position), holder.mPlayIconImageView);

        // get the {@link Word} object  based on position
        Word word = mWordList.get(position);

        //check if there is image provided for current word
        if (word.hasImage()) {
            holder.mImageWordImageView.setImageResource(word.getImageResourceId());
            holder.mImageWordImageView.setVisibility(View.VISIBLE);
        } else {
            holder.mImageWordImageView.setVisibility(View.GONE);

        }

        //find out the data and fill it based on WordViewHolder' position
        holder.menglishWordTextView.setText(word.getEnglishWordId());
        holder.mArabicWordTextView.setText(word.getArabicWordID());

        // find and set the color that resource ID maps to
        int categoryColor = ContextCompat.getColor(mContext, mColorResourceId);
        holder.mCategoryBackgroundView.setBackgroundColor(categoryColor);

        //setup play icon
        holder.mPlayIconImageView.setImageResource(word.getPlayIconResourceId());


    }

    @Override
    public int getItemCount() {

        return mWordList.size();
    }

    /**
     * listener interface
     */
    public interface OnItemClickListener {
        void onItemClick(Word wordItems, ImageView playIcon);
    }

    /**
     * Word View Holder class
     */
    public static class WordViewHolder extends RecyclerView.ViewHolder {
        /**
         * Image associated with this word
         */
        public ImageView mImageWordImageView;
        /**
         * english word
         */
        public TextView menglishWordTextView;
        /**
         * arabic word
         */
        public TextView mArabicWordTextView;

        /**
         * background color for list word
         */
        public View mCategoryBackgroundView;

        /**
         * play icon for list item
         */
        public ImageView mPlayIconImageView;


        /**
         * construct new {@link WordViewHolder} object
         *
         * @param itemView holds all word item "word_item.xml"
         */
        public WordViewHolder(final View itemView) {
            super(itemView);
            Log.i(LOG_TAG, "TEST : WordViewHolder() constructor called ...");

            //lookup and attach word item
            mImageWordImageView = (ImageView) itemView.findViewById(R.id.image_word);
            menglishWordTextView = (TextView) itemView.findViewById(R.id.english_word);
            mArabicWordTextView = (TextView) itemView.findViewById(R.id.arabic_word);
            mCategoryBackgroundView = itemView.findViewById(R.id.category_background);
            mPlayIconImageView = (ImageView) itemView.findViewById(R.id.play_icon);


        }

        /**
         * bind {@link Word} Object & playIcon to Listener
         */

        public void bind(final Word wordItems, final ImageView playIcon) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(wordItems, playIcon);
                }
            });
        }
    }


}
