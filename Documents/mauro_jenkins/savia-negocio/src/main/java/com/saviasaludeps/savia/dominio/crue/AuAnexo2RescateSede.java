/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jdlopez
 */
public class AuAnexo2RescateSede extends Auditoria {
    
    private Integer id;
    private CntPrestadorSede prestadorSedeCapita;
    private CntPrestadorSede prestadorSedeOrigen;
    private boolean aplicaRescateAnexo2;
    private boolean aplicaRescateAnexo3;
    private boolean aplicaRescateAnexo3Hosp;
    private boolean aplicaRescateHosp;
    private boolean activo;
    private String observacion;
    private String inactivoObservacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CntPrestadorSede getPrestadorSedeCapita() {
        return prestadorSedeCapita;
    }

    public void setPrestadorSedeCapita(CntPrestadorSede prestadorSedeCapita) {
        this.prestadorSedeCapita = prestadorSedeCapita;
    }

    public CntPrestadorSede getPrestadorSedeOrigen() {
        return prestadorSedeOrigen;
    }

    public void setPrestadorSedeOrigen(CntPrestadorSede prestadorSedeOrigen) {
        this.prestadorSedeOrigen = prestadorSedeOrigen;
    }

    public boolean isAplicaRescateAnexo2() {
        return aplicaRescateAnexo2;
    }

    public void setAplicaRescateAnexo2(boolean aplicaRescateAnexo2) {
        this.aplicaRescateAnexo2 = aplicaRescateAnexo2;
    }

    public boolean isAplicaRescateAnexo3() {
        return aplicaRescateAnexo3;
    }

    public void setAplicaRescateAnexo3(boolean aplicaRescateAnexo3) {
        this.aplicaRescateAnexo3 = aplicaRescateAnexo3;
    }

    public boolean isAplicaRescateHosp() {
        return aplicaRescateHosp;
    }

    public void setAplicaRescateHosp(boolean aplicaRescateHosp) {
        this.aplicaRescateHosp = aplicaRescateHosp;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getInactivoObservacion() {
        return inactivoObservacion;
    }

    public void setInactivoObservacion(String inactivoObservacion) {
        this.inactivoObservacion = inactivoObservacion;
    }

    public boolean isAplicaRescateAnexo3Hosp() {
        return aplicaRescateAnexo3Hosp;
    }

    public void setAplicaRescateAnexo3Hosp(boolean aplicaRescateAnexo3Hosp) {
        this.aplicaRescateAnexo3Hosp = aplicaRescateAnexo3Hosp;
    }

    
    @Override
    public String toString() {
        return "AuAnexo2RescateSede{" + "id=" + id + ", prestadorSedeCapita=" + (prestadorSedeCapita != null ? prestadorSedeCapita.getId() : null) 
                + ", prestadorSedeOrigen=" + (prestadorSedeOrigen != null ? prestadorSedeOrigen.getId() : null)
                + ", aplicaRescateAnexo2=" + aplicaRescateAnexo2 + ", aplicaRescateAnexo3=" + aplicaRescateAnexo3 + ", aplicaRescateAnexo3Hosp=" + aplicaRescateAnexo3Hosp
                + ", aplicaRescateHosp=" + aplicaRescateHosp + ", activo=" + activo + ", observacion=" + observacion + ", inactivoObservacion=" + inactivoObservacion + '}';
    }
    
    
}
