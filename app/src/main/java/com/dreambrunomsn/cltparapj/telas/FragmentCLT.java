package com.dreambrunomsn.cltparapj.telas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Beneficio;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.classes.LinhaBeneficio;
import com.dreambrunomsn.cltparapj.classes.LinhaDesconto;
import com.dreambrunomsn.cltparapj.conectores.OnInformacaoChangeListener;
import com.dreambrunomsn.cltparapj.dialogs.AdicionarBeneficio;
import com.dreambrunomsn.cltparapj.dialogs.AdicionarFilhoPensao;
import com.dreambrunomsn.cltparapj.utils.Mascaras;
import com.dreambrunomsn.cltparapj.utils.Utils;

import java.util.Objects;

public class FragmentCLT extends Fragment implements View.OnClickListener{

    private EditText etSalario;
    private EditText etTransporte;
    private EditText etRefeicao;

    private TextView tvInss;
    private TextView tvIrrf;
    private TextView tvTransporte;
    private TextView tvPensao;

    private Button btAddBeneficio;

    private LinearLayout painelDescontos;
    private LinearLayout painelBeneficios;
    private LinearLayout painelPensao;

    private FloatingActionButton floatingActionButton;
    private Informacoes informacoes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_clt, container, false);
        informacoes = Informacoes.getInstance();
        informacoes.setOnCLTChangeListener(new OnInformacaoChangeListener() {
            @Override
            public void onInformacaoChange() {
                init();
            }
        });


        // codigo inical
        painelDescontos = view.findViewById(R.id.painelDescontos);
        painelBeneficios = view.findViewById(R.id.painelBeneficios);
        painelPensao = view.findViewById(R.id.painelPensao);

        tvTransporte = view.findViewById(R.id.tvTransporte);
        tvPensao= view.findViewById(R.id.tvPensao);
        tvInss = view.findViewById(R.id.tvInss);
        tvIrrf = view.findViewById(R.id.tvIrrf);

        etSalario = view.findViewById(R.id.etSalario);
        Mascaras.listener(etSalario, Mascaras.SALARIO);

        etTransporte = view.findViewById(R.id.etTransporte);
        Mascaras.listener(etTransporte, Mascaras.TRANSPORTE);

        etRefeicao = view.findViewById(R.id.etRefeicao);
        etRefeicao.setFocusable(false);
        etRefeicao.setOnClickListener(this);
        Mascaras.listener(etRefeicao, Mascaras.REFEICAO);

        btAddBeneficio = view.findViewById(R.id.btAddBeneficio);
        btAddBeneficio.setOnClickListener(this);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(this);

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
            case R.id.floatingActionButton:
                AdicionarFilhoPensao afp = new AdicionarFilhoPensao(getContext());
                afp.show();
                break;
        }
    }

    @Override
    public void setUserVisibleHint(boolean visivel) {
        super.setUserVisibleHint(visivel);
        if(visivel && this.getView() != null){
            Utils.hideKeyboard(Objects.requireNonNull(getActivity()));
            this.init();
        }
    }

    private void init(){

        etSalario.setText(Mascaras.decimalDuasCasas(informacoes.getSalario(), true));
        etTransporte.setText(Mascaras.decimalDuasCasas(informacoes.getTransporte(), true));

        tvTransporte.setText(Mascaras.decimalDuasCasas(informacoes.getDescontoTransporte(), true));
        tvInss.setText(Mascaras.decimalDuasCasas(informacoes.getInss(informacoes.getSalario()), true));
        tvIrrf.setText(Mascaras.decimalDuasCasas(informacoes.getIrrf(informacoes.getSalario()), true));

        // deletar as linhas
        painelDescontos.removeAllViews();
        painelBeneficios.removeAllViews();

        // adicionar linhas
        etRefeicao.setText(Mascaras.decimalDuasCasas(informacoes.getBeneficios().get(Beneficio.REFEICAO).getValor(), true));

        if(informacoes.getPensaoClt() > 0){
            tvPensao.setText(Mascaras.decimalDuasCasas(informacoes.getPensaoCLTValor(informacoes.getSalario()), true));
            painelPensao.setVisibility(View.VISIBLE);
        } else {
            painelPensao.setVisibility(View.GONE);
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
