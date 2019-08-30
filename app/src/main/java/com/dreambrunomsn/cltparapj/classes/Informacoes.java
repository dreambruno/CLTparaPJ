package com.dreambrunomsn.cltparapj.classes;

import com.dreambrunomsn.cltparapj.conectores.OnInformacaoChangeListener;
import com.dreambrunomsn.cltparapj.enums.INSS;
import com.dreambrunomsn.cltparapj.enums.IRRF;
import com.dreambrunomsn.cltparapj.enums.InformacoesAdicionais;
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

    // PRIVATE CONSTRUCTOR
    private Informacoes() {
        this.salario = 0;
        this.transporte = 0;
        this.codigo = 0;
        this.beneficios = new HashMap<>();
        this.filho = 0;
        this.pensaoClt = 0;
        this.pensaoMei = 0;

        Beneficio bn = new Beneficio();
        bn.setCod(this.getCodigo());
        bn.setNome("Vale Refeição");
        bn.setValor(0f);
        bn.setDesconto(0f);
        this.beneficios.put(bn.getCod(), bn);
    }

    public static Informacoes getInstance() {
        if(ourInstance == null){
            ourInstance = new Informacoes();
        }
        return ourInstance;
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

    public void setOnbeneficioChangeListener(OnInformacaoChangeListener onInformacaoChange){
        this.onInformacaoChangeListener = onInformacaoChange;
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
    public void setSalario(Float salario) {
        this.salario = salario;
        if(onInformacaoChangeListener != null)
            onInformacaoChangeListener.onInformacaoChange();
    }

    /**
     * Valor mensal gasto com transporte
     * @return valor imputado x quantidade de dias do mês
     */
    public float getTransporte() {
        return transporte;
    }
    public void setTransporte(String transporte) {
        this.transporte = Mascaras.stringToFloat(transporte) * InformacoesAdicionais.DIAS_NO_MES.getValor();
        if(onInformacaoChangeListener != null)
            onInformacaoChangeListener.onInformacaoChange();
    }

    /**
     * Retorna uma lista de beneficios ordenada pela chave.
     * @return List<Beneficio>
     */
    public List<Beneficio> getBeneficios() {

        List<Integer> keys = new ArrayList<>(beneficios.keySet());

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

        List<Beneficio> lista = new ArrayList<>();
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
        float valor = this.salario * InformacoesAdicionais.DESCONTO_TRANSPORTE.getValor();
        if(valor > this.transporte){
            valor = this.transporte;
        }

        return valor;
    }

    public float getInss(Float salario) {
        float imposto;

        // Primeiro calcular faixa de imposto.
        if(salario <= INSS.FAIXA_1.getValor()){
            imposto = INSS.FAIXA_1.getImposto();
        } else if(salario <= INSS.FAIXA_2.getValor()){
            imposto = INSS.FAIXA_2.getImposto();
        } else {
            imposto = INSS.FAIXA_3.getImposto();
        }

        // Depois calcular e retornar  o valor em R$ do imposto.
        if(salario > INSS.FAIXA_3.getValor()) {
            return INSS.FAIXA_FIXA.getValor();
        } else {
            return salario * imposto;
        }
    }

    public float getPensaoCLTValor(Float salario){
        float valor = salario - this.getInss(salario);

        return valor * (this.pensaoClt / 100);
    }

    public float getPensaoMeiValor(){

        return InformacoesAdicionais.SALARIO_MINIMO.getValor() * (this.pensaoMei / 100);
    }

    public float getIrrf(Float salario){
        float valorBase = salario - this.getInss(salario);
        valorBase -= filho * IRRF.BASE_DEPENDENTE;
        valorBase -= this.getPensaoCLTValor(salario);

        IRRF irrf = IRRF.getIRRF(valorBase);

        return ( valorBase * irrf.getTaxa() ) - irrf.getAlicota();
    }
}
