package com.desenvolvimento.persistencia.projeto.historicoslol.dao;

import java.util.List;
import java.util.Optional;

import com.desenvolvimento.persistencia.projeto.historicoslol.models.Match;

public interface MatchDao {
    //public List<Match> findComprasComValorTotalMaiorOuIgualA(float valor);

    public Optional<Match> findById(String id);

    public void deleteById(String id);

    public List<Match> findAll();

    public void save(Match match);
}
