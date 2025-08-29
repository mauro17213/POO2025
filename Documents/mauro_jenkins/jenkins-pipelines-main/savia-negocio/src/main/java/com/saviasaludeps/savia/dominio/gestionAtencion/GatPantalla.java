/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author stive
 */
public class GatPantalla extends Auditoria {
    
    private Integer id;
    private String idSesion;
    private boolean cuenta;
    private boolean activo;
    private Integer maeTipoServicioId;
    private String maeTipoServicioCodigo;
    private String maeTipoServicioValor;
    private GnSede gnSedeId;

    public GatPantalla() {
    }

    public GatPantalla(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public boolean isCuenta() {
        return cuenta;
    }

    public void setCuenta(boolean cuenta) {
        this.cuenta = cuenta;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    public void setMaeTipoServicioId(Integer maeTipoServicioId) {
        this.maeTipoServicioId = maeTipoServicioId;
    }

    public String getMaeTipoServicioCodigo() {
        return maeTipoServicioCodigo;
    }

    public void setMaeTipoServicioCodigo(String maeTipoServicioCodigo) {
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
    }

    public String getMaeTipoServicioValor() {
        return maeTipoServicioValor;
    }

    public void setMaeTipoServicioValor(String maeTipoServicioValor) {
        this.maeTipoServicioValor = maeTipoServicioValor;
    }

    public GnSede getGnSedeId() {
        return gnSedeId;
    }

    public void setGnSedeId(GnSede gnSedeId) {
        this.gnSedeId = gnSedeId;
    }

    @Override
    public String toString() {
        return "GatPantalla{" + "id=" + id + ", idSesion=" + idSesion + ", cuenta=" + cuenta + ", activo=" + activo + ", maeTipoServicioId=" + maeTipoServicioId + ", maeTipoServicioCodigo=" + maeTipoServicioCodigo + ", maeTipoServicioValor=" + maeTipoServicioValor + ", gnSedeId=" + gnSedeId + '}';
    }
    
}
