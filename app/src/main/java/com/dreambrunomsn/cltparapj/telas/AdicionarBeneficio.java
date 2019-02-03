package com.dreambrunomsn.cltparapj.telas;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Beneficio;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class AdicionarBeneficio extends Dialog implements View.OnClickListener{

    private Button sair;
    private Button salvar;

    private EditText nome;
    private EditText valor;
    private EditText desconto;

    // CONSTRUCTOR
    public AdicionarBeneficio(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_adicionar_beneficio);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sair = (Button)findViewById(R.id.dgSair);
        sair.setOnClickListener(this);

        salvar = (Button)findViewById(R.id.dgSalvar);
        salvar.setOnClickListener(this);

        nome = (EditText)findViewById(R.id.dgNome);
        //Mascaras.listener(nome, Mascaras.CONTABIL, null);

        valor = (EditText)findViewById(R.id.dgValor);
        Mascaras.listener(valor, Mascaras.CONTABIL, null);

        desconto = (EditText)findViewById(R.id.dgDesconto);
        Mascaras.listener(desconto, Mascaras.CONTABIL, null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dgSair:
                dismiss();
                break;
            case R.id.dgSalvar:
                salvar();
                dismiss();
                break;
        }
    }

    public void salvar(){
        Informacoes informacoes = Informacoes.getInstance();

        Beneficio beneficio = new Beneficio();
        beneficio.setCod(informacoes.getBeneficios().size());
        beneficio.setNome(nome.getText().toString());
        beneficio.setValor(valor.getText().toString());
        beneficio.setDesconto(desconto.getText().toString());

        informacoes.addBeneficio(beneficio);
    }


}
