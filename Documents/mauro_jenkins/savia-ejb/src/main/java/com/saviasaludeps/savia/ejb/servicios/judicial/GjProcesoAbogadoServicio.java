/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.judicial;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjAbogado;
import com.saviasaludeps.savia.dominio.judicial.GjProceso;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoAbogado;
import com.saviasaludeps.savia.ejb.entidades.GjAbogados;
import com.saviasaludeps.savia.ejb.entidades.GjProcesoAbogados;
import com.saviasaludeps.savia.ejb.entidades.GjProcesos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoAbogadoRemoto;

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
@Remote(GjProcesoAbogadoRemoto.class)
public class GjProcesoAbogadoServicio extends GenericoServicio implements GjProcesoAbogadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM GjProcesoAbogados t "
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
    public List<GjProcesoAbogado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GjProcesoAbogado> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM GjProcesoAbogados t "
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
            List<GjProcesoAbogados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GjProcesoAbogados per : list) {
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
    public List<GjProcesoAbogado> consultarListaPorIdProceso(int idProceso) throws Exception {
        List<GjProcesoAbogado> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GjProcesoAbogados p "
                    + "WHERE p.gjProcesosId.id = " + idProceso + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<GjProcesoAbogados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjProcesoAbogados usuarios : list) {
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
    public List<GjProcesoAbogado> consultarListaPorIdAbogado(int idAbogado) throws Exception {
        List<GjProcesoAbogado> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GjProcesoAbogados p "
                    + "WHERE p.gjAbogadosId.id = " + idAbogado + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<GjProcesoAbogados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjProcesoAbogados usuarios : list) {
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
    public GjProcesoAbogado consultar(int id) throws Exception {
        GjProcesoAbogado objRes = null;
        try {
            GjProcesoAbogados per = getEntityManager().find(GjProcesoAbogados.class, id);
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
    public int insertar(GjProcesoAbogado obj) throws Exception {
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
    public void actualizar(GjProcesoAbogado obj) throws Exception {
        try {
            String hql = "UPDATE GjProcesoAbogados SET "
                    + " gjProcesosId.id = :gjProcesosId,"
                    + " gjAbogadosId.id = :gjAbogadosId,"
                    + " fechaInicio = :fechaInicio,"
                    + " fechaFin = :fechaFin,"
                    + " activo = :activo,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);

            query.setParameter("gjProcesosId", obj.getGjProcesosId().getId());
            query.setParameter("gjAbogadosId", obj.getGjAbogadosId().getId());
            query.setParameter("fechaInicio", obj.getFechaInicio());
            query.setParameter("fechaFin", obj.getFechaFin());
            query.setParameter("activo", obj.isActivo());

            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public GjProcesoAbogado eliminar(int id) throws Exception {
        GjProcesoAbogado obj = null;
        try {
            GjProcesoAbogados ent = getEntityManager().find(GjProcesoAbogados.class, id);
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

    public static GjProcesoAbogado castEntidadNegocio(GjProcesoAbogados per) {
        GjProcesoAbogado obj = new GjProcesoAbogado();
        obj.setId(per.getId());
        if (per.getGjProcesosId() != null) {
            obj.setGjProcesosId(new GjProceso(per.getGjProcesosId().getId()));
        }
        if (per.getGjAbogadosId() != null) {
            obj.setGjAbogadosId(new GjAbogado(per.getGjProcesosId().getId()));
        }
        obj.setFechaInicio(per.getFechaInicio());
        obj.setFechaFin(per.getFechaFin());
        obj.setActivo(per.getActivo());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    public static GjProcesoAbogados castNegocioEntidad(GjProcesoAbogado obj) {
        GjProcesoAbogados per = new GjProcesoAbogados();
        per.setId(obj.getId());
        
        if (obj.getGjProcesosId() != null) {
            per.setGjProcesosId(new GjProcesos(obj.getGjProcesosId().getId()));
        }
        if (obj.getGjAbogadosId() != null) {
            per.setGjAbogadosId(new GjAbogados(obj.getGjAbogadosId().getId()));
        }

        per.setFechaInicio(obj.getFechaInicio());
        per.setActivo(obj.isActivo());
        
        per.setFechaFin(obj.getFechaFin());
        
        
        //Auditoría
        per.setUsuarioCrea(obj.getGjProcesosId().getUsuarioCrea());
        per.setTerminalCrea(obj.getGjProcesosId().getTerminalCrea());
        per.setFechaHoraCrea(obj.getGjProcesosId().getFechaHoraCrea());
        return per;
    }
}
