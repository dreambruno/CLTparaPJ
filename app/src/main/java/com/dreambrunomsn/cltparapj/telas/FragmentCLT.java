package com.dreambrunomsn.cltparapj.telas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Beneficio;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.classes.LinhaBeneficio;
import com.dreambrunomsn.cltparapj.conectores.OnBeneficioChangeListener;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

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
        ViewGroup.LayoutParams layout = new LinearLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tvTransporte.setText(informacoes.getDescontoTransporte());
        tvInss.setText(informacoes.getInss());

        // deletar as linhas
        painelDescontos.removeAllViews();
        painelBeneficios.removeAllViews();

        // adicionar linhas
        if(informacoes.getBeneficios().get(Beneficio.REFEICAO).getValor() > 0){
            etRefeicao.setText(informacoes.getBeneficios().get(Beneficio.REFEICAO).getValorFormatado());
        }

        for (Beneficio beneficio : informacoes.getBeneficios().values()){
            if(beneficio.getValor() > 0 && beneficio.getCod() != 0) {
                LinhaBeneficio linha = new LinhaBeneficio(getContext(), beneficio);
                painelBeneficios.addView(linha);
            }
            if(beneficio.getDesconto() > 0){
                LinearLayout linha = new LinearLayout(getContext());
                linha.setGravity(Gravity.RIGHT);
                linha.setId(beneficio.getCod());

                TextView tx1 = new TextView(getContext());
                tx1.setText(Mascaras.primeiraMaiuscula(beneficio.getNome()) + ":");
                tx1.setPadding(0,0,4,0);

                TextView tx2 = new TextView(getContext());
                tx2.setText(beneficio.getDescontoFormatado());

                linha.addView(tx1);
                linha.addView(tx2);
                painelDescontos.addView(linha, layout);
            }
        }
    }
}
