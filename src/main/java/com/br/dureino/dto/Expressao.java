package com.br.dureino.dto;

import lombok.Getter;
import lombok.Setter;
import org.jfree.util.StringUtils;

@Getter
@Setter
public class Expressao {

    private String parametro;
    private String valor;
    private String formula;

    public Expressao() {
        super();
    }

    public Expressao(String parametroResultado, String valor, String formula) {
        this();
        this.parametro = parametroResultado;
        this.valor = valor;
        this.formula = formula;
    }

    public String[] getVariaveis() {
        if(!formula.isEmpty()) {
            return formula.replaceAll("\\+", " ")
                    .replaceAll("\\-", " ")
                    .replaceAll("\\*", " ")
                    .replaceAll("/", " ")
                    .replaceAll("\\(", " ")
                    .replaceAll("\\)", " ")
                    .replaceAll("[ ]{2,}", " ")
                    .trim()
                    .split(" ");
        }
        else {
            return new String[0];
        }
    }

    @Override
    public String toString() {
        return parametro + " = " + formula;
    }
}
