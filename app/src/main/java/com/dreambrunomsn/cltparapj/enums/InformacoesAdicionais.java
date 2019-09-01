package com.dreambrunomsn.cltparapj.enums;

public enum InformacoesAdicionais {
    // Salário de 2019
    SALARIO_MINIMO(998.00f),
    DESCONTO_TRANSPORTE(0.06f),
    DIAS_NO_MES(22),
    MESES_NO_ANO(12),
    ANO_SEM_FERIAS(11),
    SIMPLES(0.06f);

    private float valor;

    InformacoesAdicionais(float valor) {
        this.valor = valor;
    }

    public float getValor(){
        return valor;
    }
}
