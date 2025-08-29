/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencionComentario;
import com.saviasaludeps.savia.ejb.entidades.GatAtencionComentarios;
import com.saviasaludeps.savia.ejb.entidades.GatAtenciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatAtencionComentarioRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author acuartas
 */
@Stateless
@Remote(GatAtencionComentarioRemoto.class)
public class GatAtencionComentarioServicio extends GenericoServicio implements GatAtencionComentarioRemoto {

    @Override
    public List<GatAtencionComentario> consultarListaPorAtencion(int idAtencion) throws Exception {
        List<GatAtencionComentario> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatAtencionComentarios c "
                    + " WHERE c.gatAtencionesId.id = "+idAtencion
                    + " ORDER BY c.id DESC";
            List<GatAtencionComentarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatAtencionComentarios historico : list) {
                listaResultado.add(castEntidadNegocio(historico));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    @Override
    public GatAtencionComentario consultar(int id) throws java.lang.Exception {
        GatAtencionComentario objRes = null;
        try {
            objRes = castEntidadNegocio((GatAtencionComentarios) getEntityManager().find(GatAtencionComentarios.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int insertar(GatAtencionComentario obj) throws java.lang.Exception {
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
    public GatAtencionComentario eliminar(int id) throws java.lang.Exception {
        GatAtencionComentario obj = null;
        try {
            GatAtencionComentarios ent = getEntityManager().find(GatAtencionComentarios.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ELIMINAR, e);
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    private GatAtencionComentario castEntidadNegocio(GatAtencionComentarios entidad) {
        GatAtencionComentario negocio = new GatAtencionComentario();
        negocio.setId(entidad.getId());
        negocio.setGatAtencionId(new GatAtencion(entidad.getGatAtencionesId().getId()));
        negocio.setComentario(entidad.getComentario());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private GatAtencionComentarios castNegocioEntidad(GatAtencionComentario negocio) {
        GatAtencionComentarios entidad = new GatAtencionComentarios();
        entidad.setId(negocio.getId());
        entidad.setGatAtencionesId(new GatAtenciones(negocio.getGatAtencionId().getId()));
        entidad.setComentario(negocio.getComentario());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
    
}
