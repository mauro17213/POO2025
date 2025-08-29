/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegCarga;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegDetalleCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegCargas;
import com.saviasaludeps.savia.ejb.entidades.AsegDetalleCargas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.DetalleCargaRemoto;

/**
 *
 * @author jose perez hernandez
 */
@Stateless
@Remote(DetalleCargaRemoto.class)
public class DetalleCargaServicio extends GenericoServicio implements DetalleCargaRemoto {

    @Override
    public AsegDetalleCarga consultar(int id) throws Exception {
        AsegDetalleCarga objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AsegDetalleCargas) getEntityManager().find(AsegDetalleCargas.class, id));
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
    public int insertar(AsegDetalleCarga obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadLargo(obj)).getId();
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
    public void actualizar(AsegDetalleCarga obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidadLargo(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegDetalleCarga eliminar(int id) throws Exception {
        AsegDetalleCarga obj = null;
        try {
            AsegDetalleCargas ent = getEntityManager().find(AsegDetalleCargas.class, id);
            if (ent != null) {
                obj = castEntidadNegocioLargo(ent);
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
    public List<AsegDetalleCarga> consultarTodos() throws Exception {
        List<AsegDetalleCarga> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegDetalleCargas p "
                    + "ORDER BY p.fechaHoraProceso DESC";
            List<AsegDetalleCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegDetalleCargas per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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

    public static AsegDetalleCarga castEntidadNegocioLargo(AsegDetalleCargas per) {
        AsegDetalleCarga obj = new AsegDetalleCarga();
        obj.setId(per.getId());
        obj.setData(per.getData());
        obj.setEstado(per.getEstado());
        obj.setDetalleFallo(per.getDetalleFallo());
        obj.setFechaHoraProceso(per.getFechaHoraProceso());
        //objetos
        if(per.getAsegCargasId() != null) {
            obj.setAsegCarga(new AsegCarga(per.getAsegCargasId().getId()));
        }
        return obj;
    }

    public static AsegDetalleCargas castNegocioEntidadLargo(AsegDetalleCarga obj) {
        AsegDetalleCargas per = new AsegDetalleCargas();
        per.setId(obj.getId());
        per.setData(obj.getData());
        per.setEstado(obj.getEstado());
        //cambiar cuando aumenten el tamaño del campo
        if (obj.getDetalleFallo().length() >= 2147483647) {
            per.setDetalleFallo(obj.getDetalleFallo().substring(0, 2147483647));
        } else {
            per.setDetalleFallo(obj.getDetalleFallo());
        }
        per.setFechaHoraProceso(obj.getFechaHoraProceso());
        //objetos
        if(obj.getAsegCarga()!= null) {
            per.setAsegCargasId(new AsegCargas(obj.getAsegCarga().getId()));
        }
        return per;
    }
    
    @Override
    public List<AsegDetalleCarga> consultarPorRadicado(int radicado) throws java.lang.Exception {
        List<AsegDetalleCarga> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegDetalleCargas p"
                    + " WHERE p.asegCargasId.id = " + radicado
                    + " ORDER BY p.id ASC";
            List<AsegDetalleCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegDetalleCargas per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AsegDetalleCarga> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   
}
