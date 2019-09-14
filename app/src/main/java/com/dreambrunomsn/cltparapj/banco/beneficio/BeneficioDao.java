package com.dreambrunomsn.cltparapj.banco.beneficio;

import com.dreambrunomsn.cltparapj.classes.Beneficio;

import java.util.List;

public interface BeneficioDao {

    List<Beneficio> buscar();

    boolean atualizar(Beneficio beneficio);
}
