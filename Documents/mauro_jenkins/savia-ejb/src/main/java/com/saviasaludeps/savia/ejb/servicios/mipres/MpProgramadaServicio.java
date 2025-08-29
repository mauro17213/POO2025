/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionProgramada;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.mipres.MpProgramadaRemoto;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionInsumos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionProgramadas;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(MpProgramadaRemoto.class)
public class MpProgramadaServicio extends GenericoServicio implements MpProgramadaRemoto {

    @Override
    public List<MpPrescripcionProgramada> consultarPorTipoTecnologia(int id, int tipoTecnologia) throws Exception {

        List<MpPrescripcionProgramada> listaResultado = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mp FROM MpPrescripcionProgramadas mp WHERE mp.tipoTecnologia = :tipoTecnologia ");

            if (tipoTecnologia == 1 || tipoTecnologia == 4) {
                strQuery.append("AND mp.mpPrescripcionMedicamentosId.id = :id");
            } else if (tipoTecnologia == 2) {
                strQuery.append("AND mp.mpPrescripcionTecnologiasId.id = :id");
            } else if (tipoTecnologia == 3 || tipoTecnologia == 5) {
                strQuery.append("AND mp.mpPrescripcionInsumosId.id = :id");
            }

            List<MpPrescripcionProgramadas> lista = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("tipoTecnologia", tipoTecnologia)
                    .setParameter("id", id)
                    .getResultList();
            for (MpPrescripcionProgramadas mpInsumo : lista) {
                listaResultado.add(castEntidadNegocio(mpInsumo));
            }
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    public static MpPrescripcionProgramada castEntidadNegocio(MpPrescripcionProgramadas ent) {
        MpPrescripcionProgramada obj = new MpPrescripcionProgramada();
        obj.setEntregaCantidad(ent.getEntregaCantidad());
        obj.setEntregaNumero(ent.getEntregaNumero());
        obj.setEntregaTotal(ent.getEntregaTotal());
        obj.setEntregadoNumero(ent.getEntregadoNumero());
        obj.setEntregadoPendiente(ent.getEntregadoPendiente());
        obj.setEntregadoTotal(ent.getEntregadoTotal());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setId(ent.getId());
        obj.setMaeTipoDocumentoPrestadorCodigo(ent.getMaeTipoDocumentoPrestadorCodigo());
        obj.setMaeTipoDocumentoPrestadorId(ent.getMaeTipoDocumentoPrestadorId());
        obj.setMaeTipoDocumentoPrestadorValor(ent.getMaeTipoDocumentoPrestadorValor());
        if (ent.getMpPrescripcionInsumosId() != null) {
            obj.setMpPrescripcionInsumos(new MpPrescripcionInsumo(ent.getMpPrescripcionInsumosId().getId()));
            obj.getMpPrescripcionInsumos().setCodigoDispositivo(ent.getMpPrescripcionInsumosId().getCodigoDispositivo());
        }
        if (ent.getMpPrescripcionMedicamentosId() != null) {
            obj.setMpPrescripcionMedicamentos(new MpPrescripcionMedicamento(ent.getMpPrescripcionMedicamentosId().getId()));
            obj.getMpPrescripcionMedicamentos().setCodigoFormulaFarmaceutica(ent.getMpPrescripcionMedicamentosId().getCodigoFormulaFarmaceutica());
        }
        if (ent.getMpPrescripcionTecnologiasId() != null) {
            obj.setMpPrescripcionTecnologias(new MpPrescripcionTecnologia(ent.getMpPrescripcionTecnologiasId().getId()));
            obj.getMpPrescripcionTecnologias().setMaTecnologiaCodigo(ent.getMpPrescripcionTecnologiasId().getMaTecnologiaCodigo());
        }
        obj.setPrestadorNumeroDocumento(ent.getPrestadorNumeroDocumento());
        obj.setPrestadorRazonSocial(ent.getPrestadorRazonSocial());
        obj.setSedeCodigoHabilitacion(ent.getSedeCodigoHabilitacion());
        obj.setSedeDireccion(ent.getSedeDireccion());
        obj.setSedeTelefono(ent.getSedeTelefono());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setIdDireccionamiento(ent.getIdDireccionamiento());
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setFechaDireccionamiento(ent.getFechaDireccionamiento());
        obj.setFechaMaximaEntrega(ent.getFechaMaxEntrega());
        obj.setEstado(ent.getEstado());
        return obj;
    }

    public static MpPrescripcionProgramadas castNegocioEntidad(MpPrescripcionProgramada obj) {
        MpPrescripcionProgramadas ent = new MpPrescripcionProgramadas();
        try {
            ent.setEntregaCantidad(obj.getEntregaCantidad());
            ent.setEntregaNumero(obj.getEntregaNumero());
            ent.setEntregaTotal(obj.getEntregaTotal());
            ent.setEntregadoNumero(obj.getEntregadoNumero());
            ent.setEntregadoPendiente(obj.getEntregadoPendiente());
            ent.setEntregadoTotal(obj.getEntregadoTotal());
            ent.setFechaHoraCrea(obj.getFechaHoraCrea());
            ent.setId(obj.getId());
            ent.setMaeTipoDocumentoPrestadorCodigo(obj.getMaeTipoDocumentoPrestadorCodigo());
            ent.setMaeTipoDocumentoPrestadorId(obj.getMaeTipoDocumentoPrestadorId());
            ent.setMaeTipoDocumentoPrestadorValor(obj.getMaeTipoDocumentoPrestadorValor());
            if (obj.getMpPrescripcion() != null) {
                ent.setMpPrescripcionesId(new MpPrescripciones(obj.getMpPrescripcion().getId()));
            }
            if (obj.getMpPrescripcionInsumos() != null) {
                ent.setMpPrescripcionInsumosId(new MpPrescripcionInsumos(obj.getMpPrescripcionInsumos().getId()));
            }
            if (obj.getMpPrescripcionMedicamentos() != null) {
                ent.setMpPrescripcionMedicamentosId(new MpPrescripcionMedicamentos(obj.getMpPrescripcionMedicamentos().getId()));
            }
            if (obj.getMpPrescripcionTecnologias() != null) {
                ent.setMpPrescripcionTecnologiasId(new MpPrescripcionTecnologias(obj.getMpPrescripcionTecnologias().getId()));
            }
            ent.setIdDireccionamiento(obj.getIdDireccionamiento());
            ent.setIdTransaccion(obj.getIdTransaccion());
            ent.setEstado(obj.getEstado());
            ent.setFechaDireccionamiento(obj.getFechaDireccionamiento());
            ent.setFechaMaxEntrega(obj.getFechaMaximaEntrega());
            ent.setPrestadorNumeroDocumento(obj.getPrestadorNumeroDocumento());
            ent.setPrestadorRazonSocial(obj.getPrestadorRazonSocial());
            if (ent.getPrestadorRazonSocial() == null || ent.getPrestadorRazonSocial().equals("")) {
                ent.setPrestadorRazonSocial("No identificado");
            }
            ent.setSedeCodigoHabilitacion(obj.getSedeCodigoHabilitacion());
            ent.setSedeDireccion(obj.getSedeDireccion());
            ent.setSedeTelefono(obj.getSedeTelefono());
            ent.setTipoTecnologia(obj.getTipoTecnologia());
            ent.setUsuarioCrea(obj.getUsuarioCrea());
            ent.setTerminalCrea(obj.getTerminalCrea());
        } catch (Exception ex) {
            System.out.println("error");
        }
        return ent;
    }

}
