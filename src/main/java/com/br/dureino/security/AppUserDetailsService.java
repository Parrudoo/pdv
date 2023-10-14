package com.br.dureino.security;

import com.br.dureino.dao.UsuarioDao;
import com.br.dureino.model.Grupo;
import com.br.dureino.model.Usuario;
import com.br.dureino.util.cdi.CDIServiceLocator;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AppUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UsuarioDao usuarioDao = CDIServiceLocator.getBean(UsuarioDao.class);

       Usuario usuario = usuarioDao.porEmail(email);
            User user = null;
        if (usuario != null){

          user = new UsuarioSistema(usuario,getGrupos(usuario));

        }
        return user;
    }

    private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Grupo grupo : usuario.getGrupos()){
            authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
        }

        return authorities;
    }


}
