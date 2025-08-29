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
@Table(name = "auc_hospitalizacion_seguimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findAll", query = "SELECT a FROM AucHospitalizacionSeguimientos a"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findById", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.id = :id"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeTipoSeguimientoId", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeTipoSeguimientoId = :maeTipoSeguimientoId"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeTipoSeguimientoCodigo", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeTipoSeguimientoCodigo = :maeTipoSeguimientoCodigo"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeTipoSeguimientoValor", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeTipoSeguimientoValor = :maeTipoSeguimientoValor"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeTipoSeguimientoTipo", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeTipoSeguimientoTipo = :maeTipoSeguimientoTipo"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeTipoGestionId", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeTipoGestionId = :maeTipoGestionId"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeTipoGestionCodigo", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeTipoGestionCodigo = :maeTipoGestionCodigo"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeTipoGestionValor", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeTipoGestionValor = :maeTipoGestionValor"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeTipoGestionTipo", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeTipoGestionTipo = :maeTipoGestionTipo"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeTipoGestionEstadoId", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeTipoGestionEstadoId = :maeTipoGestionEstadoId"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeTipoGestionEstadoCodigo", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeTipoGestionEstadoCodigo = :maeTipoGestionEstadoCodigo"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeTipoGestionEstadoValor", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeTipoGestionEstadoValor = :maeTipoGestionEstadoValor"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeTipoGestionEstadoTipo", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeTipoGestionEstadoTipo = :maeTipoGestionEstadoTipo"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeDestinoId", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeDestinoId = :maeDestinoId"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeDestinoCodigo", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeDestinoCodigo = :maeDestinoCodigo"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeDestinoValor", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeDestinoValor = :maeDestinoValor"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByMaeDestinoTipo", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.maeDestinoTipo = :maeDestinoTipo"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByDescripcion", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByOrigen", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.origen = :origen"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByBorrado", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByBorradoObservacion", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.borradoObservacion = :borradoObservacion"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByCntPrestadorSedesId", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.cntPrestadorSedesId = :cntPrestadorSedesId"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByCntPrestadoresId", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.cntPrestadoresId = :cntPrestadoresId"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByFechaCierreGestion", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.fechaCierreGestion = :fechaCierreGestion"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByUsuarioCrea", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByTerminalCrea", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByFechaHoraCrea", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByUsuarioModifica", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByTerminalModifica", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByFechaHoraModifica", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByUsuarioBorra", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByTerminalBorra", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AucHospitalizacionSeguimientos.findByFechaHoraBorra", query = "SELECT a FROM AucHospitalizacionSeguimientos a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AucHospitalizacionSeguimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_tipo_seguimiento_id")
    private Integer maeTipoSeguimientoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_seguimiento_codigo")
    private String maeTipoSeguimientoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_seguimiento_valor")
    private String maeTipoSeguimientoValor;
    @Size(max = 4)
    @Column(name = "mae_tipo_seguimiento_tipo")
    private String maeTipoSeguimientoTipo;
    @Column(name = "mae_tipo_gestion_id")
    private Integer maeTipoGestionId;
    @Size(max = 8)
    @Column(name = "mae_tipo_gestion_codigo")
    private String maeTipoGestionCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_gestion_valor")
    private String maeTipoGestionValor;
    @Size(max = 4)
    @Column(name = "mae_tipo_gestion_tipo")
    private String maeTipoGestionTipo;
    @Column(name = "mae_tipo_gestion_estado_id")
    private Integer maeTipoGestionEstadoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_gestion_estado_codigo")
    private String maeTipoGestionEstadoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_gestion_estado_valor")
    private String maeTipoGestionEstadoValor;
    @Size(max = 4)
    @Column(name = "mae_tipo_gestion_estado_tipo")
    private String maeTipoGestionEstadoTipo;
    @Column(name = "mae_destino_id")
    private Integer maeDestinoId;
    @Size(max = 8)
    @Column(name = "mae_destino_codigo")
    private String maeDestinoCodigo;
    @Size(max = 128)
    @Column(name = "mae_destino_valor")
    private String maeDestinoValor;
    @Size(max = 4)
    @Column(name = "mae_destino_tipo")
    private String maeDestinoTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "origen")
    private Integer origen;
    @Column(name = "borrado")
    private Boolean borrado;
    @Size(max = 1024)
    @Column(name = "borrado_observacion")
    private String borradoObservacion;
    @Column(name = "cnt_prestador_sedes_id")
    private Integer cntPrestadorSedesId;
    @Column(name = "cnt_prestadores_id")
    private Integer cntPrestadoresId;
    @Column(name = "fecha_cierre_gestion")
    @Temporal(TemporalType.DATE)
    private Date fechaCierreGestion;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @JoinColumn(name = "auc_hospitalizaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AucHospitalizaciones aucHospitalizacionesId;

    public AucHospitalizacionSeguimientos() {
    }

    public AucHospitalizacionSeguimientos(Integer id) {
        this.id = id;
    }

    public AucHospitalizacionSeguimientos(Integer id, String descripcion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.descripcion = descripcion;
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

    public Integer getMaeTipoSeguimientoId() {
        return maeTipoSeguimientoId;
    }

    public void setMaeTipoSeguimientoId(Integer maeTipoSeguimientoId) {
        this.maeTipoSeguimientoId = maeTipoSeguimientoId;
    }

    public String getMaeTipoSeguimientoCodigo() {
        return maeTipoSeguimientoCodigo;
    }

    public void setMaeTipoSeguimientoCodigo(String maeTipoSeguimientoCodigo) {
        this.maeTipoSeguimientoCodigo = maeTipoSeguimientoCodigo;
    }

    public String getMaeTipoSeguimientoValor() {
        return maeTipoSeguimientoValor;
    }

    public void setMaeTipoSeguimientoValor(String maeTipoSeguimientoValor) {
        this.maeTipoSeguimientoValor = maeTipoSeguimientoValor;
    }

    public String getMaeTipoSeguimientoTipo() {
        return maeTipoSeguimientoTipo;
    }

    public void setMaeTipoSeguimientoTipo(String maeTipoSeguimientoTipo) {
        this.maeTipoSeguimientoTipo = maeTipoSeguimientoTipo;
    }

    public Integer getMaeTipoGestionId() {
        return maeTipoGestionId;
    }

    public void setMaeTipoGestionId(Integer maeTipoGestionId) {
        this.maeTipoGestionId = maeTipoGestionId;
    }

    public String getMaeTipoGestionCodigo() {
        return maeTipoGestionCodigo;
    }

    public void setMaeTipoGestionCodigo(String maeTipoGestionCodigo) {
        this.maeTipoGestionCodigo = maeTipoGestionCodigo;
    }

    public String getMaeTipoGestionValor() {
        return maeTipoGestionValor;
    }

    public void setMaeTipoGestionValor(String maeTipoGestionValor) {
        this.maeTipoGestionValor = maeTipoGestionValor;
    }

    public String getMaeTipoGestionTipo() {
        return maeTipoGestionTipo;
    }

    public void setMaeTipoGestionTipo(String maeTipoGestionTipo) {
        this.maeTipoGestionTipo = maeTipoGestionTipo;
    }

    public Integer getMaeTipoGestionEstadoId() {
        return maeTipoGestionEstadoId;
    }

    public void setMaeTipoGestionEstadoId(Integer maeTipoGestionEstadoId) {
        this.maeTipoGestionEstadoId = maeTipoGestionEstadoId;
    }

    public String getMaeTipoGestionEstadoCodigo() {
        return maeTipoGestionEstadoCodigo;
    }

    public void setMaeTipoGestionEstadoCodigo(String maeTipoGestionEstadoCodigo) {
        this.maeTipoGestionEstadoCodigo = maeTipoGestionEstadoCodigo;
    }

    public String getMaeTipoGestionEstadoValor() {
        return maeTipoGestionEstadoValor;
    }

    public void setMaeTipoGestionEstadoValor(String maeTipoGestionEstadoValor) {
        this.maeTipoGestionEstadoValor = maeTipoGestionEstadoValor;
    }

    public String getMaeTipoGestionEstadoTipo() {
        return maeTipoGestionEstadoTipo;
    }

    public void setMaeTipoGestionEstadoTipo(String maeTipoGestionEstadoTipo) {
        this.maeTipoGestionEstadoTipo = maeTipoGestionEstadoTipo;
    }

    public Integer getMaeDestinoId() {
        return maeDestinoId;
    }

    public void setMaeDestinoId(Integer maeDestinoId) {
        this.maeDestinoId = maeDestinoId;
    }

    public String getMaeDestinoCodigo() {
        return maeDestinoCodigo;
    }

    public void setMaeDestinoCodigo(String maeDestinoCodigo) {
        this.maeDestinoCodigo = maeDestinoCodigo;
    }

    public String getMaeDestinoValor() {
        return maeDestinoValor;
    }

    public void setMaeDestinoValor(String maeDestinoValor) {
        this.maeDestinoValor = maeDestinoValor;
    }

    public String getMaeDestinoTipo() {
        return maeDestinoTipo;
    }

    public void setMaeDestinoTipo(String maeDestinoTipo) {
        this.maeDestinoTipo = maeDestinoTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
    }

    public Integer getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(Integer cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public Integer getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(Integer cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public Date getFechaCierreGestion() {
        return fechaCierreGestion;
    }

    public void setFechaCierreGestion(Date fechaCierreGestion) {
        this.fechaCierreGestion = fechaCierreGestion;
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

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public AucHospitalizaciones getAucHospitalizacionesId() {
        return aucHospitalizacionesId;
    }

    public void setAucHospitalizacionesId(AucHospitalizaciones aucHospitalizacionesId) {
        this.aucHospitalizacionesId = aucHospitalizacionesId;
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
        if (!(object instanceof AucHospitalizacionSeguimientos)) {
            return false;
        }
        AucHospitalizacionSeguimientos other = (AucHospitalizacionSeguimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionSeguimientos[ id=" + id + " ]";
    }
    
}
