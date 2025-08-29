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
@Table(name = "gj_proceso_terceros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GjProcesoTerceros.findAll", query = "SELECT g FROM GjProcesoTerceros g"),
    @NamedQuery(name = "GjProcesoTerceros.findById", query = "SELECT g FROM GjProcesoTerceros g WHERE g.id = :id"),
    @NamedQuery(name = "GjProcesoTerceros.findByMaeCalidadActuaId", query = "SELECT g FROM GjProcesoTerceros g WHERE g.maeCalidadActuaId = :maeCalidadActuaId"),
    @NamedQuery(name = "GjProcesoTerceros.findByMaeCalidadActuaCodigo", query = "SELECT g FROM GjProcesoTerceros g WHERE g.maeCalidadActuaCodigo = :maeCalidadActuaCodigo"),
    @NamedQuery(name = "GjProcesoTerceros.findByMaeCalidadActuaValor", query = "SELECT g FROM GjProcesoTerceros g WHERE g.maeCalidadActuaValor = :maeCalidadActuaValor"),
    @NamedQuery(name = "GjProcesoTerceros.findByMaeTipoDocumentoId", query = "SELECT g FROM GjProcesoTerceros g WHERE g.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "GjProcesoTerceros.findByMaeTipoDocumentoCodigo", query = "SELECT g FROM GjProcesoTerceros g WHERE g.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "GjProcesoTerceros.findByMaeTipoDocumentoValor", query = "SELECT g FROM GjProcesoTerceros g WHERE g.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "GjProcesoTerceros.findByDocumento", query = "SELECT g FROM GjProcesoTerceros g WHERE g.documento = :documento"),
    @NamedQuery(name = "GjProcesoTerceros.findByNombres", query = "SELECT g FROM GjProcesoTerceros g WHERE g.nombres = :nombres"),
    @NamedQuery(name = "GjProcesoTerceros.findByApellidos", query = "SELECT g FROM GjProcesoTerceros g WHERE g.apellidos = :apellidos"),
    @NamedQuery(name = "GjProcesoTerceros.findByRazonSocial", query = "SELECT g FROM GjProcesoTerceros g WHERE g.razonSocial = :razonSocial"),
    @NamedQuery(name = "GjProcesoTerceros.findByTelefono", query = "SELECT g FROM GjProcesoTerceros g WHERE g.telefono = :telefono"),
    @NamedQuery(name = "GjProcesoTerceros.findByUsuarioCrea", query = "SELECT g FROM GjProcesoTerceros g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GjProcesoTerceros.findByTerminalCrea", query = "SELECT g FROM GjProcesoTerceros g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GjProcesoTerceros.findByFechaHoraCrea", query = "SELECT g FROM GjProcesoTerceros g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GjProcesoTerceros.findByUsuarioModifica", query = "SELECT g FROM GjProcesoTerceros g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GjProcesoTerceros.findByTerminalModifica", query = "SELECT g FROM GjProcesoTerceros g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GjProcesoTerceros.findByFechaHoraModifica", query = "SELECT g FROM GjProcesoTerceros g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GjProcesoTerceros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_calidad_actua_id")
    private int maeCalidadActuaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_calidad_actua_codigo")
    private String maeCalidadActuaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_calidad_actua_valor")
    private String maeCalidadActuaValor;
    @Column(name = "mae_tipo_documento_id")
    private Integer maeTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Size(max = 16)
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
    @JoinColumn(name = "gj_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GjProcesos gjProcesosId;
    @JoinColumn(name = "gj_terceros_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GjTerceros gjTercerosId;

    public GjProcesoTerceros() {
    }

    public GjProcesoTerceros(Integer id) {
        this.id = id;
    }

    public GjProcesoTerceros(Integer id, int maeCalidadActuaId, String maeCalidadActuaCodigo, String maeCalidadActuaValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeCalidadActuaId = maeCalidadActuaId;
        this.maeCalidadActuaCodigo = maeCalidadActuaCodigo;
        this.maeCalidadActuaValor = maeCalidadActuaValor;
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

    public int getMaeCalidadActuaId() {
        return maeCalidadActuaId;
    }

    public void setMaeCalidadActuaId(int maeCalidadActuaId) {
        this.maeCalidadActuaId = maeCalidadActuaId;
    }

    public String getMaeCalidadActuaCodigo() {
        return maeCalidadActuaCodigo;
    }

    public void setMaeCalidadActuaCodigo(String maeCalidadActuaCodigo) {
        this.maeCalidadActuaCodigo = maeCalidadActuaCodigo;
    }

    public String getMaeCalidadActuaValor() {
        return maeCalidadActuaValor;
    }

    public void setMaeCalidadActuaValor(String maeCalidadActuaValor) {
        this.maeCalidadActuaValor = maeCalidadActuaValor;
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

    public GjProcesos getGjProcesosId() {
        return gjProcesosId;
    }

    public void setGjProcesosId(GjProcesos gjProcesosId) {
        this.gjProcesosId = gjProcesosId;
    }

    public GjTerceros getGjTercerosId() {
        return gjTercerosId;
    }

    public void setGjTercerosId(GjTerceros gjTercerosId) {
        this.gjTercerosId = gjTercerosId;
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
        if (!(object instanceof GjProcesoTerceros)) {
            return false;
        }
        GjProcesoTerceros other = (GjProcesoTerceros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GjProcesoTerceros[ id=" + id + " ]";
    }
    
}
