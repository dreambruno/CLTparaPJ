package com.dreambrunomsn.cltparapj.classes;

import com.dreambrunomsn.cltparapj.conectores.OnBeneficioChangeListener;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Informacoes {

    private static Informacoes ourInstance;

    private int codigo;
    private int filho;

    private float salario;
    private float transporte;
    private float refeicao;
    private float pensao;

    private HashMap<Integer, Beneficio> beneficios;
    private OnBeneficioChangeListener onBeneficioChangeListener;

    private final float FAIXA_INSS_1 = 1751.81f;
    private final float FAIXA_INSS_2 = 2919.72f;
    private final float FAIXA_INSS_3 = 5839.45f;
    private final float FAIXA_FIXA = 642.34f;

    private final float BASE_IRRF_0[] = {1903.98f, 0f, 0f};
    private final float BASE_IRRF_1[] = {2826.65f, 0.075f, 142.80f};
    private final float BASE_IRRF_2[] = {3751.05f, 0.15f, 354.80f};
    private final float BASE_IRRF_3[] = {4664.68f, 0.225f, 636.13f};
    private final float BASE_IRRF_4[] = {0f, 0.275f, 869.36f};
    private final float BASE_DEPENDENTE = 189.59f;

    // PRIVATE CONSTRUCTOR
    private Informacoes() {
        this.salario = 0;
        this.transporte = 0;
        this.refeicao = 0;
        this.codigo = 0;
        this.beneficios = new HashMap<Integer, Beneficio>();
        this.filho = 0;
        this.pensao = 0;

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
        float imposto;
        float valor;

        if(this.salario <= FAIXA_INSS_1){
            imposto = 0.08f;
        } else if(this.salario <= FAIXA_INSS_2){
            imposto = 0.09f;
        } else {
            imposto = 0.11f;
        }

        if(this.salario > FAIXA_INSS_3) {
            valor = FAIXA_FIXA;
        } else {
            valor = this.salario * imposto;
        }

        DecimalFormat df = new DecimalFormat("0.00");
        return Mascaras.contabil(String.valueOf(df.format(valor)));
    }

    public String getIrrf(){
        float base = this.salario - Mascaras.stringToFloat(this.getInss());
        base -= filho * BASE_DEPENDENTE;
        base -= pensao;

        if(base <= BASE_IRRF_0[0]){
            base = 0;
        } else if(base <= BASE_IRRF_1[0]){
            base = base * BASE_IRRF_1[1] - BASE_IRRF_1[2];
        } else if(base <= BASE_IRRF_2[0]){
            base = base * BASE_IRRF_2[1] - BASE_IRRF_2[2];
        } else if(base <= BASE_IRRF_3[0]){
            base = base * BASE_IRRF_3[1] - BASE_IRRF_3[2];
        } else {
            base = base * BASE_IRRF_4[1] - BASE_IRRF_4[2];
        }

        DecimalFormat df = new DecimalFormat("0.00");
        return Mascaras.contabil(String.valueOf(df.format(base)));
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

    public int getFilho() {
        return filho;
    }
    public void setFilho(int filho) {
        this.filho = filho;
    }

    public float getPensao(){
        return pensao;
    }
    public void setPensao(float pensao){
        this.pensao = pensao;
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

    public List<Beneficio> getBeneficios() {

        List<Integer> keys = new ArrayList<Integer>(beneficios.keySet());
        Collections.sort(keys, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                if (x > y){
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        List<Beneficio> lista = new ArrayList<Beneficio>();
        for (Integer cod : keys) {
            lista.add(beneficios.get(cod));
        }
        return lista;
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
