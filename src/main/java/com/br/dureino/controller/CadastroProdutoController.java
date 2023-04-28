package com.br.dureino.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.br.dureino.model.Produto;
import com.br.dureino.model.enums.Categoria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@SessionScoped
public class CadastroProdutoController implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<SelectItem> selectItem;
	
	
	private Produto produto = new Produto();
	
	public List<SelectItem> getSelectItem(){
		
		List<Produto> items = Arrays.asList(new Produto(3, "Crescimento", "Caprino", "CAPRINO", new BigDecimal(12.0), 12));
		List<SelectItem> list = new ArrayList<>();
		for(Produto produto : items) {
			Object value = produto;			
			String label = produto.getNome();
			
			list.add(new SelectItem(value,label));
		}
		
		return list;

	}
	
	public Produto novo() {
		return this.produto = new Produto();
	}
	

}
