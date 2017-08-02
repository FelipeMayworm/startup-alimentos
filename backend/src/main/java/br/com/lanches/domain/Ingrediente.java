package br.com.lanches.domain;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

public class Ingrediente {
	@Id
    private String id;
	private String tipo;
	private int quantidade;
	private BigDecimal valor;
	
	public Ingrediente() {
    }
 
    public Ingrediente(String tipo, int quantidade, String valor) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.valor = new BigDecimal(valor);
    }
	    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = new BigDecimal(valor);
	}
	
}
