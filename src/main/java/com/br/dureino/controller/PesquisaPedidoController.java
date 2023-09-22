package com.br.dureino.controller;

import com.br.dureino.dto.ItemPedidoDetalheDTO;
import com.br.dureino.dto.PedidoDTO;
import com.br.dureino.dto.PedidoImpressaoDTO;
import com.br.dureino.lazy.LazyPedido;
import com.br.dureino.model.ItemPedido;
import com.br.dureino.model.Pedido;
import com.br.dureino.service.PedidoService;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Named
@ViewScoped
public class PesquisaPedidoController  implements Serializable {

    private static final long serialVersionUID = 1L;


    @Inject
    private PedidoService pedidoService;

    private PedidoDTO pedidoSelecionado;


    private List<ItemPedidoDetalheDTO> itemPedidoDetalheDTOS = new ArrayList<>();

    private PedidoDTO pedidoFiltroDTO = new PedidoDTO();

    @Inject
    private LazyPedido lazyPedido;

    public LazyPedido pesquisar(){
        return this.lazyPedido;
    }


    public List<ItemPedidoDetalheDTO> detalheItemPedido(){

        List<ItemPedidoDetalheDTO> detalheDTOS = pedidoService.buscarPedidoItemPedidoProduto(pedidoSelecionado.getId());

        if (!itemPedidoDetalheDTOS.isEmpty()){
            itemPedidoDetalheDTOS = new ArrayList<>();
        }


        for (ItemPedidoDetalheDTO dto : detalheDTOS){
            itemPedidoDetalheDTOS.add(new ItemPedidoDetalheDTO(
                    dto.getId(),
                    dto.getNumero(),
                    dto.getNomeProduto(),
                    dto.getPagamento(),
                    dto.getQtd(),
                    dto.getValorUnitario(),
                    dto.getValorTotal()
            ));

        }

      return this.itemPedidoDetalheDTOS;

    }


    public PedidoImpressaoDTO imprimirPedido(){

        PedidoImpressaoDTO pedidoImpressaoDTO = pedidoService.buscarPedidoParaImpressao(pedidoSelecionado.getId());

        return pedidoImpressaoDTO;
    }

}
