package com.br.dureino.dto;

import com.br.dureino.model.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Getter
@Setter
public class PedidoImpressaoDTO {

    private Long codigo;
    private String endereco;
    private String bairro;
    private String numeroPedido;

    private Date dataEmissao;
    private String vendedor;
    private String nomeCliente;
    private BigDecimal valorPedido;
    private String cidade;
    private Integer qtdItem;
    private Integer qtdTotalItens;
    private String descricao;
    private BigDecimal valorItem;
    private List<ItemPedido> itemPedidos = new ArrayList<>();






}
