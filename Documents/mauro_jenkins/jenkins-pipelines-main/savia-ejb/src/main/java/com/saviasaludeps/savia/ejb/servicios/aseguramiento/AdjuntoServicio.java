/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAdjunto;
import com.saviasaludeps.savia.ejb.entidades.AsegAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.AsegRadicadoNovedades;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.AdjuntoRemoto;
import javax.persistence.Query;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AdjuntoRemoto.class)
public class AdjuntoServicio extends GenericoServicio implements AdjuntoRemoto {
    
    @Override
    public List<AsegAdjunto> consultarListaPorAfiliado(int afiliadoId) throws Exception {
        List<AsegAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAdjuntos a "
                    + "WHERE a.radicadoNovedadesId.asegAfiliadosId.id = :afiliadoId "
                    + "ORDER BY a.fechaHoraCrea DESC";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("afiliadoId", afiliadoId);
            List<AsegAdjuntos> list = query.getResultList();
            for (AsegAdjuntos per : list) {
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
    public AsegAdjunto consultar(int id) throws Exception {
        AsegAdjunto objRes = null;
        try {
            objRes = castEntidadNegocio((AsegAdjuntos) getEntityManager().find(AsegAdjuntos.class, id));
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
    public int insertar(AsegAdjunto obj) throws Exception {
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
    public void actualizar(AsegAdjunto obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidad(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegAdjunto eliminar(int id) throws Exception {
        AsegAdjunto obj = null;
        try {
            AsegAdjuntos ent = getEntityManager().find(AsegAdjuntos.class, id);
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
    public List<AsegAdjunto> consultarTodos() throws Exception {
        List<AsegAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAdjuntos p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAdjuntos per : list) {
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

    public static AsegAdjunto castEntidadNegocio(AsegAdjuntos per) {
        AsegAdjunto obj = new AsegAdjunto();
        obj.setId(per.getId());
        obj.setTipoArchivo(per.getTipoArchivo());
        obj.setNombreArchivo(per.getNombreArchivo());
        obj.setRuta(per.getRuta());
        obj.setArchivo(per.getArchivo());
        obj.setObservacion(per.getObservacion());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        if (per.getRadicadoNovedadesId()!= null) {            
            obj.setRadicadoNovedad(NovedadAfiliadoServicio.castRadicadoNovedadesEntidadNegocio(per.getRadicadoNovedadesId()));
        }
        return obj;
    }
    
    public static AsegAdjuntos castNegocioEntidad(AsegAdjunto obj) {
        AsegAdjuntos per = new AsegAdjuntos();
        per.setId(obj.getId());
        per.setNombreArchivo(obj.getNombreArchivo());
        per.setTipoArchivo(obj.getTipoArchivo());
        per.setRuta(obj.getRuta());
        per.setArchivo(obj.getArchivo());
        per.setObservacion(obj.getObservacion());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        if (obj.getRadicadoNovedad()!= null) {
            per.setRadicadoNovedadesId(new AsegRadicadoNovedades(obj.getRadicadoNovedad().getId()));
        }
        return per;
    }

}
