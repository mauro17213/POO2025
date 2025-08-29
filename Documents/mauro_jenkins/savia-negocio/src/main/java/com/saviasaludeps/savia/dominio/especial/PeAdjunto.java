/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.io.Serializable;

/**
 *
 * @author Jaime Andres Olarte
 */
public class PeAdjunto extends Auditoria implements Serializable {
    
    public static final int DOCUMENTO_IDENTIDAD = 1;
    public static final int EXAMEN_LABORATORIO = 2;
    public static final int HISTORIA_CLINICA = 3;
    public static final int EXAMEN_CONFIRMA_PATOLOGIA = 4;
    
    private Integer id;
    private int maeTipoArchivoId;
    private String maeTipoArchivoCodigo;
    private String maeTipoArchivoValor;
    private String nombre;
    private String ruta;
    private String archivo;
    private String observacion;
    private PeAfiliadosPrograma peAfiliadosId;
    private PeGestion peGestionesId;
    private transient InputStream adjuntoStream;
    private transient String extensión;

    public PeAdjunto() {
    }

    public PeAdjunto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoArchivoId() {
        return maeTipoArchivoId;
    }

    public void setMaeTipoArchivoId(int maeTipoArchivoId) {
        this.maeTipoArchivoId = maeTipoArchivoId;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public PeAfiliadosPrograma getPeAfiliadosId() {
        return peAfiliadosId;
    }

    public void setPeAfiliadosId(PeAfiliadosPrograma peAfiliadosId) {
        this.peAfiliadosId = peAfiliadosId;
    }

    public PeGestion getPeGestionesId() {
        return peGestionesId;
    }

    public void setPeGestionesId(PeGestion peGestionesId) {
        this.peGestionesId = peGestionesId;
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

    public String getMaeTipoArchivoCodigo() {
        return maeTipoArchivoCodigo;
    }

    public void setMaeTipoArchivoCodigo(String maeTipoArchivoCodigo) {
        this.maeTipoArchivoCodigo = maeTipoArchivoCodigo;
    }

    public String getMaeTipoArchivoValor() {
        return maeTipoArchivoValor;
    }

    public void setMaeTipoArchivoValor(String maeTipoArchivoValor) {
        this.maeTipoArchivoValor = maeTipoArchivoValor;
    }
    
    @Override
    public String toString() {
        return "PeAdjunto{" + "id=" + id + 
                ", TipoArchivoId=" + maeTipoArchivoId +
                ", TipoArchivoCodigo=" + maeTipoArchivoCodigo +
                ", maeTipoArchivoValor=" + maeTipoArchivoValor +
                ", TipoArchivo=" + maeTipoArchivoId +
                ", Nombre=" + nombre + 
                ", ruta=" + ruta + 
                ", Archivo=" + archivo + 
                ", observacion=" + observacion +
                ", PeAfiliadoPrograma=" + peAfiliadosId + 
                ", PeGestion=" + peGestionesId + '}';
    }
    
}
