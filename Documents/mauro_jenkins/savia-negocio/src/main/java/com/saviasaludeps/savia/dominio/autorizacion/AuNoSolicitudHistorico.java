/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author NEXOS
 */
public class AuNoSolicitudHistorico extends Auditoria{
    
    //estados
    public static final int ESTADO_PENDIENTE = 1;
    public static final int ESTADO_EN_GESTION = 2;
    public static final int ESTADO_GESTIONADO = 3;
    public static final int ESTADO_ANULADO = 4;
    
    //tipo
    public static final int TIPO_CAMBIO_ESTADO = 1;
    public static final int TIPO_EDICION = 2;
    
    //tamaño de la descripcion
    public final static short TAMANIO_OBSERVACION = 10;
    
    private Integer id;
    private AuNoSolicitud auNoSolicitudesId;
    private Integer estado;
    private Integer tipo;
    private String observacion;
    
    //variable auxiliares
    private int posicion;
    private String observacionCorto;
   
    public AuNoSolicitudHistorico(){
        
    }
    
    public AuNoSolicitudHistorico(Integer id){
         this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuNoSolicitud getAuNoSolicitudesId() {
        return auNoSolicitudesId;
    }

    public void setAuNoSolicitudesId(AuNoSolicitud auNoSolicitudesId) {
        this.auNoSolicitudesId = auNoSolicitudesId;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getObservacionCorto() {
        if (observacion != null) {
            observacionCorto = observacion;
            if (observacion.length() >= TAMANIO_OBSERVACION) {
                observacionCorto = observacionCorto.substring(0, TAMANIO_OBSERVACION) + "..";
            } 
        }
        return observacionCorto;
    }

    public void setObservacionCorto(String observacionCorto) {
        this.observacionCorto = observacionCorto;
    }

    // Metodo adcionales
    public String getEstadoStr(){
        String nombreEstado = "";
        if(estado != null){
            switch(estado){
                case ESTADO_PENDIENTE:
                    nombreEstado = "Pendiente";
                    break;
                case ESTADO_EN_GESTION:
                    nombreEstado = "En Gestión";
                    break;
                case ESTADO_GESTIONADO:
                    nombreEstado = "Gestionado";
                    break;
                case ESTADO_ANULADO:
                    nombreEstado = "Anulado";
                    break;
                 default:
                    break;
            }   
        }
          
        return nombreEstado;
    }
    
    public String getTipoStr(){
        String tipoStr = "";
        if(tipo != null){
            switch(tipo){
                case TIPO_CAMBIO_ESTADO:
                    tipoStr = "Cambio Estado";
                    break;
                case TIPO_EDICION:
                    tipoStr = "Edició";
                    break;
                 default:
                    break;
            }  
        }
        return tipoStr;
    }
    
    @Override
    public String toString() {
        return "AuNoSolicitudHistorico{" + "id=" + id + ", auNoSolicitudesId=" + auNoSolicitudesId + ", estado=" + estado + ", tipo=" + tipo + ", observacion=" + observacion + '}';
    }
}
