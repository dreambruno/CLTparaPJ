package com.dreambrunomsn.cltparapj.enums;

public enum FGTS {
    TAXA(0.08f),
    RESCISAO(0.4f);

    private Float valor;

    FGTS(Float valor) {
        this.valor = valor;
    }

    public Float getValor(){
        return this.valor;
    }

    public static Float getRecisao(Float valor) {
        return (valor * TAXA.getValor()) * RESCISAO.getValor();
    }
}
