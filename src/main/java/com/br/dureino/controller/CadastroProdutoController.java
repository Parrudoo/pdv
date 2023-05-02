package com.br.dureino.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.br.dureino.model.Produto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@ViewScoped
public class CadastroProdutoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SelectItem> selectItem;

	private Produto produto;

	public List<SelectItem> getSelectItem() {

		List<Produto> items = Arrays
				.asList(new Produto(1, "234","Arroz", "55642", "Caprino", new BigDecimal(12.0), 12));
		List<SelectItem> list = new ArrayList<>();
		for (Produto produto : items) {
			Object value = produto;
			String label = produto.getNome();

			list.add(new SelectItem(value, label));
		}

		return list;

	}

	@PostConstruct
	public void init() {
		this.produto = new Produto();
	}
	
	public void novo() {
		this.produto = new Produto();
	}

}
