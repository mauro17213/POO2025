/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnMensaje;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnMensajes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.GnMensajeRemoto;
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
@Remote(GnMensajeRemoto.class)
public class GnMensajeServicio extends GenericoServicio implements GnMensajeRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GnMensajes c "
                    + " WHERE 1 = 1 ";            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND c.nombre  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "prioridad":
                            strQuery += "AND c.prioridad = " + e.getValue() + " ";
                            break;
                        case "exposicion":
                            strQuery += "AND c.exposicion = " + e.getValue() + " ";
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
    public List<GnMensaje> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GnMensaje> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GnMensajes c WHERE 1 = 1 ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND c.nombre  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "prioridad":
                            strQuery += "AND c.prioridad = " + e.getValue() + " ";
                            break;
                        case "exposicion":
                            strQuery += "AND c.exposicion = " + e.getValue() + " ";
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
            List<GnMensajes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnMensajes alerta : list) {
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
    public GnMensaje consultar(int id) throws Exception {
        GnMensaje objRes = null;
        try {
            objRes = castEntidadNegocio((GnMensajes) getEntityManager().find(GnMensajes.class, id));
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
    public int insertar(GnMensaje obj) throws Exception {
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
    public void actualizar(GnMensaje obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GnMensajes a SET ";
            strQuery += "a.nombre = :nombre ,";
            strQuery += "a.descripcon = :descripcon ,";
            strQuery += "a.contenido = :contenido ,";
            strQuery += "a.fechaDesde = :fechaDesde ,";
            strQuery += "a.fechaHasta = :fechaHasta ,";
            strQuery += "a.prioridad = :prioridad ,";
            strQuery += "a.exposicion = :exposicion ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("descripcon", obj.getDescripcon());
            query.setParameter("contenido", obj.getContenido());
            query.setParameter("fechaDesde", obj.getFechaDesde());
            query.setParameter("fechaHasta", obj.getFechaHasta());
            query.setParameter("prioridad", obj.getPrioridad());
            query.setParameter("exposicion", obj.getExposicion());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
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
    public GnMensaje eliminar(int id) throws Exception {
        GnMensaje obj = null;
        try {
            GnMensajes ent = getEntityManager().find(GnMensajes.class, id);
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
    
    private GnMensaje castEntidadNegocio(GnMensajes entidad) {
        GnMensaje negocio = new GnMensaje();
        negocio.setId(entidad.getId());
        negocio.setNombre(entidad.getNombre());
        negocio.setContenido(entidad.getContenido());
        negocio.setDescripcon(entidad.getDescripcon());
        negocio.setFechaDesde(entidad.getFechaDesde());
        negocio.setFechaHasta(entidad.getFechaHasta());
        negocio.setPrioridad(entidad.getPrioridad());
        negocio.setExposicion(entidad.getExposicion());
        negocio.setTexto(new String(entidad.getContenido()));
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private GnMensaje castEntidadNegocioCorto(GnMensajes entidad) {
        GnMensaje negocio = new GnMensaje();
        negocio.setId(entidad.getId());
        negocio.setNombre(entidad.getNombre());
        negocio.setContenido(entidad.getContenido());
        negocio.setDescripcon(entidad.getDescripcon());
        negocio.setFechaDesde(entidad.getFechaDesde());
        negocio.setFechaHasta(entidad.getFechaHasta());
        negocio.setPrioridad(entidad.getPrioridad());
        negocio.setExposicion(entidad.getExposicion());
        negocio.setTexto(new String(entidad.getContenido()));
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private GnMensajes castNegocioEntidad(GnMensaje negocio) {
        GnMensajes entidad = new GnMensajes();
        entidad.setNombre(negocio.getNombre());
        entidad.setContenido(negocio.getContenido());
        entidad.setDescripcon(negocio.getDescripcon());
        entidad.setFechaDesde(negocio.getFechaDesde());
        entidad.setFechaHasta(negocio.getFechaHasta());
        entidad.setPrioridad(negocio.getPrioridad());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }

    @Override
    public List<GnMensaje> consultarPorEmpresaUsuario(int idEmpresa) throws Exception {
        List<GnMensaje> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GnMensajes c WHERE 1 = 1 ";
            if (idEmpresa > 1) {
               strQuery += "AND c.exposicion >= "+GnMensaje.ESTADO_EXTERNA+" "; 
            } else {
               strQuery += "AND (c.exposicion = "+GnMensaje.ESTADO_INTERNA+" OR c.exposicion = "+GnMensaje.ESTADO_AMBAS+") ";
            }            
            strQuery += " AND c.fechaDesde <= SYSDATE() AND c.fechaHasta >= SYSDATE() ORDER BY c.prioridad ASC, c.fechaHoraCrea DESC ";
            List<GnMensajes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnMensajes alerta : list) {
                listaResultados.add(castEntidadNegocioCorto(alerta));
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
