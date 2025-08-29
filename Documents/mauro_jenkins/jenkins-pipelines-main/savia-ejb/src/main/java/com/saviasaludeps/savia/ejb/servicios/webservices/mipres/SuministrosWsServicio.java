package com.saviasaludeps.savia.ejb.servicios.webservices.mipres;

import com.google.gson.Gson;
import com.saviasaludeps.savia.dominio.mipres.MpConsumo;
import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.dominio.mipres.MpProgramadaEntrega;
import com.saviasaludeps.savia.ejb.entidades.MpEntregaSuministros;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionHistoricos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionInsumos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import com.saviasaludeps.savia.ejb.entidades.MpProgramadaEntregas;
import com.saviasaludeps.savia.ejb.utilidades.RemotoEJB;
import com.saviasaludeps.savia.negocio.mipres.MpInsumoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpMedicamentoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpTecnologiaRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.MpPrescripcionProgramadaRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.PrescripcionWsRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.SuministrosWsRemoto;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.suministro.Suministro;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Stateless
@Remote(SuministrosWsRemoto.class)
public class SuministrosWsServicio extends GenericoServicio implements SuministrosWsRemoto {
    
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
    
    private PrescripcionWsRemoto getPrescripcionWsRemoto() throws Exception {
        return (PrescripcionWsRemoto) RemotoEJB.getEJBRemoto("PrescripcionWsServicio", PrescripcionWsRemoto.class
                .getName());
    }

    public static final int ID_ESTADO_DIRECCIONADO = 1;
    public static final int ID_ESTADO_NO_DIRECCIONADO = 2;
    public static final int ID_ESTADO_PENDIENTE = 3;
    public static final int ID_ESTADO_ANULADO_DIRECCIONADO = 4;
    public static final int ID_ESTADO_ANULADO_NO_DIRECCIONADO = 5;
    public static final int ID_ESTADO_PROGRAMADO = 6;
    public static final int ID_ESTADO_ENTREGADO = 7;
    public static final int ID_ESTADO_REPORTADO = 8;

    @Override
    public MpConsumoFallo actualizarPrescripcionesSuministrosWs(
            Suministro suministro,
            Map<String, MpPrescripcionMedicamento> listaMedicamentos,
            Map<String, MpPrescripcionTecnologia> listaTecnologias,
            Map<String, MpPrescripcionInsumo> listaInsumos,
            Map<String, MpProgramadaEntrega> listaEntregaMedicamentos,
            Map<String, MpProgramadaEntrega> listaEntregaTecnologias,
            Map<String, MpProgramadaEntrega> listaEntregaInsumos
    ) throws Exception {
        MpConsumoFallo mpFallo = new MpConsumoFallo();
        mpFallo.setEstado((short) 0);
        switch (suministro.getTipoTec()) {
            case "M": //MEDICAMENTO
                mpFallo = procesarMedicamentos(suministro, "1", listaMedicamentos, listaEntregaMedicamentos);
                break;
            case "P": //procedimiento
                mpFallo = procesarTecnologias(suministro, listaTecnologias, listaEntregaTecnologias);
                break;
            case "D": //dispositivo medico
                mpFallo = procesarInsumos(suministro, "3", listaInsumos, listaEntregaInsumos);
                break;
            case "N": //PRODUCTO NUTRICIONAL
                mpFallo = procesarMedicamentos(suministro, "4", listaMedicamentos, listaEntregaMedicamentos);
                break;
            case "S": //servicio complementario
                mpFallo = procesarInsumos(suministro, "5", listaInsumos, listaEntregaInsumos);
                break;
        }
        return mpFallo;
    }

    //PROCESOS POR SERVICIO
    private MpConsumoFallo procesarMedicamentos(Suministro suministroDto, String tipoTecnologia, Map<String, MpPrescripcionMedicamento> listaMedicamentos, Map<String, MpProgramadaEntrega> listaEntregas) {
        MpConsumoFallo fallo = new MpConsumoFallo();
        fallo.setEstado((short) 0);
        MpPrescripcionMedicamento medicamento = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            medicamento = consultarMedicamentoEnLista(suministroDto.getNoPrescripcion(), suministroDto.getConTec().toString(), tipoTecnologia, listaMedicamentos);
            if (medicamento != null) {
                medicamento.setEstado(ID_ESTADO_REPORTADO);
                medicamento.setUsuarioModifica("mipres sincroniza");
                medicamento.setTerminalModifica("127.0.0.1");
                medicamento.setFechaHoraModifica(new Date());
                getMpMedicamentoRemoto().actualizarEstado(medicamento);
                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                historico.setMpPrescripcionesId(new MpPrescripciones(medicamento.getMpPrescripcion().getId()));
                historico.setIdPrescripcionTecnologia(medicamento.getId());
                historico.setTipoTecnologia(medicamento.getTipoTecnologia());
                historico.setEstado(medicamento.getEstado());
                historico.setUsuarioCrea("mipres sincroniza");
                historico.setTerminalCrea("127.0.0.1");
                historico.setFechaHoraCrea(new Date());
                insertarMpPrescripcionHistoricos(historico);
                MpProgramadaEntrega entrega = buscarMedicamentoEntregaEnLista(medicamento.getId(), suministroDto.getNoEntrega(), listaEntregas);
                if (entrega != null) {
                    MpEntregaSuministros entregaSuministro = new MpEntregaSuministros();
                    entregaSuministro.setIdSuministro(suministroDto.getIDSuministro().toString());
//                    entregaSuministro.setMpProgramadaEntregasId(new MpProgramadaEntregas(entrega.getId()));
//                    entregaSuministro.setNumeroEntrega(suministroDto.getNoEntrega());
                    if (suministroDto.getUltEntrega() != null && suministroDto.getUltEntrega() == 1) {
                        entregaSuministro.setUltimaEntrega(Boolean.TRUE);
                    } else {
                        entregaSuministro.setUltimaEntrega(Boolean.FALSE);
                    }
//                    if (suministroDto.getEntregaCompleta() != null && suministroDto.getEntregaCompleta() == 1) {
//                        entregaSuministro.setEntregaCompleta(Boolean.TRUE);
//                    } else {
//                        entregaSuministro.setEntregaCompleta(Boolean.FALSE);
//                    }
//                    entregaSuministro.setCausaNoEntrega(suministroDto.getCausaNoEntrega());
//                    entregaSuministro.setEstado(suministroDto.getEstSuministro());
//                    if (suministroDto.getFecSuministro() != null && !suministroDto.getFecSuministro().equals("")) {
//                        Date fechaSuministro = formato.parse(suministroDto.getFecSuministro());
//                        entregaSuministro.setFechaSuministro(fechaSuministro);
//                    }
//                    if (suministroDto.getFecAnulacion() != null && !suministroDto.getFecAnulacion().equals("")) {
//                        Date fechaAnulacion = formato.parse(suministroDto.getFecAnulacion());
//                        entregaSuministro.setFechaAnulacion(fechaAnulacion);
//                    }
                    entregaSuministro.setUsuarioCrea("mipres sincroniza");
                    entregaSuministro.setTerminalCrea("127.0.0.1");
                    entregaSuministro.setFechaHoraCrea(new Date());
                    insertarMpEntregaSuministros(entregaSuministro);
                }
            } else {
                fallo.setEstado((short) 1);
                fallo.setFechaHoraFallo(new Date());
                fallo.setDescripcion("No se encontro prescripcion grabada ");
                Gson gson = new Gson();
                String jsonString = gson.toJson(suministroDto);
                //byte[] json = jsonString.getBytes();
                fallo.setJson(jsonString);
            }
        } catch (Exception ex) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error: " + ex.getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(suministroDto);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
        }
        return fallo;
    }

    private MpConsumoFallo procesarTecnologias(Suministro suministroDto, Map<String, MpPrescripcionTecnologia> listaTecnologias, Map<String, MpProgramadaEntrega> listaEntregas) {
        MpConsumoFallo fallo = new MpConsumoFallo();
        fallo.setEstado((short) 0);
        MpPrescripcionTecnologia tecnologia;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            tecnologia = consultarTecnologiaEnLista(suministroDto.getNoPrescripcion(), suministroDto.getConTec().toString(), listaTecnologias);
            if (tecnologia != null) {
                tecnologia.setEstado(ID_ESTADO_REPORTADO);
                tecnologia.setUsuarioModifica("mipres sincroniza");
                tecnologia.setTerminalModifica("127.0.0.1");
                tecnologia.setFechaHoraModifica(new Date());
                getMpTecnologiaRemoto().actualizarEstado(tecnologia);
                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                historico.setMpPrescripcionesId(new MpPrescripciones(tecnologia.getMpPrescripcion().getId()));
                historico.setIdPrescripcionTecnologia(tecnologia.getId());
                historico.setTipoTecnologia(tecnologia.getTipoTecnologia());
                historico.setEstado(tecnologia.getEstado());
                historico.setUsuarioCrea("mipres sincroniza");
                historico.setTerminalCrea("127.0.0.1");
                historico.setFechaHoraCrea(new Date());
                insertarMpPrescripcionHistoricos(historico);
                MpProgramadaEntrega entrega = buscarTecnologiaEntregaEnLista(tecnologia.getId(), suministroDto.getNoEntrega(), listaEntregas);
                if (entrega != null) {
                    MpEntregaSuministros entregaSuministro = new MpEntregaSuministros();
                    entregaSuministro.setIdSuministro(suministroDto.getIDSuministro().toString());
//                    entregaSuministro.setMpProgramadaEntregasId(new MpProgramadaEntregas(entrega.getId()));
//                    entregaSuministro.setNumeroEntrega(suministroDto.getNoEntrega());
                    if (suministroDto.getUltEntrega() != null && suministroDto.getUltEntrega() == 1) {
                        entregaSuministro.setUltimaEntrega(Boolean.TRUE);
                    } else {
                        entregaSuministro.setUltimaEntrega(Boolean.FALSE);
                    }
//                    if (suministroDto.getEntregaCompleta() != null && suministroDto.getEntregaCompleta() == 1) {
//                        entregaSuministro.setEntregaCompleta(Boolean.TRUE);
//                    } else {
//                        entregaSuministro.setEntregaCompleta(Boolean.FALSE);
//                    }
//                    entregaSuministro.setCausaNoEntrega(suministroDto.getCausaNoEntrega());
//                    entregaSuministro.setEstado(suministroDto.getEstSuministro());
//                    if (suministroDto.getFecSuministro() != null && !suministroDto.getFecSuministro().equals("")) {
//                        Date fechaSuministro = formato.parse(suministroDto.getFecSuministro());
//                        entregaSuministro.setFechaSuministro(fechaSuministro);
//                    }
//                    if (suministroDto.getFecAnulacion() != null && !suministroDto.getFecAnulacion().equals("")) {
//                        Date fechaAnulacion = formato.parse(suministroDto.getFecAnulacion());
//                        entregaSuministro.setFechaAnulacion(fechaAnulacion);
//                    }
                    entregaSuministro.setUsuarioCrea("mipres sincroniza");
                    entregaSuministro.setTerminalCrea("127.0.0.1");
                    entregaSuministro.setFechaHoraCrea(new Date());
                    insertarMpEntregaSuministros(entregaSuministro);
                }
            } else {
                fallo.setEstado((short) 1);
                fallo.setFechaHoraFallo(new Date());
                fallo.setDescripcion("No se encontro prescripcion grabada ");
                Gson gson = new Gson();
                String jsonString = gson.toJson(suministroDto);
                //byte[] json = jsonString.getBytes();
                fallo.setJson(jsonString);
            }
        } catch (Exception ex) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error: " + ex.getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(suministroDto);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
        }
        return fallo;
    }

    private MpConsumoFallo procesarInsumos(Suministro suministroDto, String tipoTecnologia, Map<String, MpPrescripcionInsumo> listaInsumos, Map<String, MpProgramadaEntrega> listaEntregas) {
        MpConsumoFallo fallo = new MpConsumoFallo();
        fallo.setEstado((short) 0);
        MpPrescripcionInsumo insumo;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            insumo = consultarInsumoEnLista(suministroDto.getNoPrescripcion(), suministroDto.getConTec().toString(), tipoTecnologia, listaInsumos);
            if (insumo != null) {
                insumo.setEstado(ID_ESTADO_REPORTADO);
                insumo.setUsuarioModifica("mipres sincroniza");
                insumo.setTerminalModifica("127.0.0.1");
                insumo.setFechaHoraModifica(new Date());
                getMpInsumoRemoto().actualizarEstado(insumo);
                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                historico.setMpPrescripcionesId(new MpPrescripciones(insumo.getMpPrescripcion().getId()));
                historico.setIdPrescripcionTecnologia(insumo.getId());
                historico.setTipoTecnologia(insumo.getTipoTecnologia());
                historico.setEstado(insumo.getEstado());
                historico.setUsuarioCrea("mipres sincroniza");
                historico.setTerminalCrea("127.0.0.1");
                historico.setFechaHoraCrea(new Date());
                insertarMpPrescripcionHistoricos(historico);
                //se inserta en tabla MpEntregaSuministros
                MpProgramadaEntrega entrega = buscarInsumoEntregaEnLista(insumo.getId(), suministroDto.getNoEntrega(), listaEntregas);
                if (entrega != null) {
                    MpEntregaSuministros entregaSuministro = new MpEntregaSuministros();
                    entregaSuministro.setIdSuministro(suministroDto.getIDSuministro().toString());
//                    entregaSuministro.setMpProgramadaEntregasId(new MpProgramadaEntregas(entrega.getId()));
//                    entregaSuministro.setNumeroEntrega(suministroDto.getNoEntrega());
                    if (suministroDto.getUltEntrega() != null && suministroDto.getUltEntrega() == 1) {
                        entregaSuministro.setUltimaEntrega(Boolean.TRUE);
                    } else {
                        entregaSuministro.setUltimaEntrega(Boolean.FALSE);
                    }
//                    if (suministroDto.getEntregaCompleta() != null && suministroDto.getEntregaCompleta() == 1) {
//                        entregaSuministro.setEntregaCompleta(Boolean.TRUE);
//                    } else {
//                        entregaSuministro.setEntregaCompleta(Boolean.FALSE);
//                    }
//                    entregaSuministro.setCausaNoEntrega(suministroDto.getCausaNoEntrega());
//                    entregaSuministro.setEstado(suministroDto.getEstSuministro());
                    if (suministroDto.getFecSuministro() != null && !suministroDto.getFecSuministro().equals("")) {
                        Date fechaSuministro = formato.parse(suministroDto.getFecSuministro());
                        entregaSuministro.setFechaHoraSuminisro(fechaSuministro);
                    }
                    if (suministroDto.getFecAnulacion() != null && !suministroDto.getFecAnulacion().equals("")) {
                        Date fechaAnulacion = formato.parse(suministroDto.getFecAnulacion());
                        entregaSuministro.setFechaHoraAnula(fechaAnulacion);
                    }
                    entregaSuministro.setUsuarioCrea("mipres sincroniza");
                    entregaSuministro.setTerminalCrea("127.0.0.1");
                    entregaSuministro.setFechaHoraCrea(new Date());
                    insertarMpEntregaSuministros(entregaSuministro);
                }
            } else {
                fallo.setEstado((short) 1);
                fallo.setFechaHoraFallo(new Date());
                fallo.setDescripcion("No se encontro prescripcion grabada ");
                Gson gson = new Gson();
                String jsonString = gson.toJson(suministroDto);
                //byte[] json = jsonString.getBytes();
                fallo.setJson(jsonString);
            }
        } catch (Exception ex) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error: " + ex.getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(suministroDto);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
        }
        return fallo;
    }

    //UPDATES AND INSERTS
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

    public void actualizarMpPrescripcionMedicamentos(MpPrescripcionMedicamentos obj) throws Exception {
        try {
            getEntityManager().merge(obj);
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    public void actualizarMpPrescripcionTecnologias(MpPrescripcionTecnologias obj) throws Exception {
        try {
            getEntityManager().merge(obj);
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    public void actualizarMpPrescripcionInsumos(MpPrescripcionInsumos obj) throws Exception {
        try {
            getEntityManager().merge(obj);
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    private int insertarMpEntregaSuministros(MpEntregaSuministros entregaSuministro) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(entregaSuministro).getId();

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

    //CONSULTAS
    private MpPrescripcionTecnologia consultarTecnologiaEnLista(String noPrescripcion, String consecutivo, Map<String, MpPrescripcionTecnologia> listaTecnologias) {
        MpPrescripcionTecnologia tecnologia = listaTecnologias.getOrDefault(noPrescripcion + "||" + consecutivo, null);
        if (tecnologia != null) {
            if (tecnologia.getMpPrescripcion().getNumeroPrescripcion().equals(noPrescripcion)
                    && tecnologia.getConsecutivoOrden() == Integer.parseInt(consecutivo)) {
                return tecnologia;
            }
        }
        return null;
    }

    private MpPrescripcionInsumo consultarInsumoEnLista(String noPrescripcion, String consecutivo, String tipoTecnologia, Map<String, MpPrescripcionInsumo> listaInsumos) {
        MpPrescripcionInsumo insumo = listaInsumos.getOrDefault(noPrescripcion + "||" + consecutivo, null);
        if (insumo != null) {
            if (insumo.getMpPrescripcion().getNumeroPrescripcion().equals(noPrescripcion)
                    && Integer.toString(insumo.getTipoTecnologia()).equals(tipoTecnologia)
                    && insumo.getConsecutivoOrden() == Integer.parseInt(consecutivo)) {
                return insumo;
            }
        }
        return null;
    }

    private MpPrescripcionMedicamento consultarMedicamentoEnLista(String noPrescripcion, String consecutivo, String tipoTecnologia, Map<String, MpPrescripcionMedicamento> listaMedicamentos) {
        MpPrescripcionMedicamento medicamento = listaMedicamentos.getOrDefault(noPrescripcion + "||" + consecutivo, null);
        if (medicamento != null) {
            if (medicamento.getMpPrescripcion().getNumeroPrescripcion().equals(noPrescripcion)
                    && Integer.toString(medicamento.getTipoTecnologia()).equals(tipoTecnologia)
                    && medicamento.getConsecutivoOrden() == Integer.parseInt(consecutivo)) {
                return medicamento;
            }
        }

        return null;
    }

    //GETTER AND SETTERS

    private MpProgramadaEntrega buscarMedicamentoEntregaEnLista(int id, int numeroEntrega, Map<String, MpProgramadaEntrega> listaEntregas) {
        MpProgramadaEntrega entrega = listaEntregas.getOrDefault(numeroEntrega + "||" + id, null);
        if (entrega != null
                && numeroEntrega == entrega.getNumeroEntrega()
                && entrega.getMedicamento().getId() == id) {
            return entrega;
        }
        return null;
    }

    private MpProgramadaEntrega buscarInsumoEntregaEnLista(int id, int numeroEntrega, Map<String, MpProgramadaEntrega> listaEntregas) {
        MpProgramadaEntrega entrega = listaEntregas.getOrDefault(numeroEntrega + "||" + id, null);
        if (entrega != null
                && numeroEntrega == entrega.getNumeroEntrega()
                && entrega.getInsumo().getId() == id) {
            return entrega;
        }
        return null;
    }

    private MpProgramadaEntrega buscarTecnologiaEntregaEnLista(int id, int numeroEntrega, Map<String, MpProgramadaEntrega> listaEntregas) {
        MpProgramadaEntrega entrega = listaEntregas.getOrDefault(numeroEntrega + "||" + id, null);
        if (entrega != null
                && numeroEntrega == entrega.getNumeroEntrega()
                && entrega.getTecnologia().getId() == id) {
            return entrega;
        }
        return null;
    }

}
