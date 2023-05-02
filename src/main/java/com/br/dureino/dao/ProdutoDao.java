package com.br.dureino.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;

import com.br.dureino.model.Produto;
import com.br.dureino.util.jpa.Transactional;

public class ProdutoDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1716975848882203808L;
	
	@Inject
	private EntityManager entityManager;
	
	
	public List<Produto> listar() {
		
		return entityManager.createQuery("select p from Produto p").getResultList();
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

}
