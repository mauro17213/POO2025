/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jhonatan Jimenez
 */
public class AusCaso extends Auditoria {
    
    public static String SOLICITUD_ESTADO_EN_GESTION = "4";
    public static String MOTIVO_REAPERTURA_OTRO = "4";
    public static final int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
    
    private Empresa empresa;
    private Integer id;
    private Integer maeSolicitudEstadoId;
    private String maeSolicitudEstadoCodigo;
    private String maeSolicitudEstadoValor;
    private int maeSolicitudTipoId;
    private String maeSolicitudTipoCodigo;
    private String maeSolicitudTipoValor;
    private int maeSolicitudOrigenId;
    private String maeSolicitudOrigenCodigo;
    private String maeSolicitudOrigenValor;
    private String radicado;
    private Integer maeSolicitudPrioridadId;
    private String maeSolicitudPrioridadCodigo;
    private String maeSolicitudPrioridadValor;
    private Integer maeSolicitudEnteControlId;
    private String maeSolicitudEnteControlCodigo;
    private String maeSolicitudEnteControlValor;
    private Integer maeSolicitudRiesgoVidalId;
    private String maeSolicitudRiesgoVidalCodigo;
    private String maeSolicitudRiesgoVidalValor;
    private Integer maeCanalSupersaludId;
    private String maeCanalSupersaludCodigo;
    private String maeCanalSupersaludValor;
    private Date fechaNotificacion;

    private Date fechaVencimiento;
    private Date fechaHoraResponsable;
    private List<AusSeguimiento> seguimientosList;
    private List<AusCasoServicio> serviciosList;
    private Empresa empresasId;
    private AusPersona asuPersonasId;
    private AusPersona peticionario;
    private AusSede sedesId;
    private Usuario responsableUsuariosId;
    private List<AusAdjunto> ausAdjuntosList;
    private List<AusAdjunto> adjuntosListSeguimiento;
    private Integer diasVencimiento;
    private Ubicacion ubicacion;

    private String datosCreacion;
    private String datosModificacion;
    private AusSeguimiento seguimientoInicial;
    private List<AusSede> listaSedes;
    private AusSeguimiento seguimientoAdicional;
    private String colorVencimiento;
    private String colorCantidadServiciosCerrados;
    private boolean renderAgregarSeguimientos = true;

    private boolean pertinencia;
    private String estado;
    private String parentesco;
    private boolean multireparto;

    private Integer idAusCargaMasiva;

    private boolean instruccion;
    private boolean reabierto;
    private boolean falloTutela;
    private boolean redireccionado;
    private String correoElectronico;
    private String seguimientoExterno;

    private Integer modalidadEntrega;
    private String direccionResidencia;

    private String observacionBorrar;
    private boolean borrado;
    private Integer cantidadServicios;
    private Integer cantidadServiciosCerrados;
    private Integer maeMotivoReabreId;
    private String maeMotivoReabreCodigo;
    private String maeMotivoReabreValor;
    private String observacionReabre;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    private String usuarioReabre;
    private String terminalReabre;
    private Date fechaHoraReabre;
    private String usuarioCierra;
    private String terminalCierra;
    private Date fechaHoraCierra;
    private String observacionServicioCerrar;

    private Integer maeTecnologiaAltoCostoId;
    private String maeTecnologiaAltoCostoCodigo;
    private String maeTecnologiaAltoCostoValor;
    private Integer maeMotivoEspecificoId;
    private String maeMotivoEspecificoCodigo;
    private String maeMotivoEspecificoValor;
    private Integer maeTipoMotivoEspecificoId;
    private String maeTipoMotivoEspecificoCodigo;
    private String maeTipoMotivoEspecificoValor;
    private Integer maeSubtipoMotivoEspecificoId;
    private String maeSubtipoMotivoEspecificoCodigo;
    private String maeSubtipoMotivoEspecificoValor;
    private boolean cerrado;
    private Integer maeCasoCerradoId;
    private String maeCasoCerradoCodigo;
    private String maeCasoCerradoValor;
    private boolean proteccionDatos;
    private boolean usuarioPluripatologico;
    
    private boolean habilitarCasoMotivo;
    
    private Integer origenCierre;
    
    //2023-03-17 jyperez nuevo campo
    private Date fechaCreacionCaso;
    //2025-04-13 jyperez campo de cierre
    private Usuario usuarioCierre;
    //2025-04-07 jyperez campo manejo cierre de casos
    private int pendienteCierre;
    //variable para carga masiva
    private String errorCarga;
    private String ausCargaMasivaCaso;
    private AusCasoServicio servicio;
    private String grupoGestion;
    
    public AusCaso() {
        usuarioPluripatologico = false;
    }

    public AusCaso(Integer id) {
        this.id = id;
        usuarioPluripatologico = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaHoraResponsable() {
        return fechaHoraResponsable;
    }

    public void setFechaHoraResponsable(Date fechaHoraResponsable) {
        this.fechaHoraResponsable = fechaHoraResponsable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getMaeSolicitudEstadoId() {
        return maeSolicitudEstadoId;
    }

    public void setMaeSolicitudEstadoId(Integer maeSolicitudEstadoId) {
        this.maeSolicitudEstadoId = maeSolicitudEstadoId;
    }

    public String getMaeSolicitudEstadoCodigo() {
        return maeSolicitudEstadoCodigo;
    }

    public void setMaeSolicitudEstadoCodigo(String maeSolicitudEstadoCodigo) {
        this.maeSolicitudEstadoCodigo = maeSolicitudEstadoCodigo;
    }

    public String getMaeSolicitudEstadoValor() {
        return maeSolicitudEstadoValor;
    }

    public void setMaeSolicitudEstadoValor(String maeSolicitudEstadoValor) {
        this.maeSolicitudEstadoValor = maeSolicitudEstadoValor;
    }

    public int getMaeSolicitudTipoId() {
        return maeSolicitudTipoId;
    }

    public void setMaeSolicitudTipoId(int maeSolicitudTipoId) {
        this.maeSolicitudTipoId = maeSolicitudTipoId;
    }

    public String getMaeSolicitudTipoCodigo() {
        return maeSolicitudTipoCodigo;
    }

    public void setMaeSolicitudTipoCodigo(String maeSolicitudTipoCodigo) {
        this.maeSolicitudTipoCodigo = maeSolicitudTipoCodigo;
    }

    public String getMaeSolicitudTipoValor() {
        return maeSolicitudTipoValor;
    }

    public void setMaeSolicitudTipoValor(String maeSolicitudTipoValor) {
        this.maeSolicitudTipoValor = maeSolicitudTipoValor;
    }

    public int getMaeSolicitudOrigenId() {
        return maeSolicitudOrigenId;
    }

    public void setMaeSolicitudOrigenId(int maeSolicitudOrigenId) {
        this.maeSolicitudOrigenId = maeSolicitudOrigenId;
    }

    public String getMaeSolicitudOrigenCodigo() {
        return maeSolicitudOrigenCodigo;
    }

    public void setMaeSolicitudOrigenCodigo(String maeSolicitudOrigenCodigo) {
        this.maeSolicitudOrigenCodigo = maeSolicitudOrigenCodigo;
    }

    public String getMaeSolicitudOrigenValor() {
        return maeSolicitudOrigenValor;
    }

    public void setMaeSolicitudOrigenValor(String maeSolicitudOrigenValor) {
        this.maeSolicitudOrigenValor = maeSolicitudOrigenValor;
    }

    public Integer getMaeSolicitudPrioridadId() {
        return maeSolicitudPrioridadId;
    }

    public void setMaeSolicitudPrioridadId(Integer maeSolicitudPrioridadId) {
        this.maeSolicitudPrioridadId = maeSolicitudPrioridadId;
    }

    public String getMaeSolicitudPrioridadCodigo() {
        return maeSolicitudPrioridadCodigo;
    }

    public void setMaeSolicitudPrioridadCodigo(String maeSolicitudPrioridadCodigo) {
        this.maeSolicitudPrioridadCodigo = maeSolicitudPrioridadCodigo;
    }

    public String getMaeSolicitudPrioridadValor() {
        return maeSolicitudPrioridadValor;
    }

    public void setMaeSolicitudPrioridadValor(String maeSolicitudPrioridadValor) {
        this.maeSolicitudPrioridadValor = maeSolicitudPrioridadValor;
    }

    public Integer getMaeSolicitudEnteControlId() {
        return maeSolicitudEnteControlId;
    }

    public void setMaeSolicitudEnteControlId(Integer maeSolicitudEnteControlId) {
        this.maeSolicitudEnteControlId = maeSolicitudEnteControlId;
    }

    public String getMaeSolicitudEnteControlCodigo() {
        return maeSolicitudEnteControlCodigo;
    }

    public void setMaeSolicitudEnteControlCodigo(String maeSolicitudEnteControlCodigo) {
        this.maeSolicitudEnteControlCodigo = maeSolicitudEnteControlCodigo;
    }

    public String getMaeSolicitudEnteControlValor() {
        return maeSolicitudEnteControlValor;
    }

    public void setMaeSolicitudEnteControlValor(String maeSolicitudEnteControlValor) {
        this.maeSolicitudEnteControlValor = maeSolicitudEnteControlValor;
    }

    public Integer getMaeSolicitudRiesgoVidalId() {
        return maeSolicitudRiesgoVidalId;
    }

    public void setMaeSolicitudRiesgoVidalId(Integer maeSolicitudRiesgoVidalId) {
        this.maeSolicitudRiesgoVidalId = maeSolicitudRiesgoVidalId;
    }

    public String getMaeSolicitudRiesgoVidalCodigo() {
        return maeSolicitudRiesgoVidalCodigo;
    }

    public void setMaeSolicitudRiesgoVidalCodigo(String maeSolicitudRiesgoVidalCodigo) {
        this.maeSolicitudRiesgoVidalCodigo = maeSolicitudRiesgoVidalCodigo;
    }

    public String getMaeSolicitudRiesgoVidalValor() {
        return maeSolicitudRiesgoVidalValor;
    }

    public void setMaeSolicitudRiesgoVidalValor(String maeSolicitudRiesgoVidalValor) {
        this.maeSolicitudRiesgoVidalValor = maeSolicitudRiesgoVidalValor;
    }

    public Integer getMaeCanalSupersaludId() {
        return maeCanalSupersaludId;
    }

    public void setMaeCanalSupersaludId(Integer maeCanalSupersaludId) {
        this.maeCanalSupersaludId = maeCanalSupersaludId;
    }

    public String getMaeCanalSupersaludCodigo() {
        return maeCanalSupersaludCodigo;
    }

    public void setMaeCanalSupersaludCodigo(String maeCanalSupersaludCodigo) {
        this.maeCanalSupersaludCodigo = maeCanalSupersaludCodigo;
    }

    public String getMaeCanalSupersaludValor() {
        return maeCanalSupersaludValor;
    }

    public void setMaeCanalSupersaludValor(String maeCanalSupersaludValor) {
        this.maeCanalSupersaludValor = maeCanalSupersaludValor;
    }

    public List<AusSeguimiento> getSeguimientosList() {
        return seguimientosList;
    }

    public void setSeguimientosList(List<AusSeguimiento> seguimientosList) {
        this.seguimientosList = seguimientosList;
    }

    public List<AusCasoServicio> getServiciosList() {
        return serviciosList;
    }

    public void setServiciosList(List<AusCasoServicio> serviciosList) {
        this.serviciosList = serviciosList;
    }

    public AusPersona getAsuPersonasId() {
        return asuPersonasId;
    }

    public void setAsuPersonasId(AusPersona asuPersonasId) {
        this.asuPersonasId = asuPersonasId;
    }

    public List<AusAdjunto> getAusAdjuntosList() {
        return ausAdjuntosList;
    }

    public void setAusAdjuntosList(List<AusAdjunto> ausAdjuntosList) {
        this.ausAdjuntosList = ausAdjuntosList;
    }

    public Integer getIdAusCargaMasiva() {
        return idAusCargaMasiva;
    }

    public void setIdAusCargaMasiva(Integer idAusCargaMasiva) {
        this.idAusCargaMasiva = idAusCargaMasiva;
    }

    public String getObservacionBorrar() {
        return observacionBorrar;
    }

    public void setObservacionBorrar(String observacionBorrar) {
        this.observacionBorrar = observacionBorrar;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Integer getCantidadServicios() {
        return cantidadServicios;
    }

    public void setCantidadServicios(Integer cantidadServicios) {
        this.cantidadServicios = cantidadServicios;
    }

    public Integer getCantidadServiciosCerrados() {
        return cantidadServiciosCerrados;
    }

    public void setCantidadServiciosCerrados(Integer cantidadServiciosCerrados) {
        this.cantidadServiciosCerrados = cantidadServiciosCerrados;
    }

    public Integer getMaeMotivoReabreId() {
        return maeMotivoReabreId;
    }

    public void setMaeMotivoReabreId(Integer maeMotivoReabreId) {
        this.maeMotivoReabreId = maeMotivoReabreId;
    }

    public String getMaeMotivoReabreCodigo() {
        return maeMotivoReabreCodigo;
    }

    public void setMaeMotivoReabreCodigo(String maeMotivoReabreCodigo) {
        this.maeMotivoReabreCodigo = maeMotivoReabreCodigo;
    }

    public String getMaeMotivoReabreValor() {
        return maeMotivoReabreValor;
    }

    public void setMaeMotivoReabreValor(String maeMotivoReabreValor) {
        this.maeMotivoReabreValor = maeMotivoReabreValor;
    }

    public String getObservacionReabre() {
        return observacionReabre;
    }

    public void setObservacionReabre(String observacionReabre) {
        this.observacionReabre = observacionReabre;
    }

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public String getUsuarioReabre() {
        return usuarioReabre;
    }

    public void setUsuarioReabre(String usuarioReabre) {
        this.usuarioReabre = usuarioReabre;
    }

    public String getTerminalReabre() {
        return terminalReabre;
    }

    public void setTerminalReabre(String terminalReabre) {
        this.terminalReabre = terminalReabre;
    }

    public Date getFechaHoraReabre() {
        return fechaHoraReabre;
    }

    public void setFechaHoraReabre(Date fechaHoraReabre) {
        this.fechaHoraReabre = fechaHoraReabre;
    }

    public String getUsuarioCierra() {
        return usuarioCierra;
    }

    public void setUsuarioCierra(String usuarioCierra) {
        this.usuarioCierra = usuarioCierra;
    }

    public String getTerminalCierra() {
        return terminalCierra;
    }

    public void setTerminalCierra(String terminalCierra) {
        this.terminalCierra = terminalCierra;
    }

    public Date getFechaHoraCierra() {
        return fechaHoraCierra;
    }

    public void setFechaHoraCierra(Date fechaHoraCierra) {
        this.fechaHoraCierra = fechaHoraCierra;
    }

    public String getObservacionServicioCerrar() {
        return observacionServicioCerrar;
    }

    public void setObservacionServicioCerrar(String observacionServicioCerrar) {
        this.observacionServicioCerrar = observacionServicioCerrar;
    }

    public Integer getMaeTecnologiaAltoCostoId() {
        return maeTecnologiaAltoCostoId;
    }

    public void setMaeTecnologiaAltoCostoId(Integer maeTecnologiaAltoCostoId) {
        this.maeTecnologiaAltoCostoId = maeTecnologiaAltoCostoId;
    }

    public String getMaeTecnologiaAltoCostoCodigo() {
        return maeTecnologiaAltoCostoCodigo;
    }

    public void setMaeTecnologiaAltoCostoCodigo(String maeTecnologiaAltoCostoCodigo) {
        this.maeTecnologiaAltoCostoCodigo = maeTecnologiaAltoCostoCodigo;
    }

    public String getMaeTecnologiaAltoCostoValor() {
        return maeTecnologiaAltoCostoValor;
    }

    public void setMaeTecnologiaAltoCostoValor(String maeTecnologiaAltoCostoValor) {
        this.maeTecnologiaAltoCostoValor = maeTecnologiaAltoCostoValor;
    }

    public Integer getMaeMotivoEspecificoId() {
        return maeMotivoEspecificoId;
    }

    public void setMaeMotivoEspecificoId(Integer maeMotivoEspecificoId) {
        this.maeMotivoEspecificoId = maeMotivoEspecificoId;
    }

    public String getMaeMotivoEspecificoCodigo() {
        return maeMotivoEspecificoCodigo;
    }

    public void setMaeMotivoEspecificoCodigo(String maeMotivoEspecificoCodigo) {
        this.maeMotivoEspecificoCodigo = maeMotivoEspecificoCodigo;
    }

    public String getMaeMotivoEspecificoValor() {
        return maeMotivoEspecificoValor;
    }

    public void setMaeMotivoEspecificoValor(String maeMotivoEspecificoValor) {
        this.maeMotivoEspecificoValor = maeMotivoEspecificoValor;
    }

    public Integer getMaeTipoMotivoEspecificoId() {
        return maeTipoMotivoEspecificoId;
    }

    public void setMaeTipoMotivoEspecificoId(Integer maeTipoMotivoEspecificoId) {
        this.maeTipoMotivoEspecificoId = maeTipoMotivoEspecificoId;
    }

    public String getMaeTipoMotivoEspecificoCodigo() {
        return maeTipoMotivoEspecificoCodigo;
    }

    public void setMaeTipoMotivoEspecificoCodigo(String maeTipoMotivoEspecificoCodigo) {
        this.maeTipoMotivoEspecificoCodigo = maeTipoMotivoEspecificoCodigo;
    }

    public String getMaeTipoMotivoEspecificoValor() {
        return maeTipoMotivoEspecificoValor;
    }

    public void setMaeTipoMotivoEspecificoValor(String maeTipoMotivoEspecificoValor) {
        this.maeTipoMotivoEspecificoValor = maeTipoMotivoEspecificoValor;
    }

    public Integer getMaeSubtipoMotivoEspecificoId() {
        return maeSubtipoMotivoEspecificoId;
    }

    public void setMaeSubtipoMotivoEspecificoId(Integer maeSubtipoMotivoEspecificoId) {
        this.maeSubtipoMotivoEspecificoId = maeSubtipoMotivoEspecificoId;
    }

    public String getMaeSubtipoMotivoEspecificoCodigo() {
        return maeSubtipoMotivoEspecificoCodigo;
    }

    public void setMaeSubtipoMotivoEspecificoCodigo(String maeSubtipoMotivoEspecificoCodigo) {
        this.maeSubtipoMotivoEspecificoCodigo = maeSubtipoMotivoEspecificoCodigo;
    }

    public String getMaeSubtipoMotivoEspecificoValor() {
        return maeSubtipoMotivoEspecificoValor;
    }

    public void setMaeSubtipoMotivoEspecificoValor(String maeSubtipoMotivoEspecificoValor) {
        this.maeSubtipoMotivoEspecificoValor = maeSubtipoMotivoEspecificoValor;
    }

    public boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    public Integer getMaeCasoCerradoId() {
        return maeCasoCerradoId;
    }

    public void setMaeCasoCerradoId(Integer maeCasoCerradoId) {
        this.maeCasoCerradoId = maeCasoCerradoId;
    }

    public String getMaeCasoCerradoCodigo() {
        return maeCasoCerradoCodigo;
    }

    public void setMaeCasoCerradoCodigo(String maeCasoCerradoCodigo) {
        this.maeCasoCerradoCodigo = maeCasoCerradoCodigo;
    }

    public String getMaeCasoCerradoValor() {
        return maeCasoCerradoValor;
    }

    public void setMaeCasoCerradoValor(String maeCasoCerradoValor) {
        this.maeCasoCerradoValor = maeCasoCerradoValor;
    }

    public boolean getProteccionDatos() {
        return proteccionDatos;
    }

    public void setProteccionDatos(boolean proteccionDatos) {
        this.proteccionDatos = proteccionDatos;
    }

    public Boolean getUsuarioPluripatologico() {
        return usuarioPluripatologico;
    }

    public void setUsuarioPluripatologico(Boolean usuarioPluripatologico) {
        this.usuarioPluripatologico = usuarioPluripatologico;
    }

    public String getErrorCarga() {
        return errorCarga;
    }

    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    public String getAusCargaMasivaCaso() {
        return ausCargaMasivaCaso;
    }

    public void setAusCargaMasivaCaso(String ausCargaMasivaCaso) {
        this.ausCargaMasivaCaso = ausCargaMasivaCaso;
    }

    public AusCasoServicio getServicio() {
        if(servicio == null){
            servicio = new AusCasoServicio();
        }
        return servicio;
    }

    public void setServicio(AusCasoServicio servicio) {
        this.servicio = servicio;
    }
    
    public Integer getDiasVencimiento() throws ParseException {
        diasVencimiento = 0;
        if (getFechaNotificacion() != null) {
            if (estado != null) {
                if (estado.equals("Cerrado")) {
                    if (getSeguimientosList().size() > 0) {
                        Date fechaFin = getSeguimientosList().get(getSeguimientosList().size() - 1).getFechaHoraCrea();
                        diasVencimiento = diasEntreFechas(getFechaNotificacion().toString(), fechaFin);
                    } else {
                        diasVencimiento = diasEntreFechas(getFechaNotificacion().toString(), new Date());
                    }
                } else {
                    diasVencimiento = diasEntreFechas(getFechaNotificacion().toString(), new Date());
                }
            } else {
                if (getSeguimientosList().size() > 0) {
                    Integer estado = getSeguimientosList().get(getSeguimientosList().size() - 1).getMaeEstadoId();
                    if (estado.equals("131")) {
                        Date fechaFin = getSeguimientosList().get(getSeguimientosList().size() - 1).getFechaHoraCrea();
                        diasVencimiento = diasEntreFechas(getFechaNotificacion().toString(), fechaFin);
                    } else {
                        diasVencimiento = diasEntreFechas(getFechaNotificacion().toString(), new Date());
                    }
                } else {
                    diasVencimiento = diasEntreFechas(getFechaNotificacion().toString(), new Date());
                }
            }
        }
        if (diasVencimiento < 0) {
            diasVencimiento = 0;
        }
        return diasVencimiento;
    }

    private static int diasEntreFechas(String fechaInicial, Date fechaFinal) throws ParseException {
        Date newFechaInicial = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicial);
        int dias = (int) ((fechaFinal.getTime() - newFechaInicial.getTime()) / 86400000);
        return dias;
    }

    public void setDiasVencimiento(Integer diasVencimiento) {
        this.diasVencimiento = diasVencimiento;
    }

    public String getColorVencimiento() {
        return colorVencimiento;
    }

    public void setColorVencimiento(String colorVencimiento) {
        this.colorVencimiento = colorVencimiento;
    }

    public String getColorCantidadServiciosCerrados() {
        return colorCantidadServiciosCerrados;
    }

    public void setColorCantidadServiciosCerrados(String colorCantidadServiciosCerrados) {
        this.colorCantidadServiciosCerrados = colorCantidadServiciosCerrados;
    }

    public String establecerColorVencimiento(String est) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String strFechaActal = simpleDateFormat.format(new Date());
            if (getFechaVencimiento() != null) {
                Date newDateVencimiento = new SimpleDateFormat("yyyy-MM-dd").parse(getFechaVencimiento().toString());
                Date newDateActual = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaActal);
                int posicion = newDateVencimiento.compareTo(newDateActual);
                if (posicion < 0) {
                    colorVencimiento = "red";
                } else if (posicion == 0) {
                    colorVencimiento = "yellow";
                } else {
                    colorVencimiento = "green";
                }
                estado = est;
                if (est.equals("Archivado") || est.equals("Solucionado")) {
                    colorVencimiento = "green";

                }
            } else {
                colorVencimiento = "white";
            }
        } catch (Exception e) {

        }

        return colorVencimiento;
    }

    public String establecerColorServiciosCerrados() {

        try {
            if (getCantidadServicios() != null && getCantidadServiciosCerrados() != null) {
                if (getCantidadServicios().equals(getCantidadServiciosCerrados())) {
                    colorCantidadServiciosCerrados = "green";
                } else {
                    colorCantidadServiciosCerrados = "red";
                }
            } else {
                colorCantidadServiciosCerrados = "white";
            }
        } catch (Exception e) {

        }

        return colorCantidadServiciosCerrados;
    }

    public Empresa getEmpresasId() {
        if (empresasId == null) {
            empresasId = new Empresa();
        }
        return empresasId;
    }

    public void setEmpresasId(Empresa empresasId) {
        this.empresasId = empresasId;
    }

    public AusPersona getAusPersonasId() {
        if (asuPersonasId == null) {
            asuPersonasId = new AusPersona();
        }
        return asuPersonasId;
    }

    public void setAusPersonasId(AusPersona personasId) {
        this.asuPersonasId = personasId;
    }

    public AusSede getSedesId() {
        if (sedesId == null) {
            sedesId = new AusSede();
        }
        return sedesId;
    }

    public void setSedesId(AusSede sedesId) {
        this.sedesId = sedesId;
    }

    public Usuario getResponsableUsuariosId() {
        if (responsableUsuariosId == null) {
            responsableUsuariosId = new Usuario();
        }
        return responsableUsuariosId;
    }

    public void setResponsableUsuariosId(Usuario responsableUsuariosId) {
        this.responsableUsuariosId = responsableUsuariosId;
    }

    public List<AusAdjunto> getAdjuntosList() {
        if (ausAdjuntosList == null) {
            ausAdjuntosList = new ArrayList<>();
        }
        return ausAdjuntosList;
    }

    public void setAdjuntosList(List<AusAdjunto> adjuntosList) {
        this.ausAdjuntosList = adjuntosList;
    }

    public Empresa getEmpresa() {
        if (empresa == null) {
            empresa = new Empresa();
        }
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Ubicacion getUbicacion() {
        if (ubicacion == null) {
            ubicacion = new Ubicacion();
        }
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public AusSeguimiento getSeguimientoInicial() {
        if (seguimientoInicial == null) {
            seguimientoInicial = new AusSeguimiento();
        }
        return seguimientoInicial;
    }

    public AusSeguimiento getSeguimientoAdicional() {
        if (seguimientoAdicional == null) {
            seguimientoAdicional = new AusSeguimiento();
        }
        return seguimientoAdicional;
    }

    public void setSeguimientoAdicional(AusSeguimiento seguimientoAdicional) {
        this.seguimientoAdicional = seguimientoAdicional;
    }

    public void setSeguimientoInicial(AusSeguimiento seguimientoInicial) {
        this.seguimientoInicial = seguimientoInicial;
    }

    public String getDatosCreacion() {
        datosCreacion = "El usuario " + this.getUsuarioCrea() + " el dia " + this.getFechaHoraCrea()
                + " desde la terminal " + this.getTerminalCrea() + " realizó la creación del registro.";

        return datosCreacion;
    }

    public void setDatosCreacion(String datosCreacion) {
        this.datosCreacion = datosCreacion;
    }

    public String getDatosModificacion() {
        if (!"".equals(this.getUsuarioModifica()) && this.getUsuarioModifica() != null) {
            datosModificacion = "El usuario " + this.getUsuarioModifica() + " el dia " + this.getFechaHoraModifica()
                    + " desde la terminal " + this.getTerminalModifica() + " realizó la modificación del registro.";
        }
        return datosModificacion;
    }

    public void setDatosModificacion(String datosModificacion) {
        this.datosModificacion = datosModificacion;
    }

    public String getPertinenciaStr() {
        String pertinencia = "N/A";
            pertinencia = (getPertinencia()) ? "SI" : "NO";
        return pertinencia;
    }

    public List<AusSede> getListaSedes() {
        if (listaSedes == null) {
            listaSedes = new ArrayList<>();
        }
        return listaSedes;
    }

    public void setListaSedes(List<AusSede> listaSedes) {
        this.listaSedes = listaSedes;
    }

    public boolean isRenderAgregarSeguimientos() {
        return renderAgregarSeguimientos;
    }

    public void setRenderAgregarSeguimientos(boolean renderAgregarSeguimientos) {
        this.renderAgregarSeguimientos = renderAgregarSeguimientos;
    }

    public boolean getPertinencia() {
        return pertinencia;
    }

    public void setPertinencia(boolean pertinencia) {
        this.pertinencia = pertinencia;
    }

    public AusPersona getPeticionario() {
        if (peticionario == null) {
            peticionario = new AusPersona();
        }
        return peticionario;
    }

    public void setPeticionario(AusPersona peticionario) {
        this.peticionario = peticionario;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public boolean getMultireparto() {
        return multireparto;
    }

    public void setMultireparto(boolean multireparto) {
        this.multireparto = multireparto;
    }

    public Integer getModalidadEntrega() {
        return modalidadEntrega;
    }

    public void setModalidadEntrega(Integer modalidadEntrega) {
        this.modalidadEntrega = modalidadEntrega;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String obtenerMultireparto() {
        return getMultireparto()? "Si": "No";
    }

    public String obtenerInstruccion() {
        return getInstruccion() ? "Si": "No";
    }

    public String obtenerReabierto() {
        return getReabierto() ? "Si": "No";
    }

    public String obtenerFalloTutela() {
        return getFalloTutela()? "Si" : "No";
    }

    public String obtenerRedireccionado() {
        return getRedireccionado()? "Si": "No";
    }

    public String obtenerUsuarioPluripatologico() {
        String usuarioPluripatologicoStr = "";
        if (getUsuarioPluripatologico() != null) {
            if (getUsuarioPluripatologico()) {
                usuarioPluripatologicoStr = "Si";
            } else {
                usuarioPluripatologicoStr = "No";
            }
        }

        return usuarioPluripatologicoStr;
    }

    public String obtenerProteccionDatos() {
        return getProteccionDatos()? "Si": "No";
    }

    public List<AusAdjunto> getAdjuntosListSeguimiento() {
        if (adjuntosListSeguimiento == null) {
            adjuntosListSeguimiento = new ArrayList();
        }
        if (this.getSeguimientosList() != null) {
            if (this.getSeguimientosList().size() > 0) {
                this.getSeguimientosList().stream().filter((seguimiento) -> (seguimiento.getAdjuntosList().size() > 0)).forEachOrdered((seguimiento) -> {
                    seguimiento.getAdjuntosList().forEach((adjunto) -> {
                        boolean validar = true;
                        if (adjuntosListSeguimiento.isEmpty()) {
                            validar = true;
                        } else {
                            for (AusAdjunto adjunto2 : adjuntosListSeguimiento) {
                                if (adjunto2.getNombre().equals(adjunto.getNombre())) {
                                    validar = false;
                                }
                            }
                        }
                        if (validar) {
                            adjuntosListSeguimiento.add(adjunto);
                        }
                    });
                });
            }
        }

        return adjuntosListSeguimiento;
    }

    public void setAdjuntosListSeguimiento(List<AusAdjunto> adjuntosListSeguimiento) {
        this.adjuntosListSeguimiento = adjuntosListSeguimiento;
    }

    public boolean getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(boolean instruccion) {
        this.instruccion = instruccion;
    }

    public boolean getReabierto() {
        return reabierto;
    }

    public void setReabierto(boolean reabierto) {
        this.reabierto = reabierto;
    }

    public boolean getFalloTutela() {
        return falloTutela;
    }

    public void setFalloTutela(boolean falloTutela) {
        this.falloTutela = falloTutela;
    }

    public boolean getRedireccionado() {
        return redireccionado;
    }

    public void setRedireccionado(boolean redireccionado) {
        this.redireccionado = redireccionado;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getSeguimientoExterno() {
        return seguimientoExterno;
    }

    public void setSeguimientoExterno(String seguimientoExterno) {
        this.seguimientoExterno = seguimientoExterno;
    }

    public boolean isHabilitarCasoMotivo() {
        return habilitarCasoMotivo;
    }

    public void setHabilitarCasoMotivo(boolean habilitarCasoMotivo) {
        this.habilitarCasoMotivo = habilitarCasoMotivo;
    }

    @Override
    public String toString() {
        return "AusCaso{" + "id=" + id + ", maeSolicitudEstadoId=" + maeSolicitudEstadoId + ", maeSolicitudEstadoCodigo=" + maeSolicitudEstadoCodigo + ", maeSolicitudEstadoValor=" + maeSolicitudEstadoValor + ", maeSolicitudTipoId=" + maeSolicitudTipoId + ", maeSolicitudTipoCodigo=" + maeSolicitudTipoCodigo + ", maeSolicitudTipoValor=" + maeSolicitudTipoValor + ", maeSolicitudOrigenId=" + maeSolicitudOrigenId + ", maeSolicitudOrigenCodigo=" + maeSolicitudOrigenCodigo + ", maeSolicitudOrigenValor=" + maeSolicitudOrigenValor + ", maeSolicitudPrioridadId=" + maeSolicitudPrioridadId + ", maeSolicitudPrioridadCodigo=" + maeSolicitudPrioridadCodigo + ", maeSolicitudPrioridadValor=" + maeSolicitudPrioridadValor + ", maeSolicitudEnteControlId=" + maeSolicitudEnteControlId + ", maeSolicitudEnteControlCodigo=" + maeSolicitudEnteControlCodigo + ", maeSolicitudEnteControlValor=" + maeSolicitudEnteControlValor + ", maeSolicitudRiesgoVidalId=" + maeSolicitudRiesgoVidalId + ", maeSolicitudRiesgoVidalCodigo=" + maeSolicitudRiesgoVidalCodigo + ", maeSolicitudRiesgoVidalValor=" + maeSolicitudRiesgoVidalValor + ", maeCanalSupersaludId=" + maeCanalSupersaludId + ", maeCanalSupersaludValor=" + maeCanalSupersaludValor + ", multireparto=" + multireparto + ", instruccion=" + instruccion + ", reabierto=" + reabierto + ", falloTutela=" + falloTutela + ", fechaCreacionCaso=" + fechaCreacionCaso + ", responsableUsuariosId=" + ((responsableUsuariosId != null) ? responsableUsuariosId.toStringCorto() : "null") + ", usuarioCierre=" + ((usuarioCierre != null) ? usuarioCierre.toStringCorto(): "null") + '}';
    }

    /**
     * @return the origenCierre
     */
    public Integer getOrigenCierre() {
        return origenCierre;
    }

    /**
     * @param origenCierre the origenCierre to set
     */
    public void setOrigenCierre(Integer origenCierre) {
        this.origenCierre = origenCierre;
    }

    /**
     * @return the fechaCreacionCaso
     */
    public Date getFechaCreacionCaso() {
        return fechaCreacionCaso;
    }

    /**
     * @param fechaCreacionCaso the fechaCreacionCaso to set
     */
    public void setFechaCreacionCaso(Date fechaCreacionCaso) {
        this.fechaCreacionCaso = fechaCreacionCaso;
    }

    /**
     * @return the grupoGestion
     */
    public String getGrupoGestion() {
        return grupoGestion;
    }

    /**
     * @param grupoGestion the grupoGestion to set
     */
    public void setGrupoGestion(String grupoGestion) {
        this.grupoGestion = grupoGestion;
    }

    /**
     * @return the usuarioCierre
     */
    public Usuario getUsuarioCierre() {
        return usuarioCierre;
    }

    /**
     * @param usuarioCierre the usuarioCierre to set
     */
    public void setUsuarioCierre(Usuario usuarioCierre) {
        this.usuarioCierre = usuarioCierre;
    }
    
    public String getPendienteCierreStr(){
        String mensaje = "NO";
        try {
            if (this.cantidadServicios != null && this.cantidadServiciosCerrados != null &&
                    this.cantidadServicios > 0 && this.cantidadServiciosCerrados > 0
                    && this.cantidadServicios == this.cantidadServiciosCerrados ) {
                mensaje = "SI";
            }
        } catch (Exception ex) {
            mensaje = "NO";
        }
        return mensaje;
    }
    
    public String getCasoMedicamentoStr(){
        String mensaje = "NO";
        try {
            if (this.serviciosList != null && !this.serviciosList.isEmpty() ) {
                for (AusCasoServicio serv: this.serviciosList) {
                    if (serv.getTipoTecnologia() != null && serv.getTipoTecnologia()  == TIPO_TECNOLOGIA_MEDICAMENTO) {
                        mensaje = "SI";
                    }
                }
            }
        } catch (Exception ex) {
            mensaje = "NO";
        }
        return mensaje;
    }

    /**
     * @return the pendienteCierre
     */
    public int getPendienteCierre() {
        return pendienteCierre;
    }

    /**
     * @param pendienteCierre the pendienteCierre to set
     */
    public void setPendienteCierre(int pendienteCierre) {
        this.pendienteCierre = pendienteCierre;
    }
}
