/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservice;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.webservice.WsConexion;
import com.saviasaludeps.savia.dominio.webservice.WsServicio;
import com.saviasaludeps.savia.dominio.webservice.WsTransaccion;
import com.saviasaludeps.savia.ejb.entidades.WsTransacciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.webservice.WsTransaccionRemoto;
import java.util.Date;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(WsTransaccionRemoto.class)
@Local(WsTransaccionLocal.class)
public class WsTransaccionServicio extends GenericoServicio implements WsTransaccionLocal, WsTransaccionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM WsTransacciones s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND s.fechaHoraSolicitud BETWEEN :fh_inicio AND :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND s.fechaHoraSolicitud >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND s.fechaHoraSolicitud <= :fh_fin ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND s.id = " + (String) e.getValue() + " ";
                            break;
                        case "wsServicio.id":
                            strQuery += "AND s.wsServiciosId.id = " + (String) e.getValue() + " ";
                            break;
                        case "wsConexion.id":
                            strQuery += "AND s.wsConexionesId.id = " + (String) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND s.estado = " + (String) e.getValue() + " ";
                            break;
                        case "fechaHoraSolicitud":
                            strQuery += "AND s.fechaHoraSolicitud LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaHoraRespuesta":
                            strQuery += "AND s.fechaHoraRespuesta LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "contingencia":
                            strQuery += "AND s.contingencia = " + (String) e.getValue() + " ";
                            break;
                        case "respuesta":
                            //Si es 3 buscamos nulos
                            String valor = (String) e.getValue();
                            if (valor.equals("3")) {
                                strQuery += "AND s.respuesta is null ";
                            } else {
                                strQuery += "AND s.respuesta = " + (String) e.getValue() + " ";
                            }
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }
            cant = (int) (long) query.getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<WsTransaccion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<WsTransaccion> listResult = new ArrayList();
        try {
            String strQuery = "FROM WsTransacciones s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND s.fechaHoraSolicitud BETWEEN :fh_inicio AND :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND s.fechaHoraSolicitud >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND s.fechaHoraSolicitud <= :fh_fin ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND s.id = " + (String) e.getValue() + " ";
                            break;
                        case "wsServicio.id":
                            strQuery += "AND s.wsServiciosId.id = " + (String) e.getValue() + " ";
                            break;
                        case "wsConexion.id":
                            strQuery += "AND s.wsConexionesId.id = " + (String) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND s.estado = " + (String) e.getValue() + " ";
                            break;
                        case "fechaHoraSolicitud":
                            strQuery += "AND s.fechaHoraSolicitud LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaHoraRespuesta":
                            strQuery += "AND s.fechaHoraRespuesta LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "contingencia":
                            //Si es 3 buscamos nulos
                            strQuery += "AND s.contingencia = " + (String) e.getValue() + " ";
                            break;
                        case "respuesta":
                            //Si es 3 buscamos nulos
                            String valor = (String) e.getValue();
                            if (valor.equals("3")) {
                                strQuery += "AND s.respuesta is null ";
                            } else {
                                strQuery += "AND s.respuesta = " + (String) e.getValue() + " ";
                            }
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "s." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "s.fechaHoraSolicitud DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }
            List<WsTransacciones> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (WsTransacciones per : list) {
                listResult.add(castEntidadNegocio(per));
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
    public WsTransaccion consultar(int id) throws Exception {
        WsTransaccion objRes = null;
        try {
            objRes = castEntidadNegocioAmpliado((WsTransacciones) getEntityManager().find(WsTransacciones.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public WsTransaccion eliminar(int id) throws Exception {
        WsTransaccion obj = null;
        try {
            WsTransacciones ent = getEntityManager().find(WsTransacciones.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
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

    public static WsTransaccion castEntidadNegocio(WsTransacciones per) {
        WsTransaccion obj = new WsTransaccion();
        obj.setId(per.getId());
        obj.setEstado(per.getEstado());
        obj.setIpSolicitud(per.getIpSolicitud());
        obj.setUsuario(per.getUsuario());
        obj.setToken(per.getToken());
        obj.setFechaHoraSolicitud(per.getFechaHoraSolicitud());
        obj.setFechaHoraRespuesta(per.getFechaHoraRespuesta());
        obj.setContingencia(per.getContingencia());
//        obj.setRespuesta(per.getRespuesta());
        if (per.getWsServiciosId() != null) {
            obj.setWsServicio(
                    new WsServicio(
                            per.getWsServiciosId().getId(),
                            per.getWsServiciosId().getNombre(),
                            per.getWsServiciosId().getDescripcion()
                    )
            );
        }
        if (per.getWsConexionesId() != null) {
            obj.setWsConexion(
                    new WsConexion(
                            per.getWsConexionesId().getId(),
                            per.getWsConexionesId().getNombre()
                    )
            );
        }
        return obj;
    }

    public static WsTransaccion castEntidadNegocioAmpliado(WsTransacciones per) {
        WsTransaccion obj = new WsTransaccion();
        obj.setId(per.getId());
        obj.setEstado(per.getEstado());
        obj.setIpSolicitud(per.getIpSolicitud());
        obj.setUsuario(per.getUsuario());
        obj.setToken(per.getToken());
        obj.setFechaHoraSolicitud(per.getFechaHoraSolicitud());
        obj.setFechaHoraRespuesta(per.getFechaHoraRespuesta());
        obj.setContingencia(per.getContingencia());
        if (per.getWsServiciosId() != null) {
            obj.setWsServicio(
                    new WsServicio(
                            per.getWsServiciosId().getId(),
                            per.getWsServiciosId().getNombre(),
                            per.getWsServiciosId().getDescripcion()
                    )
            );
        }
        if (per.getWsConexionesId() != null) {
            obj.setWsConexion(
                    new WsConexion(
                            per.getWsConexionesId().getId(),
                            per.getWsConexionesId().getNombre()
                    )
            );
        }
        return obj;
    }

}
