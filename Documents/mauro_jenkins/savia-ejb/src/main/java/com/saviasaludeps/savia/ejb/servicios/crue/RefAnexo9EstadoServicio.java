/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Estado;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9EstadoRemoto;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.RefAnexo9Estados;
import com.saviasaludeps.savia.ejb.entidades.RefAnexos9;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(RefAnexo9EstadoRemoto.class)
@Local(RefAnexo9EstadoLocal.class)
public class RefAnexo9EstadoServicio extends GenericoServicio implements RefAnexo9EstadoRemoto, RefAnexo9EstadoLocal {

    @Override
    public int insertar(RefAnexo9Estado obj) throws java.lang.Exception {
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
    public RefAnexo9Estado consultarPorRefAnexo9(int id) throws Exception {
        RefAnexo9Estado objeto = new RefAnexo9Estado();
        try {
            String strQuery = "SELECT r FROM RefAnexo9Estados r WHERE r.refAnexos9Id.id = :id ORDER BY r.id DESC ";

            objeto = castEntidadNegocio((RefAnexo9Estados) getEntityManager().createQuery(strQuery)
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .getSingleResult());
        } catch (NoResultException e) {
            objeto = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objeto;
    }

    public static RefAnexo9Estados castNegocioEntidad(RefAnexo9Estado obj) {
        RefAnexo9Estados ent = new RefAnexo9Estados();
        ent.setEstado(obj.getEstado());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setId(obj.getId());
        ent.setMaeMotivoCodigo(obj.getMaeMotivoCodigo());
        ent.setMaeMotivoId(obj.getMaeMotivoId());
        ent.setMaeMotivoValor(obj.getMaeMotivoValor());
        ent.setMaeEstadoId(obj.getMaeEstadoId());
        ent.setMaeEstadoCodigo(obj.getMaeEstadoCodigo());
        ent.setMaeEstadoValor(obj.getMaeEstadoValor());
        ent.setObservacion(obj.getObservacion());
        if (obj.getRefAnexo9() != null && obj.getRefAnexo9().getId() != null) {
            ent.setRefAnexos9Id(new RefAnexos9(obj.getRefAnexo9().getId()));
        }
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        return ent;
    }

    public static RefAnexo9Estado castEntidadNegocio(RefAnexo9Estados ent) {
        RefAnexo9Estado obj = new RefAnexo9Estado();
        obj.setEstado(ent.getEstado());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setId(ent.getId());
        obj.setMaeMotivoCodigo(ent.getMaeMotivoCodigo());
        obj.setMaeMotivoId(ent.getMaeMotivoId());
        obj.setMaeMotivoValor(ent.getMaeMotivoValor());
        obj.setMaeEstadoId(ent.getMaeEstadoId());
        obj.setMaeEstadoCodigo(ent.getMaeEstadoCodigo());
        obj.setMaeEstadoValor(ent.getMaeEstadoValor());
        obj.setObservacion(ent.getObservacion());
        if (ent.getRefAnexos9Id() != null) {
            obj.setRefAnexo9(new RefAnexo9(ent.getRefAnexos9Id().getId()));
        }
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        return obj;
    }

}
