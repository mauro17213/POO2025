/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.GregorianCalendar;

/**
 *
 * @author rpalacic
 */
public class Login extends Auditoria {

    public Login() {
    }
    
    private Usuario usuario = new Usuario();
    private Empresa empresa = new Empresa();
    private GregorianCalendar fechaHoraConexion = null;
    private boolean conectado = false;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public GregorianCalendar getFechaHoraConexion() {
        return fechaHoraConexion;
    }

    public void setFechaHoraConexion(GregorianCalendar fechaHoraConexion) {
        this.fechaHoraConexion = fechaHoraConexion;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }
    
}
