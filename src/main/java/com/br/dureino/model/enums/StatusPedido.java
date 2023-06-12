package com.br.dureino.model.enums;

public enum StatusPedido {
	
	ORCAMENTO("Orçamento"),EMITIDO("Emitido"),CANCELADO("Cancelado");

	public String getDescricao() {
		return descricao;
	}

	private String descricao;

		StatusPedido(String descricao){
			this.descricao = descricao;
		}

	
}
