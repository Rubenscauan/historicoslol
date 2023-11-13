package com.desenvolvimento.persistencia.projeto.historicoslol.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.persistencia.projeto.historicoslol.models.Champion;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ChampionDao extends JpaRepository<Champion, Integer> {

    // JPQL
    public Champion findFirstByName(String champion);

    @Query("select c from Champion c where c.name = :name")
    public Champion buscaPrimeiroPorName(String name);

    @Query("select c from Champion c where c.name ilike %:name%")
    public List<Champion> buscaPorNameContendoString(String name);

    @Query("select c from Champion c where c.resource = :resource")
    public List<Champion> findByResource(String resource);

    @Query("select c from Champion c where c.range = :range")
    public List<Champion> findByRange(String range);

    @Query("select c from Champion c where c.region = :region")
    public List<Champion> findByRegion(String region);

    // Native Query
    @Query(value = "select count(*) from champions c", nativeQuery = true)
    public int conta();

    // Named Query
    @Query(name = "championPorName")
    public Champion buscaPorNameViaConsultaNomeada(String name);

    // Consultas baseadas no nome do método
    public List<Champion> findByNameStartingWithIgnoreCase(String name);

}