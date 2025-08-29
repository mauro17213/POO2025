/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "cnt_profesionales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntProfesionales.findAll", query = "SELECT c FROM CntProfesionales c"),
    @NamedQuery(name = "CntProfesionales.findById", query = "SELECT c FROM CntProfesionales c WHERE c.id = :id"),
    @NamedQuery(name = "CntProfesionales.findByMaeTipoCodumentoId", query = "SELECT c FROM CntProfesionales c WHERE c.maeTipoCodumentoId = :maeTipoCodumentoId"),
    @NamedQuery(name = "CntProfesionales.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CntProfesionales c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CntProfesionales.findByMaeTipoDocumentoValor", query = "SELECT c FROM CntProfesionales c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CntProfesionales.findByDocumento", query = "SELECT c FROM CntProfesionales c WHERE c.documento = :documento"),
    @NamedQuery(name = "CntProfesionales.findByRegistroMedico", query = "SELECT c FROM CntProfesionales c WHERE c.registroMedico = :registroMedico"),
    @NamedQuery(name = "CntProfesionales.findByPrimerNombre", query = "SELECT c FROM CntProfesionales c WHERE c.primerNombre = :primerNombre"),
    @NamedQuery(name = "CntProfesionales.findBySegundoNombre", query = "SELECT c FROM CntProfesionales c WHERE c.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "CntProfesionales.findByPrimerApellido", query = "SELECT c FROM CntProfesionales c WHERE c.primerApellido = :primerApellido"),
    @NamedQuery(name = "CntProfesionales.findBySegundoApellido", query = "SELECT c FROM CntProfesionales c WHERE c.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "CntProfesionales.findByUsuarioCrea", query = "SELECT c FROM CntProfesionales c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntProfesionales.findByTerminalCrea", query = "SELECT c FROM CntProfesionales c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntProfesionales.findByFechaHoraCrea", query = "SELECT c FROM CntProfesionales c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntProfesionales.findByUsuarioModifica", query = "SELECT c FROM CntProfesionales c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntProfesionales.findByTerminalModifica", query = "SELECT c FROM CntProfesionales c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntProfesionales.findByFechaHoraModifica", query = "SELECT c FROM CntProfesionales c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntProfesionales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_codumento_id")
    private int maeTipoCodumentoId;
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
    @Size(min = 1, max = 32)
    @Column(name = "registro_medico")
    private String registroMedico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 64)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 64)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
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
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @OneToMany(mappedBy = "cntProfesionalesId", fetch = FetchType.LAZY)
    private List<AuAnexos2> auAnexos2List;
    @OneToMany(mappedBy = "cntProfesionalesId", fetch = FetchType.LAZY)
    private List<AuAnexos3> auAnexos3List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntProfesionalesId", fetch = FetchType.LAZY)
    private List<MpPrescripciones> mpPrescripcionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntProfesionalesId", fetch = FetchType.LAZY)
    private List<CntProfesionalPrestadores> cntProfesionalPrestadoresList;
    @OneToMany(mappedBy = "cntProfesionalesId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudes> auNoSolicitudesList;
    @OneToMany(mappedBy = "cntProfesionalesId", fetch = FetchType.LAZY)
    private List<RefAnexos9> refAnexos9List;

    public CntProfesionales() {
    }

    public CntProfesionales(Integer id) {
        this.id = id;
    }

    public CntProfesionales(Integer id, int maeTipoCodumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String documento, String registroMedico, String primerNombre, String primerApellido, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoCodumentoId = maeTipoCodumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.documento = documento;
        this.registroMedico = registroMedico;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
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

    public int getMaeTipoCodumentoId() {
        return maeTipoCodumentoId;
    }

    public void setMaeTipoCodumentoId(int maeTipoCodumentoId) {
        this.maeTipoCodumentoId = maeTipoCodumentoId;
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

    public String getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(String registroMedico) {
        this.registroMedico = registroMedico;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
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

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    @XmlTransient
    public List<AuAnexos2> getAuAnexos2List() {
        return auAnexos2List;
    }

    public void setAuAnexos2List(List<AuAnexos2> auAnexos2List) {
        this.auAnexos2List = auAnexos2List;
    }

    @XmlTransient
    public List<AuAnexos3> getAuAnexos3List() {
        return auAnexos3List;
    }

    public void setAuAnexos3List(List<AuAnexos3> auAnexos3List) {
        this.auAnexos3List = auAnexos3List;
    }

    @XmlTransient
    public List<MpPrescripciones> getMpPrescripcionesList() {
        return mpPrescripcionesList;
    }

    public void setMpPrescripcionesList(List<MpPrescripciones> mpPrescripcionesList) {
        this.mpPrescripcionesList = mpPrescripcionesList;
    }

    @XmlTransient
    public List<CntProfesionalPrestadores> getCntProfesionalPrestadoresList() {
        return cntProfesionalPrestadoresList;
    }

    public void setCntProfesionalPrestadoresList(List<CntProfesionalPrestadores> cntProfesionalPrestadoresList) {
        this.cntProfesionalPrestadoresList = cntProfesionalPrestadoresList;
    }

    @XmlTransient
    public List<AuNoSolicitudes> getAuNoSolicitudesList() {
        return auNoSolicitudesList;
    }

    public void setAuNoSolicitudesList(List<AuNoSolicitudes> auNoSolicitudesList) {
        this.auNoSolicitudesList = auNoSolicitudesList;
    }

    @XmlTransient
    public List<RefAnexos9> getRefAnexos9List() {
        return refAnexos9List;
    }

    public void setRefAnexos9List(List<RefAnexos9> refAnexos9List) {
        this.refAnexos9List = refAnexos9List;
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
        if (!(object instanceof CntProfesionales)) {
            return false;
        }
        CntProfesionales other = (CntProfesionales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntProfesionales[ id=" + id + " ]";
    }
    
}
