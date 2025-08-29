/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;


import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.Reporte;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.utilidades.Util;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.ReporteCuentasMedicasRemoto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.sql.DataSource;


/**
 *
 * @author admin
 */
@Stateless
@Remote(ReporteCuentasMedicasRemoto.class)
@Local(ReporteCuentasMedicasLocal.class)
public class ReporteCuentasMedicasServicio extends GenericoServicio implements ReporteCuentasMedicasLocal, ReporteCuentasMedicasRemoto {
    
    public final static String POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE = " 23:59:59";
    public final static String POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO = " 00:00:00";
    public final static String SEPARADOR = "|";
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public final static String ENCABEZADO_MOTIVOS= "Nit      | IPS | Numero Facturado | Numero Radicado  |"
            + " Fecha Prestacion Factura | Valor Factura  | Consecutivo Detalle | Valor Pendiente Actual Servicio | Motivo Glosa | Hay Glosa Automatica | Codigo Servicio | Nombre Servicio |"
            + " Observacion Servicio | Tipo Documento | Numero Documento Usuario | Nombre Usuario | Valor Pendiente Actual Factura |"
            + " Fecha Radicacion \n";
    
    public final static String ENCABEZADO_RESPUESTA_DETALLES= "Nit     | IPS | Fecha Radicacion  | Numero Facturado | Numero Radicado |"
            + " Numero Respuesta Glosa  | Consecutivo Detalle |"
            + " Valor Factura | Tipo Documento | Numero Documento | Afiliado | Valor Facturado Servicio | Valor Pendiente Actual Servicio | "
            + " Observacion Servicio | Valor Pendiente Actual Factura |"
            + " Tipo Respuesta | Codigo Servicio | Nombre Servicio |"
            + " Valor Aceptado EPS Servicio | Valor Aceptado IPS Servicio | "
            + " Observacion Glosa Respuesta Servicio | Respuesta Detalle Servicio | Fecha Respuesta Detalle  \n";
    
    public final static String ENCABEZADO_DETALLES_SIN_RESPUESTA = "Nit     | IPS | Fecha Radicacion | Numero Facturado | Numero Radicado |"
            + " Consecutivo Detalle | Tipo Documento | Numero Documento | Afiliado | "
            + " Codigo Servicio | Nombre Servicio | Observacion Servicio | Motivo Glosa | Hay Glosa Automatica | "
            + " Valor Pendiente Servicio "
            + "  \n";
    
    public final static String ENCABEZADO_REPUESTAS_POR_DETALLE = "Nit  | IPS  | Fecha Radicacion | Numero Facturado | "
            + " Numero Radicado | Tipo Documento | Documento | Afiliado | Codigo Servicio | "
            + " Nombre Servicio | Observacion Servicio | Motivo Glosa Servicio | Hay Glosa Automatica | Valor Pendiente Actual Servicio | Fecha Primera Respuesta | "
            + " Tipo Respuesta Primera Respuesta | Valor EPS Primera Respuesta | Porcentaje EPS Primera Respuesta | Valor IPS Primera Respuesta | "
            + " Porcentaje IPS Primera Respuesta | Observacion Primera Respuesta | Fecha Segunda Respuesta | Tipo Respuesta Segunda Respuesta | "
            + " Valor EPS Segunda Respuesta | Porcentaje EPS Segunda Respuesta | Valor IPS Segunda Respuesta | "
            + " Porcentaje IPS Segunda Respuesta | Observacion Segunda Respuesta | Fecha Tercera Respuesta | Tipo Respuesta Tercera Respuesta | "
            + " Valor EPS Tercera Respuesta | Porcentaje EPS Tercera Respuesta | Valor IPS Tercera Respuesta | "
            + " Porcentaje IPS Tercera Respuesta | Observacion Tercera Respuesta "
            + "  \n";
    
    public final static String ENCABEZADO_AUDITORIA = "Grupo | Tipo Auditoria | Estado Factura | Numero Radicado | "
              + " Nit | Ips | Numero Factorado | Tipo Contrato | Fecha Radicacion | Cantidad Pacientes | Valor Factura | Valor Copago | Valor Inicial Glosa | "
              + " Valor Pendiente Actual | Usuario Lider | Usuario Tecnico | "
              + " Usuario Medico | Usuario Gestiona | Usuario Audita Rips | Fecha Devolucion | Usuario Devolucion | Fecha Cierre \n";
    
    public final static String ENCABEZADO_INFORME_GENERAL_CM = "Numero Radicado | Nit | Ips | Naturaleza Juridica | Numero Contrato | Numero Facturado | "
              + " Valor Factura | Valor Inicial Glosa | Valor Pendiente Actual | Valor Pagado Factura | Valor Copago | Tipo Contrato | Fecha Radicacion | Fecha Proceso Radicacion | Fecha Prestacion | Estado Factura | "
              + " Regimen | Id Lote | Login Radicacion | Usuario Radicacion \n";
    
    public final static String ENCABEZADO_INFORME_GENERAL_CM_DETALLES = "Id Factura | Numero Radicado | Nit | Ips | Numero Contrato | Numero Facturado | "
              + " Valor Factura | Tipo Documento | Numero Documento | Nombre Afiliado| Servicio | Cantidad | Valor Servicio | Valor Pagado Servicio | Valor Pendiente Servicio | "
              + " Valor Copago Servicio | Tipo Contrato | Fecha Radicacion | Estado Factura | Regimen "
              + "  \n";
    
    public final static String ENCABEZADO_INFORME_DEVOLUCION_FACTURAS = "Numero Radicado | Nit | Ips | Numero Contrato | Numero Facturado | "
              + " Valor Factura | Tipo Contrato | Fecha Radicacion | Fecha Prestacion | Estado Factura | "
              + " Regimen | Motivo Devolucion | Fecha Devolucion | Observacion Devolucion   \n";
    
    
    public final static String ENCABEZADO_INFORME_FACTURAS_CONCILIADAS = "Numero Radicado | Nit | Ips | Naturaleza Juridica | Numero Contrato | Numero Facturado | "
              + " Valor Factura | Valor Inicial Glosa | Valor Pendiente Actual | Valor Pagado Factura | Valor Copago | Tipo Contrato | Fecha Radicacion | Fecha Proceso Radicacion | Fecha Prestacion | Estado Factura | "
              + " Regimen | Id Lote \n";
    
    public final static String ENCABEZADO_INFORME_FACTURAS_RECHAZADAS = " Id Lote | Numero Factura | Razon Social | Nit |"
            + " Codigo EPS | Nombre Administradora | Valor Pagar | Valor Descuento | Observacion Rechazo | Fecha Radicacion \n";
    
    
    public final static String ENCABEZADO_INFORME_DETALLES_DESCUENTO_CAPITA = "Id Factura | Numero Radicado | Nit | Ips | Numero Contrato | Numero Facturado | "
              + " Valor Factura | Tipo Documento | Numero Documento | Nombre Afiliado| Servicio | Cantidad | Valor Servicio | Valor Pagado Servicio | Valor Pendiente Servicio | "
              + " Valor Copago Servicio | Tipo Contrato | Fecha Radicacion | Estado Factura | Regimen | Marcacion | Observacion Capita Descuento "
              + "  \n";
        
    
    public final static String RESTRICCION_FACTURA_ANULADA = " AND cmf.estado_factura != " + 
                                                               CmFactura.ESTADO_FACTURA_ANULADA;
    
    public final static String SUBCONSULTA_HAY_GLOSA_AUTOMATICA = "( SELECT cmg.tipologia FROM cm_auditoria_motivos_glosas cmg WHERE "
                    + "  cmg.tipologia = '"+CmAuditoriaMotivoGlosa.TIPOLOGIA_GLOSA_AUTOMATICA+"' "
                    + "  and  cmg.cm_detalles_id = cmd.id limit 1  ) as tipo_glosa ";
    
    public final static String SEPARADOS_PUNTO = ".";
    public final static String SEPARADOS_COMA  = ",";
    
    @Override
    public Reporte generarReporteMotivos(ParamConsulta paramConsulta) throws Exception {
        Reporte reporte = new Reporte();
        Connection dbConnection = null;
        StringBuilder builderCadena = new StringBuilder();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        int cantidadRegistros = 0;
        try {
             
            String strQuery = "SELECT cmd.id as idDetalle, cmf.nit, cmf.ips, cmf.numero_facturado, "
                    + " cmf.numero_radicado,cmf.fecha_prestacion , cmf.valor_factura, cmd.consecutivo_item, "
                    + " cmf.valor_pendiente_actual as valor_pendiente_factura, "
                    + " cmd.ma_servicio_codigo, cmd.ma_servicio_valor, "
                    + " cmd.valor_pendiente_actual as valor_pendiente_servicio, "
                    + " cmd.observacion as observacion_servicio ,cmd.mae_tipo_documento_valor, cmd.documento as numero_documento ,  cmd.motivo_glosa, "
                    + " cmd.nombre_completo_afiliado, cmf.fecha_radicacion ,"
                    +  SUBCONSULTA_HAY_GLOSA_AUTOMATICA
                    + " FROM cm_facturas cmf "
                    + " INNER JOIN cm_detalles cmd ON cmd.cm_facturas_id = cmf.id "
                    + " where cmf.id > 0 " + RESTRICCION_FACTURA_ANULADA;
            
            
            dbConnection = this.getConnection();
            
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {

                String fechaInicial = sdf.format(paramConsulta.getParametroConsulta1());
                String fechaFinal = sdf.format(paramConsulta.getParametroConsulta2());
                strQuery += " AND (cmf.fecha_radicacion >= '" + fechaInicial + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO + "') AND ( cmf.fecha_radicacion < '" + fechaFinal +POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "') ";

                if (paramConsulta.getParametroConsulta3() != null) {
                    strQuery += " AND cmf.nit like '%" + paramConsulta.getParametroConsulta3() + "%'";
                }

                if (paramConsulta.getParametroConsulta4() != null) {
                    strQuery += " AND cmf.ips like '%" + paramConsulta.getParametroConsulta4() + "%' ";
                }

                strQuery += " order by cmf.numero_radicado asc ";

                preparedStatement = dbConnection.prepareStatement(strQuery);
                resultSet = preparedStatement.executeQuery();
               
                while (resultSet.next()) { 
                   
                    String valorFactura = resultSet.getString("valor_factura") == null ?
                                          "" : resultSet.getString("valor_factura") ;
                            valorFactura = valorFactura.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                    String valorPendienteServicio = resultSet.getString("valor_pendiente_servicio") == null ?
                                                    "" : resultSet.getString("valor_pendiente_servicio");
                            valorPendienteServicio = valorPendienteServicio.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                    String codigoServicio = resultSet.getString("ma_servicio_codigo") == null ?
                                             "" : resultSet.getString("ma_servicio_codigo") ;
                    String nombreServicio = resultSet.getString("ma_servicio_valor") == null ?
                                             "" : resultSet.getString("ma_servicio_valor") ;
                    String tipoDocumento = resultSet.getString("mae_tipo_documento_valor") == null ?
                                             "" : resultSet.getString("mae_tipo_documento_valor") ;
                    String numeroDocumento = resultSet.getString("numero_documento") == null ?
                                             "" : resultSet.getString("numero_documento") ;
                    String valorPendienteFactura = resultSet.getString("valor_pendiente_factura") == null ? 
                                                   "" : resultSet.getString("valor_pendiente_factura");
                            valorPendienteFactura = valorPendienteFactura.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                    String tipologiaMotivsoGlosaStr =  resultSet.getInt("tipo_glosa") == CmAuditoriaMotivoGlosa.TIPOLOGIA_GLOSA_AUTOMATICA ?
                                                      "Si": "No";
                      
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nit")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ips")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_facturado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_radicado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_prestacion"));
                    builderCadena.append(SEPARADOR);   
                    builderCadena.append(valorFactura);                  
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("consecutivo_item"));     
                    builderCadena.append(SEPARADOR);  
                    builderCadena.append(valorPendienteServicio);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("motivo_glosa")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(tipologiaMotivsoGlosaStr);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(codigoServicio);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(nombreServicio));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("observacion_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(tipoDocumento);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(numeroDocumento); 
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nombre_completo_afiliado")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorPendienteFactura);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_radicacion"));
                    builderCadena.append("\n");
                    cantidadRegistros++;
                }
            }
          
            if(builderCadena.length()> 0){
              StringBuilder builderFinal = new StringBuilder(); 
              builderFinal.append(ENCABEZADO_MOTIVOS);
              builderFinal.append(builderCadena);
              reporte.setContenidoEnString(builderFinal.toString());
              reporte.setCantidadRegistros(cantidadRegistros);
              builderCadena.setLength(0);
            }
        } catch (NoResultException e) {
            reporte = new Reporte();
            reporte.setObservacion(e.getMessage());
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }       
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return reporte;
    }
    
    @Override
    public Reporte generarReporteRespuestaDetalles(ParamConsulta paramConsulta) throws Exception {
        Reporte reporte = new Reporte();
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        StringBuilder builderCadena = new StringBuilder();
        int cantidadRegistros = 0;
        try {
            String strQuery = "SELECT cmf.nit, cmf.ips, cmf.fecha_radicacion, cmf.numero_facturado, cmf.numero_radicado, "
                    + " cmf.valor_factura, cmd.mae_tipo_documento_codigo, cmd.mae_tipo_documento_valor, cmd.nombre_completo_afiliado,"
                    + " cmd.valor_facturado as valor_facturado_servicio, cmd.observacion as observacion_servicio, "
                    + " cmf.valor_pendiente_actual as valor_pendiente_factura, "
                    + " cmr.tipo_respuesta, cmd.consecutivo_item, cmr.id as id_respuesta_glosa, "
                    + " cmd.ma_servicio_codigo , cmd.ma_servicio_valor, "
                    + " cmrd.valor_pagado_eps as valor_aceptado_eps_servicio, "
                    + " cmrd.valor_aceptado_ips as valor_aceptado_ips_servicio, "
                    + " cmd.valor_pendiente_actual as valor_pendiente_actual_servicio, "
                    + " cmrd.observacion as observacion_respuesta_servicio, "
                    + " cmrd.fecha_hora_crea as fecha_respuesta_detalle"
                    + " FROM cm_facturas cmf  INNER JOIN cm_glosa_respuestas cmr ON "
                    + " cmr.cm_facturas_id = cmf.id INNER JOIN cm_glosa_respuesta_detalles cmrd ON "
                    + " cmrd.cm_glosa_respuestas_id = cmr.id "
                    + " INNER JOIN cm_detalles cmd ON cmd.id = cmrd.cm_detalles_id "
                    + " where cmf.id > 0 " + RESTRICCION_FACTURA_ANULADA;           
            dbConnection = this.getConnection();
            
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {

                String fechaInicial = sdf.format(paramConsulta.getParametroConsulta1());
                String fechaFinal = sdf.format(paramConsulta.getParametroConsulta2());
                strQuery += " AND (cmf.fecha_radicacion >= '" + fechaInicial + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO + "') AND ( cmf.fecha_radicacion < '" + fechaFinal + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "') ";

                if(paramConsulta.getParametroConsulta3() != null){
                    strQuery += " AND cmf.nit like '%" + paramConsulta.getParametroConsulta3() +"%' ";
                }
                
                if(paramConsulta.getParametroConsulta4() != null){
                    strQuery += " AND cmf.ips like '%"+ paramConsulta.getParametroConsulta4() +"%' ";
                }
                
                 strQuery += " order by cmf.numero_radicado asc";
                
                preparedStatement = dbConnection.prepareStatement(strQuery);
                resultSet = preparedStatement.executeQuery();
                
                HashMap<String,String> estados = new HashMap<>();
                estados.put(String.valueOf(CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA), CmGlosaRespuesta.getTipoRespuestaStr(CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA));
                estados.put(String.valueOf(CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION),CmGlosaRespuesta.getTipoRespuestaStr(CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION));
                estados.put(String.valueOf(CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION) ,CmGlosaRespuesta.getTipoRespuestaStr(CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION)); 
                
                while (resultSet.next()) {
                    
                    String valorFactura = resultSet.getString("valor_factura") == null ?
                                          "" : resultSet.getString("valor_factura") ;
                            valorFactura = valorFactura.replace(SEPARADOS_PUNTO, SEPARADOS_COMA); 
                    String codigo = resultSet.getString("mae_tipo_documento_codigo") == null ?
                                             "" : resultSet.getString("mae_tipo_documento_codigo") ;
                    String servicio = resultSet.getString("mae_tipo_documento_valor") == null ?
                                             "" : resultSet.getString("mae_tipo_documento_valor") ;
                    String valorFacturadoServicio = resultSet.getString("valor_facturado_servicio") == null ?
                                          "" : resultSet.getString("valor_facturado_servicio") ;
                            valorFacturadoServicio = valorFacturadoServicio.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                    String valorPendienteActualServicio = resultSet.getString("valor_pendiente_actual_servicio") == null ? 
                                              "" : resultSet.getString("valor_pendiente_actual_servicio");
                            valorPendienteActualServicio = valorPendienteActualServicio.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                    String tipoRespuesta = resultSet.getString("tipo_respuesta") == null ? 
                                                   "" : resultSet.getString("tipo_respuesta");
                            tipoRespuesta = estados.get(tipoRespuesta) != null ? estados.get(tipoRespuesta): " "; 
                    String valorPendienteFactura = resultSet.getString("valor_pendiente_factura") == null ? 
                                                   "" : resultSet.getString("valor_pendiente_factura");
                            valorPendienteFactura = valorPendienteFactura.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                    String valorAceptadoEps = resultSet.getString("valor_aceptado_eps_servicio") == null ? 
                                              "" : resultSet.getString("valor_aceptado_eps_servicio");
                            valorAceptadoEps = valorAceptadoEps.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                    String valorAceptadoIps = resultSet.getString("valor_aceptado_ips_servicio") == null ? 
                                              "" : resultSet.getString("valor_aceptado_ips_servicio");
                            valorAceptadoIps = valorAceptadoIps.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                                                       
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nit")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ips")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_radicacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_facturado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_radicado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("id_respuesta_glosa"));  
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("consecutivo_item"));     
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorFactura);     
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(codigo);
                    builderCadena.append(SEPARADOR); 
                    builderCadena.append(servicio);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nombre_completo_afiliado")));
                    builderCadena.append(SEPARADOR);            
                    builderCadena.append(valorFacturadoServicio);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorPendienteActualServicio);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("observacion_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorPendienteFactura);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(tipoRespuesta);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ma_servicio_codigo")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ma_servicio_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorAceptadoEps);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorAceptadoIps);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("observacion_respuesta_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append("Si");
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_respuesta_detalle"));
                    builderCadena.append("\n");
                    cantidadRegistros++;
                }
            }
                
            if(builderCadena.length()> 0){
              StringBuilder builderFinal = new StringBuilder(); 
              builderFinal.append(ENCABEZADO_RESPUESTA_DETALLES);
              builderFinal.append(builderCadena);
              reporte.setContenidoEnString(builderFinal.toString());
              reporte.setCantidadRegistros(cantidadRegistros);
              builderCadena.setLength(0);
            }
        } catch (NoResultException e) {
            reporte = new Reporte();
            reporte.setObservacion(e.getMessage());
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {     
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return reporte;
    }
    
    @Override
    public Reporte generarReporteDetallesSinRespuestas(ParamConsulta paramConsulta) throws Exception {
        Reporte reporte = new Reporte();
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        
        StringBuilder builderCadena = new StringBuilder();
        int cantidadRegistros = 0;
        
        try {
            
            String strQuery = "SELECT cmd.id as idDetalle,cmf.nit, cmf.ips, cmf.fecha_radicacion, cmf.numero_facturado, cmf.numero_radicado, "
                    + " cmf.valor_factura, cmd.mae_tipo_documento_codigo, cmd.mae_tipo_documento_valor, cmd.nombre_completo_afiliado,"
                    + " cmd.valor_facturado as valor_facturado_servicio, cmd.observacion as observacion_servicio, "
                    + " cmd.consecutivo_item , cmd.ma_servicio_codigo , cmd.ma_servicio_valor, "
                    + " cmd.observacion as observacion_servicio,  cmd.valor_pendiente_actual, cmd.motivo_glosa, "
                    + SUBCONSULTA_HAY_GLOSA_AUTOMATICA
                    + " FROM cm_facturas cmf  INNER JOIN cm_detalles cmd ON "
                    + " cmd.cm_facturas_id = cmf.id LEFT JOIN cm_glosa_respuestas cmr ON "
                    + " cmr.cm_facturas_id = cmf.id  "
                    + " where cmf.id > 0 AND cmr.cm_facturas_id is null and cmd.valor_pendiente_actual > 0 " + RESTRICCION_FACTURA_ANULADA;           
            dbConnection = this.getConnection();
            
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {

                String fechaInicial = sdf.format(paramConsulta.getParametroConsulta1());
                String fechaFinal = sdf.format(paramConsulta.getParametroConsulta2());
                strQuery += " AND (cmf.fecha_radicacion >= '" + fechaInicial + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO + "') AND ( cmf.fecha_radicacion < '" + fechaFinal + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "') ";

                if(paramConsulta.getParametroConsulta3() != null){
                    strQuery += " AND cmf.nit like '%" + paramConsulta.getParametroConsulta3() +"%' ";
                }
                
                if(paramConsulta.getParametroConsulta4() != null){
                    strQuery += " AND cmf.ips like '%"+ paramConsulta.getParametroConsulta4() +"%' ";
                }
                
                strQuery += " order by cmf.numero_radicado asc";
                
                preparedStatement = dbConnection.prepareStatement(strQuery);
                resultSet = preparedStatement.executeQuery();
               
                while (resultSet.next()) {
                    
                    String valorPendinteActual = resultSet.getString("valor_pendiente_actual") == null ?
                                                "" : resultSet.getString("valor_pendiente_actual") ;
                            valorPendinteActual = valorPendinteActual.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                    String tipologiaMotivsoGlosaStr =  resultSet.getInt("tipo_glosa") == CmAuditoriaMotivoGlosa.TIPOLOGIA_GLOSA_AUTOMATICA ?
                                                      "Si": "No";
                     
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nit")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ips")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_radicacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_facturado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_radicado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("consecutivo_item"));     
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("mae_tipo_documento_codigo"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("mae_tipo_documento_valor"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nombre_completo_afiliado")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ma_servicio_codigo")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ma_servicio_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("observacion_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("motivo_glosa")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(tipologiaMotivsoGlosaStr);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorPendinteActual);
                    builderCadena.append("\n");
                    cantidadRegistros++;
                }
            }
                
            if(builderCadena.length()> 0){
              StringBuilder builderFinal = new StringBuilder(); 
              builderFinal.append(ENCABEZADO_DETALLES_SIN_RESPUESTA);
              builderFinal.append(builderCadena);
              reporte.setContenidoEnString(builderFinal.toString());
              reporte.setCantidadRegistros(cantidadRegistros);
              builderCadena.setLength(0);
            }
        } catch (NoResultException e) {
            reporte = new Reporte();
            reporte.setObservacion(e.getMessage());
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return reporte;
    }
    
    @Override
    public Reporte generarReporteRespuestasPorDetalle(ParamConsulta paramConsulta) throws Exception {
        Reporte reporte = new Reporte();
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        StringBuilder builderCadena = new StringBuilder();
        int cantidadRegistros = 0;
        try {
            String strQuery = " SELECT cmd.id as idDetalle, cmf.nit, cmf.ips , cmf.fecha_radicacion, cmf.numero_facturado, cmf.numero_radicado,"
                    + " cmd.mae_tipo_documento_codigo , cmd.mae_tipo_documento_valor , cmd.nombre_completo_afiliado, "
                    + " cmd.valor_pendiente_actual as valor_pendiente_actual_servicio, cmd.motivo_glosa, "
                    + " cmd.ma_servicio_codigo, cmd.ma_servicio_valor, cmd.observacion as observacion_servicio,"
                    + " group_concat(concat('', '',cmr.tipo_respuesta, '') separator '|') AS tipo_respuesta, "
                    + " group_concat(concat('','',cmrd.valor_pagado_eps ,'') separator '|') as valores_EPS ,"
                    + " group_concat(concat('','',cmrd.valor_aceptado_ips ,'') separator '|') as valores_IPS,"
                    + " group_concat(concat('','',cmrd.fecha_hora_crea ,'') separator '|') as fechas_creacion_respuesta,"
                    + " group_concat(concat('','',COALESCE(cmrd.porcentaje_pagado_eps, '0') ,'') separator '|') as porcenaje_EPS,"
                    + " group_concat(concat('','',COALESCE(cmrd.porcentaje_aceptado_ips, '0') ,'') separator '|') as porcenaje_IPS, "
                    + " group_concat(concat('','',cmrd.observacion ,'') separator '|') as observaciones_respuesta, "
                    + SUBCONSULTA_HAY_GLOSA_AUTOMATICA
                    + " FROM cm_facturas cmf  INNER JOIN cm_detalles cmd ON "
                    + " cmd.cm_facturas_id = cmf.id INNER JOIN cm_glosa_respuesta_detalles cmrd  ON "
                    + " cmrd.cm_detalles_id = cmd.id  INNER JOIN cm_glosa_respuestas cmr "
                    + " on cmr.id = cmrd.cm_glosa_respuestas_id "
                    + " where cmf.id > 0 " + RESTRICCION_FACTURA_ANULADA;           
            dbConnection = this.getConnection();
           
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {

                String fechaInicial = sdf.format(paramConsulta.getParametroConsulta1());
                String fechaFinal = sdf.format(paramConsulta.getParametroConsulta2());
                strQuery += " AND (cmf.fecha_radicacion >= '" + fechaInicial + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO + "') AND ( cmf.fecha_radicacion < '" + fechaFinal + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE+ "') ";

                if(paramConsulta.getParametroConsulta3() != null){
                    strQuery += " AND cmf.nit like '%" + paramConsulta.getParametroConsulta3() +"%' ";
                }
                
                if(paramConsulta.getParametroConsulta4() != null){
                    strQuery += " AND cmf.ips like '%"+ paramConsulta.getParametroConsulta4() +"%' ";
                }
                
                strQuery += " group by cmrd.cm_detalles_id order by cmf.numero_facturado ";
                
                preparedStatement = dbConnection.prepareStatement(strQuery);
                resultSet = preparedStatement.executeQuery();

                HashMap<String,String> estados = new HashMap<>();
                estados.put(String.valueOf(CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA), CmGlosaRespuesta.getTipoRespuestaStr(CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA));
                estados.put(String.valueOf(CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION),CmGlosaRespuesta.getTipoRespuestaStr(CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION));
                estados.put(String.valueOf(CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION) ,CmGlosaRespuesta.getTipoRespuestaStr(CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION)); 
              
                while (resultSet.next()) {
                    
                    String[] fechasRepuestas = filtroValoresRetorno(resultSet.getString("fechas_creacion_respuesta"));                     
                    String[] valoresEps = filtroValoresRetorno(resultSet.getString("valores_EPS"));
                    String[] valoresIps = filtroValoresRetorno(resultSet.getString("valores_IPS"));                   
                    String[] porcentajesEps = filtroValoresRetorno(resultSet.getString("porcenaje_EPS"));           
                    String[] porcentajesIps = filtroValoresRetorno(resultSet.getString("porcenaje_IPS"));
                    String[] observacionesRespueta = filtroValoresRetorno(resultSet.getString("observaciones_respuesta"));
                    String[] tipoRespuestas = filtroValoresRetorno(resultSet.getString("tipo_respuesta"));
                    
                    String tipoDocumento = resultSet.getString("mae_tipo_documento_codigo") == null ?
                                             "" : resultSet.getString("mae_tipo_documento_codigo") ;
                                        
                    String documento = resultSet.getString("mae_tipo_documento_valor") == null ?
                                             "" : resultSet.getString("mae_tipo_documento_valor") ;
                    String codigo = resultSet.getString("ma_servicio_codigo") == null ?
                                             "" : resultSet.getString("ma_servicio_codigo") ; 
                    String servicio = resultSet.getString("ma_servicio_valor") == null ?
                                             "" : resultSet.getString("ma_servicio_valor") ;
                    String valorPendinteActual = resultSet.getString("valor_pendiente_actual_servicio") == null ?
                                                "" : resultSet.getString("valor_pendiente_actual_servicio") ;
                            valorPendinteActual = valorPendinteActual.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);       
                    
                    String tipologiaMotivsoGlosaStr =  resultSet.getInt("tipo_glosa") == CmAuditoriaMotivoGlosa.TIPOLOGIA_GLOSA_AUTOMATICA ?
                                                      "Si": "No";
                    
                    builderCadena.append(resultSet.getString("nit"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ips")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_radicacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_facturado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_radicado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(tipoDocumento);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(documento);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nombre_completo_afiliado")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(codigo));  
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(servicio));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("observacion_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("motivo_glosa")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(tipologiaMotivsoGlosaStr);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorPendinteActual);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(fechasRepuestas[0]);
                    builderCadena.append(SEPARADOR);
                    String tipoRespuesta = estados.get(tipoRespuestas[0]) != null ? estados.get(tipoRespuestas[0]): " "; 
                    builderCadena.append(tipoRespuesta);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valoresEps[0].replace(SEPARADOS_PUNTO, SEPARADOS_COMA));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(porcentajesEps[0]);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valoresIps[0].replace(SEPARADOS_PUNTO, SEPARADOS_COMA));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(porcentajesIps[0]);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(observacionesRespueta[0]));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(fechasRepuestas[1]);
                    builderCadena.append(SEPARADOR);
                    tipoRespuesta = estados.get(tipoRespuestas[1]) != null ? estados.get(tipoRespuestas[1]): " "; 
                    builderCadena.append(tipoRespuesta);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valoresEps[1].replace(SEPARADOS_PUNTO, SEPARADOS_COMA));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(porcentajesEps[1]);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valoresIps[1].replace(SEPARADOS_PUNTO, SEPARADOS_COMA));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(porcentajesIps[1]);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(observacionesRespueta[1]));    
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(fechasRepuestas[2]);
                    builderCadena.append(SEPARADOR);
                    tipoRespuesta = estados.get(tipoRespuestas[2]) != null ? estados.get(tipoRespuestas[2]): " "; 
                    builderCadena.append(tipoRespuesta);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valoresEps[2].replace(SEPARADOS_PUNTO, SEPARADOS_COMA));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(porcentajesEps[2]);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valoresIps[2].replace(SEPARADOS_PUNTO, SEPARADOS_COMA));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(porcentajesIps[2]);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(observacionesRespueta[2]));  
                    builderCadena.append("\n");
                    cantidadRegistros++;
                }
            }
                
            if(builderCadena.length()> 0){
              StringBuilder builderFinal = new StringBuilder(); 
              builderFinal.append(ENCABEZADO_REPUESTAS_POR_DETALLE);
              builderFinal.append(builderCadena);
              reporte.setContenidoEnString(builderFinal.toString());
              reporte.setCantidadRegistros(cantidadRegistros);
              builderCadena.setLength(0);
            }
        } catch (NoResultException e) {
            reporte = new Reporte();
            reporte.setObservacion(e.getMessage());
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return reporte;
    }
     
    @Override
    public Reporte generarReporteAuditoria(ParamConsulta paramConsulta) throws Exception {
         Reporte reporte = new Reporte();
         Connection dbConnection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet;
         StringBuilder builderCadena = new StringBuilder();
         int cantidadRegistros = 0;
         try {
             String strQuery = "SELECT "
                     + "    cmf.cm_grupos_id,"
                     + "    CASE cmf.tipo_auditoria"
                     + "        WHEN 0 THEN 'Sin auditoria'"
                     + "        WHEN 1 THEN 'En Pertinencia Tecnica'"
                     + "        WHEN 2 THEN 'En Pertinencia Medica'"
                     + "        WHEN 3 THEN 'En Devolucion Tecnica'"
                     + "        WHEN 4 THEN 'En Devolucion Medica'"
                     + "        WHEN 5 THEN 'En Cierre Auditoria'"
                     + "        WHEN 6 THEN 'Auditoria Tecnica'"
                     + "        WHEN 7 THEN 'Auditoria Medica'"
                     + "        WHEN 8 THEN 'Auditoria Para Pago'"
                     + "    END AS tipo_auditoria,"
                     + "    CASE cmf.estado_factura"
                     + "        WHEN 0 THEN 'Radicado'"
                     + "        WHEN 1 THEN 'En auditoria'"
                     + "        WHEN 2 THEN 'Auditada-Glosada'"
                     + "        WHEN 4 THEN 'Conciliada'"
                     + "        WHEN 5 THEN 'Devuelta'"
                     + "        WHEN 6 THEN 'Devuelta - Espera SAP (DEV)'"
                     + "        WHEN 7 THEN 'Auditada - Espera SAP (M2)'"
                     + "        WHEN 8 THEN 'Conciliada - Espera SAP (M3)'"
                     + "        WHEN 9 THEN 'Auditada'"
                     + "        WHEN 10 THEN 'Radicado - Espera SAP (M1)'"
                     + "        WHEN 11 THEN 'Radicado - Espera SAP (M1) - ERROR'"
                     + "        WHEN 12 THEN 'Anulada'"
                     + "    END AS estado_factura,"
                     + "    cmf.numero_radicado,"
                     + "    cmf.nit,"
                     + "    cmf.ips,"
                     + "    cmf.numero_facturado,"
                     + "    cmf.mae_tipo_contrato_valor,"
                     + "    cmf.fecha_radicacion,"
                     + "    cmf.valor_factura,"
                     + "    cmf.gn_usuarios_lider_id,"
                     + "    lider.nombre as nombre_lider,"
                     + "    cmf.gn_usuarios_tecnico_id,"
                     + "    tecnico.nombre as nombre_tecnico,"
                     + "    cmf.gn_usuarios_medico_id,"
                     + "    medico.nombre as nombre_medico,"
                     + "    cmf.gn_usuarios_gestiona_id,"
                     + "    gestiona.nombre as nombre_gestiona ,"
                     + "    cmf.valor_inicial_glosa, "
                     + "    cmf.valor_pendiente_actual, cmf.valor_copago,"
                     + "    auditaRips.usuario_audita, "
                     + "    devolucion.fecha_devolucion, "
                     + "    devolucion.usuario_crea as usuario_devolucion,"
                     + "    cierre.fecha_hora_crea as fecha_cierre, "
                     + "    (SELECT count( Distinct documento) FROM cm_detalles cmdI where cmdI.cm_facturas_id = cmf.id ) as cantidad_pacientes"
                     + " FROM cm_facturas cmf  "
                     + " LEFT JOIN gn_usuarios lider ON lider.id = cmf.gn_usuarios_lider_id "
                     + " LEFT JOIN gn_usuarios tecnico ON tecnico.id = cmf.gn_usuarios_tecnico_id " 
                     + " LEFT JOIN gn_usuarios medico ON medico.id = cmf.gn_usuarios_medico_id "
                     + " LEFT JOIN gn_usuarios gestiona ON gestiona.id = cmf.gn_usuarios_gestiona_id "
                     + " LEFT JOIN cm_rips_cargas auditaRips ON auditaRips.id = cmf.cm_rips_cargas_id "
                     + " LEFT JOIN cm_auditoria_devoluciones devolucion ON devolucion.cm_facturas_id = cmf.id "
                     + " LEFT JOIN cm_auditoria_cierres cierre ON cierre.cm_facturas_id = cmf.id"
                     + " where cmf.id > 0 " + RESTRICCION_FACTURA_ANULADA;
             
              dbConnection = this.getConnection();
             
              if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {

                String fechaInicial = sdf.format(paramConsulta.getParametroConsulta1());
                String fechaFinal = sdf.format(paramConsulta.getParametroConsulta2());
                strQuery += " AND (cmf.fecha_radicacion >= '" + fechaInicial + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO +
                          "') AND ( cmf.fecha_radicacion < '" + fechaFinal + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "') ";

                if(paramConsulta.getParametroConsulta3() != null){
                    strQuery += " AND cmf.nit like '%" + paramConsulta.getParametroConsulta3() +"%' ";
                }
                
                if(paramConsulta.getParametroConsulta4() != null){
                    strQuery += " AND cmf.ips like '%"+ paramConsulta.getParametroConsulta4() +"%' ";
                }
                
                strQuery += " order by cmf.numero_radicado DESC";
                
                preparedStatement = dbConnection.prepareStatement(strQuery);
                resultSet = preparedStatement.executeQuery();
               
                while (resultSet.next()) {   
                    
                    String valorFactura = resultSet.getString("valor_factura") == null
                            ? "" : resultSet.getString("valor_factura");
                            valorFactura = valorFactura.replace(SEPARADOS_PUNTO, SEPARADOS_COMA); 
                    String valorCopago = resultSet.getString("valor_copago") == null
                            ? "" : resultSet.getString("valor_copago");
                            valorCopago = valorCopago.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                    String valorInicialGlosa = resultSet.getString("valor_inicial_glosa") == null
                            ? "" : resultSet.getString("valor_inicial_glosa");
                            valorInicialGlosa = valorInicialGlosa.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);        
                    String valorPendienteActual = resultSet.getString("valor_pendiente_actual") == null
                            ? "" : resultSet.getString("valor_pendiente_actual");
                            valorPendienteActual = valorPendienteActual.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                    
                    builderCadena.append(resultSet.getString("cm_grupos_id"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("tipo_auditoria"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("estado_factura"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_radicado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("nit"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ips")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_facturado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("mae_tipo_contrato_valor"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_radicacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("cantidad_pacientes"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorFactura);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorCopago);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorInicialGlosa);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorPendienteActual);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nombre_lider")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nombre_tecnico")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nombre_medico")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nombre_gestiona")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("usuario_audita")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("fecha_devolucion")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("usuario_devolucion")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("fecha_cierre")));
                    builderCadena.append("\n");
                    cantidadRegistros++;
                }
            }
                
            if(builderCadena.length()> 0){
              StringBuilder builderFinal = new StringBuilder(); 
              builderFinal.append(ENCABEZADO_AUDITORIA);
              builderFinal.append(builderCadena);
              reporte.setContenidoEnString(builderFinal.toString());
              reporte.setCantidadRegistros(cantidadRegistros);
              builderCadena.setLength(0);
            }
             
         } catch (NoResultException e) {
             reporte = new Reporte();
             reporte.setObservacion(e.getMessage());
         } catch (Exception e) {
             Exception(CONSULTAR_TODOS, e);
         } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
             if (dbConnection != null) {
                 dbConnection.close();
             }
         }
         return reporte;
    }
    
    @Override
    public Reporte generarReporteGeneralCuentaMedica(ParamConsulta paramConsulta) throws Exception {
         Reporte reporte = new Reporte();
         Connection dbConnection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet;
         StringBuilder builderCadena = new StringBuilder();
         int cantidadRegistros = 0;
         try {
             String strQuery = "SELECT cmf.numero_radicado , cmf.nit, cmf.ips, cntprestador.naturaleza_juridica ,"
                     + " CASE WHEN "
                     + "     LENGTH(cmf.numero_contrato) > 3 THEN cmf.numero_contrato "
                     + "    ELSE  cntcontrato.contrato END  AS numero_contrato,"
                     + " cmf.numero_facturado, " +
                                " cmf.valor_factura, cmf.valor_inicial_glosa , cmf.valor_pendiente_actual , cmf.valor_pagado_factura, " +
                                " cmf.valor_copago, cmf.mae_tipo_contrato_valor, " +
                                " cmf.fecha_radicacion, cmf.fecha_hora_crea as fecha_proceso_radicacion, cmf.fecha_prestacion," 
                              + "    CASE cmf.estado_factura"
                              + "        WHEN 0 THEN 'Radicado'"
                              + "        WHEN 1 THEN 'En auditoria'"
                              + "        WHEN 2 THEN 'Auditada-Glosada'"
                              + "        WHEN 4 THEN 'Conciliada'"
                              + "        WHEN 5 THEN 'Devuelta'"
                              + "        WHEN 6 THEN 'Devuelta - Espera SAP (DEV)'"
                              + "        WHEN 7 THEN 'Auditada - Espera SAP (M2)'"
                              + "        WHEN 8 THEN 'Conciliada - Espera SAP (M3)'"
                              + "        WHEN 9 THEN 'Auditada'"
                              + "        WHEN 10 THEN 'Radicado - Espera SAP (M1)'"
                              + "        WHEN 11 THEN 'Radicado - Espera SAP (M1) - ERROR'"
                              + "        WHEN 12 THEN 'Anulada'"
                              + "    END AS estado_factura, "
                              + " cmf.mae_regimen_valor, cmf.cm_rips_cargas_id,"
                              + " cmrips.usuario_crea as login_radicacion, cmrips.usuario_audita as usuario_radicacion"
                              + " FROM cm_facturas cmf "
                              + " LEFT JOIN cm_rips_cargas cmrips On cmrips.id = cmf.cm_rips_cargas_id " 
                              + " LEFT JOIN cnt_contratos cntcontrato ON cntcontrato.id = cmrips.cnt_contratos_id"
                              + " LEFT JOIN cnt_prestadores cntprestador ON cntprestador.id  = cmf.cnt_prestadores_id"
                              + " where cmf.id > 0 " + RESTRICCION_FACTURA_ANULADA;
             
              dbConnection = this.getConnection();
             
              if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {

                String fechaInicial = sdf.format(paramConsulta.getParametroConsulta1());
                String fechaFinal = sdf.format(paramConsulta.getParametroConsulta2());
                strQuery += " AND ( cmf.fecha_radicacion >= '" + fechaInicial + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO
                        + "') AND ( cmf.fecha_radicacion < '" + fechaFinal   + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "') ";

                if(paramConsulta.getParametroConsulta3() != null){
                    strQuery += " AND cmf.nit like '%" + paramConsulta.getParametroConsulta3() +"%' ";
                }
                
                if(paramConsulta.getParametroConsulta4() != null){
                    strQuery += " AND cmf.ips like '%"+ paramConsulta.getParametroConsulta4() +"%' ";
                }
                
                strQuery += " order by cmf.fecha_radicacion DESC, cmf.cm_rips_cargas_id DESC ";
                
                preparedStatement = dbConnection.prepareStatement(strQuery);
                resultSet = preparedStatement.executeQuery();
               
                while (resultSet.next()) {   
                    builderCadena.append(resultSet.getString("numero_radicado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("nit"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ips")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea("naturaleza_juridica"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("numero_contrato")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_facturado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_factura")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_inicial_glosa")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_pendiente_actual")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_pagado_factura")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_copago")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_tipo_contrato_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_radicacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_proceso_radicacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_prestacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("estado_factura")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_regimen_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("cm_rips_cargas_id")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("login_radicacion")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("usuario_radicacion")));
                    builderCadena.append("\n");
                    cantidadRegistros++;
                }
            }
                
            if(builderCadena.length()> 0){
              StringBuilder builderFinal = new StringBuilder(); 
              builderFinal.append(ENCABEZADO_INFORME_GENERAL_CM);
              builderFinal.append(builderCadena);
              reporte.setContenidoEnString(builderFinal.toString());
              reporte.setCantidadRegistros(cantidadRegistros);
              builderCadena.setLength(0);
            }
             
         } catch (NoResultException e) {
             reporte = new Reporte();
             reporte.setObservacion(e.getMessage());
         } catch (Exception e) {
             Exception(CONSULTAR_TODOS, e);
         } finally {
             if (preparedStatement != null) {
                 preparedStatement.close();
             }
             if (dbConnection != null) {
                 dbConnection.close();
             }
         }
         return reporte;
    }
    
    @Override
    public Reporte generarReporteFacturasConciliadas(ParamConsulta paramConsulta) throws Exception {
         Reporte reporte = new Reporte();
         Connection dbConnection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet;
         StringBuilder builderCadena = new StringBuilder();
         int cantidadRegistros = 0;
         try {
             String strQuery = "SELECT cmf.numero_radicado , cmf.nit, cmf.ips, cntprestador.naturaleza_juridica ,"
                     + " CASE WHEN "
                     + "     LENGTH(cmf.numero_contrato) > 3 THEN cmf.numero_contrato "
                     + "    ELSE  cntcontrato.contrato END  AS numero_contrato,"
                     + " cmf.numero_facturado, " +
                                " cmf.valor_factura, cmf.valor_inicial_glosa , cmf.valor_pendiente_actual , cmf.valor_pagado_factura, " +
                                " cmf.valor_copago, cmf.mae_tipo_contrato_valor, " +
                                " cmf.fecha_radicacion, cmf.fecha_hora_crea as fecha_proceso_radicacion, cmf.fecha_prestacion," 
                              + "    CASE cmf.estado_factura"
                              + "        WHEN 0 THEN 'Radicado'"
                              + "        WHEN 1 THEN 'En auditoria'"
                              + "        WHEN 2 THEN 'Auditada-Glosada'"
                              + "        WHEN 4 THEN 'Conciliada'"
                              + "        WHEN 5 THEN 'Devuelta'"
                              + "        WHEN 6 THEN 'Devuelta - Espera SAP (DEV)'"
                              + "        WHEN 7 THEN 'Auditada - Espera SAP (M2)'"
                              + "        WHEN 8 THEN 'Conciliada - Espera SAP (M3)'"
                              + "        WHEN 9 THEN 'Auditada'"
                              + "        WHEN 10 THEN 'Radicado - Espera SAP (M1)'"
                              + "        WHEN 11 THEN 'Radicado - Espera SAP (M1) - ERROR'"
                              + "        WHEN 12 THEN 'Anulada'"
                              + "    END AS estado_factura, "
                              + " cmf.mae_regimen_valor, cmf.cm_rips_cargas_id "
                              + " FROM cm_facturas cmf "
                              + " LEFT JOIN cm_rips_cargas cmrips On cmrips.id = cmf.cm_rips_cargas_id " 
                              + " LEFT JOIN cnt_contratos cntcontrato ON cntcontrato.id = cmrips.cnt_contratos_id"
                              + " LEFT JOIN cnt_prestadores cntprestador ON cntprestador.id  = cmf.cnt_prestadores_id"
                              + " where cmf.id > 0 AND cmf.estado_factura = " + CmFactura.ESTADO_FACTURA_CONCILIADA+ 
                                            " " +RESTRICCION_FACTURA_ANULADA;
             
              dbConnection = this.getConnection();
             
              if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {

                String fechaInicial = sdf.format(paramConsulta.getParametroConsulta1());
                String fechaFinal = sdf.format(paramConsulta.getParametroConsulta2());
                strQuery += " AND (cmf.fecha_radicacion >= '" + fechaInicial + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO 
                        + "') AND ( cmf.fecha_radicacion < '" + fechaFinal + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "') ";

                if(paramConsulta.getParametroConsulta3() != null){
                    strQuery += " AND cmf.nit like '%" + paramConsulta.getParametroConsulta3() +"%' ";
                }
                
                if(paramConsulta.getParametroConsulta4() != null){
                    strQuery += " AND cmf.ips like '%"+ paramConsulta.getParametroConsulta4() +"%' ";
                }
                
                strQuery += " order by cmf.fecha_radicacion DESC, cmf.cm_rips_cargas_id DESC ";
                
                preparedStatement = dbConnection.prepareStatement(strQuery);
                resultSet = preparedStatement.executeQuery();
               
                while (resultSet.next()) {   
                    builderCadena.append(resultSet.getString("numero_radicado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("nit"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ips")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea("naturaleza_juridica"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("numero_contrato")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_facturado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_factura")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_inicial_glosa")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_pendiente_actual")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_pagado_factura")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_copago")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_tipo_contrato_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_radicacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_proceso_radicacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_prestacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("estado_factura")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_regimen_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("cm_rips_cargas_id")));
                    builderCadena.append("\n");
                    cantidadRegistros++;
                }
            }
                
            if(builderCadena.length()> 0){
              StringBuilder builderFinal = new StringBuilder(); 
              builderFinal.append(ENCABEZADO_INFORME_FACTURAS_CONCILIADAS);
              builderFinal.append(builderCadena);
              reporte.setContenidoEnString(builderFinal.toString());
              reporte.setCantidadRegistros(cantidadRegistros);
              builderCadena.setLength(0);
            }
             
         } catch (NoResultException e) {
             reporte = new Reporte();
             reporte.setObservacion(e.getMessage());
         } catch (Exception e) {
             Exception(CONSULTAR_TODOS, e);
         } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
             if (dbConnection != null) {
                 dbConnection.close();
             }
         }
         return reporte;
    }
    
    @Override
    public Reporte generarReporteFacturasRechazadas(ParamConsulta paramConsulta) throws java.lang.Exception {
         Reporte reporte = new Reporte();
         Connection dbConnection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet;
         StringBuilder builderCadena = new StringBuilder();
         int cantidadRegistros = 0;
         try {
             String strQuery = "SELECT " +
                            "    crips.id, " +
                            "    cripsaf.razon_social, " +
                            "    cripsaf.nit, " +
                            "    crips.fecha_hora_crea, " +
                            "    cripsaf.numero_factura, " +
                            "    cripsaf.valor_a_pagar, " +
                            "    cripsaf.valor_descuento, " +
                            "    cripsaf.codigo_eps, " +
                            "    cripsaf.nombre_administradora, crips.observacion_rechazo " +
                            " FROM" +
                            "    cm_rips_cargas crips " +
                            "        INNER JOIN" +
                            "    cm_rips_af_facturas cripsaf ON cripsaf.cm_rips_cargas_id = crips.id " +
                            " WHERE crips.id > 0 AND crips.estado = " +CmRipsCarga.ESTADO_RECHAZADO;
             
              dbConnection = this.getConnection();
             
              if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {

                String fechaInicial = sdf.format(paramConsulta.getParametroConsulta1());
                String fechaFinal = sdf.format(paramConsulta.getParametroConsulta2());
                strQuery += " AND ( crips.fecha_hora_crea >= '" + fechaInicial  + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO +
                          "') AND ( crips.fecha_hora_crea < '"  + fechaFinal + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "') ";

                if(paramConsulta.getParametroConsulta3() != null){
                    strQuery += " AND cripsaf.nit like '%" + paramConsulta.getParametroConsulta3() +"%' ";
                }
                
                if(paramConsulta.getParametroConsulta4() != null){
                    strQuery += " AND cripsaf.ips like '%"+ paramConsulta.getParametroConsulta4() +"%' ";
                }
                
                strQuery += " order by crips.fecha_hora_crea DESC ";
                
                preparedStatement = dbConnection.prepareStatement(strQuery);
                resultSet = preparedStatement.executeQuery();
               
                while (resultSet.next()) {   
                    
                    String valorPagar = resultSet.getString("valor_a_pagar") == null
                            ? "" : resultSet.getString("valor_a_pagar");
                            valorPagar = valorPagar.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                    String valorDescuento = resultSet.getString("valor_descuento") == null
                            ? "" : resultSet.getString("valor_descuento");
                            valorDescuento = valorDescuento.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
                    
                    builderCadena.append(resultSet.getString("id"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_factura"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("razon_social")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nit")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("codigo_eps"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nombre_administradora")));
                    builderCadena.append(SEPARADOR);  
                    builderCadena.append(valorPagar);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(valorDescuento);
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("observacion_rechazo")));
                    builderCadena.append(SEPARADOR); 
                    builderCadena.append(resultSet.getString("fecha_hora_crea"));
                    builderCadena.append("\n");
                    cantidadRegistros++;
                }
            }
                
            if(builderCadena.length()> 0){
              StringBuilder builderFinal = new StringBuilder(); 
              builderFinal.append(ENCABEZADO_INFORME_FACTURAS_RECHAZADAS);
              builderFinal.append(builderCadena);
              reporte.setContenidoEnString(builderFinal.toString());
              reporte.setCantidadRegistros(cantidadRegistros);
              builderCadena.setLength(0);
            }
             
         } catch (NoResultException e) {
             reporte = new Reporte();
             reporte.setObservacion(e.getMessage());
         } catch (Exception e) {
             Exception(CONSULTAR_TODOS, e);
         } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
         }
         return reporte;
    }

    @Override
    public Reporte generarReporteDetallesDescuentoCapita(ParamConsulta paramConsulta) throws java.lang.Exception {
        Reporte reporte = new Reporte();
         Connection dbConnection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet;
         StringBuilder builderCadena = new StringBuilder();
         int cantidadRegistros = 0;
         try {
             String strQuery = " SELECT "
                     + "    cmf.id as identificacion_factura,"
                     + "    cmf.numero_radicado,"
                     + "    cmf.nit, cmf.ips,"
                     + "    CASE "
                     + "        WHEN LENGTH(cmf.numero_contrato) > 3 THEN cmf.numero_contrato "
                     + "        ELSE cntcontrato.contrato "
                     + "    END AS numero_contrato, "
                     + "    cmf.numero_facturado, "
                     + "    cmf.valor_factura, "
                     + "    cmd.mae_tipo_documento_valor, "
                     + "    cmd.documento, "
                     + "    cmd.nombre_completo_afiliado, "
                     + "    cmd.ma_servicio_valor, "
                     + "    cmd.cantidad, "
                     + "    cmd.valor_facturado as valor_servicio, "
                     + "    cmd.valor_pagado as valor_pagado_servicio, "
                     + "    cmd.valor_pendiente_actual as valor_pendiente_servicio, "
                     + "    cmd.valor_copago as valor_copago_servicio, "
                     + "    cmf.mae_tipo_contrato_valor, "
                     + "    cmf.fecha_radicacion, "
                     + "    CASE cmf.estado_factura"
                     + "        WHEN 0 THEN 'Sin auditoria'"
                     + "        WHEN 1 THEN 'En auditoria'"
                     + "        WHEN 2 THEN 'Glosada'"
                     + "        WHEN 4 THEN 'Conciliada'"
                     + "        WHEN 5 THEN 'Devuelta'"
                     + "        WHEN 6 THEN 'Espera Sap para devolucion'"
                     + "        WHEN 7 THEN 'Espera Sap para cierre auditoria'"
                     + "        WHEN 8 THEN 'Espera Sap para respuesta/conciliacion'"
                     + "        WHEN 9 THEN 'Auditada exitosa'"
                     + "        WHEN 10 THEN 'Sin procesar'"
                     + "        WHEN 11 THEN 'Sin notificar'"
                     + "        WHEN 12 THEN 'Anulada'"
                     + "    END AS estado_factura,"
                     + "    cmf.mae_regimen_valor, cmcd.marcacion , cmcd.observacion AS observacion_capita_desc"
                     + " FROM "
                     + "    cm_facturas cmf INNER JOIN cm_detalles cmd ON cmd.cm_facturas_id = cmf.id "
                     + "        LEFT JOIN"
                     + "    cm_rips_cargas cmrips ON cmrips.id = cmf.cm_rips_cargas_id"
                     + "        LEFT JOIN"
                     + "    cnt_contratos cntcontrato ON cntcontrato.id = cmrips.cnt_contratos_id"
                     + "        INNER JOIN"
                     + "    cm_auditoria_capita_descuentos cmcd ON cmcd.cm_detalles_id = cmd.id"
                     + " WHERE cmf.id > 0 AND cmcd.marcacion = 1 " + RESTRICCION_FACTURA_ANULADA;
             
              dbConnection = this.getConnection();
             
              if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {

                String fechaInicial = sdf.format(paramConsulta.getParametroConsulta1());
                String fechaFinal = sdf.format(paramConsulta.getParametroConsulta2());
                strQuery += " AND ( cmf.fecha_radicacion >= '" + fechaInicial + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO +
                          "') AND ( cmf.fecha_radicacion < '" + fechaFinal + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "') ";

                if(paramConsulta.getParametroConsulta3() != null){
                    strQuery += " AND cmf.nit like '%" + paramConsulta.getParametroConsulta3() +"%' ";
                }
                
                if(paramConsulta.getParametroConsulta4() != null){
                    strQuery += " AND cmf.ips like '%"+ paramConsulta.getParametroConsulta4() +"%' ";
                }
                
                strQuery += " order by cmf.numero_radicado DESC";
                
                preparedStatement = dbConnection.prepareStatement(strQuery);
                resultSet = preparedStatement.executeQuery();
               
                while (resultSet.next()) {   
                    builderCadena.append(resultSet.getString("identificacion_factura"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_radicado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("nit"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ips")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("numero_contrato")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_facturado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_factura")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_tipo_documento_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("documento")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nombre_completo_afiliado")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ma_servicio_valor"))); 
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("cantidad")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_pagado_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_pendiente_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_copago_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_tipo_contrato_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_radicacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("estado_factura")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_regimen_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("marcacion")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("observacion_capita_desc")));
                    builderCadena.append("\n");
                    cantidadRegistros++;
                }
            }
                
            if(builderCadena.length()> 0){
              StringBuilder builderFinal = new StringBuilder(); 
              builderFinal.append(ENCABEZADO_INFORME_DETALLES_DESCUENTO_CAPITA);
              builderFinal.append(builderCadena);
              reporte.setContenidoEnString(builderFinal.toString());
              reporte.setCantidadRegistros(cantidadRegistros);
              builderCadena.setLength(0);
            }
             
         } catch (NoResultException e) {
             reporte = new Reporte();
             reporte.setObservacion(e.getMessage());
         } catch (Exception e) {
             Exception(CONSULTAR_TODOS, e);
         } finally {
             if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                 dbConnection.close();
            }
         }
         return reporte;
    }
    
     @Override
    public Reporte generarReporteGeneralCuentaMedicaDetalles(ParamConsulta paramConsulta) throws Exception {
         Reporte reporte = new Reporte();
         Connection dbConnection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet;
         StringBuilder builderCadena = new StringBuilder();
         int cantidadRegistros = 0;
         try {
             String strQuery = " SELECT "
                     + "    cmf.id as identificacion_factura,"
                     + "    cmf.numero_radicado,"
                     + "    cmf.nit, cmf.ips,"
                     + "    CASE "
                     + "        WHEN LENGTH(cmf.numero_contrato) > 3 THEN cmf.numero_contrato "
                     + "        ELSE cntcontrato.contrato "
                     + "    END AS numero_contrato, "
                     + "    cmf.numero_facturado, "
                     + "    cmf.valor_factura, "
                     + "    cmd.mae_tipo_documento_valor, "
                     + "    cmd.documento, "
                     + "    cmd.nombre_completo_afiliado, "
                     + "    cmd.ma_servicio_valor, "
                     + "    cmd.cantidad, "
                     + "    cmd.valor_facturado as valor_servicio, "
                     + "    cmd.valor_pagado as valor_pagado_servicio, "
                     + "    cmd.valor_pendiente_actual as valor_pendiente_servicio, "
                     + "    cmd.valor_copago as valor_copago_servicio, "
                     + "    cmf.mae_tipo_contrato_valor, "
                     + "    cmf.fecha_radicacion, "
                     + "    CASE cmf.estado_factura"
                     + "        WHEN 0 THEN 'Sin auditoria'"
                     + "        WHEN 1 THEN 'En auditoria'"
                     + "        WHEN 2 THEN 'Glosada'"
                     + "        WHEN 4 THEN 'Conciliada'"
                     + "        WHEN 5 THEN 'Devuelta'"
                     + "        WHEN 6 THEN 'Espera Sap para devolucion'"
                     + "        WHEN 7 THEN 'Espera Sap para cierre auditoria'"
                     + "        WHEN 8 THEN 'Espera Sap para respuesta/conciliacion'"
                     + "        WHEN 9 THEN 'Auditada exitosa'"
                     + "        WHEN 10 THEN 'Sin procesar'"
                     + "        WHEN 11 THEN 'Sin notificar'"
                     + "        WHEN 12 THEN 'Anulada'"
                     + "    END AS estado_factura,"
                     + "    cmf.mae_regimen_valor "
                     + "FROM"
                     + "    cm_facturas cmf INNER JOIN cm_detalles cmd ON cmd.cm_facturas_id = cmf.id "
                     + "        LEFT JOIN"
                     + "    cm_rips_cargas cmrips ON cmrips.id = cmf.cm_rips_cargas_id"
                     + "        LEFT JOIN"
                     + "    cnt_contratos cntcontrato ON cntcontrato.id = cmrips.cnt_contratos_id"
                     + " where cmf.id > 0 " + RESTRICCION_FACTURA_ANULADA;
             
              dbConnection = this.getConnection();
             
              if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {

                String fechaInicial = sdf.format(paramConsulta.getParametroConsulta1());
                String fechaFinal = sdf.format(paramConsulta.getParametroConsulta2());
                strQuery += " AND (cmf.fecha_radicacion >= '" + fechaInicial + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO
                         +"') AND ( cmf.fecha_radicacion < '" + fechaFinal   + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "') ";

                if(paramConsulta.getParametroConsulta3() != null){
                    strQuery += " AND cmf.nit like '%" + paramConsulta.getParametroConsulta3() +"%' ";
                }
                
                if(paramConsulta.getParametroConsulta4() != null){
                    strQuery += " AND cmf.ips like '%"+ paramConsulta.getParametroConsulta4() +"%' ";
                }
                
                strQuery += " order by cmf.numero_radicado DESC";
                
                preparedStatement = dbConnection.prepareStatement(strQuery);
                resultSet = preparedStatement.executeQuery();
               
                while (resultSet.next()) {   
                    builderCadena.append(resultSet.getString("identificacion_factura"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_radicado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("nit"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ips")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("numero_contrato")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_facturado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_factura")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_tipo_documento_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("documento")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("nombre_completo_afiliado")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ma_servicio_valor"))); 
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("cantidad")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_pagado_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_pendiente_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_copago_servicio")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_tipo_contrato_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_radicacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("estado_factura")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_regimen_valor")));
                    builderCadena.append("\n");
                    cantidadRegistros++;
                }
            }
                
            if(builderCadena.length()> 0){
              StringBuilder builderFinal = new StringBuilder(); 
              builderFinal.append(ENCABEZADO_INFORME_GENERAL_CM_DETALLES);
              builderFinal.append(builderCadena);
              reporte.setContenidoEnString(builderFinal.toString());
              reporte.setCantidadRegistros(cantidadRegistros);
              builderCadena.setLength(0);
            }
             
         } catch (NoResultException e) {
             reporte = new Reporte();
             reporte.setObservacion(e.getMessage());
         } catch (Exception e) {
             Exception(CONSULTAR_TODOS, e);
         } finally {
             if (preparedStatement != null) {
                 preparedStatement.close();
             }
             if (dbConnection != null) {
                 dbConnection.close();
             }
         }
         return reporte;
    }
    
     @Override
    public Reporte generarReporteDevolucionFacturas(ParamConsulta paramConsulta) throws Exception {
         Reporte reporte = new Reporte();
         Connection dbConnection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet;
         StringBuilder builderCadena = new StringBuilder();
         int cantidadRegistros = 0;
         try {
             String strQuery = "SELECT cmf.numero_radicado , cmf.nit, cmf.ips, "
                     + " CASE WHEN "
                     + "     LENGTH(cmf.numero_contrato) > 3 THEN cmf.numero_contrato "
                     + "    ELSE  cntcontrato.contrato END  AS numero_contrato,"
                     + " cmf.numero_facturado, "
                     + " cmf.valor_factura, cmf.mae_tipo_contrato_valor, "
                     + " cmf.fecha_radicacion, cmf.fecha_prestacion,"
                     + "    CASE cmf.estado_factura"
                     + "        WHEN 0 THEN 'Radicado'"
                     + "        WHEN 1 THEN 'En auditoria'"
                     + "        WHEN 2 THEN 'Auditada-Glosada'"
                     + "        WHEN 4 THEN 'Conciliada'"
                     + "        WHEN 5 THEN 'Devuelta'"
                     + "        WHEN 6 THEN 'Devuelta - Espera SAP (DEV)'"
                     + "        WHEN 7 THEN 'Auditada - Espera SAP (M2)'"
                     + "        WHEN 8 THEN 'Conciliada - Espera SAP (M3)'"
                     + "        WHEN 9 THEN 'Auditada'"
                     + "        WHEN 10 THEN 'Radicado - Espera SAP (M1)'"
                     + "        WHEN 11 THEN 'Radicado - Espera SAP (M1) - ERROR'"
                     + "        WHEN 12 THEN 'Anulada'"
                     + "    END AS estado_factura, "
                     + " cmf.mae_regimen_valor, cm_devolucion.mae_motivo_devolucion_valor, "
                     + " cm_devolucion.fecha_devolucion, cm_devolucion.observacion "
                     + " FROM cm_facturas cmf LEFT JOIN cm_auditoria_devoluciones "
                     + " cm_devolucion ON cm_devolucion.cm_facturas_id = cmf.id "
                     + " LEFT JOIN cm_rips_cargas cmrips On "
                     + "  cmrips.id = cmf.cm_rips_cargas_id "
                     + "  LEFT JOIN cnt_contratos cntcontrato ON cntcontrato.id = cmrips.cnt_contratos_id "
                     + " where cmf.id > 0 " + RESTRICCION_FACTURA_ANULADA + " AND cmf.estado_factura = "+CmFactura.ESTADO_FACTURA_DEVUELTA;
             
              dbConnection = this.getConnection();
             
              if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {

                String fechaInicial = sdf.format(paramConsulta.getParametroConsulta1());
                String fechaFinal = sdf.format(paramConsulta.getParametroConsulta2());
                strQuery += " AND (cmf.fecha_radicacion >= '" + fechaInicial + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO +
                          "') AND ( cmf.fecha_radicacion < '" + fechaFinal + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "') ";

                if(paramConsulta.getParametroConsulta3() != null){
                    strQuery += " AND cmf.nit like '%" + paramConsulta.getParametroConsulta3() +"%' ";
                }
                
                if(paramConsulta.getParametroConsulta4() != null){
                    strQuery += " AND cmf.ips like '%"+ paramConsulta.getParametroConsulta4() +"%' ";
                }
                
                strQuery += " order by cmf.numero_radicado DESC";
                
                preparedStatement = dbConnection.prepareStatement(strQuery);
                resultSet = preparedStatement.executeQuery();
               
                while (resultSet.next()) {   
                    builderCadena.append(resultSet.getString("numero_radicado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("nit"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("ips")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("numero_contrato")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("numero_facturado"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(formatearDecimalesParaMostrarExcel(resultSet.getString("valor_factura")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_tipo_contrato_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_radicacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_prestacion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("estado_factura")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_regimen_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("mae_motivo_devolucion_valor")));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(resultSet.getString("fecha_devolucion"));
                    builderCadena.append(SEPARADOR);
                    builderCadena.append(Util.filtroSupresionSaltoLinea(resultSet.getString("observacion")));
                    builderCadena.append("\n");
                    cantidadRegistros++;
                }
            }
                
            if(builderCadena.length()> 0){
              StringBuilder builderFinal = new StringBuilder(); 
              builderFinal.append(ENCABEZADO_INFORME_DEVOLUCION_FACTURAS);
              builderFinal.append(builderCadena);
              reporte.setContenidoEnString(builderFinal.toString());
              reporte.setCantidadRegistros(cantidadRegistros);
              builderCadena.setLength(0);
            }
             
         } catch (NoResultException e) {
             reporte = new Reporte();
             reporte.setObservacion(e.getMessage());
         } catch (Exception e) {
             Exception(CONSULTAR_TODOS, e);
         } finally {
             if (preparedStatement != null) {
                 preparedStatement.close();
             }
             if (dbConnection != null) {
                 dbConnection.close();
             }
         }
         return reporte;
    }
    
    private String formatearDecimalesParaMostrarExcel(String valor) {
        String valorProcesar = valor == null ? "" : valor;
        return valorProcesar.replace(SEPARADOS_PUNTO, SEPARADOS_COMA);
    }
     
    private String[] filtroValoresRetorno(String cadenaProcesar){
          String[] valoresRetornados = { "", "", "", "" };
          cadenaProcesar = cadenaProcesar == null ? "" : cadenaProcesar ;
          String[] valoresObtenidos = cadenaProcesar.split("\\|");
          for (int i = 0; i < valoresObtenidos.length && valoresObtenidos.length <= valoresRetornados.length ; i++) {
            valoresRetornados[i] = valoresObtenidos[i];
          }
          return valoresRetornados;
    }

    public Connection getConnection() throws java.lang.Exception{
        Connection dbConnection = null;
        try {            
            
            DataSource data = (DataSource) getEntityManager().getEntityManagerFactory().getProperties().get( "hibernate.connection.datasource" );
            if(data == null){
               throw new Exception("No se pudo obtener el datasource ");
            }
            dbConnection = (Connection) data.getConnection();        
            if(dbConnection == null){
               throw new Exception("No se pudo obtener la conexion con DB");
            }   
        } catch (SQLException e) {
             Exception("Error obtener conexion sql de GenericoServicio : ", e);
        }catch (Exception e) {
             Exception("Error general al obtener la conexion : ", e);
        }
          
      return dbConnection;
   }

}
