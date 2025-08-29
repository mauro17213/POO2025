/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccionRelacion;
import com.saviasaludeps.savia.ejb.entidades.GnMaestroAccionRelaciones;
import com.saviasaludeps.savia.ejb.entidades.GnMaestroAcciones;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.MaestroAcccionRelacionRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(MaestroAcccionRelacionRemoto.class)
public class MaestroAccionRelacionServicio extends GenericoServicio implements MaestroAcccionRelacionRemoto {

    @Override
    public int insertar(MaestroAccionRelacion obj) throws Exception {
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
    public List<MaestroAccionRelacion> consultarPorMaestroId(int id) throws Exception {
        List<MaestroAccionRelacion> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM GnMaestroAccionRelaciones m "
                    + "WHERE m.gnMaestrosId.id =  "+id
                    + " ORDER BY m.id";
            List<GnMaestroAccionRelaciones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnMaestroAccionRelaciones per : list) {
                listResult.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            listResult = new ArrayList();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public MaestroAccionRelacion eliminar(int id) throws Exception {
         MaestroAccionRelacion obj = null;
        try {
            GnMaestroAccionRelaciones ent = getEntityManager().find(GnMaestroAccionRelaciones.class, id);
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
    
    private GnMaestroAccionRelaciones castNegocioEntidad(MaestroAccionRelacion negocio){
        GnMaestroAccionRelaciones entidad = new GnMaestroAccionRelaciones();
        entidad.setId(negocio.getId());
        entidad.setGnMaestrosId(new GnMaestros(negocio.getMaestroId().getId()));
        entidad.setGnMaestroAccionesId(new GnMaestroAcciones(negocio.getMaestroAccionId().getId()));
        //Auditoria
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        return entidad;
    }
    
    private MaestroAccionRelacion castEntidadNegocio(GnMaestroAccionRelaciones entidad){
        MaestroAccionRelacion negocio = new MaestroAccionRelacion();
        negocio.setId(entidad.getId());
        negocio.setMaestroId(new Maestro(entidad.getGnMaestrosId().getId()));
        negocio.setMaestroAccionId(new MaestroAccion(entidad.getGnMaestroAccionesId().getId()));
        //Auditoria
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        return negocio;
    }
    
}
