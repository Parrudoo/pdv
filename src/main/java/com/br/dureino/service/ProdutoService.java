package com.br.dureino.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.br.dureino.dao.ProdutoDao;
import com.br.dureino.model.Produto;
import com.br.dureino.util.jpa.Transactional;

public class ProdutoService implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoDao produtoDao;
	
	@Transactional
	public void salvarProduto(Produto produto) {
			produtoDao.salvar(produto);
	}

	public List<Produto> listar() {
		
		return produtoDao.listar();
	}

	public List<Produto> buscarNome(String nome) {
		
		return produtoDao.buscarNome(nome);
	}

	@Transactional
	public void deletar(Produto produtoSelecionado) {
		produtoDao.deletar(produtoSelecionado);
		
	}

	@Transactional
	public void deletar(List<Produto> produtoSelecionado) {
		produtoDao.deletar(produtoSelecionado);
		
	}
	

}
