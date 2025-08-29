/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;



/**
 *
 * @author rpalacios
 */
public class CargaMasivaConciliacion extends Auditoria {

    private String nit;
    private String ips;
    private String razonSocial;
    private String representanteEps;
    private String representanteIps;
    private String nombreArchivo;
    private String mensajeExitoConcilicion;
    private transient InputStream adjuntoStream;
    private transient InputStream archivoCargaConciliable;
    private Integer idConciliacionMasiva;
    private Integer idRadicado;

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getRepresentanteEps() {
        return representanteEps;
    }

    public void setRepresentanteEps(String representanteEps) {
        this.representanteEps = representanteEps;
    }

    public String getRepresentanteIps() {
        return representanteIps;
    }

    public void setRepresentanteIps(String representanteIps) {
        this.representanteIps = representanteIps;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    public InputStream getArchivoCargaConciliable() {
        return archivoCargaConciliable;
    }

    public void setArchivoCargaConciliable(InputStream archivoCargaConciliable) {
        this.archivoCargaConciliable = archivoCargaConciliable;
    }
   
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getMensajeExitoConcilicion() {
        return mensajeExitoConcilicion;
    }

    public void setMensajeExitoConcilicion(String mensajeExitoConcilicion) {
        this.mensajeExitoConcilicion = mensajeExitoConcilicion;
    }

    public Integer getIdConciliacionMasiva() {
        return idConciliacionMasiva;
    }

    public void setIdConciliacionMasiva(Integer idConciliacionMasiva) {
        this.idConciliacionMasiva = idConciliacionMasiva;
    }

    public Integer getIdRadicado() {
        return idRadicado;
    }

    public void setIdRadicado(Integer idRadicado) {
        this.idRadicado = idRadicado;
    }

    @Override
    public String toString() {
        return "CargaMasivaConciliacion{" + "nit=" + nit + ", ips=" + ips + ", razonSocial=" + razonSocial + ", representanteEps=" + representanteEps + ", representanteIps=" + representanteIps + ", nombreArchivo=" + nombreArchivo + ", mensajeExitoConcilicion=" + mensajeExitoConcilicion + ", idConciliacionMasiva=" + idConciliacionMasiva + ", idRadicado=" + idRadicado + '}';
    }
   
}
