/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Stiven Giraldo
 */
public class AuCotizacionItem extends Auditoria {

    private Integer id;
    private AuAnexo3Item auAnexo3ItemId;
    private AuCotizacion auCotizacionId;
    private Integer mpPrescripcionItem;
    private Integer tipoTecnologiaMipres;

    public AuCotizacionItem() {
    }

    public AuCotizacionItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuAnexo3Item getAuAnexo3ItemId() {
        return auAnexo3ItemId;
    }

    public void setAuAnexo3ItemId(AuAnexo3Item auAnexo3ItemId) {
        this.auAnexo3ItemId = auAnexo3ItemId;
    }

    public AuCotizacion getAuCotizacionId() {
        return auCotizacionId;
    }

    public void setAuCotizacionId(AuCotizacion auCotizacionId) {
        this.auCotizacionId = auCotizacionId;
    }

    public Integer getMpPrescripcionItem() {
        return mpPrescripcionItem;
    }

    public void setMpPrescripcionItem(Integer mpPrescripcionItem) {
        this.mpPrescripcionItem = mpPrescripcionItem;
    }

    public Integer getTipoTecnologiaMipres() {
        return tipoTecnologiaMipres;
    }

    public void setTipoTecnologiaMipres(Integer tipoTecnologiaMipres) {
        this.tipoTecnologiaMipres = tipoTecnologiaMipres;
    }

    @Override
    public String toString() {
        return "AuCotizacionItem{" + "id=" + id + ", auAnexo3ItemId=" + auAnexo3ItemId + ", auCotizacionId=" + auCotizacionId + '}';
    }

}
