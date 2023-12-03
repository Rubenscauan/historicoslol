package com.desenvolvimento.persistencia.projeto.historicoslol.dao.mongo;


import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.persistencia.projeto.historicoslol.dao.UserDao;
import com.desenvolvimento.persistencia.projeto.historicoslol.models.User;


@Repository
public interface UserDaoMongo extends UserDao, MongoRepository<User, String> {

	// Os métodos findFirstByCpf, buscaPrimeiroPorCpf e buscaPorCpfViaConsultaNomeada fazem exatamente a mesma coisa
	public User findFirstByName(String name);

	//@Query("select c from Cliente c where c.cpf = :cpf")
	@Query(value = "{name: :#{#name}}")
	public User buscaPrimeiroPorName(String name);

	// Os métodos findByNomeStartingWith e buscaPorNomeIniciadoPor fazem exatamente a mesma coisa
	public List<User> findByNameStartingWithIgnoreCase(String str);

	//@Query("select c from Cliente c where c.nome ilike %:nome%")
	@Query(value = "{name: {$regex: :#{#name}, $options: 'i'} }")
	public List<User> buscaPorNameContendoString(String nome);

	@Query(value = "{}", count = true)
	public int conta();

}