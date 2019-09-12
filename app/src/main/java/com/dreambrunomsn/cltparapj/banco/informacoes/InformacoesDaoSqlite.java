package com.dreambrunomsn.cltparapj.banco.informacoes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.conectores.Database;

import java.util.List;

public class InformacoesDaoSqlite implements InformacoesDao {

    private SQLiteDatabase db;

    public InformacoesDaoSqlite(Context context){
        this.db = new Database(context).getWritableDatabase();
    }

    @Override
    public List<Informacoes> buscar() {
        Cursor cursor = db.query("INFORMACOES", null, null, null, null, null, "id_informacoes");

        while (cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex("id_informacoes"));
            Log.i("console", "Método InformacoesDaoSqlite.buscar: Id: " + id);
        }
        return null;
    }

    @Override
    public Informacoes buscar(int id) {
        return null;
    }

    @Override
    public long inserir(Informacoes informacoes) {
        return 0;
    }

    @Override
    public boolean atualizar(Informacoes informacoes) {
        return false;
    }

    @Override
    public boolean deletar(int id) {
        return false;
    }
}
