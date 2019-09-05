package com.dreambrunomsn.cltparapj.telas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.conectores.OnInformacaoChangeListener;
import com.dreambrunomsn.cltparapj.enums.InformacoesAdicionais;
import com.dreambrunomsn.cltparapj.utils.Mascaras;
import com.dreambrunomsn.cltparapj.utils.Utils;

import java.util.Objects;

public class FragmentPJ extends Fragment implements View.OnClickListener{

    private TextView tvContador;
    private TextView tvSaude;
    private TextView tvInssPj;
    private TextView tvIRPF;
    private TextView tvTransportePj;
    private TextView tvSimples;
    private TextView tvPensaoPj;

    private EditText etContador;
    private EditText etSaude;
    private EditText etProlabore;

    private LinearLayout llPensaoPJ;

    private Informacoes informacoes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pj, container, false);
        informacoes = Informacoes.getInstance();
        informacoes.setOnPJChangeListener(new OnInformacaoChangeListener() {
            @Override
            public void onInformacaoChange() {
                init();
            }
        });

        etContador = view.findViewById(R.id.etContador);
        tvContador = view.findViewById(R.id.tvContador);
        Mascaras.listener(etContador, Mascaras.CONTADOR);

        etSaude = view.findViewById(R.id.etSaude);
        tvSaude = view.findViewById(R.id.tvSaude);
        Mascaras.listener(etSaude, Mascaras.SAUDE);

        etProlabore = view.findViewById(R.id.etProLabore);
        tvInssPj = view.findViewById(R.id.tvInssPj);
        Mascaras.listener(etProlabore, Mascaras.PRO_LABORE);

        tvTransportePj = view.findViewById(R.id.tvTransportePj);
        tvIRPF = view.findViewById(R.id.tvIRPF);
        tvSimples = view.findViewById(R.id.tvSimples);
        tvPensaoPj = view.findViewById(R.id.tvPensaoPj);

        llPensaoPJ = view.findViewById(R.id.llPensaoPJ);

        this.init();

        return view;
    }

    @Override
    public void onClick(View view) {
        //
    }

    @Override
    public void setUserVisibleHint(boolean visivel) {
        super.setUserVisibleHint(visivel);
        if(visivel && this.getView() != null){
            Utils.hideKeyboard(Objects.requireNonNull(getActivity()));
            this.init();
        }
    }

    public void init(){
        etContador.setText(Mascaras.decimalDuasCasas(informacoes.getContador(), true));
        etSaude.setText(Mascaras.decimalDuasCasas(informacoes.getSaude(), true));

        tvContador.setText(Mascaras.decimalDuasCasas(informacoes.getContador(), true));
        tvSaude.setText(Mascaras.decimalDuasCasas(informacoes.getSaude(), true));

        etProlabore.setText(Mascaras.decimalDuasCasas(informacoes.getProLabore(), false));
        tvInssPj.setText(Mascaras.decimalDuasCasas(informacoes.getProLaboreINSS(informacoes.getTotalPJ()), true));

        tvTransportePj.setText(Mascaras.decimalDuasCasas(informacoes.getTransporte(), true));
        tvIRPF.setText(Mascaras.decimalDuasCasas(informacoes.getIRPF(informacoes.getTotalPJ()), true));
        tvSimples.setText(Mascaras.decimalDuasCasas(this.getSimples(), true));

        if(informacoes.getPensaoPJValor() > 0){
            llPensaoPJ.setVisibility(View.VISIBLE);
            tvPensaoPj.setText(Mascaras.decimalDuasCasas(informacoes.getPensaoPJValor(), true));
        } else {
            llPensaoPJ.setVisibility(View.GONE);
        }
    }

    public float getSimples(){
        float inss = informacoes.getProLaboreINSS(informacoes.getTotalPJ());

        float irpf = informacoes.getIRPF(informacoes.getTotalPJ());

        float simples = informacoes.getTotalPJ() * InformacoesAdicionais.SIMPLES.getValor();

        return inss + irpf + simples;
    }
}
