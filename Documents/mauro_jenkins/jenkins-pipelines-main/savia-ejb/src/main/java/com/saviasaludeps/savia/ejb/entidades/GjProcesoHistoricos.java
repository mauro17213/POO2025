/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Lob;
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
@Table(name = "gj_proceso_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GjProcesoHistoricos.findAll", query = "SELECT g FROM GjProcesoHistoricos g"),
    @NamedQuery(name = "GjProcesoHistoricos.findById", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.id = :id"),
    @NamedQuery(name = "GjProcesoHistoricos.findByEstado", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.estado = :estado"),
    @NamedQuery(name = "GjProcesoHistoricos.findByFechaContestacion", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.fechaContestacion = :fechaContestacion"),
    @NamedQuery(name = "GjProcesoHistoricos.findByEstadoProceso", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.estadoProceso = :estadoProceso"),
    @NamedQuery(name = "GjProcesoHistoricos.findByMaeInstanciaId", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.maeInstanciaId = :maeInstanciaId"),
    @NamedQuery(name = "GjProcesoHistoricos.findByMaeInstanciaCodigo", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.maeInstanciaCodigo = :maeInstanciaCodigo"),
    @NamedQuery(name = "GjProcesoHistoricos.findByMaeInstanciaValor", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.maeInstanciaValor = :maeInstanciaValor"),
    @NamedQuery(name = "GjProcesoHistoricos.findByLlamamientoGarantia", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.llamamientoGarantia = :llamamientoGarantia"),
    @NamedQuery(name = "GjProcesoHistoricos.findByMaeMedicaCautelarId", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.maeMedicaCautelarId = :maeMedicaCautelarId"),
    @NamedQuery(name = "GjProcesoHistoricos.findByMaeMedicaCautelarCodigo", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.maeMedicaCautelarCodigo = :maeMedicaCautelarCodigo"),
    @NamedQuery(name = "GjProcesoHistoricos.findByMaeMedicaCautelarValor", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.maeMedicaCautelarValor = :maeMedicaCautelarValor"),
    @NamedQuery(name = "GjProcesoHistoricos.findByMontoMedida", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.montoMedida = :montoMedida"),
    @NamedQuery(name = "GjProcesoHistoricos.findByProbabilidad", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.probabilidad = :probabilidad"),
    @NamedQuery(name = "GjProcesoHistoricos.findByRiesgoClasificacion", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.riesgoClasificacion = :riesgoClasificacion"),
    @NamedQuery(name = "GjProcesoHistoricos.findByClaseProvision", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.claseProvision = :claseProvision"),
    @NamedQuery(name = "GjProcesoHistoricos.findByFechaTerminacion", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.fechaTerminacion = :fechaTerminacion"),
    @NamedQuery(name = "GjProcesoHistoricos.findBySentidoSentencia", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.sentidoSentencia = :sentidoSentencia"),
    @NamedQuery(name = "GjProcesoHistoricos.findByValorSentenciaEjecutoria", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.valorSentenciaEjecutoria = :valorSentenciaEjecutoria"),
    @NamedQuery(name = "GjProcesoHistoricos.findByEstadoCumplimientoCondena", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.estadoCumplimientoCondena = :estadoCumplimientoCondena"),
    @NamedQuery(name = "GjProcesoHistoricos.findByMaeActuacionTerminacionId", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.maeActuacionTerminacionId = :maeActuacionTerminacionId"),
    @NamedQuery(name = "GjProcesoHistoricos.findByMaeActuacionTerminacionCodigo", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.maeActuacionTerminacionCodigo = :maeActuacionTerminacionCodigo"),
    @NamedQuery(name = "GjProcesoHistoricos.findByMaeActuacionTerminacionValor", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.maeActuacionTerminacionValor = :maeActuacionTerminacionValor"),
    @NamedQuery(name = "GjProcesoHistoricos.findByValorAcuerdoTransaccion", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.valorAcuerdoTransaccion = :valorAcuerdoTransaccion"),
    @NamedQuery(name = "GjProcesoHistoricos.findByFechaActuacion", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.fechaActuacion = :fechaActuacion"),
    @NamedQuery(name = "GjProcesoHistoricos.findByUsuariosCrea", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.usuariosCrea = :usuariosCrea"),
    @NamedQuery(name = "GjProcesoHistoricos.findByTerminalCrea", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GjProcesoHistoricos.findByFechaHoraCrea", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GjProcesoHistoricos.findByUsuarioModifica", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GjProcesoHistoricos.findByTerminalModifica", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GjProcesoHistoricos.findByFechaHoraModifica", query = "SELECT g FROM GjProcesoHistoricos g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GjProcesoHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Column(name = "fecha_contestacion")
    @Temporal(TemporalType.DATE)
    private Date fechaContestacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_proceso")
    private short estadoProceso;
    @Column(name = "mae_instancia_id")
    private Integer maeInstanciaId;
    @Size(max = 8)
    @Column(name = "mae_instancia_codigo")
    private String maeInstanciaCodigo;
    @Size(max = 128)
    @Column(name = "mae_instancia_valor")
    private String maeInstanciaValor;
    @Column(name = "llamamiento_garantia")
    private Boolean llamamientoGarantia;
    @Column(name = "mae_medica_cautelar_id")
    private Integer maeMedicaCautelarId;
    @Size(max = 8)
    @Column(name = "mae_medica_cautelar_codigo")
    private String maeMedicaCautelarCodigo;
    @Size(max = 128)
    @Column(name = "mae_medica_cautelar_valor")
    private String maeMedicaCautelarValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto_medida")
    private BigDecimal montoMedida;
    @Column(name = "probabilidad")
    private Short probabilidad;
    @Column(name = "riesgo_clasificacion")
    private Short riesgoClasificacion;
    @Column(name = "clase_provision")
    private Short claseProvision;
    @Column(name = "fecha_terminacion")
    @Temporal(TemporalType.DATE)
    private Date fechaTerminacion;
    @Column(name = "sentido_sentencia")
    private Short sentidoSentencia;
    @Column(name = "valor_sentencia_ejecutoria")
    private BigDecimal valorSentenciaEjecutoria;
    @Column(name = "estado_cumplimiento_condena")
    private Short estadoCumplimientoCondena;
    @Column(name = "mae_actuacion_terminacion_id")
    private Integer maeActuacionTerminacionId;
    @Size(max = 8)
    @Column(name = "mae_actuacion_terminacion_codigo")
    private String maeActuacionTerminacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_actuacion_terminacion_valor")
    private String maeActuacionTerminacionValor;
    @Column(name = "valor_acuerdo_transaccion")
    private BigDecimal valorAcuerdoTransaccion;
    @Column(name = "fecha_actuacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActuacion;
    @Lob
    @Column(name = "actuacion")
    private byte[] actuacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuarios_crea")
    private String usuariosCrea;
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
    @OneToMany(mappedBy = "gjProcesoHistoricosId", fetch = FetchType.LAZY)
    private List<GjProcesoAdjuntos> gjProcesoAdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gjProcesoHistoricosId", fetch = FetchType.LAZY)
    private List<GjProcesoGarantias> gjProcesoGarantiasList;
    @JoinColumn(name = "gj_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GjProcesos gjProcesosId;

    public GjProcesoHistoricos() {
    }

    public GjProcesoHistoricos(Integer id) {
        this.id = id;
    }

    public GjProcesoHistoricos(Integer id, short estado, short estadoProceso, String usuariosCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.estadoProceso = estadoProceso;
        this.usuariosCrea = usuariosCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Date getFechaContestacion() {
        return fechaContestacion;
    }

    public void setFechaContestacion(Date fechaContestacion) {
        this.fechaContestacion = fechaContestacion;
    }

    public short getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(short estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public Integer getMaeInstanciaId() {
        return maeInstanciaId;
    }

    public void setMaeInstanciaId(Integer maeInstanciaId) {
        this.maeInstanciaId = maeInstanciaId;
    }

    public String getMaeInstanciaCodigo() {
        return maeInstanciaCodigo;
    }

    public void setMaeInstanciaCodigo(String maeInstanciaCodigo) {
        this.maeInstanciaCodigo = maeInstanciaCodigo;
    }

    public String getMaeInstanciaValor() {
        return maeInstanciaValor;
    }

    public void setMaeInstanciaValor(String maeInstanciaValor) {
        this.maeInstanciaValor = maeInstanciaValor;
    }

    public Boolean getLlamamientoGarantia() {
        return llamamientoGarantia;
    }

    public void setLlamamientoGarantia(Boolean llamamientoGarantia) {
        this.llamamientoGarantia = llamamientoGarantia;
    }

    public Integer getMaeMedicaCautelarId() {
        return maeMedicaCautelarId;
    }

    public void setMaeMedicaCautelarId(Integer maeMedicaCautelarId) {
        this.maeMedicaCautelarId = maeMedicaCautelarId;
    }

    public String getMaeMedicaCautelarCodigo() {
        return maeMedicaCautelarCodigo;
    }

    public void setMaeMedicaCautelarCodigo(String maeMedicaCautelarCodigo) {
        this.maeMedicaCautelarCodigo = maeMedicaCautelarCodigo;
    }

    public String getMaeMedicaCautelarValor() {
        return maeMedicaCautelarValor;
    }

    public void setMaeMedicaCautelarValor(String maeMedicaCautelarValor) {
        this.maeMedicaCautelarValor = maeMedicaCautelarValor;
    }

    public BigDecimal getMontoMedida() {
        return montoMedida;
    }

    public void setMontoMedida(BigDecimal montoMedida) {
        this.montoMedida = montoMedida;
    }

    public Short getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Short probabilidad) {
        this.probabilidad = probabilidad;
    }

    public Short getRiesgoClasificacion() {
        return riesgoClasificacion;
    }

    public void setRiesgoClasificacion(Short riesgoClasificacion) {
        this.riesgoClasificacion = riesgoClasificacion;
    }

    public Short getClaseProvision() {
        return claseProvision;
    }

    public void setClaseProvision(Short claseProvision) {
        this.claseProvision = claseProvision;
    }

    public Date getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(Date fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }

    public Short getSentidoSentencia() {
        return sentidoSentencia;
    }

    public void setSentidoSentencia(Short sentidoSentencia) {
        this.sentidoSentencia = sentidoSentencia;
    }

    public BigDecimal getValorSentenciaEjecutoria() {
        return valorSentenciaEjecutoria;
    }

    public void setValorSentenciaEjecutoria(BigDecimal valorSentenciaEjecutoria) {
        this.valorSentenciaEjecutoria = valorSentenciaEjecutoria;
    }

    public Short getEstadoCumplimientoCondena() {
        return estadoCumplimientoCondena;
    }

    public void setEstadoCumplimientoCondena(Short estadoCumplimientoCondena) {
        this.estadoCumplimientoCondena = estadoCumplimientoCondena;
    }

    public Integer getMaeActuacionTerminacionId() {
        return maeActuacionTerminacionId;
    }

    public void setMaeActuacionTerminacionId(Integer maeActuacionTerminacionId) {
        this.maeActuacionTerminacionId = maeActuacionTerminacionId;
    }

    public String getMaeActuacionTerminacionCodigo() {
        return maeActuacionTerminacionCodigo;
    }

    public void setMaeActuacionTerminacionCodigo(String maeActuacionTerminacionCodigo) {
        this.maeActuacionTerminacionCodigo = maeActuacionTerminacionCodigo;
    }

    public String getMaeActuacionTerminacionValor() {
        return maeActuacionTerminacionValor;
    }

    public void setMaeActuacionTerminacionValor(String maeActuacionTerminacionValor) {
        this.maeActuacionTerminacionValor = maeActuacionTerminacionValor;
    }

    public BigDecimal getValorAcuerdoTransaccion() {
        return valorAcuerdoTransaccion;
    }

    public void setValorAcuerdoTransaccion(BigDecimal valorAcuerdoTransaccion) {
        this.valorAcuerdoTransaccion = valorAcuerdoTransaccion;
    }

    public Date getFechaActuacion() {
        return fechaActuacion;
    }

    public void setFechaActuacion(Date fechaActuacion) {
        this.fechaActuacion = fechaActuacion;
    }

    public byte[] getActuacion() {
        return actuacion;
    }

    public void setActuacion(byte[] actuacion) {
        this.actuacion = actuacion;
    }

    public String getUsuariosCrea() {
        return usuariosCrea;
    }

    public void setUsuariosCrea(String usuariosCrea) {
        this.usuariosCrea = usuariosCrea;
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
    public List<GjProcesoAdjuntos> getGjProcesoAdjuntosList() {
        return gjProcesoAdjuntosList;
    }

    public void setGjProcesoAdjuntosList(List<GjProcesoAdjuntos> gjProcesoAdjuntosList) {
        this.gjProcesoAdjuntosList = gjProcesoAdjuntosList;
    }

    @XmlTransient
    public List<GjProcesoGarantias> getGjProcesoGarantiasList() {
        return gjProcesoGarantiasList;
    }

    public void setGjProcesoGarantiasList(List<GjProcesoGarantias> gjProcesoGarantiasList) {
        this.gjProcesoGarantiasList = gjProcesoGarantiasList;
    }

    public GjProcesos getGjProcesosId() {
        return gjProcesosId;
    }

    public void setGjProcesosId(GjProcesos gjProcesosId) {
        this.gjProcesosId = gjProcesosId;
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
        if (!(object instanceof GjProcesoHistoricos)) {
            return false;
        }
        GjProcesoHistoricos other = (GjProcesoHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GjProcesoHistoricos[ id=" + id + " ]";
    }
    
}
