package com.br.dureino.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@Entity
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String sku;

	private String nome;	

	private String subCategoria;

	private BigDecimal valorUnitario;

	private Integer estoque;
	
	@OneToMany
	private List<ItemPedido> itemPedidos = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Categoria categoria;


	public Integer getEstoque(){
		Integer result = null;
		if (!itemPedidos.isEmpty()){

		for (ItemPedido itemPedido : itemPedidos){
			result = itemPedido.getQtd();
		}
		}
		return result != null && estoque != null ? estoque - result : null;
	}
	
}
