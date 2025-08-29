/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.solicitud;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;

/**
 *
 * @author jramirez
 */
public class GsAdjunto extends Auditoria {
    
    private Integer id;
    private int tipo;
    private String nombre;
    private String ruta;
    private String archivo;
    private GsSolicitud gsSolicitud;
    private transient InputStream adjuntoStream;
    private String extensionAdjunto;
    private int indice;

    public static final int TIPO_ADJUNTO_DOCUMENTO = 1;
    public static final int TIPO_ADJUNTO_CERTIFICADO = 2;
    public static final int TIPO_ADJUNTO_HISTORIA_CLINICA = 3;
    public static final int TIPO_ADJUNTO_ORDEN_SERVICIO = 4;

    public GsAdjunto() {
    }

    public GsAdjunto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public String getTipoStr() {
        switch (tipo) {
            case TIPO_ADJUNTO_DOCUMENTO:
                return "Documento";
            case TIPO_ADJUNTO_CERTIFICADO:
                return "Certificado";
            case TIPO_ADJUNTO_HISTORIA_CLINICA:
                return "Historia Clínica";
            case TIPO_ADJUNTO_ORDEN_SERVICIO:
                return "Orden Servicio";
            default:
                return "";
        }
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public GsSolicitud getGsSolicitud() {
        return gsSolicitud;
    }

    public void setGsSolicitud(GsSolicitud gsSolicitud) {
        this.gsSolicitud = gsSolicitud;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    public String getExtensionAdjunto() {
        return extensionAdjunto;
    }

    public void setExtensionAdjunto(String extensionAdjunto) {
        this.extensionAdjunto = extensionAdjunto;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    @Override
    public String toString() {
        return "GsAdjunto{" + "id=" + id + ", tipo=" + tipo + ", nombre=" + nombre + ", ruta=" + ruta + ", archivo=" + archivo + ", gsSolicitud=" + gsSolicitud + '}';
    }
}
