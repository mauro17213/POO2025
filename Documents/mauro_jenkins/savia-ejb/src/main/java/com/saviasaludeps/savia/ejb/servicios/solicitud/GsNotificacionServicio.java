/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.solicitud;

import com.saviasaludeps.savia.dominio.solicitud.GsNotificacion;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.ejb.entidades.GsNotificaciones;
import com.saviasaludeps.savia.ejb.entidades.GsSolicitudes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.solicitud.GsNotificacionRemoto;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jramirez
 */
@Stateless
@Remote(GsNotificacionRemoto.class)
public class GsNotificacionServicio extends GenericoServicio implements GsNotificacionRemoto {

    @Override
    public int insertar(GsNotificacion notificacion) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(notificacion)).getId();
            notificacion.setId(id);
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
    public void actualizar(GsNotificacion obj) throws Exception {
        try {
            String hql = "UPDATE GsNotificaciones SET"
                    + " gsSolicitudesId.id = :gsSolicitudesId,"
                    + " tipo = :tipo,"
                    + " correo = :correo,"
                    + " celular = :celular,"
                    + " encabezado = :encabezado,"
                    + " detalle = :detalle,"
                    + " estado = :estado,"
                    + " fechaHoraCrea = :fechaHoraCrea,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("gsSolicitudesId", obj.getGsSolicitudesId().getId());
            query.setParameter("tipo", obj.getTipo());
            if (obj.getCorreo() == null) {
                query.setParameter("correo", null);
            } else {
                query.setParameter("correo", obj.getCorreo());
            }
            if (obj.getCelular() == null) {
                query.setParameter("celular", null);
            } else {
                query.setParameter("celular", obj.getCelular());
            }
            if (obj.getEncabezado() == null) {
                query.setParameter("encabezado", null);
            } else {
                query.setParameter("encabezado", obj.getEncabezado());
            }
            query.setParameter("detalle", obj.getDetalle());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
            if (obj.getFechaHoraModifica() == null) {
                query.setParameter("fechaHoraModifica", null);
            } else {
                query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            }
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
    public GsNotificacion eliminar(int id) throws Exception {
        GsNotificacion obj = null;
        try {
            GsNotificaciones ent = getEntityManager().find(GsNotificaciones.class, id);
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

    public static GsNotificaciones castNegocioEntidad(GsNotificacion obj) {
        GsNotificaciones per = new GsNotificaciones();
        per.setId(obj.getId());
        per.setGsSolicitudesId(new GsSolicitudes(obj.getGsSolicitudesId().getId()));
        per.setTipo(obj.getTipo());
        per.setEncabezado(obj.getEncabezado());
        per.setDetalle(obj.getDetalle());
        per.setEstado(obj.getEstado());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

    public static GsNotificacion castEntidadNegocio(GsNotificaciones obj) {
        GsNotificacion gsNotificacion = new GsNotificacion();
        gsNotificacion.setId(obj.getId());
        gsNotificacion.setGsSolicitudesId(new GsSolicitud(obj.getGsSolicitudesId().getId()));
        gsNotificacion.setTipo(obj.getTipo());
        gsNotificacion.setEncabezado(obj.getEncabezado());
        gsNotificacion.setDetalle(obj.getDetalle());
        gsNotificacion.setEstado(obj.getEstado());
        gsNotificacion.setFechaHoraCrea(obj.getFechaHoraCrea());
        gsNotificacion.setFechaHoraModifica(obj.getFechaHoraModifica());
        return gsNotificacion;
    }
}
