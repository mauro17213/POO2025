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
@Table(name = "mp_entrega_facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpEntregaFacturas.findAll", query = "SELECT m FROM MpEntregaFacturas m"),
    @NamedQuery(name = "MpEntregaFacturas.findById", query = "SELECT m FROM MpEntregaFacturas m WHERE m.id = :id"),
    @NamedQuery(name = "MpEntregaFacturas.findByEstado", query = "SELECT m FROM MpEntregaFacturas m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpEntregaFacturas.findByNit", query = "SELECT m FROM MpEntregaFacturas m WHERE m.nit = :nit"),
    @NamedQuery(name = "MpEntregaFacturas.findByNoFactura", query = "SELECT m FROM MpEntregaFacturas m WHERE m.noFactura = :noFactura"),
    @NamedQuery(name = "MpEntregaFacturas.findByCufe", query = "SELECT m FROM MpEntregaFacturas m WHERE m.cufe = :cufe"),
    @NamedQuery(name = "MpEntregaFacturas.findByFechaFacturacion", query = "SELECT m FROM MpEntregaFacturas m WHERE m.fechaFacturacion = :fechaFacturacion"),
    @NamedQuery(name = "MpEntregaFacturas.findByMpEntregaFacturascol", query = "SELECT m FROM MpEntregaFacturas m WHERE m.mpEntregaFacturascol = :mpEntregaFacturascol"),
    @NamedQuery(name = "MpEntregaFacturas.findByFechaConsumo", query = "SELECT m FROM MpEntregaFacturas m WHERE m.fechaConsumo = :fechaConsumo"),
    @NamedQuery(name = "MpEntregaFacturas.findByIdFacturacion", query = "SELECT m FROM MpEntregaFacturas m WHERE m.idFacturacion = :idFacturacion"),
    @NamedQuery(name = "MpEntregaFacturas.findByIdTransaccion", query = "SELECT m FROM MpEntregaFacturas m WHERE m.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "MpEntregaFacturas.findByIdCicloFacturacion", query = "SELECT m FROM MpEntregaFacturas m WHERE m.idCicloFacturacion = :idCicloFacturacion"),
    @NamedQuery(name = "MpEntregaFacturas.findByNoidEPS", query = "SELECT m FROM MpEntregaFacturas m WHERE m.noidEPS = :noidEPS"),
    @NamedQuery(name = "MpEntregaFacturas.findByCodEPS", query = "SELECT m FROM MpEntregaFacturas m WHERE m.codEPS = :codEPS"),
    @NamedQuery(name = "MpEntregaFacturas.findByCantUnitaria", query = "SELECT m FROM MpEntregaFacturas m WHERE m.cantUnitaria = :cantUnitaria"),
    @NamedQuery(name = "MpEntregaFacturas.findByValorUnitario", query = "SELECT m FROM MpEntregaFacturas m WHERE m.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "MpEntregaFacturas.findByCopago", query = "SELECT m FROM MpEntregaFacturas m WHERE m.copago = :copago"),
    @NamedQuery(name = "MpEntregaFacturas.findByCuotaModeradora", query = "SELECT m FROM MpEntregaFacturas m WHERE m.cuotaModeradora = :cuotaModeradora"),
    @NamedQuery(name = "MpEntregaFacturas.findByValorTotal", query = "SELECT m FROM MpEntregaFacturas m WHERE m.valorTotal = :valorTotal"),
    @NamedQuery(name = "MpEntregaFacturas.findByNoSubEntrega", query = "SELECT m FROM MpEntregaFacturas m WHERE m.noSubEntrega = :noSubEntrega"),
    @NamedQuery(name = "MpEntregaFacturas.findByFechaAnulacion", query = "SELECT m FROM MpEntregaFacturas m WHERE m.fechaAnulacion = :fechaAnulacion"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmaTipoComparador", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmaTipoComparador = :confirmaTipoComparador"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmaCodigoComparador", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmaCodigoComparador = :confirmaCodigoComparador"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmaValorTotalComparador", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmaValorTotalComparador = :confirmaValorTotalComparador"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmaUnidadAdminstrativo", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmaUnidadAdminstrativo = :confirmaUnidadAdminstrativo"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmaUnidadHomologado", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmaUnidadHomologado = :confirmaUnidadHomologado"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmaValorMinimaConcentracion", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmaValorMinimaConcentracion = :confirmaValorMinimaConcentracion"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmaAfectaPresupuesto", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmaAfectaPresupuesto = :confirmaAfectaPresupuesto"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmaFecha", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmaFecha = :confirmaFecha"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmaCierreCiclo", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmaCierreCiclo = :confirmaCierreCiclo"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmaFhCierreCiclo", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmaFhCierreCiclo = :confirmaFhCierreCiclo"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmaFhCierreFacturaEps", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmaFhCierreFacturaEps = :confirmaFhCierreFacturaEps"),
    @NamedQuery(name = "MpEntregaFacturas.findByFechaConsumoDato", query = "SELECT m FROM MpEntregaFacturas m WHERE m.fechaConsumoDato = :fechaConsumoDato"),
    @NamedQuery(name = "MpEntregaFacturas.findByEstadoMipres", query = "SELECT m FROM MpEntregaFacturas m WHERE m.estadoMipres = :estadoMipres"),
    @NamedQuery(name = "MpEntregaFacturas.findByRespuestaDatosFactura", query = "SELECT m FROM MpEntregaFacturas m WHERE m.respuestaDatosFactura = :respuestaDatosFactura"),
    @NamedQuery(name = "MpEntregaFacturas.findByCodigoFacturado", query = "SELECT m FROM MpEntregaFacturas m WHERE m.codigoFacturado = :codigoFacturado"),
    @NamedQuery(name = "MpEntregaFacturas.findByUsuarioCrea", query = "SELECT m FROM MpEntregaFacturas m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpEntregaFacturas.findByTerminalCrea", query = "SELECT m FROM MpEntregaFacturas m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpEntregaFacturas.findByFechaHoraCrea", query = "SELECT m FROM MpEntregaFacturas m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmarUsuarioCrea", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmarUsuarioCrea = :confirmarUsuarioCrea"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmarTerminalCrea", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmarTerminalCrea = :confirmarTerminalCrea"),
    @NamedQuery(name = "MpEntregaFacturas.findByConfirmarFechaHoraCrea", query = "SELECT m FROM MpEntregaFacturas m WHERE m.confirmarFechaHoraCrea = :confirmarFechaHoraCrea")})
public class MpEntregaFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "estado")
    private Short estado;
    @Size(max = 32)
    @Column(name = "nit")
    private String nit;
    @Size(max = 32)
    @Column(name = "no_factura")
    private String noFactura;
    @Size(max = 128)
    @Column(name = "cufe")
    private String cufe;
    @Column(name = "fecha_facturacion")
    @Temporal(TemporalType.DATE)
    private Date fechaFacturacion;
    @Size(max = 45)
    @Column(name = "mp_entrega_facturascol")
    private String mpEntregaFacturascol;
    @Column(name = "fecha_consumo")
    @Temporal(TemporalType.DATE)
    private Date fechaConsumo;
    @Size(max = 32)
    @Column(name = "id_facturacion")
    private String idFacturacion;
    @Size(max = 32)
    @Column(name = "id_transaccion")
    private String idTransaccion;
    @Size(max = 32)
    @Column(name = "id_ciclo_facturacion")
    private String idCicloFacturacion;
    @Size(max = 32)
    @Column(name = "no_id_EPS")
    private String noidEPS;
    @Size(max = 8)
    @Column(name = "cod_EPS")
    private String codEPS;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cant_unitaria")
    private BigDecimal cantUnitaria;
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;
    @Column(name = "copago")
    private BigDecimal copago;
    @Column(name = "cuota_moderadora")
    private BigDecimal cuotaModeradora;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "no_sub_entrega")
    private Short noSubEntrega;
    @Column(name = "fecha_anulacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAnulacion;
    @Column(name = "confirma_tipo_comparador")
    private Short confirmaTipoComparador;
    @Column(name = "confirma_codigo_comparador")
    private Short confirmaCodigoComparador;
    @Column(name = "confirma_valor_total_comparador")
    private BigDecimal confirmaValorTotalComparador;
    @Size(max = 8)
    @Column(name = "confirma_unidad_adminstrativo")
    private String confirmaUnidadAdminstrativo;
    @Size(max = 8)
    @Column(name = "confirma_unidad_homologado")
    private String confirmaUnidadHomologado;
    @Column(name = "confirma_valor_minima_concentracion")
    private BigDecimal confirmaValorMinimaConcentracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "confirma_afecta_presupuesto")
    private boolean confirmaAfectaPresupuesto;
    @Column(name = "confirma_fecha")
    @Temporal(TemporalType.DATE)
    private Date confirmaFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "confirma_cierre_ciclo")
    private boolean confirmaCierreCiclo;
    @Column(name = "confirma_fh_cierre_ciclo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmaFhCierreCiclo;
    @Column(name = "confirma_fh_cierre_factura_eps")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmaFhCierreFacturaEps;
    @Column(name = "fecha_consumo_dato")
    @Temporal(TemporalType.DATE)
    private Date fechaConsumoDato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_mipres")
    private int estadoMipres;
    @Size(max = 512)
    @Column(name = "respuesta_datos_factura")
    private String respuestaDatosFactura;
    @Size(max = 128)
    @Column(name = "codigo_facturado")
    private String codigoFacturado;
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
    @Temporal(TemporalType.DATE)
    private Date fechaHoraCrea;
    @Size(max = 128)
    @Column(name = "confirmar_usuario_crea")
    private String confirmarUsuarioCrea;
    @Size(max = 16)
    @Column(name = "confirmar_terminal_crea")
    private String confirmarTerminalCrea;
    @Column(name = "confirmar_fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmarFechaHoraCrea;
    @JoinColumn(name = "mp_direccionamiento_entregados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpDireccionamientoEntregados mpDireccionamientoEntregadosId;
    @JoinColumn(name = "mp_entrega_suministros_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpEntregaSuministros mpEntregaSuministrosId;

    public MpEntregaFacturas() {
    }

    public MpEntregaFacturas(Integer id) {
        this.id = id;
    }

    public MpEntregaFacturas(Integer id, boolean confirmaAfectaPresupuesto, boolean confirmaCierreCiclo, int estadoMipres, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.confirmaAfectaPresupuesto = confirmaAfectaPresupuesto;
        this.confirmaCierreCiclo = confirmaCierreCiclo;
        this.estadoMipres = estadoMipres;
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

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(String noFactura) {
        this.noFactura = noFactura;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public Date getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(Date fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public String getMpEntregaFacturascol() {
        return mpEntregaFacturascol;
    }

    public void setMpEntregaFacturascol(String mpEntregaFacturascol) {
        this.mpEntregaFacturascol = mpEntregaFacturascol;
    }

    public Date getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(Date fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    public String getIdFacturacion() {
        return idFacturacion;
    }

    public void setIdFacturacion(String idFacturacion) {
        this.idFacturacion = idFacturacion;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getIdCicloFacturacion() {
        return idCicloFacturacion;
    }

    public void setIdCicloFacturacion(String idCicloFacturacion) {
        this.idCicloFacturacion = idCicloFacturacion;
    }

    public String getNoidEPS() {
        return noidEPS;
    }

    public void setNoidEPS(String noidEPS) {
        this.noidEPS = noidEPS;
    }

    public String getCodEPS() {
        return codEPS;
    }

    public void setCodEPS(String codEPS) {
        this.codEPS = codEPS;
    }

    public BigDecimal getCantUnitaria() {
        return cantUnitaria;
    }

    public void setCantUnitaria(BigDecimal cantUnitaria) {
        this.cantUnitaria = cantUnitaria;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Short getNoSubEntrega() {
        return noSubEntrega;
    }

    public void setNoSubEntrega(Short noSubEntrega) {
        this.noSubEntrega = noSubEntrega;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public Short getConfirmaTipoComparador() {
        return confirmaTipoComparador;
    }

    public void setConfirmaTipoComparador(Short confirmaTipoComparador) {
        this.confirmaTipoComparador = confirmaTipoComparador;
    }

    public Short getConfirmaCodigoComparador() {
        return confirmaCodigoComparador;
    }

    public void setConfirmaCodigoComparador(Short confirmaCodigoComparador) {
        this.confirmaCodigoComparador = confirmaCodigoComparador;
    }

    public BigDecimal getConfirmaValorTotalComparador() {
        return confirmaValorTotalComparador;
    }

    public void setConfirmaValorTotalComparador(BigDecimal confirmaValorTotalComparador) {
        this.confirmaValorTotalComparador = confirmaValorTotalComparador;
    }

    public String getConfirmaUnidadAdminstrativo() {
        return confirmaUnidadAdminstrativo;
    }

    public void setConfirmaUnidadAdminstrativo(String confirmaUnidadAdminstrativo) {
        this.confirmaUnidadAdminstrativo = confirmaUnidadAdminstrativo;
    }

    public String getConfirmaUnidadHomologado() {
        return confirmaUnidadHomologado;
    }

    public void setConfirmaUnidadHomologado(String confirmaUnidadHomologado) {
        this.confirmaUnidadHomologado = confirmaUnidadHomologado;
    }

    public BigDecimal getConfirmaValorMinimaConcentracion() {
        return confirmaValorMinimaConcentracion;
    }

    public void setConfirmaValorMinimaConcentracion(BigDecimal confirmaValorMinimaConcentracion) {
        this.confirmaValorMinimaConcentracion = confirmaValorMinimaConcentracion;
    }

    public boolean getConfirmaAfectaPresupuesto() {
        return confirmaAfectaPresupuesto;
    }

    public void setConfirmaAfectaPresupuesto(boolean confirmaAfectaPresupuesto) {
        this.confirmaAfectaPresupuesto = confirmaAfectaPresupuesto;
    }

    public Date getConfirmaFecha() {
        return confirmaFecha;
    }

    public void setConfirmaFecha(Date confirmaFecha) {
        this.confirmaFecha = confirmaFecha;
    }

    public boolean getConfirmaCierreCiclo() {
        return confirmaCierreCiclo;
    }

    public void setConfirmaCierreCiclo(boolean confirmaCierreCiclo) {
        this.confirmaCierreCiclo = confirmaCierreCiclo;
    }

    public Date getConfirmaFhCierreCiclo() {
        return confirmaFhCierreCiclo;
    }

    public void setConfirmaFhCierreCiclo(Date confirmaFhCierreCiclo) {
        this.confirmaFhCierreCiclo = confirmaFhCierreCiclo;
    }

    public Date getConfirmaFhCierreFacturaEps() {
        return confirmaFhCierreFacturaEps;
    }

    public void setConfirmaFhCierreFacturaEps(Date confirmaFhCierreFacturaEps) {
        this.confirmaFhCierreFacturaEps = confirmaFhCierreFacturaEps;
    }

    public Date getFechaConsumoDato() {
        return fechaConsumoDato;
    }

    public void setFechaConsumoDato(Date fechaConsumoDato) {
        this.fechaConsumoDato = fechaConsumoDato;
    }

    public int getEstadoMipres() {
        return estadoMipres;
    }

    public void setEstadoMipres(int estadoMipres) {
        this.estadoMipres = estadoMipres;
    }

    public String getRespuestaDatosFactura() {
        return respuestaDatosFactura;
    }

    public void setRespuestaDatosFactura(String respuestaDatosFactura) {
        this.respuestaDatosFactura = respuestaDatosFactura;
    }

    public String getCodigoFacturado() {
        return codigoFacturado;
    }

    public void setCodigoFacturado(String codigoFacturado) {
        this.codigoFacturado = codigoFacturado;
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

    public String getConfirmarUsuarioCrea() {
        return confirmarUsuarioCrea;
    }

    public void setConfirmarUsuarioCrea(String confirmarUsuarioCrea) {
        this.confirmarUsuarioCrea = confirmarUsuarioCrea;
    }

    public String getConfirmarTerminalCrea() {
        return confirmarTerminalCrea;
    }

    public void setConfirmarTerminalCrea(String confirmarTerminalCrea) {
        this.confirmarTerminalCrea = confirmarTerminalCrea;
    }

    public Date getConfirmarFechaHoraCrea() {
        return confirmarFechaHoraCrea;
    }

    public void setConfirmarFechaHoraCrea(Date confirmarFechaHoraCrea) {
        this.confirmarFechaHoraCrea = confirmarFechaHoraCrea;
    }

    public MpDireccionamientoEntregados getMpDireccionamientoEntregadosId() {
        return mpDireccionamientoEntregadosId;
    }

    public void setMpDireccionamientoEntregadosId(MpDireccionamientoEntregados mpDireccionamientoEntregadosId) {
        this.mpDireccionamientoEntregadosId = mpDireccionamientoEntregadosId;
    }

    public MpEntregaSuministros getMpEntregaSuministrosId() {
        return mpEntregaSuministrosId;
    }

    public void setMpEntregaSuministrosId(MpEntregaSuministros mpEntregaSuministrosId) {
        this.mpEntregaSuministrosId = mpEntregaSuministrosId;
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
        if (!(object instanceof MpEntregaFacturas)) {
            return false;
        }
        MpEntregaFacturas other = (MpEntregaFacturas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpEntregaFacturas[ id=" + id + " ]";
    }
    
}
