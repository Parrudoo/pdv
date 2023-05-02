package com.br.dureino.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.br.dureino.model.Produto;

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

}
