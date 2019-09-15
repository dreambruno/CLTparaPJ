package com.dreambrunomsn.cltparapj.banco.beneficio;

import android.content.ContentValues;
import android.database.Cursor;

import com.dreambrunomsn.cltparapj.classes.Beneficio;
import com.dreambrunomsn.cltparapj.classes.Informacoes;


public class BeneficioAUX {

    // Mapeando as colunas (saudade do spring)
    public static final String ID_BENEFICIO = "id_beneficio";
    private final String ID_INFORMACOES = "id_informacoes";
    private final String NOME = "nome";
    private final String VALOR = "valor";
    private final String DESCONTO = "desconto";

    // VARIAVEIS
    private int idBeneficio;
    private int idInformacoes;
    private float valor;
    private float desconto;
    private String nome;

    // CONSTRUTORES
    public BeneficioAUX(Beneficio beneficio){
        Informacoes informacoes = Informacoes.getInstance();

        this.idBeneficio = beneficio.getCod();
        this.idInformacoes = informacoes.getId();
        this.valor = beneficio.getValor();
        this.desconto = beneficio.getDesconto();
        this.nome = beneficio.getNome();
    }

    public BeneficioAUX(Cursor cursor){
        this.idBeneficio = cursor.getInt(cursor.getColumnIndex(ID_BENEFICIO));
        this.idInformacoes = cursor.getInt(cursor.getColumnIndex(ID_INFORMACOES));
        this.valor = cursor.getFloat(cursor.getColumnIndex(VALOR));
        this.desconto = cursor.getFloat(cursor.getColumnIndex(DESCONTO));
        this.nome = cursor.getString(cursor.getColumnIndex(NOME));
    }

    // MÉTODOS
    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_BENEFICIO, idBeneficio);
        contentValues.put(ID_INFORMACOES, idInformacoes);
        contentValues.put(NOME, nome);
        contentValues.put(VALOR, valor);
        contentValues.put(DESCONTO, desconto);

        return contentValues;
    }

    public Beneficio getBeneficio(){
        Beneficio beneficio = new Beneficio();

        beneficio.setCod(idBeneficio);
        beneficio.setValor(valor);
        beneficio.setDesconto(desconto);
        beneficio.setNome(nome);

        return beneficio;
    }
}
