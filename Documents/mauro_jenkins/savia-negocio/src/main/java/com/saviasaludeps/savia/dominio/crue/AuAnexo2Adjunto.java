/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;

/**
 *
 * @author AlexanderDiaz
 */
public class AuAnexo2Adjunto extends Auditoria {
    
    private Integer id;
    private int maeTipoArchivoId;
    private String maeTipoArchivoCodigo;
    private String maeTipoArchivoValor;
    private String nombreArchivo;
    private String ruta;
    private String archivo;    
    private AuAnexo2 auAnexo2;
    
    //Atributos temporales que se utilizan en el módulo de centro regulador.
    private String nombre;
    private InputStream adjuntoStream;
    private String extensión;

    public AuAnexo2Adjunto() {
    }

    public AuAnexo2Adjunto(Integer id) {
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

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
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

    public AuAnexo2 getAuAnexo2() {
        return auAnexo2;
    }

    public void setAuAnexo2(AuAnexo2 auAnexo2) {
        this.auAnexo2 = auAnexo2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    
    
    @Override
    public String toString() {
        return "AuAnexo2Adjunto{" + "id=" + id + ", maeTipoArchivoId=" + maeTipoArchivoId + ", maeTipoArchivoCodigo=" + maeTipoArchivoCodigo + 
                ", maeTipoArchivoValor=" + maeTipoArchivoValor + ", nombreArchivo=" + nombreArchivo + ", ruta=" + ruta + 
                ", archivo=" + archivo + ", refAnexos9=" + auAnexo2 +'}';
    }
}
