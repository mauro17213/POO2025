/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnAlerta;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnAlertas;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.GnAlertaRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(GnAlertaRemoto.class)
public class GnAlertaServicio extends GenericoServicio implements GnAlertaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GnAlertas c "
                    + " WHERE 1 = 1 ";            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gnUsuarioId.usuario":
                            strQuery += "AND c.gnUsuariosId.usuario LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "severidad":
                            strQuery += "AND c.severidad = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado =" + (String) e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND c.nombre  LIKE '%" + (String) e.getValue() + "%' ";
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
    public List<GnAlerta> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GnAlerta> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GnAlertas c WHERE 1 = 1 ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gnUsuarioId.usuario":
                            strQuery += "AND c.gnUsuariosId.usuario LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "severidad":
                            strQuery += "AND c.severidad = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado =" + (String) e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND c.nombre  LIKE '%" + (String) e.getValue() + "%' ";
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
            List<GnAlertas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnAlertas alerta : list) {
                listaResultados.add(castEntidadNegocio(alerta));
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
    public GnAlerta consultar(int id) throws Exception {
        GnAlerta objRes = null;
        try {
            objRes = castEntidadNegocio((GnAlertas) getEntityManager().find(GnAlertas.class, id));
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
    public int insertar(GnAlerta obj) throws Exception {
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
    public void actualizar(GnAlerta obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GnAlertas a SET ";
            strQuery += "a.gnUsuariosId.id = :gnUsuariosId ,";
            strQuery += "a.severidad = :severidad ,";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.nombre = :nombre ,";
            strQuery += "a.descripcion = :descripcion ,";
            strQuery += "a.fechaHoraLee = :fechaHoraLee ,";
            strQuery += "a.fechaHoraDescarta = :fechaHoraDescarta ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gnUsuariosId", obj.getGnUsuarioId().getId());
            query.setParameter("severidad", obj.getSeveridad());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("fechaHoraLee", obj.getFechaHoraLee());
            query.setParameter("fechaHoraDescarta", obj.getFechaHoraDescarta());
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
    public GnAlerta eliminar(int id) throws Exception {
        GnAlerta obj = null;
        try {
            GnAlertas ent = getEntityManager().find(GnAlertas.class, id);
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
    
    private GnAlerta castEntidadNegocio(GnAlertas entidad) {
        GnAlerta negocio = new GnAlerta();
        negocio.setId(entidad.getId());
        negocio.setGnUsuarioId(new Usuario(entidad.getGnUsuariosId().getId(), entidad.getGnUsuariosId().getUsuario(), entidad.getGnUsuariosId().getNombre()));
        negocio.setSeveridad(entidad.getSeveridad());
        negocio.setEstado(entidad.getEstado());
        negocio.setNombre(entidad.getNombre());
        negocio.setDescripcion(entidad.getDescripcion());
        negocio.setFechaHoraLee(entidad.getFechaHoraLee());
        negocio.setFechaHoraDescarta(entidad.getFechaHoraDescarta());
        //Auditoria
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private GnAlertas castNegocioEntidad(GnAlerta negocio) {
        GnAlertas entidad = new GnAlertas();
        entidad.setGnUsuariosId(new GnUsuarios(negocio.getGnUsuarioId().getId()));
        entidad.setSeveridad(negocio.getSeveridad());
        entidad.setEstado(negocio.getEstado());
        entidad.setNombre(negocio.getNombre());
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setFechaHoraLee(negocio.getFechaHoraLee());
        entidad.setFechaHoraDescarta(negocio.getFechaHoraDescarta());
        //Auditoria
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<GnAlerta> consultaPorIdUsuario(int idUsuario) throws Exception {
        List<GnAlerta> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GnAlertas c WHERE c.gnUsuariosId.id = "+idUsuario+
                    " ORDER BY c.severidad DESC";            
            List<GnAlertas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnAlertas alerta : list) {
                listaResultados.add(castEntidadNegocio(alerta));
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
    public List<GnAlerta> consultaPorIdUsuarioNoDescartadas(int idUsuario) throws Exception {
        List<GnAlerta> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GnAlertas c WHERE c.gnUsuariosId.id = "+idUsuario+
                    " AND c.estado > 0 ORDER BY c.severidad DESC";   
            List<GnAlertas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnAlertas alerta : list) {
                listaResultados.add(castEntidadNegocio(alerta));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            listaResultados = new ArrayList();
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }
    
}
