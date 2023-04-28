package com.br.dureino.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.br.dureino.model.Produto;

@Named
@SessionScoped
public class CadastroProdutoController implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<SelectItem> selectItem;
	
	@PostConstruct
	public void init() {
		getSelectItem();
	}
	
	
	public List<SelectItem> getSelectItem(){
		
		List<Produto> items = Arrays.asList(new Produto(1,"Arroz"), new Produto(2,"Feijão"));
		List<SelectItem> list = new ArrayList<>();
		for(Produto produto : items) {
			Object value = produto;			
			String label = produto.getNome();
			
			selectItem.add(new SelectItem(value,label));
		}
		
		return selectItem;

	}

}
