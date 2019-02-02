package com.dreambrunomsn.cltparapj.telas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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

    private Button btDescontos;
    private Button btAddDesc;
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

        btDescontos = (Button)view.findViewById(R.id.btDescontos);
        btDescontos.setOnClickListener(this);

        btAddBeneficio = (Button)view.findViewById(R.id.btAddBeneficio);
        btAddBeneficio.setOnClickListener(this);

        btAddDesc = (Button)view.findViewById(R.id.btAddDesc);
        btAddDesc.setOnClickListener(this);

        //this.init(view);

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
        }else if(view.getId() == btAddBeneficio.getId()){
            this.addBeneficio(view);
        }
    }

    private void init(View view){
        tvTransporte.setText(informacoes.getDescontoTransporte());
        tvInss.setText(informacoes.getInss());
        // deletar as linhas
        for(int i = 0; i < informacoes.getBeneficios().size(); i++){
            TableRow row = (TableRow)cltTabela.findViewById(informacoes.getBeneficios().get(i).getCod());
            cltTabela.removeView(row);
        }
        // adicionar linhas
        for (int i = 0; i < informacoes.getBeneficios().size(); i++){
            /*TableRow linha = new TableRow(getContext());
            linha.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            //
            linha.setId(informacoes.getBeneficios().get(i).getCod());
            TextView tv = new TextView(getContext());
            tv.setPadding(0,0,5,0);
            tv.setTextSize(16);
            tv.setText(informacoes.getBeneficios().get(i).getNome() + ":");

            TextView tv2 = new TextView(getContext());
            tv2.setText(informacoes.getBeneficios().get(i).getValor());

            linha.addView(tv);
            linha.addView(tv2);
*/
            TableRowBeneficios linha = new TableRowBeneficios(getContext(), informacoes.getBeneficios().get(i));
            cltTabela.addView(linha, 4+i, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
        }
    }

    private void addBeneficio(final View view){
        AdicionarBeneficio ad = new AdicionarBeneficio(getContext());
        ad.show();
        ad.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                // recarregar a página.
                init(view);
                escondeTeclado(view);
            }
        });
    }

    private void escondeTeclado(View view){
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
