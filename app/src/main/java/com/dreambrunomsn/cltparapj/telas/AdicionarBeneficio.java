package com.dreambrunomsn.cltparapj.telas;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Beneficio;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class AdicionarBeneficio extends Dialog implements View.OnClickListener{

    private Beneficio beneficio;

    private Button salvar;
    private Button excluir;

    private EditText nome;
    private EditText valor;
    private EditText desconto;

    // CONSTRUCTOR
    public AdicionarBeneficio(@NonNull Context context, Beneficio beneficio) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_adicionar_beneficio);
        this.beneficio = beneficio;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        salvar = (Button)findViewById(R.id.dgSalvar);
        salvar.setOnClickListener(this);

        excluir = (Button)findViewById(R.id.dgExcluir);
        excluir.setOnClickListener(this);

        nome = (EditText)findViewById(R.id.dgNome);

        valor = (EditText)findViewById(R.id.dgValor);
        Mascaras.listener(valor, Mascaras.CONTABIL, null);

        desconto = (EditText)findViewById(R.id.dgDesconto);
        Mascaras.listener(desconto, Mascaras.CONTABIL, null);

        if(beneficio != null){
            TextView titulo = (TextView)findViewById(R.id.dgTxTitulo);
            titulo.setText(R.string.dgTituloEdit);

            if(beneficio.getCod() != 0)
                excluir.setVisibility(View.VISIBLE);

            nome.setFocusable(false);
            nome.setText(beneficio.getNome());
            valor.setText(beneficio.getValorFormatado());
            desconto.setText(beneficio.getDescontoFormatado());

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dgSalvar:
                if(valor.getText().length() > 0 || desconto.getText().length() > 0)
                    salvar();
                dismiss();
                break;
            case R.id.dgExcluir:
                Informacoes informacoes = Informacoes.getInstance();
                informacoes.removeBeneficio(beneficio.getCod());
                dismiss();
                break;
        }
    }

    public void salvar(){
        Informacoes informacoes = Informacoes.getInstance();
        if(beneficio == null) {
            beneficio = new Beneficio();
            beneficio.setCod(informacoes.getBeneficios().size()); //TODO: Melhorar isso aqui, encontrar outra chave.
        }
        beneficio.setNome(nome.getText().toString());
        beneficio.setValor(valor.getText().toString());
        beneficio.setDesconto(desconto.getText().toString());

        informacoes.addBeneficio(beneficio);
    }


}
