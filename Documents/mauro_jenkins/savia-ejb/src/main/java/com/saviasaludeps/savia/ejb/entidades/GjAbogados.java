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
@Table(name = "gj_abogados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GjAbogados.findAll", query = "SELECT g FROM GjAbogados g"),
    @NamedQuery(name = "GjAbogados.findById", query = "SELECT g FROM GjAbogados g WHERE g.id = :id"),
    @NamedQuery(name = "GjAbogados.findByMaeTipoDocumentoId", query = "SELECT g FROM GjAbogados g WHERE g.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "GjAbogados.findByMaeTipoDocumentoCodigo", query = "SELECT g FROM GjAbogados g WHERE g.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "GjAbogados.findByMaeTipoDocumentoValor", query = "SELECT g FROM GjAbogados g WHERE g.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "GjAbogados.findByDocumento", query = "SELECT g FROM GjAbogados g WHERE g.documento = :documento"),
    @NamedQuery(name = "GjAbogados.findByTarjetaProfecional", query = "SELECT g FROM GjAbogados g WHERE g.tarjetaProfecional = :tarjetaProfecional"),
    @NamedQuery(name = "GjAbogados.findByNombre", query = "SELECT g FROM GjAbogados g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GjAbogados.findByTipo", query = "SELECT g FROM GjAbogados g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "GjAbogados.findByUsuarioCrea", query = "SELECT g FROM GjAbogados g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GjAbogados.findByTerminalCrea", query = "SELECT g FROM GjAbogados g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GjAbogados.findByFechaHoraCrea", query = "SELECT g FROM GjAbogados g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GjAbogados.findByUsuarioModifica", query = "SELECT g FROM GjAbogados g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GjAbogados.findByTerminalModifica", query = "SELECT g FROM GjAbogados g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GjAbogados.findByFechaHoraModifica", query = "SELECT g FROM GjAbogados g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GjAbogados implements Serializable {

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
    @Size(min = 1, max = 16)
    @Column(name = "tarjeta_profecional")
    private String tarjetaProfecional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private short tipo;
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
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gjAbogadosId", fetch = FetchType.LAZY)
    private List<GjProcesoAbogados> gjProcesoAbogadosList;

    public GjAbogados() {
    }

    public GjAbogados(Integer id) {
        this.id = id;
    }

    public GjAbogados(Integer id, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String documento, String tarjetaProfecional, String nombre, short tipo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.documento = documento;
        this.tarjetaProfecional = tarjetaProfecional;
        this.nombre = nombre;
        this.tipo = tipo;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTarjetaProfecional() {
        return tarjetaProfecional;
    }

    public void setTarjetaProfecional(String tarjetaProfecional) {
        this.tarjetaProfecional = tarjetaProfecional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
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

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    @XmlTransient
    public List<GjProcesoAbogados> getGjProcesoAbogadosList() {
        return gjProcesoAbogadosList;
    }

    public void setGjProcesoAbogadosList(List<GjProcesoAbogados> gjProcesoAbogadosList) {
        this.gjProcesoAbogadosList = gjProcesoAbogadosList;
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
        if (!(object instanceof GjAbogados)) {
            return false;
        }
        GjAbogados other = (GjAbogados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GjAbogados[ id=" + id + " ]";
    }
    
}
