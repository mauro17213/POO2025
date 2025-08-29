/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PeCarga;
import com.saviasaludeps.savia.dominio.especial.PeCargaHistorico;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadosProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeCargas;
import com.saviasaludeps.savia.ejb.entidades.PeCargaHistoricos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeCargaHistoricoRemoto;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author sgiraldo
 */
@Stateless
@Remote(PeCargaHistoricoRemoto.class)
public class PeCargaHistoricoServicio extends GenericoServicio implements PeCargaHistoricoRemoto {

    @Override
    public PeCargaHistorico consultar(int id) throws Exception {
         PeCargaHistorico objResult = new PeCargaHistorico();

        try {
            objResult = castEntidadNegocio((PeCargaHistoricos) getEntityManager().find(PeCargaHistoricos.class, id));
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
    public int insertar(PeCargaHistorico obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(CastNegocioEntidad(obj)).getId();
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
    
    private PeCargaHistoricos CastNegocioEntidad(PeCargaHistorico negocio) {
        PeCargaHistoricos entidad = new PeCargaHistoricos();
        entidad.setTipo(negocio.isTipo());
        entidad.setMaDiagnosticoPrincipalId(negocio.getMaDiagnosticoPrincipalId());
        entidad.setMaDiagnosticoPrincipalCodigo(negocio.getMaDiagnosticoPrincipalCodigo());
        entidad.setMaDiagnosticoPrincipalValor(negocio.getMaDiagnosticoPrincipalValor());
        entidad.setMaDiagnostico2Id(negocio.getMaDiagnostico2Id());
        entidad.setMaDiagnostico2Codigo(negocio.getMaDiagnostico2Codigo());
        entidad.setMaDiagnostico2Valor(negocio.getMaDiagnostico2Valor());
        entidad.setMaDiagnostico3Id(negocio.getMaDiagnostico3Id());
        entidad.setMaDiagnostico3Codigo(negocio.getMaDiagnostico3Codigo());
        entidad.setMaDiagnostico3Valor(negocio.getMaDiagnostico3Valor());
        entidad.setFechaDiagnostico(negocio.getFechaDiagnostico());
        entidad.setFechaInicioPrograma(negocio.getFechaInicioPrograma());
        entidad.setFechaFinPrograma(negocio.getFechaFinPrograma());
        entidad.setMaeRegionCorporalId(negocio.getMaeRegionCorporalId());
        entidad.setMaeRegionCorporalCodigo(negocio.getMaeRegionCorporalCodigo());
        entidad.setMaeRegionCorporalValor(negocio.getMaeRegionCorporalValor());
        entidad.setCntPrestadorSedesId(new CntPrestadorSedes(negocio.getCntPrestadorSedeId().getId()));
        entidad.setPeAfiliadosProgramasId(new PeAfiliadosProgramas(negocio.getPeAfiliadoProgramaId().getId()));
        entidad.setPeCargasId(new PeCargas(negocio.getPeCargaId().getId()));
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        return  entidad;
    }
    
    private PeCargaHistorico castEntidadNegocio(PeCargaHistoricos entidad) {
        PeCargaHistorico negocio = new PeCargaHistorico();
        negocio.setTipo(entidad.getTipo());
        negocio.setMaDiagnosticoPrincipalId(entidad.getMaDiagnosticoPrincipalId());
        negocio.setMaDiagnosticoPrincipalCodigo(entidad.getMaDiagnosticoPrincipalCodigo());
        negocio.setMaDiagnosticoPrincipalValor(entidad.getMaDiagnosticoPrincipalValor());
        negocio.setMaDiagnostico2Id(entidad.getMaDiagnostico2Id());
        negocio.setMaDiagnostico2Codigo(entidad.getMaDiagnostico2Codigo());
        negocio.setMaDiagnostico2Valor(entidad.getMaDiagnostico2Valor());
        negocio.setMaDiagnostico3Id(entidad.getMaDiagnostico3Id());
        negocio.setMaDiagnostico3Codigo(entidad.getMaDiagnostico3Codigo());
        negocio.setMaDiagnostico3Valor(entidad.getMaDiagnostico3Valor());
        negocio.setFechaDiagnostico(entidad.getFechaDiagnostico());
        negocio.setFechaInicioPrograma(entidad.getFechaInicioPrograma());
        negocio.setFechaFinPrograma(entidad.getFechaFinPrograma());
        negocio.setMaeRegionCorporalId(entidad.getMaeRegionCorporalId());
        negocio.setMaeRegionCorporalCodigo(entidad.getMaeRegionCorporalCodigo());
        negocio.setMaeRegionCorporalValor(entidad.getMaeRegionCorporalValor());
        negocio.setCntPrestadorSedeId(new CntPrestadorSede(entidad.getCntPrestadorSedesId().getId()));
        negocio.setPeAfiliadoProgramaId(new PeAfiliadosPrograma(entidad.getPeAfiliadosProgramasId().getId()));
        negocio.setPeCargaId(new PeCarga(entidad.getPeCargasId().getId()));
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        return negocio;
    }
    
}
