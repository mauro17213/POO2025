/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Jhonatan Jimenez
 */
public class AusAdjunto extends Auditoria{

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String ruta;
    private String nombre;
    private String archivo;
    private String usuarioCrea;
    private String terminalCrea;
    private Date fechaHoraCrea;
    private AusCaso casosId;
    private AusSeguimiento seguimientosId;
    private AusCasoServicio serviciosId;
    private AusSolicitud solicitudId;
    private int pos;
    public static final int ACCION_INSERTAR = 0;
    public static final int ACCION_BORRAR = 1;
    public static final int ACCION_NINGUNO= 2;
    
    private int accion = ACCION_INSERTAR;

    private boolean adjuntoMemoria;

    public boolean isAdjuntoMemoria() {
        adjuntoMemoria = false;
        if (id == null || id == 0) {
            adjuntoMemoria = true;
        }
        return adjuntoMemoria;
    }

    public void setAdjuntoMemoria(boolean adjuntoMemoria) {
        this.adjuntoMemoria = adjuntoMemoria;
    }
    
    
   
    public AusAdjunto() {
    }


    public AusAdjunto(Integer id, String ruta, String nombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.ruta = ruta;
        this.nombre = nombre;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public AusCaso getCasosId() {
        return casosId;
    }
    
    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }


    public void setCasosId(AusCaso casosId) {
        this.casosId = casosId;
    }

    public AusSeguimiento getSeguimientosId() {
        return seguimientosId;
    }

    public void setSeguimientosId(AusSeguimiento seguimientosId) {
        this.seguimientosId = seguimientosId;
    }

    public AusCasoServicio getServiciosId() {
        return serviciosId;
    }

    public void setServiciosId(AusCasoServicio serviciosId) {
        this.serviciosId = serviciosId;
    }
    
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
 
    public String toString() {
        return "Adjunto{" + "id=" + id + ", ruta=" + ruta + ", nombre=" + nombre + ", usuarioCrea=" + usuarioCrea + ", terminalCrea=" + terminalCrea + ", fechaHoraCrea=" + fechaHoraCrea + ", casosId=" + casosId + ", seguimientosId=" + seguimientosId + ", solicitudId=" + solicitudId + ", serviciosId=" + serviciosId + '}';
    }

    /**
     * @return the solicitudId
     */
    public AusSolicitud getSolicitudId() {
        return solicitudId;
    }

    /**
     * @param solicitudId the solicitudId to set
     */
    public void setSolicitudId(AusSolicitud solicitudId) {
        this.solicitudId = solicitudId;
    }

    
    
}
