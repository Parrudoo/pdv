package com.br.dureino.lazy;

import com.br.dureino.dao.PedidoDao;
import com.br.dureino.dto.PedidoDTO;
import com.br.dureino.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LazyPedido extends LazyDataModel<PedidoDTO> {

    @Inject
    private PedidoDao pedidoDao;

    private PedidoDTO pedidoFiltroDTO = new PedidoDTO();

    public LazyPedido(PedidoDao pedidoDao){
        this.pedidoDao = pedidoDao;
    }


    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return 0;
    }

    @Override
    public List<PedidoDTO> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {




        List<PedidoDTO> pedidos = pedidoDao.buscarPedido(first, pageSize, pedidoFiltroDTO );

//        List<PedidoDTO> pedidoFiltroDTOS = converter(pedidos);

        int qtd = pedidoDao.buscarPedido().intValue();
        this.setRowCount(qtd);

        return pedidos;
    }

    /*private List<PedidoDTO> converter(List<Pedido> pedidos) {

        List<PedidoDTO> pedidoFiltroDTOS = new ArrayList<>();

        for (Pedido pedido : pedidos){
            PedidoDTO pedidoFiltroDTO = new PedidoDTO();

            pedidoFiltroDTO.setId(pedido.getId());
            pedidoFiltroDTO.setVendedor(pedido.getVendendor());
            pedidoFiltroDTO.setNumero(pedido.getNumero());
            pedidoFiltroDTO.setData(pedido.getDataCriacao());

            pedidoFiltroDTOS.add(pedidoFiltroDTO);
        }

        return pedidoFiltroDTOS;
    }*/
}
