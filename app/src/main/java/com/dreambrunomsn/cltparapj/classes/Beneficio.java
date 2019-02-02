package com.dreambrunomsn.cltparapj.classes;

import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class Beneficio {

    private int cod;

    private String nome;
    private float valor;
    private float desconto;


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

    public String getValor() {
        return Mascaras.contabil(String.valueOf(valor));
    }
    public void setValor(String valor) {
        this.valor = Mascaras.stringToFloat(valor);
    }

    public String getDesconto() {
        return Mascaras.contabil(String.valueOf(desconto));
    }
    public void setDesconto(String desconto) {
        this.desconto = Mascaras.stringToFloat(desconto);
    }
}
