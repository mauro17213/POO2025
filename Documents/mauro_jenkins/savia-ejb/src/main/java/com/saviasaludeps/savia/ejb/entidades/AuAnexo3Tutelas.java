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
@Table(name = "au_anexo3_tutelas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo3Tutelas.findAll", query = "SELECT a FROM AuAnexo3Tutelas a"),
    @NamedQuery(name = "AuAnexo3Tutelas.findById", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByNumeroTutela", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.numeroTutela = :numeroTutela"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByEstadoProceso", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.estadoProceso = :estadoProceso"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByExoneracionCopago", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.exoneracionCopago = :exoneracionCopago"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByFechaFallo", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.fechaFallo = :fechaFallo"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByFechaNotificacion", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.fechaNotificacion = :fechaNotificacion"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByFechaVencimiento", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByIntegralidad", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.integralidad = :integralidad"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByMedidaProvisional", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.medidaProvisional = :medidaProvisional"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByNumeroFallo", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.numeroFallo = :numeroFallo"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByRadicadoJuzgado", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.radicadoJuzgado = :radicadoJuzgado"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByFase", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.fase = :fase"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByUsuarioCrea", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByTerminalCrea", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByUsuarioModifica", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByTerminalModifica", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuAnexo3Tutelas.findByFechaHoraModifica", query = "SELECT a FROM AuAnexo3Tutelas a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuAnexo3Tutelas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_tutela")
    private int numeroTutela;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "estado_proceso")
    private String estadoProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exoneracion_copago")
    private boolean exoneracionCopago;
    @Column(name = "fecha_fallo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFallo;
    @Column(name = "fecha_notificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaNotificacion;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "integralidad")
    private Boolean integralidad;
    @Column(name = "medida_provisional")
    private Boolean medidaProvisional;
    @Size(max = 16)
    @Column(name = "numero_fallo")
    private String numeroFallo;
    @Size(max = 32)
    @Column(name = "radicado_juzgado")
    private String radicadoJuzgado;
    @Size(max = 32)
    @Column(name = "fase")
    private String fase;
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
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;

    public AuAnexo3Tutelas() {
    }

    public AuAnexo3Tutelas(Integer id) {
        this.id = id;
    }

    public AuAnexo3Tutelas(Integer id, int numeroTutela, String estadoProceso, boolean exoneracionCopago, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.numeroTutela = numeroTutela;
        this.estadoProceso = estadoProceso;
        this.exoneracionCopago = exoneracionCopago;
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

    public int getNumeroTutela() {
        return numeroTutela;
    }

    public void setNumeroTutela(int numeroTutela) {
        this.numeroTutela = numeroTutela;
    }

    public String getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public boolean getExoneracionCopago() {
        return exoneracionCopago;
    }

    public void setExoneracionCopago(boolean exoneracionCopago) {
        this.exoneracionCopago = exoneracionCopago;
    }

    public Date getFechaFallo() {
        return fechaFallo;
    }

    public void setFechaFallo(Date fechaFallo) {
        this.fechaFallo = fechaFallo;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Boolean getIntegralidad() {
        return integralidad;
    }

    public void setIntegralidad(Boolean integralidad) {
        this.integralidad = integralidad;
    }

    public Boolean getMedidaProvisional() {
        return medidaProvisional;
    }

    public void setMedidaProvisional(Boolean medidaProvisional) {
        this.medidaProvisional = medidaProvisional;
    }

    public String getNumeroFallo() {
        return numeroFallo;
    }

    public void setNumeroFallo(String numeroFallo) {
        this.numeroFallo = numeroFallo;
    }

    public String getRadicadoJuzgado() {
        return radicadoJuzgado;
    }

    public void setRadicadoJuzgado(String radicadoJuzgado) {
        this.radicadoJuzgado = radicadoJuzgado;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
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

    public AuAnexos3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexos3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
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
        if (!(object instanceof AuAnexo3Tutelas)) {
            return false;
        }
        AuAnexo3Tutelas other = (AuAnexo3Tutelas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo3Tutelas[ id=" + id + " ]";
    }
    
}
