/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.webservice;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author Fabian Coronel
 */
public class WsServicio extends Auditoria{

    private Integer id;
    private String nombre;
    private String descripcion;
    private int tiempoCont;
    private boolean activo;
    private List<WsConexionServicio> listaWsConexiones;
    private List<WsTransaccion> listasWsTransacciones;

    public WsServicio() {
    }

    public WsServicio(Integer id) {
        this.id = id;
    }

    public WsServicio(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public WsServicio(Integer id, String nombre, String descripcion, int tiempoCont, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempoCont = tiempoCont;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTiempoCont() {
        return tiempoCont;
    }

    public void setTiempoCont(int tiempoCont) {
        this.tiempoCont = tiempoCont;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<WsConexionServicio> getListaWsConexiones() {
        return listaWsConexiones;
    }

    public void setListaWsConexiones(List<WsConexionServicio> listaWsConexiones) {
        this.listaWsConexiones = listaWsConexiones;
    }

    public List<WsTransaccion> getListasWsTransacciones() {
        return listasWsTransacciones;
    }

    public void setListasWsTransacciones(List<WsTransaccion> listasWsTransacciones) {
        this.listasWsTransacciones = listasWsTransacciones;
    }
    
}
