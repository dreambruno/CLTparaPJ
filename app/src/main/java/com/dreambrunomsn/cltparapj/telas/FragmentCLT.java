package com.dreambrunomsn.cltparapj.telas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Beneficio;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.classes.LinhaBeneficio;
import com.dreambrunomsn.cltparapj.classes.LinhaDesconto;
import com.dreambrunomsn.cltparapj.conectores.OnBeneficioChangeListener;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class FragmentCLT extends Fragment implements View.OnClickListener{

    private EditText etSalario;
    private EditText etTransporte;
    private EditText etRefeicao;

    private TextView tvInss;
    private TextView tvTransporte;

    private Button btAddBeneficio;

    private LinearLayout painelDescontos;
    private LinearLayout painelBeneficios;

    private Informacoes informacoes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_clt, container, false);


        // codigo inical
        informacoes = Informacoes.getInstance();
        painelDescontos = (LinearLayout)view.findViewById(R.id.painelDescontos);
        painelBeneficios = (LinearLayout)view.findViewById(R.id.painelBeneficios);

        tvTransporte = (TextView)view.findViewById(R.id.tvTransporte);

        tvInss = (TextView)view.findViewById(R.id.tvInss);

        etSalario = (EditText)view.findViewById(R.id.etSalario);
        Mascaras.listener(etSalario, Mascaras.SALARIO, tvInss);

        etTransporte = (EditText)view.findViewById(R.id.etTransporte);
        Mascaras.listener(etTransporte, Mascaras.TRANSPORTE, tvTransporte);

        etRefeicao = (EditText)view.findViewById(R.id.etRefeicao);
        etRefeicao.setFocusable(false);
        etRefeicao.setOnClickListener(this);
        Mascaras.listener(etRefeicao, Mascaras.REFEICAO, null);

        btAddBeneficio = (Button)view.findViewById(R.id.btAddBeneficio);
        btAddBeneficio.setOnClickListener(this);

        informacoes.setOnbeneficioChangeListener(new OnBeneficioChangeListener() {
            @Override
            public void onBeneficioChange() {
                init();
            }
        });

        this.init();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btAddBeneficio:
                AdicionarBeneficio ad = new AdicionarBeneficio(getContext(), null);
                ad.show();
                break;
            case R.id.etRefeicao:
                AdicionarBeneficio ade = new AdicionarBeneficio(getContext(), informacoes.getBeneficios().get(Beneficio.REFEICAO));
                ade.show();
                break;
        }
    }

    private void init(){
        tvTransporte.setText(informacoes.getDescontoTransporte());
        tvInss.setText(informacoes.getInss());

        // deletar as linhas
        painelDescontos.removeAllViews();
        painelBeneficios.removeAllViews();

        // adicionar linhas
        if(informacoes.getBeneficios().get(Beneficio.REFEICAO).getValor() > 0){
            etRefeicao.setText(informacoes.getBeneficios().get(Beneficio.REFEICAO).getValorFormatado());
        }

        for (Beneficio beneficio : informacoes.getBeneficios()){

            if(beneficio.getValor() > 0 && beneficio.getCod() != 0) {
                LinhaBeneficio linhaBeneficio = new LinhaBeneficio(getContext(), beneficio);
                painelBeneficios.addView(linhaBeneficio);
            }
            if(beneficio.getDesconto() > 0){
                LinhaDesconto linhaDesconto = new LinhaDesconto(getContext(), beneficio);
                painelDescontos.addView(linhaDesconto);
            }
        }
    }
}
