package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author bsGomez
 */
public class MpNoDireccionado extends Auditoria {

    public static final int ESTADO_ANULADO = 0;
    public static final int ESTADO_ACTIVO = 1;
    public static final int ESTADO_PROCESADO = 2;
    public static final int ESTADO_CREADO = 3;
    public static final int ESTADO_SOLICITUD_ANULACION = 4;

    private Integer id;
    private int tipoTecnologia;
    private String justificacionNoDireccionamiento;
    private String numeroPrescripcionAsociada;
    private Integer conTecAsociada;
    private Date fecNoDireccionamiento;
    private int estadoNoDireccionamiento;
    private Date fechaAnulacion;
    private Integer consecutivoTecnologia;
    private Integer idNoDireccionamiento;
    private int codigoNoDireccionamiento;
    private MpPrescripcionInsumo mpPrescripcionInsumosId;
    private MpPrescripcionMedicamento mpPrescripcionMedicamentosId;
    private MpPrescripcionTecnologia mpPrescripcionTecnologiasId;
    private MpPrescripcion mpPrescripcionesId;
    private String respuesAnula;
    private String usuarioAnula;
    private String terminalAnula;
    private Date fechaHoraAnula;
    private Integer idTransaccion;

    public MpNoDireccionado() {
    }

    public MpNoDireccionado(Integer id) {
        this.id = id;
    }

    public MpNoDireccionado(Integer id, int tipoTecnologia, String justificacionNoDireccionamiento, String numeroPrescripcionAsociada, Integer conTecAsociada, Date fecNoDireccionamiento, int estadoNoDireccionamiento, Date fechaAnulacion, Integer consecutivoTecnologia, Integer idNoDireccionamiento, int codigoNoDireccionamiento, MpPrescripcionInsumo mpPrescripcionInsumosId, MpPrescripcionMedicamento mpPrescripcionMedicamentosId, MpPrescripcionTecnologia mpPrescripcionTecnologiasId, MpPrescripcion mpPrescripcionesId, String respuesAnula, String usuarioAnula, String terminalAnula, Date fechaHoraAnula) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
        this.justificacionNoDireccionamiento = justificacionNoDireccionamiento;
        this.numeroPrescripcionAsociada = numeroPrescripcionAsociada;
        this.conTecAsociada = conTecAsociada;
        this.fecNoDireccionamiento = fecNoDireccionamiento;
        this.estadoNoDireccionamiento = estadoNoDireccionamiento;
        this.fechaAnulacion = fechaAnulacion;
        this.consecutivoTecnologia = consecutivoTecnologia;
        this.idNoDireccionamiento = idNoDireccionamiento;
        this.codigoNoDireccionamiento = codigoNoDireccionamiento;
        this.mpPrescripcionInsumosId = mpPrescripcionInsumosId;
        this.mpPrescripcionMedicamentosId = mpPrescripcionMedicamentosId;
        this.mpPrescripcionTecnologiasId = mpPrescripcionTecnologiasId;
        this.mpPrescripcionesId = mpPrescripcionesId;
        this.respuesAnula = respuesAnula;
        this.usuarioAnula = usuarioAnula;
        this.terminalAnula = terminalAnula;
        this.fechaHoraAnula = fechaHoraAnula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public String getJustificacionNoDireccionamiento() {
        return justificacionNoDireccionamiento;
    }

    public void setJustificacionNoDireccionamiento(String justificacionNoDireccionamiento) {
        this.justificacionNoDireccionamiento = justificacionNoDireccionamiento;
    }

    public String getNumeroPrescripcionAsociada() {
        return numeroPrescripcionAsociada;
    }

    public void setNumeroPrescripcionAsociada(String numeroPrescripcionAsociada) {
        this.numeroPrescripcionAsociada = numeroPrescripcionAsociada;
    }

    public Integer getConTecAsociada() {
        return conTecAsociada;
    }

    public void setConTecAsociada(Integer conTecAsociada) {
        this.conTecAsociada = conTecAsociada;
    }

    public Date getFecNoDireccionamiento() {
        return fecNoDireccionamiento;
    }

    public void setFecNoDireccionamiento(Date fecNoDireccionamiento) {
        this.fecNoDireccionamiento = fecNoDireccionamiento;
    }

    public int getEstadoNoDireccionamiento() {
        return estadoNoDireccionamiento;
    }

    public void setEstadoNoDireccionamiento(int estadoNoDireccionamiento) {
        this.estadoNoDireccionamiento = estadoNoDireccionamiento;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public Integer getConsecutivoTecnologia() {
        return consecutivoTecnologia;
    }

    public void setConsecutivoTecnologia(Integer consecutivoTecnologia) {
        this.consecutivoTecnologia = consecutivoTecnologia;
    }

    public Integer getIdNoDireccionamiento() {
        return idNoDireccionamiento;
    }

    public void setIdNoDireccionamiento(Integer idNoDireccionamiento) {
        this.idNoDireccionamiento = idNoDireccionamiento;
    }

    public int getCodigoNoDireccionamiento() {
        return codigoNoDireccionamiento;
    }

    public void setCodigoNoDireccionamiento(int codigoNoDireccionamiento) {
        this.codigoNoDireccionamiento = codigoNoDireccionamiento;
    }

    public MpPrescripcionInsumo getMpPrescripcionInsumosId() {
        return mpPrescripcionInsumosId;
    }

    public void setMpPrescripcionInsumosId(MpPrescripcionInsumo mpPrescripcionInsumosId) {
        this.mpPrescripcionInsumosId = mpPrescripcionInsumosId;
    }

    public MpPrescripcionMedicamento getMpPrescripcionMedicamentosId() {
        return mpPrescripcionMedicamentosId;
    }

    public void setMpPrescripcionMedicamentosId(MpPrescripcionMedicamento mpPrescripcionMedicamentosId) {
        this.mpPrescripcionMedicamentosId = mpPrescripcionMedicamentosId;
    }

    public MpPrescripcionTecnologia getMpPrescripcionTecnologiasId() {
        return mpPrescripcionTecnologiasId;
    }

    public void setMpPrescripcionTecnologiasId(MpPrescripcionTecnologia mpPrescripcionTecnologiasId) {
        this.mpPrescripcionTecnologiasId = mpPrescripcionTecnologiasId;
    }

    public MpPrescripcion getMpPrescripcionesId() {
        return mpPrescripcionesId;
    }

    public void setMpPrescripcionesId(MpPrescripcion mpPrescripcionesId) {
        this.mpPrescripcionesId = mpPrescripcionesId;
    }

    public String getRespuesAnula() {
        return respuesAnula;
    }

    public void setRespuesAnula(String respuesAnula) {
        this.respuesAnula = respuesAnula;
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

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    @Override
    public String toString() {
        return "MpNoDireccionado{" + "id=" + id + ", tipoTecnologia=" + tipoTecnologia + ", justificacionNoDireccionamiento=" + justificacionNoDireccionamiento + ", numeroPrescripcionAsociada=" + numeroPrescripcionAsociada + ", conTecAsociada=" + conTecAsociada + ", fecNoDireccionamiento=" + fecNoDireccionamiento + ", estadoNoDireccionamiento=" + estadoNoDireccionamiento + ", fechaAnulacion=" + fechaAnulacion + ", consecutivoTecnologia=" + consecutivoTecnologia + ", idNoDireccionamiento=" + idNoDireccionamiento + ", codigoNoDireccionamiento=" + codigoNoDireccionamiento + ", mpPrescripcionInsumosId=" + mpPrescripcionInsumosId + ", mpPrescripcionMedicamentosId=" + mpPrescripcionMedicamentosId + ", mpPrescripcionTecnologiasId=" + mpPrescripcionTecnologiasId + ", mpPrescripcionesId=" + mpPrescripcionesId + '}';
    }

}
