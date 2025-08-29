/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.mipres.MpMedicamentoRemoto;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
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
@Remote(MpMedicamentoRemoto.class)
public class MpMedicamentoServicio extends GenericoServicio implements MpMedicamentoRemoto {

    @Override
    public MpPrescripcionMedicamento consultar(int id) throws java.lang.Exception {
        MpPrescripcionMedicamento objResult = new MpPrescripcionMedicamento();
        try {
            objResult = castEntidadNegocio((MpPrescripcionMedicamentos) getEntityManager().find(MpPrescripcionMedicamentos.class, id));
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
    public List<MpPrescripcionMedicamento> consultarPorMpPrescripcion(int mpPrescripcionId) throws Exception {
        List<MpPrescripcionMedicamento> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mm FROM MpPrescripcionMedicamentos mm WHERE mm.mpPrescripcionesId.id = :id");
            List<MpPrescripcionMedicamentos> lista = getEntityManager().createQuery(strQuery.toString()).setParameter("id", mpPrescripcionId).getResultList();
            lista.forEach(mpMedicamento -> listaResultado.add(castEntidadNegocio(mpMedicamento)));
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public void actualizar(MpPrescripcionMedicamento obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidad(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            System.out.println(e);
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarDireccionamiento(MpPrescripcionMedicamento obj) throws Exception {
        try {
            MpPrescripcionMedicamentos ent = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MpPrescripcionMedicamentos m SET ";
            strQuery += " m.fechaMaximaEntrega = :fechaMaximaEntrega, ";
            strQuery += " m.idDireccionamiento = :idDireccionamiento, ";
            strQuery += " m.idTransaccion = :idTransaccion, ";
            strQuery += " m.fechaDireccionamiento = :fechaDireccionamiento, ";
            strQuery += " m.estado = :estado, ";
            strQuery += " m.cantidadTotalEntrega = :cantidadTotalEntrega, ";
            strQuery += " m.cantidadTotalFormulada = :cantidadTotalFormulada, ";
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
    public void actualizarEstadoCantidad(MpPrescripcionMedicamento obj) throws Exception {
        try {
            MpPrescripcionMedicamentos ent = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MpPrescripcionMedicamentos m SET ";
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
    public void actualizarEstado(MpPrescripcionMedicamento obj) throws Exception {
        try {
            MpPrescripcionMedicamentos ent = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MpPrescripcionMedicamentos m SET ";
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

    public static MpPrescripcionMedicamento castEntidadNegocio(MpPrescripcionMedicamentos ent) {
        MpPrescripcionMedicamento obj = new MpPrescripcionMedicamento();

        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());
        obj.setCantidadTotalFormulada(ent.getCantidadTotalFormulada());
        obj.setCantidadTratamiento(ent.getCantidadTratamiento());
        obj.setCausaSolicitud1(ent.getCausaSolicitud1());
        obj.setCausaSolicitud2(ent.getCausaSolicitud2());
        obj.setCausaSolicitud3(ent.getCausaSolicitud3());
        obj.setCausaSolicitud4(ent.getCausaSolicitud4());
        obj.setCausaSolicitud5(ent.getCausaSolicitud5());
        obj.setCausaSolicitud6(ent.getCausaSolicitud6());
        obj.setCodigoFormulaFarmaceutica(ent.getCodigoFormulaFarmaceutica());
        obj.setCodigoFrecuenciaAdministracion(ent.getCodigoFrecuenciaAdministracion());
        obj.setCodigoViaAdministracion(ent.getCodigoViaAdministracion());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setDescripcionMedicamentoPrincipioActivo(ent.getDescripcionMedicamentoPrincipioActivo());
        obj.setDescripcionProductoNutricional(ent.getDescripcionProductoNutricional());
        obj.setDescripcionRazon31(ent.getDescripcionRazon31());
        obj.setDescripcionRazon32(ent.getDescripcionRazon32());
        obj.setDescripcionRazon41(ent.getDescripcionRazon41());
        obj.setDescripcionRazon42(ent.getDescripcionRazon42());
        obj.setDescripcionRazon43(ent.getDescripcionRazon43());
        obj.setDescripcionRazon44(ent.getDescripcionRazon44());
        obj.setDescripcionRazon5(ent.getDescripcionRazon5());
        obj.setDescripcionRazon51(ent.getDescripcionRazon51());
        obj.setDescripcionRazon52(ent.getDescripcionRazon52());
        obj.setDescripcionRazon53(ent.getDescripcionRazon53());
        obj.setDescripcionRazon54(ent.getDescripcionRazon54());
        obj.setDosis(ent.getDosis());
        obj.setDosisUnidadMedida(ent.getDosisUnidadMedida());
        obj.setDuracionTratamiento(ent.getDuracionTratamiento());
        obj.setEntregados(ent.getEntregados());
        obj.setEstado(ent.getEstado());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setFechaDireccionamiento(ent.getFechaDireccionamiento());
        obj.setId(ent.getId());
        obj.setIdDireccionamiento(ent.getIdDireccionamiento());
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setIndicacionRecibida(ent.getIndicacionRecibida());
        obj.setIndicacionesEspeciales(ent.getIndicacionesEspeciales());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setEsDiagnosticoCancer(ent.getEsDiagnosticoCancer());
        obj.setEsDiagnosticoDesnutricion(ent.getEsDiagnosticoDesnutricion());
        obj.setEsDiagnosticoEnfermedadRenal(ent.getEsDiagnosticoEnfermedadRenal());
        obj.setEsDiagnosticoVih(ent.getEsDiagnosticoVih());
        obj.setMedPbsUtilizado(ent.getMedPbsUtilizado());
        if (ent.getMpPrescripcionesId() != null) {
            obj.setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionesId().getId()));
        }
        obj.setNumeroFrecuenciaAdministracion(ent.getNumeroFrecuenciaAdministracion());
        obj.setPendientes(ent.getPendientes());
        obj.setRazonCausaSolicitud31(ent.getRazonCausaSolicitud31());
        obj.setRazonCausaSolicitud32(ent.getRazonCausaSolicitud32());
        obj.setRazonCausaSolicitud41(ent.getRazonCausaSolicitud41());
        obj.setRazonCausaSolicitud42(ent.getRazonCausaSolicitud42());
        obj.setRazonCausaSolicitud43(ent.getRazonCausaSolicitud43());
        obj.setRazonCausaSolicitud44(ent.getRazonCausaSolicitud44());
        obj.setRazonCausaSolicitud51(ent.getRazonCausaSolicitud51());
        obj.setRazonCausaSolicitud52(ent.getRazonCausaSolicitud52());
        obj.setRazonCausaSolicitud53(ent.getRazonCausaSolicitud53());
        obj.setRazonCausaSolicitud54(ent.getRazonCausaSolicitud54());
        obj.setTipoMedicamento(ent.getTipoMedicamento());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setTipoProductoNutricional(ent.getTipoProductoNutricional());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setUnidadFarmaceuticaCantidadTotal(ent.getUnidadFarmaceuticaCantidadTotal());
        obj.setFechaMaximaEntrega(ent.getFechaMaximaEntrega());
        
        obj.setMaeProductosNutricionalesCodigo(CONSULTAR_TODOS);
        if (ent.getMaeProductosNutricionalesCodigo()!= null) {
        obj.setMaeProductosNutricionalesCodigo(ent.getMaeProductosNutricionalesCodigo());
        obj.setMaeProductosNutricionalesValor(ent.getMaeProductosNutricionalesValor());
        }
        return obj;
    }

    public static MpPrescripcionMedicamentos castNegocioEntidad(MpPrescripcionMedicamento obj) {
        MpPrescripcionMedicamentos ent = new MpPrescripcionMedicamentos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCantidadTotalEntrega(obj.getCantidadTotalEntrega());
        ent.setCantidadTotalFormulada(obj.getCantidadTotalFormulada());
        ent.setCantidadTratamiento(obj.getCantidadTratamiento());
        ent.setCausaSolicitud1(obj.getCausaSolicitud1());
        ent.setCausaSolicitud2(obj.getCausaSolicitud2());
        ent.setCausaSolicitud3(obj.getCausaSolicitud3());
        ent.setCausaSolicitud4(obj.getCausaSolicitud4());
        ent.setCausaSolicitud5(obj.getCausaSolicitud5());
        ent.setCausaSolicitud6(obj.getCausaSolicitud6());
        ent.setCodigoFormulaFarmaceutica(obj.getCodigoFormulaFarmaceutica());
        ent.setCodigoFrecuenciaAdministracion(obj.getCodigoFrecuenciaAdministracion());       
        ent.setCodigoViaAdministracion(obj.getCodigoViaAdministracion()); 
        ent.setConsecutivoOrden(obj.getConsecutivoOrden());
        ent.setDescripcionMedicamentoPrincipioActivo(obj.getDescripcionMedicamentoPrincipioActivo());
        ent.setDescripcionProductoNutricional(obj.getDescripcionProductoNutricional());
        ent.setDescripcionRazon31(obj.getDescripcionRazon31());
        ent.setDescripcionRazon32(obj.getDescripcionRazon32());
        ent.setDescripcionRazon41(obj.getDescripcionRazon41());
        ent.setDescripcionRazon42(obj.getDescripcionRazon42());
        ent.setDescripcionRazon43(obj.getDescripcionRazon43());
        ent.setDescripcionRazon44(obj.getDescripcionRazon44());
        ent.setDescripcionRazon44(obj.getDescripcionRazon5());
        ent.setDescripcionRazon5(obj.getDescripcionRazon5());
        ent.setDescripcionRazon51(obj.getDescripcionRazon51());
        ent.setDescripcionRazon52(obj.getDescripcionRazon52());
        ent.setDescripcionRazon53(obj.getDescripcionRazon53());
        ent.setDescripcionRazon54(obj.getDescripcionRazon54());
        ent.setDosis(obj.getDosis());
        ent.setDosisUnidadMedida(obj.getDosisUnidadMedida());
        ent.setDuracionTratamiento(obj.getDuracionTratamiento());
        ent.setEntregados(obj.getEntregados());
        ent.setEstado(obj.getEstado());
        ent.setFechaMaximaEntrega(obj.getFechaMaximaEntrega());
        ent.setEstadoJuntaProfesionales(obj.getEstadoJuntaProfesionales());
        ent.setFechaDireccionamiento(obj.getFechaDireccionamiento());
        ent.setId(obj.getId());
        ent.setIdDireccionamiento(obj.getIdDireccionamiento());
        ent.setIdTransaccion(obj.getIdTransaccion());
        ent.setIndicacionRecibida(obj.getIndicacionRecibida());
        ent.setIndicacionesEspeciales(obj.getIndicacionesEspeciales());
        ent.setJustificacionNoPbs(obj.getJustificacionNoPbs());
        ent.setEsDiagnosticoCancer(obj.getEsDiagnosticoCancer());
        ent.setEsDiagnosticoDesnutricion(obj.getEsDiagnosticoDesnutricion());
        ent.setEsDiagnosticoEnfermedadRenal(obj.getEsDiagnosticoEnfermedadRenal());
        ent.setEsDiagnosticoVih(obj.getEsDiagnosticoVih());
        ent.setMedPbsUtilizado(obj.getMedPbsUtilizado());
        if (obj.getMpPrescripcion()!= null) {
            ent.setMpPrescripcionesId(new MpPrescripciones(obj.getMpPrescripcion().getId()));
        }
        ent.setNumeroFrecuenciaAdministracion(obj.getNumeroFrecuenciaAdministracion());
        ent.setPendientes(obj.getPendientes());
        ent.setRazonCausaSolicitud31(obj.getRazonCausaSolicitud31());
        ent.setRazonCausaSolicitud32(obj.getRazonCausaSolicitud32());
        ent.setRazonCausaSolicitud41(obj.getRazonCausaSolicitud41());
        ent.setRazonCausaSolicitud42(obj.getRazonCausaSolicitud42());
        ent.setRazonCausaSolicitud43(obj.getRazonCausaSolicitud43());
        ent.setRazonCausaSolicitud44(obj.getRazonCausaSolicitud44());
        ent.setRazonCausaSolicitud51(obj.getRazonCausaSolicitud51());
        ent.setRazonCausaSolicitud52(obj.getRazonCausaSolicitud52());
        ent.setRazonCausaSolicitud53(obj.getRazonCausaSolicitud53());
        ent.setRazonCausaSolicitud54(obj.getRazonCausaSolicitud54());
        ent.setTipoMedicamento(obj.getTipoMedicamento());
        ent.setTipoPrestacion(obj.getTipoPrestacion());
        ent.setTipoProductoNutricional(obj.getTipoProductoNutricional());
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setUnidadFarmaceuticaCantidadTotal(obj.getUnidadFarmaceuticaCantidadTotal());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        return ent;
    }

}
