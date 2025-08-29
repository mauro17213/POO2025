/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegCargas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.CargaRemoto;

/**
 *
 * @author jose perez hernandez
 */
@Stateless
@Remote(CargaRemoto.class)
public class CargaServicio extends GenericoServicio implements CargaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegCargas p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " +  e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<AsegCarga> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegCarga> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM AsegCargas p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " +  e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AsegCargas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (AsegCargas per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public AsegCarga consultar(int id) throws Exception {
        AsegCarga objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AsegCargas) getEntityManager().find(AsegCargas.class, id));
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
    public int insertar(AsegCarga obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadLargo(obj)).getId();
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
    public void actualizar(AsegCarga obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidadLargo(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegCarga eliminar(int id) throws Exception {
        AsegCarga obj = null;
        try {
            AsegCargas ent = getEntityManager().find(AsegCargas.class, id);
            if (ent != null) {
                obj = castEntidadNegocioLargo(ent);
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
    public List<AsegCarga> consultarTodos() throws Exception {
        List<AsegCarga> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegCargas p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegCargas per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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

    public static AsegCarga castEntidadNegocioLargo(AsegCargas per) {
        AsegCarga obj = new AsegCarga();
        obj.setId(per.getId());
        obj.setArchivo(per.getArchivo());
        obj.setDetalle(per.getDetalle());
        obj.setEstado(per.getEstado());
        obj.setExitosos(per.getExitosos());
        obj.setFallidos(per.getFallidos());
        obj.setFechaHoraInicio(per.getFechaHoraInicio());
        obj.setFechaHoraFin(per.getFechaHoraFin());
        obj.setTipo(per.getTipo());
        obj.setNombre(per.getNombre());
        obj.setRegistros(per.getRegistros());
        obj.setRuta(per.getRuta());
        //2025-02-12 jyperez nuevos campos
        obj.setExiste(per.getExiste());
        obj.setRespNombre(per.getRespNombre());
        obj.setRespRuta(per.getRespRuta());
        obj.setRespArchivo(per.getRespArchivo());
        obj.setRespExiste(per.getRespExiste());
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        //objetos

        return obj;
    }

    public static AsegCargas castNegocioEntidadLargo(AsegCarga obj) {
        AsegCargas per = new AsegCargas();
        per.setId(obj.getId());
        per.setId(obj.getId());
        per.setArchivo(obj.getArchivo());
        per.setDetalle(obj.getDetalle());
        per.setEstado(obj.getEstado());
        per.setExitosos(obj.getExitosos());
        per.setFallidos(obj.getFallidos());
        per.setFechaHoraInicio(obj.getFechaHoraInicio());
        per.setFechaHoraFin(obj.getFechaHoraFin());
        per.setTipo(obj.getTipo());
        per.setNombre(obj.getNombre());
        per.setRegistros(obj.getRegistros());
        per.setRuta(obj.getRuta());
        //2025-02-12 jyperez nuevos campos
        per.setExiste(obj.isExiste());
        per.setRespNombre(obj.getRespNombre());
        per.setRespRuta(obj.getRespRuta());
        per.setRespArchivo(obj.getRespArchivo());
        per.setRespExiste(obj.isRespExiste());
        // auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        
        return per;
    }

    @Override
    public int consultarCantidadRegistrosProcesadosPorEstado(int id, int estado) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegDetalleCargas p "
                    + "WHERE p.id > 0 "
                    + "AND p.estado = " + estado + " "
                    + "AND p.asegCargasId.id = " + id + " ";
            
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public AsegCarga consultarSiguienteCarga(int estado) throws java.lang.Exception {
        AsegCarga objResult = null;
        try {
            String strQuery = "FROM AsegCargas p "
                    + " WHERE p.estado = " + estado
                    + " ORDER BY p.id ASC ";
            List<AsegCargas> result = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if(!result.isEmpty()) {
                objResult = castEntidadNegocioLargo(result.get(0));
            }
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }
   
}
