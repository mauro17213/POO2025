package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author idbohorquez
 */
public class CntjComite extends Auditoria {
    
    private Integer id;
    private Integer estado;
    private Date fechaProgramacion;
    private Date fechaInicio;
    private Date fechaFin;
    private String observacion;
    private String conclusion;
    private String adjuntoNombre;
    private String adjuntoArchivo;
    private String adjuntoRuta;
    private boolean adjuntoExiste;

    public CntjComite() {
    }

    public CntjComite(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaProgramacion() {
        return fechaProgramacion;
    }

    public void setFechaProgramacion(Date fechaProgramacion) {
        this.fechaProgramacion = fechaProgramacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getAdjuntoNombre() {
        return adjuntoNombre;
    }

    public void setAdjuntoNombre(String adjuntoNombre) {
        this.adjuntoNombre = adjuntoNombre;
    }

    public String getAdjuntoArchivo() {
        return adjuntoArchivo;
    }

    public void setAdjuntoArchivo(String adjuntoArchivo) {
        this.adjuntoArchivo = adjuntoArchivo;
    }

    public String getAdjuntoRuta() {
        return adjuntoRuta;
    }

    public void setAdjuntoRuta(String adjuntoRuta) {
        this.adjuntoRuta = adjuntoRuta;
    }

    public boolean isAdjuntoExiste() {
        return adjuntoExiste;
    }

    public void setAdjuntoExiste(boolean adjuntoExiste) {
        this.adjuntoExiste = adjuntoExiste;
    }

    @Override
    public String toString() {
        return "CntjComite{" + "id=" + id + ", estado=" + estado + ", fechaProgramacion=" + fechaProgramacion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", observacion=" + observacion + ", conclusion=" + conclusion + ", adjuntoNombre=" + adjuntoNombre + ", adjuntoArchivo=" + adjuntoArchivo + ", adjuntoRuta=" + adjuntoRuta + ", adjuntoExiste=" + adjuntoExiste + '}';
    }
    
}
