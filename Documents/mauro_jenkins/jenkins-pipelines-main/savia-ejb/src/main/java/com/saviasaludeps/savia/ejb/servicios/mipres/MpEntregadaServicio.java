/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionProgramada;
import com.saviasaludeps.savia.dominio.mipres.MpProgramadaEntrega;
import com.saviasaludeps.savia.ejb.entidades.MpProgramadaEntregas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.mipres.MpEntregadaRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(MpEntregadaRemoto.class)
public class MpEntregadaServicio extends GenericoServicio implements MpEntregadaRemoto {

    @Override
    public List<MpProgramadaEntrega> consultarListaPorTecnologia(int idTecnologia) throws Exception {
        List<MpProgramadaEntrega> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mp FROM MpProgramadaEntregas mp "
                    + "WHERE mp.mpPrescripcionTecnologiasId.id = :id ");
            List<MpProgramadaEntregas> lista = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("id", idTecnologia)
                    .getResultList();
            for (MpProgramadaEntregas entrega : lista) {
                listResult.add(castEntidadNegocio(entrega));
            }
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listResult;
    }

    @Override
    public List<MpProgramadaEntrega> consultarListaPorInsumo(int idTecnologia) throws Exception {
        List<MpProgramadaEntrega> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mp FROM MpProgramadaEntregas mp "
                    + "WHERE mp.mpPrescripcionInsumosId.id = :id ");
            List<MpProgramadaEntregas> lista = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("id", idTecnologia)
                    .getResultList();
            for (MpProgramadaEntregas entrega : lista) {
                listResult.add(castEntidadNegocio(entrega));
            }
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listResult;
    }

    @Override
    public List<MpProgramadaEntrega> consultarListaPorMedicamento(int idTecnologia) throws Exception {
        List<MpProgramadaEntrega> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mp FROM MpProgramadaEntregas mp "
                    + "WHERE mp.mpPrescripcionMedicamentosId.id = :id ");
            List<MpProgramadaEntregas> lista = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("id", idTecnologia)
                    .getResultList();
            for (MpProgramadaEntregas entrega : lista) {
                listResult.add(castEntidadNegocio(entrega));
            }
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listResult;
    }

    public static MpProgramadaEntrega castEntidadNegocio(MpProgramadaEntregas ent) {
        MpProgramadaEntrega obj = new MpProgramadaEntrega();
        obj.setCantidad(ent.getCantidad());
        obj.setNumeroEntrega(ent.getNumeroEntrega());
        obj.setCausaNoEntrega(ent.getCausaNoEntrega());
        obj.setEntregaTotal(ent.getEntregaTotal());
        obj.setEstado(ent.getEstado());
        obj.setFechaAnulacion(ent.getFechaAnulacion());
        obj.setFechaEntrega(ent.getFechaEntrega());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setId(ent.getId());
        obj.setIdReporteEntrega(ent.getIdReporteEntrega());
        obj.setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionesId().getId()));
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());

        return obj;
    }
}
