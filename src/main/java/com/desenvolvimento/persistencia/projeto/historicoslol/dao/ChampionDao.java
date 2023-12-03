package com.desenvolvimento.persistencia.projeto.historicoslol.dao;

import java.util.List;
import java.util.Optional;

import com.desenvolvimento.persistencia.projeto.historicoslol.models.Champion;

public interface ChampionDao {

    public List<Champion> findByNameContainingIgnoreCase(String str);

    public List<Champion> findAll();

    public Optional<Champion> findById(String id);

    public void save(Champion champ);

    public void deleteById(String id);
}
