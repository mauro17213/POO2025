package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author StivenGV
 */
public class CntjDocumento extends Auditoria {
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private short tipo;
    private String documentoNombre;
    private String documentoRuta;
    private String documentoArchivo;
    private Boolean documentoExiste;
    private List<CntjEstadoEjecucion> cntjEstadoEjecucionList;
    private CntjContrato cntjContratoId;
    private CntjExpediente cntjExpedienteId;
    private CntjPlantilla cntjPlantillaId;
    private CntjEstadoProcesoDocumento estadoDocumento;
    private Integer etapaContratacion;
    //Auxiliares
    private transient InputStream adjuntoStream;

    public CntjDocumento() {
        this.estadoDocumento = new CntjEstadoProcesoDocumento();
    }

    public CntjDocumento(Integer id) {
        this.id = id;
    }       

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public String getDocumentoNombre() {
        return documentoNombre;
    }

    public void setDocumentoNombre(String documentoNombre) {
        this.documentoNombre = documentoNombre;
    }

    public String getDocumentoRuta() {
        return documentoRuta;
    }

    public void setDocumentoRuta(String documentoRuta) {
        this.documentoRuta = documentoRuta;
    }

    public String getDocumentoArchivo() {
        return documentoArchivo;
    }

    public void setDocumentoArchivo(String documentoArchivo) {
        this.documentoArchivo = documentoArchivo;
    }

    public Boolean getDocumentoExiste() {
        return documentoExiste;
    }

    public void setDocumentoExiste(Boolean documentoExiste) {
        this.documentoExiste = documentoExiste;
    }

    public List<CntjEstadoEjecucion> getCntjEstadoEjecucionList() {
        return cntjEstadoEjecucionList;
    }

    public void setCntjEstadoEjecucionList(List<CntjEstadoEjecucion> cntjEstadoEjecucionList) {
        this.cntjEstadoEjecucionList = cntjEstadoEjecucionList;
    }

    public CntjContrato getCntjContratoId() {
        return cntjContratoId;
    }

    public void setCntjContratoId(CntjContrato cntjContratoId) {
        this.cntjContratoId = cntjContratoId;
    }

    public CntjExpediente getCntjExpedienteId() {
        return cntjExpedienteId;
    }

    public void setCntjExpedienteId(CntjExpediente cntjExpedienteId) {
        this.cntjExpedienteId = cntjExpedienteId;
    }

    public CntjPlantilla getCntjPlantillaId() {
        return cntjPlantillaId;
    }

    public void setCntjPlantillaId(CntjPlantilla cntjPlantillaId) {
        this.cntjPlantillaId = cntjPlantillaId;
    }

    public CntjEstadoProcesoDocumento getEstadoDocumento() {
        return estadoDocumento;
    }

    public void setEstadoDocumento(CntjEstadoProcesoDocumento estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    public Integer getEtapaContratacion() {
        return etapaContratacion;
    }

    public void setEtapaContratacion(Integer etapaContratacion) {
        this.etapaContratacion = etapaContratacion;
    }

    @Override
    public String toString() {
        return "CntjDocumento{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tipo=" + tipo + ", documentoNombre=" + documentoNombre + ", documentoRuta=" + documentoRuta + ", documentoArchivo=" + documentoArchivo + ", documentoExiste=" + documentoExiste + ", cntjContratoId=" + cntjContratoId + ", cntjExpedienteId=" + cntjExpedienteId + ", cntjPlantillaId=" + cntjPlantillaId + ", estadoDocumento=" + estadoDocumento + ", etapaContratacion=" + etapaContratacion + ", adjuntoStream=" + adjuntoStream + '}';
    }

}
