package com.br.dureino.dao;

import com.br.dureino.model.EnderecoEntrega;
import com.br.dureino.model.ItemPedido;
import com.br.dureino.model.Pedido;
import com.br.dureino.model.QPedido;
import com.querydsl.jpa.impl.JPAQuery;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class PedidoDao {

    private static final QPedido qPedido = QPedido.pedido;

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

    public EnderecoEntrega salvar(EnderecoEntrega enderecoEntrega){
        return entityManager.merge(enderecoEntrega);
    }

    public List<Pedido> buscarPedido(int first, int pageSize) {


      return entityManager.createQuery("select p from Pedido p")
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();



    }

    public Long buscarPedido() {

        JPAQuery<Pedido> query = new JPAQuery<>(entityManager);

        Long qtd = query.select(qPedido).from(qPedido).fetchCount();

        return qtd;
    }
}
