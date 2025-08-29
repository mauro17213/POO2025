/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import com.saviasaludeps.savia.dominio.tutela.TuPersona;
import com.saviasaludeps.savia.dominio.tutela.TuTutela;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstado;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItem;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.entidades.TuJuzgados;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaEstados;
import com.saviasaludeps.savia.ejb.entidades.TuTutelas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.utilidades.Util;
import com.saviasaludeps.savia.negocio.tutela.TuEstadoRemoto;
import com.sun.mail.imap.ACL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jramirer
 */
@Stateless
@Remote(TuEstadoRemoto.class)
public class TuEstadoTutelaServicio extends GenericoServicio implements TuEstadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(e.id) FROM TuTutelaEstados e "
                    + "WHERE e.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND e.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "tuTutelasId.id":
                            strQuery += "AND e.tuTutelasId.id = " + (int) e.getValue() + " ";
                            break;
                        case "maeEstadoId":
                            String cadenaMaestado = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND e.maeEstadoId IN ( " +  cadenaMaestado + ") ";
                            break;
                        case "maeProcesoId":
                            String cadenaProceso = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND e.maeProcesoId IN ( " +  cadenaProceso + ") ";
                            break;
                        case "responsableGnUsuarioId.nombre":
                            strQuery += "AND e.responsableGnUsuarioId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "gestorGnUsuarioId.nombre":
                            strQuery += "AND e.gestorGnUsuarioId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "abogadoGnUsuarioId.nombre":
                            strQuery += "AND e.abogadoGnUsuarioId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "medicoGnUsuarioId.nombre":
                            strQuery += "AND e.medicoGnUsuarioId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "cuantia":
                            strQuery += "AND e.cuantia LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "diasArresto":
                            strQuery += "AND e.diasArresto LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "uvt":
                            strQuery += "AND e.uvt LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "entregaSucesiva":
                            String convertir = (e.getValue().equals("true")) ? "1": "0";
                            strQuery += "AND e.entregaSucesiva = " + convertir + " ";
                            break;
                        case "exoneracion":
                            String convertirEx = (e.getValue().equals("true")) ? "1": "0";
                            strQuery += "AND t.exoneracion = " + convertirEx + " ";
                            break;
                        case "impugnacion":
                            String convertirIm = (e.getValue().equals("true")) ? "1": "0";
                            strQuery += "AND e.impugnacion = " + convertirIm + " ";
                            break;
                        case "integralidad":
                            String convertirIn = (e.getValue().equals("true")) ? "1": "0";
                            strQuery += "AND e.integralidad = " + convertirIn + " ";
                            break;
                        case "borrado":
                            strQuery += "AND e.borrado = " + (String) e.getValue() + " ";
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
    public List<TuTutelaEstado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuTutelaEstado> listResult = new ArrayList();
        try {
            String strQuery = "SELECT e FROM TuTutelaEstados e "
                    + "WHERE e.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND e.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "tuTutelasId.id":
                            strQuery += "AND e.tuTutelasId.id = " + (int) e.getValue() + " ";
                            break;
                        case "maeEstadoId":
                            String cadenaMaestado = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND e.maeEstadoId IN ( " +  cadenaMaestado + ") ";
                            break;
                        case "maeProcesoId":
                            String cadenaProceso = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND e.maeProcesoId IN ( " +  cadenaProceso + ") ";
                            break;
                        case "responsableGnUsuarioId.nombre":
                            strQuery += "AND e.responsableGnUsuarioId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "gestorGnUsuarioId.nombre":
                            strQuery += "AND e.gestorGnUsuarioId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "abogadoGnUsuarioId.nombre":
                            strQuery += "AND e.abogadoGnUsuarioId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "medicoGnUsuarioId.nombre":
                            strQuery += "AND e.medicoGnUsuarioId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "cuantia":
                            strQuery += "AND e.cuantia LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "diasArresto":
                            strQuery += "AND e.diasArresto LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "uvt":
                            strQuery += "AND e.uvt LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "entregaSucesiva":
                            String convertir = (e.getValue().equals("true")) ? "1": "0";
                            strQuery += "AND e.entregaSucesiva = " + convertir + " ";
                            break;
                        case "exoneracion":
                            String convertirEx = (e.getValue().equals("true")) ? "1": "0";
                            strQuery += "AND t.exoneracion = " + convertirEx + " ";
                            break;
                        case "impugnacion":
                            String convertirIm = (e.getValue().equals("true")) ? "1": "0";
                            strQuery += "AND e.impugnacion = " + convertirIm + " ";
                            break;
                        case "integralidad":
                            String convertirIn = (e.getValue().equals("true")) ? "1": "0";
                            strQuery += "AND e.integralidad = " + convertirIn + " ";
                            break;
                        case "borrado":
                            strQuery += "AND e.borrado = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "e." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "e.id ASC";
            }
            List<TuTutelaEstados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (TuTutelaEstados per : list) {
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
    public TuTutelaEstado consultar(int id) throws Exception {
        TuTutelaEstado objRes = null;
        try {
            objRes = castEntidadNegocio((TuTutelaEstados) getEntityManager().find(TuTutelaEstados.class, id));
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
    public int insertar(TuTutelaEstado obj) throws Exception {
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
    public void actualizar(TuTutelaEstado obj) throws Exception {
        try {
            String hql = "UPDATE TuTutelaEstados SET"
                + " tuJuzgadosId.id = :tuJuzgadosId,"
                + " maeEstadoId = :maeEstadoId,"
                + " maeEstadoCodigo = :maeEstadoCodigo,"
                + " maeEstadoValor = :maeEstadoValor,"      
                + " maeProcesoId = :maeProcesoId,"
                + " maeProcesoCodigo = :maeProcesoCodigo,"
                + " maeProcesoValor = :maeProcesoValor,"
                + " responsableGnUsuarioId.id = :responsableGnUsuarioId,"
                + " gestorGnUsuarioId.id = :gestorGnUsuarioId,"
                + " abogadoGnUsuarioId.id = :abogadoGnUsuarioId,"
                + " medicoGnUsuarioId.id = :medicoGnUsuarioId,"
                + " maeClaseSancionId = :maeClaseSancionId,"
                + " maeClaseSancionCodigo = :maeClaseSancionCodigo,"
                + " maeClaseSancionValor = :maeClaseSancionValor,"
                + " maeClaseArrestoId = :maeClaseArrestoId,"
                + " maeClaseArrestoCodigo = :maeClaseArrestoCodigo,"
                + " maeClaseArrestoValor = :maeClaseArrestoValor,"
                + " cuantia = :cuantia,"
                + " diasArresto = :diasArresto,"
                + " uvt = :uvt,"
                + " emailGestorRemitente = :emailGestorRemitente,"
                + " entregaSucesiva = :entregaSucesiva,"
                + " exoneracion = :exoneracion,"
                + " integralidad = :integralidad,"
                + " medidaProvisional = :medidaProvisional,"
                + " maeTipoFalloId = :maeTipoFalloId,"                   
                + " maeTipoFalloCodigo = :maeTipoFalloCodigo,"
                + " maeTipoFalloValor = :maeTipoFalloValor,"
                + " fechaNotificacion = :fechaNotificacion,"
                + " fechaVencimiento = :fechaVencimiento,"
                + " observacion = :observacion,"
                + " cantidadServicio = :cantidadServicio,"
                + " cantidadServicioCumplido = :cantidadServicioCumplido,"
                + " diasCambioEstado = :diasCambioEstado,"
                + " maeSmlvId = :maeSmlvId,"
                + " maeSmlvCodigo = :maeSmlvCodigo,"
                + " maeSmlvValor = :maeSmlvValor,"
                + ((obj.getBorrado() != null) ? " borrado = :borrado," : "")
                + ((obj.getUsuarioBorra() != null) ? " usuarioBorra = :usuarioBorra," : "")
                + ((obj.getTerminalBorra() != null) ? " terminalBorra = :terminalBorra," : "")
                + ((obj.getFechaHoraBorra() != null) ? " fechaHoraBorra = :fechaHoraBorra," : "")
                + " cantidadItems = :cantidadItems,"
                + " cantidadItemsCerrados = :cantidadItemsCerrados,"
                + " usuarioModifica = :usuarioModifica,"
                + " terminalModifica = :terminalModifica,"
                + " fechaHoraModifica = :fechaHoraModifica"
                + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tuJuzgadosId", obj.getTuJuzgadoId().getId());
            query.setParameter("maeEstadoId", obj.getMaeEstadoId());
            query.setParameter("maeEstadoCodigo", obj.getMaeEstadoCodigo());
            query.setParameter("maeEstadoValor", obj.getMaeEstadoValor());
            query.setParameter("maeProcesoId", obj.getMaeProcesoId());
            query.setParameter("maeProcesoCodigo", obj.getMaeProcesoCodigo());
            query.setParameter("maeProcesoValor", obj.getMaeProcesoValor());
            query.setParameter("responsableGnUsuarioId", obj.getResponsableGnUsuarioId().getId());
            query.setParameter("gestorGnUsuarioId", obj.getGestorGnUsuarioId().getId());
            query.setParameter("abogadoGnUsuarioId", obj.getAbogadoGnUsuarioId().getId());
            query.setParameter("medicoGnUsuarioId", obj.getMedicoGnUsuarioId().getId());
            query.setParameter("maeClaseSancionId", obj.getMaeClaseSancionId());
            query.setParameter("maeClaseSancionCodigo", obj.getMaeClaseSancionCodigo());
            query.setParameter("maeClaseSancionValor", obj.getMaeClaseSancionValor());
            query.setParameter("maeClaseArrestoId", obj.getMaeClaseArrestoId());
            query.setParameter("maeClaseArrestoCodigo", obj.getMaeClaseArrestoCodigo());
            query.setParameter("maeClaseArrestoValor", obj.getMaeClaseArrestoValor());
            query.setParameter("cuantia", obj.getCuantia());
            query.setParameter("diasArresto", obj.getDiasArresto());
            query.setParameter("uvt", obj.getUvt());
            query.setParameter("emailGestorRemitente", obj.getEmailGestorRemitente());
            query.setParameter("entregaSucesiva", obj.getEntregaSucesiva());
            query.setParameter("exoneracion", obj.getExoneracion());
            query.setParameter("integralidad", obj.getIntegralidad());
            query.setParameter("medidaProvisional", obj.getMedidadProvisional());
            query.setParameter("maeTipoFalloId", obj.getMaeTipoFalloId());
            query.setParameter("maeTipoFalloCodigo", obj.getMaeTipoFalloCodigo());
            query.setParameter("maeTipoFalloValor", obj.getMaeTipoFalloValor());
            query.setParameter("fechaNotificacion", obj.getFechaNotificacion());
            query.setParameter("fechaVencimiento", obj.getFechaVencimiento());
            query.setParameter("observacion", obj.getObservacion());
            query.setParameter("cantidadServicio", obj.getCantidadServicio());
            query.setParameter("cantidadServicioCumplido", obj.getCantidadServicioCumplido());
            query.setParameter("diasCambioEstado", obj.getDiasCambioEstado());
            query.setParameter("maeSmlvId", obj.getMaeSmlvId());
            query.setParameter("maeSmlvCodigo", obj.getMaeSmlvCodigo());
            query.setParameter("maeSmlvValor", obj.getMaeSmlvValor());
            if(obj.getBorrado() != null){
                query.setParameter("borrado", obj.getBorrado());
            }
            if(obj.getUsuarioBorra() != null){
                query.setParameter("usuarioBorra", obj.getUsuarioBorra());
            }
            if(obj.getTerminalBorra() != null){
                query.setParameter("terminalBorra", obj.getTerminalBorra());
            }
            if(obj.getFechaHoraBorra() != null){
                query.setParameter("fechaHoraBorra", obj.getFechaHoraBorra());
            }
            query.setParameter("cantidadItems", obj.getCantidadItems());
            query.setParameter("cantidadItemsCerrados", obj.getCantidadItemsCerrados());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
//            int id = obj.getId(); 
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarResponsableGnUsuario(TuTutelaEstado obj) throws Exception {
        try {
            String hql = "UPDATE TuTutelaEstados SET "
                + " responsableGnUsuarioId.id = :responsable_gn_usuario_id"
                + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("responsable_gn_usuario_id", obj.getResponsableGnUsuarioId().getId());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarProcesoEstado(TuTutelaEstado obj) throws Exception {
        try {
            String hql = "UPDATE TuTutelaEstados SET "
                + " maeProcesoId = :maeProcesoId,"
                + " maeProcesoCodigo = :maeProcesoCodigo,"
                + " maeProcesoValor = :maeProcesoValor"
                + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("maeProcesoId", obj.getMaeProcesoId());
            query.setParameter("maeProcesoCodigo", obj.getMaeProcesoCodigo());
            query.setParameter("maeProcesoValor", obj.getMaeProcesoValor());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public TuTutelaEstado eliminar(int id) throws Exception {
        TuTutelaEstado obj = null;
//        try {
//            TuTutelaEstados ent = getEntityManager().find(TuTutelaEstados.class, id);
//            if (ent != null) {
//                obj = castEntidadNegocio(ent);
//                getEntityManager().remove(ent);
//            }
//        } catch (NoResultException e) {
//            obj = null;
//        } catch (Exception e) {
//            Exception(ELIMINAR, e);
//        } finally {
//            cerrarEntityManager();
//        }
        return obj;
    }

    @Override
    public List<TuTutelaEstado> consultarListaEstadosPorTutela(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<TuTutelaEstado> listResult = new ArrayList();
        try {
            String strQuery = "FROM TuTutelaEstados e ";
            strQuery += " WHERE e.tuTutelasId.id = :tutelaId";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND e.id LIKE '%" + e.getValue() + "%' ";
                            break;

                    }
                }
            }

            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {

                String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                        replace("gsZona", "gsZonasId");
                strQuery += " cmd." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmd.fechaHoraCrea DESC , e.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);

            query.setParameter("facuturaId", paramConsulta.getParametroConsulta1());

            List<TuTutelaEstado> list;

            if (paramConsulta.getParametroConsulta2() != null) {
                list = query
                        .getResultList();
            } else {
                list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
            }

            for (TuTutelaEstado per : list) {
//                listResult.add(castEntidadNegocio(per));
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
    public List<TuTutelaEstado> consultarEstadosPorTutela(int id) throws Exception {
       
        List<TuTutelaEstado> listResult = new ArrayList();
        String strQuery = "FROM TuTutelaEstados e "
                      + "WHERE e.tuTutelasId.id = :tutelaId "
                      + "AND e.borrado != 1"
                      + "ORDER BY e.id DESC";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("tutelaId", id);
            List<TuTutelaEstados> list = query.getResultList();
            for (TuTutelaEstados obj : list) {
                listResult.add(castEntidadNegocio(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    public static TuTutelaEstado castEntidadNegocio(TuTutelaEstados per) {
        TuTutelaEstado obj = new TuTutelaEstado();
        obj.setId(per.getId());
        obj.setMaeProcesoId(per.getMaeProcesoId());
        obj.setMaeProcesoCodigo(per.getMaeProcesoCodigo());
        obj.setMaeProcesoValor(per.getMaeProcesoValor());
        
        obj.setMaeEstadoId(per.getMaeEstadoId());
        obj.setMaeEstadoCodigo(per.getMaeEstadoCodigo());
        obj.setMaeEstadoValor(per.getMaeEstadoValor());
        
        obj.setMaeTipoFalloId(per.getMaeTipoFalloId());
        obj.setMaeTipoFalloCodigo(per.getMaeTipoFalloCodigo());
        obj.setMaeTipoFalloValor(per.getMaeTipoFalloValor());
        obj.setIntegralidad(per.getIntegralidad());
        obj.setEntregaSucesiva(per.getEntregaSucesiva());
        obj.setExoneracion(per.getExoneracion());
        obj.setMaeClaseSancionId(per.getMaeClaseSancionId());
        obj.setMaeClaseSancionCodigo(per.getMaeClaseSancionCodigo());
        obj.setMaeClaseSancionValor(per.getMaeClaseSancionValor());
        obj.setMaeSmlvId(per.getMaeSmlvId());
        obj.setMaeSmlvCodigo(per.getMaeSmlvCodigo());
        obj.setMaeSmlvValor(per.getMaeSmlvValor());
        obj.setMaeClaseArrestoId(per.getMaeClaseArrestoId());
        obj.setMaeClaseArrestoCodigo(per.getMaeClaseArrestoCodigo());
        obj.setMaeClaseArrestoValor(per.getMaeClaseArrestoValor());
        
        //obj.setCuantia(Integer.parseInt(per.getCuantia().toString()));
        obj.setDiasArresto(per.getDiasArresto());
        obj.setUvt(per.getUvt());
        obj.setEmailGestorRemitente(per.getEmailGestorRemitente());
        obj.setImpugnacion(per.getImpugnacion());
        
        obj.setMedidadProvisional(per.getMedidaProvisional());
        obj.setCantidadServicio(per.getCantidadServicio());
        obj.setFechaNotificacion(per.getFechaNotificacion());
        obj.setFechaVencimiento(per.getFechaVencimiento());
        obj.setObservacion(per.getObservacion());
        obj.setCantidadServicioCumplido(per.getCantidadServicioCumplido());
        obj.setDiasCambioEstado(per.getDiasCambioEstado());
        //2025-07-17 jyperez nuevos campos
        obj.setCantidadItems(per.getCantidadItems());
        obj.setCantidadItemsCerrados(per.getCantidadItemsCerrados());
        //auditoria
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        if(per.getTuJuzgadosId() != null){
            TuJuzgado tuJuzgado = new TuJuzgado();
            tuJuzgado.setId(per.getTuJuzgadosId().getId());
            tuJuzgado.setNombre(per.getTuJuzgadosId().getNombre());
            tuJuzgado.setCodigo_despacho(per.getTuJuzgadosId().getCodigoDespacho());
            obj.setTuJuzgadoId(tuJuzgado);
        }
        if (per.getResponsableGnUsuarioId() != null ){
            
            obj.setResponsableGnUsuarioId(new Usuario(per.getResponsableGnUsuarioId().getId(), per.getResponsableGnUsuarioId().getUsuario(), per.getResponsableGnUsuarioId().getNombre()));
        }
        if (per.getGestorGnUsuarioId() != null){
            obj.setGestorGnUsuarioId(new Usuario(per.getGestorGnUsuarioId().getId(), per.getGestorGnUsuarioId().getUsuario(), per.getGestorGnUsuarioId().getNombre()));
        }
        if(per.getAbogadoGnUsuarioId() != null){
            obj.setAbogadoGnUsuarioId(new Usuario(per.getAbogadoGnUsuarioId().getId(), per.getAbogadoGnUsuarioId().getUsuario(), per.getAbogadoGnUsuarioId().getNombre()));
        }
        if(per.getMedicoGnUsuarioId() != null){
            obj.setMedicoGnUsuarioId(new Usuario(per.getMedicoGnUsuarioId().getId(), per.getMedicoGnUsuarioId().getUsuario(), per.getMedicoGnUsuarioId().getNombre()));
        }
        if(per.getTuTutelaItemsList() != null && !per.getTuTutelaItemsList().isEmpty()){
            obj.setListaTuTutelaItem(TuTutelaItemServicio.castEntidadNegocio(per.getTuTutelaItemsList()));
        }
        if(per.getTuSeguimientosList() != null && !per.getTuSeguimientosList().isEmpty()){
            obj.setTuSeguimientosList(TuSeguimientoServicio.castEntidadNegocio(per.getTuSeguimientosList()));
        }
        if(per.getTuAdjuntosList() != null && !per.getTuAdjuntosList().isEmpty()){
            obj.setTuAdjuntosList(TuAdjuntoServicio.castEntidadNegocio(per.getTuAdjuntosList()));
        }
        return obj;
    }
    
    public static TuTutelaEstado castEntidadNegocioSeguimiento(TuTutelaEstados per) {
        TuTutelaEstado obj = new TuTutelaEstado();
        obj.setId(per.getId());
        obj.setMaeEstadoId(per.getMaeEstadoId());
        obj.setMaeEstadoCodigo(per.getMaeEstadoValor());
        obj.setMaeEstadoValor(per.getMaeEstadoValor());
        obj.setMaeProcesoId(per.getMaeProcesoId());
        obj.setMaeProcesoCodigo(per.getMaeProcesoCodigo());
        obj.setMaeProcesoValor(per.getMaeProcesoValor());
        obj.setMaeSmlvId(per.getMaeSmlvId());
        obj.setMaeSmlvCodigo(per.getMaeSmlvCodigo());
        obj.setMaeSmlvValor(per.getMaeSmlvValor());
        obj.setMaeClaseSancionId(per.getMaeClaseSancionId());
        obj.setMaeClaseSancionCodigo(per.getMaeClaseSancionCodigo());
        obj.setMaeClaseSancionValor(per.getMaeClaseSancionValor());
        obj.setMaeClaseArrestoId(per.getMaeClaseArrestoId());
        obj.setMaeClaseArrestoCodigo(per.getMaeClaseArrestoCodigo());
        obj.setMaeClaseArrestoValor(per.getMaeClaseArrestoValor());
        obj.setMaeTipoFalloId(per.getMaeTipoFalloId());
        obj.setMaeTipoFalloCodigo(per.getMaeTipoFalloCodigo());
        obj.setMaeTipoFalloValor(per.getMaeTipoFalloValor());
        //obj.setCuantia(Integer.parseInt(per.getCuantia().toString()));
        obj.setDiasArresto(per.getDiasArresto());
        obj.setUvt(per.getUvt());
        obj.setEmailGestorRemitente(per.getEmailGestorRemitente());
        obj.setEntregaSucesiva(per.getEntregaSucesiva());
        obj.setExoneracion(per.getExoneracion());
        obj.setImpugnacion(per.getImpugnacion());
        obj.setIntegralidad(per.getIntegralidad());
        obj.setMedidadProvisional(per.getMedidaProvisional());
        obj.setCantidadServicio(per.getCantidadServicio());
        obj.setFechaNotificacion(per.getFechaNotificacion());
        obj.setFechaVencimiento(per.getFechaVencimiento());
        obj.setObservacion(per.getObservacion());
        obj.setCantidadServicioCumplido(per.getCantidadServicioCumplido());
        obj.setDiasCambioEstado(per.getDiasCambioEstado());
        if(per.getTuTutelasId() != null){
            TuTutela tutela = new TuTutela();
            tutela.setId(per.getTuTutelasId().getId());
            if(per.getTuTutelasId() != null){
                TuPersona tuPersona = new TuPersona();
                tuPersona.setId(per.getTuTutelasId().getTuPersonasId().getId());
                tuPersona.setNumeroDocumento(per.getTuTutelasId().getTuPersonasId().getNumeroDocumento());
                tutela.setTuPersona(tuPersona);
            }
            obj.setTuTutelaId(tutela);
        }
        if (per.getResponsableGnUsuarioId() != null ){
            obj.setResponsableGnUsuarioId(new Usuario(per.getResponsableGnUsuarioId().getId(), per.getResponsableGnUsuarioId().getUsuario(), per.getResponsableGnUsuarioId().getNombre()));
        }
        if (per.getGestorGnUsuarioId() != null) {
            obj.setGestorGnUsuarioId(new Usuario(per.getGestorGnUsuarioId().getId(), per.getGestorGnUsuarioId().getUsuario(), per.getGestorGnUsuarioId().getNombre()));
        }
        if(per.getAbogadoGnUsuarioId() != null){
            obj.setAbogadoGnUsuarioId(new Usuario(per.getAbogadoGnUsuarioId().getId(), per.getAbogadoGnUsuarioId().getUsuario(), per.getAbogadoGnUsuarioId().getNombre()));
        }
        if(per.getMedicoGnUsuarioId() != null){
            obj.setMedicoGnUsuarioId(new Usuario(per.getMedicoGnUsuarioId().getId(), per.getMedicoGnUsuarioId().getUsuario(), per.getMedicoGnUsuarioId().getNombre()));
        }
        
        return obj;
    }
    
    public static TuTutelaEstados castNegocioEntidad(TuTutelaEstado obj) {
        TuTutelaEstados per = new TuTutelaEstados();
        per.setId(obj.getId());
        if(obj.getTuTutelaId() != null ){
            per.setTuTutelasId(new TuTutelas(obj.getTuTutelaId().getId()));
        }
        if(obj.getTuJuzgadoId() != null){
            per.setTuJuzgadosId(new TuJuzgados(obj.getTuJuzgadoId().getId()));
        }
        per.setMaeEstadoId(obj.getMaeEstadoId());
        per.setMaeEstadoCodigo(obj.getMaeEstadoCodigo());
        per.setMaeEstadoValor(obj.getMaeEstadoValor());
        per.setMaeProcesoId(obj.getMaeProcesoId());
        per.setMaeProcesoCodigo(obj.getMaeProcesoCodigo());
        per.setMaeProcesoValor(obj.getMaeProcesoValor());
        if(obj.getResponsableGnUsuarioId() != null){
            per.setResponsableGnUsuarioId(new GnUsuarios(obj.getResponsableGnUsuarioId().getId()));
        }
        if(obj.getAbogadoGnUsuarioId() != null){
            per.setAbogadoGnUsuarioId(new GnUsuarios(obj.getAbogadoGnUsuarioId().getId()));
        }
        if(obj.getGestorGnUsuarioId() != null){
            per.setGestorGnUsuarioId(new GnUsuarios(obj.getGestorGnUsuarioId().getId()));
        }
        if(obj.getMedicoGnUsuarioId() != null){
            per.setMedicoGnUsuarioId(new GnUsuarios(obj.getMedicoGnUsuarioId().getId()));
        }
        per.setMaeClaseSancionId(obj.getMaeClaseSancionId());
        per.setMaeClaseSancionCodigo(obj.getMaeClaseSancionCodigo());
        per.setMaeClaseSancionValor(obj.getMaeClaseSancionValor());
        per.setMaeClaseArrestoId(obj.getMaeClaseArrestoId());
        per.setMaeClaseArrestoCodigo(obj.getMaeClaseArrestoCodigo());
        per.setMaeClaseArrestoValor(obj.getMaeClaseArrestoValor());
//        per.setCuantia(obj.getCuantia());
        per.setDiasArresto(obj.getDiasArresto());
        per.setUvt(obj.getUvt());
        per.setEmailGestorRemitente(obj.getEmailGestorRemitente());
        per.setEntregaSucesiva(obj.getEntregaSucesiva());
        per.setExoneracion(obj.getExoneracion());
        per.setImpugnacion(obj.getImpugnacion());
        per.setIntegralidad(obj.getIntegralidad());
        per.setMedidaProvisional(obj.getMedidadProvisional());
        per.setMaeTipoFalloId(obj.getMaeTipoFalloId());
        per.setMaeTipoFalloCodigo(obj.getMaeTipoFalloCodigo());
        per.setMaeTipoFalloValor(obj.getMaeTipoFalloValor());
        per.setFechaNotificacion(obj.getFechaNotificacion());
        per.setFechaVencimiento(obj.getFechaVencimiento());
        per.setObservacion(obj.getObservacion());
        per.setCantidadServicio(obj.getCantidadServicio());
        per.setCantidadServicioCumplido(obj.getCantidadServicioCumplido());
        per.setDiasCambioEstado(obj.getDiasCambioEstado());
        per.setMaeSmlvId(obj.getMaeSmlvId());
        per.setMaeSmlvCodigo(obj.getMaeSmlvCodigo());
        per.setMaeSmlvValor(obj.getMaeSmlvValor());
        //2025-07-17 jyperez nuevos campos
        obj.setCantidadItems(per.getCantidadItems());
        obj.setCantidadItemsCerrados(per.getCantidadItemsCerrados());
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
    
    @Override
    public int contarItemsTutela(int idTutela) throws Exception {
       int cantidad = 0;
        //List<TuTutelaEstado> listResult = new ArrayList();
        String strQuery = "SELECT COUNT(item.id) FROM TuTutelaEstados te "
                      + " JOIN te.tuTutelaItemsList item "
                      + "WHERE te.tuTutelasId.id = :tutelaId ";
        
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("tutelaId", idTutela);
            cantidad = (int) (long) query.getSingleResult();
            
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }
    
    @Override
    public int contarItemsCerradosTutela(int idTutela) throws Exception {
       int cantidad = 0;
        //List<TuTutelaEstado> listResult = new ArrayList();
        String strQuery = "SELECT COUNT(item.id) FROM TuTutelaEstados te "
                      + " JOIN te.tuTutelaItemsList item "
                      + "WHERE te.tuTutelasId.id = :tutelaId AND item.maeEstadoItemCodigo = '" + TuTutelaItem.ESTADO_ITEM_CERRADO +"' ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("tutelaId", idTutela);
            cantidad = (int) (long) query.getSingleResult();
            
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }
    
    @Override
    public int contarItemsEstado(int idEstadoTutela) throws Exception {
       int cantidad = 0;
        //List<TuTutelaEstado> listResult = new ArrayList();
        String strQuery = "SELECT COUNT(item.id) FROM TuTutelaEstados te "
                      + " JOIN te.tuTutelaItemsList item "
                      + "WHERE te.id = :estadoId ";
        
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("estadoId", idEstadoTutela);
            cantidad = (int) (long) query.getSingleResult();
            
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }
    
    @Override
    public int contarItemsCerradosEstado(int idEstadoTutela) throws Exception {
       int cantidad = 0;
        //List<TuTutelaEstado> listResult = new ArrayList();
        String strQuery = "SELECT COUNT(item.id) FROM TuTutelaEstados te "
                      + " JOIN te.tuTutelaItemsList item "
                      + "WHERE te.id = :estadoId AND item.maeEstadoItemCodigo = '" + TuTutelaItem.ESTADO_ITEM_CERRADO +"' ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("estadoId", idEstadoTutela);
            cantidad = (int) (long) query.getSingleResult();
            
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }
}
