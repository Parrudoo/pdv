package com.br.dureino.controller;

import com.br.dureino.util.jsf.FacesUtil;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Getter
@Setter
@SessionScoped
@Named
public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;


    @Inject
    private FacesContext facesContext;

    @Inject
    private HttpServletRequest request;

    @Inject
    private HttpServletResponse response;

    private String email;

    public void preRender(){
        if("true".equals(request.getParameter("invalid"))){
            FacesUtil.addErrorMessage("Usuário ou senha inválido!");
        }
    }

    public void login() throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward(request,response);

        facesContext.getResponseComplete();
    }

}
