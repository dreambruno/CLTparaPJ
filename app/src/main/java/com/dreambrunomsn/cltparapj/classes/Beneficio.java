package com.dreambrunomsn.cltparapj.classes;

import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class Beneficio {

    private int cod;

    private String nome;
    private float valor;
    private float desconto;

    // CONSTRUCTOR
    public Beneficio() {
        this.cod = 0;
        this.nome = "";
        this.valor = 0;
        this.desconto = 0;
    }

    public String getValorFormatado() {
        return Mascaras.contabil(String.valueOf(valor));
    }

    public String getDescontoFormatado() {
        return Mascaras.contabil(String.valueOf(desconto));
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
