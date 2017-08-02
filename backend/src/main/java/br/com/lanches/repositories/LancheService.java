package br.com.lanches.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.lanches.domain.Lanche;

public interface LancheService extends MongoRepository<Lanche, Integer> {

	  List<Lanche> findAll();
	  
	  Lanche findLancheById(String id);
	  
	  Lanche findLancheByNome(String nome);
	  
}
