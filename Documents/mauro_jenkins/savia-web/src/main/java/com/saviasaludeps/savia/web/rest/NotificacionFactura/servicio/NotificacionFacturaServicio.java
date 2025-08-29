/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.rest.NotificacionFactura.servicio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmFacturaEstado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFacturaTransaccion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionPaquete;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDevolucionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmFacturaEstadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaTransaccionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmRadicadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionEncabezadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionPaqueteRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionRemoto;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades.EnvioSapCierreAuditoriaFacturaHilo;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades.EnvioSapDevolucionFacturaHilo;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades.NotificacionUtilidades;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.CierreAuditoriaFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.CmNotificacionFacturaDTO;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaTokenIntersavia;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.SolicitudToken;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.DetalleServicio;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.DevolucionFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.NotificacionFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.NotificacionFacturaIntrasavia;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.NotificacionFacturaPaquete;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.NotificacionFacturaPaqueteM1;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.NotificacionFacturaPaqueteM2;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaNotificacionFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaNotificacionFacturaPaquete;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaSolicitudNotificacionFacturaIntrasavia;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.SincronizacionNotificacionFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.SolicitudCierreAuditoriaFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.SolicitudDevolucionFactura;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.shaded.json.JSONException;

/**
 *
 * @author yjimenez
 */
public class NotificacionFacturaServicio {

    public static final String CONTRATO = "000105";
    public static final String CONTRATO_CIERRE_AUDITORIA = "000105";
    public static final String STRING_VACIO = "";
    public static final String STRING_CERO = "0";
    public static final String TIPO_TRANSACCION_MOMENTO_3 = "3";
    public static final String TIPO_TRANSACCION_MOMENTO_2 = "2";
    public final static String TIPO_TRANSACCION_MOMENTO_1 = "1";
    public static final String TIPO_DOCUMENTO_MOMENTO_1 = "1";
    public static final String TIPO_DOCUMENTO_MOMENTO_2 = "1";
    public static final String TIPO_DOCUMENTO_MOMENTO_3 = "2";
    public final static String CODIGO_EXITO_RESPUESTA_M1 = "2";
    public final static String CODIGO_YA_SE_REALIZO_M1 = "3";
    public final static String CODIGO_FACTURA_CREADA_SAP = "20";
    public final static int MOMENTO_1 = 1;
    public final static int CODIGO_INICIO_SINCRONIZACION = 0;
    public final static String CODIGO_NOTAS_CREADAS_EXITOSAS_M3 = "13";
    public final static int LONGITUD_MENSAJE_RESPUESTA = 512;
    
    public final static int TIPO_PETICION_TRADICIONAL  = 1;
    public final static int TIPO_PETICION_PAQUETE      = 2;
    public final static int TIPO_PETICION_INTRASAVIA   = 3;
    
    private int tipoEnvioReceptionPeticion;
    
    public static final int TAMANO_PAQUETE_POR_FACTURAS = 100;
    public static final String TEXTO_INICIO_ENVIO_SAP = "La operación se esta realizando pendiente por respuesta SAP";

    private CmSincronizacionEncabezadoRemoto getCmSincronizacionEncabezadoRemoto() throws Exception {
        return (CmSincronizacionEncabezadoRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionEncabezadoServicio", CmSincronizacionEncabezadoRemoto.class.getName());
    }

    private CmSincronizacionDetalleRemoto getCmSincronizacionDetalleRemoto() throws Exception {
        return (CmSincronizacionDetalleRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionDetalleServicio", CmSincronizacionDetalleRemoto.class.getName());
    }

    private CmSincronizacionRemoto getCmSincronizacionRemoto() throws Exception {
        return (CmSincronizacionRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionServicio", CmSincronizacionRemoto.class.getName());
    }

    private CmRadicadoRemoto getCmRadicadoRemoto() throws Exception {
        return (CmRadicadoRemoto) RemotoEJB.getEJBRemoto("CmRadicadoServicio", CmRadicadoRemoto.class.getName());
    }

    private CmSincronizacionPaqueteRemoto getCmSincronizacionPaqueteRemoto() throws Exception {
        return (CmSincronizacionPaqueteRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionPaqueteServicio", CmSincronizacionPaqueteRemoto.class.getName());
    }

    private CmAuditoriaDevolucionRemoto getCmAuditoriaDevolucionRemoto() throws Exception {
        return (CmAuditoriaDevolucionRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDevolucionServicio", CmAuditoriaDevolucionRemoto.class.getName());
    }

    private CntContratoRemoto getCntContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto("CntContratoServicio", CntContratoRemoto.class.getName());
    }

    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }
    
     private CmFacturaEstadoRemoto getCmFacturaEstadoRemoto() throws Exception {
        return (CmFacturaEstadoRemoto) RemotoEJB.getEJBRemoto("CmFacturaEstadoServicio", CmFacturaEstadoRemoto.class.getName());
    }
    
    private CmGlosaRespuestaRemoto getCmGlosaRespuestaRemoto() throws Exception {
        return (CmGlosaRespuestaRemoto) RemotoEJB.getEJBRemoto("CmGlosaRespuestaServicio", CmGlosaRespuestaRemoto.class.getName());
    }
    
    private CmFacturaTransaccionRemoto getCmFacturaTransaccionServicioRemoto() throws Exception {
        return (CmFacturaTransaccionRemoto) RemotoEJB.getEJBRemoto("CmFacturaTransaccionServicio", CmFacturaTransaccionRemoto.class.getName());
    }
     
    public int getTipoEnvioReceptionPeticion() {
        return tipoEnvioReceptionPeticion;
    }

    public void setTipoEnvioReceptionPeticion(int tipoEnvioReceptionPeticion) {
        this.tipoEnvioReceptionPeticion = tipoEnvioReceptionPeticion;
    }
    
    public void consumoServicioDevolucionFactua(List<SincronizacionNotificacionFactura> listSincronizacionNotificacionFactura) {
        try {
            int idRadicado = 0;
            int estadoTransaccion = CmSincronizacion.ESTADO_TRANSACCION_INICIADA;
            GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
            gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            gsonBuilderRespuesta.serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();
            List<DevolucionFactura> listaDevolucionFacturas = new ArrayList<>();
            String mensajeRespuestaJson = "";
            boolean hayDevolucionesEnviar = true;
            SolicitudDevolucionFactura solicitudDevolucion = new SolicitudDevolucionFactura();

            solicitudDevolucion.setNUT("1");
            String horaInicioProceso = dateFormat.format(new Date());
            solicitudDevolucion.setFechaTransaccion(horaInicioProceso);

            if (!listSincronizacionNotificacionFactura.isEmpty()) {

                for (SincronizacionNotificacionFactura sincronizacion : listSincronizacionNotificacionFactura) {
                    idRadicado = sincronizacion.getRadicadoId();
                    CmRadicado radicado = getCmRadicadoRemoto().consultar(idRadicado);
                    CmAuditoriaDevolucion devolucion = getCmAuditoriaDevolucionRemoto().consultar(radicado.getCmAuditoriaDevolucion().getId());
                    DevolucionFactura devolucionFactura = castCmAuditoriaDevolucionToDevolucionFactura(devolucion);
                    listaDevolucionFacturas.add(devolucionFactura);
                    getCmFacturaTransaccionServicioRemoto().insertarTransaccionEnvioFactura(Integer.parseInt(devolucionFactura.getConsecutivo()),
                                                           CmFacturaTransaccion.TIPO_MOMENTO0, CmFacturaTransaccion.ESTADO_ENVIADA);
                }
                solicitudDevolucion.setFacturas(listaDevolucionFacturas);

                if (solicitudDevolucion.getFacturas() == null || (solicitudDevolucion.getFacturas().isEmpty())) {
                    mensajeRespuestaJson = "No se han encontrado devoluciones ";
                    hayDevolucionesEnviar = false;
                }
                String jsonEntrada = gson.toJson(solicitudDevolucion);

                String usuario = PropApl.getInstance().get(PropApl.WS_USUARIO_TOKEN_CONCILIACION_FACTURA);
                String contrasena = PropApl.getInstance().get(PropApl.WS_CONTRASENA_TOKEN_CONCILIACION_FACTURA);
                String urlToken = PropApl.getInstance().get(PropApl.RUTA_SERVICIO_TOKEN_CONCILIACION_FACTURA);

                RespuestaTokenIntersavia token = generarTokenExterno(usuario, contrasena, urlToken);

                if (!hayDevolucionesEnviar) {
                    CmRadicado radicado = getCmRadicadoRemoto().consultar(idRadicado);
                    radicado.setEstado_radicado(Boolean.TRUE);
                    getCmRadicadoRemoto().actualizar(radicado);
                    estadoTransaccion = CmSincronizacion.ESTADO_TRANSACCION_TERMINADA;
                } else {
                    if (CmSincronizacion.ESTADO_TRANSACCION_INICIADA == estadoTransaccion) {
                        mensajeRespuestaJson = (token == null) ? "Se ha producido un error en el servicio :"
                                + urlToken
                                : TEXTO_INICIO_ENVIO_SAP;
                        estadoTransaccion = (token == null) ? CmSincronizacion.CODIGO_RESPUESTA_ERROR_TOKEN : CmSincronizacion.CODIGO_RESPUESTA_ENVIO_SAP;
                    }
                }

                CmSincronizacion sincronizacion = new CmSincronizacion();
                sincronizacion.setCmRadicado(new CmRadicado(idRadicado));
                sincronizacion.setJsonEnvio(jsonEntrada.getBytes());
                sincronizacion.setJsonRespuesta(mensajeRespuestaJson.getBytes());
                sincronizacion.setFechaHoraEnvio(new Date());
                sincronizacion.setPaquetes(1);
                sincronizacion.setMomento(CmSincronizacion.MOMENTO_DEVOLUCION_FACTURA);
                sincronizacion.setPaquetesExitosos(0);
                sincronizacion.setEstadoTransacion(estadoTransaccion);
                sincronizacion.setId(getCmSincronizacionRemoto().insertar(sincronizacion));

                if (CmSincronizacion.CODIGO_RESPUESTA_ENVIO_SAP == estadoTransaccion) {
                    EnvioSapDevolucionFacturaHilo devolucionHilo = new EnvioSapDevolucionFacturaHilo(token, jsonEntrada, sincronizacion);
                    devolucionHilo.start();
                }

            }

        } catch (Exception ex) {
            Logger.getLogger("Error al consumir servicio devolucion factura : " + NotificacionFacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consumoServicioCierreAuditoriaFactura(List<SincronizacionNotificacionFactura> listSincronizacionNotificacionFactura) {
        try {

            int idAuditoriaCierre = 0;
            int idRadicado = 0;
            int estadoTransaccion = CmSincronizacion.ESTADO_TRANSACCION_INICIADA;
            String tipoTransaccion = "";
            
            setTipoEnvioReceptionPeticion(TIPO_PETICION_TRADICIONAL);

            SolicitudCierreAuditoriaFactura solicitudAuditoriaCierre = new SolicitudCierreAuditoriaFactura();
            if (!listSincronizacionNotificacionFactura.isEmpty()) {
                CierreAuditoriaFactura cierreAuditoria = new CierreAuditoriaFactura();
                List<DetalleServicio> detalleNotaDebito = null;
                List<DetalleServicio> detalleServicio = new ArrayList<>();
                List<CierreAuditoriaFactura> listaCierreAuditoriaFactura = new ArrayList<>();

                solicitudAuditoriaCierre.setFacturas(listaCierreAuditoriaFactura);
                for (SincronizacionNotificacionFactura sincronizacionNotificacion : listSincronizacionNotificacionFactura) {
                    try {
                        tipoTransaccion = sincronizacionNotificacion.getTipoTransaccion();
                        idAuditoriaCierre = sincronizacionNotificacion.getAuditoriaCierreId();
                        idRadicado = sincronizacionNotificacion.getRadicadoId();
                        List<CmSincronizacionDetalle> detalles;
                        List<CmSincronizacionEncabezado> encabezados = getCmSincronizacionEncabezadoRemoto().consultarDetalles(sincronizacionNotificacion.getRadicadoId());
                        if (!encabezados.isEmpty()) {
                            for (CmSincronizacionEncabezado encabezado : encabezados) {
                                encabezado.setNumeroContrato(obtenerNumeroContrato(encabezado));
                                detalles = new ArrayList<>();
                                detalles.addAll(getCmSincronizacionDetalleRemoto().consultarDetallesEncabezado(encabezado.getId()));
                                HashMap<String, CmSincronizacionDetalle> hashDetalleServicio = new HashMap<>();
                                
                                getCmFacturaTransaccionServicioRemoto().insertarTransaccionEnvioFactura(encabezado.getFactura(),
                                                           CmFacturaTransaccion.TIPO_MOMENTO2, CmFacturaTransaccion.ESTADO_ENVIADA);
                                                               
                                if (!detalles.isEmpty()) {
                                    cierreAuditoria = castCmSincronizacionEncabezadoToCierreAuditoriaFactura(encabezado, detalles.get(0));
                                    BigDecimal sumatoriaDetalles2DecimalesDebito = new BigDecimal(BigInteger.ZERO);
                                    for (CmSincronizacionDetalle detalle : detalles) {
                                        
                                        // inicio: para cada detalle se unifica valor tipo1 y tipo2 para enviarlo total como nota 
                                        String idDetalleSumado = detalle.getIdDetalles() + "-" + detalle.getConceptoContable();
                                        if (hashDetalleServicio.get(idDetalleSumado) == null) {
                                            hashDetalleServicio.put(idDetalleSumado, detalle);
                                        } else {
                                            CmSincronizacionDetalle sinDetalle = hashDetalleServicio.get(idDetalleSumado);
                                            BigDecimal valor = sinDetalle.getValorOperacion().
                                                    add(detalle.getValorOperacion()).setScale(2, RoundingMode.DOWN);
                                            sinDetalle.setValorOperacion(valor);
                                        }
                                        //fin

                                        if (CmSincronizacionDetalle.TIPOLOGIA_DEBITO == detalle.getTipologia()) {
                                            if (detalleNotaDebito == null) {
                                                detalleNotaDebito = new ArrayList<>();
                                            }
                                            detalleNotaDebito.add(castCmSincronizacionDetalleToDetalleServicio(detalle));
                                            sumatoriaDetalles2DecimalesDebito = sumatoriaDetalles2DecimalesDebito.
                                                    add(detalle.getValorOperacion()).setScale(2, RoundingMode.DOWN);
                                        }
                                    }

                                    BigDecimal nuevoSumatoriaDebito = new BigDecimal(BigInteger.ZERO);
                                    if (sumatoriaDetalles2DecimalesDebito.compareTo(BigDecimal.ZERO) > 0) {
                                        nuevoSumatoriaDebito = sumatoriaDetalles2DecimalesDebito.setScale(2, RoundingMode.DOWN);
                                    }

                                    for (CmSincronizacionDetalle detalle : new ArrayList<>(hashDetalleServicio.values())) {
                                        detalleServicio.add(castCmSincronizacionDetalleToDetalleServicio(detalle));
                                    }

                                    cierreAuditoria.setValorNotaDebito(nuevoSumatoriaDebito.toString().replace(".", ","));
                                   
                                    if(detalleServicio != null && detalleServicio.size() > 0 ){
                                       detalleServicio = unificarRegistroPorConceptoMunicipio(detalleServicio);
                                    }
                                    if(detalleNotaDebito != null && detalleNotaDebito.size()> 0 ){
                                       detalleNotaDebito = unificarRegistroPorConceptoMunicipio(detalleNotaDebito);
                                    }  
                                    cierreAuditoria.setDetalleNotaDebito(detalleNotaDebito);     
                                    cierreAuditoria.setDetalleServicio(detalleServicio);
                                }
                            }
                            listaCierreAuditoriaFactura.add(cierreAuditoria);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger("Error en sincronizacionNotificacionFactura : " + NotificacionFacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                String mensajeRespuestaJson = "";
                boolean hayDetallesEnviar = true;
                if (cierreAuditoria.getDetalleNotaDebito() == null && cierreAuditoria.getDetalleServicio() == null) {
                    mensajeRespuestaJson = "No se han generado detalles para enviar a sap";
                    hayDetallesEnviar = false;
                }

                String usuario = PropApl.getInstance().get(PropApl.CM_WS_NOTIFICACION_FACTURA_USUARIO_TOKEN);
                String contrasena = PropApl.getInstance().get(PropApl.CM_WS_NOTIFICACION_FACTURA_PASSWORD_TOKEN);
                String urlToken = PropApl.getInstance().get(PropApl.RUTA_SERVICIO_TOKEN_CONCILIACION_FACTURA);
                RespuestaTokenIntersavia token = generarTokenExterno(usuario, contrasena, urlToken);

                if (!hayDetallesEnviar) {
                    CmRadicado radicado = getCmRadicadoRemoto().consultar(idRadicado);
                    radicado.setEstado_radicado(Boolean.TRUE);
                    getCmRadicadoRemoto().actualizar(radicado);
                    estadoTransaccion = CmSincronizacion.ESTADO_TRANSACCION_TERMINADA;
                } else {
                    if (CmSincronizacion.ESTADO_TRANSACCION_INICIADA == estadoTransaccion) {
                        mensajeRespuestaJson = (token == null) ? "Se ha producido un error en el servicio :"
                                + urlToken
                                : TEXTO_INICIO_ENVIO_SAP;
                        estadoTransaccion = (token == null) ? CmSincronizacion.CODIGO_RESPUESTA_ERROR_TOKEN : CmSincronizacion.CODIGO_RESPUESTA_ENVIO_SAP;
                    }
                }
                              
                String jsonEntrada = crearPeticionEnvioM2(listaCierreAuditoriaFactura,getTipoEnvioReceptionPeticion(),idAuditoriaCierre,tipoTransaccion);
               
                for (SincronizacionNotificacionFactura sincronizacionNotificacionFactura : listSincronizacionNotificacionFactura) {
                    CmSincronizacion sincronizacion = new CmSincronizacion();
                    sincronizacion.setCmGlosaRespuesta(new CmGlosaRespuesta(sincronizacionNotificacionFactura.getGlosaRespuestaId()));
                    sincronizacion.setCmConciliacion(new CmConciliacion(sincronizacionNotificacionFactura.getConciliacionId()));
                    sincronizacion.setCmAuditoriaCierre(new CmAuditoriaCierre(sincronizacionNotificacionFactura.getAuditoriaCierreId()));
                    sincronizacion.setCmRadicado(new CmRadicado(sincronizacionNotificacionFactura.getRadicadoId()));
                    sincronizacion.setJsonEnvio(jsonEntrada.getBytes());
                    sincronizacion.setJsonRespuesta(mensajeRespuestaJson.getBytes());
                    sincronizacion.setFechaHoraEnvio(new Date());
                    sincronizacion.setPaquetes(1);
                    sincronizacion.setPaquetesExitosos(0);
                    sincronizacion.setMomento(CmSincronizacion.MOMENTO_CIERRE_AUDITORIA);
                    sincronizacion.setEstadoTransacion(estadoTransaccion);
                    sincronizacion.setId(getCmSincronizacionRemoto().insertar(sincronizacion));
                    if (CmSincronizacion.CODIGO_RESPUESTA_ENVIO_SAP == estadoTransaccion) {
                        EnvioSapCierreAuditoriaFacturaHilo cierreAuditoriaHilo = new EnvioSapCierreAuditoriaFacturaHilo(token, jsonEntrada, sincronizacion, getTipoEnvioReceptionPeticion());
                        cierreAuditoriaHilo.start();
                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger("Error al notificar cierre auditoria SAP : " + NotificacionFacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void consumoServicioNotificacionFactura(List<SincronizacionNotificacionFactura> listCmRadicados) {

        CmSincronizacion cmSincronizacion = null;
        String cadenaSeguimientoProcesoActual = "";
        String cadenaSeguimientoSubProcesoActual = "";
        try {
            GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
            gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            gsonBuilderRespuesta.serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();
            setTipoEnvioReceptionPeticion(TIPO_PETICION_TRADICIONAL);

            int cantidadFacturasProcesar;

            if (!listCmRadicados.isEmpty()) {

                for (SincronizacionNotificacionFactura cmRadicado : listCmRadicados) {

                    int estadoTransaccion;
                    String mensajeRespuestaSincronizacion;
                    String usuarioToken = PropApl.getInstance().get(PropApl.CM_WS_NOTIFICACION_FACTURA_USUARIO_TOKEN);
                    String contrasenaToken = PropApl.getInstance().get(PropApl.CM_WS_NOTIFICACION_FACTURA_PASSWORD_TOKEN);
                    String urlServicioObtenerToken = PropApl.getInstance().get(PropApl.RUTA_SERVICIO_TOKEN_CONCILIACION_FACTURA);
                    String exception = "";
                    String jsonTotalEnvioParaSap;
                    RespuestaTokenIntersavia token = null;
                    List<NotificacionFactura> listaTotalNotificacionFacturaToJosn = new ArrayList<>();
                    List<NotificacionFactura> listaPaqueteNotificacionFacturaToJosn = new ArrayList<>();

                    List<CmSincronizacionEncabezado> encabezadosActivos = getCmSincronizacionEncabezadoRemoto().consultarDetalles(cmRadicado.getRadicadoId());
                    cantidadFacturasProcesar = encabezadosActivos.size();
                    int paquetes = calcularNumeroPaquetes(cantidadFacturasProcesar);
                    cmSincronizacion = crearCmSincronizacion(cmRadicado, CmSincronizacion.MOMENTO_RESPUESTA_GLOSA_CONCILIACION, paquetes);

                    if (!encabezadosActivos.isEmpty()) {
                        List<CmSincronizacionDetalle> detalles;
                        int acumuladorFacturasPorPaquete = 0;
                        int acumuladorFacturaTotal = 0;
                        CmSincronizacionEncabezado encabezado = encabezadosActivos.get(0);
                           
                        cadenaSeguimientoProcesoActual = " idRadicado: "+cmRadicado.getRadicadoId();
                        cadenaSeguimientoSubProcesoActual = " , idEncabezadoSincronizado : "+encabezado.getId();
                        
                        CmFactura cmFactura = getCmFacturaRemoto().consultar(encabezado.getFactura());
                        if (CmFactura.ORIGEN_FACTURA_INTRASAVIA == cmFactura.getOrigenFactura()) {
                            setTipoEnvioReceptionPeticion(TIPO_PETICION_INTRASAVIA);
                        }

                        for (CmSincronizacionEncabezado cmSincronizacionEncabezado : encabezadosActivos) {
                            cadenaSeguimientoSubProcesoActual = " , idEncabezadoSincronizado : "+encabezado.getId();
                            detalles = new ArrayList<>();
                            detalles.addAll(getCmSincronizacionDetalleRemoto().consultarDetallesEncabezado(cmSincronizacionEncabezado.getId()));
                            if (!detalles.isEmpty()) {
                                
                                cmSincronizacionEncabezado.setNumeroContrato(obtenerNumeroContrato(cmSincronizacionEncabezado));                                                       
                                String contrato = (TIPO_PETICION_INTRASAVIA == getTipoEnvioReceptionPeticion()) ? CONTRATO + detalles.get(0).getCodigoMunicipio() :cmSincronizacionEncabezado.getNumeroContrato();
                                cmSincronizacionEncabezado.setNumeroContrato(contrato);
                               
                                getCmFacturaTransaccionServicioRemoto().insertarTransaccionEnvioFactura(cmSincronizacionEncabezado.getFactura(), asignarTipoMomento(cmRadicado.getTipoTransaccion()) , CmFacturaTransaccion.ESTADO_ENVIADA);
                                
                                String tipoTransaccion = obtenerTipoTransaccion(getTipoEnvioReceptionPeticion(), cmRadicado.getTipoTransaccion());
                                NotificacionFactura notificacionToJson = castCmSincronizacionEncabezadoToNotificacionFactura(cmSincronizacionEncabezado, detalles.get(0),tipoTransaccion);
                                List<DetalleServicio> detalleServicio = new ArrayList<>();
                                for (CmSincronizacionDetalle detalle : detalles) {
                                    detalleServicio.add(castCmSincronizacionDetalleToDetalleServicio(detalle));
                                }
                                if (detalleServicio.size() > 0) {
                                    detalleServicio = unificarRegistroPorConceptoMunicipio(detalleServicio);
                                }
                                notificacionToJson.setDetalleServicio(detalleServicio);
                                listaTotalNotificacionFacturaToJosn.add(notificacionToJson);
                                listaPaqueteNotificacionFacturaToJosn.add(notificacionToJson);
                            }
                            acumuladorFacturasPorPaquete++;
                            acumuladorFacturaTotal++;

                            if (TAMANO_PAQUETE_POR_FACTURAS == acumuladorFacturasPorPaquete
                                    || cantidadFacturasProcesar == acumuladorFacturaTotal) {

                                CmSincronizacionPaquete sincronizacionPaquete = guardarSincronizacionPaquete(cmSincronizacion.getId(), "En proceso");
                                sincronizacionPaquete.setTipoTransaccion(asignarTipoMomento(cmRadicado.getTipoTransaccion()));
                                        
                                String jsonEnvioPaquete = crearPeticionEnvioM3M4(listaPaqueteNotificacionFacturaToJosn, getTipoEnvioReceptionPeticion(), sincronizacionPaquete.getId(),cmRadicado.getTipoTransaccion() );
                                                                
                                Integer codigoRespuestaPaquete = CmSincronizacion.CODIGO_RESPUESTA_ENVIO_SAP;
                                Integer estadoTransaccionPaquete = CmSincronizacionPaquete.ESTADO_TRANSACCION_INICIADA;
                                String mensajeRespuestaSincronizacionPaquete = TEXTO_INICIO_ENVIO_SAP;

                                try {
                                    token = generarTokenExterno(usuarioToken, contrasenaToken, urlServicioObtenerToken);
                                } catch (Exception e) {
                                    exception = " : " + e.toString();
                                }
                                if (token == null) {
                                    mensajeRespuestaSincronizacionPaquete = "Se ha producido un error en el servicio :"
                                            + PropApl.getInstance().get(PropApl.RUTA_SERVICIO_TOKEN_CONCILIACION_FACTURA) + exception;
                                    jsonEnvioPaquete = mensajeRespuestaSincronizacionPaquete;
                                    codigoRespuestaPaquete = CmSincronizacion.CODIGO_RESPUESTA_ERROR_TOKEN;
                                    estadoTransaccionPaquete = CmSincronizacion.ESTADO_TRANSACCION_TERMINADA;
                                }

                                sincronizacionPaquete.setEstadoTransacion(estadoTransaccionPaquete);
                                sincronizacionPaquete.setCodigoRespuesta(codigoRespuestaPaquete);
                                sincronizacionPaquete.setJsonEnvio(jsonEnvioPaquete.getBytes());
                                sincronizacionPaquete.setJsonRespuesta(mensajeRespuestaSincronizacionPaquete.getBytes());
                                getCmSincronizacionPaqueteRemoto().actualizar(sincronizacionPaquete);

                                jsonTotalEnvioParaSap = gson.toJson(listaTotalNotificacionFacturaToJosn);
                                cmSincronizacion.setJsonEnvio(jsonTotalEnvioParaSap.getBytes());
                                if( CmSincronizacion.ESTADO_TRANSACCION_TERMINADA == estadoTransaccionPaquete){
                                    cmSincronizacion.setEstadoTransacion(estadoTransaccionPaquete);
                                    cmSincronizacion.setJsonRespuesta(mensajeRespuestaSincronizacionPaquete.getBytes());
                                }
                                getCmSincronizacionRemoto().actualizar(cmSincronizacion);

                                boolean envioExitoso = true;
                                if (CmSincronizacionPaquete.ESTADO_TRANSACCION_INICIADA == estadoTransaccionPaquete) {
                                    envioExitoso = enviarPeticionSapMomento3(token, jsonEnvioPaquete, sincronizacionPaquete, cmSincronizacion, getTipoEnvioReceptionPeticion());
                                }

                                listaPaqueteNotificacionFacturaToJosn.clear();
                                acumuladorFacturasPorPaquete = 0;

                                if (!envioExitoso || CmSincronizacion.ESTADO_TRANSACCION_TERMINADA == estadoTransaccionPaquete) {
                                    break;
                                }
                            }
                        }
                    }

                    if (listaTotalNotificacionFacturaToJosn.isEmpty()) {
                        mensajeRespuestaSincronizacion = "No se han generado facturas para enviar a sap";
                        jsonTotalEnvioParaSap = mensajeRespuestaSincronizacion;
                        estadoTransaccion = CmSincronizacion.ESTADO_TRANSACCION_TERMINADA;
                        CmRadicado radicado = getCmRadicadoRemoto().consultar(cmRadicado.getRadicadoId());
                        radicado.setEstado_radicado(Boolean.TRUE);
                        getCmRadicadoRemoto().actualizar(radicado);
                        if (cmSincronizacion.getId() > 0) {
                            cmSincronizacion.setJsonEnvio(jsonTotalEnvioParaSap.getBytes());
                            cmSincronizacion.setJsonRespuesta(mensajeRespuestaSincronizacion.getBytes());
                            cmSincronizacion.setEstadoTransacion(estadoTransaccion);
                            getCmSincronizacionRemoto().actualizar(cmSincronizacion);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            agregarExcepcionSincronizacion(cmSincronizacion, "-Desc Error {"+cadenaSeguimientoProcesoActual + cadenaSeguimientoSubProcesoActual+ " - error:"+ex.toString()+"}");
        }
    }
    
    public void agregarExcepcionSincronizacion(CmSincronizacion cmSincronizacion, String mensajeError) {
        try {
            if (cmSincronizacion != null && cmSincronizacion.getId() > 0) {
                String mensageFinal = cmSincronizacion.getJsonEnvio() != null ? new String(cmSincronizacion.getJsonEnvio()) :"";
                mensageFinal += mensajeError;
                cmSincronizacion.setJsonEnvio(mensageFinal.getBytes() );
                cmSincronizacion.setJsonRespuesta("".getBytes());
                cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_TERMINADA);
                getCmSincronizacionRemoto().actualizar(cmSincronizacion);
            }
        } catch (Exception e) {
        }
    }

    public boolean consumoServicioNotificacionFacturaM1(List<SincronizacionNotificacionFactura> listaReintentos) throws Exception {
        boolean radicado = false;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        setTipoEnvioReceptionPeticion(TIPO_PETICION_TRADICIONAL);
        try {
            for (SincronizacionNotificacionFactura notificacion : listaReintentos) {
                if (notificacion.getCmFacturaId() != null) {
                    CmFactura cmFactura = getCmFacturaRemoto().consultar(notificacion.getCmFacturaId());
                    CmSincronizacion cmSincronizacion = new CmSincronizacion();
                    CmRadicado cmRadicado = new CmRadicado(notificacion.getRadicadoId());
                    cmRadicado.setCmFactura(new CmFactura(notificacion.getCmFacturaId()));
                    cmRadicado.setEstado_radicado(false);
                    cmSincronizacion.setUsuarioCrea(notificacion.getUsuarioCrea());
                    cmSincronizacion.setTerminalCrea(notificacion.getTerminalCrea());
                    cmSincronizacion.setFechaHoraCrea(notificacion.getFechaHoraCrea());
                    WsInterOperabilidad wsInterOperabilidad
                            = new WsInterOperabilidad(PropApl.CM_WS_NOTIFICACION_FACTURA_URL);
                    if (wsInterOperabilidad.getToken() != null) {
                        List<CmNotificacionFacturaDTO> listaNotificacionFacturas = new ArrayList<>();
                        CmNotificacionFacturaDTO cmNotificacionFactura = new CmNotificacionFacturaDTO();
                        cmNotificacionFactura.setConsecutivo(Integer.toString(notificacion.getCmFacturaId()));
                        cmNotificacionFactura.setNITProveedor(cmFactura.getNit().trim());
                        cmNotificacionFactura.setTipoDocumento(TIPO_DOCUMENTO_MOMENTO_1);
                        cmNotificacionFactura.setNumeroDocumento(cmFactura.getNumeroFacturado());
                        cmNotificacionFactura.setPeriodoPago("");
                        cmNotificacionFactura.setRegimenAfiliado(cmFactura.getMaeRegimenValor().substring(0, 1).toUpperCase());
                        cmNotificacionFactura.setValorDocumento(cmFactura.getValorFactura().add(cmFactura.getValorCopago()).toString().replace(".", ","));
                        cmNotificacionFactura.setNumeroDocumentoOrigen(cmFactura.getNumeroFacturado());
                        if (cmFactura.getValorCopago() != null) {
                            cmNotificacionFactura.setCopago(cmFactura.getValorCopago().toString().replace(".",","));
                        } else {
                            cmNotificacionFactura.setCopago("0");
                        }
                        //TODO SUM CUOTA MODERADORA
                        cmNotificacionFactura.setCuotaModeradora("0");
                        cmNotificacionFactura.setDescuento("0");
                        cmNotificacionFactura.setIVA("0");
                        cmNotificacionFactura.setValorNotaDebito(cmFactura.getValorFactura().toString().replace(".", ","));
                        cmNotificacionFactura.setFechaDocumento(dateFormat.format(cmFactura.getFechaPrestacion()));
                        cmNotificacionFactura.setFechaRadicacion(dateFormat.format(cmFactura.getFechaRadicacion()));
                        cmNotificacionFactura.setFechaProceso(dateFormat.format(new Date()));
                        cmNotificacionFactura.setDocumentoAnticipo("");
                        cmNotificacionFactura.setTipoTransaccion(TIPO_TRANSACCION_MOMENTO_1);
                        if (cmFactura.getNumeroContrato() != null) {
                            cmNotificacionFactura.setContrato(cmFactura.getNumeroContrato());
                        }
                        listaNotificacionFacturas.add(cmNotificacionFactura);
                        
                        String jsonFactura = crearPeticionEnvioM1(listaNotificacionFacturas, getTipoEnvioReceptionPeticion(), notificacion.getCmFacturaId(),notificacion.getTipoTransaccion());
                        
                        cmSincronizacion.setFechaHoraEnvio(new Date());
                        cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_INICIADA);
                                             
                        getCmFacturaTransaccionServicioRemoto().insertarTransaccionEnvioFactura(notificacion.getCmFacturaId(), 
                                                                     CmFacturaTransaccion.TIPO_MOMENTO1, CmFacturaTransaccion.ESTADO_ENVIADA);                     
                        
                        try {
                            cmSincronizacion.setJsonEnvio(jsonFactura.getBytes());
                            Response respuestaPost = wsInterOperabilidad.consumoPostGenerico(jsonFactura);
                            cmSincronizacion.setPaquetes(1);
                            cmSincronizacion.setPaquetesExitosos(1);
                            cmSincronizacion.setJsonRespuesta(respuestaPost.readEntity(String.class).getBytes());
                            cmSincronizacion.setCodigoRetorno(respuestaPost.getStatus());
                            cmSincronizacion.setUsuarioCrea(notificacion.getUsuarioCrea());
                            cmSincronizacion.setTerminalCrea(notificacion.getTerminalCrea());
                            cmSincronizacion.setFechaHoraCrea(new Date());
                            cmSincronizacion.setMomento(MOMENTO_1);
                            cmSincronizacion.setCmRadicado(cmRadicado);
                            cmSincronizacion.setCmFactura(new CmFactura(notificacion.getCmFacturaId()));
                            if (respuestaPost.getStatus() == 200) {
                                
                                procesarPeticionRespuestaM1(respuestaPost.getEntity().toString(), cmSincronizacion, getTipoEnvioReceptionPeticion(), cmFactura);
                                   
                            } else {
                                cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_TERMINADA_CON_ERRORES);
                            }
                        } catch (Exception ex) {
                            cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_FALLIDA);
                        } finally {
                            getCmSincronizacionRemoto().insertar(cmSincronizacion);
                        }
                        //SI EL TOKEN TIENE UN ERROR
                    } else {

                    }
                }
            }
        } catch (Exception ex) {

        }
        return radicado;
    }

    public DevolucionFactura castCmAuditoriaDevolucionToDevolucionFactura(CmAuditoriaDevolucion auditoriaDevolucion) {
        DevolucionFactura devolucionFactura = new DevolucionFactura();
        String idConsecutivo = auditoriaDevolucion.getCmFactura() != null
                && auditoriaDevolucion.getCmFactura().getId() != null
                ? String.valueOf(auditoriaDevolucion.getCmFactura().getId())
                : auditoriaDevolucion.getNumeroRadicado();
        devolucionFactura.setConsecutivo(idConsecutivo);
        devolucionFactura.setNumeroRadicado(auditoriaDevolucion.getNumeroRadicado());
        String nit = auditoriaDevolucion.getNit() != null ? auditoriaDevolucion.getNit().trim() : "";
        devolucionFactura.setNITProveedor(nit);
        devolucionFactura.setCodigoTipo(auditoriaDevolucion.getMaeMotivoDevolucionCodigo());
        String descripcionTipo = auditoriaDevolucion.getMaeMotivoDevolucionCodigo() + " - " + auditoriaDevolucion.getMaeMotivoDevolucionValor();
        devolucionFactura.setDescripcionTipo(descripcionTipo);
        devolucionFactura.setJustificacion(auditoriaDevolucion.getObservacion());
        devolucionFactura.setNumeroDocumento(auditoriaDevolucion.getCmFactura().getNumeroFacturado());
        devolucionFactura.setResponsableDevolucion(auditoriaDevolucion.getUsuarioCrea());
        return devolucionFactura;
    }

    public CierreAuditoriaFactura castCmSincronizacionEncabezadoToCierreAuditoriaFactura(CmSincronizacionEncabezado encabezado, CmSincronizacionDetalle detalle) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CierreAuditoriaFactura cierreAuditoria = new CierreAuditoriaFactura();
        String idConsecutivo = encabezado.getFactura() != null && encabezado.getFactura() > 0 ? String.valueOf(encabezado.getFactura())
                : encabezado.getNumeroRadicado();
        cierreAuditoria.setConsecutivo(idConsecutivo);
        cierreAuditoria.setNITProveedor(encabezado.getProveedorNit().trim());
        cierreAuditoria.setTipoDocumento(TIPO_DOCUMENTO_MOMENTO_2);
        cierreAuditoria.setNumeroDocumento(encabezado.getNumeroDocumento());
        cierreAuditoria.setContrato(encabezado.getNumeroContrato());
        cierreAuditoria.setPeriodoPago(STRING_VACIO);
        cierreAuditoria.setRegimenAfiliado(encabezado.getRegimenInicial());
        
        String valorCopago = STRING_CERO;
        BigDecimal valorCopaBigDecimal = new BigDecimal("0");
        if(encabezado.getValorCopago() != null && encabezado.getValorCopago().compareTo(BigDecimal.ZERO)> 0){
            valorCopaBigDecimal = encabezado.getValorCopago().setScale(2, RoundingMode.DOWN);
            valorCopago = valorCopaBigDecimal.toString().replace(".", ",");
        }
        cierreAuditoria.setCopago(valorCopago);
        
        String valorDocumento = STRING_CERO;
        if (encabezado.getValorDocumento() != null
                && encabezado.getValorDocumento().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valorDoc = encabezado.getValorDocumento().setScale(2, RoundingMode.DOWN); 
            if (valorCopaBigDecimal.compareTo(BigDecimal.ZERO) > 0) {
                valorDoc = valorDoc.add(valorCopaBigDecimal).setScale(2, RoundingMode.DOWN);
            }
            valorDocumento = valorDoc.toString().replace(".", ",");
        }
        cierreAuditoria.setValorDocumento(valorDocumento);
        
        cierreAuditoria.setNumeroDocumentoOrigen(encabezado.getNumeroDocumento());    
        cierreAuditoria.setCuotaModeradora(STRING_CERO);
        cierreAuditoria.setDescuento(STRING_CERO);
        cierreAuditoria.setIVA(STRING_CERO);
        String valorNotaDebito = STRING_CERO;
        if (encabezado.getValorGlosado() != null
                && encabezado.getValorGlosado().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valorNota = encabezado.getValorGlosado().setScale(2, RoundingMode.DOWN);
            valorNotaDebito = valorNota.toString().replace(".", ",");
        }
        cierreAuditoria.setValorNotaDebito(valorNotaDebito);
        cierreAuditoria.setFechaDocumento(dateFormat.format(encabezado.getFechaHoraDocumento()));
        cierreAuditoria.setFechaRadicacion(dateFormat.format(encabezado.getFechaHoraCrea()));
        cierreAuditoria.setFechaProceso(dateFormat.format(new Date()));
        cierreAuditoria.setDocumentoAnticipo(STRING_CERO);
        cierreAuditoria.setTipoTransaccion(TIPO_TRANSACCION_MOMENTO_2);
        return cierreAuditoria;

    }

    public NotificacionFactura castCmSincronizacionEncabezadoToNotificacionFactura(CmSincronizacionEncabezado encabezado, CmSincronizacionDetalle detalle, String tipoTransaccion) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        NotificacionFactura notificaccionFactura = new NotificacionFactura();
        String idConsecutivo = encabezado.getFactura() != null && encabezado.getFactura() > 0 ? String.valueOf(encabezado.getFactura())
                : encabezado.getNumeroRadicado();
        notificaccionFactura.setConsecutivo(idConsecutivo);
        notificaccionFactura.setNITProveedor(encabezado.getProveedorNit().trim());
        notificaccionFactura.setTipoDocumento(TIPO_DOCUMENTO_MOMENTO_3);
        notificaccionFactura.setNumeroDocumento(encabezado.getNumeroDocumento());
        notificaccionFactura.setNumeroRadicado(encabezado.getNumeroRadicado());
        notificaccionFactura.setContrato(encabezado.getNumeroContrato());
        notificaccionFactura.setPeriodoPago(STRING_VACIO);
        notificaccionFactura.setRegimenAfiliado(encabezado.getRegimenInicial());
        BigDecimal valorDoc = new BigDecimal("0");
        if (encabezado.getValorPagado() != null
                && encabezado.getValorPagado().compareTo(BigDecimal.ZERO) > 0) {
            valorDoc = encabezado.getValorPagado().setScale(2, RoundingMode.DOWN);
        }
        notificaccionFactura.setValorDocumento(valorDoc.toString().replace(".", ","));
        notificaccionFactura.setNumeroDocumentoOrigen(encabezado.getNumeroDocumento());
        notificaccionFactura.setCopago(STRING_CERO);
        notificaccionFactura.setCuotaModeradora(STRING_CERO);
        notificaccionFactura.setDescuento(STRING_CERO);
        notificaccionFactura.setIVA(STRING_CERO);
        notificaccionFactura.setValorNotaDebito(STRING_CERO);
        notificaccionFactura.setFechaDocumento(dateFormat.format(encabezado.getFechaHoraDocumento()));
        notificaccionFactura.setFechaRadicacion(dateFormat.format(encabezado.getFechaHoraCrea()));
        notificaccionFactura.setFechaProceso(dateFormat.format(new Date()));
        notificaccionFactura.setDocumentoAnticipo(STRING_CERO);
        notificaccionFactura.setTipoTransaccion(tipoTransaccion);
        return notificaccionFactura;

    }

    public DetalleServicio castCmSincronizacionDetalleToDetalleServicio(CmSincronizacionDetalle detalle) {
        DetalleServicio detalleServicio = new DetalleServicio();
        detalleServicio.setMunicipio(detalle.getCodigoMunicipio());
        detalleServicio.setConceptoContable(detalle.getConceptoContable());
        BigDecimal valorOperacion = detalle.getValorOperacion().setScale(2, RoundingMode.DOWN);
        //se reasigna valor de la operacion a 2 decimales por exigencias sap.
        detalle.setValorOperacion(valorOperacion);
        detalleServicio.setValorOperacion(valorOperacion.toString().replace(".", ","));
        return detalleServicio;

    }
    
    private RespuestaTokenIntersavia generarTokenExterno(String usuario, String contrasena, String urlToken) throws Exception {
        RespuestaTokenIntersavia token;
        SolicitudToken solicitudToken = new SolicitudToken();
        solicitudToken.setUsername(usuario);
        solicitudToken.setPassword(contrasena);
        token = consumoToken(
                urlToken,
                solicitudToken
        );
        return token;
    }

    private static RespuestaTokenIntersavia consumoToken(String httpURL, SolicitudToken solicitud) throws Exception {
        RespuestaTokenIntersavia respuesta = null;

        try {
            //Objeto GSON
            GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
            gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd"); //Formato fecha 
            gsonBuilderRespuesta.serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();
            //Inicio consumos
            URL url = new URL(httpURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            //conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Type", MediaType.APPLICATION_FORM_URLENCODED);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            //String jsonEntrada = gson.toJson(solicitud);
            String jsonEntrada = "username=" + solicitud.getUsername() + "&password=" + solicitud.getPassword() + "&grant_type=";
            InputStreamReader read;
            int len = jsonEntrada.length();
            conn.setRequestProperty("Content-Length", Integer.toString(len));
            conn.connect();
            OutputStreamWriter outStr = new OutputStreamWriter(conn.getOutputStream());
            outStr.write(jsonEntrada, 0, len);
            outStr.flush();
            try {
                read = new InputStreamReader(conn.getInputStream(), "UTF-8");
            } catch (IOException exception) {
                if (conn.getErrorStream() != null) {
                    read = new InputStreamReader(conn.getErrorStream(), "UTF-8");
                }
            }
            //Lectura por linea
            InputStream ins = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            try (BufferedReader in = new BufferedReader(isr)) {
                String inputLine;
                String result = "";
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;
                }
                final RespuestaTokenIntersavia respuestasToken = gson.fromJson(result, RespuestaTokenIntersavia.class);
                if (respuestasToken == null) {
                     throw new Exception("Error al convertir RespuestaTokenIntersavia en consumoToken ");
                } else {
                    respuesta = respuestasToken;
                    respuesta.setToken(respuesta.getAccess_token());
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger("Error consumoToken : " + NotificacionFacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (IOException | JsonSyntaxException | JSONException ex) {
            Logger.getLogger("Error consumoToken : " + NotificacionFacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return respuesta;
    }

    public String obtenerNumeroContrato(CmSincronizacionEncabezado encabezado) {
        String numeroContrato = "";
        try {
            Integer idContrato = encabezado.getIdContrato();
            if (encabezado.getCmRadicadosId().getCmConciliacion() != null) {
                ParamConsulta paramConsulta = new ParamConsulta();
                String numeroDocumento = encabezado.getNumeroDocumento() != null
                        ? encabezado.getNumeroDocumento().trim() : "";
                String proveedorNit = encabezado.getProveedorNit() != null
                        ? encabezado.getProveedorNit().trim() : "";
                paramConsulta.setParametroConsulta1(numeroDocumento);
                paramConsulta.setParametroConsulta2(proveedorNit);
                List<CmFactura> facturas = getCmFacturaRemoto().consultarPorAtributos(paramConsulta);
                CmFactura factura = facturas != null && !facturas.isEmpty() ? facturas.get(0) : null;
                if (factura != null) {
                    idContrato = factura.getCmRipCarga() != null
                            && factura.getCmRipCarga().getCntContrato() != null
                            && factura.getCmRipCarga().getCntContrato().getId() != null
                            ? factura.getCmRipCarga().getCntContrato().getId() : 0;
                }
            }

            if (idContrato != null && idContrato != 0) {
                CntContrato contrato = getCntContratoRemoto().consultarDatosBasicos(idContrato);
                numeroContrato = contrato != null ? contrato.getContrato() : "";
            }
        } catch (Exception e) {
            Logger.getLogger("Error obtenerNumeroContrato : " + NotificacionFacturaServicio.class.getName()).log(Level.SEVERE, null, e);
        }
        return numeroContrato;
    }

    private static Response consumoPostGenerico(String httpUrl, RespuestaTokenIntersavia token, String jsonEntrada) {
        String result = "";
        Response response = null;
        try {
            //Objeto GSON
            GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
            gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd"); //Formato fecha 
            gsonBuilderRespuesta.serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();
            //Inicio consumos
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + token.getToken()); // Autenticacion 
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            InputStreamReader read;
            int len = jsonEntrada.length();
            conn.connect();
            OutputStreamWriter outStr = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
            outStr.write(jsonEntrada, 0, len);
            outStr.flush();
            try {
                read = new InputStreamReader(conn.getInputStream(), "UTF-8");
            } catch (IOException exception) {
                read = new InputStreamReader(conn.getErrorStream(), "UTF-8");
            }
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                //Lectura por linea
                InputStream ins = conn.getErrorStream();
                InputStreamReader isr = new InputStreamReader(ins);
                try (BufferedReader in = new BufferedReader(isr)) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        result += inputLine;
                    }

                    response = Response.status(conn.getResponseCode()).entity(result).build();
                }
            } else {
                //Lectura por linea
                InputStream ins = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(ins);
                try (BufferedReader in = new BufferedReader(isr)) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        result += inputLine;
                    }
                    response = Response.status(conn.getResponseCode()).entity(result).build();
                }
            }
        } catch (MalformedURLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        } catch (JsonSyntaxException | JSONException ex) {
            System.err.println("ERROR: " + ex.toString());
        }
        return response;
    }
    
    private  List<DetalleServicio> unificarRegistroPorConceptoMunicipio( List<DetalleServicio> detallesServicioEntrada ){
        List<DetalleServicio> detalleServicioSalida;
        HashMap<String, DetalleServicio> hashMunicipioConcepto = new HashMap<>();
        try {
            
            for (DetalleServicio servicio : detallesServicioEntrada) {
                 String idMunicipioConcepto = servicio.getMunicipio() + "-" + servicio.getConceptoContable();
                if (hashMunicipioConcepto.get(idMunicipioConcepto) == null) {
                    hashMunicipioConcepto.put(idMunicipioConcepto, servicio);
                } else {
                    DetalleServicio detalleMunicipioConcepto = hashMunicipioConcepto.get(idMunicipioConcepto);
                    BigDecimal valorHashMunicipioConcepto = new BigDecimal(detalleMunicipioConcepto.getValorOperacion().replace(",", "."));
                    BigDecimal valorServicio = new BigDecimal(servicio.getValorOperacion().replace(",", "."));
                    BigDecimal valorAcumulado = valorHashMunicipioConcepto.
                            add(valorServicio).setScale(2, RoundingMode.DOWN);
                    detalleMunicipioConcepto.setValorOperacion(valorAcumulado.toString().replace(".", ","));
                }
            }
            
            detalleServicioSalida = new ArrayList<>(hashMunicipioConcepto.values());

        } catch (Exception e) {
            detalleServicioSalida = new ArrayList<>();
            Logger.getLogger("Error unificarRegistroPorConceptoMunicipio : " + NotificacionFacturaServicio.class.getName()).log(Level.SEVERE, null, e);
        }

        return detalleServicioSalida;
    }
    
    private CmSincronizacion crearCmSincronizacion( SincronizacionNotificacionFactura cmRadicado, int tipoMomento, int paquetes) throws Exception {
        CmSincronizacion sincronizacion = new CmSincronizacion();
        sincronizacion.setCmGlosaRespuesta(new CmGlosaRespuesta(cmRadicado.getGlosaRespuestaId()));
        sincronizacion.setCmConciliacion(new CmConciliacion(cmRadicado.getConciliacionId()));
        sincronizacion.setCmAuditoriaCierre(new CmAuditoriaCierre(cmRadicado.getAuditoriaCierreId()));
        sincronizacion.setCmRadicado(new CmRadicado(cmRadicado.getRadicadoId()));
        sincronizacion.setCmFactura(new CmFactura(cmRadicado.getCmFacturaId()));
        sincronizacion.setJsonEnvio("".getBytes());
        sincronizacion.setJsonRespuesta("".getBytes());
        sincronizacion.setFechaHoraEnvio(new Date());
        sincronizacion.setPaquetes(paquetes);
        sincronizacion.setPaquetesExitosos(0);
        sincronizacion.setMomento(tipoMomento);
        sincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_INICIADA);
        sincronizacion.setId(getCmSincronizacionRemoto().insertar(sincronizacion));
        return sincronizacion;
    }
    
    private int calcularNumeroPaquetes(int numeroFacturas) throws Exception {
        int numeroPaquete;
        if (numeroFacturas <= TAMANO_PAQUETE_POR_FACTURAS) {
            numeroPaquete = 1;
        } else {
            BigDecimal bigDecimal = new BigDecimal(numeroFacturas).divide(new BigDecimal(TAMANO_PAQUETE_POR_FACTURAS));
            numeroPaquete = bigDecimal.intValue();
            bigDecimal = bigDecimal.subtract(new BigDecimal(numeroPaquete));
            if (bigDecimal.compareTo(new BigDecimal(BigInteger.ZERO)) > 0) {
                numeroPaquete = numeroPaquete + 1;
            }
        }
        return numeroPaquete;
    }
    
    private boolean enviarPeticionSapMomento3(RespuestaTokenIntersavia token, String jsonEnvioPaquete, CmSincronizacionPaquete sincronizacionPaqute,CmSincronizacion cmSincronizacion, int tipoEnvioRespuesta )  {
          
        List<CmRadicado> listaRadicados = new ArrayList<>();
        int estadoTransaccionPaquete = 0;
        int codigo;
        String mensajeRespuesta = "";
        String codigoRespuesta = "";
      
        LinkedHashMap<Integer, Integer> hashIdsUrlNotificacion = new LinkedHashMap();
        hashIdsUrlNotificacion.put(TIPO_PETICION_TRADICIONAL, PropApl.CM_WS_NOTIFICACION_FACTURA_URL);
        hashIdsUrlNotificacion.put(TIPO_PETICION_PAQUETE, PropApl.CM_WS_NOTIFICACION_FACTURA_URL);
        hashIdsUrlNotificacion.put(TIPO_PETICION_INTRASAVIA, PropApl.CM_WS_NOTIFICACION_FACTURA_INTEGRA_URL);
        
        int idUrlNotificacion = Optional.ofNullable( hashIdsUrlNotificacion.get(tipoEnvioRespuesta)).orElse(PropApl.CM_WS_NOTIFICACION_FACTURA_URL) ;

        String urlServicioEnvioMomento3 = PropApl.getInstance().get(idUrlNotificacion);
        boolean envioExitoso = true;
        try {
            Response response = consumoPostGenerico(urlServicioEnvioMomento3, token, jsonEnvioPaquete);
            if (response != null && response.getStatus() == 200) {
                
                boolean ejecucionTotalExitosa = procesarPeticionRespuestaM3M4(response.getEntity().toString(), tipoEnvioRespuesta, listaRadicados, sincronizacionPaqute.getTipoTransaccion());
             
                if (ejecucionTotalExitosa) {
                    estadoTransaccionPaquete = CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_EXITOSA;
                    codigoRespuesta = CmSincronizacionPaquete.CODIGO_RESPUESTA_ENVIO_EXITOSO+"";
                    mensajeRespuesta = CmSincronizacionPaquete.getCodigoRespuestaStr(CmSincronizacionPaquete.CODIGO_RESPUESTA_ENVIO_EXITOSO);
                } else {
                    estadoTransaccionPaquete = CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_CON_ERRORES;
                    codigoRespuesta = CmSincronizacionPaquete.CODIGO_RESPUESTA_ENVIO_CON_NOVEDADES+"";
                    mensajeRespuesta = CmSincronizacionPaquete.getCodigoRespuestaStr(CmSincronizacionPaquete.CODIGO_RESPUESTA_ENVIO_CON_NOVEDADES);
                }
            }

            if (response != null) {
                if (response.getStatus() != 200) {
                    estadoTransaccionPaquete = CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_INESPERADA;
                    envioExitoso = false;
                }

                if (sincronizacionPaqute != null) {
                    try {
                        codigo = Integer.parseInt(codigoRespuesta);
                    } catch (NumberFormatException e) {
                        codigo = 0;
                    }
                    sincronizacionPaqute.setCodigoRespuesta(codigo);
                    sincronizacionPaqute.setCodigoRetorno(response.getStatus());
                    sincronizacionPaqute.setEstadoTransacion(estadoTransaccionPaquete);
                    sincronizacionPaqute.setJsonRespuesta(response.getEntity().toString().getBytes());
                    sincronizacionPaqute.setFechaHoraRespuesta(new Date());
                    sincronizacionPaqute.setMensajeRespuesta(mensajeRespuesta);
                    getCmSincronizacionPaqueteRemoto().actualizar(sincronizacionPaqute);
                }

                if (cmSincronizacion != null) {
                    cmSincronizacion.setNumeroPaquetesProcesados(cmSincronizacion.getNumeroPaquetesProcesados() + 1);
                    int paquetesProcesados = cmSincronizacion.getNumeroPaquetesProcesados();

                    cmSincronizacion = getCmSincronizacionRemoto().consultar(cmSincronizacion.getId());
                    int cantidadPaquetesEjecutadoExitosos = cmSincronizacion.getPaquetesExitosos();

                    if (CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_EXITOSA == estadoTransaccionPaquete) {
                        cantidadPaquetesEjecutadoExitosos = cmSincronizacion.getPaquetesExitosos() + 1;
                        cmSincronizacion.setPaquetesExitosos(cantidadPaquetesEjecutadoExitosos);
                    }
                    if (paquetesProcesados == cmSincronizacion.getPaquetes()) {
                        cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_TERMINADA);
                        cmSincronizacion.setCodigoRespuesta(0);
                        cmSincronizacion.setFechaHoraRespuesta(new Date());
                    }
                    if (cantidadPaquetesEjecutadoExitosos == cmSincronizacion.getPaquetes()) {
                        cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_TERMINADA_EXITOSA);
                    }

                    String nuevaRespuesta = " Paquete procesado de id " + sincronizacionPaqute.getId()
                            + " con estado : " + sincronizacionPaqute.getEstadoTransacionStr();
                    if (paquetesProcesados > 1) {
                        String respuestaAnterior = new String(cmSincronizacion.getJsonRespuesta());
                        nuevaRespuesta += " - " + respuestaAnterior;
                    }
                    cmSincronizacion.setJsonRespuesta(nuevaRespuesta.getBytes());
                    cmSincronizacion.setMensajeRespuesta(nuevaRespuesta);
                    cmSincronizacion.setCodigoRetorno(response.getStatus());
                    getCmSincronizacionRemoto().actualizar(cmSincronizacion);
                }
            }

            for (CmRadicado radicado : listaRadicados) {
                List<CmSincronizacionEncabezado> encabezado = getCmSincronizacionEncabezadoRemoto().consultarDetalles(radicado.getId());
                if (encabezado.isEmpty()) {
                    radicado.setEstado_radicado(Boolean.TRUE);
                    getCmRadicadoRemoto().actualizar(radicado);
                }
            }

            if (response == null) {
                String mensajeError = "No se ha obtenido respuesta sap.";

                if ( cmSincronizacion != null) {
                    cmSincronizacion.setNumeroPaquetesProcesados(cmSincronizacion.getNumeroPaquetesProcesados() + 1);
                    int paquetesProcesados = cmSincronizacion.getNumeroPaquetesProcesados();
                    cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_FALLIDA);
                    if (paquetesProcesados == cmSincronizacion.getPaquetes()) {
                        cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_TERMINADA);
                        cmSincronizacion.setCodigoRespuesta(0);
                    }
                    cmSincronizacion.setMensajeRespuesta(mensajeError);
                    cmSincronizacion.setFechaHoraRespuesta(new Date());
                    getCmSincronizacionRemoto().actualizar(cmSincronizacion);
                }

                if (sincronizacionPaqute != null) {
                    registrarSincronizacionExcepcion(sincronizacionPaqute, CmSincronizacionPaquete.ESTADO_TRANSACCION_FALLIDA, mensajeError);
                }              
                envioExitoso = false;
            }
     
        } catch ( Exception ex ) {
            Logger.getLogger(NotificacionFacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
            registrarSincronizacionExcepcion(sincronizacionPaqute, CmSincronizacionPaquete.ESTADO_TRANSACCION_FALLIDA, ex.toString());
            envioExitoso = false;
        }
        
        return envioExitoso;
    }
    
    
     private boolean procesarPeticionRespuestaM1(String respuesta, CmSincronizacion cmSincronizacion, int tipoEnvio, CmFactura cmFactura) {

        tipoEnvio = tipoEnvio == 0 ? TIPO_PETICION_TRADICIONAL : tipoEnvio;
        boolean procesamietoCompleto = true;
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        NotificacionUtilidades notificacionUtilidades = new NotificacionUtilidades();
        try {
            switch (tipoEnvio) {
                case TIPO_PETICION_TRADICIONAL:
                    JsonArray entries = (JsonArray) new JsonParser().parse(respuesta);
                    if (entries != null && entries.size() > 0) {
                        for (JsonElement entry : entries) {
                            RespuestaNotificacionFactura facturaSap = gson.fromJson((JsonObject) entry, RespuestaNotificacionFactura.class);
                            if (facturaSap.getCodigoResultado().equals(CODIGO_EXITO_RESPUESTA_M1) || 
                                facturaSap.getCodigoResultado().equals(CODIGO_YA_SE_REALIZO_M1) ||
                                facturaSap.getCodigoResultado().equals(CODIGO_FACTURA_CREADA_SAP)) {
                                cmSincronizacion.setMensajeRespuesta(facturaSap.getResultado());
                                cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_TERMINADA_EXITOSA);
                                CmRadicado cmRadicado = cmSincronizacion.getCmRadicado();
                                cmRadicado.setEstado_radicado(true);
                                getCmRadicadoRemoto().actualizarEstado(cmRadicado);
                                cmFactura.setEstadoFactura(CmFactura.ESTADO_FACTURA_SIN_AUDITORIA);
                                getCmFacturaRemoto().actualizar(cmFactura);
                            } else {
                                cmSincronizacion.setMensajeRespuesta(facturaSap.getResultado());
                                cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_TERMINADA_CON_ERRORES);
                            }
                            cmSincronizacion.setCodigoRespuesta(Integer.parseInt(facturaSap.getCodigoResultado()));
                            notificacionUtilidades.actualizarHistoricoTransaccionFacturaM3M4(facturaSap, CmFacturaTransaccion.TIPO_MOMENTO1);
                        }
                    }
                    break;
                 case TIPO_PETICION_PAQUETE:
                     //NuevoParceoSap 
                     RespuestaNotificacionFacturaPaquete paquete = gson.fromJson(respuesta, RespuestaNotificacionFacturaPaquete.class);
                     if (paquete != null) {
                         for (RespuestaNotificacionFactura factura : paquete.getFacturas()) {
                             if ( factura.getCodigoResultado().equals(CODIGO_EXITO_RESPUESTA_M1) ||
                                  factura.getCodigoResultado().equals(CODIGO_YA_SE_REALIZO_M1) || 
                                  factura.getCodigoResultado().equals(CODIGO_FACTURA_CREADA_SAP)) {
                                 cmSincronizacion.setMensajeRespuesta(factura.getResultado());
                                 cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_TERMINADA_EXITOSA);
                                 CmRadicado cmRadicado = cmSincronizacion.getCmRadicado();
                                 cmRadicado.setEstado_radicado(true);
                                 getCmRadicadoRemoto().actualizarEstado(cmRadicado);
                                 cmFactura.setEstadoFactura(CmFactura.ESTADO_FACTURA_SIN_AUDITORIA);
                                 getCmFacturaRemoto().actualizar(cmFactura);
                             } else {
                                 cmSincronizacion.setMensajeRespuesta(factura.getResultado());
                                 cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_TERMINADA_CON_ERRORES);
                             }
                             cmSincronizacion.setCodigoRespuesta(Integer.parseInt(factura.getCodigoResultado()));
                             notificacionUtilidades.actualizarHistoricoTransaccionFacturaM3M4(factura, CmFacturaTransaccion.TIPO_MOMENTO1);
                         }
                     }
                     break;
             }
         } catch (SQLException | ClassNotFoundException ex) {
             procesamietoCompleto = false;
         } catch (Exception ex) {
             procesamietoCompleto = false;
         }
         return procesamietoCompleto;
    }

    private boolean procesarPeticionRespuestaM3M4(String respuesta, int tipoEnvio, List<CmRadicado> listaRadicados, int tipoTransaccion) {

        tipoEnvio = tipoEnvio == 0 ? TIPO_PETICION_TRADICIONAL : tipoEnvio;
        boolean procesamietoCompleto = false;
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        int facturasProcesadas = 0;
        int facturasProcesdasExitosas = 0;
        NotificacionUtilidades notificacionUtilidades = new NotificacionUtilidades();
        
        try {
            switch (tipoEnvio) {
                case TIPO_PETICION_TRADICIONAL:
                    JsonArray entries = (JsonArray) new JsonParser().parse(respuesta);

                    if (entries != null && entries.size() > 0) {
                        facturasProcesadas = entries.size();
                        for (JsonElement entry : entries) {
                            RespuestaNotificacionFactura factura = gson.fromJson((JsonObject) entry, RespuestaNotificacionFactura.class);
                            if (factura.getCodigoResultado().equals(CODIGO_NOTAS_CREADAS_EXITOSAS_M3)) {
                                  int idFactura = Integer.parseInt(factura.getConsecutivo());
                                  CmSincronizacionEncabezado sincronizacionEncabezado = getCmSincronizacionEncabezadoRemoto().consultarEnacabezadoPorNitConcecutivo(factura.getNITProveedor(), Integer.valueOf(factura.getConsecutivo()));

                                  if (sincronizacionEncabezado != null) {
                       
                                    CmRadicado radicado = getCmRadicadoRemoto().consultar(sincronizacionEncabezado.getCmRadicadosId().getId());
                                    listaRadicados.add(radicado);
                                    int nuevoEstadoFactura = obtenerNuevoEstadoFactura(radicado);
                                    if (cambiarEstadoFactura(nuevoEstadoFactura, idFactura)) {
                                        sincronizacionEncabezado.setEstado(CmSincronizacionEncabezado.ESTADO_FINALIZADO);
                                        getCmSincronizacionEncabezadoRemoto().actualizar(sincronizacionEncabezado);
                                        guardarHistorialEstadoFactura(nuevoEstadoFactura, idFactura, sincronizacionEncabezado);
                                        notificacionUtilidades.actualizarUsuarioPorMomento(sincronizacionEncabezado, nuevoEstadoFactura);
                                    }
                                }
                                facturasProcesdasExitosas++;
                            }
                              notificacionUtilidades.actualizarHistoricoTransaccionFacturaM3M4(factura, tipoTransaccion);
                          }
                      }
                      procesamietoCompleto = (facturasProcesadas == facturasProcesdasExitosas);
                      break;
                  case TIPO_PETICION_PAQUETE:
                      //NuevoParceoSap 
                      RespuestaNotificacionFacturaPaquete paquete = gson.fromJson(respuesta, RespuestaNotificacionFacturaPaquete.class);
                      if (paquete != null) {
                          facturasProcesadas = paquete.getFacturas() == null ? 0 : paquete.getFacturas().size();
                          for (RespuestaNotificacionFactura factura : paquete.getFacturas()) {
                              if (factura.getCodigoResultado().equals(CODIGO_NOTAS_CREADAS_EXITOSAS_M3)) {
                                  int idFactura = Integer.parseInt(factura.getConsecutivo());
                                  CmSincronizacionEncabezado sincronizacionEncabezado = getCmSincronizacionEncabezadoRemoto().consultarEnacabezadoPorNitConcecutivo(factura.getNITProveedor(), Integer.valueOf(factura.getConsecutivo()));
                                  if (sincronizacionEncabezado != null) {
                                      
                                      CmRadicado radicado = getCmRadicadoRemoto().consultar(sincronizacionEncabezado.getCmRadicadosId().getId());
                                      listaRadicados.add(radicado);
                                      int nuevoEstadoFactura = obtenerNuevoEstadoFactura(radicado);
                                      if (cambiarEstadoFactura(nuevoEstadoFactura, idFactura)) {
                                          sincronizacionEncabezado.setEstado(CmSincronizacionEncabezado.ESTADO_FINALIZADO);
                                          getCmSincronizacionEncabezadoRemoto().actualizar(sincronizacionEncabezado);
                                          guardarHistorialEstadoFactura(nuevoEstadoFactura, idFactura, sincronizacionEncabezado);
                                          notificacionUtilidades.actualizarUsuarioPorMomento(sincronizacionEncabezado, nuevoEstadoFactura);
                                      }

                                  }
                                  facturasProcesdasExitosas++;
                              }
                              notificacionUtilidades.actualizarHistoricoTransaccionFacturaM3M4(factura, tipoTransaccion);
                          }
                      }
                      procesamietoCompleto = (facturasProcesadas == facturasProcesdasExitosas);
                      break;

                  case TIPO_PETICION_INTRASAVIA:
                      RespuestaSolicitudNotificacionFacturaIntrasavia respuestaIntrasavia
                              = gson.fromJson(respuesta, RespuestaSolicitudNotificacionFacturaIntrasavia.class);
                      if (respuestaIntrasavia != null && respuestaIntrasavia.getFacturas() != null && !respuestaIntrasavia.getFacturas().isEmpty()) {
                           facturasProcesadas = respuestaIntrasavia.getFacturas().size();
                           facturasProcesdasExitosas = 0;
                          for (RespuestaNotificacionFactura factura : respuestaIntrasavia.getFacturas()) {
                              if (factura.getCodigoResultado().equals(CODIGO_NOTAS_CREADAS_EXITOSAS_M3)) {
                                  CmSincronizacionEncabezado enca = getCmSincronizacionEncabezadoRemoto().consultarEnacabezadoNitNumeroDocumento(factura.getNITProveedor(), factura.getNumeroDocumento());
                                  if (enca != null) {

                                      CmRadicado radicado = getCmRadicadoRemoto().consultar(enca.getCmRadicadosId().getId());
                                      listaRadicados.add(radicado);
                                      int nuevoEstadoFactura = obtenerNuevoEstadoFactura(radicado);
                                      int idFactura = Integer.parseInt(factura.getConsecutivo());
                                      if (cambiarEstadoFactura(nuevoEstadoFactura, idFactura)) {
                                          enca.setEstado(CmSincronizacionEncabezado.ESTADO_FINALIZADO);
                                          getCmSincronizacionEncabezadoRemoto().actualizar(enca);
                                          guardarHistorialEstadoFactura(nuevoEstadoFactura, idFactura, enca);
                                          notificacionUtilidades.actualizarUsuarioPorMomento(enca, nuevoEstadoFactura);
                                      }
                                  }
                                  facturasProcesdasExitosas++;
                              }
                              notificacionUtilidades.actualizarHistoricoTransaccionFacturaM3M4(factura, tipoTransaccion);
                          }
                          procesamietoCompleto = (facturasProcesadas == facturasProcesdasExitosas);
                      }
                      break;
              }
          } catch (SQLException | ClassNotFoundException ex) {
              procesamietoCompleto = false;
          } catch (Exception ex) {
              procesamietoCompleto = false;
          }
          return procesamietoCompleto;
    }
    
     private boolean cambiarEstadoFactura( int estadoFactura , int  idFactura) {
        boolean estadoActualizado = false;
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            if (idFactura > 0) {
                paramConsulta.setParametroConsulta2(null);
                paramConsulta.setParametroConsulta1(idFactura);
                paramConsulta.setParametroConsulta3(estadoFactura);
                getCmFacturaRemoto().actualizarEstadoAuditoria(paramConsulta);
                estadoActualizado = true;
            }
        } catch (Exception ex) {
            estadoActualizado = false;
        }
        
        return estadoActualizado;
    }
    
    private void guardarHistorialEstadoFactura(int estadoFactura  , int idFactura, CmSincronizacionEncabezado encabezado) {
        try {            
            CmFacturaEstado facturaEstado = new CmFacturaEstado();
            facturaEstado.setCmFactura(new CmFactura(idFactura));
            facturaEstado.setEstadoFactura(estadoFactura);
            facturaEstado.setUsuarioCrea(encabezado.getUsuarioCrea());
            facturaEstado.setTerminalCrea(encabezado.getTerminalCrea());
            facturaEstado.setFechaHoraCrea(new Date());
            getCmFacturaEstadoRemoto().insertar(facturaEstado);
        } catch (Exception e) {
            Logger.getLogger("Error al guardarHistorialEstadoFactura envioSapMomento3 : " + NotificacionFacturaServicio.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private int obtenerNuevoEstadoFactura(CmRadicado radicado) {
        int estadoFactura =  CmFactura.ESTADO_FACTURA_GLOSADA;
        try {
            boolean esConciliacionMasiva = radicado.getCmConciliacion() != null
                    && radicado.getCmConciliacion().getId() != null;
            
            if (esConciliacionMasiva) {
                estadoFactura = CmFactura.ESTADO_FACTURA_CONCILIADA;
            }            
            if(!esConciliacionMasiva && radicado.getCmGlosaRespuesta() != null && 
               radicado.getCmGlosaRespuesta().getId() != null){
               CmGlosaRespuesta respuesta =  getCmGlosaRespuestaRemoto().consultar(radicado.getCmGlosaRespuesta().getId());
               estadoFactura = respuesta.getTipoRespuesta() == CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION ? 
                                                               CmFactura.ESTADO_FACTURA_CONCILIADA : estadoFactura;
            }        
        } catch (Exception e) {
            Logger.getLogger("Error al obtenerNuevoEstadoFactura envioSapMomento3 : " + NotificacionFacturaServicio.class.getName()).log(Level.SEVERE, null, e);
        }
        return estadoFactura;
    }
    
    private void registrarSincronizacionExcepcion(CmSincronizacionPaquete sincronizacionPaquete, int estadoTransaccion, String mensajeError) {
        try {
            mensajeError = mensajeError.length() > LONGITUD_MENSAJE_RESPUESTA ?
                           mensajeError.substring(0, LONGITUD_MENSAJE_RESPUESTA) : mensajeError;
            sincronizacionPaquete.setEstadoTransacion(estadoTransaccion);
            sincronizacionPaquete.setCodigoRespuesta(0);
            sincronizacionPaquete.setMensajeRespuesta(mensajeError);
            sincronizacionPaquete.setJsonRespuesta(mensajeError.getBytes());
            sincronizacionPaquete.setFechaHoraRespuesta(new Date());
            getCmSincronizacionPaqueteRemoto().actualizar(sincronizacionPaquete);
        } catch (Exception ex) {
            Logger.getLogger("Error al registrarSincronizacionExcepcion momento 3 : " + NotificacionFacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private CmSincronizacionPaquete guardarSincronizacionPaquete(int idSincronizacion, String jsonEnvioPaquete) {
        CmSincronizacionPaquete sincronizacionPaquete = new CmSincronizacionPaquete();
        try {
            sincronizacionPaquete.setCmSincronizacion(new CmSincronizacion(idSincronizacion));
            sincronizacionPaquete.setEstadoTransacion(CmSincronizacionPaquete.ESTADO_TRANSACCION_INICIADA);
            sincronizacionPaquete.setCodigoRespuesta(CmSincronizacion.CODIGO_RESPUESTA_ENVIO_SAP);
            sincronizacionPaquete.setJsonEnvio(jsonEnvioPaquete.getBytes());
            sincronizacionPaquete.setJsonRespuesta(TEXTO_INICIO_ENVIO_SAP.getBytes());
            sincronizacionPaquete.setFechaHoraEnvio(new Date());
            sincronizacionPaquete.setCodigoRetorno(0);
            sincronizacionPaquete.setId(getCmSincronizacionPaqueteRemoto().insertar(sincronizacionPaquete));
        } catch (Exception ex) {
        }
        return sincronizacionPaquete;
    }
    
    private NotificacionFacturaPaquete castNotificacionFacturaPaquete(int nut,  List<NotificacionFactura> facturas, String tipoTransaccion) {
        int cantidadRegistros = facturas == null ? 0 : facturas.size();
        NotificacionFacturaPaquete notificacionPaquete = new NotificacionFacturaPaquete();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        notificacionPaquete.setNut(String.valueOf(nut));
        notificacionPaquete.setCantidadRegistros(String.valueOf(cantidadRegistros));
        notificacionPaquete.setFechaHoraTrasaccion(dateFormat.format(new Date()));
        notificacionPaquete.setTipoTrasaccion(tipoTransaccion);
        notificacionPaquete.setFacturas(facturas);
        return notificacionPaquete;
    }
    
     private NotificacionFacturaPaqueteM1 castNotificacionFacturaPaqueteM1(int nut,  List<CmNotificacionFacturaDTO> facturas, String tipoTransaccion) {
        int cantidadRegistros = facturas == null ? 0 : facturas.size();
        NotificacionFacturaPaqueteM1 notificacionPaquete = new NotificacionFacturaPaqueteM1();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        notificacionPaquete.setNut(String.valueOf(nut));
        notificacionPaquete.setCantidadRegistros(String.valueOf(cantidadRegistros));
        notificacionPaquete.setFechaHoraTrasaccion(dateFormat.format(new Date()));
        notificacionPaquete.setTipoTrasaccion(tipoTransaccion);
        notificacionPaquete.setFacturas(facturas);
        return notificacionPaquete;
    }
     
    private NotificacionFacturaPaqueteM2 castNotificacionFacturaPaqueteM2(int nut,  List<CierreAuditoriaFactura> facturas, String tipoTransaccion) {
        int cantidadRegistros = facturas == null ? 0 : facturas.size();
        NotificacionFacturaPaqueteM2 notificacionPaquete = new NotificacionFacturaPaqueteM2();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        notificacionPaquete.setNut(String.valueOf(nut));
        notificacionPaquete.setCantidadRegistros(String.valueOf(cantidadRegistros));
        notificacionPaquete.setFechaHoraTrasaccion(dateFormat.format(new Date()));
        notificacionPaquete.setTipoTrasaccion(tipoTransaccion);
        notificacionPaquete.setFacturas(facturas);
        return notificacionPaquete;
    }

    private String crearPeticionEnvioM3M4( List<NotificacionFactura> notificacionesDto, int tipoEnvio, int idSincronizaconPaquete, String tipoTransaccion) {
       
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        String jsonEnvioPaquete = "";
        tipoEnvio = tipoEnvio == 0  ? TIPO_PETICION_TRADICIONAL : tipoEnvio;
        
        switch (tipoEnvio) {

            case TIPO_PETICION_INTRASAVIA:
                final String CODIGO_NUT = "1";
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                NotificacionFacturaIntrasavia notificacionIntrasavia = new NotificacionFacturaIntrasavia();
                notificacionIntrasavia.setNUT(CODIGO_NUT);
                notificacionIntrasavia.setFechaTransaccion(dateFormat.format(new Date()));
                notificacionIntrasavia.setFacturas(notificacionesDto);
                jsonEnvioPaquete = gson.toJson(notificacionIntrasavia);
                break;
            case TIPO_PETICION_TRADICIONAL:
                jsonEnvioPaquete = gson.toJson(notificacionesDto);
                break;
            case TIPO_PETICION_PAQUETE:
                jsonEnvioPaquete = gson.toJson(castNotificacionFacturaPaquete(idSincronizaconPaquete, notificacionesDto, tipoTransaccion));
                break;
        }

        return jsonEnvioPaquete;
    }
  
    private String crearPeticionEnvioM1(List<CmNotificacionFacturaDTO> notificacionesDto, int tipoEnvio, int idfactura, String tipoTransaccion) {
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        String jsonEnvioPaquete = "";
        tipoEnvio = (tipoEnvio == 0) ? TIPO_PETICION_TRADICIONAL : tipoEnvio;
        switch (tipoEnvio) {
            case TIPO_PETICION_TRADICIONAL:
                jsonEnvioPaquete = gson.toJson(notificacionesDto);
                break;
            case TIPO_PETICION_PAQUETE:
                jsonEnvioPaquete = gson.toJson(castNotificacionFacturaPaqueteM1(idfactura, notificacionesDto, tipoTransaccion));
                break;
        }
        return jsonEnvioPaquete;
    }
    
    private String crearPeticionEnvioM2( List<CierreAuditoriaFactura> notificacionesDto, int tipoEnvio, int idAuditoriaCierre, String tipoTransaccion) {
        String jsonEnvioPaquete = "";
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        tipoEnvio = tipoEnvio == 0  ? TIPO_PETICION_TRADICIONAL : tipoEnvio;
        switch (tipoEnvio) {
            case TIPO_PETICION_TRADICIONAL:
                jsonEnvioPaquete = gson.toJson(notificacionesDto);
                break;
            case TIPO_PETICION_PAQUETE:
                jsonEnvioPaquete = gson.toJson(castNotificacionFacturaPaqueteM2(idAuditoriaCierre, notificacionesDto, tipoTransaccion));
                break;
        }
        return jsonEnvioPaquete;
    }
    
    private String obtenerTipoTransaccion( int tipoEnvioRespuesta, String tipoTransaccionOperacion ){
        String tipoTransaccion = "";
        switch(tipoEnvioRespuesta){
            case TIPO_PETICION_PAQUETE:
                tipoTransaccion = tipoTransaccionOperacion;
                break;
            case TIPO_PETICION_TRADICIONAL:
            case TIPO_PETICION_INTRASAVIA:
                tipoTransaccion = TIPO_TRANSACCION_MOMENTO_3;
                break;
        }
        
        return tipoTransaccion;
    
    }
    
    private int asignarTipoMomento(String tipoTransaccion) {
        int momento ;
        try {
            tipoTransaccion = tipoTransaccion == null || "".equals(tipoTransaccion) ? "-1" : tipoTransaccion;
            momento = Integer.parseInt(tipoTransaccion);
        } catch (NumberFormatException e) {
            momento = -1;
        }
        return momento;
    }
    
}
