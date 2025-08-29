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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cntj_expedientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjExpedientes.findAll", query = "SELECT c FROM CntjExpedientes c"),
    @NamedQuery(name = "CntjExpedientes.findById", query = "SELECT c FROM CntjExpedientes c WHERE c.id = :id"),
    @NamedQuery(name = "CntjExpedientes.findByEstadoActual", query = "SELECT c FROM CntjExpedientes c WHERE c.estadoActual = :estadoActual"),
    @NamedQuery(name = "CntjExpedientes.findByJsonData", query = "SELECT c FROM CntjExpedientes c WHERE c.jsonData = :jsonData"),
    @NamedQuery(name = "CntjExpedientes.findByNumeroExpediente", query = "SELECT c FROM CntjExpedientes c WHERE c.numeroExpediente = :numeroExpediente"),
    @NamedQuery(name = "CntjExpedientes.findByFechaEjecucionEstado", query = "SELECT c FROM CntjExpedientes c WHERE c.fechaEjecucionEstado = :fechaEjecucionEstado"),
    @NamedQuery(name = "CntjExpedientes.findByContrato", query = "SELECT c FROM CntjExpedientes c WHERE c.contrato = :contrato"),
    @NamedQuery(name = "CntjExpedientes.findByUsuarioCrea", query = "SELECT c FROM CntjExpedientes c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjExpedientes.findByTerminalCrea", query = "SELECT c FROM CntjExpedientes c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjExpedientes.findByFechaHoraCrea", query = "SELECT c FROM CntjExpedientes c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjExpedientes.findByUsuarioModifica", query = "SELECT c FROM CntjExpedientes c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjExpedientes.findByTerminalModifica", query = "SELECT c FROM CntjExpedientes c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjExpedientes.findByFechaHoraModifica", query = "SELECT c FROM CntjExpedientes c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjExpedientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "estado_actual")
    private Integer estadoActual;
    @Size(max = 2147483647)
    @Column(name = "json_data")
    private String jsonData;
    @Size(max = 16)
    @Column(name = "numero_expediente")
    private String numeroExpediente;
    @Column(name = "fecha_ejecucion_estado")
    @Temporal(TemporalType.DATE)
    private Date fechaEjecucionEstado;
    @Size(max = 32)
    @Column(name = "contrato")
    private String contrato;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjExpedientesId", fetch = FetchType.LAZY)
    private List<CntjExpedienteResponsables> cntjExpedienteResponsablesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjExpedientesId", fetch = FetchType.LAZY)
    private List<CntjEstadoEjecuciones> cntjEstadoEjecucionesList;
    @OneToMany(mappedBy = "cntjExpedientesId", fetch = FetchType.LAZY)
    private List<CntjLineas> cntjLineasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjExpedientesId", fetch = FetchType.LAZY)
    private List<CntjExpedienteAuditorias> cntjExpedienteAuditoriasList;
    @JoinColumn(name = "cntj_estados_actual_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntjEstados cntjEstadosActualId;
    @OneToMany(mappedBy = "cntjExpedientesId", fetch = FetchType.LAZY)
    private List<CntjExpedientes> cntjExpedientesList;
    @JoinColumn(name = "cntj_expedientes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntjExpedientes cntjExpedientesId;
    @JoinColumn(name = "gn_usuarios_propietario_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosPropietarioId;
    @JoinColumn(name = "gn_usuarios_responsable_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosResponsableId;
    @JoinColumn(name = "cntj_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjProcesos cntjProcesosId;
    @OneToMany(mappedBy = "cntjExpedientesId", fetch = FetchType.LAZY)
    private List<CntjContratos> cntjContratosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjExpedientesId", fetch = FetchType.LAZY)
    private List<CntjDocumentos> cntjDocumentosList;

    public CntjExpedientes() {
    }

    public CntjExpedientes(Integer id) {
        this.id = id;
    }

    public CntjExpedientes(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public Integer getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Integer estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public Date getFechaEjecucionEstado() {
        return fechaEjecucionEstado;
    }

    public void setFechaEjecucionEstado(Date fechaEjecucionEstado) {
        this.fechaEjecucionEstado = fechaEjecucionEstado;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
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
    public List<CntjExpedienteResponsables> getCntjExpedienteResponsablesList() {
        return cntjExpedienteResponsablesList;
    }

    public void setCntjExpedienteResponsablesList(List<CntjExpedienteResponsables> cntjExpedienteResponsablesList) {
        this.cntjExpedienteResponsablesList = cntjExpedienteResponsablesList;
    }

    @XmlTransient
    public List<CntjEstadoEjecuciones> getCntjEstadoEjecucionesList() {
        return cntjEstadoEjecucionesList;
    }

    public void setCntjEstadoEjecucionesList(List<CntjEstadoEjecuciones> cntjEstadoEjecucionesList) {
        this.cntjEstadoEjecucionesList = cntjEstadoEjecucionesList;
    }

    @XmlTransient
    public List<CntjLineas> getCntjLineasList() {
        return cntjLineasList;
    }

    public void setCntjLineasList(List<CntjLineas> cntjLineasList) {
        this.cntjLineasList = cntjLineasList;
    }

    @XmlTransient
    public List<CntjExpedienteAuditorias> getCntjExpedienteAuditoriasList() {
        return cntjExpedienteAuditoriasList;
    }

    public void setCntjExpedienteAuditoriasList(List<CntjExpedienteAuditorias> cntjExpedienteAuditoriasList) {
        this.cntjExpedienteAuditoriasList = cntjExpedienteAuditoriasList;
    }

    public CntjEstados getCntjEstadosActualId() {
        return cntjEstadosActualId;
    }

    public void setCntjEstadosActualId(CntjEstados cntjEstadosActualId) {
        this.cntjEstadosActualId = cntjEstadosActualId;
    }

    @XmlTransient
    public List<CntjExpedientes> getCntjExpedientesList() {
        return cntjExpedientesList;
    }

    public void setCntjExpedientesList(List<CntjExpedientes> cntjExpedientesList) {
        this.cntjExpedientesList = cntjExpedientesList;
    }

    public CntjExpedientes getCntjExpedientesId() {
        return cntjExpedientesId;
    }

    public void setCntjExpedientesId(CntjExpedientes cntjExpedientesId) {
        this.cntjExpedientesId = cntjExpedientesId;
    }

    public GnUsuarios getGnUsuariosPropietarioId() {
        return gnUsuariosPropietarioId;
    }

    public void setGnUsuariosPropietarioId(GnUsuarios gnUsuariosPropietarioId) {
        this.gnUsuariosPropietarioId = gnUsuariosPropietarioId;
    }

    public GnUsuarios getGnUsuariosResponsableId() {
        return gnUsuariosResponsableId;
    }

    public void setGnUsuariosResponsableId(GnUsuarios gnUsuariosResponsableId) {
        this.gnUsuariosResponsableId = gnUsuariosResponsableId;
    }

    public CntjProcesos getCntjProcesosId() {
        return cntjProcesosId;
    }

    public void setCntjProcesosId(CntjProcesos cntjProcesosId) {
        this.cntjProcesosId = cntjProcesosId;
    }

    @XmlTransient
    public List<CntjContratos> getCntjContratosList() {
        return cntjContratosList;
    }

    public void setCntjContratosList(List<CntjContratos> cntjContratosList) {
        this.cntjContratosList = cntjContratosList;
    }

    @XmlTransient
    public List<CntjDocumentos> getCntjDocumentosList() {
        return cntjDocumentosList;
    }

    public void setCntjDocumentosList(List<CntjDocumentos> cntjDocumentosList) {
        this.cntjDocumentosList = cntjDocumentosList;
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
        if (!(object instanceof CntjExpedientes)) {
            return false;
        }
        CntjExpedientes other = (CntjExpedientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjExpedientes[ id=" + id + " ]";
    }
    
}
