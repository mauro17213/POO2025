/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.solicitud;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.solicitud.GsMensaje;
import com.saviasaludeps.savia.ejb.entidades.GsMensajes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.solicitud.GsMensajeRemoto;
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
 * @author jramirez
 */
@Stateless
@Remote(GsMensajeRemoto.class)
public class GsMensajeServicio extends GenericoServicio implements GsMensajeRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM GsMensajes p "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND p.nombre = '" + e.getValue() + "' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = '" + e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = '" + e.getValue() + "' ";
                            break;
                        case "encabezado":
                            strQuery += "AND p.encabezado = '" + e.getValue() + "' ";
                            break;
                        case "mensajeLargo":
                            strQuery += "AND p.mensajeLargo = '" + e.getValue() + "' ";
                            break;
                        case "mensajeCorto":
                            strQuery += "AND p.mensajeCorto = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }

    @Override
    public List<GsMensaje> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GsMensaje> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GsMensajes p "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND p.nombre = '" + e.getValue() + "' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = '" + e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = '" + e.getValue() + "' ";
                            break;
                        case "encabezado":
                            strQuery += "AND p.encabezado = '" + e.getValue() + "' ";
                            break;
                        case "mensajeLargo":
                            strQuery += "AND p.mensajeLargo = '" + e.getValue() + "' ";
                            break;
                        case "mensajeCorto":
                            strQuery += "AND p.mensajeCorto = '" + e.getValue() + "' ";
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
            List<GsMensajes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GsMensajes per : list) {
                listaResultados.add(castEntidadNegocio(per));
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
    public GsMensaje consultar(int id) throws Exception {
        GsMensaje objRes = null;
        try {
            objRes = castEntidadNegocio((GsMensajes) getEntityManager().find(GsMensajes.class, id));
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
    public int insertar(GsMensaje obj) throws Exception {
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
    public void actualizar(GsMensaje obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GsMensajes SET ";
            strQuery += "nombre = :nombre,";
            strQuery += "tipo = :tipo,";
            strQuery += "estado = :estado,";
            strQuery += "encabezado = :encabezado,";
            strQuery += "mensajeLargo = :mensajeLargo,";
            strQuery += "mensajeCorto = :mensajeCorto";
            strQuery += " WHERE id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("encabezado", obj.getEncabezado());
            query.setParameter("mensajeLargo", obj.getMensajeLargo());
            query.setParameter("mensajeCorto", obj.getMensajeCorto());
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
    public GsMensaje eliminar(int id) throws Exception {
        GsMensaje obj = null;
        try {
            GsMensajes ent = getEntityManager().find(GsMensajes.class, id);
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

    private GsMensaje castEntidadNegocio(GsMensajes ent) {
        GsMensaje negocio = new GsMensaje();
        negocio.setId(ent.getId());
        negocio.setNombre(ent.getNombre());
        negocio.setTipo(ent.getTipo());
        negocio.setEstado(ent.getEstado());
        negocio.setEncabezado(ent.getEncabezado());
        negocio.setMensajeLargo(ent.getMensajeLargo());
        negocio.setMensajeCorto(ent.getMensajeCorto());
        return negocio;
    }

    private GsMensajes castNegocioEntidad(GsMensaje negocio) {
        GsMensajes entidad = new GsMensajes();
        entidad.setId(negocio.getId());
        entidad.setNombre(negocio.getNombre());
        entidad.setTipo(negocio.getTipo());
        entidad.setEstado(negocio.getEstado());
        entidad.setEncabezado(negocio.getEncabezado());
        entidad.setMensajeLargo(negocio.getMensajeLargo());
        entidad.setMensajeCorto(negocio.getMensajeCorto());
        return entidad;
    }
}
