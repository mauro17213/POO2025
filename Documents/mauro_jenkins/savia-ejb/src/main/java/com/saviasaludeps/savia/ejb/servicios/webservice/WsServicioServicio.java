/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservice;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.webservice.WsServicio;
import com.saviasaludeps.savia.ejb.entidades.WsConexionesServicios;
import com.saviasaludeps.savia.ejb.entidades.WsServicios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.webservice.WsServicioRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author rpalacios
 */
@Stateless
@Remote(WsServicioRemoto.class)
@Local(WsServicioLocal.class)
public class WsServicioServicio extends GenericoServicio implements WsServicioLocal, WsServicioRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM WsServicios s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND s.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND s.descripcion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND s.activo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cont":
                            strQuery += "AND s.cont = '" + (String) e.getValue() + "' ";
                            break;
                        case "wsExternoServicio.nombre":
                            strQuery += "AND s.wsExternoServiciosId.id = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public List<WsServicio> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<WsServicio> listResult = new ArrayList();
        try {
            String strQuery = "FROM WsServicios s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND s.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND s.descripcion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND s.activo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cont":
                            strQuery += "AND s.cont = '" + (String) e.getValue() + "' ";
                            break;
                        case "wsExternoServicio.nombre":
                            strQuery += "AND s.wsExternoServiciosId.id = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("wsExternoServicio.nombre")) {
                    strQuery += "s.wsExternoServiciosId.nombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                    strQuery += "s." + paramConsulta.getOrden() + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "s.nombre ASC";
            }
            List<WsServicios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (WsServicios per : list) {
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
    public WsServicio consultar(int id) throws Exception {
        WsServicio objRes = null;
        try {
            objRes = castEntidadNegocio((WsServicios) getEntityManager().find(WsServicios.class, id));
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
    public int insertar(WsServicio obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(WsServicio obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidad(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public WsServicio eliminar(int id) throws Exception {
        WsServicio obj = null;
        try {
            WsServicios ent = getEntityManager().find(WsServicios.class, id);
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

    @Override
    public List<WsServicio> consultarTodas() throws Exception {
        List<WsServicio> listResult = new ArrayList();
        try {
            String strQuery = "FROM WsServicios s "
                    + "ORDER BY s.nombre ASC";
            List<WsServicios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (WsServicios per : list) {
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
    public List<WsServicio> consultarListaPorConexion(int conexionId) throws Exception {
        List<WsServicio> listResult = new ArrayList();
        try {
            String strQuery = "FROM WsConexionesServicios c "
                    + "WHERE c.wsConexionesId.id = :conexionId "
                    + "ORDER BY c.wsServiciosId.nombre ASC";
            List<WsConexionesServicios> list = getEntityManager().createQuery(strQuery)
                    .setParameter("conexionId", conexionId)
                    .getResultList();
            for (WsConexionesServicios per : list) {
                listResult.add(castEntidadNegocio(per.getWsServiciosId()));
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

    public static WsServicio castEntidadNegocio(WsServicios per) {
        WsServicio obj = new WsServicio();
        obj.setId(per.getId());
        obj.setNombre(per.getNombre());
        obj.setDescripcion(per.getDescripcion());
        obj.setTiempoCont(per.getTiempoCont());
        obj.setActivo(per.getActivo());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static WsServicios castNegocioEntidad(WsServicio obj) {
        WsServicios per = new WsServicios();
        per.setId(obj.getId());
        per.setNombre(obj.getNombre());
        per.setDescripcion(obj.getDescripcion());
        per.setTiempoCont(obj.getTiempoCont());
        per.setActivo(obj.isActivo());
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
    
}
