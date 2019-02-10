package com.dreambrunomsn.cltparapj.classes;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.telas.AdicionarBeneficio;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class LinhaDesconto extends LinearLayout{

    // CONSTRUCTOR
    public LinhaDesconto(Context context, Beneficio beneficio) {
        super(context);
        this.init(context, beneficio);
    }


    private void init(Context context, final Beneficio beneficio){

        this.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        this.setGravity(Gravity.RIGHT);
        this.setId(beneficio.getCod());

        TextView nome = new TextView(getContext());
        nome.setText(Mascaras.primeiraMaiuscula(beneficio.getNome()) + ":");
        nome.setPadding(0,0, dpiToPixel(4),0);

        TextView valor = new TextView(context);
        valor.setText(beneficio.getDescontoFormatado());
        valor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AdicionarBeneficio ad = new AdicionarBeneficio(getContext(), beneficio);
                ad.show();
            }
        });

        this.addView(nome);
        this.addView(valor);
    }

    private int dpiToPixel(int dpi){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, getResources().getDisplayMetrics());
    }
}
