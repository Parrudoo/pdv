package com.br.dureino.controller;

import com.br.dureino.dto.PedidoDTO;
import com.br.dureino.lazy.LazyPedido;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Getter
@Setter
@Named
@ViewScoped
public class PesquisaPedidoController  implements Serializable {

    private static final long serialVersionUID = 1L;





    private PedidoDTO pedidoFiltroDTO = new PedidoDTO();

    @Inject
    private LazyPedido lazyPedido;






}
