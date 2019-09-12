package com.dreambrunomsn.cltparapj.telas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.banco.informacoes.InformacoesDao;
import com.dreambrunomsn.cltparapj.banco.informacoes.InformacoesDaoSqlite;


public class BoasVindas extends AppCompatActivity {

    InformacoesDao informacoesDao;

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
                //List<ContentValues> lista = new DatabaseHelper(getBaseContext()).buscarEscala();

                Intent intent = new Intent(BoasVindas.this, Dashboard.class);
            /*
            if(lista != null) {
                if (lista.size() > 0) {
                    intent.putExtra("trabalho", lista.get(0).getAsInteger("trabalho"));
                    intent.putExtra("folga", lista.get(0).getAsInteger("folga"));
                    intent.putExtra("reuniao", (lista.get(0).getAsInteger("reuniao") == 1));
                    intent.putExtra("dia", lista.get(0).getAsInteger("dia"));
                    intent.putExtra("mes", lista.get(0).getAsInteger("mes"));
                    intent.putExtra("ano", lista.get(0).getAsInteger("ano"));
                }// fim if size
            }// fim if null
            */
                startActivity(intent);
                finish();
            }
        }, 2000);// fim do new Handler()
    }
}
