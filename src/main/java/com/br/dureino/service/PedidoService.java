package com.br.dureino.service;

import com.br.dureino.dao.PedidoDao;
import com.br.dureino.dto.ItemPedidoDetalheDTO;
import com.br.dureino.dto.PedidoImpressaoDTO;
import com.br.dureino.model.EnderecoEntrega;
import com.br.dureino.model.ItemPedido;
import com.br.dureino.model.Pedido;
import com.br.dureino.util.jpa.Transactional;
import com.br.dureino.util.jsf.FacesUtil;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.view.JasperViewer;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.naming.Context;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PedidoDao pedidoDao;





    @Transactional
    public Pedido salvar(Pedido pedido){

        return pedidoDao.salvar(pedido);

    }

    public PedidoService(){

    }


    @Transactional
    public ItemPedido salvar(ItemPedido itemPedido){
        return pedidoDao.salvar(itemPedido);
    }

    @Transactional
    public void salvar(EnderecoEntrega enderecoEntrega){
         pedidoDao.salvar(enderecoEntrega);
    }


//    public List<Pedido> buscarPedidos(int first, int pageSize) {
//       return pedidoDao.buscarPedido(first, pageSize);
//    }

    public Long buscarPedidos() {
        return pedidoDao.buscarPedido();
    }

    public List<ItemPedidoDetalheDTO> buscarPedidoItemPedidoProduto(Long id) {

       return pedidoDao.buscarPedidoItemPedidoProduto(id);
    }




    public void buscarPedidoParaImpressao(Long id) throws IOException, JRException {

        PedidoImpressaoDTO pedidoImpressaoDTO = pedidoDao.buscarPedidoParaImpressao(id);


            InputStream template = PedidoService.class.getResourceAsStream("/relatorio/pedido.jasper");


            JRDataSource dataSource = new JRBeanCollectionDataSource(pedidoImpressaoDTO.getItemPedidos());

            InputStream logo = PedidoService.class.getResourceAsStream("/relatorio/logo.png");

            Map<String, Object> map = new HashMap<>();

            map.put("logo", logo);
            map.put("pedidoImpressaoDTO",pedidoImpressaoDTO);
            JasperPrint print = JasperFillManager.fillReport(template, map, dataSource);






        final JasperViewer jv = new JasperViewer(print,false);
        jv.setVisible(true);
        jv.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

    }

    @Transactional
    public void deletarItensRevomidos(Long idPedido, Long idItem) {
        pedidoDao.deletarItensRemovidos(idPedido,idItem);
    }


    public Pedido buscarPedidoEdicao(Long id) {

        return pedidoDao.buscarEdicaoPedido(id);
    }
}
