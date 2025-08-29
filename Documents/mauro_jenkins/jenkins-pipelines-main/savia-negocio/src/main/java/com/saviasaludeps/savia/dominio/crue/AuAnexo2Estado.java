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
public class AuAnexo2Estado extends Auditoria {
    
    private Integer id;
    private int estado;
    private Integer maeMotivoId;
    private String maeMotivoCodigo;
    private String maeMotivoValor;
    private String observacion;
    private AuAnexo2 auAnexo2;
    
    public static final int ESTADO_PREADMITIDA = 1;
    public static final int ESTADO_ADMITIDA = 2;
    public static final int ESTADO_ANULADA = 5;
    public static final int ESTADO_CANCELADA = 6;
    public static final int ESTADO_CERRADA = 13;
    public static final int ESTADO_DIRECCIONA = 7;
    public static final int ESTADO_RECHAZADA = 4;
            

    public AuAnexo2Estado() {
    }

    public AuAnexo2Estado(Integer id) {
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
    
    public String getEstadoStr() {
        String estadoString = "";
        switch (estado) {
            case ESTADO_PREADMITIDA:
                estadoString = "Preadmitida";
                break;
            case ESTADO_ADMITIDA:
                estadoString = "Admitida";
                break;
            case ESTADO_ANULADA:
                estadoString = "Anulada";
                break;
            case ESTADO_CANCELADA:
                estadoString = "Cancelada";
                break;
            case ESTADO_CERRADA:
                estadoString = "Cerrada";
                break;
            case ESTADO_DIRECCIONA:
                estadoString = "Direcciona";
                break;
             case ESTADO_RECHAZADA:
                estadoString = "Rechazada";
                break;
        }
        
        return estadoString;
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

    public AuAnexo2 getAuAnexo2() {
        return auAnexo2;
    }

    public void setAuAnexo2(AuAnexo2 auAnexo2) {
        this.auAnexo2 = auAnexo2;
    }

    @Override
    public String toString() {
        return "AuAnexo2Estado{" + "id=" + id + ", estado=" + getEstadoStr() + ", maeMotivoId=" + maeMotivoId + ", maeMotivoCodigo=" + maeMotivoCodigo + ", maeMotivoValor=" + maeMotivoValor + 
                ", observacion=" + observacion + ", refAnexos9Id=" + auAnexo2 +'}';
    }
    
    
}
