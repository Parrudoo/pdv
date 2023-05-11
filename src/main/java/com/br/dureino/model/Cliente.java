package com.br.dureino.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.br.dureino.model.enums.TipoPessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String documentoReceitaFederal;
	
	@OneToMany		
	private List<Endereco> endereco;
	
	private TipoPessoa tipoPessoa;
	
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)	
	private List<Pedido> pedidos;
}
