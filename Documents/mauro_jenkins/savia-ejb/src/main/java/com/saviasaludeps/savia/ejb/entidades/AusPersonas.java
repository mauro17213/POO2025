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
@Table(name = "aus_personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusPersonas.findAll", query = "SELECT a FROM AusPersonas a"),
    @NamedQuery(name = "AusPersonas.findById", query = "SELECT a FROM AusPersonas a WHERE a.id = :id"),
    @NamedQuery(name = "AusPersonas.findByMaeEstadoId", query = "SELECT a FROM AusPersonas a WHERE a.maeEstadoId = :maeEstadoId"),
    @NamedQuery(name = "AusPersonas.findByMaeEstadoCodigo", query = "SELECT a FROM AusPersonas a WHERE a.maeEstadoCodigo = :maeEstadoCodigo"),
    @NamedQuery(name = "AusPersonas.findByMaeEstadoValor", query = "SELECT a FROM AusPersonas a WHERE a.maeEstadoValor = :maeEstadoValor"),
    @NamedQuery(name = "AusPersonas.findByMaeTipoParentescoId", query = "SELECT a FROM AusPersonas a WHERE a.maeTipoParentescoId = :maeTipoParentescoId"),
    @NamedQuery(name = "AusPersonas.findByMaeTipoParentescoCodigo", query = "SELECT a FROM AusPersonas a WHERE a.maeTipoParentescoCodigo = :maeTipoParentescoCodigo"),
    @NamedQuery(name = "AusPersonas.findByMaeTipoParentescoValor", query = "SELECT a FROM AusPersonas a WHERE a.maeTipoParentescoValor = :maeTipoParentescoValor"),
    @NamedQuery(name = "AusPersonas.findByMaeTipoDocumentoId", query = "SELECT a FROM AusPersonas a WHERE a.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "AusPersonas.findByMaeTipoDocumentoCodigo", query = "SELECT a FROM AusPersonas a WHERE a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "AusPersonas.findByMaeTipoDocumentoValor", query = "SELECT a FROM AusPersonas a WHERE a.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "AusPersonas.findByDocumento", query = "SELECT a FROM AusPersonas a WHERE a.documento = :documento"),
    @NamedQuery(name = "AusPersonas.findByNombres", query = "SELECT a FROM AusPersonas a WHERE a.nombres = :nombres"),
    @NamedQuery(name = "AusPersonas.findByApellidos", query = "SELECT a FROM AusPersonas a WHERE a.apellidos = :apellidos"),
    @NamedQuery(name = "AusPersonas.findByFechaNacimento", query = "SELECT a FROM AusPersonas a WHERE a.fechaNacimento = :fechaNacimento"),
    @NamedQuery(name = "AusPersonas.findByMaeSexoId", query = "SELECT a FROM AusPersonas a WHERE a.maeSexoId = :maeSexoId"),
    @NamedQuery(name = "AusPersonas.findByMaeSexoCodigo", query = "SELECT a FROM AusPersonas a WHERE a.maeSexoCodigo = :maeSexoCodigo"),
    @NamedQuery(name = "AusPersonas.findByMaeSexoValor", query = "SELECT a FROM AusPersonas a WHERE a.maeSexoValor = :maeSexoValor"),
    @NamedQuery(name = "AusPersonas.findByCorreoElectronico", query = "SELECT a FROM AusPersonas a WHERE a.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "AusPersonas.findByDireccion", query = "SELECT a FROM AusPersonas a WHERE a.direccion = :direccion"),
    @NamedQuery(name = "AusPersonas.findByEstratro", query = "SELECT a FROM AusPersonas a WHERE a.estratro = :estratro"),
    @NamedQuery(name = "AusPersonas.findByContrato", query = "SELECT a FROM AusPersonas a WHERE a.contrato = :contrato"),
    @NamedQuery(name = "AusPersonas.findByRegimen", query = "SELECT a FROM AusPersonas a WHERE a.regimen = :regimen"),
    @NamedQuery(name = "AusPersonas.findByDicapacidad", query = "SELECT a FROM AusPersonas a WHERE a.dicapacidad = :dicapacidad"),
    @NamedQuery(name = "AusPersonas.findByGestante", query = "SELECT a FROM AusPersonas a WHERE a.gestante = :gestante"),
    @NamedQuery(name = "AusPersonas.findByUsuarioCrea", query = "SELECT a FROM AusPersonas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusPersonas.findByTerminalCrea", query = "SELECT a FROM AusPersonas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusPersonas.findByFechaHoraCrea", query = "SELECT a FROM AusPersonas a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AusPersonas.findByUsuarioModifica", query = "SELECT a FROM AusPersonas a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AusPersonas.findByTerminalModifica", query = "SELECT a FROM AusPersonas a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AusPersonas.findByFechaHoraModifica", query = "SELECT a FROM AusPersonas a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AusPersonas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_estado_id")
    private Integer maeEstadoId;
    @Size(max = 32)
    @Column(name = "mae_estado_codigo")
    private String maeEstadoCodigo;
    @Size(max = 512)
    @Column(name = "mae_estado_valor")
    private String maeEstadoValor;
    @Column(name = "mae_tipo_parentesco_id")
    private Integer maeTipoParentescoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_parentesco_codigo")
    private String maeTipoParentescoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_parentesco_valor")
    private String maeTipoParentescoValor;
    @Column(name = "mae_tipo_documento_id")
    private Integer maeTipoDocumentoId;
    @Size(max = 32)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Size(max = 512)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
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
    @Column(name = "fecha_nacimento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimento;
    @Column(name = "mae_sexo_id")
    private Integer maeSexoId;
    @Size(max = 32)
    @Column(name = "mae_sexo_codigo")
    private String maeSexoCodigo;
    @Size(max = 512)
    @Column(name = "mae_sexo_valor")
    private String maeSexoValor;
    @Size(max = 128)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Size(max = 256)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "estratro")
    private Integer estratro;
    @Size(max = 32)
    @Column(name = "contrato")
    private String contrato;
    @Column(name = "regimen")
    private Boolean regimen;
    @Column(name = "dicapacidad")
    private Boolean dicapacidad;
    @Column(name = "gestante")
    private Boolean gestante;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "gn_ubicaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUbicaciones gnUbicacionesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ausPersonasId", fetch = FetchType.LAZY)
    private List<AusPersonaTelefonos> ausPersonaTelefonosList;
    @OneToMany(mappedBy = "ausPersonasId", fetch = FetchType.LAZY)
    private List<AusCasos> ausCasosList;
    @OneToMany(mappedBy = "ausAccionantePersonasId", fetch = FetchType.LAZY)
    private List<AusCasos> ausCasosList1;

    public AusPersonas() {
    }

    public AusPersonas(Integer id) {
        this.id = id;
    }

    public AusPersonas(Integer id, String documento, String nombres, String apellidos, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
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

    public Integer getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(Integer maeEstadoId) {
        this.maeEstadoId = maeEstadoId;
    }

    public String getMaeEstadoCodigo() {
        return maeEstadoCodigo;
    }

    public void setMaeEstadoCodigo(String maeEstadoCodigo) {
        this.maeEstadoCodigo = maeEstadoCodigo;
    }

    public String getMaeEstadoValor() {
        return maeEstadoValor;
    }

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
    }

    public Integer getMaeTipoParentescoId() {
        return maeTipoParentescoId;
    }

    public void setMaeTipoParentescoId(Integer maeTipoParentescoId) {
        this.maeTipoParentescoId = maeTipoParentescoId;
    }

    public String getMaeTipoParentescoCodigo() {
        return maeTipoParentescoCodigo;
    }

    public void setMaeTipoParentescoCodigo(String maeTipoParentescoCodigo) {
        this.maeTipoParentescoCodigo = maeTipoParentescoCodigo;
    }

    public String getMaeTipoParentescoValor() {
        return maeTipoParentescoValor;
    }

    public void setMaeTipoParentescoValor(String maeTipoParentescoValor) {
        this.maeTipoParentescoValor = maeTipoParentescoValor;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
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

    public Date getFechaNacimento() {
        return fechaNacimento;
    }

    public void setFechaNacimento(Date fechaNacimento) {
        this.fechaNacimento = fechaNacimento;
    }

    public Integer getMaeSexoId() {
        return maeSexoId;
    }

    public void setMaeSexoId(Integer maeSexoId) {
        this.maeSexoId = maeSexoId;
    }

    public String getMaeSexoCodigo() {
        return maeSexoCodigo;
    }

    public void setMaeSexoCodigo(String maeSexoCodigo) {
        this.maeSexoCodigo = maeSexoCodigo;
    }

    public String getMaeSexoValor() {
        return maeSexoValor;
    }

    public void setMaeSexoValor(String maeSexoValor) {
        this.maeSexoValor = maeSexoValor;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEstratro() {
        return estratro;
    }

    public void setEstratro(Integer estratro) {
        this.estratro = estratro;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Boolean getRegimen() {
        return regimen;
    }

    public void setRegimen(Boolean regimen) {
        this.regimen = regimen;
    }

    public Boolean getDicapacidad() {
        return dicapacidad;
    }

    public void setDicapacidad(Boolean dicapacidad) {
        this.dicapacidad = dicapacidad;
    }

    public Boolean getGestante() {
        return gestante;
    }

    public void setGestante(Boolean gestante) {
        this.gestante = gestante;
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

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public GnUbicaciones getGnUbicacionesId() {
        return gnUbicacionesId;
    }

    public void setGnUbicacionesId(GnUbicaciones gnUbicacionesId) {
        this.gnUbicacionesId = gnUbicacionesId;
    }

    @XmlTransient
    public List<AusPersonaTelefonos> getAusPersonaTelefonosList() {
        return ausPersonaTelefonosList;
    }

    public void setAusPersonaTelefonosList(List<AusPersonaTelefonos> ausPersonaTelefonosList) {
        this.ausPersonaTelefonosList = ausPersonaTelefonosList;
    }

    @XmlTransient
    public List<AusCasos> getAusCasosList() {
        return ausCasosList;
    }

    public void setAusCasosList(List<AusCasos> ausCasosList) {
        this.ausCasosList = ausCasosList;
    }

    @XmlTransient
    public List<AusCasos> getAusCasosList1() {
        return ausCasosList1;
    }

    public void setAusCasosList1(List<AusCasos> ausCasosList1) {
        this.ausCasosList1 = ausCasosList1;
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
        if (!(object instanceof AusPersonas)) {
            return false;
        }
        AusPersonas other = (AusPersonas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusPersonas[ id=" + id + " ]";
    }
    
}
