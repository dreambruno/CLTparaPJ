package com.dreambrunomsn.cltparapj.telas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.utils.Utils;

public class FragmentPJ extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pj, container, false);

        Utils.hideKeyboard(getActivity());

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
