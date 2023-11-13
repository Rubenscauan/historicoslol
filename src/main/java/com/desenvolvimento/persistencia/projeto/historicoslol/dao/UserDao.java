package com.desenvolvimento.persistencia.projeto.historicoslol.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.persistencia.projeto.historicoslol.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserDao extends JpaRepository<User, Integer>  {
    
    public User findFirstByName(String user);

    @Query("select u from User u where u.name ilike %:name%")
    public List<User> buscaPorNameContendoString(String name);

    // Native Query
    @Query(value = "select count(*) from users u", nativeQuery = true)
    public int conta();
}


