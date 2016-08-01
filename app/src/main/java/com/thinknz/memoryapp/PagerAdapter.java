package com.thinknz.memoryapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by liamtubs on 26/07/2016.
 */
public class PagerAdapter extends SmartFragmentStatePagerAdapter {
    int mNumOfTabs;
    String date;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, String date) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.date = date;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();

        switch (position) {
            case 0:
                MeetingScheduleTab tab1 = new MeetingScheduleTab();
                /*bundle.putString("id", id);
                bundle.putString("name", name);
                bundle.putString("day", day);
                tab1.setArguments(bundle);*/
                tab1.date = date;
                return tab1;
            case 1:
                MeetingScheduleTab tab2 = new MeetingScheduleTab();
                /*bundle.putString("id", id);
                bundle.putString("name", name);
                bundle.putString("day", day);
                tab2.setArguments(bundle);*/
                tab2.date = date;
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
