/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author AlexanderDiaz
 */
public class MaEspecialidad extends Auditoria {
    
    private Integer id;

    private String codigo;    
    private boolean activo;
    private String nombre;
    private String descripcion;
    //2022-04-19 jyperez campos para la carga masiva
    private String errorCarga;
    private boolean actualizar;
    private String registroArchivo;
    private boolean estadoInicialActivo;

    public MaEspecialidad() {
    }

    public MaEspecialidad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "MaEspecialidad{" + "id=" + id + ", codigo=" + codigo + ", activo=" + activo + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }

    /**
     * @return the errorCarga
     */
    public String getErrorCarga() {
        return errorCarga;
    }

    /**
     * @param errorCarga the errorCarga to set
     */
    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    /**
     * @return the actualizar
     */
    public boolean isActualizar() {
        return actualizar;
    }

    /**
     * @param actualizar the actualizar to set
     */
    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    /**
     * @return the registroArchivo
     */
    public String getRegistroArchivo() {
        return registroArchivo;
    }

    /**
     * @param registroArchivo the registroArchivo to set
     */
    public void setRegistroArchivo(String registroArchivo) {
        this.registroArchivo = registroArchivo;
    }

    /**
     * @return the estadoInicialActivo
     */
    public boolean isEstadoInicialActivo() {
        return estadoInicialActivo;
    }

    /**
     * @param estadoInicialActivo the estadoInicialActivo to set
     */
    public void setEstadoInicialActivo(boolean estadoInicialActivo) {
        this.estadoInicialActivo = estadoInicialActivo;
    }

    
}
