package com.dreambrunomsn.cltparapj.classes;

import com.dreambrunomsn.cltparapj.utils.Mascaras;

import java.text.DecimalFormat;

public class Beneficio {

    public static final int REFEICAO = 0;
    private int cod;

    private String nome;
    private float valor;
    private float desconto;

    // CONSTRUCTOR
    public Beneficio() {
        this.cod = -1;
        this.nome = "";
        this.valor = 0;
        this.desconto = 0;
    }

    public String getValorFormatado() {
        DecimalFormat df = new DecimalFormat("0.00");
        return Mascaras.contabil(String.valueOf(df.format(valor)));
    }

    public String getDescontoFormatado() {
        DecimalFormat df = new DecimalFormat("0.00");
        return Mascaras.contabil(String.valueOf(df.format(desconto)));
    }

    // GETTERS AND SETTERS
    public int getCod(){
        return cod;
    }
    public void setCod(int cod){
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = Mascaras.stringToFloat(valor);
    }

    public float getDesconto() {
        return desconto;
    }
    public void setDesconto(String desconto) {
        this.desconto = Mascaras.stringToFloat(desconto);
    }
}
