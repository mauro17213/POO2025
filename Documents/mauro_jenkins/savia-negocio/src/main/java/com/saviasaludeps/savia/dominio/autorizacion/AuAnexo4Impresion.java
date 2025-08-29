/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Usuario
 */
public class AuAnexo4Impresion extends Auditoria {
    
    //Tipo Impresion
    public static final String IMPRESION_ORIGINAL = "Impresión Original";
    public static final String IMPRESION_COPIA = "Impresión Copia";
    public static final String IMPRESION_ANULADA = "Impresión Anulada";
    public static final int TIPO_ORIGINAL = 0;
    public static final int TIPO_COPIA = 1;
    public static final int TIPO_ANULADA = 2;
    
    //Origen Impresion
    public static final String APLICACION = "Aplicación";
    public static final String PAGINA_WEB = "Página Web";
    public static final String INTEROPERABILIDAD = "Interoperabilidad";
    public static final int ORIGEN_APLICACION = 0;
    public static final int ORIGEN_PAGINA = 1;
    public static final int ORIGEN_INTER = 2;
    
    private Integer id;
    private int tipoImpresion;
    private int origenImpresion;
    private AuAnexo4 auAnexo4Id;

    public String obtenerTipoImpresion(){
        switch(tipoImpresion){
            case TIPO_ORIGINAL:
                return IMPRESION_ORIGINAL;
            case TIPO_COPIA:
                return IMPRESION_COPIA;
            case TIPO_ANULADA:
                return IMPRESION_ANULADA;
        }
        return "";
    }
    
    public String obtenerOrigen(){
        switch(origenImpresion){
            case ORIGEN_APLICACION:
                return APLICACION;
            case ORIGEN_PAGINA:
                return PAGINA_WEB;
            case ORIGEN_INTER:
                return INTEROPERABILIDAD;
        }
        return "";
    }

    public AuAnexo4Impresion() {
        
    }

    public AuAnexo4Impresion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(int tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }

    public int getOrigenImpresion() {
        return origenImpresion;
    }

    public void setOrigenImpresion(int origenImpresion) {
        this.origenImpresion = origenImpresion;
    }

    public AuAnexo4 getAuAnexo4Id() {
        return auAnexo4Id;
    }

    public void setAuAnexo4Id(AuAnexo4 auAnexo4Id) {
        this.auAnexo4Id = auAnexo4Id;
    }

    @Override
    public String toString() {
        return "AuAnexo4Impresion{" + "id=" + id + ", tipoImpresion=" + tipoImpresion + ", origenImpresion=" + origenImpresion + '}';
    }    
    
}
