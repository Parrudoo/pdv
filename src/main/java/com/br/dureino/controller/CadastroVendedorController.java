package com.br.dureino.controller;

import com.br.dureino.dto.VendedorDTO;
import com.br.dureino.model.Vendedor;
import com.br.dureino.model.enums.Estado;
import com.br.dureino.service.VendedorService;
import com.br.dureino.util.jpa.Transactional;
import com.br.dureino.util.jsf.FacesUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.Select;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ViewScoped
@Named
public class CadastroVendedorController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private VendedorService vendedorService;

    private Vendedor vendedor = new Vendedor();

    private List<SelectItem> uf = new ArrayList<>();

    private List<SelectItem> items = new ArrayList<>();



    public List<SelectItem> getUf(){
        for (Estado estado : Estado.values()){

            uf.add(new SelectItem(estado.getDescricao(), estado.getDescricao()));
        }
        return uf;
    }


    public List<SelectItem> getItems(){

        List<VendedorDTO> vendedorDTOS = buscar();
        for (VendedorDTO vendedorDTO : vendedorDTOS){

            items.add(new SelectItem(vendedorDTO.getNome(),vendedorDTO.getNome()));
        }

        return items;
    }

    public void salvar(){

        try {
            vendedorService.salvar(vendedor);
            FacesUtil.addSucessoMessage("Vendedor:"+vendedor.getNome()+"Cadastrado com sucesso!");

        }catch (Exception e){
            FacesUtil.addErrorMessage("Falha!");
        }


    }

    public List<VendedorDTO> buscar(){

      return  vendedorService.buscar();
    }

}
