/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import com.saviasaludeps.savia.negocio.mipres.MpTecnologiaRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Session;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(MpTecnologiaRemoto.class)
public class MpTecnologiaServicio extends GenericoServicio implements MpTecnologiaRemoto {

    @Override
    public MpPrescripcionTecnologia consultar(int id) throws java.lang.Exception {
        MpPrescripcionTecnologia objResult = new MpPrescripcionTecnologia();
        try {
            objResult = castEntidadNegocio((MpPrescripcionTecnologias) getEntityManager().find(MpPrescripcionTecnologias.class, id));
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
    public List<MpPrescripcionTecnologia> consultarPorMpPrescripcion(int mpPrescripcionId) throws Exception {
        List<MpPrescripcionTecnologia> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mt FROM MpPrescripcionTecnologias mt WHERE mt.mpPrescripcionId.id = :id");
            List<MpPrescripcionTecnologias> lista = getEntityManager().createQuery(strQuery.toString()).setParameter("id", mpPrescripcionId).getResultList();
            lista.forEach(mpTecnologia -> listaResultado.add(castEntidadNegocio(mpTecnologia)));
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public void actualizarDireccionamiento(MpPrescripcionTecnologia obj) throws Exception {
        try {
            MpPrescripcionTecnologias ent = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MpPrescripcionTecnologias m SET ";
            strQuery += " m.fechaMaximaEntrega = :fechaMaximaEntrega, ";
            strQuery += " m.idDireccionamiento = :idDireccionamiento, ";
            strQuery += " m.idTransaccion = :idTransaccion, ";
            strQuery += " m.fechaDireccionamiento = :fechaDireccionamiento, ";
            strQuery += " m.estado = :estado, ";
            strQuery += " m.cantidadTotal = :cantidadTotal, ";
            strQuery += " m.cantidadFormulada = :cantidadFormulada, ";
            strQuery += " m.entregados = :entregados, ";
            strQuery += " m.pendientes = :pendientes, ";
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
    public void actualizarEstadoCantidad(MpPrescripcionTecnologia obj) throws Exception {
        try {
            MpPrescripcionTecnologias ent = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MpPrescripcionTecnologias m SET ";
            strQuery += " m.estado = :estado, ";
            strQuery += " m.pendientes = :pendientes, ";
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
    public void actualizarEstado(MpPrescripcionTecnologia obj) throws Exception {
        try {
            MpPrescripcionTecnologias ent = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MpPrescripcionTecnologias m SET ";
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

    public static MpPrescripcionTecnologia castEntidadNegocio(MpPrescripcionTecnologias ent) {
        MpPrescripcionTecnologia obj = new MpPrescripcionTecnologia();
        obj.setCantidadDuracionTratamiento(ent.getCantidadDuracionTratamiento());
        obj.setCantidadFormulada(ent.getCantidadFormulada());
        obj.setCantidadTotal(ent.getCantidadTotal());
        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());
        obj.setCausaSolicitud11(ent.getCausaSolicitud11());
        obj.setCausaSolicitud12(ent.getCausaSolicitud12());
        obj.setCausaSolicitud2(ent.getCausaSolicitud12());
        obj.setCausaSolicitud3(ent.getCausaSolicitud3());
        obj.setCausaSolicitud4(ent.getCausaSolicitud4());
        obj.setCausaSolicitud5(ent.getCausaSolicitud5());
        obj.setCausaSolicitud6(ent.getCausaSolicitud6());
        obj.setCausaSolicitud7(ent.getCausaSolicitud7());
        obj.setCodigoPeriodoDuracionTratamiento(ent.getCodigoPeriodoDuracionTratamiento());
        obj.setCodigoRazonCausa52(ent.getCodigoRazonCausa52());
        obj.setCodigoUnidadTiempoFrecuenciaUso(ent.getCodigoUnidadTiempoFrecuenciaUso());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setDescripcionRazon51(ent.getDescripcionRazon51());
        obj.setDescripcionRazon52(ent.getDescripcionRazon52());
        obj.setEntregados(ent.getEntregados());
        obj.setEstado(ent.getEstado());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setFechaDireccionamiento(ent.getFechaDireccionamiento());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFrecuenciaDeUso(ent.getFrecuenciaDeUso());
        obj.setId(ent.getId());
        obj.setIdDireccionamiento(ent.getIdDireccionamiento());
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setIndicacionesPaciente(ent.getIndicacionesPaciente());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        if (ent.getMpPrescripcionId() != null) {
            obj.setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionId().getId()));
        }
        obj.setPendientes(ent.getPendientes());
        obj.setRazonCausaSolicitud51(ent.getRazonCausaSolicitud51());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setFechaMaximaEntrega(ent.getFechaMaximaEntrega());
        return obj;
    }

    public static MpPrescripcionTecnologias castNegocioEntidad(MpPrescripcionTecnologia obj) {
        MpPrescripcionTecnologias ent = new MpPrescripcionTecnologias();
        ent.setCantidadDuracionTratamiento(obj.getCantidadDuracionTratamiento());
        ent.setCantidadFormulada(obj.getCantidadFormulada());
        ent.setCantidadTotal(obj.getCantidadTotal());
        ent.setCantidadTotalEntrega(obj.getCantidadTotalEntrega());
        ent.setCausaSolicitud11(obj.getCausaSolicitud11());
        ent.setCausaSolicitud12(obj.getCausaSolicitud12());
        ent.setCausaSolicitud2(obj.getCausaSolicitud12());
        ent.setCausaSolicitud3(obj.getCausaSolicitud3());
        ent.setCausaSolicitud4(obj.getCausaSolicitud4());
        ent.setCausaSolicitud5(obj.getCausaSolicitud5());
        ent.setCausaSolicitud6(obj.getCausaSolicitud6());
        ent.setCausaSolicitud7(obj.getCausaSolicitud7());
        ent.setCodigoPeriodoDuracionTratamiento(obj.getCodigoPeriodoDuracionTratamiento());
        ent.setCodigoRazonCausa52(obj.getCodigoRazonCausa52());
        ent.setCodigoUnidadTiempoFrecuenciaUso(obj.getCodigoUnidadTiempoFrecuenciaUso());
        ent.setConsecutivoOrden(obj.getConsecutivoOrden());
        ent.setDescripcionRazon51(obj.getDescripcionRazon51());
        ent.setDescripcionRazon52(obj.getDescripcionRazon52());
        ent.setEntregados(obj.getEntregados());
        ent.setEstado(obj.getEstado());
        ent.setEstadoJuntaProfesionales(obj.getEstadoJuntaProfesionales());
        ent.setFechaDireccionamiento(obj.getFechaDireccionamiento());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setFrecuenciaDeUso(obj.getFrecuenciaDeUso());
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setIdDireccionamiento(obj.getIdDireccionamiento());
        ent.setIdTransaccion(obj.getIdTransaccion());
        ent.setIndicacionesPaciente(obj.getIndicacionesPaciente());
        ent.setJustificacionNoPbs(obj.getJustificacionNoPbs());
        ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        ent.setMaTecnologiaId(obj.getMaTecnologiaId());
        ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        if (ent.getMpPrescripcionId() != null) {
            ent.setMpPrescripcionId(new MpPrescripciones(obj.getMpPrescripcion().getId()));
        }
        ent.setPendientes(obj.getPendientes());
        ent.setRazonCausaSolicitud51(obj.getRazonCausaSolicitud51());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setTipoPrestacion(obj.getTipoPrestacion());
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setFechaMaximaEntrega(obj.getFechaMaximaEntrega());
        return ent;
    }

}
