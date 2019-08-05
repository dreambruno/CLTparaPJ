package com.dreambrunomsn.cltparapj.enums;

public enum IRRF {

    BASE_0 (1903.98f, 0f, 0f),
    BASE_1 (2826.65f, 0.075f, 142.80f),
    BASE_2 (3751.05f, 0.15f, 354.80f),
    BASE_3 (4664.68f, 0.225f, 636.13f),
    BASE_4 (0f, 0.275f, 869.36f);

    public static final float BASE_DEPENDENTE = 189.59f;

    private float salario;
    private float imposto;
    private float alicota;

    IRRF(float salario, float imposto, float alicota) {
        this.salario = salario;
        this.imposto = imposto;
        this.alicota = alicota;
    }

    public float getSalario() {
        return salario;
    }

    public float getImposto() {
        return imposto;
    }

    public float getAlicota() {
        return alicota;
    }

    public static IRRF getIRRF(float valor){

        if(valor <= IRRF.BASE_0.getSalario()){
            return IRRF.BASE_0;
        } else if(valor <= IRRF.BASE_1.getSalario()){
            return IRRF.BASE_1;
        } else if(valor <= IRRF.BASE_2.getSalario()){
            return IRRF.BASE_2;
        } else if(valor <= IRRF.BASE_3.getSalario()){
            return IRRF.BASE_3;
        } else {
            return IRRF.BASE_4;
        }
    }
}
