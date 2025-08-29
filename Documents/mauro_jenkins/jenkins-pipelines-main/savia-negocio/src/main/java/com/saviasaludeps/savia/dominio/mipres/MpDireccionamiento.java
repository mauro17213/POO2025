package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class MpDireccionamiento extends Auditoria {

    public final static int ESTADO_DIRECCIONAMIENTO_ANULADO = 0;
    public final static int ESTADO_DIRECCIONAMIENTO_DIRECCIONADO = 1;
    public final static int ESTADO_DIRECCIONAMIENTO_PROGRAMADO = 1;
    public final static int ESTADO_DIRECCIONAMIENTO_CREADO = 3;
    public final static int ESTADO_DIRECCIONAMIENTO_DIRECCIONANDO = 4;
    public final static int ESTADO_NODIRECCIONAMIENTO_CREADO = 3;
    public final static int ESTADO_NODIRECCIONAMIENTO_DIRECCIONADO = 1;
    private Integer id;
    private Integer tipoTecnologia;
    private Integer maeTipoDocumentoPrestadorId;
    private String maeTipoDocumentoPrestadorCodigo;
    private String maeTipoDocumentoPrestadorValor;
    private BigInteger prestadorNumeroDocumento;
    private String prestadorNumeroDocumentoStr;
    private String respuestaDireccionamiento;
    private String prestadorRazonSocial;// razonsocial del prestador
    private String sedeCodigoHabilitacion;//codigo habilitacion sede
    private String sedeDireccionPrestador;
    private String sedeTelefonoPrestador;
    private Integer estado; // activo
    private Integer idTransaccion;
    private Integer idDireccionamiento;
    private Date fechaDireccionamiento;
    private Date fechaMaxEntrega;
    private Integer entregaCantidad;
    private Integer consecutivoEntrega;
    private Integer entregaTotal;
    private Integer entregadoNumero;
    private Integer entregadoTotal;
    private Integer entregadoPendiente;
    private String justificacionDireccionamiento;
    private Boolean envioCorreoAuto;
    private Integer causaNoEntregaCod;
    private String codigoMpEntrega;
    private String codigoMpPropio;
    private Integer subEntrega;
    private String codigoPrestadorSede;
    private Integer ubicacionSedeId;//ubicacion id sede
    private String ubicacionSedeIdStr;//ubicacion id sede
    private Integer maeRegionSedeId;
    private String maeRegionSedeCodigo;
    private String maeRegionSedeValor;
    private String direccionSede;//direccion
    private String nombreSede;//nombre sede o razzon social
    private String codigoSede;//
    private String codigoHabilitacionSede;
    private String faxSede;
    private String telefonoCitasSede;
    private String correoElectronicoSede;
    private String telefonoAdministrativoSede;
    private Boolean direccionamientoEsEstadoAnulado;
    private Date fechaAnulacionDireccionamiento;
    private Integer maeTipoDocumentoPacienteId;
    private String maeTipoDocumentoPacienteCodigo;
    private String maeTipoDocumentoPacienteValor;
    private String numeroDocumentoPaciente;
    private Boolean preeliminado;
    private Boolean eliminado;
    private String usuarioAnula;
    private String terminalAnula;
    private Date fechaHoraAnula;
    private Date fechaEnvioAuto;

    private Integer codigoTipoDireccionamiento;
    private Integer codigoEntregaParcial;
    private Integer codigoEntregaDiferida;
    private Boolean esEntregaParcial = false;
    private Boolean esEntregaDiferida = false;
    private String numeroPrescripcionAso;
    private String consecutivoTecAsociada;
    private Boolean ultimoDireccionamiento;

    private Boolean tieneCotizacion;
    private Integer idCotizacion = null;

    private MpPrescripcion mpPrescripcionId;
    private MpPrescripcionTecnologia mpPrescripcionTecnologiaId;
    private MpPrescripcionMedicamento mpPrescripcionMedicamentoId;
    private MpPrescripcionInsumo mpPrescripcionInsumoId;
    private Integer numeroDeEntregas;//resultado del calculo para convertir cuantas entregas se permite
    private Integer cronoEntregas;//dias meses años semanas
    private BigDecimal valorTecContratada;

    // Variables auxiliares
    private String tipoTecnologiaStr;
    private String valorTecnologia;
    private String estadoTecnologia;

    public MpDireccionamiento() {
    }

    public MpDireccionamiento(Integer id) {
        this.id = id;
    }

    public MpDireccionamiento(Integer id, Integer tipoTecnologia, Integer maeTipoDocumentoPrestadorId, String maeTipoDocumentoPrestadorCodigo, String maeTipoDocumentoPrestadorValor, BigInteger prestadorNumeroDocumento, String prestadorNumeroDocumentoStr, String prestadorRazonSocial, String sedeCodigoHabilitacion, String sedeDireccionPrestador, String sedeTelefonoPrestador, Integer estado, Integer idTransaccion, Date fechaDireccionamiento, Date fechaMaxEntrega, Integer entregaCantidad, Integer entregaTotal, Integer entregadoNumero, Integer entregadoTotal, Integer entregadoPendiente, String justificacionDireccionamiento, Boolean envioCorreoAuto, Integer causaNoEntregaCod, String codigoMpEntrega, String codigoMpPropio, Integer subEntrega, String codigoPrestadorSede, Integer ubicacionSedeId, String ubicacionSedeIdStr, Integer maeRegionSedeId, String maeRegionSedeCodigo, String maeRegionSedeValor, String direccionSede, String nombreSede, String codigoSede, String codigoHabilitacionSede, String faxSede, String telefonoCitasSede, String correoElectronicoSede, String telefonoAdministrativoSede, Boolean direccionamientoEsEstadoAnulado, Date fechaAnulacionDireccionamiento, Integer maeTipoDocumentoPacienteId, String maeTipoDocumentoPacienteCodigo, String maeTipoDocumentoPacienteValor, String numeroDocumentoPaciente, Boolean preeliminado, Boolean eliminado, String usuarioAnula, String terminalAnula, Date fechaHoraAnula, Date fechaEnvioAuto, Integer codigoTipoDireccionamiento, Integer codigoEntregaParcial, Integer codigoEntregaDiferida, String numeroPrescripcionAso, String consecutivoTecAsociada, MpPrescripcion mpPrescripcionId, MpPrescripcionTecnologia mpPrescripcionTecnologiaId, MpPrescripcionMedicamento mpPrescripcionMedicamentoId, MpPrescripcionInsumo mpPrescripcionInsumoId, Integer numeroDeEntregas, Integer cronoEntregas) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
        this.maeTipoDocumentoPrestadorId = maeTipoDocumentoPrestadorId;
        this.maeTipoDocumentoPrestadorCodigo = maeTipoDocumentoPrestadorCodigo;
        this.maeTipoDocumentoPrestadorValor = maeTipoDocumentoPrestadorValor;
        this.prestadorNumeroDocumento = prestadorNumeroDocumento;
        this.prestadorNumeroDocumentoStr = prestadorNumeroDocumentoStr;
        this.prestadorRazonSocial = prestadorRazonSocial;
        this.sedeCodigoHabilitacion = sedeCodigoHabilitacion;
        this.sedeDireccionPrestador = sedeDireccionPrestador;
        this.sedeTelefonoPrestador = sedeTelefonoPrestador;
        this.estado = estado;
        this.idTransaccion = idTransaccion;
        this.fechaDireccionamiento = fechaDireccionamiento;
        this.fechaMaxEntrega = fechaMaxEntrega;
        this.entregaCantidad = entregaCantidad;
        this.entregaTotal = entregaTotal;
        this.entregadoNumero = entregadoNumero;
        this.entregadoTotal = entregadoTotal;
        this.entregadoPendiente = entregadoPendiente;
        this.justificacionDireccionamiento = justificacionDireccionamiento;
        this.envioCorreoAuto = envioCorreoAuto;
        this.causaNoEntregaCod = causaNoEntregaCod;
        this.codigoMpEntrega = codigoMpEntrega;
        this.codigoMpPropio = codigoMpPropio;
        this.subEntrega = subEntrega;
        this.codigoPrestadorSede = codigoPrestadorSede;
        this.ubicacionSedeId = ubicacionSedeId;
        this.ubicacionSedeIdStr = ubicacionSedeIdStr;
        this.maeRegionSedeId = maeRegionSedeId;
        this.maeRegionSedeCodigo = maeRegionSedeCodigo;
        this.maeRegionSedeValor = maeRegionSedeValor;
        this.direccionSede = direccionSede;
        this.nombreSede = nombreSede;
        this.codigoSede = codigoSede;
        this.codigoHabilitacionSede = codigoHabilitacionSede;
        this.faxSede = faxSede;
        this.telefonoCitasSede = telefonoCitasSede;
        this.correoElectronicoSede = correoElectronicoSede;
        this.telefonoAdministrativoSede = telefonoAdministrativoSede;
        this.direccionamientoEsEstadoAnulado = direccionamientoEsEstadoAnulado;
        this.fechaAnulacionDireccionamiento = fechaAnulacionDireccionamiento;
        this.maeTipoDocumentoPacienteId = maeTipoDocumentoPacienteId;
        this.maeTipoDocumentoPacienteCodigo = maeTipoDocumentoPacienteCodigo;
        this.maeTipoDocumentoPacienteValor = maeTipoDocumentoPacienteValor;
        this.numeroDocumentoPaciente = numeroDocumentoPaciente;
        this.preeliminado = preeliminado;
        this.eliminado = eliminado;
        this.usuarioAnula = usuarioAnula;
        this.terminalAnula = terminalAnula;
        this.fechaHoraAnula = fechaHoraAnula;
        this.fechaEnvioAuto = fechaEnvioAuto;
        this.codigoTipoDireccionamiento = codigoTipoDireccionamiento;
        this.codigoEntregaParcial = codigoEntregaParcial;
        this.codigoEntregaDiferida = codigoEntregaDiferida;
        this.numeroPrescripcionAso = numeroPrescripcionAso;
        this.consecutivoTecAsociada = consecutivoTecAsociada;
        this.mpPrescripcionId = mpPrescripcionId;
        this.mpPrescripcionTecnologiaId = mpPrescripcionTecnologiaId;
        this.mpPrescripcionMedicamentoId = mpPrescripcionMedicamentoId;
        this.mpPrescripcionInsumoId = mpPrescripcionInsumoId;
        this.numeroDeEntregas = numeroDeEntregas;
        this.cronoEntregas = cronoEntregas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getMaeTipoDocumentoPrestadorId() {
        return maeTipoDocumentoPrestadorId;
    }

    public void setMaeTipoDocumentoPrestadorId(Integer maeTipoDocumentoPrestadorId) {
        this.maeTipoDocumentoPrestadorId = maeTipoDocumentoPrestadorId;
    }

    public Integer getNumeroDeEntregas() {
        return numeroDeEntregas;
    }

    public void setNumeroDeEntregas(Integer numeroDeEntregas) {
        this.numeroDeEntregas = numeroDeEntregas;
    }

    public Integer getCronoEntregas() {
        return cronoEntregas;
    }

    public void setCronoEntregas(Integer cronoEntregas) {
        this.cronoEntregas = cronoEntregas;
    }

    public BigDecimal getValorTecContratada() {
        return valorTecContratada;
    }

    public void setValorTecContratada(BigDecimal valorTecContratada) {
        this.valorTecContratada = valorTecContratada;
    }

    public String getMaeTipoDocumentoPrestadorCodigo() {
        return maeTipoDocumentoPrestadorCodigo;
    }

    public void setMaeTipoDocumentoPrestadorCodigo(String maeTipoDocumentoPrestadorCodigo) {
        this.maeTipoDocumentoPrestadorCodigo = maeTipoDocumentoPrestadorCodigo;
    }

    public String getMaeTipoDocumentoPrestadorValor() {
        return maeTipoDocumentoPrestadorValor;
    }

    public void setMaeTipoDocumentoPrestadorValor(String maeTipoDocumentoPrestadorValor) {
        this.maeTipoDocumentoPrestadorValor = maeTipoDocumentoPrestadorValor;
    }

    public BigInteger getPrestadorNumeroDocumento() {
        return prestadorNumeroDocumento;
    }

    public void setPrestadorNumeroDocumento(BigInteger prestadorNumeroDocumento) {
        this.prestadorNumeroDocumento = prestadorNumeroDocumento;
    }

    public String getPrestadorRazonSocial() {
        return prestadorRazonSocial;
    }

    public void setPrestadorRazonSocial(String prestadorRazonSocial) {
        this.prestadorRazonSocial = prestadorRazonSocial;
    }

    public String getSedeCodigoHabilitacion() {
        return sedeCodigoHabilitacion;
    }

    public void setSedeCodigoHabilitacion(String sedeCodigoHabilitacion) {
        this.sedeCodigoHabilitacion = sedeCodigoHabilitacion;
    }

    public String getSedeDireccionPrestador() {
        return sedeDireccionPrestador;
    }

    public void setSedeDireccionPrestador(String sedeDireccionPrestador) {
        this.sedeDireccionPrestador = sedeDireccionPrestador;
    }

    public String getSedeTelefonoPrestador() {
        return sedeTelefonoPrestador;
    }

    public void setSedeTelefonoPrestador(String sedeTelefonoPrestador) {
        this.sedeTelefonoPrestador = sedeTelefonoPrestador;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Date getFechaDireccionamiento() {
        return fechaDireccionamiento;
    }

    public void setFechaDireccionamiento(Date fechaDireccionamiento) {
        this.fechaDireccionamiento = fechaDireccionamiento;
    }

    public Date getFechaMaxEntrega() {
        return fechaMaxEntrega;
    }

    public void setFechaMaxEntrega(Date fechaMaxEntrega) {
        this.fechaMaxEntrega = fechaMaxEntrega;
    }

    public Integer getEntregaCantidad() {
        return entregaCantidad;
    }

    public void setEntregaCantidad(Integer entregaCantidad) {
        this.entregaCantidad = entregaCantidad;
    }

    public Integer getConsecutivoEntrega() {
        return consecutivoEntrega;
    }

    public void setConsecutivoEntrega(Integer consecutivoEntrega) {
        this.consecutivoEntrega = consecutivoEntrega;
    }

    public Integer getEntregaTotal() {
        return entregaTotal;
    }

    public void setEntregaTotal(Integer entregaTotal) {
        this.entregaTotal = entregaTotal;
    }

    public Integer getEntregadoNumero() {
        return entregadoNumero;
    }

    public void setEntregadoNumero(Integer entregadoNumero) {
        this.entregadoNumero = entregadoNumero;
    }

    public Integer getEntregadoTotal() {
        return entregadoTotal;
    }

    public void setEntregadoTotal(Integer entregadoTotal) {
        this.entregadoTotal = entregadoTotal;
    }

    public Integer getEntregadoPendiente() {
        return entregadoPendiente;
    }

    public void setEntregadoPendiente(Integer entregadoPendiente) {
        this.entregadoPendiente = entregadoPendiente;
    }

    public String getJustificacionDireccionamiento() {
        return justificacionDireccionamiento;
    }

    public void setJustificacionDireccionamiento(String justificacionDireccionamiento) {
        this.justificacionDireccionamiento = justificacionDireccionamiento;
    }

    public Boolean getEnvioCorreoAuto() {
        return envioCorreoAuto;
    }

    public void setEnvioCorreoAuto(Boolean envioCorreoAuto) {
        this.envioCorreoAuto = envioCorreoAuto;
    }

    public Integer getCausaNoEntregaCod() {
        return causaNoEntregaCod;
    }

    public void setCausaNoEntregaCod(Integer causaNoEntregaCod) {
        this.causaNoEntregaCod = causaNoEntregaCod;
    }

    public String getCodigoMpEntrega() {
        return codigoMpEntrega;
    }

    public void setCodigoMpEntrega(String codigoMpEntrega) {
        this.codigoMpEntrega = codigoMpEntrega;
    }

    public String getCodigoMpPropio() {
        return codigoMpPropio;
    }

    public void setCodigoMpPropio(String codigoMpPropio) {
        this.codigoMpPropio = codigoMpPropio;
    }

    public Integer getSubEntrega() {
        return subEntrega;
    }

    public void setSubEntrega(Integer subEntrega) {
        this.subEntrega = subEntrega;
    }

    public String getCodigoPrestadorSede() {
        return codigoPrestadorSede;
    }

    public void setCodigoPrestadorSede(String codigoPrestadorSede) {
        this.codigoPrestadorSede = codigoPrestadorSede;
    }

    public Integer getUbicacionSedeId() {
        return ubicacionSedeId;
    }

    public void setUbicacionSedeId(Integer ubicacionSedeId) {
        this.ubicacionSedeId = ubicacionSedeId;
    }

    public String getUbicacionSedeIdStr() {
        return ubicacionSedeIdStr;
    }

    public void setUbicacionSedeIdStr(String ubicacionSedeIdStr) {
        this.ubicacionSedeIdStr = ubicacionSedeIdStr;
    }

    public Integer getMaeRegionSedeId() {
        return maeRegionSedeId;
    }

    public void setMaeRegionSedeId(Integer maeRegionSedeId) {
        this.maeRegionSedeId = maeRegionSedeId;
    }

    public String getMaeRegionSedeCodigo() {
        return maeRegionSedeCodigo;
    }

    public void setMaeRegionSedeCodigo(String maeRegionSedeCodigo) {
        this.maeRegionSedeCodigo = maeRegionSedeCodigo;
    }

    public String getMaeRegionSedeValor() {
        return maeRegionSedeValor;
    }

    public void setMaeRegionSedeValor(String maeRegionSedeValor) {
        this.maeRegionSedeValor = maeRegionSedeValor;
    }

    public String getDireccionSede() {
        return direccionSede;
    }

    public void setDireccionSede(String direccionSede) {
        this.direccionSede = direccionSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(String codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getCodigoHabilitacionSede() {
        return codigoHabilitacionSede;
    }

    public void setCodigoHabilitacionSede(String codigoHabilitacionSede) {
        this.codigoHabilitacionSede = codigoHabilitacionSede;
    }

    public String getFaxSede() {
        return faxSede;
    }

    public void setFaxSede(String faxSede) {
        this.faxSede = faxSede;
    }

    public String getTelefonoCitasSede() {
        return telefonoCitasSede;
    }

    public void setTelefonoCitasSede(String telefonoCitasSede) {
        this.telefonoCitasSede = telefonoCitasSede;
    }

    public String getCorreoElectronicoSede() {
        return correoElectronicoSede;
    }

    public void setCorreoElectronicoSede(String correoElectronicoSede) {
        this.correoElectronicoSede = correoElectronicoSede;
    }

    public String getTelefonoAdministrativoSede() {
        return telefonoAdministrativoSede;
    }

    public void setTelefonoAdministrativoSede(String telefonoAdministrativoSede) {
        this.telefonoAdministrativoSede = telefonoAdministrativoSede;
    }

    public Boolean getDireccionamientoEsEstadoAnulado() {
        return direccionamientoEsEstadoAnulado;
    }

    public void setDireccionamientoEsEstadoAnulado(Boolean direccionamientoEsEstadoAnulado) {
        this.direccionamientoEsEstadoAnulado = direccionamientoEsEstadoAnulado;
    }

    public Date getFechaAnulacionDireccionamiento() {
        return fechaAnulacionDireccionamiento;
    }

    public void setFechaAnulacionDireccionamiento(Date fechaAnulacionDireccionamiento) {
        this.fechaAnulacionDireccionamiento = fechaAnulacionDireccionamiento;
    }

    public Integer getMaeTipoDocumentoPacienteId() {
        return maeTipoDocumentoPacienteId;
    }

    public void setMaeTipoDocumentoPacienteId(Integer maeTipoDocumentoPacienteId) {
        this.maeTipoDocumentoPacienteId = maeTipoDocumentoPacienteId;
    }

    public String getMaeTipoDocumentoPacienteCodigo() {
        return maeTipoDocumentoPacienteCodigo;
    }

    public void setMaeTipoDocumentoPacienteCodigo(String maeTipoDocumentoPacienteCodigo) {
        this.maeTipoDocumentoPacienteCodigo = maeTipoDocumentoPacienteCodigo;
    }

    public String getMaeTipoDocumentoPacienteValor() {
        return maeTipoDocumentoPacienteValor;
    }

    public void setMaeTipoDocumentoPacienteValor(String maeTipoDocumentoPacienteValor) {
        this.maeTipoDocumentoPacienteValor = maeTipoDocumentoPacienteValor;
    }

    public String getNumeroDocumentoPaciente() {
        return numeroDocumentoPaciente;
    }

    public void setNumeroDocumentoPaciente(String numeroDocumentoPaciente) {
        this.numeroDocumentoPaciente = numeroDocumentoPaciente;
    }

    public Boolean getPreeliminado() {
        return preeliminado;
    }

    public void setPreeliminado(Boolean preeliminado) {
        this.preeliminado = preeliminado;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getUsuarioAnula() {
        return usuarioAnula;
    }

    public void setUsuarioAnula(String usuarioAnula) {
        this.usuarioAnula = usuarioAnula;
    }

    public String getTerminalAnula() {
        return terminalAnula;
    }

    public void setTerminalAnula(String terminalAnula) {
        this.terminalAnula = terminalAnula;
    }

    public Date getFechaHoraAnula() {
        return fechaHoraAnula;
    }

    public void setFechaHoraAnula(Date fechaHoraAnula) {
        this.fechaHoraAnula = fechaHoraAnula;
    }

    public Date getFechaEnvioAuto() {
        return fechaEnvioAuto;
    }

    public void setFechaEnvioAuto(Date fechaEnvioAuto) {
        this.fechaEnvioAuto = fechaEnvioAuto;
    }

    public Integer getCodigoEntregaParcial() {
        return codigoEntregaParcial;
    }

    public void setCodigoEntregaParcial(Integer codigoEntregaPArcial) {
        this.codigoEntregaParcial = codigoEntregaPArcial;
    }

    public Integer getCodigoEntregaDiferida() {
        return codigoEntregaDiferida;
    }

    public void setCodigoEntregaDiferida(Integer codigoEntregaDiferida) {
        this.codigoEntregaDiferida = codigoEntregaDiferida;
    }

    public Boolean getEsEntregaDiferida() {
        return esEntregaDiferida;
    }

    public void setEsEntregaDiferida(Boolean esEntregaDiferida) {
        this.esEntregaDiferida = esEntregaDiferida;
    }

    public String getNumeroPrescripcionAso() {
        return numeroPrescripcionAso;
    }

    public void setNumeroPrescripcionAso(String numeroPrescripcionAso) {
        this.numeroPrescripcionAso = numeroPrescripcionAso;
    }

    public String getConsecutivoTecAsociada() {
        return consecutivoTecAsociada;
    }

    public void setConsecutivoTecAsociada(String consecutivoTecAsociada) {
        this.consecutivoTecAsociada = consecutivoTecAsociada;
    }

    public Boolean getUltimoDireccionamiento() {
        return ultimoDireccionamiento;
    }

    public void setUltimoDireccionamiento(Boolean ultimoDireccionamiento) {
        this.ultimoDireccionamiento = ultimoDireccionamiento;
    }

    public Boolean getTieneCotizacion() {
        return tieneCotizacion;
    }

    public void setTieneCotizacion(Boolean tieneCotizacion) {
        this.tieneCotizacion = tieneCotizacion;
    }

    public Integer getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Integer getCodigoTipoDireccionamiento() {
        return codigoTipoDireccionamiento;
    }

    public void setCodigoTipoDireccionamiento(Integer codigoTipoDireccionamiento) {
        this.codigoTipoDireccionamiento = codigoTipoDireccionamiento;
    }

    public Boolean getEsEntregaParcial() {
        return esEntregaParcial;
    }

    public void setEsEntregaParcial(Boolean esEntregaParcial) {
        this.esEntregaParcial = esEntregaParcial;
    }

    public MpPrescripcion getMpPrescripcionId() {
        if (mpPrescripcionId == null) {
            mpPrescripcionId = new MpPrescripcion();
        }
        return mpPrescripcionId;
    }

    public void setMpPrescripcionId(MpPrescripcion mpPrescripcionId) {
        this.mpPrescripcionId = mpPrescripcionId;
    }

    public MpPrescripcionTecnologia getMpPrescripcionTecnologiaId() {
        if (mpPrescripcionTecnologiaId == null) {
            mpPrescripcionTecnologiaId = new MpPrescripcionTecnologia();
        }
        return mpPrescripcionTecnologiaId;
    }

    public void setMpPrescripcionTecnologiaId(MpPrescripcionTecnologia mpPrescripcionTecnologiaId) {
        this.mpPrescripcionTecnologiaId = mpPrescripcionTecnologiaId;
    }

    public MpPrescripcionMedicamento getMpPrescripcionMedicamentoId() {
        if (mpPrescripcionMedicamentoId == null) {
            mpPrescripcionMedicamentoId = new MpPrescripcionMedicamento();
        }
        return mpPrescripcionMedicamentoId;
    }

    public void setMpPrescripcionMedicamentoId(MpPrescripcionMedicamento mpPrescripcionMedicamentoId) {
        this.mpPrescripcionMedicamentoId = mpPrescripcionMedicamentoId;
    }

    public MpPrescripcionInsumo getMpPrescripcionInsumoId() {
        if (mpPrescripcionInsumoId == null) {
            mpPrescripcionInsumoId = new MpPrescripcionInsumo();
        }
        return mpPrescripcionInsumoId;
    }

    public void setMpPrescripcionInsumoId(MpPrescripcionInsumo mpPrescripcionInsumoId) {
        this.mpPrescripcionInsumoId = mpPrescripcionInsumoId;
    }

    public String getPrestadorNumeroDocumentoStr() {
        return prestadorNumeroDocumentoStr;
    }

    public void setPrestadorNumeroDocumentoStr(String prestadorNumeroDocumentoStr) {
        this.prestadorNumeroDocumentoStr = prestadorNumeroDocumentoStr;
    }

    public String getRespuestaDireccionamiento() {
        return respuestaDireccionamiento;
    }

    public void setRespuestaDireccionamiento(String respuestaDireccionamiento) {
        this.respuestaDireccionamiento = respuestaDireccionamiento;
    }

    public Integer getIdDireccionamiento() {
        return idDireccionamiento;
    }

    public void setIdDireccionamiento(Integer idDireccionamiento) {
        this.idDireccionamiento = idDireccionamiento;
    }

    public String getTipoTecnologiaStr() {
        String valor = "";
        if (tipoTecnologia != null) {
            switch (tipoTecnologia) {
                case 1:
                    valor = "Medicamento";
                    break;
                case 2:
                    valor = "Procedimiento";
                    break;
                case 3:
                    valor = "Dispositivo";
                    break;
                case 4:
                    valor = "Producto Nutricional";
                    break;
                case 5:
                    valor = "Servicio Complementario";
                    break;
                default:
                    break;
            }
        }
        return valor;
    }

    public void setTipoTecnologiaStr(String tipoTecnologiaStr) {
        this.tipoTecnologiaStr = tipoTecnologiaStr;
    }

    public String getValorTecnologia() {
        return valorTecnologia;
    }

    public void setValorTecnologia(String valorTecnologia) {
        this.valorTecnologia = valorTecnologia;
    }

    public String getEstadoTecnologia() {
        return estadoTecnologia;
    }

    public void setEstadoTecnologia(String estadoTecnologia) {
        this.estadoTecnologia = estadoTecnologia;
    }

    @Override
    public String toString() {
        return "MpDireccionamiento{" + "id=" + id + ", tipoTecnologia=" + tipoTecnologia + ", maeTipoDocumentoPrestadorId=" + maeTipoDocumentoPrestadorId + ", maeTipoDocumentoPrestadorCodigo=" + maeTipoDocumentoPrestadorCodigo + ", maeTipoDocumentoPrestadorValor=" + maeTipoDocumentoPrestadorValor + ", prestadorNumeroDocumento=" + prestadorNumeroDocumento + ", prestadorNumeroDocumentoStr=" + prestadorNumeroDocumentoStr + ", prestadorRazonSocial=" + prestadorRazonSocial + ", sedeCodigoHabilitacion=" + sedeCodigoHabilitacion + ", sedeDireccionPrestador=" + sedeDireccionPrestador + ", sedeTelefonoPrestador=" + sedeTelefonoPrestador + ", estado=" + estado + ", idTransaccion=" + idTransaccion + ", idDireccionamiento=" + idDireccionamiento + ", fechaDireccionamiento=" + fechaDireccionamiento + ", fechaMaxEntrega=" + fechaMaxEntrega + ", entregaCantidad=" + entregaCantidad + ", consecutivoEntrega=" + consecutivoEntrega + ", entregaTotal=" + entregaTotal + ", entregadoNumero=" + entregadoNumero + ", entregadoTotal=" + entregadoTotal + ", entregadoPendiente=" + entregadoPendiente + ", justificacionDireccionamiento=" + justificacionDireccionamiento + ", envioCorreoAuto=" + envioCorreoAuto + ", causaNoEntregaCod=" + causaNoEntregaCod + ", codigoMpEntrega=" + codigoMpEntrega + ", codigoMpPropio=" + codigoMpPropio + ", subEntrega=" + subEntrega + ", codigoPrestadorSede=" + codigoPrestadorSede + ", ubicacionSedeId=" + ubicacionSedeId + ", ubicacionSedeIdStr=" + ubicacionSedeIdStr + ", maeRegionSedeId=" + maeRegionSedeId + ", maeRegionSedeCodigo=" + maeRegionSedeCodigo + ", maeRegionSedeValor=" + maeRegionSedeValor + ", direccionSede=" + direccionSede + ", nombreSede=" + nombreSede + ", codigoSede=" + codigoSede + ", codigoHabilitacionSede=" + codigoHabilitacionSede + ", faxSede=" + faxSede + ", telefonoCitasSede=" + telefonoCitasSede + ", correoElectronicoSede=" + correoElectronicoSede + ", telefonoAdministrativoSede=" + telefonoAdministrativoSede + ", direccionamientoEsEstadoAnulado=" + direccionamientoEsEstadoAnulado + ", fechaAnulacionDireccionamiento=" + fechaAnulacionDireccionamiento + ", maeTipoDocumentoPacienteId=" + maeTipoDocumentoPacienteId + ", maeTipoDocumentoPacienteCodigo=" + maeTipoDocumentoPacienteCodigo + ", maeTipoDocumentoPacienteValor=" + maeTipoDocumentoPacienteValor + ", numeroDocumentoPaciente=" + numeroDocumentoPaciente + ", preeliminado=" + preeliminado + ", eliminado=" + eliminado + ", usuarioAnula=" + usuarioAnula + ", terminalAnula=" + terminalAnula + ", fechaHoraAnula=" + fechaHoraAnula + ", fechaEnvioAuto=" + fechaEnvioAuto + ", codigoTipoDireccionamiento=" + codigoTipoDireccionamiento + ", codigoEntregaParcial=" + codigoEntregaParcial + ", codigoEntregaDiferida=" + codigoEntregaDiferida + ", esEntregaParcial=" + esEntregaParcial + ", esEntregaDiferida=" + esEntregaDiferida + ", numeroPrescripcionAso=" + numeroPrescripcionAso + ", consecutivoTecAsociada=" + consecutivoTecAsociada + ", ultimoDireccionamiento=" + ultimoDireccionamiento + ", mpPrescripcionId=" + mpPrescripcionId + ", mpPrescripcionTecnologiaId=" + mpPrescripcionTecnologiaId + ", mpPrescripcionMedicamentoId=" + mpPrescripcionMedicamentoId + ", mpPrescripcionInsumoId=" + mpPrescripcionInsumoId + ", numeroDeEntregas=" + numeroDeEntregas + ", cronoEntregas=" + cronoEntregas + ", tipoTecnologiaStr=" + tipoTecnologiaStr + '}';
    }
}
