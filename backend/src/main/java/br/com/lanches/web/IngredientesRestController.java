package br.com.lanches.web;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lanches.domain.Ingrediente;
import br.com.lanches.repositories.IngredientesServiceImpl;

@org.springframework.web.bind.annotation.RestController
public class IngredientesRestController {
	
	@Autowired
	IngredientesServiceImpl ingredientesServiceImpl;
	
	@RequestMapping("/api/ingredientes")
	public String ingredientes(){
		List<Ingrediente> ingredientes = ingredientesServiceImpl.findAll();
		JSONArray ingreds = montaArrayJson(ingredientes);
		return ingreds.toJSONString();
	 }
	
	@PostMapping("/api/ingrediente")
	public String ingredienteAdd(@RequestBody Ingrediente ingrediente){
		try{
			ingredientesServiceImpl.saveIngrediente(ingrediente);		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ingredientes();
	 }
	
	@PutMapping("/api/ingrediente/{ingredienteId}")
	public void ingredienteEdit(@PathVariable("ingredienteId") String ingredienteId, @RequestBody Ingrediente ingrediente){
		
		try{
			Ingrediente ingred = ingredientesServiceImpl.findIngredienteById(ingredienteId);
			ingred.setTipo(ingrediente.getTipo());
			ingred.setQuantidade(ingrediente.getQuantidade());
			ingred.setValor(ingrediente.getValor().toString());
			ingredientesServiceImpl.saveIngrediente(ingred);			
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
	 }
	
	@DeleteMapping("/api/ingrediente/{ingredienteId}")
	public void ingredienteDelete(@PathVariable("ingredienteId") String ingredienteId){
		
		try{
			Ingrediente ingred = ingredientesServiceImpl.findIngredienteById(ingredienteId);
			ingredientesServiceImpl.deleteIngrediente(ingred);			
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
	 }
	
	private JSONArray montaArrayJson(List<Ingrediente> ingredientes){
		JSONArray ingreds = new JSONArray();
		try{
			for(Ingrediente i : ingredientes){
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", i.getId());
				jsonObj.put("tipo", i.getTipo());
				jsonObj.put("quantidade", i.getQuantidade());
				jsonObj.put("valor", i.getValor());
				ingreds.add(jsonObj);				
	        }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ingreds;
	}
	
}
