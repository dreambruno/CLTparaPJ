package com.dreambrunomsn.cltparapj.banco.informacoes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dreambrunomsn.cltparapj.banco.beneficio.BeneficioDao;
import com.dreambrunomsn.cltparapj.banco.beneficio.BeneficioDaoSqlite;
import com.dreambrunomsn.cltparapj.conectores.Database;

public class InformacoesDaoSqlite implements InformacoesDao {

    private final String TABELA = "INFORMACOES";

    private final String WHERE_ID = "id_informacoes = 1";

    private SQLiteDatabase database;

    private BeneficioDao beneficioDao;

    public InformacoesDaoSqlite(Context context){
        this.database = new Database(context).getWritableDatabase();
        beneficioDao = new BeneficioDaoSqlite(context);
    }

    @Override
    public void buscar() {
        try {
            Cursor cursor = database.query(TABELA, null, WHERE_ID, null, null, null, null);

            if (cursor.moveToFirst()) {
                new InformacoesAUX(cursor);
                beneficioDao.buscar();
            }
        } catch (Exception ex){
            Log.e("console", "Erro no método InformacoesDaoSqlite.buscar(): " + ex);
        }
    }

    @Override
    public boolean atualizar() {
        try {
            database.update(TABELA, new InformacoesAUX().getContentValues(), WHERE_ID, null);
            beneficioDao.atualizar();

            return true;
        }catch (Exception ex){
            Log.e("console", "Erro no método InformacoesDaoSqlite.atualizar(): " + ex);
            return false;
        }
    }
}
