package com.dreambrunomsn.cltparapj.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.dreambrunomsn.cltparapj.classes.Informacoes;

public class Mascaras {

    public static final int CONTABIL = 0;
    public static final int SALARIO = 1;
    public static final int TRANSPORTE = 2;
    public static final int REFEICAO = 3;
    public static final int PENSAO = 4;

    public static void listener(final EditText editText, final int mascara, final TextView textView, final TextView textViewAux){
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
                Informacoes informacoes = Informacoes.getInstance();

                switch(mascara){
                    case CONTABIL:
                        String contabil = Mascaras.contabil(editable.toString());
                        editText.setText(contabil);
                        break;
                    case SALARIO:
                        String salario = Mascaras.contabil(editable.toString());
                        informacoes.setSalario(salario);
                        editText.setText(salario);
                        textView.setText(informacoes.getInss());
                        textViewAux.setText(informacoes.getIrrf());
                        break;
                    case TRANSPORTE:
                        String transporte = Mascaras.contabil(editable.toString());
                        informacoes.setTransporte(transporte);
                        editText.setText(transporte);
                        textView.setText(informacoes.getDescontoTransporte());
                        break;
                    case REFEICAO:
                        String refeicao = Mascaras.contabil(editable.toString());
                        informacoes.setRefeicao(refeicao);
                        editText.setText(refeicao);
                        break;
                    case PENSAO:
                        String pensao = Mascaras.decimal(editable.toString());
                        editText.setText(pensao);
                }

                editText.addTextChangedListener(this);
                editText.setSelection(editText.length());
            }
        });

        //return editText;
    }

    public static String decimal(String texto) {
        String valor = texto.replaceAll("\\D", "");
        String decimal;

        while(valor.indexOf("0") == 0){
            valor = valor.replaceFirst("0", "");
        }

        switch (valor.length()){
            case 0:
                return "";
            case 1:
                return "0,0" + valor;
            case 2:
                return "0," + valor;
            default:
                decimal = "," + valor.substring(valor.length() -2);
                valor = valor.substring(0, valor.length() -2);
        }

        if(valor.length() <= 3){
            return valor + decimal;
        } else {
            String formatado = "";
            do{
                formatado = "." + valor.substring(valor.length() - 3) + formatado;
                valor = valor.substring(0, valor.length() - 3);
            } while(valor.length() > 3);

            return valor + formatado + decimal;
        }
    }

    public static String contabil(String texto){
        String valor = Mascaras.decimal(texto);
        if(valor.length() == 0){
            return "";
        } else {
            return "R$ " + valor;
        }
    }

    public static float stringToFloat(String valor){
        valor = valor.replaceAll("[^0-9,]", "");
        valor = valor.replace(",", ".");
        try {
            return Float.parseFloat(valor);
        }catch (Exception ex){
            Log.e("console", "Mascara.stringToFloat(): " + ex);
            return 0;
        }
    }

    public static String primeiraMaiuscula(String texto){
        if(texto == null || texto.length() == 0)
            return "Outro";

        String textoTratado = "";
        String[] palavras = texto.split(" ");

        for(String item : palavras){
            String letra = String.valueOf(item.charAt(0));
            textoTratado += item.replaceFirst(letra, letra.toUpperCase()) + " ";
        }

        return textoTratado.trim();
    }
}
