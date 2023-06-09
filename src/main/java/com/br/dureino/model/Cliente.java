package com.br.dureino.model;

import java.util.List;

import javax.persistence.*;

import com.br.dureino.model.enums.TipoPessoa;

import com.sun.istack.NotNull;
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

	@Column(nullable = false,length = 14)
	private String documentoReceitaFederal;
	
	@OneToMany		
	private List<Endereco> endereco;

	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;
	
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)	
	private List<Pedido> pedidos;
}
