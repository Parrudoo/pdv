package com.br.dureino.dto;

import com.br.dureino.model.ItemPedido;
import com.br.dureino.model.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoDTO {

    private Long id;

    private Integer numero;

    private Date data;

    private Vendedor vendedor;

    private BigDecimal valorTotal;

    private String pagamento;

    private String nomeProduto;

    private List<ItemPedidoDetalheDTO> itemPedidos;

    private boolean disable;
}
