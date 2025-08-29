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
@Table(name = "mp_direccionamiento_entregados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpDireccionamientoEntregados.findAll", query = "SELECT m FROM MpDireccionamientoEntregados m"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findById", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.id = :id"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByIdTransaccion", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByIdReporteEntrega", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.idReporteEntrega = :idReporteEntrega"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByEstRepEntrega", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.estRepEntrega = :estRepEntrega"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByCantidadEntrega", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.cantidadEntrega = :cantidadEntrega"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByNumeroEntrega", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.numeroEntrega = :numeroEntrega"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByEntregaTotal", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.entregaTotal = :entregaTotal"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByCausaNoEntrega", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.causaNoEntrega = :causaNoEntrega"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByEstadoEntrega", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.estadoEntrega = :estadoEntrega"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByFechaEntrega", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByFechaAnulacion", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.fechaAnulacion = :fechaAnulacion"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByJustificacionDireccionamiento", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.justificacionDireccionamiento = :justificacionDireccionamiento"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByFechaDireccionamientoAutomatico", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.fechaDireccionamientoAutomatico = :fechaDireccionamientoAutomatico"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByFechaMaximaDireccionamiento", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.fechaMaximaDireccionamiento = :fechaMaximaDireccionamiento"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByValorReportado", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.valorReportado = :valorReportado"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByValorTotal", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.valorTotal = :valorTotal"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByFechaReporteFactura", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.fechaReporteFactura = :fechaReporteFactura"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByTipoTecEntregado", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.tipoTecEntregado = :tipoTecEntregado"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByCodTecEntregado", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.codTecEntregado = :codTecEntregado"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByDescTecEntregado", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.descTecEntregado = :descTecEntregado"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByNumeroLote", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.numeroLote = :numeroLote"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByEstadoSuministro", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.estadoSuministro = :estadoSuministro"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByEntregaCompleta", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.entregaCompleta = :entregaCompleta"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByCierreSuministro", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.cierreSuministro = :cierreSuministro"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByAnulado", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.anulado = :anulado"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByTipoTecnologia", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByItemId", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.itemId = :itemId"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByCufe", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.cufe = :cufe"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByFechaConsumo", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.fechaConsumo = :fechaConsumo"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByUsuarioCrea", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByTerminalCrea", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpDireccionamientoEntregados.findByFechaHoraCrea", query = "SELECT m FROM MpDireccionamientoEntregados m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpDireccionamientoEntregados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_transaccion")
    private int idTransaccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_reporte_entrega")
    private int idReporteEntrega;
    @Column(name = "est_rep_entrega")
    private Integer estRepEntrega;
    @Column(name = "cantidad_entrega")
    private Integer cantidadEntrega;
    @Column(name = "numero_entrega")
    private Integer numeroEntrega;
    @Column(name = "entrega_total")
    private Integer entregaTotal;
    @Column(name = "causa_no_entrega")
    private Integer causaNoEntrega;
    @Column(name = "estado_entrega")
    private Integer estadoEntrega;
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    @Column(name = "fecha_anulacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulacion;
    @Size(max = 2048)
    @Column(name = "justificacion_direccionamiento")
    private String justificacionDireccionamiento;
    @Column(name = "fecha_direccionamiento_automatico")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDireccionamientoAutomatico;
    @Column(name = "fecha_maxima_direccionamiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMaximaDireccionamiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_reportado")
    private BigDecimal valorReportado;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "fecha_reporte_factura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporteFactura;
    @Column(name = "tipo_tec_entregado")
    private Integer tipoTecEntregado;
    @Size(max = 32)
    @Column(name = "cod_tec_entregado")
    private String codTecEntregado;
    @Size(max = 512)
    @Column(name = "desc_tec_entregado")
    private String descTecEntregado;
    @Size(max = 32)
    @Column(name = "numero_lote")
    private String numeroLote;
    @Column(name = "estado_suministro")
    private Integer estadoSuministro;
    @Column(name = "entrega_completa")
    private Integer entregaCompleta;
    @Column(name = "cierre_suministro")
    private Boolean cierreSuministro;
    @Column(name = "anulado")
    private Boolean anulado;
    @Column(name = "tipo_tecnologia")
    private Integer tipoTecnologia;
    @Column(name = "item_id")
    private Integer itemId;
    @Size(max = 256)
    @Column(name = "cufe")
    private String cufe;
    @Column(name = "fecha_consumo")
    @Temporal(TemporalType.DATE)
    private Date fechaConsumo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpDireccionamientoEntregadosId", fetch = FetchType.LAZY)
    private List<MpEntregaSuministros> mpEntregaSuministrosList;
    @JoinColumn(name = "mp_direccionamiento_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpDireccionamientos mpDireccionamientoId;
    @JoinColumn(name = "mp_prescripcion_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionId;
    @JoinColumn(name = "mp_prescripicion_insumos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionInsumos mpPrescripicionInsumosId;
    @JoinColumn(name = "mp_prescripicion_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionMedicamentos mpPrescripicionMedicamentosId;
    @JoinColumn(name = "mp_prescripicion_tecnologias_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionTecnologias mpPrescripicionTecnologiasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpDireccionamientoEntregadosId", fetch = FetchType.LAZY)
    private List<MpEntregaFacturas> mpEntregaFacturasList;

    public MpDireccionamientoEntregados() {
    }

    public MpDireccionamientoEntregados(Integer id) {
        this.id = id;
    }

    public MpDireccionamientoEntregados(Integer id, int idTransaccion, int idReporteEntrega, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.idTransaccion = idTransaccion;
        this.idReporteEntrega = idReporteEntrega;
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

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getIdReporteEntrega() {
        return idReporteEntrega;
    }

    public void setIdReporteEntrega(int idReporteEntrega) {
        this.idReporteEntrega = idReporteEntrega;
    }

    public Integer getEstRepEntrega() {
        return estRepEntrega;
    }

    public void setEstRepEntrega(Integer estRepEntrega) {
        this.estRepEntrega = estRepEntrega;
    }

    public Integer getCantidadEntrega() {
        return cantidadEntrega;
    }

    public void setCantidadEntrega(Integer cantidadEntrega) {
        this.cantidadEntrega = cantidadEntrega;
    }

    public Integer getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(Integer numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public Integer getEntregaTotal() {
        return entregaTotal;
    }

    public void setEntregaTotal(Integer entregaTotal) {
        this.entregaTotal = entregaTotal;
    }

    public Integer getCausaNoEntrega() {
        return causaNoEntrega;
    }

    public void setCausaNoEntrega(Integer causaNoEntrega) {
        this.causaNoEntrega = causaNoEntrega;
    }

    public Integer getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(Integer estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public String getJustificacionDireccionamiento() {
        return justificacionDireccionamiento;
    }

    public void setJustificacionDireccionamiento(String justificacionDireccionamiento) {
        this.justificacionDireccionamiento = justificacionDireccionamiento;
    }

    public Date getFechaDireccionamientoAutomatico() {
        return fechaDireccionamientoAutomatico;
    }

    public void setFechaDireccionamientoAutomatico(Date fechaDireccionamientoAutomatico) {
        this.fechaDireccionamientoAutomatico = fechaDireccionamientoAutomatico;
    }

    public Date getFechaMaximaDireccionamiento() {
        return fechaMaximaDireccionamiento;
    }

    public void setFechaMaximaDireccionamiento(Date fechaMaximaDireccionamiento) {
        this.fechaMaximaDireccionamiento = fechaMaximaDireccionamiento;
    }

    public BigDecimal getValorReportado() {
        return valorReportado;
    }

    public void setValorReportado(BigDecimal valorReportado) {
        this.valorReportado = valorReportado;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getFechaReporteFactura() {
        return fechaReporteFactura;
    }

    public void setFechaReporteFactura(Date fechaReporteFactura) {
        this.fechaReporteFactura = fechaReporteFactura;
    }

    public Integer getTipoTecEntregado() {
        return tipoTecEntregado;
    }

    public void setTipoTecEntregado(Integer tipoTecEntregado) {
        this.tipoTecEntregado = tipoTecEntregado;
    }

    public String getCodTecEntregado() {
        return codTecEntregado;
    }

    public void setCodTecEntregado(String codTecEntregado) {
        this.codTecEntregado = codTecEntregado;
    }

    public String getDescTecEntregado() {
        return descTecEntregado;
    }

    public void setDescTecEntregado(String descTecEntregado) {
        this.descTecEntregado = descTecEntregado;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public Integer getEstadoSuministro() {
        return estadoSuministro;
    }

    public void setEstadoSuministro(Integer estadoSuministro) {
        this.estadoSuministro = estadoSuministro;
    }

    public Integer getEntregaCompleta() {
        return entregaCompleta;
    }

    public void setEntregaCompleta(Integer entregaCompleta) {
        this.entregaCompleta = entregaCompleta;
    }

    public Boolean getCierreSuministro() {
        return cierreSuministro;
    }

    public void setCierreSuministro(Boolean cierreSuministro) {
        this.cierreSuministro = cierreSuministro;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public Date getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(Date fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
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
    public List<MpEntregaSuministros> getMpEntregaSuministrosList() {
        return mpEntregaSuministrosList;
    }

    public void setMpEntregaSuministrosList(List<MpEntregaSuministros> mpEntregaSuministrosList) {
        this.mpEntregaSuministrosList = mpEntregaSuministrosList;
    }

    public MpDireccionamientos getMpDireccionamientoId() {
        return mpDireccionamientoId;
    }

    public void setMpDireccionamientoId(MpDireccionamientos mpDireccionamientoId) {
        this.mpDireccionamientoId = mpDireccionamientoId;
    }

    public MpPrescripciones getMpPrescripcionId() {
        return mpPrescripcionId;
    }

    public void setMpPrescripcionId(MpPrescripciones mpPrescripcionId) {
        this.mpPrescripcionId = mpPrescripcionId;
    }

    public MpPrescripcionInsumos getMpPrescripicionInsumosId() {
        return mpPrescripicionInsumosId;
    }

    public void setMpPrescripicionInsumosId(MpPrescripcionInsumos mpPrescripicionInsumosId) {
        this.mpPrescripicionInsumosId = mpPrescripicionInsumosId;
    }

    public MpPrescripcionMedicamentos getMpPrescripicionMedicamentosId() {
        return mpPrescripicionMedicamentosId;
    }

    public void setMpPrescripicionMedicamentosId(MpPrescripcionMedicamentos mpPrescripicionMedicamentosId) {
        this.mpPrescripicionMedicamentosId = mpPrescripicionMedicamentosId;
    }

    public MpPrescripcionTecnologias getMpPrescripicionTecnologiasId() {
        return mpPrescripicionTecnologiasId;
    }

    public void setMpPrescripicionTecnologiasId(MpPrescripcionTecnologias mpPrescripicionTecnologiasId) {
        this.mpPrescripicionTecnologiasId = mpPrescripicionTecnologiasId;
    }

    @XmlTransient
    public List<MpEntregaFacturas> getMpEntregaFacturasList() {
        return mpEntregaFacturasList;
    }

    public void setMpEntregaFacturasList(List<MpEntregaFacturas> mpEntregaFacturasList) {
        this.mpEntregaFacturasList = mpEntregaFacturasList;
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
        if (!(object instanceof MpDireccionamientoEntregados)) {
            return false;
        }
        MpDireccionamientoEntregados other = (MpDireccionamientoEntregados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpDireccionamientoEntregados[ id=" + id + " ]";
    }
    
}
