/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Jhonatan Jimenez
 */
public class TuJuzgado extends Auditoria {

    private Integer id;
    private String nombre;
    private String codigo_despacho;
    private Boolean activo = true;

    private Ubicacion gnUbicacionId;

    public TuJuzgado() {
    }

    public TuJuzgado(Integer id) {
        this.id = id;
    }
    
    public TuJuzgado(Integer id, String nombre, String codigo_despacho, Boolean activo, Ubicacion gnUbicacionId) {
        this.id = id;
        this.nombre = nombre;
        this.codigo_despacho = codigo_despacho;
        this.activo = activo;
        this.gnUbicacionId = gnUbicacionId;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo_despacho() {
        return codigo_despacho;
    }

    public void setCodigo_despacho(String codigo_despacho) {
        this.codigo_despacho = codigo_despacho;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getActivoStr() {
        String activo = "N/A";
        if (getActivo() != null) {
            activo = (getActivo()) ? "SI" : "NO";
        }
        return activo;
    }
//    
//    public String getActivoStr() {
//        return (activo) ? "SI" : "NO";
//    }

  
  public Ubicacion getGnUbicacionId() {
        if (gnUbicacionId == null) {
            gnUbicacionId = new Ubicacion();
        }
        return gnUbicacionId;
    }

    public void setGnUbicacionId(Ubicacion gnUbicacionId) {
        this.gnUbicacionId = gnUbicacionId;
    }

    @Override
    public String toString() {
        return "TuJuzgado{" + "id=" + id + ", nombre=" + nombre + ", codigo_despacho=" + codigo_despacho + ", activo=" + activo + ", usuarioCrea=" + usuarioCrea + ", terminalCrea=" + terminalCrea + ", fechaHoraCrea=" + fechaHoraCrea + ", usuarioModifica=" + usuarioModifica + ", terminalModifica=" + terminalModifica + ", fechaHoraModifica=" + fechaHoraModifica + ", gnUbicacionId=" + gnUbicacionId + '}';
    }



}
