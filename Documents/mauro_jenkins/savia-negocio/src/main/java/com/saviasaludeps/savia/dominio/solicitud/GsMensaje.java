/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.solicitud;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author jramirez
 */
public class GsMensaje extends Auditoria {

    private Integer id;
    private String nombre;
    private int tipo;
    private int estado;
    private String encabezado;
    private String mensajeLargo;
    private String mensajeCorto;
    private List<GsSolicitud> listaGsSolicitudes;

    public GsMensaje() {
    }

    public GsMensaje(Integer id) {
        this.id = id;
    }

    public GsMensaje(Integer id, String nombre, int tipo, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getMensajeLargo() {
        return mensajeLargo;
    }

    public void setMensajeLargo(String mensajeLargo) {
        this.mensajeLargo = mensajeLargo;
    }

    public String getMensajeCorto() {
        return mensajeCorto;
    }

    public void setMensajeCorto(String mensajeCorto) {
        this.mensajeCorto = mensajeCorto;
    }

    public List<GsSolicitud> getListaGsSolicitudes() {
        return listaGsSolicitudes;
    }

    public void setListaGsSolicitudes(List<GsSolicitud> listaGsSolicitudes) {
        this.listaGsSolicitudes = listaGsSolicitudes;
    } 

    @Override
    public String toString() {
        return "GsMensaje{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", estado=" + estado + ", encabezado=" + encabezado + ", mensajeLargo=" + mensajeLargo + ", mensajeCorto=" + mensajeCorto + '}';
    }
}
