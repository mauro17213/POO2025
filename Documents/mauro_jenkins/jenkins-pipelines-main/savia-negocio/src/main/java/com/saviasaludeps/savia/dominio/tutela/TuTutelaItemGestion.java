package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Jose Perez Hernandez
 */
public class TuTutelaItemGestion extends Auditoria{
    
    public final static short TAMANIO_MAXIMO_CAMPOS_LARGOS = 30;
    
    private Integer id;
    private String observacion;
    private String observacionIps;
    private int maeEstadoItemId;
    private String maeEstadoItemCodigo;
    private String maeEstadoItemValor;
    private TuTutelaItem tuTutelaItemId;

    public TuTutelaItemGestion() {
    }

    public TuTutelaItemGestion(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the observacionIps
     */
    public String getObservacionIps() {
        return observacionIps;
    }

    /**
     * @param observacionIps the observacionIps to set
     */
    public void setObservacionIps(String observacionIps) {
        this.observacionIps = observacionIps;
    }

    /**
     * @return the maeEstadoItemId
     */
    public int getMaeEstadoItemId() {
        return maeEstadoItemId;
    }

    /**
     * @param maeEstadoItemId the maeEstadoItemId to set
     */
    public void setMaeEstadoItemId(int maeEstadoItemId) {
        this.maeEstadoItemId = maeEstadoItemId;
    }

    /**
     * @return the maeEstadoItemCodigo
     */
    public String getMaeEstadoItemCodigo() {
        return maeEstadoItemCodigo;
    }

    /**
     * @param maeEstadoItemCodigo the maeEstadoItemCodigo to set
     */
    public void setMaeEstadoItemCodigo(String maeEstadoItemCodigo) {
        this.maeEstadoItemCodigo = maeEstadoItemCodigo;
    }

    /**
     * @return the maeEstadoItemValor
     */
    public String getMaeEstadoItemValor() {
        return maeEstadoItemValor;
    }

    /**
     * @param maeEstadoItemValor the maeEstadoItemValor to set
     */
    public void setMaeEstadoItemValor(String maeEstadoItemValor) {
        this.maeEstadoItemValor = maeEstadoItemValor;
    }

    /**
     * @return the tuTutelaItemId
     */
    public TuTutelaItem getTuTutelaItemId() {
        return tuTutelaItemId;
    }

    /**
     * @param tuTutelaItemId the tuTutelaItemId to set
     */
    public void setTuTutelaItemId(TuTutelaItem tuTutelaItemId) {
        this.tuTutelaItemId = tuTutelaItemId;
    }

    @Override
    public String toString() {
        return "TuTutelaItemGestion{" + "id=" + id + ", observacion=" + observacion + ", observacionIps=" + observacionIps + ", maeEstadoItemId=" + maeEstadoItemId + ", maeEstadoItemCodigo=" + maeEstadoItemCodigo + ", maeEstadoItemValor=" + maeEstadoItemValor + ", tuTutelaItemId=" + tuTutelaItemId + '}';
    }
    
    /**
     * @return the descripcion
     */
    public String getObservacionCorta() {
        String observacionCorto = "";
        if (getObservacion()!= null) {
            observacionCorto = getObservacion();
            if (getObservacion().length() >= TAMANIO_MAXIMO_CAMPOS_LARGOS) {
                return observacionCorto.substring(0, TAMANIO_MAXIMO_CAMPOS_LARGOS) + "..";
            } else {
                return observacionCorto;
            }
        }
        return observacionCorto;
    }
    
}
