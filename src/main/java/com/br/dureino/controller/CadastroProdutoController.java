package com.br.dureino.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.br.dureino.model.Produto;
import com.br.dureino.service.NegocioException;
import com.br.dureino.service.ProdutoService;
import com.br.dureino.util.jsf.FacesUtil;

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

	private List<Produto> produtos;

	private List<Produto> produtosNome;
	
	private Produto produtoSelecionado;
	
	private Produto deleteProduto;

	private String nomeDigitado;

	@Inject
	private ProdutoService cadastroProdutoService;

	@PostConstruct
	public void init() {
		listarProduto();
		this.produto = new Produto();

	}
	
	public void inicializar() {
		this.produto = new Produto();
	}

	public void salvar() throws NegocioException {
		try {
			cadastroProdutoService.salvarProduto(produto);
			FacesUtil.addSucessoMessage("Produto salvo com sucesso!");
			novo();
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());

		}
	}
	
	public void deletar() {
		try {			
			cadastroProdutoService.deletar(this.produtoSelecionado);
			this.produtoSelecionado = new Produto();
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public List<SelectItem> getSelectItem() {

		List<SelectItem> list = new ArrayList<>();
		for (Produto produto : produtos) {
			Object value = produto;
			String label = produto.getNome();

			list.add(new SelectItem(value, label));
		}

		return list;

	}

	private void listarProduto() {
		this.produtos = cadastroProdutoService.listar();

	}

	public void buscarProdutos() {

		if (nomeDigitado != null && !nomeDigitado.isEmpty()) {
			this.produtosNome = cadastroProdutoService.buscarNome(nomeDigitado);
		} else {
			this.produtosNome = new ArrayList<>();
		}

	}
	
	public Produto selecionar() {
		
		Produto produto = new Produto();		
		produto.setEstoque(produtoSelecionado.getEstoque());
		produto.setNome(produtoSelecionado.getNome());
		return produto;
		
	}

	public void novo() {
		this.produto = new Produto();
	}

}
