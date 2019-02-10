package com.dreambrunomsn.cltparapj.telas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Beneficio;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.classes.LinhaBeneficio;
import com.dreambrunomsn.cltparapj.classes.LinhaDesconto;
import com.dreambrunomsn.cltparapj.conectores.OnBeneficioChangeListener;
import com.dreambrunomsn.cltparapj.dialogs.AdicionarFilhoPensao;
import com.dreambrunomsn.cltparapj.dialogs.AdicionarBeneficio;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class FragmentCLT extends Fragment implements View.OnClickListener{

    private EditText etSalario;
    private EditText etTransporte;
    private EditText etRefeicao;

    private TextView tvInss;
    private TextView tvIrrf;
    private TextView tvTransporte;

    private Button btAddBeneficio;

    private LinearLayout painelDescontos;
    private LinearLayout painelBeneficios;

    private FloatingActionButton floatingActionButton;
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
        tvIrrf = (TextView)view.findViewById(R.id.tvIrrf);

        etSalario = (EditText)view.findViewById(R.id.etSalario);
        Mascaras.listener(etSalario, Mascaras.SALARIO, tvInss, tvIrrf);

        etTransporte = (EditText)view.findViewById(R.id.etTransporte);
        Mascaras.listener(etTransporte, Mascaras.TRANSPORTE, tvTransporte, null);

        etRefeicao = (EditText)view.findViewById(R.id.etRefeicao);
        etRefeicao.setFocusable(false);
        etRefeicao.setOnClickListener(this);
        Mascaras.listener(etRefeicao, Mascaras.REFEICAO, null, null);

        btAddBeneficio = (Button)view.findViewById(R.id.btAddBeneficio);
        btAddBeneficio.setOnClickListener(this);

        informacoes.setOnbeneficioChangeListener(new OnBeneficioChangeListener() {
            @Override
            public void onBeneficioChange() {
                init();
            }
        });

        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
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

    private void init(){
        tvTransporte.setText(informacoes.getDescontoTransporte());
        tvInss.setText(informacoes.getInss());
        tvIrrf.setText(informacoes.getIrrf());

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
