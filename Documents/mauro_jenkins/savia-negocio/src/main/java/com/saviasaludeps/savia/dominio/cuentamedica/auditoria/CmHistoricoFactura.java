/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class CmHistoricoFactura extends Auditoria {

    private Integer id;
    private String cmHistoricoFacturas;
    private int tipos;
    private String descripcion;
    private CmFactura cmFactura;
    
    public final static int TIPO_DIAGNOSTICO = 0;
    public final static int TIPO_AUTORIZACION = 1;
    public final static int TIPO_CONCEPTO_CONTABLE = 2;
    public final static int TIPO_MOTIVO_GLOSA = 3;
    public final static int TIPO_REVERTIR_FACTURA_M2 = 4;
    public final static int TIPO_DESCUENTO_CAPITA = 5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipos() {
        return tipos;
    }

    public void setTipos(int tipos) {
        this.tipos = tipos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CmFactura getCmFactura() {
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

    public String getCmHistoricoFacturas() {
        return cmHistoricoFacturas;
    }

    public void setCmHistoricoFacturas(String cmHistoricoFacturas) {
        this.cmHistoricoFacturas = cmHistoricoFacturas;
    }
     
    public String getEstadoFacturaStr() {
        return CmHistoricoFactura.getTiposStr(getTipos());
    }
    
    public static String getTiposStr(int tipos) {
        String str;
        switch (tipos) {
            case TIPO_AUTORIZACION:
                str = "Autorización";
                break;
            case TIPO_CONCEPTO_CONTABLE:
                str = "Concepto Contable";
                break;
            case TIPO_DIAGNOSTICO:
                str = "Diagnóstico";
                break;
            case TIPO_MOTIVO_GLOSA:
                str = "Motivo Glosa";
                break;
             case TIPO_REVERTIR_FACTURA_M2:
                str = "Revertir Factura M2";
                break;
            case TIPO_DESCUENTO_CAPITA:
                str = "Descuento Capita";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    @Override
    public String toString() {
        return "CmHistoricoFactura{" + "id=" + id + ", cmHistoricoFacturas=" + cmHistoricoFacturas + ", tipos=" + tipos + ", descripcion=" + descripcion + '}';
    }
    
}
