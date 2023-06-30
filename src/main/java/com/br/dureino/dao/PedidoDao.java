package com.br.dureino.dao;

import com.br.dureino.model.ItemPedido;
import com.br.dureino.model.Pedido;
import com.br.dureino.model.QPedido;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PedidoDao {

    @Inject
    private EntityManager entityManager;


    public Pedido buscarPeloCodigo(Long codigo){
        return  entityManager.find(Pedido.class,codigo);
    }


    public ItemPedido salvar(ItemPedido itemPedido){
        return entityManager.merge(itemPedido);
    }



    public Pedido salvar(Pedido pedido) {

       return entityManager.merge(pedido);

    }
}
