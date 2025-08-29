/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.judicial;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjProceso;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoPretencion;
import com.saviasaludeps.savia.ejb.entidades.GjProcesoPretenciones;
import com.saviasaludeps.savia.ejb.entidades.GjProcesos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoPretencionRemoto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author bsgomez
 */
@Stateless
@Remote(GjProcesoPretencionRemoto.class)
public class GjProcesoPretencionServicio extends GenericoServicio implements GjProcesoPretencionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM GjProcesoPretenciones t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "xxx":
                            strQuery += "AND t.xxx LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "xx":
                            strQuery += "AND t.xx LIKE '" + (String) e.getValue() + "%' ";
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
    public List<GjProcesoPretencion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GjProcesoPretencion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM GjProcesoPretenciones t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "xxx":
                            strQuery += "AND t.xxx LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "xx":
                            strQuery += "AND t.xx LIKE '" + (String) e.getValue() + "%' ";
                            break;

                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "t." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id ASC";
            }
            List<GjProcesoPretenciones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GjProcesoPretenciones per : list) {
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
    public List<GjProcesoPretencion> consultarListaPorIdProceso(int idProceso) throws Exception {
        List<GjProcesoPretencion> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GjProcesoPretenciones p "
                    + "WHERE p.tuGruposId.id = " + idProceso + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<GjProcesoPretenciones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjProcesoPretenciones usuarios : list) {
                listaResultados.add(castEntidadNegocio(usuarios));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public GjProcesoPretencion consultar(int id) throws Exception {
        GjProcesoPretencion objRes = null;
        try {
            GjProcesoPretenciones per = getEntityManager().find(GjProcesoPretenciones.class, id);
            if (per != null) {
                objRes = castEntidadNegocio(per);
            }
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
    public int insertar(GjProcesoPretencion obj) throws Exception {
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
    public void actualizar(GjProcesoPretencion obj) throws Exception {
        try {
            String hql = "UPDATE GjProcesoPretenciones SET "
                    + " gjProcesosId.id = :gjProcesosId,"
                    + " maePretencionId = :maePretencionId,"
                    + " maePretencionCodigo = :maePretencionCodigo,"
                    + " maePretencionValor = :maePretencionValor ";
                    
            hql += " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("gjProcesosId", obj.getGjProcesosId().getId());
            query.setParameter("maePretencionId", obj.getMaePretencionId());
            query.setParameter("maePretencionCodigo", obj.getMaePretencionCodigo());
            query.setParameter("maePretencionValor", obj.getMaePretencionValor());
           
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public GjProcesoPretencion eliminar(int id) throws Exception {
        GjProcesoPretencion obj = null;
        try {
            GjProcesoPretenciones ent = getEntityManager().find(GjProcesoPretenciones.class, id);
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

    public static GjProcesoPretencion castEntidadNegocio(GjProcesoPretenciones per) {
        GjProcesoPretencion obj = new GjProcesoPretencion();
        obj.setId(per.getId());
        if (per.getGjProcesosId() != null) {
            obj.setGjProcesosId(new GjProceso(per.getGjProcesosId().getId()));
        }
        obj.setMaePretencionId(per.getMaePretencionId());
        obj.setMaePretencionCodigo(per.getMaePretencionCodigo());
        obj.setMaePretencionValor(per.getMaePretencionValor());

        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());

        return obj;
    }

    public static GjProcesoPretenciones castNegocioEntidad(GjProcesoPretencion obj) {
        GjProcesoPretenciones per = new GjProcesoPretenciones();
        per.setId(obj.getId());

        if (obj.getGjProcesosId() != null) {
            per.setGjProcesosId(new GjProcesos(obj.getGjProcesosId().getId()));
        }

        per.setMaePretencionId(obj.getMaePretencionId());
        per.setMaePretencionCodigo(obj.getMaePretencionCodigo());
        per.setMaePretencionValor(obj.getMaePretencionValor());

        //Auditoría
        per.setUsuarioCrea(obj.getGjProcesosId().getUsuarioCrea());
        per.setTerminalCrea(obj.getGjProcesosId().getTerminalCrea());
        per.setFechaHoraCrea(obj.getGjProcesosId().getFechaHoraCrea());

        return per;
    }
}
