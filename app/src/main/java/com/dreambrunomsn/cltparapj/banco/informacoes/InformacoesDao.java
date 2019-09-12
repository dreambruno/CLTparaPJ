package com.dreambrunomsn.cltparapj.banco.informacoes;

import com.dreambrunomsn.cltparapj.classes.Informacoes;

import java.util.List;

public interface InformacoesDao {

    List<Informacoes> buscar();

    Informacoes buscar( int id );

    long inserir(Informacoes informacoes);

    boolean atualizar(Informacoes informacoes);

    boolean deletar( int id );
}
