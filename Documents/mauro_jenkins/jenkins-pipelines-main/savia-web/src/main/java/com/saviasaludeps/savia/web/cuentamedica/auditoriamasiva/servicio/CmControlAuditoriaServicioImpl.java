/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.servicio;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionPaquete;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.Reporte;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccion;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccionDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaMasivaRemoto;
//import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionEncabezadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionPaqueteRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmFacturaDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmTransaccionDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmTransaccionRemoto;
import com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.bean.CmControlAuditoriaMasivaBean;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaNotificacionFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaNotificacionFacturaPaquete;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


/**
 *
 * @author Admin
 */
public class CmControlAuditoriaServicioImpl extends AccionesBO implements CmControlAuditoriaMasivaServicioIface {
    
    public final static String CODIGO_RESPUESTA_NOTAS_CREADAS_EXITOSAS = "13";
    public final static String CODIGO_RESPUESTA_NOTA_CREADA_EXITOSA    = "2";
    
  
    private CmSincronizacionPaqueteRemoto getCmSincronizacionPaqueteRemoto() throws Exception {
        return (CmSincronizacionPaqueteRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionPaqueteServicio", CmSincronizacionPaqueteRemoto.class.getName());
    }
    
    private CmSincronizacionEncabezadoRemoto getCmSincronizacionEncabezadoRemoto() throws Exception {
        return (CmSincronizacionEncabezadoRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionEncabezadoServicio", CmSincronizacionEncabezadoRemoto.class.getName());
    }

    private CmSincronizacionDetalleRemoto getCmSincronizacionDetalleRemoto() throws Exception {
        return (CmSincronizacionDetalleRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionDetalleServicio", CmSincronizacionDetalleRemoto.class.getName());
    }
    
    private CmAuditoriaMasivaRemoto getCmAuditoriaMasivaRemoto() throws Exception {
        return (CmAuditoriaMasivaRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaMasivaServicio", CmAuditoriaMasivaRemoto.class.getName());
    }
    
    private CmSincronizacionRemoto getCmSincronizacionRemoto() throws Exception {
        return (CmSincronizacionRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionServicio", CmSincronizacionRemoto.class.getName());
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
    public void cargaInicial(CmControlAuditoriaMasivaBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void Accion(CmControlAuditoriaMasivaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case CmControlAuditoriaMasivaBean.ACCION_VER_SINCRONIZACION_ENCABEZADOS:
                            verSincronizacionEncabezados(bean);
                            break;
                        case CmControlAuditoriaMasivaBean.ACCION_VER_SINCRONIZACION_DETALLES:
                            verSincronizacionDetalles(bean);
                            break;
                        case CmControlAuditoriaMasivaBean.ACCION_VER_REPORTE_ERRORES:
                            verErroresSincronizacion(bean);
                            break;
                          case CmControlAuditoriaMasivaBean.ACCION_VER_WS_CM_FACTURAS:
                            verWsCmFacturas(bean);
                            break;
                        case CmControlAuditoriaMasivaBean.ACCION_VER_CM_FACTURAS_DETALLES:
                            verWsCmFacturaDetalle(bean);
                            break;
                         case CmControlAuditoriaMasivaBean.ACCION_VER_TIPO_FUENTE_DATOS:
                            verTipoFuenteDatos(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    break;
            }
        }
    }
 
    private void listar(CmControlAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsulta().setParametroConsulta1(true);
            bean.getParamConsulta().setCantidadRegistros(getCmAuditoriaMasivaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCmAuditoriaMasivaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void generarSalidaInforme(CmControlAuditoriaMasivaBean bean, Reporte reporte) {
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
            }else if (ext.equalsIgnoreCase(".txt")) {
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
      
    private void verSincronizacionEncabezados(CmControlAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsultaSincEncabezado().setParametroConsulta1( Integer.valueOf(bean.getObjeto().getCmRadicado()) );
            bean.getParamConsultaSincEncabezado().setCantidadRegistros(getCmSincronizacionEncabezadoRemoto().consultarCantidadLista(bean.getParamConsultaSincEncabezado()));
            bean.setRegistrosSincEncabezado(getCmSincronizacionEncabezadoRemoto().consultarLista(bean.getParamConsultaSincEncabezado()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    private void verSincronizacionDetalles(CmControlAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsultaSincDetalle().setCantidadRegistros(getCmSincronizacionDetalleRemoto().consultarCantidadLista(bean.getParamConsultaSincDetalle()));
            bean.setRegistrosSincDetalle(getCmSincronizacionDetalleRemoto().consultarLista(bean.getParamConsultaSincDetalle()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void verWsCmFacturas(CmControlAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getWsCmFacturaRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistrosWsCmFacturas(getWsCmFacturaRemoto().consultarLista(bean.getParamConsulta(0)));        
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
     private void verWsCmFacturaDetalle(CmControlAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsulta(1).setCantidadRegistros(getWsCmFacturaDetalleRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setRegistrosWsCmFacturaDetalles(getWsCmFacturaDetalleRemoto().consultarLista(bean.getParamConsulta(1)));       
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    private void verErroresSincronizacion(CmControlAuditoriaMasivaBean bean){
        List<WsCmTransaccionDetalle> wstransaccionDetallePaquetes = new ArrayList<>();
        List<CmSincronizacionPaquete> sincronizacionPaquetes =  new ArrayList<>();
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        Reporte reporte = new Reporte();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        String postname = sdf.format(new Date());
        HSSFWorkbook libro = new HSSFWorkbook();
        Sheet hoja = libro.createSheet("Pagina " + 1);
        Row fila = hoja.createRow(0);
        int idUltimaOperacion = 0;
        
        try {
              
             int idAuditoriaMasiva = Optional.ofNullable(bean.getObjeto().getId()).orElse(0);

             WsCmTransaccion wsTransaccion = getWsCmTransaccionRemoto().consultarUltimaWsTransaccionPorCmAuditoriaMasiva(idAuditoriaMasiva);

             if (wsTransaccion.existeWsCmTransaccion()) {
                 idUltimaOperacion = wsTransaccion.getId();
             } else {
                 CmSincronizacion ultimaSincronizacion = getCmSincronizacionRemoto().consultarUltimaSincronizacionPorCmAuditoriaMasiva(idAuditoriaMasiva);
                 if (ultimaSincronizacion != null && ultimaSincronizacion.getId() != null) {
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
                     
              if (!sincronizacionPaquetes.isEmpty() || !wstransaccionDetallePaquetes.isEmpty()) {

                 String[] columns = {
                     "Consecutivo/Id Factura", 
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
                 reporte.setNombreReporte("ErroresAuditoriaMasiva_" +  idAuditoriaMasiva + "_" + postname + ".xls");
                 generarSalidaInforme(bean, reporte);
             } else {
                   String mensaje = idUltimaOperacion == 0  ? ", No se crearon registros para envio a sap" : ", sincronizacion id :" + idUltimaOperacion;
                   
                   Row dataRow = hoja.createRow(1);
                   dataRow.createCell(0).setCellValue("Ha ocurrido un error al crear la radicación "
                           + "y sincronización de la auditoria masiva : "+idAuditoriaMasiva+" "+mensaje);
                   libro.write(byteOutput);
                   reporte.setContenidoEnBytes(byteOutput.toByteArray());
                   reporte.setNombreReporte("ErroresAuditoriaMasiva_" + idAuditoriaMasiva + "_" + postname + ".xls");
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

    private void procesarErroresSincronizacionPaquete(List<CmSincronizacionPaquete> sincronizacionPaquetes, Sheet hoja) throws JsonSyntaxException {
        int filaExcel = 1;
        for (CmSincronizacionPaquete paquete : sincronizacionPaquetes) {
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
    
    private void verTipoFuenteDatos(CmControlAuditoriaMasivaBean bean) {
        try {
            ParamConsulta consulta = new ParamConsulta();
            consulta.setParametroConsulta1(bean.getObjeto().getCmRadicado());
            int cantidadFactuas = getWsCmFacturaRemoto().consultarCantidadLista(consulta);
            if (cantidadFactuas > 0) {
                bean.setTipoFuenteDatos(CmControlAuditoriaMasivaBean.TIPO_FUENTE_DATOS_WS_FACTURAS);
            } else {
                consulta.setParametroConsulta1(Integer.parseInt(bean.getObjeto().getCmRadicado()));
                cantidadFactuas = getCmSincronizacionEncabezadoRemoto().consultarCantidadLista(consulta);
                if (cantidadFactuas > 0) {
                    bean.setTipoFuenteDatos(CmControlAuditoriaMasivaBean.TIPO_FUENTE_DATOS_SINCRONIZACION_ENCABEZADO);
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
}
