/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.io.Serializable;

public class CmAuditoriaAdjunto extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer idRegistroDB;
    private Integer posInsertar;
    private CmFactura cmFactura;
    private int archivoTipo;     
    private String archivoNombre;
    private String archivoRuta;
    private boolean guardadoEnDisco;
    private boolean guardadoEnDB;
    private CmDetalle cmDetalle;
     
    public static final int TIPO_FACTURA = 0;
    public static final int TIPO_DETALLE = 1;
    
    private transient InputStream inputStream;
  
    public CmAuditoriaAdjunto() {
    }

    public CmAuditoriaAdjunto(Integer id) {
        this.id = id;
    }

    public CmAuditoriaAdjunto(int archivoTipo, String archivoNombre, String archivoRuta, InputStream inputStream) {
        this.archivoTipo = archivoTipo;
        this.archivoNombre = archivoNombre;
        this.archivoRuta = archivoRuta;
        this.inputStream = inputStream;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosInsertar() {
        return posInsertar;
    }

    public void setPosInsertar(Integer posInsertar) {
        this.posInsertar = posInsertar;
    }

    public CmFactura getCmFactura() {
        if(cmFactura== null){
            cmFactura = new CmFactura();
        }
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

    public boolean isGuardadoEnDisco() {
        return guardadoEnDisco;
    }

    public void setGuardadoEnDisco(boolean guardadoEnDisco) {
        this.guardadoEnDisco = guardadoEnDisco;
    }

    public boolean isGuardadoEnDB() {
        return guardadoEnDB;
    }

    public void setGuardadoEnDB(boolean guardadoEnDB) {
        this.guardadoEnDB = guardadoEnDB;
    }

    public Integer getIdRegistroDB() {
        return idRegistroDB;
    }

    public void setIdRegistroDB(Integer idRegistroDB) {
        this.idRegistroDB = idRegistroDB;
    }
    
    public int getArchivoTipo() {
        return archivoTipo;
    }

    public void setArchivoTipo(int archivoTipo) {
        this.archivoTipo = archivoTipo;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }
    
    public String getTipoStr( ) {
       return CmAuditoriaAdjunto.getTipoStr( getArchivoTipo() );
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public CmDetalle getCmDetalle() {
        return cmDetalle;
    }

    public void setCmDetalle(CmDetalle cmDetalle) {
        this.cmDetalle = cmDetalle;
    }
    
     public static String getTipoStr(int tipo) {
        switch (tipo) {
            case TIPO_FACTURA:
                return "Adjunto factura";
            case TIPO_DETALLE:
                return "Adjunto detalle";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return "CmAuditoriaAdjuntos{" + "id=" + id + ", posInsertar=" + posInsertar + ", cmfacturaId=" + cmFactura.getId() + ", archivoTipo=" + archivoTipo + ", archivoNombre=" + archivoNombre + ", archivoRuta=" + archivoRuta + '}';
    }

  
    
}
