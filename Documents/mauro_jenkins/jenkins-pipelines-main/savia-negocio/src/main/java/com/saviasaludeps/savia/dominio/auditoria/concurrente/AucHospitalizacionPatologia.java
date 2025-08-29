/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import static com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionSeguimiento.TAMANIO_DESCRIPCION;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author sgiraldov
 */
public class AucHospitalizacionPatologia extends Auditoria {
    
   
    private Integer id;
    private int maePatologiaId;
    private String maePatologiaCodigo;
    private String maePatologiaValor;
    private int estado;
    private String descripcion;
    private String descripcionCorto;
    private AucHospitalizacion aucHospitalizacionId;
    private int pos;
    
    public static final int ESTADO_CONFIRMADO = 1;
    public static final int ESTADO_EN_ESTUDIO = 2;
    public static final int ESTADO_NEGATIVO = 3;
    
    private static final String CONFIRMADO = "Confirmado";
    private static final String EN_ESTUDIO = "En Estudio";
    private static final String NEGATIVO = "Negativo";
    public static final int TAMANIO_DESCRIPCION = 50;
    
    public AucHospitalizacionPatologia() {
    }

    public AucHospitalizacionPatologia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaePatologiaId() {
        return maePatologiaId;
    }

    public void setMaePatologiaId(int maePatologiaId) {
        this.maePatologiaId = maePatologiaId;
    }

    public String getMaePatologiaCodigo() {
        return maePatologiaCodigo;
    }

    public void setMaePatologiaCodigo(String maePatologiaCodigo) {
        this.maePatologiaCodigo = maePatologiaCodigo;
    }

    public String getMaePatologiaValor() {
        return maePatologiaValor;
    }

    public void setMaePatologiaValor(String maePatologiaValor) {
        this.maePatologiaValor = maePatologiaValor;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcionCorto() {
        if (getDescripcion() != null) {
            descripcionCorto = getDescripcion();
            if (getDescripcion().length() >= TAMANIO_DESCRIPCION) {
                return descripcionCorto.substring(0, TAMANIO_DESCRIPCION) + "..";
            } else {
                return descripcionCorto;
            }
        }
        return descripcionCorto;
    }

    public void setDescripcionCorto(String descripcionCorto) {
        this.descripcionCorto = descripcionCorto;
    }
    
    public AucHospitalizacion getAucHospitalizacionId() {
        return aucHospitalizacionId;
    }

    public void setAucHospitalizacionId(AucHospitalizacion aucHospitalizacionId) {
        this.aucHospitalizacionId = aucHospitalizacionId;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
    //Metodos auxiliares
    public String getEstadoStr() {
        String est = "";
        switch(estado) {
            case ESTADO_CONFIRMADO:
                est = CONFIRMADO;
                break;
            case ESTADO_EN_ESTUDIO:
                est = EN_ESTUDIO;
                break;
            case ESTADO_NEGATIVO:
                est = NEGATIVO;
                break;
        }
        return est;
    }

    @Override
    public String toString() {
        return "AucHospitalizacionPatologia{" + "id=" + id + ", maePatologiaId=" + maePatologiaId + ", maePatologiaCodigo=" + maePatologiaCodigo + ", maePatologiaValor=" + maePatologiaValor + ", estado=" + estado + ", descripcion=" + descripcion + ", aucHospitalizacionId=" + aucHospitalizacionId + '}';
    }
    
}
