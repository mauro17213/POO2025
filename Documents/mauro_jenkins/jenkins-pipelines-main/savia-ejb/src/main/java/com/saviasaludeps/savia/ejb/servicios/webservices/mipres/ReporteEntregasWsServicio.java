
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservices.mipres;

import com.google.gson.Gson;
import com.saviasaludeps.savia.dominio.mipres.MpConsumo;
import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.ejb.entidades.MpDireccionamientoEntregados;
import com.saviasaludeps.savia.ejb.entidades.MpDireccionamientos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionHistoricos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionInsumos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionProgramadas;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import com.saviasaludeps.savia.ejb.entidades.MpProgramadaEntregas;
import com.saviasaludeps.savia.ejb.utilidades.RemotoEJB;
import com.saviasaludeps.savia.negocio.mipres.MpInsumoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpMedicamentoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpTecnologiaRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.MpPrescripcionProgramadaRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.PrescripcionWsRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.ReporteEntregasWsRemoto;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.reporteentrega.ReporteEntrega;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yjimenez
 */
@Stateless
@Remote(ReporteEntregasWsRemoto.class)
public class ReporteEntregasWsServicio extends GenericoServicio implements ReporteEntregasWsRemoto {

    public static final int ID_ESTADO_DIRECCIONADO = 1;
    public static final int ID_ESTADO_NO_DIRECCIONADO = 2;
    public static final int ID_ESTADO_PENDIENTE = 3;
    public static final int ID_ESTADO_ANULADO_DIRECCIONADO = 4;
    public static final int ID_ESTADO_ANULADO_NO_DIRECCIONADO = 5;
    public static final int ID_ESTADO_PROGRAMADO = 6;
    public static final int ID_ESTADO_ENTREGADO = 7;
    public static final int ID_ESTADO_REPORTADO = 8;

    private PrescripcionWsRemoto getPrescripcionWsRemoto() throws Exception {
        return (PrescripcionWsRemoto) RemotoEJB.getEJBRemoto("PrescripcionWsServicio", PrescripcionWsRemoto.class
                .getName());
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

    private MpPrescripcionProgramadaRemoto getMpPrescripcionProgramadaRemotoRemoto() throws Exception {
        return (MpPrescripcionProgramadaRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionProgramadaServicio", MpPrescripcionProgramadaRemoto.class.getName());
    }
    @Override
    public MpConsumoFallo registrarReporteEntregas(
            ReporteEntrega reporte, 
            Map<String, MpDireccionamiento> listaDireccionamiento)
            throws Exception {
        MpConsumoFallo fallo = new MpConsumoFallo();
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        MpDireccionamientoEntregados entrega = new MpDireccionamientoEntregados();
        MpDireccionamiento direccionamiento = listaDireccionamiento.getOrDefault(reporte.getNoPrescripcion() + "||" + reporte.getConTec(), null);
        if (direccionamiento != null) {
            entrega.setId(Integer.parseInt(reporte.getID()));
            MpDireccionamientos dir = new MpDireccionamientos();
            dir.setId(direccionamiento.getId());
//            entrega.setMpDireccionamientosId(dir);
            entrega.setNumeroEntrega(Integer.parseInt(reporte.getNoEntrega()));
            entrega.setIdReporteEntrega(Integer.parseInt(reporte.getIDReporteEntrega()));
            entrega.setEstadoEntrega(Integer.parseInt(reporte.getEstadoEntrega()));
            entrega.setCausaNoEntrega(Integer.parseInt(reporte.getCausaNoEntrega()));
            entrega.setValorTotal(new BigDecimal(reporte.getValorEntregado()));
            entrega.setCodTecEntregado(reporte.getCodTecEntregado());
            entrega.setCantidadEntrega(Integer.parseInt(reporte.getCantTotEntregada()));
            entrega.setNumeroLote(reporte.getNoLote());
            entrega.setFechaEntrega(dateTimeFormat.parse(reporte.getFecEntrega()));
            entrega.setFechaReporteFactura(dateTimeFormat.parse(reporte.getFecRepEntrega()));
            entrega.setFechaAnulacion(dateTimeFormat.parse(reporte.getFecAnulacion()));
            
        }
        return fallo;
    }

    @Override
    public MpConsumoFallo actualizarPrescripcionesReporteEntregasWs(ReporteEntrega reporte, Map<String, MpPrescripcionMedicamento> listaMedicamentos, Map<String, MpPrescripcionTecnologia> listaTecnologias, Map<String, MpPrescripcionInsumo> listaInsumos) throws Exception {
        MpConsumoFallo fallo = new MpConsumoFallo();
        switch (reporte.getTipoTec()) {
            case "M": //MEDICAMENTO
                fallo = procesarMedicamentos(reporte, "1", listaMedicamentos);
                break;
            case "P": //procedimiento
                fallo = procesarTecnologias("2", reporte, listaTecnologias);
                break;
            case "D": //dispositivo medico
                fallo = procesarInsumos(reporte, "3", listaInsumos);
                break;
            case "N": //PRODUCTO NUTRICIONAL
                fallo = procesarMedicamentos(reporte, "4", listaMedicamentos);
                break;
            case "S": //servicio complementario
                fallo = procesarInsumos(reporte, "5", listaInsumos);
                break;
        }
        return fallo;
    }

    //PROCESOS
    private MpConsumoFallo procesarMedicamentos(ReporteEntrega entregaDto, String tipoTecnologia, Map<String, MpPrescripcionMedicamento> listaMedicamentos) {
        MpPrescripcionMedicamento medicamento;
        MpConsumoFallo fallo = new MpConsumoFallo();
        fallo.setEstado((short) 0);
        try {
            medicamento = consultarMedicamentoEnLista(entregaDto.getNoPrescripcion(), entregaDto.getConTec(), tipoTecnologia, listaMedicamentos);
            if (medicamento != null) {
                medicamento.setEstado(ID_ESTADO_ENTREGADO);
                medicamento.setUsuarioModifica("mipres sincroniza");
                medicamento.setTerminalModifica("127.0.0.1");
                medicamento.setFechaHoraModifica(new Date());
                medicamento.setCantidadTotalEntrega(medicamento.getCantidadTotalFormulada());
                if (medicamento.getCantidadTotalEntrega() == null) {
                    medicamento.setCantidadTotalEntrega(BigDecimal.ZERO);
                }
                if (medicamento.getCantidadTotalFormulada() == null) {
                    medicamento.setCantidadTotalFormulada(BigDecimal.ZERO);
                }
                if (entregaDto.getCantTotEntregada() != null && !entregaDto.getCantTotEntregada().equals("")
                        && (entregaDto.getFecAnulacion() == null || "".equals(entregaDto.getFecAnulacion()))) {
                    try {
                        medicamento.setEntregados(new BigDecimal(entregaDto.getCantTotEntregada()).add(medicamento.getEntregados()));
                        medicamento.setPendientes(medicamento.getPendientes().subtract(new BigDecimal(entregaDto.getCantTotEntregada())));
                    } catch (Exception ex) {

                    }
                }
                getMpMedicamentoRemoto().actualizarEstadoCantidad(medicamento);
                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                historico.setMpPrescripcionesId(new MpPrescripciones(medicamento.getMpPrescripcion().getId()));
                historico.setIdPrescripcionTecnologia(medicamento.getId());
                historico.setTipoTecnologia(medicamento.getTipoTecnologia());
                historico.setEstado(medicamento.getEstado());
                historico.setUsuarioCrea("mipres sincroniza");
                historico.setTerminalCrea("127.0.0.1");
                historico.setFechaHoraCrea(new Date());
                insertarMpPrescripcionHistoricos(historico);
                //se inserta en tabla MpProgramadaEntregas
                MpProgramadaEntregas programadaEntrega = new MpProgramadaEntregas();
                programadaEntrega.setIdReporteEntrega(entregaDto.getIDReporteEntrega());
                programadaEntrega.setMpPrescripcionesId(new MpPrescripciones(medicamento.getMpPrescripcion().getId()));
                programadaEntrega.setMpPrescripcionMedicamentosId(new MpPrescripcionMedicamentos(medicamento.getId()));
                try {
                    programadaEntrega.setCantidad(new BigDecimal(entregaDto.getCantTotEntregada()));
                } catch (Exception ex) {
                    programadaEntrega.setCantidad(BigDecimal.ZERO);
                }
                if (entregaDto.getNoEntrega() != null
                        && entregaDto.getNoEntrega().matches("[0-9]+")) {
                    programadaEntrega.setNumeroEntrega(Integer.parseInt(entregaDto.getNoEntrega()));
                }
                if (entregaDto.getCantTotEntregada() != null && entregaDto.getCantTotEntregada().equals("1")) {
                    programadaEntrega.setEntregaTotal(true);
                } else {
                    programadaEntrega.setEntregaTotal(false);
                }
                programadaEntrega.setCausaNoEntrega(entregaDto.getCausaNoEntrega());
                if (entregaDto.getEstadoEntrega() != null
                        && entregaDto.getEstadoEntrega().matches("[0-9]+")) {
                    programadaEntrega.setEstado(Integer.parseInt(entregaDto.getEstadoEntrega()));
                }
                if (entregaDto.getFecEntrega() != null && !entregaDto.getFecEntrega().equals("")) {
                    programadaEntrega.setFechaEntrega(new SimpleDateFormat("yyyy-MM-dd").parse(entregaDto.getFecEntrega()));
                }
                if (entregaDto.getFecAnulacion() != null && !entregaDto.getFecAnulacion().equals("")) {
                    programadaEntrega.setFechaAnulacion(new SimpleDateFormat("yyyy-MM-dd").parse(entregaDto.getFecAnulacion()));
                }
                int idProgramada = getMpPrescripcionProgramadaRemotoRemoto()
                        .consultarPorPrescripcionTecnologiaEntrega(
                                tipoTecnologia,
                                medicamento.getMpPrescripcion().getId(),
                                medicamento.getId(),
                                programadaEntrega.getNumeroEntrega());
                if (idProgramada > 0) {
                    programadaEntrega.setMpPrescripcionProgramadasId(new MpPrescripcionProgramadas(idProgramada));
                }
                programadaEntrega.setUsuarioCrea("mipres sincroniza");
                programadaEntrega.setTerminalCrea("127.0.0.1");
                programadaEntrega.setFechaHoraCrea(new Date());
                insertarMpProgramadaEntregas(programadaEntrega);
            } else {
                fallo.setEstado((short) 1);
                fallo.setFechaHoraFallo(new Date());
                fallo.setDescripcion("No se encontro prescripcion grabada ");
                Gson gson = new Gson();
                String jsonString = gson.toJson(entregaDto);
                //byte[] json = jsonString.getBytes();
                fallo.setJson(jsonString);
            }
        } catch (Exception ex) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error: " + ex.getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(entregaDto);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
        }
        return fallo;
    }

    private MpConsumoFallo procesarTecnologias(String tipoTecnologia, ReporteEntrega entregaDto, Map<String, MpPrescripcionTecnologia> listaTecnologias) {
        MpPrescripcionTecnologia tecnologia = null;
        MpConsumoFallo fallo = new MpConsumoFallo();
        fallo.setEstado((short) 0);
        try {
            tecnologia = consultarTecnologiaEnLista(entregaDto.getNoPrescripcion(), entregaDto.getConTec(), listaTecnologias);
            if (tecnologia != null) {
                tecnologia.setEstado(ID_ESTADO_ENTREGADO);
                tecnologia.setUsuarioModifica("mipres sincroniza");
                tecnologia.setTerminalModifica("127.0.0.1");
                tecnologia.setFechaHoraModifica(new Date());
                if (entregaDto.getCantTotEntregada() != null && !entregaDto.getCantTotEntregada().equals("")
                        && (entregaDto.getFecAnulacion() == null || "".equals(entregaDto.getFecAnulacion()))) {
                    try {
                        //Si el pendiente esta vacio o es menor que 0 se toma la cantidad formulada como el pendiente total
                        if (tecnologia.getPendientes() == null
                                || (tecnologia.getPendientes().compareTo(BigDecimal.ZERO)) < 0
                                || (tecnologia.getPendientes().compareTo(BigDecimal.ZERO)) == 0) {
                            tecnologia.setPendientes(new BigDecimal(tecnologia.getCantidadFormulada()));
                        }
                        tecnologia.setEntregados(new BigDecimal(entregaDto.getCantTotEntregada()).add(tecnologia.getEntregados()));
                        tecnologia.setPendientes(tecnologia.getPendientes().subtract(new BigDecimal(entregaDto.getCantTotEntregada())));
                    } catch (Exception ex) {
//                        System.out.println("ERROR: "+ ex.toString());

                        fallo.setEstado((short) 1);
                        fallo.setFechaHoraFallo(new Date());
                        fallo.setDescripcion("Error: " + ex.getMessage());
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(entregaDto);
                        //byte[] json = jsonString.getBytes();
                        fallo.setJson(jsonString);
                        return fallo;
                    }
                }
                getMpTecnologiaRemoto().actualizarEstadoCantidad(tecnologia);
                //se construye e inserta objeto MpPrescripcionHistoricos
                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                historico.setMpPrescripcionesId(new MpPrescripciones(tecnologia.getMpPrescripcion().getId()));
                historico.setIdPrescripcionTecnologia(tecnologia.getId());
                historico.setTipoTecnologia(tecnologia.getTipoTecnologia());
                historico.setEstado(tecnologia.getEstado());
                historico.setUsuarioCrea("mipres sincroniza");
                historico.setTerminalCrea("127.0.0.1");
                historico.setFechaHoraCrea(new Date());
                insertarMpPrescripcionHistoricos(historico);
                MpProgramadaEntregas programadaEntrega = new MpProgramadaEntregas();
                programadaEntrega.setIdReporteEntrega(entregaDto.getIDReporteEntrega());
                programadaEntrega.setMpPrescripcionesId(new MpPrescripciones(tecnologia.getMpPrescripcion().getId()));
                programadaEntrega.setMpPrescripcionTecnologiasId(new MpPrescripcionTecnologias(tecnologia.getId()));
                if (entregaDto.getNoEntrega() != null
                        && entregaDto.getNoEntrega().matches("[0-9]+")) {
                    programadaEntrega.setNumeroEntrega(Integer.parseInt(entregaDto.getNoEntrega()));
                }
                try {
                    programadaEntrega.setCantidad(new BigDecimal(entregaDto.getCantTotEntregada()));
                } catch (Exception ex) {
                    programadaEntrega.setCantidad(BigDecimal.ZERO);
                }
                if (entregaDto.getCantTotEntregada() != null && entregaDto.getCantTotEntregada().equals("1")) {
                    programadaEntrega.setEntregaTotal(true);
                } else {
                    programadaEntrega.setEntregaTotal(false);
                }
                programadaEntrega.setCausaNoEntrega(entregaDto.getCausaNoEntrega());
                if (entregaDto.getEstadoEntrega() != null
                        && entregaDto.getEstadoEntrega().matches("[0-9]+")) {
                    programadaEntrega.setEstado(Integer.parseInt(entregaDto.getEstadoEntrega()));
                }
                if (entregaDto.getFecEntrega() != null && !entregaDto.getFecEntrega().equals("")) {
                    if (entregaDto.getCantTotEntregada() != null && !entregaDto.getCantTotEntregada().equals("")
                            && (entregaDto.getFecAnulacion() == null || "".equals(entregaDto.getFecAnulacion()))) {
                        try {
                            tecnologia.setEntregados(new BigDecimal(entregaDto.getCantTotEntregada()).add(tecnologia.getEntregados()));
                            //Si el pendiente esta en 0 o nulo se toma la cantidad formulada
                            if (tecnologia.getCantidadFormulada() != null && tecnologia.getCantidadFormulada() > 0) {
                                tecnologia.setPendientes(new BigDecimal(tecnologia.getCantidadFormulada()));
                            }
                            tecnologia.setPendientes(tecnologia.getPendientes().subtract(tecnologia.getEntregados()));
                        } catch (Exception ex) {

                        }
                    }
                    programadaEntrega.setFechaEntrega(new SimpleDateFormat("yyyy-MM-dd").parse(entregaDto.getFecEntrega()));
                }
                if (entregaDto.getFecAnulacion() != null && !entregaDto.getFecAnulacion().equals("")) {
                    programadaEntrega.setFechaAnulacion(new SimpleDateFormat("yyyy-MM-dd").parse(entregaDto.getFecAnulacion()));
                }
                int idProgramada = getMpPrescripcionProgramadaRemotoRemoto()
                        .consultarPorPrescripcionTecnologiaEntrega(
                                tipoTecnologia,
                                tecnologia.getMpPrescripcion().getId(),
                                tecnologia.getId(),
                                programadaEntrega.getNumeroEntrega());
                if (idProgramada > 0) {
                    programadaEntrega.setMpPrescripcionProgramadasId(new MpPrescripcionProgramadas(idProgramada));
                }
                programadaEntrega.setUsuarioCrea("mipres sincroniza");
                programadaEntrega.setTerminalCrea("127.0.0.1");
                programadaEntrega.setFechaHoraCrea(new Date());
                insertarMpProgramadaEntregas(programadaEntrega);
            } else {
                fallo.setEstado((short) 1);
                fallo.setFechaHoraFallo(new Date());
                fallo.setDescripcion("No se encontro prescripcion grabada ");
                Gson gson = new Gson();
                String jsonString = gson.toJson(entregaDto);
                //byte[] json = jsonString.getBytes();
                fallo.setJson(jsonString);
            }
        } catch (Exception ex) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error: " + ex.getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(entregaDto);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
        }
        return fallo;
    }

    private MpConsumoFallo procesarInsumos(ReporteEntrega entregaDto, String tipoTecnologia, Map<String, MpPrescripcionInsumo> listaInsumos) {
        MpPrescripcionInsumo insumo;
        MpConsumoFallo fallo = new MpConsumoFallo();
        fallo.setEstado((short) 0);
        try {
            insumo = consultarInsumoEnLista(entregaDto.getNoPrescripcion(), entregaDto.getConTec(), tipoTecnologia, listaInsumos);
            if (insumo != null) {
                insumo.setEstado(ID_ESTADO_ENTREGADO);
                insumo.setUsuarioModifica("mipres sincroniza");
                insumo.setTerminalModifica("127.0.0.1");
                insumo.setFechaHoraModifica(new Date());
                if (entregaDto.getCantTotEntregada() != null && !entregaDto.getCantTotEntregada().equals("")
                        && (entregaDto.getFecAnulacion() == null || "".equals(entregaDto.getFecAnulacion()))) {
                    try {
                        insumo.setEntregados(new BigDecimal(entregaDto.getCantTotEntregada()).add(insumo.getEntregados()));
                        insumo.setPendiente(insumo.getPendiente().subtract(new BigDecimal(entregaDto.getCantTotEntregada())));
                    } catch (Exception ex) {

                    }
                }
                getMpInsumoRemoto().actualizarEstadoCantidad(insumo);
                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                historico.setMpPrescripcionesId(new MpPrescripciones(insumo.getMpPrescripcion().getId()));
                historico.setIdPrescripcionTecnologia(insumo.getId());
                historico.setTipoTecnologia(insumo.getTipoTecnologia());
                historico.setEstado(insumo.getEstado());
                historico.setUsuarioCrea("mipres sincroniza");
                historico.setTerminalCrea("127.0.0.1");
                historico.setFechaHoraCrea(new Date());
                insertarMpPrescripcionHistoricos(historico);
                MpProgramadaEntregas programadaEntrega = new MpProgramadaEntregas();
                programadaEntrega.setIdReporteEntrega(entregaDto.getIDReporteEntrega());
                programadaEntrega.setMpPrescripcionesId(new MpPrescripciones(insumo.getMpPrescripcion().getId()));
                programadaEntrega.setMpPrescripcionInsumosId(new MpPrescripcionInsumos(insumo.getId()));
                if (entregaDto.getNoEntrega() != null
                        && entregaDto.getNoEntrega().matches("[0-9]+")) {
                    programadaEntrega.setNumeroEntrega(Integer.parseInt(entregaDto.getNoEntrega()));
                }
                try {
                    programadaEntrega.setCantidad(new BigDecimal(entregaDto.getCantTotEntregada()));
                } catch (Exception ex) {
                    programadaEntrega.setCantidad(BigDecimal.ZERO);
                }
                if (entregaDto.getCantTotEntregada() != null && entregaDto.getCantTotEntregada().equals("1")) {
                    programadaEntrega.setEntregaTotal(true);
                } else {
                    programadaEntrega.setEntregaTotal(false);
                }
                programadaEntrega.setCausaNoEntrega(entregaDto.getCausaNoEntrega());
                if (entregaDto.getEstadoEntrega() != null
                        && entregaDto.getEstadoEntrega().matches("[0-9]+")) {
                    programadaEntrega.setEstado(Integer.parseInt(entregaDto.getEstadoEntrega()));
                }
                if (entregaDto.getFecEntrega() != null && !entregaDto.getFecEntrega().equals("")) {
                    programadaEntrega.setFechaEntrega(new SimpleDateFormat("yyyy-MM-dd").parse(entregaDto.getFecEntrega()));
                }
                if (entregaDto.getFecAnulacion() != null && !entregaDto.getFecAnulacion().equals("")) {
                    programadaEntrega.setFechaAnulacion(new SimpleDateFormat("yyyy-MM-dd").parse(entregaDto.getFecAnulacion()));
                }
                int idProgramada = getMpPrescripcionProgramadaRemotoRemoto()
                        .consultarPorPrescripcionTecnologiaEntrega(
                                tipoTecnologia,
                                insumo.getMpPrescripcion().getId(),
                                insumo.getId(),
                                programadaEntrega.getNumeroEntrega());
                if (idProgramada > 0) {
                    programadaEntrega.setMpPrescripcionProgramadasId(new MpPrescripcionProgramadas(idProgramada));
                }
                programadaEntrega.setUsuarioCrea("mipres sincroniza");
                programadaEntrega.setTerminalCrea("127.0.0.1");
                programadaEntrega.setFechaHoraCrea(new Date());
                insertarMpProgramadaEntregas(programadaEntrega);
            } else {
                fallo.setEstado((short) 1);
                fallo.setFechaHoraFallo(new Date());
                fallo.setDescripcion("No se encontro prescripcion grabada ");
                Gson gson = new Gson();
                String jsonString = gson.toJson(entregaDto);
                //byte[] json = jsonString.getBytes();
                fallo.setJson(jsonString);
            }
        } catch (Exception ex) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error: " + ex.getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(entregaDto);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
        }
        return fallo;
    }

    //CONSULTAS
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

    //INSERT HISTORICOS
    private int insertarMpProgramadaEntregas(MpProgramadaEntregas programadaEntrega) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(programadaEntrega).getId();
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
