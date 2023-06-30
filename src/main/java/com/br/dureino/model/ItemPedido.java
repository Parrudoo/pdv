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
	
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

}
