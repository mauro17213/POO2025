/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author sgiraldov
 */
public class AucHospitalizacionObjecion extends Auditoria {
    
    private Integer id;
    private int tipoTecnologia;
    private int maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private int cantidadSolicitada;
    private String observacion;
    private boolean borrado;
    private String observacionBorrado;
    private String notaCm;
    private AucHospitalizacion aucHospitalizacionId;
    private int pos;

    public AucHospitalizacionObjecion() {
    }

    public AucHospitalizacionObjecion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNotaCm() {
        return notaCm;
    }

    public void setNotaCm(String notaCm) {
        this.notaCm = notaCm;
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
    
    //Metodo auxiliar
    public String getTipoTecnologiaStr() {
        String tipo = "";
        switch(tipoTecnologia) {
            case 1:
                tipo = "Tecnologia";
                break;
            case 2:
                tipo = "Medicamento";
                break;
            case 3:
                tipo = "Insumo";
                break;
            case 4:
                tipo = "Paquete";
                break;
        }
        return tipo;
    }

    @Override
    public String toString() {
        return "AucHospitalizacionObjecion{" + "id=" + id + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", cantidadSolicitada=" + cantidadSolicitada + ", observacion=" + observacion + ", borrado=" + borrado + ", observacionBorrado=" + observacionBorrado + ", notaCm=" + notaCm + ", pos=" + pos + '}';
    }
}
