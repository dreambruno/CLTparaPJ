package com.dreambrunomsn.cltparapj.classes;

import com.dreambrunomsn.cltparapj.conectores.OnBeneficioChangeListener;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Informacoes {

    private static Informacoes ourInstance;

    private float salario;
    private float transporte;
    private float refeicao;

    private int codigo;

    private HashMap<Integer, Beneficio> beneficios;
    private OnBeneficioChangeListener onBeneficioChangeListener;

    // PRIVATE CONSTRUCTOR
    private Informacoes() {
        this.salario = 0;
        this.transporte = 0;
        this.refeicao = 0;
        this.codigo = 0;
        this.beneficios = new HashMap<Integer, Beneficio>();

        Beneficio bn = new Beneficio();
        bn.setCod(this.getCodigo());
        bn.setNome("Vale Refeição");
        bn.setValor("R$ 0,00");
        bn.setDesconto("R$ 0,00");
        this.beneficios.put(bn.getCod(), bn);
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
        if(onBeneficioChangeListener != null)
            onBeneficioChangeListener.onBeneficioChange();
    }

    public void removeBeneficio(int cod){
        this.beneficios.remove(cod);
        if(onBeneficioChangeListener != null)
            onBeneficioChangeListener.onBeneficioChange();
    }

    public void setOnbeneficioChangeListener(OnBeneficioChangeListener onbeneficioChangeListener){
        this.onBeneficioChangeListener = onbeneficioChangeListener;
    }


    // GETTERS AND SETTERS
    public int getCodigo(){
        return codigo++;
    }
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

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
}
