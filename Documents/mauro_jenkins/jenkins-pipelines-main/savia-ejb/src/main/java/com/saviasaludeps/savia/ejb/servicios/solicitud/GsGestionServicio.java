/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.solicitud;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.solicitud.GsGestion;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.entidades.GsGestiones;
import com.saviasaludeps.savia.ejb.entidades.GsSolicitudes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.solicitud.GsGestionRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jramirez
 */
@Stateless
@Remote(GsGestionRemoto.class)
public class GsGestionServicio extends GenericoServicio implements GsGestionRemoto {

    @Override
    public List<GsGestion> consultarPorSolicitud(int solicitudId) throws Exception {
        List<GsGestion> listResult = new ArrayList();
        try {
            String strQuery = "FROM GsGestiones g "
                    + " WHERE g.gsSolicitudesId.id = :solicitudId ";
            strQuery += "ORDER BY id ASC";
            List<GsGestiones> list = getEntityManager().createQuery(strQuery)
                    .setParameter("solicitudId", solicitudId)
                    .getResultList();
            for (GsGestiones per : list) {
                listResult.add(castEntidadNegocio(per));
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
    public int insertar(GsGestion caso) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(caso)).getId();
            caso.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(GsGestion obj) throws Exception {
        try {
            String hql = "UPDATE GsGestiones SET"
                    + " gsSolicitudesId.id = :gsSolicitudesId,"
                    + " gnUsuariosId.id = :gnUsuariosId,"
                    + " estado = :estado,"
                    + " descripcion = :descripcion,"
                    + " usuarioCrea = :usuarioCrea,"
                    + " terminalCrea = :terminalCrea,"
                    + " fechaHoraCrea = :fechaHoraCrea"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("gsSolicitudesId", obj.getGsSolicitud().getId());
            query.setParameter("gnUsuariosId", obj.getUsuario().getId());
            query.setParameter("estado", obj.getEstado());
            if (obj.getDescripcion() == null) {
                query.setParameter("descripcion", null);
            } else {
                query.setParameter("descripcion", obj.getDescripcion());
            }
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public GsGestion eliminar(int id) throws Exception {
        GsGestion obj = null;
        try {
            GsGestiones ent = getEntityManager().find(GsGestiones.class, id);
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

    @Override
    public GsGestion consultar(int id) throws Exception {
        GsGestion objRes = null;
        try {
            GsGestiones casoNegocio = (GsGestiones) getEntityManager().find(GsGestiones.class, id);
            objRes = castEntidadNegocio(casoNegocio);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    public static GsGestion castEntidadNegocio(GsGestiones per) {
        GsGestion obj = new GsGestion();
        obj.setId(per.getId());
        obj.setGsSolicitud(new GsSolicitud(per.getGsSolicitudesId().getId()));
        obj.setUsuario(
                new Usuario(
                        per.getGnUsuariosId().getId(),
                        per.getGnUsuariosId().getUsuario(),
                        per.getGnUsuariosId().getNombre()
                )
        );
        obj.setEstado(per.getEstado());
        obj.setDescripcion(per.getDescripcion());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    public static GsGestiones castNegocioEntidad(GsGestion obj) {
        GsGestiones per = new GsGestiones();
        per.setId(obj.getId());
        per.setGsSolicitudesId(new GsSolicitudes(obj.getGsSolicitud().getId()));
        per.setGnUsuariosId(new GnUsuarios(obj.getUsuario().getId()));
        per.setEstado(obj.getEstado());
        per.setDescripcion(obj.getDescripcion());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }
}
