package com.br.dureino.model;

import java.math.BigDecimal;

import com.br.dureino.model.enums.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
	
	private Integer id;
	
	private String nome;
	
	private String categoria;
	
	private String subCategoria;
	
	private BigDecimal valorUnitario;
	
	private Integer estoque;
	
	
	

	
}
