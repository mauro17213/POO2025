/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.ejb.entidades.GatSedeTaquillas;
import com.saviasaludeps.savia.ejb.entidades.GatTaquillaServicios;
import com.saviasaludeps.savia.ejb.entidades.GnSedes;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatSedeTaquillaRemoto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author acuartas
 */
@Stateless
@Remote(GatSedeTaquillaRemoto.class)
public class GatSedeTaquillaServicio extends GenericoServicio implements GatSedeTaquillaRemoto {

    @Override
    public int insertar(GatSedeTaquilla obj) throws java.lang.Exception {
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
    public void actualizar(GatSedeTaquilla obj) throws java.lang.Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatSedeTaquillas a SET ";
            strQuery += "a.gnSedesId.id = :gnSedesId ,";
            strQuery += "a.nombre = :nombre ,";
            strQuery += "a.activo = :activo ,";
            strQuery += "a.disponible = :disponible ,";
            strQuery += "a.gnUsuariosId.id = :gnUsuariosId ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gnSedesId", obj.getGnSedeId().getId());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("disponible", obj.isDisponible());
            if (obj.getUsuarioId() != null) {
                query.setParameter("gnUsuariosId", obj.getUsuarioId().getId());
            } else {
                query.setParameter("gnUsuariosId", null);
            }            
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica()); 
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
    public GatSedeTaquilla eliminar(int id) throws java.lang.Exception {
        GatSedeTaquilla obj = null;
        try {
            GatSedeTaquillas ent = getEntityManager().find(GatSedeTaquillas.class, id);
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

    private GatSedeTaquilla castEntidadNegocio(GatSedeTaquillas entidad) {
        GatSedeTaquilla negocio = new GatSedeTaquilla();
        negocio.setId(entidad.getId());
        negocio.setGnSedeId(new GnSede(entidad.getGnSedesId().getId()));
        negocio.getGnSedeId().setNombre(entidad.getGnSedesId().getNombre());
        negocio.setNombre(entidad.getNombre());
        negocio.setActivo(entidad.getActivo());
        negocio.setDisponible(entidad.getDisponible());
        if (entidad.getGnUsuariosId() != null) {
            negocio.setUsuarioId(new Usuario(entidad.getGnUsuariosId().getId()));
            negocio.getUsuarioId().setNombre(entidad.getGnUsuariosId().getNombre());
        }
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private GatSedeTaquillas castNegocioEntidad(GatSedeTaquilla negocio) {
        GatSedeTaquillas entidad = new GatSedeTaquillas();
        entidad.setGnSedesId(new GnSedes(negocio.getGnSedeId().getId()));
        entidad.setNombre(negocio.getNombre());
        entidad.setActivo(negocio.isActivo());
        entidad.setDisponible(negocio.isDisponible());
        if (negocio.getUsuarioId() != null && negocio.getUsuarioId().getId() != null) {
            entidad.setGnUsuariosId(new GnUsuarios(negocio.getUsuarioId().getId()));
        }        
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<GatSedeTaquilla> listarPorIdSede(int idSede) throws Exception {
        List<GatSedeTaquilla> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatSedeTaquillas c "
                    + " WHERE c.gnSedesId.id = "+idSede
                    + " ORDER BY c.nombre ASC";
            List<GatSedeTaquillas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatSedeTaquillas taquilla : list) {
                listaResultado.add(castEntidadNegocio(taquilla));
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
    public GatSedeTaquilla consultarPorIdUsuario(int idUsuario) throws Exception {
        GatSedeTaquilla objeto = new GatSedeTaquilla();
        try {
            String strQuery = "FROM GatSedeTaquillas c "
                    + " WHERE c.gnUsuariosId.id = "+idUsuario
                    + " AND c.activo = 1"
                    + " ORDER BY c.id DESC";
            List<GatSedeTaquillas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatSedeTaquillas taquilla : list) {
                objeto = castEntidadNegocio(taquilla);
                break;
            }
        } catch (NoResultException e) {
            objeto = new GatSedeTaquilla();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objeto;
    }

    @Override
    public void liberarTaquillasDeUsuario(int idUsuario) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatSedeTaquillas a SET ";
            strQuery += "a.disponible = 1, ";
            strQuery += "a.gnUsuariosId = NULL ";
            strQuery += " WHERE a.gnUsuariosId.id = :idUsuario ";
            Query query = session.createQuery(strQuery);
            query.setParameter("idUsuario", idUsuario);
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
    public void liberarTaquilla(int idTaquilla) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatSedeTaquillas a SET ";
            strQuery += "a.disponible = 1, ";
            strQuery += "a.gnUsuariosId = NULL ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", idTaquilla);
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
    public List<GatSedeTaquilla> listarOcupadas(int idSede, int maeServicioId, int idTaquilla) throws Exception {
        List<GatSedeTaquilla> resultList = new ArrayList<>();
        try {
            String strQuery = "FROM GatTaquillaServicios c "
                    + " WHERE c.gatSedeTaquillasId.gnSedesId.id = "+idSede
                    + " AND c.gatSedeTaquillasId.id != "+ idTaquilla
                    + " AND c.maeTipoServicioId = "+maeServicioId
                    + " AND c.gatSedeTaquillasId.gnSedesId.activo = 1"
                    + " AND c.gatSedeTaquillasId.activo = 1"
                    + " ORDER BY c.id DESC";
            List<GatTaquillaServicios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatTaquillaServicios servicio : list) {
                resultList.add(castEntidadNegocio(servicio.getGatSedeTaquillasId()));
            }
        } catch (NoResultException e) {
            resultList = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return resultList;
    }

    @Override
    public GatSedeTaquilla consultar(int id) throws Exception {
        GatSedeTaquilla objRes = null;
        try {
            objRes = castEntidadNegocio((GatSedeTaquillas) getEntityManager().find(GatSedeTaquillas.class, id));
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
    public List<GatSedeTaquilla> listarPorIdSedeActivas(int idSede) throws Exception {
        List<GatSedeTaquilla> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatSedeTaquillas c "
                    + " WHERE c.gnSedesId.id = "+idSede
                    + " AND c.activo = 1 AND c.gnSedesId.activo = 1 AND c.disponible = 1"
                    + " ORDER BY c.id DESC";
            List<GatSedeTaquillas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatSedeTaquillas taquilla : list) {
                listaResultado.add(castEntidadNegocio(taquilla));
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
    public boolean estaLibre(int idTaquilla) throws Exception {
        boolean libre = true;
        try {
            String strQuery = "FROM GatSedeTaquillas c "
                    + " WHERE c.id = "+idTaquilla
                    + " AND c.disponible = 1"
                    + " ORDER BY c.id DESC";
            List<GatSedeTaquillas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list.isEmpty()) {
                libre = false;
            }
        } catch (NoResultException e) {
            libre = false;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return libre;
    }
}
