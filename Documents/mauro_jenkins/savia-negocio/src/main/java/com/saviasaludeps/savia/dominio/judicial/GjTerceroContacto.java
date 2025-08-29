package com.saviasaludeps.savia.dominio.judicial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author bsgomez
 */
public class GjTerceroContacto extends Auditoria {

    private Integer id;
    private int maeTipoContactoId;
    private String maeTipoContactoCodigo;
    private String maeTipoContactoValor;
    private String contacto;
    private boolean activo;
    private String observacion;
    private GjTercero gjTercerosId;
    private int posicion;

    public GjTerceroContacto() {
    }

    public GjTerceroContacto(Integer id) {
        this.id = id;
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

    public GjTercero getGjTercerosId() {
        return gjTercerosId;
    }

    public void setGjTercerosId(GjTercero gjTercerosId) {
        this.gjTercerosId = gjTercerosId;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "GjTerceroContacto{" + "id=" + id + ", maeTipoContactoId=" + maeTipoContactoId + ", maeTipoContactoCodigo=" + maeTipoContactoCodigo + ", maeTipoContactoValor=" + maeTipoContactoValor + ", contacto=" + contacto + ", activo=" + activo + ", observacion=" + observacion + ", gjTercerosId=" + gjTercerosId + '}';
    }

}
