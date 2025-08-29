/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9DatoClinico;
import com.saviasaludeps.savia.ejb.entidades.RefAnexo9DatosClinicos;
import com.saviasaludeps.savia.ejb.entidades.RefAnexos9;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9DatoClinicoRemoto;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(RefAnexo9DatoClinicoRemoto.class)
@Local(RefAnexo9DatoClinicoLocal.class)
public class RefAnexo9DatoClinicoServicio extends GenericoServicio implements RefAnexo9DatoClinicoRemoto, RefAnexo9DatoClinicoLocal {

    @Override
    public int insertar(RefAnexo9DatoClinico obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(RefAnexo9DatoClinico obj) throws Exception {

        try {
            String sql = "UPDATE RefAnexo9DatosClinicos SET fechaHoraDatos = :fechaHoraDatos, "
                    + "triage = :triage, "
                    + "antecedentes = :antecedentes, "
                    + "resumenClinico = :resumenClinico, "
                    + "temperatura = :temperatura, "
                    + "frecuenciaCardiaca = :frecuenciaCardiaca, "
                    + "tensionArtedialDiastole = :tensionArtedialDiastole, "
                    + "tensionArterialSistole = :tensionArterialSistole, "
                    + "frecuenciaRespiratoria = :frecuenciaRespiratoria, "
                    + "saturacionOxigeno = :saturacionOxigeno, "
                    + "peso = :peso, "
                    + "talla = :talla, "
                    + "perimetroAbdominal = :perimetroAbdominal, "
                    + "imc = :imc, "
                    + "hallazgosExamenFisico = :hallazgosExamenFisico, "
                    + "examenesApoyoDiagnostico = :examenesApoyoDiagnostico, "
                    + "tratamientoAplicado = :tratamientoAplicado, "
                    + "motivoRemision = :motivoRemision, "
                    + "escalaGlasgow = :escalaGlasgow, "
                    + "sistemaNeurologico = :sistemaNeurologico, "
                    + "sistemaSentidos = :sistemaSentidos, "
                    + "sistemaCardiobascular = :sistemaCardiobascular, "
                    + "sistemaRespiratorio = :sistemaRespiratorio, "
                    + "sistemaDigestivo = :sistemaDigestivo, "
                    + "sistemaGenitoUrinario = :sistemaGenitoUrinario, "
                    + "sistemaOsteomuscular = :sistemaOsteomuscular, "
                    + "observacionGeneral = :observacionGeneral, "
                    + "usuarioCrea = :usuarioCrea, "
                    + "terminalCrea = :terminalCrea, "
                    + "fechaHoraCrea = :fechaHoraCrea, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = : terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica, "
                    + "refAnexos9Id.id = :refAnexos9Id "
                    + "WHERE id = :id";

            Query query = getEntityManager().createQuery(sql);
            query.setParameter("fechaHoraDatos", obj.getFechaHoraDatos());
            query.setParameter("triage", obj.getTriage());
            query.setParameter("antecedentes", obj.getAntecedentes());
            query.setParameter("resumenClinico", obj.getResumenClinico());
            query.setParameter("temperatura", obj.getTemperatura());
            query.setParameter("frecuenciaCardiaca", obj.getFrecuenciaCardiaca());
            query.setParameter("tensionArtedialDiastole", obj.getTensionArtedialDiastole());
            query.setParameter("tensionArterialSistole", obj.getTensionArterialSistole());
            query.setParameter("frecuenciaRespiratoria", obj.getFrecuenciaRespiratoria());
            query.setParameter("saturacionOxigeno", obj.getSaturacionOxigeno());
            query.setParameter("peso", obj.getPeso());
            query.setParameter("talla", obj.getTalla());
            query.setParameter("perimetroAbdominal", obj.getPerimetroAbdominal());
            query.setParameter("imc", obj.getImc());
            query.setParameter("hallazgosExamenFisico", obj.getHallazgosExamenFisico());
            query.setParameter("examenesApoyoDiagnostico", obj.getExamenesApoyoDiagnostico());
            query.setParameter("tratamientoAplicado", obj.getTratamientoAplicado());
            query.setParameter("motivoRemision", obj.getMotivoRemision());
            query.setParameter("escalaGlasgow", obj.getEscalaGlasgow());
            query.setParameter("sistemaNeurologico", obj.getSistemaNeurologico());
            query.setParameter("sistemaSentidos", obj.getSistemaSentidos());
            query.setParameter("sistemaCardiobascular", obj.getSistemaCardiovascular());
            query.setParameter("sistemaRespiratorio", obj.getSistemaRespiratorio());
            query.setParameter("sistemaDigestivo", obj.getSistemaDigestivo());
            query.setParameter("sistemaGenitoUrinario", obj.getSistemaGenitoUrinario());
            query.setParameter("sistemaOsteomuscular", obj.getSistemaOsteomuscular());
            query.setParameter("observacionGeneral", obj.getObservacionGeneral());
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("refAnexos9Id", obj.getRefAnexo9().getId());
            query.setParameter("id", obj.getId());

            query.executeUpdate();

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }

    }

    @Override
    public RefAnexo9DatoClinico consultarPorRefAnexo9(int refAnexo9Id) throws Exception {
        RefAnexo9DatoClinico resultado = new RefAnexo9DatoClinico();

        try {
            String strQuery = "SELECT r FROM RefAnexo9DatosClinicos r "
                    + "WHERE refAnexos9Id.id = :id ";

            resultado = castEntidadNegocio((RefAnexo9DatosClinicos) getEntityManager().createQuery(strQuery).setParameter("id", refAnexo9Id).setMaxResults(1).getSingleResult());
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return resultado;
    }

    @Override
    public RefAnexo9DatoClinico consultar(int id) throws Exception {
        RefAnexo9DatoClinico resultado = new RefAnexo9DatoClinico();

        try {
            resultado = castEntidadNegocio((RefAnexo9DatosClinicos) getEntityManager().find(RefAnexo9DatosClinicos.class, id));
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return resultado;
    }

    public static RefAnexo9DatoClinico castEntidadNegocio(RefAnexo9DatosClinicos ent) {
        RefAnexo9DatoClinico obj = new RefAnexo9DatoClinico();

        obj.setAntecedentes(ent.getAntecedentes());
        obj.setEscalaGlasgow(ent.getEscalaGlasgow());
        obj.setExamenesApoyoDiagnostico(ent.getExamenesApoyoDiagnostico());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraDatos(ent.getFechaHoraDatos());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFrecuenciaCardiaca(ent.getFrecuenciaCardiaca());
        obj.setFrecuenciaRespiratoria(ent.getFrecuenciaRespiratoria());
        obj.setHallazgosExamenFisico(ent.getHallazgosExamenFisico());
        obj.setId(ent.getId());
        obj.setImc(ent.getImc());
        obj.setMotivoRemision(ent.getMotivoRemision());
        obj.setObservacionGeneral(ent.getObservacionGeneral());
        obj.setPerimetroAbdominal(ent.getPerimetroAbdominal());
        obj.setPeso(ent.getPeso());
        if (ent.getRefAnexos9Id() != null) {
            obj.setRefAnexo9(new RefAnexo9(ent.getRefAnexos9Id().getId()));
        }
        obj.setResumenClinico(ent.getResumenClinico());
        obj.setSaturacionOxigeno(ent.getSaturacionOxigeno());
        obj.setSistemaCardiovascular(ent.getSistemaCardiobascular());
        obj.setSistemaDigestivo(ent.getSistemaDigestivo());
        obj.setSistemaGenitoUrinario(ent.getSistemaGenitoUrinario());
        obj.setSistemaNeurologico(ent.getSistemaNeurologico());
        obj.setSistemaOsteomuscular(ent.getSistemaOsteomuscular());
        obj.setSistemaRespiratorio(ent.getSistemaRespiratorio());
        obj.setSistemaSentidos(ent.getSistemaSentidos());
        obj.setTalla(ent.getTalla());
        obj.setTemperatura(ent.getTemperatura());
        obj.setTensionArtedialDiastole(ent.getTensionArtedialDiastole());
        obj.setTensionArterialSistole(ent.getTensionArterialSistole());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setTratamientoAplicado(ent.getTratamientoAplicado());
        obj.setTriage(ent.getTriage());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());

        return obj;
    }

    public static RefAnexo9DatosClinicos castNegocioEntidad(RefAnexo9DatoClinico obj) {
        RefAnexo9DatosClinicos ent = new RefAnexo9DatosClinicos();
        ent.setAntecedentes(obj.getAntecedentes());
        ent.setEscalaGlasgow(obj.getEscalaGlasgow());
        ent.setExamenesApoyoDiagnostico(obj.getExamenesApoyoDiagnostico());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setFechaHoraDatos(obj.getFechaHoraDatos());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setFrecuenciaCardiaca(obj.getFrecuenciaCardiaca());
        ent.setFrecuenciaRespiratoria(obj.getFrecuenciaRespiratoria());
        ent.setHallazgosExamenFisico(obj.getHallazgosExamenFisico());
        ent.setId(obj.getId());
        ent.setImc(obj.getImc());
        ent.setMotivoRemision(obj.getMotivoRemision());
        ent.setObservacionGeneral(obj.getObservacionGeneral());
        ent.setPerimetroAbdominal(obj.getPerimetroAbdominal());
        ent.setPeso(obj.getPeso());
        if (obj.getRefAnexo9() != null && obj.getRefAnexo9().getId() != null) {
            ent.setRefAnexos9Id(new RefAnexos9(obj.getRefAnexo9().getId()));
        }
        ent.setResumenClinico(obj.getResumenClinico());
        ent.setSaturacionOxigeno(obj.getSaturacionOxigeno());
        ent.setSistemaCardiobascular(obj.getSistemaCardiovascular());
        ent.setSistemaDigestivo(obj.getSistemaDigestivo());
        ent.setSistemaGenitoUrinario(obj.getSistemaGenitoUrinario());
        ent.setSistemaNeurologico(obj.getSistemaNeurologico());
        ent.setSistemaOsteomuscular(obj.getSistemaOsteomuscular());
        ent.setSistemaRespiratorio(obj.getSistemaRespiratorio());
        ent.setSistemaSentidos(obj.getSistemaSentidos());
        ent.setTalla(obj.getTalla());
        ent.setTemperatura(obj.getTemperatura());
        ent.setTensionArtedialDiastole(obj.getTensionArtedialDiastole());
        ent.setTensionArterialSistole(obj.getTensionArterialSistole());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setTratamientoAplicado(obj.getTratamientoAplicado());
        ent.setTriage(obj.getTriage());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        return ent;
    }
}
