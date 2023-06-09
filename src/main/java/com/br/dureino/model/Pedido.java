package com.br.dureino.model;

import java.util.List;

import javax.persistence.*;

import com.br.dureino.model.enums.FormaPagamento;
import com.br.dureino.model.enums.StatusPedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne	
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	private FormaPagamento pagamento;

	@ManyToOne
	private Vendedor vendendor;
	
	@OneToMany
	private List<ItemPedido> itemPedidos;
}
