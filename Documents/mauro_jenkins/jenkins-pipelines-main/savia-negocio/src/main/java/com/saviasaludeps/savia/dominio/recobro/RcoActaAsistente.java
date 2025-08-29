/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.recobro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author pavacca
 */
public class RcoActaAsistente extends Auditoria{
    
    public static final int LONGITUD_CAMPO_CORTO = 20;
    
    private Integer id;
    private RcoActa rcoActasId;
    private String nombre;
    private String cargo;
    private String firma;
    private String observaciones;
    
    // variables auxliares
    private int posicion;
    
    public RcoActaAsistente(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RcoActa getRcoActasId() {
        return rcoActasId;
    }

    public void setRcoActasId(RcoActa rcoActasId) {
        this.rcoActasId = rcoActasId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    //metodo auxliliares
    public String getObservacionNombreCorto() {
        String observacionNombreCorto = "";
        if (getObservaciones() != null) {
            observacionNombreCorto = getObservaciones();
            if (getObservaciones().length() >= LONGITUD_CAMPO_CORTO) {
                return observacionNombreCorto.substring(0, LONGITUD_CAMPO_CORTO) + "..";
            } else {
                return observacionNombreCorto;
            }
        }
        return observacionNombreCorto;
    }
    
    @Override
    public String toString() {
        return "RcoActaAsistente{" + "id=" + id + ", nombre=" + nombre + ", cargo=" + cargo + ", firma=" + firma + ", observaciones=" + observaciones + '}';
    }
    
}
