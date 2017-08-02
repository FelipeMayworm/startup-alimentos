package br.com.lanches.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.lanches.domain.Ingrediente;

public interface IngredienteService extends MongoRepository<Ingrediente, Integer> {

	  List<Ingrediente> findAll();
	  
	  Ingrediente findIngredienteById(String id);
	  
	  Ingrediente findIngredienteByTipo(String tipo);
	  
}
