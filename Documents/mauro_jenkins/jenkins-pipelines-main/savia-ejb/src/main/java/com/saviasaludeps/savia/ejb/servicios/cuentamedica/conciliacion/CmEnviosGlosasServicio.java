/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmDevolucionMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmReintento;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmEnviosGlosaRemoto;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
@Remote(CmEnviosGlosaRemoto.class)
@Local(CmEnviosGlosasLocal.class)
public class CmEnviosGlosasServicio extends GenericoServicio implements CmEnviosGlosasLocal, CmEnviosGlosaRemoto {

    
    public static final int TIPO_CAMPO_TRANSACCION = 1;
    public static final int TIPO_CAMPO_RELACION    = 2;
    public static final int TIPO_CAMPO_ESTADO      = 3;
    public static final int TIPO_CAMPO_PROVEEDOR   = 5;
    public static final int TIPO_CAMPO_IPS         = 6;
    public static final int TIPO_CAMPO_CANTIDAD_FACTURAS  = 9;
    

    @Override
    public List<CmReintento> consultarListaReintentos(ParamConsulta paramConsulta) throws Exception {
        List<CmReintento> listResult = new ArrayList();
        try {
            String strQuery = "SELECT "
                    + "r.id, "
                    + "r.radicado, "
                    + "r.cm_conciliaciones_id, "
                    + "r.cm_auditoria_devoluciones_id, "
                    + "r.cm_glosa_respuestas_id, "
                    + "r.cm_auditoria_cierres_id, "
                    + "r.cm_factura_id, "
                    + "r.estado_radicado, "
                    + "f.cm_rips_cargas_id, "
                    + "f.nit, "
                    + "f.ips, "
                    + "f.numero_facturado, "
                    + "f.valor_factura, "
                    + "r.usuario_crea, "
                    + "r.fecha_hora_crea, "
                    + "s.proveedor_nit, "
                    + "r.cm_auditoria_masiva_id, "
                    + "r.cm_devolucion_masiva_id ,"
                    + "r.cm_rips_cargas_id as cargaReintento "
                    + "FROM cm_radicados r "
                    + "LEFT JOIN cm_facturas f "
                    + "ON r.cm_factura_id = f.id "
                    + "LEFT JOIN cm_sincronizacion_encabezados s "
                    + "ON s.cm_radicados_id = r.id "
                    + "WHERE r.estado_radicado = 0 ";
            if (paramConsulta.getFiltros() != null && !paramConsulta.getFiltros().isEmpty()) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND r.id =" + e.getValue() + " ";
                            break;
                        case "idConciliacionMasiva":
                            strQuery += "AND r.cm_conciliaciones_id =" + e.getValue() + " ";
                            break;
                        case "idGlosaRespuesta":
                            strQuery += "AND r.cm_glosa_respuestas_id =" + e.getValue() + " ";
                            break;
                        case "usuario":
                            strQuery += "AND r.usuario_crea LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idAuditoriaDevolucion":
                            strQuery += "AND r.cm_auditoria_devoluciones_id = " + e.getValue() + " ";
                            break;
                        case "idAuditoriaCierre":
                            strQuery += "AND r.cm_auditoria_cierres_id = " + e.getValue() + " ";
                            break;
                        case "idAuditoriaCierreMasivo":
                            strQuery += "AND r.cm_auditoria_masiva_id = " + e.getValue() + " ";
                            break;
                        case "idAuditoriaDevolucionMasiva":
                            strQuery += "AND r.cm_devolucion_masiva_id = " + e.getValue() + " ";
                            break;
                        case "cmFactura.id":
                            strQuery += "AND r.cm_factura_id = " + e.getValue() + " ";
                            break;
                        case "cmFactura.cmRipCarga.id":
                            strQuery += "AND (f.cm_rips_cargas_id = " + e.getValue() + " ";
                            strQuery += "OR r.cm_rips_cargas_id  = " + e.getValue() + " ) ";
                            break;
                        case "cmFactura.numeroFacturado":
                            strQuery += "AND f.numero_facturado = '" + e.getValue() + "' ";
                            break;
                        case "cmFactura.ips":
                            strQuery += "AND f.ips LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idRadicado":
                            strQuery += "AND r.radicado = " + e.getValue() + " ";
                            break;
                        case "tipoReintento":
                            int tipoReintento = Integer.valueOf((String) e.getValue());
                            switch (tipoReintento) {
                                case CmReintento.TIPO_REINTENTO_RESPUESTA_GLOSA:
                                    strQuery += "AND r.cm_glosa_respuestas_id > 0 ";
                                    break;
                                case CmReintento.TIPO_REINTENTO_RESPUESTA_CONCILIACION:
                                    strQuery += "AND r.cm_conciliaciones_id > 0 ";
                                    break;
                                case CmReintento.TIPO_REINTENTO_NOTIFICACION_FACTURA:
                                    strQuery += "AND (r.cm_factura_id > 0 OR r.cm_rips_cargas_id > 0)";
                                    break;
                                case CmReintento.TIPO_REINTENTO_DEVOLUCION_FACTURA:
                                    strQuery += "AND r.cm_auditoria_devoluciones_id > 0 ";
                                    break;
                                case CmReintento.TIPO_REINTENTO_CIERRE_AUDITORIA:
                                    strQuery += "AND r.cm_auditoria_cierres_id > 0 ";
                                    break;
                                case CmReintento.TIPO_REINTENTO_CIERRE_AUDITORIA_MASIVA:
                                    strQuery += "AND r.cm_auditoria_masiva_id > 0 ";
                                    break;
                                case CmReintento.TIPO_REINTENTO_DEVOLUCION_FACTURA_MASIVA:
                                    strQuery += "AND r.cm_devolucion_masiva_id > 0 ";
                                    break;
                            }
                            break;
                       case "tipoRelacion":
                            strQuery += "AND r.tipoRelacion = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "GROUP by "
                    + "r.radicado, "
                    + "r.id, "
                    + "r.cm_conciliaciones_id, "
                    + "r.cm_auditoria_devoluciones_id, "
                    + "r.cm_glosa_respuestas_id, "
                    + "r.cm_auditoria_cierres_id, "
                    + "r.cm_factura_id, "
                    + "r.estado_radicado, "
                    + "f.cm_rips_cargas_id, "
                    + "f.nit, "
                    + "f.ips, "
                    + "f.numero_facturado, "
                    + "f.valor_factura, "
                    + "r.usuario_crea, "
                    + "r.fecha_hora_crea, "
                    + "s.proveedor_nit, "
                    + "r.cm_auditoria_masiva_id, "
                    + "r.cm_devolucion_masiva_id, "
                    + "r.cm_rips_cargas_id ";
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("id")) {
                    strQuery += " r.id ";
                } else if (paramConsulta.getOrden().equals("estado")) {
                    strQuery += " r.estado_radicado ";
                } else if (paramConsulta.getOrden().equals("usuario")) {
                    strQuery += " r.usuario_crea ";
                } else if (paramConsulta.getOrden().equals("fechaCreacion")) {
                    strQuery += " r.fecha_creacion ";
                } else if (paramConsulta.getOrden().equals("idConciliacionMasiva")) {
                    strQuery += " r.cm_conciliaciones_id ";
                } else if (paramConsulta.getOrden().equals("idGlosaRespuesta")) {
                    strQuery += " r.cm_glosa_respuestas_id ";
                } else if (paramConsulta.getOrden().equals("idAuditoriaDevolucion")) {
                    strQuery += " r.cm_auditoria_devoluciones_id ";
                } else if (paramConsulta.getOrden().equals("idAuditoriaCierre")) {
                    strQuery += " r.cm_auditoria_cierres_id ";
                }else if (paramConsulta.getOrden().equals("idAuditoriaDevolucionMasiva")) {
                    strQuery += " r.cm_devolucion_masiva_id ";
                } else if (paramConsulta.getOrden().equals("idAuditoriaCierreMasivo")) {
                    strQuery += " r.cm_auditoria_masiva_id ";
                }
                strQuery += (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "r.fecha_hora_crea DESC ";
            }
            Query query = getEntityManager().createNativeQuery(strQuery);
            if (paramConsulta.getRegistrosPagina() > 0) {
                List<Object[]> listaObj = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
                for (Object[] objeto : listaObj) {
                    CmReintento resultado = new CmReintento();
                    resultado.setId(Integer.parseInt(objeto[0].toString()));
                    resultado.setRadicado(Integer.parseInt(objeto[1].toString()));
                    resultado.setEstado(objeto[7].toString());
                    resultado.setUsuario(objeto[13].toString());
                    resultado.setFechaCreacion(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objeto[14].toString()));
                    CmConciliacion conciliacion = new CmConciliacion();
                    CmGlosaRespuesta glosaRespuesta = new CmGlosaRespuesta();
                    CmAuditoriaCierre auditoriaCierre = new CmAuditoriaCierre();
                    CmAuditoriaDevolucion auditoriaDevolucion = new CmAuditoriaDevolucion();
                    CmFactura cmFactura = new CmFactura();
                    CmAuditoriaMasivaN cmAuditoriaMasiva = new CmAuditoriaMasivaN();
                    CmDevolucionMasivaN cmDevolucionMasiva = new CmDevolucionMasivaN();
                    CmRipsCarga cmRipsCarga= new CmRipsCarga();
                    
                    int tipoReintento = 0;
                    if (objeto[15] != null) {
                        resultado.setProveedor(objeto[15].toString());
                    }
                    if (objeto[2] != null) {
                        Integer idConciliacionMasiva = Integer.parseInt(objeto[2].toString());
                        conciliacion.setId(idConciliacionMasiva);
                        resultado.setIdConciliacionMasiva(idConciliacionMasiva);
                        tipoReintento = CmReintento.TIPO_REINTENTO_RESPUESTA_CONCILIACION;
                    }
                    if (objeto[4] != null) {
                        Integer idGlosaRespuesta = Integer.parseInt(objeto[4].toString());
                        glosaRespuesta.setId(idGlosaRespuesta);
                        resultado.setIdGlosaRespuesta(idGlosaRespuesta);
                        tipoReintento = CmReintento.TIPO_REINTENTO_RESPUESTA_GLOSA;
                    }
                    if (objeto[6] != null) {
                        tipoReintento = CmReintento.TIPO_REINTENTO_NOTIFICACION_FACTURA;
                        cmFactura = new CmFactura(Integer.parseInt(objeto[6].toString()));
                        cmFactura.setNumeroFacturado(objeto[11].toString());
                        cmFactura.setNit(objeto[9].toString());
                        cmFactura.setIps(objeto[10].toString());
                        cmFactura.setValorFactura(new BigDecimal(objeto[12].toString()));
                        cmFactura.setCmRipCarga(new CmRipsCarga(Integer.parseInt(objeto[8].toString())));
                    }
                    if (objeto[5] != null) {
                        Integer idAuditoriaCierre = Integer.parseInt(objeto[5].toString());
                        resultado.setIdAuditoriaCierre(idAuditoriaCierre);
                        tipoReintento = CmReintento.TIPO_REINTENTO_CIERRE_AUDITORIA;
                        auditoriaCierre.setId(idAuditoriaCierre);
                    }
                    if (objeto[3] != null) {
                        Integer idAuditoriaDevolucion = Integer.parseInt(objeto[3].toString());
                        resultado.setIdAuditoriaDevolucion(idAuditoriaDevolucion);
                        tipoReintento = CmReintento.TIPO_REINTENTO_DEVOLUCION_FACTURA;
                        auditoriaDevolucion.setId(idAuditoriaDevolucion);
                    }      
                    if (objeto[16] != null) {
                        Integer idAuditoriaCierreMasiva = Integer.parseInt(objeto[16].toString());
                        resultado.setIdAuditoriaCierreMasivo(idAuditoriaCierreMasiva);
                        tipoReintento = CmReintento.TIPO_REINTENTO_CIERRE_AUDITORIA_MASIVA;
                        cmAuditoriaMasiva.setId(idAuditoriaCierreMasiva);
                    }
                    if (objeto[17] != null) {
                        Integer idAuditoriaDevolucionMasiva = Integer.parseInt(objeto[17].toString());
                        resultado.setIdAuditoriaDevolucionMasiva(idAuditoriaDevolucionMasiva);
                        tipoReintento = CmReintento.TIPO_REINTENTO_DEVOLUCION_FACTURA_MASIVA;
                        cmDevolucionMasiva.setId(idAuditoriaDevolucionMasiva);
                    }
                    if (objeto[18] != null) {
                        Integer idCargaRips = Integer.parseInt(objeto[18].toString());
                        cmFactura.setCmRipCarga(new CmRipsCarga(idCargaRips));
                        resultado.setIdRipCarga(idCargaRips);
                        tipoReintento = CmReintento.TIPO_REINTENTO_NOTIFICACION_FACTURA;
                        cmRipsCarga.setId(idCargaRips);
                    }
                    
                    resultado.setTipoReintento(tipoReintento);
                    resultado.setCmConciliacion(conciliacion);
                    resultado.setCmGlosaRespuesta(glosaRespuesta);
                    resultado.setCmAuditoriaCierre(auditoriaCierre);
                    resultado.setCmAuditoriaDevolucion(auditoriaDevolucion);
                    resultado.setCmFactura(cmFactura);
                    resultado.setCmAuditoriaMasiva(cmAuditoriaMasiva);
                    resultado.setCmDevolucionMasiva(cmDevolucionMasiva);
                    resultado.setCmRipsCarga(cmRipsCarga);
                    listResult.add(resultado);
                }
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public int consultarCantidadReintentos(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT "
                    + "COUNT(*) FROM "
                    + "(SELECT r.radicado, "
                    + "r.id, "
                    + "r.cm_conciliaciones_id, "
                    + "r.cm_auditoria_devoluciones_id, "
                    + "r.cm_glosa_respuestas_id, "
                    + "r.cm_auditoria_cierres_id, "
                    + "r.cm_factura_id, "
                    + "r.cm_auditoria_masiva_id, "
                    + "r.cm_devolucion_masiva_id, "
                    + "r.cm_rips_cargas_id as cargaReintento, "
                    + "r.estado_radicado, "
                    + "f.cm_rips_cargas_id, "
                    + "f.nit, "
                    + "f.ips, "
                    + "f.numero_facturado, "
                    + "f.valor_factura, "
                    + "r.usuario_crea, "
                    + "r.fecha_hora_crea, "
                    + "s.proveedor_nit "
                    + "FROM cm_radicados r "
                    + "LEFT JOIN cm_facturas f "
                    + "ON r.cm_factura_id = f.id "
                    + "LEFT JOIN cm_sincronizacion_encabezados s "
                    + "ON s.cm_radicados_id = r.id "
                    + "WHERE r.estado_radicado = 0 ";
            if (paramConsulta.getFiltros() != null && !paramConsulta.getFiltros().isEmpty()) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND r.id =" + e.getValue() + " ";
                            break;
                        case "idConciliacionMasiva":
                            strQuery += "AND r.cm_conciliaciones_id =" + e.getValue() + " ";
                            break;
                        case "idGlosaRespuesta":
                            strQuery += "AND r.cm_glosa_respuestas_id =" + e.getValue() + " ";
                            break;
                        case "usuario":
                            strQuery += "AND r.usuario_crea LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idAuditoriaDevolucion":
                            strQuery += "AND r.cm_auditoria_devoluciones_id = " + e.getValue() + " ";
                            break;
                        case "idAuditoriaCierre":
                            strQuery += "AND r.cm_auditoria_cierres_id = " + e.getValue() + " ";
                            break;
                        case "idAuditoriaCierreMasivo":
                            strQuery += "AND r.cm_auditoria_masiva_id = " + e.getValue() + " ";
                            break;
                        case "idAuditoriaDevolucionMasiva":
                            strQuery += "AND r.cm_devolucion_masiva_id = " + e.getValue() + " ";
                            break;
                        case "cmFactura.id":
                            strQuery += "AND r.cm_factura_id = " + e.getValue() + " ";
                            break;
                        case "cmFactura.cmRipCarga.id":
                            strQuery += "AND (f.cm_rips_cargas_id = " + e.getValue() + " ";
                            strQuery += "OR r.cm_rips_cargas_id  = " + e.getValue() + " ) ";
                            break;
                        case "cmFactura.numeroFacturado":
                            strQuery += "AND f.numero_facturado = '" + e.getValue() + "' ";
                            break;
                        case "cmFactura.ips":
                            strQuery += "AND f.ips LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idRadicado":
                            strQuery += "AND r.radicado = " + e.getValue() + " ";
                            break;
                        case "tipoReintento":
                            int tipoReintento = Integer.valueOf((String) e.getValue());
                            switch (tipoReintento) {
                                case CmReintento.TIPO_REINTENTO_RESPUESTA_GLOSA:
                                    strQuery += "AND r.cm_glosa_respuestas_id > 0 ";
                                    break;
                                case CmReintento.TIPO_REINTENTO_RESPUESTA_CONCILIACION:
                                    strQuery += "AND r.cm_conciliaciones_id > 0 ";
                                    break;
                                case CmReintento.TIPO_REINTENTO_NOTIFICACION_FACTURA:
                                    strQuery += "AND (r.cm_factura_id > 0 OR r.cm_rips_cargas_id > 0) ";
                                    break;
                                case CmReintento.TIPO_REINTENTO_DEVOLUCION_FACTURA:
                                    strQuery += "AND r.cm_auditoria_devoluciones_id > 0 ";
                                    break;
                                case CmReintento.TIPO_REINTENTO_CIERRE_AUDITORIA:
                                    strQuery += "AND r.cm_auditoria_cierres_id > 0 ";
                                    break;
                                case CmReintento.TIPO_REINTENTO_CIERRE_AUDITORIA_MASIVA:
                                    strQuery += "AND r.cm_auditoria_masiva_id > 0 ";
                                    break;
                                case CmReintento.TIPO_REINTENTO_DEVOLUCION_FACTURA_MASIVA:
                                    strQuery += "AND r.cm_devolucion_masiva_id > 0 ";
                                    break;
                            }
                            break;
                    }
                }
            }
            strQuery += "GROUP by "
                    + "r.radicado, "
                    + "r.id, "
                    + "r.cm_conciliaciones_id, "
                    + "r.cm_auditoria_devoluciones_id, "
                    + "r.cm_glosa_respuestas_id, "
                    + "r.cm_auditoria_cierres_id, "
                    + "r.cm_factura_id, "
                    + "r.cm_auditoria_masiva_id, "
                    + "r.cm_devolucion_masiva_id, "
                    + "r.estado_radicado, "
                    + "f.cm_rips_cargas_id, "
                    + "f.nit, "
                    + "f.ips, "
                    + "f.numero_facturado, "
                    + "f.valor_factura, "
                    + "r.usuario_crea, "
                    + "r.fecha_hora_crea, "
                    + "s.proveedor_nit) f ";
            Object result = getEntityManager().createNativeQuery(strQuery).getSingleResult();
            cant = Integer.parseInt(result.toString());
        } catch (NoResultException e) {
            cant = 0;
        } catch (NumberFormatException e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }
    
    @Override
    public List<CmReintento> consultarListaRadicadosActivos(ParamConsulta paramConsulta) throws Exception {
        List<CmReintento> listResult = new ArrayList();
        try {
            String strQuery = "SELECT "
                    + " r.id, "
                    + " r.tipo_transaccion, "
                    + " r.tipo_relacion, "
                    + " r.estado, "
                    + " r.estado_radicado, "
                    + " wsf.proveedor_nit, "
                    + " cmf.ips, "
                    + " r.usuario_crea, "
                    + " r.fecha_hora_crea ,"
                    + " count(cmf.id) as numeroFacturas"
                    + " FROM cm_radicados r "
                    + " LEFT JOIN ws_cm_facturas wsf ON wsf.cm_radicados_id = r.id "
                    + " LEFT JOIN cm_facturas cmf ON wsf.cm_facturas_id = cmf.id"
                    + " WHERE r.estado_radicado = 0 ";
            if (paramConsulta.getFiltros() != null && !paramConsulta.getFiltros().isEmpty()) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND r.id =" + e.getValue() + " ";
                            break;
                        case "idConciliacionMasiva":
                            strQuery += " AND r.cm_conciliaciones_id =" + e.getValue() + " ";
                            break;
                        case "tipoRelacion":
                            strQuery += " AND r.tipoRelacion = " + e.getValue() + " ";
                            break;
                        case "tipoTransaccion":
                            strQuery += " AND r.tipo_transaccion = " + e.getValue() + " ";
                            break;
                        case "ips":
                            strQuery += " AND cmf.ips = '" + e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += " AND r.estado = " + e.getValue() + " ";
                            break;
                        case "usuario":
                            strQuery += " AND r.usuario_crea = '" + e.getValue() + "' ";
                            break;   
                        case "proveedor":
                            strQuery += " AND wsf.proveedor_nit = '" + e.getValue() + "' ";
                            break;  
                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta1() != null && ! paramConsulta.getParametroConsulta1().equals("")) {
                strQuery += " AND wsf.numero_documento = '" + paramConsulta.getParametroConsulta1() + "' ";
            }
            
            strQuery += "GROUP by "
                    + " r.id, "
                    + " r.radicado, "
                    + " r.cm_factura_id, "
                    + " r.estado_radicado, "
                    + " cmf.ips, "
                    + " r.usuario_crea, "
                    + " r.fecha_hora_crea, "
                    + " wsf.proveedor_nit, "
                    + " r.cm_rips_cargas_id ";

            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("id")) {
                    strQuery += " r.id ";
                } else if (paramConsulta.getOrden().equals("estado")) {
                    strQuery += " r.estado ";
                } else if (paramConsulta.getOrden().equals("tipoRelacion")) {
                    strQuery += " r.tipo_relacion ";
                } else if (paramConsulta.getOrden().equals("proveedor")) {
                    strQuery += " wsf.proveedor_nit ";
                } else if (paramConsulta.getOrden().equals("fechaHoraCrea")) {
                    strQuery += " r.fecha_hora_crea ";
                }
                
                strQuery += (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "r.fecha_hora_crea DESC ";
            }
            Query query = getEntityManager().createNativeQuery(strQuery);
            if (paramConsulta.getRegistrosPagina() > 0) {
                List<Object[]> listaObj = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
                for (Object[] objeto : listaObj) {
                    var transaccion =  objeto[TIPO_CAMPO_TRANSACCION] != null ? objeto[TIPO_CAMPO_TRANSACCION].toString(): "0";    
                    var realacion   =  objeto[TIPO_CAMPO_RELACION] != null ? objeto[TIPO_CAMPO_RELACION].toString(): "0";  
                    var estado      =  objeto[TIPO_CAMPO_ESTADO] != null ? objeto[TIPO_CAMPO_ESTADO].toString(): "0"; 
                    var proveedor   =  objeto[TIPO_CAMPO_PROVEEDOR] != null ? objeto[TIPO_CAMPO_PROVEEDOR].toString(): ""; 
                    var ips         =  objeto[TIPO_CAMPO_IPS] != null ? objeto[TIPO_CAMPO_IPS].toString(): ""; 
                    var numeroFacturas   =  objeto[TIPO_CAMPO_CANTIDAD_FACTURAS] != null ? objeto[TIPO_CAMPO_CANTIDAD_FACTURAS]: "0"; 
                    CmReintento resultado = new CmReintento();
                    resultado.setId(Integer.parseInt(objeto[0].toString()));
                    resultado.setTipoTransaccion(Short.parseShort((String) transaccion));
                    resultado.setTipoRelacion(Short.parseShort((String) realacion));
                    resultado.setEstado((String)estado);
                    resultado.setEstadoRadicado((Boolean) objeto[4]);
                    resultado.setProveedor(proveedor);
                    resultado.setIps(ips);
                    resultado.setUsuarioCrea(objeto[7].toString());
                    resultado.setFechaHoraCrea(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objeto[8].toString()));
                    resultado.setNumeroFacturas(new BigDecimal((BigInteger) numeroFacturas).toString());
                    listResult.add(resultado);
                }
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public int consultarCantidadRadicadosActivos(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = " SELECT COUNT(*) FROM  (SELECT "
                    + " r.id, "
                    + " r.tipo_transaccion, "
                    + " r.tipo_relacion, "
                    + " r.estado, "
                    + " r.estado_radicado, "
                    + " wsf.proveedor_nit, "
                    + " cmf.ips, "
                    + " r.usuario_crea, "
                    + " r.fecha_hora_crea "
                    + " FROM cm_radicados r "
                    + " LEFT JOIN ws_cm_facturas wsf ON wsf.cm_radicados_id = r.id "
                    + " LEFT JOIN cm_facturas cmf ON wsf.cm_facturas_id = cmf.id"
                    + " WHERE r.estado_radicado = 0 ";
            if (paramConsulta.getFiltros() != null && !paramConsulta.getFiltros().isEmpty()) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND r.id =" + e.getValue() + " ";
                            break;
                        case "idConciliacionMasiva":
                            strQuery += " AND r.cm_conciliaciones_id =" + e.getValue() + " ";
                            break;
                        case "tipoRelacion":
                            strQuery += " AND r.tipoRelacion = " + e.getValue() + " ";
                            break;
                         case "tipoTransaccion":
                            strQuery += " AND r.tipo_transaccion = " + e.getValue() + " ";
                            break;
                         case "ips":
                            strQuery += " AND cmf.ips = '" + e.getValue() + "' ";
                            break;
                         case "estado":
                            strQuery += " AND r.estado = " + e.getValue() + " ";
                            break;
                        case "usuario":
                            strQuery += " AND r.usuario_crea = '" + e.getValue() + "' ";
                            break; 
                         case "proveedor":
                            strQuery += " AND wsf.proveedor_nit = '" + e.getValue() + "' ";
                            break;  
                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta1() != null && ! paramConsulta.getParametroConsulta1().equals("")){
                strQuery += " AND wsf.numero_documento = '" + paramConsulta.getParametroConsulta1() + "' ";
            }
            
            strQuery += "GROUP by "
                    + " r.id, "
                    + " r.radicado, "
                    + " r.cm_factura_id, "
                    + " r.estado_radicado, "
                    + " cmf.ips, "
                    + " r.usuario_crea, "
                    + " r.fecha_hora_crea, "
                    + " wsf.proveedor_nit, "
                    + " r.cm_rips_cargas_id ) f ";
            Object result = getEntityManager().createNativeQuery(strQuery).getSingleResult();
            cant = Integer.parseInt(result.toString());
        } catch (NoResultException e) {
            cant = 0;
        } catch (NumberFormatException e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

}
