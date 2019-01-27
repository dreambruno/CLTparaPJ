package com.dreambrunomsn.cltparapj.classes;

import android.util.Log;

import com.dreambrunomsn.cltparapj.utils.Mascaras;

import java.text.DecimalFormat;

public class Informacoes {

    private static Informacoes ourInstance;

    private float salario;
    private float transporte;
    private float alimentacao;
    private float refeicao;

    // PRIVATE CONSTRUCTOR
    private Informacoes() {
        this.salario = 0;
    }

    public static Informacoes getInstance() {
        if(ourInstance == null){
            ourInstance = new Informacoes();
        }
        return ourInstance;
    }

    private float stringToFloat(String valor){
        valor = valor.replaceAll("[^0-9,]", "");
        valor = valor.replace(",", ".");
        try {
            return Float.parseFloat(valor);
        }catch (Exception ex){
            Log.e("console", "Informacoes.setSalario(): " + ex);
            return 0;
        }
    }

    public String getInss() {
        float valor = this.salario * 0.09f;
        DecimalFormat df = new DecimalFormat("0.00");

        return Mascaras.contabil(String.valueOf(df.format(valor)));
    }

    // GETTERS AND SETTERS
    public float getSalario() {
        return salario;
    }
    public void setSalario(String salario) {
        this.salario = this.stringToFloat(salario);
    }

    public String getTransporte() {
        float valor = this.salario * 0.06f;
        if(valor > this.transporte){
            valor = this.transporte;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return Mascaras.contabil(String.valueOf(df.format(valor)));
    }
    public void setTransporte(String transporte) {
        this.transporte = this.stringToFloat(transporte) * 22;
    }

    public float getRefeicao() {
        return refeicao;
    }
    public void setRefeicao(String refeicao) {
        this.refeicao = this.stringToFloat(refeicao);
    }

    public float getAlimentacao() {
        return alimentacao;
    }
    public void setAlimentacao(String alimentacao) {
        this.alimentacao = this.stringToFloat(alimentacao);
    }
}
