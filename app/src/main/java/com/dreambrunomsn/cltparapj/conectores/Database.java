package com.dreambrunomsn.cltparapj.conectores;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dreambrunomsn.cltparapj.banco.informacoes.InformacoesAUX;

public class Database extends SQLiteOpenHelper {

    private static final String BANCO_NOME = "CLTparaPJ2019";
    private static final int BANCO_VERSAO = 1;

    public Database(Context context) {
        super(context, BANCO_NOME, null, BANCO_VERSAO);
        Log.i("console", "Método Construtor Database, versao " + BANCO_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.i("console", "Método onCreate: INICIO");

        String INFORMACOES = "CREATE TABLE IF NOT EXISTS INFORMACOES (" +
                "    id_informacoes INTEGER         PRIMARY KEY," +
                "    cod_beneficio  INTEGER         DEFAULT 0," +
                "    filho          INTEGER         DEFAULT 0," +
                "    salario        DECIMAL         DEFAULT 0," +
                "    transporte     DECIMAL         DEFAULT 0," +
                "    pensao_clt     DECIMAL         DEFAULT 0," +
                "    pensao_pj      DECIMAL         DEFAULT 0," +
                "    contador       DECIMAL         DEFAULT 0," +
                "    saude          DECIMAL         DEFAULT 0," +
                "    pro_labore     DECIMAL         DEFAULT 0," +
                "    nome           VARCHAR (1, 30) DEFAULT 'save'" +
                ")";

        String BENEFICIO = "CREATE TABLE IF NOT EXISTS BENEFICIO (" +
                "    id_beneficio   INTEGER         PRIMARY KEY," +
                "    id_informacoes INTEGER         REFERENCES INFORMACOES (id_informacoes) NOT NULL," +
                "    nome           VARCHAR (1, 15) DEFAULT 'Outro'," +
                "    valor          DECIMAL         DEFAULT 0," +
                "    desconto       DECIMAL         DEFAULT 0" +
                ")";

        database.beginTransaction();
        try {
            database.execSQL(INFORMACOES);
            Log.i("console", "Método onCreate: informações criado");

            long id = database.insert("INFORMACOES", null, new InformacoesAUX().getContentValues());
            Log.i("console", "Método onCreate: base informações inserido id " + id);

            database.execSQL(BENEFICIO);
            Log.i("console", "Método onCreate: beneficios criado");

            database.setTransactionSuccessful();
        } catch (Exception ex){
            Log.e("console", "Método onCreate erro: " + ex.getMessage());
        } finally {
            database.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        Log.i("console", "Método onUpgrade: INICIO");
        try{
            database.delete("INFORMACOES", null, null);
            database.delete("BENEFICIO", null, null);
        } catch (Exception ex) {
            Log.e("console", "Método onUpgrade erro: " + ex.getMessage());
        }
    }
}
