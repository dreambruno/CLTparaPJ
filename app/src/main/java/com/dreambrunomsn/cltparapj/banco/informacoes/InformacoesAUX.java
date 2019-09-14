package com.dreambrunomsn.cltparapj.banco.informacoes;

import android.content.ContentValues;
import android.database.Cursor;

import com.dreambrunomsn.cltparapj.classes.Informacoes;

public class InformacoesAUX {

    // Mapeando as colunas (saudade do spring)
    private final String ID_INFORMACOES = "id_informacoes";
    private final String COD_BENEFICIO = "cod_beneficio";
    private final String FILHO = "filho";

    private final String SALARIO = "salario";
    private final String TRANSPORTE = "transporte";
    private final String PENSAO_CLT = "pensao_clt";
    private final String PENSAO_PJ = "pensao_pj";
    private final String CONTADOR = "contador";
    private final String SAUDE = "saude";
    private final String PRO_LABORE = "pro_labore";

    private final String NOME = "nome";


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

    // CONSTRUTORES
    public InformacoesAUX(){
        Informacoes informacoes = Informacoes.getInstance();

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
        this.id = cursor.getInt(cursor.getColumnIndex(ID_INFORMACOES));
        this.codigoBeneficio = cursor.getInt(cursor.getColumnIndex(COD_BENEFICIO));
        this.filho = cursor.getInt(cursor.getColumnIndex(FILHO));

        this.salario = cursor.getFloat(cursor.getColumnIndex(SALARIO));
        this.transporte = cursor.getFloat(cursor.getColumnIndex(TRANSPORTE));
        this.pensaoClt = cursor.getFloat(cursor.getColumnIndex(PENSAO_CLT));
        this.pensaoPJ = cursor.getFloat(cursor.getColumnIndex(PENSAO_PJ));
        this.contador = cursor.getFloat(cursor.getColumnIndex(CONTADOR));
        this.saude = cursor.getFloat(cursor.getColumnIndex(SAUDE));
        this.proLabore = cursor.getFloat(cursor.getColumnIndex(PRO_LABORE));

        this.nome = cursor.getString(cursor.getColumnIndex(NOME));
    }

    // MÉTODOS
    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_INFORMACOES, id);
        contentValues.put(COD_BENEFICIO, codigoBeneficio);
        contentValues.put(FILHO, filho);

        contentValues.put(SALARIO, salario);
        contentValues.put(TRANSPORTE, transporte);
        contentValues.put(PENSAO_CLT, pensaoClt);
        contentValues.put(PENSAO_PJ, pensaoPJ);
        contentValues.put(CONTADOR, contador);
        contentValues.put(SAUDE, saude);
        contentValues.put(PRO_LABORE, proLabore);

        contentValues.put(NOME, nome);

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
