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
@Table(name = "au_anexo2_rescates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo2Rescates.findAll", query = "SELECT a FROM AuAnexo2Rescates a"),
    @NamedQuery(name = "AuAnexo2Rescates.findById", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo2Rescates.findByFuenteOrigen", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "AuAnexo2Rescates.findByMaeAfiliadoTipoDocumentoId", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.maeAfiliadoTipoDocumentoId = :maeAfiliadoTipoDocumentoId"),
    @NamedQuery(name = "AuAnexo2Rescates.findByMaeAfiliadoTipoDocumentoCodigo", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.maeAfiliadoTipoDocumentoCodigo = :maeAfiliadoTipoDocumentoCodigo"),
    @NamedQuery(name = "AuAnexo2Rescates.findByMaeAfiliadoTipoDocumentoValor", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.maeAfiliadoTipoDocumentoValor = :maeAfiliadoTipoDocumentoValor"),
    @NamedQuery(name = "AuAnexo2Rescates.findByAfiliadoNumeroDocumento", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.afiliadoNumeroDocumento = :afiliadoNumeroDocumento"),
    @NamedQuery(name = "AuAnexo2Rescates.findByAfiliadoPrimerNombre", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.afiliadoPrimerNombre = :afiliadoPrimerNombre"),
    @NamedQuery(name = "AuAnexo2Rescates.findByAfiliadoSegundoNombre", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.afiliadoSegundoNombre = :afiliadoSegundoNombre"),
    @NamedQuery(name = "AuAnexo2Rescates.findByAfiliadoPrimerApellido", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.afiliadoPrimerApellido = :afiliadoPrimerApellido"),
    @NamedQuery(name = "AuAnexo2Rescates.findByAfiliadoSegundoApellido", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.afiliadoSegundoApellido = :afiliadoSegundoApellido"),
    @NamedQuery(name = "AuAnexo2Rescates.findByMotivoConsulta", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.motivoConsulta = :motivoConsulta"),
    @NamedQuery(name = "AuAnexo2Rescates.findByEstado", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexo2Rescates.findByDescripcion", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AuAnexo2Rescates.findByFechaHoraRescate", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.fechaHoraRescate = :fechaHoraRescate"),
    @NamedQuery(name = "AuAnexo2Rescates.findByTipoRescate", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.tipoRescate = :tipoRescate"),
    @NamedQuery(name = "AuAnexo2Rescates.findByUsuarioCrea", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo2Rescates.findByTerminalCrea", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo2Rescates.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexo2Rescates.findByUsuarioModifica", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexo2Rescates.findByTerminalModifica", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuAnexo2Rescates.findByFechaHoraModifica", query = "SELECT a FROM AuAnexo2Rescates a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuAnexo2Rescates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fuente_origen")
    private short fuenteOrigen;
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
    @Size(max = 2048)
    @Column(name = "motivo_consulta")
    private String motivoConsulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Size(max = 2048)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_hora_rescate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRescate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_rescate")
    private int tipoRescate;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexo2RescatesId", fetch = FetchType.LAZY)
    private List<AuAnexo2RescateGestiones> auAnexo2RescateGestionesList;
    @OneToMany(mappedBy = "auAnexo2RescatesId", fetch = FetchType.LAZY)
    private List<AuSolicitudAdjuntos> auSolicitudAdjuntosList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "cnt_prestador_sedes_origen_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesOrigenId;
    @JoinColumn(name = "cnt_prestador_sedes_destino_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesDestinoId;
    @JoinColumn(name = "cnt_prestadores_origen_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresOrigenId;
    @JoinColumn(name = "cnt_prestadores_destino_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresDestinoId;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;
    @JoinColumn(name = "auc_hospitalizaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AucHospitalizaciones aucHospitalizacionesId;
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;
    @JoinColumn(name = "au_anexos2_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos2 auAnexos2Id;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;

    public AuAnexo2Rescates() {
    }

    public AuAnexo2Rescates(Integer id) {
        this.id = id;
    }

    public AuAnexo2Rescates(Integer id, short fuenteOrigen, int maeAfiliadoTipoDocumentoId, String maeAfiliadoTipoDocumentoCodigo, String maeAfiliadoTipoDocumentoValor, String afiliadoNumeroDocumento, String afiliadoPrimerNombre, String afiliadoPrimerApellido, int estado, int tipoRescate, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fuenteOrigen = fuenteOrigen;
        this.maeAfiliadoTipoDocumentoId = maeAfiliadoTipoDocumentoId;
        this.maeAfiliadoTipoDocumentoCodigo = maeAfiliadoTipoDocumentoCodigo;
        this.maeAfiliadoTipoDocumentoValor = maeAfiliadoTipoDocumentoValor;
        this.afiliadoNumeroDocumento = afiliadoNumeroDocumento;
        this.afiliadoPrimerNombre = afiliadoPrimerNombre;
        this.afiliadoPrimerApellido = afiliadoPrimerApellido;
        this.estado = estado;
        this.tipoRescate = tipoRescate;
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

    public short getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(short fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
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

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaHoraRescate() {
        return fechaHoraRescate;
    }

    public void setFechaHoraRescate(Date fechaHoraRescate) {
        this.fechaHoraRescate = fechaHoraRescate;
    }

    public int getTipoRescate() {
        return tipoRescate;
    }

    public void setTipoRescate(int tipoRescate) {
        this.tipoRescate = tipoRescate;
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
    public List<AuAnexo2RescateGestiones> getAuAnexo2RescateGestionesList() {
        return auAnexo2RescateGestionesList;
    }

    public void setAuAnexo2RescateGestionesList(List<AuAnexo2RescateGestiones> auAnexo2RescateGestionesList) {
        this.auAnexo2RescateGestionesList = auAnexo2RescateGestionesList;
    }

    @XmlTransient
    public List<AuSolicitudAdjuntos> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjuntos> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public CntPrestadorSedes getCntPrestadorSedesOrigenId() {
        return cntPrestadorSedesOrigenId;
    }

    public void setCntPrestadorSedesOrigenId(CntPrestadorSedes cntPrestadorSedesOrigenId) {
        this.cntPrestadorSedesOrigenId = cntPrestadorSedesOrigenId;
    }

    public CntPrestadorSedes getCntPrestadorSedesDestinoId() {
        return cntPrestadorSedesDestinoId;
    }

    public void setCntPrestadorSedesDestinoId(CntPrestadorSedes cntPrestadorSedesDestinoId) {
        this.cntPrestadorSedesDestinoId = cntPrestadorSedesDestinoId;
    }

    public CntPrestadores getCntPrestadoresOrigenId() {
        return cntPrestadoresOrigenId;
    }

    public void setCntPrestadoresOrigenId(CntPrestadores cntPrestadoresOrigenId) {
        this.cntPrestadoresOrigenId = cntPrestadoresOrigenId;
    }

    public CntPrestadores getCntPrestadoresDestinoId() {
        return cntPrestadoresDestinoId;
    }

    public void setCntPrestadoresDestinoId(CntPrestadores cntPrestadoresDestinoId) {
        this.cntPrestadoresDestinoId = cntPrestadoresDestinoId;
    }

    public AuAnexos3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexos3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public AucHospitalizaciones getAucHospitalizacionesId() {
        return aucHospitalizacionesId;
    }

    public void setAucHospitalizacionesId(AucHospitalizaciones aucHospitalizacionesId) {
        this.aucHospitalizacionesId = aucHospitalizacionesId;
    }

    public PeProgramas getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PeProgramas peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    public AuAnexos2 getAuAnexos2Id() {
        return auAnexos2Id;
    }

    public void setAuAnexos2Id(AuAnexos2 auAnexos2Id) {
        this.auAnexos2Id = auAnexos2Id;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
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
        if (!(object instanceof AuAnexo2Rescates)) {
            return false;
        }
        AuAnexo2Rescates other = (AuAnexo2Rescates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo2Rescates[ id=" + id + " ]";
    }
    
}
