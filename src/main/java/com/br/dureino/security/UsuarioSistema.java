package com.br.dureino.security;

import com.br.dureino.dao.UsuarioDao;
import com.br.dureino.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class UsuarioSistema extends User {

    private Usuario usuario;

    public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getNome(), usuario.getSenha(), authorities);
        this.usuario = usuario;
    }



    public Usuario getUsuario() {
        return usuario;
    }


}
