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
@Table(name = "au_anexo3_afiliados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo3Afiliados.findAll", query = "SELECT a FROM AuAnexo3Afiliados a"),
    @NamedQuery(name = "AuAnexo3Afiliados.findById", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByMaeTipoDocumentoId", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByMaeTipoDocumentoCodigo", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByMaeTipoDocumentoValor", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByNumeroIdentificacion", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.numeroIdentificacion = :numeroIdentificacion"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByPrimerNombre", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.primerNombre = :primerNombre"),
    @NamedQuery(name = "AuAnexo3Afiliados.findBySegundoNombre", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByPrimerApellido", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.primerApellido = :primerApellido"),
    @NamedQuery(name = "AuAnexo3Afiliados.findBySegundoApellido", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByFechaNacimiento", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByDireccionAfiliado", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.direccionAfiliado = :direccionAfiliado"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByTelefonoAfiliado", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.telefonoAfiliado = :telefonoAfiliado"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByCelularAfiliado", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.celularAfiliado = :celularAfiliado"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByCorreoElectronico", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByMaeRegimenId", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByMaeRegimenCodigo", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByMaeRegimenValor", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByIncapacidadProlongada", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.incapacidadProlongada = :incapacidadProlongada"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByUsuarioCrea", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByTerminalCrea", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByUsuarioModifica", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByTerminalModifica", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuAnexo3Afiliados.findByFechaHoraModifica", query = "SELECT a FROM AuAnexo3Afiliados a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuAnexo3Afiliados implements Serializable {

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
    @Size(min = 1, max = 32)
    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;
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
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "direccion_afiliado")
    private String direccionAfiliado;
    @Size(max = 32)
    @Column(name = "telefono_afiliado")
    private String telefonoAfiliado;
    @Size(max = 32)
    @Column(name = "celular_afiliado")
    private String celularAfiliado;
    @Size(max = 512)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "mae_regimen_id")
    private Integer maeRegimenId;
    @Size(max = 8)
    @Column(name = "mae_regimen_codigo")
    private String maeRegimenCodigo;
    @Size(max = 128)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Column(name = "incapacidad_prolongada")
    private Boolean incapacidadProlongada;
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
    @JoinColumn(name = "au_anexo3_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos3 auAnexo3Id;

    public AuAnexo3Afiliados() {
    }

    public AuAnexo3Afiliados(Integer id) {
        this.id = id;
    }

    public AuAnexo3Afiliados(Integer id, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String numeroIdentificacion, String primerNombre, String primerApellido, Date fechaNacimiento, String direccionAfiliado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroIdentificacion = numeroIdentificacion;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.direccionAfiliado = direccionAfiliado;
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

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccionAfiliado() {
        return direccionAfiliado;
    }

    public void setDireccionAfiliado(String direccionAfiliado) {
        this.direccionAfiliado = direccionAfiliado;
    }

    public String getTelefonoAfiliado() {
        return telefonoAfiliado;
    }

    public void setTelefonoAfiliado(String telefonoAfiliado) {
        this.telefonoAfiliado = telefonoAfiliado;
    }

    public String getCelularAfiliado() {
        return celularAfiliado;
    }

    public void setCelularAfiliado(String celularAfiliado) {
        this.celularAfiliado = celularAfiliado;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Integer getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(Integer maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public Boolean getIncapacidadProlongada() {
        return incapacidadProlongada;
    }

    public void setIncapacidadProlongada(Boolean incapacidadProlongada) {
        this.incapacidadProlongada = incapacidadProlongada;
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

    public AuAnexos3 getAuAnexo3Id() {
        return auAnexo3Id;
    }

    public void setAuAnexo3Id(AuAnexos3 auAnexo3Id) {
        this.auAnexo3Id = auAnexo3Id;
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
        if (!(object instanceof AuAnexo3Afiliados)) {
            return false;
        }
        AuAnexo3Afiliados other = (AuAnexo3Afiliados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo3Afiliados[ id=" + id + " ]";
    }
    
}
