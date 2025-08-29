
package com.saviasaludeps.savia.dominio.facturacionelectronica;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class FeDocumento extends Auditoria implements Serializable{

    private Integer id;
    private String prestadorNit;
    private short documentoTipo;
    private int documentoId;
    private String documentoNumero;
    private BigDecimal documentoValor;
    private String estado;
    private String codigoUnicoDian;
    private String estadoDescripcion;
    private String referenciaPrestadoroNit;
    private Short referenciaDocumentoTipo;
    private Integer referenciaDocumentoId;
    private String referenciaDocumentoNumero;
    private Date fechaDocumento;
    private Date fechaRadicacion;
    private List<FeDocumento> feDocumentosList;
    private FeDocumento feDocumento;

    public FeDocumento() {
    }

    public FeDocumento(Integer id) {
        this.id = id;
    }

    public FeDocumento(Integer id, String prestadorNit, short documentoTipo, int documentoId, String documentoNumero, BigDecimal documentoValor, String estado, String codigoUnicoDian, String estadoDescripcion, Date fechaDocumento, Date fechaRadicacion, Date fechaHoraCrea) {
        this.id = id;
        this.prestadorNit = prestadorNit;
        this.documentoTipo = documentoTipo;
        this.documentoId = documentoId;
        this.documentoNumero = documentoNumero;
        this.documentoValor = documentoValor;
        this.estado = estado;
        this.codigoUnicoDian = codigoUnicoDian;
        this.estadoDescripcion = estadoDescripcion;
        this.fechaDocumento = fechaDocumento;
        this.fechaRadicacion = fechaRadicacion;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrestadorNit() {
        return prestadorNit;
    }

    public void setPrestadorNit(String prestadorNit) {
        this.prestadorNit = prestadorNit;
    }

    public short getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(short documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public int getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(int documentoId) {
        this.documentoId = documentoId;
    }

    public String getDocumentoNumero() {
        return documentoNumero;
    }

    public void setDocumentoNumero(String documentoNumero) {
        this.documentoNumero = documentoNumero;
    }

    public BigDecimal getDocumentoValor() {
        return documentoValor;
    }

    public void setDocumentoValor(BigDecimal documentoValor) {
        this.documentoValor = documentoValor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoUnicoDian() {
        return codigoUnicoDian;
    }

    public void setCodigoUnicoDian(String codigoUnicoDian) {
        this.codigoUnicoDian = codigoUnicoDian;
    }

    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public void setEstadoDescripcion(String estadoDescripcion) {
        this.estadoDescripcion = estadoDescripcion;
    }

    public String getReferenciaPrestadoroNit() {
        return referenciaPrestadoroNit;
    }

    public void setReferenciaPrestadoroNit(String referenciaPrestadoroNit) {
        this.referenciaPrestadoroNit = referenciaPrestadoroNit;
    }

    public Short getReferenciaDocumentoTipo() {
        return referenciaDocumentoTipo;
    }

    public void setReferenciaDocumentoTipo(Short referenciaDocumentoTipo) {
        this.referenciaDocumentoTipo = referenciaDocumentoTipo;
    }

    public Integer getReferenciaDocumentoId() {
        return referenciaDocumentoId;
    }

    public void setReferenciaDocumentoId(Integer referenciaDocumentoId) {
        this.referenciaDocumentoId = referenciaDocumentoId;
    }

    public String getReferenciaDocumentoNumero() {
        return referenciaDocumentoNumero;
    }

    public void setReferenciaDocumentoNumero(String referenciaDocumentoNumero) {
        this.referenciaDocumentoNumero = referenciaDocumentoNumero;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }
    
    public List<FeDocumento> getFeDocumentosList() {
        return feDocumentosList;
    }

    public void setFeDocumentosList(List<FeDocumento> feDocumentosList) {
        this.feDocumentosList = feDocumentosList;
    }

    public FeDocumento getFeDocumento() {
        return feDocumento;
    }

    public void setFeDocumento(FeDocumento feDocumento) {
        this.feDocumento = feDocumento;
    }

    @Override
    public String toString() {
        return "FeDocumento{" + "id=" + id + ", prestadorNit=" + prestadorNit + ", documentoTipo=" + documentoTipo + ", documentoId=" + documentoId + ", documentoNumero=" + documentoNumero + ", documentoValor=" + documentoValor + ", estado=" + estado + ", codigoUnicoDian=" + codigoUnicoDian + ", estadoDescripcion=" + estadoDescripcion + ", referenciaPrestadoroNit=" + referenciaPrestadoroNit + ", referenciaDocumentoTipo=" + referenciaDocumentoTipo + ", referenciaDocumentoId=" + referenciaDocumentoId + ", referenciaDocumentoNumero=" + referenciaDocumentoNumero + ", fechaDocumento=" + fechaDocumento + ", fechaRadicacion=" + fechaRadicacion + ", fechaHoraCrea=" + fechaHoraCrea + '}';
    }

    
}
