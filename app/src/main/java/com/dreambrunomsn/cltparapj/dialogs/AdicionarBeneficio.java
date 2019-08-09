package com.dreambrunomsn.cltparapj.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
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
        Mascaras.listener(valor, Mascaras.CONTABIL, null, null);

        desconto = (EditText)findViewById(R.id.dgDesconto);
        Mascaras.listener(desconto, Mascaras.CONTABIL, null, null);

        if(beneficio != null){
            TextView titulo = (TextView)findViewById(R.id.dgTxTitulo);
            titulo.setText(R.string.dgTituloEdit);

            if(beneficio.getCod() != 0)
                excluir.setVisibility(View.VISIBLE);

            nome.setFocusable(false);
            nome.setText(beneficio.getNome());
            valor.setText(Mascaras.decimalDuasCasas(beneficio.getValor(), true));
            desconto.setText(Mascaras.decimalDuasCasas(beneficio.getDesconto(), true));

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dgSalvar:
                gravar();
                dismiss();
                break;
            case R.id.dgExcluir:
                Informacoes informacoes = Informacoes.getInstance();
                informacoes.removeBeneficio(beneficio.getCod());
                dismiss();
                break;
        }
    }

    @Override
    public void dismiss() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(salvar.getWindowToken(), 0);
        super.dismiss();
    }

    public void gravar(){
        Informacoes informacoes = Informacoes.getInstance();
        if(beneficio == null) {
            beneficio = new Beneficio();
            beneficio.setCod(informacoes.getCodigo());
        }

        beneficio.setNome(nome.getText().toString());
        beneficio.setValor(Mascaras.stringToFloat(valor.getText().toString()));

        if(beneficio.getCod() == Beneficio.REFEICAO && valor.getText().length() == 0){
            beneficio.setDesconto(0f);
        } else {
            beneficio.setDesconto(Mascaras.stringToFloat(desconto.getText().toString()));
        }

        informacoes.addBeneficio(beneficio);
    }


}
