package com.dreambrunomsn.cltparapj.enums;

public enum SalarioMinimo {
    // Salário de 2019
    VALOR (998.00f);

    private float valor;

    SalarioMinimo(float valor) {
        this.valor = valor;
    }

    public float getValor(){
        return valor;
    }
}
