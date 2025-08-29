
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservices.mipres;

import com.google.gson.Gson;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.mipres.MpConsumo;
import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionHistorico;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionProgramada;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionHistoricos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import com.saviasaludeps.savia.ejb.utilidades.RemotoEJB;
import com.saviasaludeps.savia.ejb.utilidades.Util;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpInsumoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpMedicamentoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpPrescripcionDetalleRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpTecnologiaRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.DireccionamientoWsRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.MpPrescripcionProgramadaRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.MpPrescripcionHistoricoRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.PrescripcionWsRemoto;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento.Direccionamiento;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.JuntaProfesional;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.Prescripcion;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yjimenez
 */
@Stateless
@Remote(DireccionamientoWsRemoto.class)
public class DireccionamientoWsServicio extends GenericoServicio implements DireccionamientoWsRemoto {

    public final static int ESTADO_CONSUMO_CONSULTANDO = 0;
    public final static int ESTADO_CONSUMO_GUARDANDO = 1;
    public final static int ESTADO_CONSUMO_EXITOSO = 2;
    public final static int ESTADO_CONSUMO_CONSULTANDO_ERROR = 3;
    public final static int ESTADO_CONSUMO_GUARDANDO_ERROR = 4;

    private final static int ESTADO_SERVICIO_DIRECCIONADO = 1;
    private final static int ESTADO_SERVICIO_ANULADO = 4;

    private MpPrescripcionProgramadaRemoto getMpPrescripcionProgramadaRemoto() throws Exception {
        return (MpPrescripcionProgramadaRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionProgramadaServicio", MpPrescripcionProgramadaRemoto.class.getName());
    }

    private MpPrescripcionHistoricoRemoto getMpPrescripcionHistoricoRemoto() throws Exception {
        return (MpPrescripcionHistoricoRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionHistoricoServicio", MpPrescripcionHistoricoRemoto.class.getName());
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

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private MpPrescripcionDetalleRemoto getMpPrescripcionDetalleRemoto() throws Exception {
        return (MpPrescripcionDetalleRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionDetalleServicio", MpPrescripcionDetalleRemoto.class.getName());
    }

    private PrescripcionWsRemoto getPrescripcionWsRemoto() throws Exception {
        return (PrescripcionWsRemoto) RemotoEJB.getEJBRemoto("PrescripcionWsServicio", PrescripcionWsRemoto.class
                .getName());
    }

    //PROCESO ACTUALIZACIÓN DE SERVICIOS REPORTADOS COMO DIRECCIONADOS
    @Override
    public MpConsumoFallo procesarDireccionadas(Direccionamiento direccionamiento,
            Map<String, MpPrescripcionMedicamento> listaMedicamentos,
            Map<String, MpPrescripcionTecnologia> listaTecnologias,
            Map<String, MpPrescripcionInsumo> listaInsumos,
            Map<String, CntPrestador> listaSedes,
            HashMap<String, Maestro> hashMaestroTipoDocumentoEmpresas) throws Exception {
        MpConsumoFallo mpFallo = new MpConsumoFallo();
        mpFallo.setEstado((short) 0);
        switch (direccionamiento.getTipoTec()) {
            case "M":
                mpFallo = procesarMedicamento(
                        direccionamiento,
                        "1",
                        listaMedicamentos,
                        listaSedes,
                        hashMaestroTipoDocumentoEmpresas);
                break;
            case "P":
                mpFallo = procesarTecnologia(
                        direccionamiento,
                        listaTecnologias,
                        listaSedes,
                        hashMaestroTipoDocumentoEmpresas);
                break;
            case "D":
                mpFallo = procesarInsumo(
                        direccionamiento,
                        "3",
                        listaInsumos,
                        listaSedes,
                        hashMaestroTipoDocumentoEmpresas);
                break;
            case "N":
                mpFallo = procesarMedicamento(
                        direccionamiento,
                        "4",
                        listaMedicamentos,
                        listaSedes,
                        hashMaestroTipoDocumentoEmpresas);
                break;
            case "S":
                mpFallo = procesarInsumo(
                        direccionamiento,
                        "5",
                        listaInsumos,
                        listaSedes,
                        hashMaestroTipoDocumentoEmpresas);
                break;
            default:
                throw new IllegalArgumentException("Tipo de tecnología no soportado: " + direccionamiento.getTipoTec());
        }
        return mpFallo;
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

    private MpConsumoFallo procesarMedicamento(
            Direccionamiento direccionamiento,
            String tipoTecnologia,
            Map<String, MpPrescripcionMedicamento> listaMedicamentos,
            Map<String, CntPrestador> listaSedes,
            HashMap<String, Maestro> hashMaestroTipoDocumentoEmpresas) {
        MpConsumoFallo fallo = new MpConsumoFallo();
        fallo.setEstado((short) 0);
        MpPrescripcionMedicamento medicamento = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            medicamento = consultarMedicamentoEnLista(
                    direccionamiento.getNoPrescripcion(),
                    direccionamiento.getConTec().toString(),
                    tipoTecnologia, listaMedicamentos);
            if (medicamento != null) {
                MpPrescripcionProgramada programada = castMpPrescripcionProgramada(medicamento.getMpPrescripcion());
                programada.setMpPrescripcionMedicamentos(new MpPrescripcionMedicamento(medicamento.getId()));
                programada.setTipoTecnologia(medicamento.getTipoTecnologia());
                programada.setEntregaNumero(direccionamiento.getNoEntrega());
                programada.setEntregaCantidad(medicamento.getCantidadTotalEntrega().intValue());
                programada.setEntregaTotal(medicamento.getCantidadTotalFormulada().intValue());
                programada.setEntregadoNumero(direccionamiento.getNoSubEntrega());
                CntPrestador prestador = listaSedes.getOrDefault(direccionamiento.getNoIDProv(), null);
                if (prestador != null) {
                    programada.setMaeTipoDocumentoPrestadorId(prestador.getMaeTipoDocumentoId());
                    programada.setMaeTipoDocumentoPrestadorCodigo(prestador.getMaeTipoDocumentoCodigo());
                    programada.setMaeTipoDocumentoPrestadorValor(prestador.getMaeTipoDocumentoValor());
                    programada.setPrestadorRazonSocial(prestador.getRazonSocial());
                    programada.setPrestadorNumeroDocumento(prestador.getNumeroDocumento());
                    programada.setSedeCodigoHabilitacion(prestador.getCodigoMinSalud());
                } else {
                    programada.setMaeTipoDocumentoPrestadorCodigo("AA");
                    programada.setPrestadorNumeroDocumento(direccionamiento.getNoIDProv());
                    programada.setPrestadorRazonSocial("NO SE ENCUENTRA SEDE");
                    programada.setSedeCodigoHabilitacion(direccionamiento.getNoIDProv());
                }
//                Maestro tipoDocumentoPrestador = hashMaestroTipoDocumentoEmpresas.get(direccionamiento.getTipoIDProv());
//                if (tipoDocumentoPrestador != null) {
//                    programada.setMaeTipoDocumentoPrestadorId(tipoDocumentoPrestador.getId());
//                    programada.setMaeTipoDocumentoPrestadorCodigo(tipoDocumentoPrestador.getValor());
//                    programada.setMaeTipoDocumentoPrestadorValor(tipoDocumentoPrestador.getNombre());
//                }
//                programada.setPrestadorNumeroDocumento(direccionamiento.getNoIDProv());
//                if (programada.getPrestadorRazonSocial() == null) {
//                    programada.setPrestadorRazonSocial("No registrado en la base de datos");
//                }
                if (direccionamiento.getCantTotAEntregar() != null && !direccionamiento.getCantTotAEntregar().equals("")) {
                    programada.setEntregadoTotal(Util.convertirANumero(direccionamiento.getCantTotAEntregar()));
                }
                //CntPrestadorSedes sede = consultarPrestadorSedePorCodigoHabilitacion(prescripcion.getSedeCodigoHabilitacion());
//                if (sede != null) {
//                    programada.setSedeDireccion(prestador.getDireccion());
//                    programada.setSedeTelefono(sede.getTelefonoCitas());
//                }
                programada.setIdTransaccion(direccionamiento.getID());
                try {
                    if (direccionamiento.getFecMaxEnt() != null) {
                        programada.setFechaMaximaEntrega(formato.parse(direccionamiento.getFecMaxEnt()));
                    }
                } catch (ParseException ex) {
                    programada.setFechaMaximaEntrega(null);
                }
                programada.setIdDireccionamiento(direccionamiento.getIDDireccionamiento());
                if (direccionamiento.getFecAnulacion() != null
                        && !direccionamiento.getFecAnulacion().equals("")) {
                    programada.setEstado(ESTADO_SERVICIO_ANULADO);
                    Date fechaDireccionamiento = formato.parse(direccionamiento.getFecAnulacion());
                    programada.setFechaDireccionamiento(fechaDireccionamiento);
                } else {
                    programada.setEstado(ESTADO_SERVICIO_DIRECCIONADO);
                    if (direccionamiento.getFecDireccionamiento() != null
                            && !direccionamiento.getFecDireccionamiento().equals("")) {
                        Date fechaDireccionamiento = formato.parse(direccionamiento.getFecDireccionamiento());
                        programada.setFechaDireccionamiento(fechaDireccionamiento);
                    }
                }
                programada.setEntregadoPendiente(medicamento.getPendientes().intValue());
                programada.setMpPrescripcion(medicamento.getMpPrescripcion());
                try {
                    programada.setId(getMpPrescripcionProgramadaRemoto().insertar(programada));
                } catch (Exception ex) {
                    fallo.setEstado((short) 1);
                    fallo.setFechaHoraFallo(new Date());
                    fallo.setDescripcion("Error insertar PrescripcionProgramada-Medicamento: " + ex.getMessage());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(direccionamiento);
                    fallo.setJson(jsonString);
                    return fallo;
                }
                MpPrescripcionHistorico historico = new MpPrescripcionHistorico();
                historico.setMpPrescripcion(new MpPrescripcion(medicamento.getMpPrescripcion().getId()));
                historico.setIdPrescripcionTecnologia(medicamento.getId());
                historico.setTipoTecnologia(medicamento.getTipoTecnologia());
                historico.setEstado(medicamento.getEstado());
                historico.setUsuarioCrea("mipres sincroniza");
                historico.setTerminalCrea("127.0.0.1");
                historico.setFechaHoraCrea(new Date());
                getMpPrescripcionHistoricoRemoto().insertar(historico);
                //insertarMpPrescripcionHistoricos(historico);
            } else {
                fallo.setEstado((short) 1);
                fallo.setFechaHoraFallo(new Date());
                fallo.setDescripcion("Medicamento no encontrado: "
                        + direccionamiento.getNoPrescripcion() + "-"
                        + direccionamiento.getConTec().toString() + "-"
                        + tipoTecnologia);
                Gson gson = new Gson();
                String jsonString = gson.toJson(direccionamiento);
                //byte[] json = jsonString.getBytes();
                fallo.setJson(jsonString);
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

    private MpConsumoFallo procesarTecnologia(
            Direccionamiento direccionamiento,
            Map<String, MpPrescripcionTecnologia> listaTecnologias,
            Map<String, CntPrestador> listaSedes,
            HashMap<String, Maestro> hashMaestroTipoDocumentoEmpresas) {
        MpConsumoFallo fallo = new MpConsumoFallo();
        fallo.setEstado((short) 0);
        MpPrescripcionTecnologia tecnologia;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            tecnologia = consultarTecnologiaEnLista(direccionamiento.getNoPrescripcion(), direccionamiento.getConTec().toString(), listaTecnologias);
            if (tecnologia != null) {
                MpPrescripcionProgramada programada = castMpPrescripcionProgramada(tecnologia.getMpPrescripcion());
                programada.setMpPrescripcionTecnologias(new MpPrescripcionTecnologia(tecnologia.getId()));
                programada.setTipoTecnologia(tecnologia.getTipoTecnologia());
                programada.setEntregaNumero(direccionamiento.getNoEntrega());
                programada.setEntregaCantidad(tecnologia.getEntregados().intValue());
                programada.setEntregaTotal(tecnologia.getCantidadFormulada());
                programada.setEntregadoNumero(direccionamiento.getNoSubEntrega());
                CntPrestador prestador = listaSedes.getOrDefault(direccionamiento.getNoIDProv(), null);
                if (prestador != null) {
                    programada.setMaeTipoDocumentoPrestadorId(prestador.getMaeTipoDocumentoId());
                    programada.setMaeTipoDocumentoPrestadorCodigo(prestador.getMaeTipoDocumentoCodigo());
                    programada.setMaeTipoDocumentoPrestadorValor(prestador.getMaeTipoDocumentoValor());
                    programada.setPrestadorRazonSocial(prestador.getRazonSocial());
                    programada.setPrestadorNumeroDocumento(prestador.getNumeroDocumento());
                    programada.setSedeCodigoHabilitacion(prestador.getCodigoMinSalud());
                } else {
                    programada.setMaeTipoDocumentoPrestadorCodigo("AA");
                    programada.setPrestadorNumeroDocumento(direccionamiento.getNoIDProv());
                    programada.setPrestadorRazonSocial("NO SE ENCUENTRA SEDE");
                    programada.setSedeCodigoHabilitacion(direccionamiento.getNoIDProv());
                }
//                CntPrestador prestador = getCntPrestadorRemoto().consultarPorNit(direccionamiento.getNoIDProv());
//                if (prestador != null) {
//                    programada.setPrestadorRazonSocial(prestador.getRazonSocial());
//                    programada.setSedeCodigoHabilitacion(prestador.getCodigoMinSalud());
//                }
//                Maestro tipoDocumentoPrestador = hashMaestroTipoDocumentoEmpresas.get(direccionamiento.getTipoIDProv());
//                if (tipoDocumentoPrestador != null) {
//                    programada.setMaeTipoDocumentoPrestadorId(tipoDocumentoPrestador.getId());
//                    programada.setMaeTipoDocumentoPrestadorCodigo(tipoDocumentoPrestador.getValor());
//                    programada.setMaeTipoDocumentoPrestadorValor(tipoDocumentoPrestador.getNombre());
//                }
//                programada.setPrestadorNumeroDocumento(direccionamiento.getNoIDProv());
//                programada.setPrestadorNumeroDocumento(direccionamiento.getNoIDProv());
                if (programada.getPrestadorRazonSocial() == null) {
                    programada.setPrestadorRazonSocial("No registrado en la base de datos");
                }
                if (direccionamiento.getCantTotAEntregar() != null && !direccionamiento.getCantTotAEntregar().equals("")) {
                    programada.setEntregadoTotal(Util.convertirANumero(direccionamiento.getCantTotAEntregar()));
                }
                //CntPrestadorSedes sede = consultarPrestadorSedePorCodigoHabilitacion(prescripcion.getSedeCodigoHabilitacion());
//                if (sede != null) {
//                    programada.setSedeDireccion(prestador.getDireccion());
//                    programada.setSedeTelefono(sede.getTelefonoCitas());
//                }
                programada.setIdTransaccion(direccionamiento.getID());
                try {
                    if (direccionamiento.getFecMaxEnt() != null) {
                        programada.setFechaMaximaEntrega(formato.parse(direccionamiento.getFecMaxEnt()));
                    }
                } catch (ParseException ex) {
                    programada.setFechaMaximaEntrega(null);
                }
                programada.setIdDireccionamiento(direccionamiento.getIDDireccionamiento());
                if (direccionamiento.getFecAnulacion() != null && !direccionamiento.getFecAnulacion().equals("")) {
                    programada.setEstado(ESTADO_SERVICIO_ANULADO);
                    Date fechaDireccionamiento = formato.parse(direccionamiento.getFecAnulacion());
                    programada.setFechaDireccionamiento(fechaDireccionamiento);
                } else {
                    programada.setEstado(ESTADO_SERVICIO_DIRECCIONADO);
                    if (direccionamiento.getFecDireccionamiento() != null && !direccionamiento.getFecDireccionamiento().equals("")) {
                        Date fechaDireccionamiento = formato.parse(direccionamiento.getFecDireccionamiento());
                        programada.setFechaDireccionamiento(fechaDireccionamiento);
                    }
                }
                programada.setEntregadoPendiente(tecnologia.getPendientes().intValue());
                programada.setMpPrescripcion(tecnologia.getMpPrescripcion());
                try {
                    programada.setId(getMpPrescripcionProgramadaRemoto().insertar(programada));
                } catch (Exception ex) {
                    fallo.setEstado((short) 1);
                    fallo.setFechaHoraFallo(new Date());
                    fallo.setDescripcion("Error insertar PrescripcionProgramada-Tecnologia: " + ex.getMessage());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(direccionamiento);
                    fallo.setJson(jsonString);
                    return fallo;
                }
                MpPrescripcionHistorico historico = new MpPrescripcionHistorico();
                historico.setMpPrescripcion(new MpPrescripcion(tecnologia.getMpPrescripcion().getId()));
                historico.setIdPrescripcionTecnologia(tecnologia.getId());
                historico.setTipoTecnologia(tecnologia.getTipoTecnologia());
                historico.setEstado(tecnologia.getEstado());
                historico.setUsuarioCrea("mipres sincroniza");
                historico.setTerminalCrea("127.0.0.1");
                historico.setFechaHoraCrea(new Date());
                getMpPrescripcionHistoricoRemoto().insertar(historico);
                //insertarMpPrescripcionHistoricos(historico);
            } else {
                fallo.setEstado((short) 1);
                fallo.setFechaHoraFallo(new Date());
                fallo.setDescripcion("Tecnologia no encontrada: "
                        + direccionamiento.getNoPrescripcion() + "-"
                        + direccionamiento.getConTec().toString());
                Gson gson = new Gson();
                String jsonString = gson.toJson(direccionamiento);
                //byte[] json = jsonString.getBytes();
                fallo.setJson(jsonString);
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

    private MpConsumoFallo procesarInsumo(
            Direccionamiento direccionamiento,
            String tipoTecnologia,
            Map<String, MpPrescripcionInsumo> listaInsumos,
            Map<String, CntPrestador> listaSedes,
            HashMap<String, Maestro> hashMaestroTipoDocumentoEmpresas) {
        MpConsumoFallo fallo = new MpConsumoFallo();
        fallo.setEstado((short) 0);
        MpPrescripcionInsumo insumo;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            insumo = consultarInsumoEnLista(direccionamiento.getNoPrescripcion(), direccionamiento.getConTec().toString(), tipoTecnologia, listaInsumos);
            if (insumo != null) {
                MpPrescripcionProgramada programada = castMpPrescripcionProgramada(insumo.getMpPrescripcion());
                programada.setMpPrescripcionInsumos(new MpPrescripcionInsumo(insumo.getId()));
                programada.setTipoTecnologia(insumo.getTipoTecnologia());
                programada.setEntregaNumero(direccionamiento.getNoEntrega());
                programada.setIdTransaccion(direccionamiento.getID());
                try {
                    if (direccionamiento.getFecMaxEnt() != null) {
                        programada.setFechaMaximaEntrega(formato.parse(direccionamiento.getFecMaxEnt()));
                    }
                } catch (ParseException ex) {
                    programada.setFechaMaximaEntrega(null);
                }
                programada.setIdDireccionamiento(direccionamiento.getIDDireccionamiento());
                if (direccionamiento.getFecAnulacion() != null && !direccionamiento.getFecAnulacion().equals("")) {
                    programada.setEstado(ESTADO_SERVICIO_ANULADO);
                    Date fechaDireccionamiento = formato.parse(direccionamiento.getFecAnulacion());
                    programada.setFechaDireccionamiento(fechaDireccionamiento);
                } else {
                    programada.setEstado(ESTADO_SERVICIO_DIRECCIONADO);
                    if (direccionamiento.getFecDireccionamiento() != null && !direccionamiento.getFecDireccionamiento().equals("")) {
                        Date fechaDireccionamiento = formato.parse(direccionamiento.getFecDireccionamiento());
                        programada.setFechaDireccionamiento(fechaDireccionamiento);
                    }
                }
                if (insumo.getCantidad() != null && insumo.getCantidad().equals("")) {
                    programada.setEntregaCantidad(Util.convertirANumero(insumo.getCantidad()));
                }
                if (direccionamiento.getCantTotAEntregar() != null && !direccionamiento.getCantTotAEntregar().equals("")) {
                    programada.setEntregaTotal(Util.convertirANumero(direccionamiento.getCantTotAEntregar()));
                } else {
                    programada.setEntregaTotal(0);
                }
                programada.setEntregadoNumero(direccionamiento.getNoSubEntrega());
                if (direccionamiento.getCantTotAEntregar() != null && !direccionamiento.getCantTotAEntregar().equals("")) {
                    programada.setEntregadoTotal(Util.convertirANumero(direccionamiento.getCantTotAEntregar()));
                } else {
                    programada.setEntregadoTotal(0);
                }
                if (programada.getEntregaTotal() != 0) {
                    if (programada.getEntregaTotal() > programada.getEntregadoTotal()) {
                        programada.setEntregadoPendiente(insumo.getPendiente().intValue());
                    }
                }
//                programada.setPrestadorRazonSocial(insumo.getMpPrescripcion().getPrestadorRazonSocial());
//                if (programada.getPrestadorRazonSocial() == null) {
//                    programada.setPrestadorRazonSocial("Sin definir");
//                }
                CntPrestador prestador = listaSedes.getOrDefault(direccionamiento.getNoIDProv(), null);
                if (prestador != null) {
                    programada.setMaeTipoDocumentoPrestadorId(prestador.getMaeTipoDocumentoId());
                    programada.setMaeTipoDocumentoPrestadorCodigo(prestador.getMaeTipoDocumentoCodigo());
                    programada.setMaeTipoDocumentoPrestadorValor(prestador.getMaeTipoDocumentoValor());
                    programada.setPrestadorRazonSocial(prestador.getRazonSocial());
                    programada.setPrestadorNumeroDocumento(prestador.getNumeroDocumento());
                    programada.setSedeCodigoHabilitacion(prestador.getCodigoMinSalud());
                } else {
                    programada.setMaeTipoDocumentoPrestadorCodigo("AA");
                    programada.setPrestadorNumeroDocumento(direccionamiento.getNoIDProv());
                    programada.setPrestadorRazonSocial("NO SE ENCUENTRA SEDE");
                    programada.setSedeCodigoHabilitacion(direccionamiento.getNoIDProv());
                }
//                Maestro tipoDocumentoPrestador = hashMaestroTipoDocumentoEmpresas.get(direccionamiento.getTipoIDProv());
//                if (tipoDocumentoPrestador != null) {
//                    programada.setMaeTipoDocumentoPrestadorId(tipoDocumentoPrestador.getId());
//                    programada.setMaeTipoDocumentoPrestadorCodigo(tipoDocumentoPrestador.getValor());
//                    programada.setMaeTipoDocumentoPrestadorValor(tipoDocumentoPrestador.getNombre());
//                }
//                programada.setPrestadorNumeroDocumento(direccionamiento.getNoIDProv());
//                if (programada.getPrestadorRazonSocial() == null) {
//                    programada.setPrestadorRazonSocial("No registrado en la base de datos");
//                }
                if (direccionamiento.getCantTotAEntregar() != null && !direccionamiento.getCantTotAEntregar().equals("")) {
                    programada.setEntregadoTotal(Util.convertirANumero(direccionamiento.getCantTotAEntregar()));
                }
                programada.setMpPrescripcion(insumo.getMpPrescripcion());
                try {
                    programada.setId(getMpPrescripcionProgramadaRemoto().insertar(programada));
                } catch (Exception ex) {
                    fallo.setEstado((short) 1);
                    fallo.setFechaHoraFallo(new Date());
                    fallo.setDescripcion("Error insertar PrescripcionProgramada-Insumo: " + ex.getMessage());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(direccionamiento);
                    fallo.setJson(jsonString);
                    return fallo;
                }
                MpPrescripcionHistorico historico = new MpPrescripcionHistorico();
                historico.setMpPrescripcion(new MpPrescripcion(insumo.getMpPrescripcion().getId()));
                historico.setIdPrescripcionTecnologia(insumo.getId());
                historico.setTipoTecnologia(insumo.getTipoTecnologia());
                historico.setEstado(insumo.getEstado());
                historico.setUsuarioCrea("mipres sincroniza");
                historico.setTerminalCrea("127.0.0.1");
                historico.setFechaHoraCrea(new Date());
                getMpPrescripcionHistoricoRemoto().insertar(historico);
                //insertarMpPrescripcionHistoricos(historico);
            } else {
                fallo.setEstado((short) 1);
                fallo.setFechaHoraFallo(new Date());
                fallo.setDescripcion("Insumo no encontrada: "
                        + direccionamiento.getNoPrescripcion() + "-"
                        + direccionamiento.getConTec().toString());
                Gson gson = new Gson();
                String jsonString = gson.toJson(direccionamiento);
                fallo.setJson(jsonString);
                return fallo;
            }
        } catch (Exception ex) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error: " + ex.getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(direccionamiento);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
            return fallo;
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
            System.out.println("ERROR: " + e.toString());
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

    private MpPrescripcionProgramada castMpPrescripcionProgramada(MpPrescripcion prescripcion) throws Exception {
        MpPrescripcionProgramada programada = new MpPrescripcionProgramada();
        programada.setMaeTipoDocumentoPrestadorId(prescripcion.getMaTipoDocumentoPrestadorId());
        programada.setMaeTipoDocumentoPrestadorCodigo(prescripcion.getMaTipoDocumentoPrestadorCodigo());
        programada.setMaeTipoDocumentoPrestadorValor(prescripcion.getMaTipoDocumentoPrestadorValor());
        programada.setUsuarioCrea("mipres sincroniza");
        programada.setTerminalCrea("127.0.0.1");
        programada.setFechaHoraCrea(new Date());
        return programada;
    }

    //CONSULTAS EN LISTA DE SERVICIOS    
    private MpPrescripcionMedicamento consultarMedicamentoEnLista(String noPrescripcion, String consecutivo, String tipoTecnologia, Map<String, MpPrescripcionMedicamento> listaMedicamentos) {
        MpPrescripcionMedicamento medicamento = listaMedicamentos.getOrDefault(noPrescripcion.trim() + "||" + consecutivo.trim(), null);
        if (medicamento != null
                && (Integer.parseInt(consecutivo)) == medicamento.getConsecutivoOrden()
                && Integer.toString(medicamento.getTipoTecnologia()).equals(tipoTecnologia)) {
            return medicamento;
        }
        return null;

    }

    private MpPrescripcionTecnologia consultarTecnologiaEnLista(String noPrescripcion, String consecutivo, Map<String, MpPrescripcionTecnologia> listaTecnologias) {
        MpPrescripcionTecnologia tecnologia = listaTecnologias.getOrDefault(noPrescripcion.trim() + "||" + consecutivo.trim(), null);
        if (tecnologia != null
                && (Integer.parseInt(consecutivo)) == tecnologia.getConsecutivoOrden()) {
            return tecnologia;
        }

        return null;
    }

    private MpPrescripcionInsumo consultarInsumoEnLista(String noPrescripcion, String consecutivo, String tipoTecnologia, Map<String, MpPrescripcionInsumo> listaInsumos) {
        MpPrescripcionInsumo insumo = listaInsumos.getOrDefault(noPrescripcion.trim() + "||" + consecutivo.trim(), null);
        if (insumo != null
                && (Integer.parseInt(consecutivo)) == insumo.getConsecutivoOrden()
                && Integer.toString(insumo.getTipoTecnologia()).equals(tipoTecnologia)) {
            return insumo;
        }

        return null;
    }

}
