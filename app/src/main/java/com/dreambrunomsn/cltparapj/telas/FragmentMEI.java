package com.dreambrunomsn.cltparapj.telas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class FragmentMEI extends Fragment implements View.OnClickListener {

    private Button popBt;
    private EditText tvTeste;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mei, container, false);


        popBt = (Button) view.findViewById(R.id.btTeste);
        tvTeste = (EditText) view.findViewById(R.id.tvTexte);

        popBt.setOnClickListener(this);
        //tvTeste.addTextChangedListener(Mascaras.textChangeListener(tvTeste, Mascaras.CONTABIL));
        //Mascaras.listener(tvTeste, Mascaras.CONTABIL);


        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == popBt.getId()){
            tvTeste.setText(Mascaras.contabil(tvTeste.getText().toString()));
        }
    }
}
