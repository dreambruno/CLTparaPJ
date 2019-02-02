package com.dreambrunomsn.cltparapj.classes;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.R;

import static com.dreambrunomsn.cltparapj.R.id.bnNome;

public class TableRowBeneficios extends TableRow {

    private TextView nome;
    private EditText valor;
    private Button editar;

    // CONSTRUCTORS
    public TableRowBeneficios(Context context){
        super(context);
    }

    public TableRowBeneficios(Context context, Beneficio beneficio) {
        super(context);
        this.init(context, beneficio);
    }


    private void init(Context context, Beneficio beneficio){
        View view = inflate(context, R.layout.table_row_beneficios, this);
        /*TableLayout.LayoutParams tableRowParams=
                new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(tableRowParams);
        setGravity(Gravity.CENTER);*/


        nome = (TextView)findViewById(R.id.bnNome);
        valor = (EditText)findViewById(R.id.bnValor);
        //editar = (Button)findViewById(R.id.bnEdit);

        if(nome == null)
            System.out.println("null");

        nome.setText(beneficio.getNome());
        valor.setText(beneficio.getValor());
        /*
        editar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Click!", Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}
