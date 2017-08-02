package br.com.lanches.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lanches.domain.Ingrediente;
import br.com.lanches.domain.Lanche;
import br.com.lanches.repositories.LancheService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LancheServiceTest {

	@Autowired
    private LancheService lancheService;
 
 
    @Before
    public void setUp() throws Exception {
    	List<Ingrediente> ingredientesXBurger = new ArrayList<Ingrediente>();
    	Ingrediente ingrediente1= new Ingrediente("Hambúrguer de carne", 1, "3.00");
    	Ingrediente ingrediente2= new Ingrediente("Queijo", 1, "1.50");
    	ingredientesXBurger.add(ingrediente1);
    	ingredientesXBurger.add(ingrediente2);
    	
    	List<Ingrediente> ingredientesXBacon = new ArrayList<Ingrediente>();
    	Ingrediente ingrediente3= new Ingrediente("Bacon", 1, "2.00");
    	ingredientesXBacon.addAll(ingredientesXBurger);
    	ingredientesXBacon.add(ingrediente3);
    	
        Lanche lanche1= new Lanche("X-Burger", 1, ingredientesXBurger, String.valueOf(calculaPreco(ingredientesXBurger)));
        Lanche lanche2= new Lanche("X-Bacon", 1, ingredientesXBacon, String.valueOf(calculaPreco(ingredientesXBacon)));
        
        assertNull(lanche1.getId());
        assertNull(lanche2.getId());
        
        this.lancheService.save(lanche1);
        this.lancheService.save(lanche2);
        
        assertNotNull(lanche1.getId());
        assertNotNull(lanche2.getId());
    }
    
    private BigDecimal calculaPreco(List<Ingrediente> ingredientes){
    	
    	Function<Ingrediente, BigDecimal> valor = ingrediente -> ingrediente.getValor();
    	BigDecimal valorTotal = ingredientes.stream().map(valor).reduce(BigDecimal.ZERO, BigDecimal::add);
    	return valorTotal;
    }
 
    @Test
    public void testFetchData(){
        Lanche lancheA = lancheService.findLancheByNome("X-Burger");
        assertNotNull(lancheA);
        assertEquals(1, lancheA.getQuantidade());
        Iterable<Lanche> lanches = lancheService.findAll();
        int count = 0;
        for(Lanche p : lanches){
        	System.out.println(p.getNome());
            count++;
        }
        assertEquals(count, 2);
    }
 
    @Test
    public void testDataUpdate(){
        Lanche lancheB = lancheService.findLancheByNome("X-Bacon");
        assertNotNull(lancheB);
        assertEquals("6.50", lancheB.getValor());
        
        lancheB.setValor("7.00");
        lancheService.save(lancheB);
        
        Lanche lancheC= lancheService.findLancheByNome("X-Bacon");
        assertNotNull(lancheC);
        assertEquals("7.00", lancheC.getValor());
    }
 
    @After
    public void tearDown() throws Exception {
      this.lancheService.deleteAll();
    }
    
}
