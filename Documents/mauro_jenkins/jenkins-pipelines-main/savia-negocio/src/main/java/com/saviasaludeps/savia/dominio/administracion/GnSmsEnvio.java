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
 * @author sgiraldov
 */
public class GnSmsEnvio extends Auditoria {
    
    public static final int ORIGEN_AUTORIZACIONES = 1;
    public static final int ORIGEN_MIPRES = 2;
    
    public static final int ESTADO_CREADO = 0;
    public static final int ESTADO_ENVIADO = 1;
    public static final int ESTADO_FALLIDO = 2;
    
    private Integer id;
    private int origen;
    private String celulares;
    private String texto;
    private int estado;
    private Date fechaHoraEnvio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public String getCelulares() {
        return celulares;
    }

    public void setCelulares(String celulares) {
        this.celulares = celulares;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }
    
    
    
}
