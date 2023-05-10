package com.br.dureino.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.br.dureino.model.Produto;
import com.br.dureino.model.Venda;
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

	private List<Produto> produtosSelecionados;
	
	private List<Produto> produtoGuardado = new ArrayList<Produto>();

	private Produto produtoSelecionado;

	private Produto deleteProdSelecionado;

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

	public void deletar() throws NegocioException {
		try {
			if (produtoSelecionado != null) {
				cadastroProdutoService.deletar(this.produtoSelecionado);	
				FacesUtil.addSucessoMessage("Produto excluido com sucesso!");
				this.produtoGuardado.remove(this.produtoSelecionado);
				this.produtoSelecionado = new Produto();				
				buscarProdutos();
				listarProduto();				
			}else {
				cadastroProdutoService.deletar(this.deleteProdSelecionado);
				this.produtoGuardado.remove(deleteProdSelecionado);
				buscarProdutos();
				FacesUtil.addSucessoMessage("Produto excluido com sucesso!");
								
			}

		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void deletarProdSelecionado() {
		cadastroProdutoService.deletar(produtosSelecionados);
	}

	public boolean habilitarDatatable() {
		if (produtosSelecionados != null) {
			return true;
		}
		return false;
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

	public List<Produto> selecionar() {		
		
		for(Produto prod : produtosSelecionados) {								
			
			
				
			this.produtoGuardado.add(new Produto(prod.getId(),
					prod.getSku(),
					prod.getNome(), 
					prod.getCategoria(), 
					prod.getSubCategoria(),
					prod.getValorUnitario(),
					prod.getQtd(),
					prod.getVenda(),
					prod.getEstoque()));
		}
		
		return this.produtoGuardado;
	}
	
		
	public void removerDaLista() {		
		
		  Venda venda = new Venda();		
		  produtoSelecionado.setVenda(null);
		this.produtoGuardado.remove(this.produtoSelecionado);				
					
		
	}
	

	public void novo() {
		this.produto = new Produto();
	}

}
