package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

public class MpDireccionamientoEntregado extends Auditoria {

    public static final Boolean FACTURA_CERRADA = true;

    private Integer id;
    private Integer idReporteEntrega;
    private Integer cantidadEntrega;
    private Integer numeroEntrega;
    private Integer entregaTotal;
    private Integer causaNoEntrega;
    private Integer estadoEntrega;
    private Date fechaEntrega;
    private Date fechaAnulacion;
    private Date fechaSuministro;
    private String justificacionDireccionamiento;
    private Date fechaDireccionamientoAutomatico;
    private Date fechaMaximaDireccionamiento;
    private BigDecimal copago;
    private BigDecimal cuotaModeradora;
    private String codFactCufe;
    private BigDecimal valorReportado;
    private BigDecimal valorTotal;
    private Date fechaCierreFacturaEps;
    private Date fechaCierreCiclo;
    private Date fechaReporteFactura;
    private String codTecEntregado;
    private String descTecEntregado;
    private Integer tipoTecEntregado;
    private String numeroLote;
    private Integer estadoSuministro;
    private Boolean entregaCompleta;
    private Integer idTransaccion;
    private String idSuministro;
    private Integer estRepEntrega;
    private Short tipoComparador;
    private String codigoComparador;
    private String unidadAdminstrativo;
    private String unidadHomologado;
    private BigDecimal valorMinimaConcentracion;
    private BigDecimal valorTotalComparador;
    private Boolean afectaPresupuesto;
    private Boolean cierreCiclo;
    private Boolean cierreSuministro;
    private Boolean anularSuministro;
    private Boolean anulado;
    private Integer tipoTecnologia;
    private Integer itemId;
    private Boolean ultimaEntrega;
    private String idFacturacion;
    private String idCicloFacturacion;
    private String noidEPS;
    private String codEPS;
    private Long cantUnMinDis;
    private Long valorTotFacturado;
    private String noSubEntrega;
    private Integer estFacturacion;
    private Date fechaAnulacionFac;
    private MpDireccionamiento mpDireccionamientoId;
    private MpPrescripcion mpPrescripcion;
    private MpPrescripcionMedicamento mpPrescripcionMedicamentoId;
    private MpPrescripcionInsumo mpPrescripcionInsumoId;
    private MpPrescripcionTecnologia mpPrescripcionTecnologiaId;
    private String cufe;
    private String codFactura;
    private Date fechaConsumo;
    private String ipsDirecciona;

    public MpDireccionamientoEntregado() {
    }

    public MpDireccionamientoEntregado(Integer id) {
        this.id = id;
    }

    public MpDireccionamientoEntregado(Integer id, Integer idReporteEntrega, Integer cantidadEntrega, Integer numeroEntrega, Integer entregaTotal, Integer causaNoEntrega, Integer estadoEntrega, Date fechaEntrega, Date fechaAnulacion, String justificacionDireccionamiento, Date fechaDireccionamientoAutomatico, Date fechaMaximaDireccionamiento, BigDecimal copago, BigDecimal cuotaModeradora, String codFactCufe, BigDecimal valorReportado, BigDecimal valorTotal, Date fechaCierreFacturaEps, Date fechaCierreCiclo, Date fechaReporteFactura, String codTecEntregado, String numeroLote, Integer estadoSuministro, Boolean entregaCompleta, Integer idTransaccion, Integer estRepEntrega, Short tipoComparador, String codigoComparador, String unidadAdminstrativo, String unidadHomologado, BigDecimal valorMinimaConcentracion, BigDecimal valorTotalComparador, Boolean afectaPresupuesto, Boolean cierreCiclo, Boolean cierreSuministro, Boolean anulado, Integer tipoTecnologia, Integer itemId, Boolean ultimaEntrega, String idFacturacion, String idCicloFacturacion, String noidEPS, String codEPS, Long cantUnMinDis, Long valorTotFacturado, String noSubEntrega, Integer estFacturacion, Date fechaAnulacionFac, MpDireccionamiento mpDireccionamientoId) {
        this.id = id;
        this.idReporteEntrega = idReporteEntrega;
        this.cantidadEntrega = cantidadEntrega;
        this.numeroEntrega = numeroEntrega;
        this.entregaTotal = entregaTotal;
        this.causaNoEntrega = causaNoEntrega;
        this.estadoEntrega = estadoEntrega;
        this.fechaEntrega = fechaEntrega;
        this.fechaAnulacion = fechaAnulacion;
        this.justificacionDireccionamiento = justificacionDireccionamiento;
        this.fechaDireccionamientoAutomatico = fechaDireccionamientoAutomatico;
        this.fechaMaximaDireccionamiento = fechaMaximaDireccionamiento;
        this.copago = copago;
        this.cuotaModeradora = cuotaModeradora;
        this.codFactCufe = codFactCufe;
        this.valorReportado = valorReportado;
        this.valorTotal = valorTotal;
        this.fechaCierreFacturaEps = fechaCierreFacturaEps;
        this.fechaCierreCiclo = fechaCierreCiclo;
        this.fechaReporteFactura = fechaReporteFactura;
        this.codTecEntregado = codTecEntregado;
        this.numeroLote = numeroLote;
        this.estadoSuministro = estadoSuministro;
        this.entregaCompleta = entregaCompleta;
        this.idTransaccion = idTransaccion;
        this.estRepEntrega = estRepEntrega;
        this.tipoComparador = tipoComparador;
        this.codigoComparador = codigoComparador;
        this.unidadAdminstrativo = unidadAdminstrativo;
        this.unidadHomologado = unidadHomologado;
        this.valorMinimaConcentracion = valorMinimaConcentracion;
        this.valorTotalComparador = valorTotalComparador;
        this.afectaPresupuesto = afectaPresupuesto;
        this.cierreCiclo = cierreCiclo;
        this.cierreSuministro = cierreSuministro;
        this.anulado = anulado;
        this.tipoTecnologia = tipoTecnologia;
        this.itemId = itemId;
        this.ultimaEntrega = ultimaEntrega;
        this.idFacturacion = idFacturacion;
        this.idCicloFacturacion = idCicloFacturacion;
        this.noidEPS = noidEPS;
        this.codEPS = codEPS;
        this.cantUnMinDis = cantUnMinDis;
        this.valorTotFacturado = valorTotFacturado;
        this.noSubEntrega = noSubEntrega;
        this.estFacturacion = estFacturacion;
        this.fechaAnulacionFac = fechaAnulacionFac;
        this.mpDireccionamientoId = mpDireccionamientoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdReporteEntrega() {
        return idReporteEntrega;
    }

    public void setIdReporteEntrega(Integer idReporteEntrega) {
        this.idReporteEntrega = idReporteEntrega;
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

    public Integer getTipoTecEntregado() {
        return tipoTecEntregado;
    }

    public void setTipoTecEntregado(Integer tipoTecEntregado) {
        this.tipoTecEntregado = tipoTecEntregado;
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

    public Boolean getEntregaCompleta() {
        return entregaCompleta;
    }

    public void setEntregaCompleta(Boolean entregaCompleta) {
        this.entregaCompleta = entregaCompleta;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Integer getEstRepEntrega() {
        return estRepEntrega;
    }

    public void setEstRepEntrega(Integer estRepEntrega) {
        this.estRepEntrega = estRepEntrega;
    }

    public Short getTipoComparador() {
        return tipoComparador;
    }

    public void setTipoComparador(Short tipoComparador) {
        this.tipoComparador = tipoComparador;
    }

    public String getCodigoComparador() {
        return codigoComparador;
    }

    public void setCodigoComparador(String codigoComparador) {
        this.codigoComparador = codigoComparador;
    }

    public String getUnidadAdminstrativo() {
        return unidadAdminstrativo;
    }

    public void setUnidadAdminstrativo(String unidadAdminstrativo) {
        this.unidadAdminstrativo = unidadAdminstrativo;
    }

    public String getUnidadHomologado() {
        return unidadHomologado;
    }

    public void setUnidadHomologado(String unidadHomologado) {
        this.unidadHomologado = unidadHomologado;
    }

    public BigDecimal getValorMinimaConcentracion() {
        return valorMinimaConcentracion;
    }

    public void setValorMinimaConcentracion(BigDecimal valorMinimaConcentracion) {
        this.valorMinimaConcentracion = valorMinimaConcentracion;
    }

    public BigDecimal getValorTotalComparador() {
        return valorTotalComparador;
    }

    public void setValorTotalComparador(BigDecimal valorTotalComparador) {
        this.valorTotalComparador = valorTotalComparador;
    }

    public Boolean getAfectaPresupuesto() {
        return afectaPresupuesto;
    }

    public void setAfectaPresupuesto(Boolean afectaPresupuesto) {
        this.afectaPresupuesto = afectaPresupuesto;
    }

    public Boolean getCierreCiclo() {
        return cierreCiclo;
    }

    public void setCierreCiclo(Boolean cierreCiclo) {
        this.cierreCiclo = cierreCiclo;
    }

    public Boolean getCierreSuministro() {
        return cierreSuministro;
    }

    public void setCierreSuministro(Boolean cierreSuministro) {
        this.cierreSuministro = cierreSuministro;
    }

    public Boolean getAnularSuministro() {
        return anularSuministro;
    }

    public void setAnularSuministro(Boolean anularSuministro) {
        this.anularSuministro = anularSuministro;
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

    public Boolean getUltimaEntrega() {
        return ultimaEntrega;
    }

    public void setUltimaEntrega(Boolean ultimaEntrega) {
        this.ultimaEntrega = ultimaEntrega;
    }

    public String getIdFacturacion() {
        return idFacturacion;
    }

    public void setIdFacturacion(String idFacturacion) {
        this.idFacturacion = idFacturacion;
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

    public Long getCantUnMinDis() {
        return cantUnMinDis;
    }

    public void setCantUnMinDis(Long cantUnMinDis) {
        this.cantUnMinDis = cantUnMinDis;
    }

    public Long getValorTotFacturado() {
        return valorTotFacturado;
    }

    public void setValorTotFacturado(Long valorTotFacturado) {
        this.valorTotFacturado = valorTotFacturado;
    }

    public String getNoSubEntrega() {
        return noSubEntrega;
    }

    public void setNoSubEntrega(String noSubEntrega) {
        this.noSubEntrega = noSubEntrega;
    }

    public Integer getEstFacturacion() {
        return estFacturacion;
    }

    public void setEstFacturacion(Integer estFacturacion) {
        this.estFacturacion = estFacturacion;
    }

    public Date getFechaAnulacionFac() {
        return fechaAnulacionFac;
    }

    public void setFechaAnulacionFac(Date fechaAnulacionFac) {
        this.fechaAnulacionFac = fechaAnulacionFac;
    }

    public MpDireccionamiento getMpDireccionamientoId() {
        return mpDireccionamientoId;
    }

    public void setMpDireccionamientoId(MpDireccionamiento mpDireccionamientoId) {
        this.mpDireccionamientoId = mpDireccionamientoId;
    }

    public String getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(String idSuministro) {
        this.idSuministro = idSuministro;
    }

    public Date getFechaSuministro() {
        return fechaSuministro;
    }

    public void setFechaSuministro(Date fechaSuministro) {
        this.fechaSuministro = fechaSuministro;
    }

    public MpPrescripcion getMpPrescripcion() {
        return mpPrescripcion;
    }

    public void setMpPrescripcion(MpPrescripcion mpPrescripcion) {
        this.mpPrescripcion = mpPrescripcion;
    }

    public MpPrescripcionMedicamento getMpPrescripcionMedicamentoId() {
        return mpPrescripcionMedicamentoId;
    }

    public void setMpPrescripcionMedicamentoId(MpPrescripcionMedicamento mpPrescripcionMedicamentoId) {
        this.mpPrescripcionMedicamentoId = mpPrescripcionMedicamentoId;
    }

    public MpPrescripcionInsumo getMpPrescripcionInsumoId() {
        return mpPrescripcionInsumoId;
    }

    public void setMpPrescripcionInsumoId(MpPrescripcionInsumo mpPrescripcionInsumoId) {
        this.mpPrescripcionInsumoId = mpPrescripcionInsumoId;
    }

    public MpPrescripcionTecnologia getMpPrescripcionTecnologiaId() {
        return mpPrescripcionTecnologiaId;
    }

    public void setMpPrescripcionTecnologiaId(MpPrescripcionTecnologia mpPrescripcionTecnologiaId) {
        this.mpPrescripcionTecnologiaId = mpPrescripcionTecnologiaId;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public String getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(String codFactura) {
        this.codFactura = codFactura;
    }

    public Date getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(Date fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    public String getIpsDirecciona() {
        return ipsDirecciona;
    }

    public void setIpsDirecciona(String ipsDirecciona) {
        this.ipsDirecciona = ipsDirecciona;
    }
    

}
