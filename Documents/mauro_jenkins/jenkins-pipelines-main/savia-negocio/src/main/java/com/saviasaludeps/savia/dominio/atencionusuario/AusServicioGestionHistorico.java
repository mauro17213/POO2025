/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Jhonatan Jimenez
 */
public class AusServicioGestionHistorico extends Auditoria {
    
    public static final int TAMANIO_DESCRIPCION = 65;
    
    private Integer id;
    private int maeEstadoId;
    private String maeEstadoCodigo;
    private String maeEstadoValor;
    private String observacion;
    private String observacionCorto;
    private AusCasoServicio servicios;
    private String usuarioAsignado;
    
    public AusServicioGestionHistorico() {
    }

    public AusServicioGestionHistorico(Integer id) {
        this.id = id;
    }

    public AusServicioGestionHistorico(Integer id, int maeEstadoId, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeEstadoId = maeEstadoId;
        this.observacion = observacion;
        this.setUsuarioCrea(usuarioCrea);
        this.setTerminalCrea(terminalCrea);
        this.setFechaHoraCrea(fechaHoraCrea);
    }

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
    
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion =  observacion.length() <= 1 ? " " : observacion;
    }

    public String getObservacionCorto() {
         if (getObservacion() != null) {
            observacionCorto = getObservacion();
            if (getObservacion().length() >= TAMANIO_DESCRIPCION) {
                return observacionCorto.substring(0, TAMANIO_DESCRIPCION) + ".. ";
            } else {
                return observacionCorto;
            }
        }
        return observacionCorto;
    }

    public void setObservacionCorto(String observacionCorto) {
        this.observacionCorto = observacionCorto;
    }

    public AusCasoServicio getCasoServicios() {
        return servicios;
    }

    public void setCasoServicios(AusCasoServicio serviciosId) {
        this.servicios = serviciosId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AusServicioGestionHistorico)) {
            return false;
        }
        AusServicioGestionHistorico other = (AusServicioGestionHistorico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(String usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    @Override
    public String toString() {
        return "AusServicioGestionHistorico{" + "id=" + id + ", maeEstadoId=" + maeEstadoId + ", maeEstadoCodigo=" + maeEstadoCodigo + ", maeEstadoValor=" + maeEstadoValor + ", observacion=" + observacion + ", servicios=" + servicios + ", usuarioAsignado=" + usuarioAsignado + '}';
    }
    
    
}
