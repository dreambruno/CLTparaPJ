package com.dreambrunomsn.cltparapj.telas;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.classes.Beneficio;
import com.dreambrunomsn.cltparapj.classes.Informacoes;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class FragmentMEI extends Fragment implements View.OnClickListener {

    private Informacoes informacoes;

    private TextView anualClt;
    private TextView anualCltBeneficios;
    private TextView sal_liq;
    private TextView sal_com_imp;
    private TextView ferias;

    private final int CREDITO = 0;
    private final int DEBITO = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mei, container, false);

        informacoes = Informacoes.getInstance();

        anualClt = view.findViewById(R.id.tvAnualClt);
        anualCltBeneficios = view.findViewById(R.id.tvAnualCltBeneficios);
        sal_liq = view.findViewById(R.id.tvSalLiq);
        sal_com_imp = view.findViewById(R.id.tvSalImp);
        ferias = view.findViewById(R.id.tvFerias);

        this.init();
        return view;
    }

    @Override
    public void onClick(View view) {
        //
    }

    @Override
    public void setUserVisibleHint(boolean visivel) {
        super.setUserVisibleHint(visivel);
        if(visivel && this.getView() != null){
            this.init();
        }
    }

    private void init(){
        sal_com_imp.setText(Mascaras.decimalDuasCasas(this.getSalarioComImposto(informacoes.getSalario()), true));
        sal_liq.setText(Mascaras.decimalDuasCasas(this.getSalarioLiquido(), true));
        ferias.setText(Mascaras.decimalDuasCasas(this.getFerias(), true));
        anualClt.setText(Mascaras.decimalDuasCasas(this.getAnual(), true));
        anualCltBeneficios.setText(Mascaras.decimalDuasCasas(this.getAnualgetAnualComBeneficios(), true));
    }

    private Float getSalarioComImposto(Float valor){
        valor -= informacoes.getInss(valor) + informacoes.getIrrf(valor) + informacoes.getPensaoCLTValor(valor);

        return valor;
    }

    private float[] getBeneficiosMes(){
        float[] valor = {0,0};
        float credito = 0;
        float debito = informacoes.getDescontoTransporte();

        credito += informacoes.getBeneficios(Beneficio.REFEICAO).getValor() * 22;
        debito += informacoes.getBeneficios(Beneficio.REFEICAO).getDesconto();

        for(int i = 1; i < informacoes.getBeneficios().size(); i++){
            Beneficio beneficio = informacoes.getBeneficios(i);
            credito += beneficio.getValor();
            debito += beneficio.getDesconto();
        }
        valor[CREDITO] = credito;
        valor[DEBITO] = debito;

        return valor;
    }

    private Float getSalarioLiquido(){
        float valor = this.getSalarioComImposto(informacoes.getSalario());
        float[] beneficios = this.getBeneficiosMes();

        return valor + beneficios[CREDITO] - beneficios[DEBITO];
    }

    private Float getFerias(){
        float valor = informacoes.getSalario() * 1.333f;

        return this.getSalarioComImposto(valor) / 3;
    }

    private Float getAnual(){

        return this.getSalarioComImposto(informacoes.getSalario()) * 13 + this.getFerias();
    }

    private Float getAnualgetAnualComBeneficios(){

        Float valor = this.getSalarioComImposto(informacoes.getSalario()) * 2;
        valor += this.getFerias();
        return valor + this.getSalarioLiquido() * 11;
    }
}
