/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.saviasaludeps.savia.web.utilidades;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SergioRios
 */
public class SessionValidator implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpSession session = req.getSession();
        Object conexion = session.getAttribute("conexion");
        if(conexion == null) {
            req.getRequestDispatcher("/login.faces?faces-redirect=true").forward(request, response);
        }else{
            if(req.getRequestURL().toString().contains("/login.faces")){
                resp.sendRedirect(req.getContextPath());
            }else{
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
