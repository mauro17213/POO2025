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
@Table(name = "pe_direccionados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeDireccionados.findAll", query = "SELECT p FROM PeDireccionados p"),
    @NamedQuery(name = "PeDireccionados.findById", query = "SELECT p FROM PeDireccionados p WHERE p.id = :id"),
    @NamedQuery(name = "PeDireccionados.findByMaeAfiliadoTipoDocumentoId", query = "SELECT p FROM PeDireccionados p WHERE p.maeAfiliadoTipoDocumentoId = :maeAfiliadoTipoDocumentoId"),
    @NamedQuery(name = "PeDireccionados.findByMaeAfiliadoTipoDocumentoCodigo", query = "SELECT p FROM PeDireccionados p WHERE p.maeAfiliadoTipoDocumentoCodigo = :maeAfiliadoTipoDocumentoCodigo"),
    @NamedQuery(name = "PeDireccionados.findByMaeAfiliadoTipoDocumentoValor", query = "SELECT p FROM PeDireccionados p WHERE p.maeAfiliadoTipoDocumentoValor = :maeAfiliadoTipoDocumentoValor"),
    @NamedQuery(name = "PeDireccionados.findByAfiliadoNumeroDocumento", query = "SELECT p FROM PeDireccionados p WHERE p.afiliadoNumeroDocumento = :afiliadoNumeroDocumento"),
    @NamedQuery(name = "PeDireccionados.findByAfiliadoPrimerNombre", query = "SELECT p FROM PeDireccionados p WHERE p.afiliadoPrimerNombre = :afiliadoPrimerNombre"),
    @NamedQuery(name = "PeDireccionados.findByAfiliadoSegundoNombre", query = "SELECT p FROM PeDireccionados p WHERE p.afiliadoSegundoNombre = :afiliadoSegundoNombre"),
    @NamedQuery(name = "PeDireccionados.findByAfiliadoPrimerApellido", query = "SELECT p FROM PeDireccionados p WHERE p.afiliadoPrimerApellido = :afiliadoPrimerApellido"),
    @NamedQuery(name = "PeDireccionados.findByAfiliadoSegundoApellido", query = "SELECT p FROM PeDireccionados p WHERE p.afiliadoSegundoApellido = :afiliadoSegundoApellido"),
    @NamedQuery(name = "PeDireccionados.findByTieneTutela", query = "SELECT p FROM PeDireccionados p WHERE p.tieneTutela = :tieneTutela"),
    @NamedQuery(name = "PeDireccionados.findByEstado", query = "SELECT p FROM PeDireccionados p WHERE p.estado = :estado"),
    @NamedQuery(name = "PeDireccionados.findByObservacion", query = "SELECT p FROM PeDireccionados p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "PeDireccionados.findByFechaHoraEnGestion", query = "SELECT p FROM PeDireccionados p WHERE p.fechaHoraEnGestion = :fechaHoraEnGestion"),
    @NamedQuery(name = "PeDireccionados.findByFechaHoraGestiona", query = "SELECT p FROM PeDireccionados p WHERE p.fechaHoraGestiona = :fechaHoraGestiona"),
    @NamedQuery(name = "PeDireccionados.findByUsuarioCrea", query = "SELECT p FROM PeDireccionados p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeDireccionados.findByTerminalCrea", query = "SELECT p FROM PeDireccionados p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeDireccionados.findByFechaHoraCrea", query = "SELECT p FROM PeDireccionados p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeDireccionados.findByUsuarioModifica", query = "SELECT p FROM PeDireccionados p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeDireccionados.findByTerminalModifica", query = "SELECT p FROM PeDireccionados p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeDireccionados.findByFechaHoraModifica", query = "SELECT p FROM PeDireccionados p WHERE p.fechaHoraModifica = :fechaHoraModifica")})
public class PeDireccionados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_afiliado_tipo_documento_id")
    private int maeAfiliadoTipoDocumentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_afiliado_tipo_documento_codigo")
    private String maeAfiliadoTipoDocumentoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_afiliado_tipo_documento_valor")
    private String maeAfiliadoTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "afiliado_numero_documento")
    private String afiliadoNumeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "afiliado_primer_nombre")
    private String afiliadoPrimerNombre;
    @Size(max = 32)
    @Column(name = "afiliado_segundo_nombre")
    private String afiliadoSegundoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "afiliado_primer_apellido")
    private String afiliadoPrimerApellido;
    @Size(max = 32)
    @Column(name = "afiliado_segundo_apellido")
    private String afiliadoSegundoApellido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiene_tutela")
    private boolean tieneTutela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Size(max = 1024)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "fecha_hora_en_gestion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEnGestion;
    @Column(name = "fecha_hora_gestiona")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraGestiona;
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peDireccionadosId", fetch = FetchType.LAZY)
    private List<PeDireccionadoItems> peDireccionadoItemsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peDireccionadosId", fetch = FetchType.LAZY)
    private List<PeDireccionadoGestiones> peDireccionadoGestionesList;

    public PeDireccionados() {
    }

    public PeDireccionados(Integer id) {
        this.id = id;
    }

    public PeDireccionados(Integer id, int maeAfiliadoTipoDocumentoId, String maeAfiliadoTipoDocumentoCodigo, String maeAfiliadoTipoDocumentoValor, String afiliadoNumeroDocumento, String afiliadoPrimerNombre, String afiliadoPrimerApellido, boolean tieneTutela, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeAfiliadoTipoDocumentoId = maeAfiliadoTipoDocumentoId;
        this.maeAfiliadoTipoDocumentoCodigo = maeAfiliadoTipoDocumentoCodigo;
        this.maeAfiliadoTipoDocumentoValor = maeAfiliadoTipoDocumentoValor;
        this.afiliadoNumeroDocumento = afiliadoNumeroDocumento;
        this.afiliadoPrimerNombre = afiliadoPrimerNombre;
        this.afiliadoPrimerApellido = afiliadoPrimerApellido;
        this.tieneTutela = tieneTutela;
        this.estado = estado;
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

    public int getMaeAfiliadoTipoDocumentoId() {
        return maeAfiliadoTipoDocumentoId;
    }

    public void setMaeAfiliadoTipoDocumentoId(int maeAfiliadoTipoDocumentoId) {
        this.maeAfiliadoTipoDocumentoId = maeAfiliadoTipoDocumentoId;
    }

    public String getMaeAfiliadoTipoDocumentoCodigo() {
        return maeAfiliadoTipoDocumentoCodigo;
    }

    public void setMaeAfiliadoTipoDocumentoCodigo(String maeAfiliadoTipoDocumentoCodigo) {
        this.maeAfiliadoTipoDocumentoCodigo = maeAfiliadoTipoDocumentoCodigo;
    }

    public String getMaeAfiliadoTipoDocumentoValor() {
        return maeAfiliadoTipoDocumentoValor;
    }

    public void setMaeAfiliadoTipoDocumentoValor(String maeAfiliadoTipoDocumentoValor) {
        this.maeAfiliadoTipoDocumentoValor = maeAfiliadoTipoDocumentoValor;
    }

    public String getAfiliadoNumeroDocumento() {
        return afiliadoNumeroDocumento;
    }

    public void setAfiliadoNumeroDocumento(String afiliadoNumeroDocumento) {
        this.afiliadoNumeroDocumento = afiliadoNumeroDocumento;
    }

    public String getAfiliadoPrimerNombre() {
        return afiliadoPrimerNombre;
    }

    public void setAfiliadoPrimerNombre(String afiliadoPrimerNombre) {
        this.afiliadoPrimerNombre = afiliadoPrimerNombre;
    }

    public String getAfiliadoSegundoNombre() {
        return afiliadoSegundoNombre;
    }

    public void setAfiliadoSegundoNombre(String afiliadoSegundoNombre) {
        this.afiliadoSegundoNombre = afiliadoSegundoNombre;
    }

    public String getAfiliadoPrimerApellido() {
        return afiliadoPrimerApellido;
    }

    public void setAfiliadoPrimerApellido(String afiliadoPrimerApellido) {
        this.afiliadoPrimerApellido = afiliadoPrimerApellido;
    }

    public String getAfiliadoSegundoApellido() {
        return afiliadoSegundoApellido;
    }

    public void setAfiliadoSegundoApellido(String afiliadoSegundoApellido) {
        this.afiliadoSegundoApellido = afiliadoSegundoApellido;
    }

    public boolean getTieneTutela() {
        return tieneTutela;
    }

    public void setTieneTutela(boolean tieneTutela) {
        this.tieneTutela = tieneTutela;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaHoraEnGestion() {
        return fechaHoraEnGestion;
    }

    public void setFechaHoraEnGestion(Date fechaHoraEnGestion) {
        this.fechaHoraEnGestion = fechaHoraEnGestion;
    }

    public Date getFechaHoraGestiona() {
        return fechaHoraGestiona;
    }

    public void setFechaHoraGestiona(Date fechaHoraGestiona) {
        this.fechaHoraGestiona = fechaHoraGestiona;
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

    public AuAnexos3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexos3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public PeProgramas getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PeProgramas peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    @XmlTransient
    public List<PeDireccionadoItems> getPeDireccionadoItemsList() {
        return peDireccionadoItemsList;
    }

    public void setPeDireccionadoItemsList(List<PeDireccionadoItems> peDireccionadoItemsList) {
        this.peDireccionadoItemsList = peDireccionadoItemsList;
    }

    @XmlTransient
    public List<PeDireccionadoGestiones> getPeDireccionadoGestionesList() {
        return peDireccionadoGestionesList;
    }

    public void setPeDireccionadoGestionesList(List<PeDireccionadoGestiones> peDireccionadoGestionesList) {
        this.peDireccionadoGestionesList = peDireccionadoGestionesList;
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
        if (!(object instanceof PeDireccionados)) {
            return false;
        }
        PeDireccionados other = (PeDireccionados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeDireccionados[ id=" + id + " ]";
    }
    
}
