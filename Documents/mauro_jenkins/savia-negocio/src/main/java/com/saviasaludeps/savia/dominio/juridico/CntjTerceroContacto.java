package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author StivenGV
 */
public class CntjTerceroContacto extends Auditoria {
    
    private Integer id;
    private int maeTipoContactoId;
    private String maeTipoContactoCodigo;
    private String maeTipoContactoValor;
    private String contacto;
    private boolean activo;
    private String observacion;
    private CntjTercero cntjTercerosId;

    public CntjTerceroContacto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoContactoId() {
        return maeTipoContactoId;
    }

    public void setMaeTipoContactoId(int maeTipoContactoId) {
        this.maeTipoContactoId = maeTipoContactoId;
    }

    public String getMaeTipoContactoCodigo() {
        return maeTipoContactoCodigo;
    }

    public void setMaeTipoContactoCodigo(String maeTipoContactoCodigo) {
        this.maeTipoContactoCodigo = maeTipoContactoCodigo;
    }

    public String getMaeTipoContactoValor() {
        return maeTipoContactoValor;
    }

    public void setMaeTipoContactoValor(String maeTipoContactoValor) {
        this.maeTipoContactoValor = maeTipoContactoValor;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
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

    public CntjTercero getCntjTercerosId() {
        return cntjTercerosId;
    }

    public void setCntjTercerosId(CntjTercero cntjTercerosId) {
        this.cntjTercerosId = cntjTercerosId;
    }
    
    
    
}
