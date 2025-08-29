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
public class AuAnexo4Historico extends Auditoria {

    public static final int ESTADO_AUTORIZADA = 0;
    public static final int ESTADO_AUTORIZADA_AUTOMATICO = 1;
    public static final int ESTADO_ANULADA = 2;
    public static final int ESTADO_PREAUTORIZADO = 3;
    public static final int ESTADO_AUTORIZADA_PREAUTORIZACION = 4;
    public static final int ESTADO_ANULADA_PREAUTORIZACION = 5;
    //2023-09-27 jyperez estado pago anticipado
    public static final int ESTADO_AUTORIZADO_PAGO_ANTICIPADO = 6;
    //2023-09-29 jyperez estado anulado pago anticipado
    public static final int ESTADO_ANULADO_PAGO_ANTICIPADO = 7;

    public final static int ESTADO_APROBADO_AUDITORIA = 9757;
    public final static int ESTADO_APROBADO_AUTOMATICO = 9758;

    public final static String TIPO_ESTADO_MOTIVO_A4 = "48";
    public final static String VALOR_EDICION_COPAGO = "22";

    public final static String TIPO_ESTADO_MOTIVO_A3 = "50";
    public final static String VALOR_PREAUTORIZADO = "23";
    public final static String VALOR_AUTORIZADO_PREAUTORIZADO = "24";
    public final static String VALOR_ANULADO_PREAUTORIZADO = "25";
    public final static String VALOR_ANULADO = "11";

    public final static short TAMANIO_OBSERVACION = 50;

    private Integer id;
    private int maeCausaId;
    private Integer estado;
    private String maeCausaCodigo;
    private String maeCausaValor;
    private String observacionAutorizacion;
    private AuAnexo4 auAnexos4Id;

    //aux
    private String observacionCorto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeCausaId() {
        return maeCausaId;
    }

    public void setMaeCausaId(int maeCausaId) {
        this.maeCausaId = maeCausaId;
    }

    public String getMaeCausaCodigo() {
        return maeCausaCodigo;
    }

    public void setMaeCausaCodigo(String maeCausaCodigo) {
        this.maeCausaCodigo = maeCausaCodigo;
    }

    public String getMaeCausaValor() {
        return maeCausaValor;
    }

    public void setMaeCausaValor(String maeCausaValor) {
        this.maeCausaValor = maeCausaValor;
    }

    public String getObservacionAutorizacion() {
        return observacionAutorizacion;
    }

    public void setObservacionAutorizacion(String observacionAutorizacion) {
        this.observacionAutorizacion = observacionAutorizacion;
    }

    public AuAnexo4 getAuAnexos4Id() {
        return auAnexos4Id;
    }

    public void setAuAnexos4Id(AuAnexo4 auAnexos4Id) {
        this.auAnexos4Id = auAnexos4Id;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getEstadoStr() {
        switch (estado) {
            case ESTADO_AUTORIZADA:
                return "Autorizada";
            case ESTADO_AUTORIZADA_AUTOMATICO:
                return "Autorizada Automático";
            case ESTADO_ANULADA:
                return "Anulada";
            case ESTADO_PREAUTORIZADO:
                return "Preautorizado";
            case ESTADO_AUTORIZADA_PREAUTORIZACION:
                return "Autorizada Preautorización";
            case ESTADO_ANULADA_PREAUTORIZACION:
                return "Anulada Preautorización";
            case ESTADO_AUTORIZADO_PAGO_ANTICIPADO:
                return "Autorizada Pago Anticipado";
            case ESTADO_ANULADO_PAGO_ANTICIPADO:
                return "Anulada Pago Anticipado";
            default:
                return "";
        }
    }

    public String observacionCorto() {
        if (observacionAutorizacion != null) {
            observacionCorto = observacionAutorizacion;
            if (observacionAutorizacion.length() >= TAMANIO_OBSERVACION) {
                return observacionCorto.substring(0, TAMANIO_OBSERVACION) + "..";
            } else {
                return observacionCorto;
            }
        }
        return observacionCorto;
    }

    @Override
    public String toString() {
        return "AuAnexo4Historico{" + "id=" + id + ", maeCausaId=" + maeCausaId + ", estado=" + estado + ", maeCausaCodigo=" + maeCausaCodigo + ", maeCausaValor=" + maeCausaValor + ", observacionAutorizacion=" + observacionAutorizacion + ", auAnexos4Id=" + auAnexos4Id + ", observacionCorto=" + observacionCorto + '}';
    }

}
