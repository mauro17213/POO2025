/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnCorreoEnvio;
import com.saviasaludeps.savia.ejb.entidades.GnCorreoEnvios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.GnCorreoEnvioRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author stive
 */
@Stateless
@Remote(GnCorreoEnvioRemoto.class)
public class GnCorreoEnvioServicio extends GenericoServicio implements GnCorreoEnvioRemoto {

    @Override
    public List<GnCorreoEnvio> consultarListaPendientes() throws Exception {
        List<GnCorreoEnvio> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GnCorreoEnvios c WHERE c.estado = 0 ORDER BY c.fechaHoraCrea ";
            List<GnCorreoEnvios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnCorreoEnvios envio : list) {
                listaResultados.add(castEntidadNegocio(envio));
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
    public void actualizarEnviados(int idGnCorreoEnvio, int estado) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GnCorreoEnvios a SET ";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.fechaHoraEnvio = :fechaHoraEnvio ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("estado", estado);
            query.setParameter("fechaHoraEnvio", new Date());
            query.setParameter("id", idGnCorreoEnvio);
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
    public int insertar(GnCorreoEnvio obj) throws Exception {
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

    private GnCorreoEnvio castEntidadNegocio(GnCorreoEnvios entidad) {
        GnCorreoEnvio negocio = new GnCorreoEnvio();
        negocio.setId(entidad.getId());
        negocio.setOrigen(entidad.getOrigen());
        negocio.setDestino(entidad.getDestino());
        negocio.setEncabezado(entidad.getEncabezado());
        negocio.setDetalle(entidad.getDetalle());
        negocio.setEstado(entidad.getEstado());
        negocio.setTipo(entidad.getTipo());
        negocio.setAdjunto1(entidad.getAdjunto1());
        negocio.setAdjunto2(entidad.getAdjunto2());
        negocio.setAdjunto3(entidad.getAdjunto3());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setFechaHoraEnvio(entidad.getFechaHoraEnvio());
        return negocio;
    }
    
    private GnCorreoEnvios castNegocioEntidad(GnCorreoEnvio negocio) {
        GnCorreoEnvios entidad = new GnCorreoEnvios();
        entidad.setOrigen(negocio.getOrigen());
        entidad.setDestino(negocio.getDestino());
        entidad.setEncabezado(negocio.getEncabezado());
        entidad.setDetalle(negocio.getDetalle());
        entidad.setEstado(negocio.getEstado());
        entidad.setTipo(negocio.getTipo());
        entidad.setAdjunto1(negocio.getAdjunto1());
        entidad.setAdjunto2(negocio.getAdjunto2());
        entidad.setAdjunto3(negocio.getAdjunto3());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setFechaHoraEnvio(negocio.getFechaHoraEnvio());
        return entidad;
    }
    
}
