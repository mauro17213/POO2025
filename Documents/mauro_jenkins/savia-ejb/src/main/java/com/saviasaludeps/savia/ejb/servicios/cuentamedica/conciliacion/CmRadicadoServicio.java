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
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmReintento;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaCierres;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaDevoluciones;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaMasiva;
import com.saviasaludeps.savia.ejb.entidades.CmConciliaciones;
import com.saviasaludeps.savia.ejb.entidades.CmDevolucionMasiva;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestas;
import com.saviasaludeps.savia.ejb.entidades.CmRadicados;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CmSincronizacionEncabezados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmRadicadoRemoto;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author yjimenez
 */
@Stateless
@Remote(CmRadicadoRemoto.class)
@Local(CmRadicadoLocal.class)
public class CmRadicadoServicio extends GenericoServicio implements CmRadicadoLocal, CmRadicadoRemoto {
    
    public static final int TIPO_CAMPO_ID_CM_RADICADO   = 0;
    public static final int TIPO_CAMPO_TIPO_TRANSACCION = 1;
    public static final int TIPO_CAMPO_TIPO_RELACION    = 2;
    public static final int TIPO_CAMPO_ESTADO = 3;
    public static final int TIPO_CAMPO_CARGA_FE_RIPS = 4;

    @Override
    public CmRadicado consultar(int id) throws Exception {
        CmRadicado obj = null;
        try {
            CmRadicados per = (CmRadicados) getEntityManager().find(CmRadicados.class, id);
            if (per != null) {
                obj = castEntidadNegocio(per);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public int insertar(CmRadicado obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizarEstado(CmRadicado obj) throws Exception {
        try {
            String hql = "UPDATE CmRadicados SET "
                    + "estadoRadicado = :estado_radicado "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado_radicado", obj.getEstado_radicado());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarEstadoProceso(int idCmRadicado, short estado) throws Exception {
        try {
            String hql = "UPDATE CmRadicados SET "
                    + "estado = :estado "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", estado);
            query.setParameter("id", idCmRadicado);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizar(CmRadicado obj) throws Exception {
        try {
            //obj.setId((int) getEntityManager().merge(castNegocioEntidad(obj)).getId()); 
            CmRadicados cmRadicados = castNegocioEntidad(obj);
            String hql = "UPDATE CmRadicados SET"
                    + " radicado = :radicado,"
                    + " estadoRadicado = :estadoRadicado,"
                    + " usuarioCrea = :usuarioCrea,"
                    + " terminalCrea  = :terminalCrea,"
                    + " fechaHoraCrea = :fechaHoraCrea,"
                    + " cmConciliacionesId.id = :cmConciliacionesId,"
                    + " cmGlosaRespuestasId.id = :cmGlosaRespuestasId"
                    + " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("radicado", cmRadicados.getRadicado());
            query.setParameter("estadoRadicado", cmRadicados.getEstadoRadicado());
            query.setParameter("usuarioCrea", cmRadicados.getUsuarioCrea());
            query.setParameter("terminalCrea", cmRadicados.getTerminalCrea());
            query.setParameter("fechaHoraCrea", cmRadicados.getFechaHoraCrea());

            Integer idConciliacionMasiva = cmRadicados.getCmConciliacionesId() != null
                    && cmRadicados.getCmConciliacionesId().getId() != null
                    ? cmRadicados.getCmConciliacionesId().getId() : null;

            Integer idGlosaRespuesta = cmRadicados.getCmGlosaRespuestasId() != null
                    && cmRadicados.getCmGlosaRespuestasId().getId() != null
                    ? cmRadicados.getCmGlosaRespuestasId().getId() : null;

            query.setParameter("cmConciliacionesId", idConciliacionMasiva);
            query.setParameter("cmGlosaRespuestasId", idGlosaRespuesta);
            query.setParameter("id", cmRadicados.getId());
            query.executeUpdate();

        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarIntentosEjecutados(int cantidadIntento, int idRadicado) throws Exception {
        try {
            String hql = "UPDATE CmRadicados SET"
                    + " intentosEjecutados = :intentosEjecutados"
                    + " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("intentosEjecutados", (short) cantidadIntento);
            query.setParameter("id", idRadicado);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarIntentosPermitidos(int cantidadIntento, int idRadicado) throws Exception {
        try {
            String hql = "UPDATE CmRadicados SET"
                    + " intentosPermitidos = :intentosPermitidos"
                    + " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("intentosPermitidos", (short) cantidadIntento);
            query.setParameter("id", idRadicado);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
     @Override
    public void actualizarFechaRegistroFacturas( int idCmRadicado, Date fecha) throws Exception {
        try {
            String hql = "UPDATE CmRadicados SET "
                    + "fechaHoraFactura = :fechaHoraFactura "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("fechaHoraFactura", fecha);
            query.setParameter("id", idCmRadicado);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
     @Override
    public void actualizarFechaRegistroTransaccion( int idCmRadicado, Date fecha) throws Exception {
        try {
            String hql = "UPDATE CmRadicados SET "
                    + "fechaHoraTransaccion = :fechaHoraTransaccion "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("fechaHoraTransaccion", fecha);
            query.setParameter("id", idCmRadicado);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarFechaRegistroEnvioSap( int idCmRadicado, Date fecha) throws Exception {
        try {
            String hql = "UPDATE CmRadicados SET "
                    + "fechaHoraEnvioSap = :fechaHoraEnvioSap "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("fechaHoraEnvioSap", fecha);
            query.setParameter("id", idCmRadicado);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }


    @Override
    public CmRadicado eliminar(int id) throws Exception {
        CmRadicado obj = null;
        try {
            CmRadicados per = getEntityManager().find(CmRadicados.class, id);
            if (per != null) {
                obj = castEntidadNegocio(per);
                getEntityManager().remove(per);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public int consultarCantidadRadicado(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT COUNT(cr) FROM CmRadicados cr WHERE cr.id > 0 AND cr.estadoRadicado = '0'");
            if (paramConsulta.getFiltros() != null && !paramConsulta.getFiltros().isEmpty()) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND cr.id = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                        case "radicado":
                            strQuery.append("AND cr.radicado = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                        case "usuario":
                            strQuery.append("AND cr.usuarioCrea LIKE '%");
                            strQuery.append(e.getValue());
                            strQuery.append("%' ");
                            break;
                        case "idConciliacionMasiva":
                            strQuery.append("AND cr.cmConciliacionesId.id  = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                        case "idGlosaRespuesta":
                            strQuery.append("AND cr.cmGlosaRespuestasId.id = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                        case "idAuditoriaDevolucion":
                            strQuery.append("AND cr.cmAuditoriaDevolucionesId.id = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                        case "idAuditoriaCierre":
                            strQuery.append("AND cr.cmAuditoriaCierresId.id = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                        case "tipoReintento":
                            int tipoReintento = Integer.valueOf((String) e.getValue());
                            switch (tipoReintento) {
                                case CmReintento.TIPO_REINTENTO_RESPUESTA_GLOSA:
                                    strQuery.append("AND cr.cmGlosaRespuestasId.id > 0 ");
                                    break;
                                case CmReintento.TIPO_REINTENTO_RESPUESTA_CONCILIACION:
                                    strQuery.append("AND cr.cmConciliacionesId.id > 0 ");
                                    break;
                                case CmReintento.TIPO_REINTENTO_NOTIFICACION_FACTURA:
                                    strQuery.append("AND cr.cmFacturaId > 0 ");
                                    break;
                                case CmReintento.TIPO_REINTENTO_DEVOLUCION_FACTURA:
                                    strQuery.append("AND cr.cmAuditoriaDevolucionesId.id > 0 ");
                                    break;
                                case CmReintento.TIPO_REINTENTO_CIERRE_AUDITORIA:
                                    strQuery.append("AND cr.cmAuditoriaCierresId.id > 0");
                                    break;
                            }
                            break;
                        case "idRadicado":
                            strQuery.append("AND cr.radicado LIKE '%");
                            strQuery.append(e.getValue());
                            strQuery.append("%' ");
                            break;
                        case "cmFactura.id":
                            strQuery.append("AND cr.cmFacturaId LIKE '%");
                            strQuery.append(e.getValue());
                            strQuery.append("%' ");
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString()).getSingleResult();
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
    public CmReintento consultarRadicadoPendientePorFactura(ParamConsulta paramConsulta) throws Exception {
        CmReintento obj = new CmReintento();
        try {

            if (paramConsulta.getParametroConsulta1() != null) {

                String strQuery = " FROM CmSincronizacionEncabezados cms WHERE cms.cmRadicadosId.estadoRadicado = '0' ";

                if (paramConsulta.getParametroConsulta1() != null) {
                    int idFactura = (int) paramConsulta.getParametroConsulta1();
                    strQuery += " AND ( cms.facturaId = " + idFactura + " AND (cms.estado = " + CmSincronizacionEncabezado.ESTADO_PROCESO
                            + " OR cms.estado = " + CmSincronizacionEncabezado.ESTADO_SIN_PROCESAR_MOMENTO_1 + " ) ) ";
                }

                strQuery += " ORDER BY cms.id DESC ";
                Query query = getEntityManager().createQuery(strQuery);
                List<CmSincronizacionEncabezados> lisSincronizacionEncabezado = query.getResultList();

                if (lisSincronizacionEncabezado != null && lisSincronizacionEncabezado.size() > 0) {
                    CmSincronizacionEncabezados sincronizacionEncabezado = lisSincronizacionEncabezado.get(0);
                    if (sincronizacionEncabezado != null) {
                        obj.setId(sincronizacionEncabezado.getCmRadicadosId().getId());
                    }
                }
            }

        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public CmRadicado consultarSiguienteRadicadoActivo(ParamConsulta paramConsulta) throws Exception {
        CmRadicado obj = new CmRadicado();
        try {

            if (paramConsulta.getParametroConsulta1() != null) {
                String strQuery = "SELECT  cmr.id , cmr.intentos_permitidos,  cmr.intentos_ejecutados "
                        + " FROM  cm_radicados cmr  "
                        + " WHERE cmr.estado_radicado = '0' AND "
                        + " IF(cmr.intentos_permitidos = 0 ,1 ,IF(cmr.intentos_permitidos > cmr.intentos_ejecutados ,1,0))  = 1 AND "
                        + " TIMESTAMPDIFF(hour,IF ((SELECT cmsi.fecha_hora_envio FROM cm_sincronizaciones cmsi WHERE cmsi.cm_radicados_id = cmr.id  ORDER BY cmsi.id DESC LIMIT 1) IS NULL,DATE_ADD(NOW(), INTERVAL -6 HOUR),(SELECT cmsi.fecha_hora_envio FROM cm_sincronizaciones cmsi WHERE cmsi.cm_radicados_id = cmr.id ORDER BY cmsi.id DESC LIMIT 1)),now()) > :horasDiferencia ";

                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += " AND cmr.id < " + paramConsulta.getParametroConsulta2();
                }

                strQuery += " ORDER by id DESC LIMIT 1 ";

                Query query = getEntityManager().createNativeQuery(strQuery);
                query.setParameter("horasDiferencia", paramConsulta.getParametroConsulta1());

                List<Object[]> listRadicados = query.getResultList();
                if (listRadicados != null) {
                    for (Object[] radicado : listRadicados) {
                        obj = new CmRadicado((Integer) radicado[0]);
                        obj.setIntentosPermitidos(Short.parseShort(radicado[1].toString()));
                        obj.setIntentosEjecutados(Short.parseShort(radicado[2].toString()));
                    }
                }

                if (obj.getId() == null || obj.getId() == 0) {
                    obj = new CmRadicado(0);
                }
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public CmRadicado consultarRadicadoActivoWsCreacionFacturas(int idRadicadoExcluir, int horasDifencia) throws Exception {
        CmRadicado obj = new CmRadicado();
        try {
            int horasDiferenciaPorDefecto = horasDifencia == 0 ? 3 : horasDifencia;
            String strQuery = "SELECT cmr.id, cmr.tipo_transaccion, cmr.tipo_relacion, cmr.estado , cmr.cm_fe_rips_cargas_id"
                    + " FROM cm_radicados cmr WHERE "
                    + "((cmr.estado_radicado = " + CmRadicado.ESTADO_RADICADO_ACTIVO +" AND cmr.estado = " + CmRadicado.ESTADO_CREADO + ") OR "
                    + "(cmr.estado_radicado = " + CmRadicado.ESTADO_RADICADO_ACTIVO +" AND cmr.estado = " + CmRadicado.ESTADO_FACTURAS_INICIO+" AND TIMESTAMPDIFF(hour, IF(cmr.fecha_hora_factura IS NULL,DATE_ADD(NOW(), INTERVAL -"+horasDiferenciaPorDefecto+" HOUR),cmr.fecha_hora_factura)  ,now()) >= :horasDiferencia)) ";

            if (idRadicadoExcluir > 0) {
                strQuery += " AND cmr.id < :idRadicadoExcluir ";
            }
            strQuery += " ORDER by cmr.id DESC LIMIT 1 ";

            Query query = getEntityManager().createNativeQuery(strQuery);
            if (idRadicadoExcluir > 0) {
                query.setParameter("idRadicadoExcluir", idRadicadoExcluir);
            }
            query.setParameter("horasDiferencia", horasDifencia);

            List<Object[]> listRadicados = query.getResultList();
            if (listRadicados != null) {
                for (Object[] radicado : listRadicados) {
  
                    var transaccion  =  radicado[TIPO_CAMPO_TIPO_TRANSACCION] != null ? radicado[TIPO_CAMPO_TIPO_TRANSACCION].toString(): "0";  
                    var tipoRelacion  =  radicado[TIPO_CAMPO_TIPO_RELACION] != null ? radicado[TIPO_CAMPO_TIPO_RELACION].toString(): "0";  
                    var estado  =  radicado[TIPO_CAMPO_ESTADO] != null ? radicado[TIPO_CAMPO_ESTADO].toString(): "0";  
                    var cargaFeRips  =  radicado[TIPO_CAMPO_CARGA_FE_RIPS] != null ? radicado[TIPO_CAMPO_CARGA_FE_RIPS].toString(): "0";  

                    obj = new CmRadicado((Integer) radicado[TIPO_CAMPO_ID_CM_RADICADO]);
                    obj.setTipoTransaccion(Short.parseShort((String) transaccion));
                    obj.setTipoRelacion(Short.parseShort((String) tipoRelacion));
                    obj.setEstado(Short.parseShort((String) estado));
                    obj.setCmFeRipsCarga(new CmFeRipsCarga( Integer.parseInt(cargaFeRips)));
                }
            }

        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    @Override
    public CmRadicado consultarRadicadoActivoWsCreacionTransacciones(int idRadicadoExcluir, int horasDifencia) throws Exception {
        CmRadicado obj = new CmRadicado();
        try {
            int horasDiferenciaPorDefecto = horasDifencia == 0 ? 3 : horasDifencia;
            String strQuery = "SELECT cmr.id, cmr.tipo_transaccion, cmr.tipo_relacion, cmr.estado "
                    + " FROM cm_radicados cmr WHERE "
                    + "("
                        + "(cmr.estado_radicado = " + CmRadicado.ESTADO_RADICADO_ACTIVO +" AND cmr.estado = " + CmRadicado.ESTADO_FACTURAS_FIN + ") OR "
                        + "(cmr.estado_radicado = " + CmRadicado.ESTADO_RADICADO_ACTIVO +" AND cmr.estado = " + CmRadicado.ESTADO_TRANSACCION_INICIO+" AND TIMESTAMPDIFF(hour, IF(cmr.fecha_hora_transaccion IS NULL,DATE_ADD(NOW(), INTERVAL -"+horasDiferenciaPorDefecto+" HOUR),cmr.fecha_hora_transaccion) ,now()) >= :horasDiferencia) OR "
                        + "( (cmr.estado_radicado = " + CmRadicado.ESTADO_RADICADO_ACTIVO +" AND cmr.estado = " + CmRadicado.ESTADO_ENVIO_SAP_FIN+" AND TIMESTAMPDIFF(hour, IF(cmr.fecha_hora_envio_sap IS NULL,DATE_ADD(NOW(), INTERVAL -"+horasDiferenciaPorDefecto+" HOUR),cmr.fecha_hora_envio_sap) ,now()) >= :horasDiferencia )  AND "
                        + "  ((SELECT count(wsf.id) FROM ws_cm_facturas wsf WHERE wsf.estado = "+WsCmFactura.ESTADO_CREADA+" AND wsf.cm_radicados_id = cmr.id  ) > 0 )"
                        + ")"
                    + ") ";

            if (idRadicadoExcluir > 0) {
                strQuery += " AND cmr.id < :idRadicadoExcluir ";
            }
            strQuery += " ORDER by cmr.id DESC LIMIT 1 ";

            Query query = getEntityManager().createNativeQuery(strQuery);
            if (idRadicadoExcluir > 0) {
                query.setParameter("idRadicadoExcluir", idRadicadoExcluir);
            }
            query.setParameter("horasDiferencia", horasDifencia);

            List<Object[]> listRadicados = query.getResultList();
            if (listRadicados != null) {
                for (Object[] radicado : listRadicados) {
  
                    var transaccion  =  radicado[TIPO_CAMPO_TIPO_TRANSACCION] != null ? radicado[TIPO_CAMPO_TIPO_TRANSACCION].toString(): "0";  
                    var tipoRelacion  =  radicado[TIPO_CAMPO_TIPO_RELACION] != null ? radicado[TIPO_CAMPO_TIPO_RELACION].toString(): "0";  
                    var estado  =  radicado[TIPO_CAMPO_ESTADO] != null ? radicado[TIPO_CAMPO_ESTADO].toString(): "0";  

                    obj = new CmRadicado((Integer) radicado[TIPO_CAMPO_ID_CM_RADICADO]);
                    obj.setTipoTransaccion(Short.parseShort((String) transaccion));
                    obj.setTipoRelacion(Short.parseShort((String) tipoRelacion));
                    obj.setEstado(Short.parseShort((String) estado));
                }
            }

        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    @Override
    public CmRadicado consultarRadicadoActivoWsEnvioSap(int idRadicadoExcluir, int horasDifencia) throws Exception {
        CmRadicado obj = new CmRadicado();
        try {
            int horasDiferenciaPorDefecto = horasDifencia == 0 ? 3 : horasDifencia;
            String strQuery = "SELECT cmr.id, cmr.tipo_transaccion, cmr.tipo_relacion, cmr.estado "
                    + " FROM cm_radicados cmr WHERE "
                    + "((cmr.estado_radicado = " + CmRadicado.ESTADO_RADICADO_ACTIVO +" AND cmr.estado = " + CmRadicado.ESTADO_TRANSACCION_FIN+ ") OR "
                    + "(cmr.estado_radicado = " + CmRadicado.ESTADO_RADICADO_ACTIVO +" AND cmr.estado = "  + CmRadicado.ESTADO_ENVIO_SAP_INICIO+" AND TIMESTAMPDIFF(hour, IF(cmr.fecha_hora_envio_sap IS NULL,DATE_ADD(NOW(), INTERVAL -"+horasDiferenciaPorDefecto+" HOUR),cmr.fecha_hora_envio_sap) ,now()) >= :horasDiferencia)) ";

            if (idRadicadoExcluir > 0) {
                strQuery += " AND cmr.id < :idRadicadoExcluir ";
            }
            strQuery += " ORDER by cmr.id DESC LIMIT 1 ";

            Query query = getEntityManager().createNativeQuery(strQuery);
            if (idRadicadoExcluir > 0) {
                query.setParameter("idRadicadoExcluir", idRadicadoExcluir);
            }
            query.setParameter("horasDiferencia", horasDifencia);

            List<Object[]> listRadicados = query.getResultList();
            if (listRadicados != null) {
                for (Object[] radicado : listRadicados) {
  
                    var transaccion  =  radicado[TIPO_CAMPO_TIPO_TRANSACCION] != null ? radicado[TIPO_CAMPO_TIPO_TRANSACCION].toString(): "0";  
                    var tipoRelacion  =  radicado[TIPO_CAMPO_TIPO_RELACION] != null ? radicado[TIPO_CAMPO_TIPO_RELACION].toString(): "0";  
                    var estado  =  radicado[TIPO_CAMPO_ESTADO] != null ? radicado[TIPO_CAMPO_ESTADO].toString(): "0";  

                    obj = new CmRadicado((Integer) radicado[TIPO_CAMPO_ID_CM_RADICADO]);
                    obj.setTipoTransaccion(Short.parseShort((String) transaccion));
                    obj.setTipoRelacion(Short.parseShort((String) tipoRelacion));
                    obj.setEstado(Short.parseShort((String) estado));
                }
            }

        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    @Override
    public CmRadicado consultarRadicadoSegunConciliacionMasiva(int idConciliacion) throws Exception {
        CmRadicado obj = new CmRadicado();
        try {

            if (idConciliacion>0) {

                String strQuery = " FROM CmRadicados cmr WHERE cmr.id > 0 && cmr.cmConciliacionesId.id = "+idConciliacion+"";
                strQuery += " ORDER BY cmr.id DESC ";
                Query query = getEntityManager().createQuery(strQuery);
                List<CmRadicados> cmRadicados = query.getResultList();
              
                for (CmRadicados cmRadicado : cmRadicados) {
                    obj = castEntidadNegocio(cmRadicado);
                    break;
                }
            }

        } catch (NoResultException e) {
            obj = new CmRadicado();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    public static CmRadicados castNegocioEntidad(CmRadicado obj) {
        CmRadicados per = new CmRadicados();
        per.setId(obj.getId());
        per.setEstadoRadicado(obj.getEstado_radicado());
        per.setRadicado(obj.getRadicado());
        if (obj.getCmConciliacion() != null) {
            per.setCmConciliacionesId(new CmConciliaciones(obj.getCmConciliacion().getId()));
        }
        if (obj.getCmGlosaRespuesta() != null) {
            per.setCmGlosaRespuestasId(new CmGlosaRespuestas(obj.getCmGlosaRespuesta().getId()));
        }

        if (obj.getCmGlosaRespuestasConciliacion() != null) {
            per.setCmGlosaRespuestasConciliacionId(new CmGlosaRespuestas(obj.getCmGlosaRespuestasConciliacion().getId()));
        }

        if (obj.getCmAuditoriaCierre() != null && obj.getCmAuditoriaCierre().getId() != null) {
            per.setCmAuditoriaCierresId(new CmAuditoriaCierres(obj.getCmAuditoriaCierre().getId()));
        }

        if (obj.getCmAuditoriaDevolucion() != null && obj.getCmAuditoriaDevolucion().getId() != null) {
            per.setCmAuditoriaDevolucionesId(new CmAuditoriaDevoluciones(obj.getCmAuditoriaDevolucion().getId()));
        }
        if (obj.getCmFactura() != null) {
            per.setCmFacturaId(obj.getCmFactura().getId());
        }

        if (obj.getCmAuditoriaMasivaN() != null && obj.getCmAuditoriaMasivaN().getId() != null) {
            per.setCmAuditoriaMasivaId(new CmAuditoriaMasiva(obj.getCmAuditoriaMasivaN().getId()));
        }
        if (obj.getCmDevolucionMasivaN() != null && obj.getCmDevolucionMasivaN().getId() != null) {
            per.setCmDevolucionMasivaId(new CmDevolucionMasiva(obj.getCmDevolucionMasivaN().getId()));
        }
        if (obj.getCmRipsCarga() != null && obj.getCmRipsCarga().getId() != null) {
            per.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        }
        
        if(obj.getCmGlosaMasiva() != null && obj.getCmGlosaMasiva().getId() != null){
            per.setCmGlosaMasivaId(obj.getCmGlosaMasiva().getId());
        }
        
        if(obj.getCmFeRipsCarga() != null && obj.getCmFeRipsCarga().getId() != null){
            per.setCmFeRipsCargasId( new CmFeRipsCargas( obj.getCmFeRipsCarga().getId()) );
        }
        
        per.setTipoTransaccion(obj.getTipoTransaccion());
        per.setEstado(obj.getEstado());
        per.setTipoRelacion(obj.getTipoRelacion());
        per.setIntentosPermitidos(obj.getIntentosPermitidos());
        per.setIntentosEjecutados(obj.getIntentosEjecutados());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }

    private CmRadicado castEntidadNegocio(CmRadicados obj) {
        CmRadicado per = new CmRadicado();
        per.setId(obj.getId());
        per.setEstado_radicado(obj.getEstadoRadicado());
        per.setRadicado(obj.getRadicado());
        if (obj.getCmConciliacionesId() != null) {
            per.setCmConciliacion(new CmConciliacion(obj.getCmConciliacionesId().getId()));
        }
        if (obj.getCmGlosaRespuestasId() != null) {
            CmGlosaRespuesta cmGlosaRespuesta = new CmGlosaRespuesta(obj.getCmGlosaRespuestasId().getId());
            cmGlosaRespuesta.setTipoRespuesta(obj.getCmGlosaRespuestasId().getTipoRespuesta());
            per.setCmGlosaRespuesta(cmGlosaRespuesta);
        }
        if (obj.getCmAuditoriaCierresId() != null) {
            per.setCmAuditoriaCierre(new CmAuditoriaCierre(obj.getCmAuditoriaCierresId().getId()));
        }
        if (obj.getCmAuditoriaDevolucionesId() != null) {
            per.setCmAuditoriaDevolucion(new CmAuditoriaDevolucion(obj.getCmAuditoriaDevolucionesId().getId()));
        }
        if (obj.getCmFacturaId() != null) {
            per.setCmFactura(new CmFactura(obj.getCmFacturaId()));
        }
        if (obj.getCmAuditoriaMasivaId() != null) {
            per.setCmAuditoriaMasivaN(new CmAuditoriaMasivaN(obj.getCmAuditoriaMasivaId().getId()));
        }
        if (obj.getCmDevolucionMasivaId() != null) {
            per.setCmDevolucionMasivaN(new CmDevolucionMasivaN(obj.getCmDevolucionMasivaId().getId()));
        }
        if (obj.getCmRipsCargasId() != null) {
            per.setCmRipsCarga(new CmRipsCarga(obj.getCmRipsCargasId().getId()));
        }
        if (obj.getCmGlosaRespuestasConciliacionId() != null) {
            per.setCmGlosaRespuestasConciliacion(new CmGlosaRespuesta(obj.getCmGlosaRespuestasConciliacionId().getId()));
        }
        if(obj.getCmGlosaMasivaId() != null){
           per.setCmGlosaMasiva(new CmGlosaMasivaN(obj.getCmGlosaMasivaId()));
        }
        
        if(obj.getCmFeRipsCargasId() != null){
           per.setCmFeRipsCarga(new CmFeRipsCarga(obj.getCmFeRipsCargasId().getId()));
        }
       
        per.setTipoRelacion(obj.getTipoRelacion());
        per.setEstado(obj.getEstado());
        per.setTipoTransaccion(obj.getTipoTransaccion());
        per.setIntentosPermitidos(obj.getIntentosPermitidos());
        per.setIntentosEjecutados(obj.getIntentosEjecutados());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setFechaHoraFactura(obj.getFechaHoraFactura());
        per.setFechaHoraTransaccion(obj.getFechaHoraTransaccion());
        per.setFechaHoraEnvioSap(obj.getFechaHoraEnvioSap());
        return per;
    }

}
