/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmInforme;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmInformes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmInformeRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jperezn
 */
@Stateless
@Remote(CmInformeRemoto.class)
@Local(CmInformeLocal.class)
public class CmInformeServicio extends GenericoServicio implements CmInformeLocal, CmInformeRemoto {

    @Override
    public  int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception{
         int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmi) FROM CmInformes cmi "
                    + "WHERE cmi.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND cmi.tipo = " + (String) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND cmi.estado = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND cmi.fechaHoraInicio = '" + (String) e.getValue() + "' ";
                            break;
                        case "registros":
                            strQuery += "AND cmi.registros = '" + (String) e.getValue() + "' ";
                            break;
                        case "ruta":
                            strQuery += "AND cmi.ruta = '" + (String) e.getValue() + "' ";
                            break;
                        case "archivo":
                            strQuery += "AND cmi.archivo = '" + (String) e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND cmi.usuarioCrea = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }
    
    @Override    
    public List<CmInforme> consultarLista(ParamConsulta paramConsulta) throws Exception{
         List<CmInforme> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmInformes cmi "
                    + "WHERE cmi.id > 0  ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND cmi.tipo = " + (String) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND cmi.estado = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND cmi.fechaHoraInicio = '" + (String) e.getValue() + "' ";
                            break;
                        case "registros":
                            strQuery += "AND cmi.registros = '" + (String) e.getValue() + "' ";
                            break;
                        case "ruta":
                            strQuery += "AND cmi.ruta = '" + (String) e.getValue() + "' ";
                            break;
                        case "archivo":
                            strQuery += "AND cmi.archivo = '" + (String) e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND cmi.usuarioCrea = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "cmi." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "cmi.id DESC";
            }
            List<CmInformes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmInformes per : list) {
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
    public CmInforme consultar(int id) throws Exception {
        CmInforme obj = null;
        try {
            CmInformes per = (CmInformes) getEntityManager().find(CmInformes.class, id);
            obj = castEntidadNegocio(per);
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
    public int insertar(CmInforme obj) throws Exception {
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
    public void actualizar(CmInforme obj) throws Exception {
        try {
           CmInformes informe =  castNegocioEntidad(obj);
           
           String hql = "UPDATE CmInformes SET"
               + " tipo = :tipo,"
               + " estado = :estado,"
               + " fechaDesde = :fechaDesde,"
               + " fechaHasta  = :fechaHasta,"
               + " fechaHoraInicio = :fechaHoraInicio,"
               + " fechaHoraFin = :fechaHoraFin,"
               + " registros = :registros,"
               + " ruta = :ruta,"
               + " archivo = :archivo, "
               + " observacion = :observacion, "
               + " usuarioCrea = :usuarioCrea "
               + " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tipo", informe.getTipo());
            query.setParameter("estado", informe.getEstado());
            query.setParameter("fechaDesde", informe.getFechaDesde());
            query.setParameter("fechaHasta",informe.getFechaHasta());
            query.setParameter("fechaHoraInicio", informe.getFechaHoraInicio());
            query.setParameter("fechaHoraFin", informe.getFechaHoraFin());
            query.setParameter("registros", informe.getRegistros());
            query.setParameter("ruta", informe.getRuta());
            query.setParameter("archivo", informe.getArchivo());
            query.setParameter("observacion", informe.getObservacion());
            query.setParameter("usuarioCrea", informe.getUsuarioCrea());
            query.setParameter("id", informe.getId());
            query.executeUpdate(); 
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmInforme eliminar(int id) throws Exception {
        CmInforme obj = null;
        try {
            CmInformes per = getEntityManager().find(CmInformes.class, id);
            if (per != null) {
                obj = castEntidadNegocio(per);
                getEntityManager().remove(per);
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

    public static CmInformes castNegocioEntidad(CmInforme obj) {
        CmInformes per = new CmInformes();
        per.setId(obj.getId());
        per.setTipo(obj.getTipo());
        per.setEstado(obj.getEstado());     
        per.setFechaDesde(obj.getFechaDesde());
        per.setFechaHasta(obj.getFechaHasta());
        per.setFechaHoraInicio(obj.getFechaHoraInicio());
        per.setFechaHoraFin(obj.getFechaHoraFin());
        per.setRegistros(obj.getRegistros());
        per.setRuta(obj.getRuta());
        per.setArchivo(obj.getArchivo());
        per.setObservacion(obj.getObservacion());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        return per;
    }

    private CmInforme castEntidadNegocio(CmInformes neg) {
        CmInforme ent = new CmInforme();
        ent.setId(neg.getId());
        ent.setTipo(neg.getTipo());
        ent.setEstado(neg.getEstado());     
        ent.setFechaDesde(neg.getFechaDesde());
        ent.setFechaHasta(neg.getFechaHasta());
        ent.setFechaHoraInicio(neg.getFechaHoraInicio());
        ent.setFechaHoraFin(neg.getFechaHoraFin());
        ent.setRegistros(neg.getRegistros());
        ent.setRuta(neg.getRuta());
        ent.setArchivo(neg.getArchivo());
        ent.setObservacion(neg.getObservacion());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        return ent;
    }
}
