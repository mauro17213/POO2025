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
@Table(name = "ref_anexo9_gestiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RefAnexo9Gestiones.findAll", query = "SELECT r FROM RefAnexo9Gestiones r"),
    @NamedQuery(name = "RefAnexo9Gestiones.findById", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.id = :id"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByOrigen", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.origen = :origen"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByMaeTipoId", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.maeTipoId = :maeTipoId"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByMaeTipoCodigo", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.maeTipoCodigo = :maeTipoCodigo"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByMaeTipoValor", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.maeTipoValor = :maeTipoValor"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByMaeMotivoId", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.maeMotivoId = :maeMotivoId"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByMaeMotivoCodigo", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.maeMotivoCodigo = :maeMotivoCodigo"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByMaeMotivoValor", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.maeMotivoValor = :maeMotivoValor"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByObservacion", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.observacion = :observacion"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByContactoNombre", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.contactoNombre = :contactoNombre"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByContactoTelefono", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.contactoTelefono = :contactoTelefono"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByFechaHoraAceptacion", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.fechaHoraAceptacion = :fechaHoraAceptacion"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByFechaHoraEgreso", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.fechaHoraEgreso = :fechaHoraEgreso"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByUsuarioCrea", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByTerminalCrea", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RefAnexo9Gestiones.findByFechaHoraCrea", query = "SELECT r FROM RefAnexo9Gestiones r WHERE r.fechaHoraCrea = :fechaHoraCrea")})
public class RefAnexo9Gestiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "origen")
    private int origen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_id")
    private int maeTipoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_codigo")
    private String maeTipoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_valor")
    private String maeTipoValor;
    @Column(name = "mae_motivo_id")
    private Integer maeMotivoId;
    @Size(max = 8)
    @Column(name = "mae_motivo_codigo")
    private String maeMotivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_motivo_valor")
    private String maeMotivoValor;
    @Size(max = 1024)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 256)
    @Column(name = "contacto_nombre")
    private String contactoNombre;
    @Size(max = 16)
    @Column(name = "contacto_telefono")
    private String contactoTelefono;
    @Column(name = "fecha_hora_aceptacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAceptacion;
    @Column(name = "fecha_hora_egreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEgreso;
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
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "ref_anexos9_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RefAnexos9 refAnexos9Id;

    public RefAnexo9Gestiones() {
    }

    public RefAnexo9Gestiones(Integer id) {
        this.id = id;
    }

    public RefAnexo9Gestiones(Integer id, int origen, int maeTipoId, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.origen = origen;
        this.maeTipoId = maeTipoId;
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

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getMaeTipoId() {
        return maeTipoId;
    }

    public void setMaeTipoId(int maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
    }

    public Integer getMaeMotivoId() {
        return maeMotivoId;
    }

    public void setMaeMotivoId(Integer maeMotivoId) {
        this.maeMotivoId = maeMotivoId;
    }

    public String getMaeMotivoCodigo() {
        return maeMotivoCodigo;
    }

    public void setMaeMotivoCodigo(String maeMotivoCodigo) {
        this.maeMotivoCodigo = maeMotivoCodigo;
    }

    public String getMaeMotivoValor() {
        return maeMotivoValor;
    }

    public void setMaeMotivoValor(String maeMotivoValor) {
        this.maeMotivoValor = maeMotivoValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getContactoNombre() {
        return contactoNombre;
    }

    public void setContactoNombre(String contactoNombre) {
        this.contactoNombre = contactoNombre;
    }

    public String getContactoTelefono() {
        return contactoTelefono;
    }

    public void setContactoTelefono(String contactoTelefono) {
        this.contactoTelefono = contactoTelefono;
    }

    public Date getFechaHoraAceptacion() {
        return fechaHoraAceptacion;
    }

    public void setFechaHoraAceptacion(Date fechaHoraAceptacion) {
        this.fechaHoraAceptacion = fechaHoraAceptacion;
    }

    public Date getFechaHoraEgreso() {
        return fechaHoraEgreso;
    }

    public void setFechaHoraEgreso(Date fechaHoraEgreso) {
        this.fechaHoraEgreso = fechaHoraEgreso;
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

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public RefAnexos9 getRefAnexos9Id() {
        return refAnexos9Id;
    }

    public void setRefAnexos9Id(RefAnexos9 refAnexos9Id) {
        this.refAnexos9Id = refAnexos9Id;
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
        if (!(object instanceof RefAnexo9Gestiones)) {
            return false;
        }
        RefAnexo9Gestiones other = (RefAnexo9Gestiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RefAnexo9Gestiones[ id=" + id + " ]";
    }
    
}
