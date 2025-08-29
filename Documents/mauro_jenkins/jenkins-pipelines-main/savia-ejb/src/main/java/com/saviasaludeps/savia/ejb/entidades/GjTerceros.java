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
@Table(name = "gj_terceros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GjTerceros.findAll", query = "SELECT g FROM GjTerceros g"),
    @NamedQuery(name = "GjTerceros.findById", query = "SELECT g FROM GjTerceros g WHERE g.id = :id"),
    @NamedQuery(name = "GjTerceros.findByTipo", query = "SELECT g FROM GjTerceros g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "GjTerceros.findByMaeTipoDocumentoId", query = "SELECT g FROM GjTerceros g WHERE g.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "GjTerceros.findByMaeTipoDocumentoCodigo", query = "SELECT g FROM GjTerceros g WHERE g.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "GjTerceros.findByMaeTipoDocumentoValor", query = "SELECT g FROM GjTerceros g WHERE g.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "GjTerceros.findByDocumento", query = "SELECT g FROM GjTerceros g WHERE g.documento = :documento"),
    @NamedQuery(name = "GjTerceros.findByNombres", query = "SELECT g FROM GjTerceros g WHERE g.nombres = :nombres"),
    @NamedQuery(name = "GjTerceros.findByApellidos", query = "SELECT g FROM GjTerceros g WHERE g.apellidos = :apellidos"),
    @NamedQuery(name = "GjTerceros.findByRazonSocial", query = "SELECT g FROM GjTerceros g WHERE g.razonSocial = :razonSocial"),
    @NamedQuery(name = "GjTerceros.findByTelefono", query = "SELECT g FROM GjTerceros g WHERE g.telefono = :telefono"),
    @NamedQuery(name = "GjTerceros.findByGnUbicacionesId", query = "SELECT g FROM GjTerceros g WHERE g.gnUbicacionesId = :gnUbicacionesId"),
    @NamedQuery(name = "GjTerceros.findByDireccion", query = "SELECT g FROM GjTerceros g WHERE g.direccion = :direccion"),
    @NamedQuery(name = "GjTerceros.findByCorreoElectronico", query = "SELECT g FROM GjTerceros g WHERE g.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "GjTerceros.findByUsuarioCrea", query = "SELECT g FROM GjTerceros g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GjTerceros.findByTerminalCrea", query = "SELECT g FROM GjTerceros g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GjTerceros.findByFechaHoraCrea", query = "SELECT g FROM GjTerceros g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GjTerceros.findByUsuarioModifica", query = "SELECT g FROM GjTerceros g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GjTerceros.findByTerminalModifica", query = "SELECT g FROM GjTerceros g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GjTerceros.findByFechaHoraModifica", query = "SELECT g FROM GjTerceros g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GjTerceros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private short tipo;
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
    @Size(max = 128)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 128)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 256)
    @Column(name = "razon_social")
    private String razonSocial;
    @Size(max = 32)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gn_ubicaciones_id")
    private int gnUbicacionesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "correo_electronico")
    private String correoElectronico;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gjTercerosId", fetch = FetchType.LAZY)
    private List<GjTerceroContactos> gjTerceroContactosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gjTercerosId", fetch = FetchType.LAZY)
    private List<GjProcesoTerceros> gjProcesoTercerosList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;

    public GjTerceros() {
    }

    public GjTerceros(Integer id) {
        this.id = id;
    }

    public GjTerceros(Integer id, short tipo, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String documento, int gnUbicacionesId, String direccion, String correoElectronico, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.documento = documento;
        this.gnUbicacionesId = gnUbicacionesId;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
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

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getGnUbicacionesId() {
        return gnUbicacionesId;
    }

    public void setGnUbicacionesId(int gnUbicacionesId) {
        this.gnUbicacionesId = gnUbicacionesId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
    public List<GjTerceroContactos> getGjTerceroContactosList() {
        return gjTerceroContactosList;
    }

    public void setGjTerceroContactosList(List<GjTerceroContactos> gjTerceroContactosList) {
        this.gjTerceroContactosList = gjTerceroContactosList;
    }

    @XmlTransient
    public List<GjProcesoTerceros> getGjProcesoTercerosList() {
        return gjProcesoTercerosList;
    }

    public void setGjProcesoTercerosList(List<GjProcesoTerceros> gjProcesoTercerosList) {
        this.gjProcesoTercerosList = gjProcesoTercerosList;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
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
        if (!(object instanceof GjTerceros)) {
            return false;
        }
        GjTerceros other = (GjTerceros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GjTerceros[ id=" + id + " ]";
    }
    
}
