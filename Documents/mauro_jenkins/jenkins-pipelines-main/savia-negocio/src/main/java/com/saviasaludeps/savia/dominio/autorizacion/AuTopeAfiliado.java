/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public class AuTopeAfiliado extends Auditoria {
    
    private Integer id;
    private boolean activo;
    private AsegAfiliado aseAfiliadosId;
    private String justificacionActivo;
    private String justificacionInactivo;
    private List<AuSolicitudAdjunto> auSolicitudAdjuntoList;
     
    public AuTopeAfiliado() {
        
    }

    public AuTopeAfiliado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public AsegAfiliado getAseAfiliadosId() {
        return aseAfiliadosId;
    }

    public void setAseAfiliadosId(AsegAfiliado aseAfiliadosId) {
        this.aseAfiliadosId = aseAfiliadosId;
    }


    public String getJustificacionActivo() {
        return justificacionActivo;
    }

    public void setJustificacionActivo(String justificacionActivo) {
        this.justificacionActivo = justificacionActivo;
    }

    public String getJustificacionInactivo() {
        return justificacionInactivo;
    }

    public void setJustificacionInactivo(String justificacionInactivo) {
        this.justificacionInactivo = justificacionInactivo;
    }

    public List<AuSolicitudAdjunto> getAuSolicitudAdjuntoList() {
        return auSolicitudAdjuntoList;
    }

    public void setAuSolicitudAdjuntoList(List<AuSolicitudAdjunto> auSolicitudAdjuntoList) {
        this.auSolicitudAdjuntoList = auSolicitudAdjuntoList;
    }

    //metodos
    public String getActivoStr(){
        String nombreActivo = "Inactivo";
        if(activo){
            nombreActivo = "Activo";
        }
        return nombreActivo;
    }

    @Override
    public String toString() {
        return "AuTopeAfiliado{" + "id=" + id + ", activo=" + activo + ", aseAfiliadosId=" + aseAfiliadosId + ", justificacionActivo=" + justificacionActivo + ", justificacionInactivo=" + justificacionInactivo + '}';
    }
}
