package com.br.dureino.filter;

import com.br.dureino.model.Login;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class FilterConfig implements Filter {
    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = (HttpSession) req.getSession();

        Login login = (Login) session.getAttribute("usuario");

        if (login == null){
           chain.doFilter(request,response);
        }else {
            res.sendRedirect(req.getContextPath() + "index.xhtml");
        }
    }

    @Override
    public void destroy() {

    }
}
//https://www.youtube.com/watch?v=NNTWFIKiUmI