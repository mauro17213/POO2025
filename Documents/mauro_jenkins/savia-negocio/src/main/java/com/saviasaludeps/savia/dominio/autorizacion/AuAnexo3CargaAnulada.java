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
import java.util.List;

/**
 *
 * @author NEXOS
 */
public class AuAnexo3CargaAnulada extends Auditoria{
    
    //estados
    public static final int ESTADO_EN_COLA = 0;
    public static final int ESTADO_PROCESANDO = 1;
    public static final int ESTADO_PROCESADO = 2;
    public static final int ESTADO_CANCELADO = 3;
    public static final int ESTADO_PROCESADO_INCOMPLETO = 4;
   
    
    private Integer id;
    private Empresa gnEmpresasId;
    private int estado;
    private String nombre;
    private String ruta;
    private String archivo;
    private boolean existe;
    private int registros;
    private Integer exitosos;
    private Integer fallidos;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private String estadoObservacion;
    
    //private List<AuAnexo3CargaAnuladaSuceso> auAnexo3CargaAnuladaSucesosList;
    
    //variable adicionales
    private transient InputStream adjuntoStream;
    
    public AuAnexo3CargaAnulada(){
        
    }
    
    public AuAnexo3CargaAnulada(Integer id){
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

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
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

    public String getEstadoObservacion() {
        return estadoObservacion;
    }

    public void setEstadoObservacion(String estadoObservacion) {
        this.estadoObservacion = estadoObservacion;
    }

    /*public List<AuAnexo3CargaAnuladaSuceso> getAuAnexo3CargaAnuladaSucesosList() {
        return auAnexo3CargaAnuladaSucesosList;
    }

    public void setAuAnexo3CargaAnuladaSucesosList(List<AuAnexo3CargaAnuladaSuceso> auAnexo3CargaAnuladaSucesosList) {
        this.auAnexo3CargaAnuladaSucesosList = auAnexo3CargaAnuladaSucesosList;
    }*/

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
            case ESTADO_PROCESANDO:
                nombreEstado = "Procesando";
                break;
            case ESTADO_PROCESADO:
                nombreEstado = "Procesado";
                break;
            case ESTADO_CANCELADO:
                nombreEstado = "Cancelado";
                break;
            case ESTADO_PROCESADO_INCOMPLETO:
                nombreEstado = "Procesado Incompleto";
                break;
             default:
                break;
        }     
        return nombreEstado;
    }
    
    public String getGenerarNombreArchivo() {
        SimpleDateFormat df = new SimpleDateFormat("YYYYMMddHHmmss");
        int indiceExtension = getNombre().lastIndexOf(".");
        String ext = getNombre().substring(indiceExtension, getNombre().length());
        archivo = "carga_anulada_solicitudes" + "_" + df.format(new Date()) + ext;
        return archivo;
    }

    @Override
    public String toString() {
        return "AuAnexo4CargaAnulada{" + "id=" + id + ", gnEmpresasId=" + gnEmpresasId + ", estado=" + estado + ", nombre=" + nombre + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" + existe + ", registros=" + registros + ", exitosos=" + exitosos + ", fallidos=" + fallidos + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", estadoObservacion=" + estadoObservacion + '}';
    }    
}
