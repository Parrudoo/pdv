package com.br.dureino.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


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

	@Column(unique = true)
	private Integer numero;
	
	@ManyToOne	
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@Enumerated(EnumType.STRING)
	private FormaPagamento pagamento;

	private Date dataCriacao = new Date();

	private BigDecimal valorFrete = BigDecimal.ZERO;

	private BigDecimal subTotal;

	private BigDecimal total = BigDecimal.ZERO;

	private BigDecimal valorDesconto = BigDecimal.ZERO;


	@OneToOne(cascade = CascadeType.ALL ,mappedBy = "pedido")
	private EnderecoEntrega enderecoEntrega = new EnderecoEntrega();

	@ManyToOne
	private Vendedor vendendor;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemPedido>  itemPedidos = new ArrayList<>();


	public String getDataCriacao(){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String date = format.format(new Date());

		return  date;
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal totalProdutos = BigDecimal.ZERO;

		total =	total.add(valorFrete.subtract(valorDesconto));

		if (!itemPedidos.isEmpty()){

		for (ItemPedido itemPedido : itemPedidos){
			totalProdutos = totalProdutos.add(itemPedido.getValorTotal());
		}
		totalProdutos = totalProdutos.add(total);
		}
		return totalProdutos;
	}

	public Integer getNumero(){
		Random random = new Random();
		Integer aleatorio = random.nextInt(1000);
		setNumero(aleatorio);

		return numero;
	}


}
