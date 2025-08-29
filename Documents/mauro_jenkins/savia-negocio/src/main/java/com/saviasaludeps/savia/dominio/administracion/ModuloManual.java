/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class ModuloManual extends Auditoria {

    private Integer id;
    private String version;
    private Date fechaVersion;
    private boolean actual;
    private String descripcion;
    private String nombre;
    private String ruta;
    private String archivo;
    private Modulo modulo;
    private int tipo;
    private transient InputStream adjuntoStream;
    private transient String extensión;
    
    public final static int TIPO_MANUAL_INTERNO = 0;
    public final static int TIPO_MANUAL_EXTERNO = 1;

    public ModuloManual() {
    }

    public ModuloManual(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getFechaVersion() {
        return fechaVersion;
    }

    public void setFechaVersion(Date fechaVersion) {
        this.fechaVersion = fechaVersion;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    public String getExtensión() {
        return extensión;
    }

    public void setExtensión(String extensión) {
        this.extensión = extensión;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public String getTipoStr() {
        String strTipo = "";
        switch (this.tipo) {
            case TIPO_MANUAL_INTERNO: 
                strTipo = "MANUAL INTERNO";
                break; 
            case TIPO_MANUAL_EXTERNO: 
                strTipo = "MANUAL EXTERNO";
                break;
            default:
                break;
        }
        return strTipo;
    }

    @Override
    public String toString() {
        return "ModuloManual{" + "id=" + id + ", version=" + version + ", fechaVersion=" + fechaVersion + ", actual=" + actual
                + ", descripcion=" + descripcion + ", nombre=" + nombre + ", ruta=" + ruta + ", archivo=" + archivo
                + ", modulo=" + modulo + '}';
    }

}
