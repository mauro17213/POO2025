/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

/**
 *
 * @author JorgeEPerez
 */
public class CmAuditoriaSelectorOperacionMasiva {
    
    public final static int TIPO_OPERACION_GESTION_ITEM     = 1; 
    public final static int TIPO_OPERACION_CAPITA_DESCUENTO = 2; 
    public final static int TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS = 3; 
    public final static int TIPO_OPERACION_LEVANTAMIENTO_CONCEPTOS_CONTABLES = 4; 
    public final static int TIPO_OPERACION_GESTION_CONCEPTOS_MOTIVOS = 5;
    public final static int TIPO_OPERACION_LEVANTAMIENTO_DIAGNOSTICOS = 6;
    public final static int TIPO_OPERACION_GESTION_AUDITORIA_MASIVA = 7;
    public final static int TIPO_OPERACION_CIERRE_MASIVA = 8;
    public final static int TIPO_OPERACION_LEVANTAMIENTO_AUTORIZACIONES = 9;
    public final static int TIPO_OPERACION_GESTION_AUTORIZACIONES = 10;
    public final static int TIPO_OPERACION_LEVANTAMIENTO_DESCUENTO_CAPITA = 11;
    public final static int TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS_ESPECIFICOS = 12;
    public final static int TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS_ESPECIFICOS_FACTURAS = 13;
    public final static int TIPO_OPERACION_MARCAR_COPAGO_NO_EFECTIVO = 14;
    public final static int TIPO_OPERACION_DESMARCAR_COPAGO_NO_EFECTIVO = 15;
    
    Integer idFactura;
    int tipoOperacion;
    int tipoGestionMasiva;
    
    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public int getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(int tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public int getTipoGestionMasiva() {
        return tipoGestionMasiva;
    }

    public void setTipoGestionMasiva(int tipoGestionMasiva) {
        this.tipoGestionMasiva = tipoGestionMasiva;
    }
    
    
    public String getTipoOperacionStr() {
        return CmAuditoriaSelectorOperacionMasiva.getTipoOperacionStr(getTipoOperacion());
    }
   
    public static String getTipoOperacionStr(int tipoOperacion) {
        String str;
        switch (tipoOperacion) {
            case TIPO_OPERACION_GESTION_ITEM:
                str = "Gestión";
                break;
            case TIPO_OPERACION_CAPITA_DESCUENTO:
                str = "Descuento Capita";
                break;
            case TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS:
                str = "Levantamiento Motivos";
                break;
            case TIPO_OPERACION_LEVANTAMIENTO_CONCEPTOS_CONTABLES:
                str = "Levantamiento Conceptos Contables";
                break;
            case TIPO_OPERACION_GESTION_CONCEPTOS_MOTIVOS:
                str = "Gestion Conceptos ";
                break;
            case TIPO_OPERACION_LEVANTAMIENTO_DIAGNOSTICOS:
                str = "Levantamiento Diagnosticos";
                break;
            case TIPO_OPERACION_GESTION_AUDITORIA_MASIVA:
                str = "Gestión Auditoría Masiva";
                break;
            case TIPO_OPERACION_CIERRE_MASIVA:
                str = "Cierre Auditoría Masiva";
                break;
            case TIPO_OPERACION_LEVANTAMIENTO_AUTORIZACIONES:
                str = "Levantamiento Autorizaciones";
                break;
            case TIPO_OPERACION_GESTION_AUTORIZACIONES:
                str = "Gestión Autorizaciones";
                break;
            case TIPO_OPERACION_LEVANTAMIENTO_DESCUENTO_CAPITA:
                str = "Levantamiento Descuento Capita";
                break;
            case TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS_ESPECIFICOS:
                str = "Levantamiento Motivos Especificos";
                break;
            case TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS_ESPECIFICOS_FACTURAS:
                str = "Levantamiento Motivos Especificos todas facturas";
                break;
            case TIPO_OPERACION_MARCAR_COPAGO_NO_EFECTIVO:
                str = "Marcar Copago No Efectivo";
                break;
            case TIPO_OPERACION_DESMARCAR_COPAGO_NO_EFECTIVO:
                str = "Desmarcar Copago No Efectivo";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }
    
}
