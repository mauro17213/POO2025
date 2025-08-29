/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservice;

import com.saviasaludeps.savia.dominio.webservice.WsConexion;
import com.saviasaludeps.savia.dominio.webservice.WsConexionServicio;
import com.saviasaludeps.savia.dominio.webservice.WsServicio;
import com.saviasaludeps.savia.ejb.entidades.WsConexiones;
import com.saviasaludeps.savia.ejb.entidades.WsConexionesServicios;
import com.saviasaludeps.savia.ejb.entidades.WsServicios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.webservice.WsConexionesServiciosRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(WsConexionesServiciosRemoto.class)
@Local(WsConexionesServiciosLocal.class)
public class WsConexionesServiciosServicio extends GenericoServicio implements WsConexionesServiciosLocal, WsConexionesServiciosRemoto {
    
    @Override
    public List<WsConexionServicio> getWsConexionServicio(int ws_conexion_id) throws Exception {
        List<WsConexionServicio> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM WsConexionesServicios s "
                    + "WHERE s.wsConexionesId.id = :id "
                    + " ORDER BY s.id";
            
            List<WsConexionesServicios> list = getEntityManager().createQuery(strQuery).setParameter("id", ws_conexion_id).getResultList();
            
            for (WsConexionesServicios per : list) {
                listResult.add(castEntidadNegocio(per));
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
    
    @Override
    public int insertar(WsConexionServicio obj) throws java.lang.Exception {
        int id = 0;
        try {
                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
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
    public WsConexionServicio eliminar(int id) throws Exception {
        WsConexionServicio obj = null;
        try {
            WsConexionesServicios ent = getEntityManager().find(WsConexionesServicios.class, id);
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
    
    
    public static WsConexionesServicios castNegocioEntidad(WsConexionServicio obj) {
        WsConexionesServicios per = new WsConexionesServicios();
        
        per.setId(obj.getId());
        if (obj.getWsConexion() != null && obj.getWsConexion().getId() != null) {
            per.setWsConexionesId(new WsConexiones(obj.getWsConexion().getId()));
        }
        
        if (obj.getWsServicio() != null && obj.getWsServicio().getId() != null) {
            per.setWsServiciosId(new WsServicios(obj.getWsServicio().getId()));
        }
        
        return per;
    }
    
    public static WsConexionServicio castEntidadNegocio(WsConexionesServicios per) {
        WsConexionServicio obj = new WsConexionServicio();
        obj.setId(per.getId());
        if (per.getWsConexionesId() != null && per.getWsConexionesId().getId() != null) {
            obj.setWsConexion(new WsConexion(per.getWsConexionesId().getId()));
        }
        
        if (per.getWsServiciosId() != null && per.getWsServiciosId().getId() != null) {
            obj.setWsServicio(new WsServicio(per.getWsServiciosId().getId()));
        }
        
        return obj;
    }
    
}
