/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class PeGestion extends Auditoria implements Serializable {
    
    public static final int LLAMADA_TELEFONICA = 1;
    public static final int CORREO_ELECTRONICO = 2;
    public static final int NOTIFICACION_OFICIO = 3;
    public static final int GESTION_CITA = 4;
    public static final int GESTION_VISITA_DOMICILIARIA = 5;
    
    private Integer id;
    private int tipo;
    private Integer maeTipoId;
    private String maeTipoCodigo;
    private String maeTipoValor;
    private String descripcion;    
    private PeAfiliadosPrograma peAfiliadosProgramasId;
    private Usuario usuariosId;
    private Integer fuenteOriegen;
    
    private Boolean borrado;
    private String borradoObservacion;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    
    //Utilizado en carga masiva gestiones
    private Integer consecutivo;
    private String errorCarga;
    private String registroArchivo;
    private transient Integer sincronizado;
    private transient String gestionCargaMasiva;
    
    public PeGestion() {
    }

    public PeGestion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PeAfiliadosPrograma getPeAfiliadosProgramasId() {
        return peAfiliadosProgramasId;
    }

    public void setPeAfiliadosProgramasId(PeAfiliadosPrograma peAfiliadosProgramasId) {
        this.peAfiliadosProgramasId = peAfiliadosProgramasId;
    }

    public Usuario getUsuariosId() {
        return usuariosId;
    }

    public void setUsuariosId(Usuario usuariosId) {
        this.usuariosId = usuariosId;
    }

    public Integer getMaeTipoId() {
        return maeTipoId;
    }

    public void setMaeTipoId(Integer maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
    }

    public String getErrorCarga() {
        return errorCarga;
    }

    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    public String getRegistroArchivo() {
        return registroArchivo;
    }

    public void setRegistroArchivo(String registroArchivo) {
        this.registroArchivo = registroArchivo;
    }

    public Integer getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Integer sincronizado) {
        this.sincronizado = sincronizado;
    }

    public String getGestionCargaMasiva() {
        return gestionCargaMasiva;
    }

    public void setGestionCargaMasiva(String gestionCargaMasiva) {
        this.gestionCargaMasiva = gestionCargaMasiva;
    }

    public Integer getFuenteOriegen() {
        return fuenteOriegen;
    }

    public void setFuenteOriegen(Integer fuenteOriegen) {
        this.fuenteOriegen = fuenteOriegen;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
    }

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }
    
    
    
    public String getFeunteOrigenStr(){
        String resultado = "";
        if(this.fuenteOriegen == null){
            return resultado;
        }
        if(this.fuenteOriegen == 1){
            resultado = "Manual";
        }else if(this.fuenteOriegen == 2){
            resultado = "Carga masiva";
        }
        return resultado;
    }

    @Override
    public String toString() {
        return "PeGestion{" + "id=" + id + ", tipo=" + tipo + ", maeTipoId=" + maeTipoId + ", maeTipoCodigo=" + maeTipoCodigo + ", maeTipoValor=" + maeTipoValor + ", descripcion=" + descripcion + ", peAfiliadosProgramasId=" + peAfiliadosProgramasId + ", usuariosId=" + usuariosId + ", fuenteOriegen=" + fuenteOriegen + ", errorCarga=" + errorCarga + ", registroArchivo=" + registroArchivo + ", sincronizado=" + sincronizado + ", gestionCargaMasiva=" + gestionCargaMasiva + '}';
    }

}
