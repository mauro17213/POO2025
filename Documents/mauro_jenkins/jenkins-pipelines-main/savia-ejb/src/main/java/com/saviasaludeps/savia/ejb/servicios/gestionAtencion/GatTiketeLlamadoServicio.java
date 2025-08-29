/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiketeLlamado;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiquete;
import com.saviasaludeps.savia.ejb.entidades.GatTiketeLlamados;
import com.saviasaludeps.savia.ejb.entidades.GatTiquetes;
import com.saviasaludeps.savia.ejb.entidades.GatSedeTaquillas;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTiketeLlamadoRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author acuartas
 */
@Stateless
@Remote(GatTiketeLlamadoRemoto.class)
public class GatTiketeLlamadoServicio extends GenericoServicio implements GatTiketeLlamadoRemoto {
    
    private static final SimpleDateFormat formatoCorto = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatTiketeLlamados c "
                    + " WHERE 1 = 1 ";            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
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
    public List<GatTiketeLlamado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GatTiketeLlamado> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GatTiketeLlamados c WHERE 1 = 1 ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
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
            List<GatTiketeLlamados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GatTiketeLlamados sedeTaquilla : list) {
                listaResultados.add(castEntidadNegocio(sedeTaquilla));
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
    public GatTiketeLlamado consultar(int id) throws Exception {
        GatTiketeLlamado objRes = null;
        try {        
            objRes = castEntidadNegocio((GatTiketeLlamados) getEntityManager().find(GatTiketeLlamados.class, id));
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
    public int insertar(GatTiketeLlamado obj) throws java.lang.Exception {
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
    public void actualizar(GatTiketeLlamado obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatTiketeLlamados a SET ";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.cantidad = :cantidad ,";
            strQuery += "a.maximo = :maximo ,";
            strQuery += "a.gnUsuariosId.id = :gnUsuariosId ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("cantidad", obj.getCantidad());
            query.setParameter("maximo", obj.getMaximo());
            query.setParameter("gnUsuariosId", obj.getGnUsuarioId().getId());
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
    public GatTiketeLlamado eliminar(int id) throws Exception {
        GatTiketeLlamado obj = null;
        try {
            GatTiketeLlamados ent = getEntityManager().find(GatTiketeLlamados.class, id);
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
    
    private GatTiketeLlamado castEntidadNegocio(GatTiketeLlamados entidad) {
        GatTiketeLlamado negocio = new GatTiketeLlamado();
        negocio.setId(entidad.getId());
        negocio.setEstado(entidad.getEstado());
        negocio.setCantidad(entidad.getCantidad());
        negocio.setMaximo(entidad.getMaximo());
        negocio.setGatSedeTaquillaId(new GatSedeTaquilla(entidad.getGatSedeTaquillasId().getId()));
        negocio.getGatSedeTaquillaId().setNombre(entidad.getGatSedeTaquillasId().getNombre());
        negocio.setGatTiqueteId(new GatTiquete(entidad.getGatTiquetesId().getId()));
        negocio.setGnUsuarioId(new Usuario(entidad.getGnUsuariosId().getId()));
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        return negocio;
    }
    
    private GatTiketeLlamados castNegocioEntidad(GatTiketeLlamado negocio) {
        GatTiketeLlamados entidad = new GatTiketeLlamados();
        entidad.setEstado(negocio.getEstado());
        entidad.setCantidad(negocio.getCantidad());
        entidad.setMaximo(negocio.getMaximo());
        entidad.setGatSedeTaquillasId(new GatSedeTaquillas(negocio.getGatSedeTaquillaId().getId()));
        entidad.setGatTiquetesId(new GatTiquetes(negocio.getGatTiqueteId().getId()));
        entidad.setGnUsuariosId(new GnUsuarios(negocio.getGnUsuarioId().getId()));
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        return entidad;
    }

    @Override
    public List<GatTiketeLlamado> consultarPorTiquete(int idTiquete) throws Exception {
        List<GatTiketeLlamado> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatTiketeLlamados c "
                    + " WHERE c.gatTiquetesId.id = "+idTiquete
                    + " ORDER BY c.id DESC";
            List<GatTiketeLlamados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatTiketeLlamados llamado : list) {
                listaResultado.add(castEntidadNegocio(llamado));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    @Override
    public void actualizarRellamados(int idTiquete) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatTiketeLlamados a SET ";
            strQuery += "a.estado = :estado ";
            strQuery += " WHERE a.gatTiquetesId.id = :idTiquete AND a.estado = 1 ";
            Query query = session.createQuery(strQuery);            
            query.setParameter("estado", GatTiketeLlamado.ESTADO_CANCELADO);
            query.setParameter("idTiquete", idTiquete);
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
    public List<GatTiketeLlamado> consultarLlamdosPorSede(int idSede, Integer maeTipoServicio) throws Exception {
        List<GatTiketeLlamado> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatTiketeLlamados c "
                    + " WHERE c.gatSedeTaquillasId.gnSedesId.id = "+idSede
                    + " AND c.estado = 1";
            if (maeTipoServicio != null) {
                strQuery += " AND c.gatTiquetesId.maeTipoServicioId = "+maeTipoServicio;
            }
            strQuery += " AND c.fechaHoraCrea >= '"+ formatoCorto.format(new Date())+ " 00:00:00' "
                    + " ORDER BY c.id DESC";
            List<GatTiketeLlamados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatTiketeLlamados llamado : list) {
                listaResultado.add(castEntidadNegocioTurno(llamado));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    private GatTiketeLlamado castEntidadNegocioTurno(GatTiketeLlamados entidad) {
        GatTiketeLlamado negocio = new GatTiketeLlamado();
        negocio.setId(entidad.getId());
        negocio.setCantidad(entidad.getCantidad());
        negocio.setMaximo(entidad.getMaximo());
        negocio.setGatSedeTaquillaId(new GatSedeTaquilla(entidad.getGatSedeTaquillasId().getId()));
        negocio.getGatSedeTaquillaId().setNombre(entidad.getGatSedeTaquillasId().getNombre());
        negocio.setGatTiqueteId(new GatTiquete(entidad.getGatTiquetesId().getId(), entidad.getGatTiquetesId().getNumero()));
        negocio.setGnUsuarioId(new Usuario(entidad.getGnUsuariosId().getId()));
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        return negocio;
    }

    @Override
    public GatTiketeLlamado tieneLlamado(int idTiquete) throws Exception {
        GatTiketeLlamado llamado = new GatTiketeLlamado();
        try {
            String strQuery = "FROM GatTiketeLlamados c "
                    + " WHERE c.gatTiquetesId.id = "+idTiquete
                    + " ORDER BY c.id DESC";
            List<GatTiketeLlamados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatTiketeLlamados tiketeLlamado : list) {
                llamado = castEntidadNegocioTurno(tiketeLlamado);
                break;
            }
        } catch (NoResultException e) {
            llamado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return llamado;   
    }
}
