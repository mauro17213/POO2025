/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.DiaHabil;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnDiasHabiles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.CalendarioRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(CalendarioRemoto.class)
public class CalendarioServicio extends GenericoServicio implements CalendarioRemoto {

    @Override
    public List<DiaHabil> consultarTodos(ParamConsulta paramConsulta) throws Exception {
        List<DiaHabil> listResult = new ArrayList();
        String strQuery = "FROM GnDiasHabiles "
                + "WHERE fecha BETWEEN :fh_desde AND :fh_hasta";
        try {
            List<GnDiasHabiles> list = getEntityManager().createQuery(strQuery)
                    .setParameter("fh_desde", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP)
                    .setParameter("fh_hasta", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP)
                    .getResultList();
            for (GnDiasHabiles obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public boolean actualizar(DiaHabil obj) throws Exception {
        boolean crear = true;
        String strQuery = "FROM GnDiasHabiles WHERE fecha =:fecha";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("fecha", obj.getFecha());
            List<GnDiasHabiles> list = query.getResultList();
            if (!list.isEmpty()) {
                getEntityManager().remove(list.get(0));
                crear = false;
            } else {
                obj.setId(null);
                getEntityManager().merge(castNegocioEntidad(obj)).getId();
                crear = true;
            }
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return crear;
    }
    
    @Override
    public DiaHabil consultarDia(Date fecha) throws Exception {
        DiaHabil diaHabil = null;
        try {
            TypedQuery<GnDiasHabiles> query = em.createNamedQuery("GnDiasHabiles.findByFecha", GnDiasHabiles.class);
            query.setParameter("fecha",fecha);
            GnDiasHabiles dias = query.getSingleResult();
            if (dias!=null){
                diaHabil= castEntidadNegocio(dias);
            }
        } catch (NoResultException e) {
            diaHabil = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return diaHabil;
    }
    
    @Override
    public int consultarHabilies(Date fechaDesde, Date fechaHasta) throws Exception {
        int habilies = 0;
        try {            
            String strQuery = "SELECT COUNT(d) "
                    + "FROM GnDiasHabiles AS d "
                    + "WHERE d.fecha BETWEEN :fechaDesde AND :fechaHasta "
                    + "AND d.habil = 0";
            int noHabiles = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("fechaDesde", fechaDesde, TemporalType.DATE)
                    .setParameter("fechaHasta", fechaHasta, TemporalType.DATE)
                    .getSingleResult();
            int totales = (int) ((fechaHasta.getTime() - fechaDesde.getTime()) / 86400000);
            habilies = totales - noHabiles;
        } catch (NoResultException e) {
            habilies = 0;
        } catch (Exception e) {
            habilies = 0;
        } finally {
            cerrarEntityManager();
        }
        return habilies;
    }
    
    @Override
    public boolean esDiaHabil(Date fecha) throws Exception {
        boolean esHabil = true;
        try {            
            String strQuery = "SELECT COUNT(d) "
                    + "FROM GnDiasHabiles AS d "
                    + "WHERE d.fecha = :fecha "
                    + "AND d.habil = 0";
            int noHabiles = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("fecha", fecha, TemporalType.DATE)
                    .getSingleResult();
            esHabil = (noHabiles == 0);            
        } catch (NoResultException e) {
            esHabil = true;
        } catch (Exception e) {
            esHabil = true;
        } finally {
            cerrarEntityManager();
        }
        return esHabil;
    }

    public static DiaHabil castEntidadNegocio(GnDiasHabiles per) {
        DiaHabil obj = new DiaHabil();
        obj.setId(per.getId());
        obj.setAgno(per.getAgno());
        obj.setFecha(per.getFecha());
        obj.setHabil(per.getHabil());
        //Auditoria
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    public static GnDiasHabiles castNegocioEntidad(DiaHabil obj) {
        GnDiasHabiles per = new GnDiasHabiles();
        per.setId(obj.getId());
        per.setAgno(obj.getAgno());
        obj.setHabil(per.getHabil());
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(per.getFecha());
//        calendar.add(Calendar.DAY_OF_YEAR, 1);
//        obj.setFecha(calendar.getTime());
        per.setFecha(obj.getFecha());
        per.setHabil(obj.isHabil());
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }
}
