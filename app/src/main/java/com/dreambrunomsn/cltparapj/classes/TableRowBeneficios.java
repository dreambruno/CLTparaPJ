package com.dreambrunomsn.cltparapj.classes;

import android.content.Context;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.utils.Mascaras;

public class TableRowBeneficios extends TableRow {

    // CONSTRUCTORS
    public TableRowBeneficios(Context context){
        super(context);
    }

    public TableRowBeneficios(Context context, Beneficio beneficio) {
        super(context);
        this.init(context, beneficio);
    }


    private void init(Context context, Beneficio beneficio){
        this.setId(beneficio.getCod());

        TextView nome = new TextView(context);
        nome.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        nome.setPadding(0,0, 5,0);
        String textoNome = Mascaras.primeiraMaiuscula(beneficio.getNome());
        nome.setText(textoNome + ":");

        //TextView valor = new TextView(context);
        EditText valor = new EditText(context);
        valor.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        valor.setText(beneficio.getValorFormatado());
        valor.setFocusable(false);

        Button editar = new Button(context);
        editar.setText("Editar");


        this.addView(nome);
        this.addView(valor);
        //this.addView(editar);
    }
}
