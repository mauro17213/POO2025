/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Jaime Andres Olarte
 */
public class RefTransporteSeguimiento extends Auditoria {
    
    private Integer id;
    private int maeTipoReporteId;
    private String maeTipoReporteCodigo;
    private String maeTipoReporteValor;
    private String observacion;
    private RefTransporte refTransporte;

    public RefTransporteSeguimiento() {
    }

    public RefTransporteSeguimiento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoReporteId() {
        return maeTipoReporteId;
    }

    public void setMaeTipoReporteId(int maeTipoReporteId) {
        this.maeTipoReporteId = maeTipoReporteId;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public RefTransporte getRefTransporte() {
        return refTransporte;
    }

    public void setRefTransporte(RefTransporte refTransporte) {
        this.refTransporte = refTransporte;
    }

    public String getMaeTipoReporteCodigo() {
        return maeTipoReporteCodigo;
    }

    public void setMaeTipoReporteCodigo(String maeTipoReporteCodigo) {
        this.maeTipoReporteCodigo = maeTipoReporteCodigo;
    }

    public String getMaeTipoReporteValor() {
        return maeTipoReporteValor;
    }

    public void setMaeTipoReporteValor(String maeTipoReporteValor) {
        this.maeTipoReporteValor = maeTipoReporteValor;
    }    

    @Override
    public String toString() {
        return "RefAnexo9DatoClinico{" + "id=" + id + ", maeTipoReporteId=" + maeTipoReporteId + ", maeTipoReporteCodigo=" + maeTipoReporteCodigo + ", maeTipoReporteValor=" + maeTipoReporteValor +
                "observacion=" + observacion + "refTransporte=" + refTransporte +'}';   
    }
    
}
