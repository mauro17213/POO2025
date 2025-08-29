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
public class AucHospitalizacionInoportunidad extends Auditoria {
    
    private Integer id;
    private int maeTipoInoportunidadId;
    private String maeTipoInoportunidadCodigo;
    private String maeTipoInoportunidadValor;
    private Integer maeMotivoInoportunidadId;
    private String maeMotivoInoportunidadCodigo;
    private String maeMotivoInoportunidadValor;
    private Date fechaInicioInoportunidad;
    private Date fechaFinInoportunidad;
    private Integer diasInoportunidad;
    private String observacion;
    private String observacionCorto;
    private boolean borrado;
    private String observacionBorrado;
    private AucHospitalizacion aucHospitalizacionId;
    private int pos;
    
    public static final int TAMANIO_OBSERVACION = 20;
    
    public AucHospitalizacionInoportunidad() {
    }

    public AucHospitalizacionInoportunidad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoInoportunidadId() {
        return maeTipoInoportunidadId;
    }

    public void setMaeTipoInoportunidadId(int maeTipoInoportunidadId) {
        this.maeTipoInoportunidadId = maeTipoInoportunidadId;
    }

    public String getMaeTipoInoportunidadCodigo() {
        return maeTipoInoportunidadCodigo;
    }

    public void setMaeTipoInoportunidadCodigo(String maeTipoInoportunidadCodigo) {
        this.maeTipoInoportunidadCodigo = maeTipoInoportunidadCodigo;
    }

    public String getMaeTipoInoportunidadValor() {
        return maeTipoInoportunidadValor;
    }

    public void setMaeTipoInoportunidadValor(String maeTipoInoportunidadValor) {
        this.maeTipoInoportunidadValor = maeTipoInoportunidadValor;
    }

    public Integer getMaeMotivoInoportunidadId() {
        return maeMotivoInoportunidadId;
    }

    public void setMaeMotivoInoportunidadId(Integer maeMotivoInoportunidadId) {
        this.maeMotivoInoportunidadId = maeMotivoInoportunidadId;
    }

    public String getMaeMotivoInoportunidadCodigo() {
        return maeMotivoInoportunidadCodigo;
    }

    public void setMaeMotivoInoportunidadCodigo(String maeMotivoInoportunidadCodigo) {
        this.maeMotivoInoportunidadCodigo = maeMotivoInoportunidadCodigo;
    }

    public String getMaeMotivoInoportunidadValor() {
        return maeMotivoInoportunidadValor;
    }

    public void setMaeMotivoInoportunidadValor(String maeMotivoInoportunidadValor) {
        this.maeMotivoInoportunidadValor = maeMotivoInoportunidadValor;
    }

    public Date getFechaInicioInoportunidad() {
        return fechaInicioInoportunidad;
    }

    public void setFechaInicioInoportunidad(Date fechaInicioInoportunidad) {
        this.fechaInicioInoportunidad = fechaInicioInoportunidad;
    }

    public Date getFechaFinInoportunidad() {
        return fechaFinInoportunidad;
    }

    public void setFechaFinInoportunidad(Date fechaFinInoportunidad) {
        this.fechaFinInoportunidad = fechaFinInoportunidad;
    }

    public Integer getDiasInoportunidad() {
        return diasInoportunidad;
    }

    public void setDiasInoportunidad(Integer diasInoportunidad) {
        this.diasInoportunidad = diasInoportunidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacionCorto() {
        if (getObservacion() != null) {
            observacionCorto = getObservacion();
            if (getObservacion().length() >= TAMANIO_OBSERVACION) {
                return observacionCorto.substring(0, TAMANIO_OBSERVACION) + "..";
            } else {
                return observacionCorto;
            }
        }
        return observacionCorto;
    }

    public void setObservacionCorto(String observacionCorto) {
        this.observacionCorto = observacionCorto;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getObservacionBorrado() {
        return observacionBorrado;
    }

    public void setObservacionBorrado(String observacionBorrado) {
        this.observacionBorrado = observacionBorrado;
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

    @Override
    public String toString() {
        return "AucHospitalizacionInoportunidad{" + "id=" + id + ", maeTipoInoportunidadId=" + maeTipoInoportunidadId + ", maeTipoInoportunidadCodigo=" + maeTipoInoportunidadCodigo + ", maeTipoInoportunidadValor=" + maeTipoInoportunidadValor + ", maeMotivoInoportunidadId=" + maeMotivoInoportunidadId + ", maeMotivoInoportunidadCodigo=" + maeMotivoInoportunidadCodigo + ", maeMotivoInoportunidadValor=" + maeMotivoInoportunidadValor + ", fechaInicioInoportunidad=" + fechaInicioInoportunidad + ", fechaFinInoportunidad=" + fechaFinInoportunidad + ", diasInoportunidad=" + diasInoportunidad + ", observacion=" + observacion + ", observacionCorto=" + observacionCorto + ", borrado=" + borrado + ", observacionBorrado=" + observacionBorrado + ", pos=" + pos + '}';
    }
}
