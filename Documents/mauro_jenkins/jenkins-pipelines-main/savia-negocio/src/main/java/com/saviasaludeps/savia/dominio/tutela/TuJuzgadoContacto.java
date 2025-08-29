/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author pavacca
 */
public class TuJuzgadoContacto extends Auditoria{
    
    private Integer id;
    private Integer maeTipoContactoId;
    private String maeTipoContactoCodigo;
    private String maeTipoContactoValor;
    private String contacto;
    private boolean activo;
    private String observacion;
    private TuJuzgado tuJuzgadosId;
    private int posicion;
    
    public TuJuzgadoContacto(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeTipoContactoId() {
        return maeTipoContactoId;
    }

    public void setMaeTipoContactoId(Integer maeTipoContactoId) {
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

    public TuJuzgado getTuJuzgadosId() {
        return tuJuzgadosId;
    }

    public void setTuJuzgadosId(TuJuzgado tuJuzgadosId) {
        this.tuJuzgadosId = tuJuzgadosId;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    @Override
    public String toString() {
        return "TuJuzgadoContacto{" + "id=" + id + ", maeTipoContactoId=" + maeTipoContactoId + ", maeTipoContactoCodigo=" + maeTipoContactoCodigo + ", maeTipoContactoValor=" + maeTipoContactoValor + ", contacto=" + contacto + ", activo=" + activo + ", observacion=" + observacion + ", tuJuzgadosId=" + tuJuzgadosId + '}';
    }

}
