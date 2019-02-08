package com.dreambrunomsn.cltparapj.classes;

import android.widget.NumberPicker;

import com.dreambrunomsn.cltparapj.utils.Mascaras;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Informacoes {

    private static Informacoes ourInstance;

    private float salario;
    private float transporte;
    private float refeicao;

    private HashMap<Integer, Beneficio> beneficios;
    private OnBeneficiosChangeListener onBeneficiosChangeListener;

    // PRIVATE CONSTRUCTOR
    private Informacoes() {
        this.salario = 0;
        this.transporte = 0;
        this.refeicao = 0;
        beneficios = new HashMap<Integer, Beneficio>();

        Beneficio bn = new Beneficio();
        bn.setCod(0);
        bn.setNome("Vale Refeição");
        bn.setValor("R$ 0,00");
        bn.setDesconto("R$ 0,00");
        beneficios.put(0, bn);
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
        this.beneficios.put(beneficio.getCod(), beneficio);
        if(onBeneficiosChangeListener != null)
            onBeneficiosChangeListener.onBeneficiosChange();
    }

    public void removeBeneficio(int cod){
        this.beneficios.remove(cod);
        if(onBeneficiosChangeListener != null)
            onBeneficiosChangeListener.onBeneficiosChange();
    }

    public OnBeneficiosChangeListener getOnBeneficiosChangeListener() {
        return onBeneficiosChangeListener;
    }

    public void setOnbeneficioChangeListener(OnBeneficiosChangeListener onbeneficioChangeListener){
        this.onBeneficiosChangeListener = onbeneficioChangeListener;
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

    public HashMap<Integer, Beneficio> getBeneficios() {
        return beneficios;
    }
    public Beneficio getBeneficios(int key){
        if(beneficios.containsKey(key))
            return beneficios.get(key);
        return null;
    }
    public void setBeneficios(HashMap<Integer, Beneficio> beneficios) {
        this.beneficios = beneficios;
    }

    public interface OnBeneficiosChangeListener {
        void onBeneficiosChange();
    }
}
