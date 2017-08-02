package br.com.lanches.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import br.com.lanches.domain.Lanche;

@Component
public class LanchesServiceImpl{
	
	@Autowired
    private LancheService lancheService;
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<Lanche> findAll(){
		return lancheService.findAll();
		 
	}

	public Lanche findLancheById(String id) {
		return lancheService.findLancheById(id);
	}
	
	public void saveLanche(Lanche lanche) {
		mongoTemplate.save(lanche);		
	}
	
	public void deleteLanche(Lanche lanche) {
		lancheService.delete(lanche);		
	}
	
}
