package com.dreambrunomsn.cltparapj.banco.beneficio;

import com.dreambrunomsn.cltparapj.classes.Informacoes;

import java.util.List;

public interface BeneficioDao {

    List<Informacoes> buscar();

    Informacoes buscar( int id );

    long inserir(Informacoes informacoes);

    boolean atualizar(Informacoes informacoes);

    boolean deletar( int id );
}
