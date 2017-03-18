package com.example.yazan.englishforkids.activities;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.dision.android.rtlviewpager.RTLPagerAdapter;
import com.dision.android.rtlviewpager.RTLViewPager;
import com.dision.android.rtlviewpager.Tab;
import com.example.yazan.englishforkids.R;
import com.example.yazan.englishforkids.fragments.ColorsFragment;
import com.example.yazan.englishforkids.fragments.FamilyFragment;
import com.example.yazan.englishforkids.fragments.NumbersFragment;
import com.example.yazan.englishforkids.fragments.PhrasesFragment;


public class MainActivity extends AppCompatActivity {

    /**
     * Constant Number for tabs
     */
    private static final int TAB_FAMILY = 1;
    private static final int TAB_NUMBER = 2;
    private static final int TAB_COLOR = 3;
    private static final int TAB_PHRASE = 4;
    /**
     * tabs (fragments)
     */
    private Tab[] mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // attach/find language view pager that will allow the user to swipe between fragments
        RTLViewPager viewPager = (RTLViewPager) findViewById(R.id.viewpager);

        //attach  language Tab Layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        //create array of tabs (fragments)
        mTabs = getTabs();

        //setup RTLPagerAdapter with RTLViewPager
        RTLPagerAdapter rtlViewPager = new RTLPagerAdapter(getFragmentManager(), mTabs, true);
        viewPager.setAdapter(rtlViewPager);
        viewPager.setRtlOriented(true);


        // setup view pager with tab layout
        tabLayout.setupWithViewPager(viewPager);


    }

    /**
     * Helper method for {@link #onCreate(Bundle)}
     * <p>
     * create array of tabs(fragments)
     *
     * @return array of {@link Tab} object
     */
    public Tab[] getTabs() {

        Tab[] tabs = new Tab[]{
                new Tab(TAB_FAMILY, getString(R.string.category_family)) {
                    @Override
                    public Fragment getFragment() {
                        return new FamilyFragment();
                    }
                },
                new Tab(TAB_NUMBER, getString(R.string.category_numbers)) {
                    @Override
                    public Fragment getFragment() {
                        return new NumbersFragment();
                    }
                },
                new Tab(TAB_COLOR, getString(R.string.category_colors)) {
                    @Override
                    public Fragment getFragment() {
                        return new ColorsFragment();
                    }
                },
                new Tab(TAB_PHRASE, getString(R.string.category_phrases)) {
                    @Override
                    public Fragment getFragment() {
                        return new PhrasesFragment();
                    }
                },

        };

        return tabs;
    }

}
