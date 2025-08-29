/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnUsuarioSesion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.GnUsuarioSesionRemoto;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarioSesiones;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(GnUsuarioSesionRemoto.class)
public class GnUsuarioSesionServicio extends GenericoServicio implements GnUsuarioSesionRemoto {

    @Override
    public GnUsuarioSesion consultarPorIdYIp(String idSesion, String ip) throws Exception {
        GnUsuarioSesion objRes = null;
        try {
            String strQuery = "FROM GnUsuarioSesiones u "
                    + "WHERE u.idSesion = '" + idSesion + "' "
                    + "AND u.ipServidor = '" + ip + "' "
                    + "ORDER BY u.fechaHoraUltimaGestion DESC";
            List<GnUsuarioSesiones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null) {
                for (GnUsuarioSesiones sesion : list) {
                    objRes = castEntidadNegocio(sesion);
                    break;
                }
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
    public int insertar(GnUsuarioSesion obj) throws Exception {
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
    public void actualizar(GnUsuarioSesion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GnUsuarioSesiones a SET ";
            strQuery += "a.activa = :activa, ";
            strQuery += "a.fechaHoraUltimaGestion = :fechaHoraUltimaGestion, ";
            strQuery += "a.fechaHoraFin = :fechaHoraFin, ";
            strQuery += "a.idSesion = :idSesion, ";
            strQuery += "a.ipServidor = :ipServidor, ";
            strQuery += "a.gnUsuariosId.id = :gnUsuarioId ";
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("activa", obj.isActiva());
            query.setParameter("fechaHoraUltimaGestion", obj.getFechaHoraUltimaGestion());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            query.setParameter("idSesion", obj.getIdSesion());
            query.setParameter("ipServidor", obj.getIpServidor());
            query.setParameter("gnUsuarioId", obj.getGnUsuarioId().getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    
    private GnUsuarioSesion castEntidadNegocio(GnUsuarioSesiones entidad) {
        GnUsuarioSesion negocio = new GnUsuarioSesion();
        negocio.setId(entidad.getId());
        negocio.setIdSesion(entidad.getIdSesion());
        negocio.setIpServidor(entidad.getIpServidor());
        negocio.setFechaHoraInicio(entidad.getFechaHoraInicio());
        negocio.setFechaHoraUltimaGestion(entidad.getFechaHoraUltimaGestion());
        negocio.setFechaHoraFin(entidad.getFechaHoraFin());
        negocio.setActiva(entidad.getActiva());
        negocio.setGnUsuarioId(new Usuario(entidad.getGnUsuariosId().getId()));
        return negocio;
    }
    
    private GnUsuarioSesiones castNegocioEntidad(GnUsuarioSesion negocio) {
        GnUsuarioSesiones entidad = new GnUsuarioSesiones();
        entidad.setId(negocio.getId());
        entidad.setIdSesion(negocio.getIdSesion());
        entidad.setIpServidor(negocio.getIpServidor());
        entidad.setFechaHoraInicio(negocio.getFechaHoraInicio());
        entidad.setFechaHoraUltimaGestion(negocio.getFechaHoraUltimaGestion());
        entidad.setFechaHoraFin(negocio.getFechaHoraFin());
        entidad.setActiva(negocio.isActiva());
        entidad.setGnUsuariosId(new GnUsuarios(negocio.getGnUsuarioId().getId()));
        return entidad;
    }

    @Override
    public List<GnUsuarioSesion> listarSesionesUsuario(int idUser) throws Exception {
        List<GnUsuarioSesion> objRes = new ArrayList<>();
        try {
            String strQuery = "FROM GnUsuarioSesiones u "
                    + "WHERE u.gnUsuariosId.id = '" + idUser + "' "
                    + "AND u.activa = 1 "
                    + "ORDER BY u.fechaHoraUltimaGestion DESC";
            List<GnUsuarioSesiones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null) {
                for (GnUsuarioSesiones sesion : list) {
                    objRes.add(castEntidadNegocio(sesion));
                }
            }            
        } catch (NoResultException e) {
            objRes = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public List<GnUsuarioSesion> consultarPorIdSesion(String idSesion) throws Exception {
        List<GnUsuarioSesion> objRes = new ArrayList<>();
        try {
            String strQuery = "FROM GnUsuarioSesiones u "
                    + "WHERE u.idSesion = '" + idSesion + "' ";
            List<GnUsuarioSesiones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null) {
                for (GnUsuarioSesiones sesion : list) {
                    objRes.add(castEntidadNegocio(sesion));
                    break;
                }
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
    
}
