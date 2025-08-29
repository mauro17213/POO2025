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
@Table(name = "tu_memorial_personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuMemorialPersonas.findAll", query = "SELECT t FROM TuMemorialPersonas t"),
    @NamedQuery(name = "TuMemorialPersonas.findById", query = "SELECT t FROM TuMemorialPersonas t WHERE t.id = :id"),
    @NamedQuery(name = "TuMemorialPersonas.findByTipoPersonal", query = "SELECT t FROM TuMemorialPersonas t WHERE t.tipoPersonal = :tipoPersonal"),
    @NamedQuery(name = "TuMemorialPersonas.findByMaeTipoDocumentoId", query = "SELECT t FROM TuMemorialPersonas t WHERE t.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "TuMemorialPersonas.findByMaeTipoDocumentoCodigo", query = "SELECT t FROM TuMemorialPersonas t WHERE t.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "TuMemorialPersonas.findByMaeTipoDocumentoValor", query = "SELECT t FROM TuMemorialPersonas t WHERE t.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "TuMemorialPersonas.findByNumeroDocumento", query = "SELECT t FROM TuMemorialPersonas t WHERE t.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "TuMemorialPersonas.findByMaeGnCargoId", query = "SELECT t FROM TuMemorialPersonas t WHERE t.maeGnCargoId = :maeGnCargoId"),
    @NamedQuery(name = "TuMemorialPersonas.findByMaeGnCargoCodigo", query = "SELECT t FROM TuMemorialPersonas t WHERE t.maeGnCargoCodigo = :maeGnCargoCodigo"),
    @NamedQuery(name = "TuMemorialPersonas.findByMaeGnCargoValor", query = "SELECT t FROM TuMemorialPersonas t WHERE t.maeGnCargoValor = :maeGnCargoValor"),
    @NamedQuery(name = "TuMemorialPersonas.findByNumeroTarjetaProfesional", query = "SELECT t FROM TuMemorialPersonas t WHERE t.numeroTarjetaProfesional = :numeroTarjetaProfesional"),
    @NamedQuery(name = "TuMemorialPersonas.findByPrimerNombre", query = "SELECT t FROM TuMemorialPersonas t WHERE t.primerNombre = :primerNombre"),
    @NamedQuery(name = "TuMemorialPersonas.findBySegundoNombre", query = "SELECT t FROM TuMemorialPersonas t WHERE t.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "TuMemorialPersonas.findByPrimerApellido", query = "SELECT t FROM TuMemorialPersonas t WHERE t.primerApellido = :primerApellido"),
    @NamedQuery(name = "TuMemorialPersonas.findBySegundoApellido", query = "SELECT t FROM TuMemorialPersonas t WHERE t.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "TuMemorialPersonas.findByUsuarioCrea", query = "SELECT t FROM TuMemorialPersonas t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuMemorialPersonas.findByTerminalCrea", query = "SELECT t FROM TuMemorialPersonas t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuMemorialPersonas.findByFechaHoraCrea", query = "SELECT t FROM TuMemorialPersonas t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuMemorialPersonas.findByUsuarioModifica", query = "SELECT t FROM TuMemorialPersonas t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuMemorialPersonas.findByTerminalModifica", query = "SELECT t FROM TuMemorialPersonas t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuMemorialPersonas.findByFechaHoraModifica", query = "SELECT t FROM TuMemorialPersonas t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuMemorialPersonas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_personal")
    private int tipoPersonal;
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
    @Size(min = 1, max = 45)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_gn_cargo_id")
    private int maeGnCargoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_gn_cargo_codigo")
    private String maeGnCargoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "mae_gn_cargo_valor")
    private String maeGnCargoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero_tarjeta_profesional")
    private String numeroTarjetaProfesional;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuMemorialPersonaId", fetch = FetchType.LAZY)
    private List<TuMemorialFirmas> tuMemorialFirmasList;

    public TuMemorialPersonas() {
    }

    public TuMemorialPersonas(Integer id) {
        this.id = id;
    }

    public TuMemorialPersonas(Integer id, int tipoPersonal, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String numeroDocumento, int maeGnCargoId, String maeGnCargoCodigo, String maeGnCargoValor, String numeroTarjetaProfesional, String primerNombre, String primerApellido, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoPersonal = tipoPersonal;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroDocumento = numeroDocumento;
        this.maeGnCargoId = maeGnCargoId;
        this.maeGnCargoCodigo = maeGnCargoCodigo;
        this.maeGnCargoValor = maeGnCargoValor;
        this.numeroTarjetaProfesional = numeroTarjetaProfesional;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
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

    public int getTipoPersonal() {
        return tipoPersonal;
    }

    public void setTipoPersonal(int tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
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

    public int getMaeGnCargoId() {
        return maeGnCargoId;
    }

    public void setMaeGnCargoId(int maeGnCargoId) {
        this.maeGnCargoId = maeGnCargoId;
    }

    public String getMaeGnCargoCodigo() {
        return maeGnCargoCodigo;
    }

    public void setMaeGnCargoCodigo(String maeGnCargoCodigo) {
        this.maeGnCargoCodigo = maeGnCargoCodigo;
    }

    public String getMaeGnCargoValor() {
        return maeGnCargoValor;
    }

    public void setMaeGnCargoValor(String maeGnCargoValor) {
        this.maeGnCargoValor = maeGnCargoValor;
    }

    public String getNumeroTarjetaProfesional() {
        return numeroTarjetaProfesional;
    }

    public void setNumeroTarjetaProfesional(String numeroTarjetaProfesional) {
        this.numeroTarjetaProfesional = numeroTarjetaProfesional;
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
    public List<TuMemorialFirmas> getTuMemorialFirmasList() {
        return tuMemorialFirmasList;
    }

    public void setTuMemorialFirmasList(List<TuMemorialFirmas> tuMemorialFirmasList) {
        this.tuMemorialFirmasList = tuMemorialFirmasList;
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
        if (!(object instanceof TuMemorialPersonas)) {
            return false;
        }
        TuMemorialPersonas other = (TuMemorialPersonas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuMemorialPersonas[ id=" + id + " ]";
    }
    
}
