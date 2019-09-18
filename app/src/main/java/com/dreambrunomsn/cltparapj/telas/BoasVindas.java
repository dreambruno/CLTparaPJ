package com.dreambrunomsn.cltparapj.telas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.banco.informacoes.InformacoesDao;
import com.dreambrunomsn.cltparapj.banco.informacoes.InformacoesDaoSqlite;


public class BoasVindas extends AppCompatActivity {

    private InformacoesDao informacoesDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boas_vindas);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().hide();

        informacoesDao = new InformacoesDaoSqlite(getBaseContext());

        init();
    }

    private void init(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                // Pegando dados do banco
                informacoesDao.buscar();

                Intent intent = new Intent(BoasVindas.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        }, 2000);// fim do new Handler()
    }
}
