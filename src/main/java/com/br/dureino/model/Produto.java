package com.br.dureino.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

import com.br.dureino.model.enums.Und;
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

	private BigDecimal valorUnitario = BigDecimal.ZERO;

	private BigDecimal precoCusto = BigDecimal.ZERO;

	private String ncm;

	private BigDecimal lucro;

	private Integer estoque;

	private BigDecimal valorMinimo;

	private BigDecimal valorMaximo;

	private Integer estoqueMinimo;

	private Integer estoqueMaximo;

	private Boolean controlerEstoque = false;

	private Boolean habilitado = true;


	private Und und;
	
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


	public boolean getControlerEstoque(){
		if (controlerEstoque != null){
			if (this.controlerEstoque){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	public BigDecimal getLucro(){
		BigDecimal resultado = BigDecimal.ZERO;

		resultado =	resultado.add(valorUnitario.subtract(precoCusto));
			this.setLucro(resultado);


		return resultado;
	}
}
