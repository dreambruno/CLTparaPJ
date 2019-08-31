package com.dreambrunomsn.cltparapj.conectores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dreambrunomsn.cltparapj.telas.FragmentCLT;
import com.dreambrunomsn.cltparapj.telas.FragmentComparativo;
import com.dreambrunomsn.cltparapj.telas.FragmentPJ;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentCLT();
            case 1:
                return new FragmentPJ();
            case 2:
                return new FragmentComparativo();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
