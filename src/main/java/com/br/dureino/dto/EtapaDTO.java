package com.br.dureino.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class EtapaDTO {



    private String nome;
    private Expressao expressao;
    private String comentario;

    public EtapaDTO(String nome){
            this.nome = nome;
    }

}
