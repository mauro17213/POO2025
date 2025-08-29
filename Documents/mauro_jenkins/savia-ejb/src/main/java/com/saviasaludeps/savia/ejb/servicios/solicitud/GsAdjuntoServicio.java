/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.solicitud;

import com.saviasaludeps.savia.dominio.solicitud.GsAdjunto;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.ejb.entidades.GsAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.GsSolicitudes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.solicitud.GsAdjuntoRemoto;
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
@Remote(GsAdjuntoRemoto.class)
public class GsAdjuntoServicio extends GenericoServicio implements GsAdjuntoRemoto {

    @Override
    public List<GsAdjunto> consultarPorSolicitud(int solicitudId) throws Exception {
        List<GsAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "FROM GsAdjuntos a "
                    + " WHERE a.gsSolicitudesId.id = :solicitudId ";
            strQuery += "ORDER BY id ASC";
            List<GsAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("solicitudId", solicitudId)
                    .getResultList();
            for (GsAdjuntos casoNegocion : list) {
                listResult.add(castEntidadNegocio(casoNegocion));
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
    public int insertar(GsAdjunto obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar " + obj.getArchivo() + " como adjunto en solicitudes.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizar(GsAdjunto obj) throws Exception {
        try {
            String hql = "UPDATE GsAdjuntos SET"
                    + " gsSolicitudesId.id = :gsSolicitudesId,"
                    + " tipo = :tipo,"
                    + " nombre = :nombre,"
                    + " ruta = :ruta,"
                    + " archivo = :archivo"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("gsSolicitudesId", obj.getGsSolicitud().getId());
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("ruta", obj.getRuta());
            query.setParameter("archivo", obj.getArchivo());
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
    public GsAdjunto eliminar(int id) throws Exception {
        GsAdjunto obj = null;
        try {
            GsAdjuntos ent = getEntityManager().find(GsAdjuntos.class, id);
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
    public GsAdjunto consultar(int id) throws Exception {
        GsAdjunto objRes = null;
        try {
            GsAdjuntos casoNegocio = (GsAdjuntos) getEntityManager().find(GsAdjuntos.class, id);
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

    public static GsAdjunto castEntidadNegocio(GsAdjuntos per) {
        GsAdjunto obj = new GsAdjunto();
        obj.setId(per.getId());
        obj.setGsSolicitud(new GsSolicitud(per.getGsSolicitudesId().getId()));
        obj.setTipo(per.getTipo());
        obj.setNombre(per.getNombre());
        obj.setRuta(per.getRuta());
        obj.setArchivo(per.getArchivo());
        return obj;
    }

    public static GsAdjuntos castNegocioEntidad(GsAdjunto obj) {
        GsAdjuntos per = new GsAdjuntos();
        per.setId(obj.getId());
        per.setGsSolicitudesId(new GsSolicitudes(obj.getGsSolicitud().getId()));
        per.setTipo(obj.getTipo());
        per.setNombre(obj.getNombre());
        per.setRuta(obj.getRuta());
        per.setArchivo(obj.getArchivo());
        return per;
    }
}
