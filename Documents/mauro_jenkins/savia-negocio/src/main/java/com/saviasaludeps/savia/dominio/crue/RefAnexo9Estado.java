/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Jaime Andres Olarte
 */
public class RefAnexo9Estado extends Auditoria {

    private Integer id;
    private int estado;
    private Integer maeMotivoId;
    private String maeMotivoCodigo;
    private String maeMotivoValor;
    private String observacion;
    private RefAnexo9 refAnexo9;
    private Integer maeEstadoId;
    private String maeEstadoCodigo;
    private String maeEstadoValor;
    
    public static final String ESTADO_TIPO = "90";
    public static final String ESTADO_PREADMITIDA_CODIGO = "1";
    public static final String ESTADO_ADMITIDA_CODIGO = "2";
    public static final String ESTADO_ANULADA_CODIGO = "5";
    public static final String ESTADO_CANCELADA_CODIGO = "6";
    public static final String ESTADO_CERRADA_CODIGO = "9";
    public static final String ESTADO_DIRECCIONA_CODIGO = "7";
    public static final String ESTADO_RECHAZADA_CODIGO = "4";
    public static final String ESTADO_GESTION_DE_REGULACION_CODIGO = "10";
    public static final String ESTADO_AUDITORIA_CODIGO = "11";
    public static final String ESTADO_PERTINENCIA_MEDICA_CODIGO = "12";
    public static final String ESTADO_GESTION_TELEASISTENCIA_CODIGO = "17";
    public static final String ESTADO_CONCEPTO_TELEASISTENCIA_CODIGO = "18";

    /*public static final Integer ESTADO_PREADMITIDA = 9791;
    public static final Integer ESTADO_ADMITIDA = 9792;
    public static final Integer ESTADO_ANULADA = 9795;
    public static final Integer ESTADO_CANCELADA = 9796;
    public static final Integer ESTADO_CERRADA = 9803;
    public static final Integer ESTADO_DIRECCIONA = 9797;
    public static final Integer ESTADO_RECHAZADA = 9794;
    public static final Integer ESTADO_GESTION_DE_REGULACION = 10286;
    public static final Integer ESTADO_AUDITORIA = 10287;
    public static final Integer ESTADO_PERTINENCIA_MEDICA = 10288;
    public static final Integer ESTADO_GESTION_TELEASISTENCIA = 61334;
    public static final Integer ESTADO_CONCEPTO_TELEASISTENCIA = 61335;*/

    public RefAnexo9Estado() {
    }

    public RefAnexo9Estado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getMaeMotivoId() {
        return maeMotivoId;
    }

    public void setMaeMotivoId(Integer maeMotivoId) {
        this.maeMotivoId = maeMotivoId;
    }

    public String getMaeMotivoCodigo() {
        return maeMotivoCodigo;
    }

    public void setMaeMotivoCodigo(String maeMotivoCodigo) {
        this.maeMotivoCodigo = maeMotivoCodigo;
    }

    public String getMaeMotivoValor() {
        return maeMotivoValor;
    }

    public void setMaeMotivoValor(String maeMotivoValor) {
        this.maeMotivoValor = maeMotivoValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public RefAnexo9 getRefAnexo9() {
        return refAnexo9;
    }

    public void setRefAnexo9(RefAnexo9 refAnexo9) {
        this.refAnexo9 = refAnexo9;
    }

    public Integer getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(Integer maeEstadoId) {
        this.maeEstadoId = maeEstadoId;
    }

    public String getMaeEstadoCodigo() {
        return maeEstadoCodigo;
    }

    public void setMaeEstadoCodigo(String maeEstadoCodigo) {
        this.maeEstadoCodigo = maeEstadoCodigo;
    }

    public String getMaeEstadoValor() {
        return maeEstadoValor;
    }

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
    }

    @Override
    public String toString() {
        return "RefAnexo9Estado{" + "id=" + id + ", estado=" + estado + ", maeMotivoId=" + maeMotivoId + ", maeMotivoCodigo=" + maeMotivoCodigo + ", maeMotivoValor=" + maeMotivoValor
                + ", observacion=" + observacion + ", maeEstadoId=" + maeEstadoId + ", maeEstadoCodigo=" + maeEstadoCodigo + ", maeEstadoValor=" + maeEstadoValor + "}";
    }
}
