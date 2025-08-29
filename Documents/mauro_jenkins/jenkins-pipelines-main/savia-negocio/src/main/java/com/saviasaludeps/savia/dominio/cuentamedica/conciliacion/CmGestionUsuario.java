/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;


/**
 *
 * @author jperezn
 */

public class CmGestionUsuario  extends Auditoria implements Cloneable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private int estado;
    private BigDecimal valorPagadoEps;
    private BigDecimal porcentajePagadoEps;
    private BigDecimal valorAceptadoIps;
    private BigDecimal porcentajeAceptadoIps;
    private String usuarioGestiona;
    private CmDetalle cmDetalle;
    
    public final static int ESTADO_GUARDADO_TEMPORAL_RESPUESTA = 0;

    public CmGestionUsuario() {
    }

    public CmGestionUsuario(Integer id) {
        this.id = id;
    }

    public CmGestionUsuario(Integer id, int estado) {
        this.id = id;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public BigDecimal getValorPagadoEps() {
        return valorPagadoEps;
    }

    public void setValorPagadoEps(BigDecimal valorPagadoEps) {
        this.valorPagadoEps = valorPagadoEps;
    }

    public BigDecimal getPorcentajePagadoEps() {
        return porcentajePagadoEps;
    }

    public void setPorcentajePagadoEps(BigDecimal porcentajePagadoEps) {
        this.porcentajePagadoEps = porcentajePagadoEps;
    }

    public BigDecimal getValorAceptadoIps() {
        return valorAceptadoIps;
    }

    public void setValorAceptadoIps(BigDecimal valorAceptadoIps) {
        this.valorAceptadoIps = valorAceptadoIps;
    }

    public BigDecimal getPorcentajeAceptadoIps() {
        return porcentajeAceptadoIps;
    }

    public void setPorcentajeAceptadoIps(BigDecimal porcentajeAceptadoIps) {
        this.porcentajeAceptadoIps = porcentajeAceptadoIps;
    }

    public String getUsuarioGestiona() {
        return usuarioGestiona;
    }

    public void setUsuarioGestiona(String usuarioGestiona) {
        this.usuarioGestiona = usuarioGestiona;
    }    

    public CmDetalle getCmDetalle() {
        if(cmDetalle == null){
            cmDetalle = new CmDetalle();
        }
        return cmDetalle;
    }

    public void setCmDetalle(CmDetalle cmDetalle) {
        this.cmDetalle = cmDetalle;
    }
    
    public String getEstadoFacturaStr() {
        return CmGestionUsuario.getEstadoStr(getEstado());
    }

    public static String getEstadoStr(int estado) {
        String str;
        switch (estado) {
            case ESTADO_GUARDADO_TEMPORAL_RESPUESTA:
                str = "Guardado temporal respuesta";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CmGestionUsuario)) {
            return false;
        }
        CmGestionUsuario other = (CmGestionUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmGestionUsuarios[ id=" + id + " ]";
    }
    
}
