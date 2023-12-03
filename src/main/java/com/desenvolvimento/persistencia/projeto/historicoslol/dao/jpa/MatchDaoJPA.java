package com.desenvolvimento.persistencia.projeto.historicoslol.dao.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.persistencia.projeto.historicoslol.models.Champion;
import com.desenvolvimento.persistencia.projeto.historicoslol.models.Match;
import com.desenvolvimento.persistencia.projeto.historicoslol.models.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MatchDaoJPA extends JpaRepository<Match, Integer>  {
    
    public Match findFirstById(int id);

    // Native Query
    @Query(value = "select count(*) from matches m", nativeQuery = true)
    public int conta();


    @Query("SELECT MAX(count) FROM (SELECT COUNT(m.champion) AS count " +
       "FROM Match m " +
       "GROUP BY m.champion) AS counts")
    Integer findMaxPlayedCount();

    
    // Consulta customizada para obter o campeão mais jogado
    @Query("SELECT m.champion, COUNT(m.champion) AS totalJogado " +
       "FROM Match m " +
       "GROUP BY m.champion " +
       "HAVING COUNT(m.champion) = :maxPlayedCount " +
       "ORDER BY totalJogado DESC, m.champion.id ASC")
    List<Champion> findChampionsWithMaxPlayedCount(@Param("maxPlayedCount") int maxPlayedCount);

    @Query("SELECT m.user, COUNT(m.user) AS totalJogado " +
       "FROM Match m " +
       "GROUP BY m.user " +
       "HAVING COUNT(m.user) = :maxPlayedCount " +
       "ORDER BY totalJogado DESC, m.user.id ASC")
    List<User> findUsersWithMaxPlayedMatches(@Param("maxPlayedCount") int maxPlayedCount);

    @Query("SELECT MAX(count) FROM (SELECT COUNT(m.user) AS count " +
       "FROM Match m " +
       "GROUP BY m.user) AS counts")
    Integer findMaxPlayedMatchesCount();

    @Query("SELECT m FROM Match m WHERE m.champion.name = :championName")
    List<Match> findByChampionName(@Param("championName") String championName);

}