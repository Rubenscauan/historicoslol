package com.desenvolvimento.persistencia.projeto.historicoslol.dao.mongo;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.persistencia.projeto.historicoslol.dao.MatchDao;
import com.desenvolvimento.persistencia.projeto.historicoslol.models.Match;

@Repository
public interface MatchDaoMongo extends MatchDao, MongoRepository<Match, Integer> {

    // Consulta JQPL que traz o objeto compra com suas associações cliente, itens e produto em uma única consulta SQL ao banco
    // @Query("SELECT c FROM Compra c " +
    //         "left join fetch c.cliente " + 
    //         "left join fetch c.itens i " +
    //         "left join fetch i.produto " +
    //         "where c.id in " +
    //         "(SELECT c.id FROM Compra c join c.itens i group by c having sum(i.quantidade * i.valorUnitario) >= :valor)")
    @Query(value = "{}")
    public List<Match> findComprasComValorTotalMaiorOuIgualA(float valor);

}