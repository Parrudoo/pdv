package com.br.dureino.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.br.dureino.dao.ProdutoDao;
import com.br.dureino.model.Produto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@ViewScoped
public class PesquisaProdutoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private ProdutoDao produtoDao;
	
	private List<Produto> produtos;
	
	
	
	@PostConstruct
	public void init() {
		listarProduto();		
	}
	
	public void listarProduto() {
		
		this.produtos = produtoDao.listar();
		
	}

}
