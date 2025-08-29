/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Stiven Giraldo
 */
public class AuItemBitacora extends Auditoria {

    public static final int ID_SOLICITUD_DEVOLUCION = 1;
    public static final int ID_RESPUESTA_DEVOLUCION = 2;
    public static final int ID_SOLICITUD_COTIZACION = 3;
    public static final int ID_RESPUESTA_COTIZACION = 4;
    public static final int ID_DERIVACION = 5;
    public static final int ID_RECHAZO_ITEM = 6;
    public static final int ID_ANULACION_SOLICITUD = 7;
    public static final int ID_CAMBIO_ESTADO = 8;
    public static final int ID_RESPUESTA_DIRECCIONADO = 9;
    public static final int ID_INFORMACION_DIRECCIONADO = 10;
    public static final int ID_COMENTARIO = 11;
    public static final int ID_RESPUESTA_SEGUIMIENTO = 12;
    public static final int ID_NEGACION_ITEM = 13;
    public static final int ID_ALTERNATIVA = 14;
    public static final int ID_CONTROL_REGISTROS_ENTREGAS = 15;
    public static final int ID_VIGENCIA_COTIZACION = 16;

    public final static short TAMANIO_OBSERVACION = 50;

    private Integer id;
    private int tipo;
    private Integer estado;
    private String descripcion;
    private AuAnexo3Item auAnexo3ItemId;
    //aux
    private String observacionCorto;

    public AuItemBitacora() {

    }

    public AuItemBitacora(int id) {
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

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public AuAnexo3Item getAuAnexo3ItemId() {
        return auAnexo3ItemId;
    }

    public void setAuAnexo3ItemId(AuAnexo3Item auAnexo3ItemId) {
        this.auAnexo3ItemId = auAnexo3ItemId;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "AuItemBitacora{" + "id=" + id + ", tipo=" + tipo + ", estado=" + estado + ", descripcion=" + descripcion + ", auAnexo3ItemId=" + auAnexo3ItemId + ", observacionCorto=" + observacionCorto + '}';
    }

    public String getStrTipo() {
        String tipo = "";
        switch (getTipo()) {
            case ID_SOLICITUD_DEVOLUCION:
                tipo = "Solicitud Devolución";
                break;
            case ID_RESPUESTA_DEVOLUCION:
                tipo = "Respuesta Devolución";
                break;
            case ID_SOLICITUD_COTIZACION:
                tipo = "Solicitud Cotización";
                break;
            case ID_RESPUESTA_COTIZACION:
                tipo = "Respuesta Cotización";
                break;
            case ID_DERIVACION:
                tipo = "Derivación";
                break;
            case ID_RECHAZO_ITEM:
                tipo = "Rechazo Item";
                break;
            case ID_ANULACION_SOLICITUD://Se utiliza para bitacora del anexo3,verBitacorasAnexo3
                tipo = "Anulación solicitud";
                break;
            case ID_CAMBIO_ESTADO:
                tipo = "Cambio Estado";
                break;
            case ID_RESPUESTA_DIRECCIONADO:
                tipo = "Respuesta Direccionado";
                break;
            case ID_INFORMACION_DIRECCIONADO:
                tipo = "Información Direccionado";
                break;
            case ID_COMENTARIO:
                tipo = "Comentario";
                break;
            case ID_RESPUESTA_SEGUIMIENTO:
                tipo = "Respuesta Seguimiento";
                break;
            case ID_NEGACION_ITEM:
                tipo = "Negacion Item";
                break;
            case ID_ALTERNATIVA: 
                tipo = "Alternativa";
                break;
            case ID_CONTROL_REGISTROS_ENTREGAS: 
                tipo = "Control Registros Entregas";
                break;
            case ID_VIGENCIA_COTIZACION: 
                tipo = "Vigencia Cotización";
                break;
        }
        return tipo;
    }

    public String observacionCorto() {
        if (descripcion != null) {
            observacionCorto = descripcion;
            if (descripcion.length() >= TAMANIO_OBSERVACION) {
                return observacionCorto.substring(0, TAMANIO_OBSERVACION) + "..";
            } else {
                return observacionCorto;
            }
        }
        return observacionCorto;
    }

}
