package com.br.dureino.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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

	
	@OneToMany
	@JoinColumn(name = "vendedor_id")
	private List<Pedido> pedidos;
}
