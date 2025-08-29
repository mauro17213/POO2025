/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.mipres.contingencia;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;

/**
 *
 * @author rpalacic
 */
public class MpcPrescripcionAdjunto extends Auditoria {
    
    public static final short TIPO_ACTA = 1;
    public static final short TIPO_CONSENTIMIENTO = 2;
    public static final short TIPO_FORMATO = 3;
    public static final short TIPO_PRECRIPCION = 4;
    
    
    private Integer id;
    private MpcPrescripcion mpcPrescripcion;
    private short tipo;
    private String nombreArchivo;
    private String ruta;
    private String archivo;
    private boolean existe;
    
    //Auxiliares
    private transient InputStream adjuntoStream;

    public MpcPrescripcionAdjunto() {
    }

    public MpcPrescripcionAdjunto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MpcPrescripcion getMpcPrescripcion() {
        return mpcPrescripcion;
    }

    public void setMpcPrescripcionId(MpcPrescripcion mpcPrescripcion) {
        this.mpcPrescripcion = mpcPrescripcion;
    }

    public short getTipo() {
        return tipo;
    }

    public String getTipoStr() {
        switch (tipo) {
            case TIPO_ACTA:
                return "Acta";
            case TIPO_CONSENTIMIENTO:
                return "Concentimiento";
            case TIPO_FORMATO:
                return "Formato";
            case TIPO_PRECRIPCION:
                return "Prescripcion";
            default:
                return "";
        }
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
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

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    

    //Auxliares    
    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }
    
    public void setAdjuntoStream(InputStream adjuntoStream) {    
        this.adjuntoStream = adjuntoStream;
    }

    @Override
    public String toString() {
        return "MpcPrescripcionAdjunto{" + "id=" + id + ", mpcPrescripcion=" + mpcPrescripcion + ", tipo=" + tipo + ", norbreArchivo=" + nombreArchivo + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" + existe + '}';
    }
    
}
