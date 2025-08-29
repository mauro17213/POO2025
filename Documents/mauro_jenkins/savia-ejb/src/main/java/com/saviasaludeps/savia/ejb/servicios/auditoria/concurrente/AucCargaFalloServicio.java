/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCarga;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaFallo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucCargaFallos;
import com.saviasaludeps.savia.ejb.entidades.AucCargas;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucCargaFalloRemoto;
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
@Remote(AucCargaFalloRemoto.class)
public class AucCargaFalloServicio extends GenericoServicio implements AucCargaFalloRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM AucCargaFallos c ";
            strQuery += "WHERE c.aucCargasId.id = " + paramConsulta.getParametroConsulta3() + " ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND c.tipo = " + e.getValue() + " ";
                            break;
                        case "descripcion":
                            strQuery += "AND c.descripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fila":
                            strQuery += "AND c.fila = " + e.getValue() + " ";
                            break;
                        case "columna":
                            strQuery += "AND c.columna = " + e.getValue() + " ";
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
    public List<AucCargaFallo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AucCargaFallo> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucCargaFallos c ";
            strQuery += "WHERE c.aucCargasId.id = " + paramConsulta.getParametroConsulta3() + " ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND c.tipo = " + e.getValue() + " ";
                            break;
                        case "descripcion":
                            strQuery += "AND c.descripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fila":
                            strQuery += "AND c.fila = " + e.getValue() + " ";
                            break;
                        case "columna":
                            strQuery += "AND c.columna = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id ASC";
            }
            List<AucCargaFallos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AucCargaFallos entidad : list) {
                listaResultados.add(castEntidadNegocio(entidad));
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
    public AucCargaFallo consultar(int id) throws Exception {
        AucCargaFallo objRes = null;
        try {
            objRes = castEntidadNegocio((AucCargaFallos) getEntityManager().find(AucCargaFallos.class, id));
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
    public int insertar(AucCargaFallo obj) throws Exception {
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
    public void actualizar(AucCargaFallo obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucCargaFallos a SET ";
            strQuery += "a.aucCargasId.id = :aucCargasId ,";
            strQuery += "a.tipo = :tipo ,";
            strQuery += "a.descripcion = :descripcion ,";
            strQuery += "a.fila = :fila ,";
            strQuery += "a.columna = :columna ,";
            strQuery += "a.fechaHora = :fechaHora ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aucCargasId", obj.getAucCargaId().getId());
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("fila", obj.getFila());
            query.setParameter("columna", obj.getColumna());
            query.setParameter("fechaHora", obj.getFechaHora());
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
    public AucCargaFallo eliminar(int id) throws Exception {
        AucCargaFallo obj = null;
        try {
            AucCargaFallos ent = getEntityManager().find(AucCargaFallos.class, id);
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

    private AucCargaFallo castEntidadNegocio(AucCargaFallos entidad) {
        AucCargaFallo negocio = new AucCargaFallo();
        negocio.setId(entidad.getId());
        negocio.setAucCargaId(new AucCarga(entidad.getAucCargasId().getId()));
        negocio.setTipo(entidad.getTipo());
        negocio.setDescripcion(entidad.getDescripcion());
        negocio.setFila(entidad.getFila());
        negocio.setColumna(entidad.getColumna());
        negocio.setFechaHora(entidad.getFechaHora());
        return negocio;
    }

    private AucCargaFallos castNegocioEntidad(AucCargaFallo negocio) {
        AucCargaFallos entidad = new AucCargaFallos();
        entidad.setAucCargasId(new AucCargas(negocio.getAucCargaId().getId()));
        entidad.setTipo(negocio.getTipo());
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setFila(negocio.getFila());
        entidad.setColumna(negocio.getColumna());
        entidad.setFechaHora(negocio.getFechaHora());
        return entidad;
    }

    @Override
    public List<AucCargaFallo> consultarListaPorIdCarga(int id) throws Exception {
        List<AucCargaFallo> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucCargaFallos p "
                    + "WHERE p.aucCargasId.id = " + id;

            List<AucCargaFallos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucCargaFallos fallo : list) {
                listaResultados.add(castEntidadNegocio(fallo));
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

}
