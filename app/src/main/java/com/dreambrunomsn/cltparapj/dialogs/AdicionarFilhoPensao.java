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

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class AdicionarFilhoPensao extends Dialog implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    private Button salvar;

    private SeekBar filho;
    private EditText pensaoClt;
    private EditText pensaoMei;
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

        pensaoClt = (EditText)findViewById(R.id.fpPensaoClt);
        Mascaras.listener(pensaoClt, Mascaras.PENSAO, null, null);

        pensaoMei = (EditText)findViewById(R.id.fpPensaoMei);
        Mascaras.listener(pensaoMei, Mascaras.PENSAO, null, null);

        salvar = (Button)findViewById(R.id.fpSalvar);
        salvar.setOnClickListener(this);

        mostrador.setText(String.valueOf(informacoes.getFilho()));
        filho.setProgress(informacoes.getFilho());
        pensaoClt.setText(Mascaras.decimalDuasCasas(informacoes.getPensaoClt(), false));
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
        informacoes.setPensaoClt(Mascaras.stringToFloat(pensaoClt.getText().toString()));
        informacoes.setPensaoMei(Mascaras.stringToFloat(pensaoMei.getText().toString()));
    }
}
