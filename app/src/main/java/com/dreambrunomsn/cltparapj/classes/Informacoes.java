package com.dreambrunomsn.cltparapj.classes;

import com.dreambrunomsn.cltparapj.utils.Mascaras;

import java.text.DecimalFormat;

public class Informacoes {
    private static Informacoes ourInstance;

    private float salario;
    private float inss;

    public static Informacoes getInstance() {
        if(ourInstance == null){
            ourInstance = new Informacoes();
        }
        return ourInstance;
    }

    // PRIVATE CONSTRUCTOR
    private Informacoes() {
        this.salario = 0;
        this.inss = 0;
    }

    private float stringToFloat(String valor){
        valor = valor.replaceAll("[^0-9,]", "");
        valor = valor.replace(",", ".");
        try {
            return Float.parseFloat(valor);
        }catch (Exception ex){
            System.out.println("Erro Informacoes.setSalario() " + ex);
            return 0;
        }
    }

    // GETTERS AND SETTERS
    public float getSalario() {
        return salario;
    }
    public void setSalario(String salario) {
        float valor = stringToFloat(salario);
        this.salario = valor;
        this.setInss(valor);
    }

    public String getInss() {
        DecimalFormat df = new DecimalFormat("0.00");
        return Mascaras.contabil(String.valueOf(df.format(this.inss)));
    }
    private void setInss(float inss) {
        inss = inss * 0.09f;
        this.inss = inss;
    }
}
