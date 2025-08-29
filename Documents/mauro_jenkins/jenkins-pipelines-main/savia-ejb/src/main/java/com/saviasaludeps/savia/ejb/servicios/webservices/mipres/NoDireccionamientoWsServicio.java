
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservices.mipres;

import com.google.gson.Gson;
import com.saviasaludeps.savia.dominio.mipres.MpConsumo;
import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionProgramada;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.MpConsumos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionHistoricos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionInsumos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import com.saviasaludeps.savia.ejb.utilidades.RemotoEJB;
import com.saviasaludeps.savia.ejb.utilidades.Util;
import com.saviasaludeps.savia.negocio.mipres.MpInsumoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpMedicamentoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpTecnologiaRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.MpPrescripcionProgramadaRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.NoDireccionamientoWsRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.PrescripcionWsRemoto;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento.Direccionamiento;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.nodireccionamiento.NoDireccionamiento;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author yjimenez
 */
@Stateless
@Remote(NoDireccionamientoWsRemoto.class)
public class NoDireccionamientoWsServicio extends GenericoServicio implements NoDireccionamientoWsRemoto {

    public final static int ESTADO_CONSUMO_CONSULTANDO = 0;
    public final static int ESTADO_CONSUMO_GUARDANDO = 1;
    public final static int ESTADO_CONSUMO_EXITOSO = 2;
    public final static int ESTADO_CONSUMO_CONSULTANDO_ERROR = 3;
    public final static int ESTADO_CONSUMO_GUARDANDO_ERROR = 4;

    private final static int ESTADO_SERVICIO_NO_DIRECCIONADO = 2;
    private final static int ESTADO_SERVICIO_ANULADO = 4;

    private MpPrescripcionProgramadaRemoto getMpPrescripcionProgramadaRemoto() throws Exception {
        return (MpPrescripcionProgramadaRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionProgramadaServicio", MpPrescripcionProgramadaRemoto.class.getName());
    }

    private MpMedicamentoRemoto getMpMedicamentoRemoto() throws Exception {
        return (MpMedicamentoRemoto) RemotoEJB.getEJBRemoto("MpMedicamentoServicio", MpMedicamentoRemoto.class.getName());
    }

    private MpTecnologiaRemoto getMpTecnologiaRemoto() throws Exception {
        return (MpTecnologiaRemoto) RemotoEJB.getEJBRemoto("MpTecnologiaServicio", MpTecnologiaRemoto.class.getName());
    }

    private MpInsumoRemoto getMpInsumoRemoto() throws Exception {
        return (MpInsumoRemoto) RemotoEJB.getEJBRemoto("MpInsumoServicio", MpInsumoRemoto.class.getName());
    }

    private PrescripcionWsRemoto getPrescripcionWsRemoto() throws Exception {
        return (PrescripcionWsRemoto) RemotoEJB.getEJBRemoto("PrescripcionWsServicio", PrescripcionWsRemoto.class
                .getName());
    }

    //PROCESO
    @Override
    public MpConsumoFallo procesarNoDireccionadas(NoDireccionamiento noDireccionamiento, Map<String, MpPrescripcionMedicamento> listaMedicamentos, Map<String, MpPrescripcionTecnologia> listaTecnologias, Map<String, MpPrescripcionInsumo> listaInsumos) throws Exception {
        MpConsumoFallo fallo = new MpConsumoFallo();
        switch (noDireccionamiento.getTipoTec()) {
            case "M": //MEDICAMENTO
                fallo = procesarMedicamento(noDireccionamiento, "1", listaMedicamentos);
                break;
            case "P": //procedimiento
                fallo = procesarTecnologia(noDireccionamiento, listaTecnologias);
                break;
            case "D": //dispositivo medico
                fallo = procesarInsumo(noDireccionamiento, "3", listaInsumos);
                break;
            case "N": //PRODUCTO NUTRICIONAL
                fallo = procesarMedicamento(noDireccionamiento, "4", listaMedicamentos);
                break;
            case "S": //servicio complementario
                fallo = procesarInsumo(noDireccionamiento, "5", listaInsumos);
                break;
        }
        return fallo;
    }

    private MpConsumoFallo procesarMedicamento(NoDireccionamiento direccionamiento, String tipoTecnologia, Map<String, MpPrescripcionMedicamento> listaMedicamentos) {
        MpPrescripcionMedicamento medicamento = null;
        MpConsumoFallo fallo = new MpConsumoFallo();
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            medicamento = consultarMedicamentoEnLista(direccionamiento.getNoPrescripcion(), direccionamiento.getConTec().toString(), tipoTecnologia, listaMedicamentos);
            if (medicamento != null) {
                medicamento.setIdTransaccion(direccionamiento.getID());
                medicamento.setIdDireccionamiento(direccionamiento.getIDNODireccionamiento().toString());
                if (direccionamiento.getFecAnulacion() != null && !direccionamiento.getFecAnulacion().equals("")) {
                    medicamento.setEstado(ESTADO_SERVICIO_ANULADO);
                    Date fechaDireccionamiento = formato.parse(direccionamiento.getFecAnulacion());
                    medicamento.setFechaDireccionamiento(fechaDireccionamiento);
                } else {
                    medicamento.setEstado(ESTADO_SERVICIO_NO_DIRECCIONADO);
                    if (direccionamiento.getFecNODireccionamiento() != null && !direccionamiento.getFecNODireccionamiento().equals("")) {
                        Date fechaDireccionamiento = formato.parse(direccionamiento.getFecNODireccionamiento());
                        medicamento.setFechaDireccionamiento(fechaDireccionamiento);
                    }
                }
                medicamento.setCantidadTotalEntrega(medicamento.getCantidadTotalFormulada());
                if (medicamento.getCantidadTotalEntrega() == null) {
                    medicamento.setCantidadTotalEntrega(BigDecimal.ZERO);
                }
                if (medicamento.getCantidadTotalFormulada() == null) {
                    medicamento.setCantidadTotalFormulada(BigDecimal.ZERO);
                }
                medicamento.setUsuarioModifica("mipres sincroniza");
                medicamento.setTerminalModifica("127.0.0.1");
                medicamento.setFechaHoraModifica(new Date());
                getMpMedicamentoRemoto().actualizarDireccionamiento(medicamento);
                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                historico.setMpPrescripcionesId(new MpPrescripciones(medicamento.getMpPrescripcion().getId()));
                historico.setIdPrescripcionTecnologia(medicamento.getId());
                historico.setTipoTecnologia(medicamento.getTipoTecnologia());
                historico.setEstado(medicamento.getEstado());
                historico.setUsuarioCrea("mipres sincroniza");
                historico.setTerminalCrea("127.0.0.1");
                historico.setFechaHoraCrea(new Date());
                insertarMpPrescripcionHistoricos(historico);
                }
        } catch (Exception ex) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error: " + ex.getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(direccionamiento);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
        }
        return fallo;
    }

    private MpConsumoFallo procesarTecnologia(NoDireccionamiento direccionamiento, Map<String, MpPrescripcionTecnologia> listaTecnologias) {
        MpPrescripcionTecnologia tecnologia;
        MpConsumoFallo fallo = new MpConsumoFallo();
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            tecnologia = consultarTecnologiaEnLista(direccionamiento.getNoPrescripcion(), direccionamiento.getConTec().toString(), listaTecnologias);
            if (tecnologia != null) {
                tecnologia.setIdTransaccion(direccionamiento.getID());
                tecnologia.setIdDireccionamiento(direccionamiento.getIDNODireccionamiento().toString());

                if (direccionamiento.getFecAnulacion() != null && !direccionamiento.getFecAnulacion().equals("")) {
                    tecnologia.setEstado(ESTADO_SERVICIO_ANULADO);
                    Date fechaDireccionamiento = formato.parse(direccionamiento.getFecAnulacion());
                    tecnologia.setFechaDireccionamiento(fechaDireccionamiento);
                } else {
                    tecnologia.setEstado(ESTADO_SERVICIO_NO_DIRECCIONADO);
                    if (direccionamiento.getFecNODireccionamiento() != null && !direccionamiento.getFecNODireccionamiento().equals("")) {
                        Date fechaDireccionamiento = formato.parse(direccionamiento.getFecNODireccionamiento());
                        tecnologia.setFechaDireccionamiento(fechaDireccionamiento);
                    }
                }
                tecnologia.setUsuarioModifica("mipres sincroniza");
                tecnologia.setTerminalModifica("127.0.0.1");
                tecnologia.setFechaHoraModifica(new Date());
                getMpTecnologiaRemoto().actualizarDireccionamiento(tecnologia);
                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                historico.setMpPrescripcionesId(new MpPrescripciones(tecnologia.getMpPrescripcion().getId()));
                historico.setIdPrescripcionTecnologia(tecnologia.getId());
                historico.setTipoTecnologia(tecnologia.getTipoTecnologia());
                historico.setEstado(tecnologia.getEstado());
                historico.setUsuarioCrea("mipres sincroniza");
                historico.setTerminalCrea("127.0.0.1");
                historico.setFechaHoraCrea(new Date());
                insertarMpPrescripcionHistoricos(historico);
                }
        } catch (Exception ex) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error: " + ex.getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(direccionamiento);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
        }
        return fallo;
    }

    private MpConsumoFallo procesarInsumo(NoDireccionamiento direccionamiento, String tipoTecnologia, Map<String, MpPrescripcionInsumo> listaInsumos) {
        MpPrescripcionInsumo insumo;
        MpConsumoFallo fallo = new MpConsumoFallo();
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            insumo = consultarInsumoEnLista(direccionamiento.getNoPrescripcion(), direccionamiento.getConTec().toString(), tipoTecnologia, listaInsumos);
            if (insumo != null) {
                insumo.setIdTransaccion(direccionamiento.getID());
                insumo.setIdDireccionamiento(direccionamiento.getIDNODireccionamiento().toString());
                if (direccionamiento.getFecAnulacion() != null && !direccionamiento.getFecAnulacion().equals("")) {
                    insumo.setEstado(ESTADO_SERVICIO_ANULADO);//anular direccionado
                    Date fechaDireccionamiento = formato.parse(direccionamiento.getFecAnulacion());
                    insumo.setFechaDireccionamiento(fechaDireccionamiento);
                } else {
                    insumo.setEstado(ESTADO_SERVICIO_NO_DIRECCIONADO);
                    if (direccionamiento.getFecNODireccionamiento() != null && !direccionamiento.getFecNODireccionamiento().equals("")) {
                        Date fechaDireccionamiento = formato.parse(direccionamiento.getFecNODireccionamiento());
                        insumo.setFechaDireccionamiento(fechaDireccionamiento);
                    }
                }
                insumo.setUsuarioModifica("mipres sincroniza");
                insumo.setTerminalModifica("127.0.0.1");
                insumo.setFechaHoraModifica(new Date());
                getMpInsumoRemoto().actualizarDireccionamiento(insumo);
                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                historico.setMpPrescripcionesId(new MpPrescripciones(insumo.getMpPrescripcion().getId()));
                historico.setIdPrescripcionTecnologia(insumo.getId());
                historico.setTipoTecnologia(insumo.getTipoTecnologia());
                historico.setEstado(insumo.getEstado());
                historico.setUsuarioCrea("mipres sincroniza");
                historico.setTerminalCrea("127.0.0.1");
                historico.setFechaHoraCrea(new Date());
                insertarMpPrescripcionHistoricos(historico);
                }
        } catch (Exception ex) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error: " + ex.getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(direccionamiento);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
        }
        return fallo;
    }

    //CONSULTAS
    private CntPrestadorSedes consultarPrestadorSedePorCodigoHabilitacion(String codigoHabilitacion) throws Exception {
        CntPrestadorSedes objRes = null;
        try {
            String strQuery = "FROM CntPrestadorSedes c "
                    + "WHERE c.codigoHabilitacion ='" + codigoHabilitacion + "'";

            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && !list.isEmpty()) {
                objRes = list.get(0);
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            System.out.println(e);
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    //CASTEOS
    public static MpPrescripcionMedicamento castMedicamentosMedicamento(MpPrescripcionMedicamentos ent) {
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
        //obj.setCodigoViaAdministracion(ent.getCodigoViaAdministracion());
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
        //obj.setDosisUnidadMedida(ent.getDosisUnidadMedida());
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
        //obj.setUnidadFarmaceuticaCantidadTotal(ent.getUnidadFarmaceuticaCantidadTotal());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        return obj;
    }

    //CONSULTAS EN LISTA DE SERVICIOS    
    private MpPrescripcionMedicamento consultarMedicamentoEnLista(String noPrescripcion, String consecutivo, String tipoTecnologia, Map<String, MpPrescripcionMedicamento> listaMedicamentos) {
        MpPrescripcionMedicamento medicamento = listaMedicamentos.getOrDefault(noPrescripcion.trim() + "-" + consecutivo.trim(), null);
        if (medicamento != null
                && (Integer.parseInt(consecutivo)) == medicamento.getConsecutivoOrden()
                && Integer.toString(medicamento.getTipoTecnologia()).equals(tipoTecnologia)) {
            return medicamento;
        }
        return null;
    }

    private MpPrescripcionTecnologia consultarTecnologiaEnLista(String noPrescripcion, String consecutivo, Map<String, MpPrescripcionTecnologia> listaTecnologias) {
        MpPrescripcionTecnologia tecnologia = listaTecnologias.getOrDefault(noPrescripcion.trim() + "-" + consecutivo.trim(), null);
        if (tecnologia != null
                && (Integer.parseInt(consecutivo)) == tecnologia.getConsecutivoOrden()) {
            return tecnologia;
        }
        return null;
    }

    private MpPrescripcionInsumo consultarInsumoEnLista(String noPrescripcion, String consecutivo, String tipoTecnologia, Map<String, MpPrescripcionInsumo> listaInsumos) {
        MpPrescripcionInsumo insumo = listaInsumos.getOrDefault(noPrescripcion.trim() + "-" + consecutivo.trim(), null);
        if (insumo != null
                && (Integer.parseInt(consecutivo)) == insumo.getConsecutivoOrden()
                && Integer.toString(insumo.getTipoTecnologia()).equals(tipoTecnologia)) {
            return insumo;
        }
        return null;
    }

    //INSERTS HISTORICOS
    private int insertarMpPrescripcionHistoricos(MpPrescripcionHistoricos historico) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(historico).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

}
