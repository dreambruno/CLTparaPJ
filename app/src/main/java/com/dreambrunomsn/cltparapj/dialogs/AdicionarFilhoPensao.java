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
import android.widget.Toast;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class AdicionarFilhoPensao extends Dialog implements View.OnClickListener{

    private Button salvar;

    private EditText filho;
    private EditText pensao;


    // CONSTRUCTOR
    public AdicionarFilhoPensao(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_adicionar_filho_pensao);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        filho = (EditText)findViewById(R.id.fpFilho);
        pensao = (EditText)findViewById(R.id.fpPensao);

        salvar = (Button)findViewById(R.id.fpSalvar);
        salvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fpSalvar:
                this.salvar();
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

    private void salvar(){
        Informacoes informacoes = Informacoes.getInstance();

        informacoes.setFilho(Integer.parseInt(filho.getText().toString())); //TODO: AJUSTAR GET
        informacoes.setPensao(Float.valueOf(pensao.getText().toString())); //TODO: AJUSTAR GET

        Toast.makeText(getContext(), "Filho!", Toast.LENGTH_SHORT).show();
    }
}
