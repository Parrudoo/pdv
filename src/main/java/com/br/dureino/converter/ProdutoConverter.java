package com.br.dureino.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import com.br.dureino.dao.ProdutoDao;
import com.br.dureino.model.Produto;
import com.br.dureino.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter<Produto> {

	@Inject
	private ProdutoDao produtoDao;

	public ProdutoConverter() {
		this.produtoDao = CDIServiceLocator.getBean(ProdutoDao.class);
	}

	@Override
	public Produto getAsObject(FacesContext context, UIComponent component, String value) {

		Produto retorno = null;
		if (value != null && !value.isEmpty()) {

			retorno = produtoDao.buscarPeloCodigo(new Long(value));
			return retorno;
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Produto value) {

		if (value != null) {
			Long codigo = ((Produto) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}
		return " ";
	}

}
