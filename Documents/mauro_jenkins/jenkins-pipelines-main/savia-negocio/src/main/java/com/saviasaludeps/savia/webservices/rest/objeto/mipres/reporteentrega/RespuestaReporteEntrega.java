/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.mipres.reporteentrega;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class RespuestaReporteEntrega implements Serializable {

    private List<ReporteEntrega> reporteEntregas;

    public List<ReporteEntrega> getReporteEntregas() {
        return reporteEntregas;
    }

    public void setReporteEntregas(List<ReporteEntrega> reporteEntregas) {
        this.reporteEntregas = reporteEntregas;
    }

}
