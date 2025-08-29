/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author rpalacic
 */
public class Log extends Auditoria {

    public Log() {
    }

    public Log(String accion, String modulo, String opcion, String descripcion, Date fechaHora, String ipPublica, String ipPrivada, String empresaEjecucion, String usuario, String empresaUsuario) {
        this.accion = accion;
        this.modulo = modulo;
        this.opcion = opcion;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.ipPublica = ipPublica;
        this.ipPrivada = ipPrivada;
        this.empresaEjecucion = empresaEjecucion;
        this.usuario = usuario;
        this.empresaUsuario = empresaUsuario;
    }

    private Integer id;
    private String accion;
    private String modulo;
    private String opcion;
    private String descripcion;
    private Date fechaHora;
    private String ipPublica;
    private String ipPrivada;
    private String empresaEjecucion;
    private String usuario;
    private String empresaUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public String getDescripcionCorta() {
        if (descripcion != null && descripcion.length() > 32) {
            return descripcion.substring(0, 32);
        } else {
            return descripcion;
        }
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIpPrivada() {
        return ipPrivada;
    }

    public void setIpPrivada(String ipPrivada) {
        this.ipPrivada = ipPrivada;
    }

    public String getIpPublica() {
        return ipPublica;
    }

    public void setIpPublica(String ipPublica) {
        this.ipPublica = ipPublica;
    }

    public String getEmpresaEjecucion() {
        return empresaEjecucion;
    }

    public void setEmpresaEjecucion(String empresaEjecucion) {
        this.empresaEjecucion = empresaEjecucion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmpresaUsuario() {
        return empresaUsuario;
    }

    public void setEmpresaUsuario(String empresaUsuario) {
        this.empresaUsuario = empresaUsuario;
    }

    @Override
    public String toString() {
        return "Log{" + "id=" + id + ", accion=" + accion + ", modulo=" + modulo + ", opcion=" + opcion + ", descripcion=" + descripcion + ", fechaHora=" + fechaHora + ", ipPublica=" + ipPublica + ", ipPrivada=" + ipPrivada + ", empresaEjecucion=" + empresaEjecucion + ", usuario=" + usuario + ", empresaUsuario=" + empresaUsuario + '}';
    }

}
