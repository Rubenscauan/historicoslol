package com.desenvolvimento.persistencia.projeto.historicoslol.dao;


import java.util.List;
import java.util.Optional;

import com.desenvolvimento.persistencia.projeto.historicoslol.models.User;


public interface UserDao {

	public User findFirstByName(String name);

	public User buscaPrimeiroPorName(String name);

	public List<User> findByNameStartingWithIgnoreCase(String str);

	public List<User> buscaPorNameContendoString(String nome);

	public int conta();

    public void save(User user);

    public void deleteById(String id);

    public Optional<User> findById(String id);

    public List<User> findAll();

}