package com.dreambrunomsn.cltparapj.enums;

public enum INSS {

    FAIXA_1 (1751.81f, 0.08f),
    FAIXA_2 (2919.72f, 0.09f),
    FAIXA_3 (5839.45f, 0.11f),
    FAIXA_FIXA (642.34f, 0f);

    private float valor;
    private float imposto;

    INSS(float valor, float imposto) {
        this.valor = valor;
        this.imposto = imposto;
    }

    public float getValor() {
        return valor;
    }

    public float getImposto(){
        return imposto;
    }
}
