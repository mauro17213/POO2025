/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jyperez
 */
public class AsegInforme extends Auditoria {

    private Integer id;
    private int tipo;
    private int estado;
    private Date fechaDesde;
    private Date fechaHasta;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private int registros;
    private String ruta;
    private String archivo;
    private String observacion;
    //private String usuarioCrea;
    //private String terminalCrea;
    //private Date fechaHoraCrea;

    public AsegInforme() {
    }

    public AsegInforme(Integer id) {
        this.id = id;
    }

    public AsegInforme(Integer id, int tipo, int estado, Date fechaDesde, Date fechaHasta, Date fechaHoraInicio, int registros, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.fechaHoraInicio = fechaHoraInicio;
        this.registros = registros;
        setUsuarioCrea(usuarioCrea);
        setTerminalCrea(terminalCrea);
        setFechaHoraCrea(fechaHoraCrea);
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
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

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
        this.registros = registros;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsegInforme)) {
            return false;
        }
        AsegInforme other = (AsegInforme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public String getFechaStr(Date fecha) {
        String mensaje = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            mensaje = sdf.format(fecha);
        }catch (Exception ex) {
            mensaje= "";
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return "AsegInforme{" + "id=" + id + ", tipo=" + tipo + ", estado=" + estado + ", fechaDesde=" + getFechaStr(fechaDesde) + ", fechaHasta=" + getFechaStr(fechaHasta) + ", fechaHoraInicio=" + getFechaStr(fechaHoraInicio) + ", fechaHoraFin=" + getFechaStr(fechaHoraFin) + ", registros=" + registros + ", ruta=" + ruta + ", archivo=" + archivo + ", observacion=" + observacion + '}';
    }
    
}
