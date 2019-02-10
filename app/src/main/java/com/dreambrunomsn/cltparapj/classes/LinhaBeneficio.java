package com.dreambrunomsn.cltparapj.classes;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.telas.AdicionarBeneficio;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class LinhaBeneficio extends LinearLayout {

    // CONSTRUCTORS
    public LinhaBeneficio(Context context){
        super(context);
    }
    public LinhaBeneficio(Context context, Beneficio beneficio) {
        super(context);
        this.init(context, beneficio);
    }


    private void init(Context context, final Beneficio beneficio){

        this.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        TextView nome = new TextView(context);
        nome.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        nome.setLayoutParams(new LayoutParams((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 95, getResources().getDisplayMetrics()), LayoutParams.WRAP_CONTENT));
        String textoNome = Mascaras.primeiraMaiuscula(beneficio.getNome());
        nome.setText(textoNome + ":");

        EditText valor = new EditText(context);
        valor.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        valor.setText(beneficio.getValorFormatado());
        valor.setFocusable(false);
        valor.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
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
}
