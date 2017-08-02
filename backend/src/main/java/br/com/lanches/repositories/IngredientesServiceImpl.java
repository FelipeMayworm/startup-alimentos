package br.com.lanches.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import br.com.lanches.domain.Ingrediente;

@Component
public class IngredientesServiceImpl{
	
	@Autowired
    private IngredienteService ingredienteService;
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<Ingrediente> findAll(){
		return ingredienteService.findAll();
		 
	}

	public Ingrediente findIngredienteById(String id) {
		return ingredienteService.findIngredienteById(id);
	}
	
	public void saveIngrediente(Ingrediente ingrediente) {
		mongoTemplate.save(ingrediente);		
	}
	
	public void deleteIngrediente(Ingrediente ingrediente) {
		ingredienteService.delete(ingrediente);		
	}
	
}
