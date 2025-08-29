/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuTutela;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstado;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItem;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.entidades.TuPersonas;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaEstados;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaItems;
import com.saviasaludeps.savia.ejb.entidades.TuTutelas;
import com.saviasaludeps.savia.ejb.servicios.administracion.UsuarioServicio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuItemRemoto;
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
 * @author pavacca
 */
@Stateless
@Remote(TuItemRemoto.class)
@Local(TuTutelaItemLocal.class)
public class TuTutelaItemServicio extends GenericoServicio implements TuTutelaItemLocal, TuItemRemoto{
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t) FROM TuTutelaItems t "
                    + "WHERE t.id > 0 ";
            //configuramos los parámetros de Empresa y de los estados a validar
            if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND t.maeEstadoItemCodigo IN ('" + paramConsulta.getParametroConsulta1() +"' , '" + paramConsulta.getParametroConsulta2() + "') ";
            }
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND t.gnUsuarioAsignadoId.gnEmpresasId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            //2025-07-23 jyperez validamos si existe parámetro para evitar consultar Todos los usuarios
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND t.gnUsuarioAsignadoId.id = " + (int) paramConsulta.getParametroConsulta3() + " ";
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tuTutelasId.id":
                            strQuery += "AND t.tuTutelasId.id = " + (String) e.getValue() + " ";
                            break;
                        case "tuPersonasId.maeTipoDocumentoId":
                            strQuery += "AND t.tuPersonasId.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "tuPersonasId.numeroDocumento":
                            strQuery += "AND t.tuPersonasId.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "tuPersonasId.nombres":
                            strQuery += "AND t.tuPersonasId.nombres LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tuPersonasId.apellidos":
                            strQuery += "AND t.tuPersonasId.apellidos LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeEstadoItemId":
                            strQuery += "AND t.maeEstadoItemId = " + (String) e.getValue() + " ";
                            break;
                        case "maeTipoPrestacionId":
                            strQuery += "AND t.maeTipoPrestacionId = " + (String) e.getValue() + " ";
                            break;
                        case "maeCausaTutelaId":
                            strQuery += "AND t.maeCausaTutelaId = " + (String) e.getValue() + " ";
                            break;
                        case "maePresentacionId":
                            strQuery += "AND t.maePresentacionId = " + (String) e.getValue() + " ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND t.maTecnologiaValor  LIKE '%" + (String) e.getValue() + "%' ";
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
    public List<TuTutelaItem> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<TuTutelaItem> listResult = new ArrayList();
        try {
            String strQuery = "FROM TuTutelaItems t "
                    + "WHERE t.id > 0 ";
                        //configuramos los parámetros de Empresa y de los estados a validar
            if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND t.maeEstadoItemCodigo IN ('" + paramConsulta.getParametroConsulta1() +"' , '" + paramConsulta.getParametroConsulta2() + "') ";
            }
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND t.gnUsuarioAsignadoId.gnEmpresasId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            //2025-07-23 jyperez validamos si existe parámetro para evitar consultar Todos los usuarios
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND t.gnUsuarioAsignadoId.id = " + (int) paramConsulta.getParametroConsulta3() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tuTutelasId.id":
                            strQuery += "AND t.tuTutelasId.id = " + (String) e.getValue() + " ";
                            break;
                        case "tuPersonasId.maeTipoDocumentoId":
                            strQuery += "AND t.tuPersonasId.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "tuPersonasId.numeroDocumento":
                            strQuery += "AND t.tuPersonasId.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "tuPersonasId.nombres":
                            strQuery += "AND t.tuPersonasId.nombres LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tuPersonasId.apellidos":
                            strQuery += "AND t.tuPersonasId.apellidos LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeEstadoItemId":
                            strQuery += "AND t.maeEstadoItemId = " + (String) e.getValue() + " ";
                            break;
                        case "maeTipoPrestacionId":
                            strQuery += "AND t.maeTipoPrestacionId = " + (String) e.getValue() + " ";
                            break;
                        case "maeCausaTutelaId":
                            strQuery += "AND t.maeCausaTutelaId = " + (String) e.getValue() + " ";
                            break;
                        case "maePresentacionId":
                            strQuery += "AND t.maePresentacionId = " + (String) e.getValue() + " ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND t.maTecnologiaValor  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "t." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id DESC";
            }
            List<TuTutelaItems> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (TuTutelaItems per : list) {
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
    public TuTutelaItem consultar(int id) throws Exception {
        TuTutelaItem objRes = null;
        try {
            objRes = castEntidadNegocio((TuTutelaItems) getEntityManager().find(TuTutelaItems.class, id));
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
    public int insertar(TuTutelaItem tuTutelaItem) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(tuTutelaItem)).getId();
            tuTutelaItem.setId(id);
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
    public void actualizar(TuTutelaItem obj) throws Exception {
        try {
            String hql = "UPDATE TuTutelaItems SET"
                + ((obj.getDestinoCntPrestadorSedeNombre()!= null) ? " destinoCntPrestadorSedeNombre = :destinoCntPrestadorSedeNombre," : "")
                + ((obj.getPrescripcionCntPrestadorSedeNombre()!= null) ? " prescripcionCntPrestadorSedeNombre = :prescripcionCntPrestadorSedeNombre," : "") 
                + " cantidad = :cantidad,"
                + " maeCausaTutelaId = :maeCausaTutelaId,"
                + " maeCausaTutelaCodigo = :maeCausaTutelaCodigo,"
                + " maeCausaTutelaValor = :maeCausaTutelaValor,"
                + " fechaEnvio = :fechaEnvio,"
                + " fechaCita = :fechaCita,"
                + " fechaRespuesta = :fechaRespuesta,"
                + " fechaCumplimiento = :fechaCumplimiento,"
                + " correoDestinatario = :correoDestinatario,"
                + " tipoServicioId = :tipoServicioId,"
                + ((obj.getNotificadaAIps() != null) ? " notificadaAIps = :notificadaAIps," : "")
                + ((obj.getRespuestaIps() != null) ? " respuestaIps = :respuestaIps," : "")
                + ((obj.getParametroIpsId() != null) ? " parametroIpsId = :parametroIpsId," : "")
                + " tipoServicio = :tipoServicio,"
                + " maTecnologiaId = :maTecnologiaId,"
                + " maTecnologiaCodigo = :maTecnologiaCodigo,"
                + " maTecnologiaValor = :maTecnologiaValor,"
                + " maePresentacionId = :maePresentacionId,"
                + " maePresentacionCodigo = :maePresentacionCodigo,"
                + " maePresentacionValor = :maePresentacionValor,"
                + " maeTipoPrestacionId = :maeTipoPrestacionId,"
                + " maeTipoPrestacionCodigo = :maeTipoPrestacionCodigo,"
                + " maeTipoPrestacionValor = :maeTipoPrestacionValor," 
                + " observacion = :observacion,"
                + ((obj.getObservacionIps() != null) ? " observacionIps = :observacionIps," : "")
                + ((obj.getSolicitarFechaCita() != null) ? " solicitarFechaCita = :solicitarFechaCita," : "")
                + ((obj.getDestinoCntPrestadorSedeId() != null) ? " destinoCntPrestadorSedeId.id = :destinoCntPrestadorSedeId," : "")
                + ((obj.getPrescripcionCntPrestadorSedeId() != null) ? " prescripcionCntPrestadorSedeId.id = :prescripcionCntPrestadorSedeId," : "")
                + ((obj.getRegistrosAuditoriaAutorizacion() != null && !obj.getRegistrosAuditoriaAutorizacion().isEmpty()) ? " auAutorizacionesId = :auAutorizacionesId," : "")
                //2024-06-04 jyperez nuevos campos
                + " maeTipoServicioId = :maeTipoServicioId,"
                + " maeTipoServicioCodigo = :maeTipoServicioCodigo,"
                + " maeTipoServicioValor = :maeTipoServicioValor,"
                //2024-09-23 jyperez nuevos campos
                + " aplicaDestino = :aplicaDestino,"
                //2025-06-27 jyperez nuevos campos
                + " maeTipoAsignacionId = :maeTipoAsignacionId,"
                + " maeTipoAsignacionCodigo = :maeTipoAsignacionCodigo,"
                + " maeTipoAsignacionValor = :maeTipoAsignacionValor,"
                + " maeEstadoItemId = :maeEstadoItemId,"
                + " maeEstadoItemCodigo = :maeEstadoItemCodigo,"
                + " maeEstadoItemValor = :maeEstadoItemValor,"
                + " aplicaAsignacion = :aplicaAsignacion,"
                + ((obj.getGnUsuarioAsignadoId() != null) ? " gnUsuarioAsignadoId.id = " + obj.getGnUsuarioAsignadoId().getId() + " ," : "")
                + " usuarioModifica = :usuarioModifica,"
                + " terminalModifica = :terminalModifica,"
                + " fechaHoraModifica = :fechaHoraModifica"
                + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            if(obj.getDestinoCntPrestadorSedeNombre() != null){
                query.setParameter("destinoCntPrestadorSedeNombre", obj.getDestinoCntPrestadorSedeNombre());
            }
            if(obj.getPrescripcionCntPrestadorSedeNombre() != null){
                query.setParameter("prescripcionCntPrestadorSedeNombre", obj.getPrescripcionCntPrestadorSedeNombre());
            }
            query.setParameter("cantidad", obj.getCantidad());
            query.setParameter("maeCausaTutelaId", obj.getMaeCausaTutelaId());
            query.setParameter("maeCausaTutelaCodigo", obj.getMaeCausaTutelaCodigo());
            query.setParameter("maeCausaTutelaValor", obj.getMaeCausaTutelaValor());
            query.setParameter("fechaEnvio", obj.getFechaEnvio());
            query.setParameter("fechaCita", obj.getFechaCita());
            query.setParameter("fechaRespuesta", obj.getFechaRespuesta());
            query.setParameter("fechaCumplimiento", obj.getFechaCumplimiento());
            query.setParameter("correoDestinatario", obj.getCorreoDestinatario());
            query.setParameter("tipoServicioId", obj.getTipoServicioId());
            if(obj.getNotificadaAIps() != null){
                 query.setParameter("notificadaAIps", obj.getNotificadaAIps());
            }
            if(obj.getRespuestaIps() != null){
                 query.setParameter("respuestaIps", obj.getRespuestaIps());
            }
            if(obj.getParametroIpsId() != null){
                 query.setParameter("parametroIpsId", obj.getParametroIpsId());
            }
            query.setParameter("tipoServicio", obj.getTipoServicio());
            query.setParameter("maTecnologiaId", obj.getMaTecnologiaId());
            query.setParameter("maTecnologiaCodigo", obj.getMaTecnologiaCodigo());
            query.setParameter("maTecnologiaValor", obj.getMaTecnologiaValor());
            
            query.setParameter("maePresentacionId", obj.getMaePresentacionId());
            query.setParameter("maePresentacionCodigo", obj.getMaePresentacionCodigo());
            query.setParameter("maePresentacionValor", obj.getMaePresentacionValor());
            query.setParameter("maeTipoPrestacionId", obj.getMaeTipoPrestacionId());
            query.setParameter("maeTipoPrestacionCodigo", obj.getMaeTipoPrestacionCodigo());
            query.setParameter("maeTipoPrestacionValor", obj.getMaeTipoPrestacionValor());
            query.setParameter("observacion", obj.getObservacion());
            if(obj.getObservacionIps() != null){
                query.setParameter("observacionIps", obj.getObservacionIps());
            }
            if(obj.getSolicitarFechaCita() != null){
                query.setParameter("solicitarFechaCita", obj.getSolicitarFechaCita());
            } 
            if(obj.getDestinoCntPrestadorSedeId() != null){
                query.setParameter("destinoCntPrestadorSedeId", obj.getDestinoCntPrestadorSedeId().getId());
            }
            if(obj.getPrescripcionCntPrestadorSedeId() != null){
                query.setParameter("prescripcionCntPrestadorSedeId", obj.getPrescripcionCntPrestadorSedeId().getId());
            }
            
            if(obj.getRegistrosAuditoriaAutorizacion() != null && !obj.getRegistrosAuditoriaAutorizacion().isEmpty()){
                query.setParameter("auAutorizacionesId", obj.getRegistrosAuditoriaAutorizacion().get(0).getId());
            }
            //2024-07-12 jyperez corrección error parámetros no definidos

            query.setParameter("maeTipoServicioId", obj.getMaeTipoServicioId());
            query.setParameter("maeTipoServicioCodigo", obj.getMaeTipoServicioCodigo());
            query.setParameter("maeTipoServicioValor", obj.getMaeTipoServicioValor());
            //2024-09-23 jyperez nuevos campos
            query.setParameter("aplicaDestino", obj.isAplicaDestino());
            //2025-06-27 jyperez nuevos campos
            query.setParameter("maeTipoAsignacionId", obj.getMaeTipoAsignacionId());
            query.setParameter("maeTipoAsignacionCodigo", obj.getMaeTipoAsignacionCodigo());
            query.setParameter("maeTipoAsignacionValor", obj.getMaeTipoAsignacionValor());
            query.setParameter("maeEstadoItemId", obj.getMaeEstadoItemId());
            query.setParameter("maeEstadoItemCodigo", obj.getMaeEstadoItemCodigo());
            query.setParameter("maeEstadoItemValor", obj.getMaeEstadoItemValor());
            query.setParameter("aplicaAsignacion", obj.isAplicaAsignacion());
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
    public void actualizarGestionItem(TuTutelaItem obj) throws Exception {
        try {
            String hql = "UPDATE TuTutelaItems SET"
                + " fechaCumplimiento = :fechaCumplimiento,"
                + " fechaRespuesta = :fechaRespuesta,"
                + " observacion = :observacion,"
                + " maeEstadoItemId = :maeEstadoItemId,"
                + " maeEstadoItemCodigo = :maeEstadoItemCodigo,"
                + " maeEstadoItemValor = :maeEstadoItemValor,"
                + " usuarioModifica = :usuarioModifica,"
                + " terminalModifica = :terminalModifica,"
                + " fechaHoraModifica = :fechaHoraModifica"
                + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);

            query.setParameter("fechaCumplimiento", obj.getFechaCumplimiento());
            query.setParameter("fechaRespuesta", obj.getFechaRespuesta());
            query.setParameter("observacion", obj.getObservacion());
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
    public TuTutelaItem eliminar(int id) throws Exception {
        TuTutelaItem obj = null;
        try {
            TuTutelaItems ent = getEntityManager().find(TuTutelaItems.class, id);
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
    public void eliminarAutorizacion(TuTutelaItem obj) throws Exception {
        try {
            String hql = "UPDATE TuTutelaItems SET" 
                + " auAutorizacionesId = :auAutorizacionesId,"
                + " usuarioModifica = :usuarioModifica,"
                + " terminalModifica = :terminalModifica,"
                + " fechaHoraModifica = :fechaHoraModifica"
                + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("auAutorizacionesId", null);
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
    
    @SuppressWarnings("null")
    public static TuTutelaItems castNegocioEntidad(TuTutelaItem obj) {
        TuTutelaItems per = new TuTutelaItems();
        per.setId(obj.getId());
        if(obj.getTuTutelasId() != null){
            per.setTuTutelasId(new TuTutelas(obj.getTuTutelasId().getId()));
        }
        if(obj.getTuPersonasId() != null){
            per.setTuPersonasId(new TuPersonas(obj.getTuPersonasId().getId()));
        }
        if(obj.getTuTutelaEstadosId() != null){
            per.setTuTutelaEstadosId(new TuTutelaEstados(obj.getTuTutelaEstadosId().getId()));
        }
       
        if(obj.getDestinoCntPrestadorSedeId() != null){
            per.setDestinoCntPrestadorSedeId(new CntPrestadorSedes(obj.getDestinoCntPrestadorSedeId().getId()));
        }
        per.setDestinoCntPrestadorSedeNombre(obj.getDestinoCntPrestadorSedeNombre());
        if(obj.getPrescripcionCntPrestadorSedeId() != null){
            per.setPrescripcionCntPrestadorSedeId(new CntPrestadorSedes(obj.getPrescripcionCntPrestadorSedeId().getId()));
        }
        
        if(obj.getRegistrosAuditoriaAutorizacion() != null && !obj.getRegistrosAuditoriaAutorizacion().isEmpty()){
            per.setAuAutorizacionesId(obj.getRegistrosAuditoriaAutorizacion().get(0).getId());
        }
        per.setPrescripcionCntPrestadorSedeNombre(obj.getPrescripcionCntPrestadorSedeNombre());
        per.setCantidad(obj.getCantidad());
        per.setMaeCausaTutelaId(obj.getMaeCausaTutelaId());
        per.setMaeCausaTutelaCodigo(obj.getMaeCausaTutelaCodigo());
        per.setMaeCausaTutelaValor(obj.getMaeCausaTutelaValor());
        per.setFechaEnvio(obj.getFechaEnvio());
        per.setFechaCita(obj.getFechaCita());
        per.setFechaRespuesta(obj.getFechaRespuesta());
        per.setFechaCumplimiento(obj.getFechaCumplimiento());
        per.setCorreoDestinatario(obj.getCorreoDestinatario());
        per.setTipoServicioId(obj.getTipoServicioId());
        per.setNotificadaAIps(obj.getNotificadaAIps());
        per.setRespuestaIps(obj.getRespuestaIps());
        per.setParametroIpsId(obj.getParametroIpsId());
        per.setTipoServicio((obj.getTipoServicio() != null)?  obj.getTipoServicio() : 0);
        per.setMaTecnologiaId(obj.getMaTecnologiaId());
        per.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        per.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        per.setMaePresentacionId(obj.getMaePresentacionId());
        per.setMaePresentacionCodigo(obj.getMaePresentacionCodigo());
        per.setMaePresentacionValor(obj.getMaePresentacionValor());
        per.setMaeTipoPrestacionId(obj.getMaeTipoPrestacionId());
        per.setMaeTipoPrestacionCodigo(obj.getMaeTipoPrestacionCodigo());
        per.setMaeTipoPrestacionValor(obj.getMaeTipoPrestacionValor());
        per.setObservacion(obj.getObservacion());
        per.setObservacionIps(obj.getObservacionIps());
        per.setSolicitarFechaCita(obj.getSolicitarFechaCita());
        //2024-06-04 jyperez nuevos campos
        per.setMaeTipoServicioId(obj.getMaeTipoServicioId());
        per.setMaeTipoServicioCodigo(obj.getMaeTipoServicioCodigo());
        per.setMaeTipoServicioValor(obj.getMaeTipoServicioValor());
        //2024-09-23 jyperez nuevos campos
        per.setAplicaDestino(obj.isAplicaDestino());
        //2025-06-27 jyperez nuevos campos
        per.setMaeTipoAsignacionId(obj.getMaeTipoAsignacionId());
        per.setMaeTipoAsignacionCodigo(obj.getMaeTipoAsignacionCodigo());
        per.setMaeTipoAsignacionValor(obj.getMaeTipoAsignacionValor());
        per.setMaeEstadoItemId(obj.getMaeEstadoItemId());
        per.setMaeEstadoItemCodigo(obj.getMaeEstadoItemCodigo());
        per.setMaeEstadoItemValor(obj.getMaeEstadoItemValor());
        per.setAplicaAsignacion(obj.isAplicaAsignacion());
        if(obj.getGnUsuarioAsignadoId() != null && obj.getGnUsuarioAsignadoId().getId() != null) {
            per.setGnUsuarioAsignadoId(new GnUsuarios(obj.getGnUsuarioAsignadoId().getId()));
        }
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
    
    public static List<TuTutelaItem> castEntidadNegocio(List<TuTutelaItems> serviciosNegocio) {
        List<TuTutelaItem> listaServicios = new ArrayList<>();
        for(TuTutelaItems servicioNegocio:serviciosNegocio){
            listaServicios.add(castEntidadNegocio(servicioNegocio));
        }
        /*serviciosNegocio.forEach(servicioNegocio -> {
            listaServicios.add(castEntidadNegocio(servicioNegocio));
        });*/
        return listaServicios;
    }
    
    public static TuTutelaItem castEntidadNegocio(TuTutelaItems servicioNegocio) {
        TuTutelaItem servicioEntidad = new TuTutelaItem();
        servicioEntidad.setId(servicioNegocio.getId());
        servicioEntidad.setCantidad(servicioNegocio.getCantidad());
        servicioEntidad.setMaeCausaTutelaId(servicioNegocio.getMaeCausaTutelaId());
        servicioEntidad.setMaeCausaTutelaCodigo(servicioNegocio.getMaeCausaTutelaCodigo());
        servicioEntidad.setMaeCausaTutelaValor(servicioNegocio.getMaeCausaTutelaValor());
        servicioEntidad.setFechaEnvio(servicioNegocio.getFechaEnvio());
        servicioEntidad.setFechaCita(servicioNegocio.getFechaCita());
        servicioEntidad.setFechaRespuesta(servicioNegocio.getFechaRespuesta());
        servicioEntidad.setFechaCumplimiento(servicioNegocio.getFechaCumplimiento());
        servicioEntidad.setCorreoDestinatario(servicioNegocio.getCorreoDestinatario());
        servicioEntidad.setTipoServicioId(servicioNegocio.getTipoServicioId());
        if(servicioNegocio.getDestinoCntPrestadorSedeId() != null){
            CntPrestadorSede destinoPrestador = new CntPrestadorSede();
            destinoPrestador.setId(servicioNegocio.getDestinoCntPrestadorSedeId().getId());
            destinoPrestador.setCodigoSede(servicioNegocio.getDestinoCntPrestadorSedeId().getCodigo());
            destinoPrestador.setNombreSede(servicioNegocio.getDestinoCntPrestadorSedeId().getNombre());
            servicioEntidad.setDestinoCntPrestadorSedeId(destinoPrestador);
        }
        servicioEntidad.setDestinoCntPrestadorSedeNombre(servicioNegocio.getDestinoCntPrestadorSedeNombre());
        servicioEntidad.setNotificadaAIps(servicioNegocio.getNotificadaAIps());
        servicioEntidad.setRespuestaIps(servicioNegocio.getRespuestaIps());
        servicioEntidad.setParametroIpsId(servicioNegocio.getParametroIpsId());
        if(servicioNegocio.getPrescripcionCntPrestadorSedeId() != null){
            CntPrestadorSede PrescionPrestador = new CntPrestadorSede();
            PrescionPrestador.setId(servicioNegocio.getPrescripcionCntPrestadorSedeId().getId());
            PrescionPrestador.setCodigoSede(servicioNegocio.getPrescripcionCntPrestadorSedeId().getCodigo());
            PrescionPrestador.setNombreSede(servicioNegocio.getPrescripcionCntPrestadorSedeId().getNombre());
            servicioEntidad.setPrescripcionCntPrestadorSedeId(PrescionPrestador);
        }
        servicioEntidad.setPrescripcionCntPrestadorSedeNombre(servicioNegocio.getPrescripcionCntPrestadorSedeNombre());
        servicioEntidad.setTipoServicio(servicioNegocio.getTipoServicio());
        servicioEntidad.setMaTecnologiaId(servicioNegocio.getMaTecnologiaId());
        servicioEntidad.setMaTecnologiaCodigo(servicioNegocio.getMaTecnologiaCodigo());
        servicioEntidad.setMaTecnologiaValor(servicioNegocio.getMaTecnologiaValor());
        servicioEntidad.setMaePresentacionId(servicioNegocio.getMaePresentacionId());
        servicioEntidad.setMaePresentacionCodigo(servicioNegocio.getMaePresentacionCodigo());
        servicioEntidad.setMaePresentacionValor(servicioNegocio.getMaePresentacionValor());
        servicioEntidad.setMaeTipoPrestacionId(servicioNegocio.getMaeTipoPrestacionId());
        servicioEntidad.setMaeTipoPrestacionCodigo(servicioNegocio.getMaeTipoPrestacionCodigo());
        servicioEntidad.setMaeTipoPrestacionValor(servicioNegocio.getMaeTipoPrestacionValor());
        servicioEntidad.setObservacion(servicioNegocio.getObservacion());
        servicioEntidad.setObservacionIps(servicioNegocio.getObservacionIps());
        servicioEntidad.setSolicitarFechaCita(servicioNegocio.getSolicitarFechaCita());
        //2024-06-04 jyperez nuevos campos
        servicioEntidad.setMaeTipoServicioId(servicioNegocio.getMaeTipoServicioId());
        servicioEntidad.setMaeTipoServicioCodigo(servicioNegocio.getMaeTipoServicioCodigo());
        servicioEntidad.setMaeTipoServicioValor(servicioNegocio.getMaeTipoServicioValor());
        //2025-06-27 jyperez nuevos campos
        servicioEntidad.setMaeTipoAsignacionId(servicioNegocio.getMaeTipoAsignacionId());
        servicioEntidad.setMaeTipoAsignacionCodigo(servicioNegocio.getMaeTipoAsignacionCodigo());
        servicioEntidad.setMaeTipoAsignacionValor(servicioNegocio.getMaeTipoAsignacionValor());
        servicioEntidad.setMaeEstadoItemId(servicioNegocio.getMaeEstadoItemId());
        servicioEntidad.setMaeEstadoItemCodigo(servicioNegocio.getMaeEstadoItemCodigo());
        servicioEntidad.setMaeEstadoItemValor(servicioNegocio.getMaeEstadoItemValor());
        servicioEntidad.setAplicaAsignacion(servicioNegocio.getAplicaAsignacion());
        if(servicioNegocio.getGnUsuarioAsignadoId() != null) {
            servicioEntidad.setGnUsuarioAsignadoId(UsuarioServicio.castEntidadNegocio(servicioNegocio.getGnUsuarioAsignadoId()));
        } else {
            //2025-07-01 jyperez adicionamos el objeto vacio
            servicioEntidad.setGnUsuarioAsignadoId(new Usuario());
        }
        if(servicioNegocio.getTuPersonasId() != null){
            servicioEntidad.setTuPersonasId(TuPersonaServicio.castEntidadNegocio(servicioNegocio.getTuPersonasId()));
        }
        if(servicioNegocio.getTuTutelaDiagnosticosList() != null){
            servicioEntidad.setTuTutelaDiagnosticosId(TuDiagnosticoServicio.castEntidadNegocio(servicioNegocio.getTuTutelaDiagnosticosList()));    
        }
        
        if(servicioNegocio.getTuTutelaEstadosId() != null){
            servicioEntidad.setTuTutelaEstadosId(new TuTutelaEstado(servicioNegocio.getTuTutelaEstadosId().getId()));
        }
        
        servicioEntidad.setConsultarAutorizacion(servicioNegocio.getAuAutorizacionesId());
        //servicioEntidad.setTuTutelasId();
        if(servicioNegocio.getTuAdjuntosList() != null && !servicioNegocio.getTuAdjuntosList().isEmpty()){
             servicioEntidad.setTuAdjuntosList(TuAdjuntoServicio.castEntidadNegocio(servicioNegocio.getTuAdjuntosList()));
        }
        //2025-07-03 jyperez adicionamos la tutela
        if (servicioNegocio.getTuTutelasId() != null) {
            servicioEntidad.setTuTutelasId( new TuTutela(servicioNegocio.getTuTutelasId().getId()));
            servicioEntidad.setTuTutelasId(TuTutelaServicio.castEntidadNegocio(servicioNegocio.getTuTutelasId()));
        }
        //2024-09-23 jyperez nuevos campos
        servicioEntidad.setAplicaDestino(servicioNegocio.getAplicaDestino());
        //Auditoria
        servicioEntidad.setUsuarioCrea(servicioNegocio.getUsuarioCrea());
        servicioEntidad.setTerminalCrea(servicioNegocio.getTerminalCrea());
        servicioEntidad.setFechaHoraCrea(servicioNegocio.getFechaHoraCrea());
        //servicioEntidad.setUsuarioModifica(servicioNegocio.getUsuarioModifica());
        //servicioEntidad.setTerminalModifica(servicioNegocio.getTerminalModifica());
        //servicioEntidad.setFechaHoraModifica(servicioNegocio.getFechaHoraModifica());
        return servicioEntidad;
    }
}
