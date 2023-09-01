package com.br.dureino.controller;

import com.br.dureino.model.Login;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@SessionScoped
@Named
public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;

    private Login login = new Login();


    public LoginController(Login login) {
        this.login = new Login();
    }

    public String logarNoSistema(){


        if (login.getName().equals("admin") && login.getSenha().equals("admin")){

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("usuario",login);


            return "index.xhtml?faces-redirect=true";
        }else{
            return "login.xhtml?faces-redirect=true";
        }
    }


    public String logout(){

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "login.xhtml?faces-redirect=true";

    }


}
