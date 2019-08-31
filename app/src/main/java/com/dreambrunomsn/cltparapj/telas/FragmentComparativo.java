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
import com.dreambrunomsn.cltparapj.enums.FGTS;
import com.dreambrunomsn.cltparapj.enums.InformacoesAdicionais;
import com.dreambrunomsn.cltparapj.utils.Mascaras;
import com.dreambrunomsn.cltparapj.utils.Utils;

public class FragmentComparativo extends Fragment implements View.OnClickListener {

    private Informacoes informacoes;

    private TextView anualClt;
    private TextView anualCltBeneficios;
    private TextView sal_liq;
    private TextView sal_com_imp;
    private TextView ferias;

    private TextView salarioPJ;
    private TextView salarioPJ13eFerias;
    private TextView beneficiosPJ;
    private TextView totalPJ;
    private TextView totalPJLiquido;
    private TextView anualPJ;

    private final int CREDITO = 0;
    private final int DEBITO = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comparativo, container, false);

        Utils.hideKeyboard(getActivity());

        informacoes = Informacoes.getInstance();

        anualClt = view.findViewById(R.id.tvAnualClt);
        anualCltBeneficios = view.findViewById(R.id.tvAnualCltBeneficios);
        sal_liq = view.findViewById(R.id.tvSalLiq);
        sal_com_imp = view.findViewById(R.id.tvSalImp);
        ferias = view.findViewById(R.id.tvFerias);

        salarioPJ = view.findViewById(R.id.tvSalPJ);
        salarioPJ13eFerias = view.findViewById(R.id.tvPJ12eFerias);
        beneficiosPJ = view.findViewById(R.id.tvPJBeneficios);
        totalPJ = view.findViewById(R.id.tvXPJTotal);
        totalPJLiquido = view.findViewById(R.id.tvTotalPJLiquido);
        anualPJ = view.findViewById(R.id.tvXPJAnual);

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
        // Informações CLT
        sal_com_imp.setText(Mascaras.decimalDuasCasas(this.getSalarioComImposto(informacoes.getSalario()), true));
        sal_liq.setText(Mascaras.decimalDuasCasas(this.getSalarioLiquido(), true));
        ferias.setText(Mascaras.decimalDuasCasas(this.getSalarioFerias(), true));
        anualClt.setText(Mascaras.decimalDuasCasas(this.getAnual(), true));
        anualCltBeneficios.setText(Mascaras.decimalDuasCasas(this.getAnualgetAnualComBeneficios(), true));

        // Informações PJ
        salarioPJ.setText(Mascaras.decimalDuasCasas(this.getSalarioBasePJ(), true));
        salarioPJ13eFerias.setText(Mascaras.decimalDuasCasas(this.getPJ13eFerias(), true));
        beneficiosPJ.setText(Mascaras.decimalDuasCasas(this.getPJBeneficios(), true));
        totalPJ.setText(Mascaras.decimalDuasCasas(this.getTotalPJ(), true));
        totalPJLiquido.setText(Mascaras.decimalDuasCasas(this.getTotalComTaxas(), true));
        anualPJ.setText(Mascaras.decimalDuasCasas(this.getAnualPJ(), true));
    }

    private Float getSalarioComImposto(Float valor){
        valor -= informacoes.getInss(valor) + informacoes.getIrrf(valor) + informacoes.getPensaoCLTValor(valor);

        return valor;
    }

    private float[] getBeneficiosMes(){
        float[] valor = {0,0};
        float credito = 0;
        float debito = informacoes.getDescontoTransporte();

        credito += informacoes.getBeneficios(Beneficio.REFEICAO).getValor() * InformacoesAdicionais.DIAS_NO_MES.getValor();
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

    private Float getSalarioFerias(){
        float valor = informacoes.getSalario() * 1.333f;

        return this.getSalarioComImposto(valor);
    }

    private Float getAnual(){

        return this.getSalarioComImposto(informacoes.getSalario()) * InformacoesAdicionais.MESES_NO_ANO.getValor() + this.getSalarioFerias();
    }

    private Float getAnualgetAnualComBeneficios(){

        Float valor = this.getSalarioComImposto(informacoes.getSalario()); // 13º Salario
        valor += this.getSalarioFerias(); // Salário de férias (total)
        return valor + (this.getSalarioLiquido() * InformacoesAdicionais.ANO_SEM_FERIAS.getValor()); // 11 meses com benefícios
    }

    private Float getSalarioBasePJ(){
        Float valor = informacoes.getSalario() * 1.20f;
        return  valor + FGTS.getRecisao( valor ) ;
    }

    private Float getPJ13eFerias(){
        return  ( this.getSalarioBasePJ() * 1.333f ) / InformacoesAdicionais.MESES_NO_ANO.getValor();
    }

    private Float getPJBeneficios(){

        float credito = informacoes.getTransporte();
        credito += informacoes.getBeneficios(Beneficio.REFEICAO).getValor() * InformacoesAdicionais.DIAS_NO_MES.getValor();

        for(int i = 1; i < informacoes.getBeneficios().size(); i++){
            credito += informacoes.getBeneficios(i).getValor();
        }

        return credito;
    }

    private Float getTotalPJ(){
        Float valor = this.getSalarioBasePJ();
        valor += this.getPJ13eFerias();
        valor += this.getPJBeneficios();

        valor += 1600f;// valor contador e plano de saude
        valor = valor * 1.13f; // imposto

        return valor;
    }

    private Float getTotalComTaxas(){
        return this.getTotalPJ() - 1600f - (this.getTotalPJ() * 0.11f);
    }

    private Float getAnualPJ(){
        return this.getTotalPJ() * InformacoesAdicionais.MESES_NO_ANO.getValor();
    }
}
