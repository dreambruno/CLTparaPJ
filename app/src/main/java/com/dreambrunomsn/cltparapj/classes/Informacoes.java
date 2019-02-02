package com.dreambrunomsn.cltparapj.classes;

import android.util.Log;

import com.dreambrunomsn.cltparapj.utils.Mascaras;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Informacoes {

    private static Informacoes ourInstance;

    private float salario;
    private float transporte;
    private float alimentacao;
    private float refeicao;

    private List<Beneficio> beneficios;

    // PRIVATE CONSTRUCTOR
    private Informacoes() {
        this.salario = 0;
        this.transporte = 0;
        this.alimentacao = 0;
        this.refeicao = 0;
        beneficios = new ArrayList<Beneficio>();
        /*
        Beneficio bn = new Beneficio();
        bn.setNome("Teste");
        bn.setValor("R$ 15,00");
        beneficios.add(bn);*/
    }

    public static Informacoes getInstance() {
        if(ourInstance == null){
            ourInstance = new Informacoes();
        }
        return ourInstance;
    }

    public String getInss() {
        float valor = this.salario * 0.09f;
        DecimalFormat df = new DecimalFormat("0.00");

        return Mascaras.contabil(String.valueOf(df.format(valor)));
    }

    public String getDescontoTransporte() {
        float valor = this.salario * 0.06f;
        if(valor > this.transporte){
            valor = this.transporte;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return Mascaras.contabil(String.valueOf(df.format(valor)));
    }

    public void addBeneficio(Beneficio beneficio){
        this.beneficios.add(beneficio);
    }


    // GETTERS AND SETTERS
    public float getSalario() {
        return salario;
    }
    public void setSalario(String salario) {
        this.salario = Mascaras.stringToFloat(salario);
    }

    public float getTransporte() {
        return transporte;
    }
    public void setTransporte(String transporte) {
        this.transporte = Mascaras.stringToFloat(transporte) * 22;
    }

    public float getRefeicao() {
        return refeicao;
    }
    public void setRefeicao(String refeicao) {
        this.refeicao = Mascaras.stringToFloat(refeicao);
    }

    public float getAlimentacao() {
        return alimentacao;
    }
    public void setAlimentacao(String alimentacao) {
        this.alimentacao = Mascaras.stringToFloat(alimentacao);
    }

    public List<Beneficio> getBeneficios() {
        return beneficios;
    }
    public void setBeneficios(List<Beneficio> beneficios) {
        this.beneficios = beneficios;
    }
}
