/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Log;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.administracion.LogRemoto;
import com.saviasaludeps.savia.ejb.entidades.GnLogs;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(LogRemoto.class)
public class LogServicio extends GenericoServicio implements LogRemoto {

    @Override
    public int consultarCantidadTodos(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(l) FROM GnLogs l "
                    + "WHERE fechaHora BETWEEN :fh_inicio AND :fh_fin ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "usuario":
                            strQuery += "AND usuario LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "empresaUsuario":
                            strQuery += "AND empresaUsuario LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "empresaEjecucion":
                            strQuery += "AND empresaEjecucion LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "modulo":
                            strQuery += "AND modulo LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "opcion":
                            strQuery += "AND opcion LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "accion":
                            strQuery += "AND accion LIKE '" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP)
                    .setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP)
                    .getSingleResult();
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
    public List<Log> consultarTodos(ParamConsulta paramConsulta) throws Exception {
        List<Log> listResult = new ArrayList();
        try {
            //Armado de HQL
            String strQuery = "FROM GnLogs "
                    + "WHERE fechaHora BETWEEN :fh_inicio AND :fh_fin ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "usuario":
                            strQuery += "AND usuario LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "empresaUsuario":
                            strQuery += "AND empresaUsuario LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "empresaEjecucion":
                            strQuery += "AND empresaEjecucion LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "modulo":
                            strQuery += "AND modulo LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "opcion":
                            strQuery += "AND opcion LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "accion":
                            strQuery += "AND accion LIKE '" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            if (paramConsulta.getOrden() != null) {
                strQuery += " ORDER BY " + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "ORDER BY fechaHora DESC ";
            }
            List<GnLogs> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP)
                    .setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP)
                    .getResultList();
            for (GnLogs obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public Log consultar(int id) throws Exception {
        Log objRes = null;
        try {
            objRes = castEntidadNegocio((GnLogs) getEntityManager().find(GnLogs.class, id));
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
    public int insertar(Log obj) throws Exception {
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

    public static Log castEntidadNegocio(GnLogs per) {
        Log obj = new Log();
        obj.setId(per.getId());
        obj.setAccion(per.getAccion());
        obj.setFechaHora(per.getFechaHora());
        obj.setDescripcion(per.getDescripcion());
        obj.setIpPrivada(per.getIpPrivada());
        obj.setIpPublica(per.getIpPublica());
        //Módulo (Opción)
        obj.setModulo(per.getModulo());
        obj.setOpcion(per.getOpcion());
        //Usuario
        obj.setEmpresaEjecucion(per.getEmpresaEjecucion());
        obj.setUsuario(per.getUsuario());
        obj.setEmpresaUsuario(per.getEmpresaUsuario());
        return obj;
    }

    public static GnLogs castNegocioEntidad(Log obj) {
        GnLogs per = new GnLogs();
        per.setId(obj.getId());
        per.setAccion(obj.getAccion());
        per.setFechaHora(obj.getFechaHora());
//        per.setIpPrivada(obj.getIpPrivada());
        try {
            String ipPrivada = InetAddress.getLocalHost().getHostName();
            if (ipPrivada.length() > 16) {
                per.setIpPrivada(ipPrivada.substring(0, 16));
            } else {
                per.setIpPrivada(ipPrivada);
            }
        } catch (UnknownHostException ex) {
            per.setIpPrivada("0.0.0.0");
        }
        String ipPublica = obj.getIpPublica();
        if (ipPublica.length() > 16) {
            per.setIpPublica(ipPublica.substring(0, 16));
        } else {
            per.setIpPublica(ipPublica);
        }
        if (obj.getDescripcion().length() > 32768) {
            per.setDescripcion(obj.getDescripcion().substring(0,32768));
        } else {
            per.setDescripcion(obj.getDescripcion());
        }
//        per.setDescripcion(obj.getDescripcion());
        //Módulo (Opción)
        per.setModulo(obj.getModulo());
        per.setOpcion(obj.getOpcion());
        //Usuario
        per.setEmpresaEjecucion(obj.getEmpresaEjecucion());
        per.setUsuario(obj.getUsuario());
        per.setEmpresaUsuario(obj.getEmpresaUsuario());
        return per;
    }

    @Override
    public List<Log> consultarUltimos(String empresa, int cant) {
        List<Log> lista = new ArrayList();
        String strQuery = "FROM GnLogs "
                + "WHERE empresaEjecucion = :empresa_ejecucion "
                + "ORDER BY fechaHora DESC";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnLogs> list = query
                    .setFirstResult(0)
                    .setMaxResults(cant)
                    .setParameter("empresa_ejecucion", empresa)
                    .getResultList();
            for (GnLogs obj : list) {
                lista.add(castEntidadNegocio(obj));
            }
        } catch (Exception e) {
            lista = new ArrayList();
        } finally {
            cerrarEntityManager();
        }
        return lista;
    }

}
