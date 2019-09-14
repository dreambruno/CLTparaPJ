package com.dreambrunomsn.cltparapj.banco.informacoes;

import com.dreambrunomsn.cltparapj.classes.Informacoes;


public interface InformacoesDao {

    void buscar( int id );

    boolean atualizar(Informacoes informacoes);
}
