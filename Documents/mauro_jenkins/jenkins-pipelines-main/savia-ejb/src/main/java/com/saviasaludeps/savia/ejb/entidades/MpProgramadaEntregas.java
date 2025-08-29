/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "mp_programada_entregas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpProgramadaEntregas.findAll", query = "SELECT m FROM MpProgramadaEntregas m"),
    @NamedQuery(name = "MpProgramadaEntregas.findById", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.id = :id"),
    @NamedQuery(name = "MpProgramadaEntregas.findByIdReporteEntrega", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.idReporteEntrega = :idReporteEntrega"),
    @NamedQuery(name = "MpProgramadaEntregas.findByCantidad", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "MpProgramadaEntregas.findByNumeroEntrega", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.numeroEntrega = :numeroEntrega"),
    @NamedQuery(name = "MpProgramadaEntregas.findByEntregaTotal", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.entregaTotal = :entregaTotal"),
    @NamedQuery(name = "MpProgramadaEntregas.findByCausaNoEntrega", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.causaNoEntrega = :causaNoEntrega"),
    @NamedQuery(name = "MpProgramadaEntregas.findByEstado", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpProgramadaEntregas.findByFechaEntrega", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "MpProgramadaEntregas.findByFechaAnulacion", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.fechaAnulacion = :fechaAnulacion"),
    @NamedQuery(name = "MpProgramadaEntregas.findByCopago", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.copago = :copago"),
    @NamedQuery(name = "MpProgramadaEntregas.findByCuotaModeradora", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.cuotaModeradora = :cuotaModeradora"),
    @NamedQuery(name = "MpProgramadaEntregas.findByCodFactCufe", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.codFactCufe = :codFactCufe"),
    @NamedQuery(name = "MpProgramadaEntregas.findByValorReportado", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.valorReportado = :valorReportado"),
    @NamedQuery(name = "MpProgramadaEntregas.findByValorTotal", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.valorTotal = :valorTotal"),
    @NamedQuery(name = "MpProgramadaEntregas.findByFechaCierreFacturaEps", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.fechaCierreFacturaEps = :fechaCierreFacturaEps"),
    @NamedQuery(name = "MpProgramadaEntregas.findByFechaCierreCiclo", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.fechaCierreCiclo = :fechaCierreCiclo"),
    @NamedQuery(name = "MpProgramadaEntregas.findByFechaReporteFactura", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.fechaReporteFactura = :fechaReporteFactura"),
    @NamedQuery(name = "MpProgramadaEntregas.findByUsuarioCrea", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpProgramadaEntregas.findByTerminalCrea", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpProgramadaEntregas.findByFechaHoraCrea", query = "SELECT m FROM MpProgramadaEntregas m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpProgramadaEntregas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "id_reporte_entrega")
    private String idReporteEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Column(name = "numero_entrega")
    private Integer numeroEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entrega_total")
    private boolean entregaTotal;
    @Size(max = 2048)
    @Column(name = "causa_no_entrega")
    private String causaNoEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    @Column(name = "fecha_anulacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulacion;
    @Column(name = "copago")
    private BigDecimal copago;
    @Column(name = "cuota_moderadora")
    private BigDecimal cuotaModeradora;
    @Size(max = 192)
    @Column(name = "cod_fact_cufe")
    private String codFactCufe;
    @Column(name = "valor_reportado")
    private BigDecimal valorReportado;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "fecha_cierre_factura_eps")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierreFacturaEps;
    @Column(name = "fecha_cierre_ciclo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierreCiclo;
    @Column(name = "fecha_reporte_factura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporteFactura;
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
    @JoinColumn(name = "mp_prescripcion_insumos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionInsumos mpPrescripcionInsumosId;
    @JoinColumn(name = "mp_prescripcion_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionMedicamentos mpPrescripcionMedicamentosId;
    @JoinColumn(name = "mp_prescripcion_tecnologias_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionTecnologias mpPrescripcionTecnologiasId;
    @JoinColumn(name = "mp_prescripciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionesId;
    @JoinColumn(name = "mp_prescripcion_programadas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionProgramadas mpPrescripcionProgramadasId;

    public MpProgramadaEntregas() {
    }

    public MpProgramadaEntregas(Integer id) {
        this.id = id;
    }

    public MpProgramadaEntregas(Integer id, String idReporteEntrega, boolean entregaTotal, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.idReporteEntrega = idReporteEntrega;
        this.entregaTotal = entregaTotal;
        this.estado = estado;
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

    public String getIdReporteEntrega() {
        return idReporteEntrega;
    }

    public void setIdReporteEntrega(String idReporteEntrega) {
        this.idReporteEntrega = idReporteEntrega;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(Integer numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public boolean getEntregaTotal() {
        return entregaTotal;
    }

    public void setEntregaTotal(boolean entregaTotal) {
        this.entregaTotal = entregaTotal;
    }

    public String getCausaNoEntrega() {
        return causaNoEntrega;
    }

    public void setCausaNoEntrega(String causaNoEntrega) {
        this.causaNoEntrega = causaNoEntrega;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public BigDecimal getCopago() {
        return copago;
    }

    public void setCopago(BigDecimal copago) {
        this.copago = copago;
    }

    public BigDecimal getCuotaModeradora() {
        return cuotaModeradora;
    }

    public void setCuotaModeradora(BigDecimal cuotaModeradora) {
        this.cuotaModeradora = cuotaModeradora;
    }

    public String getCodFactCufe() {
        return codFactCufe;
    }

    public void setCodFactCufe(String codFactCufe) {
        this.codFactCufe = codFactCufe;
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

    public Date getFechaCierreFacturaEps() {
        return fechaCierreFacturaEps;
    }

    public void setFechaCierreFacturaEps(Date fechaCierreFacturaEps) {
        this.fechaCierreFacturaEps = fechaCierreFacturaEps;
    }

    public Date getFechaCierreCiclo() {
        return fechaCierreCiclo;
    }

    public void setFechaCierreCiclo(Date fechaCierreCiclo) {
        this.fechaCierreCiclo = fechaCierreCiclo;
    }

    public Date getFechaReporteFactura() {
        return fechaReporteFactura;
    }

    public void setFechaReporteFactura(Date fechaReporteFactura) {
        this.fechaReporteFactura = fechaReporteFactura;
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

    public MpPrescripcionInsumos getMpPrescripcionInsumosId() {
        return mpPrescripcionInsumosId;
    }

    public void setMpPrescripcionInsumosId(MpPrescripcionInsumos mpPrescripcionInsumosId) {
        this.mpPrescripcionInsumosId = mpPrescripcionInsumosId;
    }

    public MpPrescripcionMedicamentos getMpPrescripcionMedicamentosId() {
        return mpPrescripcionMedicamentosId;
    }

    public void setMpPrescripcionMedicamentosId(MpPrescripcionMedicamentos mpPrescripcionMedicamentosId) {
        this.mpPrescripcionMedicamentosId = mpPrescripcionMedicamentosId;
    }

    public MpPrescripcionTecnologias getMpPrescripcionTecnologiasId() {
        return mpPrescripcionTecnologiasId;
    }

    public void setMpPrescripcionTecnologiasId(MpPrescripcionTecnologias mpPrescripcionTecnologiasId) {
        this.mpPrescripcionTecnologiasId = mpPrescripcionTecnologiasId;
    }

    public MpPrescripciones getMpPrescripcionesId() {
        return mpPrescripcionesId;
    }

    public void setMpPrescripcionesId(MpPrescripciones mpPrescripcionesId) {
        this.mpPrescripcionesId = mpPrescripcionesId;
    }

    public MpPrescripcionProgramadas getMpPrescripcionProgramadasId() {
        return mpPrescripcionProgramadasId;
    }

    public void setMpPrescripcionProgramadasId(MpPrescripcionProgramadas mpPrescripcionProgramadasId) {
        this.mpPrescripcionProgramadasId = mpPrescripcionProgramadasId;
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
        if (!(object instanceof MpProgramadaEntregas)) {
            return false;
        }
        MpProgramadaEntregas other = (MpProgramadaEntregas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpProgramadaEntregas[ id=" + id + " ]";
    }
    
}
