/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author sgiraldov
 */
public class AucHospitalizacionEstancia extends Auditoria {
    
    private Integer id;
    private Date fechaIngreso;
    private Date fechaEgreso;
    private Integer dias;
    private int maeServicioId;
    private String maeServicioCodigo;
    private String maeServicioValor;
    private AucHospitalizacion aucHospitalizacionId;
    private int pos;
    
    
    //Variables auxiliares
    private Date ultimaFechaEgreso;
    
    public AucHospitalizacionEstancia() {
    }

    public AucHospitalizacionEstancia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public int getMaeServicioId() {
        return maeServicioId;
    }

    public void setMaeServicioId(int maeServicioId) {
        this.maeServicioId = maeServicioId;
    }

    public String getMaeServicioCodigo() {
        return maeServicioCodigo;
    }

    public void setMaeServicioCodigo(String maeServicioCodigo) {
        this.maeServicioCodigo = maeServicioCodigo;
    }

    public String getMaeServicioValor() {
        return maeServicioValor;
    }

    public void setMaeServicioValor(String maeServicioValor) {
        this.maeServicioValor = maeServicioValor;
    }

    public AucHospitalizacion getAucHospitalizacionId() {
        return aucHospitalizacionId;
    }

    public void setAucHospitalizacionId(AucHospitalizacion aucHospitalizacionId) {
        this.aucHospitalizacionId = aucHospitalizacionId;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
    //Getter y Setter Axuiliares

    public Date getUltimaFechaEgreso() {
        return ultimaFechaEgreso;
    }

    public void setUltimaFechaEgreso(Date ultimaFechaEgreso) {
        this.ultimaFechaEgreso = ultimaFechaEgreso;
    }
    
    public int getCalcularDiasEstancia() {
        Long fechaInicio = null;
        Long fechaFin  = null;
        if (getFechaIngreso()!= null) {
            fechaInicio = getFechaIngreso().getTime();
        } else {
            return 0;
        }
        if (getFechaEgreso()!= null) {
            fechaFin = getFechaEgreso().getTime();
        } else {
            fechaFin = new Date().getTime();
        }
        int milisecondsByDay = 86400000;
        return (int) ((fechaFin-fechaInicio) / milisecondsByDay);
    }
    
    @Override
    public String toString() {
        return "AucHospitalizacionEstancia{" + "id=" + id + ", fechaIngreso=" + fechaIngreso + ", fechaEgreso=" + fechaEgreso + ", dias=" + dias + ", maeServicioId=" + maeServicioId + ", maeServicioCodigo=" + maeServicioCodigo + ", maeServicioValor=" + maeServicioValor + ", aucHospitalizacionId=" + aucHospitalizacionId + ", pos=" + pos + ", ultimaFechaEgreso=" + ultimaFechaEgreso + '}';
    }
    
}
