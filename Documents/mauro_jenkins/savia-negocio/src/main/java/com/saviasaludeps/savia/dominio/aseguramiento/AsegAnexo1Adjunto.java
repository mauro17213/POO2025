/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;

/**
 *
 * @author jyperez
 */
public class AsegAnexo1Adjunto extends Auditoria {
    private Integer id;
    private String archivo;
    private String ruta;
    private AsegAnexo1 asegAnexo1Id;
    
    //campos adicionales manejo archivos - Anexo1
    private String nombre;
    private InputStream adjuntoStream;
    private String extensión;

    public AsegAnexo1Adjunto() {
    }

    public AsegAnexo1Adjunto(Integer id, String archivo, String ruta) {
        this.id = id;
        this.archivo = archivo;
        this.ruta = ruta;
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the archivo
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the asegAnexo1Id
     */
    public AsegAnexo1 getAsegAnexo1Id() {
        return asegAnexo1Id;
    }

    /**
     * @param asegAnexo1Id the asegAnexo1Id to set
     */
    public void setAsegAnexo1Id(AsegAnexo1 asegAnexo1Id) {
        this.asegAnexo1Id = asegAnexo1Id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the adjuntoStream
     */
    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    /**
     * @param adjuntoStream the adjuntoStream to set
     */
    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    /**
     * @return the extensión
     */
    public String getExtensión() {
        return extensión;
    }

    /**
     * @param extensión the extensión to set
     */
    public void setExtensión(String extensión) {
        this.extensión = extensión;
    }

    @Override
    public String toString() {
        return "AsegAnexo1Adjunto{" + "id=" + id + ", archivo=" + archivo + ", ruta=" + ruta + '}';
    }

}
