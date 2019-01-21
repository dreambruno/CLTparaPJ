package com.dreambrunomsn.cltparapj.telas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class FragmentCLT extends Fragment implements View.OnClickListener{

    private EditText etSalario;
    private EditText etRefeicao;
    private EditText etAlimentacao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clt, container, false);


        // codigo inical
        etSalario = (EditText)view.findViewById(R.id.etSalario);
        Mascaras.listener(etSalario, Mascaras.CONTABIL);

        etRefeicao = (EditText)view.findViewById(R.id.etRefeicao);
        Mascaras.listener(etRefeicao, Mascaras.CONTABIL);

        etAlimentacao = (EditText)view.findViewById(R.id.etAlimentacao);
        Mascaras.listener(etAlimentacao, Mascaras.CONTABIL);



        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
