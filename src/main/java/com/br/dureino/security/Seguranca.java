package com.br.dureino.security;

import com.br.dureino.model.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.enterprise.context.RequestScoped;

import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class Seguranca {


    public Usuario getUsuario(){

        String nome = null;
        UsuarioSistema usuario = getUsuarioLogado();

        Usuario user = new Usuario();
        if (usuario != null){

            user.setNome(usuario.getUsuario().getNome());
            user.setEmail(usuario.getUsuario().getEmail());
            user.setGrupos(usuario.getUsuario().getGrupos());
            user.setId(usuario.getUsuario().getId());
//            nome = usuario.getUsuario().getNome();
        }
            return user;
    }

    private UsuarioSistema getUsuarioLogado() {

        UsuarioSistema usuarioSistema = null;

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)
        FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (token != null && token.getPrincipal() != null){
            usuarioSistema = (UsuarioSistema) token.getPrincipal();
        }
        return usuarioSistema;
    }

}
