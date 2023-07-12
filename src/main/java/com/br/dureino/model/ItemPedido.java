package com.br.dureino.model;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Schedule;
import javax.persistence.*;

import io.swagger.annotations.ApiParam;
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
	
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	private BigDecimal subTotal;

	@Transient
	public boolean isProdutoAssociado(Long id){
		return this.produto.getId().equals(id);
	}

	@Transient
	public BigDecimal getValorTotal(){
		return  this.getQtd() != null && this.produto.getValorUnitario() != null
				? new BigDecimal(this.qtd).multiply(produto.getValorUnitario()) : BigDecimal.ZERO;
	}

		public BigDecimal getSubTotal(){
		BigDecimal total = BigDecimal.ZERO;

		total = total.add(this.getValorTotal());

		return total;

		}


}
