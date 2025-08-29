/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegValidacionDerecho;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegValidacionDerechos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.ValidacionDerechosRemoto;

/**
 *
 * @author jose perez hernandez
 */
@Stateless
@Remote(ValidacionDerechosRemoto.class)
public class ValidacionDerechosServicio extends GenericoServicio implements ValidacionDerechosRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegValidacionDerechos p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " +  e.getValue() + " ";
                            break;
                        case "registrosCargados":
                            strQuery += "AND p.registrosCargados = " + e.getValue() + " ";
                            break;
                        case "registrosEncontrados":
                            strQuery += "AND p.registrosEncontrados = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "fechaHoraFin":
                            strQuery += "AND p.fechaHoraFin = '" + e.getValue().toString() + "' ";
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
    public List<AsegValidacionDerecho> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegValidacionDerecho> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM AsegValidacionDerechos p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " +  e.getValue() + " ";
                            break;
                        case "registrosCargados":
                            strQuery += "AND p.registrosCargados = " + e.getValue() + " ";
                            break;
                        case "registrosEncontrados":
                            strQuery += "AND p.registrosEncontrados = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "fechaHoraFin":
                            strQuery += "AND p.fechaHoraFin = '" + e.getValue().toString() + "' ";
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
            List<AsegValidacionDerechos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (AsegValidacionDerechos per : list) {
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
    public AsegValidacionDerecho consultar(int id) throws Exception {
        AsegValidacionDerecho objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AsegValidacionDerechos) getEntityManager().find(AsegValidacionDerechos.class, id));
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
    public int insertar(AsegValidacionDerecho obj) throws Exception {
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
    public void actualizar(AsegValidacionDerecho obj) throws Exception {
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
    public AsegValidacionDerecho eliminar(int id) throws Exception {
        AsegValidacionDerecho obj = null;
        try {
            AsegValidacionDerechos ent = getEntityManager().find(AsegValidacionDerechos.class, id);
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
    public List<AsegValidacionDerecho> consultarTodos() throws Exception {
        List<AsegValidacionDerecho> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegValidacionDerechos p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegValidacionDerechos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegValidacionDerechos per : list) {
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

    public static AsegValidacionDerecho castEntidadNegocioLargo(AsegValidacionDerechos per) {
        AsegValidacionDerecho obj = new AsegValidacionDerecho();
        obj.setId(per.getId());
        obj.setEstado(per.getEstado());
        obj.setFechaHoraInicio(per.getFechaHoraInicio());
        obj.setFechaHoraFin(per.getFechaHoraFin());
        obj.setRegistrosCargados(per.getRegistrosCargados());
        obj.setRegistrosEncontrados(per.getRegistrosEncontrados());
        obj.setRuta(per.getRuta());
        obj.setArchivo(per.getArchivo());
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        //objetos
        return obj;
    }

    public static AsegValidacionDerechos castNegocioEntidadLargo(AsegValidacionDerecho obj) {
        AsegValidacionDerechos per = new AsegValidacionDerechos();
        per.setId(obj.getId());
        per.setEstado(obj.getEstado());
        per.setFechaHoraInicio(obj.getFechaHoraInicio());
        per.setFechaHoraFin(obj.getFechaHoraFin());
        per.setRegistrosCargados(obj.getRegistrosCargados());
        per.setRegistrosEncontrados(obj.getRegistrosEncontrados());
        per.setRuta(obj.getRuta());
        per.setArchivo(obj.getArchivo());
        // auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        //objetos
        return per;
    }

    @Override
    public int consultarValidacionDerechosPorEstado(int estado) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegValidacionDerechos p "
                    + "WHERE p.estado = " + estado;
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
}
