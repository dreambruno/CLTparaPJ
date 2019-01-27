package com.dreambrunomsn.cltparapj.telas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class FragmentCLT extends Fragment implements View.OnClickListener{

    private EditText etSalario;
    private EditText etRefeicao;
    private EditText etAlimentacao;
    private EditText etTransporte;

    private TextView tvInss;
    private TextView tvTransporte;

    private Button btDescontos;
    private Button btAddDesc;

    private TableLayout tbDescontos;

    private Informacoes informacoes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clt, container, false);


        // codigo inical
        informacoes = Informacoes.getInstance();
        tbDescontos = (TableLayout)view.findViewById(R.id.tbDescontos);

        tvTransporte = (TextView)view.findViewById(R.id.tvTransporte);
        tvTransporte.setText(informacoes.getTransporte());

        tvInss = (TextView)view.findViewById(R.id.tvInss);
        tvInss.setText(informacoes.getInss());

        etSalario = (EditText)view.findViewById(R.id.etSalario);
        Mascaras.listener(etSalario, Mascaras.SALARIO, tvInss);

        etTransporte = (EditText)view.findViewById(R.id.etTransporte);
        Mascaras.listener(etTransporte, Mascaras.TRANSPORTE, tvTransporte);

        etRefeicao = (EditText)view.findViewById(R.id.etRefeicao);
        Mascaras.listener(etRefeicao, Mascaras.REFEICAO, null);

        etAlimentacao = (EditText)view.findViewById(R.id.etAlimentacao);
        Mascaras.listener(etAlimentacao, Mascaras.ALIMENTACAO, null);

        btDescontos = (Button)view.findViewById(R.id.btDescontos);
        btDescontos.setOnClickListener(this);

        btAddDesc = (Button)view.findViewById(R.id.btAddDesc);
        btAddDesc.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btDescontos.getId()){
            if(tbDescontos.getVisibility() == View.VISIBLE){
                tbDescontos.setVisibility(View.GONE);
                btDescontos.setText(R.string.btMostrar);
            }else {
                tbDescontos.setVisibility(View.VISIBLE);
                btDescontos.setText(R.string.btOcultar);
            }
        }else if(view.getId() == btAddDesc.getId()){
            //TableRow tr = new TableRow();
            //tbDescontos
        }
    }
}
