package com.dreambrunomsn.cltparapj.telas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Beneficio;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.classes.TableRowBeneficios;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class FragmentCLT extends Fragment implements View.OnClickListener{

    private EditText etSalario;
    private EditText etRefeicao;
    private EditText etAlimentacao;
    private EditText etTransporte;

    private TextView tvInss;
    private TextView tvTransporte;

    private Button btAddBeneficio;

    private TableLayout tbDescontos;
    private TableLayout cltTabela;

    private Informacoes informacoes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clt, container, false);


        // codigo inical
        informacoes = Informacoes.getInstance();
        tbDescontos = (TableLayout)view.findViewById(R.id.tbDescontos);
        cltTabela = (TableLayout)view.findViewById(R.id.cltTabela);

        tvTransporte = (TextView)view.findViewById(R.id.tvTransporte);

        tvInss = (TextView)view.findViewById(R.id.tvInss);

        etSalario = (EditText)view.findViewById(R.id.etSalario);
        Mascaras.listener(etSalario, Mascaras.SALARIO, tvInss);

        etTransporte = (EditText)view.findViewById(R.id.etTransporte);
        Mascaras.listener(etTransporte, Mascaras.TRANSPORTE, tvTransporte);

        etRefeicao = (EditText)view.findViewById(R.id.etRefeicao);
        Mascaras.listener(etRefeicao, Mascaras.REFEICAO, null);

        etAlimentacao = (EditText)view.findViewById(R.id.etAlimentacao);
        Mascaras.listener(etAlimentacao, Mascaras.ALIMENTACAO, null);

        btAddBeneficio = (Button)view.findViewById(R.id.btAddBeneficio);
        btAddBeneficio.setOnClickListener(this);

        //this.init(view);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btAddBeneficio.getId()){
            this.addBeneficio(view);
        }
    }

    private void init(View view){
        ViewGroup.LayoutParams layout = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        tvTransporte.setText(informacoes.getDescontoTransporte());
        tvInss.setText(informacoes.getInss());
        // deletar as linhas
        for(int i = 0; i < informacoes.getBeneficios().size(); i++){
            Beneficio beneficio = informacoes.getBeneficios().get(i);
            TableRowBeneficios row = (TableRowBeneficios) cltTabela.findViewById(beneficio.getCod());
            cltTabela.removeView(row);
        }
        // adicionar linhas
        for (int i = 0; i < informacoes.getBeneficios().size(); i++){

            Beneficio beneficio = informacoes.getBeneficios().get(i);
            if(beneficio.getValor() > 0) {
                TableRowBeneficios linha = new TableRowBeneficios(getContext(), informacoes.getBeneficios().get(i));
                int indice = cltTabela.indexOfChild(cltTabela.findViewById(R.id.trAdicionar));
                cltTabela.addView(linha, indice, layout);
            }
            if(beneficio.getDesconto() > 0){
                TableRow linha = new TableRow(getContext());
                linha.setGravity(Gravity.RIGHT);
                TextView tx1 = new TextView(getContext());
                TextView tx2 = new TextView(getContext());
                tx1.setText(Mascaras.primeiraMaiuscula(beneficio.getNome()) + ":");
                tx2.setText(beneficio.getDescontoFormatado());

                linha.addView(tx1);
                linha.addView(tx2);
                tbDescontos.addView(linha, layout);
            }
        }
    }

    private void addBeneficio(final View view){
        AdicionarBeneficio ad = new AdicionarBeneficio(getContext());
        ad.show();
        ad.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                escondeTeclado(view);
                init(view);
            }
        });
    }

    private void escondeTeclado(View view){
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
