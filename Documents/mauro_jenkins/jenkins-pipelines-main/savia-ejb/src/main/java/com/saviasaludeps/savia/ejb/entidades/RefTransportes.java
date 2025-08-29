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
@Table(name = "ref_transportes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RefTransportes.findAll", query = "SELECT r FROM RefTransportes r"),
    @NamedQuery(name = "RefTransportes.findById", query = "SELECT r FROM RefTransportes r WHERE r.id = :id"),
    @NamedQuery(name = "RefTransportes.findByMaeClaseTransporteId", query = "SELECT r FROM RefTransportes r WHERE r.maeClaseTransporteId = :maeClaseTransporteId"),
    @NamedQuery(name = "RefTransportes.findByMaeClaseTransporteCodigo", query = "SELECT r FROM RefTransportes r WHERE r.maeClaseTransporteCodigo = :maeClaseTransporteCodigo"),
    @NamedQuery(name = "RefTransportes.findByMaeClaseTransporteValor", query = "SELECT r FROM RefTransportes r WHERE r.maeClaseTransporteValor = :maeClaseTransporteValor"),
    @NamedQuery(name = "RefTransportes.findByMaeTipoTransporteId", query = "SELECT r FROM RefTransportes r WHERE r.maeTipoTransporteId = :maeTipoTransporteId"),
    @NamedQuery(name = "RefTransportes.findByMaeTipoTransporteCodigo", query = "SELECT r FROM RefTransportes r WHERE r.maeTipoTransporteCodigo = :maeTipoTransporteCodigo"),
    @NamedQuery(name = "RefTransportes.findByMaeTipoTransporteValor", query = "SELECT r FROM RefTransportes r WHERE r.maeTipoTransporteValor = :maeTipoTransporteValor"),
    @NamedQuery(name = "RefTransportes.findByMaeTransporteLiquidacionId", query = "SELECT r FROM RefTransportes r WHERE r.maeTransporteLiquidacionId = :maeTransporteLiquidacionId"),
    @NamedQuery(name = "RefTransportes.findByMaeTransporteLiquidacionCodigo", query = "SELECT r FROM RefTransportes r WHERE r.maeTransporteLiquidacionCodigo = :maeTransporteLiquidacionCodigo"),
    @NamedQuery(name = "RefTransportes.findByMaeTransporteLiquidacionValor", query = "SELECT r FROM RefTransportes r WHERE r.maeTransporteLiquidacionValor = :maeTransporteLiquidacionValor"),
    @NamedQuery(name = "RefTransportes.findByObservacion", query = "SELECT r FROM RefTransportes r WHERE r.observacion = :observacion"),
    @NamedQuery(name = "RefTransportes.findByFechaHoraOrigen", query = "SELECT r FROM RefTransportes r WHERE r.fechaHoraOrigen = :fechaHoraOrigen"),
    @NamedQuery(name = "RefTransportes.findByFechaHoraDestino", query = "SELECT r FROM RefTransportes r WHERE r.fechaHoraDestino = :fechaHoraDestino"),
    @NamedQuery(name = "RefTransportes.findByUsuarioCrea", query = "SELECT r FROM RefTransportes r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RefTransportes.findByTerminalCrea", query = "SELECT r FROM RefTransportes r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RefTransportes.findByFechaHoraCrea", query = "SELECT r FROM RefTransportes r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RefTransportes.findByUsuarioModifica", query = "SELECT r FROM RefTransportes r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RefTransportes.findByTerminalModifica", query = "SELECT r FROM RefTransportes r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RefTransportes.findByFechaHoraModifica", query = "SELECT r FROM RefTransportes r WHERE r.fechaHoraModifica = :fechaHoraModifica")})
public class RefTransportes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_clase_transporte_id")
    private Integer maeClaseTransporteId;
    @Size(max = 8)
    @Column(name = "mae_clase_transporte_codigo")
    private String maeClaseTransporteCodigo;
    @Size(max = 128)
    @Column(name = "mae_clase_transporte_valor")
    private String maeClaseTransporteValor;
    @Column(name = "mae_tipo_transporte_id")
    private Integer maeTipoTransporteId;
    @Size(max = 8)
    @Column(name = "mae_tipo_transporte_codigo")
    private String maeTipoTransporteCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_transporte_valor")
    private String maeTipoTransporteValor;
    @Column(name = "mae_transporte_liquidacion_id")
    private Integer maeTransporteLiquidacionId;
    @Size(max = 8)
    @Column(name = "mae_transporte_liquidacion_codigo")
    private String maeTransporteLiquidacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_transporte_liquidacion_valor")
    private String maeTransporteLiquidacionValor;
    @Size(max = 2048)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_origen")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraOrigen;
    @Column(name = "fecha_hora_destino")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraDestino;
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
    @JoinColumn(name = "ref_anexos9_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RefAnexos9 refAnexos9Id;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refTransportesId", fetch = FetchType.LAZY)
    private List<RefTransporteSeguimientos> refTransporteSeguimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refTransportesId", fetch = FetchType.LAZY)
    private List<RefTransporteInsumos> refTransporteInsumosList;

    public RefTransportes() {
    }

    public RefTransportes(Integer id) {
        this.id = id;
    }

    public RefTransportes(Integer id, Date fechaHoraOrigen, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaHoraOrigen = fechaHoraOrigen;
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

    public Integer getMaeClaseTransporteId() {
        return maeClaseTransporteId;
    }

    public void setMaeClaseTransporteId(Integer maeClaseTransporteId) {
        this.maeClaseTransporteId = maeClaseTransporteId;
    }

    public String getMaeClaseTransporteCodigo() {
        return maeClaseTransporteCodigo;
    }

    public void setMaeClaseTransporteCodigo(String maeClaseTransporteCodigo) {
        this.maeClaseTransporteCodigo = maeClaseTransporteCodigo;
    }

    public String getMaeClaseTransporteValor() {
        return maeClaseTransporteValor;
    }

    public void setMaeClaseTransporteValor(String maeClaseTransporteValor) {
        this.maeClaseTransporteValor = maeClaseTransporteValor;
    }

    public Integer getMaeTipoTransporteId() {
        return maeTipoTransporteId;
    }

    public void setMaeTipoTransporteId(Integer maeTipoTransporteId) {
        this.maeTipoTransporteId = maeTipoTransporteId;
    }

    public String getMaeTipoTransporteCodigo() {
        return maeTipoTransporteCodigo;
    }

    public void setMaeTipoTransporteCodigo(String maeTipoTransporteCodigo) {
        this.maeTipoTransporteCodigo = maeTipoTransporteCodigo;
    }

    public String getMaeTipoTransporteValor() {
        return maeTipoTransporteValor;
    }

    public void setMaeTipoTransporteValor(String maeTipoTransporteValor) {
        this.maeTipoTransporteValor = maeTipoTransporteValor;
    }

    public Integer getMaeTransporteLiquidacionId() {
        return maeTransporteLiquidacionId;
    }

    public void setMaeTransporteLiquidacionId(Integer maeTransporteLiquidacionId) {
        this.maeTransporteLiquidacionId = maeTransporteLiquidacionId;
    }

    public String getMaeTransporteLiquidacionCodigo() {
        return maeTransporteLiquidacionCodigo;
    }

    public void setMaeTransporteLiquidacionCodigo(String maeTransporteLiquidacionCodigo) {
        this.maeTransporteLiquidacionCodigo = maeTransporteLiquidacionCodigo;
    }

    public String getMaeTransporteLiquidacionValor() {
        return maeTransporteLiquidacionValor;
    }

    public void setMaeTransporteLiquidacionValor(String maeTransporteLiquidacionValor) {
        this.maeTransporteLiquidacionValor = maeTransporteLiquidacionValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaHoraOrigen() {
        return fechaHoraOrigen;
    }

    public void setFechaHoraOrigen(Date fechaHoraOrigen) {
        this.fechaHoraOrigen = fechaHoraOrigen;
    }

    public Date getFechaHoraDestino() {
        return fechaHoraDestino;
    }

    public void setFechaHoraDestino(Date fechaHoraDestino) {
        this.fechaHoraDestino = fechaHoraDestino;
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

    public RefAnexos9 getRefAnexos9Id() {
        return refAnexos9Id;
    }

    public void setRefAnexos9Id(RefAnexos9 refAnexos9Id) {
        this.refAnexos9Id = refAnexos9Id;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    @XmlTransient
    public List<RefTransporteSeguimientos> getRefTransporteSeguimientosList() {
        return refTransporteSeguimientosList;
    }

    public void setRefTransporteSeguimientosList(List<RefTransporteSeguimientos> refTransporteSeguimientosList) {
        this.refTransporteSeguimientosList = refTransporteSeguimientosList;
    }

    @XmlTransient
    public List<RefTransporteInsumos> getRefTransporteInsumosList() {
        return refTransporteInsumosList;
    }

    public void setRefTransporteInsumosList(List<RefTransporteInsumos> refTransporteInsumosList) {
        this.refTransporteInsumosList = refTransporteInsumosList;
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
        if (!(object instanceof RefTransportes)) {
            return false;
        }
        RefTransportes other = (RefTransportes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RefTransportes[ id=" + id + " ]";
    }
    
}
