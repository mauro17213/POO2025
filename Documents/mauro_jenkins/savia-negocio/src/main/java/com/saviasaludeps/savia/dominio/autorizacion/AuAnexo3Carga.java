/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class AuAnexo3Carga extends Auditoria {

    public static final int ESTADO_COLA = 0;
    public static final int ESTADO_VALIDACION_ESTRUCTURA_PROCESO = 1;
    public static final int ESTADO_VALIDACION_ESTRUCTURA_ERROR = 2;
    public static final int ESTADO_VALIDACION_ESTRUCTURA_OK = 3;
    public static final int ESTADO_VALIDACION_NORMATIVA_PROCESO = 4;
    public static final int ESTADO_VALIDACION_NORMATIVA_ERROR = 5;
    public static final int ESTADO_VALIDACION_NORMATIVA_OK = 6;
    public static final int ESTADO_ENVIO_PROCESO = 7;
    public static final int ESTADO_ENVIO_ERROR = 8;
    public static final int ESTADO_ENVIO_OK = 9;
    public static final int ESTADO_CANCELADO = 10;
    private Integer id;
    private int estado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private int registrosTotal;
    private Integer registrosExitosos;
    private Integer registrosRechazados;
    private String nombreArchivo;
    private String ruta;
    private String archivo;
    private boolean existe;
    private List<AuAnexo3> auAnexos3List;
    private Empresa gnEmpresasId;
    private List<AuAnexo3CargaDetalle> auAnexo3CargaDetallesList;
    private transient InputStream adjuntoStreamValidacion;
    private transient InputStream adjuntoStreamDocumento;
    private String codigoPrestador;
    private String usuarioGestionEstado;
    private String terminalGestionEstado;
    private Date fechaHoraGestionEstado;
            
    private String estadoObservacion;
    
    private String registros;
    private Usuario usuario;

    public AuAnexo3Carga() {
    }

    public AuAnexo3Carga(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public List<AuAnexo3> getAuAnexos3List() {
        return auAnexos3List;
    }

    public void setAuAnexos3List(List<AuAnexo3> auAnexos3List) {
        this.auAnexos3List = auAnexos3List;
    }

    public Empresa getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(Empresa gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public List<AuAnexo3CargaDetalle> getAuAnexo3CargaDetallesList() {
        return auAnexo3CargaDetallesList;
    }

    public void setAuAnexo3CargaDetallesList(List<AuAnexo3CargaDetalle> auAnexo3CargaDetallesList) {
        this.auAnexo3CargaDetallesList = auAnexo3CargaDetallesList;
    }

    public String getRegistros() {
        registros = Integer.toString(registrosTotal);
        return registros;
    }

    public void setRegistros(String registros) {
        this.registros = registros;
    }

    public InputStream getAdjuntoStreamValidacion() {
        return adjuntoStreamValidacion;
    }

    public void setAdjuntoStreamValidacion(InputStream adjuntoStreamValidacion) {
        this.adjuntoStreamValidacion = adjuntoStreamValidacion;
    }

    public InputStream getAdjuntoStreamDocumento() {
        return adjuntoStreamDocumento;
    }

    public void setAdjuntoStreamDocumento(InputStream adjuntoStreamDocumento) {
        this.adjuntoStreamDocumento = adjuntoStreamDocumento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEstadoStr() {
        String str;
        switch (this.estado) {
            case ESTADO_COLA:
                str = "EN COLA";
                break;
            case ESTADO_VALIDACION_ESTRUCTURA_PROCESO:
                str = "VALIDACION ESTRUCTURA PROCESO";
                break;
            case ESTADO_VALIDACION_ESTRUCTURA_ERROR:
                str = "VALIDACION ESTRUCTURA ERROR";
                break;
            case ESTADO_VALIDACION_ESTRUCTURA_OK:
                str = "VALIDACION ESTRUCTURA OK";
                break;
            case ESTADO_VALIDACION_NORMATIVA_PROCESO:
                str = "VALIDACION NORMATIVA PROCESO";
                break;
            case ESTADO_VALIDACION_NORMATIVA_ERROR:
                str = "VALIDACION NORMATIVA ERROR";
                break;
            case ESTADO_VALIDACION_NORMATIVA_OK:
                str = "VALIDACION NORMATIVA OK";
                break;
            case ESTADO_ENVIO_PROCESO:
                str = "ENVIO PROCESO";
                break;
            case ESTADO_ENVIO_ERROR:
                str = "ENVIO ERROR";
                break;
            case ESTADO_ENVIO_OK:
                str = "ENVIO OK";
                break;
            case ESTADO_CANCELADO:
                str = "CANCELADO";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    public String getCodigoPrestador() {
        return codigoPrestador;
    }

    public void setCodigoPrestador(String codigoPrestador) {
        this.codigoPrestador = codigoPrestador;
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

    public String getEstadoObservacion() {
        return estadoObservacion;
    }

    public void setEstadoObservacion(String estadoObservacion) {
        this.estadoObservacion = estadoObservacion;
    }
    
    public String getGenerarArchivo() {
        SimpleDateFormat df = new SimpleDateFormat("YYYYMMddHHmmss");
        int indiceExtension = getNombreArchivo().lastIndexOf(".");
        String ext = getNombreArchivo().substring(indiceExtension, getNombreArchivo().length());
        archivo = "carga_solicitudes" + "_" + df.format(new Date()) + ext;
        return archivo;
    }

    @Override
    public String toString() {
        return "AuAnexo3Carga{" + "id=" + id + ", estado=" + estado + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", registrosTotal=" + registrosTotal + ", registrosExitosos=" + registrosExitosos + ", registrosRechazados=" + registrosRechazados + ", nombreArchivo=" + nombreArchivo + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" + existe + '}';
    }
}
