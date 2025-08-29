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
public class GnAlerta extends Auditoria {
    
    //ESTADOS
    public static final int SEVERIDAD_MENSAJE = 1;
    public static final int SEVERIDAD_ADVERTENCIA = 2;
    public static final int SEVERIDAD_FALLA = 3;
    
    public static final int ESTADO_GENERADO = 1;
    public static final int ESTADO_LEIDO = 2;
    public static final int ESTADO_DESCARTADO = 0;    
    
    //TEXTOS
    public static final String MENSAJE = "Mensaje";
    public static final String ADVERTENCIA = "Advertencia";
    public static final String FALLA = "Falla";
    public static final String GENERADO = "Generado";
    public static final String LEIDO = "Leido";
    public static final String DESCARTADO = "Descartado";
    
    private Integer id;
    private int severidad;
    private int estado;
    private String nombre;
    private String descripcion;
    private Date fechaHoraLee;
    private Date fechaHoraDescarta;
    private Usuario gnUsuarioId;

    public GnAlerta() {
    }    

    public GnAlerta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSeveridad() {
        return severidad;
    }

    public void setSeveridad(int severidad) {
        this.severidad = severidad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public Date getFechaHoraLee() {
        return fechaHoraLee;
    }

    public void setFechaHoraLee(Date fechaHoraLee) {
        this.fechaHoraLee = fechaHoraLee;
    }

    public Date getFechaHoraDescarta() {
        return fechaHoraDescarta;
    }

    public void setFechaHoraDescarta(Date fechaHoraDescarta) {
        this.fechaHoraDescarta = fechaHoraDescarta;
    }

    public Usuario getGnUsuarioId() {
        return gnUsuarioId;
    }

    public void setGnUsuarioId(Usuario gnUsuarioId) {
        this.gnUsuarioId = gnUsuarioId;
    }
    
    //Metodos
    public String getSeveridadStr() {
        switch(getSeveridad()) {
            case SEVERIDAD_MENSAJE:
                return MENSAJE;
            case SEVERIDAD_ADVERTENCIA:
                return ADVERTENCIA;
            case SEVERIDAD_FALLA:
                return FALLA;
        }
        return "";
    }
    
    public String getEstadoStr() {
        switch(getEstado()) {
            case ESTADO_GENERADO:
                return GENERADO;
            case ESTADO_LEIDO:
                return LEIDO;
            case ESTADO_DESCARTADO:
                return DESCARTADO;
        }
        return "";
    }

    @Override
    public String toString() {
        return "GnAlerta{" + "id=" + id + ", severidad=" + severidad + ", estado=" + estado + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fechaHoraLee=" + fechaHoraLee + ", fechaHoraDescarta=" + fechaHoraDescarta + ", gnUsuarioId=" + gnUsuarioId + '}';
    }
    
}
