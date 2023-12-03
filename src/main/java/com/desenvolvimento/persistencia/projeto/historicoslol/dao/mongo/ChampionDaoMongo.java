package com.desenvolvimento.persistencia.projeto.historicoslol.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.persistencia.projeto.historicoslol.dao.ChampionDao;
import com.desenvolvimento.persistencia.projeto.historicoslol.models.Champion;

@Repository

public interface ChampionDaoMongo extends ChampionDao, MongoRepository<Champion, Integer> {

    public List<Champion> findByNameContainingIgnoreCase(String str);

}