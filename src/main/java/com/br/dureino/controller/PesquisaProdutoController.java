package com.br.dureino.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
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
		System.out.println("valor dos produtos:"+produtos);
	}
	
	public void listarProduto() {
		
//		this.produtos = produtoDao.listar();
		this.produtos = Arrays.asList(new Produto(1, "12", "Arroz", null, null, new BigDecimal(12.0), 12));
	}

}
