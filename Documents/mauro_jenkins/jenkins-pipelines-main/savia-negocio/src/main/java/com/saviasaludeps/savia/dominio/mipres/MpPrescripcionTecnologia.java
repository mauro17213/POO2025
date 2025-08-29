package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

public class MpPrescripcionTecnologia extends Auditoria {

    //ESTADOS
    public static final int ESTADO_PENDIENTE = 3;
    public static final int ESTADO_ANULANDO_NO_DIRECCIONAMIENTO = 14;

    private Integer id;
    private Integer idTransaccion;
    private String idDireccionamiento;
    private int estado;
    private int estadoJuntaProfesionales;
    private int consecutivoOrden;
    private int tipoTecnologia;
    private int tipoPrestacion;
    private Integer causaSolicitud2;
    private Integer causaSolicitud3;
    private Integer causaSolicitud4;
    private Integer causaSolicitud5;
    private Integer causaSolicitud6;
    private Integer causaSolicitud7;
    private Integer causaSolicitud11;
    private Integer causaSolicitud12;
    private Integer razonCausaSolicitud51;
    private String descripcionRazon51;
    private Integer codigoRazonCausa52;
    private String descripcionRazon52;
    private Integer maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer cantidadFormulada;
    private Integer frecuenciaDeUso;
    private Integer codigoUnidadTiempoFrecuenciaUso;
    private Integer cantidadDuracionTratamiento;
    private Integer cantidadTotal;
    private Integer codigoPeriodoDuracionTratamiento;
    private String justificacionNoPbs;
    private String indicacionesPaciente;
    private BigDecimal cantidadTotalEntrega;
    private BigDecimal entregados;
    private BigDecimal pendientes;
    private Date fechaDireccionamiento;
    private Date fechaMaximaEntrega;
    private BigDecimal valorUnitario;
    private String direccionado;
    private Integer cicloFacturacion;
    private Integer codFacturaIps;
    private String justificacionTecJunta;
    private String modJunta;
    private String numActaJunta;
    private Boolean reaccionesAdversasPaciente;
    private Integer cantidadDireccionada;
    private Integer cantidadMinimaDispensada;
    private Integer cantidadPrescrita;
    private String codigoMipresEntregar;
    private String codFormulada;
    private Integer estadoAuditoria;
    private Integer consecutivoJuntaTecnologia;
    private Date fechaActaJunta;
    private Integer estadoPrescripcion;
    private Boolean combinacionCups;
    private Boolean tieneCups;
    private Boolean descProcedPbsCargoUpc;
    private Boolean descartoEvidenciaEfiEfecClinica;
    private Boolean descartoNoExistePbs;
    private Boolean evidenciaEfiEfecClinica;
    private Boolean existePbsUpc;
    private Boolean obsReaccPbsCargoUpc;
    private Boolean pbsUtilizado;
    private Boolean existentePbsUpc;
    private Boolean listaNoUsoSanitarioUnirs;
    private Boolean faseExperimental;
    private Boolean regAprobAutClin;
    private Boolean financiadoPbsUpc;
    private Boolean utilizoProcedExistentePbsUpc;
    private Boolean procRealizaraCol;
    private Boolean procePbsDescartado;

    private String usuarioAtiende;
    private String terminalAtiende;
    private Date fechaHoraAtiende;
    private Boolean atendido;
    private Boolean banderaAtencion;

    private MpPrescripcion mpPrescripcion;

    public MpPrescripcionTecnologia() {
    }

    public MpPrescripcionTecnologia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getIdDireccionamiento() {
        return idDireccionamiento;
    }

    public void setIdDireccionamiento(String idDireccionamiento) {
        this.idDireccionamiento = idDireccionamiento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstadoJuntaProfesionales() {
        return estadoJuntaProfesionales;
    }

    public void setEstadoJuntaProfesionales(int estadoJuntaProfesionales) {
        this.estadoJuntaProfesionales = estadoJuntaProfesionales;
    }

    public int getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(int consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getTipoPrestacion() {
        return tipoPrestacion;
    }

    public void setTipoPrestacion(int tipoPrestacion) {
        this.tipoPrestacion = tipoPrestacion;
    }

    public Integer getCausaSolicitud2() {
        return causaSolicitud2;
    }

    public void setCausaSolicitud2(Integer causaSolicitud2) {
        this.causaSolicitud2 = causaSolicitud2;
    }

    public Integer getCausaSolicitud3() {
        return causaSolicitud3;
    }

    public void setCausaSolicitud3(Integer causaSolicitud3) {
        this.causaSolicitud3 = causaSolicitud3;
    }

    public Integer getCausaSolicitud4() {
        return causaSolicitud4;
    }

    public void setCausaSolicitud4(Integer causaSolicitud4) {
        this.causaSolicitud4 = causaSolicitud4;
    }

    public Integer getCausaSolicitud5() {
        return causaSolicitud5;
    }

    public void setCausaSolicitud5(Integer causaSolicitud5) {
        this.causaSolicitud5 = causaSolicitud5;
    }

    public Integer getCausaSolicitud6() {
        return causaSolicitud6;
    }

    public void setCausaSolicitud6(Integer causaSolicitud6) {
        this.causaSolicitud6 = causaSolicitud6;
    }

    public Integer getCausaSolicitud7() {
        return causaSolicitud7;
    }

    public void setCausaSolicitud7(Integer causaSolicitud7) {
        this.causaSolicitud7 = causaSolicitud7;
    }

    public Integer getCausaSolicitud11() {
        return causaSolicitud11;
    }

    public void setCausaSolicitud11(Integer causaSolicitud11) {
        this.causaSolicitud11 = causaSolicitud11;
    }

    public Integer getCausaSolicitud12() {
        return causaSolicitud12;
    }

    public void setCausaSolicitud12(Integer causaSolicitud12) {
        this.causaSolicitud12 = causaSolicitud12;
    }

    public Integer getRazonCausaSolicitud51() {
        return razonCausaSolicitud51;
    }

    public void setRazonCausaSolicitud51(Integer razonCausaSolicitud51) {
        this.razonCausaSolicitud51 = razonCausaSolicitud51;
    }

    public String getDescripcionRazon51() {
        return descripcionRazon51;
    }

    public void setDescripcionRazon51(String descripcionRazon51) {
        this.descripcionRazon51 = descripcionRazon51;
    }

    public Integer getCodigoRazonCausa52() {
        return codigoRazonCausa52;
    }

    public void setCodigoRazonCausa52(Integer codigoRazonCausa52) {
        this.codigoRazonCausa52 = codigoRazonCausa52;
    }

    public String getDescripcionRazon52() {
        return descripcionRazon52;
    }

    public void setDescripcionRazon52(String descripcionRazon52) {
        this.descripcionRazon52 = descripcionRazon52;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getCantidadFormulada() {
        return cantidadFormulada;
    }

    public void setCantidadFormulada(Integer cantidadFormulada) {
        this.cantidadFormulada = cantidadFormulada;
    }

    public Integer getFrecuenciaDeUso() {
        return frecuenciaDeUso;
    }

    public void setFrecuenciaDeUso(Integer frecuenciaDeUso) {
        this.frecuenciaDeUso = frecuenciaDeUso;
    }

    public Integer getCodigoUnidadTiempoFrecuenciaUso() {
        return codigoUnidadTiempoFrecuenciaUso;
    }

    public void setCodigoUnidadTiempoFrecuenciaUso(Integer codigoUnidadTiempoFrecuenciaUso) {
        this.codigoUnidadTiempoFrecuenciaUso = codigoUnidadTiempoFrecuenciaUso;
    }

    public Integer getCantidadDuracionTratamiento() {
        return cantidadDuracionTratamiento;
    }

    public void setCantidadDuracionTratamiento(Integer cantidadDuracionTratamiento) {
        this.cantidadDuracionTratamiento = cantidadDuracionTratamiento;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Integer getCodigoPeriodoDuracionTratamiento() {
        return codigoPeriodoDuracionTratamiento;
    }

    public void setCodigoPeriodoDuracionTratamiento(Integer codigoPeriodoDuracionTratamiento) {
        this.codigoPeriodoDuracionTratamiento = codigoPeriodoDuracionTratamiento;
    }

    public String getJustificacionNoPbs() {
        return justificacionNoPbs;
    }

    public void setJustificacionNoPbs(String justificacionNoPbs) {
        this.justificacionNoPbs = justificacionNoPbs;
    }

    public String getIndicacionesPaciente() {
        return indicacionesPaciente;
    }

    public void setIndicacionesPaciente(String indicacionesPaciente) {
        this.indicacionesPaciente = indicacionesPaciente;
    }

    public BigDecimal getCantidadTotalEntrega() {
        return cantidadTotalEntrega;
    }

    public void setCantidadTotalEntrega(BigDecimal cantidadTotalEntrega) {
        this.cantidadTotalEntrega = cantidadTotalEntrega;
    }

    public BigDecimal getEntregados() {
        return entregados;
    }

    public void setEntregados(BigDecimal entregados) {
        this.entregados = entregados;
    }

    public BigDecimal getPendientes() {
        return pendientes;
    }

    public void setPendientes(BigDecimal pendientes) {
        this.pendientes = pendientes;
    }

    public Date getFechaDireccionamiento() {
        return fechaDireccionamiento;
    }

    public void setFechaDireccionamiento(Date fechaDireccionamiento) {
        this.fechaDireccionamiento = fechaDireccionamiento;
    }

    public Date getFechaMaximaEntrega() {
        return fechaMaximaEntrega;
    }

    public void setFechaMaximaEntrega(Date fechaMaximaEntrega) {
        this.fechaMaximaEntrega = fechaMaximaEntrega;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getDireccionado() {
        return direccionado;
    }

    public void setDireccionado(String direccionado) {
        this.direccionado = direccionado;
    }

    public Integer getCicloFacturacion() {
        return cicloFacturacion;
    }

    public void setCicloFacturacion(Integer cicloFacturacion) {
        this.cicloFacturacion = cicloFacturacion;
    }

    public Integer getCodFacturaIps() {
        return codFacturaIps;
    }

    public void setCodFacturaIps(Integer codFacturaIps) {
        this.codFacturaIps = codFacturaIps;
    }

    public String getJustificacionTecJunta() {
        return justificacionTecJunta;
    }

    public void setJustificacionTecJunta(String justificacionTecJunta) {
        this.justificacionTecJunta = justificacionTecJunta;
    }

    public String getModJunta() {
        return modJunta;
    }

    public void setModJunta(String modJunta) {
        this.modJunta = modJunta;
    }

    public String getNumActaJunta() {
        return numActaJunta;
    }

    public void setNumActaJunta(String numActaJunta) {
        this.numActaJunta = numActaJunta;
    }

    public Boolean getReaccionesAdversasPaciente() {
        return reaccionesAdversasPaciente;
    }

    public void setReaccionesAdversasPaciente(Boolean reaccionesAdversasPaciente) {
        this.reaccionesAdversasPaciente = reaccionesAdversasPaciente;
    }

    public Integer getCantidadDireccionada() {
        return cantidadDireccionada;
    }

    public void setCantidadDireccionada(Integer cantidadDireccionada) {
        this.cantidadDireccionada = cantidadDireccionada;
    }

    public Integer getCantidadMinimaDispensada() {
        return cantidadMinimaDispensada;
    }

    public void setCantidadMinimaDispensada(Integer cantidadMinimaDispensada) {
        this.cantidadMinimaDispensada = cantidadMinimaDispensada;
    }

    public Integer getCantidadPrescrita() {
        return cantidadPrescrita;
    }

    public void setCantidadPrescrita(Integer cantidadPrescrita) {
        this.cantidadPrescrita = cantidadPrescrita;
    }

    public String getCodigoMipresEntregar() {
        return codigoMipresEntregar;
    }

    public void setCodigoMipresEntregar(String codigoMipresEntregar) {
        this.codigoMipresEntregar = codigoMipresEntregar;
    }

    public String getCodFormulada() {
        return codFormulada;
    }

    public void setCodFormulada(String codFormulada) {
        this.codFormulada = codFormulada;
    }

    public Integer getEstadoAuditoria() {
        return estadoAuditoria;
    }

    public void setEstadoAuditoria(Integer estadoAuditoria) {
        this.estadoAuditoria = estadoAuditoria;
    }

    public Integer getConsecutivoJuntaTecnologia() {
        return consecutivoJuntaTecnologia;
    }

    public void setConsecutivoJuntaTecnologia(Integer consecutivoJuntaTecnologia) {
        this.consecutivoJuntaTecnologia = consecutivoJuntaTecnologia;
    }

    public Date getFechaActaJunta() {
        return fechaActaJunta;
    }

    public void setFechaActaJunta(Date fechaActaJunta) {
        this.fechaActaJunta = fechaActaJunta;
    }

    public Integer getEstadoPrescripcion() {
        return estadoPrescripcion;
    }

    public void setEstadoPrescripcion(Integer estadoPrescripcion) {
        this.estadoPrescripcion = estadoPrescripcion;
    }

    public Boolean getCombinacionCups() {
        return combinacionCups;
    }

    public void setCombinacionCups(Boolean combinacionCups) {
        this.combinacionCups = combinacionCups;
    }

    public Boolean getTieneCups() {
        return tieneCups;
    }

    public void setTieneCups(Boolean tieneCups) {
        this.tieneCups = tieneCups;
    }

    public Boolean getDescProcedPbsCargoUpc() {
        return descProcedPbsCargoUpc;
    }

    public void setDescProcedPbsCargoUpc(Boolean descProcedPbsCargoUpc) {
        this.descProcedPbsCargoUpc = descProcedPbsCargoUpc;
    }

    public Boolean getDescartoEvidenciaEfiEfecClinica() {
        return descartoEvidenciaEfiEfecClinica;
    }

    public void setDescartoEvidenciaEfiEfecClinica(Boolean descartoEvidenciaEfiEfecClinica) {
        this.descartoEvidenciaEfiEfecClinica = descartoEvidenciaEfiEfecClinica;
    }

    public Boolean getDescartoNoExistePbs() {
        return descartoNoExistePbs;
    }

    public void setDescartoNoExistePbs(Boolean descartoNoExistePbs) {
        this.descartoNoExistePbs = descartoNoExistePbs;
    }

    public Boolean getEvidenciaEfiEfecClinica() {
        return evidenciaEfiEfecClinica;
    }

    public void setEvidenciaEfiEfecClinica(Boolean evidenciaEfiEfecClinica) {
        this.evidenciaEfiEfecClinica = evidenciaEfiEfecClinica;
    }

    public Boolean getExistePbsUpc() {
        return existePbsUpc;
    }

    public void setExistePbsUpc(Boolean existePbsUpc) {
        this.existePbsUpc = existePbsUpc;
    }

    public Boolean getObsReaccPbsCargoUpc() {
        return obsReaccPbsCargoUpc;
    }

    public void setObsReaccPbsCargoUpc(Boolean obsReaccPbsCargoUpc) {
        this.obsReaccPbsCargoUpc = obsReaccPbsCargoUpc;
    }

    public Boolean getPbsUtilizado() {
        return pbsUtilizado;
    }

    public void setPbsUtilizado(Boolean pbsUtilizado) {
        this.pbsUtilizado = pbsUtilizado;
    }

    public Boolean getExistentePbsUpc() {
        return existentePbsUpc;
    }

    public void setExistentePbsUpc(Boolean existentePbsUpc) {
        this.existentePbsUpc = existentePbsUpc;
    }

    public Boolean getListaNoUsoSanitarioUnirs() {
        return listaNoUsoSanitarioUnirs;
    }

    public void setListaNoUsoSanitarioUnirs(Boolean listaNoUsoSanitarioUnirs) {
        this.listaNoUsoSanitarioUnirs = listaNoUsoSanitarioUnirs;
    }

    public Boolean getFaseExperimental() {
        return faseExperimental;
    }

    public void setFaseExperimental(Boolean faseExperimental) {
        this.faseExperimental = faseExperimental;
    }

    public Boolean getRegAprobAutClin() {
        return regAprobAutClin;
    }

    public void setRegAprobAutClin(Boolean regAprobAutClin) {
        this.regAprobAutClin = regAprobAutClin;
    }

    public Boolean getFinanciadoPbsUpc() {
        return financiadoPbsUpc;
    }

    public void setFinanciadoPbsUpc(Boolean financiadoPbsUpc) {
        this.financiadoPbsUpc = financiadoPbsUpc;
    }

    public Boolean getUtilizoProcedExistentePbsUpc() {
        return utilizoProcedExistentePbsUpc;
    }

    public void setUtilizoProcedExistentePbsUpc(Boolean utilizoProcedExistentePbsUpc) {
        this.utilizoProcedExistentePbsUpc = utilizoProcedExistentePbsUpc;
    }

    public MpPrescripcion getMpPrescripcion() {
        return mpPrescripcion;
    }

    public void setMpPrescripcion(MpPrescripcion mpPrescripcion) {
        this.mpPrescripcion = mpPrescripcion;
    }

    public Boolean getProcRealizaraCol() {
        return procRealizaraCol;
    }

    public void setProcRealizaraCol(Boolean procRealizaraCol) {
        this.procRealizaraCol = procRealizaraCol;
    }

    public Boolean getProcePbsDescartado() {
        return procePbsDescartado;
    }

    public void setProcePbsDescartado(Boolean procePbsDescartado) {
        this.procePbsDescartado = procePbsDescartado;
    }

    public String getUsuarioAtiende() {
        return usuarioAtiende;
    }

    public void setUsuarioAtiende(String usuarioAtiende) {
        this.usuarioAtiende = usuarioAtiende;
    }

    public String getTerminalAtiende() {
        return terminalAtiende;
    }

    public void setTerminalAtiende(String terminalAtiende) {
        this.terminalAtiende = terminalAtiende;
    }

    public Date getFechaHoraAtiende() {
        return fechaHoraAtiende;
    }

    public void setFechaHoraAtiende(Date fechaHoraAtiende) {
        this.fechaHoraAtiende = fechaHoraAtiende;
    }

    public Boolean getAtendido() {
        return atendido;
    }

    public void setAtendido(Boolean atendido) {
        this.atendido = atendido;
    }

    public Boolean getBanderaAtencion() {
        return banderaAtencion;
    }

    public void setBanderaAtencion(Boolean banderaAtencion) {
        this.banderaAtencion = banderaAtencion;
    }

    @Override
    public String toString() {
        return "MpPrescripcionTecnologia{" + "id=" + id + ", idTransaccion=" + idTransaccion + ", idDireccionamiento=" + idDireccionamiento + ", estado=" + estado + ", estadoJuntaProfesionales=" + estadoJuntaProfesionales + ", consecutivoOrden=" + consecutivoOrden + ", tipoTecnologia=" + tipoTecnologia + ", tipoPrestacion=" + tipoPrestacion + ", causaSolicitud2=" + causaSolicitud2 + ", causaSolicitud3=" + causaSolicitud3 + ", causaSolicitud4=" + causaSolicitud4 + ", causaSolicitud5=" + causaSolicitud5 + ", causaSolicitud6=" + causaSolicitud6 + ", causaSolicitud7=" + causaSolicitud7 + ", causaSolicitud11=" + causaSolicitud11 + ", causaSolicitud12=" + causaSolicitud12 + ", razonCausaSolicitud51=" + razonCausaSolicitud51 + ", descripcionRazon51=" + descripcionRazon51 + ", codigoRazonCausa52=" + codigoRazonCausa52 + ", descripcionRazon52=" + descripcionRazon52 + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", cantidadFormulada=" + cantidadFormulada + ", frecuenciaDeUso=" + frecuenciaDeUso + ", codigoUnidadTiempoFrecuenciaUso=" + codigoUnidadTiempoFrecuenciaUso + ", cantidadDuracionTratamiento=" + cantidadDuracionTratamiento + ", cantidadTotal=" + cantidadTotal + ", codigoPeriodoDuracionTratamiento=" + codigoPeriodoDuracionTratamiento + ", justificacionNoPbs=" + justificacionNoPbs + ", indicacionesPaciente=" + indicacionesPaciente + ", cantidadTotalEntrega=" + cantidadTotalEntrega + ", entregados=" + entregados + ", pendientes=" + pendientes + ", fechaDireccionamiento=" + fechaDireccionamiento + ", fechaMaximaEntrega=" + fechaMaximaEntrega + ", valorUnitario=" + valorUnitario + ", direccionado=" + direccionado + ", cicloFacturacion=" + cicloFacturacion + ", codFacturaIps=" + codFacturaIps + ", justificacionTecJunta=" + justificacionTecJunta + ", modJunta=" + modJunta + ", numActaJunta=" + numActaJunta + ", reaccionesAdversasPaciente=" + reaccionesAdversasPaciente + ", cantidadDireccionada=" + cantidadDireccionada + ", cantidadMinimaDispensada=" + cantidadMinimaDispensada + ", cantidadPrescrita=" + cantidadPrescrita + ", codigoMipresEntregar=" + codigoMipresEntregar + ", codFormulada=" + codFormulada + ", estadoAuditoria=" + estadoAuditoria + ", consecutivoJuntaTecnologia=" + consecutivoJuntaTecnologia + ", fechaActaJunta=" + fechaActaJunta + ", estadoPrescripcion=" + estadoPrescripcion + ", combinacionCups=" + combinacionCups + ", tieneCups=" + tieneCups + ", descProcedPbsCargoUpc=" + descProcedPbsCargoUpc + ", descartoEvidenciaEfiEfecClinica=" + descartoEvidenciaEfiEfecClinica + ", descartoNoExistePbs=" + descartoNoExistePbs + ", evidenciaEfiEfecClinica=" + evidenciaEfiEfecClinica + ", existePbsUpc=" + existePbsUpc + ", obsReaccPbsCargoUpc=" + obsReaccPbsCargoUpc + ", pbsUtilizado=" + pbsUtilizado + ", existentePbsUpc=" + existentePbsUpc + ", listaNoUsoSanitarioUnirs=" + listaNoUsoSanitarioUnirs + ", faseExperimental=" + faseExperimental + ", regAprobAutClin=" + regAprobAutClin + ", financiadoPbsUpc=" + financiadoPbsUpc + ", utilizoProcedExistentePbsUpc=" + utilizoProcedExistentePbsUpc + ", procRealizaraCol=" + procRealizaraCol + ", procePbsDescartado=" + procePbsDescartado + ", mpPrescripcion=" + mpPrescripcion + '}';
    }

}
