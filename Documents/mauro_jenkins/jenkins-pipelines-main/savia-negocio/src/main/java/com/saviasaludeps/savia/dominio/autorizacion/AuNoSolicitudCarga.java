/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NEXOS
 */
public class AuNoSolicitudCarga extends Auditoria{
    
    //estados
    public static final int ESTADO_EN_COLA = 0;
    public static final int ESTADO_VALIDACION_ESTRUCTURA_PROCESO = 1;
    public static final int ESTADO_VALIDACION_ESTRUCTURA_ERROR = 2;
    public static final int ESTADO_VALIDACION_ESTRUCTURA_OK = 3;
    public static final int ESTADO_VALIDACION_NORMATIVA_PROCESO = 4;
    public static final int ESTADO_VALIDACION_NORMATIVA_ERROR = 5;
    public static final int ESTADO_VALIDACION_NORMATIVA_OK = 6;
    public static final int ESTADO_ENVIO_PROCESO = 7;
    public static final int ESTADO_ENVIO_ERROR = 8;
    public static final int ESTADO_ENVIO_OK = 9;
    
    private Integer id;
    private Empresa gnEmpresasId;
    private CntPrestadorSede cntPrestadorSedesId;
    private int estado;
    private String estadoObservacion;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private int registrosTotal;
    private Integer registrosExitosos;
    private Integer registrosRechazados;
    private String nombreArchivo;
    private String ruta;
    private String archivo;
    private boolean existe;
    private String usuarioGestionEstado;
    private String terminalGestionEstado;
    private Date fechaHoraGestionEstado;
    
    private List<AuNoSolicitudCargaSuceso> auNoSolicitudCargaSucesosList;
    private List<AuNoSolicitudCargaDetalle> auNoSolicitudCargaDetallesList;
    
    //variable adicionales
    private transient InputStream adjuntoStream;
    
    public AuNoSolicitudCarga(){
        
    }
    
    public AuNoSolicitudCarga(Integer id){
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

    public CntPrestadorSede getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSede cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEstadoObservacion() {
        return estadoObservacion;
    }

    public void setEstadoObservacion(String estadoObservacion) {
        this.estadoObservacion = estadoObservacion;
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

    public int getRegistrosTotal() {
        return registrosTotal;
    }

    public void setRegistrosTotal(int registrosTotal) {
        this.registrosTotal = registrosTotal;
    }

    public Integer getRegistrosExitosos() {
        return registrosExitosos;
    }

    public void setRegistrosExitosos(Integer registrosExitosos) {
        this.registrosExitosos = registrosExitosos;
    }

    public Integer getRegistrosRechazados() {
        return registrosRechazados;
    }

    public void setRegistrosRechazados(Integer registrosRechazados) {
        this.registrosRechazados = registrosRechazados;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
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

    public String getUsuarioGestionEstado() {
        return usuarioGestionEstado;
    }

    public void setUsuarioGestionEstado(String usuarioGestionEstado) {
        this.usuarioGestionEstado = usuarioGestionEstado;
    }

    public String getTerminalGestionEstado() {
        return terminalGestionEstado;
    }

    public void setTerminalGestionEstado(String terminalGestionEstado) {
        this.terminalGestionEstado = terminalGestionEstado;
    }

    public Date getFechaHoraGestionEstado() {
        return fechaHoraGestionEstado;
    }

    public void setFechaHoraGestionEstado(Date fechaHoraGestionEstado) {
        this.fechaHoraGestionEstado = fechaHoraGestionEstado;
    }

    public List<AuNoSolicitudCargaSuceso> getAuNoSolicitudCargaSucesosList() {
        return auNoSolicitudCargaSucesosList;
    }

    public void setAuNoSolicitudCargaSucesosList(List<AuNoSolicitudCargaSuceso> auNoSolicitudCargaSucesosList) {
        this.auNoSolicitudCargaSucesosList = auNoSolicitudCargaSucesosList;
    }

    public List<AuNoSolicitudCargaDetalle> getAuNoSolicitudCargaDetallesList() {
        return auNoSolicitudCargaDetallesList;
    }

    public void setAuNoSolicitudCargaDetallesList(List<AuNoSolicitudCargaDetalle> auNoSolicitudCargaDetallesList) {
        this.auNoSolicitudCargaDetallesList = auNoSolicitudCargaDetallesList;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }
    
    // Metodo adcionales
    public String getEstadoStr(){
        String nombreEstado = "";
        switch(estado){
            case ESTADO_EN_COLA:
                nombreEstado = "En Cola";
                break;
            case ESTADO_VALIDACION_ESTRUCTURA_PROCESO:
                nombreEstado = "Validación Estructura Proceso";
                break;
            case ESTADO_VALIDACION_ESTRUCTURA_ERROR:
                nombreEstado = "Validación Estructura Error";
                break;
            case ESTADO_VALIDACION_ESTRUCTURA_OK:
                nombreEstado = "Validación Estructura OK";
                break;
            case ESTADO_VALIDACION_NORMATIVA_PROCESO:
                nombreEstado = "Validación Normativa Proceso";
                break;
            case ESTADO_VALIDACION_NORMATIVA_ERROR:
                nombreEstado = "Validación Normativa Error";
                break;
            case ESTADO_VALIDACION_NORMATIVA_OK:
                nombreEstado = "Validación Normativa OK";
                break;
            case ESTADO_ENVIO_PROCESO:
                nombreEstado = "Envío Proceso";
                break;
            case ESTADO_ENVIO_ERROR:
                nombreEstado = "Envío Error";
                break;
            case ESTADO_ENVIO_OK:
                nombreEstado = "Envío Ok";
                break;
             default:
                break;
        }     
        return nombreEstado;
    }
    
    public String getGenerarNombreArchivo() {
        SimpleDateFormat df = new SimpleDateFormat("YYYYMMddHHmmss");
        int indiceExtension = getNombreArchivo().lastIndexOf(".");
        String ext = getNombreArchivo().substring(indiceExtension, getNombreArchivo().length());
        archivo = "carga_sin_autorizaciones" + "_" + df.format(new Date()) + ext;
        return archivo;
    }
    
    @Override
    public String toString() {
        return "AuNoSolicitudCarga{" + "id=" + id + ", gnEmpresasId=" + gnEmpresasId + ", cntPrestadorSedesId=" + cntPrestadorSedesId + ", estado=" + estado + ", estadoObservacion=" + estadoObservacion + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", registrosTotal=" + registrosTotal + ", registrosExitosos=" + registrosExitosos + ", registrosRechazados=" + registrosRechazados + ", nombreArchivo=" + nombreArchivo + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" + existe + ", usuarioGestionEstado=" + usuarioGestionEstado + ", terminalGestionEstado=" + terminalGestionEstado + ", fechaHoraGestionEstado=" + fechaHoraGestionEstado + '}';
    }
}
