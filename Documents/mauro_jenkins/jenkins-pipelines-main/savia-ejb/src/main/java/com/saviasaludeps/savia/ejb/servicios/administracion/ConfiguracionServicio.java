/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnConfiguracion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnConfiguraciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.administracion.ConfiguracionRemoto;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(ConfiguracionRemoto.class)
public class ConfiguracionServicio extends GenericoServicio implements ConfiguracionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(m) FROM GnConfiguraciones m "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND m.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += "AND m.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "valor":
                            strQuery += "AND m.valor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
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
    public List<GnConfiguracion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GnConfiguracion> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnConfiguraciones m "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND m.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += "AND m.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "valor":
                            strQuery += "AND m.valor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "m." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "m.id ASC ";
            }
            List<GnConfiguraciones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnConfiguraciones per : list) {
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
    public GnConfiguracion consultar(int id) throws Exception {
        GnConfiguracion objRes = null;
        try {
            objRes = castEntidadNegocio((GnConfiguraciones) getEntityManager().find(GnConfiguraciones.class, id));
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
    public int insertar(GnConfiguracion obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizar(GnConfiguracion obj) throws Exception {
        try {
            String sql = "UPDATE GnConfiguraciones "
                    + "SET nombre = :nombre, "
                    + "valor = :valor "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("valor", obj.getValor());
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
    public int consultarIdNuevo() throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT MAX(m.id) + 1 "
                    + "FROM GnConfiguraciones m ";
            cant = (int) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
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
    public HashMap<Integer, String> consultarHash() {
        HashMap<Integer, String> hashResult = new HashMap();
        String strQuery = "FROM GnConfiguraciones ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnConfiguraciones> list = query.getResultList();
            for (GnConfiguraciones per : list) {
                hashResult.put(per.getId(), per.getValor());
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            hashResult = new HashMap();
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }
    
    public static GnConfiguracion castEntidadNegocio(GnConfiguraciones ent) {
        GnConfiguracion obj = new GnConfiguracion();
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setValor(ent.getValor());
        return obj;
    }

    public static GnConfiguraciones castNegocioEntidad(GnConfiguracion obj) {
        GnConfiguraciones ent = new GnConfiguraciones();
        ent.setId(obj.getId());
        ent.setNombre(obj.getNombre());
        ent.setValor(obj.getValor());
        return ent;
    }
}
