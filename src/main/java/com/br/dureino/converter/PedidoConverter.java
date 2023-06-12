package com.br.dureino.converter;

import com.br.dureino.dao.PedidoDao;
import com.br.dureino.model.Pedido;
import com.br.dureino.model.enums.StatusPedido;
import com.br.dureino.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter<Pedido> {

    private PedidoDao pedidoDao;

    public PedidoConverter() {
        this.pedidoDao = CDIServiceLocator.getBean(PedidoDao.class);
    }

    @Override
    public Pedido getAsObject(FacesContext context, UIComponent component, String value) {
        Pedido retorno = null;
        if (value != null){
             retorno = pedidoDao.buscarPeloCodigo(new Long(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Pedido value) {

        if (value != null){
          Long codigo = ((Pedido) value).getId();
          String retorno = (codigo == null ? null : codigo.toString());

        return retorno;
        }

        return "";
    }
}
