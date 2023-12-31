package com.br.dureino.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemPedido {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer qtd;


	private BigDecimal valorTotal;

	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;



	@Transient
	public boolean isProdutoAssociado(Long id){
		return this.produto.getId().equals(id);
	}

	@Transient
	public BigDecimal getValorTotal(){
		return  this.getQtd() != null && this.produto.getValorUnitario() != null
				? new BigDecimal(this.qtd).multiply(produto.getValorUnitario()) : BigDecimal.ZERO;
	}


	public Integer getQtd(){
		if (this.qtd == null){
			this.setQtd(1);
		}else {
			return this.qtd;
		}

		return this.qtd != null ? this.getQtd() : null;
	}



}
