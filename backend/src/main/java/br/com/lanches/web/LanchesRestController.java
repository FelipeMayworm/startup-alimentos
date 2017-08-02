package br.com.lanches.web;

import java.math.BigDecimal;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lanches.domain.Ingrediente;
import br.com.lanches.domain.Lanche;
import br.com.lanches.repositories.LanchesServiceImpl;

@org.springframework.web.bind.annotation.RestController
public class LanchesRestController {
	
	@Autowired
	LanchesServiceImpl lanchesServiceImpl;
	
	@RequestMapping("/api/lanches")
	public String lanches(){
		List<Lanche> lanches = lanchesServiceImpl.findAll();
		JSONArray sandubas = montaArrayJson(lanches);
		return sandubas.toJSONString();
	 }
	
	@PostMapping("/api/lanche")
	public String lancheAdd(@RequestBody Lanche lanche){
		try{
			BigDecimal sum = lanche.getIngredientes().stream().map(Ingrediente::getValor).reduce(BigDecimal::add).get();
			int carnePromocao = lanche.getIngredientes().stream().filter(x -> x.getTipo().contains("Hambúrguer de carne")).map(Ingrediente::getQuantidade).findAny().orElse(0);
			int queijoPromocao = lanche.getIngredientes().stream().filter(x -> x.getTipo().contains("Queijo")).map(Ingrediente::getQuantidade).findAny().orElse(0);
			int light = lanche.getIngredientes().stream().filter(x -> x.getTipo().contains("Alface")).map(Ingrediente::getQuantidade).findAny().orElse(0);
			int notLight = lanche.getIngredientes().stream().filter(x -> x.getTipo().contains("Bacon")).map(Ingrediente::getQuantidade).findAny().orElse(0);
			if(light > 0 && notLight == 0) sum = sum.subtract((sum.divide(new BigDecimal(10))));
			int descontarCarne = 0;
			if(carnePromocao > 2){
				descontarCarne = (carnePromocao/3)+1;
				sum = sum.add(lanche.getIngredientes().stream().filter(x -> x.getTipo().contains("Hambúrguer de carne")).map(Ingrediente::getValor).findAny().orElse(new BigDecimal(0)).multiply(new BigDecimal(carnePromocao).subtract(new BigDecimal(descontarCarne))));				
			}
			int descontarQueijo = 0;
			if(queijoPromocao > 2){
				descontarQueijo = queijoPromocao/3+1;
				sum = sum.add(lanche.getIngredientes().stream().filter(x -> x.getTipo().contains("Queijo")).map(Ingrediente::getValor).findAny().orElse(new BigDecimal(0)).multiply(new BigDecimal(queijoPromocao).subtract(new BigDecimal(descontarQueijo))));				
			}
			
			lanche.setValor(sum.toString());
			lanchesServiceImpl.saveLanche(lanche);		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lanches();
	 }
	
	@RequestMapping("/api/lanche/{lancheId}")
	public String askLanche(@PathVariable("lancheId") String lancheId, @RequestBody Lanche lanche){
		Lanche l = lanchesServiceImpl.findLancheById(lancheId);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("nome", l.getNome());
		jsonObj.put("id", l.getId());
		jsonObj.put("quantidade", l.getQuantidade());
		BigDecimal valorTotal = new BigDecimal(l.getValor()).multiply(new BigDecimal(lanche.getQuantidade()));
		jsonObj.put("valor", valorTotal);
		return jsonObj.toJSONString();
	 }
	
	@DeleteMapping("/api/lanche/{lancheId}")
	public void lancheDelete(@PathVariable("lancheId") String lancheId){
		
		try{
			Lanche sanduba = lanchesServiceImpl.findLancheById(lancheId);
			lanchesServiceImpl.deleteLanche(sanduba);			
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
	 }
	
	private JSONArray montaArrayJson(List<Lanche> lanches){
		JSONArray sandubas = new JSONArray();
		try{
			for(Lanche l : lanches){
				JSONObject jsonObj = new JSONObject();
				JSONArray ingreds = new JSONArray();
				jsonObj.put("nome", l.getNome());
				jsonObj.put("id", l.getId());
				for(Ingrediente i : l.getIngredientes()){
					JSONObject jsonIngreds = new JSONObject();
					jsonIngreds.put("tipo", i.getTipo());
					jsonIngreds.put("quantidade", i.getQuantidade());
					ingreds.add(jsonIngreds);
				}
				jsonObj.put("quantidade", l.getQuantidade());
				jsonObj.put("valor", l.getValor());
				jsonObj.put("ingredientes", ingreds);
				sandubas.add(jsonObj);
				
	        }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sandubas;
	}
	
}
