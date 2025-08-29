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
@Table(name = "cm_rips_cargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsCargas.findAll", query = "SELECT c FROM CmRipsCargas c"),
    @NamedQuery(name = "CmRipsCargas.findById", query = "SELECT c FROM CmRipsCargas c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsCargas.findByContrato", query = "SELECT c FROM CmRipsCargas c WHERE c.contrato = :contrato"),
    @NamedQuery(name = "CmRipsCargas.findByCntTipoContratoId", query = "SELECT c FROM CmRipsCargas c WHERE c.cntTipoContratoId = :cntTipoContratoId"),
    @NamedQuery(name = "CmRipsCargas.findByValorCarga", query = "SELECT c FROM CmRipsCargas c WHERE c.valorCarga = :valorCarga"),
    @NamedQuery(name = "CmRipsCargas.findByEstado", query = "SELECT c FROM CmRipsCargas c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmRipsCargas.findByNumeroCuenta", query = "SELECT c FROM CmRipsCargas c WHERE c.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "CmRipsCargas.findByMaeRegimenId", query = "SELECT c FROM CmRipsCargas c WHERE c.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "CmRipsCargas.findByMaeRegimenCodigo", query = "SELECT c FROM CmRipsCargas c WHERE c.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "CmRipsCargas.findByMaeRegimenValor", query = "SELECT c FROM CmRipsCargas c WHERE c.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "CmRipsCargas.findByMaeRegionalId", query = "SELECT c FROM CmRipsCargas c WHERE c.maeRegionalId = :maeRegionalId"),
    @NamedQuery(name = "CmRipsCargas.findByMaeReginalCodigo", query = "SELECT c FROM CmRipsCargas c WHERE c.maeReginalCodigo = :maeReginalCodigo"),
    @NamedQuery(name = "CmRipsCargas.findByMaeRegionalValor", query = "SELECT c FROM CmRipsCargas c WHERE c.maeRegionalValor = :maeRegionalValor"),
    @NamedQuery(name = "CmRipsCargas.findByMaeContratoModalidadId", query = "SELECT c FROM CmRipsCargas c WHERE c.maeContratoModalidadId = :maeContratoModalidadId"),
    @NamedQuery(name = "CmRipsCargas.findByMaeContratoModalidadCodigo", query = "SELECT c FROM CmRipsCargas c WHERE c.maeContratoModalidadCodigo = :maeContratoModalidadCodigo"),
    @NamedQuery(name = "CmRipsCargas.findByMaeContratoModalidadValor", query = "SELECT c FROM CmRipsCargas c WHERE c.maeContratoModalidadValor = :maeContratoModalidadValor"),
    @NamedQuery(name = "CmRipsCargas.findByFechaHoraInicio", query = "SELECT c FROM CmRipsCargas c WHERE c.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "CmRipsCargas.findByFechaHoraFin", query = "SELECT c FROM CmRipsCargas c WHERE c.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "CmRipsCargas.findByTiempo", query = "SELECT c FROM CmRipsCargas c WHERE c.tiempo = :tiempo"),
    @NamedQuery(name = "CmRipsCargas.findByFechaPrestacion", query = "SELECT c FROM CmRipsCargas c WHERE c.fechaPrestacion = :fechaPrestacion"),
    @NamedQuery(name = "CmRipsCargas.findByUsuarioAudita", query = "SELECT c FROM CmRipsCargas c WHERE c.usuarioAudita = :usuarioAudita"),
    @NamedQuery(name = "CmRipsCargas.findByPbs", query = "SELECT c FROM CmRipsCargas c WHERE c.pbs = :pbs"),
    @NamedQuery(name = "CmRipsCargas.findByCamaFija", query = "SELECT c FROM CmRipsCargas c WHERE c.camaFija = :camaFija"),
    @NamedQuery(name = "CmRipsCargas.findByCantidadFactura", query = "SELECT c FROM CmRipsCargas c WHERE c.cantidadFactura = :cantidadFactura"),
    @NamedQuery(name = "CmRipsCargas.findByObservacionRechazo", query = "SELECT c FROM CmRipsCargas c WHERE c.observacionRechazo = :observacionRechazo"),
    @NamedQuery(name = "CmRipsCargas.findByMaeRechazoId", query = "SELECT c FROM CmRipsCargas c WHERE c.maeRechazoId = :maeRechazoId"),
    @NamedQuery(name = "CmRipsCargas.findByMaeRechazoCodigo", query = "SELECT c FROM CmRipsCargas c WHERE c.maeRechazoCodigo = :maeRechazoCodigo"),
    @NamedQuery(name = "CmRipsCargas.findByMaeRechazoValor", query = "SELECT c FROM CmRipsCargas c WHERE c.maeRechazoValor = :maeRechazoValor"),
    @NamedQuery(name = "CmRipsCargas.findByObservacion", query = "SELECT c FROM CmRipsCargas c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "CmRipsCargas.findByUsuarioCrea", query = "SELECT c FROM CmRipsCargas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsCargas.findByTerminalCrea", query = "SELECT c FROM CmRipsCargas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsCargas.findByFechaHoraCrea", query = "SELECT c FROM CmRipsCargas c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsCargas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "contrato")
    private String contrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cnt_tipo_contrato_id")
    private int cntTipoContratoId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_carga")
    private BigDecimal valorCarga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "numero_cuenta")
    private Integer numeroCuenta;
    @Column(name = "mae_regimen_id")
    private Integer maeRegimenId;
    @Size(max = 8)
    @Column(name = "mae_regimen_codigo")
    private String maeRegimenCodigo;
    @Size(max = 128)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Column(name = "mae_regional_id")
    private Integer maeRegionalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_reginal_codigo")
    private String maeReginalCodigo;
    @Size(max = 128)
    @Column(name = "mae_regional_valor")
    private String maeRegionalValor;
    @Column(name = "mae_contrato_modalidad_id")
    private Integer maeContratoModalidadId;
    @Size(max = 8)
    @Column(name = "mae_contrato_modalidad_codigo")
    private String maeContratoModalidadCodigo;
    @Size(max = 128)
    @Column(name = "mae_contrato_modalidad_valor")
    private String maeContratoModalidadValor;
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "tiempo")
    private Integer tiempo;
    @Column(name = "fecha_prestacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPrestacion;
    @Size(max = 128)
    @Column(name = "usuario_audita")
    private String usuarioAudita;
    @Column(name = "pbs")
    private Boolean pbs;
    @Column(name = "cama_fija")
    private Boolean camaFija;
    @Size(max = 45)
    @Column(name = "cantidad_factura")
    private String cantidadFactura;
    @Size(max = 512)
    @Column(name = "observacion_rechazo")
    private String observacionRechazo;
    @Column(name = "mae_rechazo_id")
    private Integer maeRechazoId;
    @Size(max = 8)
    @Column(name = "mae_rechazo_codigo")
    private String maeRechazoCodigo;
    @Size(max = 128)
    @Column(name = "mae_rechazo_valor")
    private String maeRechazoValor;
    @Size(max = 512)
    @Column(name = "observacion")
    private String observacion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsAuUrgencias> cmRipsAuUrgenciasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaEstados> cmRipsCargaEstadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaApProcedimientos> cmRipsCargaApProcedimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsAdServiciosAgrupados> cmRipsAdServiciosAgrupadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaUsUsuarios> cmRipsCargaUsUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsApProcedimientos> cmRipsApProcedimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsSucesos> cmRipsSucesosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaAnexos> cmRipsCargaAnexosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaAdServiciosAgrupados> cmRipsCargaAdServiciosAgrupadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsEstructuraErrores> cmRipsEstructuraErroresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaAtOtrosServicios> cmRipsCargaAtOtrosServiciosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCtControl> cmRipsCtControlList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaAhHospitalizaciones> cmRipsCargaAhHospitalizacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsAhHospitalizaciones> cmRipsAhHospitalizacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsUsUsuarios> cmRipsUsUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaAnRecienNacidos> cmRipsCargaAnRecienNacidosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaAfFacturas> cmRipsCargaAfFacturasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsAtOtrosServicios> cmRipsAtOtrosServiciosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaAcConsultas> cmRipsCargaAcConsultasList;
    @JoinColumn(name = "cnt_contratos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntContratos cntContratosId;
    @JoinColumn(name = "gn_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes gnPrestadorSedesId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaCtControl> cmRipsCargaCtControlList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsAmMedicamentos> cmRipsAmMedicamentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsAfFacturas> cmRipsAfFacturasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaAuUrgencias> cmRipsCargaAuUrgenciasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsAnRecienNacidos> cmRipsAnRecienNacidosList;
    @OneToMany(mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmFacturas> cmFacturasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsAcConsultas> cmRipsAcConsultasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRipsCargaAmMedicamentos> cmRipsCargaAmMedicamentosList;
    @OneToMany(mappedBy = "cmRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRadicados> cmRadicadosList;

    public CmRipsCargas() {
    }

    public CmRipsCargas(Integer id) {
        this.id = id;
    }

    public CmRipsCargas(Integer id, int cntTipoContratoId, BigDecimal valorCarga, int estado, String maeReginalCodigo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.cntTipoContratoId = cntTipoContratoId;
        this.valorCarga = valorCarga;
        this.estado = estado;
        this.maeReginalCodigo = maeReginalCodigo;
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

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public int getCntTipoContratoId() {
        return cntTipoContratoId;
    }

    public void setCntTipoContratoId(int cntTipoContratoId) {
        this.cntTipoContratoId = cntTipoContratoId;
    }

    public BigDecimal getValorCarga() {
        return valorCarga;
    }

    public void setValorCarga(BigDecimal valorCarga) {
        this.valorCarga = valorCarga;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
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

    public Integer getMaeRegionalId() {
        return maeRegionalId;
    }

    public void setMaeRegionalId(Integer maeRegionalId) {
        this.maeRegionalId = maeRegionalId;
    }

    public String getMaeReginalCodigo() {
        return maeReginalCodigo;
    }

    public void setMaeReginalCodigo(String maeReginalCodigo) {
        this.maeReginalCodigo = maeReginalCodigo;
    }

    public String getMaeRegionalValor() {
        return maeRegionalValor;
    }

    public void setMaeRegionalValor(String maeRegionalValor) {
        this.maeRegionalValor = maeRegionalValor;
    }

    public Integer getMaeContratoModalidadId() {
        return maeContratoModalidadId;
    }

    public void setMaeContratoModalidadId(Integer maeContratoModalidadId) {
        this.maeContratoModalidadId = maeContratoModalidadId;
    }

    public String getMaeContratoModalidadCodigo() {
        return maeContratoModalidadCodigo;
    }

    public void setMaeContratoModalidadCodigo(String maeContratoModalidadCodigo) {
        this.maeContratoModalidadCodigo = maeContratoModalidadCodigo;
    }

    public String getMaeContratoModalidadValor() {
        return maeContratoModalidadValor;
    }

    public void setMaeContratoModalidadValor(String maeContratoModalidadValor) {
        this.maeContratoModalidadValor = maeContratoModalidadValor;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Date getFechaPrestacion() {
        return fechaPrestacion;
    }

    public void setFechaPrestacion(Date fechaPrestacion) {
        this.fechaPrestacion = fechaPrestacion;
    }

    public String getUsuarioAudita() {
        return usuarioAudita;
    }

    public void setUsuarioAudita(String usuarioAudita) {
        this.usuarioAudita = usuarioAudita;
    }

    public Boolean getPbs() {
        return pbs;
    }

    public void setPbs(Boolean pbs) {
        this.pbs = pbs;
    }

    public Boolean getCamaFija() {
        return camaFija;
    }

    public void setCamaFija(Boolean camaFija) {
        this.camaFija = camaFija;
    }

    public String getCantidadFactura() {
        return cantidadFactura;
    }

    public void setCantidadFactura(String cantidadFactura) {
        this.cantidadFactura = cantidadFactura;
    }

    public String getObservacionRechazo() {
        return observacionRechazo;
    }

    public void setObservacionRechazo(String observacionRechazo) {
        this.observacionRechazo = observacionRechazo;
    }

    public Integer getMaeRechazoId() {
        return maeRechazoId;
    }

    public void setMaeRechazoId(Integer maeRechazoId) {
        this.maeRechazoId = maeRechazoId;
    }

    public String getMaeRechazoCodigo() {
        return maeRechazoCodigo;
    }

    public void setMaeRechazoCodigo(String maeRechazoCodigo) {
        this.maeRechazoCodigo = maeRechazoCodigo;
    }

    public String getMaeRechazoValor() {
        return maeRechazoValor;
    }

    public void setMaeRechazoValor(String maeRechazoValor) {
        this.maeRechazoValor = maeRechazoValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    @XmlTransient
    public List<CmRipsAuUrgencias> getCmRipsAuUrgenciasList() {
        return cmRipsAuUrgenciasList;
    }

    public void setCmRipsAuUrgenciasList(List<CmRipsAuUrgencias> cmRipsAuUrgenciasList) {
        this.cmRipsAuUrgenciasList = cmRipsAuUrgenciasList;
    }

    @XmlTransient
    public List<CmRipsCargaEstados> getCmRipsCargaEstadosList() {
        return cmRipsCargaEstadosList;
    }

    public void setCmRipsCargaEstadosList(List<CmRipsCargaEstados> cmRipsCargaEstadosList) {
        this.cmRipsCargaEstadosList = cmRipsCargaEstadosList;
    }

    @XmlTransient
    public List<CmRipsCargaApProcedimientos> getCmRipsCargaApProcedimientosList() {
        return cmRipsCargaApProcedimientosList;
    }

    public void setCmRipsCargaApProcedimientosList(List<CmRipsCargaApProcedimientos> cmRipsCargaApProcedimientosList) {
        this.cmRipsCargaApProcedimientosList = cmRipsCargaApProcedimientosList;
    }

    @XmlTransient
    public List<CmRipsAdServiciosAgrupados> getCmRipsAdServiciosAgrupadosList() {
        return cmRipsAdServiciosAgrupadosList;
    }

    public void setCmRipsAdServiciosAgrupadosList(List<CmRipsAdServiciosAgrupados> cmRipsAdServiciosAgrupadosList) {
        this.cmRipsAdServiciosAgrupadosList = cmRipsAdServiciosAgrupadosList;
    }

    @XmlTransient
    public List<CmRipsCargaUsUsuarios> getCmRipsCargaUsUsuariosList() {
        return cmRipsCargaUsUsuariosList;
    }

    public void setCmRipsCargaUsUsuariosList(List<CmRipsCargaUsUsuarios> cmRipsCargaUsUsuariosList) {
        this.cmRipsCargaUsUsuariosList = cmRipsCargaUsUsuariosList;
    }

    @XmlTransient
    public List<CmRipsApProcedimientos> getCmRipsApProcedimientosList() {
        return cmRipsApProcedimientosList;
    }

    public void setCmRipsApProcedimientosList(List<CmRipsApProcedimientos> cmRipsApProcedimientosList) {
        this.cmRipsApProcedimientosList = cmRipsApProcedimientosList;
    }

    @XmlTransient
    public List<CmRipsSucesos> getCmRipsSucesosList() {
        return cmRipsSucesosList;
    }

    public void setCmRipsSucesosList(List<CmRipsSucesos> cmRipsSucesosList) {
        this.cmRipsSucesosList = cmRipsSucesosList;
    }

    @XmlTransient
    public List<CmRipsCargaAnexos> getCmRipsCargaAnexosList() {
        return cmRipsCargaAnexosList;
    }

    public void setCmRipsCargaAnexosList(List<CmRipsCargaAnexos> cmRipsCargaAnexosList) {
        this.cmRipsCargaAnexosList = cmRipsCargaAnexosList;
    }

    @XmlTransient
    public List<CmRipsCargaAdServiciosAgrupados> getCmRipsCargaAdServiciosAgrupadosList() {
        return cmRipsCargaAdServiciosAgrupadosList;
    }

    public void setCmRipsCargaAdServiciosAgrupadosList(List<CmRipsCargaAdServiciosAgrupados> cmRipsCargaAdServiciosAgrupadosList) {
        this.cmRipsCargaAdServiciosAgrupadosList = cmRipsCargaAdServiciosAgrupadosList;
    }

    @XmlTransient
    public List<CmRipsEstructuraErrores> getCmRipsEstructuraErroresList() {
        return cmRipsEstructuraErroresList;
    }

    public void setCmRipsEstructuraErroresList(List<CmRipsEstructuraErrores> cmRipsEstructuraErroresList) {
        this.cmRipsEstructuraErroresList = cmRipsEstructuraErroresList;
    }

    @XmlTransient
    public List<CmRipsCargaAtOtrosServicios> getCmRipsCargaAtOtrosServiciosList() {
        return cmRipsCargaAtOtrosServiciosList;
    }

    public void setCmRipsCargaAtOtrosServiciosList(List<CmRipsCargaAtOtrosServicios> cmRipsCargaAtOtrosServiciosList) {
        this.cmRipsCargaAtOtrosServiciosList = cmRipsCargaAtOtrosServiciosList;
    }

    @XmlTransient
    public List<CmRipsCtControl> getCmRipsCtControlList() {
        return cmRipsCtControlList;
    }

    public void setCmRipsCtControlList(List<CmRipsCtControl> cmRipsCtControlList) {
        this.cmRipsCtControlList = cmRipsCtControlList;
    }

    @XmlTransient
    public List<CmRipsCargaAhHospitalizaciones> getCmRipsCargaAhHospitalizacionesList() {
        return cmRipsCargaAhHospitalizacionesList;
    }

    public void setCmRipsCargaAhHospitalizacionesList(List<CmRipsCargaAhHospitalizaciones> cmRipsCargaAhHospitalizacionesList) {
        this.cmRipsCargaAhHospitalizacionesList = cmRipsCargaAhHospitalizacionesList;
    }

    @XmlTransient
    public List<CmRipsAhHospitalizaciones> getCmRipsAhHospitalizacionesList() {
        return cmRipsAhHospitalizacionesList;
    }

    public void setCmRipsAhHospitalizacionesList(List<CmRipsAhHospitalizaciones> cmRipsAhHospitalizacionesList) {
        this.cmRipsAhHospitalizacionesList = cmRipsAhHospitalizacionesList;
    }

    @XmlTransient
    public List<CmRipsUsUsuarios> getCmRipsUsUsuariosList() {
        return cmRipsUsUsuariosList;
    }

    public void setCmRipsUsUsuariosList(List<CmRipsUsUsuarios> cmRipsUsUsuariosList) {
        this.cmRipsUsUsuariosList = cmRipsUsUsuariosList;
    }

    @XmlTransient
    public List<CmRipsCargaAnRecienNacidos> getCmRipsCargaAnRecienNacidosList() {
        return cmRipsCargaAnRecienNacidosList;
    }

    public void setCmRipsCargaAnRecienNacidosList(List<CmRipsCargaAnRecienNacidos> cmRipsCargaAnRecienNacidosList) {
        this.cmRipsCargaAnRecienNacidosList = cmRipsCargaAnRecienNacidosList;
    }

    @XmlTransient
    public List<CmRipsCargaAfFacturas> getCmRipsCargaAfFacturasList() {
        return cmRipsCargaAfFacturasList;
    }

    public void setCmRipsCargaAfFacturasList(List<CmRipsCargaAfFacturas> cmRipsCargaAfFacturasList) {
        this.cmRipsCargaAfFacturasList = cmRipsCargaAfFacturasList;
    }

    @XmlTransient
    public List<CmRipsAtOtrosServicios> getCmRipsAtOtrosServiciosList() {
        return cmRipsAtOtrosServiciosList;
    }

    public void setCmRipsAtOtrosServiciosList(List<CmRipsAtOtrosServicios> cmRipsAtOtrosServiciosList) {
        this.cmRipsAtOtrosServiciosList = cmRipsAtOtrosServiciosList;
    }

    @XmlTransient
    public List<CmRipsCargaAcConsultas> getCmRipsCargaAcConsultasList() {
        return cmRipsCargaAcConsultasList;
    }

    public void setCmRipsCargaAcConsultasList(List<CmRipsCargaAcConsultas> cmRipsCargaAcConsultasList) {
        this.cmRipsCargaAcConsultasList = cmRipsCargaAcConsultasList;
    }

    public CntContratos getCntContratosId() {
        return cntContratosId;
    }

    public void setCntContratosId(CntContratos cntContratosId) {
        this.cntContratosId = cntContratosId;
    }

    public CntPrestadorSedes getGnPrestadorSedesId() {
        return gnPrestadorSedesId;
    }

    public void setGnPrestadorSedesId(CntPrestadorSedes gnPrestadorSedesId) {
        this.gnPrestadorSedesId = gnPrestadorSedesId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    @XmlTransient
    public List<CmRipsCargaCtControl> getCmRipsCargaCtControlList() {
        return cmRipsCargaCtControlList;
    }

    public void setCmRipsCargaCtControlList(List<CmRipsCargaCtControl> cmRipsCargaCtControlList) {
        this.cmRipsCargaCtControlList = cmRipsCargaCtControlList;
    }

    @XmlTransient
    public List<CmRipsAmMedicamentos> getCmRipsAmMedicamentosList() {
        return cmRipsAmMedicamentosList;
    }

    public void setCmRipsAmMedicamentosList(List<CmRipsAmMedicamentos> cmRipsAmMedicamentosList) {
        this.cmRipsAmMedicamentosList = cmRipsAmMedicamentosList;
    }

    @XmlTransient
    public List<CmRipsAfFacturas> getCmRipsAfFacturasList() {
        return cmRipsAfFacturasList;
    }

    public void setCmRipsAfFacturasList(List<CmRipsAfFacturas> cmRipsAfFacturasList) {
        this.cmRipsAfFacturasList = cmRipsAfFacturasList;
    }

    @XmlTransient
    public List<CmRipsCargaAuUrgencias> getCmRipsCargaAuUrgenciasList() {
        return cmRipsCargaAuUrgenciasList;
    }

    public void setCmRipsCargaAuUrgenciasList(List<CmRipsCargaAuUrgencias> cmRipsCargaAuUrgenciasList) {
        this.cmRipsCargaAuUrgenciasList = cmRipsCargaAuUrgenciasList;
    }

    @XmlTransient
    public List<CmRipsAnRecienNacidos> getCmRipsAnRecienNacidosList() {
        return cmRipsAnRecienNacidosList;
    }

    public void setCmRipsAnRecienNacidosList(List<CmRipsAnRecienNacidos> cmRipsAnRecienNacidosList) {
        this.cmRipsAnRecienNacidosList = cmRipsAnRecienNacidosList;
    }

    @XmlTransient
    public List<CmFacturas> getCmFacturasList() {
        return cmFacturasList;
    }

    public void setCmFacturasList(List<CmFacturas> cmFacturasList) {
        this.cmFacturasList = cmFacturasList;
    }

    @XmlTransient
    public List<CmRipsAcConsultas> getCmRipsAcConsultasList() {
        return cmRipsAcConsultasList;
    }

    public void setCmRipsAcConsultasList(List<CmRipsAcConsultas> cmRipsAcConsultasList) {
        this.cmRipsAcConsultasList = cmRipsAcConsultasList;
    }

    @XmlTransient
    public List<CmRipsCargaAmMedicamentos> getCmRipsCargaAmMedicamentosList() {
        return cmRipsCargaAmMedicamentosList;
    }

    public void setCmRipsCargaAmMedicamentosList(List<CmRipsCargaAmMedicamentos> cmRipsCargaAmMedicamentosList) {
        this.cmRipsCargaAmMedicamentosList = cmRipsCargaAmMedicamentosList;
    }

    @XmlTransient
    public List<CmRadicados> getCmRadicadosList() {
        return cmRadicadosList;
    }

    public void setCmRadicadosList(List<CmRadicados> cmRadicadosList) {
        this.cmRadicadosList = cmRadicadosList;
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
        if (!(object instanceof CmRipsCargas)) {
            return false;
        }
        CmRipsCargas other = (CmRipsCargas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsCargas[ id=" + id + " ]";
    }
    
}
