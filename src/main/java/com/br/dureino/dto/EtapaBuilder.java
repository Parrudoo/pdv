package com.br.dureino.dto;

import java.math.BigDecimal;

public class EtapaBuilder {

    private EtapaDTO etapa;

    public EtapaBuilder(EtapaDTO etapa) {
        super();
        this.etapa = etapa;
    }

    public EtapaBuilder addComentario(String comentario) {
        etapa.setComentario(comentario);
        return this;
    }

    public EtapaBuilder setExpressao(String parametro, BigDecimal valor, String formula) {
        String str = valor != null ? valor.toString() : "";
        etapa.setExpressao(new Expressao(parametro, str, formula));
        return this;
    }
}
