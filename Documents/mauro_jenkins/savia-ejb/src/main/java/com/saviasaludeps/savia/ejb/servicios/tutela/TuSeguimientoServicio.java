/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuSeguimiento;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.entidades.TuSeguimientos;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaEstados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuSeguimientoRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(TuSeguimientoRemoto.class)
@Local(TuSeguimientoLocal.class)
public class TuSeguimientoServicio extends GenericoServicio implements TuSeguimientoLocal, TuSeguimientoRemoto{
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM TuSeguimientos t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND t.id = " + (String) e.getValue() + " ";
                            break;
                        case "tuTutelaEstadosId.id":
                            strQuery += "AND t.tuTutelaEstadosId.id = " + e.getValue() + " ";
                            break;
                        case "tuTutelaEstadosId.tuTutelasId.id":
                            strQuery += "AND t.tuTutelaEstadosId.tuTutelasId.id = " + e.getValue() + " ";
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
    public List<TuSeguimiento> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuSeguimiento> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuSeguimientos t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND t.id = " + (String) e.getValue() + " ";
                            break;
                        case "tuTutelaEstadosId.id":
                            strQuery += "AND t.tuTutelaEstadosId.id = " + e.getValue() + " ";
                            break;
                        case "tuTutelaEstadosId.tuTutelasId.id":
                            strQuery += "AND t.tuTutelaEstadosId.tuTutelasId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            //strQuery += "AND t.activo = 1 ";
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().
                    replace("tuTutelaEstadosId.tuTutelaId.tuPersona.numeroDocumento", "tuTutelaEstadosId.tuTutelasId.tuPersonasId.numeroDocumento");
                strQuery += "t." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id ASC";
            }
            List<TuSeguimientos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (TuSeguimientos per : list) {
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
    public TuSeguimiento consultar(int id) throws Exception {
        TuSeguimiento objRes = null;
        try {
            objRes = castEntidadNegocio((TuSeguimientos) getEntityManager().find(TuSeguimientos.class, id));
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
    public int insertar(TuSeguimiento seguimiento) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(seguimiento)).getId();
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar un seguimiento en gestión tutelas");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    @Override
    public void actualizar(TuSeguimiento obj) throws Exception {
        try {
            String hql = "UPDATE TuSeguimientos SET"
                + " maeTipoSeguimientoId = :maeTipoSeguimientoId,"
                + " maeTipoSeguimientoCodigo = :maeTipoSeguimientoCodigo,"
                + " maeTipoSeguimientoValor = :maeTipoSeguimientoValor,"
                + ((obj.getObservacion() != null) ? " observacion = :observacion," : "") 
                + ((obj.getFechaSeguimiento() != null) ? " fechaSeguimiento = :fechaSeguimiento," : "") 
                + " usuarioModifica = :usuarioModifica,"
                + " terminalModifica = :terminalModifica,"
                + " fechaHoraModifica = :fechaHoraModifica"
                + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("maeTipoSeguimientoId", obj.getMaeTipoSeguimientoId());
            query.setParameter("maeTipoSeguimientoCodigo", obj.getMaeTipoSeguimientoCodigo());
            query.setParameter("maeTipoSeguimientoValor", obj.getMaeTipoSeguimientoValor());
            if(obj.getObservacion() != null)
                query.setParameter("observacion", obj.getObservacion().replace("​", "").replace("‐", ""));
            if(obj.getFechaSeguimiento() != null)
                query.setParameter("fechaSeguimiento", new Date());
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
    public TuSeguimiento eliminar(int id) throws Exception {
        TuSeguimiento obj = null;
        try {
            TuSeguimientos ent = getEntityManager().find(TuSeguimientos.class, id);
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
    
    public static TuSeguimientos castNegocioEntidad(TuSeguimiento obj) {
        TuSeguimientos per = new TuSeguimientos();
        per.setId(obj.getId());
        if (obj.getTuTutelaEstadosId() != null) {
            per.setTuTutelaEstadosId(new TuTutelaEstados(obj.getTuTutelaEstadosId().getId()));
        }
        per.setMaeTipoSeguimientoId(obj.getMaeTipoSeguimientoId());
        per.setMaeTipoSeguimientoCodigo(obj.getMaeTipoSeguimientoCodigo());
        per.setMaeTipoSeguimientoValor(obj.getMaeTipoSeguimientoValor());
        if (obj.getGestorGnUsuarioId() != null) {
            per.setGestorGnUsuarioId(new GnUsuarios(obj.getGestorGnUsuarioId().getId()));
        }
        per.setFechaSeguimiento(obj.getFechaSeguimiento());
        if (obj.getNotificadoGnUsuarioId() != null) {
            per.setNotificadoGnUsuarioId(new GnUsuarios(obj.getNotificadoGnUsuarioId().getId()));
        }
        per.setObservacion(obj.getObservacion().replace("​", "").replace("‐", "").replace("﻿", " ").replace("﻿﻿﻿﻿", " "));
        //Auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }
    
    public static List<TuSeguimiento> castEntidadNegocio(List<TuSeguimientos> serviciosNegocio) {
        List<TuSeguimiento> listaServicios = new ArrayList<>();
        for(TuSeguimientos tuSeguimientos:serviciosNegocio){
            listaServicios.add(castEntidadNegocio(tuSeguimientos));
        }
        /*serviciosNegocio.forEach(tuSeguimientos ->{
            listaServicios.add(castEntidadNegocio(tuSeguimientos));
        });*/
        return listaServicios;
    }
    
    public static TuSeguimiento castEntidadNegocio(TuSeguimientos servicioNegocio) {
        TuSeguimiento servicioEntidad = new TuSeguimiento();
        servicioEntidad.setId(servicioNegocio.getId());
        servicioEntidad.setMaeTipoSeguimientoId(servicioNegocio.getMaeTipoSeguimientoId());
        servicioEntidad.setMaeTipoSeguimientoCodigo(servicioNegocio.getMaeTipoSeguimientoCodigo());
        servicioEntidad.setMaeTipoSeguimientoValor(servicioNegocio.getMaeTipoSeguimientoValor());
        
        if(servicioNegocio.getGestorGnUsuarioId() != null){
            servicioEntidad.setGestorGnUsuarioId(new Usuario(servicioNegocio.getGestorGnUsuarioId().getId(), servicioNegocio.getGestorGnUsuarioId().getUsuario(), servicioNegocio.getGestorGnUsuarioId().getNombre()));
        }
        
        servicioEntidad.setFechaSeguimiento(servicioNegocio.getFechaSeguimiento());
        if(servicioNegocio.getNotificadoGnUsuarioId() != null){
            servicioEntidad.setNotificadoGnUsuarioId(new Usuario(servicioNegocio.getNotificadoGnUsuarioId().getId(), servicioNegocio.getNotificadoGnUsuarioId().getUsuario(), servicioNegocio.getNotificadoGnUsuarioId().getNombre()));
        }
        servicioEntidad.setObservacion(servicioNegocio.getObservacion());
        if(servicioNegocio.getTuTutelaEstadosId() != null){
            servicioEntidad.setTuTutelaEstadosId(TuEstadoTutelaServicio.castEntidadNegocioSeguimiento(servicioNegocio.getTuTutelaEstadosId()));
        }
        if(servicioNegocio.getTuAdjuntosList() != null && !servicioNegocio.getTuAdjuntosList().isEmpty()){
            servicioEntidad.setTuAdjuntosList(TuAdjuntoServicio.castEntidadNegocio(servicioNegocio.getTuAdjuntosList()));
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
      
    @Override
    public List<TuSeguimiento> consultarLista(int idTutela) throws java.lang.Exception {
        List<TuSeguimiento> listResult = new ArrayList();
        try {
            String strQuery = "FROM TuSeguimientos p "
                    + "WHERE ";
            strQuery += " p.tuTutelaEstadosId.tuTutelasId.id = " + idTutela + " ";

            List<TuSeguimientos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuSeguimientos cont : list) {
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
