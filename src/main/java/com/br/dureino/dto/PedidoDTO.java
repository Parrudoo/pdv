package com.br.dureino.dto;

import com.br.dureino.model.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoDTO {


    private Integer numero;

    private String data;

    private Vendedor vendedor;

    private BigDecimal valorTotal;

    private String pagamento;
}
