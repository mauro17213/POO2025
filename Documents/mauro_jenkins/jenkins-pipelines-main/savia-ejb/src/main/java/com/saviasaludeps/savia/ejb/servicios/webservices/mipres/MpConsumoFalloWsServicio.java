
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservices.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpConsumo;
import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.MpConsumoFallos;
import com.saviasaludeps.savia.ejb.entidades.MpConsumos;
import com.saviasaludeps.savia.negocio.webservices.mipres.MpConsumoFalloWsRemoto;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

@Stateless
@Remote(MpConsumoFalloWsRemoto.class)
public class MpConsumoFalloWsServicio extends GenericoServicio implements MpConsumoFalloWsRemoto {
    
    @Override
    public Integer insertarConsumoFallo(MpConsumoFallo fallo) throws Exception {
        int _id = 0;
        try {
            MpConsumoFallos per = castNegocioEntidad(fallo);
            _id = (int) getEntityManager().merge(per).getId();
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }
    
    public static MpConsumoFallos castNegocioEntidad(MpConsumoFallo obj) {
        MpConsumoFallos per = new MpConsumoFallos();
        per.setId(obj.getId());
        per.setIdProceso(obj.getIdProceso());
        per.setFechaProceso(obj.getFechaProceso());
        per.setJson(obj.getJson().getBytes());
        per.setEstado((short) obj.getEstado());
        per.setFechaHoraFallo(obj.getFechaHoraFallo());
        per.setFechaHoraCorreccion(obj.getFechaHoraCorreccion());
        per.setFechaHoraResincronizacin(obj.getFechaHoraResincronizacin());
        per.setMpConsumosId(new MpConsumos(obj.getMpConsumoId().getId()));
        per.setDescripcion(obj.getDescripcion());
        return per;
    }
    
    public static MpConsumoFallo castEntidadNegocio(MpConsumoFallos per) {
        MpConsumoFallo obj = new MpConsumoFallo();
        obj.setId(per.getId());
        obj.setIdProceso(per.getIdProceso());
        obj.setFechaProceso(per.getFechaProceso());
        obj.setJson(new String((per.getJson()), StandardCharsets.UTF_8));
        obj.setEstado(per.getEstado());
        obj.setFechaHoraFallo(per.getFechaHoraFallo());
        obj.setFechaHoraCorreccion(per.getFechaHoraCorreccion());
        obj.setFechaHoraResincronizacin(per.getFechaHoraResincronizacin());
        MpConsumo consumo = new MpConsumo();
        consumo.setId(per.getMpConsumosId().getId());
        consumo.setServicio(per.getMpConsumosId().getServicio());
        consumo.setRegistros(per.getMpConsumosId().getRegistros());
        consumo.setRegistrosExitosos(per.getMpConsumosId().getRegistrosExitosos());
        consumo.setEstado(per.getMpConsumosId().getEstado());
        obj.setMpConsumoId(consumo);
        obj.setDescripcion(per.getDescripcion());
        return obj;
    }
    
    @Override
    public void actualizarEstado(MpConsumoFallo obj) throws Exception {
        try {
            String hql = "UPDATE MpConsumoFallos SET "
                    + "estado= :estado ,";
            if (obj.getEstado() == 2) {
                hql += "fechaHoraCorreccion= NOW() ";
            } else {
                hql += "fechaHoraResincronizacin= NOW() ";
                
            }
            hql += "WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", (short) obj.getEstado());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarEstadoFallosResincronizar() throws Exception {
        try {
            String hql = "UPDATE MpConsumoFallos SET "
                    + "estado = 1 ";
            hql += "WHERE estado = 3 ";
            ///+ "and fechaHoraResincronizacin >= DATE()-70";
            Query query = getEntityManager().createQuery(hql);
            query.executeUpdate();
        } catch (NoResultException e) {
            
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<MpConsumoFallo> consultarxEstadoyServicio(int estado, String servicio) throws Exception {
        List<MpConsumoFallo> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MpConsumoFallos mc "
                    + "WHERE mc.estado =  " + estado
                    + " AND mc.mpConsumosId.servicio =  '" + servicio
                    + "' ORDER BY mc.id";
            List<MpConsumoFallos> list = getEntityManager().createQuery(strQuery).setMaxResults(1000)
                    .getResultList();
            for (MpConsumoFallos per : list) {
                //for (MpConsumoFallos per : list.subList(0, 1000)) {
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
    
}
