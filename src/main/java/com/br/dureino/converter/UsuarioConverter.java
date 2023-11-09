package com.br.dureino.converter;

import com.br.dureino.dao.UsuarioDao;
import com.br.dureino.model.Usuario;
import com.br.dureino.util.cdi.CDIServiceLocator;
import org.jboss.weld.bootstrap.api.CDI11Bootstrap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter<Usuario> {

    @Inject
    private UsuarioDao usuarioDao;

    public UsuarioConverter(){
        this.usuarioDao = CDIServiceLocator.getBean(UsuarioDao.class);
    }

    @Override
    public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
        Usuario usuario = null;
        if (value != null){

           return usuarioDao.usuarioPorId(new Long(value));

        }
        return usuario;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Usuario value) {

        if (value != null){

            Long codigo = ((Usuario) value).getId();
            String retorno = (codigo != null ? codigo.toString() : null);

            return retorno;
        }
        return "";
    }
}
