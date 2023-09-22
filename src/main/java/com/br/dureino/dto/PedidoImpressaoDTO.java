package com.br.dureino.dto;

import com.br.dureino.model.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoImpressaoDTO {

    private String endereco;
    private String bairro;
    private String numeroPedido;

    private Date dataEmissao;
    private String vendedor;
    private String nomeCliente;
    private BigDecimal valorPedido;
   private String cidade;



}
