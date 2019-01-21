package com.dreambrunomsn.cltparapj.conectores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dreambrunomsn.cltparapj.telas.FragmentMEI;
import com.dreambrunomsn.cltparapj.telas.FragmentCLT;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentCLT clt = new FragmentCLT();
                return clt;
            case 1:
                FragmentMEI mei = new FragmentMEI();
                return mei;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
