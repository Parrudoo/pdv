package com.br.dureino.model.enums;

public enum FormaPagamento {
    DINHEIRO("Dinheiro"),
    CARTAO_CREDITO("Cartão de Crédito"),
    BOLETO_BANCARIO("Boleto Bancário");

    FormaPagamento(String descricao){
        this.descricao = descricao;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }
}
