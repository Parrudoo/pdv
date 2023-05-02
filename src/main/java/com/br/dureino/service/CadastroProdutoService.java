package com.br.dureino.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.br.dureino.dao.ProdutoDao;
import com.br.dureino.model.Produto;
import com.br.dureino.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoDao produtoDao;
	
	@Transactional
	public Produto salvarProduto(Produto produto) {
		
		return produtoDao.salvar(produto);
	}
	

}
