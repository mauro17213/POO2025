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
public class AuAnexo4Estado extends Auditoria {
    
    private Integer id;
    private int maeEstadoId;
    private String maeEstadoCodigo;
    private String maeEstadoValor;
    private Integer maeMotivoEstadoId;
    private String maeMotivoEstadoCodigo;
    private String maeMotivoEstadoValor;
    private String observacion;
    private AuAnexo4 auAnexo4Id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(int maeEstadoId) {
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

    public Integer getMaeMotivoEstadoId() {
        return maeMotivoEstadoId;
    }

    public void setMaeMotivoEstadoId(Integer maeMotivoEstadoId) {
        this.maeMotivoEstadoId = maeMotivoEstadoId;
    }

    public String getMaeMotivoEstadoCodigo() {
        return maeMotivoEstadoCodigo;
    }

    public void setMaeMotivoEstadoCodigo(String maeMotivoEstadoCodigo) {
        this.maeMotivoEstadoCodigo = maeMotivoEstadoCodigo;
    }

    public String getMaeMotivoEstadoValor() {
        return maeMotivoEstadoValor;
    }

    public void setMaeMotivoEstadoValor(String maeMotivoEstadoValor) {
        this.maeMotivoEstadoValor = maeMotivoEstadoValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public AuAnexo4 getAuAnexo4Id() {
        return auAnexo4Id;
    }

    public void setAuAnexo4Id(AuAnexo4 auAnexo4Id) {
        this.auAnexo4Id = auAnexo4Id;
    }

    
}
