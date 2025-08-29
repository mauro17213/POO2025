/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author NEXOS
 */
public class AuNoSolicitudEntregaCarga extends Auditoria{
    
    //Estados
    public static final int ESTADO_EN_COLA = 0;
    public static final int ESTADO_EN_PROCESO = 1;
    public static final int ESTADO_PROCESADO = 2;
    public static final int ESTADO_CANCELADO = 3;
    public static final int ESTADO_PROCESADO_INCOMPLETO = 4;
    
    private Integer id;
    private Empresa gnEmpresasId;
    private String nombre;
    private String ruta;
    private String archivo;
    private boolean existe;
    private int estado;
    private Integer registros;
    private Integer exitosos;
    private Integer fallidos;
    private String detalle;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private Integer tipoTecnologia;
    
     //variable adicionales
    private transient InputStream adjuntoStream;
    private transient InputStream adjuntoStreamValidaciones;
    
    public AuNoSolicitudEntregaCarga() {
    }

    public AuNoSolicitudEntregaCarga(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(Empresa gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getRegistros() {
        return registros;
    }

    public void setRegistros(Integer registros) {
        this.registros = registros;
    }

    public Integer getExitosos() {
        return exitosos;
    }

    public void setExitosos(Integer exitosos) {
        this.exitosos = exitosos;
    }

    public Integer getFallidos() {
        return fallidos;
    }

    public void setFallidos(Integer fallidos) {
        this.fallidos = fallidos;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    public InputStream getAdjuntoStreamValidaciones() {
        return adjuntoStreamValidaciones;
    }

    public void setAdjuntoStreamValidaciones(InputStream adjuntoStreamValidaciones) {
        this.adjuntoStreamValidaciones = adjuntoStreamValidaciones;
    }
    
    //Metodo auxiliar
    //metodos aux
    public String getEstadoStr() {
        String est = "";
        switch (estado) {
            case ESTADO_EN_COLA:
                est = "En Cola";
                break;
            case ESTADO_EN_PROCESO:
                est = "En Proceso";
                break;
            case ESTADO_PROCESADO:
                est = "Procesado";
                break;
            case ESTADO_CANCELADO:
                est = "Cancelado";
                break;
            case ESTADO_PROCESADO_INCOMPLETO:
                est = "Procesado Incompleto";
                break;
        }
        return est;
    }
    
    
    //Metodo auxiliar
    public String getTipoTecnologiaStr() {
        String tipo = "";
        switch (tipoTecnologia) {
            case 1:
                tipo = "Procedimiento";
                break;
            case 2:
                tipo = "Medicamento";
                break;
            case 3:
                tipo = "Insumo";
                break;
            case 4:
                tipo = "Paquete";
                break;
        }
        return tipo;
    }
    
     public String getGenerarNombreArchivo() {
        SimpleDateFormat df = new SimpleDateFormat("YYYYMMddHHmmss");
        int indiceExtension = getNombre().lastIndexOf(".");
        String ext = getNombre().substring(indiceExtension, getNombre().length());
        archivo = "carga_entrega_sin_autorizaciones" + "_" + df.format(new Date()) + ext;
        return archivo;
    }
    
    @Override
    public String toString() {
        return "AuNoSolicitudEntregaCarga{" + "id=" + id + ", gnEmpresasId=" + gnEmpresasId + ", nombre=" + nombre + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" + existe + ", estado=" + estado + ", registros=" + registros + ", exitosos=" + exitosos + ", fallidos=" + fallidos + ", detalle=" + detalle + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", tipoTecnologia=" + tipoTecnologia + '}';
    }

}
