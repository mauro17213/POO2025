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
public class AucHospitalizacionAdverso extends Auditoria {
    
    private Integer id;
    private Date fechaEvento;
    private String descripcionEvento;
    private int maeCategoriaEventoId;
    private String maeCategoriaEventoCodigo;
    private String maeCategoriaEventoValor;
    private int maeSubcategoriaEventoId;
    private String maeSubcategoriaEventoCodigo;
    private String maeSubcategoriaEventoValor;
    private Date fechaAnalisis;
    private String descripcionPlanMejora;
    private Date fechaSolicitudAnalisis;
    private String descripcionAnalisis;
    private Integer maeConclusionEventoId;
    private String maeConclusionEventoCodigo;
    private String maeConclusionEventoValor;
    private AucHospitalizacion aucHospitalizacionId;
    private Date fechaSolicitudAnalisisIps;
    private boolean borrado;
    private String observacionBorrado;
    private int pos;
    
    public AucHospitalizacionAdverso() {
    }

    public AucHospitalizacionAdverso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public int getMaeCategoriaEventoId() {
        return maeCategoriaEventoId;
    }

    public void setMaeCategoriaEventoId(int maeCategoriaEventoId) {
        this.maeCategoriaEventoId = maeCategoriaEventoId;
    }

    public String getMaeCategoriaEventoCodigo() {
        return maeCategoriaEventoCodigo;
    }

    public void setMaeCategoriaEventoCodigo(String maeCategoriaEventoCodigo) {
        this.maeCategoriaEventoCodigo = maeCategoriaEventoCodigo;
    }

    public String getMaeCategoriaEventoValor() {
        return maeCategoriaEventoValor;
    }

    public void setMaeCategoriaEventoValor(String maeCategoriaEventoValor) {
        this.maeCategoriaEventoValor = maeCategoriaEventoValor;
    }

    public int getMaeSubcategoriaEventoId() {
        return maeSubcategoriaEventoId;
    }

    public void setMaeSubcategoriaEventoId(int maeSubcategoriaEventoId) {
        this.maeSubcategoriaEventoId = maeSubcategoriaEventoId;
    }

    public String getMaeSubcategoriaEventoCodigo() {
        return maeSubcategoriaEventoCodigo;
    }

    public void setMaeSubcategoriaEventoCodigo(String maeSubcategoriaEventoCodigo) {
        this.maeSubcategoriaEventoCodigo = maeSubcategoriaEventoCodigo;
    }

    public String getMaeSubcategoriaEventoValor() {
        return maeSubcategoriaEventoValor;
    }

    public void setMaeSubcategoriaEventoValor(String maeSubcategoriaEventoValor) {
        this.maeSubcategoriaEventoValor = maeSubcategoriaEventoValor;
    }

    public Date getFechaAnalisis() {
        return fechaAnalisis;
    }

    public void setFechaAnalisis(Date fechaAnalisis) {
        this.fechaAnalisis = fechaAnalisis;
    }

    public String getDescripcionPlanMejora() {
        return descripcionPlanMejora;
    }

    public void setDescripcionPlanMejora(String descripcionPlanMejora) {
        this.descripcionPlanMejora = descripcionPlanMejora;
    }

    public Date getFechaSolicitudAnalisis() {
        return fechaSolicitudAnalisis;
    }

    public void setFechaSolicitudAnalisis(Date fechaSolicitudAnalisis) {
        this.fechaSolicitudAnalisis = fechaSolicitudAnalisis;
    }

    public String getDescripcionAnalisis() {
        return descripcionAnalisis;
    }

    public void setDescripcionAnalisis(String descripcionAnalisis) {
        this.descripcionAnalisis = descripcionAnalisis;
    }

    public Integer getMaeConclusionEventoId() {
        return maeConclusionEventoId;
    }

    public void setMaeConclusionEventoId(Integer maeConclusionEventoId) {
        this.maeConclusionEventoId = maeConclusionEventoId;
    }

    public String getMaeConclusionEventoCodigo() {
        return maeConclusionEventoCodigo;
    }

    public void setMaeConclusionEventoCodigo(String maeConclusionEventoCodigo) {
        this.maeConclusionEventoCodigo = maeConclusionEventoCodigo;
    }

    public String getMaeConclusionEventoValor() {
        return maeConclusionEventoValor;
    }

    public void setMaeConclusionEventoValor(String maeConclusionEventoValor) {
        this.maeConclusionEventoValor = maeConclusionEventoValor;
    }

    public AucHospitalizacion getAucHospitalizacionId() {
        return aucHospitalizacionId;
    }

    public void setAucHospitalizacionId(AucHospitalizacion aucHospitalizacionId) {
        this.aucHospitalizacionId = aucHospitalizacionId;
    }

    public Date getFechaSolicitudAnalisisIps() {
        return fechaSolicitudAnalisisIps;
    }

    public void setFechaSolicitudAnalisisIps(Date fechaSolicitudAnalisisIps) {
        this.fechaSolicitudAnalisisIps = fechaSolicitudAnalisisIps;
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
    
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "AucHospitalizacionAdverso{" + "id=" + id + ", fechaEvento=" + fechaEvento + ", descripcionEvento=" + descripcionEvento + ", maeCategoriaEventoId=" + maeCategoriaEventoId + ", maeCategoriaEventoCodigo=" + maeCategoriaEventoCodigo + ", maeCategoriaEventoValor=" + maeCategoriaEventoValor + ", maeSubcategoriaEventoId=" + maeSubcategoriaEventoId + ", maeSubcategoriaEventoCodigo=" + maeSubcategoriaEventoCodigo + ", maeSubcategoriaEventoValor=" + maeSubcategoriaEventoValor + ", fechaAnalisis=" + fechaAnalisis + ", descripcionPlanMejora=" + descripcionPlanMejora + ", fechaSolicitudAnalisis=" + fechaSolicitudAnalisis + ", descripcionAnalisis=" + descripcionAnalisis + ", maeConclusionEventoId=" + maeConclusionEventoId + ", maeConclusionEventoCodigo=" + maeConclusionEventoCodigo + ", maeConclusionEventoValor=" + maeConclusionEventoValor + ", fechaSolicitudAnalisisIps=" + fechaSolicitudAnalisisIps + ", borrado=" + borrado + ", observacionBorrado=" + observacionBorrado + '}';
    }
}
