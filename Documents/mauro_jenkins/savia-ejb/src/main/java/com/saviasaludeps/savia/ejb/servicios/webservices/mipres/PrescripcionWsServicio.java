package com.saviasaludeps.savia.ejb.servicios.webservices.mipres;

import com.google.gson.Gson;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.mipres.MpAfiliado;
import com.saviasaludeps.savia.dominio.mipres.MpAnuladaPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpConsumo;
import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamientoEntregado;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaFactura;
import com.saviasaludeps.savia.dominio.mipres.MpNoDireccionado;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionHistorico;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionRecobrante;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.dominio.mipres.MpProgramadaEntrega;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionales;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.entidades.MaDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MpAfiliados;
import com.saviasaludeps.savia.ejb.entidades.MpConsumos;
import com.saviasaludeps.savia.ejb.entidades.MpDireccionamientoEntregados;
import com.saviasaludeps.savia.ejb.entidades.MpDireccionamientos;
import com.saviasaludeps.savia.ejb.entidades.MpEntregaFacturas;
import com.saviasaludeps.savia.ejb.entidades.MpEntregaSuministros;
import com.saviasaludeps.savia.ejb.entidades.MpMedicamentoIndicacionesUnirs;
import com.saviasaludeps.savia.ejb.entidades.MpMedicamentoPrincipiosActivos;
import com.saviasaludeps.savia.ejb.entidades.MpNoDireccionados;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionAnulada;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionHistoricos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionInsumos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionRecobrantes;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import com.saviasaludeps.savia.negocio.webservices.mipres.PrescripcionWsRemoto;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento.Direccionamiento;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.nodireccionamiento.NoDireccionamiento;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.Dispositivo;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.Medicamento;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.Prescripcion;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.PrescripcionDetalle;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.Procedimiento;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.ProductoNutricional;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.ServicioComplementario;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.reporteentrega.ReporteEntrega;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.suministro.Suministro;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;
import com.saviasaludeps.savia.ejb.utilidades.RemotoEJB;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.IndicacionUNIRS;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.JuntaProfesional;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.PrescripcionRecobrante;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.PrincipioActivo;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.Tutela;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author yjimenez
 */
@Stateless
@Remote(PrescripcionWsRemoto.class)
public class PrescripcionWsServicio extends GenericoServicio implements PrescripcionWsRemoto {

    public final static int TIPO_MEDICAMENTO = 1;
    public final static int TIPO_TECNOLOGIA = 2;
    public final static int TIPO_DISPOSITIVO_MEDICO = 3;
    public final static int TIPO_PRODUCTO_NUTRICIONAL = 4;
    public final static int TIPO_SERVICIO_COMPLEMENTARIO = 5;

    public final static int ESTADO_CONSUMO_CONSULTANDO = 0;
    public final static int ESTADO_CONSUMO_GUARDANDO = 1;
    public final static int ESTADO_CONSUMO_EXITOSO = 2;
    public final static int ESTADO_CONSUMO_CONSULTANDO_ERROR = 3;
    public final static int ESTADO_CONSUMO_GUARDANDO_ERROR = 4;

    public final static int ESTADO_PRESCRIPCION_PENDIENTE = 0;
    public final static int ESTADO_PRESCRIPCION_DIRECCIONADA = 1;
    public final static int ESTADO_PRESCRIPCION_PROGRAMADA = 2;

    public final static int ESTADO_PRESCRIPCION_SERVICIO_DIRECCIONADA = 1;
    public final static int ESTADO_PRESCRIPCION_SERVICIO_NO_DIRECCIONADA = 2;
    public final static int ESTADO_PRESCRIPCION_SERVICIO_PENDIENTE = 3;

    public final static String TIPO_DOCUMENTO_NACIDOVIVO = "NV";
    public final static String TIPO_DOCUMENTO_CERTIFICADONACIDOVIVO = "CN";
    SimpleDateFormat formatoSimple = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatoFH = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    //INSERTS PRESCRIPCION Y SERVICIOS
    @Override
    public MpConsumoFallo procesarPrescripciones(
            Prescripcion prescripcionDto,
            Map<String, AsegAfiliado> listaAfiliados,
            Map<String, CntProfesional> listaProfesionales,
            HashMap<String, Maestro> hashTipoDocumentos,
            HashMap<String, Maestro> hashTipoDocumentoEmpresas,
            HashMap<String, Maestro> hashTipoDocumentoProfesionales) throws Exception {
        MpConsumoFallo fallo = new MpConsumoFallo();
        try {
            fallo.setEstado(MpConsumoFallo.ESTADO_CONSUMOFALLO_EXITOSO);
            //CREAR LA PRESCRIPCION
            MpPrescripciones prescripcion = castMpPrescripcion(
                    prescripcionDto.getPrescripcion(),
                    listaAfiliados,
                    listaProfesionales,
                    hashTipoDocumentos,
                    hashTipoDocumentoEmpresas,
                    hashTipoDocumentoProfesionales
            );
            if (prescripcion != null) {
                if (!prescripcion.getUsuarioCrea().equals("error")) {
                    prescripcion.setId(insertarMpPrescripcion(prescripcion));
                    //INSERTAR SERVICIO DE TECNOLOGIAS
                    if (prescripcionDto.getProcedimientos() != null && !prescripcionDto.getProcedimientos().isEmpty()) {
                        for (Procedimiento procedimiento : prescripcionDto.getProcedimientos()) {
                            MpPrescripcionTecnologias tecnologia = castMpPrescripcionTecnologias(prescripcion, procedimiento);
                            if (tecnologia != null) {
                                tecnologia.setId(insertarMpPrescripcionTecnologias(tecnologia));
                                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                                historico.setMpPrescripcionesId(prescripcion);
                                historico.setIdPrescripcionTecnologia(tecnologia.getId());
                                historico.setTipoTecnologia(tecnologia.getTipoTecnologia());
                                historico.setEstado(tecnologia.getEstado());
                                historico.setUsuarioCrea("mipres sincroniza");
                                historico.setTerminalCrea("127.0.0.1");
                                historico.setFechaHoraCrea(new Date());
                                insertarMpPrescripcionHistoricos(historico);
                            }
                        }
                    }
                    //INSERTAR SERVICIOS DE MEDICAMENTOS
                    if (prescripcionDto.getMedicamentos() != null && !prescripcionDto.getMedicamentos().isEmpty()) {
                        for (Medicamento medicamento : prescripcionDto.getMedicamentos()) {
                            MpPrescripcionMedicamentos medicamentos = castMpPrescripcionMedicamentos(prescripcion, medicamento);
                            if (medicamentos != null) {
                                medicamentos.setId(insertarMpPrescripcionMedicamentos(medicamentos));
                                if (medicamento.getPrincipiosActivos() != null) {
                                    for (PrincipioActivo dtoPrinAct : medicamento.getPrincipiosActivos()) {
                                        MpMedicamentoPrincipiosActivos activo = castMpMedicamentoPrincipioActivo(medicamentos, dtoPrinAct);
                                        activo.setId(insertarMpMedicamentoPrincipiosActivos(activo));
                                    }
                                }
                                if (medicamento.getPrincipiosActivos() != null) {
                                    for (IndicacionUNIRS dtoIndicacion : medicamento.getIndicacionesUNIRS()) {
                                        MpMedicamentoIndicacionesUnirs indicacion = castMpMedicamentoIndicacionesUnirs(medicamentos, dtoIndicacion);
                                        indicacion.setId(insertarMpMedicamentoIndicacionesUnirs(indicacion));
                                    }
                                }
                                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                                historico.setMpPrescripcionesId(prescripcion);
                                historico.setIdPrescripcionTecnologia(medicamentos.getId());
                                historico.setTipoTecnologia(medicamentos.getTipoTecnologia());
                                historico.setEstado(medicamentos.getEstado());
                                historico.setUsuarioCrea("mipres sincroniza");
                                historico.setTerminalCrea("127.0.0.1");
                                historico.setFechaHoraCrea(new Date());
                                insertarMpPrescripcionHistoricos(historico);
                            }
                        }
                    }
                    //INSERTAR SERVICIOS PRODUCTO NUTRICIONALES
                    if (prescripcionDto.getProductosnutricionales() != null && !prescripcionDto.getProductosnutricionales().isEmpty()) {
                        for (ProductoNutricional nutricional : prescripcionDto.getProductosnutricionales()) {
                            MpPrescripcionMedicamentos medicamentos = castMpPrescripcionMedicamentosNutricional(prescripcion, nutricional);
                            if (medicamentos != null) {
                                medicamentos.setId(insertarMpPrescripcionMedicamentos(medicamentos));
                                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                                historico.setMpPrescripcionesId(prescripcion);
                                historico.setIdPrescripcionTecnologia(medicamentos.getId());
                                historico.setTipoTecnologia(medicamentos.getTipoTecnologia());
                                historico.setEstado(medicamentos.getEstado());
                                historico.setUsuarioCrea("mipres sincroniza");
                                historico.setTerminalCrea("127.0.0.1");
                                historico.setFechaHoraCrea(new Date());
                                insertarMpPrescripcionHistoricos(historico);
                            }
                        }
                    }
                    //INSERTAR SERVICIOS INSUMOS DISPOSITIVOS
                    if (prescripcionDto.getDispositivos() != null && !prescripcionDto.getDispositivos().isEmpty()) {
                        for (Dispositivo dispositivo : prescripcionDto.getDispositivos()) {
                            MpPrescripcionInsumos insumo = castMpPrescripcionInsumos(prescripcion, dispositivo);
                            if (insumo != null) {
                                insumo.setId(insertarMpPrescripcionInsumos(insumo));
                                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                                historico.setMpPrescripcionesId(prescripcion);
                                historico.setIdPrescripcionTecnologia(insumo.getId());
                                historico.setTipoTecnologia(insumo.getTipoTecnologia());
                                historico.setEstado(insumo.getEstado());
                                historico.setUsuarioCrea("mipres sincroniza");
                                historico.setTerminalCrea("127.0.0.1");
                                historico.setFechaHoraCrea(new Date());
                                insertarMpPrescripcionHistoricos(historico);
                            }
                        }
                    }
                    //INSERTAR SERVICIOS COMPLEMENTARIOS
                    if (prescripcionDto.getServiciosComplementarios() != null && !prescripcionDto.getServiciosComplementarios().isEmpty()) {
                        for (ServicioComplementario complementario : prescripcionDto.getServiciosComplementarios()) {
                            MpPrescripcionInsumos insumo = castMpPrescripcionInsumosServComplementario(prescripcion, complementario);
                            if (insumo != null) {
                                insumo.setId(insertarMpPrescripcionInsumos(insumo));
                                //se construye e inserta objeto MpPrescripcionHistoricos
                                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                                historico.setMpPrescripcionesId(prescripcion);
                                historico.setIdPrescripcionTecnologia(insumo.getId());
                                historico.setTipoTecnologia(insumo.getTipoTecnologia());
                                historico.setEstado(insumo.getEstado());
                                historico.setUsuarioCrea("mipres sincroniza");
                                historico.setTerminalCrea("127.0.0.1");
                                historico.setFechaHoraCrea(new Date());
                                insertarMpPrescripcionHistoricos(historico);
                            }
                        }
                    }
                } else {
                    fallo.setEstado((short) 1);
                    fallo.setFechaHoraFallo(new Date());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(prescripcionDto);
                    fallo.setJson(jsonString);
                    if (prescripcion.getTerminalCrea().equals("01")) {
                        fallo.setDescripcion("Error: no se encuentra tipo documento: "
                                + prescripcionDto.getPrescripcion().getTipoIDPaciente());
                    }
                    if (prescripcion.getTerminalCrea().equals("02")) {
                        fallo.setDescripcion("Error: no se encuentra Afiliado con tipo id: "
                                + prescripcionDto.getPrescripcion().getTipoIDPaciente()
                                + " e identificacion numero: " + prescripcionDto.getPrescripcion().getNroIDPaciente());
                    }
                    if (prescripcion.getTerminalCrea().equals("03")) {
                        fallo.setDescripcion("Error: no se encuentra Afiliado con tipo id: "
                                + prescripcionDto.getPrescripcion().getTipoIDPaciente()
                                + " e identificacion numero: " + prescripcionDto.getPrescripcion().getNroIDPaciente());
                    }
                }
            } else {
                fallo.setEstado((short) 1);
                fallo.setFechaHoraFallo(new Date());
                fallo.setDescripcion("Error: Prescripcion null");
                Gson gson = new Gson();
                String jsonString = gson.toJson(prescripcionDto);
                byte[] json = jsonString.getBytes();
                fallo.setJson(jsonString);
            }
        } catch (Exception e) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error: " + e.getMessage() + "CAUSE" + e.getCause().getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(prescripcionDto);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
        }
        return fallo;
    }

    //ACTUALIZAR TECNOLOGIA JUNTA
    @Override
    public MpConsumoFallo procesarJunta(JuntaProfesional junta,
            Map<String, MpPrescripcionMedicamento> listaMedicamentos,
            Map<String, MpPrescripcionTecnologia> listaTecnologias,
            Map<String, MpPrescripcionInsumo> listaInsumos) throws Exception {
        MpConsumoFallo fallo = new MpConsumoFallo();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fallo.setEstado((short) 0);
            switch (junta.getJunta_profesional().getTipoTecnologia()) {
                case "M":
                    MpPrescripcionMedicamento medicamento = listaMedicamentos
                            .getOrDefault(junta.getJunta_profesional().getNoPrescripcion().trim() + "||"
                                    + junta.getJunta_profesional().getConsecutivo().trim(), null);
                    if (medicamento != null) {
                        StringBuilder sql = new StringBuilder();
                        sql.append("UPDATE MpPrescripcionMedicamentos ");
                        sql.append("SET estadoJuntaProfesionales = :estado ");
                        if (junta.getJunta_profesional().getJustificacionTecnica() != null) {
                            sql.append(", justificacionTecJunta = :justificacion ");
                        }
                        if (junta.getJunta_profesional().getModalidad() != null) {
                            sql.append(", modJunta = :modalidad ");
                        }
                        if (junta.getJunta_profesional().getNoActa() != null
                                && !junta.getJunta_profesional().getNoActa().equals("")) {
                            sql.append(", numActaJunta = :numeroActa ");
                        }
                        if (junta.getJunta_profesional().getFechaActa() != null) {
                            sql.append(", fechaActaJunta = :fecha ");
                        }
                        sql.append("WHERE id = :id ");
                        Query query = getEntityManager().createQuery(sql.toString());
                        if (junta.getJunta_profesional().getEstJM() != null) {
                            query.setParameter("estado", Integer.parseInt(junta.getJunta_profesional().getEstJM()));
                        }
                        if (junta.getJunta_profesional().getJustificacionTecnica() != null) {
                            query.setParameter("justificacion", junta.getJunta_profesional().getJustificacionTecnica());
                        }
                        if (junta.getJunta_profesional().getModalidad() != null) {
                            query.setParameter("modalidad", junta.getJunta_profesional().getModalidad());
                        }
                        if (junta.getJunta_profesional().getNoActa() != null
                                && !junta.getJunta_profesional().getNoActa().equals("")) {
                            query.setParameter("numeroActa", junta.getJunta_profesional().getNoActa());
                        }
                        if (junta.getJunta_profesional().getFechaActa() != null) {
                            String fecha = junta.getJunta_profesional().getFechaActa().substring(0, 10);
                            Date fechaActa = formato.parse(fecha);
                            query.setParameter("fecha", fechaActa);
                        }
                        query.setParameter("id", medicamento.getId());
                        query.executeUpdate();
                    }
                    break;
                case "P":
                    MpPrescripcionTecnologia tecnologia = listaTecnologias
                            .getOrDefault(junta.getJunta_profesional().getNoPrescripcion().trim() + "||"
                                    + junta.getJunta_profesional().getConsecutivo().trim(), null);
                    if (tecnologia != null) {
                        StringBuilder sql = new StringBuilder();
                        sql.append("UPDATE MpPrescripcionTecnologias ");
                        if (junta.getJunta_profesional().getEstJM() != null) {
                            sql.append("SET estadoJuntaProfesionales = :estado ");
                        }
                        if (junta.getJunta_profesional().getJustificacionTecnica() != null) {
                            sql.append(", justificacionTecJunta = :justificacion ");
                        }
                        if (junta.getJunta_profesional().getModalidad() != null) {
                            sql.append(", modJunta = :modalidad ");
                        }
                        if (junta.getJunta_profesional().getNoActa() != null
                                && !junta.getJunta_profesional().getNoActa().equals("")) {
                            sql.append(", numActaJunta = :numeroActa ");
                        }
                        if (junta.getJunta_profesional().getFechaActa() != null) {
                            sql.append(", fechaActaJunta = :fecha ");
                        }
                        sql.append("WHERE id = :id ");
                        Query query = getEntityManager().createQuery(sql.toString());
                        if (junta.getJunta_profesional().getEstJM() != null) {
                            query.setParameter("estado", Integer.parseInt(junta.getJunta_profesional().getEstJM()));
                        }
                        if (junta.getJunta_profesional().getJustificacionTecnica() != null) {
                            query.setParameter("justificacion", junta.getJunta_profesional().getJustificacionTecnica());
                        }
                        if (junta.getJunta_profesional().getModalidad() != null) {
                            query.setParameter("modalidad", junta.getJunta_profesional().getModalidad());
                        }
                        if (junta.getJunta_profesional().getNoActa() != null
                                && !junta.getJunta_profesional().getNoActa().equals("")) {
                            query.setParameter("numeroActa", junta.getJunta_profesional().getNoActa());
                        }
                        if (junta.getJunta_profesional().getFechaActa() != null) {
                            String fecha = junta.getJunta_profesional().getFechaActa().substring(0, 10);
                            Date fechaActa = formato.parse(fecha);
                            query.setParameter("fecha", fechaActa);
                        }
                        query.setParameter("id", tecnologia.getId());
                        query.executeUpdate();
                    }
                    break;
                case "D":
                    MpPrescripcionInsumo insumo = listaInsumos
                            .getOrDefault(junta.getJunta_profesional().getNoPrescripcion().trim() + "||"
                                    + junta.getJunta_profesional().getConsecutivo().trim(), null);
                    if (insumo != null) {
                        StringBuilder sql = new StringBuilder();
                        sql.append("UPDATE MpPrescripcionInsumos ");
                        if (junta.getJunta_profesional().getEstJM() != null) {
                            sql.append("SET estadoJuntaProfesionales = :estado ");
                        }
                        if (junta.getJunta_profesional().getJustificacionTecnica() != null) {
                            sql.append(", justificacionTecJunta = :justificacion ");
                        }
                        if (junta.getJunta_profesional().getModalidad() != null) {
                            sql.append(", modalidadJunta = :modalidad ");
                        }
                        if (junta.getJunta_profesional().getNoActa() != null
                                && !junta.getJunta_profesional().getNoActa().equals("")) {
                            sql.append(", numActaJunta = :numeroActa ");
                        }
                        if (junta.getJunta_profesional().getFechaActa() != null) {
                            sql.append(", fechaActaJunta = :fecha ");
                        }
                        sql.append("WHERE id = :id ");
                        Query query = getEntityManager().createQuery(sql.toString());
                        if (junta.getJunta_profesional().getEstJM() != null) {
                            query.setParameter("estado", Integer.parseInt(junta.getJunta_profesional().getEstJM()));
                        }
                        if (junta.getJunta_profesional().getJustificacionTecnica() != null) {
                            query.setParameter("justificacion", junta.getJunta_profesional().getJustificacionTecnica());
                        }
                        if (junta.getJunta_profesional().getModalidad() != null) {
                            query.setParameter("modalidad", junta.getJunta_profesional().getModalidad());
                        }
                        if (junta.getJunta_profesional().getNoActa() != null
                                && !junta.getJunta_profesional().getNoActa().equals("")) {
                            query.setParameter("numeroActa", junta.getJunta_profesional().getNoActa());
                        }
                        if (junta.getJunta_profesional().getFechaActa() != null) {
                            String fecha = junta.getJunta_profesional().getFechaActa().substring(0, 10);
                            Date fechaActa = formato.parse(fecha);
                            query.setParameter("fecha", fechaActa);
                        }
                        query.setParameter("id", insumo.getId());
                        query.executeUpdate();
                    }
                    break;
                case "N":
                    MpPrescripcionMedicamento nut = listaMedicamentos
                            .getOrDefault(junta.getJunta_profesional().getNoPrescripcion().trim() + "||"
                                    + junta.getJunta_profesional().getConsecutivo().trim(), null);
                    if (nut != null) {
                        StringBuilder sql = new StringBuilder();
                        sql.append("UPDATE MpPrescripcionMedicamentos ");
                        if (junta.getJunta_profesional().getEstJM() != null) {
                            sql.append("SET estadoJuntaProfesionales = :estado ");
                        }
                        if (junta.getJunta_profesional().getJustificacionTecnica() != null) {
                            sql.append(", justificacionTecJunta = :justificacion ");
                        }
                        if (junta.getJunta_profesional().getModalidad() != null) {
                            sql.append(", modJunta = :modalidad ");
                        }
                        if (junta.getJunta_profesional().getNoActa() != null
                                && !junta.getJunta_profesional().getNoActa().equals("")) {
                            sql.append(", numActaJunta = :numeroActa ");
                        }
                        if (junta.getJunta_profesional().getFechaActa() != null) {
                            sql.append(", fechaActaJunta = :fecha ");
                        }
                        sql.append("WHERE id = :id ");
                        Query query = getEntityManager().createQuery(sql.toString());
                        if (junta.getJunta_profesional().getEstJM() != null) {
                            query.setParameter("estado", Integer.parseInt(junta.getJunta_profesional().getEstJM()));
                        }
                        if (junta.getJunta_profesional().getJustificacionTecnica() != null) {
                            query.setParameter("justificacion", junta.getJunta_profesional().getJustificacionTecnica());
                        }
                        if (junta.getJunta_profesional().getModalidad() != null) {
                            query.setParameter("modalidad", junta.getJunta_profesional().getModalidad());
                        }
                        if (junta.getJunta_profesional().getNoActa() != null
                                && !junta.getJunta_profesional().getNoActa().equals("")) {
                            query.setParameter("numeroActa", junta.getJunta_profesional().getNoActa());
                        }
                        if (junta.getJunta_profesional().getFechaActa() != null) {
                            String fecha = junta.getJunta_profesional().getFechaActa().substring(0, 10);
                            Date fechaActa = formato.parse(fecha);
                            query.setParameter("fecha", fechaActa);
                        }
                        query.setParameter("id", nut.getId());
                        query.executeUpdate();
                    }
                    break;
                case "S":
                    MpPrescripcionInsumo sup = listaInsumos
                            .getOrDefault(junta.getJunta_profesional().getNoPrescripcion().trim() + "||"
                                    + junta.getJunta_profesional().getConsecutivo().trim(), null);
                    if (sup != null) {
                        StringBuilder sql = new StringBuilder();
                        sql.append("UPDATE MpPrescripcionInsumos ");
                        if (junta.getJunta_profesional().getEstJM() != null) {
                            sql.append("SET estadoJuntaProfesionales = :estado ");
                        }
                        if (junta.getJunta_profesional().getJustificacionTecnica() != null) {
                            sql.append(", justificacionTecJunta = :justificacion ");
                        }
                        if (junta.getJunta_profesional().getModalidad() != null) {
                            sql.append(", modalidadJunta = :modalidad ");
                        }
                        if (junta.getJunta_profesional().getNoActa() != null
                                && !junta.getJunta_profesional().getNoActa().equals("")) {
                            sql.append(", numActaJunta = :numeroActa ");
                        }
                        if (junta.getJunta_profesional().getFechaActa() != null) {
                            sql.append(", fechaActaJunta = :fecha ");
                        }
                        sql.append("WHERE id = :id ");
                        Query query = getEntityManager().createQuery(sql.toString());
                        if (junta.getJunta_profesional().getEstJM() != null) {
                            query.setParameter("estado", Integer.parseInt(junta.getJunta_profesional().getEstJM()));
                        }
                        if (junta.getJunta_profesional().getJustificacionTecnica() != null) {
                            query.setParameter("justificacion", junta.getJunta_profesional().getJustificacionTecnica());
                        }
                        if (junta.getJunta_profesional().getModalidad() != null) {
                            query.setParameter("modalidad", junta.getJunta_profesional().getModalidad());
                        }
                        if (junta.getJunta_profesional().getNoActa() != null
                                && !junta.getJunta_profesional().getNoActa().equals("")) {
                            query.setParameter("numeroActa", junta.getJunta_profesional().getNoActa());
                        }
                        if (junta.getJunta_profesional().getFechaActa() != null) {
                            String fecha = junta.getJunta_profesional().getFechaActa().substring(0, 10);
                            Date fechaActa = formato.parse(fecha);
                            query.setParameter("fecha", fechaActa);
                        }
                        query.setParameter("id", sup.getId());
                        query.executeUpdate();
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de tecnología no soportado: " + junta.getJunta_profesional().getTipoTecnologia());
            }
        } catch (Exception e) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error junta: " + e.getMessage() + "CAUSE" + e.getCause().getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(junta);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
        } finally {
            cerrarEntityManager();
        }
        return fallo;
    }

    //ACTUALIZAR DIRECCIONAMIENTO
    @Override
    public void actualizaDireccionamiento(
            String respuesta,
            Integer id,
            Integer estado,
            Integer idTransaccion,
            Integer idDireccionamiento) throws Exception {
        MpConsumoFallo fallo = new MpConsumoFallo();
        try {
            fallo.setEstado((short) 0);

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpDireccionamientos ");
            sql.append("SET estado = :estado ");
            if (respuesta != null && !respuesta.equals("")) {
                sql.append(", respuestaDireccionamiento = :respuesta ");
            }
            if (idTransaccion > 0) {
                sql.append(", idTransaccion = :transaccionId");
            }
            if (idDireccionamiento > 0) {
                sql.append(", idDireccionamiento = :idDireccionamiento ");
            }
            sql.append("WHERE id = :id ");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", estado);
            if (respuesta != null && !respuesta.equals("")) {
                query.setParameter("respuesta", respuesta);
            }
            query.setParameter("id", id);
            if (idTransaccion > 0) {
                query.setParameter("transaccionId", idTransaccion);
            }
            if (idDireccionamiento > 0) {
                query.setParameter("idDireccionamiento", idDireccionamiento);
            }
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error junta: " + e.getMessage() + "CAUSE" + e.getCause().getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizaDireccionamientoEntregado(
            String respuesta,
            Integer id,
            Integer idTransaccion,
            Integer idDatoFacturado) throws Exception {
        MpConsumoFallo fallo = new MpConsumoFallo();
        try {
            fallo.setEstado((short) 0);

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpDireccionamientoEntregados ");
            sql.append("SET id_facturacion = :idDatoFacturado, ");
            sql.append("est_facturacion = :estado ");
            sql.append("WHERE id = :id ");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", 2);
            query.setParameter("idDatoFacturado", idDatoFacturado);
            query.setParameter("id", id);
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error junta: " + e.getMessage() + " CAUSE " + e.getCause().getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    //ACTUALIZAR NO DIRECCIONAMIENTO
    @Override
    public void actualizaNoDireccionamiento(
            String respuesta,
            Integer id,
            Integer estado,
            Integer idTransaccion,
            Integer idNoDireccionamiento,
            String respuestaAnula) throws Exception {
        MpConsumoFallo fallo = new MpConsumoFallo();
        try {
            fallo.setEstado((short) 0);

            StringBuilder sql = new StringBuilder("UPDATE MpNoDireccionados SET estadoNoDireccionamiento = :estado ");
            if (respuesta != null && !respuesta.isEmpty()) {
                sql.append(", respuestaNoDireccionamiento = :respuesta ");
            }
            if (idTransaccion != null && idTransaccion > 0) {
                sql.append(", idTransaccion = :idTransaccion ");
            }
            if (idNoDireccionamiento != null && idNoDireccionamiento > 0) {
                sql.append(", idNoDireccionamiento = :idNoDireccionamiento ");
            }
            if (respuestaAnula != null && !respuestaAnula.isEmpty()) {
                sql.append(", respuestaAnulacion = :respuestaAnula ");
            }
            sql.append("WHERE id = :id ");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", estado);
            query.setParameter("id", id);

            if (respuesta != null && !respuesta.isEmpty()) {
                query.setParameter("respuesta", respuesta);
            }
            if (idTransaccion != null && idTransaccion > 0) {
                query.setParameter("idTransaccion", idTransaccion);
            }
            if (idNoDireccionamiento != null && idNoDireccionamiento > 0) {
                query.setParameter("idNoDireccionamiento", idNoDireccionamiento);
            }
            if (respuestaAnula != null && !respuestaAnula.isEmpty()) {
                query.setParameter("respuestaAnula", respuestaAnula);
            }

            query.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error En No Direccionado: " + e.getMessage() + "CAUSE" + e.getCause().getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MpConsumoFallo procesarPrescripcionesRecobrante(
            PrescripcionRecobrante prescripcionDto,
            Map<String, AsegAfiliado> listaAfiliados,
            Map<String, CntProfesional> listaProfesionales,
            HashMap<String, Maestro> hashTipoDocumentos,
            HashMap<String, Maestro> hashTipoDocumentoEmpresas,
            HashMap<String, Maestro> hashTipoDocumentoProfesionales,
            boolean prescripcionExiste) throws Exception {
        MpConsumoFallo fallo = new MpConsumoFallo();
        try {
            fallo.setEstado((short) 0);
            //CREAR LA PRESCRIPCION
            MpPrescripciones prescripcion = castMpPrescripcionRecobrante(
                    prescripcionDto.getTutela(),
                    listaAfiliados,
                    listaProfesionales,
                    hashTipoDocumentos,
                    hashTipoDocumentoEmpresas,
                    hashTipoDocumentoProfesionales
            );
            if (prescripcion != null) {
                if (!prescripcion.getUsuarioCrea().equals("error")) {
                    prescripcion.setId(insertarMpPrescripcion(prescripcion));
                    if (prescripcion.getId() > 0) {
                        MpPrescripcionRecobrantes prescripcionRecobrante
                                = castMpPrescripcionRecobrante(prescripcionDto.getTutela(), prescripcion.getId());
                        insertarMpPrescripcionRecobrantes(prescripcionRecobrante);
                    }

                    //INSERTAR SERVICIO DE TECNOLOGIAS
                    if (prescripcionDto.getProcedimientos() != null && !prescripcionDto.getProcedimientos().isEmpty()) {
                        for (Procedimiento procedimiento : prescripcionDto.getProcedimientos()) {
                            MpPrescripcionTecnologias tecnologia = castMpPrescripcionTecnologias(prescripcion, procedimiento);
                            if (tecnologia != null) {
                                tecnologia.setId(insertarMpPrescripcionTecnologias(tecnologia));
                                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                                historico.setMpPrescripcionesId(prescripcion);
                                historico.setIdPrescripcionTecnologia(tecnologia.getId());
                                historico.setTipoTecnologia(tecnologia.getTipoTecnologia());
                                historico.setEstado(tecnologia.getEstado());
                                historico.setUsuarioCrea("mipres sincroniza");
                                historico.setTerminalCrea("127.0.0.1");
                                historico.setFechaHoraCrea(new Date());
                                insertarMpPrescripcionHistoricos(historico);
                            }
                        }
                    }
                    //INSERTAR SERVICIOS DE MEDICAMENTOS
                    if (prescripcionDto.getMedicamentos() != null && !prescripcionDto.getMedicamentos().isEmpty()) {
                        for (Medicamento medicamento : prescripcionDto.getMedicamentos()) {
                            MpPrescripcionMedicamentos medicamentos = castMpPrescripcionMedicamentos(prescripcion, medicamento);
                            if (medicamentos != null) {
                                medicamentos.setId(insertarMpPrescripcionMedicamentos(medicamentos));
                                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                                historico.setMpPrescripcionesId(prescripcion);
                                historico.setIdPrescripcionTecnologia(medicamentos.getId());
                                historico.setTipoTecnologia(medicamentos.getTipoTecnologia());
                                historico.setEstado(medicamentos.getEstado());
                                historico.setUsuarioCrea("mipres sincroniza");
                                historico.setTerminalCrea("127.0.0.1");
                                historico.setFechaHoraCrea(new Date());
                                insertarMpPrescripcionHistoricos(historico);
                            }
                        }
                    }
                    //INSERTAR SERVICIOS PRODUCTO NUTRICIONALES
                    if (prescripcionDto.getProductosnutricionales() != null && !prescripcionDto.getProductosnutricionales().isEmpty()) {
                        for (ProductoNutricional nutricional : prescripcionDto.getProductosnutricionales()) {
                            MpPrescripcionMedicamentos medicamentos = castMpPrescripcionMedicamentosNutricional(prescripcion, nutricional);
                            if (medicamentos != null) {
                                medicamentos.setId(insertarMpPrescripcionMedicamentos(medicamentos));
                                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                                historico.setMpPrescripcionesId(prescripcion);
                                historico.setIdPrescripcionTecnologia(medicamentos.getId());
                                historico.setTipoTecnologia(medicamentos.getTipoTecnologia());
                                historico.setEstado(medicamentos.getEstado());
                                historico.setUsuarioCrea("mipres sincroniza");
                                historico.setTerminalCrea("127.0.0.1");
                                historico.setFechaHoraCrea(new Date());
                                insertarMpPrescripcionHistoricos(historico);
                            }
                        }
                    }
                    //INSERTAR SERVICIOS INSUMOS DISPOSITIVOS
                    if (prescripcionDto.getDispositivos() != null && !prescripcionDto.getDispositivos().isEmpty()) {
                        for (Dispositivo dispositivo : prescripcionDto.getDispositivos()) {
                            MpPrescripcionInsumos insumo = castMpPrescripcionInsumos(prescripcion, dispositivo);
                            if (insumo != null) {
                                insumo.setId(insertarMpPrescripcionInsumos(insumo));
                                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                                historico.setMpPrescripcionesId(prescripcion);
                                historico.setIdPrescripcionTecnologia(insumo.getId());
                                historico.setTipoTecnologia(insumo.getTipoTecnologia());
                                historico.setEstado(insumo.getEstado());
                                historico.setUsuarioCrea("mipres sincroniza");
                                historico.setTerminalCrea("127.0.0.1");
                                historico.setFechaHoraCrea(new Date());
                                insertarMpPrescripcionHistoricos(historico);
                            }
                        }
                    }
                    //INSERTAR SERVICIOS COMPLEMENTARIOS
                    if (prescripcionDto.getServiciosComplementarios() != null && !prescripcionDto.getServiciosComplementarios().isEmpty()) {
                        for (ServicioComplementario complementario : prescripcionDto.getServiciosComplementarios()) {
                            MpPrescripcionInsumos insumo = castMpPrescripcionInsumosServComplementario(prescripcion, complementario);
                            if (insumo != null) {
                                insumo.setId(insertarMpPrescripcionInsumos(insumo));
                                //se construye e inserta objeto MpPrescripcionHistoricos
                                MpPrescripcionHistoricos historico = new MpPrescripcionHistoricos();
                                historico.setMpPrescripcionesId(prescripcion);
                                historico.setIdPrescripcionTecnologia(insumo.getId());
                                historico.setTipoTecnologia(insumo.getTipoTecnologia());
                                historico.setEstado(insumo.getEstado());
                                historico.setUsuarioCrea("mipres sincroniza");
                                historico.setTerminalCrea("127.0.0.1");
                                historico.setFechaHoraCrea(new Date());
                                insertarMpPrescripcionHistoricos(historico);
                            }
                        }
                    }

                } else {
                    if (prescripcion.getTerminalCrea().equals("01")) {
                        fallo.setEstado((short) 1);
                        fallo.setFechaHoraFallo(new Date());
                        fallo.setDescripcion("Error: no se encuentra tipo documento"
                                + prescripcionDto.getTutela().getTipoIDPaciente());
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(prescripcionDto);
                        byte[] json = jsonString.getBytes();
                        fallo.setJson(jsonString);
                    }
                    if (prescripcion.getTerminalCrea().equals("02")) {
                        fallo.setEstado((short) 1);
                        fallo.setFechaHoraFallo(new Date());
                        fallo.setDescripcion("Error: no se encuentra Afiliado con tipo id: "
                                + prescripcionDto.getTutela().getTipoIDPaciente()
                                + " e identificacion numero: " + prescripcionDto.getTutela().getNroIDPaciente());
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(prescripcionDto);
                        byte[] json = jsonString.getBytes();
                        fallo.setJson(jsonString);
                    }
                }
            } else {
                fallo.setEstado((short) 1);
                fallo.setFechaHoraFallo(new Date());
                fallo.setDescripcion("Error: no se pudo hacer cast del objeto");
                Gson gson = new Gson();
                String jsonString = gson.toJson(prescripcionDto);
                byte[] json = jsonString.getBytes();
                fallo.setJson(jsonString);
            }
        } catch (Exception e) {
            fallo.setEstado((short) 1);
            fallo.setFechaHoraFallo(new Date());
            fallo.setDescripcion("Error: " + e.getMessage());
            Gson gson = new Gson();
            String jsonString = gson.toJson(prescripcionDto);
            //byte[] json = jsonString.getBytes();
            fallo.setJson(jsonString);
        }
        return fallo;
    }

    private MpPrescripcionRecobrantes castMpPrescripcionRecobrante(Tutela prescripcionDto, int idPrecripcion) {
        MpPrescripcionRecobrantes recobrante = new MpPrescripcionRecobrantes();
        try {
            MpPrescripciones prescripcion = new MpPrescripciones();
            prescripcion.setId(idPrecripcion);
            recobrante.setMpPrescripcionesId(prescripcion);
            recobrante.setFallo(prescripcionDto.getNroFallo());
            if (prescripcionDto.getFFalloTutela() != null) {
                recobrante.setFechaFallo(new SimpleDateFormat("yyyy-MM-dd").parse(prescripcionDto.getFFalloTutela()));
            }
            if (prescripcionDto.getF1Instan() != null) {
                recobrante.setFechaPrimeraInstancia(new SimpleDateFormat("yyyy-MM-dd").parse(prescripcionDto.getF1Instan()));
            }
            if (prescripcionDto.getF2Instan() != null) {
                recobrante.setFechaSegundaInstancia(new SimpleDateFormat("yyyy-MM-dd").parse(prescripcionDto.getF2Instan()));
            }
            if (prescripcionDto.getFCorte() != null) {
                recobrante.setFechaCorte(new SimpleDateFormat("yyyy-MM-dd").parse(prescripcionDto.getFCorte()));
            }
            if (prescripcionDto.getFDesacato() != null) {
                recobrante.setFechaDesacato(new SimpleDateFormat("yyyy-MM-dd").parse(prescripcionDto.getFDesacato()));
            }
            if ((prescripcionDto.getCodDxPpal() != null && !prescripcionDto.getCodDxPpal().equals(""))) {
                MaDiagnosticos diagnostico = consultarDiagnosticoPorCodigo(prescripcionDto.getCodDxPpal());
                if (diagnostico != null) {
                    recobrante.setMaDiagnosticoMotivaPrincipalId(diagnostico.getId());
                    recobrante.setMaDiagnosticoMotivaPrincipalCodigo(diagnostico.getCodigo());
                    recobrante.setMaDiagnosticoMotivaPrincipalValor(diagnostico.getNombre());
                }
            }
            if ((prescripcionDto.getCodDxRel1() != null && !prescripcionDto.getCodDxRel1().equals(""))) {
                MaDiagnosticos diagnostico = consultarDiagnosticoPorCodigo(prescripcionDto.getCodDxRel1());
                if (diagnostico != null) {
                    recobrante.setMaDiagnosticoMotiva2Id(diagnostico.getId());
                    recobrante.setMaDiagnosticoMotiva2Codigo(diagnostico.getCodigo());
                    recobrante.setMaDiagnosticoMotiva2Valor(diagnostico.getNombre());
                }
            }
            if ((prescripcionDto.getCodDxRel2() != null && !prescripcionDto.getCodDxRel2().equals(""))) {
                MaDiagnosticos diagnostico = consultarDiagnosticoPorCodigo(prescripcionDto.getCodDxRel2());
                if (diagnostico != null) {
                    recobrante.setMaDiagnosticoMotiva3Id(diagnostico.getId());
                    recobrante.setMaDiagnosticoMotiva3Codigo(diagnostico.getCodigo());
                    recobrante.setMaDiagnosticoMotiva3Valor(diagnostico.getNombre());
                }
            }
            recobrante.setCriterio1Corte(validaBit(prescripcionDto.getCritDef1CC()));
            recobrante.setCriterio2Corte(validaBit(prescripcionDto.getCritDef2CC()));
            recobrante.setCriterio3Corte(validaBit(prescripcionDto.getCritDef3CC()));
            recobrante.setCriterio4Corte(validaBit(prescripcionDto.getCritDef4CC()));
            recobrante.setAclaracionFallo(prescripcionDto.getAclFalloTut());
            recobrante.setJustificacionMedica(prescripcionDto.getJustifMed());
            recobrante.setUsuarioCrea("mipres sincroniza");
            recobrante.setTerminalCrea("127.0.0.1");
            recobrante.setFechaHoraCrea(new Date());
            //ULTIMOS CAMPOS FALTANTES |LGUERRERO| 20230905
            //recobrante.setTipoTutela(prescripcionDto.getm); NO SE AGREGA INCONGRUENCIA POR TECNOLOGIA
            //recobrante.setNumeroFallo(prescripcionDto.getNroFallo());

        } catch (ParseException ex) {
            Logger.getLogger(PrescripcionWsServicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.Exception ex) {
            Logger.getLogger(PrescripcionWsServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recobrante;
    }

    private boolean validaBit(String dato) {
        boolean bit = false;
        if (dato.equals("1")) {
            bit = true;
        }
        return bit;
    }

    private int insertarMpPrescripcion(MpPrescripciones prescripcion) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(prescripcion).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarMpAfiliado(MpAfiliados afiliado) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(afiliado).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarMpPrescripcionRecobrantes(MpPrescripcionRecobrantes prescripcion) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(prescripcion).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarMpPrescripcionTecnologias(MpPrescripcionTecnologias tecnologia) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(tecnologia).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarMpPrescripcionMedicamentos(MpPrescripcionMedicamentos medicamento) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(medicamento).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private MpPrescripcionMedicamentos consultaMpPrescripcionMedicamento(Integer idMpMed) throws Exception {
        MpPrescripcionMedicamentos med = null;
        try {
            Query q = getEntityManager().createNamedQuery("MpPrescripcionMedicamentos.findById");
            q.setParameter(idMpMed, "id");
            Object obj = q.getSingleResult();
        } catch (NoResultException e) {
            med = null;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return med;
    }

    private MpPrescripcionTecnologias consultaMpPrescripcionTecnologia(Integer idMpTec) throws Exception {
        MpPrescripcionTecnologias tec = null;
        try {
            Query q = getEntityManager().createNamedQuery("MpPrescripcionTecnologias.findById");
            q.setParameter(idMpTec, "id");
            Object obj = q.getSingleResult();
        } catch (NoResultException e) {
            tec = null;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return tec;
    }

    private MpPrescripcionInsumos consultaMpPrescripcionInsumo(Integer idMpIns) throws Exception {
        MpPrescripcionInsumos ins = null;
        try {
            Query q = getEntityManager().createNamedQuery("MpPrescripcionInsumos.findById");
            q.setParameter(idMpIns, "id");
            Object obj = q.getSingleResult();
        } catch (NoResultException e) {
            ins = null;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return ins;
    }

    private int insertarMpMedicamentoPrincipiosActivos(MpMedicamentoPrincipiosActivos activo) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(activo).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            System.out.println("errro");
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarMpMedicamentoIndicacionesUnirs(MpMedicamentoIndicacionesUnirs indicacion) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(indicacion).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarMpPrescripcionInsumos(MpPrescripcionInsumos insumo) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(insumo).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarCntProfesionales(CntProfesionales profesional) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(profesional).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
//            System.out.println(e);
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
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public Integer insertarConsumo(Date periodo, String servicio) throws Exception {
        Integer idConsumo = null;
        MpConsumos consumo = new MpConsumos();
        consumo.setPeriodo(periodo);
        consumo.setServicio(servicio);
        consumo.setRegistros(0);
        consumo.setEstado(ESTADO_CONSUMO_CONSULTANDO);
        consumo.setObservacion("inicio servicio");
        consumo.setFechaHoraInicio(new Date());
        idConsumo = insertarMpConsumos(consumo);
        return idConsumo;
    }

    private int insertarMpConsumos(MpConsumos consumo) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(consumo).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public MpConsumos castEntidadNegocioMpConsumo(MpConsumo obj) throws Exception {
        MpConsumos per = new MpConsumos();
        per.setPeriodo(formatoSimple.parse(obj.getPeriodo()));
        per.setServicio(obj.getServicio());
        per.setServicioDireccion(obj.getServicioDireccion());
        per.setServicioDescripcion(obj.getServicioDescripcion());
        per.setRegistros(obj.getRegistros());
        per.setEstado(obj.getEstado());
        per.setObservacion(obj.getObservacion());
        per.setFechaHoraInicio(obj.getFechaHoraInicio());
        return per;
    }

    @Override
    public Integer insertarMpConsumo(MpConsumo mpConsumo) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpConsumo(mpConsumo)).getId();
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    //UPDATES
    private void actualizarMpConsumos(MpConsumos obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MpConsumos m SET ";
            strQuery += " m.estado = '" + obj.getEstado() + "', ";
            strQuery += " m.registros = '" + obj.getRegistros() + "', ";
            strQuery += " m.registrosExitosos = '" + obj.getRegistrosExitosos() + "', ";
            strQuery += " m.periodo = '" + obj.getPeriodo() + "', ";

            if (obj.getFechaHoraFin() != null) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                strQuery += " m.fechaHoraFin = '" + formato.format(obj.getFechaHoraFin()) + "', ";
            }
            strQuery += " m.observacion = '" + obj.getObservacion() + "' ";
            strQuery += "  WHERE m.id = " + obj.getId();
            org.hibernate.Query query = session.createQuery(strQuery);
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public List<MpConsumo> consultarConsumosRegistrosVsExitosos(String servicio) throws Exception {
        List<MpConsumo> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MpConsumos mp "
                    + "WHERE mp.registros > mp.registrosExitosos"
                    + " AND mp.servicio =  '" + servicio
                    + "' ORDER BY mp.id";
            List<MpConsumos> list = getEntityManager()
                    .createQuery(strQuery).setMaxResults(5000)
                    .getResultList();
            for (MpConsumos per : list) {
                //for (MpConsumoFallos per : list.subList(0, 1000)) {
                listResult.add(castMpConsumoEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            listResult = new ArrayList();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public Integer consultarCantidadFallidos(String servicio, Integer idConsumo) throws Exception {
        Integer cantidadFallos = 0;
        try {
            String strQuery = "FROM MpConsumos mp "
                    + "WHERE mp.id > " + (idConsumo - 4)
                    + " AND mp.servicio =  '" + servicio
                    + "' ORDER BY mp.id";
            List<MpConsumos> list = getEntityManager()
                    .createQuery(strQuery).setMaxResults(5000)
                    .getResultList();
            cantidadFallos = 0;
            for (MpConsumos per : list) {
                if (per.getId().intValue() != idConsumo.intValue()) {
                    if (per.getEstado() == 3) {
                        cantidadFallos++;
                    } else {
                        cantidadFallos = 0;
                    }
                }
            }
        } catch (NoResultException e) {
            System.out.println("" + e.getMessage());
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return cantidadFallos;
    }

    @Override
    public MpConsumo consultarUltimoConsumo(String servicio, String fecha) throws Exception {
        MpConsumo result = new MpConsumo();
        try {
            String strQuery = "FROM MpConsumos mp "
                    + "WHERE mp.servicio =  '" + servicio
                    + "' AND mp.periodo = '" + fecha
                    + "' AND mp.estado = 2 "
                    + " ORDER BY mp.id DESC";
            Query query = getEntityManager().createQuery(strQuery).setMaxResults(1);
            List<MpConsumos> list = query
                    .getResultList();
            if (!list.isEmpty()) {
                for (MpConsumos per : list) {
                    result = (castMpConsumoEntidadNegocio(per));
                }
            } else {
                result = null;
            }
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public MpConsumo consultarIdConsumo(Integer id) throws Exception {
        MpConsumo result = new MpConsumo();
        try {
            String strQuery = "FROM MpConsumos mp "
                    + "WHERE mp.id =  '" + id
                    + "' ORDER BY mp.id";
            MpConsumos per = (MpConsumos) getEntityManager()
                    .createQuery(strQuery).getSingleResult();
            //for (MpConsumoFallos per : list.subList(0, 1000)) {
            result = (castMpConsumoEntidadNegocio(per));

        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    public static MpConsumo castMpConsumoEntidadNegocio(MpConsumos per) {
        MpConsumo obj = new MpConsumo();
        obj.setId(per.getId());
        obj.setRegistros(per.getRegistros());
        obj.setRegistrosExitosos(per.getRegistrosExitosos());
        obj.setServicio(per.getServicio());
        obj.setObservacion(per.getObservacion());
        obj.setEstado(per.getEstado());
        obj.setPeriodo(per.getPeriodo().toString());
        return obj;
    }

//    @Override
//    public void actualizarConsumo(int estado, String observacion, int registros, Integer idConsumo, int exitosos) throws Exception {
//        MpConsumos consumo = BucarMpConsumosPorId(idConsumo);
//        if (consumo != null) {
//            consumo.setEstado(estado);
//            consumo.setRegistros(registros);
//            Integer maxSize = 1024;
//            if (observacion != null) {
//                if (observacion.length() > maxSize) {
//                    String obs = observacion.substring(0, maxSize);
//                    consumo.setObservacion(new String(obs.getBytes("US-ASCII"), StandardCharsets.UTF_8));
//                } else {
//                    consumo.setObservacion(new String(observacion.getBytes("US-ASCII"), StandardCharsets.UTF_8));
//                }
//            }
//            if (estado == MpConsumo.ESTADO_CONSUMO_EXITOSO
//                    || estado == MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR
//                    || estado == MpConsumo.ESTADO_CONSUMO_GUARDANDO_ERROR) {
//                consumo.setFechaHoraFin(new Date());
//            }
//            consumo.setRegistrosExitosos(exitosos);
//            actualizarMpConsumos(consumo);
//        }
//    }
    @Override
    public void actualizarConsumo(int estado, String observacion, int registros, Integer idConsumo, int exitosos) throws Exception {
        MpConsumos consumo = BucarMpConsumosPorId(idConsumo);
        if (consumo == null) {
            return;
        }

        consumo.setEstado(estado);
        consumo.setRegistros(registros);
        if (observacion != null) {
            int maxSize = 1024;
            if (observacion.length() > maxSize) {
                observacion = observacion.substring(0, maxSize);
            }
            consumo.setObservacion(new String(observacion.getBytes(StandardCharsets.UTF_8)));
        }
        if (estado == MpConsumo.ESTADO_CONSUMO_EXITOSO
                || estado == MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR
                || estado == MpConsumo.ESTADO_CONSUMO_GUARDANDO_ERROR) {
            consumo.setFechaHoraFin(new Date());
        }
        consumo.setRegistrosExitosos(exitosos);
        actualizarMpConsumos(consumo);
    }

    @Override
    public void actualizarMpConsumo(MpConsumo mpConsumo) throws Exception {
        MpConsumos consumo = BucarMpConsumosPorId(mpConsumo.getId());
        if (consumo == null) {
            return;
        }

        consumo.setEstado(mpConsumo.getEstado());
        consumo.setRegistros(mpConsumo.getRegistros());
        if (mpConsumo.getObservacion() != null) {
            int maxSize = 1024;
            if (mpConsumo.getObservacion().length() > maxSize) {
                mpConsumo.setObservacion(mpConsumo.getObservacion().substring(0, maxSize));
            }
            consumo.setObservacion(new String(mpConsumo.getObservacion().getBytes(StandardCharsets.UTF_8)));
        }
        if (mpConsumo.getEstado() == MpConsumo.ESTADO_CONSUMO_EXITOSO
                || mpConsumo.getEstado() == MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR
                || mpConsumo.getEstado() == MpConsumo.ESTADO_CONSUMO_GUARDANDO_ERROR) {
            consumo.setFechaHoraFin(new Date());
        }
        consumo.setRegistrosExitosos(mpConsumo.getRegistrosExitosos());
        actualizarMpConsumos(consumo);
    }

    //CASTEOS
    private MpPrescripciones castMpPrescripcion(PrescripcionDetalle prescripcionDto,
            Map<String, AsegAfiliado> listaAfiliados,
            Map<String, CntProfesional> listaProfesionales,
            HashMap<String, Maestro> hashTipoDocumentos,
            HashMap<String, Maestro> hashTipoDocumentoEmpresas,
            HashMap<String, Maestro> hashTipoDocumentoProfesionales
    ) throws Exception {
        MpPrescripciones prescripcion = new MpPrescripciones();
        try {
            if (prescripcionDto != null) {
                if (prescripcionDto.getTipoIDPaciente().equals(TIPO_DOCUMENTO_NACIDOVIVO)) {
                    prescripcionDto.setTipoIDPaciente(TIPO_DOCUMENTO_CERTIFICADONACIDOVIVO);
                }
                Integer idTipoDocumento = obtenerIdDocumento(prescripcionDto.getTipoIDPaciente(), hashTipoDocumentos);
                Maestro maeTipoDocumento = hashTipoDocumentos.get(prescripcionDto.getTipoIDPaciente());
                AsegAfiliado afiliado = null;
                MpAfiliados mpAfiliado = new MpAfiliados();
                if (idTipoDocumento != null && idTipoDocumento > 0) {
                    afiliado = listaAfiliados.getOrDefault(idTipoDocumento + "||" + prescripcionDto.getNroIDPaciente().trim(), null);
                    if (afiliado == null) {
                        try {
                            afiliado = getAfiliadoRemoto()
                                    .consultarPorAfiliadoDocumentoHistorico(
                                            idTipoDocumento,
                                            prescripcionDto.getNroIDPaciente().trim());
                        } catch (Exception ex) {
                            //don´t do anything
                        }
                    }
                } else {
                    afiliado = null;
                }
                if (afiliado == null) {
                    if (maeTipoDocumento != null) {
                        mpAfiliado.setMaeTipoDocumentoId(maeTipoDocumento.getId());
                        mpAfiliado.setMaeTipoDocumentoValor(maeTipoDocumento.getDescripcion());
                        mpAfiliado.setMaeTipoDocumentoCodigo(maeTipoDocumento.getValor());
                        mpAfiliado.setNumeroDocumento(prescripcionDto.getNroIDPaciente());
                        mpAfiliado.setPrimerNombre(prescripcionDto.getPNPaciente());
                        mpAfiliado.setSegundoNombre(prescripcionDto.getSNPaciente());
                        mpAfiliado.setPrimerApellido(prescripcionDto.getPAPaciente());
                        mpAfiliado.setSegundoApellido(prescripcionDto.getSAPaciente());
                        mpAfiliado.setUsuarioCrea("mipres sincroniza");
                        mpAfiliado.setTerminalCrea("127.0.0.1");
                        mpAfiliado.setFechaHoraCrea(new Date());
                        mpAfiliado.setId(insertarMpAfiliado(mpAfiliado));
                        afiliado = new AsegAfiliado();
                        afiliado.setId(0);
                    } else {
                        prescripcion.setUsuarioCrea("error");
                        prescripcion.setTerminalCrea("03");
                        afiliado = null;
                    }
                } else {
                    if (maeTipoDocumento != null) {
                        AsegAfiliados asegAfiliado = new AsegAfiliados();
                        asegAfiliado.setId(afiliado.getId());
                        mpAfiliado.setAsegAfiliadosId(asegAfiliado);
                        mpAfiliado.setMaeTipoDocumentoId(maeTipoDocumento.getId());
                        mpAfiliado.setMaeTipoDocumentoValor(maeTipoDocumento.getDescripcion());
                        mpAfiliado.setMaeTipoDocumentoCodigo(maeTipoDocumento.getValor());
                        mpAfiliado.setNumeroDocumento(afiliado.getNumeroDocumento());

                        mpAfiliado.setPrimerNombre(afiliado.getPrimerNombre());
                        mpAfiliado.setSegundoNombre(afiliado.getSegundoNombre());
                        mpAfiliado.setPrimerApellido(afiliado.getPrimerApellido());
                        mpAfiliado.setSegundoApellido(afiliado.getSegundoApellido());
                        //genero
                        mpAfiliado.setMaeGeneroId(afiliado.getMaeGeneroId());
                        mpAfiliado.setMaeGeneroCodigo(afiliado.getMaeGeneroCodigo());
                        mpAfiliado.setMaeGeneroValor(afiliado.getMaeGeneroValor());
                        //estado
                        mpAfiliado.setMaeEstadoAfiliacionId(afiliado.getMaeEstadoAfiliacion());
                        mpAfiliado.setMaeEstadoAfiliacionCodigo(afiliado.getMaeEstadoAfiliacionCodigo());
                        mpAfiliado.setMaeEstadoAfiliacionValor(afiliado.getMaeEstadoAfiliacionValor());
                        mpAfiliado.setUsuarioCrea("mipres sincroniza");
                        mpAfiliado.setTerminalCrea("127.0.0.1");
                        mpAfiliado.setFechaHoraCrea(new Date());
                        mpAfiliado.setId(insertarMpAfiliado(mpAfiliado));
                    } else {
                        prescripcion.setUsuarioCrea("error");
                        prescripcion.setTerminalCrea("03");
                        afiliado = null;
                    }
                }
                //        = consultarAfiliadoLista(idTipoDocumento, prescripcionDto.getNroIDPaciente(), listaAfiliados);

                if (afiliado != null) {
                    //prescripcion = new MpPrescripciones();
                    if (afiliado.getId() != 0) {
                        prescripcion.setAsegAfiliadosId(new AsegAfiliados(afiliado.getId()));
                    }
                    prescripcion.setMpAfiliadosId(mpAfiliado);
                    Maestro maestroTipoDocumentoEmpresa = obtenerMaestroEmpresa(prescripcionDto.getTipoIDIPS(), hashTipoDocumentoEmpresas);
                    if (maestroTipoDocumentoEmpresa != null) {
                        prescripcion.setMaTipoDocumentoPrestadorId(maestroTipoDocumentoEmpresa.getId());
                        prescripcion.setMaTipoDocumentoPrestadorCodigo(maestroTipoDocumentoEmpresa.getValor());
                        prescripcion.setMaTipoDocumentoPrestadorValor(maestroTipoDocumentoEmpresa.getNombre());
                    }
                    prescripcion.setPrestadorNumeroDocumento(prescripcionDto.getNroIDIPS());
                    if (prescripcionDto.getCodHabIPS() != null && !prescripcionDto.getCodHabIPS().equals("")) {
                        prescripcion.setDireccionIpsPrescriptora(prescripcionDto.getDirSedeIPS());
                        if (prescripcionDto.getTelSedeIPS() != null) {
                            if (prescripcionDto.getTelSedeIPS().length() > 46) {
                                prescripcion.setTelefonoIpsPrescriptora(prescripcionDto.getTelSedeIPS().substring(0, 46));
                            } else {
                                prescripcion.setTelefonoIpsPrescriptora(prescripcionDto.getTelSedeIPS());
                            }
                        }
                        prescripcion.setMunicipioIpsPrescriptora(Integer.parseInt(prescripcionDto.getCodDANEMunIPS()));
                        CntPrestadorSedes sede = consultarPrestadorSedePorCodigoHabilitacion(prescripcionDto.getCodHabIPS());
                        if (sede != null) {
                            prescripcion.setPrestadorRazonSocial(sede.getNombre());
                        }
                    } else {
                        prescripcion.setPrestadorRazonSocial("Prestador no Encontrado");
                    }
                    prescripcion.setSedeCodigoHabilitacion(prescripcionDto.getCodHabIPS());
                    CntProfesional profesional = null;
                    Maestro meastroTipoDocumentoProfesional = obtenerMaestroProfesional(prescripcionDto.getTipoIDProf(), hashTipoDocumentoProfesionales);
                    if (meastroTipoDocumentoProfesional != null) {
                        profesional = consultarProfesionalLista(meastroTipoDocumentoProfesional.getId(), prescripcionDto.getNumIDProf(), listaProfesionales);
                    } else {
                        prescripcion.setUsuarioCrea("error");
                        prescripcion.setTerminalCrea("04");
                        return prescripcion;
                    }
                    if (profesional != null) {
                        prescripcion.setCntProfesionalesId(new CntProfesionales(profesional.getId()));
                    } else {
                        //crear profesional si no se encuentra registrado
                        if (prescripcion.getUsuarioCrea() == null || !prescripcion.getUsuarioCrea().equals("error")) {
                            CntProfesionales ent = new CntProfesionales();
                            ent.setMaeTipoCodumentoId(meastroTipoDocumentoProfesional.getId());
                            ent.setMaeTipoDocumentoCodigo(meastroTipoDocumentoProfesional.getValor());
                            ent.setMaeTipoDocumentoValor(meastroTipoDocumentoProfesional.getNombre());
                            ent.setDocumento(prescripcionDto.getNumIDProf());
                            ent.setPrimerNombre(prescripcionDto.getPNProfS());
                            ent.setSegundoNombre(prescripcionDto.getSNProfS());
                            ent.setPrimerApellido(prescripcionDto.getPAProfS());
                            ent.setSegundoApellido(prescripcionDto.getSAProfS());
                            //Validación temporal codigo registro profesional mayor a 32 caracteres
                            ent.setRegistroMedico(prescripcionDto.getRegProfS());
                            ent.setUsuarioCrea("mipres sincroniza");
                            ent.setTerminalCrea("127.0.0.1");
                            ent.setFechaHoraCrea(new Date());
                            //TODO Validar si no tiene registro medico usar la cedula
                            if (prescripcionDto.getRegProfS() == null || "".equals(prescripcionDto.getRegProfS())) {
                                ent.setRegistroMedico(prescripcionDto.getNumIDProf());
                            }
                            ent.setId(insertarCntProfesionales(ent));
                            prescripcion.setCntProfesionalesId(ent);
                        }
                    }
                    prescripcion.setNumeroPrescripcion(prescripcionDto.getNoPrescripcion());
                    prescripcion.setFechaPrescripcion(new SimpleDateFormat("yyyy-MM-dd").parse(prescripcionDto.getFPrescripcion()));
                    prescripcion.setHoraPrescripcion(new SimpleDateFormat("HH:mm:ss").parse(prescripcionDto.getHPrescripcion()));
                    prescripcion.setCodAmbAte(prescripcionDto.getCodAmbAte().toString());
                    if (prescripcionDto.getRefAmbAte() == null || prescripcionDto.getRefAmbAte().equals("0")) {
                        prescripcion.setReferenciaAmbitoAtencion(false);
                    } else {
                        prescripcion.setReferenciaAmbitoAtencion(true);
                    }
                    if (prescripcionDto.getPacCovid19() == null || prescripcionDto.getPacCovid19() == 0) {
                        prescripcion.setPacienteCovid19(false);
                    } else {
                        prescripcion.setPacienteCovid19(true);
                    }
                    if (prescripcionDto.getEnfHuerfana() == null || prescripcionDto.getEnfHuerfana() == 0) {
                        prescripcion.setEnfermedadHuerfana(false);
                    } else {
                        prescripcion.setEnfermedadHuerfana(true);
                    }
                    if ((prescripcionDto.getCodEnfHuerfana() != null && !prescripcionDto.getCodEnfHuerfana().equals(""))) {
                        prescripcion.setCodigoEnfermedadHuerfana(prescripcionDto.getCodEnfHuerfana());
                    }
                    if ((prescripcionDto.getEnfHuerfanaDX() != null && !prescripcionDto.getEnfHuerfanaDX().equals(""))) {
                        //prescripcion.setDiagnosticoEnfermedadHuerfana(prescripcionDto.getEnfHuerfanaDX());
                    }
                    if ((prescripcionDto.getCodDxPpal() != null && !prescripcionDto.getCodDxPpal().equals(""))) {
                        MaDiagnosticos diagnostico = consultarDiagnosticoPorCodigo(prescripcionDto.getCodDxPpal());
                        if (diagnostico != null) {
                            prescripcion.setMaDiagnosticoPrincipalId(diagnostico.getId());
                            prescripcion.setMaDiagnosticoPrincipalCodigo(diagnostico.getCodigo());
                            prescripcion.setMaDiagnosticoPrincipalValor(diagnostico.getNombre());
                        }
                    }
                    if ((prescripcionDto.getCodDxRel1() != null && !prescripcionDto.getCodDxRel1().equals(""))) {
                        MaDiagnosticos diagnostico = consultarDiagnosticoPorCodigo(prescripcionDto.getCodDxRel1());
                        if (diagnostico != null) {
                            prescripcion.setMaDiagnosticoRelacionado1Id(diagnostico.getId());
                            prescripcion.setMaDiagnosticoRelacionado1Codigo(diagnostico.getCodigo());
                            prescripcion.setMaDiagnosticoRelacionado1Valor(diagnostico.getNombre());
                        }
                    }
                    if ((prescripcionDto.getCodDxRel2() != null && !prescripcionDto.getCodDxRel2().equals(""))) {
                        MaDiagnosticos diagnostico = consultarDiagnosticoPorCodigo(prescripcionDto.getCodDxRel2());
                        if (diagnostico != null) {
                            prescripcion.setMaDiagnosticoRelacionado2Id(diagnostico.getId());
                            prescripcion.setMaDiagnosticoRelacionado2Codigo(diagnostico.getCodigo());
                            prescripcion.setMaDiagnosticoRelacionado2Valor(diagnostico.getNombre());
                        }
                    }
                    prescripcion.setSopNutricional(prescripcionDto.getSopNutricional());
                    prescripcion.setCodigoEps(prescripcionDto.getCodEPS());
                    prescripcion.setAsegAfiliadoMadreTipoDocumento(prescripcionDto.getTipoIDMadrePaciente());
                    prescripcion.setAsegAfiliadoMadreDocumento(prescripcionDto.getNroIDMadrePaciente());
                    prescripcion.setTipoTransaccion(Integer.parseInt(prescripcionDto.getTipoTransc()));
                    prescripcion.setNumeroDocumentoDonanteVivo(prescripcionDto.getNroIDDonanteVivo());
                    prescripcion.setTipoDocumentoDonanteVivo(prescripcionDto.getTipoIDDonanteVivo());
                    prescripcion.setEstado(prescripcionDto.getEstPres());
                    prescripcion.setUsuarioCrea("mipres sincroniza");
                    prescripcion.setTerminalCrea("127.0.0.1");
                    prescripcion.setFechaHoraCrea(new Date());
                    //Validaciones adicionales (integridad de datos)
                    //prescripcion = validarPrescripcion(prescripcion);

                    //DATOS FALTANTES - por verificar |LGUERERRO 20230905|
                    prescripcion.setAfectaPresMax(false);
                    prescripcion.setCompradorHomologo("");
                    prescripcion.setTranscripcion("");
                    prescripcion.setDerechosVerificados(false);
                    prescripcion.setPortabilidad(false);
                    prescripcion.setMunicipioPortabilidad(0);
                    prescripcion.setTransferidaPor("");
                    prescripcion.setActualizadaPor("");
                    prescripcion.setRequiereAnulacion(false);
                    prescripcion.setNotaAuditoria("");

                } else {
                    if (!prescripcion.getUsuarioCrea().equals("error")) {
                        prescripcion.setUsuarioCrea("error");
                        prescripcion.setTerminalCrea("03");
                    }
//                    System.out.println("ERROR: afiliado no encontrado - prescripcion: "+prescripcionDto.getNoPrescripcion() +" afiliado "+ prescripcionDto.getTipoIDPaciente()+" - "+prescripcionDto.getNroIDPaciente());
                }
            } else {
                prescripcion = null;
            }
        } catch (Exception ex) {
            //System.out.println("excepción casteando mp prescripción: " + ex.toString());
//            System.out.println("error castMpPrescripcion: " + ex.getMessage());
            prescripcion = null;
        }
        return prescripcion;
    }

    private MpPrescripciones castMpPrescripcionRecobrante(Tutela prescripcionDto,
            Map<String, AsegAfiliado> listaAfiliados,
            Map<String, CntProfesional> listaProfesionales,
            HashMap<String, Maestro> hashTipoDocumentos,
            HashMap<String, Maestro> hashTipoDocumentoEmpresas,
            HashMap<String, Maestro> hashTipoDocumentoProfesionales
    ) throws Exception {
        MpPrescripciones prescripcion = new MpPrescripciones();
        try {
            if (prescripcionDto != null) {
                if (prescripcionDto.getTipoIDPaciente().equals(TIPO_DOCUMENTO_NACIDOVIVO)) {
                    prescripcionDto.setTipoIDPaciente(TIPO_DOCUMENTO_CERTIFICADONACIDOVIVO);
                }
                Integer idTipoDocumento = obtenerIdDocumento(prescripcionDto.getTipoIDPaciente(), hashTipoDocumentos);
                Maestro maeTipoDocumento = hashTipoDocumentos.get(prescripcionDto.getTipoIDPaciente());
                AsegAfiliado afiliado = null;
                MpAfiliados mpAfiliado = new MpAfiliados();
                if (idTipoDocumento != null && idTipoDocumento > 0) {
                    afiliado = listaAfiliados.getOrDefault(idTipoDocumento + "||" + prescripcionDto.getNroIDPaciente().trim(), null);
                    if (afiliado == null) {
                        try {
                            afiliado = getAfiliadoRemoto()
                                    .consultarPorAfiliadoDocumentoHistorico(
                                            idTipoDocumento,
                                            prescripcionDto.getNroIDPaciente().trim());
                        } catch (Exception ex) {

                        }
                    }
                } else {
                    afiliado = null;
                }
                if (afiliado == null) {
                    if (maeTipoDocumento != null) {
                        mpAfiliado.setMaeTipoDocumentoId(maeTipoDocumento.getId());
                        mpAfiliado.setMaeTipoDocumentoValor(maeTipoDocumento.getDescripcion());
                        mpAfiliado.setMaeTipoDocumentoCodigo(maeTipoDocumento.getValor());
                        mpAfiliado.setNumeroDocumento(prescripcionDto.getNroIDPaciente());
                        mpAfiliado.setPrimerNombre(prescripcionDto.getPNPaciente());
                        mpAfiliado.setSegundoNombre(prescripcionDto.getSNPaciente());
                        mpAfiliado.setPrimerApellido(prescripcionDto.getPAPaciente());
                        mpAfiliado.setSegundoApellido(prescripcionDto.getSAPaciente());
                        mpAfiliado.setUsuarioCrea("mipres sincroniza");
                        mpAfiliado.setTerminalCrea("127.0.0.1");
                        mpAfiliado.setFechaHoraCrea(new Date());
                        mpAfiliado.setId(insertarMpAfiliado(mpAfiliado));
                        afiliado = new AsegAfiliado();
                        afiliado.setId(0);
                    } else {
                        prescripcion.setUsuarioCrea("error");
                        prescripcion.setTerminalCrea("03");
                        afiliado = null;
                    }
                } else {
                    if (maeTipoDocumento != null) {
                        AsegAfiliados asegAfiliado = new AsegAfiliados();
                        asegAfiliado.setId(afiliado.getId());
                        mpAfiliado.setAsegAfiliadosId(asegAfiliado);
                        mpAfiliado.setMaeTipoDocumentoId(maeTipoDocumento.getId());
                        mpAfiliado.setMaeTipoDocumentoValor(maeTipoDocumento.getDescripcion());
                        mpAfiliado.setMaeTipoDocumentoCodigo(maeTipoDocumento.getValor());
                        mpAfiliado.setNumeroDocumento(afiliado.getNumeroDocumento());

                        mpAfiliado.setPrimerNombre(afiliado.getPrimerNombre());
                        mpAfiliado.setSegundoNombre(afiliado.getSegundoNombre());
                        mpAfiliado.setPrimerApellido(afiliado.getPrimerApellido());
                        mpAfiliado.setSegundoApellido(afiliado.getSegundoApellido());
                        //genero
                        mpAfiliado.setMaeGeneroId(afiliado.getMaeGeneroId());
                        mpAfiliado.setMaeGeneroCodigo(afiliado.getMaeGeneroCodigo());
                        mpAfiliado.setMaeGeneroValor(afiliado.getMaeGeneroValor());
                        //estado
                        mpAfiliado.setMaeEstadoAfiliacionId(afiliado.getMaeEstadoAfiliacion());
                        mpAfiliado.setMaeEstadoAfiliacionCodigo(afiliado.getMaeEstadoAfiliacionCodigo());
                        mpAfiliado.setMaeEstadoAfiliacionValor(afiliado.getMaeEstadoAfiliacionValor());
                        mpAfiliado.setUsuarioCrea("mipres sincroniza");
                        mpAfiliado.setTerminalCrea("127.0.0.1");
                        mpAfiliado.setFechaHoraCrea(new Date());
                        mpAfiliado.setId(insertarMpAfiliado(mpAfiliado));
                    } else {
                        prescripcion.setUsuarioCrea("error");
                        prescripcion.setTerminalCrea("03");
                        afiliado = null;
                    }
                }
                //        = consultarAfiliadoLista(idTipoDocumento, prescripcionDto.getNroIDPaciente(), listaAfiliados);

                if (afiliado != null) {
                    //prescripcion = new MpPrescripciones();
                    if (afiliado.getId() != 0) {
                        prescripcion.setAsegAfiliadosId(new AsegAfiliados(afiliado.getId()));
                    }
                    prescripcion.setMpAfiliadosId(mpAfiliado);
//                    Maestro maestroTipoDocumentoEmpresa = obtenerMaestroEmpresa(prescripcionDto.getTipoIDEPS(), hashTipoDocumentoEmpresas);
//                    if (maestroTipoDocumentoEmpresa != null) {
//                        prescripcion.setMaTipoDocumentoPrestadorId(maestroTipoDocumentoEmpresa.getId());
//                        prescripcion.setMaTipoDocumentoPrestadorCodigo(maestroTipoDocumentoEmpresa.getValor());
//                        prescripcion.setMaTipoDocumentoPrestadorValor(maestroTipoDocumentoEmpresa.getNombre());
//                    }
//                    prescripcion.setPrestadorNumeroDocumento(prescripcionDto.getNroIDEPS());
                    prescripcion.setRecobrante(true);
//                    if (prescripcionDto.getCodHabEPS() != null && !prescripcionDto.getCodHabEPS().equals("")) {
//                        CntPrestadorSedes sede = consultarPrestadorSedePorCodigoHabilitacion(prescripcionDto.getCodHabIPS());
//                        if (sede != null) {
//                            prescripcion.setPrestadorRazonSocial(sede.getNombre());
//                        }
//                    } else {
//                        prescripcion.setPrestadorRazonSocial("Prestador no Encontrado");
//                    }
//                    prescripcion.setSedeCodigoHabilitacion(prescripcionDto.getCodHabIPS());
                    CntProfesional profesional = null;
                    Maestro meastroTipoDocumentoProfesional = obtenerMaestroProfesional(prescripcionDto.getTipoIDProf(), hashTipoDocumentoProfesionales);
                    if (meastroTipoDocumentoProfesional != null) {
                        profesional = consultarProfesionalLista(meastroTipoDocumentoProfesional.getId(), prescripcionDto.getNumIDProf(), listaProfesionales);
                    }
                    if (profesional != null) {
                        prescripcion.setCntProfesionalesId(new CntProfesionales(profesional.getId()));
                    } else {
                        //crear profesional si no se encuentra registrado
                        CntProfesionales ent = new CntProfesionales();
                        ent.setMaeTipoCodumentoId(meastroTipoDocumentoProfesional.getId());
                        ent.setMaeTipoDocumentoCodigo(meastroTipoDocumentoProfesional.getValor());
                        ent.setMaeTipoDocumentoValor(meastroTipoDocumentoProfesional.getNombre());
                        ent.setDocumento(prescripcionDto.getNumIDProf());
                        ent.setPrimerNombre(prescripcionDto.getPNProfS());
                        ent.setSegundoNombre(prescripcionDto.getSNProfS());
                        ent.setPrimerApellido(prescripcionDto.getPAProfS());
                        ent.setSegundoApellido(prescripcionDto.getSAProfS());
                        //Validación temporal codigo registro profesional mayor a 32 caracteres
                        ent.setRegistroMedico(prescripcionDto.getRegProfS());
                        ent.setUsuarioCrea("mipres sincroniza");
                        ent.setTerminalCrea("127.0.0.1");
                        ent.setFechaHoraCrea(new Date());
                        //TODO Validar si no tiene registro medico usar la cedula
                        if (prescripcionDto.getRegProfS() == null || "".equals(prescripcionDto.getRegProfS())) {
                            ent.setRegistroMedico(prescripcionDto.getNumIDProf());
                        }
                        ent.setId(insertarCntProfesionales(ent));
                        prescripcion.setCntProfesionalesId(ent);
                    }
                    prescripcion.setNumeroPrescripcion(prescripcionDto.getNoTutela());
                    prescripcion.setFechaPrescripcion(new SimpleDateFormat("yyyy-MM-dd").parse(prescripcionDto.getFTutela()));
                    prescripcion.setHoraPrescripcion(new SimpleDateFormat("HH:mm:ss").parse(prescripcionDto.getHTutela()));
//                    prescripcion.setCodAmbAte(prescripcionDto.getCodAmbAte().toString());
//                    if (prescripcionDto.getRefAmbAte() == null || prescripcionDto.getRefAmbAte().equals("0")) {
//                        prescripcion.setReferenciaAmbitoAtencion(false);
//                    } else {
//                        prescripcion.setReferenciaAmbitoAtencion(true);
//                    }
//                    if (prescripcionDto.getPacCovid19() == null || prescripcionDto.getPacCovid19() == 0) {
//                        prescripcion.setPacienteCovid19(false);
//                    } else {
//                        prescripcion.setPacienteCovid19(true);
//                    }
//                    if (prescripcionDto.getEnfHuerfana() == null || prescripcionDto.getEnfHuerfana() == 0) {
//                        prescripcion.setEnfermedadHuerfana(false);
//                    } else {
//                        prescripcion.setEnfermedadHuerfana(true);
//                    }
                    if ((prescripcionDto.getCodDxPpal() != null && !prescripcionDto.getCodDxPpal().equals(""))) {
                        MaDiagnosticos diagnostico = consultarDiagnosticoPorCodigo(prescripcionDto.getCodDxPpal());
                        if (diagnostico != null) {
                            prescripcion.setMaDiagnosticoPrincipalId(diagnostico.getId());
                            prescripcion.setMaDiagnosticoPrincipalCodigo(diagnostico.getCodigo());
                            prescripcion.setMaDiagnosticoPrincipalValor(diagnostico.getNombre());
                        }
                    }
                    if ((prescripcionDto.getCodDxRel1() != null && !prescripcionDto.getCodDxRel1().equals(""))) {
                        MaDiagnosticos diagnostico = consultarDiagnosticoPorCodigo(prescripcionDto.getCodDxRel1());
                        if (diagnostico != null) {
                            prescripcion.setMaDiagnosticoRelacionado1Id(diagnostico.getId());
                            prescripcion.setMaDiagnosticoRelacionado1Codigo(diagnostico.getCodigo());
                            prescripcion.setMaDiagnosticoRelacionado1Valor(diagnostico.getNombre());
                        }
                    }
                    if ((prescripcionDto.getCodDxRel2() != null && !prescripcionDto.getCodDxRel2().equals(""))) {
                        MaDiagnosticos diagnostico = consultarDiagnosticoPorCodigo(prescripcionDto.getCodDxRel2());
                        if (diagnostico != null) {
                            prescripcion.setMaDiagnosticoRelacionado2Id(diagnostico.getId());
                            prescripcion.setMaDiagnosticoRelacionado2Codigo(diagnostico.getCodigo());
                            prescripcion.setMaDiagnosticoRelacionado2Valor(diagnostico.getNombre());
                        }
                    }
//                    prescripcion.setSopNutricional(prescripcionDto.getSopNutricional());
                    prescripcion.setCodigoEps(prescripcionDto.getCodEPS());
                    prescripcion.setAsegAfiliadoMadreDocumento(prescripcionDto.getTipoIDMadrePaciente());
                    prescripcion.setAsegAfiliadoMadreDocumento(prescripcionDto.getNroIDMadrePaciente());
                    prescripcion.setTipoTransaccion(1);
//                    prescripcion.setNumeroDocumentoDonanteVivo(prescripcionDto.getNroIDDonanteVivo());
//                    prescripcion.setTipoDocumentoDonanteVivo(prescripcionDto.getTipoIDDonanteVivo());
                    prescripcion.setEstado(Integer.parseInt(prescripcionDto.getEstTut()));
                    prescripcion.setUsuarioCrea("mipres sincroniza");
                    prescripcion.setTerminalCrea("127.0.0.1");
                    prescripcion.setFechaHoraCrea(new Date());
                    //Validaciones adicionales (integridad de datos)
                    //prescripcion = validarPrescripcion(prescripcion);
                } else {
//                    System.out.println("ERROR: afiliado no encontrado - prescripcion: "+prescripcionDto.getNoPrescripcion() +" afiliado "+ prescripcionDto.getTipoIDPaciente()+" - "+prescripcionDto.getNroIDPaciente());
                }
            } else {
                prescripcion = null;
            }
        } catch (Exception ex) {
//            System.out.println("excepción casteando mp prescripción: " + ex.toString());
            prescripcion = null;
        }
        return prescripcion;
    }

    @Override
    public int insertarMpAfiliados(MpAfiliado obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadMpAfiliado(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpAfiliados castNegocioEntidadMpAfiliado(MpAfiliado obj) {
        MpAfiliados ent = new MpAfiliados();
        ent.setId(obj.getId());
        ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        ent.setNumeroDocumento(obj.getNumeroDocumento());
        ent.setPrimerNombre(obj.getPrimerNombre());
        ent.setSegundoNombre(obj.getSegundoNombre());
        ent.setPrimerApellido(obj.getPrimerApellido());
        if (obj.getAsegAfiliadosId() != null) {
            AsegAfiliados afiliado = new AsegAfiliados();
            afiliado.setId(obj.getAsegAfiliadosId().getId());
            ent.setAsegAfiliadosId(afiliado);
            ent.setMaeGeneroId(obj.getMaeGeneroId());
            ent.setMaeGeneroValor(obj.getMaeGeneroValor());
            ent.setMaeGeneroCodigo(obj.getMaeGeneroCodigo());
            ent.setMaeEstadoAfiliacionId(obj.getMaeEstadoAfiliacionId());
            ent.setMaeEstadoAfiliacionValor(obj.getMaeEstadoAfiliacionValor());
            ent.setMaeEstadoAfiliacionCodigo(obj.getMaeEstadoAfiliacionCodigo());
        }
        return ent;
    }

    @Override
    public int insertarMpPrescripcion(MpPrescripcion obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadMpPrescripcion(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripciones castNegocioEntidadMpPrescripcion(MpPrescripcion obj) {
        MpPrescripciones ent = new MpPrescripciones();
        ent.setId(obj.getId());
        //asegAfiliado
        AsegAfiliados aseg = new AsegAfiliados();
        aseg.setId(obj.getAsegAfiliado().getId());
        ent.setAsegAfiliadosId(aseg);
        //mpAfiliados
        MpAfiliados mp = new MpAfiliados();
        mp.setId(obj.getMpAfiliado().getId());
        ent.setMpAfiliadosId(mp);
        //cntProfesional
        CntProfesionales cntProf = new CntProfesionales();
        cntProf.setId(obj.getCntProfesional().getId());
        ent.setCntProfesionalesId(cntProf);
        //diagnosticos
        if (obj.getMaDiagnosticoPrincipalId() > 0) {
            ent.setMaDiagnosticoPrincipalId(obj.getMaDiagnosticoPrincipalId());
            ent.setMaDiagnosticoPrincipalValor(obj.getMaDiagnosticoPrincipalValor());
            ent.setMaDiagnosticoPrincipalCodigo(obj.getMaDiagnosticoPrincipalCodigo());
        }
        if (obj.getMaDiagnosticoRelacionado1Id() > 0) {
            ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
        }
        if (obj.getMaDiagnosticoRelacionado2Id() > 0) {
            ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
        }
        //general
        ent.setRecobrante(obj.getRecobrante());
        ent.setNumeroPrescripcion(obj.getNumeroPrescripcion());
        ent.setFechaPrescripcion(obj.getFechaPrescripcion());
        ent.setHoraPrescripcion(obj.getHoraPrescripcion());
        ent.setCodigoEps(obj.getCodigoEps());
        ent.setAsegAfiliadoMadreTipoDocumento(obj.getAsegAfiliadoMadreTipoDocumento());
        ent.setAsegAfiliadoMadreDocumento(obj.getAsegAfiliadoMadreDocumento());
        ent.setTipoTransaccion(obj.getTipoTransaccion());
        ent.setEstado(obj.getEstado());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    @Override
    public int insertarMpPrescripcionRecobrante(MpPrescripcionRecobrante obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadMpPrescripcionRecobrante(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripcionRecobrantes castNegocioEntidadMpPrescripcionRecobrante(MpPrescripcionRecobrante obj) {
        MpPrescripcionRecobrantes ent = new MpPrescripcionRecobrantes();
        //mpPrescripcion
        MpPrescripciones mp = new MpPrescripciones();
        mp.setId(obj.getMpPrescripcionId().getId());
        ent.setMpPrescripcionesId(mp);
        //diagnosticos
        if (obj.getMaDiagnosticoMotivaPrincipalId() > 0) {
            ent.setMaDiagnosticoMotivaPrincipalId(obj.getMaDiagnosticoMotivaPrincipalId());
            ent.setMaDiagnosticoMotivaPrincipalValor(obj.getMaDiagnosticoMotivaPrincipalValor());
            ent.setMaDiagnosticoMotivaPrincipalCodigo(obj.getMaDiagnosticoMotivaPrincipalCodigo());
        }
        if (obj.getMaDiagnosticoMotiva2Id() > 0) {
            ent.setMaDiagnosticoMotiva2Id(obj.getMaDiagnosticoMotiva2Id());
            ent.setMaDiagnosticoMotiva2Valor(obj.getMaDiagnosticoMotiva2Valor());
            ent.setMaDiagnosticoMotiva2Codigo(obj.getMaDiagnosticoMotiva2Codigo());
        }
        if (obj.getMaDiagnosticoMotiva3Id() > 0) {
            ent.setMaDiagnosticoMotiva3Id(obj.getMaDiagnosticoMotiva3Id());
            ent.setMaDiagnosticoMotiva3Valor(obj.getMaDiagnosticoMotiva3Valor());
            ent.setMaDiagnosticoMotiva3Codigo(obj.getMaDiagnosticoMotiva3Codigo());
        }
        //general
        ent.setFallo(obj.getFallo());
        ent.setFechaFallo(obj.getFechaFallo());
        ent.setFechaPrimeraInstancia(obj.getFechaPrimeraInstancia());
        ent.setFechaSegundaInstancia(obj.getFechaSegundaInstancia());
        ent.setFechaCorte(obj.getFechaCorte());
        ent.setFechaDesacato(obj.getFechaDesacato());
        ent.setCriterio1Corte(obj.isCriterio1Corte());
        ent.setCriterio2Corte(obj.isCriterio2Corte());
        ent.setCriterio3Corte(obj.isCriterio3Corte());
        ent.setCriterio4Corte(obj.isCriterio4Corte());
        ent.setAclaracionFallo(obj.getAclaracionFallo());
        ent.setJustificacionMedica(obj.getJustificacionMedica());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    @Override
    public int insertarMpPrescripcionTecnologia(MpPrescripcionTecnologia obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadMpPrescripcionRecobrante(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripcionTecnologias castNegocioEntidadMpPrescripcionRecobrante(MpPrescripcionTecnologia obj) {
        MpPrescripcionTecnologias ent = new MpPrescripcionTecnologias();
        //mpPrescripcion
        MpPrescripciones mp = new MpPrescripciones();
        mp.setId(obj.getMpPrescripcion().getId());
        ent.setMpPrescripcionId(mp);
        //maTecnologia
        ent.setMaTecnologiaId(obj.getMaTecnologiaId());
        ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        //general
        ent.setEstado(obj.getEstado());
        ent.setEstadoJuntaProfesionales(obj.getEstadoJuntaProfesionales());
        ent.setConsecutivoOrden(obj.getConsecutivoOrden());
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setTipoPrestacion(obj.getTipoPrestacion());
        ent.setCausaSolicitud2(obj.getCausaSolicitud2());
        ent.setCausaSolicitud3(obj.getCausaSolicitud3());
        ent.setCausaSolicitud4(obj.getCausaSolicitud4());
        ent.setCausaSolicitud5(obj.getCausaSolicitud5());
        ent.setCausaSolicitud6(obj.getCausaSolicitud6());
        ent.setCausaSolicitud7(obj.getCausaSolicitud7());
        ent.setCausaSolicitud11(obj.getCausaSolicitud11());
        ent.setCausaSolicitud12(obj.getCausaSolicitud12());
        ent.setRazonCausaSolicitud51(obj.getRazonCausaSolicitud51());
        ent.setDescripcionRazon51(obj.getDescripcionRazon51());
        ent.setCodigoRazonCausa52(obj.getRazonCausaSolicitud51());
        ent.setDescripcionRazon52(obj.getDescripcionRazon52());
        ent.setCantidadFormulada(obj.getCantidadFormulada());
        ent.setCodigoUnidadTiempoFrecuenciaUso(obj.getCodigoUnidadTiempoFrecuenciaUso());
        ent.setCantidadDuracionTratamiento(obj.getCantidadDuracionTratamiento());
        ent.setCantidadTotal(obj.getCantidadTotal());
        ent.setCantidadTotalEntrega(obj.getCantidadTotalEntrega());
        ent.setPendientes(obj.getPendientes());
        ent.setCodigoPeriodoDuracionTratamiento(obj.getCodigoPeriodoDuracionTratamiento());
        ent.setJustificacionNoPbs(obj.getJustificacionNoPbs());
        ent.setEntregados(obj.getEntregados());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    @Override
    public int insertarMpPrescripcionHistorico(MpPrescripcionHistorico obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadMpPrescripcionHistorico(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripcionHistoricos castNegocioEntidadMpPrescripcionHistorico(MpPrescripcionHistorico obj) {
        MpPrescripcionHistoricos ent = new MpPrescripcionHistoricos();
        //mpPrescripcion
        MpPrescripciones mp = new MpPrescripciones();
        mp.setId(obj.getMpPrescripcion().getId());
        ent.setMpPrescripcionesId(mp);

        //general
        ent.setIdPrescripcionTecnologia(obj.getIdPrescripcionTecnologia());
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setEstado(obj.getEstado());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    @Override
    public int insertarMpPrescripcionMedicamento(MpPrescripcionMedicamento obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadMpPrescripcionMedicamento(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripcionMedicamentos castNegocioEntidadMpPrescripcionMedicamento(MpPrescripcionMedicamento obj) {
        MpPrescripcionMedicamentos ent = new MpPrescripcionMedicamentos();
        //mpPrescripcion
        MpPrescripciones mp = new MpPrescripciones();
        mp.setId(obj.getMpPrescripcion().getId());
        ent.setMpPrescripcionesId(mp);

        //general
        ent.setEstado(obj.getEstado());
        ent.setEstadoJuntaProfesionales(obj.getEstadoJuntaProfesionales());
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setConsecutivoOrden(obj.getConsecutivoOrden());
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setMedPbsUtilizado(obj.getMedPbsUtilizado());
        ent.setTipoMedicamento(obj.getTipoMedicamento());
        ent.setTipoPrestacion(obj.getTipoPrestacion());
        ent.setCausaSolicitud1(obj.getCausaSolicitud1());
        ent.setCausaSolicitud2(obj.getCausaSolicitud2());
        ent.setCausaSolicitud3(obj.getCausaSolicitud3());
        ent.setCausaSolicitud4(obj.getCausaSolicitud4());
        ent.setCausaSolicitud5(obj.getCausaSolicitud5());
        ent.setCausaSolicitud6(obj.getCausaSolicitud6());
        ent.setRazonCausaSolicitud31(obj.getRazonCausaSolicitud31());
        ent.setDescripcionRazon31(obj.getDescripcionRazon31());
        ent.setRazonCausaSolicitud32(obj.getRazonCausaSolicitud32());
        ent.setDescripcionRazon32(obj.getDescripcionRazon32());
        ent.setRazonCausaSolicitud41(obj.getRazonCausaSolicitud41());
        ent.setDescripcionRazon41(obj.getDescripcionRazon41());
        ent.setRazonCausaSolicitud42(obj.getRazonCausaSolicitud42());
        ent.setDescripcionRazon42(obj.getDescripcionRazon42());
        ent.setRazonCausaSolicitud43(obj.getRazonCausaSolicitud43());
        ent.setDescripcionRazon43(obj.getDescripcionRazon43());
        ent.setRazonCausaSolicitud44(obj.getRazonCausaSolicitud44());
        ent.setDescripcionRazon44(obj.getDescripcionRazon44());
        ent.setDescripcionMedicamentoPrincipioActivo(obj.getDescripcionMedicamentoPrincipioActivo());
        ent.setCodigoFormulaFarmaceutica(obj.getCodigoFormulaFarmaceutica());
        ent.setCodigoViaAdministracion(obj.getCodigoViaAdministracion());
        ent.setJustificacionNoPbs(obj.getJustificacionNoPbs());
        ent.setDosis(obj.getDosis());
        ent.setNumeroFrecuenciaAdministracion(obj.getNumeroFrecuenciaAdministracion());
        ent.setCodigoFrecuenciaAdministracion(obj.getCodigoFrecuenciaAdministracion());
        ent.setIndicacionesEspeciales(obj.getIndicacionesEspeciales());
        ent.setCantidadTratamiento(obj.getCantidadTratamiento());
        ent.setDuracionTratamiento(obj.getDuracionTratamiento());
        ent.setCantidadTotalEntrega(obj.getCantidadTotalEntrega());
        ent.setCantidadTotalFormulada(obj.getCantidadTotalFormulada());
        ent.setPendientes(obj.getPendientes());
        ent.setUnidadFarmaceuticaCantidadTotal(obj.getUnidadFarmaceuticaCantidadTotal());
        ent.setIndicacionRecibida(obj.getIndicacionRecibida());
        ent.setEntregados(obj.getEntregados());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    @Override
    public int insertarMpPrescripcionInsumo(MpPrescripcionInsumo obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadMpPrescripcionInsumo(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripcionInsumos castNegocioEntidadMpPrescripcionInsumo(MpPrescripcionInsumo obj) {
        MpPrescripcionInsumos ent = new MpPrescripcionInsumos();
        //mpPrescripcion
        MpPrescripciones mp = new MpPrescripciones();
        mp.setId(obj.getMpPrescripcion().getId());
        ent.setMpPrescripcionId(mp);

        //general
        ent.setEstado(obj.getEstado());
        ent.setEstadoJuntaProfesionales(obj.getEstadoJuntaProfesionales());
        ent.setConsecutivoOrden(obj.getConsecutivoOrden());
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setTipoPrestacion(obj.getTipoPrestacion());
        ent.setCausaSolicitud1(obj.getCausaSolicitud1());
        ent.setCodigoDispositivo(obj.getCodigoDispositivo());
        ent.setMaeDispositivosId(Integer.parseInt(obj.getMaeDispositivosId()));
        ent.setMaedispositivosValor(obj.getMaeDispositivosNombre());
        ent.setMaeDispositivosCodigo(obj.getMaeDispositivosCodigo());
        ent.setCantidad(obj.getCantidad());
        ent.setCantidadTotalEntrega(obj.getCantidadTotalEntrega());
        ent.setFrecuenciaUso(obj.getFrecuenciaUso());
        ent.setCantidadFormulada(obj.getCantidadFormulada());
        ent.setPendiente(obj.getPendiente());
        ent.setJustificacionNoPbs(obj.getJustificacionNoPbs());
        ent.setIndicacionesRecomendaciones(obj.getIndicacionesRecomendaciones());
        ent.setCodPerDurTrat(obj.getCodPerDurTrat());
        ent.setEntregados(obj.getEntregados());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    @Override
    public int insertarMpPrescripcionAnulada(MpAnuladaPrescripcion obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadMpPrescripcionAnulada(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "...");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripcionAnulada castNegocioEntidadMpPrescripcionAnulada(MpAnuladaPrescripcion obj) {
        MpPrescripcionAnulada ent = new MpPrescripcionAnulada();
        MpPrescripciones pres = new MpPrescripciones();
        pres.setId(obj.getMpPrescripcionId().getId());
        ent.setMpPrescripcionesId(pres);
        ent.setNumeroPrescripcion(obj.getNumeroPrescripcion());
        ent.setTipo(obj.getTipo());
        ent.setJustificacion(obj.getJustificacion());
        ent.setObservacion(obj.getObservacion());
        ent.setFechaHoraSolicitud(obj.getFechaHoraSolicitud());
        ent.setUsuarioSolicita(obj.getUsuarioSolicita());
        ent.setEstado(obj.getEstado());
        ent.setFechaHoraAnulacion(obj.getFechaHoraAnulacion());
        ent.setUsuarioAnula(obj.getUsuarioAnula());
        //auditoria datos
        ent.setUsuarioCrea("sincronizacionMipres");
        ent.setFechaHoraCrea(new Date());
        ent.setTerminalCrea("127.0.0.1");

        return ent;
    }

    @Override
    public MpPrescripcion consultarMpPrescripcion(String noPrescripcion) throws Exception {
        MpPrescripcion objRes = null;
        try {
            String strQuery = "FROM MpPrescripciones m "
                    + "WHERE m.numeroPrescripcion ='" + noPrescripcion + "'";
            objRes = castMpPrescripcionCorto((MpPrescripciones) getEntityManager().createQuery(strQuery).getSingleResult());
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private MpPrescripcion castMpPrescripcionCorto(MpPrescripciones per) {
        MpPrescripcion obj = new MpPrescripcion();
        obj.setId(per.getId());
        obj.setNumeroPrescripcion(per.getNumeroPrescripcion());
        obj.setEstado(per.getEstado());
        return obj;
    }

    @Override
    public MpPrescripcionRecobrante consultarMpPrescripcionRec(String idPrescripcion) throws Exception {
        MpPrescripcionRecobrante objRes = null;
        try {
            String strQuery = "FROM MpPrescripcionRecobrantes m "
                    + "WHERE m.mpPrescripcionesId ='" + idPrescripcion + "'";
            objRes = castMpPrescripcionRecCorto((MpPrescripcionRecobrantes) getEntityManager().createQuery(strQuery).getSingleResult());
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private MpPrescripcionRecobrante castMpPrescripcionRecCorto(MpPrescripcionRecobrantes per) {
        MpPrescripcionRecobrante obj = new MpPrescripcionRecobrante();
        obj.setId(per.getId());
        return obj;
    }

    @Override
    public List<MpPrescripcionTecnologia> consultarListaMpPrescripcionTecnologia(
            String idPrescripcion) throws Exception {
        List<MpPrescripcionTecnologia> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MpPrescripcionTecnologias m "
                    + "WHERE m.mpPrescripcionId ='" + idPrescripcion + "'";
            List<MpPrescripcionTecnologias> list = getEntityManager()
                    .createQuery(strQuery)
                    .getResultList();
            for (MpPrescripcionTecnologias per : list) {
                listResult.add(castMpPrescripcionTecnologiaCorto(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList<>();;
        } catch (Exception e) {
            listResult = new ArrayList<>();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    private MpPrescripcionTecnologia castMpPrescripcionTecnologiaCorto(MpPrescripcionTecnologias ent) throws java.lang.Exception {
        MpPrescripcionTecnologia obj = new MpPrescripcionTecnologia();
        obj.setId(ent.getId());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        return obj;
    }

    @Override
    public List<MpPrescripcionMedicamento> consultarListaMpPrescripcionMedicamento(
            String idPrescripcion) throws Exception {
        List<MpPrescripcionMedicamento> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MpPrescripcionMedicamentos m "
                    + "WHERE m.mpPrescripcionesId ='" + idPrescripcion + "'";
            List<MpPrescripcionMedicamentos> list = getEntityManager()
                    .createQuery(strQuery)
                    .getResultList();
            for (MpPrescripcionMedicamentos per : list) {
                listResult.add(castMpPrescripcionMedicamentoCorto(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList<>();;
        } catch (Exception e) {
            listResult = new ArrayList<>();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    private MpPrescripcionMedicamento castMpPrescripcionMedicamentoCorto(MpPrescripcionMedicamentos ent) throws java.lang.Exception {
        MpPrescripcionMedicamento obj = new MpPrescripcionMedicamento();
        obj.setId(ent.getId());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setCodigoFormulaFarmaceutica(ent.getCodigoFormulaFarmaceutica());
        obj.setDescripcionProductoNutricional(ent.getDescripcionProductoNutricional());
        return obj;
    }

    @Override
    public List<MpPrescripcionInsumo> consultarListaMpPrescripcionInsumo(
            String idPrescripcion) throws Exception {
        List<MpPrescripcionInsumo> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MpPrescripcionInsumos m "
                    + "WHERE m.mpPrescripcionId ='" + idPrescripcion + "'";
            List<MpPrescripcionInsumos> list = getEntityManager()
                    .createQuery(strQuery)
                    .getResultList();
            for (MpPrescripcionInsumos per : list) {
                listResult.add(castMpPrescripcionInsumoCorto(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList<>();;
        } catch (Exception e) {
            listResult = new ArrayList<>();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    private MpPrescripcionInsumo castMpPrescripcionInsumoCorto(MpPrescripcionInsumos ent) throws java.lang.Exception {
        MpPrescripcionInsumo obj = new MpPrescripcionInsumo();
        obj.setId(ent.getId());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setCodigoDispositivo(ent.getCodigoDispositivo());
        obj.setCodigoServicioComplementario(ent.getCodigoServicioComplementario());
        return obj;
    }

    private MpPrescripcionTecnologias castMpPrescripcionTecnologias(MpPrescripciones prescripcion, Procedimiento procedimiento) throws java.lang.Exception {
        MpPrescripcionTecnologias tecnologias = new MpPrescripcionTecnologias();
        tecnologias.setMpPrescripcionId(prescripcion);
        tecnologias.setEstado(ESTADO_PRESCRIPCION_SERVICIO_PENDIENTE);
        if (procedimiento.getEstJM() != null) {
            tecnologias.setEstadoJuntaProfesionales(procedimiento.getEstJM());
        }
        tecnologias.setConsecutivoOrden(procedimiento.getConOrden());
        tecnologias.setTipoTecnologia(TIPO_TECNOLOGIA);
        tecnologias.setTipoPrestacion(procedimiento.getTipoPrest());
        if (procedimiento.getCausaS2() != null) {
            tecnologias.setCausaSolicitud2(procedimiento.getCausaS2());
        }
        if (procedimiento.getCausaS3() != null) {
            tecnologias.setCausaSolicitud3(procedimiento.getCausaS3());
        }
        if (procedimiento.getCausaS4() != null) {
            tecnologias.setCausaSolicitud4(procedimiento.getCausaS4());
        }
        if (procedimiento.getCausaS5() != null) {
            tecnologias.setCausaSolicitud5(procedimiento.getCausaS5());
        }
        if (procedimiento.getCausaS6() != null) {
            tecnologias.setCausaSolicitud6(procedimiento.getCausaS6());
        }
        if (procedimiento.getCausaS7() != null) {
            tecnologias.setCausaSolicitud7(procedimiento.getCausaS7());
        }
        if (procedimiento.getCausaS11() != null) {
            tecnologias.setCausaSolicitud11(procedimiento.getCausaS11());
        }
        if (procedimiento.getCausaS12() != null) {
            tecnologias.setCausaSolicitud12(procedimiento.getCausaS12());
        }
        if (procedimiento.getRznCausaS51() != null) {
            tecnologias.setRazonCausaSolicitud51(procedimiento.getRznCausaS51());
        }
        if (procedimiento.getDescRzn51() != null) {
            tecnologias.setDescripcionRazon51(procedimiento.getDescRzn51());
        }
        if (procedimiento.getRznCausaS52() != null) {
            tecnologias.setCodigoRazonCausa52(procedimiento.getRznCausaS52());
        }
        if (procedimiento.getDescRzn52() != null) {
            tecnologias.setDescripcionRazon52(procedimiento.getDescRzn52() != null ? new String(procedimiento.getDescRzn52().getBytes("US-ASCII"), StandardCharsets.UTF_8) : null);
        }

        MaTecnologias tecnologiaProcedimiento = consultarMaTecnologiaPorCups(procedimiento.getCodCUPS());
        if (tecnologiaProcedimiento != null) {
            tecnologias.setMaTecnologiaId(tecnologiaProcedimiento.getId());
            tecnologias.setMaTecnologiaCodigo(tecnologiaProcedimiento.getCups());
            tecnologias.setMaTecnologiaValor(tecnologiaProcedimiento.getCupsDescipcion());
        } else {
            tecnologias.setMaTecnologiaCodigo(procedimiento.getCodCUPS());
            tecnologias.setMaTecnologiaValor(procedimiento.getCodCUPS());
        }
        try {
            tecnologias.setCantidadFormulada(Integer.parseInt(procedimiento.getCanForm()));
        } catch (NumberFormatException ex) {
            tecnologias.setCantidadFormulada(0);
        }
        //tecnologias.setFrecuenciaDeUso(procedimiento.getCodFreUso());
        tecnologias.setCodigoUnidadTiempoFrecuenciaUso(procedimiento.getCodFreUso());
        try {
            tecnologias.setCantidadDuracionTratamiento(Integer.parseInt(procedimiento.getCant()));
        } catch (NumberFormatException ex) {
            tecnologias.setCantidadDuracionTratamiento(0);
        }
        try {
            tecnologias.setCantidadTotal(Integer.parseInt(procedimiento.getCantTotal()));
            tecnologias.setCantidadTotalEntrega(new BigDecimal(procedimiento.getCantTotal()));
            tecnologias.setPendientes(new BigDecimal(procedimiento.getCantTotal()));
        } catch (NumberFormatException ex) {
            tecnologias.setCantidadTotal(0);
            tecnologias.setPendientes(BigDecimal.ZERO);
        }
        try {
            tecnologias.setCodigoPeriodoDuracionTratamiento(procedimiento.getCodPerDurTrat());
        } catch (Exception ex) {
            tecnologias.setCodigoPeriodoDuracionTratamiento(0);
        }
        if (procedimiento.getJustNoPBS() != null) {
            String justificacion = new String(procedimiento.getJustNoPBS().getBytes("US-ASCII"), StandardCharsets.UTF_8);
            tecnologias.setJustificacionNoPbs(justificacion.length() > 512 ? justificacion.substring(0, 512) : justificacion);
        }
        tecnologias.setEntregados(BigDecimal.ZERO);
        tecnologias.setUsuarioCrea("mipres sincroniza");
        tecnologias.setTerminalCrea("127.0.0.1");
        tecnologias.setFechaHoraCrea(new Date());
        tecnologias = validarTecnologia(tecnologias);
        return tecnologias;
    }

    private MpPrescripcionMedicamentos castMpPrescripcionMedicamentos(
            MpPrescripciones prescripcion,
            Medicamento medicamentoDto) throws java.lang.Exception {
        MpPrescripcionMedicamentos medicamento = new MpPrescripcionMedicamentos();
        medicamento.setMpPrescripcionesId(prescripcion);
        medicamento.setEstado(ESTADO_PRESCRIPCION_SERVICIO_PENDIENTE);
        if (medicamentoDto.getEstJM() != null) {
            medicamento.setEstadoJuntaProfesionales(medicamentoDto.getEstJM());
        }
        medicamento.setConsecutivoOrden(medicamentoDto.getConOrden());
        medicamento.setTipoTecnologia(TIPO_MEDICAMENTO);
        if (medicamentoDto.getMedPBSUtilizado() != null) {
            medicamento.setMedPbsUtilizado(medicamentoDto.getMedPBSUtilizado());
        }
        medicamento.setTipoMedicamento(medicamentoDto.getTipoMed());
        if (medicamentoDto.getTipoPrest() != null) {
            medicamento.setTipoPrestacion(medicamentoDto.getTipoPrest());
        }
        //ajuste temporal por tipo de dato
        if (medicamentoDto.getCausaS1() != null && medicamentoDto.getCausaS1() == 1) {
            medicamento.setCausaSolicitud1(true);
        } else {
            medicamento.setCausaSolicitud1(false);
        }
        if (medicamentoDto.getCausaS2() != null && medicamentoDto.getCausaS2() == 1) {
            medicamento.setCausaSolicitud2(true);
        } else {
            medicamento.setCausaSolicitud2(false);
        }
        if (medicamentoDto.getCausaS3() != null && medicamentoDto.getCausaS3() == 1) {
            medicamento.setCausaSolicitud3(true);
        } else {
            medicamento.setCausaSolicitud3(false);
        }
        if (medicamentoDto.getCausaS4() != null && medicamentoDto.getCausaS4() == 1) {
            medicamento.setCausaSolicitud4(true);
        } else {
            medicamento.setCausaSolicitud4(false);
        }
        if (medicamentoDto.getCausaS5() != null && medicamentoDto.getCausaS5() == 1) {
            medicamento.setCausaSolicitud5(true);
        } else {
            medicamento.setCausaSolicitud5(false);
        }
        if (medicamentoDto.getCausaS6() != null && medicamentoDto.getCausaS6() == 1) {
            medicamento.setCausaSolicitud6(true);
        } else {
            medicamento.setCausaSolicitud6(false);
        }
        //ajuste temporal por tipo de dato
        //medicamento.setRazonCausaSolicitud31(medicamentoDto.getRznCausaS31());
        if (medicamentoDto.getRznCausaS31() != null && medicamentoDto.getRznCausaS31() == 1) {
            medicamento.setRazonCausaSolicitud31(true);
            medicamento.setDescripcionRazon31(medicamentoDto.getDescRzn31() != null ? new String(medicamentoDto.getDescRzn31().getBytes("US-ASCII"), StandardCharsets.UTF_8) : null);
        } else {
            medicamento.setRazonCausaSolicitud31(false);
        }
        if (medicamentoDto.getRznCausaS32() != null && medicamentoDto.getRznCausaS32() == 1) {
            medicamento.setRazonCausaSolicitud32(true);
            medicamento.setDescripcionRazon32(medicamentoDto.getDescRzn32() != null ? new String(medicamentoDto.getDescRzn32().getBytes("US-ASCII"), StandardCharsets.UTF_8) : null);
        } else {
            medicamento.setRazonCausaSolicitud32(false);
        }
        if (medicamentoDto.getRznCausaS41() != null && medicamentoDto.getRznCausaS41() == 1) {
            medicamento.setRazonCausaSolicitud41(true);
            medicamento.setDescripcionRazon41(medicamentoDto.getDescRzn41() != null ? new String(medicamentoDto.getDescRzn41().getBytes("US-ASCII"), StandardCharsets.UTF_8) : null);
        } else {
            medicamento.setRazonCausaSolicitud41(false);
        }
        if (medicamentoDto.getRznCausaS42() != null && medicamentoDto.getRznCausaS42() == 1) {
            medicamento.setRazonCausaSolicitud42(true);
            medicamento.setDescripcionRazon42(medicamentoDto.getDescRzn42() != null ? new String(medicamentoDto.getDescRzn42().getBytes("US-ASCII"), StandardCharsets.UTF_8) : null);
        } else {
            medicamento.setRazonCausaSolicitud42(false);
        }
        if (medicamentoDto.getRznCausaS43() != null && medicamentoDto.getRznCausaS43() == 1) {
            medicamento.setRazonCausaSolicitud43(true);
            medicamento.setDescripcionRazon43(medicamentoDto.getDescRzn43() != null ? new String(medicamentoDto.getDescRzn43().getBytes("US-ASCII"), StandardCharsets.UTF_8) : null);
        } else {
            medicamento.setRazonCausaSolicitud43(false);
        }
        if (medicamentoDto.getRznCausaS44() != null && medicamentoDto.getRznCausaS44() == 1) {
            medicamento.setRazonCausaSolicitud44(true);
            medicamento.setDescripcionRazon44(medicamentoDto.getDescRzn44() != null ? new String(medicamentoDto.getDescRzn44().getBytes("US-ASCII"), StandardCharsets.UTF_8) : null);
        } else {
            medicamento.setRazonCausaSolicitud44(false);
        }
        if (medicamentoDto.getDescMedPrinAct() != null) {
            medicamento.setDescripcionMedicamentoPrincipioActivo(new String(medicamentoDto.getDescMedPrinAct().getBytes("US-ASCII"), StandardCharsets.UTF_8));
        }
        medicamento.setCodigoFormulaFarmaceutica(medicamentoDto.getCodFF());
        //medicamento.setFormaFarmaceutica(medicamentoDto.getCodFF());
        medicamento.setCodigoViaAdministracion(medicamentoDto.getCodVA());
        if (medicamentoDto.getJustNoPBS() != null) {
            String justificacion = new String(medicamentoDto.getJustNoPBS().getBytes("US-ASCII"), StandardCharsets.UTF_8);
            medicamento.setJustificacionNoPbs(justificacion.length() > 512 ? justificacion.substring(0, 512) : justificacion);
        }
        if (medicamentoDto.getDosis() != null && !medicamentoDto.getDosis().equals("")) {
            try {
                medicamentoDto.setDosis(medicamentoDto.getDosis().replace(",", "."));
                medicamento.setDosis(new BigDecimal(medicamentoDto.getDosis()));
            } catch (Exception ex) {
                medicamento.setDosis(BigDecimal.ZERO);
            }
        } else {
            medicamento.setDosis(BigDecimal.ZERO);
        }
        //medicamento.setDosisUnidadMedida(medicamentoDto.getDosisUM());
        medicamento.setNumeroFrecuenciaAdministracion(medicamentoDto.getNoFAdmon());
        medicamento.setCodigoFrecuenciaAdministracion(medicamentoDto.getCodFreAdmon());
        medicamento.setIndicacionesEspeciales(medicamentoDto.getIndEsp());
        if (medicamentoDto.getCanTrat() != null && !medicamentoDto.getCanTrat().equals("")) {
            try {
                medicamento.setCantidadTratamiento(Integer.parseInt(medicamentoDto.getCanTrat()));
            } catch (NumberFormatException ex) {
                medicamento.setCantidadTratamiento(0);
            }
        }
        medicamento.setDuracionTratamiento(medicamentoDto.getDurTrat());
        if (medicamentoDto.getCantTotalF() != null && !medicamentoDto.getCantTotalF().equals("")) {
            try {
                medicamentoDto.setCantTotalF(medicamentoDto.getCantTotalF().replace(",", "."));
                if (medicamentoDto.getCantTotalF() != null && medicamentoDto.getCantTotalF().length() > 8) {
                    medicamentoDto.setCantTotalF(medicamentoDto.getCantTotalF().substring(0, 8));
                }
                medicamento.setCantidadTotalEntrega(new BigDecimal(medicamentoDto.getCantTotalF()));
                medicamento.setCantidadTotalFormulada(new BigDecimal(medicamentoDto.getCantTotalF()).setScale(2));
                medicamento.setPendientes(medicamento.getCantidadTotalFormulada());
            } catch (Exception ex) {
                medicamento.setCantidadTotalEntrega(BigDecimal.ZERO);
                medicamento.setCantidadTotalFormulada(BigDecimal.ZERO);
                medicamento.setPendientes(BigDecimal.ZERO);
            }
        }
        if (medicamentoDto.getUFCantTotal() != null) {
            medicamento.setUnidadFarmaceuticaCantidadTotal(medicamentoDto.getUFCantTotal());
        }
        if (medicamentoDto.getIndRec() != null) {
            medicamento.setIndicacionRecibida(new String(medicamentoDto.getIndRec().getBytes("US-ASCII"), StandardCharsets.UTF_8));
        }
        //medicamento.setDescripcionMedicamentoPrincipioActivo(medicamentoDto.getDscMedPA());
        medicamento.setEntregados(BigDecimal.ZERO);
        medicamento.setUsuarioCrea("mipres sincroniza");
        medicamento.setTerminalCrea("127.0.0.1");
        medicamento.setFechaHoraCrea(new Date());
        medicamento = validarMedicamento(medicamento);
        return medicamento;
    }

    private MpMedicamentoPrincipiosActivos castMpMedicamentoPrincipioActivo(
            MpPrescripcionMedicamentos prescripcionMed,
            PrincipioActivo dtoPrinAct) throws java.lang.Exception {
        MpMedicamentoPrincipiosActivos activo = new MpMedicamentoPrincipiosActivos();

        activo.setMpPrescripcionMedicamentosId(prescripcionMed);
        activo.setConsecutivoOrden(dtoPrinAct.getConOrden());
        activo.setCodigoPrincipioActivo(dtoPrinAct.getCodPriAct());
        BigDecimal cantCons = new BigDecimal(dtoPrinAct.getConcCant().replaceAll(",", "."));
        activo.setConcecutivoCantidad(cantCons);
        activo.setUnidadMedidaConcentracion(dtoPrinAct.getUMedConc());
        BigDecimal cantCont = new BigDecimal(dtoPrinAct.getCantCont().replaceAll(",", "."));
        activo.setCantidadContenido(cantCont);
        activo.setUnidadCantidadContenido(dtoPrinAct.getUMedCantCont());
        activo.setUsuarioCrea("mipres sincroniza");
        activo.setTerminalCrea("127.0.0.1");
        activo.setFechaHoraCrea(new Date());
        return activo;
    }

    private MpMedicamentoIndicacionesUnirs castMpMedicamentoIndicacionesUnirs(
            MpPrescripcionMedicamentos prescripcionMed,
            IndicacionUNIRS dtoIndicacion) throws java.lang.Exception {
        MpMedicamentoIndicacionesUnirs indicacion = new MpMedicamentoIndicacionesUnirs();

        indicacion.setMpPrescripcionMedicamentosId(prescripcionMed);

        indicacion.setConsecutivoOrden(dtoIndicacion.getConOrden());
        indicacion.setCodigoIndicacion(dtoIndicacion.getCodIndicacion() + "");
        indicacion.setUsuarioCrea("mipres sincroniza");
        indicacion.setTerminalCrea("127.0.0.1");
        indicacion.setFechaHoraCrea(new Date());
        return indicacion;
    }

    private MpPrescripcionMedicamentos castMpPrescripcionMedicamentosNutricional(MpPrescripciones prescripcion, ProductoNutricional nutricionalDto) throws java.lang.Exception {
        MpPrescripcionMedicamentos medicamento = new MpPrescripcionMedicamentos();
        medicamento.setMpPrescripcionesId(prescripcion);
        medicamento.setEstado(ESTADO_PRESCRIPCION_SERVICIO_PENDIENTE);
        if (nutricionalDto.getEstJM() != null) {
            medicamento.setEstadoJuntaProfesionales(nutricionalDto.getEstJM());
        }
        medicamento.setConsecutivoOrden(nutricionalDto.getConOrden());
        medicamento.setTipoTecnologia(TIPO_PRODUCTO_NUTRICIONAL);
        medicamento.setTipoPrestacion(nutricionalDto.getTipoPrest());
        //ajuste temporal por tipo de dato
        if (nutricionalDto.getCausaS1() != null && nutricionalDto.getCausaS1() == 1) {
            medicamento.setCausaSolicitud1(true);
        } else {
            medicamento.setCausaSolicitud1(false);
        }
        if (nutricionalDto.getCausaS2() != null && nutricionalDto.getCausaS2() == 1) {
            medicamento.setCausaSolicitud2(true);
        } else {
            medicamento.setCausaSolicitud2(false);
        }
        if (nutricionalDto.getCausaS3() != null && nutricionalDto.getCausaS3() == 1) {
            medicamento.setCausaSolicitud3(true);
        } else {
            medicamento.setCausaSolicitud3(false);
        }
        if (nutricionalDto.getCausaS4() != null && nutricionalDto.getCausaS4() == 1) {
            medicamento.setCausaSolicitud4(true);
        } else {
            medicamento.setCausaSolicitud4(false);
        }
        if (nutricionalDto.getCausaS5() != null && nutricionalDto.getCausaS5() == 1) {
            medicamento.setCausaSolicitud5(true);
        } else {
            medicamento.setCausaSolicitud5(false);
        }
        //ajuste temporal por tipo de dato
        //medicamento.setRazonCausaSolicitud31(medicamentoDto.getRznCausaS31());

        if (nutricionalDto.getDescRzn41() != null) {
            medicamento.setRazonCausaSolicitud41(true);
            medicamento.setDescripcionRazon41(new String(nutricionalDto.getDescRzn41().getBytes("US-ASCII"), StandardCharsets.UTF_8));
        }
        if (nutricionalDto.getDescRzn42() != null) {
            medicamento.setRazonCausaSolicitud42(true);
            medicamento.setDescripcionRazon42(new String(nutricionalDto.getDescRzn42().getBytes("US-ASCII"), StandardCharsets.UTF_8));
        }
        if (nutricionalDto.getDescRzn51() != null) {
            medicamento.setRazonCausaSolicitud51(true);
            medicamento.setDescripcionRazon51(new String(nutricionalDto.getDescRzn51().getBytes("US-ASCII"), StandardCharsets.UTF_8));
        }
        if (nutricionalDto.getDescRzn52() != null) {
            medicamento.setRazonCausaSolicitud52(true);
            medicamento.setDescripcionRazon52(new String(nutricionalDto.getDescRzn52().getBytes("US-ASCII"), StandardCharsets.UTF_8));
        }
        if (nutricionalDto.getDescRzn53() != null) {
            medicamento.setRazonCausaSolicitud53(true);
            medicamento.setDescripcionRazon53(new String(nutricionalDto.getDescRzn53().getBytes("US-ASCII"), StandardCharsets.UTF_8));
        }
        if (nutricionalDto.getDescRzn54() != null) {
            medicamento.setRazonCausaSolicitud54(true);
            medicamento.setDescripcionRazon54(new String(nutricionalDto.getDescRzn54().getBytes("US-ASCII"), StandardCharsets.UTF_8));
        }
        if (nutricionalDto.getJustNoPBS() != null) {
            String justificacion = new String(nutricionalDto.getJustNoPBS().getBytes("US-ASCII"), StandardCharsets.UTF_8);
            medicamento.setJustificacionNoPbs(justificacion.length() > 512 ? justificacion.substring(0, 512) : justificacion);
        }
        if (nutricionalDto.getDosis() != null && !nutricionalDto.getDosis().equals("")) {
            try {
                nutricionalDto.setDosis(nutricionalDto.getDosis().replace(",", "."));
                medicamento.setDosis(new BigDecimal(nutricionalDto.getDosis()));
            } catch (Exception ex) {
                medicamento.setDosis(BigDecimal.ZERO);
            }
        } else {
            medicamento.setDosis(BigDecimal.ZERO);
        }
        //medicamento.setDosisUnidadMedida(nutricionalDto.getDosisUM());
        medicamento.setNumeroFrecuenciaAdministracion(nutricionalDto.getNoFAdmon());
        medicamento.setCodigoFrecuenciaAdministracion(nutricionalDto.getCodFreAdmon());
        medicamento.setIndicacionesEspeciales(nutricionalDto.getIndEsp());
        if (nutricionalDto.getCanTrat()
                != null && !nutricionalDto.getCanTrat().equals("")) {
            try {
                medicamento.setCantidadTratamiento(Integer.parseInt(nutricionalDto.getCanTrat()));
            } catch (NumberFormatException ex) {
                medicamento.setCantidadTratamiento(0);
            }
        }
        medicamento.setDuracionTratamiento(nutricionalDto.getDurTrat());
        medicamento.setUnidadFarmaceuticaCantidadTotal(nutricionalDto.getUFCantTotal());
        if (nutricionalDto.getCantTotalF()
                != null && !nutricionalDto.getCantTotalF().equals("")) {
            try {
                nutricionalDto.setCantTotalF(nutricionalDto.getCantTotalF().replace(",", "."));
                medicamento.setCantidadTotalFormulada(new BigDecimal(nutricionalDto.getCantTotalF()));
                if (nutricionalDto.getCantTotalF().length() > 8) {
                    nutricionalDto.setCantTotalF(nutricionalDto.getCantTotalF().substring(0, 8));
                }
                medicamento.setCantidadTotalEntrega(new BigDecimal(nutricionalDto.getCantTotalF()).setScale(2));
                medicamento.setPendientes(medicamento.getCantidadTotalFormulada());
            } catch (Exception ex) {
                medicamento.setCantidadTotalEntrega(BigDecimal.ZERO);
            }
        }
        if (nutricionalDto.getDXVIH()
                != null && nutricionalDto.getDXVIH() == 0) {
            medicamento.setEsDiagnosticoVih(Boolean.FALSE);
        } else {
            medicamento.setEsDiagnosticoVih(Boolean.TRUE);
        }
        if (nutricionalDto.getDXCaPal()
                != null && nutricionalDto.getDXCaPal() == 0) {
            medicamento.setEsDiagnosticoCancer(Boolean.FALSE);
        } else {
            medicamento.setEsDiagnosticoCancer(Boolean.TRUE);
        }
        if (nutricionalDto.getDXEnfRCEV()
                != null && nutricionalDto.getDXEnfRCEV() == 0) {
            medicamento.setEsDiagnosticoEnfermedadRenal(Boolean.FALSE);
        } else {
            medicamento.setEsDiagnosticoEnfermedadRenal(Boolean.TRUE);
        }
        if (nutricionalDto.getDXDesPro()
                != null && nutricionalDto.getDXDesPro() == 0) {
            medicamento.setEsDiagnosticoDesnutricion(Boolean.FALSE);
        } else {
            medicamento.setEsDiagnosticoDesnutricion(Boolean.TRUE);
        }
        //medicamento.setUnidadFarmaceuticaCantidadTotal(nutricionalDto.getCantTotalF());
        //medicamento.setUnidadFarmaceuticaCantidadTotal(nutricionalDto.getUFCantTotal());
        medicamento.setIndicacionRecibida(new String(nutricionalDto.getIndRec().getBytes("US-ASCII"), StandardCharsets.UTF_8));
        medicamento.setTipoProductoNutricional(nutricionalDto.getTippProNut());
        medicamento.setDescripcionProductoNutricional(nutricionalDto.getDescProdNutr());
        if (nutricionalDto.getDescProdNutr() != null && !nutricionalDto.getDescProdNutr().equals("")) {
            Maestro maeProdNutr = getMaestroRemoto()
                    .consultarPorValorTipo(
                            nutricionalDto.getDescProdNutr(),
                            MaestroTipo.MP_PRODUCTOS_NUTRICIONALES);
            if (maeProdNutr != null) {
                medicamento.setMaeProductosNutricionalesId(maeProdNutr.getId());
                medicamento.setMaeProductosNutricionalesCodigo(maeProdNutr.getValor());
                medicamento.setMaeProductosNutricionalesValor(maeProdNutr.getNombre());
            }
        }
        medicamento.setCodigoViaAdministracion(nutricionalDto.getCodViaAdmon() + "");
        medicamento.setEntregados(BigDecimal.ZERO);
        medicamento.setUsuarioCrea("mipres sincroniza");
        medicamento.setTerminalCrea("127.0.0.1");
        medicamento.setFechaHoraCrea(new Date());
        medicamento = validarMedicamentoNutricionales(medicamento);
        return medicamento;
    }

    private MpPrescripcionInsumos castMpPrescripcionInsumos(MpPrescripciones prescripcion, Dispositivo dispositivoDto) throws java.lang.Exception {
        MpPrescripcionInsumos insumo = new MpPrescripcionInsumos();
        insumo.setMpPrescripcionId(prescripcion);
        insumo.setEstado(ESTADO_PRESCRIPCION_SERVICIO_PENDIENTE);
        insumo.setEstadoJuntaProfesionales(dispositivoDto.getEstJM());
        insumo.setConsecutivoOrden(dispositivoDto.getConOrden());
        insumo.setTipoPrestacion(dispositivoDto.getTipoPrest());
        insumo.setTipoTecnologia(TIPO_DISPOSITIVO_MEDICO);
        insumo.setCausaSolicitud1(dispositivoDto.getCausaS1());
        insumo.setCodigoDispositivo(dispositivoDto.getCodDisp());
        if (dispositivoDto.getCodDisp() != null && !dispositivoDto.getCodDisp().equals("")) {
            Maestro maeDisp = getMaestroRemoto()
                    .consultarPorValorTipo(
                            dispositivoDto.getCodDisp(),
                            MaestroTipo.MP_DISPOSITIVOS_MEDICOS);
            if (maeDisp != null) {
                insumo.setMaeDispositivosId(maeDisp.getId());
                insumo.setMaeDispositivosCodigo(maeDisp.getValor());
                insumo.setMaedispositivosValor(maeDisp.getNombre());
            }
        }

        insumo.setCantidad(dispositivoDto.getCant());
        insumo.setCantidadTotalEntrega(new BigDecimal(dispositivoDto.getCantTotal()));
        insumo.setFrecuenciaUso(dispositivoDto.getCadaFreUso());
        insumo.setCodFrecuenciaUso(dispositivoDto.getCodFreUso());
        insumo.setCantidadFormulada(dispositivoDto.getCanForm());
        try {
            insumo.setPendiente(new BigDecimal(dispositivoDto.getCantTotal()).setScale(2));
        } catch (NumberFormatException ex) {
            insumo.setPendiente(BigDecimal.ZERO);
        }
        if (dispositivoDto.getJustNoPBS() != null) {
            String justificacion = new String(dispositivoDto.getJustNoPBS().getBytes("US-ASCII"), StandardCharsets.UTF_8);
            insumo.setJustificacionNoPbs(justificacion.length() > 512 ? justificacion.substring(0, 512) : justificacion);
        }
        Integer maxSize = 120;
        if (dispositivoDto.getIndRec() != null) {
            if (dispositivoDto.getIndRec().length() > maxSize) {
                String indicaciones = dispositivoDto.getIndRec().substring(0, maxSize);
                insumo.setIndicacionesRecomendaciones(new String(indicaciones.getBytes("US-ASCII"), StandardCharsets.UTF_8));
            } else {
                insumo.setIndicacionesRecomendaciones(new String(dispositivoDto.getIndRec().getBytes("US-ASCII"), StandardCharsets.UTF_8));
            }
        }
        try {
            insumo.setCodPerDurTrat(dispositivoDto.getCodPerDurTrat());
        } catch (Exception ex) {
            insumo.setCodPerDurTrat(0);
        }
        insumo.setEntregados(BigDecimal.ZERO);
        insumo.setUsuarioCrea("mipres sincroniza");
        insumo.setTerminalCrea("127.0.0.1");
        insumo.setFechaHoraCrea(new Date());
        insumo = validarInsumo(insumo);
        return insumo;
    }

    private MpPrescripcionInsumos castMpPrescripcionInsumosServComplementario(MpPrescripciones prescripcion, ServicioComplementario servComplementario) throws Exception {
        MpPrescripcionInsumos insumo = new MpPrescripcionInsumos();
        insumo.setMpPrescripcionId(prescripcion);
        insumo.setEstado(ESTADO_PRESCRIPCION_SERVICIO_PENDIENTE);
        if (servComplementario.getEstJM() != null) {
            insumo.setEstadoJuntaProfesionales(servComplementario.getEstJM());
        }

        insumo.setConsecutivoOrden(servComplementario.getConOrden());
        insumo.setTipoPrestacion(servComplementario.getTipoPrest());
        insumo.setTipoTecnologia(TIPO_SERVICIO_COMPLEMENTARIO);
        if (servComplementario.getCausaS1() != null) {
            insumo.setCausaSolicitud1(servComplementario.getCausaS1());
        }
        if (servComplementario.getCausaS2() != null) {
            insumo.setCausaSolicitud2(servComplementario.getCausaS2());
        }
        if (servComplementario.getCausaS3() != null) {
            insumo.setCausaSolicitud3(servComplementario.getCausaS3());
        }
        if (servComplementario.getCausaS4() != null) {
            insumo.setCausaSolicitud4(servComplementario.getCausaS4());
        }
        if (servComplementario.getCausaS5() != null) {
            insumo.setCausaSolicitud5(servComplementario.getCausaS5());
        }

        insumo.setCantidad(servComplementario.getCant());
        insumo.setFrecuenciaUso(servComplementario.getCadaFreUso());
        if (servComplementario.getDescCausaS4() != null) {
            insumo.setDescripcionCausa4(new String(servComplementario.getDescCausaS4().getBytes("US-ASCII"), StandardCharsets.UTF_8));
        }
        insumo.setCantidadFormulada(servComplementario.getCanForm());
        try {
            insumo.setCantidadTotalEntrega(new BigDecimal(servComplementario.getCantTotal()));
            insumo.setPendiente(new BigDecimal(servComplementario.getCantTotal()).setScale(2));
        } catch (NumberFormatException ex) {
            insumo.setPendiente(BigDecimal.ZERO);
        }
        insumo.setCodigoServicioComplementario(Integer.parseInt(servComplementario.getCodSerComp()));
        if (servComplementario.getCodSerComp() != null && !servComplementario.getCodSerComp().equals("")) {
            Maestro maeDisp = getMaestroRemoto()
                    .consultarPorValorTipo(
                            servComplementario.getCodSerComp(),
                            MaestroTipo.MP_SERVICIOS_COMPLEMENTARIOS);
            if (maeDisp != null) {
                insumo.setMaeServiciosComplementariosId(maeDisp.getId());
                insumo.setMaeServiciosComplementariosCodigo(maeDisp.getValor());
                insumo.setMaeServiciosComplementariosValor(maeDisp.getNombre());
            }
        }
        insumo.setTipoTransporte(servComplementario.getTipoTrans() != null ? servComplementario.getTipoTrans().toString() : null);
        insumo.setReqAcom(servComplementario.getReqAcom() != null ? servComplementario.getReqAcom().toString() : null);
        insumo.setNumeroDocumentoAcompanante(servComplementario.getNroIDAcomAlb());
        insumo.setTipoDocumentoAcompanante(servComplementario.getTipoIDAcomAlb());
        insumo.setParentezcoAcompanante(servComplementario.getParentAcomAlb() != null ? servComplementario.getParentAcomAlb().toString() : null);
        insumo.setNombreAlb(servComplementario.getNombAlb());
        insumo.setCodigoMunicipioOrigenAlb(servComplementario.getCodMunOriAlb() != null ? servComplementario.getCodMunOriAlb().toString() : null);
        insumo.setCodigoMunicipioDestinoAlb(servComplementario.getCodMunDesAlb() != null ? servComplementario.getCodMunDesAlb().toString() : null);
        if (servComplementario.getJustNoPBS() != null) {
            String justificacion = new String(servComplementario.getJustNoPBS().getBytes("US-ASCII"), StandardCharsets.UTF_8);
            insumo.setJustificacionNoPbs(justificacion.length() > 512 ? justificacion.substring(0, 512) : justificacion);
        }
        Integer maxSize = 120;
        if (servComplementario.getIndRec() != null) {
            if (servComplementario.getIndRec().length() > maxSize) {
                String indicaciones = servComplementario.getIndRec().substring(0, maxSize);
                insumo.setIndicacionesRecomendaciones(new String(indicaciones.getBytes("US-ASCII"), StandardCharsets.UTF_8));
            } else {
                insumo.setIndicacionesRecomendaciones(new String(servComplementario.getIndRec().getBytes("US-ASCII"), StandardCharsets.UTF_8));
            }
        }

        try {
            insumo.setCodPerDurTrat(servComplementario.getCodPerDurTrat());
        } catch (Exception ex) {
            insumo.setCodPerDurTrat(0);
        }
        insumo.setDescripcionCausa4(servComplementario.getDescSerComp());
        insumo.setEntregados(BigDecimal.ZERO);
        insumo.setUsuarioCrea("mipres sincroniza");
        insumo.setTerminalCrea("127.0.0.1");
        insumo.setFechaHoraCrea(new Date());
        insumo = insumoServComplementario(insumo);
        return insumo;
    }

    //CONSULTAS 
    private MaTecnologias consultarMaTecnologiaPorCups(String cups) throws Exception {
        MaTecnologias objRes = null;
        try {
            String strQuery = "FROM MaTecnologias m " + "WHERE m.cups ='" + cups + "'";
            List<MaTecnologias> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && !list.isEmpty()) {
                objRes = list.get(0);
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private GnMaestros consultarMaestroPorValorTipo(String valor, String tipo) throws Exception {
        GnMaestros objRes = null;
        try {
            String strQuery = "FROM GnMaestros m "
                    + "WHERE m.valor ='" + valor + "'"
                    + " AND m.tipo ='" + tipo + "'";
            List<GnMaestros> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && !list.isEmpty()) {
                objRes = list.get(0);
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private AsegAfiliados consultarAfiliado(String tipodocumento, String numeroDocumento) throws Exception {
        AsegAfiliados afiliado = null;
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + "WHERE p.maeTipoDocumentoId = '" + tipodocumento + "' "
                    + " AND p.numeroDocumento = '" + numeroDocumento + "' "
                    + " AND p.maeEstadoAfiliacionId = '134'";

            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && !list.isEmpty()) {
                afiliado = list.get(0);
            }

        } catch (NoResultException e) {
//            System.out.println(e);
            afiliado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return afiliado;
    }

    private MaDiagnosticos consultarDiagnosticoPorCodigo(String codigo) throws Exception {
        MaDiagnosticos objRes = null;
        try {
            String strQuery = "FROM MaDiagnosticos m "
                    + "WHERE m.codigo ='" + codigo + "'";

            objRes = (MaDiagnosticos) getEntityManager().createQuery(strQuery).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private CntProfesionales consultarProfesionalPorTipoDocumentoNumeroDocumentoRm(String tipoDocumento, String numeroDocumento, String registroMedico) throws Exception {
        CntProfesionales objRes = null;
        try {
            String strQuery = "FROM CntProfesionales m "
                    + "WHERE m.maeTipoDocumentoCodigo ='" + tipoDocumento + "'"
                    + "AND m.documento ='" + numeroDocumento + "'"
                    + "AND m.registroMedico ='" + registroMedico + "'";
            List<CntProfesionales> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && !list.isEmpty()) {
                objRes = list.get(0);
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private CntProfesionales consultarProfesionalPorTipoDocumentoNumeroDocumento(String tipoDocumento, String numeroDocumento) throws Exception {
        CntProfesionales objRes = null;
        try {
            String strQuery = "FROM CntProfesionales m "
                    + "WHERE m.maeTipoDocumentoCodigo ='" + tipoDocumento + "'"
                    + "AND m.documento ='" + numeroDocumento + "'";

            List<CntProfesionales> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && !list.isEmpty()) {
                objRes = list.get(0);
            }
        } catch (NoResultException e) {
//            System.out.println(e);
            objRes = null;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public String obtenerUltimaFechaEjecucionServicio(String servicio) throws java.lang.Exception {
        String fecha = "";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //        se reemplaza la consulta 
            String strQuery = "SELECT MAX(m.periodo) FROM MpConsumos m "
                    + "WHERE m.servicio = :servicio "
                    + "AND m.estado = 2";

            TypedQuery<Date> query = getEntityManager().createQuery(strQuery, Date.class);
            query.setParameter("servicio", servicio);
            Date periodo = query.getSingleResult();
            if (periodo != null) {
                fecha = formato.format(periodo);
            }
        } catch (NoResultException e) {
            fecha = "";
        } catch (Exception e) {
//        System.out.println(e);
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return fecha;
    }

    @Override
    public MpConsumo consultarUltimoConsumo(String servicio) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        MpConsumo consumo;
        try {
            String strQuery = "SELECT m FROM MpConsumos m "
                    + "WHERE m.servicio = :servicio "
                    + "AND m.estado = 2 "
                    + "ORDER BY m.id DESC ";
            MpConsumos entRes = getEntityManager()
                    .createQuery(strQuery, MpConsumos.class)
                    .setParameter("servicio", servicio)
                    .setMaxResults(1)
                    .getSingleResult();
            consumo = new MpConsumo();
            consumo.setId(entRes.getId());
            consumo.setPeriodo(formato.format(entRes.getPeriodo()));
            consumo.setServicio(entRes.getServicio());
            consumo.setRegistros(entRes.getRegistros());
            consumo.setRegistrosExitosos(entRes.getRegistrosExitosos());
            consumo.setEstado(entRes.getEstado());
            consumo.setObservacion(entRes.getObservacion());
            consumo.setFechaHoraInicio(entRes.getFechaHoraInicio());
            consumo.setFechaHoraFin(entRes.getFechaHoraFin());
        } catch (NoResultException e) {
            consumo = null;
        } catch (Exception e) {
            consumo = null;
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return consumo;
    }

//    private MpConsumos BucarMpConsumosPorId(Integer id) throws Exception {
//        MpConsumos objRes = null;
//        try {
//            String strQuery = "FROM MpConsumos m " + "WHERE m.id = " + id;
//            List<MpConsumos> list = getEntityManager().createQuery(strQuery).getResultList();
//            if (list != null && !list.isEmpty()) {
//                objRes = list.get(0);
//            }
//        } catch (NoResultException e) {
////            System.out.println(e);
//            objRes = null;
//        } catch (Exception e) {
////            System.out.println(e);
//            Exception(CONSULTAR, e);
//        } finally {
//            cerrarEntityManager();
//        }
//        return objRes;
//    }
    private MpConsumos BucarMpConsumosPorId(Integer id) throws Exception {
        MpConsumos objRes = null;
        try {
            String strQuery = "SELECT m FROM MpConsumos m WHERE m.id = :id";
            objRes = getEntityManager()
                    .createQuery(strQuery, MpConsumos.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            throw new Exception("Error al buscar el objeto MpConsumos con id " + id, e);
        }
        cerrarEntityManager();
        return objRes;
    }

    private MpPrescripciones consultarPrescripcionPorNumero(String numPrescripcion) throws Exception {
        MpPrescripciones objRes = null;
        try {
            String strQuery = "FROM MpPrescripciones m "
                    + "WHERE m.numeroPrescripcion ='" + numPrescripcion + "'";

            List<MpPrescripciones> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && !list.isEmpty()) {
                objRes = list.get(0);
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

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
//            System.out.println(e);
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    //VALIDACIONES
    private MpPrescripciones validarPrescripcion(MpPrescripciones prescripcion) {
        boolean bloqueo = false;
        //Validar si tiene aseg_afiliados_id
        if (prescripcion.getAsegAfiliadosId() == null || prescripcion.getAsegAfiliadosId().getId() == null) {
            bloqueo = true;
        }
        //Validar si tiene ma_tipo_documento_prestador_codigo
        if (prescripcion.getMaTipoDocumentoPrestadorCodigo() == null) {
            bloqueo = true;
        }
        //Validar si tiene un profesional asignado
        if (prescripcion.getCntProfesionalesId() == null) {
            bloqueo = true;
        }
        //Validar si tiene numero_prescripcion
        if (prescripcion.getNumeroPrescripcion() == null) {
            bloqueo = true;
        }
        //Validar si tiene fecha_prescripcion
        if (prescripcion.getFechaPrescripcion() == null) {
            bloqueo = true;
        }
        //Validar si tiene usuario_crea
        if (prescripcion.getUsuarioCrea() == null) {
            bloqueo = true;
        }
        //Validar si tiene terminal_crea
        if (prescripcion.getTerminalCrea() == null) {
            bloqueo = true;
        }
        //Validar si tiene fecha_hora_crea
        if (prescripcion.getFechaHoraCrea() == null) {
            bloqueo = true;
        }
        if (bloqueo) {
            prescripcion = null;
        }
        return prescripcion;
    }

    private MpPrescripcionTecnologias validarTecnologia(MpPrescripcionTecnologias tecnologias) {
        boolean bloqueo = false;
        //Validar si tiene mp_prescripcion_id
        if (tecnologias.getMpPrescripcionId() == null) {
            bloqueo = true;
        }
        //Validar si tiene usuario_crea
        if (tecnologias.getUsuarioCrea() == null) {
            bloqueo = true;
        }
        //Validar si tiene terminal_crea
        if (tecnologias.getTerminalCrea() == null) {
            bloqueo = true;
        }
        //Validar si tiene fecha_hora_crea
        if (tecnologias.getFechaHoraCrea() == null) {
            bloqueo = true;
        }
        if (bloqueo) {
            tecnologias = null;
        }
        return tecnologias;
    }

    private MpPrescripcionMedicamentos validarMedicamento(MpPrescripcionMedicamentos medicamento) {
        boolean bloqueo = false;
        //Validar si tiene mp_prescripcion_id
        if (medicamento.getMpPrescripcionesId() == null) {
            bloqueo = true;
        }
        //Validar si tiene usuario_crea
        if (medicamento.getUsuarioCrea() == null) {
            bloqueo = true;
        }
        //Validar si tiene terminal_crea
        if (medicamento.getTerminalCrea() == null) {
            bloqueo = true;
        }
        //Validar si tiene fecha_hora_crea
        if (medicamento.getFechaHoraCrea() == null) {
            bloqueo = true;
        }
        if (bloqueo) {
            medicamento = null;
        }
        return medicamento;
    }

    private MpPrescripcionInsumos validarInsumo(MpPrescripcionInsumos insumo) {
        boolean bloqueo = false;
        //Validar si tiene mp_prescripcion_id
        if (insumo.getMpPrescripcionId() == null) {
            bloqueo = true;
        }
        //Validar si tiene usuario_crea
        if (insumo.getUsuarioCrea() == null) {
            bloqueo = true;
        }
        //Validar si tiene terminal_crea
        if (insumo.getTerminalCrea() == null) {
            bloqueo = true;
        }
        //Validar si tiene fecha_hora_crea
        if (insumo.getFechaHoraCrea() == null) {
            bloqueo = true;
        }
        if (bloqueo) {
            insumo = null;
        }
        return insumo;
    }

    private MpPrescripcionMedicamentos validarMedicamentoNutricionales(MpPrescripcionMedicamentos medicamento) {
        boolean bloqueo = false;
        //Validar si tiene mp_prescripcion_id
        if (medicamento.getMpPrescripcionesId() == null) {
            bloqueo = true;
        }
        //Validar si tiene usuario_crea
        if (medicamento.getUsuarioCrea() == null) {
            bloqueo = true;
        }
        //Validar si tiene terminal_crea
        if (medicamento.getTerminalCrea() == null) {
            bloqueo = true;
        }
        //Validar si tiene fecha_hora_crea
        if (medicamento.getFechaHoraCrea() == null) {
            bloqueo = true;
        }
        if (bloqueo) {
            medicamento = null;
        }
        return medicamento;
    }

    private MpPrescripcionInsumos insumoServComplementario(MpPrescripcionInsumos insumo) {
        boolean bloqueo = false;
        //Validar si tiene mp_prescripcion_id
        if (insumo.getMpPrescripcionId() == null) {
            bloqueo = true;
        }
        //Validar si tiene usuario_crea
        if (insumo.getUsuarioCrea() == null) {
            bloqueo = true;
        }
        //Validar si tiene terminal_crea
        if (insumo.getTerminalCrea() == null) {
            bloqueo = true;
        }
        //Validar si tiene fecha_hora_crea
        if (insumo.getFechaHoraCrea() == null) {
            bloqueo = true;
        }
        if (bloqueo) {
            insumo = null;
        }
        return insumo;
    }

    @Override
    public Map<String, AsegAfiliado> consultarListaAfiliados(List<Prescripcion> listaPrescripciones, HashMap<String, Maestro> hashMaestrosTipoDocumento) throws Exception {
        Map<String, AsegAfiliado> listaAfiliados = new HashMap<>();
        String strQuery = "select id, mae_tipo_documento_id,mae_tipo_documento_codigo, numero_documento,"
                + "primer_nombre, segundo_nombre, primer_apellido,segundo_apellido, "
                + "mae_genero_id, mae_genero_codigo, mae_genero_valor,"
                + "mae_estado_afiliacion_id, mae_estado_afiliacion_codigo, mae_estado_afiliacion_valor  "
                + "from aseg_afiliados WHERE (mae_tipo_documento_id, numero_documento) IN ";
        String whereIn = "(";
        for (Prescripcion prescripcion : listaPrescripciones) {
            if (prescripcion.getPrescripcion().getTipoIDPaciente() != null
                    && !prescripcion.getPrescripcion().getTipoIDPaciente().equals("")
                    && prescripcion.getPrescripcion().getNroIDPaciente() != null
                    && !prescripcion.getPrescripcion().getNroIDPaciente().equals("")) {
                whereIn += "(" + obtenerIdDocumento(prescripcion.getPrescripcion().getTipoIDPaciente(), hashMaestrosTipoDocumento) + ",";
                whereIn += "'" + prescripcion.getPrescripcion().getNroIDPaciente() + "'),";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ")";
        strQuery += whereIn;
        List<Object[]> listObjAfiliados = null;
        try {
            listObjAfiliados = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {
//            System.out.println("error:  " + ex.getMessage());
        } finally {
            cerrarEntityManager();
        }
        if (listObjAfiliados != null) {
            for (Object[] obj : listObjAfiliados) {
                try {
                    AsegAfiliado afiliado = new AsegAfiliado();
                    afiliado.setId((Integer) (obj[0]));
                    afiliado.setMaeTipoDocumento((int) (obj[1]));
                    afiliado.setNumeroDocumento((String) (obj[3]));
                    afiliado.setPrimerNombre((String) (obj[4]));
                    afiliado.setSegundoNombre((String) (obj[5]));
                    afiliado.setPrimerApellido((String) (obj[6]));
                    afiliado.setSegundoApellido((String) (obj[7]));
                    afiliado.setMaeGeneroId((int) (obj[8]));
                    afiliado.setMaeGeneroCodigo((String) (obj[9]));
                    afiliado.setMaeGeneroValor((String) (obj[10]));
                    afiliado.setMaeEstadoAfiliacion((int) (obj[11]));
                    afiliado.setMaeEstadoCivilCodigo((String) (obj[12]));
                    afiliado.setMaeEstadoAfiliacionValor((String) (obj[13]));
                    listaAfiliados.put(afiliado.getMaeTipoDocumento() + "||" + afiliado.getNumeroDocumento().trim(), afiliado);
                } catch (Exception ex) {
//                    System.out.println("error:  " + ex.getMessage());
                }
            }
        }

        return listaAfiliados;
    }

    @Override
    public Map<String, AsegAfiliado> consultarListaAfiliadosRec(
            List<PrescripcionRecobrante> listaPrescripciones,
            HashMap<String, Maestro> hashMaestrosTipoDocumento) throws Exception {
        Map<String, AsegAfiliado> listaAfiliados = new HashMap<>();
        try {
            String strQuery = "select id, mae_tipo_documento_id,mae_tipo_documento_codigo, numero_documento,"
                    + "primer_nombre, segundo_nombre, primer_apellido,segundo_apellido, "
                    + "mae_genero_id, mae_genero_codigo, mae_genero_valor,"
                    + "mae_estado_afiliacion_id, mae_estado_afiliacion_codigo, mae_estado_afiliacion_valor  "
                    + "from aseg_afiliados WHERE (mae_tipo_documento_id, numero_documento) IN ";
            String whereIn = "(";
            for (PrescripcionRecobrante prescripcion : listaPrescripciones) {
                if (prescripcion.getTutela().getTipoIDPaciente() != null
                        && !prescripcion.getTutela().getTipoIDPaciente().equals("")
                        && prescripcion.getTutela().getNroIDPaciente() != null
                        && !prescripcion.getTutela().getNroIDPaciente().equals("")) {
                    whereIn += "(" + obtenerIdDocumento(prescripcion.getTutela().getTipoIDPaciente(), hashMaestrosTipoDocumento) + ",";
                    whereIn += "'" + prescripcion.getTutela().getNroIDPaciente() + "'),";
                }
            }
            //Remover ultima ,
            if (!whereIn.equals("(")) {
                whereIn = whereIn.substring(0, whereIn.length() - 1);
            }
            whereIn += ")";
            strQuery += whereIn;
            List<Object[]> listObjAfiliados = null;
            try {
                listObjAfiliados = getEntityManager().createNativeQuery(strQuery).getResultList();
            } catch (Exception ex) {
//            System.out.println("error:  " + ex.getMessage());
            } finally {
                cerrarEntityManager();
            }
            if (listObjAfiliados != null) {
                for (Object[] obj : listObjAfiliados) {
                    try {
                        AsegAfiliado afiliado = new AsegAfiliado();
                        afiliado.setId((Integer) (obj[0]));
                        afiliado.setMaeTipoDocumento((int) (obj[1]));
                        afiliado.setNumeroDocumento((String) (obj[3]));
                        afiliado.setPrimerNombre((String) (obj[4]));
                        afiliado.setSegundoNombre((String) (obj[5]));
                        afiliado.setPrimerApellido((String) (obj[6]));
                        afiliado.setSegundoApellido((String) (obj[7]));
                        afiliado.setMaeGeneroId((int) (obj[8]));
                        afiliado.setMaeGeneroCodigo((String) (obj[9]));
                        afiliado.setMaeGeneroValor((String) (obj[10]));
                        afiliado.setMaeEstadoAfiliacion((int) (obj[11]));
                        afiliado.setMaeEstadoCivilCodigo((String) (obj[12]));
                        afiliado.setMaeEstadoAfiliacionValor((String) (obj[13]));
                        listaAfiliados.put(afiliado.getMaeTipoDocumento() + "||" + afiliado.getNumeroDocumento().trim(), afiliado);
                    } catch (Exception ex) {
//                    System.out.println("error:  " + ex.getMessage());
                    }
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error consultarListaAfiliadosRec  - " + ex.getMessage());
        }

        return listaAfiliados;
    }

    @Override
    public Map<String, MpPrescripcionMedicamento> consultarListaMedicamentos(List<Direccionamiento> listaDireccionamientos) throws Exception {
        Map<String, MpPrescripcionMedicamento> listaMedicamentos = new HashMap<>();
        try {
            String strQuery = "select "
                    + "mm.id, "
                    + "mm.consecutivo_orden, "
                    + "mm.tipo_tecnologia, "
                    + "mm.cantidad_total_entrega, "
                    + "mm.entregados, "
                    + "mm.pendientes, "
                    + "mm.duracion_tratamiento, "
                    + "mm.cantidad_tratamiento, "
                    + "mm.cantidad_total_formulada, "
                    + "mp.id AS id_prescripcion, "
                    + "mp.prestador_razon_social, "
                    + "mp.numero_prescripcion, "
                    + "mp.ma_tipo_documento_prestador_id, "
                    + "mp.ma_tipo_documento_prestador_codigo, "
                    + "mp.ma_tipo_documento_prestador_valor, "
                    + "mp.prestador_numero_documento, "
                    + "mp.sede_codigo_habilitacion "
                    + "FROM mp_prescripcion_medicamentos mm INNER JOIN mp_prescripciones mp "
                    + "ON mp.id = mm.mp_prescripciones_id "
                    + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
            String whereIn = "(";
            for (Direccionamiento direccionamiento : listaDireccionamientos) {
                if (direccionamiento.getNoPrescripcion() != null && !direccionamiento.getNoPrescripcion().equals("")
                        && direccionamiento.getConTec() != null && direccionamiento.getTipoTec() != null) {
                    whereIn += "('" + direccionamiento.getNoPrescripcion() + "',";
                    whereIn += direccionamiento.getConTec() + ",";
                    whereIn += "'" + convertirTipoTec(direccionamiento.getTipoTec()) + "'),";
                }
            }
            //Remover ultima ,
            if (!whereIn.equals("(")) {
                whereIn = whereIn.substring(0, whereIn.length() - 1);
            }
            whereIn += ") AND id_direccionamiento IS NULL ";
            strQuery += whereIn;
            List<Object[]> listObjMedicamentos = null;
            try {
                listObjMedicamentos = getEntityManager().createNativeQuery(strQuery).getResultList();
            } catch (Exception ex) {
                // 
            } finally {
                cerrarEntityManager();
            }
            if (listObjMedicamentos != null) {
                for (Object[] obj : listObjMedicamentos) {
                    MpPrescripcionMedicamento medicamento = new MpPrescripcionMedicamento();
                    medicamento.setId((Integer) (obj[0]));
                    medicamento.setConsecutivoOrden((Integer) (obj[1]));
                    medicamento.setTipoTecnologia((Integer) (obj[2]));
                    medicamento.setCantidadTotalEntrega((BigDecimal) obj[3]);
                    medicamento.setEntregados((BigDecimal) obj[4]);
                    medicamento.setPendientes((BigDecimal) obj[5]);
                    medicamento.setDuracionTratamiento((Integer) obj[6]);
                    medicamento.setCantidadTratamiento((Integer) obj[7]);
                    medicamento.setCantidadTotalFormulada((BigDecimal) obj[8]);
                    medicamento.setMpPrescripcion(new MpPrescripcion((Integer) (obj[9])));
                    medicamento.getMpPrescripcion().setPrestadorRazonSocial((String) obj[10]);
                    medicamento.getMpPrescripcion().setNumeroPrescripcion((String) obj[11]);
                    medicamento.getMpPrescripcion().setMaTipoDocumentoPrestadorId((Integer) (obj[12]));
                    medicamento.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo((String) (obj[13]));
                    medicamento.getMpPrescripcion().setMaTipoDocumentoPrestadorValor((String) (obj[14]));
                    medicamento.getMpPrescripcion().setPrestadorNumeroDocumento((String) (obj[15]));
                    medicamento.getMpPrescripcion().setSedeCodigoHabilitacion((String) (obj[16]));
                    listaMedicamentos.put(medicamento.getMpPrescripcion().getNumeroPrescripcion() + "||" + medicamento.getConsecutivoOrden(), medicamento);
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error consultarListaMedicamentos  - " + ex.getMessage());
        }
        return listaMedicamentos;
    }

    @Override
    public Map<String, MpPrescripcionMedicamento> consultarListaMedicamentos2(List<Direccionamiento> listaDireccionamientos) throws Exception {
        Map<String, MpPrescripcionMedicamento> listaMedicamentos = new HashMap<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("FROM MpPrescripcionMedicamentos p ")
                    .append("WHERE (p.mpPrescripcionesId.numeroPrescripcion, p.consecutivoOrden, p.tipoTecnologia) IN (");
            StringBuilder strWhere = new StringBuilder();
            for (Direccionamiento direccionamiento : listaDireccionamientos) {
                if (direccionamiento.getNoPrescripcion() != null && !direccionamiento.getNoPrescripcion().equals("")
                        && direccionamiento.getConTec() != null && direccionamiento.getTipoTec() != null) {
                    strWhere.append("('").append(direccionamiento.getNoPrescripcion())
                            .append("',").append(direccionamiento.getConTec())
                            .append(",").append(convertirTipoTec(direccionamiento.getTipoTec())).append("),");
                }
            }
            String whereIn = strWhere.toString();
            whereIn = whereIn.substring(0, whereIn.length() - 1);
            strQuery.append(whereIn);
            strQuery.append(")");
            List<MpPrescripcionMedicamentos> list = getEntityManager()
                    .createQuery(strQuery.toString())
                    .getResultList();

            for (MpPrescripcionMedicamentos tec : list) {
                MpPrescripcionMedicamento tecnologia = new MpPrescripcionMedicamento();
                tecnologia.setId(tec.getId());
                tecnologia.setConsecutivoOrden(tec.getConsecutivoOrden());
                tecnologia.setTipoTecnologia(tec.getTipoTecnologia());
                tecnologia.setCantidadTotalEntrega(tec.getCantidadTotalEntrega());
                tecnologia.setEntregados(tec.getEntregados());
                tecnologia.setPendientes(tec.getPendientes());
                tecnologia.setDuracionTratamiento(tec.getDuracionTratamiento());
                tecnologia.setCantidadTratamiento(tec.getCantidadTratamiento());
                tecnologia.setCantidadTotalFormulada((tec.getCantidadTotalFormulada()));
                tecnologia.setMpPrescripcion(new MpPrescripcion(tec.getMpPrescripcionesId().getId()));
                tecnologia.getMpPrescripcion().setPrestadorRazonSocial(tec.getMpPrescripcionesId().getPrestadorRazonSocial());
                tecnologia.getMpPrescripcion().setNumeroPrescripcion(tec.getMpPrescripcionesId().getNumeroPrescripcion());
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorId(tec.getMpPrescripcionesId().getMaTipoDocumentoPrestadorId());
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo(tec.getMpPrescripcionesId().getMaTipoDocumentoPrestadorCodigo());
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorValor(tec.getMpPrescripcionesId().getMaTipoDocumentoPrestadorValor());
                tecnologia.getMpPrescripcion().setPrestadorNumeroDocumento(tec.getMpPrescripcionesId().getPrestadorNumeroDocumento());
                tecnologia.getMpPrescripcion().setSedeCodigoHabilitacion(tec.getMpPrescripcionesId().getSedeCodigoHabilitacion());
                listaMedicamentos.put(tec.getMpPrescripcionesId().getNumeroPrescripcion() + "||" + tec.getConsecutivoOrden(), tecnologia);
            }

        } catch (NoResultException e) {
            listaMedicamentos = null;
        } catch (Exception e) {
            throw new Exception("Error consultarListaTecnologias2  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return listaMedicamentos;
    }

    @Override
    public Map<String, MpPrescripcionTecnologia> consultarListaTecnologias(List<Direccionamiento> listaDireccionamientos) throws Exception {
        Map<String, MpPrescripcionTecnologia> listaTecnologias = new HashMap<>();
        try {
            String strQuery = "select "
                    + "mm.id, "
                    + "mm.consecutivo_orden, "
                    + "mm.tipo_tecnologia, "
                    + "mm.cantidad_total_entrega, "
                    + "mm.entregados, "
                    + "mm.pendientes, "
                    + "mm.cantidad_duracion_tratamiento, "
                    + "mm.cantidad_total, "
                    + "mm.cantidad_formulada, "
                    + "mp.id AS id_prescripcion, "
                    + "mp.prestador_razon_social, "
                    + "mp.numero_prescripcion, "
                    + "mp.ma_tipo_documento_prestador_id, "
                    + "mp.ma_tipo_documento_prestador_codigo, "
                    + "mp.ma_tipo_documento_prestador_valor, "
                    + "mp.prestador_numero_documento, "
                    + "mp.sede_codigo_habilitacion "
                    + "FROM mp_prescripcion_tecnologias mm INNER JOIN mp_prescripciones mp "
                    + "ON mp.id = mm.mp_prescripcion_id "
                    + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
            String whereIn = "(";
            for (Direccionamiento direccionamiento : listaDireccionamientos) {
                if (direccionamiento.getNoPrescripcion() != null && !direccionamiento.getNoPrescripcion().equals("")
                        && direccionamiento.getConTec() != null && direccionamiento.getTipoTec() != null) {
                    whereIn += "('" + direccionamiento.getNoPrescripcion() + "',";
                    whereIn += direccionamiento.getConTec() + ",";
                    whereIn += "'" + convertirTipoTec(direccionamiento.getTipoTec()) + "'),";
                }
            }
            //Remover ultima ,
            if (!whereIn.equals("(")) {
                whereIn = whereIn.substring(0, whereIn.length() - 1);
            }
            whereIn += ") AND id_direccionamiento IS NULL ";
            strQuery += whereIn;
            List<Object[]> listObjTecnologias = null;
            try {
                listObjTecnologias = getEntityManager().createNativeQuery(strQuery).getResultList();
            } catch (Exception ex) {

            } finally {
                cerrarEntityManager();
            }
            if (listObjTecnologias != null) {
                for (Object[] obj : listObjTecnologias) {
                    MpPrescripcionTecnologia tecnologia = new MpPrescripcionTecnologia();
                    tecnologia.setId((Integer) (obj[0]));
                    tecnologia.setConsecutivoOrden((Integer) (obj[1]));
                    tecnologia.setTipoTecnologia((Integer) (obj[2]));
                    tecnologia.setCantidadTotalEntrega(((BigDecimal) obj[3]));
                    tecnologia.setEntregados((BigDecimal) obj[4]);
                    tecnologia.setPendientes((BigDecimal) obj[5]);
                    tecnologia.setCantidadDuracionTratamiento((Integer) obj[6]);
                    tecnologia.setCantidadTotal((Integer) obj[7]);
                    tecnologia.setCantidadFormulada((Integer) obj[8]);
                    tecnologia.setMpPrescripcion(new MpPrescripcion((Integer) (obj[9])));
                    tecnologia.getMpPrescripcion().setPrestadorRazonSocial((String) obj[10]);
                    tecnologia.getMpPrescripcion().setNumeroPrescripcion((String) obj[11]);
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorId((Integer) (obj[12]));
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo((String) (obj[13]));
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorValor((String) (obj[14]));
                    tecnologia.getMpPrescripcion().setPrestadorNumeroDocumento((String) (obj[15]));
                    tecnologia.getMpPrescripcion().setSedeCodigoHabilitacion((String) (obj[16]));
                    listaTecnologias.put(tecnologia.getMpPrescripcion().getNumeroPrescripcion() + "||" + tecnologia.getConsecutivoOrden(), tecnologia);
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error consultarListaTecnologias  - " + ex.getMessage());
        }
        return listaTecnologias;
    }

    @Override
    public Map<String, MpPrescripcionTecnologia> consultarListaTecnologias2(List<Direccionamiento> listaDireccionamientos) throws Exception {
        Map<String, MpPrescripcionTecnologia> listaTecnologias = new HashMap<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("FROM MpPrescripcionTecnologias p ")
                    .append("WHERE (p.mpPrescripcionId.numeroPrescripcion, p.consecutivoOrden, p.tipoTecnologia) IN (");
            StringBuilder strWhere = new StringBuilder();
            for (Direccionamiento direccionamiento : listaDireccionamientos) {
                if (direccionamiento.getNoPrescripcion() != null && !direccionamiento.getNoPrescripcion().equals("")
                        && direccionamiento.getConTec() != null && direccionamiento.getTipoTec() != null) {
                    strWhere.append("('").append(direccionamiento.getNoPrescripcion())
                            .append("',").append(direccionamiento.getConTec())
                            .append(",").append("'").append(convertirTipoTec(direccionamiento.getTipoTec())).append("'),");
                }
            }
            String whereIn = strWhere.toString();
            whereIn = whereIn.substring(0, whereIn.length() - 1);
            strQuery.append(whereIn);
            strQuery.append(")");
            List<MpPrescripcionTecnologias> list = getEntityManager()
                    .createQuery(strQuery.toString())
                    .getResultList();

            for (MpPrescripcionTecnologias tec : list) {
                MpPrescripcionTecnologia tecnologia = new MpPrescripcionTecnologia();
                tecnologia.setId(tec.getId());
                tecnologia.setConsecutivoOrden(tec.getConsecutivoOrden());
                tecnologia.setTipoTecnologia(tec.getTipoTecnologia());
                tecnologia.setCantidadTotalEntrega(tec.getCantidadTotalEntrega());
                tecnologia.setEntregados(tec.getEntregados());
                tecnologia.setPendientes(tec.getPendientes());
                tecnologia.setCantidadDuracionTratamiento(tec.getCantidadDuracionTratamiento());
                tecnologia.setCantidadTotal(tec.getCantidadTotal());
                tecnologia.setCantidadFormulada(tec.getCantidadFormulada());
                tecnologia.setMpPrescripcion(new MpPrescripcion(tec.getMpPrescripcionId().getId()));
                tecnologia.getMpPrescripcion().setPrestadorRazonSocial(tec.getMpPrescripcionId().getPrestadorRazonSocial());
                tecnologia.getMpPrescripcion().setNumeroPrescripcion(tec.getMpPrescripcionId().getNumeroPrescripcion());
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorId(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorId());
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorCodigo());
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorValor(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorValor());
                tecnologia.getMpPrescripcion().setPrestadorNumeroDocumento(tec.getMpPrescripcionId().getPrestadorNumeroDocumento());
                tecnologia.getMpPrescripcion().setSedeCodigoHabilitacion(tec.getMpPrescripcionId().getSedeCodigoHabilitacion());
                listaTecnologias.put(tec.getMpPrescripcionId().getNumeroPrescripcion() + "||" + tec.getConsecutivoOrden(), tecnologia);
            }

        } catch (NoResultException e) {
            listaTecnologias = null;
        } catch (Exception e) {
            throw new Exception("Error consultarListaTecnologias2  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return listaTecnologias;
    }

    @Override
    public Map<String, MpPrescripcionInsumo> consultarListaInsumos(List<Direccionamiento> listaDireccionamientos) throws Exception {
        Map<String, MpPrescripcionInsumo> listaInsumos = new HashMap<>();
        try {
            String strQuery = "select "
                    + "mm.id, "
                    + "mm.consecutivo_orden, "
                    + "mm.tipo_tecnologia, "
                    + "mm.cantidad_total_entrega, "
                    + "mm.entregados, "
                    + "mm.pendiente, "
                    + "mm.duracion_tratamiento, "
                    + "mm.cantidad_formulada, "
                    + "mp.id AS id_prescripcion, "
                    + "mp.prestador_razon_social, "
                    + "mp.numero_prescripcion, "
                    + "mp.ma_tipo_documento_prestador_id, "
                    + "mp.ma_tipo_documento_prestador_codigo, "
                    + "mp.ma_tipo_documento_prestador_valor, "
                    + "mp.prestador_numero_documento, "
                    + "mp.sede_codigo_habilitacion "
                    + "FROM mp_prescripcion_insumos mm INNER JOIN mp_prescripciones mp "
                    + "ON mp.id = mm.mp_prescripcion_id "
                    + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
            String whereIn = "(";
            for (Direccionamiento direccionamiento : listaDireccionamientos) {
                if (direccionamiento.getNoPrescripcion() != null && !direccionamiento.getNoPrescripcion().equals("")
                        && direccionamiento.getConTec() != null && direccionamiento.getTipoTec() != null) {
                    whereIn += "('" + direccionamiento.getNoPrescripcion() + "',";
                    whereIn += direccionamiento.getConTec() + ",";
                    whereIn += "'" + convertirTipoTec(direccionamiento.getTipoTec()) + "'),";
                }
            }
            //Remover ultima ,
            if (!whereIn.equals("(")) {
                whereIn = whereIn.substring(0, whereIn.length() - 1);
            }
            whereIn += ") AND id_direccionamiento IS NULL ";
            strQuery += whereIn;
            List<Object[]> listObjInsumos = null;
            try {
                listObjInsumos = getEntityManager().createNativeQuery(strQuery).getResultList();
            } catch (Exception ex) {

            } finally {
                cerrarEntityManager();
            }
            if (listObjInsumos != null) {
                for (Object[] obj : listObjInsumos) {
                    MpPrescripcionInsumo insumo = new MpPrescripcionInsumo();
                    insumo.setId((Integer) (obj[0]));
                    insumo.setConsecutivoOrden((Integer) (obj[1]));
                    insumo.setTipoTecnologia((Integer) (obj[2]));
                    insumo.setCantidadTotalEntrega((BigDecimal) obj[3]);
                    insumo.setEntregados((BigDecimal) obj[4]);
                    insumo.setPendiente((BigDecimal) obj[5]);
                    insumo.setDuracionTratamiento((Integer) obj[6]);
                    insumo.setCantidadFormulada((String) obj[7]);
                    insumo.setMpPrescripcion(new MpPrescripcion((Integer) (obj[8])));
                    insumo.getMpPrescripcion().setPrestadorRazonSocial((String) obj[9]);
                    insumo.getMpPrescripcion().setNumeroPrescripcion((String) obj[10]);
                    insumo.getMpPrescripcion().setMaTipoDocumentoPrestadorId((Integer) (obj[11]));
                    insumo.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo((String) (obj[12]));
                    insumo.getMpPrescripcion().setMaTipoDocumentoPrestadorValor((String) (obj[13]));
                    insumo.getMpPrescripcion().setPrestadorNumeroDocumento((String) (obj[14]));
                    insumo.getMpPrescripcion().setSedeCodigoHabilitacion((String) (obj[15]));
                    listaInsumos.put(insumo.getMpPrescripcion().getNumeroPrescripcion() + "||" + insumo.getConsecutivoOrden(), insumo);
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error consultarListaInsumos  - " + ex.getMessage());
        }
        return listaInsumos;
    }

    @Override
    public Map<String, MpPrescripcionInsumo> consultarListaInsumos2(List<Direccionamiento> listaDireccionamientos) throws Exception {
        Map<String, MpPrescripcionInsumo> listaInsumos = new HashMap<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("FROM MpPrescripcionInsumos p ")
                    .append("WHERE (p.mpPrescripcionId.numeroPrescripcion, p.consecutivoOrden, p.tipoTecnologia) IN (");
            StringBuilder strWhere = new StringBuilder();
            for (Direccionamiento direccionamiento : listaDireccionamientos) {
                if (direccionamiento.getNoPrescripcion() != null && !direccionamiento.getNoPrescripcion().equals("")
                        && direccionamiento.getConTec() != null && direccionamiento.getTipoTec() != null) {
                    strWhere.append("('").append(direccionamiento.getNoPrescripcion())
                            .append("',").append(direccionamiento.getConTec())
                            .append(",").append("'").append(convertirTipoTec(direccionamiento.getTipoTec())).append("'),");
                }
            }
            String whereIn = strWhere.toString();
            whereIn = whereIn.substring(0, whereIn.length() - 1);
            strQuery.append(whereIn);
            strQuery.append(")");
            List<MpPrescripcionInsumos> list = getEntityManager()
                    .createQuery(strQuery.toString())
                    .getResultList();

            for (MpPrescripcionInsumos tec : list) {
                MpPrescripcionInsumo tecnologia = new MpPrescripcionInsumo();
                tecnologia.setId(tec.getId());
                tecnologia.setConsecutivoOrden(tec.getConsecutivoOrden());
                tecnologia.setTipoTecnologia(tec.getTipoTecnologia());
                tecnologia.setCantidadTotalEntrega(tec.getCantidadTotalEntrega());
                tecnologia.setEntregados(tec.getEntregados());
                tecnologia.setPendiente(tec.getPendiente());
                tecnologia.setDuracionTratamiento(tec.getDuracionTratamiento());
                //tecnologia.setCantidadTotal(tec.getCantidadTotal());
                tecnologia.setCantidadFormulada(tec.getCantidadFormulada());
                tecnologia.setMpPrescripcion(new MpPrescripcion(tec.getMpPrescripcionId().getId()));
                tecnologia.getMpPrescripcion().setPrestadorRazonSocial(tec.getMpPrescripcionId().getPrestadorRazonSocial());
                tecnologia.getMpPrescripcion().setNumeroPrescripcion(tec.getMpPrescripcionId().getNumeroPrescripcion());
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorId(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorId());
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorCodigo());
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorValor(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorValor());
                tecnologia.getMpPrescripcion().setPrestadorNumeroDocumento(tec.getMpPrescripcionId().getPrestadorNumeroDocumento());
                tecnologia.getMpPrescripcion().setSedeCodigoHabilitacion(tec.getMpPrescripcionId().getSedeCodigoHabilitacion());
                listaInsumos.put(tec.getMpPrescripcionId().getNumeroPrescripcion() + "||" + tec.getConsecutivoOrden(), tecnologia);
            }

        } catch (NoResultException e) {
            listaInsumos = null;
        } catch (Exception e) {
            throw new Exception("Error consultarListaTecnologias2  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return listaInsumos;
    }

    @Override
    public Map<String, MpPrescripcionMedicamento> consultarListaMedicamentosNd(List<NoDireccionamiento> listaDireccionamientos) throws Exception {
        Map<String, MpPrescripcionMedicamento> listaMedicamentos = new HashMap<>();
        try {
            String strQuery = "select "
                    + "mm.id, "
                    + "mm.consecutivo_orden, "
                    + "mm.tipo_tecnologia, "
                    + "mm.cantidad_total_entrega, "
                    + "mm.entregados, "
                    + "mm.pendientes, "
                    + "mm.duracion_tratamiento, "
                    + "mm.cantidad_tratamiento, "
                    + "mm.cantidad_total_formulada, "
                    + "mp.id AS id_prescripcion, "
                    + "mp.prestador_razon_social, "
                    + "mp.numero_prescripcion, "
                    + "mp.ma_tipo_documento_prestador_id, "
                    + "mp.ma_tipo_documento_prestador_codigo, "
                    + "mp.ma_tipo_documento_prestador_valor, "
                    + "mp.prestador_numero_documento, "
                    + "mp.sede_codigo_habilitacion "
                    + "FROM mp_prescripcion_medicamentos mm INNER JOIN mp_prescripciones mp "
                    + "ON mp.id = mm.mp_prescripciones_id "
                    + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
            String whereIn = "(";
            for (NoDireccionamiento direccionamiento : listaDireccionamientos) {
                if (direccionamiento.getNoPrescripcion() != null && !direccionamiento.getNoPrescripcion().equals("")
                        && direccionamiento.getConTec() != null && direccionamiento.getTipoTec() != null) {
                    whereIn += "('" + direccionamiento.getNoPrescripcion() + "',";
                    whereIn += direccionamiento.getConTec() + ",";
                    whereIn += "'" + convertirTipoTec(direccionamiento.getTipoTec()) + "'),";
                }
            }
            //Remover ultima ,
            if (!whereIn.equals("(")) {
                whereIn = whereIn.substring(0, whereIn.length() - 1);
            }
            whereIn += ") AND id_direccionamiento IS NULL ";
            strQuery += whereIn;
            List<Object[]> listObjMedicamentos = null;
            try {
                listObjMedicamentos = getEntityManager().createNativeQuery(strQuery).getResultList();
            } catch (Exception ex) {

            } finally {
                cerrarEntityManager();
            }
            if (listObjMedicamentos != null) {
                for (Object[] obj : listObjMedicamentos) {
                    MpPrescripcionMedicamento medicamento = new MpPrescripcionMedicamento();
                    medicamento.setId((Integer) (obj[0]));
                    medicamento.setConsecutivoOrden((Integer) (obj[1]));
                    medicamento.setTipoTecnologia((Integer) (obj[2]));
                    medicamento.setCantidadTotalEntrega((BigDecimal) obj[3]);
                    medicamento.setEntregados((BigDecimal) obj[4]);
                    medicamento.setPendientes((BigDecimal) obj[5]);
                    medicamento.setDuracionTratamiento((Integer) obj[6]);
                    medicamento.setCantidadTratamiento((Integer) obj[7]);
                    medicamento.setCantidadTotalFormulada((BigDecimal) obj[8]);
                    medicamento.setMpPrescripcion(new MpPrescripcion((Integer) (obj[9])));
                    medicamento.getMpPrescripcion().setPrestadorRazonSocial((String) obj[10]);
                    medicamento.getMpPrescripcion().setNumeroPrescripcion((String) obj[11]);
                    medicamento.getMpPrescripcion().setMaTipoDocumentoPrestadorId((Integer) (obj[12]));
                    medicamento.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo((String) (obj[13]));
                    medicamento.getMpPrescripcion().setMaTipoDocumentoPrestadorValor((String) (obj[14]));
                    medicamento.getMpPrescripcion().setPrestadorNumeroDocumento((String) (obj[15]));
                    medicamento.getMpPrescripcion().setSedeCodigoHabilitacion((String) (obj[16]));
                    listaMedicamentos.put(medicamento.getMpPrescripcion().getNumeroPrescripcion() + "||" + medicamento.getConsecutivoOrden(), medicamento);
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error consultarListaMedicamentosNd  - " + ex.getMessage());
        }
        return listaMedicamentos;
    }

    @Override
    public Map<String, MpPrescripcionTecnologia> consultarListaTecnologiasNd(List<NoDireccionamiento> listaDireccionamientos) throws Exception {
        Map<String, MpPrescripcionTecnologia> listaTecnologias = new HashMap<>();
        try {
            String strQuery = "select "
                    + "mm.id, "
                    + "mm.consecutivo_orden, "
                    + "mm.tipo_tecnologia, "
                    + "mm.cantidad_total_entrega, "
                    + "mm.entregados, "
                    + "mm.pendientes, "
                    + "mm.cantidad_duracion_tratamiento, "
                    + "mm.cantidad_total, "
                    + "mm.cantidad_formulada, "
                    + "mp.id AS id_prescripcion, "
                    + "mp.prestador_razon_social, "
                    + "mp.numero_prescripcion, "
                    + "mp.ma_tipo_documento_prestador_id, "
                    + "mp.ma_tipo_documento_prestador_codigo, "
                    + "mp.ma_tipo_documento_prestador_valor, "
                    + "mp.prestador_numero_documento, "
                    + "mp.sede_codigo_habilitacion "
                    + "FROM mp_prescripcion_tecnologias mm INNER JOIN mp_prescripciones mp "
                    + "ON mp.id = mm.mp_prescripcion_id "
                    + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
            String whereIn = "(";
            for (NoDireccionamiento direccionamiento : listaDireccionamientos) {
                if (direccionamiento.getNoPrescripcion() != null && !direccionamiento.getNoPrescripcion().equals("")
                        && direccionamiento.getConTec() != null && direccionamiento.getTipoTec() != null) {
                    whereIn += "('" + direccionamiento.getNoPrescripcion() + "',";
                    whereIn += direccionamiento.getConTec() + ",";
                    whereIn += "'" + convertirTipoTec(direccionamiento.getTipoTec()) + "'),";
                }
            }
            //Remover ultima ,
            if (!whereIn.equals("(")) {
                whereIn = whereIn.substring(0, whereIn.length() - 1);
            }
            whereIn += ") AND id_direccionamiento IS NULL ";
            strQuery += whereIn;
            List<Object[]> listObjTecnologias = null;
            try {
                listObjTecnologias = getEntityManager().createNativeQuery(strQuery).getResultList();
            } catch (Exception ex) {

            } finally {
                cerrarEntityManager();
            }
            if (listObjTecnologias != null) {
                for (Object[] obj : listObjTecnologias) {
                    MpPrescripcionTecnologia tecnologia = new MpPrescripcionTecnologia();
                    tecnologia.setId((Integer) (obj[0]));
                    tecnologia.setConsecutivoOrden((Integer) (obj[1]));
                    tecnologia.setTipoTecnologia((Integer) (obj[2]));
                    tecnologia.setCantidadTotalEntrega(((BigDecimal) obj[3]));
                    tecnologia.setEntregados((BigDecimal) obj[4]);
                    tecnologia.setPendientes((BigDecimal) obj[5]);
                    tecnologia.setCantidadDuracionTratamiento((Integer) obj[6]);
                    tecnologia.setCantidadTotal((Integer) obj[7]);
                    tecnologia.setCantidadFormulada((Integer) obj[8]);
                    tecnologia.setMpPrescripcion(new MpPrescripcion((Integer) (obj[9])));
                    tecnologia.getMpPrescripcion().setPrestadorRazonSocial((String) obj[10]);
                    tecnologia.getMpPrescripcion().setNumeroPrescripcion((String) obj[11]);
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorId((Integer) (obj[12]));
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo((String) (obj[13]));
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorValor((String) (obj[14]));
                    tecnologia.getMpPrescripcion().setPrestadorNumeroDocumento((String) (obj[15]));
                    tecnologia.getMpPrescripcion().setSedeCodigoHabilitacion((String) (obj[16]));
                    listaTecnologias.put(tecnologia.getMpPrescripcion().getNumeroPrescripcion() + "||" + tecnologia.getConsecutivoOrden(), tecnologia);
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error consultarListaTecnologiasNd  - " + ex.getMessage());
        }
        return listaTecnologias;
    }

    @Override
    public Map<String, MpPrescripcionInsumo> consultarListaInsumosNd(List<NoDireccionamiento> listaDireccionamientos) throws Exception {
        Map<String, MpPrescripcionInsumo> listaInsumos = new HashMap<>();
        try {
            String strQuery = "select "
                    + "mm.id, "
                    + "mm.consecutivo_orden, "
                    + "mm.tipo_tecnologia, "
                    + "mm.cantidad_total_entrega, "
                    + "mm.entregados, "
                    + "mm.pendiente, "
                    + "mm.duracion_tratamiento, "
                    + "mm.cantidad_formulada, "
                    + "mp.id AS id_prescripcion, "
                    + "mp.prestador_razon_social, "
                    + "mp.numero_prescripcion, "
                    + "mp.ma_tipo_documento_prestador_id, "
                    + "mp.ma_tipo_documento_prestador_codigo, "
                    + "mp.ma_tipo_documento_prestador_valor, "
                    + "mp.prestador_numero_documento, "
                    + "mp.sede_codigo_habilitacion "
                    + "FROM mp_prescripcion_insumos mm INNER JOIN mp_prescripciones mp "
                    + "ON mp.id = mm.mp_prescripcion_id "
                    + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
            String whereIn = "(";
            for (NoDireccionamiento direccionamiento : listaDireccionamientos) {
                if (direccionamiento.getNoPrescripcion() != null && !direccionamiento.getNoPrescripcion().equals("")
                        && direccionamiento.getConTec() != null && direccionamiento.getTipoTec() != null) {
                    whereIn += "('" + direccionamiento.getNoPrescripcion() + "',";
                    whereIn += direccionamiento.getConTec() + ",";
                    whereIn += "'" + convertirTipoTec(direccionamiento.getTipoTec()) + "'),";
                }
            }
            //Remover ultima ,
            if (!whereIn.equals("(")) {
                whereIn = whereIn.substring(0, whereIn.length() - 1);
            }
            whereIn += ") AND id_direccionamiento IS NULL ";
            strQuery += whereIn;
            List<Object[]> listObjInsumos = null;
            try {
                listObjInsumos = getEntityManager().createNativeQuery(strQuery).getResultList();
            } catch (Exception ex) {

            } finally {
                cerrarEntityManager();
            }
            if (listObjInsumos != null) {
                for (Object[] obj : listObjInsumos) {
                    MpPrescripcionInsumo insumo = new MpPrescripcionInsumo();
                    insumo.setId((Integer) (obj[0]));
                    insumo.setConsecutivoOrden((Integer) (obj[1]));
                    insumo.setTipoTecnologia((Integer) (obj[2]));
                    insumo.setCantidadTotalEntrega((BigDecimal) obj[3]);
                    insumo.setEntregados((BigDecimal) obj[4]);
                    insumo.setPendiente((BigDecimal) obj[5]);
                    insumo.setDuracionTratamiento((Integer) obj[6]);
                    insumo.setCantidadFormulada((String) obj[7]);
                    insumo.setMpPrescripcion(new MpPrescripcion((Integer) (obj[8])));
                    insumo.getMpPrescripcion().setPrestadorRazonSocial((String) obj[9]);
                    insumo.getMpPrescripcion().setNumeroPrescripcion((String) obj[10]);
                    insumo.getMpPrescripcion().setMaTipoDocumentoPrestadorId((Integer) (obj[11]));
                    insumo.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo((String) (obj[12]));
                    insumo.getMpPrescripcion().setMaTipoDocumentoPrestadorValor((String) (obj[13]));
                    insumo.getMpPrescripcion().setPrestadorNumeroDocumento((String) (obj[14]));
                    insumo.getMpPrescripcion().setSedeCodigoHabilitacion((String) (obj[15]));
                    listaInsumos.put(insumo.getMpPrescripcion().getNumeroPrescripcion() + "||" + insumo.getConsecutivoOrden(), insumo);
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error consultarListaInsumosNd  - " + ex.getMessage());
        }
        return listaInsumos;
    }

    @Override
    public Map<String, CntPrestador> consultarListaPrestadores(List<Direccionamiento> lista, HashMap<String, Maestro> hashMaestrosTipoDocumento) throws Exception {
        Map<String, CntPrestador> listaSedes = new HashMap<>();
        try {
            String strQuery = "SELECT id, mae_tipo_documento_id,"
                    + "mae_tipo_documento_codigo, mae_tipo_documento_valor,"
                    + "numero_documento, razon_social, codigo_min_salud "
                    + "FROM cnt_prestadores "
                    + "WHERE numero_documento IN ";
            String whereIn = "(";
            for (Direccionamiento direccionamiento : lista) {
                if (direccionamiento.getNoIDProv() != null
                        && !direccionamiento.getNoIDProv().equals("")) {
                    whereIn += "'" + direccionamiento.getNoIDProv() + "',";
                }
            }
            //Remover ultima ,
            if (!whereIn.equals("(")) {
                whereIn = whereIn.substring(0, whereIn.length() - 1);
            }
            whereIn += ")";
            strQuery += whereIn;
            List<Object[]> listObjSedes = null;
            try {
                listObjSedes = getEntityManager().createNativeQuery(strQuery).getResultList();
            } catch (Exception ex) {
                System.out.println("error " + ex.getCause().toString());
            } finally {
                cerrarEntityManager();
            }
            if (listObjSedes != null) {
                for (Object[] obj : listObjSedes) {
                    CntPrestador sede = new CntPrestador();
                    sede.setId((Integer) (obj[0]));
                    sede.setMaeTipoDocumentoId((Integer) (obj[1]));
                    sede.setMaeTipoDocumentoCodigo((String) (obj[2]));
                    sede.setMaeTipoDocumentoValor((String) (obj[3]));
                    sede.setNumeroDocumento((String) (obj[4]));
                    sede.setRazonSocial((String) (obj[5]));
                    sede.setCodigoMinSalud((String) (obj[6]));
                    listaSedes.put(sede.getNumeroDocumento(), sede);
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error consultarListaPrestadores  - " + ex.getMessage());
        }
        return listaSedes;
    }

    @Override
    public Map<String, CntProfesional> consultarListaProfesionales(List<Prescripcion> listaPrescripciones, HashMap<String, Maestro> hashMaestrosTipoDocumentoProfesionales) throws Exception {
        Map<String, CntProfesional> listaProfesionales = new HashMap<>();
        try {
            String strQuery = "select id, mae_tipo_codumento_id, documento from cnt_profesionales WHERE (mae_tipo_codumento_id, documento) IN ";
            String whereIn = "(";
            for (Prescripcion prescripcion : listaPrescripciones) {
                if (prescripcion.getPrescripcion().getNumIDProf() != null && !prescripcion.getPrescripcion().getNumIDProf().equals("")
                        && prescripcion.getPrescripcion().getTipoIDProf() != null && !prescripcion.getPrescripcion().getTipoIDProf().equals("")) {
                    whereIn += "(" + obtenerIdDocumento(prescripcion.getPrescripcion().getTipoIDProf(), hashMaestrosTipoDocumentoProfesionales) + ",";
                    whereIn += "'" + prescripcion.getPrescripcion().getNumIDProf() + "'),";
                }
            }
            //Remover ultima ,
            if (!whereIn.equals("(")) {
                whereIn = whereIn.substring(0, whereIn.length() - 1);
            }
            whereIn += ")";
            strQuery += whereIn;
            List<Object[]> listObjSedes = null;
            try {
                listObjSedes = getEntityManager().createNativeQuery(strQuery).getResultList();
            } catch (Exception ex) {

            } finally {
                cerrarEntityManager();
            }
            for (Object[] obj : listObjSedes) {
                CntProfesional profesional = new CntProfesional();
                profesional.setId((Integer) (obj[0]));
                profesional.setMaeTipoCodumentoId((int) (obj[1]));
                profesional.setDocumento((String) (obj[2]));
                listaProfesionales.put(profesional.getDocumento(), profesional);
            }
        } catch (Exception ex) {
            throw new Exception("Error consultarListaProfesionales  - " + ex.getMessage());
        }
        return listaProfesionales;
    }

    @Override
    public Map<String, CntProfesional> consultarListaProfesionalesRec(List<PrescripcionRecobrante> listaPrescripciones, HashMap<String, Maestro> hashMaestrosTipoDocumentoProfesionales) throws Exception {
        Map<String, CntProfesional> listaProfesionales = new HashMap<>();
        try {
            String strQuery = "select id, mae_tipo_codumento_id, documento from cnt_profesionales WHERE (mae_tipo_codumento_id, documento) IN ";
            String whereIn = "(";
            for (PrescripcionRecobrante prescripcion : listaPrescripciones) {
                if (prescripcion.getTutela().getNumIDProf() != null && !prescripcion.getTutela().getNumIDProf().equals("")
                        && prescripcion.getTutela().getTipoIDProf() != null && !prescripcion.getTutela().getTipoIDProf().equals("")) {
                    whereIn += "(" + obtenerIdDocumento(prescripcion.getTutela().getTipoIDProf(), hashMaestrosTipoDocumentoProfesionales) + ",";
                    whereIn += "'" + prescripcion.getTutela().getNumIDProf() + "'),";
                }
            }
            //Remover ultima ,
            if (!whereIn.equals("(")) {
                whereIn = whereIn.substring(0, whereIn.length() - 1);
            }
            whereIn += ")";
            strQuery += whereIn;
            List<Object[]> listObjSedes = null;
            try {
                listObjSedes = getEntityManager().createNativeQuery(strQuery).getResultList();
            } catch (Exception ex) {

            } finally {
                cerrarEntityManager();
            }
            for (Object[] obj : listObjSedes) {
                CntProfesional profesional = new CntProfesional();
                profesional.setId((Integer) (obj[0]));
                profesional.setMaeTipoCodumentoId((int) (obj[1]));
                profesional.setDocumento((String) (obj[2]));
                listaProfesionales.put(profesional.getDocumento(), profesional);
            }
        } catch (Exception ex) {
            throw new Exception("Error consultarListaProfesionalesRec  - " + ex.getMessage());
        }
        return listaProfesionales;
    }

    private Integer obtenerIdDocumento(String tipoDocumento, HashMap<String, Maestro> hashMaestrosTipoDocumento) {
        if (hashMaestrosTipoDocumento.get(tipoDocumento) != null) {
            return hashMaestrosTipoDocumento.get(tipoDocumento).getId();
        } else {
            return 0;
        }
    }

    private Maestro obtenerMaestroEmpresa(String tipoDocumento, HashMap<String, Maestro> hashMaestrosTipoDocumentoEmpresa) {
        return hashMaestrosTipoDocumentoEmpresa.get(tipoDocumento);
    }

    private Maestro obtenerMaestroProfesional(String tipoDocumento, HashMap<String, Maestro> hashMaestrosTipoDocumentoEmpresa) {
        return hashMaestrosTipoDocumentoEmpresa.get(tipoDocumento);
    }

    private AsegAfiliado consultarAfiliadoLista(Integer idTipoDocumento, String documento, Map<String, AsegAfiliado> listaAfiliados) {
        AsegAfiliado afiliado = listaAfiliados.getOrDefault(documento.trim(), null);
        if (afiliado != null && idTipoDocumento == afiliado.getMaeTipoDocumento()) {
            return afiliado;
        }
        return null;
    }

    private CntProfesional consultarProfesionalLista(Integer idTipoDocumento, String documento, Map<String, CntProfesional> listaProfesionales) {
        CntProfesional profesional = listaProfesionales.getOrDefault(documento.trim(), null);
        if (profesional != null && idTipoDocumento == profesional.getMaeTipoCodumentoId()) {
            return profesional;
        }
        return null;
    }

    @Override
    public List<Prescripcion> consultarListaPrescripciones(List<Prescripcion> listaPrescripciones) throws java.lang.Exception {
        List<Prescripcion> listaPrescripcionesLocales = new ArrayList<>();
        String strQuery = "select numero_prescripcion from mp_prescripciones WHERE numero_prescripcion IN ";
        String whereIn = "(";
        for (Prescripcion prescripcion : listaPrescripciones) {
            if (prescripcion.getPrescripcion().getCodHabIPS() != null && !prescripcion.getPrescripcion().getCodHabIPS().equals("")) {
                whereIn += "'" + prescripcion.getPrescripcion().getNoPrescripcion() + "',";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ")";
        strQuery += whereIn;
        List<Object> listObjPrescripciones = null;
        try {
            listObjPrescripciones = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        for (Object obj : listObjPrescripciones) {
            Prescripcion prescripcion = new Prescripcion();
            prescripcion.setPrescripcion(new PrescripcionDetalle());
            prescripcion.getPrescripcion().setNoPrescripcion((String) (obj));
            listaPrescripcionesLocales.add(prescripcion);
        }
        return listaPrescripcionesLocales;
    }

    @Override
    public List<MpPrescripcion> consultarListaPrescripcionesPorAnular(String periodo, String regimen) throws Exception {
        List<MpPrescripcion> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MpPrescripciones mp "
                    + "WHERE mp.numeroPrescripcion LIKE '" + periodo + "%' "
                    + "AND mp.asegAfiliadosId.maeRegimenCodigo = '" + regimen
                    + "' ORDER BY mp.id ";
            List<MpPrescripciones> list = getEntityManager()
                    .createQuery(strQuery).setMaxResults(5000)
                    .getResultList();
            for (MpPrescripciones per : list) {
                //for (MpConsumoFallos per : list.subList(0, 1000)) {
                listResult.add(castMpPrescripcionId(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            listResult = new ArrayList();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public List<MpPrescripcion> consultarListaPrescripcionesPorAnularEstado(String regimen, int desde, int hasta) throws Exception {
        List<MpPrescripcion> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MpPrescripciones mp "
                    + " WHERE mp.asegAfiliadosId.maeRegimenCodigo = :regimen "
                    //                + " AND mp.estado = 2 "
                    + " ORDER BY mp.id ";

            List<MpPrescripciones> list = getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("regimen", regimen)
                    .setFirstResult(desde)
                    .setMaxResults(hasta)
                    .getResultList();

            for (MpPrescripciones per : list) {
                listResult.add(castMpPrescripcionId(per));
            }
        } catch (NoResultException e) {
            // Manejo de la excepción
        } catch (Exception e) {
            throw e;
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public List<MpPrescripcion> consultarListaPrescripcionesPorAnularEstadoFecha(String regimen, int desde, int hasta, Date fecha) throws Exception {
        List<MpPrescripcion> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MpPrescripciones mp "
                    + " WHERE mp.asegAfiliadosId.maeRegimenCodigo = :regimen "
                    + " AND mp.fechaPrescripcion = :fecha "
                    + " ORDER BY mp.id ";

            List<MpPrescripciones> list = getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("regimen", regimen)
                    .setParameter("fecha", fecha)
                    .setFirstResult(desde)
                    .setMaxResults(hasta)
                    .getResultList();

            for (MpPrescripciones per : list) {
                listResult.add(castMpPrescripcionId(per));
            }
        } catch (NoResultException e) {
            // Manejo de la excepción
        } catch (Exception e) {
            throw e;
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public List<MpPrescripcion> consultarListaPrescripcionesPorAnularEstadoSolicitud(String regimen, int desde, int hasta, Date fecha) throws Exception {
        List<MpPrescripcion> listResult = new ArrayList<>();
        try {

            String strQuery = "FROM MpPrescripciones mp "
                    + " WHERE mp.asegAfiliadosId.maeRegimenCodigo = :regimen "
                    + " AND mp.estado <> 2 "
                    + " AND mp.fechaPrescripcion BETWEEN :fechaInicio AND :fechaFin "
                    + " ORDER BY mp.id ";

            Date fechaRecibida = truncarHoraFecha(fecha);
            Date fechaInicio = restarDias(fechaRecibida, 3);

            List<MpPrescripciones> list = getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("regimen", regimen)
                    .setParameter("fechaInicio", fechaInicio)
                    .setParameter("fechaFin", fechaRecibida)
                    .setFirstResult(desde)
                    .setMaxResults(hasta)
                    .getResultList();

            // Procesar los resultados
            for (MpPrescripciones per : list) {
                listResult.add(castMpPrescripcionId(per));
            }
        } catch (NoResultException e) {

        } catch (Exception e) {
            throw e;
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    public static Date truncarHoraFecha(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date restarDias(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, -dias);
        return calendar.getTime();
    }

    @Override
    public List<MpAnuladaPrescripcion> consultarListaAnuladas(String regimen, int desde, int hasta) throws Exception {
        List<MpAnuladaPrescripcion> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MpPrescripcionAnulada mp "
                    + " WHERE mp.mpPrescripcionesId.asegAfiliadosId.maeRegimenCodigo = :regimen "
                    + " AND mp.estado = 0 "
                    + " ORDER BY mp.id ";

            List<MpPrescripcionAnulada> list = getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("regimen", regimen)
                    .setFirstResult(desde)
                    .setMaxResults(hasta)
                    .getResultList();

            for (MpPrescripcionAnulada per : list) {
                listResult.add(castMpPrescripcionAnulada(per));
            }
        } catch (NoResultException e) {
            // Manejo de la excepción
        } catch (Exception e) {
            throw e;
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public MpAnuladaPrescripcion validarExisteSolicitud(Integer id, String pres) throws Exception {
        MpAnuladaPrescripcion resul = new MpAnuladaPrescripcion();
        try {
            String strQuery = "FROM MpPrescripcionAnulada mp "
                    + " WHERE mp.mpPrescripcionesId.id = :id "
                    + " AND mp.numeroPrescripcion = :pres "
                    + " ORDER BY mp.id ";

            MpPrescripcionAnulada res = getEntityManager()
                    .createQuery(strQuery, MpPrescripcionAnulada.class)
                    .setParameter("id", id)
                    .setParameter("pres", pres)
                    .setMaxResults(1)
                    .getSingleResult();

            resul = castMpPrescripcionAnulada(res);

        } catch (NoResultException e) {
            // Manejo de la excepción
        } catch (Exception e) {
            throw e;
        } finally {
            cerrarEntityManager();
        }
        return resul;
    }

    public static MpAnuladaPrescripcion castMpPrescripcionAnulada(MpPrescripcionAnulada per) {
        MpAnuladaPrescripcion obj = new MpAnuladaPrescripcion();
        obj.setId(per.getId());
        if (per.getMpPrescripcionesId() != null) {
            MpPrescripcion pres = new MpPrescripcion();
            pres.setId(per.getMpPrescripcionesId().getId());
            obj.setMpPrescripcionId(pres);
        }
        obj.setEstado(per.getEstado());
        obj.setNumeroPrescripcion(per.getNumeroPrescripcion());
        return obj;
    }

    @Override
    public List<MpPrescripcion> consultarListaPrescripcionesPorFechaMinima(Date fecha, String regimen) throws Exception {
        List<MpPrescripcion> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MpPrescripciones mp "
                    + "WHERE mp.fechaHoraCrea > '" + formatoFH.format(fecha)
                    + "' AND mp.asegAfiliadosId.maeRegimenCodigo = '" + regimen
                    + "' ORDER BY mp.id ";
            List<MpPrescripciones> list = getEntityManager()
                    .createQuery(strQuery).setMaxResults(5000)
                    .getResultList();
            for (MpPrescripciones per : list) {
                //for (MpConsumoFallos per : list.subList(0, 1000)) {
                listResult.add(castMpPrescripcionId(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            listResult = new ArrayList();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    //ACTUALIZAR TECNOLOGIA JUNTA
    @Override
    public void actualizaPrescripcionAnulada(
            String respuesta,
            Integer idPrescripcion,
            Integer estado) throws Exception {
        MpConsumoFallo fallo = new MpConsumoFallo();
        try {
            fallo.setEstado((short) 0);

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpPrescripciones ");
            sql.append("SET estado = :estado ");
            if (respuesta != null && !respuesta.equals("")) {
                sql.append(", notaAuditoria = :respuesta ");
            }
            sql.append("WHERE id = :id ");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", estado);
            if (respuesta != null && !respuesta.equals("")) {//faltaba validacion para asignar el valor a respuesta
                query.setParameter("respuesta", respuesta);
            }
            query.setParameter("id", idPrescripcion);
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error Anulando Prescripción: " + e.getMessage() + "CAUSE" + e.getCause().getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizaPrescripcionSolicitud(
            Integer idSolicitud,
            Integer estado) throws Exception {
        MpConsumoFallo fallo = new MpConsumoFallo();
        try {
            fallo.setEstado((short) 0);

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpPrescripcionAnulada ");
            sql.append("SET estado = :estado ");
            sql.append("WHERE id = :id ");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", estado);
            query.setParameter("id", idSolicitud);
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error Anulando Prescripción: " + e.getMessage() + "CAUSE" + e.getCause().getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public List<MpPrescripcion> consultarPrescripcion(String noPrescripcion) throws java.lang.Exception {
        List<MpPrescripcion> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MpPrescripciones mp "
                    + "WHERE mp.numeroPrescripcion = '" + noPrescripcion
                    + "' ORDER BY mp.id";
            List<MpPrescripciones> list = getEntityManager()
                    .createQuery(strQuery).setMaxResults(5000)
                    .getResultList();
            for (MpPrescripciones per : list) {
                //for (MpConsumoFallos per : list.subList(0, 1000)) {
                listResult.add(castMpPrescripcionId(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            listResult = new ArrayList();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    public static MpPrescripcion castMpPrescripcionId(MpPrescripciones per) {
        MpPrescripcion obj = new MpPrescripcion();
        obj.setId(per.getId());
        obj.setEstado(per.getEstado());
        obj.setNumeroPrescripcion(per.getNumeroPrescripcion());
        return obj;
    }

    @Override
    public List<Prescripcion> consultarListaPrescripcionesRec(List<PrescripcionRecobrante> listaPrescripciones) throws java.lang.Exception {
        List<Prescripcion> listaPrescripcionesLocales = new ArrayList<>();
        String strQuery = "select numero_prescripcion from mp_prescripciones WHERE numero_prescripcion IN ";
        String whereIn = "(";
        for (PrescripcionRecobrante prescripcion : listaPrescripciones) {
            if (prescripcion.getTutela() != null) {
                whereIn += "'" + prescripcion.getTutela().getNoTutela() + "',";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ")";
        strQuery += whereIn;
        List<Object> listObjPrescripciones = null;
        try {
            listObjPrescripciones = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        for (Object obj : listObjPrescripciones) {
            Prescripcion prescripcion = new Prescripcion();
            prescripcion.setPrescripcion(new PrescripcionDetalle());
            prescripcion.getPrescripcion().setNoPrescripcion((String) (obj));
            listaPrescripcionesLocales.add(prescripcion);
        }
        return listaPrescripcionesLocales;
    }

    private String convertirTipoTec(String tipoTec) {
        String str = "";
        switch (tipoTec) {
            case "M":
                str = "1";
                break;
            case "P":
                str = "2";
                break;
            case "D":
                str = "3";
                break;
            case "N":
                str = "4";
                break;
            case "S":
                str = "5";
                break;
            default:
                break;
        }
        return str;
    }

    @Override
    public Map<String, MpPrescripcionMedicamento> consultarListaMedicamentosEnt(List<ReporteEntrega> listaEntregas) throws java.lang.Exception {
        Map<String, MpPrescripcionMedicamento> listaMedicamentos = new HashMap<>();
        String strQuery = "select "
                + "mm.id, "
                + "mm.consecutivo_orden, "
                + "mm.tipo_tecnologia, "
                + "mm.cantidad_total_entrega, "
                + "mm.entregados, "
                + "mm.pendientes, "
                + "mm.duracion_tratamiento, "
                + "mm.cantidad_tratamiento, "
                + "mm.cantidad_total_formulada, "
                + "mp.id AS id_prescripcion, "
                + "mp.prestador_razon_social, "
                + "mp.numero_prescripcion, "
                + "mp.ma_tipo_documento_prestador_id, "
                + "mp.ma_tipo_documento_prestador_codigo, "
                + "mp.ma_tipo_documento_prestador_valor, "
                + "mp.prestador_numero_documento, "
                + "mp.sede_codigo_habilitacion "
                + "FROM mp_prescripcion_medicamentos mm INNER JOIN mp_prescripciones mp "
                + "ON mp.id = mm.mp_prescripciones_id "
                + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
        String whereIn = "(";
        for (ReporteEntrega entrega : listaEntregas) {
            if (entrega.getNoPrescripcion() != null && !entrega.getNoPrescripcion().equals("")
                    && entrega.getConTec() != null && entrega.getTipoTec() != null) {
                whereIn += "('" + entrega.getNoPrescripcion() + "',";
                whereIn += entrega.getConTec() + ",";
                whereIn += "'" + convertirTipoTec(entrega.getTipoTec()) + "'),";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjMedicamentos = null;
        try {
            listObjMedicamentos = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        if (listObjMedicamentos != null) {
            for (Object[] obj : listObjMedicamentos) {
                MpPrescripcionMedicamento medicamento = new MpPrescripcionMedicamento();
                medicamento.setId((Integer) (obj[0]));
                medicamento.setConsecutivoOrden((Integer) (obj[1]));
                medicamento.setTipoTecnologia((Integer) (obj[2]));
                medicamento.setCantidadTotalEntrega((BigDecimal) obj[3]);
                medicamento.setEntregados((BigDecimal) obj[4]);
                medicamento.setPendientes((BigDecimal) obj[5]);
                medicamento.setDuracionTratamiento((Integer) obj[6]);
                medicamento.setCantidadTratamiento((Integer) obj[7]);
                medicamento.setCantidadTotalFormulada((BigDecimal) obj[8]);
                medicamento.setMpPrescripcion(new MpPrescripcion((Integer) (obj[9])));
                medicamento.getMpPrescripcion().setPrestadorRazonSocial((String) obj[10]);
                medicamento.getMpPrescripcion().setNumeroPrescripcion((String) obj[11]);
                medicamento.getMpPrescripcion().setMaTipoDocumentoPrestadorId((Integer) (obj[12]));
                medicamento.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo((String) (obj[13]));
                medicamento.getMpPrescripcion().setMaTipoDocumentoPrestadorValor((String) (obj[14]));
                medicamento.getMpPrescripcion().setPrestadorNumeroDocumento((String) (obj[15]));
                medicamento.getMpPrescripcion().setSedeCodigoHabilitacion((String) (obj[16]));
                listaMedicamentos.put(medicamento.getMpPrescripcion().getNumeroPrescripcion() + "||" + medicamento.getConsecutivoOrden(), medicamento);
            }
        }
        return listaMedicamentos;
    }

    @Override
    public Map<String, MpPrescripcionTecnologia> consultarListaTecnologiasEnt(List<ReporteEntrega> listaEntregas) throws java.lang.Exception {
        Map<String, MpPrescripcionTecnologia> listaTecnologias = new HashMap<>();
        String strQuery = "select "
                + "mm.id, "
                + "mm.consecutivo_orden, "
                + "mm.tipo_tecnologia, "
                + "mm.cantidad_total_entrega, "
                + "mm.entregados, "
                + "mm.pendientes, "
                + "mm.cantidad_duracion_tratamiento, "
                + "mm.cantidad_total, "
                + "mm.cantidad_formulada, "
                + "mp.id AS id_prescripcion, "
                + "mp.prestador_razon_social, "
                + "mp.numero_prescripcion, "
                + "mp.ma_tipo_documento_prestador_id, "
                + "mp.ma_tipo_documento_prestador_codigo, "
                + "mp.ma_tipo_documento_prestador_valor, "
                + "mp.prestador_numero_documento, "
                + "mp.sede_codigo_habilitacion "
                + "FROM mp_prescripcion_tecnologias mm INNER JOIN mp_prescripciones mp "
                + "ON mp.id = mm.mp_prescripcion_id "
                + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
        String whereIn = "(";
        for (ReporteEntrega entrega : listaEntregas) {
            if (entrega.getNoPrescripcion() != null && !entrega.getNoPrescripcion().equals("")
                    && entrega.getConTec() != null && entrega.getTipoTec() != null) {
                whereIn += "('" + entrega.getNoPrescripcion() + "',";
                whereIn += entrega.getConTec() + ",";
                whereIn += "'" + convertirTipoTec(entrega.getTipoTec()) + "'),";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjTecnologias = null;
        try {
            listObjTecnologias = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        if (listObjTecnologias != null) {
            for (Object[] obj : listObjTecnologias) {
                MpPrescripcionTecnologia tecnologia = new MpPrescripcionTecnologia();
                tecnologia.setId((Integer) (obj[0]));
                tecnologia.setConsecutivoOrden((Integer) (obj[1]));
                tecnologia.setTipoTecnologia((Integer) (obj[2]));
                tecnologia.setCantidadTotalEntrega(((BigDecimal) obj[3]));
                tecnologia.setEntregados((BigDecimal) obj[4]);
                tecnologia.setPendientes((BigDecimal) obj[5]);
                tecnologia.setCantidadDuracionTratamiento((Integer) obj[6]);
                tecnologia.setCantidadTotal((Integer) obj[7]);
                tecnologia.setCantidadFormulada((Integer) obj[8]);
                tecnologia.setMpPrescripcion(new MpPrescripcion((Integer) (obj[9])));
                tecnologia.getMpPrescripcion().setPrestadorRazonSocial((String) obj[10]);
                tecnologia.getMpPrescripcion().setNumeroPrescripcion((String) obj[11]);
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorId((Integer) (obj[12]));
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo((String) (obj[13]));
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorValor((String) (obj[14]));
                tecnologia.getMpPrescripcion().setPrestadorNumeroDocumento((String) (obj[15]));
                tecnologia.getMpPrescripcion().setSedeCodigoHabilitacion((String) (obj[16]));
                listaTecnologias.put(tecnologia.getMpPrescripcion().getNumeroPrescripcion() + "||" + tecnologia.getConsecutivoOrden(), tecnologia);
            }
        }
        return listaTecnologias;
    }

    @Override
    public Map<String, MpPrescripcionInsumo> consultarListaInsumosEnt(List<ReporteEntrega> listaEntregas) throws java.lang.Exception {
        Map<String, MpPrescripcionInsumo> listaInsumos = new HashMap<>();
        String strQuery = "select "
                + "mm.id, "
                + "mm.consecutivo_orden, "
                + "mm.tipo_tecnologia, "
                + "mm.cantidad_total_entrega, "
                + "mm.entregados, "
                + "mm.pendiente, "
                + "mm.duracion_tratamiento, "
                + "mm.cantidad_formulada, "
                + "mp.id AS id_prescripcion, "
                + "mp.prestador_razon_social, "
                + "mp.numero_prescripcion, "
                + "mp.ma_tipo_documento_prestador_id, "
                + "mp.ma_tipo_documento_prestador_codigo, "
                + "mp.ma_tipo_documento_prestador_valor, "
                + "mp.prestador_numero_documento, "
                + "mp.sede_codigo_habilitacion "
                + "FROM mp_prescripcion_insumos mm INNER JOIN mp_prescripciones mp "
                + "ON mp.id = mm.mp_prescripcion_id "
                + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
        String whereIn = "(";
        for (ReporteEntrega entrega : listaEntregas) {
            if (entrega.getNoPrescripcion() != null && !entrega.getNoPrescripcion().equals("")
                    && entrega.getConTec() != null && entrega.getTipoTec() != null) {
                whereIn += "('" + entrega.getNoPrescripcion() + "',";
                whereIn += entrega.getConTec() + ",";
                whereIn += "'" + convertirTipoTec(entrega.getTipoTec()) + "'),";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjInsumos = null;
        try {
            listObjInsumos = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        if (listObjInsumos != null) {
            for (Object[] obj : listObjInsumos) {
                MpPrescripcionInsumo insumo = new MpPrescripcionInsumo();
                insumo.setId((Integer) (obj[0]));
                insumo.setConsecutivoOrden((Integer) (obj[1]));
                insumo.setTipoTecnologia((Integer) (obj[2]));
                insumo.setCantidadTotalEntrega((BigDecimal) obj[3]);
                insumo.setEntregados((BigDecimal) obj[4]);
                insumo.setPendiente((BigDecimal) obj[5]);
                insumo.setDuracionTratamiento((Integer) obj[6]);
                insumo.setCantidadFormulada((String) obj[7]);
                insumo.setMpPrescripcion(new MpPrescripcion((Integer) (obj[8])));
                insumo.getMpPrescripcion().setPrestadorRazonSocial((String) obj[9]);
                insumo.getMpPrescripcion().setNumeroPrescripcion((String) obj[10]);
                insumo.getMpPrescripcion().setMaTipoDocumentoPrestadorId((Integer) (obj[11]));
                insumo.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo((String) (obj[12]));
                insumo.getMpPrescripcion().setMaTipoDocumentoPrestadorValor((String) (obj[13]));
                insumo.getMpPrescripcion().setPrestadorNumeroDocumento((String) (obj[14]));
                insumo.getMpPrescripcion().setSedeCodigoHabilitacion((String) (obj[15]));
                listaInsumos.put(insumo.getMpPrescripcion().getNumeroPrescripcion() + "||" + insumo.getConsecutivoOrden(), insumo);
            }
        }
        return listaInsumos;
    }

    @Override
    public Map<String, MpPrescripcionMedicamento> consultarListaMedicamentosSum(List<Suministro> listaSuministros) throws java.lang.Exception {
        Map<String, MpPrescripcionMedicamento> listaMedicamentos = new HashMap<>();
        String strQuery = "select "
                + "mm.id, "
                + "mm.consecutivo_orden, "
                + "mm.tipo_tecnologia, "
                + "mm.cantidad_total_entrega, "
                + "mm.entregados, "
                + "mm.pendientes, "
                + "mm.duracion_tratamiento, "
                + "mm.cantidad_tratamiento, "
                + "mm.cantidad_total_formulada, "
                + "mp.id AS id_prescripcion, "
                + "mp.prestador_razon_social, "
                + "mp.numero_prescripcion, "
                + "mp.ma_tipo_documento_prestador_id, "
                + "mp.ma_tipo_documento_prestador_codigo, "
                + "mp.ma_tipo_documento_prestador_valor, "
                + "mp.prestador_numero_documento, "
                + "mp.sede_codigo_habilitacion "
                + "FROM mp_prescripcion_medicamentos mm INNER JOIN mp_prescripciones mp "
                + "ON mp.id = mm.mp_prescripciones_id "
                + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
        String whereIn = "(";
        for (Suministro suministro : listaSuministros) {
            if (suministro.getNoPrescripcion() != null && !suministro.getNoPrescripcion().equals("")
                    && suministro.getConTec() != null && suministro.getTipoTec() != null) {
                whereIn += "('" + suministro.getNoPrescripcion() + "',";
                whereIn += suministro.getConTec() + ",";
                whereIn += "'" + convertirTipoTec(suministro.getTipoTec()) + "'),";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjMedicamentos = null;
        try {
            listObjMedicamentos = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        if (listObjMedicamentos != null) {
            for (Object[] obj : listObjMedicamentos) {
                MpPrescripcionMedicamento medicamento = new MpPrescripcionMedicamento();
                medicamento.setId((Integer) (obj[0]));
                medicamento.setConsecutivoOrden((Integer) (obj[1]));
                medicamento.setTipoTecnologia((Integer) (obj[2]));
                medicamento.setCantidadTotalEntrega((BigDecimal) obj[3]);
                medicamento.setEntregados((BigDecimal) obj[4]);
                medicamento.setPendientes((BigDecimal) obj[5]);
                medicamento.setDuracionTratamiento((Integer) obj[6]);
                medicamento.setCantidadTratamiento((Integer) obj[7]);
                medicamento.setCantidadTotalFormulada((BigDecimal) obj[8]);
                medicamento.setMpPrescripcion(new MpPrescripcion((Integer) (obj[9])));
                medicamento.getMpPrescripcion().setPrestadorRazonSocial((String) obj[10]);
                medicamento.getMpPrescripcion().setNumeroPrescripcion((String) obj[11]);
                medicamento.getMpPrescripcion().setMaTipoDocumentoPrestadorId((Integer) (obj[12]));
                medicamento.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo((String) (obj[13]));
                medicamento.getMpPrescripcion().setMaTipoDocumentoPrestadorValor((String) (obj[14]));
                medicamento.getMpPrescripcion().setPrestadorNumeroDocumento((String) (obj[15]));
                medicamento.getMpPrescripcion().setSedeCodigoHabilitacion((String) (obj[16]));
                listaMedicamentos.put(medicamento.getMpPrescripcion().getNumeroPrescripcion() + "||" + medicamento.getConsecutivoOrden(), medicamento);
            }
        }
        return listaMedicamentos;
    }

    @Override
    public Map<String, MpPrescripcionTecnologia> consultarListaTecnologiasSum(List<Suministro> listaSuministros) throws java.lang.Exception {
        Map<String, MpPrescripcionTecnologia> listaTecnologias = new HashMap<>();
        String strQuery = "select "
                + "mm.id, "
                + "mm.consecutivo_orden, "
                + "mm.tipo_tecnologia, "
                + "mm.cantidad_total_entrega, "
                + "mm.entregados, "
                + "mm.pendientes, "
                + "mm.cantidad_duracion_tratamiento, "
                + "mm.cantidad_total, "
                + "mm.cantidad_formulada, "
                + "mp.id AS id_prescripcion, "
                + "mp.prestador_razon_social, "
                + "mp.numero_prescripcion, "
                + "mp.ma_tipo_documento_prestador_id, "
                + "mp.ma_tipo_documento_prestador_codigo, "
                + "mp.ma_tipo_documento_prestador_valor, "
                + "mp.prestador_numero_documento, "
                + "mp.sede_codigo_habilitacion "
                + "FROM mp_prescripcion_tecnologias mm INNER JOIN mp_prescripciones mp "
                + "ON mp.id = mm.mp_prescripcion_id "
                + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
        String whereIn = "(";
        for (Suministro suministro : listaSuministros) {
            if (suministro.getNoPrescripcion() != null && !suministro.getNoPrescripcion().equals("")
                    && suministro.getConTec() != null && suministro.getTipoTec() != null) {
                whereIn += "('" + suministro.getNoPrescripcion() + "',";
                whereIn += suministro.getConTec() + ",";
                whereIn += "'" + convertirTipoTec(suministro.getTipoTec()) + "'),";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjTecnologias = null;
        try {
            listObjTecnologias = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        if (listObjTecnologias != null) {
            for (Object[] obj : listObjTecnologias) {
                MpPrescripcionTecnologia tecnologia = new MpPrescripcionTecnologia();
                tecnologia.setId((Integer) (obj[0]));
                tecnologia.setConsecutivoOrden((Integer) (obj[1]));
                tecnologia.setTipoTecnologia((Integer) (obj[2]));
                tecnologia.setCantidadTotalEntrega(((BigDecimal) obj[3]));
                tecnologia.setEntregados((BigDecimal) obj[4]);
                tecnologia.setPendientes((BigDecimal) obj[5]);
                tecnologia.setCantidadDuracionTratamiento((Integer) obj[6]);
                tecnologia.setCantidadTotal((Integer) obj[7]);
                tecnologia.setCantidadFormulada((Integer) obj[8]);
                tecnologia.setMpPrescripcion(new MpPrescripcion((Integer) (obj[9])));
                tecnologia.getMpPrescripcion().setPrestadorRazonSocial((String) obj[10]);
                tecnologia.getMpPrescripcion().setNumeroPrescripcion((String) obj[11]);
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorId((Integer) (obj[12]));
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo((String) (obj[13]));
                tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorValor((String) (obj[14]));
                tecnologia.getMpPrescripcion().setPrestadorNumeroDocumento((String) (obj[15]));
                tecnologia.getMpPrescripcion().setSedeCodigoHabilitacion((String) (obj[16]));
                listaTecnologias.put(tecnologia.getMpPrescripcion().getNumeroPrescripcion() + "||" + tecnologia.getConsecutivoOrden(), tecnologia);
            }
        }
        return listaTecnologias;
    }

    @Override
    public Map<String, MpPrescripcionInsumo> consultarListaInsumosSum(List<Suministro> listaSuministros) throws java.lang.Exception {
        Map<String, MpPrescripcionInsumo> listaInsumos = new HashMap<>();
        String strQuery = "select "
                + "mm.id, "
                + "mm.consecutivo_orden, "
                + "mm.tipo_tecnologia, "
                + "mm.cantidad_total_entrega, "
                + "mm.entregados, "
                + "mm.pendiente, "
                + "mm.duracion_tratamiento, "
                + "mm.cantidad_formulada, "
                + "mp.id AS id_prescripcion, "
                + "mp.prestador_razon_social, "
                + "mp.numero_prescripcion, "
                + "mp.ma_tipo_documento_prestador_id, "
                + "mp.ma_tipo_documento_prestador_codigo, "
                + "mp.ma_tipo_documento_prestador_valor, "
                + "mp.prestador_numero_documento, "
                + "mp.sede_codigo_habilitacion "
                + "FROM mp_prescripcion_insumos mm INNER JOIN mp_prescripciones mp "
                + "ON mp.id = mm.mp_prescripcion_id "
                + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
        String whereIn = "(";
        for (Suministro suministro : listaSuministros) {
            if (suministro.getNoPrescripcion() != null && !suministro.getNoPrescripcion().equals("")
                    && suministro.getConTec() != null && suministro.getTipoTec() != null) {
                whereIn += "('" + suministro.getNoPrescripcion() + "',";
                whereIn += suministro.getConTec() + ",";
                whereIn += "'" + convertirTipoTec(suministro.getTipoTec()) + "'),";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjInsumos = null;
        try {
            listObjInsumos = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        if (listObjInsumos != null) {
            for (Object[] obj : listObjInsumos) {
                MpPrescripcionInsumo insumo = new MpPrescripcionInsumo();
                insumo.setId((Integer) (obj[0]));
                insumo.setConsecutivoOrden((Integer) (obj[1]));
                insumo.setTipoTecnologia((Integer) (obj[2]));
                insumo.setCantidadTotalEntrega((BigDecimal) obj[3]);
                insumo.setEntregados((BigDecimal) obj[4]);
                insumo.setPendiente((BigDecimal) obj[5]);
                insumo.setDuracionTratamiento((Integer) obj[6]);
                insumo.setCantidadFormulada((String) obj[7]);
                insumo.setMpPrescripcion(new MpPrescripcion((Integer) (obj[8])));
                insumo.getMpPrescripcion().setPrestadorRazonSocial((String) obj[9]);
                insumo.getMpPrescripcion().setNumeroPrescripcion((String) obj[10]);
                insumo.getMpPrescripcion().setMaTipoDocumentoPrestadorId((Integer) (obj[11]));
                insumo.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo((String) (obj[12]));
                insumo.getMpPrescripcion().setMaTipoDocumentoPrestadorValor((String) (obj[13]));
                insumo.getMpPrescripcion().setPrestadorNumeroDocumento((String) (obj[14]));
                insumo.getMpPrescripcion().setSedeCodigoHabilitacion((String) (obj[15]));
                listaInsumos.put(insumo.getMpPrescripcion().getNumeroPrescripcion() + "||" + insumo.getConsecutivoOrden(), insumo);
            }
        }
        return listaInsumos;
    }

    @Override
    public Map<String, MpDireccionamiento> consultarListaDireccionamientos(
            List<ReporteEntrega> entregas) throws java.lang.Exception {
        Map<String, MpDireccionamiento> listaDireccionamientos = new HashMap<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("FROM MpDireccionamientos p ")
                    .append("WHERE p.mpPrescripcionesId.numeroPrescripcion  IN ");
            String whereIn = "(";
            for (ReporteEntrega entrega : entregas) {
                whereIn += "" + entrega.getNoPrescripcion() + ",";
            }
            //Remover ultima ,
            if (!whereIn.equals("(")) {
                whereIn = whereIn.substring(0, whereIn.length() - 1);
            }
            whereIn += ") ";
            strQuery.append(whereIn);

            List<MpDireccionamientos> list = getEntityManager()
                    .createQuery(strQuery.toString())
                    .getResultList();
            int consecutivo = 0;
            if (list != null) {
                for (MpDireccionamientos obj : list) {
                    MpDireccionamiento direccionamiento = new MpDireccionamiento();
                    direccionamiento.setId(obj.getId());
                    // Prescripcion
                    MpPrescripcion presc = new MpPrescripcion();
                    presc.setId(obj.getMpPrescripcionesId().getId());
                    presc.setNumeroPrescripcion(obj.getMpPrescripcionesId().getNumeroPrescripcion());
                    direccionamiento.setMpPrescripcionId(presc);
                    // MpPrescripcionTecnologia
                    if (obj.getMpPrescripcionTecnologiasId() != null) {
                        MpPrescripcionTecnologia tec = new MpPrescripcionTecnologia();
                        //tipo tecnologia | 2 - Procedimientos
                        tec.setId(obj.getMpPrescripcionTecnologiasId().getId());
                        tec.setTipoTecnologia(obj.getMpPrescripcionTecnologiasId().getTipoTecnologia());
                        tec.setConsecutivoOrden(obj.getMpPrescripcionTecnologiasId().getConsecutivoOrden());
                        consecutivo = obj.getMpPrescripcionTecnologiasId().getConsecutivoOrden();
                        direccionamiento.setMpPrescripcionTecnologiaId(tec);
                    }
                    // MpPrescripcionMedicamento
                    if (obj.getMpPrescripcionMedicamentosId() != null) {
                        MpPrescripcionMedicamento med = new MpPrescripcionMedicamento();
                        //tipo tecnologia | 1 - Med 4 - Prod Nut
                        med.setId(obj.getMpPrescripcionMedicamentosId().getId());
                        med.setTipoTecnologia(obj.getMpPrescripcionMedicamentosId().getTipoTecnologia());
                        med.setConsecutivoOrden(obj.getMpPrescripcionMedicamentosId().getConsecutivoOrden());
                        consecutivo = obj.getMpPrescripcionMedicamentosId().getConsecutivoOrden();
                        direccionamiento.setMpPrescripcionMedicamentoId(med);
                    }
                    // MpPrescripcionMedicamento
                    if (obj.getMpPrescripcionInsumosId() != null) {
                        MpPrescripcionInsumo ins = new MpPrescripcionInsumo();
                        //tipo tecnologia | 3 - Dispositivos 5 - Serv Comp 
                        ins.setId(obj.getMpPrescripcionInsumosId().getId());
                        ins.setTipoTecnologia(obj.getMpPrescripcionInsumosId().getTipoTecnologia());
                        ins.setConsecutivoOrden(obj.getMpPrescripcionInsumosId().getConsecutivoOrden());
                        consecutivo = obj.getMpPrescripcionInsumosId().getConsecutivoOrden();
                        direccionamiento.setMpPrescripcionInsumoId(ins);
                    }
                    listaDireccionamientos.put(obj.getMpPrescripcionesId().getNumeroPrescripcion() + "||" + consecutivo, direccionamiento);
                }
            }
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaDireccionamientos;
    }

    @Override
    public Map<String, MpProgramadaEntrega> consultarListaEntregaMedicamentos(List<MpPrescripcionMedicamento> listaMedicamentos) throws Exception {
        Map<String, MpProgramadaEntrega> listaEntregas = new HashMap<>();
        String strQuery = "select "
                + "mm.id, mm.mp_prescripcion_medicamentos_id "
                + "FROM mp_programada_entregas mm "
                + "WHERE (mm.mp_prescripcion_medicamentos_id) IN ";
        String whereIn = "(";
        for (MpPrescripcionMedicamento medicamento : listaMedicamentos) {
            whereIn += "" + medicamento.getId() + ",";
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjEntregas = null;
        try {
            listObjEntregas = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        if (listObjEntregas != null) {
            for (Object[] obj : listObjEntregas) {
                MpProgramadaEntrega entrega = new MpProgramadaEntrega();
                entrega.setId((Integer) (obj[0]));
                entrega.setMedicamento(new MpPrescripcionMedicamento((Integer) (obj[1])));
                listaEntregas.put(entrega.getNumeroEntrega() + "-" + entrega.getMedicamento().getId(), entrega);
            }
        }
        return listaEntregas;
    }

    @Override
    public Map<String, MpProgramadaEntrega> consultarListaEntregaMedicamentosUnificado(List<Suministro> listaSuministros) throws Exception {

        String strQuery = "select "
                + "mm.id, "
                + "mm.consecutivo_orden, "
                + "mm.tipo_tecnologia, "
                + "mm.cantidad_total_entrega, "
                + "mm.entregados, "
                + "mm.pendientes, "
                + "mm.duracion_tratamiento, "
                + "mm.cantidad_tratamiento, "
                + "mm.cantidad_total_formulada, "
                + "mp.id AS id_prescripcion, "
                + "mp.prestador_razon_social, "
                + "mp.numero_prescripcion, "
                + "mp.ma_tipo_documento_prestador_id, "
                + "mp.ma_tipo_documento_prestador_codigo, "
                + "mp.ma_tipo_documento_prestador_valor, "
                + "mp.prestador_numero_documento, "
                + "mp.sede_codigo_habilitacion "
                + "FROM mp_prescripcion_medicamentos mm INNER JOIN mp_prescripciones mp "
                + "ON mp.id = mm.mp_prescripciones_id "
                + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
        String whereIn = "(";
        for (Suministro suministro : listaSuministros) {
            if (suministro.getNoPrescripcion() != null && !suministro.getNoPrescripcion().equals("")
                    && suministro.getConTec() != null && suministro.getTipoTec() != null) {
                whereIn += "('" + suministro.getNoPrescripcion() + "',";
                whereIn += suministro.getConTec() + ",";
                whereIn += "'" + convertirTipoTec(suministro.getTipoTec()) + "'),";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjMedicamentos = null;
        try {
            listObjMedicamentos = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        //String medicamentosIds = "";
        List<MpPrescripcionMedicamento> listaMedicamentos = new ArrayList<>();
        if (listObjMedicamentos != null) {
            for (Object[] obj : listObjMedicamentos) {
                MpPrescripcionMedicamento medicamento = new MpPrescripcionMedicamento();
                medicamento.setId((Integer) (obj[0]));
                listaMedicamentos.add(medicamento);
                //medicamentosIds = medicamentosIds+obj[0]+",";
            }
        }

        strQuery = "";
        whereIn = "";

        Map<String, MpProgramadaEntrega> listaEntregas = new HashMap<>();
        strQuery = "select "
                + "mm.id, mm.mp_prescripcion_medicamentos_id, mm.numero_entrega "
                + "FROM mp_programada_entregas mm "
                + "WHERE (mm.mp_prescripcion_medicamentos_id) IN ";
        whereIn = "(";
        for (MpPrescripcionMedicamento medicamento : listaMedicamentos) {
            whereIn += "" + medicamento.getId() + ",";
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjEntregas = null;
        try {
            listObjEntregas = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        if (listObjEntregas != null) {
            for (Object[] obj : listObjEntregas) {
                MpProgramadaEntrega entrega = new MpProgramadaEntrega();
                entrega.setId((Integer) (obj[0]));
                entrega.setMedicamento(new MpPrescripcionMedicamento((Integer) (obj[1])));
                entrega.setNumeroEntrega((Integer) (obj[2]));
                listaEntregas.put(entrega.getNumeroEntrega() + "||" + entrega.getMedicamento().getId(), entrega);
            }
        }
        return listaEntregas;
    }

    @Override
    public Map<String, MpProgramadaEntrega> consultarListaEntregaTecnologias(List<MpPrescripcionTecnologia> listaTecnologias) throws Exception {
        Map<String, MpProgramadaEntrega> listaEntregas = new HashMap<>();
        String strQuery = "SELECT "
                + "mm.id, mm.mp_prescripcion_tecnologias_id "
                + "FROM mp_programada_entregas mm "
                + "WHERE (mm.mp_prescripcion_tecnologias_id) IN ";
        String whereIn = "(";
        for (MpPrescripcionTecnologia tecnologia : listaTecnologias) {
            whereIn += "" + tecnologia.getId() + ",";
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjEntregas = null;
        try {
            listObjEntregas = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        if (listObjEntregas != null) {
            for (Object[] obj : listObjEntregas) {
                MpProgramadaEntrega entrega = new MpProgramadaEntrega();
                entrega.setId((Integer) (obj[0]));
                entrega.setTecnologia(new MpPrescripcionTecnologia((Integer) (obj[1])));
                listaEntregas.put(entrega.getNumeroEntrega() + "||" + entrega.getTecnologia().getId(), entrega);
            }
        }
        return listaEntregas;
    }

    @Override
    public Map<String, MpProgramadaEntrega> consultarListaEntregaTecnologiasUnificado(List<Suministro> listaSuministros) throws Exception {
        String strQuery = "select "
                + "mm.id, "
                + "mm.consecutivo_orden, "
                + "mm.tipo_tecnologia, "
                + "mm.cantidad_total_entrega, "
                + "mm.entregados, "
                + "mm.pendientes, "
                + "mm.cantidad_duracion_tratamiento, "
                + "mm.cantidad_total, "
                + "mm.cantidad_formulada, "
                + "mp.id AS id_prescripcion, "
                + "mp.prestador_razon_social, "
                + "mp.numero_prescripcion, "
                + "mp.ma_tipo_documento_prestador_id, "
                + "mp.ma_tipo_documento_prestador_codigo, "
                + "mp.ma_tipo_documento_prestador_valor, "
                + "mp.prestador_numero_documento, "
                + "mp.sede_codigo_habilitacion "
                + "FROM mp_prescripcion_tecnologias mm INNER JOIN mp_prescripciones mp "
                + "ON mp.id = mm.mp_prescripcion_id "
                + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
        String whereIn = "(";
        for (Suministro suministro : listaSuministros) {
            if (suministro.getNoPrescripcion() != null && !suministro.getNoPrescripcion().equals("")
                    && suministro.getConTec() != null && suministro.getTipoTec() != null) {
                whereIn += "('" + suministro.getNoPrescripcion() + "',";
                whereIn += suministro.getConTec() + ",";
                whereIn += "'" + convertirTipoTec(suministro.getTipoTec()) + "'),";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjTecnologias = getEntityManager().createNativeQuery(strQuery).getResultList();
        List<MpPrescripcionTecnologia> listaTecnologias = new ArrayList<>();
        if (listObjTecnologias != null) {
            for (Object[] obj : listObjTecnologias) {
                MpPrescripcionTecnologia tecnologia = new MpPrescripcionTecnologia();
                tecnologia.setId((Integer) (obj[0]));
                listaTecnologias.add(tecnologia);
            }
        }
        Map<String, MpProgramadaEntrega> listaEntregas = new HashMap<>();

        strQuery = "";
        whereIn = "";
        strQuery = "SELECT "
                + "mm.id, mm.mp_prescripcion_tecnologias_id, mm.numero_entrega "
                + "FROM mp_programada_entregas mm "
                + "WHERE (mm.mp_prescripcion_tecnologias_id) IN ";
        whereIn = "(";
        for (MpPrescripcionTecnologia tecnologia : listaTecnologias) {
            whereIn += "" + tecnologia.getId() + ",";
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjEntregas = getEntityManager().createNativeQuery(strQuery).getResultList();
        if (listObjEntregas != null) {
            for (Object[] obj : listObjEntregas) {
                MpProgramadaEntrega entrega = new MpProgramadaEntrega();
                entrega.setId((Integer) (obj[0]));
                entrega.setTecnologia(new MpPrescripcionTecnologia((Integer) (obj[1])));
                entrega.setNumeroEntrega((Integer) (obj[2]));
                listaEntregas.put(entrega.getNumeroEntrega() + "||" + entrega.getTecnologia().getId(), entrega);
            }
        }
        return listaEntregas;
    }

    @Override
    public Map<String, MpProgramadaEntrega> consultarListaEntregaInsumos(List<MpPrescripcionInsumo> listaInsumos) throws Exception {
        Map<String, MpProgramadaEntrega> listaEntregas = new HashMap<>();
        String strQuery = "select "
                + "mm.id, mm.mp_prescripcion_insumos_id "
                + "FROM mp_programada_entregas mm "
                + "WHERE (mm.mp_prescripcion_insumos_id) IN ";
        String whereIn = "(";
        for (MpPrescripcionInsumo insumo : listaInsumos) {
            whereIn += "" + insumo.getId() + ",";
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjEntregas = getEntityManager().createNativeQuery(strQuery).getResultList();
        if (listObjEntregas != null) {
            for (Object[] obj : listObjEntregas) {
                MpProgramadaEntrega entrega = new MpProgramadaEntrega();
                entrega.setId((Integer) (obj[0]));
                entrega.setInsumo(new MpPrescripcionInsumo((Integer) (obj[1])));
                listaEntregas.put(entrega.getNumeroEntrega() + "-" + entrega.getInsumo().getId(), entrega);
            }
        }
        return listaEntregas;
    }

    @Override
    public Map<String, MpProgramadaEntrega> consultarListaEntregaInsumosUnificado(List<Suministro> listaSuministros) throws Exception {
        String strQuery = "select "
                + "mm.id, "
                + "mm.consecutivo_orden, "
                + "mm.tipo_tecnologia, "
                + "mm.cantidad_total_entrega, "
                + "mm.entregados, "
                + "mm.pendiente, "
                + "mm.duracion_tratamiento, "
                + "mm.cantidad_formulada, "
                + "mp.id AS id_prescripcion, "
                + "mp.prestador_razon_social, "
                + "mp.numero_prescripcion, "
                + "mp.ma_tipo_documento_prestador_id, "
                + "mp.ma_tipo_documento_prestador_codigo, "
                + "mp.ma_tipo_documento_prestador_valor, "
                + "mp.prestador_numero_documento, "
                + "mp.sede_codigo_habilitacion "
                + "FROM mp_prescripcion_insumos mm INNER JOIN mp_prescripciones mp "
                + "ON mp.id = mm.mp_prescripcion_id "
                + "WHERE (mp.numero_prescripcion, mm.consecutivo_orden, mm.tipo_tecnologia) IN ";
        String whereIn = "(";
        for (Suministro suministro : listaSuministros) {
            if (suministro.getNoPrescripcion() != null && !suministro.getNoPrescripcion().equals("")
                    && suministro.getConTec() != null && suministro.getTipoTec() != null) {
                whereIn += "('" + suministro.getNoPrescripcion() + "',";
                whereIn += suministro.getConTec() + ",";
                whereIn += "'" + convertirTipoTec(suministro.getTipoTec()) + "'),";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjInsumos = getEntityManager().createNativeQuery(strQuery).getResultList();
        List<MpPrescripcionInsumo> listaInsumos = new ArrayList<>();
        if (listObjInsumos != null) {
            for (Object[] obj : listObjInsumos) {
                MpPrescripcionInsumo insumo = new MpPrescripcionInsumo();
                insumo.setId((Integer) (obj[0]));
                listaInsumos.add(insumo);
            }
        }
        strQuery = "";
        whereIn = "";
        Map<String, MpProgramadaEntrega> listaEntregas = new HashMap<>();
        strQuery = "select "
                + "mm.id, mm.mp_prescripcion_insumos_id, mm.numero_entrega "
                + "FROM mp_programada_entregas mm "
                + "WHERE (mm.mp_prescripcion_insumos_id) IN ";
        whereIn = "(";
        for (MpPrescripcionInsumo insumo : listaInsumos) {
            whereIn += "" + insumo.getId() + ",";
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ") ";
        strQuery += whereIn;
        List<Object[]> listObjEntregas = getEntityManager().createNativeQuery(strQuery).getResultList();
        if (listObjEntregas != null) {
            for (Object[] obj : listObjEntregas) {
                MpProgramadaEntrega entrega = new MpProgramadaEntrega();
                entrega.setId((Integer) (obj[0]));
                entrega.setInsumo(new MpPrescripcionInsumo((Integer) (obj[1])));
                entrega.setNumeroEntrega((Integer) (obj[2]));
                listaEntregas.put(entrega.getNumeroEntrega() + "||" + entrega.getInsumo().getId(), entrega);
            }
        }
        return listaEntregas;
    }

//    @Override
//    public List<MpDireccionamiento> consultarDireccionamientosPendientesXEnviar(Integer estado, String regimen) throws Exception {
//        List<MpDireccionamiento> listaDireccionamientos = new ArrayList();
//        try {
//            String strQuery = "FROM MpDireccionamientos mpd ";
//            strQuery += " WHERE mpd.estado = " + estado + " "
//                    + " AND mpd.mpPrescripcionesId.asegAfiliadosId.maeRegimenCodigo = '" + regimen + "'";
//            strQuery = strQuery + " ORDER BY id ASC";
//
//            Query query = getEntityManager().createQuery(strQuery).setMaxResults(100);
//            List<MpDireccionamientos> list = query
//                    .getResultList();
//            for (MpDireccionamientos per : list) {
//                listaDireccionamientos.add(castearEntidadNegocioDireccionamiento(per));
//            }
//        } catch (NoResultException e) {
//            listaDireccionamientos = new ArrayList();
//        } catch (Exception e) {
//            Exception(CONSULTAR_TODOS, e);
//        } finally {
//            cerrarEntityManager();
//        }
//        return listaDireccionamientos;
//    }
    @Override
    public List<MpDireccionamiento> consultarDireccionamientosPendientesXEnviar(Integer estado, String regimen) throws Exception {
        List<MpDireccionamiento> listaDireccionamientos = new ArrayList<>();
        try {
            String strQuery = "FROM MpDireccionamientos mpd "
                    + "WHERE mpd.estado = :estado "
                    + "AND mpd.mpPrescripcionesId.asegAfiliadosId.maeRegimenCodigo = :regimen "
                    + "AND mpd.eliminado IS NULL "
                    + "AND mpd.preeliminado IS NULL "
                    + "AND (mpd.envioCorreoAuto = false OR mpd.envioCorreoAuto IS NULL "
                    + "OR (mpd.envioCorreoAuto = true AND mpd.fechaEnvioAuto <= :fechaActual)) "
                    + "ORDER BY mpd.id ASC";
//            Query query = getEntityManager().createQuery(strQuery).setMaxResults(100);
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("estado", estado);
            query.setParameter("regimen", regimen);
            query.setParameter("fechaActual", new Date());
            List<MpDireccionamientos> list = query.getResultList();
            for (MpDireccionamientos per : list) {
                listaDireccionamientos.add(castearEntidadNegocioDireccionamiento(per));
            }
        } catch (NoResultException e) {
            listaDireccionamientos = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaDireccionamientos;
    }

    private MpDireccionamiento castearEntidadNegocioDireccionamiento(MpDireccionamientos entidad) {
        MpDireccionamiento negocio = new MpDireccionamiento();
        negocio.setId(entidad.getId());
        negocio.setTipoTecnologia(entidad.getTipoTecnologia());
        negocio.setMpPrescripcionId(new MpPrescripcion(entidad.getMpPrescripcionesId().getId()));
        negocio.setNumeroPrescripcionAso(entidad.getMpPrescripcionesId().getNumeroPrescripcion());
        if (entidad.getMpPrescripcionTecnologiasId() != null) {
            negocio.setMpPrescripcionTecnologiaId(new MpPrescripcionTecnologia());
            negocio.getMpPrescripcionTecnologiaId().setConsecutivoOrden(entidad.getMpPrescripcionTecnologiasId().
                    getConsecutivoOrden());
        } else if (entidad.getMpPrescripcionMedicamentosId() != null) {
            negocio.setMpPrescripcionMedicamentoId(new MpPrescripcionMedicamento());
            negocio.getMpPrescripcionMedicamentoId().setConsecutivoOrden(entidad.getMpPrescripcionMedicamentosId().
                    getConsecutivoOrden());
        } else if (entidad.getMpPrescripcionInsumosId() != null) {
            negocio.setMpPrescripcionInsumoId(new MpPrescripcionInsumo());
            negocio.getMpPrescripcionInsumoId().setConsecutivoOrden(entidad.getMpPrescripcionInsumosId().getConsecutivoOrden());
        }
        negocio.getMpPrescripcionId().setAsegAfiliado(new AsegAfiliado());
        negocio.getMpPrescripcionId().getAsegAfiliado().setDireccionResidencia(entidad.getMpPrescripcionesId().
                getAsegAfiliadosId().getDireccionResidencia());
        negocio.setMaeTipoDocumentoPacienteCodigo(entidad.getMaeTipoDocumentoCodigo());
        negocio.setNumeroDocumentoPaciente(entidad.getNumeroDocumentoPaciente());
        negocio.setConsecutivoEntrega(entidad.getConsecutivoEntrega());
        negocio.setSubEntrega(entidad.getSubEntrega());
        negocio.setMaeTipoDocumentoPrestadorCodigo(entidad.getMaeTipoDocumentoPrestadorCodigo());
        negocio.setPrestadorNumeroDocumentoStr(entidad.getPrestadorNumeroDocumento().toString());
        negocio.setUbicacionSedeIdStr(entidad.getUbicacionSedeId());
        negocio.setFechaMaxEntrega(entidad.getFechaMaxEntrega());
        negocio.setEntregaTotal(entidad.getEntregaTotal());
        negocio.setCodigoMpEntrega(entidad.getCodigoMpEntrega());
        negocio.setIdDireccionamiento(entidad.getIdDireccionamiento());
        return negocio;
    }

    @Override
    public List<MpEntregaFactura> consultarFacturadosPendientesXEnviar() throws java.lang.Exception {
        List<MpEntregaFactura> listResult = new ArrayList();
        try {
            String strQuery = "FROM MpEntregaFacturas mpef ";
            strQuery += "WHERE mpef.estadoMipres = " + 1 + " ";
            strQuery += "ORDER BY mpef.id ASC";

//            Query query = getEntityManager().createQuery(strQuery).setMaxResults(100);
            Query query = getEntityManager().createQuery(strQuery);
            List<MpEntregaFacturas> list = query
                    .getResultList();
            for (MpEntregaFacturas per : list) {
                listResult.add(castNegocioDtoMpEntregaFactura(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    private MpEntregaFactura castNegocioDtoMpEntregaFactura(MpEntregaFacturas obj) {
        MpEntregaFactura per = new MpEntregaFactura();
        try {
            // Entrega
            if (obj.getMpDireccionamientoEntregadosId() != null) {
                MpDireccionamientoEntregado dir = new MpDireccionamientoEntregado();
                dir.setId(obj.getMpDireccionamientoEntregadosId().getId());
                per.setMpDireccionamientoEntregadoId(dir);
            }
            per.setEstado(obj.getEstado());
            per.setIdTransaccion(obj.getIdTransaccion());
            per.setIdFacturacion(obj.getIdFacturacion());
            per.setNoSubEntrega(obj.getNoSubEntrega());
            per.setCufe(obj.getNoFactura());
            per.setNoFactura(obj.getNoFactura());
            per.setNoidEPS(obj.getNoidEPS());
            per.setCodEPS(obj.getCodEPS());
            per.setCantUnitaria(obj.getCantUnitaria());
            per.setValorTotal(obj.getValorTotal());
            per.setValorUnitario(obj.getValorUnitario());
            per.setCuotaModeradora(obj.getCuotaModeradora());
            per.setCopago(obj.getCopago());
            per.setFechaFacturacion(obj.getFechaFacturacion());
            per.setFechaAnulacion(obj.getFechaAnulacion());
            per.setMpEntregaFacturascol(obj.getMpEntregaFacturascol());
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return per;
    }

    @Override
    public List<MpDireccionamientoEntregado> consultarDireccionamientosPendientesXEnviar(boolean facturaCerrada, String regimen) throws java.lang.Exception {
        List<MpDireccionamientoEntregado> listResult = new ArrayList();
        try {
            String strQuery = "FROM MpDireccionamientoEntregados mpde ";
            strQuery += "WHERE mpde.cierre_ciclo = " + facturaCerrada + " ";
            strQuery += "AND mpde.id_facturacion IS NULL ";
            strQuery += "AND mpde.mpPrescripcionesId.asegAfiliadosId.maeRegimenCodigo = '" + regimen + "'";
            strQuery += "ORDER BY mpde.id ASC";

//            Query query = getEntityManager().createQuery(strQuery).setMaxResults(100);
            Query query = getEntityManager().createQuery(strQuery);
            List<MpDireccionamientoEntregados> list = query
                    .getResultList();
            for (MpDireccionamientoEntregados per : list) {
                MpDireccionamientoEntregado dir = new MpDireccionamientoEntregado();
                dir.setId(per.getId());
                dir.setIdTransaccion(per.getIdTransaccion());
//                dir.setTipoComparador(per.getTipoComparador());
                listResult.add(dir);
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public List<MpNoDireccionado> consultarNoDireccionamientosPorEstado(Integer estado, String regimen) throws java.lang.Exception {
        List<MpNoDireccionado> listaNoDireccionado = new ArrayList();
        try {
            String strQuery = "FROM MpNoDireccionados mpd ";
            strQuery += " WHERE mpd.estadoNoDireccionamiento = " + estado + ""
                    + " AND mpd.mpPrescripcionesId.asegAfiliadosId.maeRegimenCodigo = '" + regimen + "'";

            Query query = getEntityManager().createQuery(strQuery);
            List<MpNoDireccionados> list = query
                    .getResultList();
            for (MpNoDireccionados per : list) {
                listaNoDireccionado.add(castearEntidadNegocioNoDireccionado(per));
            }
        } catch (NoResultException e) {
            listaNoDireccionado = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaNoDireccionado;
    }

    private MpNoDireccionado castearEntidadNegocioNoDireccionado(MpNoDireccionados entidad) {
        MpNoDireccionado negocio = new MpNoDireccionado();
        negocio.setId(entidad.getId());
        negocio.setMpPrescripcionesId(new MpPrescripcion());
        negocio.getMpPrescripcionesId().setNumeroPrescripcion(entidad.getMpPrescripcionesId().getNumeroPrescripcion());
        negocio.getMpPrescripcionesId().setAsegAfiliado(new AsegAfiliado());
        negocio.getMpPrescripcionesId().getAsegAfiliado().setMaeTipoDocumentoCodigo(entidad.getMpPrescripcionesId().
                getAsegAfiliadosId().getMaeTipoDocumentoCodigo());
        negocio.getMpPrescripcionesId().getAsegAfiliado().setNumeroDocumento(entidad.getMpPrescripcionesId().
                getAsegAfiliadosId().getNumeroDocumento());
        negocio.getMpPrescripcionesId().getAsegAfiliado().setMaeRegimenCodigo(entidad.getMpPrescripcionesId().
                getAsegAfiliadosId().getMaeRegimenCodigo());
        negocio.setTipoTecnologia(entidad.getTipoTecnologia());
        if (entidad.getMpPrescripcionTecnologiasId() != null) {
            negocio.setMpPrescripcionTecnologiasId(new MpPrescripcionTecnologia());
            negocio.getMpPrescripcionTecnologiasId().setConsecutivoOrden(entidad.getMpPrescripcionTecnologiasId().
                    getConsecutivoOrden());
        } else if (entidad.getMpPrescripcionMedicamentosId() != null) {
            negocio.setMpPrescripcionMedicamentosId(new MpPrescripcionMedicamento());
            negocio.getMpPrescripcionMedicamentosId().setConsecutivoOrden(entidad.getMpPrescripcionMedicamentosId().
                    getConsecutivoOrden());
        } else if (entidad.getMpPrescripcionInsumosId() != null) {
            negocio.setMpPrescripcionInsumosId(new MpPrescripcionInsumo());
            negocio.getMpPrescripcionInsumosId().setConsecutivoOrden(entidad.getMpPrescripcionInsumosId().getConsecutivoOrden());
        }
        negocio.setConsecutivoTecnologia(entidad.getConsecutivoTecnologia());
        negocio.setCodigoNoDireccionamiento(entidad.getCodigoNoDireccionamiento());
        negocio.setNumeroPrescripcionAsociada(entidad.getNumeroPrescripcionAsociada());
        negocio.setConTecAsociada(entidad.getConTecAsociada());
        return negocio;
    }

    @Override
    public Map<String, MpDireccionamientoEntregado> consultarListaDatosFacturados(
            List<String> listaPrescripciones) throws java.lang.Exception {

        Map<String, MpDireccionamientoEntregado> mapDireccionamientos = new HashMap<>();
        try {
            if (!listaPrescripciones.isEmpty()) {
                StringBuilder strQuery = new StringBuilder();
                strQuery.append("FROM MpDireccionamientoEntregados p ")
                        .append("WHERE (p.id_transaccion) IN (");
                StringBuilder strWhere = new StringBuilder();
                for (String pre : listaPrescripciones) {
                    strWhere.append("('").append(pre).append("'),");
                }
                String whereIn = strWhere.toString();
                whereIn = whereIn.substring(0, whereIn.length() - 1);
                strQuery.append(whereIn);
                strQuery.append(")");
                List<MpDireccionamientoEntregados> list = getEntityManager()
                        .createQuery(strQuery.toString())
                        .getResultList();

                for (MpDireccionamientoEntregados per : list) {
                    MpDireccionamientoEntregado obj = new MpDireccionamientoEntregado();
                    obj.setId(per.getId());
                    obj.setIdTransaccion(per.getIdTransaccion());
                    mapDireccionamientos.put(per.getId() + "||" + per.getIdTransaccion(), obj);
                }
            }
        } catch (NoResultException e) {
            mapDireccionamientos = null;
        } catch (Exception e) {
            throw new Exception("Error consultarListaMpDireccionamientos  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return mapDireccionamientos;
    }

    @Override
    public boolean validarEstadoDireccionamiento(int idDireccionamiento) throws Exception {
        boolean esValido = false;
        try {
            String strQuery = "FROM MpDireccionamientos mpd ";
            strQuery += " WHERE mpd.estado = " + MpDireccionamiento.ESTADO_DIRECCIONAMIENTO_CREADO + " "
                    + " AND mpd.id = '" + idDireccionamiento + "'";
            strQuery = strQuery + " ORDER BY id ASC";

            Query query = getEntityManager().createQuery(strQuery).setMaxResults(100);
            List<MpDireccionamientos> list = query
                    .getResultList();

            if (!list.isEmpty()) {
                esValido = true;
                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE MpDireccionamientos ");
                sql.append("SET estado = :estado ");
                sql.append("WHERE id = :id ");
                query = getEntityManager().createQuery(sql.toString());
                query.setParameter("estado", MpDireccionamiento.ESTADO_DIRECCIONAMIENTO_DIRECCIONANDO);
                query.setParameter("id", idDireccionamiento);
                query.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("Error junta: " + e.getMessage() + "CAUSE" + e.getCause().getMessage());
        } finally {
            cerrarEntityManager();
        }
        return esValido;
    }

    @Override
    public List<MpDireccionamiento> consultarAnulamientosPendientesXEnviar(Integer estado, String regimen) throws Exception {
        List<MpDireccionamiento> listaDireccionamientos = new ArrayList();
        try {
            String strQuery = "FROM MpDireccionamientos mpd ";
            strQuery += " WHERE mpd.estado = " + estado + " "
                    + " AND mpd.mpPrescripcionesId.asegAfiliadosId.maeRegimenCodigo = '" + regimen + "'"
                    + " AND mpd.preeliminado = 1";
            strQuery = strQuery + " ORDER BY id ASC";

//            Query query = getEntityManager().createQuery(strQuery).setMaxResults(100);
            Query query = getEntityManager().createQuery(strQuery);
            List<MpDireccionamientos> list = query
                    .getResultList();
            for (MpDireccionamientos per : list) {
                listaDireccionamientos.add(castearEntidadNegocioAnular(per));
            }
        } catch (NoResultException e) {
            listaDireccionamientos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaDireccionamientos;
    }

    public boolean validarEstadoSuministro(int id) throws Exception {
        boolean esValido = false;
        Integer valor = 1;
        try {
            String strQuery = "FROM MpEntregaSuministros mpd ";
            strQuery += " WHERE mpd.estadoMipres = " + valor + " "
                    + " AND mpd.id = '" + id + "'";
            strQuery = strQuery + " ORDER BY id ASC";

            Query query = getEntityManager().createQuery(strQuery).setMaxResults(100);
            List<MpEntregaSuministros> list = query
                    .getResultList();

            if (!list.isEmpty()) {
                esValido = true;
                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE MpEntregaSuministros ");
                sql.append("SET estadoMipres = :estado ");
                sql.append("WHERE id = :id ");
                query = getEntityManager().createQuery(sql.toString());
                query.setParameter("estado", 9);
                query.setParameter("id", id);
                query.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("Error junta: " + e.getMessage() + "CAUSE" + e.getCause().getMessage());
        } finally {
            cerrarEntityManager();
        }
        return esValido;
    }

    @Override
    public void actualizarSuministroRespuesta(Integer id, Integer estadoMipres, Integer idSuministro) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpEntregaSuministros ");
            sql.append("SET estadoMipres = :estadoMipres, ");
            sql.append("idSuministro = :idSuministro ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estadoMipres", estadoMipres);
            query.setParameter("idSuministro", idSuministro.toString());
            query.setParameter("id", id);
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error junta: " + e.getMessage() + " CAUSE: " + e.getCause());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarRespuestaSuministro(Integer id, String respuesta) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpEntregaSuministros ");
            sql.append("SET respuestaSuministro = :respuesta ");
            sql.append("WHERE id = :id");
            Query query = getEntityManager().createQuery(sql.toString());
            if (respuesta != null && respuesta.length() > 512) {
                query.setParameter("respuesta", respuesta.substring(0, 512));
            } else {
                query.setParameter("respuesta", respuesta);
            }
            query.setParameter("id", id);
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error suministro error: " + e.getMessage() + " CAUSE: " + e.getCause());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarSuministroRespuestaFallo(Integer id) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpEntregaSuministros ");
            sql.append("SET estadoMipres = :estadoMipres ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estadoMipres", 1);
            query.setParameter("id", id);
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error junta: " + e.getMessage() + " CAUSE: " + e.getCause());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarSuministroRespuestaAnulacion(Integer id) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpEntregaSuministros ");
            sql.append(" SET estadoMipres = :estadoMipres, ");
            sql.append(" anulado = :anulado, ");
            sql.append(" fechaHoraAnula = :fecha ");
            sql.append(" WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estadoMipres", 4);
            query.setParameter("anulado", true);
            query.setParameter("fecha", new Date());
            query.setParameter("id", id);
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error junta: " + e.getMessage() + " CAUSE: " + e.getCause());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarEntregaProcesada(Integer id, Integer estado) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpDireccionamientoEntregados ");
            sql.append(" SET estRepEntrega = :estado ");
            sql.append(" WHERE id = :id ");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", estado);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage() + " CAUSE: " + e.getCause());
        } finally {
            cerrarEntityManager();
        }
    }

    private MpDireccionamiento castearEntidadNegocioAnular(MpDireccionamientos entidad) {
        MpDireccionamiento negocio = new MpDireccionamiento();
        negocio.setId(entidad.getId());
        negocio.setMpPrescripcionId(new MpPrescripcion());
        negocio.getMpPrescripcionId().setAsegAfiliado(new AsegAfiliado());
        negocio.getMpPrescripcionId().getAsegAfiliado().setMaeRegimenCodigo(entidad.getMpPrescripcionesId().getAsegAfiliadosId().getMaeRegimenCodigo());
        negocio.setIdDireccionamiento(entidad.getIdDireccionamiento());
        return negocio;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<MpPrescripcion> consultarPrescripcionesConsultaAnulacion(List<String> listaPrescripcionesAnuladas) throws Exception {
        List<MpPrescripcion> objRes = new ArrayList<>();
        if (listaPrescripcionesAnuladas == null || listaPrescripcionesAnuladas.isEmpty()) {
            return objRes;
        }

        try {
            String strQuery = "SELECT m FROM MpPrescripciones m "
                    + "WHERE m.numeroPrescripcion IN :lista "
                    + "AND m.estado = 4";

            TypedQuery<MpPrescripciones> query = getEntityManager().createQuery(strQuery, MpPrescripciones.class);
            query.setParameter("lista", listaPrescripcionesAnuladas);

            List<MpPrescripciones> prescripciones = query.getResultList();

            for (MpPrescripciones prescripcion : prescripciones) {
                MpPrescripcion prescripcionFiltrada = new MpPrescripcion();
                prescripcionFiltrada.setNumeroPrescripcion(prescripcion.getNumeroPrescripcion());
                prescripcionFiltrada.setId(prescripcion.getId());

                objRes.add(prescripcionFiltrada);
            }
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public List<MpNoDireccionado> consultarAnulamientosPendientesXEnviarNoDireccionados(Integer estado, String regimen) throws java.lang.Exception {
        List<MpNoDireccionado> listaNoDireccionamientos = new ArrayList();
        try {
            String strQuery = "FROM MpNoDireccionados mpd ";
            strQuery += " WHERE mpd.estadoNoDireccionamiento = " + estado + " "
                    + " AND mpd.mpPrescripcionesId.asegAfiliadosId.maeRegimenCodigo = '" + regimen + "'";
            strQuery = strQuery + " ORDER BY id ASC";

            Query query = getEntityManager().createQuery(strQuery);
            List<MpNoDireccionados> list = query
                    .getResultList();
            for (MpNoDireccionados per : list) {
                listaNoDireccionamientos.add(castearEntidadNegocioAnularNoDireccionado(per));
            }
        } catch (NoResultException e) {
            listaNoDireccionamientos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaNoDireccionamientos;
    }

    private MpNoDireccionado castearEntidadNegocioAnularNoDireccionado(MpNoDireccionados entidad) {
        MpNoDireccionado negocio = new MpNoDireccionado();
        negocio.setId(entidad.getId());
        negocio.setMpPrescripcionesId(new MpPrescripcion());
        negocio.getMpPrescripcionesId().setAsegAfiliado(new AsegAfiliado());
        negocio.getMpPrescripcionesId().getAsegAfiliado().setMaeRegimenCodigo(entidad.getMpPrescripcionesId().getAsegAfiliadosId().getMaeRegimenCodigo());
        negocio.setIdNoDireccionamiento(entidad.getIdNoDireccionamiento());
        negocio.setIdTransaccion(entidad.getIdTransaccion());
        negocio.setTipoTecnologia(entidad.getTipoTecnologia());
        if (entidad.getMpPrescripcionMedicamentosId() != null) {
            negocio.setMpPrescripcionMedicamentosId(
                    new MpPrescripcionMedicamento(entidad.getMpPrescripcionMedicamentosId().getId())
            );
        }

        if (entidad.getMpPrescripcionTecnologiasId() != null) {
            negocio.setMpPrescripcionTecnologiasId(
                    new MpPrescripcionTecnologia(entidad.getMpPrescripcionTecnologiasId().getId())
            );
        }

        if (entidad.getMpPrescripcionInsumosId() != null) {
            negocio.setMpPrescripcionInsumosId(
                    new MpPrescripcionInsumo(entidad.getMpPrescripcionInsumosId().getId())
            );
        }

        //Falta NIt
        return negocio;
    }

    @Override
    public void actualizaItemNoDireccionamiento(int tipoTecnologia, int idItem) throws Exception {
        try {
            String hql = "";
            switch (tipoTecnologia) {
                case 1:
                case 4:
                    hql = "UPDATE MpPrescripcionMedicamentos SET estado = " + MpPrescripcionMedicamento.ESTADO_PENDIENTE + " "
                            + "WHERE id = " + idItem;
                    break;
                case 2:
                    hql = "UPDATE MpPrescripcionTecnologias SET estado = " + MpPrescripcionTecnologia.ESTADO_PENDIENTE + " "
                            + "WHERE id = " + idItem;
                    break;
                case 3:
                case 5:
                    hql = "UPDATE MpPrescripcionInsumos SET estado = " + MpPrescripcionInsumo.ESTADO_PENDIENTE + " "
                            + "WHERE id = " + idItem;
                    break;
                default:
                    break;
            }
            if (!hql.isEmpty()) {
                Query query = getEntityManager().createQuery(hql.toString());
                query.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error junta: " + e.getMessage() + "CAUSE" + e.getCause().getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

}
