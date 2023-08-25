package com.br.dureino.dao;

import com.br.dureino.dto.ItemPedidoDetalheDTO;
import com.br.dureino.model.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {

    private static final QPedido qPedido = QPedido.pedido;
    private static final QProduto qProduto = QProduto.produto;
    private static final QItemPedido qItemPedido = QItemPedido.itemPedido;

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

    public List<ItemPedidoDetalheDTO> buscarPedidoItemPedidoProduto(Long id) {
        JPAQuery<Tuple> query = new JPAQuery<>(entityManager);

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qPedido.id.eq(id));

           List<Tuple> tuples = query.select(qPedido.id
                                            ,qPedido.numero
                                            ,qItemPedido.qtd
                                            ,qProduto.nome
                                            ,qItemPedido.valorTotal
                                            ,qProduto.valorUnitario)
                                    .from(qPedido)
                                    .innerJoin(qItemPedido)
                                    .on(qPedido.eq(qItemPedido.pedido))
                                    .innerJoin(qProduto)
                                    .on(qProduto.eq(qItemPedido.produto))
                                    .where(builder).fetch();


           List<ItemPedidoDetalheDTO> itemPedidoDetalheDTOS = new ArrayList<>();

             for(Tuple tuple : tuples){
                 ItemPedidoDetalheDTO dto = new ItemPedidoDetalheDTO();
                 dto.setId(tuple.get(qPedido.id));
                 dto.setNumero(tuple.get(qPedido.numero));
                 dto.setQtd(tuple.get(qItemPedido.qtd));
                 dto.setValorTotal(tuple.get(qItemPedido.valorTotal));
                 dto.setValorUnitario(tuple.get(qProduto.valorUnitario));
                 dto.setNomeProduto(tuple.get(qProduto.nome));

                 itemPedidoDetalheDTOS.add(dto);
             }

             return itemPedidoDetalheDTOS;
    }
}
