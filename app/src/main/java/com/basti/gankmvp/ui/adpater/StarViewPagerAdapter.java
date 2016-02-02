package com.basti.gankmvp.ui.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.basti.gankmvp.ui.fragment.StarFragment;

/**
 * Created by SHIBW-PC on 2016/2/2.
 */
public class StarViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTitle;

    public StarViewPagerAdapter(FragmentManager fm, String[] title) {
        super(fm);
        mTitle = title;
    }

    private StarViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return StarFragment.newInstance(mTitle[position]);
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
