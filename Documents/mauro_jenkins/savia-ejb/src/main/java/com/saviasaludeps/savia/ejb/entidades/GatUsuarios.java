/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "gat_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatUsuarios.findAll", query = "SELECT g FROM GatUsuarios g"),
    @NamedQuery(name = "GatUsuarios.findById", query = "SELECT g FROM GatUsuarios g WHERE g.id = :id"),
    @NamedQuery(name = "GatUsuarios.findByMaeTipoDocumentoId", query = "SELECT g FROM GatUsuarios g WHERE g.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "GatUsuarios.findByMaeTipoDocumentoCodigo", query = "SELECT g FROM GatUsuarios g WHERE g.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "GatUsuarios.findByMaeTipoDocumentoValor", query = "SELECT g FROM GatUsuarios g WHERE g.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "GatUsuarios.findByNumeroDocumento", query = "SELECT g FROM GatUsuarios g WHERE g.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "GatUsuarios.findByNombres", query = "SELECT g FROM GatUsuarios g WHERE g.nombres = :nombres"),
    @NamedQuery(name = "GatUsuarios.findByApellidos", query = "SELECT g FROM GatUsuarios g WHERE g.apellidos = :apellidos"),
    @NamedQuery(name = "GatUsuarios.findByFechaNacimiento", query = "SELECT g FROM GatUsuarios g WHERE g.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "GatUsuarios.findByUsuarioCrea", query = "SELECT g FROM GatUsuarios g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GatUsuarios.findByTerminalCrea", query = "SELECT g FROM GatUsuarios g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GatUsuarios.findByFechaHoraCrea", query = "SELECT g FROM GatUsuarios g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GatUsuarios implements Serializable {

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
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Size(max = 128)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 128)
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
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
    @OneToMany(mappedBy = "gatUsuariosId", fetch = FetchType.LAZY)
    private List<GatAtenciones> gatAtencionesList;
    @OneToMany(mappedBy = "gatUsuariosId", fetch = FetchType.LAZY)
    private List<GatTiquetes> gatTiquetesList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;

    public GatUsuarios() {
    }

    public GatUsuarios(Integer id) {
        this.id = id;
    }

    public GatUsuarios(Integer id, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String numeroDocumento, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroDocumento = numeroDocumento;
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    @XmlTransient
    public List<GatAtenciones> getGatAtencionesList() {
        return gatAtencionesList;
    }

    public void setGatAtencionesList(List<GatAtenciones> gatAtencionesList) {
        this.gatAtencionesList = gatAtencionesList;
    }

    @XmlTransient
    public List<GatTiquetes> getGatTiquetesList() {
        return gatTiquetesList;
    }

    public void setGatTiquetesList(List<GatTiquetes> gatTiquetesList) {
        this.gatTiquetesList = gatTiquetesList;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
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
        if (!(object instanceof GatUsuarios)) {
            return false;
        }
        GatUsuarios other = (GatUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatUsuarios[ id=" + id + " ]";
    }
    
}
