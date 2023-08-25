package com.br.dureino.service;

import com.br.dureino.dao.PedidoDao;
import com.br.dureino.dto.ItemPedidoDetalheDTO;
import com.br.dureino.model.EnderecoEntrega;
import com.br.dureino.model.ItemPedido;
import com.br.dureino.model.Pedido;
import com.br.dureino.util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class PedidoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PedidoDao pedidoDao;


    @Transactional
    public Pedido salvar(Pedido pedido){

        return pedidoDao.salvar(pedido);

    }

    @Transactional
    public ItemPedido salvar(ItemPedido itemPedido){
        return pedidoDao.salvar(itemPedido);
    }

    @Transactional
    public EnderecoEntrega salvar(EnderecoEntrega enderecoEntrega){
        return pedidoDao.salvar(enderecoEntrega);
    }


    public List<Pedido> buscarPedidos(int first, int pageSize) {
       return pedidoDao.buscarPedido(first, pageSize);
    }

    public Long buscarPedidos() {
        return pedidoDao.buscarPedido();
    }

    public List<ItemPedidoDetalheDTO> buscarPedidoItemPedidoProduto(Long id) {

       return pedidoDao.buscarPedidoItemPedidoProduto(id);
    }
}
