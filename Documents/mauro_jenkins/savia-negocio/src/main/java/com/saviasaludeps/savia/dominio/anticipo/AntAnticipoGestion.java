/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.anticipo;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author NEXOS
 */
public class AntAnticipoGestion extends Auditoria {
    //tipo
    public static final int TIPO_CAMBIO_ESTADO = 1;
    public static final int TIPO_NOTA = 2;
    
    // tamaño de la descripción
    public static final int TAMANIO_DESCRIPCION = 65;
    
    private Integer id;
    private AntAnticipo antAnticiposId;
    private int tipo;
    private Integer estado;
    private String descripcion;
    private String descripcionCorto;

    public AntAnticipoGestion() {
        
    }

    public AntAnticipoGestion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AntAnticipo getAntAnticiposId() {
        if(antAnticiposId == null){
            antAnticiposId = new AntAnticipo();
        }
        return antAnticiposId;
    }

    public void setAntAnticiposId(AntAnticipo antAnticiposId) {
        this.antAnticiposId = antAnticiposId;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    // metodo auxiliares
    public String getDescripcionCorto() {
        if (getDescripcion() != null) {
            descripcionCorto = getDescripcion();
            if (getDescripcion().length() >= TAMANIO_DESCRIPCION) {
                return descripcionCorto.substring(0, TAMANIO_DESCRIPCION) + "... ";
            } else {
                return descripcionCorto + " " ;
            }
        }
        return descripcionCorto;
    }
    
    public String getEstadoStr(){
        String nombreEstado = "";
        if(estado != null){
            switch(estado){
                case AntAnticipo.ESTADO_PENDIENTE_COTIZACION:
                    nombreEstado = "Pendiente Cotización";
                    break;
                case AntAnticipo.ESTADO_CON_COTIZACION:
                    nombreEstado = "Con Cotizacion";
                    break;
                case AntAnticipo.ESTADO_GESTION_FIRMAS:
                    nombreEstado = "Gestion Firmas";
                    break;
                case AntAnticipo.ESTADO_AUTORIZADO:
                    nombreEstado = "Autorizado";
                    break;
                case AntAnticipo.ESTADO_GESTION_TESORERIA:
                    nombreEstado = "Gestion Tesorería";
                    break;
                case AntAnticipo.ESTADO_PAGADO:
                    nombreEstado = "Pagado";
                    break;
                case AntAnticipo.ESTADO_DEVUELTO:
                    nombreEstado = "Devuelto";
                    break;
                case AntAnticipo.ESTADO_CANCELADO:
                    nombreEstado = "Cancelado";
                    break;
                default:
                    break;
            }     
        }
        return nombreEstado;
    }
    
    public String getTipoStr(){
        String tipoStr = "";
        switch(tipo){
            case TIPO_CAMBIO_ESTADO:
                tipoStr = "Cambio Estado";
                break;
            case TIPO_NOTA:
                tipoStr = "Nota";
                break;
        }
        return tipoStr;
    }
    
    @Override
    public String toString() {
        return "AntAnticipoGestion{" + "id=" + id + ", antAnticiposId=" + antAnticiposId + ", tipo=" + tipo + ", descripcion=" + descripcion + '}';
    }
    
}
