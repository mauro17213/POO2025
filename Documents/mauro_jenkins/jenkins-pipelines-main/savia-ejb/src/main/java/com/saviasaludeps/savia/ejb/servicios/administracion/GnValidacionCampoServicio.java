/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnValidacionCampo;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnMaestroTipos;
import com.saviasaludeps.savia.ejb.entidades.GnValidacionCampos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.GnValidacionCampoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(GnValidacionCampoRemoto.class)
public class GnValidacionCampoServicio extends GenericoServicio implements GnValidacionCampoRemoto {

    @Override
    public List<GnValidacionCampo> consultarPorTipo(String tipo) throws Exception {
         List<GnValidacionCampo> listResult = new ArrayList<>();
        String strQuery = "FROM GnValidacionCampos e "
                + "WHERE e.gnMaestroTiposTipo.tipo = '" + tipo + "' "
                + "ORDER BY e.nombre ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnValidacionCampos> list = query.getResultList();
            for (GnValidacionCampos obj : list) {
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
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GnValidacionCampos c "
                    + " WHERE 1 = 1 ";            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "validator":
                            strQuery += "AND c.validator LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND c.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "expresionRegular":
                            strQuery += "AND c.expresionRegular LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "gnMaestroTiposTipo.nombre":
                            strQuery += "AND c.gnMaestroTiposTipo.nombre  LIKE '%" + (String) e.getValue() + "%' ";
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
    public List<GnValidacionCampo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GnValidacionCampo> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GnValidacionCampos c WHERE 1 = 1 ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "validator":
                            strQuery += "AND c.validator LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND c.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "expresionRegular":
                            strQuery += "AND c.expresionRegular LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "gnMaestroTiposTipo.nombre":
                            strQuery += "AND c.gnMaestroTiposTipo.nombre  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id DESC";
            }
            List<GnValidacionCampos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnValidacionCampos validacion : list) {
                listaResultados.add(castEntidadNegocio(validacion));
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
    public GnValidacionCampo consultar(int id) throws Exception {
        GnValidacionCampo objRes = null;
        try {
            objRes = castEntidadNegocio((GnValidacionCampos) getEntityManager().find(GnValidacionCampos.class, id));
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
    public int insertar(GnValidacionCampo obj) throws Exception {
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
    public void actualizar(GnValidacionCampo obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GnValidacionCampos a SET ";
            strQuery += "a.gnMaestroTiposTipo.tipo = :gnMaestroTipo ,";
            strQuery += "a.validator = :validator ,";
            strQuery += "a.nombre = :nombre ,";
            strQuery += "a.expresionRegular = :expresionRegular ";
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setParameter("gnMaestroTipo", obj.getGnMaestroTiposTipo().getTipo());
            query.setParameter("validator", obj.getValidator());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("expresionRegular", obj.getExpresionRegular());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public GnValidacionCampo eliminar(int id) throws Exception {
        GnValidacionCampo obj = null;
        try {
            GnValidacionCampos ent = getEntityManager().find(GnValidacionCampos.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ELIMINAR, e);
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    private GnValidacionCampo castEntidadNegocio(GnValidacionCampos entidad) {
        GnValidacionCampo negocio = new GnValidacionCampo();
        negocio.setId(entidad.getId());
        negocio.setNombre(entidad.getNombre());
        negocio.setValidator(entidad.getValidator());
        negocio.setExpresionRegular(entidad.getExpresionRegular());
        negocio.setGnMaestroTiposTipo(new MaestroTipo(entidad.getGnMaestroTiposTipo().getTipo(), 
                entidad.getGnMaestroTiposTipo().getNombre(), 
                entidad.getGnMaestroTiposTipo().getActivo()));
        return negocio;
    }
    
    private GnValidacionCampos castNegocioEntidad(GnValidacionCampo negocio) {
        GnValidacionCampos entidad = new GnValidacionCampos();
        entidad.setNombre(negocio.getNombre());
        entidad.setValidator(negocio.getValidator());
        entidad.setExpresionRegular(negocio.getExpresionRegular());
        entidad.setGnMaestroTiposTipo(new GnMaestroTipos(negocio.getGnMaestroTiposTipo().getTipo(), 
                negocio.getGnMaestroTiposTipo().getNombre(), 
                negocio.getGnMaestroTiposTipo().isActivo()));
        return entidad;
    }

    @Override
    public List<GnValidacionCampo> listarTodas() throws Exception {
        List<GnValidacionCampo> objResult = new ArrayList<>();
        String strQuery = "FROM GnValidacionCampos a "
                + "WHERE 1 = 1 ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnValidacionCampos> list = query.getResultList();
            if (list != null) {
                for (GnValidacionCampos validacion : list) {
                    objResult.add(castEntidadNegocio(validacion));
                }
            }
        } catch (NoResultException e) {
            objResult = new ArrayList<>();
        } catch (Exception e) {
            objResult = new ArrayList<>();
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }
    
}
