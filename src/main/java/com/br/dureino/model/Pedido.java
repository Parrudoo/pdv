package com.br.dureino.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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

	private LocalDateTime dataCriação;

	private BigDecimal valorFrete;

	private BigDecimal subTotal;

	private BigDecimal total;

	private BigDecimal valorDesconto;

	@ManyToOne
	private Vendedor vendendor;
	
	@OneToMany
	private List<ItemPedido> itemPedidos;


}
