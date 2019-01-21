package com.dreambrunomsn.cltparapj.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class Mascaras {

    public static final int CONTABIL = 1;
    public static final int DATA = 2;

    public static void listener(final EditText editText, final int mascara){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                editText.removeTextChangedListener(this);

                switch(mascara){
                    case CONTABIL:
                        editText.setText(Mascaras.contabil(editable.toString()));
                        break;
                    case DATA:
                        editText.setText(Mascaras.data());
                        break;
                }

                editText.addTextChangedListener(this);
                editText.setSelection(editText.length());
            }
        });

        //return editText;
    }

    public static String contabil(String texto) {
        String valor = "0" + texto.replaceAll("\\D", "");
        String decimal;

        while(valor.indexOf("0") == 0){
            valor = valor.replaceFirst("0", "");
        }

        switch (valor.length()){
            case 0:
                return "R$ 0,00";
            case 1:
                return "R$ 0,0" + valor;
            case 2:
                return "R$ 0," + valor;
            default:
                decimal = "," + valor.substring(valor.length() -2);
                valor = valor.substring(0, valor.length() -2);
        }

        if(valor.length() <= 3){
            return "R$ " + valor + decimal;
        } else {
            String formatado = "";
            do{
                formatado = "." + valor.substring(valor.length() - 3) + formatado;
                valor = valor.substring(0, valor.length() - 3);
            } while(valor.length() > 3);

            return "R$ " + valor + formatado + decimal;
        }
    }

    public static String data(){
        return "28/05/2007";
    }
}
