
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class AsegValidacionDerecho extends Auditoria {

    private Integer id;
    private int estado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private Integer registrosCargados;
    private Integer registrosEncontrados;
    private String ruta;
    private String archivo;
    //campos adicionales - Tipo Validación 0- Contrato Afiliado 1-Numero Documento
    private int tipoValidacion;
    private transient InputStream adjuntoStream;
    private List<String> afiliadosValidacion;
    
    public static final int TIPO_VALIDACION_CONTRATO = 1;
    public static final int TIPO_VALIDACION_DOCUMENTO = 2;

    public AsegValidacionDerecho() {
    }

    public AsegValidacionDerecho(Integer id) {
        this.id = id;
    }

    public AsegValidacionDerecho(Integer id, int estado, Date fechaHoraInicio, String ruta, String archivo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.ruta = ruta;
        this.archivo = archivo;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
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

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Integer getRegistrosCargados() {
        return registrosCargados;
    }

    public void setRegistrosCargados(Integer registrosCargados) {
        this.registrosCargados = registrosCargados;
    }

    public Integer getRegistrosEncontrados() {
        return registrosEncontrados;
    }

    public void setRegistrosEncontrados(Integer registrosEncontrados) {
        this.registrosEncontrados = registrosEncontrados;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
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
        if (!(object instanceof AsegValidacionDerecho)) {
            return false;
        }
        AsegValidacionDerecho other = (AsegValidacionDerecho) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * @return the tipoValidacion
     */
    public int getTipoValidacion() {
        return tipoValidacion;
    }

    /**
     * @param tipoValidacion the tipoValidacion to set
     */
    public void setTipoValidacion(int tipoValidacion) {
        this.tipoValidacion = tipoValidacion;
    }

    /**
     * @return the adjuntoStream
     */
    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    /**
     * @param adjuntoStream the adjuntoStream to set
     */
    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    /**
     * @return the afiliadosValidacion
     */
    public List<String> getAfiliadosValidacion() {
        return afiliadosValidacion;
    }

    /**
     * @param afiliadosValidacion the afiliadosValidacion to set
     */
    public void setAfiliadosValidacion(List<String> afiliadosValidacion) {
        this.afiliadosValidacion = afiliadosValidacion;
    }
    
    public String getFechaStr(Date fecha) {
        String mensaje = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            mensaje = sdf.format(fecha);
        }catch (Exception ex) {
            mensaje= "";
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return "AsegValidacionDerecho{" + "id=" + id + ", estado=" + estado + ", fechaHoraInicio=" + getFechaStr(fechaHoraInicio) + ", fechaHoraFin=" + getFechaStr(fechaHoraFin) + ", registrosCargados=" + registrosCargados + ", registrosEncontrados=" + registrosEncontrados + ", ruta=" + ruta + ", archivo=" + archivo + ", usuarioCrea=" + usuarioCrea + ", terminalCrea=" + terminalCrea + ", fechaHoraCrea=" + getFechaStr(fechaHoraCrea) + ", usuarioModifica=" + usuarioModifica + ", terminalModifica=" + terminalModifica + ", fechaHoraModifica=" + getFechaStr(fechaHoraModifica) + ", tipoValidacion=" + tipoValidacion + ", afiliadosValidacion=" + afiliadosValidacion + '}';
    }
    
}
