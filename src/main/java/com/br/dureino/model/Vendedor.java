package com.br.dureino.model;

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
public class Vendedor {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String cpf;

	private String telefone;
	
	@OneToMany
	@JoinColumn(name = "vendedor_id")
	private List<Pedido> pedidos;

	@OneToOne(mappedBy = "vendedor",cascade = CascadeType.ALL)
	@JoinColumn(name = "vendedor_id")
	private Endereco endereco = new Endereco();

}
