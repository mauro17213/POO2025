/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionInsumos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.mipres.MpInsumoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Session;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(MpInsumoRemoto.class)
public class MpInsumoServicio extends GenericoServicio implements MpInsumoRemoto {

    @Override
    public MpPrescripcionInsumo consultar(int id) throws java.lang.Exception {
        MpPrescripcionInsumo objResult = new MpPrescripcionInsumo();
        try {
            objResult = castEntidadNegocio((MpPrescripcionInsumos) getEntityManager().find(MpPrescripcionInsumos.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }

    @Override
    public List<MpPrescripcionInsumo> consultarPorMpPrescripcion(int mpPrescripcionId) throws Exception {
        List<MpPrescripcionInsumo> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mi FROM MpPrescripcionInsumos mi WHERE mi.mpPrescripcionId.id = :id");
            List<MpPrescripcionInsumos> lista = getEntityManager().createQuery(strQuery.toString()).setParameter("id", mpPrescripcionId).getResultList();
            lista.forEach(mpInsumo -> listaResultado.add(castEntidadNegocio(mpInsumo)));
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public void actualizarDireccionamiento(MpPrescripcionInsumo obj) throws Exception {
        try {
            MpPrescripcionInsumos ent = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MpPrescripcionInsumos m SET ";
            strQuery += " m.fechaMaximaEntrega = :fechaMaximaEntrega, ";
            strQuery += " m.idDireccionamiento = :idDireccionamiento, ";
            strQuery += " m.idTransaccion = :idTransaccion, ";
            strQuery += " m.fechaDireccionamiento = :fechaDireccionamiento, ";
            strQuery += " m.estado = :estado, ";
            strQuery += " m.cantidadTotalEntrega = :cantidadTotalEntrega, ";
            strQuery += " m.cantidadFormulada = :cantidadFormulada, ";
            strQuery += " m.entregados = :entregados, ";
            strQuery += " m.pendiente = :pendiente, ";
            strQuery += " m.usuarioModifica = :usuarioModifica, ";
            strQuery += " m.terminalModifica = :terminalModifica, ";
            strQuery += " m.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE m.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(ent);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            System.out.println(e);
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarEstadoCantidad(MpPrescripcionInsumo obj) throws Exception {
        try {
            MpPrescripcionInsumos ent = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MpPrescripcionInsumos m SET ";
            strQuery += " m.estado = :estado, ";
            strQuery += " m.pendiente = :pendiente, ";
            strQuery += " m.entregados = :entregados, ";
            strQuery += " m.usuarioModifica = :usuarioModifica, ";
            strQuery += " m.terminalModifica = :terminalModifica, ";
            strQuery += " m.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE m.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(ent);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            System.out.println(e);
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarEstado(MpPrescripcionInsumo obj) throws Exception {
        try {
            MpPrescripcionInsumos ent = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MpPrescripcionInsumos m SET ";
            strQuery += " m.estado = :estado, ";
            strQuery += " m.usuarioModifica = :usuarioModifica, ";
            strQuery += " m.terminalModifica = :terminalModifica, ";
            strQuery += " m.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE m.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(ent);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            System.out.println(e);
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    public static MpPrescripcionInsumo castEntidadNegocio(MpPrescripcionInsumos ent) {
        MpPrescripcionInsumo obj = new MpPrescripcionInsumo();
        obj.setCantidad(ent.getCantidad());
        obj.setCantidadFormulada(ent.getCantidadFormulada());
        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());
        obj.setCausaSolicitud1(ent.getCausaSolicitud1());
        obj.setCausaSolicitud2(ent.getCausaSolicitud2());
        obj.setCausaSolicitud3(ent.getCausaSolicitud3());
        obj.setCausaSolicitud4(ent.getCausaSolicitud4());
        obj.setCausaSolicitud5(ent.getCausaSolicitud5());
        obj.setCodigoDispositivo(ent.getCodigoDispositivo());
        obj.setCodigoForma(ent.getCodigoForma());
        obj.setCodigoMunicipioDestinoAlb(ent.getCodigoMunicipioDestinoAlb());
        obj.setCodigoMunicipioOrigenAlb(ent.getCodigoMunicipioOrigenAlb());
        obj.setCodigoServicioComplementario(ent.getCodigoServicioComplementario());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setDescripcionCausa4(ent.getDescripcionCausa4());
        obj.setDescripcionCausaS4(ent.getDescripcionCausaS4());
        obj.setDuracionTratamiento(ent.getDuracionTratamiento());
        obj.setEntregados(ent.getEntregados());
        obj.setEstado(ent.getEstado());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setFechaDireccionamiento(ent.getFechaDireccionamiento());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFrecuenciaUso(ent.getFrecuenciaUso());
        obj.setId(ent.getId());
        obj.setIdDireccionamiento(ent.getIdDireccionamiento());
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setIndicacionesRecomendaciones(ent.getIndicacionesRecomendaciones());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        if (ent.getMpPrescripcionId() != null) {
            obj.setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionId().getId()));
        }
        obj.setNombreAlb(ent.getNombreAlb());
        obj.setNombreTecnologiaAvalada(ent.getNombreTecnologiaAvalada());
        obj.setNumeroDocumentoAcompanante(ent.getNumeroDocumentoAcompanante());
        obj.setParentezcoAcompanante(ent.getParentezcoAcompanante());
        obj.setPendiente(ent.getPendiente());
        obj.setReqAcom(ent.getReqAcom());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setTipoDocumentoAcompanante(ent.getTipoDocumentoAcompanante());
        obj.setTipoDocumentoAcompananteAlbergue(ent.getTipoDocumentoAcompananteAlbergue());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setTipoTransporte(ent.getTipoTransporte());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setFechaMaximaEntrega(ent.getFechaMaximaEntrega());
        if (ent.getMaeDispositivosCodigo() != null) {
            obj.setMaeDispositivosCodigo(ent.getMaeDispositivosCodigo());
            obj.setMaeDispositivosNombre(ent.getMaedispositivosValor());
        }
        if (ent.getMaeServiciosComplementariosCodigo() != null) {
            obj.setMaeServiciosComplementariosCodigo(ent.getMaeServiciosComplementariosCodigo());
            obj.setMaeServiciosComplementariosNombre(ent.getMaeServiciosComplementariosValor());
        }
        return obj;
    }

    public static MpPrescripcionInsumos castNegocioEntidad(MpPrescripcionInsumo obj) {
        MpPrescripcionInsumos ent = new MpPrescripcionInsumos();
        ent.setCantidad(obj.getCantidad());
        ent.setCantidadFormulada(obj.getCantidadFormulada());
        ent.setCantidadTotalEntrega(obj.getCantidadTotalEntrega());
        ent.setCausaSolicitud1(obj.getCausaSolicitud1());
        ent.setCausaSolicitud2(obj.getCausaSolicitud2());
        ent.setCausaSolicitud3(obj.getCausaSolicitud3());
        ent.setCausaSolicitud4(obj.getCausaSolicitud4());
        ent.setCausaSolicitud5(obj.getCausaSolicitud5());
        ent.setCodigoDispositivo(obj.getCodigoDispositivo());
        ent.setCodigoForma(obj.getCodigoForma());
        ent.setCodigoMunicipioDestinoAlb(obj.getCodigoMunicipioDestinoAlb());
        ent.setCodigoMunicipioOrigenAlb(obj.getCodigoMunicipioOrigenAlb());
        ent.setCodigoServicioComplementario(obj.getCodigoServicioComplementario());
        ent.setConsecutivoOrden(obj.getConsecutivoOrden());
        ent.setDescripcionCausa4(obj.getDescripcionCausa4());
        ent.setDescripcionCausaS4(obj.getDescripcionCausaS4());
        ent.setDuracionTratamiento(obj.getDuracionTratamiento());
        ent.setEntregados(obj.getEntregados());
        ent.setEstado(obj.getEstado());
        ent.setEstadoJuntaProfesionales(obj.getEstadoJuntaProfesionales());
        ent.setFechaDireccionamiento(obj.getFechaDireccionamiento());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setFrecuenciaUso(obj.getFrecuenciaUso());
        ent.setId(obj.getId());
        ent.setIdDireccionamiento(obj.getIdDireccionamiento());
        ent.setIdTransaccion(obj.getIdTransaccion());
        ent.setIndicacionesRecomendaciones(obj.getIndicacionesRecomendaciones());
        ent.setJustificacionNoPbs(obj.getJustificacionNoPbs());
        if (obj.getMpPrescripcion() != null) {
            ent.setMpPrescripcionId(new MpPrescripciones(obj.getMpPrescripcion().getId()));
        }
        ent.setNombreAlb(obj.getNombreAlb());
        ent.setNombreTecnologiaAvalada(obj.getNombreTecnologiaAvalada());
        ent.setNumeroDocumentoAcompanante(obj.getNumeroDocumentoAcompanante());
        ent.setParentezcoAcompanante(obj.getParentezcoAcompanante());
        ent.setPendiente(obj.getPendiente());
        ent.setReqAcom(obj.getReqAcom());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setTipoDocumentoAcompanante(obj.getTipoDocumentoAcompanante());
        ent.setTipoDocumentoAcompananteAlbergue(obj.getTipoDocumentoAcompananteAlbergue());
        ent.setTipoPrestacion(obj.getTipoPrestacion());
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setTipoTransporte(obj.getTipoTransporte());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setFechaMaximaEntrega(obj.getFechaMaximaEntrega());
        return ent;
    }

}
