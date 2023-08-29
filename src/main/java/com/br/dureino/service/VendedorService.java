package com.br.dureino.service;

import com.br.dureino.dao.VendedorDao;
import com.br.dureino.dto.VendedorDTO;
import com.br.dureino.model.Vendedor;
import com.br.dureino.util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
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

        List<Vendedor> vendedors = vendedorDao.buscar();
        List<VendedorDTO> vendedorDTOS = new ArrayList<>();

        for (Vendedor vendedor : vendedors){

            VendedorDTO vendedorDTO = new VendedorDTO();
            vendedorDTO.setNome(vendedor.getNome());
            vendedorDTOS.add(vendedorDTO);
        }

        return vendedorDTOS;
    }
}
