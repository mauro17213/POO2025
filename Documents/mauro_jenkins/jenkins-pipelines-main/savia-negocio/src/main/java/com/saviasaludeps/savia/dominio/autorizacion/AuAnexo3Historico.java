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
public class AuAnexo3Historico extends Auditoria {

    public static final int TIPO_CAMBIO_ESTADO = 1;
    public static final int TIPO_EDITADO = 2;
    public static final int TIPO_ANULADO = 3;

    public final static short TAMANIO_OBSERVACION = 50;

    private Integer id;
    private int tipo;
    private Integer estado;
    private String observacion;
    private AuAnexo3Item auAnexo3ItemId;
    private AuAnexo3 auAnexo3Id;
    //aux
    private String observacionCorto;

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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public AuAnexo3Item getAuAnexo3ItemId() {
        return auAnexo3ItemId;
    }

    public void setAuAnexo3ItemId(AuAnexo3Item auAnexo3ItemId) {
        this.auAnexo3ItemId = auAnexo3ItemId;
    }

    public AuAnexo3 getAuAnexo3Id() {
        return auAnexo3Id;
    }

    public void setAuAnexo3Id(AuAnexo3 auAnexo3Id) {
        this.auAnexo3Id = auAnexo3Id;
    }

    public String getTipoStr() {
        switch (tipo) {
            case TIPO_CAMBIO_ESTADO:
                return "Cambio Estado";
            case TIPO_EDITADO:
                return "Editado";
            case TIPO_ANULADO:
                return "Anulado";
            default:
                return "";
        }
    }

    public String observacionCorto() {
        if (observacion != null) {
            observacionCorto = observacion;
            if (observacion.length() >= TAMANIO_OBSERVACION) {
                return observacionCorto.substring(0, TAMANIO_OBSERVACION) + "..";
            } else {
                return observacionCorto;
            }
        }
        return observacionCorto;
    }

    @Override
    public String toString() {
        return "AuAnexo3Historico{" + "id=" + id + ", tipo=" + tipo + ", estado=" + estado + ", observacion=" + observacion + ", auAnexo3ItemId=" + auAnexo3ItemId + ", auAnexo3Id=" + auAnexo3Id + ", observacionCorto=" + observacionCorto + '}';
    }

}
