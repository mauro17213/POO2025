/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author jperezn
 */
public class CmFacturaEstado extends Auditoria{
    
    public final static int TIPO_ESTADO_RADICADO = 0;
    public final static int TIPO_ESTADO_EN_AUDITORIA = 1;
    public final static int TIPO_ESTADO_AUDITADA_GLOSA = 2;
    public final static int TIPO_ESTADO_CIERRE_AUDITORIA = 3;
    public final static int TIPO_ESTADO_CONCILIADA = 4;
    public final static int TIPO_ESTADO_DEVUELTA = 5;
    public final static int TIPO_ESTADO_DEVUELTA_ESPERA_SAP = 6;
    public final static int TIPO_ESTADO_AUDITADA_ESPERA_SAP_M2  = 7;
    public final static int TIPO_ESTADO_CONCILIADA_ESPERA_SAP_M3 = 8;
    public final static int TIPO_ESTADO_AUDITADA = 9;
    public final static int TIPO_ESTADO_RADICADO_ESPERA_SAP_M1 = 10;
    public final static int TIPO_ESTADO_RADICADO_ESPERA_SAP_M2 = 11;
    public final static int TIPO_ESTADO_ANULADA = 12;
    public final static int TIPO_ESTADO_EN_PROCESO_AUDITORIA = 13;
    
    
    private Integer id;
    private int estadoFactura;
    private CmFactura cmFactura;
    private int origenFactura;

    public CmFacturaEstado() {
    }

    public CmFacturaEstado(Integer id) {
        this.id = id;
    }

    public CmFacturaEstado(Integer id, int estadoFactura) {
        this.id = id;
        this.estadoFactura = estadoFactura;
    }

    public CmFacturaEstado( CmFactura cmFactura, int estadoFactura, String usuario, String terminal, Date fecha) {
        this.estadoFactura = estadoFactura;
        this.cmFactura = cmFactura;
        this.usuarioCrea = usuario;
        this.fechaHoraCrea = fecha;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(int estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public CmFactura getCmFactura() {
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

    public int getOrigenFactura() {
        return origenFactura;
    }

    public void setOrigenFactura(int origenFactura) {
        this.origenFactura = origenFactura;
    }
    
    public String getEstadFacturaStr() {
        return CmFacturaEstado.getEstadFacturaStr(getEstadoFactura());
    }
    
    public static String getEstadFacturaStr(int estadoFactura) {
        String str;
        switch (estadoFactura) {
            case (TIPO_ESTADO_ANULADA):
                str = "Anulada";
                break;
            case (TIPO_ESTADO_AUDITADA):
                str = "Auditada";
                break;
            case (TIPO_ESTADO_AUDITADA_ESPERA_SAP_M2):
                str = "Auditada espera sap m2";
                break;
            case (TIPO_ESTADO_AUDITADA_GLOSA):
                str = "Auditada glosada";
                break;
            case (TIPO_ESTADO_CIERRE_AUDITORIA):
                str = "Cierre auditoria";
                break;
            case (TIPO_ESTADO_CONCILIADA):
                str = "Conciliada";
                break;
            case (TIPO_ESTADO_CONCILIADA_ESPERA_SAP_M3):
                str = "Conciliada espera sap m3";
                break;
            case (TIPO_ESTADO_DEVUELTA):
                str = "Devuelta";
                break;
            case (TIPO_ESTADO_DEVUELTA_ESPERA_SAP):
                str = "Devuelta espera sap";
                break;
            case (TIPO_ESTADO_EN_AUDITORIA):
                str = "En auditoria";
                break;
            case (TIPO_ESTADO_EN_PROCESO_AUDITORIA):
                str = "En proceso auditoria";
                break;
            case (TIPO_ESTADO_RADICADO):
                str = "Radicada";
                break;
            case (TIPO_ESTADO_RADICADO_ESPERA_SAP_M1):
                str = "Radicado espera sap m1";
                break;
            case (TIPO_ESTADO_RADICADO_ESPERA_SAP_M2):
                str = "Radicado espera sap m2";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CmFacturaEstado)) {
            return false;
        }
        CmFacturaEstado other = (CmFacturaEstado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CmFacturaEstado{" + "id=" + id + ", estadoFactura=" + estadoFactura + '}';
    }

    
    
}
