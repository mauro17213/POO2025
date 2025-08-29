/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.anticipo;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;

/**
 *
 * @author NEXOS
 */
public class AntAnticipoAdjunto extends Auditoria {
    public static final int ORIGEN_ANTICIPOS = 1;
            
    private Integer id;
    private AntAnticipo antAnticiposId;
    private int origen;
    private int maeTipoArchivoId;
    private String maeTipoArchivoCodigo;
    private String maeTipoArchivoValor;
    private String maeTipoArchivoTipo;
    private String nombre;
    private String ruta;
    private String archivo;
    private boolean existe;
    
    // variables auxiliares
    private transient String extension;
    private transient InputStream adjuntoStream;
    
    public AntAnticipoAdjunto() {
        
    }

    public AntAnticipoAdjunto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AntAnticipo getAntAnticiposId() {
        return antAnticiposId;
    }

    public void setAntAnticiposId(AntAnticipo antAnticiposId) {
        this.antAnticiposId = antAnticiposId;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getMaeTipoArchivoId() {
        return maeTipoArchivoId;
    }

    public void setMaeTipoArchivoId(int maeTipoArchivoId) {
        this.maeTipoArchivoId = maeTipoArchivoId;
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

    public String getMaeTipoArchivoTipo() {
        return maeTipoArchivoTipo;
    }

    public void setMaeTipoArchivoTipo(String maeTipoArchivoTipo) {
        this.maeTipoArchivoTipo = maeTipoArchivoTipo;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }
    // metodos auxiliares
    public String getOrigenStr(){
        String origenStr = "";
        switch(origen){
            case ORIGEN_ANTICIPOS:
               origenStr = "Anticipos";
               break;
            
        }
       return origenStr;
    }
    
    @Override
    public String toString() {
        return "AntAnticipoAdjunto{" + "id=" + id + ", antAnticiposId=" + antAnticiposId + ", origen=" + origen + ", maeTipoArchivoId=" + maeTipoArchivoId + ", maeTipoArchivoCodigo=" + maeTipoArchivoCodigo + ", maeTipoArchivoValor=" + maeTipoArchivoValor + ", maeTipoArchivoTipo=" + maeTipoArchivoTipo + ", nombre=" + nombre + ", ruta=" + ruta + ", archivo=" + archivo + ", extension=" + extension + ", existe=" + existe + '}';
    }
}
