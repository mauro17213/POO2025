/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jhonatan Jimenez
 */
public class AusSeguimiento extends Auditoria {

    public final static short TAMANIO_MAXIMO_CAMPOS_LARGOS = 30;
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private int maeEstadoId;
    private String maeEstadoCodigo;
    private String maeEstadoValor;
    private String observacion;

    private AusCaso casosId;
    private List<AusAdjunto> adjuntosList;
    
    private String datosCreacion;
    
    private String datosModificacion;
    
    private boolean desHabilitarSelectEstado;
    
    private int pos;

    private int maeSolicitudEstadoActual;
    
    private Maestro seguimientoNuevo;

    public AusSeguimiento() {
    }

    public AusSeguimiento(int id ) {
        this.id = id;
    }

    public AusSeguimiento(Integer id, int maeSolicitudEstado, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.observacion = observacion;
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
    
    public String getObservacionCorto() {
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

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public AusCaso getCasosId() {
        return casosId;
    }

    public void setCasosId(AusCaso casosId) {
        this.casosId = casosId;
    }
    
    public int getMaeSolicitudEstadoActual() {
        return maeSolicitudEstadoActual;
    }

    public void setMaeSolicitudEstadoActual(int maeSolicitudEstadoActual) {
        this.maeSolicitudEstadoActual = maeSolicitudEstadoActual;
    }

    public Maestro getSeguimientoNuevo() {
        return seguimientoNuevo;
    }

    public void setSeguimientoNuevo(Maestro seguimientoNuevo) {
        this.seguimientoNuevo = seguimientoNuevo;
    }
    
    public String getDatosCreacion() {
         datosCreacion = "El usuario " + this.getUsuarioCrea() + " el dia " + this.getFechaHoraCrea()
                + " desde la terminal " + this.getTerminalCrea() + " realizó la creación del registro.";

        return datosCreacion;
    }

    public void setDatosCreacion(String datosCreacion) {
        this.datosCreacion = datosCreacion;
    }

    public boolean getDesHabilitarSelectEstado() {
        return desHabilitarSelectEstado;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
    public void setDesHabilitarSelectEstado(boolean desHabilitarSelectEstado) {
        this.desHabilitarSelectEstado = desHabilitarSelectEstado;
    }
    
    public String getDatosModificacion() {
         if( !"".equals(this.getUsuarioModifica()) && this.getUsuarioModifica() != null ){
            datosModificacion = "El usuario "+ this.getUsuarioModifica()+" el dia "+this.getFechaHoraModifica()+
            " desde la terminal "+ this.getTerminalModifica()+" realizó la modificación del registro.";
        }
        return datosModificacion;
    }

    public void setDatosModificacion(String datosModificacion) {
        this.datosModificacion = datosModificacion;
    }

    @XmlTransient
    public List<AusAdjunto> getAdjuntosList() {
         if(adjuntosList == null){
            adjuntosList = new ArrayList<>();
        }
        return adjuntosList;
    }

    public void setAdjuntosList(List<AusAdjunto> adjuntosList) {
        this.adjuntosList = adjuntosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AusSeguimiento)) {
            return false;
        }
        AusSeguimiento other = (AusSeguimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AusSeguimiento{" + "id=" + id + ", maeEstadoId=" + maeEstadoId + ", maeEstadoCodigo=" + maeEstadoCodigo + ", maeEstadoValor=" + maeEstadoValor + ", observacion=" + observacion + ", casosId=" + casosId + ", datosCreacion=" + datosCreacion + ", datosModificacion=" + datosModificacion + ", desHabilitarSelectEstado=" + desHabilitarSelectEstado + ", pos=" + pos + ", maeSolicitudEstadoActual=" + maeSolicitudEstadoActual + ", seguimientoNuevo=" + seguimientoNuevo + '}';
    }

    
    
}
