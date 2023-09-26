package com.br.dureino.controller;

import com.br.dureino.dto.ItemPedidoDetalheDTO;
import com.br.dureino.dto.PedidoDTO;
import com.br.dureino.dto.PedidoImpressaoDTO;
import com.br.dureino.lazy.LazyPedido;
import com.br.dureino.service.PedidoService;

import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

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

    public void imprimirPedido() throws IOException, JRException {


        pedidoService.buscarPedidoParaImpressao(pedidoSelecionado.getId());


    }



}
