/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.anticipo;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author NEXOS
 */
public class AntAnticipoValor extends Auditoria{
    //tipo
    public static final int TIPO_CREACION_COTIZACION = 0;
    public static final int TIPO_RECHAZO_ITEM = 1;
    public static final int TIPO_NEGACION_ITEM = 2; 
    public static final int TIPO_DEVUELVE_COTIZACION = 3; 
    public static final int TIPO_ANULA_SOLICITUD = 4; 
    public static final int TIPO_ANULA_AUTORIZACION = 5;
    public static final int TIPO_NO_PRESTADO = 6; 
    
    private Integer id;
    private String observacion;
    private boolean devolucion;
    private Integer tipoDevolucion;
    private BigDecimal valor;
    private AntAnticipo antAnticiposId;
    private AntAnticipoItem antAnticipoItemsId;
    private AuCotizacion auCotizacionesId;
    
    // variables auxiliares
    private AuAnexo4 auAnexo4;
            
    public AntAnticipoValor(){
        
    }
    
    public AntAnticipoValor(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isDevolucion() {
        return devolucion;
    }

    public void setDevolucion(boolean devolucion) {
        this.devolucion = devolucion;
    }

    public Integer getTipoDevolucion() {
        return tipoDevolucion;
    }

    public void setTipoDevolucion(Integer tipoDevolucion) {
        this.tipoDevolucion = tipoDevolucion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public AntAnticipo getAntAnticiposId() {
        return antAnticiposId;
    }

    public void setAntAnticiposId(AntAnticipo antAnticiposId) {
        this.antAnticiposId = antAnticiposId;
    }

    public AntAnticipoItem getAntAnticipoItemsId() {
        return antAnticipoItemsId;
    }

    public void setAntAnticipoItemsId(AntAnticipoItem antAnticipoItemsId) {
        this.antAnticipoItemsId = antAnticipoItemsId;
    }

    public AuCotizacion getAuCotizacionesId() {
        return auCotizacionesId;
    }

    public void setAuCotizacionesId(AuCotizacion auCotizacionesId) {
        this.auCotizacionesId = auCotizacionesId;
    }

    public AuAnexo4 getAuAnexo4() {
        return auAnexo4;
    }

    public void setAuAnexo4(AuAnexo4 auAnexo4) {
        this.auAnexo4 = auAnexo4;
    }
    
    // metodos
    public String getDevolucionStr(){
        String strDevolucionStr = "No";
        if(devolucion){
            strDevolucionStr = "Si";
        }
        return strDevolucionStr;
    }
    
    public String getTipoDevolucionStr(){
        String strTipoDevolucionStr = "";
        if(tipoDevolucion != null){
            switch(tipoDevolucion){
                case TIPO_CREACION_COTIZACION:
                    strTipoDevolucionStr = "No Aplica";
                    break;
                case TIPO_RECHAZO_ITEM:
                    strTipoDevolucionStr = "Rechazo Item";
                    break;
                case TIPO_NEGACION_ITEM:
                    strTipoDevolucionStr = "Negación Item";
                    break;
                 case TIPO_DEVUELVE_COTIZACION:
                    strTipoDevolucionStr = "Devuelve cotización";
                    break;
                case TIPO_ANULA_SOLICITUD:
                    strTipoDevolucionStr = "Anula Solicitud";
                    break;
                case TIPO_ANULA_AUTORIZACION:
                    strTipoDevolucionStr = "Anula Autorización";
                    break;
                case TIPO_NO_PRESTADO:
                    strTipoDevolucionStr = "No prestado";
                    break;
            }
        }
        return strTipoDevolucionStr;
    }
    
    public String colorValorDevolucion(){
        String color = "";
        if(isDevolucion()){
            color = "color: red ; text-decoration-line: line-through ;";
        }
        return color;
    }
    
    @Override
    public String toString() {
        return "AntAnticipoValor{" + "id=" + id + ", observacion=" + observacion + ", devolucion=" + devolucion + ", tipoDevolucion=" + tipoDevolucion + ", valor=" + valor + ", antAnticiposId=" + antAnticiposId + ", antAnticipoItemsId=" + antAnticipoItemsId + ", auCotizacionesId=" + auCotizacionesId + '}';
    }
}
