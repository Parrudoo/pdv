package com.br.dureino.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEntrega implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String logradouro;
	
	private String complemento;
	
	private String cidade;
	
	private String cep;

}
