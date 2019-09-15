package com.dreambrunomsn.cltparapj.banco.informacoes;

import com.dreambrunomsn.cltparapj.classes.Informacoes;


public interface InformacoesDao {

    void buscar();

    boolean atualizar(Informacoes informacoes);
}
