package com.br.dureino.model.enums;

public enum Und {
    KF("kg"),
    CM("cm"),
    L("litro");


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    private Und(String descricao){
        this.descricao = descricao;
    }
}
