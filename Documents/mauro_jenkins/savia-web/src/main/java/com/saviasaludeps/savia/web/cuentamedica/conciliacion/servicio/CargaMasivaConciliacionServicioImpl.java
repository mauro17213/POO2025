/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuestaDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionPaquete;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacionRespuestaGlosaModulo;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.Reporte;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccion;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccionDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
//import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmConciliacionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionEncabezadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionPaqueteRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionRemoto;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CargaMasivaConciliacionBean;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.utilidades.EjecucionRegistroTablasSincronizacionHilo;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.ConciliacionGlosadoGenericoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmRadicadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmFacturaDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmTransaccionDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmTransaccionRemoto;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaNotificacionFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaNotificacionFacturaPaquete;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Admin
 */
public class CargaMasivaConciliacionServicioImpl extends AccionesBO implements CargaMasivaConciliacionServicioIface {

    public static final int TIPO_CONCILIACION_MASIVA = 2;
    public static final int CANTIDAD_MAXIMA_FACTURAS_PROCESAR = 10000;
    public static final int TIPO_AJUSTE_EPS = 1;
    public static final int TIPO_AJUSTE_IPS = 2;
    public static final int TIPO_AJUSTE_EPS_MENOR = 3;
    public static final int TIPO_AJUSTE_IPS_MENOR = 4;
    
    public final static String CODIGO_RESPUESTA_NOTAS_CREADAS_EXITOSAS = "13";
    public final static String CODIGO_RESPUESTA_NOTA_CREADA_EXITOSA    = "2";

    private Map<String, CmFactura> hashFacturasDeArchivo;
    private List<String> listaNumerosFacturadosACargar;
    private List<String> listaLLaveRadicadoNumeroFacturaACargar;
    private Map<Integer, BigDecimal> mapAjuste;
    
    public Map<Integer, BigDecimal> getMapAjuste() {
        if (mapAjuste == null) {
            mapAjuste = new HashMap<>();
        }
        return mapAjuste;
    }

    public void setMapAjuste(Map<Integer, BigDecimal> mapAjuste) {
        this.mapAjuste = mapAjuste;
    }

    public List<String> getListaNumerosFacturadosACargar() {
        if (listaNumerosFacturadosACargar == null) {
            listaNumerosFacturadosACargar = new ArrayList<>();
        }
        return listaNumerosFacturadosACargar;
    }

    public void setListaNumerosFacturadosACargar(List<String> listaNumerosFacturadosACargar) {
        this.listaNumerosFacturadosACargar = listaNumerosFacturadosACargar;
    }

    public List<String> getListaLLaveRadicadoNumeroFacturaACargar() {
        if (listaLLaveRadicadoNumeroFacturaACargar == null) {
            listaLLaveRadicadoNumeroFacturaACargar = new ArrayList<>();
        }
        return listaLLaveRadicadoNumeroFacturaACargar;
    }

    public void setListaLLaveRadicadoNumeroFacturaACargar(List<String> listaLLaveRadicadoNumeroFacturaACargar) {
        this.listaLLaveRadicadoNumeroFacturaACargar = listaLLaveRadicadoNumeroFacturaACargar;
    }

    public Map<String, CmFactura> getHashFacturasDeArchivo() {
        if (hashFacturasDeArchivo == null) {
            hashFacturasDeArchivo = new HashMap<>();
        }
        return hashFacturasDeArchivo;
    }

    public void setHashFacturasDeArchivo(Map<String, CmFactura> hashFacturasDeArchivo) {
        this.hashFacturasDeArchivo = hashFacturasDeArchivo;
    }

    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }

    private CmConciliacionRemoto getCmConciliacionRemoto() throws Exception {
        return (CmConciliacionRemoto) RemotoEJB.getEJBRemoto("CmConciliacionServicio", CmConciliacionRemoto.class.getName());
    }

    private CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
    }

    private CmGlosaRespuestaRemoto getCmGlosaRespuestaRemoto() throws Exception {
        return (CmGlosaRespuestaRemoto) RemotoEJB.getEJBRemoto("CmGlosaRespuestaServicio", CmGlosaRespuestaRemoto.class.getName());
    }

    private CmSincronizacionRemoto getCmSincronizacionRemoto() throws Exception {
        return (CmSincronizacionRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionServicio", CmSincronizacionRemoto.class.getName());
    }

    private CmSincronizacionPaqueteRemoto getCmSincronizacionPaqueteRemoto() throws Exception {
        return (CmSincronizacionPaqueteRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionPaqueteServicio", CmSincronizacionPaqueteRemoto.class.getName());
    }

    private CmSincronizacionEncabezadoRemoto getCmSincronizacionEncabezadoRemoto() throws Exception {
        return (CmSincronizacionEncabezadoRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionEncabezadoServicio", CmSincronizacionEncabezadoRemoto.class.getName());
    }

    private CmSincronizacionDetalleRemoto getCmSincronizacionDetalleRemoto() throws Exception {
        return (CmSincronizacionDetalleRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionDetalleServicio", CmSincronizacionDetalleRemoto.class.getName());
    }

    private ConciliacionGlosadoGenericoRemoto getConciliacionGlosadoGenericoRemoto() throws Exception {
        return (ConciliacionGlosadoGenericoRemoto) RemotoEJB.getEJBRemotoGenerico("ConciliacionGlosadoGenericoServicio", ConciliacionGlosadoGenericoRemoto.class.getName());
    }
    
    private CmRadicadoRemoto getCmRadicadoServicioRemoto() throws Exception {
        return (CmRadicadoRemoto) RemotoEJB.getEJBRemoto(("CmRadicadoServicio"), CmRadicadoRemoto.class.getName());
    }
    
    private WsCmTransaccionRemoto getWsCmTransaccionRemoto() throws Exception {
        return (WsCmTransaccionRemoto) RemotoEJB.getEJBRemoto("WsCmTransaccionServicio", WsCmTransaccionRemoto.class.getName());
    }
    
    private WsCmTransaccionDetalleRemoto getWsCmTransaccionDetalleRemoto() throws Exception {
        return (WsCmTransaccionDetalleRemoto) RemotoEJB.getEJBRemoto("WsCmTransaccionDetalleServicio", WsCmTransaccionDetalleRemoto.class.getName());
    }
    
    private WsCmFacturaRemoto getWsCmFacturaRemoto() throws Exception {
        return (WsCmFacturaRemoto) RemotoEJB.getEJBRemoto("WsCmFacturaServicio", WsCmFacturaRemoto.class.getName());
    }
    
    private WsCmFacturaDetalleRemoto getWsCmFacturaDetalleRemoto() throws Exception {
        return (WsCmFacturaDetalleRemoto) RemotoEJB.getEJBRemoto("WsCmFacturaDetalleServicio", WsCmFacturaDetalleRemoto.class.getName());
    }

    @Override
    public void Accion(CargaMasivaConciliacionBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case CargaMasivaConciliacionBean.ACCION_BUSCAR_IPS:
                            buscarIps(bean);
                            break;
                        case CargaMasivaConciliacionBean.ACCION_CARGAR_CONCILIACIONES:
                            cargarConciliaciones(bean);
                            break;
                        case CargaMasivaConciliacionBean.ACCION_BLOQUEAR_FACTURA:
                            actualizarBloqueoFacturas(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                        guardarConciliacionMasiva(bean); 
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    reporteConciliacion(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    registrarEjecucionSincronizacion(bean);
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case CargaMasivaConciliacionBean.ACCION_VER_ENCABEZADOS:
                            verEncabezados(bean);
                            verResumenEncabezados(bean);
                            break;
                        case CargaMasivaConciliacionBean.ACCION_VER_CUENTAS_CONTABLES:
                            verCuentasContables(bean);
                            break;
                         case CargaMasivaConciliacionBean.ACCION_VER_WS_CM_FACTURAS:
                            verWsCmFacturas(bean);
                            break;
                        case CargaMasivaConciliacionBean.ACCION_VER_CM_FACTURAS_DETALLES:
                            verWsCmFacturaDetalle(bean);
                            break;
                        case CargaMasivaConciliacionBean.ACCION_VER_TIPO_FUENTE_DATOS:
                            verTipoFuenteDatos(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    reporteErroresConciliacion(bean);
                    break;

                case Url.ACCION_ADICIONAL_5:
                    verAdjuntos(bean);
                    break;
            }
            cargas(bean);
        }
    }

    private void agregarMensajeErrorGuardadoConciliacion(CargaMasivaConciliacionBean bean) {
        String mensajeError = bean.isError()? bean.getErrores().get(0):"";
        bean.limpiarMensajes();
        bean.addError("No se puede guardar la conciliación masiva: "+mensajeError);
    }

    private void buscarIps(CargaMasivaConciliacionBean bean) {
        try {
            bean.getParamConsulta().setParametroConsulta1(bean.getObjeto().getNit());
            bean.getParamConsulta().setParametroConsulta2(bean.getObjeto().getRazonSocial());
            bean.setListaIps(getCmFacturaRemoto().consultarIps(bean.getParamConsulta()));
            bean.getParamConsulta().setParametroConsulta1(null);
            bean.getParamConsulta().setParametroConsulta2(null);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listar(CargaMasivaConciliacionBean bean) {
        try {
            int PARAMETRO_CONSULTA_ESTADOS_FACTURA = 1;
            bean.getParamConsultaListaConciliaciones().setParametroConsulta3(PARAMETRO_CONSULTA_ESTADOS_FACTURA);
            bean.getParamConsultaListaConciliaciones().setCantidadRegistros(getCmConciliacionRemoto().consultarCantidadLista(bean.getParamConsultaListaConciliaciones()));
            bean.setRegistros(getCmConciliacionRemoto().consultarLista(bean.getParamConsultaListaConciliaciones()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void actualizarBloqueoFacturas(CargaMasivaConciliacionBean bean) {
        try {
            getCmFacturaRemoto().actualizarEstadoGestion(bean.getParamConsultaUtilitario());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(CargaMasivaConciliacionBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private boolean validarDatosArchivoConciliar(CargaMasivaConciliacionBean bean) {

        boolean esArchivoValido = true;
        try {
            int origenFacturaUnificado = 0;
            String numeroFacturadoInicial = "";
            String numerosFacturadosAverificar = String.join(",", getListaNumerosFacturadosACargar());
            bean.getParamConsulta().setParametroConsulta1(numerosFacturadosAverificar);
            Map<String, CmFactura> mapFacturasEnSistema = getCmFacturaRemoto().hashCosultarPorNumeroFacturado(bean.getParamConsulta());
            StringBuilder builderMensajeError = new StringBuilder();
            List<CmFactura> facturasAconciliar = bean.getFacturasEncontradas();
                     
            for (String llaveRadicadoNumeroFacturado : getListaLLaveRadicadoNumeroFacturaACargar()) {
                StringBuilder builderErrorPorFactura = new StringBuilder();
                boolean existeFacturaEnBD = true;
                boolean facturaConciliada = false;
                CmFactura facturaEncontradaEnBD = mapFacturasEnSistema.get(llaveRadicadoNumeroFacturado);
                CmFactura facturaEncontradaEnArchivo = getHashFacturasDeArchivo().get(llaveRadicadoNumeroFacturado) == null
                        ? new CmFactura() : getHashFacturasDeArchivo().get(llaveRadicadoNumeroFacturado);

                if (facturaEncontradaEnBD == null) {
                    existeFacturaEnBD = false;
                    builderErrorPorFactura.append(" - La factura no existen en el sistema ");
                }

                if (existeFacturaEnBD && CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION == facturaEncontradaEnBD.getTipoRespuesta()) {
                    builderErrorPorFactura.append(" - La factura ya fue conciliada ");
                    facturaConciliada = true;
                }

                if (existeFacturaEnBD && facturaEncontradaEnBD.getEstadoFactura() > CmFactura.ESTADO_FACTURA_GLOSADA) {
                    builderErrorPorFactura.append(" - La factura se encuentra en un estado ");
                    builderErrorPorFactura.append(facturaEncontradaEnBD.getEstadoFacturaStr());
                    builderErrorPorFactura.append(", el cual no es valido para realizar proceso de conciliacion");
                    facturaConciliada = true;
                }

                String nitEncontradoFacturaEnBD = !existeFacturaEnBD ? "" : facturaEncontradaEnBD.getNit().trim();
                String ipsRequerido = bean.getObjeto().getIps() == null ? "" : bean.getObjeto().getIps().trim();

                if (existeFacturaEnBD && (!ipsRequerido.equals(nitEncontradoFacturaEnBD)
                        || !facturaEncontradaEnArchivo.getNit().equals(nitEncontradoFacturaEnBD))) {
                    builderErrorPorFactura.append(" - La factura esta asociada a un nit diferente al seleccionado inicialmente, ");
                    builderErrorPorFactura.append("nit asociado a carga archivo : ");
                    builderErrorPorFactura.append(ipsRequerido);
                    builderErrorPorFactura.append(", nit asociado a factura : ");
                    builderErrorPorFactura.append(facturaEncontradaEnArchivo.getNit());
                    builderErrorPorFactura.append(" , nit encontrado en sistema para la factura : ");
                    builderErrorPorFactura.append(nitEncontradoFacturaEnBD);
                }

                Integer idUsuarioSolicita = bean.getConexion().getUsuario().getId();

                Integer idUsuarioGestionaFactura = null;
                if (existeFacturaEnBD && !facturaConciliada) {
                    idUsuarioGestionaFactura = facturaEncontradaEnBD.getUsuarioGestiona().getId() != null
                            ? facturaEncontradaEnBD.getUsuarioGestiona().getId() : null;
                }

                if (existeFacturaEnBD && !facturaConciliada && idUsuarioGestionaFactura != null
                        && idUsuarioGestionaFactura != idUsuarioSolicita) {
                    builderErrorPorFactura.append(" - La factura esta bloqueada o siendo usada actualemente por el usuario : ");
                    if (facturaEncontradaEnBD.getUsuarioGestiona() != null) {
                        builderErrorPorFactura.append(facturaEncontradaEnBD.getUsuarioGestiona().getNombre());
                        builderErrorPorFactura.append("(");
                        builderErrorPorFactura.append(facturaEncontradaEnBD.getUsuarioGestiona().getCorreoElectronico());
                        builderErrorPorFactura.append(")");
                    }
                }

                int tipoMarcacion = existeFacturaEnBD ? facturaEncontradaEnBD.getMarcacion() : 0;
                if (existeFacturaEnBD && !facturaConciliada && CmFactura.TIPO_BLOQUEO_GLOSA == tipoMarcacion) {
                    builderErrorPorFactura.append(" - La Factura esta marcada en estos momentos ");
                }
                
                
                if(origenFacturaUnificado==0 && existeFacturaEnBD){
                   origenFacturaUnificado = facturaEncontradaEnBD.getOrigenFactura();
                   numeroFacturadoInicial= facturaEncontradaEnBD.getNumeroFacturado();
                }
            
                if (facturaEncontradaEnBD != null && facturaEncontradaEnBD.getOrigenFactura() != origenFacturaUnificado) {
                    builderErrorPorFactura.append("- Las facturas deben tener el mismo origen de factura para ser procesadas, ");
                    builderErrorPorFactura.append("origen encontrado:( " );
                    builderErrorPorFactura.append(CmFactura.getOrigenFacturaStr(origenFacturaUnificado));
                    builderErrorPorFactura.append("  número factura:" );
                    builderErrorPorFactura.append(numeroFacturadoInicial );
                    builderErrorPorFactura.append(") ");
                    builderErrorPorFactura.append(", origen diferente: (" );
                    builderErrorPorFactura.append(CmFactura.getOrigenFacturaStr(facturaEncontradaEnBD.getOrigenFactura()));
                    builderErrorPorFactura.append("  número factura:" );
                    builderErrorPorFactura.append( facturaEncontradaEnBD.getNumeroFacturado());
                    builderErrorPorFactura.append(")");
                }

                if (existeFacturaEnBD && !facturaConciliada) {
                    String numeroFacturado = facturaEncontradaEnArchivo.getNumeroFacturado();
                    if (!numeroFacturado.equals(facturaEncontradaEnBD.getNumeroFacturado())) {
                        builderErrorPorFactura.append(" - El número facturado enviado en el archivo es diferente al encontrado en el sistema, ");
                        builderErrorPorFactura.append(" número facturado enviado en el archivo :  ");
                        builderErrorPorFactura.append(numeroFacturado);
                        builderErrorPorFactura.append(" , número facturado encontrado en sistema :  ");
                        builderErrorPorFactura.append(facturaEncontradaEnBD.getNumeroFacturado());
                    }
                }

                if (existeFacturaEnBD && !facturaConciliada) {
                    BigDecimal valorPendienteActual = facturaEncontradaEnArchivo.getValorPendienteActual();
                    BigDecimal valorPendienteActualFactuarEncontrada = facturaEncontradaEnBD.getValorPendienteActual() == null
                            ? new BigDecimal(BigInteger.ZERO) : facturaEncontradaEnBD.getValorPendienteActual();
                    if (valorPendienteActual.compareTo(valorPendienteActualFactuarEncontrada) != 0) {
                        builderErrorPorFactura.append(" - El valor pendiente actual es diferente al encontrado en el sistema, ");
                        builderErrorPorFactura.append(" valor encontrado en sistema :  ");
                        builderErrorPorFactura.append(valorPendienteActualFactuarEncontrada);
                        builderErrorPorFactura.append(" ,valor a conciliar : ");
                        builderErrorPorFactura.append(valorPendienteActual);
                    }
                }
                
                if ( facturaEncontradaEnBD != null &&  facturaEncontradaEnBD.isFacturaCargaElectronica() ) {
                    builderErrorPorFactura.append(" - La factura de número factura ( ");
                    builderErrorPorFactura.append(facturaEncontradaEnBD.getNumeroFacturado());
                    builderErrorPorFactura.append(" ) pertenece a carga de facturación electrónica, no puede ser conciliada en este proceso.");
                }

                if (builderErrorPorFactura.length() <= 0) {
                    BigDecimal valorEPS = facturaEncontradaEnArchivo != null
                            ? facturaEncontradaEnArchivo.getValorPagadoEPS()
                            : new BigDecimal("0");
                    BigDecimal valorIPS = facturaEncontradaEnArchivo != null
                            ? facturaEncontradaEnArchivo.getValorAceptadoIPS() : new BigDecimal("0");
                    facturaEncontradaEnBD.setValorPagadoEPS(valorEPS);
                    facturaEncontradaEnBD.setValorAceptadoIPS(valorIPS);
                    facturasAconciliar.add(facturaEncontradaEnBD);
                } else {
                    esArchivoValido = false;
                    StringBuilder builderMensajeErrorEncabezado = new StringBuilder();
                    builderMensajeErrorEncabezado.append("Se presentan errores en la linea : ");
                    builderMensajeErrorEncabezado.append(facturaEncontradaEnArchivo.getNumeroLineaArchivo());
                    builderMensajeErrorEncabezado.append(" en la factura de radicado - numero facturado : ");
                    builderMensajeErrorEncabezado.append(llaveRadicadoNumeroFacturado);
                    builderMensajeErrorEncabezado.append(" ( ");
                    builderMensajeErrorEncabezado.append(builderErrorPorFactura);
                    builderMensajeErrorEncabezado.append(" ) ");
                    builderMensajeErrorEncabezado.append("\r\n");
                    builderMensajeError.append(builderMensajeErrorEncabezado);
                }
            }

            if (builderMensajeError.length() > 0) {
                bean.setMensajeErrorFormatoConciliacion(builderMensajeError);
            }
            bean.getParamConsulta().setParametroConsulta1(null);
        } catch (Exception e) {
            bean.addError("Error en función validarDatosArchivoConciliar " + e.toString());
            esArchivoValido = false;
        }

        return esArchivoValido;
    }

    private boolean validarEstructuraArchivoConciliar(CargaMasivaConciliacionBean bean) {
        final int INDICE_COLUMNA_RADICADO = 0;
        final int INDICE_COLUMNA_NUMERO_FACTURADO = 1;
        final int INDICE_COLUMNA_NIT = 2;
        final int INDICE_COLUMNA_PENDIENTE_ACTUAL = 3;
        final int INDICE_COLUMNA_VALOR_EPS = 4;
        final int INDICE_COLUMNA_VALOR_IPS = 5;
        boolean esArchivoValido = true;
        BufferedReader br = null;
        InputStream is;
        int numeroLinea = 1;
        String[] camposArchivo;
        try {
            is = bean.getObjeto().getAdjuntoStream();
            br = new BufferedReader(new InputStreamReader(is));
            String linea;
            StringBuilder builderMensajeError = new StringBuilder();
            getListaLLaveRadicadoNumeroFacturaACargar().clear();
            getListaNumerosFacturadosACargar().clear();
            getHashFacturasDeArchivo().clear();

            while ((linea = br.readLine()) != null) {
                if (!"".equals(linea)) {
                    CmFactura factura = new CmFactura();
                    StringBuilder builderErrorPorFactura = new StringBuilder();
                    camposArchivo = linea.split(";");
                    if (CargaMasivaConciliacionBean.NUMERO_CAMPOS_POR_FILA_CONCILIACION != camposArchivo.length) {
                        builderErrorPorFactura.append(" - La cantidad de columnas o datos no es igual a ");
                        builderErrorPorFactura.append(CargaMasivaConciliacionBean.NUMERO_CAMPOS_POR_FILA_CONCILIACION);
                        builderErrorPorFactura.append(" , además todos las columnas deben tener un valor");
                    } else {
                        int radicado;
                        BigDecimal valorPagadoIPS;
                        BigDecimal valorPagadoEPS;
                        BigDecimal pendienteActual;
                        BigDecimal valorEpsSumaIps = new BigDecimal("0");
                        BigDecimal valorPagadoIpsTemp = new BigDecimal("0");
                        BigDecimal valorPagadoEpsTemp = new BigDecimal("0");
                        BigDecimal pendienteActualTemp = new BigDecimal("0");

                        try {
                            radicado = Integer.parseInt(camposArchivo[INDICE_COLUMNA_RADICADO]);
                            factura.setNumeroRadicado(radicado);
                        } catch (NumberFormatException e) {
                            builderErrorPorFactura.append(" - Se presenta un error de datos, el valor radicado ");
                            builderErrorPorFactura.append(camposArchivo[INDICE_COLUMNA_RADICADO]);
                            builderErrorPorFactura.append(" debe ser un número mayor o igual a cero.");
                        }

                        String numeroFacturado = camposArchivo[INDICE_COLUMNA_NUMERO_FACTURADO];
                        factura.setNumeroFacturado(numeroFacturado);

                        String nit = camposArchivo[INDICE_COLUMNA_NIT];
                        factura.setNit(nit);
                        boolean esVerificableSuma = true;

                        try {
                            pendienteActual = new BigDecimal(camposArchivo[INDICE_COLUMNA_PENDIENTE_ACTUAL]);
                            factura.setValorPendienteActual(pendienteActual);
                            pendienteActualTemp = pendienteActualTemp.add(pendienteActual);
                        } catch (NumberFormatException e) {
                            builderErrorPorFactura.append(" - Se presenta un error de datos, el valor pendiente ");
                            builderErrorPorFactura.append(camposArchivo[INDICE_COLUMNA_PENDIENTE_ACTUAL]);
                            builderErrorPorFactura.append(" debe ser un número mayor o igual a cero, los decimales deben separarse con punto(.), sin espacios al principio o final");
                            esVerificableSuma = false;
                        }

                        try {
                            valorPagadoEPS = new BigDecimal(camposArchivo[INDICE_COLUMNA_VALOR_EPS]);
                            factura.setValorPagadoEPS(valorPagadoEPS);
                            valorPagadoEpsTemp = valorPagadoEpsTemp.add(valorPagadoEPS);
                        } catch (NumberFormatException e) {
                            builderErrorPorFactura.append(" - Se presenta un error de datos, el valor EPS ");
                            builderErrorPorFactura.append(camposArchivo[INDICE_COLUMNA_VALOR_EPS]);
                            builderErrorPorFactura.append(" debe ser un número mayor o igual a cero, los decimales deben separarse con punto(.), sin espacios al principio o final");
                            esVerificableSuma = false;
                        }

                        try {
                            valorPagadoIPS = new BigDecimal(camposArchivo[INDICE_COLUMNA_VALOR_IPS]);
                            factura.setValorAceptadoIPS(valorPagadoIPS);
                            valorPagadoIpsTemp = valorPagadoIpsTemp.add(valorPagadoIPS);
                        } catch (NumberFormatException e) {
                            builderErrorPorFactura.append(" - Se presenta un error de datos, el valor IPS ");
                            builderErrorPorFactura.append(camposArchivo[INDICE_COLUMNA_VALOR_IPS]);
                            builderErrorPorFactura.append(" debe ser un número mayor o igual a cero, los decimales deben separarse con punto(.), sin espacios al principio o final");
                            esVerificableSuma = false;
                        }

                        if (esVerificableSuma) {
                            valorEpsSumaIps = valorEpsSumaIps.add(valorPagadoEpsTemp).
                                    add(valorPagadoIpsTemp);
                            if (valorEpsSumaIps.compareTo(pendienteActualTemp) > 0) {
                                builderErrorPorFactura.append(" - Se presenta un error de datos, la suma de EPS e IPS supera el valor pendiente ");
                                builderErrorPorFactura.append(pendienteActualTemp);
                                builderErrorPorFactura.append(" por favor verifique.");
                            }
                        }

                    }

                    if (builderErrorPorFactura.length() > 0) {
                        StringBuilder builderMensajeErrorEncabezado = new StringBuilder();
                        builderMensajeErrorEncabezado.append("Se presentan errores en la línea : ");
                        builderMensajeErrorEncabezado.append(numeroLinea);
                        if (factura.getNumeroRadicado() > 0) {
                            builderMensajeErrorEncabezado.append(" en la factura de radicado : ");
                            builderMensajeErrorEncabezado.append(factura.getNumeroRadicado());
                        }
                        builderMensajeErrorEncabezado.append(" ( ");
                        builderMensajeErrorEncabezado.append(builderErrorPorFactura);
                        builderMensajeErrorEncabezado.append(" ) ");
                        builderMensajeErrorEncabezado.append("\r\n");
                        builderMensajeError.append(builderMensajeErrorEncabezado);
                    }

                    String numeroFactuado = "'" + String.valueOf(factura.getNumeroFacturado()) + "'";
                    getListaNumerosFacturadosACargar().add(numeroFactuado);
                    String llave = factura.getNumeroRadicado() + "-" + factura.getNumeroFacturado();
                    getListaLLaveRadicadoNumeroFacturaACargar().add(llave);
                    getHashFacturasDeArchivo().put(llave, factura);
                    factura.setNumeroLineaArchivo(numeroLinea);
                }
                numeroLinea++;
                if(CANTIDAD_MAXIMA_FACTURAS_PROCESAR < numeroLinea){
                    builderMensajeError.append("Se ha superado la cantidad máxima de facturas a procesar : " );
                    builderMensajeError.append(CANTIDAD_MAXIMA_FACTURAS_PROCESAR);                   
                    break;
                }
                
            }

            if (builderMensajeError.length() > 0) {
                esArchivoValido = false;
                bean.setMensajeErrorFormatoConciliacion(builderMensajeError);
            }

            if (getHashFacturasDeArchivo().size() <= 0) {
                builderMensajeError.append("El archivo ");
                builderMensajeError.append(bean.getObjeto().getNombreArchivo());
                builderMensajeError.append(" no presenta datos o contenido que pueda ser conciliable.");
                esArchivoValido = false;
                bean.setMensajeErrorFormatoConciliacion(builderMensajeError);
            }

        } catch (IOException ioe) {
            bean.addError("Error en función validarEstructuraArchivoConciliar " + ioe.getMessage());
            esArchivoValido = false;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
                bean.addError("Error en función validarEstructuraArchivoConciliar " + ioe.getMessage());
                esArchivoValido = false;
            }
        }
        return esArchivoValido;
    }

    private void cargarConciliaciones(CargaMasivaConciliacionBean bean) {
        try {
            if (validarEstructuraArchivoConciliar(bean)) {
                if (validarDatosArchivoConciliar(bean)) {
                    bean.addMensaje("Se ha realizado el cargue de facturas conciliación de manera exitosa, por favor espere un momento.");
                } else {
                    String error = bean.getErrores().size() > 0 ? bean.getErrores().get(0) : "";
                    bean.getErrores().clear();
                    bean.addError("Se han presentado errores en el archivo conciliación masiva." + error);
                }
            } else {
                String error = bean.getErrores().size() > 0 ? bean.getErrores().get(0) : "";
                bean.getErrores().clear();
                bean.addError("Se han presentado errores en el archivo conciliación masiva." + error);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarConciliacionMasiva(CargaMasivaConciliacionBean bean) {
        try {
            if (verificarBloqueoFacturas(bean)) {

                CmConciliacion cmConciliacion = new CmConciliacion();
                //calculo valor ips y eps para tabla conciliacion
                calcularValoresConciliacion(bean.getCmConciliacion(), cmConciliacion);
                cmConciliacion.setValorPagadoEps(bean.getCmConciliacion().getValorPagadoEps());
                cmConciliacion.setValorAceptadoIps(bean.getCmConciliacion().getValorAceptadoIps());
                //Tomamos el primer prestador y asignamos a conciliacion
                int idPrestador = (bean.getFacturasEncontradas() != null
                        && !bean.getFacturasEncontradas().isEmpty())
                        ?  Optional.ofNullable(bean.getFacturasEncontradas().get(0).getIdCntPrestador()).orElse(0) : 0;
                cmConciliacion.setCntPrestadores(new CntPrestador(idPrestador));
                bean.auditoriaGuardar(cmConciliacion);

                String observacionConciliacion = bean.getCmConciliacion().getObservacion() == null
                        || bean.getCmConciliacion().getObservacion().equals("") ? " "
                        : bean.getCmConciliacion().getObservacion();
                cmConciliacion.setObservacion(observacionConciliacion);

                int totalFacturasPorProcesar = bean.getFacturasEncontradas() != null
                        ? bean.getFacturasEncontradas().size() : 0;
                cmConciliacion.setCantidadFacturas(totalFacturasPorProcesar);
                cmConciliacion.setEstadoProceso(CmConciliacion.ESTADO_EN_PROCESO);

                int idConciliacion = getCmConciliacionRemoto().insertar(cmConciliacion);
                
                ReporteConciliacion reporteConciliacion = new ReporteConciliacion();
                reporteConciliacion.setId(idConciliacion);
                bean.setReporteConciliacion(reporteConciliacion);

                if (guardarArchivoCargaMasiva(bean)) {

                    CargaMasivaConciliacionBean copiaBean = (CargaMasivaConciliacionBean) bean.clone();
                    List<CmFactura> facuras = new ArrayList<>(bean.getFacturasEncontradas().size());
                    for (CmFactura facturasEncontrada : bean.getFacturasEncontradas()) {
                        facuras.add((CmFactura) facturasEncontrada.clone());
                    }
                    copiaBean.setFacturasEncontradas(facuras);
                    copiaBean.setCmConciliacion((CmConciliacion) bean.getCmConciliacion().clone());

                    CmConciliacionRespuestaGlosaModulo conciliacionModuloGenerico = new CmConciliacionRespuestaGlosaModulo();
                    bean.auditoriaGuardar(conciliacionModuloGenerico);
                    conciliacionModuloGenerico.setIdConciliacionMasiva(idConciliacion);
                    conciliacionModuloGenerico.setIdUsuarioSolicitanteProceso(bean.getConexion().getUsuario().getId());
                    conciliacionModuloGenerico.setCmConciliacion(copiaBean.getCmConciliacion());
                    conciliacionModuloGenerico.setFacturasEncontradas(copiaBean.getFacturasEncontradas());
                    conciliacionModuloGenerico.setTipoSincronizacion(CmConciliacionRespuestaGlosaModulo.TIPO_SINCRONIZACION_CONCILIACION_MASIVA);
                    getConciliacionGlosadoGenericoRemoto().ejecutarConciliacionMasiva(conciliacionModuloGenerico);

                    bean.addMensaje(" En este momento se realiza el proceso de conciliación de id " + idConciliacion);
                   
                } else {
                    agregarMensajeErrorGuardadoConciliacion(bean);
                    getCmConciliacionRemoto().eliminar(idConciliacion);
                }

            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private boolean guardarArchivoCargaMasiva(CargaMasivaConciliacionBean bean) {
        boolean hayGuardado ;
        try {

            SimpleDateFormat df = new SimpleDateFormat("YYYYMMddHHmmss");
            String nombreBaseArchivo = bean.getObjeto().getNombreArchivo();
            int indiceExtension = bean.getObjeto().getNombreArchivo().lastIndexOf(".");
            String ext = nombreBaseArchivo.substring(indiceExtension, nombreBaseArchivo.length());
            int idConciliacion = bean.getReporteConciliacion() != null
                    && bean.getReporteConciliacion().getId() > 0 ? bean.getReporteConciliacion().getId() : 0;
            String nombreFinalArchivo = "conciliacion_masiva_" + idConciliacion + "_" + df.format(new Date()) + ext;

            String rutaCopiado = PropApl.getInstance().get(PropApl.CM_RUTA_CONCILIACION_CARGA_MASIVA);

            if (rutaCopiado != null || !"".equals(rutaCopiado)) {
                File nuevoAr = new File(rutaCopiado, nombreFinalArchivo);

                try (FileOutputStream outputStream = new FileOutputStream(nuevoAr, false)) {
                    int read;
                    byte[] bytes = new byte[1024];
                    while ((read = bean.getObjeto().getArchivoCargaConciliable().read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                }
            }
            CmConciliacion cmConciliaciones = getCmConciliacionRemoto().consultar(idConciliacion);
            cmConciliaciones.setArchivo(nombreBaseArchivo);
            cmConciliaciones.setArchivoNombre(nombreFinalArchivo);
            cmConciliaciones.setArchivoRuta(rutaCopiado);
            cmConciliaciones.setArchivoExiste(true);
            getCmConciliacionRemoto().actualizar(cmConciliaciones);
            hayGuardado = true;
        } catch (IOException e) {
            bean.addError(e.getMessage());
            hayGuardado = false;
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
            hayGuardado = false;
        }
        return hayGuardado;
    }

    public void ajusteValores(List<CmDetalle> detallesPorFactura, CmFactura cmFactura) {
        BigDecimal valorTotalPagadoEPS = new BigDecimal("0");
        BigDecimal valorTotalAceptadoIPS = new BigDecimal("0");
        CmGlosaRespuestaDetalle respuestaDetalle = new CmGlosaRespuestaDetalle();
        respuestaDetalle.setPorcentajePagadoEps(cmFactura.getPorcentajePagadoEPS());
        respuestaDetalle.setPorcentajeAceptadoIps(cmFactura.getPorcentajeAceptadoIPS());
        int numeroDetallesCalculados = 0;
        for (CmDetalle detalle : detallesPorFactura) {
            BigDecimal valorPendienteActualDetalle = Optional.ofNullable(detalle.getValorPendienteActual()).orElse(new BigDecimal(BigInteger.ZERO));
            if (valorPendienteActualDetalle.compareTo(new BigDecimal(BigInteger.ZERO)) > 0) {
                calcularPorcentajesRespuestaDetalle(detalle, respuestaDetalle, TIPO_CONCILIACION_MASIVA);

                valorTotalPagadoEPS = valorTotalPagadoEPS.
                        add(respuestaDetalle.getValorPagadoEps()).
                        setScale(4, RoundingMode.HALF_UP);

                valorTotalAceptadoIPS = valorTotalAceptadoIPS.
                        add(respuestaDetalle.getValorAceptadoIps()).
                        setScale(4, RoundingMode.HALF_UP);
                numeroDetallesCalculados++;
            }
        }

        if (valorTotalPagadoEPS.compareTo(new BigDecimal("0.0")) > 0) {
            if (valorTotalPagadoEPS.compareTo(cmFactura.getValorPagadoEPS()) > 0) {
                BigDecimal camparacion = valorTotalPagadoEPS.subtract(cmFactura.getValorPagadoEPS());
                BigDecimal agrearIpsPorRegistro = camparacion.divide(new BigDecimal(numeroDetallesCalculados), 5, RoundingMode.CEILING);
                getMapAjuste().put(TIPO_AJUSTE_EPS, agrearIpsPorRegistro);
            } else {
                if (cmFactura.getValorPagadoEPS() != null && cmFactura.getValorPagadoEPS().compareTo(valorTotalPagadoEPS) > 0) {
                    BigDecimal camparacion = cmFactura.getValorPagadoEPS().subtract(valorTotalPagadoEPS);
                    BigDecimal agrearIpsPorRegistro = camparacion.divide(new BigDecimal(numeroDetallesCalculados), 5, RoundingMode.CEILING);
                    getMapAjuste().put(TIPO_AJUSTE_EPS_MENOR, agrearIpsPorRegistro);
                }
            }
        }

        if (valorTotalAceptadoIPS.compareTo(new BigDecimal("0.0")) > 0) {
            if (valorTotalAceptadoIPS.compareTo(cmFactura.getValorAceptadoIPS()) > 0) {
                BigDecimal camparacion = valorTotalAceptadoIPS.subtract(cmFactura.getValorAceptadoIPS());
                BigDecimal agrearEpsPorRegistro = camparacion.divide(new BigDecimal(numeroDetallesCalculados), 5, RoundingMode.CEILING);
                getMapAjuste().put(TIPO_AJUSTE_IPS, agrearEpsPorRegistro);
            } else {
                if (cmFactura.getValorAceptadoIPS() != null && cmFactura.getValorAceptadoIPS().compareTo(valorTotalAceptadoIPS) > 0) {
                    BigDecimal camparacion = cmFactura.getValorAceptadoIPS().subtract(valorTotalAceptadoIPS);
                    BigDecimal agrearEpsPorRegistro = camparacion.divide(new BigDecimal(numeroDetallesCalculados), 5, RoundingMode.CEILING);
                    getMapAjuste().put(TIPO_AJUSTE_IPS_MENOR, agrearEpsPorRegistro);
                }
            }
        }
    }

    private void calcularValoresConciliacion(CmConciliacion conciliacionIn, CmConciliacion conciliacionOut) {

        BigDecimal procentajeTotal = new BigDecimal("0");
        BigDecimal procentajeCalculadoIPS = new BigDecimal("0");
        BigDecimal procentajeCalculadoEPS = new BigDecimal("0");
        BigDecimal promedioIPS;
        BigDecimal promedioEPS;
        BigDecimal valorLimite = conciliacionIn.getValorFacturasPendienteActualPrecalculado();
        BigDecimal valorPropuestoIPS = conciliacionIn.getValorAceptadoIps();
        BigDecimal valorPropuestoEPS = conciliacionIn.getValorPagadoEps();
        BigDecimal valorTotal;

        if (valorPropuestoIPS.compareTo(new BigDecimal("0")) > 0) {
            promedioIPS = valorPropuestoIPS.divide(valorLimite, 5, RoundingMode.CEILING);
            procentajeCalculadoIPS = promedioIPS.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
        }
        conciliacionOut.setPorcentajeAceptadoIps(procentajeCalculadoIPS);

        if (valorPropuestoEPS.compareTo(new BigDecimal("0")) > 0) {
            promedioEPS = valorPropuestoEPS.divide(valorLimite, 5, RoundingMode.CEILING);
            procentajeCalculadoEPS = promedioEPS.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
        }
        conciliacionOut.setPorcentajePagadoEps(procentajeCalculadoEPS);

        valorTotal = valorPropuestoIPS.
                add(valorPropuestoEPS).setScale(4, RoundingMode.HALF_UP);
        conciliacionOut.setValor(valorTotal);

        if (valorTotal.compareTo(new BigDecimal("0")) > 0) {
            promedioEPS = valorTotal.divide(valorLimite, 5, RoundingMode.CEILING);
            procentajeTotal = promedioEPS.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
        }
        conciliacionOut.setPorcentaje(procentajeTotal);

    }

    private void calcularPorcentajesRespuestaDetalle(CmDetalle detalle, CmGlosaRespuestaDetalle respuestaDetalle, int tipoConciliacion) {

        BigDecimal valorPendientePagoDetalle = detalle.getValorPendienteActual() == null
                ? new BigDecimal("0") : detalle.getValorPendienteActual();

        BigDecimal porcentajePagadoEPS = new BigDecimal(BigInteger.ZERO);
        BigDecimal porcentajeAceptadoIPS = new BigDecimal(BigInteger.ZERO);
        respuestaDetalle.setValorPagadoEps(new BigDecimal(BigInteger.ZERO));
        respuestaDetalle.setValorAceptadoIps(new BigDecimal(BigInteger.ZERO));

        if (TIPO_CONCILIACION_MASIVA == tipoConciliacion) {
            porcentajePagadoEPS = respuestaDetalle.getPorcentajePagadoEps();
            porcentajeAceptadoIPS = respuestaDetalle.getPorcentajeAceptadoIps();
        }

        respuestaDetalle.setPorcentajeAceptadoIps(porcentajeAceptadoIPS);
        respuestaDetalle.setPorcentajePagadoEps(porcentajePagadoEPS);

        BigDecimal ajuste;
        BigDecimal valorAjuste = new BigDecimal("0.0");
        //se calcula porcentaje(ips,eps)  con base al valor pendiente de cada detalle
        if (porcentajePagadoEPS != null) {
            BigDecimal promedio = porcentajePagadoEPS.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
            respuestaDetalle.setValorPagadoEps(promedio.multiply(valorPendientePagoDetalle).setScale(4, RoundingMode.HALF_UP));

            if (porcentajeAceptadoIPS != null) {
                BigDecimal promedioIps = porcentajeAceptadoIPS.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
                respuestaDetalle.setValorAceptadoIps(promedioIps.multiply(valorPendientePagoDetalle).setScale(4, RoundingMode.HALF_UP));
            }

            if (!getMapAjuste().isEmpty()) {
                boolean noHayAjusteAceptadoIps = true;
                if (getMapAjuste().get(TIPO_AJUSTE_EPS) != null) {
                    ajuste = respuestaDetalle.getValorPagadoEps().subtract(getMapAjuste().get(TIPO_AJUSTE_EPS));
                    respuestaDetalle.setValorPagadoEps(ajuste);
                    valorAjuste = getMapAjuste().get(TIPO_AJUSTE_EPS);
                }
                if (getMapAjuste().get(TIPO_AJUSTE_IPS) != null) {
                    ajuste = respuestaDetalle.getValorPagadoEps().add(getMapAjuste().get(TIPO_AJUSTE_IPS));
                    respuestaDetalle.setValorPagadoEps(ajuste);
                    valorAjuste = getMapAjuste().get(TIPO_AJUSTE_IPS);
                    noHayAjusteAceptadoIps = false;
                }
                if (getMapAjuste().get(TIPO_AJUSTE_EPS_MENOR) != null && noHayAjusteAceptadoIps) {
                    ajuste = respuestaDetalle.getValorPagadoEps().add(getMapAjuste().get(TIPO_AJUSTE_EPS_MENOR));
                    respuestaDetalle.setValorPagadoEps(ajuste);
                    valorAjuste = getMapAjuste().get(TIPO_AJUSTE_EPS_MENOR);
                }

            }
        }
        if (porcentajeAceptadoIPS != null) {
            BigDecimal promedio = porcentajeAceptadoIPS.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
            respuestaDetalle.setValorAceptadoIps(promedio.multiply(valorPendientePagoDetalle).setScale(4, RoundingMode.HALF_UP));

            if (!getMapAjuste().isEmpty()) {
                boolean noHayAjusteAceptadoIps = true;
                if (getMapAjuste().get(TIPO_AJUSTE_EPS) != null) {
                    ajuste = respuestaDetalle.getValorAceptadoIps().add(getMapAjuste().get(TIPO_AJUSTE_EPS));
                    respuestaDetalle.setValorAceptadoIps(ajuste);
                    valorAjuste = getMapAjuste().get(TIPO_AJUSTE_EPS);
                    noHayAjusteAceptadoIps = false;
                }
                if (getMapAjuste().get(TIPO_AJUSTE_IPS) != null) {
                    ajuste = respuestaDetalle.getValorAceptadoIps().subtract(getMapAjuste().get(TIPO_AJUSTE_IPS));
                    respuestaDetalle.setValorAceptadoIps(ajuste);
                    valorAjuste = getMapAjuste().get(TIPO_AJUSTE_IPS);
                }
                if (getMapAjuste().get(TIPO_AJUSTE_IPS_MENOR) != null && noHayAjusteAceptadoIps) {
                    ajuste = respuestaDetalle.getValorAceptadoIps().add(getMapAjuste().get(TIPO_AJUSTE_IPS_MENOR));
                    respuestaDetalle.setValorAceptadoIps(ajuste);
                    valorAjuste = getMapAjuste().get(TIPO_AJUSTE_IPS_MENOR);
                }
            }
        }

        // Valor pagado = valor aceptado ips + valor pagado eps
        BigDecimal valorPagado = respuestaDetalle.getValorAceptadoIps().
                add(respuestaDetalle.getValorPagadoEps()).setScale(4, RoundingMode.HALF_UP);
        respuestaDetalle.setValorPagado(valorPagado);

        //Valor pendiente = valor pendiente en detalle -  valor pagado(eps+ips)
        BigDecimal valorPendiente = valorPendientePagoDetalle.subtract(respuestaDetalle.getValorPagado())
                .setScale(4, RoundingMode.HALF_UP);

        if (valorPendiente.compareTo(new BigDecimal("0.0")) < 0) {
            BigDecimal deuda = valorPendiente.abs();
            if (valorAjuste.compareTo(new BigDecimal("0.0")) > 0 && valorAjuste.compareTo(deuda) >= 0) {
                valorPendiente = new BigDecimal("0.0");
            }
        }

        //para distribucion de valores 50% con decimales impares se equilibra a cero
        if (valorPendiente.equals(new BigDecimal("-0.0001"))) {
            valorPendiente = new BigDecimal(BigInteger.ZERO);
        }
        respuestaDetalle.setValorPendiente(valorPendiente);

    }

    private void actualizarValorPendienteActualDetalles(CmDetalle detalle,
            CmGlosaRespuestaDetalle respuestaDetalle, CargaMasivaConciliacionBean bean) throws Exception {
        //MathContext mc  = new MathContext(4);
        BigDecimal valorPendienteActual = detalle.getValorPendienteActual() == null
                ? new BigDecimal("0") : detalle.getValorPendienteActual();
        BigDecimal nuevoPendientaActual = valorPendienteActual.subtract(respuestaDetalle.getValorPagado())
                .setScale(4, RoundingMode.HALF_UP);

        BigDecimal ajuste = new BigDecimal("0.0");

        if (!getMapAjuste().isEmpty()) {
            if (getMapAjuste().get(TIPO_AJUSTE_EPS) != null) {
                ajuste = getMapAjuste().get(TIPO_AJUSTE_EPS);
            }
            if (getMapAjuste().get(TIPO_AJUSTE_IPS) != null) {
                ajuste = getMapAjuste().get(TIPO_AJUSTE_IPS);
            }
        }

        if (nuevoPendientaActual.compareTo(new BigDecimal("0.0")) < 0) {
            BigDecimal deuda = nuevoPendientaActual.abs();
            if (ajuste.compareTo(new BigDecimal("0.0")) > 0 && ajuste.compareTo(deuda) >= 0) {
                nuevoPendientaActual = new BigDecimal("0.0");
            }
        }

        if (nuevoPendientaActual.equals(new BigDecimal("-0.0001"))) {
            nuevoPendientaActual = new BigDecimal(BigInteger.ZERO);
        }
        detalle.setValorPendienteActual(nuevoPendientaActual);
        bean.auditoriaModificar(detalle);
        getCmDetalleRemoto().actualizar(detalle);
    }

    private void actualizarValorPendienteActualFactura(CmFactura factura,
            CmGlosaRespuesta respuesta,
            CargaMasivaConciliacionBean bean) throws Exception {

        BigDecimal valorPendienteActual = factura.getValorPendienteActual() == null
                ? new BigDecimal("0") : factura.getValorPendienteActual();

        BigDecimal nuevoPendientaActual = valorPendienteActual.subtract(respuesta.getValorPagado())
                .setScale(4, RoundingMode.HALF_UP);

        if (nuevoPendientaActual.compareTo(new BigDecimal("0.1")) <= 0
                && nuevoPendientaActual.compareTo(new BigDecimal("0.0001")) >= 0) {
            nuevoPendientaActual = new BigDecimal(BigInteger.ZERO);
        }
        if (nuevoPendientaActual.compareTo(new BigDecimal("0.1")) <= 0
                && nuevoPendientaActual.compareTo(new BigDecimal("-0.0001")) >= 0) {
            nuevoPendientaActual = new BigDecimal(BigInteger.ZERO);
        }

        factura.setValorPendienteActual(nuevoPendientaActual);

        bean.auditoriaModificar(factura);
        getCmFacturaRemoto().actualizar(factura);
    }

    private void editar(CargaMasivaConciliacionBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(CargaMasivaConciliacionBean bean) {
        try {

            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(CargaMasivaConciliacionBean bean) {
        try {

            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(CargaMasivaConciliacionBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    //Estado
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void cargaInicial(CargaMasivaConciliacionBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void reporteConciliacion(CargaMasivaConciliacionBean bean) {
        try {
                   
            Map<String,Integer> wsFacturasExitosas  = obtenerWSFacturasExitosasEnviadasSap(bean);  
            List<ReporteConciliacion> lista = getCmConciliacionRemoto().reporteConciliacionEnvioSapExitoso(bean.getReporteConciliacion().getId(), wsFacturasExitosas);
            bean.setListaReporteConciliacion(lista);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private Map<String, Integer> obtenerWSFacturasExitosasEnviadasSap(CargaMasivaConciliacionBean bean) {
        Map<String, Integer> facturasEnviadasExitosas = new HashMap<>();

        try {

            List<WsCmFactura> listaCmFacturas = getWsCmFacturaRemoto().consultarTodasPorIdCmRadicado(bean.getReporteConciliacion().getIntRadicacion());

            for (WsCmFactura wsfacturas : listaCmFacturas) {
                if (WsCmFactura.ESTADO_EXITOSO == wsfacturas.getEstado()
                        || WsCmFactura.ESTADO_SIN_VALORES_EPS == wsfacturas.getEstado()) {
                    String llave = wsfacturas.getNumeroDocumento() + "-" + wsfacturas.getNumeroRadicado();
                    if (facturasEnviadasExitosas.get(llave) == null) {
                        facturasEnviadasExitosas.put(llave, 1);
                    }
                }
            }
        } catch (Exception e) {
            facturasEnviadasExitosas = new HashMap<>();
        }

        return facturasEnviadasExitosas;
    }

    private void reporteErroresConciliacion(CargaMasivaConciliacionBean bean) {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        int idUltimaOperacion = 0;
        List<CmSincronizacionPaquete> sincronizacionPaquetes =  new ArrayList<>();
        List<WsCmTransaccionDetalle> wstransaccionDetallePaquetes = new ArrayList<>();
        
        try {
            
            int idConciliacionMasivo =  bean.getObjeto().getIdConciliacionMasiva();
            Integer idCmRadicado =  bean.getObjeto().getIdRadicado();
            
            WsCmTransaccion  wsTransaccion = getWsCmTransaccionRemoto().consultarUltimaWsTransaccionPorCmConciliacion(idConciliacionMasivo);
            if(wsTransaccion.existeWsCmTransaccion()){
                idUltimaOperacion = wsTransaccion.getId();
            }else{
               CmSincronizacion ultimaSincronizacion =  getCmSincronizacionRemoto().consultarUltimaSincronizacionPorCmConciliacion(idConciliacionMasivo);
               if(ultimaSincronizacion != null && ultimaSincronizacion.getId() != null ){
                   idUltimaOperacion = ultimaSincronizacion.getId();
               }        
            }
            
            
            if (wsTransaccion.existeWsCmTransaccion()) {
                wstransaccionDetallePaquetes = getWsCmTransaccionDetalleRemoto().consultarPorIdWsCmTransaccion(wsTransaccion.getId());
            } else {
                ParamConsulta paramConsulta = new ParamConsulta();
                paramConsulta.setParametroConsulta1(idUltimaOperacion);
                sincronizacionPaquetes = getCmSincronizacionPaqueteRemoto().consultarPorSincronizacion(paramConsulta);
            }    
            
            Reporte reporte = new Reporte();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
            String postname = sdf.format(new Date());
            HSSFWorkbook libro = new HSSFWorkbook();
            Sheet hoja = libro.createSheet("Pagina " + 1);
            Row fila = hoja.createRow(0);
                
            if (!sincronizacionPaquetes.isEmpty() || !wstransaccionDetallePaquetes.isEmpty()) {

                String[] columns = {
                    "Consecutivo",
                    "Resultado"
                };

                int tam = columns.length;
                for (int i = 0; i < tam; i++) {
                    fila.createCell(i).setCellValue(columns[i]);
                }

                if(wsTransaccion.existeWsCmTransaccion()){
                  procesarErroresEsTransaccionPaquete(wstransaccionDetallePaquetes, hoja);
                }else{
                  procesarErroresSincronizacionPaquete(sincronizacionPaquetes, hoja);
                }
                
                libro.write(byteOutput);
                reporte.setContenidoEnBytes(byteOutput.toByteArray());
                reporte.setNombreReporte("ErroresConciliacion_" + idConciliacionMasivo + "_" + postname + ".xls");
                generarSalidaInforme(bean, reporte);
            } else {
                
                String mensajeErrorCmRadicado = (idCmRadicado == null || idCmRadicado == 0) ? 
                                                ", No se creo cmradicado ": " , de cmRadicado: ( "+ idCmRadicado+")";
                String mensajeErrorTransaccion = (idUltimaOperacion != 0) ? ",de ws transaccion : ( "+ idUltimaOperacion+" )" : " " ;
                
                Row dataRow = hoja.createRow(1);
                dataRow.createCell(0).setCellValue("Ha ocurrido un error en la radicación "
                        + "y sincronización de la conciliación masiva de id : (" + idConciliacionMasivo+")"+ mensajeErrorCmRadicado + mensajeErrorTransaccion);
                libro.write(byteOutput);
                reporte.setContenidoEnBytes(byteOutput.toByteArray());
                reporte.setNombreReporte("ErroresConciliacion_" + idConciliacionMasivo + "_" + postname + ".xls");
                bean.addMensaje("No se han encontrado respuestas relacionadas.");
                generarSalidaInforme(bean, reporte);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        } finally {
            try {
                byteOutput.close();
            } catch (IOException ex) {
                bean.addError(ex.getMessage());
            }
        }
    }

    private void procesarErroresSincronizacionPaquete(List<CmSincronizacionPaquete> paquetes, Sheet hoja) throws JsonSyntaxException {
        int filaExcel = 1;
        for (CmSincronizacionPaquete paquete : paquetes) {
            try {
                if (paquete.getEstadoTransacion() != CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_EXITOSA) {
                    String respuestaSap = new String(paquete.getJsonRespuesta());

                    Gson gson = new Gson();
                    JsonArray entries;
                    try {
                        entries = (JsonArray) new JsonParser().parse(respuestaSap);
                    } catch (JsonSyntaxException e) {
                        entries = null;
                    }

                    if (entries != null && entries.size() > 0) {
                        for (JsonElement entry : entries) {
                            RespuestaNotificacionFactura factura = gson.fromJson((JsonObject) entry, RespuestaNotificacionFactura.class);
                            if (!CODIGO_RESPUESTA_NOTAS_CREADAS_EXITOSAS.equals(factura.getCodigoResultado())
                                    && !CODIGO_RESPUESTA_NOTA_CREADA_EXITOSA.equals(factura.getCodigoResultado())) {
                                Row dataRow = hoja.createRow(filaExcel);
                                dataRow.createCell(0).setCellValue(factura.getConsecutivo());
                                dataRow.createCell(1).setCellValue(entry.toString());
                                filaExcel++;
                            }
                        }
                    } else {
                        Row dataRow = hoja.createRow(filaExcel);
                        dataRow.createCell(0).setCellValue("El paquete " + paquete.getId() + " posee un error en su estructura :" + paquete.getJsonRespuestaStr());
                        filaExcel++;
                    }
                }
            } catch (Exception e) {
                Row dataRow = hoja.createRow(filaExcel);
                dataRow.createCell(0).setCellValue("El paquete " + paquete.getId() + " posee un error en su estructura :" + paquete.getJsonRespuestaStr());
                filaExcel++;
            }
        }
    }
    
    private void procesarErroresEsTransaccionPaquete( List<WsCmTransaccionDetalle> paquetes, Sheet hoja) throws JsonSyntaxException {
        int filaExcel = 1;
        Gson gson = new Gson();
        
        for (WsCmTransaccionDetalle paquete : paquetes) {
            try {
                    String respuestaSap = new String(paquete.getJsonRespuesta());

                    RespuestaNotificacionFacturaPaquete envio = gson.fromJson(respuestaSap, RespuestaNotificacionFacturaPaquete.class);

                    if (envio != null) {
                        for (RespuestaNotificacionFactura factura : envio.getFacturas()) {
                            if (!CODIGO_RESPUESTA_NOTAS_CREADAS_EXITOSAS.equals(factura.getCodigoResultado())
                                    && !CODIGO_RESPUESTA_NOTA_CREADA_EXITOSA.equals(factura.getCodigoResultado())) {
                                Row dataRow = hoja.createRow(filaExcel);
                                dataRow.createCell(0).setCellValue(factura.getConsecutivo());
                                dataRow.createCell(1).setCellValue(factura.getResultado());
                                filaExcel++;
                            }
                        }
                    } else {
                        Row dataRow = hoja.createRow(filaExcel);
                        dataRow.createCell(0).setCellValue("El paquete " + paquete.getId() + " posee un error en su estructura :" + paquete.getJsonRespuestaStr());
                        filaExcel++;
                    }
                
            } catch (Exception e) {
                Row dataRow = hoja.createRow(filaExcel);
                dataRow.createCell(0).setCellValue("El paquete " + paquete.getId() + " posee un error en su estructura :" + paquete.getJsonRespuestaStr());
                filaExcel++;
            }
        }      
    }

    private boolean verificarBloqueoFacturas(CargaMasivaConciliacionBean bean) {
        boolean facturaPermitida = true;
        try {
            int usuarioGestiona = bean.getConexion().getUsuario().getId();
            List<String> listIdFacturasEncontradas = new ArrayList<>();
            for (CmFactura cmFactura : bean.getFacturasEncontradas()) {
                listIdFacturasEncontradas.add(String.valueOf(cmFactura.getId()));
            }
            String idsFacturas = String.join(",", listIdFacturasEncontradas);
            bean.getParamConsultaUtilitario().setParametroConsulta1(idsFacturas);
            bean.getParamConsultaUtilitario().setParametroConsulta2(usuarioGestiona);
            String facturasEncontradasBloqueadas = getCmFacturaRemoto().consultarFacturasBloquedas(bean.getParamConsultaUtilitario());
            bean.getParamConsultaUtilitario().setParametroConsulta1(null);
            bean.getParamConsultaUtilitario().setParametroConsulta2(null);
            if (facturasEncontradasBloqueadas != null && facturasEncontradasBloqueadas.length() > 0) {
                bean.addError("Las siguentes facturas estan bloquedas por favor contacte administrador, "
                        + facturasEncontradasBloqueadas);
                facturaPermitida = false;
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
        return facturaPermitida;
    }

    private void registrarEjecucionSincronizacion(CargaMasivaConciliacionBean bean) {
        try {

            CmConciliacion conciliacion = getCmConciliacionRemoto().consultar(bean.getObjeto().getIdConciliacionMasiva());

            if (conciliacion != null) {

                if (conciliacion.getEstadoProceso() == null
                        || conciliacion.getEstadoProceso() != CmConciliacion.ESTADO_REGISTRADA) {

                    CargaMasivaConciliacionBean copiaBean = (CargaMasivaConciliacionBean) bean.clone();
                    int numeroSincronizacionesAsociadas = getCmSincronizacionRemoto().consultarCantidadPorConciliacion(bean.getObjeto().getIdConciliacionMasiva());

                    if (numeroSincronizacionesAsociadas > 0) {
                        bean.addError("El identificador ya ha participado en proceso de envío a Sap.");
                    } else {
                    
                        int numeroFacturasAsociadas = getCmGlosaRespuestaRemoto().consultarCantidadPorConciliacion(bean.getObjeto().getIdConciliacionMasiva());

                        CmConciliacion obj = getCmConciliacionRemoto().consultar(bean.getObjeto().getIdConciliacionMasiva());
                        if (obj != null) {
                            obj.setCantidadFacturas(numeroFacturasAsociadas);
                            obj.setEstadoProceso(CmConciliacion.ESTADO_EN_PROCESO);
                            getCmConciliacionRemoto().actualizar(obj);
                        }

                        crearCmRadicadoSiNoExiste(bean.getObjeto().getIdConciliacionMasiva(), bean);
                         
                        EjecucionRegistroTablasSincronizacionHilo ejecucionRegistroHilo = new EjecucionRegistroTablasSincronizacionHilo(copiaBean);
                        ejecucionRegistroHilo.start();
                        bean.addMensaje("Se ha lanzado el proceso de manera exitosa.");
                    }

                } else {
                    if (conciliacion.getEstadoProceso() == CmConciliacion.ESTADO_REGISTRADA) {
                        bean.addError("El estado de la conciliación es registrada, lo que indica que ya sido procesado en el registro de encabezados sincronizados.");
                    }
                }
            } else {
                bean.addError("El identificador de conciliación masiva no existe, por favor verifique.");
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private int crearCmRadicadoSiNoExiste(Integer idCmConciliacion, CargaMasivaConciliacionBean bean) {
        int idCmRadicado = 0;
        try {
            if (idCmConciliacion != null && idCmConciliacion > 0) {
                Map<String, Object> filtros = new HashMap<>();
                ParamConsulta paramConsulta = new ParamConsulta();
                filtros.put("idConciliacionMasiva", idCmConciliacion);
                paramConsulta.setFiltros(filtros);
                int id = getCmRadicadoServicioRemoto().consultarCantidadRadicado(paramConsulta);
                if (id == 0) {
                    CmRadicado cmRadicado = new CmRadicado();
                    cmRadicado.setCmConciliacion(new CmConciliacion(idCmConciliacion));
                    cmRadicado.setEstado_radicado(false);
                    cmRadicado.setRadicado(0);
                    bean.auditoriaGuardar(cmRadicado);
                    idCmRadicado = getCmRadicadoServicioRemoto().insertar(cmRadicado);
                }
            }
        } catch (Exception e) {
            idCmRadicado = 0;
        }
        return idCmRadicado;
    }

    private void generarSalidaInforme(CargaMasivaConciliacionBean bean, Reporte reporte) {
        try {
            byte[] exportContent = reporte.getContenidoEnBytes();
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + reporte.getNombreReporte() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            int i = reporte.getNombreReporte().lastIndexOf(".");
            String ext = reporte.getNombreReporte().substring(i, reporte.getNombreReporte().length());
            if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/doc");
            } else if (ext.equalsIgnoreCase(".docx")) {
                ec.setResponseContentType("application/docx");
            } else if (ext.equalsIgnoreCase(".xls")) {
                ec.setResponseContentType("application/xls");
            } else if (ext.equalsIgnoreCase(".xlsx")) {
                ec.setResponseContentType("application/xlsx");
            } else if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("application/txt");
            }

            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
            }
            fc.responseComplete();
        } catch (IOException e) {
            bean.addError("No es posible descargar este archivo, por favor contacte con el administrador : " + e.getMessage());
        }
    }

    private void verEncabezados(CargaMasivaConciliacionBean bean) {
        try {
            bean.getParamConsultaSincEncabezado().setParametroConsulta1(bean.getObjeto().getIdRadicado());
            bean.getParamConsultaSincEncabezado().setCantidadRegistros(getCmSincronizacionEncabezadoRemoto().consultarCantidadLista(bean.getParamConsultaSincEncabezado()));
            bean.setRegistrosSincEncabezado(getCmSincronizacionEncabezadoRemoto().consultarLista(bean.getParamConsultaSincEncabezado()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void verWsCmFacturas(CargaMasivaConciliacionBean bean) {
        try {
            bean.getParamConsulta(0).setParametroConsulta1(bean.getObjeto().getIdRadicado());
            bean.getParamConsulta(0).setCantidadRegistros(getWsCmFacturaRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistrosWsCmFacturas(getWsCmFacturaRemoto().consultarLista(bean.getParamConsulta(0)));        
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
     private void verWsCmFacturaDetalle(CargaMasivaConciliacionBean bean) {
        try {
            bean.getParamConsulta(1).setCantidadRegistros(getWsCmFacturaDetalleRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setRegistrosWsCmFacturaDetalles(getWsCmFacturaDetalleRemoto().consultarLista(bean.getParamConsulta(1)));       
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verTipoFuenteDatos(CargaMasivaConciliacionBean bean) {
        try {
            ParamConsulta consulta = new ParamConsulta();
            consulta.setParametroConsulta1(bean.getObjeto().getIdRadicado());
            int cantidadFactuas = getWsCmFacturaRemoto().consultarCantidadLista(consulta);
            if (cantidadFactuas > 0) {
                bean.setTipoFuenteDatos(CargaMasivaConciliacionBean.TIPO_FUENTE_DATOS_WS_FACTURAS);
            } else {
                cantidadFactuas = getCmSincronizacionEncabezadoRemoto().consultarCantidadLista(consulta);
                if (cantidadFactuas > 0) {
                    bean.setTipoFuenteDatos(CargaMasivaConciliacionBean.TIPO_FUENTE_DATOS_SINCRONIZACION_ENCABEZADO);
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verResumenEncabezados(CargaMasivaConciliacionBean bean) {
        try {
            bean.setListaCmSincronizaconEncabezadoResumen(getCmSincronizacionEncabezadoRemoto().consultarResumenEncabezado(bean.getObjeto().getIdRadicado()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verCuentasContables(CargaMasivaConciliacionBean bean) {
        try {
            bean.getParamConsultaSincDetalle().setCantidadRegistros(getCmSincronizacionDetalleRemoto().consultarCantidadLista(bean.getParamConsultaSincDetalle()));
            bean.setRegistrosSincDetalle(getCmSincronizacionDetalleRemoto().consultarLista(bean.getParamConsultaSincDetalle()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verAdjuntos(CargaMasivaConciliacionBean bean) {
        try {
            List<com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion> listaAdjuntos = getCmConciliacionRemoto().consultarAnexos(bean.getObjeto().getIdConciliacionMasiva());
            if (listaAdjuntos.size() > 0) {
                bean.setListaAnexos(listaAdjuntos);
            }
        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }
}
