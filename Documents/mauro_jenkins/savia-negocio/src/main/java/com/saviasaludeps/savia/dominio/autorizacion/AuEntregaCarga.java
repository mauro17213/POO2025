/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public class AuEntregaCarga extends Auditoria {

    //Estados
    public static final int ESTADO_EN_COLA = 0;
    public static final int ESTADO_EN_PROCESO = 1;
    public static final int ESTADO_PROCESADO = 2;
    public static final int ESTADO_CANCELADO = 3;
    public static final int ESTADO_PROCESADO_INCOMPLETO = 4;

    //TEXTOS
    public static final String EN_COLA = "En Cola";
    public static final String EN_PROCESO = "En Proceso";
    public static final String PROCESADO = "Procesado";
    public static final String CANCELADO = "Cancelado";
    public static final String PROCESADO_INCOMPLETO = "Procesado Incompleto";
    
    //Tipo tecnologia
    public static final int TIPO_TECNOLOGIA_PROCEDIMIENTO = 1;
    public static final int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
    public static final int TIPO_TECNOLOGIA_INSUMO = 3;
    public static final int TIPO_TECNOLOGIA_PAQUETE = 4;
    
    //Tipo tecnologia texto
    public static final String TIPO_TECNOLOGIA_PROCEDIMIENTO_STR = "Procedimiento";
    public static final String TIPO_TECNOLOGIA_MEDICAMENTO_STR = "Medicamento";
    public static final String TIPO_TECNOLOGIA_INSUMO_STR = "Insumo";
    public static final String TIPO_TECNOLOGIA_PAQUETE_STR = "Paquete";
    
    //Columnas archivo
    public static final int COLUMNA_NO_PRESTADO = 11;
    
    private Integer id;
    private String nombre;
    private String ruta;
    private String archivo;
    private int estado;
    private Integer registros;
    private Integer exitosos;
    private Integer fallidos;
    private String detalle;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private Integer empresa;
    private int tipoTecnologia;

    //Variables auxiliares
    private transient InputStream adjuntoStream;

    private List<AuEntregaCargaDetalle> auEntregaCargaDetalleList;

    public AuEntregaCarga() {
    }

    public AuEntregaCarga(Integer id) {
        this.id = id;
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

    public List<AuEntregaCargaDetalle> getAuEntregaCargaDetalleList() {
        return auEntregaCargaDetalleList;
    }

    public void setAuEntregaCargaDetalleList(List<AuEntregaCargaDetalle> auEntregaCargaDetalleList) {
        this.auEntregaCargaDetalleList = auEntregaCargaDetalleList;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    //metodos aux
    public String getEstadoStr() {
        String est = "";
        switch (estado) {
            case ESTADO_EN_COLA:
                est = EN_COLA;
                break;
            case ESTADO_EN_PROCESO:
                est = EN_PROCESO;
                break;
            case ESTADO_PROCESADO:
                est = PROCESADO;
                break;
            case ESTADO_CANCELADO:
                est = CANCELADO;
                break;
            case ESTADO_PROCESADO_INCOMPLETO:
                est = PROCESADO_INCOMPLETO;
                break;
        }
        return est;
    }

    @Override
    public String toString() {
        return "AuEntregaCarga{" + "id=" + id + ", nombre=" + nombre + ", ruta=" + ruta + ", archivo=" + archivo + ", estado=" + estado + ", registros=" + registros + ", exitosos=" + exitosos + ", fallidos=" + fallidos + ", detalle=" + detalle + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", auEntregaCargaDetalleList=" + auEntregaCargaDetalleList + '}';
    }

    /**
     * @return the tipoTecnologia
     */
    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    /**
     * @param tipoTecnologia the tipoTecnologia to set
     */
    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }
    
    public String getTipoTecnologiaStr() {
        String texto = "";
        switch(tipoTecnologia) {
            case TIPO_TECNOLOGIA_PROCEDIMIENTO:
                texto = TIPO_TECNOLOGIA_PROCEDIMIENTO_STR;
            break;
            case TIPO_TECNOLOGIA_MEDICAMENTO:
                texto = TIPO_TECNOLOGIA_MEDICAMENTO_STR;
            break;
            case TIPO_TECNOLOGIA_INSUMO:
                texto = TIPO_TECNOLOGIA_INSUMO_STR;
            break;
            case TIPO_TECNOLOGIA_PAQUETE:
                texto = TIPO_TECNOLOGIA_PAQUETE_STR;
            break;
        }
        return texto;
    }

}
