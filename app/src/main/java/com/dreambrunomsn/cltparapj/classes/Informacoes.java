package com.dreambrunomsn.cltparapj.classes;

import com.dreambrunomsn.cltparapj.conectores.OnInformacaoChangeListener;
import com.dreambrunomsn.cltparapj.enums.INSS;
import com.dreambrunomsn.cltparapj.enums.IRRF;
import com.dreambrunomsn.cltparapj.enums.SalarioMinimo;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

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
    private float pensaoClt;
    private float pensaoMei;

    private HashMap<Integer, Beneficio> beneficios;
    private OnInformacaoChangeListener onInformacaoChangeListener;

    /*private final float BASE_IRRF_0[] = {1903.98f, 0f, 0f};
    private final float BASE_IRRF_1[] = {2826.65f, 0.075f, 142.80f};
    private final float BASE_IRRF_2[] = {3751.05f, 0.15f, 354.80f};
    private final float BASE_IRRF_3[] = {4664.68f, 0.225f, 636.13f};
    private final float BASE_IRRF_4[] = {0f, 0.275f, 869.36f};
    private final float BASE_DEPENDENTE = 189.59f;*/

    // PRIVATE CONSTRUCTOR
    private Informacoes() {
        this.salario = 0;
        this.transporte = 0;
        this.codigo = 0;
        this.beneficios = new HashMap<Integer, Beneficio>();
        this.filho = 0;
        this.pensaoClt = 0;
        this.pensaoMei = 0;

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

    public String getInssFormatado() {
        return Mascaras.decimalDuasCasas(getInss(), true);
    }

    public String getIrrfFormatado(){
        return Mascaras.decimalDuasCasas(getIrrf(), true);
    }

    public String getDescontoTransporteFormatado() {
        return Mascaras.decimalDuasCasas(getDescontoTransporte(), true);
    }

    public String getPensaoCltFormatada(){
        return Mascaras.decimalDuasCasas(this.pensaoClt, false);
    }

    public String getPensaoMeiFormatada(){
        return Mascaras.decimalDuasCasas(this.pensaoMei, false);
    }

    public String getValorPensaoClt(){
        float valor = (this.salario - Mascaras.stringToFloat(this.getInssFormatado()))  * (this.pensaoClt / 100);

        return Mascaras.decimalDuasCasas(valor, true);
    }

    public String getValorPensaoMei(){
        float valor = SalarioMinimo.VALOR.getValor() * (this.pensaoMei / 100);

        return Mascaras.decimalDuasCasas(valor, true);
    }

    public void addBeneficio(Beneficio beneficio){
        this.beneficios.put(beneficio.getCod(), beneficio);
        if(onInformacaoChangeListener != null)
            onInformacaoChangeListener.onInformacaoChange();
    }

    public void removeBeneficio(int cod){
        this.beneficios.remove(cod);
        if(onInformacaoChangeListener != null)
            onInformacaoChangeListener.onInformacaoChange();
    }

    public void setOnbeneficioChangeListener(OnInformacaoChangeListener onbeneficioChangeListener){
        this.onInformacaoChangeListener = onbeneficioChangeListener;
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
        if(onInformacaoChangeListener != null)
            onInformacaoChangeListener.onInformacaoChange();
    }

    public float getPensaoClt(){
        return pensaoClt;
    }
    public void setPensaoClt(float pensaoClt){
        this.pensaoClt = pensaoClt;
        if(onInformacaoChangeListener != null)
            onInformacaoChangeListener.onInformacaoChange();
    }

    public float getPensaoMei(){
        return pensaoMei;
    }
    public void setPensaoMei(float pensaoMei){
        this.pensaoMei = pensaoMei;
        if(onInformacaoChangeListener != null)
            onInformacaoChangeListener.onInformacaoChange();
    }

    public float getSalario() {
        return salario;
    }
    public void setSalario(String salario) {
        this.salario = Mascaras.stringToFloat(salario);
        if(onInformacaoChangeListener != null)
            onInformacaoChangeListener.onInformacaoChange();
    }

    public float getTransporte() {
        return transporte;
    }
    public void setTransporte(String transporte) {
        this.transporte = Mascaras.stringToFloat(transporte) * 22;
        if(onInformacaoChangeListener != null)
            onInformacaoChangeListener.onInformacaoChange();
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

    public float getDescontoTransporte() {
        float valor = this.salario * 0.06f;
        if(valor > this.transporte){
            valor = this.transporte;
        }

        return valor;
    }

    public float getInss() {
        float imposto;
        float valor;

        // Primeiro calcular faixa de imposto.
        if(this.salario <= INSS.FAIXA_1.getValor()){
            imposto = INSS.FAIXA_1.getImposto();
        } else if(this.salario <= INSS.FAIXA_2.getValor()){
            imposto = INSS.FAIXA_2.getImposto();
        } else {
            imposto = INSS.FAIXA_3.getImposto();
        }

        // Depois calcular e retornar  o valor em R$ do imposto.
        if(this.salario > INSS.FAIXA_3.getValor()) {
            valor = INSS.FAIXA_FIXA.getValor();
        } else {
            valor = this.salario * imposto;
        }

        return valor;
    }

    public float getIrrf(){
        float valor = this.salario - Mascaras.stringToFloat(this.getInssFormatado());
        valor -= filho * IRRF.BASE_DEPENDENTE;
        valor -= this.salario * (this.pensaoClt / 100);

        IRRF irrf = IRRF.getIRRF(valor);

        valor = ( valor * irrf.getImposto() ) - irrf.getAlicota();

        /*if(valor <= IRRF.BASE_0.getSalario()){
            valor = 0;
        } else if(valor <= BASE_IRRF_1[0]){
            valor = valor * BASE_IRRF_1[1] - BASE_IRRF_1[2];
        } else if(valor <= BASE_IRRF_2[0]){
            valor = valor * BASE_IRRF_2[1] - BASE_IRRF_2[2];
        } else if(valor <= BASE_IRRF_3[0]){
            valor = valor * BASE_IRRF_3[1] - BASE_IRRF_3[2];
        } else {
            valor = valor * BASE_IRRF_4[1] - BASE_IRRF_4[2];
        }*/

        return valor;
    }
}
