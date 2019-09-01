package com.dreambrunomsn.cltparapj.classes;

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
    public void setValor(Float valor) {
        this.valor = valor;
    }

    public float getDesconto() {
        return desconto;
    }
    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }
}
