package com.br.dureino.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.hibernate.engine.internal.Cascade;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnderecoEntrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date dataEntrega;
		
	private String logradouro;
	
	private String complemento;
	
	private String cidade;

	private String numero;
	
	private String cep;


	@OneToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

}
