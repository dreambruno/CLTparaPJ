package com.dreambrunomsn.cltparapj.banco.informacoes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.conectores.Database;

public class InformacoesDaoSqlite implements InformacoesDao {

    private final String TABELA = "INFORMACOES";

    private final String WHERE_ID = "id_informacoes = ?";

    private SQLiteDatabase database;

    public InformacoesDaoSqlite(Context context){
        this.database = new Database(context).getWritableDatabase();
    }

    @Override
    public void buscar(int id) {
        try {
            Cursor cursor = database.query(TABELA, null, WHERE_ID, new String[]{String.valueOf(id)}, null, null, "id_informacoes");

            if (cursor.moveToFirst()) {
                Informacoes informacoes = Informacoes.getInstance();
                informacoes.setInformacoes(new InformacoesAUX(cursor));
            }
        } catch (Exception ex){
            Log.e("console", "Erro no método InformacoesDaoSqlite.buscar(): " + ex);
        }
    }

    @Override
    public boolean atualizar(Informacoes informacoes) {
        try {
            return database.update(TABELA, new InformacoesAUX().getContentValues(), WHERE_ID, new String[]{String.valueOf(informacoes.getId())}) > 0;
        }catch (Exception ex){
            Log.e("console", "Erro no método InformacoesDaoSqlite.atualizar(): " + ex);
            return false;
        }
    }
}
