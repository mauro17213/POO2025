/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnSmsEnvio;
import com.saviasaludeps.savia.ejb.entidades.GnSmsEnvios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.GnMensajeRemoto;
import com.saviasaludeps.savia.negocio.administracion.GnSmsEnvioRemoto;
import java.util.ArrayList;
import java.util.List;
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
@Remote(GnSmsEnvioRemoto.class)
public class GnSmsEnvioServicio extends GenericoServicio implements GnSmsEnvioRemoto {

    @Override
    public int insertar(GnSmsEnvio obj) throws Exception {
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
    public void actualizar(GnSmsEnvio obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GnSmsEnvios a SET ";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.fechaHoraEnvio = :fechaHoraEnvio ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("fechaHoraEnvio", obj.getFechaHoraEnvio());
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
    
    private GnSmsEnvios castNegocioEntidad(GnSmsEnvio negocio) {
        GnSmsEnvios entidad = new GnSmsEnvios();
        entidad.setOrigen(negocio.getOrigen());
        entidad.setCelulares(negocio.getCelulares());
        entidad.setTexto(negocio.getTexto());
        entidad.setEstado(negocio.getEstado());
        entidad.setFechaHoraEnvio(negocio.getFechaHoraEnvio());
        //Auditoria
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
    
    private GnSmsEnvio castEntidadNegocio(GnSmsEnvios entidad) {
        GnSmsEnvio negocio = new GnSmsEnvio();
        negocio.setId(entidad.getId());
        negocio.setOrigen(entidad.getOrigen());
        negocio.setCelulares(entidad.getCelulares());
        negocio.setTexto(entidad.getTexto());
        negocio.setEstado(entidad.getEstado());
        negocio.setFechaHoraEnvio(entidad.getFechaHoraEnvio());
        //Auditoria
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }

    @Override
    public List<GnSmsEnvio> listarPendientes() throws Exception {
        List<GnSmsEnvio> listResult = new ArrayList();
        String strQuery = "FROM GnSmsEnvios e "
                + "WHERE e.estado = 0 "
                + "ORDER BY e.id ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnSmsEnvios> list = query.getResultList();
            for (GnSmsEnvios obj : list) {
                listResult.add(castEntidadNegocio(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
}
