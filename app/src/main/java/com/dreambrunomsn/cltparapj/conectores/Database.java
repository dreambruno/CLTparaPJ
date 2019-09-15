package com.dreambrunomsn.cltparapj.conectores;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    private static final String BANCO_NOME = "CLTparaPJ2019";
    private static final int BANCO_VERSAO = 1;

    public Database(Context context) {
        super(context, BANCO_NOME, null, BANCO_VERSAO);
        Log.i("console", "Método Construtor Database, versao " + BANCO_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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

        db.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id_informacoes", "1");

            db.execSQL(INFORMACOES);
            db.insert("INFORMACOES", null, contentValues);
            Log.i("console", "Método onCreate: informações criado");

            db.execSQL(BENEFICIO);
            db.insert("BENEFICIO", null, contentValues);
            Log.i("console", "Método onCreate: beneficios criado");

            db.setTransactionSuccessful();
        } catch (Exception ex){
            Log.e("console", "Método onCreate erro: " + ex.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("console", "Método onUpgrade: INICIO");
    }
}
