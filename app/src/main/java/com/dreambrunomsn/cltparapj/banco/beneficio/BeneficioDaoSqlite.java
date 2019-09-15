package com.dreambrunomsn.cltparapj.banco.beneficio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dreambrunomsn.cltparapj.classes.Beneficio;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.conectores.Database;

public class BeneficioDaoSqlite implements BeneficioDao {

    private final String TABELA = "BENEFICIO";

    private SQLiteDatabase database;
    private Informacoes informacoes;


    public BeneficioDaoSqlite(Context context){
        this.database = new Database(context).getWritableDatabase();
        this.informacoes = Informacoes.getInstance();
    }


    @Override
    public void buscar() {
        try {
            Cursor cursor = database.query(TABELA, null, null, null, null, null, BeneficioAUX.ID_BENEFICIO);
            if (cursor.moveToFirst()){
                do{
                    BeneficioAUX beneficio = new BeneficioAUX(cursor);
                    informacoes.addBeneficio(beneficio.getBeneficio());
                } while (cursor.moveToNext());
            }
        } catch (Exception ex){
            Log.e("console", "Erro no método BeneficioDaoSqlite.buscar(): " + ex);
        }
    }

    @Override
    public void atualizar() {
        database.beginTransaction();
        try{
            database.delete(TABELA, null, null);

            for (Beneficio beneficio : informacoes.getBeneficios()) {
                BeneficioAUX aux = new BeneficioAUX(beneficio);
                database.insert(TABELA, null, aux.getContentValues());
            }

            database.setTransactionSuccessful();
        } catch (Exception ex){
            Log.e("console", "Erro no método BeneficioDaoSqlite.atualizar(): " + ex);
        } finally {
            database.endTransaction();
        }
    }
}
