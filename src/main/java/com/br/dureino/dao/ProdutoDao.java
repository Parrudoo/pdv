package com.br.dureino.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;

import com.br.dureino.model.Produto;
import com.br.dureino.model.QProduto;
import com.br.dureino.util.jpa.Transactional;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

public class ProdutoDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1716975848882203808L;
	
	private static final QProduto qProduto = QProduto.produto;
	
	@Inject
	private EntityManager entityManager;
	
	
	public List<Produto> listar() {
		
//		return entityManager.createQuery("select p from Produto p").getResultList();

		  JPAQuery<Produto> produto = new JPAQuery<>(entityManager);
		  List<Produto> produtos = produto.select(qProduto)
				  .from(qProduto).fetch();
		  
		  return produtos;
		
	}

	
	public Produto salvar(Produto produto) {				
		/*
		 * o codigo foi comentado, pois usei uma classe para fazer a transação, e nao
		 * precisar ficar implementando toda vez
		 */
		
		/*
		 * EntityTransaction trx = entityManager.getTransaction(); trx.begin();
		 */
		produto = entityManager.merge(produto);
		/* trx.commit(); */
		return produto;
	}



	public List<Produto> buscarNome(String nome) {

		//		return entityManager.createQuery("select p from Produto p where p.nome like CONCAT(:nome ,'%')",Produto.class).
//				setParameter("nome", nome)
//				.getResultList();

		JPAQuery<Produto> query = new JPAQuery<>(entityManager);

		List<Produto> produtos = query.select(qProduto)
				.from(qProduto)
				.where(qProduto.nome.like(nome+"%")).fetch();
		return produtos;


	}


	public Produto buscarPeloCodigo(Long codigo) {		
		return entityManager.find(Produto.class, codigo);
	}



	
	public void deletar(Produto produtoSelecionado) {
		produtoSelecionado = entityManager.find(Produto.class, produtoSelecionado.getId());
		entityManager.remove(produtoSelecionado);
		entityManager.flush();
		
	}



	
	public void deletar(List<Produto> produtoSelecionado) {		
		for(Produto produto : produtoSelecionado) {
			produto = entityManager.find(Produto.class, produto.getId());
			
			entityManager.remove(produto);
		}
		
		
	}

}
