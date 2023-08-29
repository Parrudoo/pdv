package com.br.dureino.model.enums;

public enum Estado {

    PI("Piaui"),
    MA("Maranhão"),
    CE("Ceará"),
    GO("Goiania");

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    private Estado(String descricao){
        this.descricao = descricao;
    }
}
