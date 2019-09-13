package com.dreambrunomsn.cltparapj.banco.informacoes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.conectores.Database;

import java.util.ArrayList;
import java.util.List;

public class InformacoesDaoSqlite implements InformacoesDao {

    private final String TABELA = "INFORMACOES";

    private final String WHERE_ID = "id_informacoes = ?";

    private SQLiteDatabase database;

    public InformacoesDaoSqlite(Context context){
        this.database = new Database(context).getWritableDatabase();
    }

    @Override
    public List<Informacoes> buscar() {

        List<Informacoes> lista = new ArrayList<>();

        Cursor cursor = database.query(TABELA, null, null, null, null, null, "id_informacoes");

        while (cursor.moveToFirst()){
            lista.add(new Informacoes(new InformacoesAUX(cursor)));
        }

        return lista;
    }

    @Override
    public Informacoes buscar(int id) {

        Cursor cursor = database.query(TABELA, null, WHERE_ID, new String[]{String.valueOf(id)}, null, null, "id_informacoes");

        if (cursor.moveToFirst()){
            return new Informacoes(new InformacoesAUX(cursor));
        }
        return null;
    }

    @Override
    public long inserir(Informacoes informacoes) {

        return database.insert(TABELA, null, new InformacoesAUX(informacoes).getContentValues());
    }

    @Override
    public boolean atualizar(Informacoes informacoes) {

        return database.update(TABELA, new InformacoesAUX(informacoes).getContentValues(), WHERE_ID, new String[]{String.valueOf(informacoes.getId())}) > 0;
    }

    @Override
    public boolean deletar(int id) {

        return database.delete(TABELA, WHERE_ID, new String[]{String.valueOf(id)}) > 0;
    }
}
