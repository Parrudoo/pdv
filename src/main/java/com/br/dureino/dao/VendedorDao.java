package com.br.dureino.dao;

import com.br.dureino.dto.VendedorDTO;
import com.br.dureino.model.QVendedor;
import com.br.dureino.model.Vendedor;
import com.br.dureino.util.jpa.Transactional;
import com.querydsl.jpa.impl.JPAQuery;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.util.List;

public class VendedorDao {

    private static final QVendedor qVendedor = QVendedor.vendedor;

    @Inject
    private EntityManager entityManager;


    @Transactional
    public Vendedor salvar(Vendedor vendedor){

        return entityManager.merge(vendedor);
    }

    public List<VendedorDTO> buscar() {
        JPAQuery<Vendedor> query = new JPAQuery<>(entityManager);

       List<Vendedor> list = query.select(qVendedor).select(qVendedor).fetch();
        return list;

    }
}
