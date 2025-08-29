/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.maestro.MaCarga;
import com.saviasaludeps.savia.dominio.maestro.MaDetalleCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.MaCargas;
import com.saviasaludeps.savia.ejb.entidades.MaDetalleCargas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.maestro.MaDetalleCargaRemoto;

/**
 *
 * @author jose perez hernandez
 */
@Stateless
@Remote(MaDetalleCargaRemoto.class)
public class MaDetalleCargaServicio extends GenericoServicio implements MaDetalleCargaRemoto {

    @Override
    public MaDetalleCarga consultar(int id) throws Exception {
        MaDetalleCarga objRes = null;
        try {
            objRes = castEntidadNegocioLargo((MaDetalleCargas) getEntityManager().find(MaDetalleCargas.class, id));
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
    public int insertar(MaDetalleCarga obj) throws Exception {
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
    public void actualizar(MaDetalleCarga obj) throws Exception {
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
    public MaDetalleCarga eliminar(int id) throws Exception {
        MaDetalleCarga obj = null;
        try {
            MaDetalleCargas ent = getEntityManager().find(MaDetalleCargas.class, id);
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
    public List<MaDetalleCarga> consultarTodos() throws Exception {
        List<MaDetalleCarga> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaDetalleCargas p "
                    + "ORDER BY p.fechaHoraProceso DESC";
            List<MaDetalleCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaDetalleCargas per : list) {
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

    public static MaDetalleCarga castEntidadNegocioLargo(MaDetalleCargas per) {
        MaDetalleCarga obj = new MaDetalleCarga();
        obj.setId(per.getId());
        obj.setData(per.getData());
        obj.setEstado(per.getEstado());
        obj.setDetalleFallo(per.getDetalleFallo());
        obj.setFechaHoraProceso(per.getFechaHoraProceso());
        //objetos
        if(per.getMaCargasId()!= null) {
            obj.setMaCarga(new MaCarga(per.getMaCargasId().getId()));
        }
        return obj;
    }

    public static MaDetalleCargas castNegocioEntidadLargo(MaDetalleCarga obj) {
        MaDetalleCargas per = new MaDetalleCargas();
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
        if(obj.getMaCarga()!= null) {
            per.setMaCargasId(new MaCargas(obj.getMaCarga().getId()));
        }
        return per;
    }
    
    @Override
    public List<MaDetalleCarga> consultarPorRadicado(int radicado) throws java.lang.Exception {
        List<MaDetalleCarga> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaDetalleCargas p"
                    + " WHERE p.maCargasId.id = " + radicado
                    + " ORDER BY p.id ASC";
            List<MaDetalleCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaDetalleCargas per : list) {
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
    public List<MaDetalleCarga> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   
}
