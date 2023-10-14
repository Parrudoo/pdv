package com.br.dureino.dao;

import com.br.dureino.model.Usuario;
import com.br.dureino.util.jsf.FacesUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;

public class UsuarioDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Usuario usuarioPorId(Long id){
        return this.manager.find(Usuario.class,id);
    }

    public List<Usuario> vendedores(){
        return this.manager.createQuery("select v from Usuario v").getResultList();
    }

    public Usuario porEmail(String email){
        Usuario usuario = null;
            try{
               usuario = this.manager.createQuery("select u from Usuario u where lower(u.email) =: email",Usuario.class)
                        .setParameter("email",email.toLowerCase()).getSingleResult();
                return usuario;
            }catch (NoResultException e){
                FacesUtil.addErrorMessage("Nenhum resultado encontrado"+e.getMessage());
            }
            return usuario;
    }
}
