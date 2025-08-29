/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeConfiguracion;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatSedeConfiguracionRemoto;
import com.saviasaludeps.savia.ejb.entidades.GatSedeConfiguraciones;
import com.saviasaludeps.savia.ejb.entidades.GnSedes;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(GatSedeConfiguracionRemoto.class)
public class GatSedeConfiguracionServicio extends GenericoServicio implements GatSedeConfiguracionRemoto {
    
    

    @Override
    public GatSedeConfiguracion consultarPorIdSede(int id) throws Exception {
        GatSedeConfiguracion objRes = new GatSedeConfiguracion();
        try {
            String strQuery = "FROM GatSedeConfiguraciones c "
                    + " WHERE c.gnSedesId.id = "+id
                    + " ORDER BY c.id DESC";
            List<GatSedeConfiguraciones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatSedeConfiguraciones configuracion : list) {
                objRes = castEntidadNegocio(configuracion);
                break;
            }
        } catch (NoResultException e) {
            objRes =  new GatSedeConfiguracion();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int insertar(GatSedeConfiguracion obj) throws Exception {
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
    
    private GatSedeConfiguraciones castNegocioEntidad(GatSedeConfiguracion negocio) {
        GatSedeConfiguraciones entidad = new GatSedeConfiguraciones();
        entidad.setGnSedesId(new GnSedes(negocio.getGnSedeId().getId()));
        entidad.setTurnero(negocio.getTurnero());
        return entidad;
    }
    
    private GatSedeConfiguracion castEntidadNegocio(GatSedeConfiguraciones entidad) {
        GatSedeConfiguracion negocio = new GatSedeConfiguracion();
        negocio.setId(entidad.getId());
        negocio.setGnSedeId(new GnSede(entidad.getGnSedesId().getId()));
        negocio.setTurnero(entidad.getTurnero());
        return negocio;
    }

    @Override
    public void actualizar(GatSedeConfiguracion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatSedeConfiguraciones a SET ";
            strQuery += "a.gnSedesId.id = :gnSedesId ,";
            strQuery += "a.turnero = :turnero ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gnSedesId", obj.getGnSedeId().getId());
            query.setParameter("turnero", obj.getTurnero());
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
    
}
