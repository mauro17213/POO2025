/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpConsumo;
import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import com.saviasaludeps.savia.ejb.entidades.MpConsumoFallos;
import com.saviasaludeps.savia.ejb.entidades.MpConsumos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.mipres.MpConsumoRemoto;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author rpalacios
 */
@Stateless
@Remote(MpConsumoRemoto.class)
public class MpConsumoServicio extends GenericoServicio implements MpConsumoRemoto {

    @Override
    public MpConsumo consultarUltimoConsumoExitoso(String servicio) throws Exception {
        MpConsumo obj = null;
        try {
            String strQuery = "SELECT m FROM MpConsumos m "
                    + "WHERE m.servicio = :servicio "
                    + "AND m.estado = 2 "
                    + "ORDER BY m.id DESC ";
            obj = castEntidadNegocio(
                    getEntityManager()
                            .createQuery(strQuery, MpConsumos.class)
                            .setParameter("servicio", servicio)
                            .setMaxResults(1)
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public Boolean consultarPrimerConsumoExitosoDia(String servicio, Date fecha) throws Exception {
        try {
            String strQuery = "SELECT COUNT(m) FROM MpConsumos m "
                    + "WHERE m.servicio = :servicio "
                    + "AND m.estado = 2 "
                    + "AND m.periodo = :fecha";
            
            java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());


            Long count = getEntityManager()
                    .createQuery(strQuery, Long.class)
                    .setParameter("servicio", servicio)
                    .setParameter("fecha", fechaSql)
                    .getSingleResult();

            return count > 0;  // true si hay registros, false si no

        } catch (Exception e) {
            Exception(CONSULTAR, e);
            return false;
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public Integer insertar(MpConsumo mpConsumo) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(mpConsumo)).getId();
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
    public void actualizar(MpConsumo obj) throws Exception {
        try {
            String strQuery = "UPDATE MpConsumos m SET ";
            strQuery += "m.estado = :estado, ";
            strQuery += "m.registros = :registros, ";
            strQuery += "m.registrosExitosos = :registrosExitosos, ";
            strQuery += "m.periodo = :periodo, ";
            if (obj.getFechaHoraFin() != null) {
                strQuery += " m.fechaHoraFin = :fechaHoraFin, ";
            }
            strQuery += "m.observacion = :observacion ";
            strQuery += "WHERE m.id = :id";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("registros", obj.getRegistros());
            query.setParameter("registrosExitosos", obj.getRegistrosExitosos());
            query.setParameter("periodo", obj.getPeriodoDate());
            if (obj.getFechaHoraFin() != null) {
                query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            }
            query.setParameter("observacion", obj.getObservacion());
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

    @Override
    public Integer insertarFallo(MpConsumoFallo fallo) throws Exception {
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

    public static MpConsumos castNegocioEntidad(MpConsumo obj) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        MpConsumos per = new MpConsumos();
        per.setPeriodo(formato.parse(obj.getPeriodo()));
        per.setServicio(obj.getServicio());
        per.setServicioDireccion(obj.getServicioDireccion());
        per.setServicioDescripcion(obj.getServicioDescripcion());
        per.setRegistros(obj.getRegistros());
        per.setEstado(obj.getEstado());
        per.setObservacion(obj.getObservacion());
        per.setFechaHoraInicio(obj.getFechaHoraInicio());
        return per;
    }

    public static MpConsumo castEntidadNegocio(MpConsumos ent) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        MpConsumo obj = new MpConsumo();
        obj.setId(ent.getId());
        obj.setPeriodo(formato.format(ent.getPeriodo()));
        obj.setServicio(ent.getServicio());
        obj.setRegistros(ent.getRegistros());
        obj.setRegistrosExitosos(ent.getRegistrosExitosos());
        obj.setEstado(ent.getEstado());
        obj.setObservacion(ent.getObservacion());
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        return obj;
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

}
