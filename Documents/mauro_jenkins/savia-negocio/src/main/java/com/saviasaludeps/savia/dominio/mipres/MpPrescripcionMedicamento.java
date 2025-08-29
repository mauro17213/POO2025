package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author BSGomez
 */
public class MpPrescripcionMedicamento extends Auditoria {

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
    private Integer tipoMedicamento;
    private int tipoPrestacion;
    private String medPbsUtilizado;
    private Boolean causaSolicitud1;
    private Boolean causaSolicitud2;
    private Boolean causaSolicitud3;
    private Boolean causaSolicitud4;
    private Boolean causaSolicitud5;
    private Boolean causaSolicitud6;
    private boolean razonCausaSolicitud31;
    private String descripcionRazon31;
    private boolean razonCausaSolicitud32;
    private String descripcionRazon32;
    private boolean razonCausaSolicitud41;
    private String descripcionRazon41;
    private boolean razonCausaSolicitud42;
    private String descripcionRazon42;
    private boolean razonCausaSolicitud43;
    private String descripcionRazon43;
    private boolean razonCausaSolicitud44;
    private String descripcionRazon44;
    private Boolean razonCausaSolicitud51;
    private String descripcionRazon51;
    private Boolean razonCausaSolicitud52;
    private String descripcionRazon52;
    private Boolean razonCausaSolicitud53;
    private String descripcionRazon53;
    private Boolean razonCausaSolicitud54;
    private String descripcionRazon54;
    private Boolean razonCausaSolicitud5;
    private String descripcionRazon5;
    private String descripcionMedicamentoPrincipioActivo;
    private String codigoFormulaFarmaceutica;
    private String codigoViaAdministracion;
    private String justificacionNoPbs;
    private BigDecimal dosis;
    private String dosisUnidadMedida;
    private String numeroFrecuenciaAdministracion;
    private Integer codigoFrecuenciaAdministracion;
    private Integer indicacionesEspeciales;
    private Integer cantidadTratamiento;
    private Integer duracionTratamiento;
    private BigDecimal cantidadTotalFormulada;
    private String unidadFarmaceuticaCantidadTotal;
    private String indicacionRecibida;
    private Boolean esDiagnosticoVih;
    private Boolean esDiagnosticoCancer;
    private Boolean esDiagnosticoEnfermedadRenal;
    private Boolean esDiagnosticoDesnutricion;
    private String tipoProductoNutricional;
    private String descripcionProductoNutricional;
    private BigDecimal cantidadTotalEntrega;
    private BigDecimal entregados;
    private BigDecimal pendientes;
    private Date fechaDireccionamiento;
    private Date fechaMaximaEntrega;
    private int maeProductosNutricionalesId;
    private String maeProductosNutricionalesCodigo;
    private String maeProductosNutricionalesValor;
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
    private Boolean indicacionUnirs;
    private String formaFarmaceutica;
    private Integer consecOrdenPrincActivo;
    private Boolean enFaseExperimental;
    private String cantidadMedPrincipioActivo;
    private Boolean descMedPbsUpc;
    private Boolean descartoNoExistePbs;
    private Boolean listaNoUsoSanitarioUnirs;
    private Boolean existentePbsCargoUpc;
    private Boolean financPbsCargoUpc;
    private Boolean utilizoNutriMedExistentePbsUpc;
    private Boolean evidenciaEfiEfecClinica;
    private Boolean existePbsCargoUpc;
    private Boolean obsReaccPbsCargoUpc;
    private Boolean descartoEvidenciaEfiEfecClinica;
    private Boolean regAprobAutClin;
    private Boolean descartoReaccionesAdversas;
    private Boolean cubiertoPbsCargoUpc;
    private Boolean pbsUtilizado;
    private Boolean resultSatisPrev;
    private Boolean registroInvima;
    private Boolean descartoAlternativa;

    private String usuarioAtiende;
    private String terminalAtiende;
    private Date fechaHoraAtiende;
    private Boolean atendido;
    private Boolean banderaAtencion;

    private MpPrescripcion mpPrescripcion;

    public MpPrescripcionMedicamento() {
    }

    public MpPrescripcionMedicamento(Integer id) {
        this.id = id;
    }

    public Boolean getDescartoAlternativa() {
        return descartoAlternativa;
    }

    public void setDescartoAlternativa(Boolean descartoAlternativa) {
        this.descartoAlternativa = descartoAlternativa;
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

    public MpPrescripcion getMpPrescripcion() {
        return mpPrescripcion;
    }

    public void setMpPrescripcion(MpPrescripcion mpPrescripcion) {
        this.mpPrescripcion = mpPrescripcion;
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

    public Integer getTipoMedicamento() {
        return tipoMedicamento;
    }

    public void setTipoMedicamento(Integer tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }

    public int getTipoPrestacion() {
        return tipoPrestacion;
    }

    public void setTipoPrestacion(int tipoPrestacion) {
        this.tipoPrestacion = tipoPrestacion;
    }

    public String getMedPbsUtilizado() {
        return medPbsUtilizado;
    }

    public void setMedPbsUtilizado(String medPbsUtilizado) {
        this.medPbsUtilizado = medPbsUtilizado;
    }

    public Boolean getCausaSolicitud1() {
        return causaSolicitud1;
    }

    public void setCausaSolicitud1(Boolean causaSolicitud1) {
        this.causaSolicitud1 = causaSolicitud1;
    }

    public Boolean getCausaSolicitud2() {
        return causaSolicitud2;
    }

    public void setCausaSolicitud2(Boolean causaSolicitud2) {
        this.causaSolicitud2 = causaSolicitud2;
    }

    public Boolean getCausaSolicitud3() {
        return causaSolicitud3;
    }

    public void setCausaSolicitud3(Boolean causaSolicitud3) {
        this.causaSolicitud3 = causaSolicitud3;
    }

    public Boolean getCausaSolicitud4() {
        return causaSolicitud4;
    }

    public void setCausaSolicitud4(Boolean causaSolicitud4) {
        this.causaSolicitud4 = causaSolicitud4;
    }

    public Boolean getCausaSolicitud5() {
        return causaSolicitud5;
    }

    public void setCausaSolicitud5(Boolean causaSolicitud5) {
        this.causaSolicitud5 = causaSolicitud5;
    }

    public Boolean getCausaSolicitud6() {
        return causaSolicitud6;
    }

    public void setCausaSolicitud6(Boolean causaSolicitud6) {
        this.causaSolicitud6 = causaSolicitud6;
    }

    public boolean getRazonCausaSolicitud31() {
        return razonCausaSolicitud31;
    }

    public void setRazonCausaSolicitud31(boolean razonCausaSolicitud31) {
        this.razonCausaSolicitud31 = razonCausaSolicitud31;
    }

    public String getDescripcionRazon31() {
        return descripcionRazon31;
    }

    public void setDescripcionRazon31(String descripcionRazon31) {
        this.descripcionRazon31 = descripcionRazon31;
    }

    public boolean getRazonCausaSolicitud32() {
        return razonCausaSolicitud32;
    }

    public void setRazonCausaSolicitud32(boolean razonCausaSolicitud32) {
        this.razonCausaSolicitud32 = razonCausaSolicitud32;
    }

    public String getDescripcionRazon32() {
        return descripcionRazon32;
    }

    public void setDescripcionRazon32(String descripcionRazon32) {
        this.descripcionRazon32 = descripcionRazon32;
    }

    public boolean getRazonCausaSolicitud41() {
        return razonCausaSolicitud41;
    }

    public void setRazonCausaSolicitud41(boolean razonCausaSolicitud41) {
        this.razonCausaSolicitud41 = razonCausaSolicitud41;
    }

    public String getDescripcionRazon41() {
        return descripcionRazon41;
    }

    public void setDescripcionRazon41(String descripcionRazon41) {
        this.descripcionRazon41 = descripcionRazon41;
    }

    public boolean getRazonCausaSolicitud42() {
        return razonCausaSolicitud42;
    }

    public void setRazonCausaSolicitud42(boolean razonCausaSolicitud42) {
        this.razonCausaSolicitud42 = razonCausaSolicitud42;
    }

    public String getDescripcionRazon42() {
        return descripcionRazon42;
    }

    public void setDescripcionRazon42(String descripcionRazon42) {
        this.descripcionRazon42 = descripcionRazon42;
    }

    public boolean getRazonCausaSolicitud43() {
        return razonCausaSolicitud43;
    }

    public void setRazonCausaSolicitud43(boolean razonCausaSolicitud43) {
        this.razonCausaSolicitud43 = razonCausaSolicitud43;
    }

    public String getDescripcionRazon43() {
        return descripcionRazon43;
    }

    public void setDescripcionRazon43(String descripcionRazon43) {
        this.descripcionRazon43 = descripcionRazon43;
    }

    public boolean getRazonCausaSolicitud44() {
        return razonCausaSolicitud44;
    }

    public void setRazonCausaSolicitud44(boolean razonCausaSolicitud44) {
        this.razonCausaSolicitud44 = razonCausaSolicitud44;
    }

    public String getDescripcionRazon44() {
        return descripcionRazon44;
    }

    public void setDescripcionRazon44(String descripcionRazon44) {
        this.descripcionRazon44 = descripcionRazon44;
    }

    public Boolean getRazonCausaSolicitud51() {
        return razonCausaSolicitud51;
    }

    public void setRazonCausaSolicitud51(Boolean razonCausaSolicitud51) {
        this.razonCausaSolicitud51 = razonCausaSolicitud51;
    }

    public String getDescripcionRazon51() {
        return descripcionRazon51;
    }

    public void setDescripcionRazon51(String descripcionRazon51) {
        this.descripcionRazon51 = descripcionRazon51;
    }

    public Boolean getRazonCausaSolicitud52() {
        return razonCausaSolicitud52;
    }

    public void setRazonCausaSolicitud52(Boolean razonCausaSolicitud52) {
        this.razonCausaSolicitud52 = razonCausaSolicitud52;
    }

    public String getDescripcionRazon52() {
        return descripcionRazon52;
    }

    public void setDescripcionRazon52(String descripcionRazon52) {
        this.descripcionRazon52 = descripcionRazon52;
    }

    public Boolean getRazonCausaSolicitud53() {
        return razonCausaSolicitud53;
    }

    public void setRazonCausaSolicitud53(Boolean razonCausaSolicitud53) {
        this.razonCausaSolicitud53 = razonCausaSolicitud53;
    }

    public String getDescripcionRazon53() {
        return descripcionRazon53;
    }

    public void setDescripcionRazon53(String descripcionRazon53) {
        this.descripcionRazon53 = descripcionRazon53;
    }

    public Boolean getRazonCausaSolicitud54() {
        return razonCausaSolicitud54;
    }

    public void setRazonCausaSolicitud54(Boolean razonCausaSolicitud54) {
        this.razonCausaSolicitud54 = razonCausaSolicitud54;
    }

    public String getDescripcionRazon54() {
        return descripcionRazon54;
    }

    public void setDescripcionRazon54(String descripcionRazon54) {
        this.descripcionRazon54 = descripcionRazon54;
    }

    public Boolean getRazonCausaSolicitud5() {
        return razonCausaSolicitud5;
    }

    public void setRazonCausaSolicitud5(Boolean razonCausaSolicitud5) {
        this.razonCausaSolicitud5 = razonCausaSolicitud5;
    }

    public String getDescripcionRazon5() {
        return descripcionRazon5;
    }

    public void setDescripcionRazon5(String descripcionRazon5) {
        this.descripcionRazon5 = descripcionRazon5;
    }

    public String getDescripcionMedicamentoPrincipioActivo() {
        return descripcionMedicamentoPrincipioActivo;
    }

    public void setDescripcionMedicamentoPrincipioActivo(String descripcionMedicamentoPrincipioActivo) {
        this.descripcionMedicamentoPrincipioActivo = descripcionMedicamentoPrincipioActivo;
    }

    public String getCodigoFormulaFarmaceutica() {
        return codigoFormulaFarmaceutica;
    }

    public void setCodigoFormulaFarmaceutica(String codigoFormulaFarmaceutica) {
        this.codigoFormulaFarmaceutica = codigoFormulaFarmaceutica;
    }

    public String getCodigoViaAdministracion() {
        return codigoViaAdministracion;
    }

    public void setCodigoViaAdministracion(String codigoViaAdministracion) {
        this.codigoViaAdministracion = codigoViaAdministracion;
    }

    public String getJustificacionNoPbs() {
        return justificacionNoPbs;
    }

    public void setJustificacionNoPbs(String justificacionNoPbs) {
        this.justificacionNoPbs = justificacionNoPbs;
    }

    public BigDecimal getDosis() {
        return dosis;
    }

    public void setDosis(BigDecimal dosis) {
        this.dosis = dosis;
    }

    public String getDosisUnidadMedida() {
        return dosisUnidadMedida;
    }

    public void setDosisUnidadMedida(String dosisUnidadMedida) {
        this.dosisUnidadMedida = dosisUnidadMedida;
    }

    public String getNumeroFrecuenciaAdministracion() {
        return numeroFrecuenciaAdministracion;
    }

    public void setNumeroFrecuenciaAdministracion(String numeroFrecuenciaAdministracion) {
        this.numeroFrecuenciaAdministracion = numeroFrecuenciaAdministracion;
    }

    public Integer getCodigoFrecuenciaAdministracion() {
        return codigoFrecuenciaAdministracion;
    }

    public void setCodigoFrecuenciaAdministracion(Integer codigoFrecuenciaAdministracion) {
        this.codigoFrecuenciaAdministracion = codigoFrecuenciaAdministracion;
    }

    public Integer getIndicacionesEspeciales() {
        return indicacionesEspeciales;
    }

    public void setIndicacionesEspeciales(Integer indicacionesEspeciales) {
        this.indicacionesEspeciales = indicacionesEspeciales;
    }

    public Integer getCantidadTratamiento() {
        return cantidadTratamiento;
    }

    public void setCantidadTratamiento(Integer cantidadTratamiento) {
        this.cantidadTratamiento = cantidadTratamiento;
    }

    public Integer getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(Integer duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public BigDecimal getCantidadTotalFormulada() {
        return cantidadTotalFormulada;
    }

    public void setCantidadTotalFormulada(BigDecimal cantidadTotalFormulada) {
        this.cantidadTotalFormulada = cantidadTotalFormulada;
    }

    public String getUnidadFarmaceuticaCantidadTotal() {
        return unidadFarmaceuticaCantidadTotal;
    }

    public void setUnidadFarmaceuticaCantidadTotal(String unidadFarmaceuticaCantidadTotal) {
        this.unidadFarmaceuticaCantidadTotal = unidadFarmaceuticaCantidadTotal;
    }

    public String getIndicacionRecibida() {
        return indicacionRecibida;
    }

    public void setIndicacionRecibida(String indicacionRecibida) {
        this.indicacionRecibida = indicacionRecibida;
    }

    public Boolean getEsDiagnosticoVih() {
        return esDiagnosticoVih;
    }

    public void setEsDiagnosticoVih(Boolean esDiagnosticoVih) {
        this.esDiagnosticoVih = esDiagnosticoVih;
    }

    public Boolean getEsDiagnosticoCancer() {
        return esDiagnosticoCancer;
    }

    public void setEsDiagnosticoCancer(Boolean esDiagnosticoCancer) {
        this.esDiagnosticoCancer = esDiagnosticoCancer;
    }

    public Boolean getEsDiagnosticoEnfermedadRenal() {
        return esDiagnosticoEnfermedadRenal;
    }

    public void setEsDiagnosticoEnfermedadRenal(Boolean esDiagnosticoEnfermedadRenal) {
        this.esDiagnosticoEnfermedadRenal = esDiagnosticoEnfermedadRenal;
    }

    public Boolean getEsDiagnosticoDesnutricion() {
        return esDiagnosticoDesnutricion;
    }

    public void setEsDiagnosticoDesnutricion(Boolean esDiagnosticoDesnutricion) {
        this.esDiagnosticoDesnutricion = esDiagnosticoDesnutricion;
    }

    public String getTipoProductoNutricional() {
        return tipoProductoNutricional;
    }

    public void setTipoProductoNutricional(String tipoProductoNutricional) {
        this.tipoProductoNutricional = tipoProductoNutricional;
    }

    public String getDescripcionProductoNutricional() {
        return descripcionProductoNutricional;
    }

    public void setDescripcionProductoNutricional(String descripcionProductoNutricional) {
        this.descripcionProductoNutricional = descripcionProductoNutricional;
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

    public int getMaeProductosNutricionalesId() {
        return maeProductosNutricionalesId;
    }

    public void setMaeProductosNutricionalesId(int maeProductosNutricionalesId) {
        this.maeProductosNutricionalesId = maeProductosNutricionalesId;
    }

    public String getMaeProductosNutricionalesCodigo() {
        return maeProductosNutricionalesCodigo;
    }

    public void setMaeProductosNutricionalesCodigo(String maeProductosNutricionalesCodigo) {
        this.maeProductosNutricionalesCodigo = maeProductosNutricionalesCodigo;
    }

    public String getMaeProductosNutricionalesValor() {
        return maeProductosNutricionalesValor;
    }

    public void setMaeProductosNutricionalesValor(String maeProductosNutricionalesValor) {
        this.maeProductosNutricionalesValor = maeProductosNutricionalesValor;
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

    public Boolean getIndicacionUnirs() {
        return indicacionUnirs;
    }

    public void setIndicacionUnirs(Boolean indicacionUnirs) {
        this.indicacionUnirs = indicacionUnirs;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public Integer getConsecOrdenPrincActivo() {
        return consecOrdenPrincActivo;
    }

    public void setConsecOrdenPrincActivo(Integer consecOrdenPrincActivo) {
        this.consecOrdenPrincActivo = consecOrdenPrincActivo;
    }

    public Boolean getEnFaseExperimental() {
        return enFaseExperimental;
    }

    public void setEnFaseExperimental(Boolean enFaseExperimental) {
        this.enFaseExperimental = enFaseExperimental;
    }

    public String getCantidadMedPrincipioActivo() {
        return cantidadMedPrincipioActivo;
    }

    public void setCantidadMedPrincipioActivo(String cantidadMedPrincipioActivo) {
        this.cantidadMedPrincipioActivo = cantidadMedPrincipioActivo;
    }

    public Boolean getDescMedPbsUpc() {
        return descMedPbsUpc;
    }

    public void setDescMedPbsUpc(Boolean descMedPbsUpc) {
        this.descMedPbsUpc = descMedPbsUpc;
    }

    public Boolean getDescartoNoExistePbs() {
        return descartoNoExistePbs;
    }

    public void setDescartoNoExistePbs(Boolean descartoNoExistePbs) {
        this.descartoNoExistePbs = descartoNoExistePbs;
    }

    public Boolean getListaNoUsoSanitarioUnirs() {
        return listaNoUsoSanitarioUnirs;
    }

    public void setListaNoUsoSanitarioUnirs(Boolean listaNoUsoSanitarioUnirs) {
        this.listaNoUsoSanitarioUnirs = listaNoUsoSanitarioUnirs;
    }

    public Boolean getExistentePbsCargoUpc() {
        return existentePbsCargoUpc;
    }

    public void setExistentePbsCargoUpc(Boolean existentePbsCargoUpc) {
        this.existentePbsCargoUpc = existentePbsCargoUpc;
    }

    public Boolean getFinancPbsCargoUpc() {
        return financPbsCargoUpc;
    }

    public void setFinancPbsCargoUpc(Boolean financPbsCargoUpc) {
        this.financPbsCargoUpc = financPbsCargoUpc;
    }

    public Boolean getUtilizoNutriMedExistentePbsUpc() {
        return utilizoNutriMedExistentePbsUpc;
    }

    public void setUtilizoNutriMedExistentePbsUpc(Boolean utilizoNutriMedExistentePbsUpc) {
        this.utilizoNutriMedExistentePbsUpc = utilizoNutriMedExistentePbsUpc;
    }

    public Boolean getEvidenciaEfiEfecClinica() {
        return evidenciaEfiEfecClinica;
    }

    public void setEvidenciaEfiEfecClinica(Boolean evidenciaEfiEfecClinica) {
        this.evidenciaEfiEfecClinica = evidenciaEfiEfecClinica;
    }

    public Boolean getExistePbsCargoUpc() {
        return existePbsCargoUpc;
    }

    public void setExistePbsCargoUpc(Boolean existePbsCargoUpc) {
        this.existePbsCargoUpc = existePbsCargoUpc;
    }

    public Boolean getObsReaccPbsCargoUpc() {
        return obsReaccPbsCargoUpc;
    }

    public void setObsReaccPbsCargoUpc(Boolean obsReaccPbsCargoUpc) {
        this.obsReaccPbsCargoUpc = obsReaccPbsCargoUpc;
    }

    public Boolean getDescartoEvidenciaEfiEfecClinica() {
        return descartoEvidenciaEfiEfecClinica;
    }

    public void setDescartoEvidenciaEfiEfecClinica(Boolean descartoEvidenciaEfiEfecClinica) {
        this.descartoEvidenciaEfiEfecClinica = descartoEvidenciaEfiEfecClinica;
    }

    public Boolean getRegAprobAutClin() {
        return regAprobAutClin;
    }

    public void setRegAprobAutClin(Boolean regAprobAutClin) {
        this.regAprobAutClin = regAprobAutClin;
    }

    public Boolean getDescartoReaccionesAdversas() {
        return descartoReaccionesAdversas;
    }

    public void setDescartoReaccionesAdversas(Boolean descartoReaccionesAdversas) {
        this.descartoReaccionesAdversas = descartoReaccionesAdversas;
    }

    public Boolean getCubiertoPbsCargoUpc() {
        return cubiertoPbsCargoUpc;
    }

    public void setCubiertoPbsCargoUpc(Boolean cubiertoPbsCargoUpc) {
        this.cubiertoPbsCargoUpc = cubiertoPbsCargoUpc;
    }

    public Boolean getPbsUtilizado() {
        return pbsUtilizado;
    }

    public void setPbsUtilizado(Boolean pbsUtilizado) {
        this.pbsUtilizado = pbsUtilizado;
    }

    public Boolean getResultSatisPrev() {
        return resultSatisPrev;
    }

    public void setResultSatisPrev(Boolean resultSatisPrev) {
        this.resultSatisPrev = resultSatisPrev;
    }

    public Boolean getRegistroInvima() {
        return registroInvima;
    }

    public void setRegistroInvima(Boolean registroInvima) {
        this.registroInvima = registroInvima;
    }

    @Override
    public String toString() {
        return "MpPrescripcionMedicamento{" + "id=" + id + ", idTransaccion=" + idTransaccion + ", idDireccionamiento=" + idDireccionamiento + ", estado=" + estado + ", estadoJuntaProfesionales=" + estadoJuntaProfesionales + ", consecutivoOrden=" + consecutivoOrden + ", tipoTecnologia=" + tipoTecnologia + ", tipoMedicamento=" + tipoMedicamento + ", tipoPrestacion=" + tipoPrestacion + ", medPbsUtilizado=" + medPbsUtilizado + ", causaSolicitud1=" + causaSolicitud1 + ", causaSolicitud2=" + causaSolicitud2 + ", causaSolicitud3=" + causaSolicitud3 + ", causaSolicitud4=" + causaSolicitud4 + ", causaSolicitud5=" + causaSolicitud5 + ", causaSolicitud6=" + causaSolicitud6 + ", razonCausaSolicitud31=" + razonCausaSolicitud31 + ", descripcionRazon31=" + descripcionRazon31 + ", razonCausaSolicitud32=" + razonCausaSolicitud32 + ", descripcionRazon32=" + descripcionRazon32 + ", razonCausaSolicitud41=" + razonCausaSolicitud41 + ", descripcionRazon41=" + descripcionRazon41 + ", razonCausaSolicitud42=" + razonCausaSolicitud42 + ", descripcionRazon42=" + descripcionRazon42 + ", razonCausaSolicitud43=" + razonCausaSolicitud43 + ", descripcionRazon43=" + descripcionRazon43 + ", razonCausaSolicitud44=" + razonCausaSolicitud44 + ", descripcionRazon44=" + descripcionRazon44 + ", razonCausaSolicitud51=" + razonCausaSolicitud51 + ", descripcionRazon51=" + descripcionRazon51 + ", razonCausaSolicitud52=" + razonCausaSolicitud52 + ", descripcionRazon52=" + descripcionRazon52 + ", razonCausaSolicitud53=" + razonCausaSolicitud53 + ", descripcionRazon53=" + descripcionRazon53 + ", razonCausaSolicitud54=" + razonCausaSolicitud54 + ", descripcionRazon54=" + descripcionRazon54 + ", razonCausaSolicitud5=" + razonCausaSolicitud5 + ", descripcionRazon5=" + descripcionRazon5 + ", descripcionMedicamentoPrincipioActivo=" + descripcionMedicamentoPrincipioActivo + ", codigoFormulaFarmaceutica=" + codigoFormulaFarmaceutica + ", codigoViaAdministracion=" + codigoViaAdministracion + ", justificacionNoPbs=" + justificacionNoPbs + ", dosis=" + dosis + ", dosisUnidadMedida=" + dosisUnidadMedida + ", numeroFrecuenciaAdministracion=" + numeroFrecuenciaAdministracion + ", codigoFrecuenciaAdministracion=" + codigoFrecuenciaAdministracion + ", indicacionesEspeciales=" + indicacionesEspeciales + ", cantidadTratamiento=" + cantidadTratamiento + ", duracionTratamiento=" + duracionTratamiento + ", cantidadTotalFormulada=" + cantidadTotalFormulada + ", unidadFarmaceuticaCantidadTotal=" + unidadFarmaceuticaCantidadTotal + ", indicacionRecibida=" + indicacionRecibida + ", esDiagnosticoVih=" + esDiagnosticoVih + ", esDiagnosticoCancer=" + esDiagnosticoCancer + ", esDiagnosticoEnfermedadRenal=" + esDiagnosticoEnfermedadRenal + ", esDiagnosticoDesnutricion=" + esDiagnosticoDesnutricion + ", tipoProductoNutricional=" + tipoProductoNutricional + ", descripcionProductoNutricional=" + descripcionProductoNutricional + ", cantidadTotalEntrega=" + cantidadTotalEntrega + ", entregados=" + entregados + ", pendientes=" + pendientes + ", fechaDireccionamiento=" + fechaDireccionamiento + ", fechaMaximaEntrega=" + fechaMaximaEntrega + ", maeProductosNutricionalesId=" + maeProductosNutricionalesId + ", maeProductosNutricionalesCodigo=" + maeProductosNutricionalesCodigo + ", maeProductosNutricionalesValor=" + maeProductosNutricionalesValor + ", valorUnitario=" + valorUnitario + ", direccionado=" + direccionado + ", cicloFacturacion=" + cicloFacturacion + ", codFacturaIps=" + codFacturaIps + ", justificacionTecJunta=" + justificacionTecJunta + ", modJunta=" + modJunta + ", numActaJunta=" + numActaJunta + ", reaccionesAdversasPaciente=" + reaccionesAdversasPaciente + ", cantidadDireccionada=" + cantidadDireccionada + ", cantidadMinimaDispensada=" + cantidadMinimaDispensada + ", cantidadPrescrita=" + cantidadPrescrita + ", codigoMipresEntregar=" + codigoMipresEntregar + ", codFormulada=" + codFormulada + ", estadoAuditoria=" + estadoAuditoria + ", consecutivoJuntaTecnologia=" + consecutivoJuntaTecnologia + ", fechaActaJunta=" + fechaActaJunta + ", estadoPrescripcion=" + estadoPrescripcion + ", indicacionUnirs=" + indicacionUnirs + ", formaFarmaceutica=" + formaFarmaceutica + ", consecOrdenPrincActivo=" + consecOrdenPrincActivo + ", enFaseExperimental=" + enFaseExperimental + ", cantidadMedPrincipioActivo=" + cantidadMedPrincipioActivo + ", descMedPbsUpc=" + descMedPbsUpc + ", descartoNoExistePbs=" + descartoNoExistePbs + ", listaNoUsoSanitarioUnirs=" + listaNoUsoSanitarioUnirs + ", existentePbsCargoUpc=" + existentePbsCargoUpc + ", financPbsCargoUpc=" + financPbsCargoUpc + ", utilizoNutriMedExistentePbsUpc=" + utilizoNutriMedExistentePbsUpc + ", evidenciaEfiEfecClinica=" + evidenciaEfiEfecClinica + ", existePbsCargoUpc=" + existePbsCargoUpc + ", obsReaccPbsCargoUpc=" + obsReaccPbsCargoUpc + ", descartoEvidenciaEfiEfecClinica=" + descartoEvidenciaEfiEfecClinica + ", regAprobAutClin=" + regAprobAutClin + ", descartoReaccionesAdversas=" + descartoReaccionesAdversas + ", cubiertoPbsCargoUpc=" + cubiertoPbsCargoUpc + ", pbsUtilizado=" + pbsUtilizado + '}';
    }

}
