package com.dreambrunomsn.cltparapj.classes;

import com.dreambrunomsn.cltparapj.banco.informacoes.InformacoesAUX;
import com.dreambrunomsn.cltparapj.conectores.OnInformacaoChangeListener;
import com.dreambrunomsn.cltparapj.enums.FGTS;
import com.dreambrunomsn.cltparapj.enums.INSS;
import com.dreambrunomsn.cltparapj.enums.IRRF;
import com.dreambrunomsn.cltparapj.enums.InformacoesAdicionais;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Informacoes {

    private static Informacoes ourInstance;

    private int id;
    private int codigoBeneficio;
    private int filho;

    private float salario;
    private float transporte;
    private float pensaoClt;
    private float pensaoPJ;

    private float contador;
    private float saude;
    private float proLabore;

    private String nome;

    private HashMap<Integer, Beneficio> beneficios;
    private OnInformacaoChangeListener onCLTChangeListener;
    private OnInformacaoChangeListener onPJChangeListener;

    // PRIVATE CONSTRUCTOR
    private Informacoes() {
        this.id = 0;
        this.salario = 0;
        this.transporte = 0;
        this.codigoBeneficio = 0;
        this.beneficios = new HashMap<>();
        this.filho = 0;
        this.pensaoClt = 0;
        this.pensaoPJ = 0;
        this.contador = 1000;
        this.saude = 600;
        this.proLabore = 28;
        this.nome = "";

        Beneficio bn = new Beneficio();
        bn.setCod(this.getCodigoBeneficio());
        bn.setNome("Vale Refeição");
        bn.setValor(0f);
        bn.setDesconto(0f);
        this.beneficios.put(bn.getCod(), bn);
    }

    public Informacoes(InformacoesAUX informacoesAUX) {
        this.id = informacoesAUX.getId();
        this.salario = informacoesAUX.getSalario();
        this.transporte = informacoesAUX.getTransporte();

        this.codigoBeneficio = informacoesAUX.getCodigoBeneficio();
        this.filho = informacoesAUX.getFilho();
        this.pensaoClt = informacoesAUX.getPensaoClt();
        this.pensaoPJ = informacoesAUX.getPensaoPJ();
        this.contador = informacoesAUX.getContador();
        this.saude = informacoesAUX.getSaude();
        this.proLabore = informacoesAUX.getProLabore();

        this.nome = informacoesAUX.getNome();




        this.beneficios = new HashMap<>();

        Beneficio bn = new Beneficio();
        bn.setCod(this.getCodigoBeneficio());
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
        if(onCLTChangeListener != null)
            onCLTChangeListener.onInformacaoChange();
    }

    public void removeBeneficio(int cod){
        this.beneficios.remove(cod);
        if(onCLTChangeListener != null)
            onCLTChangeListener.onInformacaoChange();
    }

    public void setOnCLTChangeListener(OnInformacaoChangeListener onInformacaoChange){
        this.onCLTChangeListener = onInformacaoChange;
    }

    public void setOnPJChangeListener(OnInformacaoChangeListener onPJChangeListener){
        this.onPJChangeListener = onPJChangeListener;
    }


    // GETTERS AND SETTERS
    public int getId(){
        return id;
    }
    public void setId( int id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public int getCodigoBeneficio(){
        return codigoBeneficio++;
    }
    public void setCodigoBeneficio(int codigoBeneficio){
        this.codigoBeneficio = codigoBeneficio;
    }

    public int getFilho() {
        return filho;
    }
    public void setFilho(int filho) {
        this.filho = filho;
        if(onCLTChangeListener != null)
            onCLTChangeListener.onInformacaoChange();
    }

    public float getPensaoClt(){
        return pensaoClt;
    }
    public void setPensaoClt(float pensaoClt){
        this.pensaoClt = pensaoClt;
        if(onCLTChangeListener != null)
            onCLTChangeListener.onInformacaoChange();
    }

    public float getPensaoPJ(){
        return pensaoPJ;
    }
    public void setPensaoPJ(float pensaoPJ){
        this.pensaoPJ = pensaoPJ;
        if(onCLTChangeListener != null)
            onCLTChangeListener.onInformacaoChange();
    }

    public float getSalario() {
        return salario;
    }
    public void setSalario(Float salario) {
        this.salario = salario;
        if(onCLTChangeListener != null)
            onCLTChangeListener.onInformacaoChange();
    }

    /**
     * Valor mensal gasto com transporte
     * @return valor imputado x quantidade de dias do mês
     */
    public float getTransporte() {
        return transporte;
    }
    public void setTransporte(Float transporte) {
        this.transporte = transporte * InformacoesAdicionais.DIAS_NO_MES.getValor();
        if(onCLTChangeListener != null)
            onCLTChangeListener.onInformacaoChange();
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

    /**
     * Calcula o valor a ser pago de INSS
     * @param salario
     * @return o desconto em Reais
     */
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

    public float getPensaoPJValor(){

        return InformacoesAdicionais.SALARIO_MINIMO.getValor() * (this.pensaoPJ / 100);
    }

    public float getIrrf(Float salario){
        float valorBase = salario - this.getInss(salario);
        valorBase -= filho * IRRF.BASE_DEPENDENTE;
        valorBase -= this.getPensaoCLTValor(salario);

        IRRF irrf = IRRF.getIRRF(valorBase);

        return ( valorBase * irrf.getTaxa() ) - irrf.getAlicota();
    }

    public float getContador() {
        return contador;
    }
    public void setContador(float contador) {
        this.contador = contador;
        if(onPJChangeListener != null)
            onPJChangeListener.onInformacaoChange();
    }

    public float getSaude() {
        return saude;
    }
    public void setSaude(float saude) {
        this.saude = saude;
        if(onPJChangeListener != null)
            onPJChangeListener.onInformacaoChange();
    }

    public float getProLabore() {
        return proLabore;
    }
    public void setProLabore(float proLabore) {
        this.proLabore = proLabore;
        if(onPJChangeListener != null)
            onPJChangeListener.onInformacaoChange();
    }
    public float getProLaboreINSS(float salario){
        float valor = salario * (this.getProLabore() / 100);
        return this.getInss(valor);
    }

    public float getTotalPJ(){
        float base = this.getSalario() * 1.2f;
        base += FGTS.getRecisao(base);

        float ferias = ( base * 1.333f ) / InformacoesAdicionais.MESES_NO_ANO.getValor();

        float beneficios = this.getTransporte();
        beneficios += this.getBeneficios(Beneficio.REFEICAO).getValor() * InformacoesAdicionais.DIAS_NO_MES.getValor();
        for(Beneficio item : this.getBeneficios()){
            beneficios += item.getValor();
        }

        float saude = this.getSaude();

        float contador = this.getContador();

        float total = base + ferias + beneficios + saude + contador;

        return total * 1.10f; // expectativa de imposto
    }

    public float getIRPF(float sal){
        float valorBase = sal * (this.getProLabore() / 100);
        valorBase = valorBase - this.getInss(valorBase);
        valorBase -= filho * IRRF.BASE_DEPENDENTE;
        valorBase -= this.getPensaoPJValor();

        IRRF irrf = IRRF.getIRRF(valorBase);

        return ( valorBase * irrf.getTaxa() ) - irrf.getAlicota();
    }
}
