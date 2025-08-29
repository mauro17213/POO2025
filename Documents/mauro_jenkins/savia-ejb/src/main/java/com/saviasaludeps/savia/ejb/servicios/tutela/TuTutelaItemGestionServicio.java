/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItem;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItemGestion;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaItemGestiones;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaItems;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuTutelaItemGestionRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(TuTutelaItemGestionRemoto.class)
public class TuTutelaItemGestionServicio extends GenericoServicio implements TuTutelaItemGestionRemoto{
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM TuTutelaItemGestiones t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND t.id = " + (String) e.getValue() + " ";
                            break;
                        case "maeEstadoItemId":
                            strQuery += "AND t.maeEstadoItemId = " + e.getValue() + " ";
                            break;
                        case "tuTutelaItemId.id":
                            strQuery += "AND t.tuTutelaItemId.id = " + e.getValue() + " ";
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

    public List<TuTutelaItemGestion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuTutelaItemGestion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuTutelaItemGestiones t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND t.id = " + (String) e.getValue() + " ";
                            break;
                        case "maeEstadoItemId":
                            strQuery += "AND t.maeEstadoItemId = " + e.getValue() + " ";
                            break;
                        case "tuTutelaItemId.id":
                            strQuery += "AND t.tuTutelaItemId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            //strQuery += "AND t.activo = 1 ";
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden();
                strQuery += "t." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id ASC";
            }
            List<TuTutelaItemGestiones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (TuTutelaItemGestiones per : list) {
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
    public TuTutelaItemGestion consultar(int id) throws Exception {
        TuTutelaItemGestion objRes = null;
        try {
            objRes = castEntidadNegocio((TuTutelaItemGestiones) getEntityManager().find(TuTutelaItemGestiones.class, id));
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
    public int insertar(TuTutelaItemGestion seguimiento) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(seguimiento)).getId();
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar un seguimiento en item tutelas");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    @Override
    public void actualizar(TuTutelaItemGestion obj) throws Exception {
        try {
            String hql = "UPDATE TuTutelaItemGestiones SET"
                + " observacion = :observacion,"
                + " observacionIps = :observacionIps,"
                + " maeEstadoItemId = :maeEstadoItemId,"
                + " maeEstadoItemCodigo = :maeEstadoItemCodigo,"
                + " maeEstadoItemValor = :maeEstadoItemValor,"
                + ((obj.getTuTutelaItemId()!= null) ? " tuTutelaItemId.id = " + obj.getTuTutelaItemId().getId() + " , " : "")
                + " usuarioModifica = :usuarioModifica,"
                + " terminalModifica = :terminalModifica,"
                + " fechaHoraModifica = :fechaHoraModifica"
                + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("observacion", obj.getObservacion());
            query.setParameter("observacionIps", obj.getObservacionIps());
            query.setParameter("maeEstadoItemId", obj.getMaeEstadoItemId());
            query.setParameter("maeEstadoItemCodigo", obj.getMaeEstadoItemCodigo());
            query.setParameter("maeEstadoItemValor", obj.getMaeEstadoItemValor());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public TuTutelaItemGestion eliminar(int id) throws Exception {
        TuTutelaItemGestion obj = null;
        try {
            TuTutelaItemGestiones ent = getEntityManager().find(TuTutelaItemGestiones.class, id);
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
    
    public static TuTutelaItemGestiones castNegocioEntidad(TuTutelaItemGestion obj) {
        TuTutelaItemGestiones per = new TuTutelaItemGestiones();
        per.setId(obj.getId());        
        per.setObservacion(obj.getObservacion());
        per.setObservacionIps(obj.getObservacionIps());
        per.setMaeEstadoItemId(obj.getMaeEstadoItemId());
        per.setMaeEstadoItemCodigo(obj.getMaeEstadoItemCodigo());
        per.setMaeEstadoItemValor(obj.getMaeEstadoItemValor());
        if (obj.getTuTutelaItemId()!= null) {
            per.setTuTutelaItemId(new TuTutelaItems(obj.getTuTutelaItemId().getId()));
        }
        //validar si la línea de abajo es necesaria
        //per.setObservacion(obj.getObservacion().replace("​", "").replace("‐", "").replace("﻿", " ").replace("﻿﻿﻿﻿", " "));
        //Auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }
    
    public static List<TuTutelaItemGestion> castEntidadNegocio(List<TuTutelaItemGestiones> serviciosNegocio) {
        List<TuTutelaItemGestion> listaServicios = new ArrayList<>();
        for(TuTutelaItemGestiones tuSeguimientos:serviciosNegocio){
            listaServicios.add(castEntidadNegocio(tuSeguimientos));
        }
        /*serviciosNegocio.forEach(tuSeguimientos ->{
            listaServicios.add(castEntidadNegocio(tuSeguimientos));
        });*/
        return listaServicios;
    }
    
    public static TuTutelaItemGestion castEntidadNegocio(TuTutelaItemGestiones servicioNegocio) {
        TuTutelaItemGestion servicioEntidad = new TuTutelaItemGestion();
        servicioEntidad.setId(servicioNegocio.getId());
        servicioEntidad.setId(servicioNegocio.getId());        
        servicioEntidad.setObservacion(servicioNegocio.getObservacion());
        servicioEntidad.setObservacionIps(servicioNegocio.getObservacionIps());
        servicioEntidad.setMaeEstadoItemId(servicioNegocio.getMaeEstadoItemId());
        servicioEntidad.setMaeEstadoItemCodigo(servicioNegocio.getMaeEstadoItemCodigo());
        servicioEntidad.setMaeEstadoItemValor(servicioNegocio.getMaeEstadoItemValor());
        if (servicioNegocio.getTuTutelaItemId()!= null) {
            servicioEntidad.setTuTutelaItemId(new TuTutelaItem(servicioNegocio.getTuTutelaItemId().getId()));
        }
        //Auditoria
        servicioEntidad.setUsuarioCrea(servicioNegocio.getUsuarioCrea());
        servicioEntidad.setTerminalCrea(servicioNegocio.getTerminalCrea());
        servicioEntidad.setFechaHoraCrea(servicioNegocio.getFechaHoraCrea());
        servicioEntidad.setUsuarioModifica(servicioNegocio.getUsuarioModifica());
        servicioEntidad.setTerminalModifica(servicioNegocio.getTerminalModifica());
        servicioEntidad.setFechaHoraModifica(servicioNegocio.getFechaHoraModifica());
        return servicioEntidad;
    }
      
    public List<TuTutelaItemGestion> consultarLista(int idTutelaItem) throws java.lang.Exception {
        List<TuTutelaItemGestion> listResult = new ArrayList();
        try {
            String strQuery = "FROM TuTutelaItemGestiones p "
                    + "WHERE ";
            strQuery += " p.tuTutelaItemId.id = " + idTutelaItem + " ";

            List<TuTutelaItemGestiones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuTutelaItemGestiones cont : list) {
                listResult.add(castEntidadNegocio(cont));
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

}
