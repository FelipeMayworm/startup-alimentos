package br.com.lanches.domain;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Lanche {

	@Id
    private String id;
	private String nome;
    private int quantidade;
    private List<Ingrediente> ingredientes;
    private String valor;
    
    public Lanche() {
    }
 
    public Lanche(String nome, int quantidade, List<Ingrediente> ingrediente, String valor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.ingredientes = ingrediente;
        this.valor = valor;
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
