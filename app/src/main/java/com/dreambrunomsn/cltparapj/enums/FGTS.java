package com.dreambrunomsn.cltparapj.enums;

public enum FGTS {
    TAXA(0.08f),
    RESCISAO(0.4f),
    JUROS(0.03f),
    TR(0f); //2019

    private Float valor;

    FGTS(Float valor) {
        this.valor = valor;
    }

    public Float getValor(){
        return this.valor;
    }

    public static Float getRecisao(Float salario) {
        Float fgts = salario * TAXA.getValor();
        Float juros = (JUROS.getValor() + TR.getValor()) / InformacoesAdicionais.MESES_NO_ANO.getValor();

        fgts += fgts * juros;

        return fgts  * RESCISAO.getValor();
    }
}
