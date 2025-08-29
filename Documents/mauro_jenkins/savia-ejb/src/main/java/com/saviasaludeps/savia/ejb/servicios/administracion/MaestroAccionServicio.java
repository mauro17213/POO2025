/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.ejb.entidades.GnMaestroAcciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.MaestroAccionRemoto;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(MaestroAccionRemoto.class)
public class MaestroAccionServicio extends GenericoServicio implements MaestroAccionRemoto {

    @Override
    public MaestroAccion consultar(int id) throws Exception {
        MaestroAccion objRes = null;
        try {
            objRes = castEntidadNegocio((GnMaestroAcciones) getEntityManager().find(GnMaestroAcciones.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
    private MaestroAccion castEntidadNegocio( GnMaestroAcciones entidad ){
        MaestroAccion negocio = new MaestroAccion();
        negocio.setId(entidad.getId());
        negocio.setIdGrupo(entidad.getGrupoId());
        negocio.setMaestrosTipo(new MaestroTipo(entidad.getMaestrosTipo().getTipo()));
        negocio.setNombre(entidad.getNombre());
        negocio.setDescripcion(entidad.getDescripcion());
        return negocio;
    }
    
}
