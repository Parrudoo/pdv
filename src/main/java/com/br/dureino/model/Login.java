package com.br.dureino.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;

@Getter
@Setter
public class Login {

    private String name;

    private String senha;
}
