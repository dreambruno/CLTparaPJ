package com.dreambrunomsn.cltparapj.banco.informacoes;

import android.content.ContentValues;
import android.database.Cursor;

import com.dreambrunomsn.cltparapj.classes.Informacoes;

public class InformacoesAUX {

    private int id;
    private int codigoBeneficio;
    private int filho;

    private float salario;
    private float transporte;
    private float pensaoClt;
    private float pensaoPJ;
    private float contador;
    private float saude;
    private float proLabore;

    private String nome;

    public InformacoesAUX(Informacoes informacoes){
        this.id = informacoes.getId();
        this.codigoBeneficio = informacoes.getCodigoBeneficio();
        this.filho = informacoes.getFilho();

        this.salario = informacoes.getSalario();
        this.transporte = informacoes.getTransporte();
        this.pensaoClt = informacoes.getPensaoClt();
        this.pensaoPJ = informacoes.getPensaoPJ();
        this.contador = informacoes.getContador();
        this.saude = informacoes.getSaude();
        this.proLabore = informacoes.getProLabore();

        this.nome = informacoes.getNome();
    }

    public InformacoesAUX(Cursor cursor){
        this.id = cursor.getInt(cursor.getColumnIndex("id_informacoes"));
        this.codigoBeneficio = cursor.getInt(cursor.getColumnIndex("cod_beneficio"));
        this.filho = cursor.getInt(cursor.getColumnIndex("filho"));

        this.salario = cursor.getFloat(cursor.getColumnIndex("salario"));
        this.transporte = cursor.getFloat(cursor.getColumnIndex("transporte"));
        this.pensaoClt = cursor.getFloat(cursor.getColumnIndex("pensao_clt"));
        this.pensaoPJ = cursor.getFloat(cursor.getColumnIndex("pensao_pj"));
        this.contador = cursor.getFloat(cursor.getColumnIndex("salacontadorrio"));
        this.saude = cursor.getFloat(cursor.getColumnIndex("saude"));
        this.proLabore = cursor.getFloat(cursor.getColumnIndex("pro_labore"));

        this.nome = cursor.getString(cursor.getColumnIndex("nome"));
    }

    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_informacoes", id);
        contentValues.put("cod_beneficio", codigoBeneficio);
        contentValues.put("filho", filho);

        contentValues.put("salario", salario);
        contentValues.put("transporte", transporte);
        contentValues.put("pensao_clt", pensaoClt);
        contentValues.put("pensao_pj", pensaoPJ);
        contentValues.put("contador", contador);
        contentValues.put("saude", saude);
        contentValues.put("pro_labore", proLabore);

        contentValues.put("nome", nome);

        return contentValues;
    }

    // GETERES
    public int getId() {
        return id;
    }
    public int getCodigoBeneficio() {
        return codigoBeneficio;
    }
    public int getFilho() {
        return filho;
    }
    public float getSalario() {
        return salario;
    }
    public float getTransporte() {
        return transporte;
    }
    public float getPensaoClt() {
        return pensaoClt;
    }
    public float getPensaoPJ() {
        return pensaoPJ;
    }
    public float getContador() {
        return contador;
    }
    public float getSaude() {
        return saude;
    }
    public float getProLabore() {
        return proLabore;
    }
    public String getNome() {
        return nome;
    }
}
