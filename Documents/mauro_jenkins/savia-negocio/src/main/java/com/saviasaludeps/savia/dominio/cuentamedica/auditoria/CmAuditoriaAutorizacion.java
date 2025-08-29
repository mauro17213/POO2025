/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class CmAuditoriaAutorizacion extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer posInsertar;
    private int idCmAuditoriaDetalle;
    private CmFactura cmFactura;
    private CmDetalle cmDetalle;
    private int idAutorizacinAnexos4;
    private AuAnexo4 anexo4;
    private String numeroAutorizacion;
    private String codigoServicio;
    private String nombreServicio;
    private String razonSocialPrestador;
    private String nitPrestador;
    private int cantidad;
    private BigDecimal valorAutorizacion;
    private Date fechaAutorizacion;
    private boolean anexo4Valido;
    private boolean activa;
    private boolean pagoAnticipado;
    private String sucesoRips;
    
    public CmAuditoriaAutorizacion() {
    }

    public CmAuditoriaAutorizacion(Integer id) {
        this.id = id;
    }

    public CmAuditoriaAutorizacion(CmFactura cmFactura, CmDetalle cmDetalle, String numeroAutorizacion, BigDecimal valorAutorizacion) {
        this.cmFactura = cmFactura;
        this.cmDetalle = cmDetalle;
        this.numeroAutorizacion = numeroAutorizacion;
        this.valorAutorizacion = valorAutorizacion;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPosInsertar() {
        return posInsertar;
    }

    public void setPosInsertar(Integer posInsertar) {
        this.posInsertar = posInsertar;
    }

    public int getIdCmAuditoriaDetalle() {
        return idCmAuditoriaDetalle;
    }

    public void setIdCmAuditoriaDetalle(int idCmAuditoriaDetalle) {
        this.idCmAuditoriaDetalle = idCmAuditoriaDetalle;
    }


    public void setId(Integer id) {
        this.id = id;
    } 
    public int getIdAutorizacinAnexos4() {
        return idAutorizacinAnexos4;
    }

    public void setIdAutorizacinAnexos4(int idAutorizacinAnexos4) {
        this.idAutorizacinAnexos4 = idAutorizacinAnexos4;
    }

 

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getCodigoServicio() {
        return codigoServicio == null || codigoServicio != null && codigoServicio.equals("null") ? "" : codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getNombreServicio() {
        return nombreServicio == null  || nombreServicio != null && nombreServicio.equals("null") ? "" : nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValorAutorizacion() {
        return valorAutorizacion;
    }

    public void setValorAutorizacion(BigDecimal valorAutorizacion) {
        this.valorAutorizacion = valorAutorizacion;
    }

    public CmFactura getCmFactura() {
        if(cmFactura==null){
            cmFactura = new CmFactura();
        }
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

    public CmDetalle getCmDetalle() {
         if(cmDetalle==null){
            cmDetalle = new CmDetalle();
        }
        return cmDetalle;
    }

    public void setCmDetalle(CmDetalle cmDetalle) {
        this.cmDetalle = cmDetalle;
    }
    
    public boolean isAnexo4Valido() {
        return anexo4Valido;
    }

    public void setAnexo4Valido(boolean anexo4Valido) {
        this.anexo4Valido = anexo4Valido;
    }

    

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public AuAnexo4 getAnexo4() {
        if(anexo4== null){
            anexo4 = new AuAnexo4();
        }
        return anexo4;
    }

    public void setAnexo4(AuAnexo4 anexo4) {
        this.anexo4 = anexo4;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getRazonSocialPrestador() {
        return razonSocialPrestador;
    }

    public void setRazonSocialPrestador(String razonSocialPrestador) {
        this.razonSocialPrestador = razonSocialPrestador;
    }

    public String getNitPrestador() {
        return nitPrestador;
    }

    public void setNitPrestador(String nitPrestador) {
        this.nitPrestador = nitPrestador;
    }

    public boolean isPagoAnticipado() {
        return pagoAnticipado;
    }

    public void setPagoAnticipado(boolean pagoAnticipado) {
        this.pagoAnticipado = pagoAnticipado;
    }
    
    public String getPagoAnticipasoStr(){
       return isPagoAnticipado() ? "Si" : "No";
    }

    public String getSucesoRips() {
        return sucesoRips;
    }

    public void setSucesoRips(String sucesoRips) {
        this.sucesoRips = sucesoRips;
    }
    
    public boolean  haySucesoRips(){
        return getSucesoRips() != null &&  
               getSucesoRips().length() > 1;
    }

    @Override
    public String toString() {
        return "CmAuditoriaAutorizacion{" + "id=" + id + ", posInsertar=" + posInsertar + ", idCmAuditoriaDetalle=" + idCmAuditoriaDetalle + ", cmFactura=" +getCmFactura().getId() + ", cmDetalle=" + getCmDetalle().getId() + ", idAutorizacinAnexos4=" + idAutorizacinAnexos4 + ", anexo4=" + getAnexo4().getId() + ", numeroAutorizacion=" + numeroAutorizacion + ", codigoServicio=" + codigoServicio + ", nombreServicio=" + nombreServicio + ", razonSocialPrestador=" + razonSocialPrestador + ", nitPrestador=" + nitPrestador + ", cantidad=" + cantidad + ", valorAutorizacion=" + valorAutorizacion + ", fechaAutorizacion=" + fechaAutorizacion + ", anexo4Valido=" + anexo4Valido + ", activa=" + activa + '}';
    }
      
}
