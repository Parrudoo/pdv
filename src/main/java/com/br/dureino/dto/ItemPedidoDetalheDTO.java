package com.br.dureino.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemPedidoDetalheDTO {

    private Long id;

    private Integer numero;

    private String nomeProduto;

    private String pagamento;

    private Integer qtd;

    private BigDecimal valorUnitario;

    private BigDecimal valorTotal;


}
