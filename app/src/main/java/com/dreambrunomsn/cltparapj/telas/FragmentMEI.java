package com.dreambrunomsn.cltparapj.telas;


import android.os.Bundle;
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
    private TextView sal_liq;
    private TextView sal_com_imp;
    private TextView ferias;
    /*private TextView xxx;
    private TextView xxx;
    private TextView xxx;
    private TextView xxx;*/

    private final int CREDITO = 0;
    private final int DEBITO = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mei, container, false);

        informacoes = Informacoes.getInstance();

        anualClt = (TextView)view.findViewById(R.id.tvAnualClt);
        sal_liq = (TextView)view.findViewById(R.id.tvSalLiq);
        sal_com_imp = (TextView)view.findViewById(R.id.tvSalImp);
        ferias = (TextView)view.findViewById(R.id.tvFerias);

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
        sal_com_imp.setText(this.getSalarioComImposto());
        sal_liq.setText(this.getSalarioLiquido());
        ferias.setText(this.getFerias());
        anualClt.setText(this.getAnual());
    }

    private String getSalarioComImposto(){
        float valor = informacoes.getSalario() - informacoes.getInss() - informacoes.getIrrf();
        valor -= valor * (informacoes.getPensaoClt() / 100);

        return Mascaras.decimalDuasCasas(valor, true);
    }

    private float[] getBeneficiosMes(){
        float[] valor = {0,0};
        float credito = informacoes.getTransporte();
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

    private String getSalarioLiquido(){
        float valor = informacoes.getSalario();
        float[] beneficios = getBeneficiosMes();

        beneficios[DEBITO] += informacoes.getInss();
        beneficios[DEBITO] += informacoes.getIrrf();
        beneficios[DEBITO] += Mascaras.stringToFloat(informacoes.getValorPensaoClt());

        valor = valor + beneficios[CREDITO] - beneficios[DEBITO];

        return Mascaras.decimalDuasCasas(valor, true);
    }

    private String getFerias(){
        float valor = informacoes.getSalario();
        valor -= informacoes.getInss() + informacoes.getIrrf() + Mascaras.stringToFloat(informacoes.getValorPensaoClt());
        valor /= 3;

        return Mascaras.decimalDuasCasas(valor, true);
    }

    private String getAnual(){
        float valor = informacoes.getSalario() - informacoes.getInss() - informacoes.getIrrf();
        float ferias = valor / 3;
        float[] beneficios = this.getBeneficiosMes();

        valor = (valor * 13) + ferias;
        valor -= valor * (informacoes.getPensaoClt() / 100);
        valor += beneficios[CREDITO] * 11;
        valor -= beneficios[DEBITO] * 11;

        return Mascaras.decimalDuasCasas(valor, true);
    }
}
