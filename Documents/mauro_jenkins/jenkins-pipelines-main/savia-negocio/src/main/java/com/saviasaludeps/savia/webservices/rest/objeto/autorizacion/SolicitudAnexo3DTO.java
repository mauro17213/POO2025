/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class SolicitudAnexo3DTO  implements Serializable{

    private int nut;
    private String fechaHoraTransaccion;
    private int cantidadRegistros;
    private String codHabilitacionPrestador;
    private List<Anexo3AutorizacionesDTO> solicitudAutoriaciones;
//    private String usuarioCrea;
//    private String fechaCrea;
//    private String terminalCrea;

    public String getFechaHoraTransaccion() {
        return fechaHoraTransaccion;
    }

    public void setFechaHoraTransaccion(String fechaHoraTransaccion) {
        this.fechaHoraTransaccion = fechaHoraTransaccion;
    }

    public List<Anexo3AutorizacionesDTO> getSolicitudAutoriaciones() {
        return solicitudAutoriaciones;
    }

    public void setSolicitudAutoriaciones(List<Anexo3AutorizacionesDTO> solicitudAutoriaciones) {
        this.solicitudAutoriaciones = solicitudAutoriaciones;
    }

    public int getNut() {
        return nut;
    }

    public void setNut(int nut) {
        this.nut = nut;
    }

    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(int cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public String getCodHabilitacionPrestador() {
        return codHabilitacionPrestador;
    }

    public void setCodHabilitacionPrestador(String codHabilitacionPrestador) {
        this.codHabilitacionPrestador = codHabilitacionPrestador;
    }
//  campos auditoria
//    public String getUsuarioCrea() {
//        return usuarioCrea;
//    }
//
//    public void setUsuarioCrea(String usuarioCrea) {
//        this.usuarioCrea = usuarioCrea;
//    }
//
//    public String getFechaCrea() {
//        return fechaCrea;
//    }
//
//    public void setFechaCrea(String fechaCrea) {
//        this.fechaCrea = fechaCrea;
//    }
//
//    public String getTerminalCrea() {
//        return terminalCrea;
//    }
//
//    public void setTerminalCrea(String terminalCrea) {
//        this.terminalCrea = terminalCrea;
//    }
    
}
