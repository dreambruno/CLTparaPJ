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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class AdicionarFilhoPensao extends Dialog implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    private Button salvar;

    private SeekBar filho;
    private EditText pensao;
    private TextView mostrador;

    private Informacoes informacoes;


    // CONSTRUCTOR
    public AdicionarFilhoPensao(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_adicionar_filho_pensao);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        informacoes = Informacoes.getInstance();

        mostrador = (TextView)findViewById(R.id.fpMostrador);

        filho = (SeekBar)findViewById(R.id.fpFilho);
        filho.setOnSeekBarChangeListener(this);

        pensao = (EditText)findViewById(R.id.fpPensao);
        Mascaras.listener(pensao, Mascaras.PENSAO, null, null);

        salvar = (Button)findViewById(R.id.fpSalvar);
        salvar.setOnClickListener(this);

        mostrador.setText(String.valueOf(informacoes.getFilho()));
        filho.setProgress(informacoes.getFilho());
        pensao.setText(informacoes.getPensaoFormatada());
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        mostrador.setText(String.valueOf(i));
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }

    private void salvar(){
        informacoes.setFilho(filho.getProgress());
        informacoes.setPensao(Mascaras.stringToFloat(pensao.getText().toString()));
    }
}
