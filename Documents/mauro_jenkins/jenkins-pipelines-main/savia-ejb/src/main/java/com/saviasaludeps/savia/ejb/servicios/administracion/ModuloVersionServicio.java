/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.ModuloVersion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnModuloVersiones;
import com.saviasaludeps.savia.ejb.entidades.GnModulos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.ModuloVersionRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(ModuloVersionRemoto.class)
public class ModuloVersionServicio extends GenericoServicio implements ModuloVersionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;

        String strQuery = "SELECT COUNT(mv) FROM GnModuloVersiones mv WHERE 1 = 1";
        try {
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND mv.gnModulosId.id = " + (String) e.getValue() + " ";
                            break;
                        case "version":
                            strQuery += " AND mv.version LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaVersion":
                            strQuery += " AND mv.fechaVersion = " + (String) e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += " AND mv.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND mv.fechaHoraCrea = " + (String) e.getValue() + " ";
                            break;
                        case "descripcion":
                            strQuery += " AND mv.descripcion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return cant;
    }

    @Override
    public List<ModuloVersion> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<ModuloVersion> listResult = new ArrayList<>();
        String strQuery = "SELECT mv FROM GnModuloVersiones mv WHERE 1 = 1 ";
        try {
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND mv.gnModulosId.id = " + (String) e.getValue() + " ";
                            break;
                        case "version":
                            strQuery += " AND mv.version LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaVersion":
                            strQuery += " AND mv.fechaVersion = " + (String) e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += " AND mv.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND mv.fechaHoraCrea = " + (String) e.getValue() + " ";
                            break;
                        case "descripcion":
                            strQuery += " AND mv.descripcion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "mv." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "mv.fechaVersion DESC, mv.fechaHoraCrea DESC";
            }
            List<GnModuloVersiones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnModuloVersiones item : list) {
                listResult.add(castEntidadNegocio(item));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return listResult;
    }

    @Override
    public List<ModuloVersion> consultarTodos() throws java.lang.Exception {
        List<ModuloVersion> listResult = new ArrayList<>();
        String sql = "FROM ModuloVersiones ";
        try {
            Query query = getEntityManager().createQuery(sql);
            List<GnModuloVersiones> list = query.getResultList();
            for (GnModuloVersiones item : list) {
                listResult.add(castEntidadNegocio(item));
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
    public ModuloVersion consultar(int id) throws java.lang.Exception {
        ModuloVersion objResult = new ModuloVersion();
        try {
            objResult = castEntidadNegocio(getEntityManager().find(GnModuloVersiones.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return objResult;
    }

    @Override
    public List<ModuloVersion> consultarByModulo(int id) throws Exception {
        List<ModuloVersion> listResult = new ArrayList<>();
        String sql = "FROM GnModuloVersiones mv "
                + "WHERE mv.gnModulosId.id = :id "
                + "ORDER BY mv.fechaVersion DESC, mv.fechaHoraCrea DESC";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("id", id);
            List<GnModuloVersiones> list = query.getResultList();
            for (GnModuloVersiones item : list) {
                listResult.add(castEntidadNegocio(item));
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
    public ModuloVersion consultarActualByModulo(int id) throws Exception {
        ModuloVersion ObjectResult = new ModuloVersion();
        String sql = "FROM GnModuloVersiones mv "
                + "WHERE mv.gnModulosId.id = :id "
                + "ORDER BY mv.fechaVersion DESC, mv.fechaHoraCrea DESC";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("id", id);
            List<GnModuloVersiones> list = query.setMaxResults(1).getResultList();
            for (GnModuloVersiones item : list) {
                ObjectResult = castEntidadNegocio(item);
            }
        } catch (NoResultException e) {
            ObjectResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ObjectResult;
    }

    @Override
    public int insertar(ModuloVersion objeto) throws Exception {
        int id = 0;
        try {
            id = getEntityManager().merge(castNegocioEntidad(objeto)).getId();
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    public static ModuloVersion castEntidadNegocio(GnModuloVersiones per) {
        ModuloVersion obj = new ModuloVersion();
        obj.setDescripcion(per.getDescripcion());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setFechaVersion(per.getFechaVersion());
        obj.setId(per.getId());
        if (per.getGnModulosId() != null) {
            obj.setModulo(new Modulo(per.getGnModulosId().getId()));
            obj.getModulo().setNombre(per.getGnModulosId().getNombre());
        }
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setVersion(per.getVersion());
        return obj;
    }

    public static GnModuloVersiones castNegocioEntidad(ModuloVersion obj) {
        GnModuloVersiones per = new GnModuloVersiones();
        per.setDescripcion(obj.getDescripcion());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setFechaVersion(obj.getFechaVersion());
        per.setId(obj.getId());
        per.setGnModulosId(obj.getModulo() != null ? new GnModulos(obj.getModulo().getId()) : null);
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setVersion(obj.getVersion());
        return per;
    }

}
