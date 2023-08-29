package com.br.dureino.service;

import com.br.dureino.dao.VendedorDao;
import com.br.dureino.dto.VendedorDTO;
import com.br.dureino.model.Vendedor;
import com.br.dureino.util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class VendedorService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private VendedorDao vendedorDao;



    @Transactional
    public Vendedor salvar(Vendedor vendedor){

        return vendedorDao.salvar(vendedor);
    }

    public List<VendedorDTO> buscar() {
        return vendedorDao.buscar();
    }
}
