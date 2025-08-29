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
@Table(name = "cntj_terceros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjTerceros.findAll", query = "SELECT c FROM CntjTerceros c"),
    @NamedQuery(name = "CntjTerceros.findById", query = "SELECT c FROM CntjTerceros c WHERE c.id = :id"),
    @NamedQuery(name = "CntjTerceros.findByTipoTercero", query = "SELECT c FROM CntjTerceros c WHERE c.tipoTercero = :tipoTercero"),
    @NamedQuery(name = "CntjTerceros.findByNaturalezaJuridica", query = "SELECT c FROM CntjTerceros c WHERE c.naturalezaJuridica = :naturalezaJuridica"),
    @NamedQuery(name = "CntjTerceros.findByMaeTipoDocumentoId", query = "SELECT c FROM CntjTerceros c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CntjTerceros.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CntjTerceros c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CntjTerceros.findByMaeTipoDocumentoValor", query = "SELECT c FROM CntjTerceros c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CntjTerceros.findByNumeroDocumento", query = "SELECT c FROM CntjTerceros c WHERE c.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "CntjTerceros.findByRazonSocial", query = "SELECT c FROM CntjTerceros c WHERE c.razonSocial = :razonSocial"),
    @NamedQuery(name = "CntjTerceros.findByMaeRepresentanteTipoDocumentoId", query = "SELECT c FROM CntjTerceros c WHERE c.maeRepresentanteTipoDocumentoId = :maeRepresentanteTipoDocumentoId"),
    @NamedQuery(name = "CntjTerceros.findByMaeRepresentanteTipoDocumentoCodigo", query = "SELECT c FROM CntjTerceros c WHERE c.maeRepresentanteTipoDocumentoCodigo = :maeRepresentanteTipoDocumentoCodigo"),
    @NamedQuery(name = "CntjTerceros.findByMaeRepresentanteTipoDocumentoValor", query = "SELECT c FROM CntjTerceros c WHERE c.maeRepresentanteTipoDocumentoValor = :maeRepresentanteTipoDocumentoValor"),
    @NamedQuery(name = "CntjTerceros.findByRepresentanteNumeroDocumento", query = "SELECT c FROM CntjTerceros c WHERE c.representanteNumeroDocumento = :representanteNumeroDocumento"),
    @NamedQuery(name = "CntjTerceros.findByNombreRepresentanteLegal", query = "SELECT c FROM CntjTerceros c WHERE c.nombreRepresentanteLegal = :nombreRepresentanteLegal"),
    @NamedQuery(name = "CntjTerceros.findByCodigoHabilitacion", query = "SELECT c FROM CntjTerceros c WHERE c.codigoHabilitacion = :codigoHabilitacion"),
    @NamedQuery(name = "CntjTerceros.findByDireccion", query = "SELECT c FROM CntjTerceros c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "CntjTerceros.findByCorreoElectronico", query = "SELECT c FROM CntjTerceros c WHERE c.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "CntjTerceros.findByTelefonoTercero", query = "SELECT c FROM CntjTerceros c WHERE c.telefonoTercero = :telefonoTercero"),
    @NamedQuery(name = "CntjTerceros.findByMaeCargoId", query = "SELECT c FROM CntjTerceros c WHERE c.maeCargoId = :maeCargoId"),
    @NamedQuery(name = "CntjTerceros.findByMaeCargoCodigo", query = "SELECT c FROM CntjTerceros c WHERE c.maeCargoCodigo = :maeCargoCodigo"),
    @NamedQuery(name = "CntjTerceros.findByMaeCargoValor", query = "SELECT c FROM CntjTerceros c WHERE c.maeCargoValor = :maeCargoValor"),
    @NamedQuery(name = "CntjTerceros.findByMaeAreaId", query = "SELECT c FROM CntjTerceros c WHERE c.maeAreaId = :maeAreaId"),
    @NamedQuery(name = "CntjTerceros.findByMaeAreaCodigo", query = "SELECT c FROM CntjTerceros c WHERE c.maeAreaCodigo = :maeAreaCodigo"),
    @NamedQuery(name = "CntjTerceros.findByMaeAreaValor", query = "SELECT c FROM CntjTerceros c WHERE c.maeAreaValor = :maeAreaValor"),
    @NamedQuery(name = "CntjTerceros.findByUnionTemporal", query = "SELECT c FROM CntjTerceros c WHERE c.unionTemporal = :unionTemporal"),
    @NamedQuery(name = "CntjTerceros.findByUsuarioCrea", query = "SELECT c FROM CntjTerceros c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjTerceros.findByTerminalCrea", query = "SELECT c FROM CntjTerceros c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjTerceros.findByFechaHoraCrea", query = "SELECT c FROM CntjTerceros c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjTerceros.findByUsuarioModifica", query = "SELECT c FROM CntjTerceros c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjTerceros.findByTerminalModifica", query = "SELECT c FROM CntjTerceros c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjTerceros.findByFechaHoraModifica", query = "SELECT c FROM CntjTerceros c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjTerceros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tercero")
    private short tipoTercero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "naturaleza_juridica")
    private short naturalezaJuridica;
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
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Size(max = 256)
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "mae_representante_tipo_documento_id")
    private Integer maeRepresentanteTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_representante_tipo_documento_codigo")
    private String maeRepresentanteTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_representante_tipo_documento_valor")
    private String maeRepresentanteTipoDocumentoValor;
    @Size(max = 32)
    @Column(name = "representante_numero_documento")
    private String representanteNumeroDocumento;
    @Size(max = 128)
    @Column(name = "nombre_representante_legal")
    private String nombreRepresentanteLegal;
    @Size(max = 16)
    @Column(name = "codigo_habilitacion")
    private String codigoHabilitacion;
    @Size(max = 256)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 128)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Size(max = 16)
    @Column(name = "telefono_tercero")
    private String telefonoTercero;
    @Column(name = "mae_cargo_id")
    private Integer maeCargoId;
    @Size(max = 8)
    @Column(name = "mae_cargo_codigo")
    private String maeCargoCodigo;
    @Size(max = 128)
    @Column(name = "mae_cargo_valor")
    private String maeCargoValor;
    @Column(name = "mae_area_id")
    private Integer maeAreaId;
    @Size(max = 8)
    @Column(name = "mae_area_codigo")
    private String maeAreaCodigo;
    @Size(max = 128)
    @Column(name = "mae_area_valor")
    private String maeAreaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "union_temporal")
    private boolean unionTemporal;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjTercerosId", fetch = FetchType.LAZY)
    private List<CntjTerceroUnionTemporal> cntjTerceroUnionTemporalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjTercerosId", fetch = FetchType.LAZY)
    private List<CntjTerceroContactos> cntjTerceroContactosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjTercerosId", fetch = FetchType.LAZY)
    private List<CntjContratos> cntjContratosList;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @JoinColumn(name = "gn_ubicaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUbicaciones gnUbicacionesId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjTercerosId", fetch = FetchType.LAZY)
    private List<CntjContratoSupervisores> cntjContratoSupervisoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjTercerosId", fetch = FetchType.LAZY)
    private List<CntjContratoSeguimientos> cntjContratoSeguimientosList;

    public CntjTerceros() {
    }

    public CntjTerceros(Integer id) {
        this.id = id;
    }

    public CntjTerceros(Integer id, short tipoTercero, short naturalezaJuridica, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String numeroDocumento, boolean unionTemporal, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoTercero = tipoTercero;
        this.naturalezaJuridica = naturalezaJuridica;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroDocumento = numeroDocumento;
        this.unionTemporal = unionTemporal;
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

    public short getTipoTercero() {
        return tipoTercero;
    }

    public void setTipoTercero(short tipoTercero) {
        this.tipoTercero = tipoTercero;
    }

    public short getNaturalezaJuridica() {
        return naturalezaJuridica;
    }

    public void setNaturalezaJuridica(short naturalezaJuridica) {
        this.naturalezaJuridica = naturalezaJuridica;
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Integer getMaeRepresentanteTipoDocumentoId() {
        return maeRepresentanteTipoDocumentoId;
    }

    public void setMaeRepresentanteTipoDocumentoId(Integer maeRepresentanteTipoDocumentoId) {
        this.maeRepresentanteTipoDocumentoId = maeRepresentanteTipoDocumentoId;
    }

    public String getMaeRepresentanteTipoDocumentoCodigo() {
        return maeRepresentanteTipoDocumentoCodigo;
    }

    public void setMaeRepresentanteTipoDocumentoCodigo(String maeRepresentanteTipoDocumentoCodigo) {
        this.maeRepresentanteTipoDocumentoCodigo = maeRepresentanteTipoDocumentoCodigo;
    }

    public String getMaeRepresentanteTipoDocumentoValor() {
        return maeRepresentanteTipoDocumentoValor;
    }

    public void setMaeRepresentanteTipoDocumentoValor(String maeRepresentanteTipoDocumentoValor) {
        this.maeRepresentanteTipoDocumentoValor = maeRepresentanteTipoDocumentoValor;
    }

    public String getRepresentanteNumeroDocumento() {
        return representanteNumeroDocumento;
    }

    public void setRepresentanteNumeroDocumento(String representanteNumeroDocumento) {
        this.representanteNumeroDocumento = representanteNumeroDocumento;
    }

    public String getNombreRepresentanteLegal() {
        return nombreRepresentanteLegal;
    }

    public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
    }

    public String getCodigoHabilitacion() {
        return codigoHabilitacion;
    }

    public void setCodigoHabilitacion(String codigoHabilitacion) {
        this.codigoHabilitacion = codigoHabilitacion;
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

    public String getTelefonoTercero() {
        return telefonoTercero;
    }

    public void setTelefonoTercero(String telefonoTercero) {
        this.telefonoTercero = telefonoTercero;
    }

    public Integer getMaeCargoId() {
        return maeCargoId;
    }

    public void setMaeCargoId(Integer maeCargoId) {
        this.maeCargoId = maeCargoId;
    }

    public String getMaeCargoCodigo() {
        return maeCargoCodigo;
    }

    public void setMaeCargoCodigo(String maeCargoCodigo) {
        this.maeCargoCodigo = maeCargoCodigo;
    }

    public String getMaeCargoValor() {
        return maeCargoValor;
    }

    public void setMaeCargoValor(String maeCargoValor) {
        this.maeCargoValor = maeCargoValor;
    }

    public Integer getMaeAreaId() {
        return maeAreaId;
    }

    public void setMaeAreaId(Integer maeAreaId) {
        this.maeAreaId = maeAreaId;
    }

    public String getMaeAreaCodigo() {
        return maeAreaCodigo;
    }

    public void setMaeAreaCodigo(String maeAreaCodigo) {
        this.maeAreaCodigo = maeAreaCodigo;
    }

    public String getMaeAreaValor() {
        return maeAreaValor;
    }

    public void setMaeAreaValor(String maeAreaValor) {
        this.maeAreaValor = maeAreaValor;
    }

    public boolean getUnionTemporal() {
        return unionTemporal;
    }

    public void setUnionTemporal(boolean unionTemporal) {
        this.unionTemporal = unionTemporal;
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
    public List<CntjTerceroUnionTemporal> getCntjTerceroUnionTemporalList() {
        return cntjTerceroUnionTemporalList;
    }

    public void setCntjTerceroUnionTemporalList(List<CntjTerceroUnionTemporal> cntjTerceroUnionTemporalList) {
        this.cntjTerceroUnionTemporalList = cntjTerceroUnionTemporalList;
    }

    @XmlTransient
    public List<CntjTerceroContactos> getCntjTerceroContactosList() {
        return cntjTerceroContactosList;
    }

    public void setCntjTerceroContactosList(List<CntjTerceroContactos> cntjTerceroContactosList) {
        this.cntjTerceroContactosList = cntjTerceroContactosList;
    }

    @XmlTransient
    public List<CntjContratos> getCntjContratosList() {
        return cntjContratosList;
    }

    public void setCntjContratosList(List<CntjContratos> cntjContratosList) {
        this.cntjContratosList = cntjContratosList;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public GnUbicaciones getGnUbicacionesId() {
        return gnUbicacionesId;
    }

    public void setGnUbicacionesId(GnUbicaciones gnUbicacionesId) {
        this.gnUbicacionesId = gnUbicacionesId;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    @XmlTransient
    public List<CntjContratoSupervisores> getCntjContratoSupervisoresList() {
        return cntjContratoSupervisoresList;
    }

    public void setCntjContratoSupervisoresList(List<CntjContratoSupervisores> cntjContratoSupervisoresList) {
        this.cntjContratoSupervisoresList = cntjContratoSupervisoresList;
    }

    @XmlTransient
    public List<CntjContratoSeguimientos> getCntjContratoSeguimientosList() {
        return cntjContratoSeguimientosList;
    }

    public void setCntjContratoSeguimientosList(List<CntjContratoSeguimientos> cntjContratoSeguimientosList) {
        this.cntjContratoSeguimientosList = cntjContratoSeguimientosList;
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
        if (!(object instanceof CntjTerceros)) {
            return false;
        }
        CntjTerceros other = (CntjTerceros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjTerceros[ id=" + id + " ]";
    }
    
}
