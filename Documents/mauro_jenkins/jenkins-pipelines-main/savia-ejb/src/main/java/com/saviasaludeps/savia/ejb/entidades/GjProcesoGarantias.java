/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "gj_proceso_garantias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GjProcesoGarantias.findAll", query = "SELECT g FROM GjProcesoGarantias g"),
    @NamedQuery(name = "GjProcesoGarantias.findById", query = "SELECT g FROM GjProcesoGarantias g WHERE g.id = :id"),
    @NamedQuery(name = "GjProcesoGarantias.findByMaeTipoDocumentoId", query = "SELECT g FROM GjProcesoGarantias g WHERE g.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "GjProcesoGarantias.findByMaeTipoDocumentoCodigo", query = "SELECT g FROM GjProcesoGarantias g WHERE g.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "GjProcesoGarantias.findByMaeTipoDocumentoValor", query = "SELECT g FROM GjProcesoGarantias g WHERE g.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "GjProcesoGarantias.findByDocumento", query = "SELECT g FROM GjProcesoGarantias g WHERE g.documento = :documento"),
    @NamedQuery(name = "GjProcesoGarantias.findByNombres", query = "SELECT g FROM GjProcesoGarantias g WHERE g.nombres = :nombres"),
    @NamedQuery(name = "GjProcesoGarantias.findByApellidos", query = "SELECT g FROM GjProcesoGarantias g WHERE g.apellidos = :apellidos"),
    @NamedQuery(name = "GjProcesoGarantias.findByUsuarioCrea", query = "SELECT g FROM GjProcesoGarantias g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GjProcesoGarantias.findByTerminalCrea", query = "SELECT g FROM GjProcesoGarantias g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GjProcesoGarantias.findByFechaHoraModifica", query = "SELECT g FROM GjProcesoGarantias g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GjProcesoGarantias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_documento_id")
    private int maeTipoDocumentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "gj_proceso_historicos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GjProcesoHistoricos gjProcesoHistoricosId;
    @JoinColumn(name = "gj_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GjProcesos gjProcesosId;

    public GjProcesoGarantias() {
    }

    public GjProcesoGarantias(Integer id) {
        this.id = id;
    }

    public GjProcesoGarantias(Integer id, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String documento, String nombres, String apellidos, String usuarioCrea, String terminalCrea, Date fechaHoraModifica) {
        this.id = id;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(int maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public GjProcesoHistoricos getGjProcesoHistoricosId() {
        return gjProcesoHistoricosId;
    }

    public void setGjProcesoHistoricosId(GjProcesoHistoricos gjProcesoHistoricosId) {
        this.gjProcesoHistoricosId = gjProcesoHistoricosId;
    }

    public GjProcesos getGjProcesosId() {
        return gjProcesosId;
    }

    public void setGjProcesosId(GjProcesos gjProcesosId) {
        this.gjProcesosId = gjProcesosId;
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
        if (!(object instanceof GjProcesoGarantias)) {
            return false;
        }
        GjProcesoGarantias other = (GjProcesoGarantias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GjProcesoGarantias[ id=" + id + " ]";
    }
    
}
