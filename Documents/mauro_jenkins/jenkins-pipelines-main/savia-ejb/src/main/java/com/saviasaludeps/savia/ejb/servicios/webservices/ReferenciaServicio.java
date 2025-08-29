package com.saviasaludeps.savia.ejb.servicios.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.GnCorreoEnvio;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Historico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorContacto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSedeServicioHabilitacion;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Diagnostico;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Item;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2RescateGestion;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2RescateSede;
import com.saviasaludeps.savia.dominio.crue.AuTipoRescate;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifarioValor;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Diagnosticos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos2;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos4;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionales;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnMaestroAccionRelaciones;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.MaDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.MaServiciosHabilitacion;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologias;
import com.saviasaludeps.savia.ejb.entidades.RefAnexo9Adjuntos;
import com.saviasaludeps.savia.ejb.entidades.RefAnexo9DatosClinicos;
import com.saviasaludeps.savia.ejb.entidades.RefAnexo9Diagnosticos;
import com.saviasaludeps.savia.ejb.entidades.RefAnexo9Estados;
import com.saviasaludeps.savia.ejb.entidades.RefAnexos9;
import com.saviasaludeps.savia.ejb.utilidades.AuReporte;
import com.saviasaludeps.savia.ejb.utilidades.DateUtil;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.utilidades.PropApl;
import com.saviasaludeps.savia.ejb.utilidades.RemotoEJB;
import com.saviasaludeps.savia.negocio.administracion.ConfiguracionRemoto;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.GnCorreoEnvioRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4HistoricoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4Remoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorContactoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorProfesionalRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeServicioHabilitacionRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntProfesionalRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2DiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2ItemRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateGestionRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateSedeRemoto;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9Remoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaEspecialidadRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaServicioHabilitacionRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaSoatTarifarioValorRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaRemoto;
import com.saviasaludeps.savia.negocio.webservices.ReferenciaRemoto;
import com.saviasaludeps.savia.solicitud.dominio.AuConstantes;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaCopagoDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.referencia.AdjuntoDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.referencia.AutorizacionAnexo2DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.referencia.DetalleRespuestaSolicitudAnexo9DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.referencia.ReferenciaAnexo9DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.referencia.RespuestaSolicitudAnexo2DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.referencia.RespuestaSolicitudAnexo9DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.referencia.SolicitudAnexo2DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.referencia.SolicitudAnexo9DTO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;

/**
 *
 * @author yjimenez
 */
@Stateless
@Remote(ReferenciaRemoto.class)
public class ReferenciaServicio extends GenericoServicio implements ReferenciaRemoto {

    private final static String FN_GN_VAL_CONTRATO = "fn_gn_val_contrato";
    private final static String FN_GN_CAL_COPAGOMODERADORA = "fn_gn_cal_copagomoderadora";
    public final static String CODIGO_SERVICIO_HABILITACION_URGENCIAS = "1102";
    public final static int CODIGO_SERVICIO_HABILITACION_SERVICIO_URGENCIAS = 501;
    public final static String CODIGO_SERVICIO_HABILITACION_OODONTOLOGIA = "334";
    public final static String REF_URGENCIA_MEDICA_TECNOLOGIA = "890701";
    public final static String REF_URGENCIA_ODONTOLOGICA_TECNOLOGIA = "890703";
    public final static String HABILITACION_URGENCIAS = "REF_URGENCIA_HABILITACION";

    //ANEXO2
    public final static String AU4_OBSERVACION = "Autorizacion Automatica";

    private final static String USUARIO_IVR = "IVR";
    private final static String REPORTE_IVR = "reporte IVR";
    private final static String TERMINAL_IVR = "127.0.0.1";

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private CntPrestadorSedeServicioHabilitacionRemoto getCntPrestadorSedeServicioHabilitacionRemoto() throws Exception {
        return (CntPrestadorSedeServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicioHabilitacionServicio", CntPrestadorSedeServicioHabilitacionRemoto.class.getName());
    }

    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
        return (EmpresaRemoto) object;
    }

    private AuAnexo4Remoto getAuAnexo4Remoto() throws Exception {
        return (AuAnexo4Remoto) RemotoEJB.getEJBRemoto("AuAnexo4Servicio", AuAnexo4Remoto.class.getName());
    }

    private CntContratoDetalleRemoto getContratoDetalleRemoto() throws Exception {
        return (CntContratoDetalleRemoto) RemotoEJB.getEJBRemoto(("CntContratoDetalleServicio"), CntContratoDetalleRemoto.class.getName());
    }

    private MaSoatTarifarioValorRemoto getTarifarioValorRemoto() throws Exception {
        return (MaSoatTarifarioValorRemoto) RemotoEJB.getEJBRemoto(("MaSoatTarifarioValorServicio"), MaSoatTarifarioValorRemoto.class.getName());
    }

    private AuAnexo4ItemRemoto getAuAnexo4ItemRemoto() throws Exception {
        return (AuAnexo4ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo4ItemServicio", AuAnexo4ItemRemoto.class.getName());
    }

    private MaTecnologiaRemoto getMaTecnologiaRemoto() throws Exception {
        return (MaTecnologiaRemoto) RemotoEJB.getEJBRemoto("MaTecnologiaServicio", MaTecnologiaRemoto.class.getName());
    }

    private ConfiguracionRemoto getConfiguracionRemoto() throws Exception {
        return (ConfiguracionRemoto) RemotoEJB.getEJBRemoto("ConfiguracionServicio", ConfiguracionRemoto.class.getName());
    }

    private MaServicioHabilitacionRemoto getMaServicioHabilitacionRemoto() throws Exception {
        return (MaServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto("MaServicioHabilitacionServicio", MaServicioHabilitacionRemoto.class.getName());
    }

    private AuAnexo2ItemRemoto getAuAnexo2ItemRemoto() throws Exception {
        return (AuAnexo2ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo2ItemServicio", AuAnexo2ItemRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private CntProfesionalRemoto getCntProfesionalRemoto() throws Exception {
        return (CntProfesionalRemoto) RemotoEJB.getEJBRemoto("CntProfesionalServicio", CntProfesionalRemoto.class.getName());
    }

    private AuAnexo2DiagnosticoRemoto getAuAnexo2DiagnosticoRemoto() throws Exception {
        return (AuAnexo2DiagnosticoRemoto) RemotoEJB.getEJBRemoto("AuAnexo2DiagnosticoServicio", AuAnexo2DiagnosticoRemoto.class.getName());
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private UbicacionRemoto getUbicacionRemoto() throws Exception {
        return (UbicacionRemoto) RemotoEJB.getEJBRemoto("UbicacionServicio", UbicacionRemoto.class.getName());
    }

    private RefAnexo9Remoto getRefAnexo9Remoto() throws Exception {
        return (RefAnexo9Remoto) RemotoEJB.getEJBRemoto("RefAnexo9Servicio", RefAnexo9Remoto.class.getName());
    }

    private MaEspecialidadRemoto getMaEspecialidadRemoto() throws Exception {
        return (MaEspecialidadRemoto) RemotoEJB.getEJBRemoto("MaEspecialidadServicio", MaEspecialidadRemoto.class.getName());
    }

    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private AuAnexo2RescateRemoto getAuAnexo2RescateRemoto() throws Exception {
        return (AuAnexo2RescateRemoto) RemotoEJB.getEJBRemoto("AuAnexo2RescateServicio", AuAnexo2RescateRemoto.class.getName());
    }

    private AuAnexo2RescateGestionRemoto getAuAnexo2RescateGestionRemoto() throws Exception {
        return (AuAnexo2RescateGestionRemoto) RemotoEJB.getEJBRemoto("AuAnexo2RescateGestionServicio", AuAnexo2RescateGestionRemoto.class.getName());
    }

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private CntPrestadorProfesionalRemoto getCntPrestadorProfesionalRemoto() throws Exception {
        return (CntPrestadorProfesionalRemoto) RemotoEJB.getEJBRemoto("CntPrestadorProfesionalServicio", CntPrestadorProfesionalRemoto.class.getName());
    }

    private MaEspecialidadRemoto getEspecialidadRemoto() throws Exception {
        return (MaEspecialidadRemoto) RemotoEJB.getEJBRemoto(("MaEspecialidadServicio"), MaEspecialidadRemoto.class.getName());
    }

    private AuAnexo4HistoricoRemoto getAuAnexo4HistoricoRemoto() throws Exception {
        return (AuAnexo4HistoricoRemoto) RemotoEJB.getEJBRemoto("AuAnexo4HistoricoServicio", AuAnexo4HistoricoRemoto.class.getName());
    }

    private CntPrestadorContactoRemoto getCntPrestadorContactoRemoto() throws Exception {
        return (CntPrestadorContactoRemoto) RemotoEJB.getEJBRemoto("CntPrestadorContactoServicio", CntPrestadorContactoRemoto.class.getName());
    }

    private GnCorreoEnvioRemoto getGnCorreoEnvioRemoto() throws Exception {
        return (GnCorreoEnvioRemoto) RemotoEJB.getEJBRemoto("GnCorreoEnvioServicio", GnCorreoEnvioRemoto.class.getName());
    }

    private AuAnexo2RescateSedeRemoto getAuAnexo2RescateSedeRemoto() throws Exception {
        return (AuAnexo2RescateSedeRemoto) RemotoEJB.getEJBRemoto("AuAnexo2RescateSedeServicio", AuAnexo2RescateSedeRemoto.class.getName());
    }

    @Override
    public RespuestaSolicitudAnexo2DTO crearSolicitudAnexo2(SolicitudAnexo2DTO solicitud, String rutaInforme) throws Exception {
        RespuestaSolicitudAnexo2DTO respuesta;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        if (solicitud.getTipoUrgencia() == 1 || solicitud.getTipoUrgencia() == 0) {
            GnMaestros tipoDocumento = consultarMaestroPorValorTipo(solicitud.getTipoDocumento(), MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
            if (tipoDocumento != null) {
                AsegAfiliados afiliado = consultarAfiliado(tipoDocumento.getId().toString(), solicitud.getNumeroDocumento());
                if (afiliado != null) {
                    GnMaestros maeEstado = consultarMaestroPorValorTipo(afiliado.getMaeEstadoAfiliacionCodigo(), MaestroTipo.ASEG_ESTADO_AFILIACION);
                    if (maeEstado != null) {
                        boolean activo = false;
                        for (GnMaestroAccionRelaciones perRel : maeEstado.getGnMaestroAccionRelacionesList()) {
                            if (perRel.getGnMaestroAccionesId().getId() == MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO) {
                                activo = true;
                                break;
                            }
                        }
                        if (activo) {
                            CntPrestadorSedes prestador = consultarPrestadorPorCodigoHabilitacion(solicitud.getCodigoHabilitacion());
                            if (prestador != null) {
                                AuAnexos2 anexos2 = castAuAnexos2(afiliado, prestador, solicitud.getTipoUrgencia());
                                anexos2.setIdLlamada(solicitud.getId());
                                AuAnexo2 anexo2 = castAuAnexo2(afiliado, prestador, solicitud.getTipoUrgencia());
                                AuAnexo2Items anexo2Item;
                                String servicios = CODIGO_SERVICIO_HABILITACION_URGENCIAS + "," + CODIGO_SERVICIO_HABILITACION_SERVICIO_URGENCIAS;

                                List<CntPrestadorSedeServicioHabilitacion> listCntPrestadorSedeServicioHabilitacion;

                                AuAnexo2Item auAnexos2Item = new AuAnexo2Item();
                                auAnexos2Item.setCantidadSolicitada(1);

                                MaServicioHabilitacion servicio = getMaServicioHabilitacionRemoto().consultarPorCodigo(CODIGO_SERVICIO_HABILITACION_SERVICIO_URGENCIAS);
//                                MaServicioHabilitacion servicio = listServicios.stream()
//                                        .filter(servi -> servi.getCodigo() == CODIGO_SERVICIO_HABILITACION_SERVICIO_URGENCIAS)
//                                        .findFirst()
//                                        .get();
                                if (servicio != null) {
                                    auAnexos2Item.setMaServicioSolicitadoId(servicio.getId());
                                    auAnexos2Item.setMaServicioSolicitadoCodigo(servicio.getCodigo() + "");
                                    auAnexos2Item.setMaServicioSolicitadoValor(servicio.getNombre());
                                }
                                if (solicitud.getTipoUrgencia() == 0) { //Medica
                                    String cnfiguracionMedica
                                            = PropApl.getInstance().get(PropApl.REF_URGENCIA_MEDICA_TECNOLOGIA);

                                    MaTecnologia tec = getMaTecnologiaRemoto().consultarPorCodigo(cnfiguracionMedica);
                                    auAnexos2Item.setMaTecnologiaId(tec.getId());
                                    auAnexos2Item.setMaTecnologiaCodigo(tec.getCodigoPropio());
                                    auAnexos2Item.setMaTecnologiaValor(tec.getPropioDescripcion());

                                    //Se valida por REPS
                                    listCntPrestadorSedeServicioHabilitacion = getCntPrestadorSedeServicioHabilitacionRemoto()
                                            .consultarPorSedeTecnologiaYServiciosHabilitacion(
                                                    prestador.getId(),
                                                    cnfiguracionMedica,
                                                    servicios
                                            );
                                } else {//Odontologia
                                    String cnfiguracionOdontologica
                                            = PropApl.getInstance().get(PropApl.REF_URGENCIA_ODONTOLOGICA_TECNOLOGIA);

                                    MaTecnologia tec = getMaTecnologiaRemoto().consultarPorCodigo(cnfiguracionOdontologica);
                                    auAnexos2Item.setMaTecnologiaId(tec.getId());
                                    auAnexos2Item.setMaTecnologiaCodigo(tec.getCodigoPropio());
                                    auAnexos2Item.setMaTecnologiaValor(tec.getPropioDescripcion());

                                    //ivanegaa 2022-02-03 valida que el prestador sea odontologico
                                    listCntPrestadorSedeServicioHabilitacion = getCntPrestadorSedeServicioHabilitacionRemoto()
                                            .consultarPorSedeTecnologiaYServiciosHabilitacion(
                                                    prestador.getId(),
                                                    cnfiguracionOdontologica,
                                                    CODIGO_SERVICIO_HABILITACION_OODONTOLOGIA
                                            );
                                    if (listCntPrestadorSedeServicioHabilitacion.stream()
                                            .findFirst().orElse(null) != null) {
                                        //Se valida por REPS que sea urgencias
                                        listCntPrestadorSedeServicioHabilitacion = getCntPrestadorSedeServicioHabilitacionRemoto()
                                                .consultarPorSedeTecnologiaYServiciosHabilitacion(
                                                        prestador.getId(),
                                                        cnfiguracionOdontologica,
                                                        servicios
                                                );
                                    }
                                }
                                List<AuAnexo2Item> listaAnexo2Items = new ArrayList();
                                listaAnexo2Items.add(auAnexos2Item);
                                anexo2.setListaAuAnexo2Item(listaAnexo2Items);

                                CntPrestadorSedeServicioHabilitacion prestadorSedeServicioHabilitacion = listCntPrestadorSedeServicioHabilitacion.stream()
                                        .filter(service -> service.isActivo())
                                        .findFirst().orElse(null);
                                if (prestadorSedeServicioHabilitacion != null) {
                                    anexo2.setEstado(AuAnexo2.ESTADO_AUTORIZADA_AUTOMATICA);
                                    anexos2.setFuenteOrigen(AuAnexo2.FUENTE_ORIGEN_IVR);
                                    anexo2.setId(insertarAuAnexos2(anexos2));
                                    auAnexos2Item.setAuAnexos2Id(anexo2);
                                    auAnexos2Item.setCantidadSolicitada(1);
                                    auAnexos2Item.setMaServicioSolicitadoId(175);
                                    auAnexos2Item.setMaServicioSolicitadoCodigo("501");
                                    auAnexos2Item.setMaServicioSolicitadoValor("SERVICIO DE URGENCIAS - URGENCIAS BAJA COMPLEJIDAD");
                                    auAnexos2Item.setUsuarioCrea(USUARIO_IVR);
                                    auAnexos2Item.setTerminalCrea(TERMINAL_IVR);
                                    auAnexos2Item.setFechaHoraCrea(new Date());
                                    int idAnexo2Item = getAuAnexo2ItemRemoto().insertar(auAnexos2Item);
                                    auAnexos2Item.setId(idAnexo2Item);
                                    anexo2Item = castAuAnexo2Items(anexos2, solicitud.getTipoUrgencia());
                                    AuAnexo2Diagnostico diagnostico = castAuAnexo2Diagnosticos(anexo2, solicitud.getTipoUrgencia());
                                    diagnostico.setId(getAuAnexo2DiagnosticoRemoto().insertar(diagnostico));
                                    AuAnexo4 anexos4 = crearAnexo4(anexo2, auAnexos2Item);
                                    respuesta = new RespuestaSolicitudAnexo2DTO();
                                    respuesta.setRc("0");
                                    respuesta.setMsg("Autorización completada");
                                    respuesta.setFechaTransaccion(dateFormat.format(new Date()));
                                    AutorizacionAnexo2DTO autorizacion = new AutorizacionAnexo2DTO();
                                    autorizacion.setNua(anexos4.getId().toString());
                                    autorizacion.setCodigoRespuesta("2");
                                    autorizacion.setNumeroAtencion(anexo2.getId().toString());
                                    respuesta.setAutorizacion(autorizacion);
                                    crearAnexo2Rescate(anexo2);
                                    /* Flujo ips capita */
                                    crearAnexo2RescateSede(anexo2);
                                } else {
                                    respuesta = new RespuestaSolicitudAnexo2DTO();
                                    respuesta.setRc("-1000");
                                    respuesta.setMsg("error prestador Sin ServicioHabilitacion");
                                    respuesta.setFechaTransaccion(dateFormat.format(new Date()));
                                }
                            } else {
                                respuesta = new RespuestaSolicitudAnexo2DTO();
                                respuesta.setRc("-1000");
                                respuesta.setMsg("Sede del prestador no existe o está Inactiva");
                                respuesta.setFechaTransaccion(dateFormat.format(new Date()));
                            }
                        } else {
                            respuesta = new RespuestaSolicitudAnexo2DTO();
                            respuesta.setRc("-1000");
                            respuesta.setMsg("Afiliado Inactivo");
                            respuesta.setFechaTransaccion(dateFormat.format(new Date()));
                        }
                    } else {
                        respuesta = new RespuestaSolicitudAnexo2DTO();
                        respuesta.setRc("-1000");
                        respuesta.setMsg("Afiliado Inactivo");
                        respuesta.setFechaTransaccion(dateFormat.format(new Date()));
                    }

                } else {
                    respuesta = new RespuestaSolicitudAnexo2DTO();
                    respuesta.setRc("-1000");
                    respuesta.setMsg("Afiliado no encontrado");
                    respuesta.setFechaTransaccion(dateFormat.format(new Date()));

                }
            } else {
                respuesta = new RespuestaSolicitudAnexo2DTO();
                respuesta.setRc("-1000");
                respuesta.setMsg("Tipo de Documento Inválido");
                respuesta.setFechaTransaccion(dateFormat.format(new Date()));
            }
        } else {
            respuesta = new RespuestaSolicitudAnexo2DTO();
            respuesta.setRc("-1000");
            respuesta.setMsg("Tipo urgencia Invalida");
            respuesta.setFechaTransaccion(dateFormat.format(new Date()));
        }

        return respuesta;
    }

    @Override
    public RespuestaSolicitudAnexo9DTO crearSolicitudAnexo9(SolicitudAnexo9DTO solicitudes, String usuario, String rutaAdjunto, String rutaAnexo) throws Exception {
        RespuestaSolicitudAnexo9DTO respuesta = new RespuestaSolicitudAnexo9DTO();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        respuesta.setFechaTransaccion(dateFormat.format(new Date()));
        String error = "";
        respuesta.setReferencias(new ArrayList());
        respuesta.setCodigoRespuesta("0");
        if (!solicitudes.getReferencias().isEmpty()) {
            respuesta.setCantidadRegistros(solicitudes.getReferencias().size() + "");
        }
        if (solicitudes.getNut() == 0) {
            error = error + "Nut Requerido |";
        }
        if (solicitudes.getFechaHoraTransaccion() == null) {
            error = error + "FechaHoraTransaccion Requerido |";
        }
        if (solicitudes.getCantidadRegistros() == 0) {
            error = error + "CantidadRegistros Requerido |";
        }
        if (solicitudes.getCodHabilitacionPrestador() == null) {
            error = error + "CodHabilitacionPrestador Requerido |";
        }

        for (ReferenciaAnexo9DTO solicitud : solicitudes.getReferencias()) {
            Ubicacion ubicacionResidencia = new Ubicacion();
            DetalleRespuestaSolicitudAnexo9DTO detalle = new DetalleRespuestaSolicitudAnexo9DTO();
            detalle.setCodigoRespuesta("0");
            //2024-05-20| validaciones nuevas campos A9 HU 960
            AsegAfiliado afiliado = null;
            MaServiciosHabilitacion servicioSolicita = null;
            MaServiciosHabilitacion servicioRefiere = null;
            if (solicitud.getTipoDocumento() != null) {
                GnMaestros tipoDocumento = consultarMaestroPorValorTipo(
                        solicitud.getTipoDocumento(),
                        MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
                if (tipoDocumento != null) {
                    if (solicitud.getDocumento() != null) {
                        if (!solicitud.getDocumento().matches("[0-9]*")) {
                            error = error + (" Numero Documento Invalido |");
                        }
                        afiliado = getAfiliadoRemoto().consultar(
                                tipoDocumento.getId(),
                                solicitud.getDocumento());
                    } else {
                        error = error + "Documento Requerido |";
                    }
                } else {
                    error = error + "TipoDocumento no se reconoce |";
                }
            } else {
                error = error + "TipoDocumento Requerido |";
            }
            if (afiliado != null) {
                Maestro maeEstado = getMaestroRemoto().consultarPorValorTipo(afiliado.getMaeEstadoAfiliacionCodigo(), MaestroTipo.ASEG_ESTADO_AFILIACION);
                if (maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {

                    if (solicitud.getConsecutivo() == null) {
                        error = error + "Consecutivo Requerido |";
                    } else {
                        if (!solicitud.getConsecutivo().matches("[0-9]*")) {
                            error = error + (" Consecutivo Invalido |");
                        }
                    }

                    if (solicitud.getTipoAnexo() == null) {
                        error = error + "TipoAnexo Requerido |";
                    } else {
                        if (!validarTipoAnexo(solicitud.getTipoAnexo())) {
                            error = error + ("No se reconoce tipo de anexo reportado |");
                        }
                    }

                    // Afiliado
                    if (solicitud.getTipoDocumento() == null) {
                        error = error + "TipoDocumento Requerido |";
                    } else {
                        GnMaestros tipoDocumento = consultarMaestroPorValorTipo(
                                solicitud.getTipoDocumento(),
                                MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
                        if (tipoDocumento == null) {
                            error = error + "TipoDocumento no se reconoce|";
                        }
                    }

                    if (solicitud.getDocumento() == null) {
                        error = error + "Documento Requerido |";
                    } else {
                        if (!solicitud.getDocumento().matches("[0-9]*")) {
                            error = error + (" Numero Documento Invalido |");
                        }
                    }

                    // Acompañante 
                    if (solicitud.getTipodocumentoAcompaniante() != null) {
                        GnMaestros tipoDocumentoAcom = consultarMaestroPorValorTipo(
                                solicitud.getTipodocumentoAcompaniante(),
                                MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
                        if (tipoDocumentoAcom == null) {
                            error = error + "TipoDocumentoAcompaniante no se reconoce|";
                        }
                    } else {
                        error = error + "TipoDocumentoAcompaniante Requerido |";
                    }

                    if (solicitud.getDocumentoAcompaniante() == null
                            || solicitud.getDocumentoAcompaniante().isEmpty()) {
                        error = error + "DocumentoAcompaniante Requerido |";
                    } else {
                        if (!solicitud.getDocumentoAcompaniante().matches("[0-9]*")) {
                            error = error + (" Numero DocumentoAcompaniante Invalido |");
                        }
                    }

                    if (solicitud.getPrimerNombreAcompaniante() == null) {
                        error = error + "PrimerNombreAcompaniante Requerido |";
                    } else {
                        if (!(validarCampoTexto(solicitud.getPrimerNombreAcompaniante()).equals(""))) {
                            error = error + (validarCampoTexto(solicitud.getPrimerNombreAcompaniante()) + "PrimerNombreAcompaniante |");
                        }
                        if (solicitud.getPrimerNombreAcompaniante().length() > 63) {
                            error = error + "PrimerNombreAcompaniante excede cantidad de caracteres permitidos |";
                        }
                    }

                    if (solicitud.getPrimerApellidoAcompaniante() == null) {
                        error = error + "PrimerApellidoAcompaniante Requerido |";
                    } else {
                        if (!(validarCampoTexto(solicitud.getPrimerApellidoAcompaniante()).equals(""))) {
                            error = error + (validarCampoTexto(solicitud.getPrimerApellidoAcompaniante()) + "PrimerApellidoAcompaniante |");
                        }
                        if (solicitud.getPrimerApellidoAcompaniante().length() > 63) {
                            error = error + "PrimerApellidoAcompaniante excede cantidad de caracteres permitidos |";
                        }
                    }

                    if (solicitud.getSegundoNombreAcompaniante() != null) {
                        if (!(validarCampoTexto(solicitud.getSegundoNombreAcompaniante()).equals(""))) {
                            error = error + (validarCampoTexto(solicitud.getSegundoNombreAcompaniante()) + "SegundoNombreAcompaniante |");
                        }
                        if (solicitud.getSegundoNombreAcompaniante().length() > 63) {
                            error = error + "SegundoNombreAcompaniante excede cantidad de caracteres permitidos |";
                        }
                    }

                    if (solicitud.getSegundoApellidoAcompaniante() != null) {
                        if (!(validarCampoTexto(solicitud.getSegundoApellidoAcompaniante()).equals(""))) {
                            error = error + (validarCampoTexto(solicitud.getSegundoApellidoAcompaniante()) + "SegundoApellidoAcompaniante |");
                        }
                        if (solicitud.getSegundoApellidoAcompaniante().length() > 63) {
                            error = error + "SegundoApellidoAcompaniante excede cantidad de caracteres permitidos |";
                        }
                    }

                    if (solicitud.getTelefonoAcompaniante() == null) {
                        error = error + "TelefonoAcompaniante Requerido |";
                    } else {
                        if (!solicitud.getTelefonoAcompaniante().matches("[0-9]*")) {
                            error = error + (" Numero TelefonoAcompaniante Invalido |");
                        }
                        if (solicitud.getTelefonoAcompaniante().length() < 10
                                || solicitud.getTelefonoAcompaniante().length() > 11) {
                            error = error + (" Numero TelefonoAcompaniante debe ser de diez digitos |");
                        }
                    }

                    if (solicitud.getDireccionAcompaniante() == null
                            || solicitud.getDireccionAcompaniante().isEmpty()) {
                        error = error + "DireccionAcompaniante Requerido |";
                    }

                    if (solicitud.getCodigoMunicipioAcompaniante() != null
                            && !solicitud.getCodigoMunicipioAcompaniante().isEmpty()) {
                        String departamentoPrefijoResidencia
                                = solicitud.getCodigoMunicipioAcompaniante()
                                        .substring(0, 2);
                        String municipioPrefijoResidencia
                                = solicitud.getCodigoMunicipioAcompaniante()
                                        .substring(2, solicitud.getCodigoMunicipioAcompaniante().length());
                        ubicacionResidencia
                                = getUbicacionRemoto().consultarMunicipiosPorPrefijo(
                                        departamentoPrefijoResidencia,
                                        municipioPrefijoResidencia);
                        if (ubicacionResidencia == null) {
                            error = error + "CodigoMunicipioAcompaniante no encontrado |";
                        }
                    } else {
                        error = error + "CodigoMunicipioAcompaniante Requerido |";
                    }

                    // Medico
                    Maestro maeTipoDocumentoMed = null;
                    if (solicitud.getTipoDocumentoMedico() != null) {
                        maeTipoDocumentoMed = getMaestroRemoto()
                                .consultarPorValorTipo(
                                        solicitud.getTipoDocumentoMedico(),
                                        MaestroTipo.GN_TIPO_DOCUMENTO_PROFESIONAL);
                        if (maeTipoDocumentoMed == null) {
                            error = error + "TipoDocumentoMedico no encontrado |";
                        }
                    } else {
                        error = error + "TipoDocumentoMedico requerido |";
                    }

                    if (solicitud.getDocumentoMedico() == null
                            || solicitud.getDocumentoMedico().isEmpty()) {
                        error = error + "DocumentoMedico Requerido |";
                    } else {
                        if (!solicitud.getDocumentoMedico().matches("[0-9]*")) {
                            error = error + (" Numero DocumentoMedico Invalido |");
                        }
                    }

                    MaEspecialidad especialidad = null;
                    if (solicitud.getCodigoEspecialidadMedica() == null) {
                        error = error + "CodigoEspecialidadMedica Requerido |";
                    } else {
                        if (solicitud.getCodigoEspecialidadMedica() != null) {
                            especialidad = getEspecialidadRemoto()
                                    .consultarPorCodigo(solicitud.getCodigoEspecialidadMedica());
                        }
                        if (especialidad == null) {
                            error = error + "CodigoEspecialidadMedica no se encuentra |";
                        }
                    }

                    if (solicitud.getRegistroMedico() == null
                            || solicitud.getRegistroMedico().isEmpty()) {
                        error = error + "RegistroMedico Requerido |";
                    }

                    CntProfesional profesional = null;
                    if (solicitud.getDocumentoMedico() != null
                            && maeTipoDocumentoMed != null
                            && especialidad != null) {
                        profesional = getCntProfesionalRemoto()
                                .consultar(
                                        maeTipoDocumentoMed.getValor(),
                                        solicitud.getDocumentoMedico());
                        if (profesional == null) {
                            profesional = new CntProfesional();
                            profesional.setMaeTipoCodumentoId(maeTipoDocumentoMed.getId());
                            profesional.setMaeTipoDocumentoCodigo(maeTipoDocumentoMed.getValor());
                            profesional.setMaeTipoDocumentoValor(maeTipoDocumentoMed.getNombre());
                            profesional.setDocumento(solicitud.getDocumentoMedico());

                            if (solicitud.getPrimerNombreMedico() == null) {
                                error = error + "PrimerNombreMedico Requerido |";
                            } else {
                                if (!(validarCampoTexto(solicitud.getPrimerNombreMedico()).equals(""))) {
                                    error = error + (validarCampoTexto(solicitud.getPrimerNombreMedico()) + "PrimerNombreMedico |");
                                }
                            }

                            if (solicitud.getPrimerApellidoMedico() == null) {
                                error = error + "PrimerApellidoMedico Requerido |";
                            } else {
                                if (!(validarCampoTexto(solicitud.getPrimerApellidoMedico()).equals(""))) {
                                    error = error + (validarCampoTexto(solicitud.getPrimerApellidoMedico()) + "PrimerApellidoMedico |");
                                }
                            }

                            if (solicitud.getSegundoNombreMedico() != null) {
                                if (!(validarCampoTexto(solicitud.getSegundoNombreMedico()).equals(""))) {
                                    error = error + (validarCampoTexto(solicitud.getSegundoNombreMedico()) + "SegundoNombreMedico |");
                                }
                            }

                            if (solicitud.getSegundoApellidoMedico() != null) {
                                if (!(validarCampoTexto(solicitud.getSegundoApellidoMedico()).equals(""))) {
                                    error = error + (validarCampoTexto(solicitud.getSegundoApellidoMedico()) + "SegundoApellidoMedico |");
                                }
                            }

                            if (solicitud.getPrimerApellidoMedico() != null) {
                                profesional.setPrimerApellido(solicitud.getPrimerApellidoMedico());
                                if (solicitud.getSegundoApellidoMedico() != null) {
                                    profesional.setSegundoApellido(solicitud.getSegundoApellidoMedico());
                                }
                            }

                            if (solicitud.getPrimerNombreMedico() != null) {
                                profesional.setPrimerNombre(solicitud.getPrimerNombreMedico());
                                if (solicitud.getSegundoNombreMedico() != null) {
                                    profesional.setSegundoNombre(solicitud.getSegundoNombreMedico());
                                }
                            }

                            profesional.setRegistroMedico(solicitud.getRegistroMedico());

                            if (error.equals("")) {
                                profesional.setUsuarioCrea(usuario);
                                profesional.setTerminalCrea("intersavia");
                                profesional.setFechaHoraCrea(new Date());
                                profesional.setId(getCntProfesionalRemoto().insertar(profesional));
                            } else {
                                profesional = null;
                            }
                        }
                    }

                    if (solicitud.getCodigoHabilitacionIPSSolicitud() == null
                            || solicitud.getCodigoHabilitacionIPSSolicitud().isEmpty()) {
                        error = error + "CodigoHabilitacionIPSSolicitud Requerido |";
                    }
                    CntPrestadorSede prestador = getCntPrestadorSedeRemoto()
                            .consultarPorCodigoHabilitacion(solicitud.getCodigoHabilitacionIPSSolicitud());
                    if (prestador != null) {
                        //consultar relacion profesional-prestador
                        List<CntProfesionalPrestador> contratos = new ArrayList();
                        if (profesional != null) {
                            contratos = getCntPrestadorProfesionalRemoto()
                                    .consultarPorProfesional(profesional.getId());

                            CntProfesionalPrestador contrato = new CntProfesionalPrestador();
                            boolean agregarContrato = true;

                            if (!contratos.isEmpty()) {
                                int idPrestador = prestador.getCntPrestador().getId();
                                for (CntProfesionalPrestador cnt : contratos) {
                                    if (cnt.getCntPrestador().getId() == idPrestador) {
                                        if (cnt.getMaEspecialidadCodigo().equals(especialidad.getCodigo())) {
                                            agregarContrato = false;
                                        }
                                    }
                                }
                            }
                            if (agregarContrato) {
                                contrato.setCntProfesionalesId(profesional);
                                contrato.setCntPrestador(prestador.getCntPrestador());
                                contrato.setActivo(true);
                                contrato.setMaEspecialidadId(especialidad.getId());
                                contrato.setMaEspecialidadCodigo(especialidad.getCodigo());
                                contrato.setMaEspecialidadValor(especialidad.getNombre());
                                contrato.setUsuarioCrea(usuario);
                                contrato.setTerminalCrea("intersavia");
                                contrato.setFechaHoraCrea(new Date());
                                try {
                                    getCntPrestadorProfesionalRemoto().insertar(contrato);
                                } catch (Exception e) {
                                    error = error + ("Hubo un fallo guardando el profesional-prestador");
                                }
                            }
                        }

                        if (solicitud.getCodigoServicioRefiere() == null) {
                            error = error + "CodigoServicioRefiere Requerido |";
                        } else {
                            servicioRefiere = consultarMaServiciosHabilitacionPorCodigo(solicitud.getCodigoServicioRefiere());
                            if (servicioRefiere == null) {
                                error = error + "CodigoServicioRefiere no se reconoce |";
                            }
                        }
                        if (solicitud.getCodigoServicioSolicita() == null) {
                            error = error + "CodigoServicioSolicita Requerido |";
                        } else {
                            servicioSolicita = consultarMaServiciosHabilitacionPorCodigo(solicitud.getCodigoServicioSolicita());
                            if (servicioSolicita == null) {
                                error = error + "CodigoServicioSolicita no se reconoce |";
                            }
                        }
                        if (solicitud.getResumenHistoriaC() == null) {
                            error = error + "ResumenHistoriaC Requerido |";
                        }
                        if (solicitud.getSignoVitalTemperatura() == null) {
                            error = error + "SignoVitalTemperatura Requerido |";
                        }
                        if (solicitud.getSignoVitalFrecuenciaC() == null) {
                            error = error + "SignoVitalFrecuenciaC Requerido |";
                        }
                        if (solicitud.getSignoVitalTensionAS() == null) {
                            error = error + "SignoVitalTensionAS Requerido |";
                        }
                        if (solicitud.getSignoVitalTensionAD() == null) {
                            error = error + "SignoVitalTensionAD Requerido |";
                        }
                        if (solicitud.getSignoVitalSaturacionOx() == null) {
                            error = error + "SignoVitalSaturacionOx Requerido |";
                        }
                        if (solicitud.getSignoVitalFrecuenciaR() == null) {
                            error = error + "SignoVitalFrecuenciaR Requerido |";
                        }
                        if (solicitud.getPeso() == null) {
                            error = error + "Peso Requerido |";
                        } else {
                            if (solicitud.getPeso() == 0) {
                                error = error + "Peso Requerido |";
                            }
                        }
                        if (solicitud.getTalla() == null) {
                            error = error + "Talla Requerido |";
                        } else {
                            if (solicitud.getTalla() == 0) {
                                error = error + "Talla Requerido |";
                            }
                        }

                        if (solicitud.getHallazgoExamenFisico() == null) {
                            error = error + "HallazgoExamenFisico Requerido |";
                        }

                        if (solicitud.getMotivoRemision() == null) {
                            error = error + "MotivoRemision Requerido |";
                        }

                        if (solicitud.getAdjuntos() != null) {
                            for (AdjuntoDTO adj : solicitud.getAdjuntos()) {
                                if (adj.getNombre() == null) {
                                    error = error + "Adjunto Nombre Requerido, consecutivo " + solicitud.getConsecutivo() + " |";
                                }
                                if (adj.getAnexo() == null) {
                                    error = error + "Adjunto Anexo Requerido, consecutivo " + solicitud.getConsecutivo() + " |";
                                }
                                if (adj.getTipo() == null) {
                                    error = error + "Adjunto Tipo Requerido, consecutivo " + solicitud.getConsecutivo() + " |";
                                }
                            }
                        }

                        if (solicitud.getDiagnosticoPrincipal() == null) {
                            error = error + "DiagnosticoPrincipal Requerido |";
                        } else {
                            MaDiagnosticos diagnosticoP = consultarDiagnosticoPorCodigo(solicitud.getDiagnosticoPrincipal());
                            if (diagnosticoP == null) {
                                error = error + "DiagnosticoPrincipal no encontrado |";
                            }
                        }

                        if (solicitud.getDiagnosticoRelacionado1() != null && !solicitud.getDiagnosticoRelacionado1().isEmpty()) {
                            MaDiagnosticos diagnostico1 = consultarDiagnosticoPorCodigo(solicitud.getDiagnosticoRelacionado1());
                            if (diagnostico1 == null) {
                                error = error + "DiagnosticoRelacionado1 no encontrado |";
                            }
                        }

                        if (solicitud.getDiagnosticoRelacionado2() != null && !solicitud.getDiagnosticoRelacionado2().isEmpty()) {
                            MaDiagnosticos diagnostico2 = consultarDiagnosticoPorCodigo(solicitud.getDiagnosticoRelacionado2());
                            if (diagnostico2 == null) {
                                error = error + "DiagnosticoRelacionado2 no encontrado |";
                            }
                        }

                        if (solicitud.getAntecedentesHistoriaC() == null) {
                            error = error + "AntecedentesHistoriaC Requerido |";
                        }
                        if (solicitud.getMotivoRemision() == null) {
                            error = error + "MotivoRemision Requerido |";
                        }
                        if (solicitud.getResumenHistoriaC() == null) {
                            error = error + "ResumenHistoriaC Requerido |";
                        }
                        if (solicitud.getPrioridadTriage() == null) {
                            error = error + "PrioridadTriage Requerido |";
                        } else {
                            if (solicitud.getPrioridadTriage() < 1
                                    || solicitud.getPrioridadTriage() > 5) {
                                error = error + "PrioridadTriage fuera del rango |";
                            }
                        }

                        if (solicitud.getEscalaGlasgow() != null) {
                            if (solicitud.getEscalaGlasgow() < 3
                                    || solicitud.getEscalaGlasgow() > 15) {
                                error = error + "EscalaGlasgow  fuera del rango |";
                            }
                        }

                        if (solicitud.getNombreInformante() == null) {
                            error = error + "NombreInformante Requerido |";
                        } else {
                            if (!(validarCampoTexto(solicitud.getNombreInformante()).equals(""))) {
                                error = error + (validarCampoTexto(solicitud.getNombreInformante()) + "NombreInformante |");
                            }
                        }
                        if (solicitud.getCargoInformante() == null
                                || solicitud.getCargoInformante().isEmpty()) {
                            error = error + "CargoInformante Requerido |";
                        }

                        if (solicitud.getTelefonoInformante() == null) {
                            error = error + "TelefonoInformante Requerido |";
                        } else {
                            if (!solicitud.getTelefonoInformante().matches("[0-9]*")) {
                                error = error + (" Numero TelefonoInformante Invalido |");
                            }
                            if (solicitud.getTelefonoInformante().length() < 10
                                    || solicitud.getTelefonoInformante().length() > 11) {
                                error = error + (" Numero TelefonoInformante debe tener diez digitos |");
                            }
                        }

                        if (solicitud.getTelefonoSolicita() == null) {
                            error = error + "TelefonoInformante Requerido |";
                        } else {
                            if (!solicitud.getTelefonoSolicita().matches("[0-9]*")
                                    || solicitud.getTelefonoSolicita().length() < 10
                                    || solicitud.getTelefonoSolicita().length() > 11) {
                                error = error + (" Numero TelefonoSolicita Invalido |");
                            }
                        }

                        Maestro maeCausaExt = null;
                        if (solicitud.getCausaExterna() != null) {
                            maeCausaExt = getMaestroRemoto()
                                    .consultarPorValorTipo(
                                            solicitud.getCausaExterna(),
                                            MaestroTipo.GN_ORIGEN_ATENCION);
                            if (maeCausaExt == null) {
                                error = error + "CausaExterna no encontrada |";
                            }
                        }

                        Maestro maeCondicionDest = null;
                        if (solicitud.getCondicionDestino() != null) {
                            maeCondicionDest = getMaestroRemoto()
                                    .consultarPorValorTipo(
                                            solicitud.getCondicionDestino(),
                                            MaestroTipo.CR_A2_DESTINO_PACIENTE);
                            if (maeCondicionDest == null) {
                                error = error + "CondicionDestino no encontrada |";
                            }
                        }

                        if (solicitud.getPrioridad() < 1 || solicitud.getPrioridad() > 2) {
                            error += "Prioridad no encontrada |";
                        }

                        Maestro maeTipoAtencion = null;
                        if (solicitud.getTipoAtencion() != null) {
                            maeTipoAtencion = getMaestroRemoto()
                                    .consultarPorValorTipo(
                                            solicitud.getTipoAtencion(),
                                            MaestroTipo.GN_TIPO_SERVICIO);
                            if (maeTipoAtencion == null) {
                                error = error + "TipoAtencion no encontrada |";
                            }
                        }

                        Maestro maeUbicacion = null;
                        if (solicitud.getUbicacion() != null) {
                            maeUbicacion = getMaestroRemoto()
                                    .consultarPorValorTipo(
                                            solicitud.getUbicacion(),
                                            MaestroTipo.GN_UBICACION);
                            if (maeUbicacion == null) {
                                error = error + "Ubicacion no encontrada |";
                            }
                        }

                        Maestro maeModalidadTecnologia = null;
                        if (solicitud.getModalidadTecnologia() != null) {
                            maeModalidadTecnologia = getMaestroRemoto()
                                    .consultarPorValorTipo(
                                            solicitud.getModalidadTecnologia(),
                                            MaestroTipo.GN_A1_MODALIDAD_TECNOLOGIA);
                            if (maeModalidadTecnologia == null) {
                                error = error + "ModalidadTecnologia no encontrada |";
                            }
                        }

                        if (solicitud.getTipoTecnologia() < 1
                                || solicitud.getTipoTecnologia() > 5) {
                            error = error + "TipoTecnologia no encontrada |";
                        }

                        MaTecnologia tec = null;
                        if (solicitud.getTecnologia() != null) {
                            tec = getMaTecnologiaRemoto()
                                    .consultarPorCodigo(solicitud.getTecnologia());
                            if (tec == null) {
                                error = error + " Tecnologia no encontrada |";
                            }
                        }

                        if (error.equals("")) {
                            RefAnexos9 anexo9 = castRefAnexos9(
                                    afiliado,
                                    prestador,
                                    solicitud,
                                    profesional,
                                    usuario,
                                    ubicacionResidencia,
                                    servicioSolicita,
                                    servicioRefiere,
                                    maeCausaExt,
                                    maeCondicionDest,
                                    maeTipoAtencion,
                                    maeUbicacion,
                                    maeModalidadTecnologia,
                                    tec
                            );
                            if (solicitud.getAdjuntos() != null
                                    && !solicitud.getAdjuntos().isEmpty()) {
                                if (!validarAdjuntosAnexo9(
                                        anexo9,
                                        solicitud.getAdjuntos(),
                                        usuario,
                                        rutaAdjunto,
                                        rutaAnexo)) {
                                    detalle.setDescripcionRespuesta("No existe tipo de archivo reportado ");
                                    detalle.setConsecutivo(solicitud.getConsecutivo());
                                    detalle.setCodigoRespuesta("1");
                                }
                            }
                            if (detalle.getCodigoRespuesta().equals("0")) {
                                //insertar anexo 9
                                anexo9.setId(insertarRefAnexos9(anexo9));
                                //insertar diagnosticos
                                if (solicitud.getDiagnosticoPrincipal() != null && !solicitud.getDiagnosticoPrincipal().isEmpty()) {
                                    MaDiagnosticos diagnosticoP = consultarDiagnosticoPorCodigo(solicitud.getDiagnosticoPrincipal());
                                    RefAnexo9Diagnosticos diagnostico = castRefAnexo9Diagnosticos(anexo9, diagnosticoP, true, usuario);
                                    diagnostico.setId(insertarRefAnexo9Diagnosticos(diagnostico));
                                }
                                if (solicitud.getDiagnosticoRelacionado1() != null && !solicitud.getDiagnosticoRelacionado1().isEmpty()) {
                                    MaDiagnosticos diagnostico2 = consultarDiagnosticoPorCodigo(solicitud.getDiagnosticoRelacionado1());
                                    RefAnexo9Diagnosticos diagnostico = castRefAnexo9Diagnosticos(anexo9, diagnostico2, false, usuario);
                                    diagnostico.setId(insertarRefAnexo9Diagnosticos(diagnostico));
                                }
                                if (solicitud.getDiagnosticoRelacionado2() != null && !solicitud.getDiagnosticoRelacionado2().isEmpty()) {
                                    MaDiagnosticos diagnostico3 = consultarDiagnosticoPorCodigo(solicitud.getDiagnosticoRelacionado1());
                                    RefAnexo9Diagnosticos diagnostico = castRefAnexo9Diagnosticos(anexo9, diagnostico3, false, usuario);
                                    diagnostico.setId(insertarRefAnexo9Diagnosticos(diagnostico));
                                }
                                //insertar datos clinicos
                                RefAnexo9DatosClinicos datosClinicos = castRefAnexo9DatosClinicos(anexo9, solicitud, usuario);
                                datosClinicos.setId(insertarRefAnexo9DatosClinicos(datosClinicos));
                                //insertar estado
                                RefAnexo9Estados estado = new RefAnexo9Estados();
                                estado.setRefAnexos9Id(anexo9);
                                estado.setEstado(1);
                                estado.setUsuarioCrea(usuario);
                                estado.setTerminalCrea(TERMINAL_IVR);
                                estado.setFechaHoraCrea(new Date());
                                estado.setId(insertarRefAnexo9Estados(estado));
                                //respuesta servicio
                                detalle.setConsecutivo(solicitud.getConsecutivo());
                                detalle.setReferencia(anexo9.getId().toString());
                                detalle.setTipoAnexo(String.valueOf(anexo9.getTipo()));
                                detalle.setCodigoHabilitacionIPSSolicita(solicitud.getCodigoHabilitacionIPSSolicitud());
                                detalle.setNombreIPS(prestador.getCntPrestador().getRazonSocial());
                                detalle.setNombreSedeIPS(prestador.getNombreSede());
                                detalle.setTipoDocumento(solicitud.getTipoDocumento());
                                detalle.setDocumento(solicitud.getDocumento());
                                int adjunto = 0;
                                detalle.setEstadoRemision("Pre-admitida");
                                detalle.setCodigoEstadoRemision("1");
                                detalle.setDescripcionRespuesta("Referencia generada de manera exitosa");
                                if (solicitud.getAdjuntos() != null && !solicitud.getAdjuntos().isEmpty()) {
                                    guardarAdjuntosAnexo9(anexo9, solicitud.getAdjuntos(), usuario, rutaAdjunto, rutaAnexo);
                                }
                            }
                        } else {
                            detalle.setDescripcionRespuesta(error);
                            detalle.setCodigoRespuesta("1");
                        }

                    } else {
                        detalle.setDescripcionRespuesta("codigoHabilitacionIPSSolicitud no encontrada");
                        detalle.setCodigoRespuesta("1");
                    }

                    if (detalle.getCodigoRespuesta().equals("0")
                            && !error.equals("")) {
                        detalle.setDescripcionRespuesta(error);
                        detalle.setCodigoRespuesta("1");
                    }
                } else {
                    detalle.setDescripcionRespuesta("Afiliado no esta activo");
                    detalle.setCodigoRespuesta("1");
                }
            } else {
                detalle.setDescripcionRespuesta("Afiliado no Encontrado");
                detalle.setCodigoRespuesta("1");
            }

            detalle.setConsecutivo(solicitud.getConsecutivo());
            respuesta.getReferencias().add(detalle);

        }
        if (!respuesta.getReferencias().isEmpty()) {
            for (DetalleRespuestaSolicitudAnexo9DTO referencia : respuesta.getReferencias()) {
                if (referencia.getCodigoRespuesta().equals("0")) {
                    respuesta.setMensaje("Transacción Exitosa");
                    respuesta.setFechaTransaccion(dateFormat.format(new Date()));
                } else {
                    respuesta.setCodigoRespuesta("1");
                    respuesta.setMensaje("Error en Referencias");
                    respuesta.setFechaTransaccion(dateFormat.format(new Date()));
                    break;
                }
            }
        }
        return respuesta;
    }

    private String validarCampoTexto(String texto) {
        String mensaje = "";
        Pattern patron = Pattern.compile("[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+");
        Matcher emparejador = patron.matcher(texto);
        if (!emparejador.matches()) {
            mensaje = " Solo se permite letras. Campo: ";
        }
        return mensaje;
    }

    //2022-11-25|lguerreroh|se comenta metodo por retiro de dto de ws de savia-negocio
//    @Override
//    public DetalleRespuestaSolicitudAnexo9DTO consultarEstadoAnexo9porId(Integer id) throws Exception {
//        DetalleRespuestaSolicitudAnexo9DTO dto = new DetalleRespuestaSolicitudAnexo9DTO();
//        try {
//            RefAnexo9 anexo = getRefAnexo9Remoto().consultar(id);
//            if (anexo != null) {
//                dto.setReferencia("" + anexo.getId());
//                dto.setTipoAnexo(anexo.getTipoStr());
//                dto.setCodigoHabilitacionIPSSolicita(anexo.getCntPrestadorSede().getCodigoHabilitacionSede());
//                dto.setNombreIPS(anexo.getCntPrestadorSede().getCntPrestador().getRazonSocial());
//                dto.setNombreSedeIPS(anexo.getCntPrestadorSede().getNombreSede());
//                dto.setTipoDocumento(anexo.getAsegAfiliado().getMaeTipoDocumentoCodigo());
//                dto.setDocumento(anexo.getAsegAfiliado().getNumeroDocumento());
//                dto.setEstadoRemision(anexo.getMaeEstadoValor());
//                dto.setCodigoEstadoRemision("" + anexo.getMaeEstadoId());
//                dto.setCodigoRespuesta("0");
//                dto.setDescripcionRespuesta("Exitoso");
//            } else {
//                dto.setReferencia("" + id);
//                dto.setDescripcionRespuesta("No existe la referencia");
//            }
//        } catch (NoResultException e) {
//            dto = null;
//        } catch (Exception e) {
//            Exception(CONSULTAR_TODOS, e);
//        } finally {
//            cerrarEntityManager();
//        }
//        return dto;
//    }
    private AsegAfiliados consultarAfiliado(String tipodocumento, String numeroDocumento) throws Exception {
        AsegAfiliados afiliado = null;
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + "WHERE p.maeTipoDocumentoId = '" + tipodocumento + "' "
                    + " AND p.numeroDocumento = '" + numeroDocumento + "' ";

            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null && !list.isEmpty()) {
                afiliado = list.get(0);
            }

        } catch (NoResultException e) {
            afiliado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return afiliado;
    }

    private CntPrestadorSedes consultarPrestadorPorCodigoHabilitacion(String codigoHabilitacion) throws Exception {
        CntPrestadorSedes ips = null;
        try {
            String strQuery = "FROM CntPrestadorSedes ps "
                    + "WHERE  ps.codigoHabilitacion = '" + codigoHabilitacion + "' "
                    + "AND ps.estadoSede = 1 "
                    + "AND ps.cntPrestadoresId.activo = 1";
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && !list.isEmpty()) {
                ips = list.get(0);
            }
        } catch (NoResultException e) {
            ips = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ips;
    }

    private AuAnexos2 castAuAnexos2(AsegAfiliados afiliado, CntPrestadorSedes ips, Integer tipoUrgencia) throws Exception {
        AuAnexos2 anexo2 = new AuAnexos2();
        GnEmpresas empresa = consultarEmpresaPorCntPrestador(ips.getCntPrestadoresId().getId());
        if (empresa != null) {
            anexo2.setGnEmpresasId(empresa);
        }
        anexo2.setAsegAfiliadosId(afiliado);
        anexo2.setCntPrestadorSedesId(ips);
        CntProfesionales profesional = new CntProfesionales();
        profesional.setId(15568);
        anexo2.setCntProfesionalesId(profesional);
        anexo2.setTipo(tipoUrgencia);
        anexo2.setEstado(AuAnexo2.ESTADO_AUTORIZADA_AUTOMATICA);
        anexo2.setMaeOrigenAtencionId(9745);
        anexo2.setMaeOrigenAtencionCodigo("1");
        anexo2.setMaeOrigenAtencionValor("ENFERMEDAD GENERAL");
        anexo2.setMaeDestinoPacienteId(9785);
        anexo2.setMaeDestinoPacienteCodigo("2");
        anexo2.setMaeDestinoPacienteValor("Observación");
        anexo2.setCodigoAtencionIps(ips.getCodigoHabilitacion());
        anexo2.setFechaHoraAtencion(new Date());
        anexo2.setFechaHoraReporte(new Date());
        anexo2.setMotivo(REPORTE_IVR);
        anexo2.setRemitido(false);
        anexo2.setRemiteNit(ips.getCntPrestadoresId().getNumeroDocumento());
        anexo2.setRemiteNombre(ips.getNombre());
        if (ips.getFechaFacturaElectronica() != null || ips.getGrupoRipsMinisterio() != null
                && ips.getGrupoRipsMinisterio().equals(AuConstantes.UNO)
                || ips.getGrupoRipsMinisterio().equals(AuConstantes.DOS)
                || ips.getGrupoRipsMinisterio().equals(AuConstantes.TRES)) {
            anexo2.setVersion(true);
            Maestro maeViaIngreso = getMaestroRemoto()
                    .consultarPorValorTipo(
                            "01",
                            MaestroTipo.CR_A2_VIA_INGRESO);
            if (maeViaIngreso != null) {
                anexo2.setMaeViaIngresoId(maeViaIngreso.getId());
                anexo2.setMaeViaIngresoValor(maeViaIngreso.getNombre());
                anexo2.setMaeViaIngresoCodigo(maeViaIngreso.getValor());
            }
            Maestro maeDestinoPaciente = getMaestroRemoto()
                    .consultarPorValorTipo(
                            "03",
                            MaestroTipo.CR_A2_DESTINO_PACIENTE);
            if (maeDestinoPaciente != null) {
                anexo2.setMaeCondicionDestinoId(maeDestinoPaciente.getId());
                anexo2.setMaeCondicionDestinoValor(maeDestinoPaciente.getNombre());
                anexo2.setMaeCondicionDestinoCodigo(maeDestinoPaciente.getValor());
            }
            anexo2.setAfiliadoDireccionAlternativa(afiliado.getDireccionResidencia());
        }
        anexo2.setTriage(0);
        anexo2.setInformaNombre(USUARIO_IVR);
        anexo2.setInformaCargo(USUARIO_IVR);
        anexo2.setInformaTelefono("0");
        anexo2.setUsuarioCrea(USUARIO_IVR);
        anexo2.setTerminalCrea(TERMINAL_IVR);
        anexo2.setFechaHoraCrea(new Date());
        return anexo2;
    }

    private AuAnexo2 castAuAnexo2(AsegAfiliados afiliado, CntPrestadorSedes ips, Integer tipoUrgencia) throws Exception {
        AuAnexo2 anexo2 = new AuAnexo2();

        Empresa emp = getEmpresaRemoto().consultarPorPrestador(ips.getCntPrestadoresId().getId());

        if (emp != null) {
            anexo2.setGnEmpresa(emp);
        }
        anexo2.setAsegAfiliado(getAfiliadoRemoto().consultar(afiliado.getId()));
        anexo2.setCntPrestadorSede(getCntPrestadorSedeRemoto().consultar(ips.getId()));
        CntProfesionales profesional = new CntProfesionales();
        profesional.setId(15568);
        anexo2.setCntProfesionales(getCntProfesionalRemoto().consultar(profesional.getId()));
        anexo2.setTipo(tipoUrgencia);
        anexo2.setEstado(AuAnexo2.ESTADO_AUTORIZADA_AUTOMATICA);
        anexo2.setMaeOrigenAtencionId(9745);
        anexo2.setMaeOrigenAtencionCodigo("1");
        anexo2.setMaeOrigenAtencionValor("ENFERMEDAD GENERAL");
        anexo2.setMaeDestinoPacienteId(9785);
        anexo2.setMaeDestinoPacienteCodigo("2");
        anexo2.setMaeDestinoPacienteValor("Observación");
        anexo2.setCodigoAtencionIps(ips.getCodigoHabilitacion());
        anexo2.setFechaHoraAtencion(new Date());
        anexo2.setFechaHoraReporte(new Date());
        anexo2.setMotivo(REPORTE_IVR);
        anexo2.setRemitido(false);
        anexo2.setRemiteNit(ips.getCntPrestadoresId().getNumeroDocumento());
        anexo2.setRemiteNombre(ips.getNombre());
        anexo2.setTriage(0);
        if (ips.getFechaFacturaElectronica() != null || ips.getGrupoRipsMinisterio() != null
                && ips.getGrupoRipsMinisterio().equals(AuConstantes.UNO)
                || ips.getGrupoRipsMinisterio().equals(AuConstantes.DOS)
                || ips.getGrupoRipsMinisterio().equals(AuConstantes.TRES)) {
            anexo2.setVersion(AuConstantes.UNO);
        } else {
            anexo2.setVersion(AuConstantes.CERO);
        }
        anexo2.setInformaNombre(USUARIO_IVR);
        anexo2.setInformaCargo(USUARIO_IVR);
        anexo2.setInformaTelefono("0");
        anexo2.setUsuarioCrea(USUARIO_IVR);
        anexo2.setTerminalCrea("127.0.01");
        anexo2.setFechaHoraCrea(new Date());
        return anexo2;
    }

    private RefAnexos9 castRefAnexos9(
            AsegAfiliado afiliado,
            CntPrestadorSede ips,
            ReferenciaAnexo9DTO solicitud,
            CntProfesional profesional,
            String usuario,
            Ubicacion ubicacionResidencia,
            MaServiciosHabilitacion servicioSolicita,
            MaServiciosHabilitacion servicioRefiere,
            Maestro maeCausaExt,
            Maestro maeCondicionDest,
            Maestro maeTipoAtencion,
            Maestro maeUbicacion,
            Maestro maeModalidadTecnologia,
            MaTecnologia tec
    ) throws java.lang.Exception {

        RefAnexos9 anexo9 = new RefAnexos9();
        AsegAfiliados afiliados = new AsegAfiliados();
        if (ips.getFechaFacturaElectronica() != null || ips.getGrupoRipsMinisterio() != null
                && ips.getGrupoRipsMinisterio().equals(AuConstantes.UNO)
                || ips.getGrupoRipsMinisterio().equals(AuConstantes.DOS)
                || ips.getGrupoRipsMinisterio().equals(AuConstantes.TRES)) {
            anexo9.setVersion(true);
            if (maeCausaExt != null) {
                anexo9.setMaeCausaExternaId(maeCausaExt.getId());
                anexo9.setMaeCausaExternaValor(maeCausaExt.getNombre());
                anexo9.setMaeCausaExternaCodigo(maeCausaExt.getValor());
            }
            if (maeCondicionDest != null) {
                anexo9.setMaeCondicionDestinoId(maeCondicionDest.getId());
                anexo9.setMaeCondicionDestinoValor(maeCondicionDest.getNombre());
                anexo9.setMaeCondicionDestinoCodigo(maeCondicionDest.getValor());
            }
            if (solicitud.getPrioridad() == 1) {
                anexo9.setPrioridad(true);
            } else {
                anexo9.setPrioridad(false);
            }
            if (maeTipoAtencion != null) {
                anexo9.setMaeTipoAtencionId(maeTipoAtencion.getId());
                anexo9.setMaeTipoAtencionValor(maeTipoAtencion.getNombre());
                anexo9.setMaeTipoAtencionCodigo(maeTipoAtencion.getValor());
            }
            if (maeUbicacion != null) {
                anexo9.setMaeUbicacionId(maeUbicacion.getId());
                anexo9.setMaeUbicacionValor(maeUbicacion.getNombre());
                anexo9.setMaeUbicacionCodigo(maeUbicacion.getValor());
            }
            if (maeModalidadTecnologia != null) {
                anexo9.setMaeModalidadTecnologiaId(maeModalidadTecnologia.getId());
                anexo9.setMaeModalidadTecnologiaValor(maeModalidadTecnologia.getNombre());
                anexo9.setMaeModalidadTecnologiaCodigo(maeModalidadTecnologia.getValor());
            }
            if (solicitud.getTipoTecnologia() > 0) {
                anexo9.setTipoTecnologia(solicitud.getTipoTecnologia());
            }
            if (tec != null) {
                anexo9.setMaTecnologiaId(tec.getId());
                anexo9.setMaTecnologiaValor(tec.getCupsDescipcion());
                anexo9.setMaTecnologiaCodigo(tec.getCodigoPropio());
            }
            if (solicitud.getCantidadTecnologia() > 0) {
                anexo9.setCantidadTecnologiaSolicitada(solicitud.getCantidadTecnologia());
            }
            if (solicitud.getAfiliadoDireccionAlternativa() != null) {
                anexo9.setAfiliadoDireccionAlternativa(solicitud.getAfiliadoDireccionAlternativa());
            }
            if (solicitud.getNombreContactoEmergencia() != null) {
                anexo9.setNombreContactoEmergencia(solicitud.getNombreContactoEmergencia());
            }
            if (solicitud.getTelefonoContactoEmergencia() != null) {
                anexo9.setTelefonoContactoEmergencia(solicitud.getTelefonoContactoEmergencia());
            }
        }

        afiliados.setId(afiliado.getId());
        anexo9.setAsegAfiliadosId(afiliados);
        CntProfesionales profesionales = new CntProfesionales();

        profesionales.setId(profesional.getId());
        anexo9.setCntProfesionalesId(profesionales);
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();

        gsonBuilderRespuesta.setDateFormat(
                "yyyy-MM-dd"); //Formato fecha 
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        GnEmpresas empresa = consultarEmpresaPorCntPrestador(ips.getCntPrestador().getId());
        if (empresa
                != null) {
            anexo9.setGnEmpresasId(empresa);
        }
        CntPrestadorSedes sede = new CntPrestadorSedes();

        sede.setId(ips.getId());
        anexo9.setCntPrestadorSedesId(sede);

        anexo9.setTipo(Integer.parseInt(solicitud.getTipoAnexo()));
        anexo9.setFechaHoraSolicitud(
                new Date());
        anexo9.setFechaHoraRegiostro(
                new Date());
        anexo9.setAplicaNoIpsContrato(
                false);
        anexo9.setAplicaNoAfiliado(
                false);
        if (servicioSolicita
                != null) {
            anexo9.setMaServicioSolicitaId(servicioSolicita.getId());
            anexo9.setMaServicioSolicitaCodigo(String.valueOf(servicioSolicita.getCodigo()));
            anexo9.setMaServicioSolicitaValor(servicioSolicita.getNombre());
        }
        if (servicioRefiere
                != null) {
            anexo9.setMaServicioRemiteId(servicioRefiere.getId());
            anexo9.setMaServicioRemiteCodigo(String.valueOf(servicioRefiere.getCodigo()));
            anexo9.setMaServicioRemiteValor(servicioRefiere.getNombre());
        }
        //2024-05-21|lguerrero| eliminar campos cama y piso A9 HU 961
//        anexo9.setCama(solicitud.getCama());
//        anexo9.setUbicacion(solicitud.getPiso());

        anexo9.setAplicaLdf(
                false);
        anexo9.setAplicaMaterna(
                false);
        anexo9.setAplicaNeonato(
                false);
        GnMaestros canalComunicacion = consultarMaestroPorValorTipo("1", MaestroTipo.CR_A9_CANAL_COMUNICACION);
        if (canalComunicacion
                != null) {
            anexo9.setMaeCanalComunicacionId(canalComunicacion.getId());
            anexo9.setMaeCanalComunicacionCodigo(canalComunicacion.getValor());
            anexo9.setMaeCanalComunicacionValor(canalComunicacion.getDescripcion());
        }
        GnMaestros estado = consultarMaestroPorValorTipo("1", MaestroTipo.CR_A9_ESTADO);
        if (estado
                != null) {
            anexo9.setMaeEstadoId(estado.getId());
            anexo9.setEstado(Integer.parseInt(estado.getValor()));
            anexo9.setMaeEstadoCodigo(estado.getValor());
            anexo9.setMaeEstadoValor(estado.getDescripcion());
        }
        //20/01/2023|lguerreroh| Datos no requeridos

        if (solicitud.getTipodocumentoAcompaniante()
                != null) {
            GnMaestros tipoDocumento = consultarMaestroPorValorTipo(solicitud.getTipodocumentoAcompaniante(), MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
            if (tipoDocumento != null) {
                anexo9.setMaeAcompananteTipoDocumentoId(tipoDocumento.getId());
                anexo9.setMaeAcompananteTipoDocumentoCodigo(tipoDocumento.getValor());
                anexo9.setMaeAcompananteTipoDocumentoValor(tipoDocumento.getDescripcion());
            }
            if (solicitud.getDocumentoAcompaniante() != null) {
                anexo9.setAcompananteDocumento(solicitud.getDocumentoAcompaniante());
            }
        }

        if (solicitud.getPrimerNombreAcompaniante()
                != null) {
            anexo9.setAcompanantePrimerNombre(solicitud.getPrimerNombreAcompaniante());
            if (solicitud.getSegundoNombreAcompaniante() != null) {
                anexo9.setAcompananteSegundoNombre(solicitud.getSegundoNombreAcompaniante());
            }
            if (solicitud.getPrimerApellidoAcompaniante() != null) {
                anexo9.setAcompanantePrimerApellido(solicitud.getPrimerApellidoAcompaniante());
                if (solicitud.getSegundoApellidoAcompaniante() != null) {
                    anexo9.setAcompananteSegundoApellido(solicitud.getSegundoApellidoAcompaniante());
                }
            }
            if (solicitud.getTelefonoAcompaniante() != null) {
                anexo9.setAcompananteTelefono(solicitud.getTelefonoAcompaniante());
            }
            if (solicitud.getDireccionAcompaniante() != null) {
                anexo9.setAcompananteDireccion(solicitud.getDireccionAcompaniante());
            }
        }

        if (solicitud.getCodigoMunicipioAcompaniante()
                != null) {
            if (ubicacionResidencia != null) {
                anexo9.setAcompananteMunicipio(ubicacionResidencia.getNombre());
                anexo9.setAcompananteDepartamento(ubicacionResidencia.getUbicacionPadre().getNombre());
            }
        }
        MaEspecialidad especialidad = getMaEspecialidadRemoto().consultarPorCodigo(solicitud.getCodigoEspecialidadMedica());
        if (especialidad
                != null) {
            anexo9.setMaEspecialidadesId(especialidad.getId());
            anexo9.setMaEspecialidadCodigo(especialidad.getCodigo());
            anexo9.setMaEspecialidadValor(especialidad.getNombre());
        }

        //anexo9.setAcompananteMunicipio(ACTUALIZAR);
        anexo9.setInformanteNombre(solicitud.getNombreInformante());
        anexo9.setInformanteCargo(solicitud.getCargoInformante());
        anexo9.setInformanteTelefono(solicitud.getTelefonoInformante());
        //20/01/2023|lguerreroh| Datos faltantes
        anexo9.setFuenteOrigen(RefAnexo9.FUENTE_ORIGEN_INTEROPERABILIDAD);

        anexo9.setUsuarioCrea(usuario);

        anexo9.setTerminalCrea(TERMINAL_IVR);

        anexo9.setFechaHoraCrea(
                new Date());
        anexo9.setProfesionalSolicitaTelefono(solicitud.getTelefonoSolicita());
        return anexo9;

    }

    private static boolean esNumerico(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private AuAnexo2Items castAuAnexo2Items(AuAnexos2 anexo2, Integer tipoUrgencia) throws java.lang.Exception {
        AuAnexo2Items anexo2Item = new AuAnexo2Items();
        MaTecnologias tecnologia = null;
        if (tipoUrgencia == 0) {
            // urgencia 890701
            tecnologia = consultarTecnologiaPorCodigo("890701");

        } else {
            //urgencia odontologica 890703
            tecnologia = consultarTecnologiaPorCodigo("890703");

        }

        if (tecnologia != null) {
            anexo2Item.setMaTecnologiaCodigo(tecnologia.getCodigoPropio());
            anexo2Item.setMaTecnologiaId(tecnologia.getId());
            anexo2Item.setMaTecnologiaValor(tecnologia.getCupsDescipcion());
        }
        anexo2Item.setAuAnexos2Id(anexo2);

        anexo2Item.setCantidadSolicitada(1);
        anexo2Item.setMaServicioSolicitadoId(175);
        anexo2Item.setMaServicioSolicitadoCodigo("501");
        anexo2Item.setMaServicioSolicitadoValor("SERVICIO DE URGENCIAS - URGENCIAS BAJA COMPLEJIDAD");
        anexo2Item.setUsuarioCrea(USUARIO_IVR);
        anexo2Item.setTerminalCrea(TERMINAL_IVR);
        anexo2Item.setFechaHoraCrea(new Date());
        return anexo2Item;

    }

    private AuAnexo2Diagnostico castAuAnexo2Diagnosticos(AuAnexo2 anexo2, Integer tipoUrgencia) throws java.lang.Exception {
        AuAnexo2Diagnostico anexo2Diagnostico = new AuAnexo2Diagnostico();
        MaDiagnosticos diagnostico = null;
        if (tipoUrgencia == 0) {
            diagnostico = consultarDiagnosticoPorCodigo("R448");
        } else {
            diagnostico = consultarDiagnosticoPorCodigo("K089");
        }

        if (diagnostico != null) {
            anexo2Diagnostico.setAuAnexo2(anexo2);
            anexo2Diagnostico.setPrincipal(true);
            anexo2Diagnostico.setMaDiagnosticosId(diagnostico.getId());
            anexo2Diagnostico.setMaDiagnosticosCodigo(diagnostico.getCodigo());
            anexo2Diagnostico.setMaDiagnosticosValor(diagnostico.getNombre());
            anexo2Diagnostico.setMaeTipoDiagnosticoId(3145);
            anexo2Diagnostico.setMaeTipoDiagnosticoCodigo("1");
            anexo2Diagnostico.setMaeTipoDiagnosticoValor("Confirmado");
            anexo2Diagnostico.setUsuarioCrea(USUARIO_IVR);
            anexo2Diagnostico.setTerminalCrea(TERMINAL_IVR);
            anexo2Diagnostico.setFechaHoraCrea(new Date());
        }

        return anexo2Diagnostico;

    }

    private RefAnexo9Diagnosticos castRefAnexo9Diagnosticos(RefAnexos9 anexo9, MaDiagnosticos diagnostico, boolean principal, String usuarioCrea) throws java.lang.Exception {
        RefAnexo9Diagnosticos anexo9Diagnostico = new RefAnexo9Diagnosticos();
        //MaDiagnosticos diagnostico = consultarDiagnosticoPorCodigo(diagnosticoCIE10);
        if (diagnostico != null) {
            anexo9Diagnostico.setRefAnexos9Id(anexo9);
            anexo9Diagnostico.setMaDiagnosticosId(diagnostico.getId());
            anexo9Diagnostico.setMaDiagnosticosCodigo(diagnostico.getCodigo());
            anexo9Diagnostico.setMaDiagnosticosValor(diagnostico.getNombre());
            anexo9Diagnostico.setPrincipal(principal);
            anexo9Diagnostico.setUsuarioCrea(usuarioCrea);
            anexo9Diagnostico.setTerminalCrea(TERMINAL_IVR);
            anexo9Diagnostico.setFechaHoraCrea(new Date());
        }
        return anexo9Diagnostico;

    }

    private RefAnexo9DatosClinicos castRefAnexo9DatosClinicos(RefAnexos9 anexo9, ReferenciaAnexo9DTO solicitud, String usuario) throws java.lang.Exception {
        RefAnexo9DatosClinicos datosClinicos = new RefAnexo9DatosClinicos();
        datosClinicos.setRefAnexos9Id(anexo9);
        datosClinicos.setFechaHoraDatos(new Date());
        datosClinicos.setTriage((solicitud.getPrioridadTriage()));
        datosClinicos.setResumenClinico(solicitud.getResumenHistoriaC());
        datosClinicos.setTemperatura((solicitud.getSignoVitalTemperatura()));
        datosClinicos.setFrecuenciaCardiaca((solicitud.getSignoVitalFrecuenciaC()));
        datosClinicos.setFrecuenciaRespiratoria((solicitud.getSignoVitalFrecuenciaR()));
        datosClinicos.setTensionArtedialDiastole((solicitud.getSignoVitalTensionAD()));
        datosClinicos.setTensionArterialSistole((solicitud.getSignoVitalTensionAS()));
        datosClinicos.setSaturacionOxigeno((solicitud.getSignoVitalSaturacionOx()));
        datosClinicos.setHallazgosExamenFisico(solicitud.getHallazgoExamenFisico());
        datosClinicos.setMotivoRemision(solicitud.getMotivoRemision());
        datosClinicos.setEscalaGlasgow((solicitud.getEscalaGlasgow()));
        datosClinicos.setUsuarioCrea(usuario);
        //20/01/2023|lguerreroh| datos faltantes
        datosClinicos.setAntecedentes(solicitud.getAntecedentesHistoriaC());
        //
        if (solicitud.getPeso() != null
                && solicitud.getTalla() != null) {
            datosClinicos.setPeso(new BigDecimal(solicitud.getPeso()));
            datosClinicos.setTalla((solicitud.getTalla()));
            datosClinicos.setImc(calcularIMC(datosClinicos.getPeso(), datosClinicos.getTalla()));
        }
        datosClinicos.setTerminalCrea(TERMINAL_IVR);
        datosClinicos.setFechaHoraCrea(new Date());
        return datosClinicos;

    }

    public BigDecimal calcularIMC(BigDecimal peso, Integer talla) {
        Double tallaMts = talla.doubleValue();
        tallaMts = tallaMts * tallaMts;
        Double imc = peso.doubleValue() / tallaMts;
        return (BigDecimal.valueOf(imc));
    }

    private void guardarAdjuntosAnexo9(RefAnexos9 anexo9, List<AdjuntoDTO> adjuntos, String usuario, String rutaAdjunto, String rutaAnexo) throws java.lang.Exception {
        for (AdjuntoDTO adjunto : adjuntos) {
            String ruta;
            if (adjunto.getTipo().equals("1") || adjunto.getTipo().equals("2") || adjunto.getTipo().equals("3")) {
                ruta = rutaAnexo;
                generarPDF(adjunto, rutaAnexo);
            } else {
                ruta = rutaAdjunto;
                generarPDF(adjunto, rutaAdjunto);
            }
            RefAnexo9Adjuntos adjuntoA9 = new RefAnexo9Adjuntos();
            adjuntoA9.setRefAnexos9Id(anexo9);
            GnMaestros tipoAdjunto = consultarMaestroPorValorTipo(adjunto.getTipo(), MaestroTipo.CR_ADJUNTO_TIPO);
            if (tipoAdjunto != null) {
                adjuntoA9.setMaeTipoArchivoId(tipoAdjunto.getId());
                adjuntoA9.setMaeTipoArchivoCodigo(tipoAdjunto.getValor());
                adjuntoA9.setMaeTipoArchivoValor(tipoAdjunto.getDescripcion());
                adjuntoA9.setNombreArchivo(adjunto.getNombre());
                adjuntoA9.setRuta(ruta);
                adjuntoA9.setArchivo(adjunto.getNombre() + ".pdf");
                adjuntoA9.setUsuarioCrea(usuario);
                adjuntoA9.setTerminalCrea(TERMINAL_IVR);
                adjuntoA9.setFechaHoraCrea(new Date());
                adjuntoA9.setId(insertarRefAnexo9Adjuntos(adjuntoA9));
            }
        }
    }

    private boolean validarAdjuntosAnexo9(RefAnexos9 anexo9, List<AdjuntoDTO> adjuntos, String usuario, String rutaAdjunto, String rutaAnexo) throws java.lang.Exception {
        for (AdjuntoDTO adjunto : adjuntos) {
            GnMaestros tipoAdjunto = consultarMaestroPorValorTipo(adjunto.getTipo(), MaestroTipo.CR_ADJUNTO_TIPO);
            if (tipoAdjunto == null) {
                return false;
            }
        }
        return true;
    }

    private boolean validarTipoAnexo(String tipo) throws java.lang.Exception {
        boolean exitoso = false;
        switch (tipo) {
            case "9":
                exitoso = true;
                break;
            case "10":
                exitoso = true;
                break;
            default:
                exitoso = false;
        }
        return exitoso;
    }

    private void generarPDF(AdjuntoDTO adjunto, String ruta) {
        try {
            byte[] base64decodedBytes = Base64.getDecoder().decode(adjunto.getAnexo());
            File directorio = new File(ruta);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            String rutaCompleta = ruta + adjunto.getNombre() + ".pdf";
            File file = new File(rutaCompleta);
            FileOutputStream fop = new FileOutputStream(file);
            try {
                fop.write(base64decodedBytes);
                fop.flush();
                fop.close();
            } catch (IOException ex) {
                fop.flush();
                fop.close();
            }
        } catch (IOException excepcion) {

        }
    }

    private int insertarRefAnexo9DatosClinicos(RefAnexo9DatosClinicos datosClinicos) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(datosClinicos).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarRefAnexo9Adjuntos(RefAnexo9Adjuntos adjunto) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(adjunto).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
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
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarAuAnexos2(AuAnexos2 anexo2) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(anexo2).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarAuAnexos2Item(AuAnexo2Items anexo2Item) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(anexo2Item).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarAuAnexos4(AuAnexos4 anexo4) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(anexo4).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarAuAnexos4Item(AuAnexo4Items anexo4Item) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(anexo4Item).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarAuAnexo2Diagnosticos(AuAnexo2Diagnosticos anexo2Diagnostico) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(anexo2Diagnostico).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarRefAnexos9(RefAnexos9 anexo9) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(anexo9).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarRefAnexo9Diagnosticos(RefAnexo9Diagnosticos anexo9Diag) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(anexo9Diag).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int insertarRefAnexo9Estados(RefAnexo9Estados estado) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(estado).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public GnMaestros consultarMestroPorId(int id) throws Exception {
        GnMaestros objRes = null;

        try {
            objRes = (GnMaestros) getEntityManager().find(GnMaestros.class,
                    id);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private GnMaestros consultarMaestroPorValor(String valor) throws Exception {
        GnMaestros objRes = null;
        try {
            String strQuery = "FROM GnMaestros m "
                    + "WHERE m.valor ='" + valor + "'";
            objRes = (GnMaestros) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private CntProfesionales consultarProfesionalPorDocumento(String documento) throws Exception {
        CntProfesionales objRes = null;
        try {
            String strQuery = "FROM CntProfesionales m "
                    + "WHERE m.documento ='" + documento + "'";

            objRes = (CntProfesionales) getEntityManager().createQuery(strQuery).setMaxResults(1).getSingleResult();

        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
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
            objRes = (GnMaestros) getEntityManager().createQuery(strQuery).getSingleResult();

        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private MaServiciosHabilitacion consultarMaServicioHabilitacionPorCodigo(String codigo) throws Exception {
        MaServiciosHabilitacion objRes = null;
        try {
            String strQuery = "FROM MaServiciosHabilitacion m "
                    + "WHERE m.codigo ='" + codigo + "'";
            objRes = (MaServiciosHabilitacion) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private GnEmpresas consultarEmpresaPorCodigoHabilitacion(String codigoHabilitacion) throws Exception {
        GnEmpresas objRes = null;
        try {
            String strQuery = "FROM GnEmpresas m "
                    + "WHERE m.codigoHabilitacion ='" + codigoHabilitacion + "'";
            objRes = (GnEmpresas) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private GnEmpresas consultarEmpresaPorCntPrestador(Integer cntPrestadorSedesId) throws Exception {
        GnEmpresas objRes = null;
        try {
            String strQuery = "FROM GnEmpresas m "
                    + "WHERE m.cntPrestadoresId ='" + cntPrestadorSedesId + "'";
            objRes = (GnEmpresas) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private MaServiciosHabilitacion consultarMaServiciosHabilitacionPorCodigo(String codigo) throws Exception {
        MaServiciosHabilitacion objRes = null;
        try {
            String strQuery = "FROM MaServiciosHabilitacion m "
                    + "WHERE m.codigo ='" + codigo + "'";
            objRes = (MaServiciosHabilitacion) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private ValidaRespuestaCopagoDTO validarCalCopagoModeradora(String idAfiliado, String tipoTecnologia, String idTecnologia,
            String valorTecnologia, String ambito, String idTutela, String idPrograma) {
        ValidaRespuestaCopagoDTO neg;
        try {
            String strQuery = "SELECT " + FN_GN_CAL_COPAGOMODERADORA + "("
                    + "'" + idAfiliado + "',"
                    + "'" + tipoTecnologia + "',"
                    + "'" + idTecnologia + "',"
                    + "'" + valorTecnologia + "',"
                    + "'" + ambito + "',"
                    + "'" + idTutela + "',"
                    + "'" + idPrograma + "'"
                    + ")";
            neg = MySqlServicio.ejecutarFuncionCopago(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaCopagoDTO();
            neg.setCodigo(99);
        }
        return neg;
    }

    private ValidaRespuestaDTO validarValContrato(String codigoHabilitacionSede, String codigoTecnologia, String idRegimen) throws java.lang.Exception {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_GN_VAL_CONTRATO + "("
                    + "'" + codigoHabilitacionSede + "',"
                    + "'" + codigoTecnologia + "',"
                    + "'" + idRegimen + "'"
                    + ")";
            neg = MySqlServicio.ejecutarFuncionAu(strQuery);

        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    private AuAnexos4 castAnexo4(AuAnexos2 anexo2) {
        AuAnexos4 anexo4 = new AuAnexos4();
        try {
            GnMaestros regimenAfiliado = consultarMaestroPorValorTipo("0" + anexo2.getAsegAfiliadosId().getRegimen(), MaestroTipo.GN_REGIMEN);
            MaServiciosHabilitacion servicioHabilitacion = consultarMaServicioHabilitacionPorCodigo("501");
            Date fInicio = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(fInicio);
            c.add(Calendar.DATE, 1);
            Date fFin = c.getTime();
            //Afiliado
            GnMaestros tipoDocumento = consultarMestroPorId(anexo2.getAsegAfiliadosId().getMaeTipoDocumentoId());
            if (tipoDocumento != null) {
                anexo4.setAfiliadoTipoDocumento(tipoDocumento.getValor());
            }
            anexo4.setAsegAfiliadosId(anexo2.getAsegAfiliadosId());
            anexo4.setAfiliadoNumeroDocumento(anexo2.getAsegAfiliadosId().getNumeroDocumento());
            anexo4.setAfiliadoPrimerApellido(anexo2.getAsegAfiliadosId().getPrimerApellido());
            anexo4.setAfiliadoSegundoApellido(anexo2.getAsegAfiliadosId().getSegundoApellido());
            anexo4.setAfiliadoPrimerNombre(anexo2.getAsegAfiliadosId().getPrimerNombre());
            anexo4.setAfiliadoSegundoNombre(anexo2.getAsegAfiliadosId().getSegundoNombre());
            anexo4.setAfiliadoFechaNacimiento(anexo2.getAsegAfiliadosId().getFechaNacimiento());
            anexo4.setAfiliadoUbicacion(anexo2.getAsegAfiliadosId().getAfiliacionUbicacionesId().getId());
            anexo4.setAfiliadoDepartamento(obtenerDepartamento(anexo2.getAsegAfiliadosId().getAfiliacionUbicacionesId().getId()));
            anexo4.setAfiliadoMunicipio(obtenerMunicipio(anexo2.getAsegAfiliadosId().getAfiliacionUbicacionesId().getId()));
            anexo4.setAfiliadoDireccion(anexo2.getAsegAfiliadosId().getDireccionResidencia());
            anexo4.setAfiliadoTelefono(anexo2.getAsegAfiliadosId().getTelefonoFijo());
            anexo4.setAfiliadoCelular(anexo2.getAsegAfiliadosId().getTelefonoMovil());
            anexo4.setAfiliadoCorreo(anexo2.getAsegAfiliadosId().getEmail());
            //Prestador
            anexo4.setCntPrestadorSedesId(anexo2.getCntPrestadorSedesId());
            GnMaestros tipoDocumentoPrest = consultarMestroPorId(anexo2.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoId());
            if (tipoDocumentoPrest != null) {
                anexo4.setPrestadorTipoDocumento(tipoDocumentoPrest.getValor());
            }
            anexo4.setPrestadorNumeroDocumento(anexo2.getCntPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
            anexo4.setPrestadorNombre(anexo2.getCntPrestadorSedesId().getNombre());
            anexo4.setPrestadorCodigoHabilitacion(anexo2.getCntPrestadorSedesId().getCodigoHabilitacion());
            anexo4.setPrestadorTelefonoCita(anexo2.getCntPrestadorSedesId().getTelefonoCitas());
            anexo4.setPrestadorDireccion(anexo2.getCntPrestadorSedesId().getDireccion());
            anexo4.setPrestadorDepartamento(obtenerDepartamento(anexo2.getCntPrestadorSedesId().getUbicacionId()));
            anexo4.setPrestadorMunicipio(obtenerMunicipio(anexo2.getCntPrestadorSedesId().getUbicacionId()));
            //General
            anexo4.setAuAnexos2Id(anexo2);
            anexo4.setNumeroAutorizacion(anexo2.getId().toString());
            anexo4.setFechaInicio(fInicio);
            anexo4.setFechaFin(fFin);
            anexo4.setDiasVigencia(1);
            anexo4.setFechaAutorizacion(fInicio);
            anexo4.setFechaAutorizacionImpresion(fInicio);
            anexo4.setEstado(1);
            if (regimenAfiliado != null) {
                anexo4.setMaeRegimenId(regimenAfiliado.getId());
                anexo4.setMaeRegimenCodigo(regimenAfiliado.getValor());
                anexo4.setMaeRegimenValor(regimenAfiliado.getDescripcion());
            }

            anexo4.setMaeAmbitoAtencionId(0);
            anexo4.setMaeAmbitoAtencionCodigo("0");
            anexo4.setMaeAmbitoAtencionValor("0");
            if (servicioHabilitacion != null) {
                anexo4.setMaServicioHabilitadoId(servicioHabilitacion.getId());
                anexo4.setMaServicioHabilitadoCodigo(String.valueOf(servicioHabilitacion.getCodigo()));
                anexo4.setMaServicioHabilitadoValor(servicioHabilitacion.getNombre());
            }

            anexo4.setMaEspecialidadId(0);
            anexo4.setMaeGuiaManejoIntegralId(3129);
            anexo4.setMaeGuiaManejoIntegralCodigo("0");
            anexo4.setMaeGuiaManejoIntegralValor("Sin guia");
            anexo4.setAplicaFactura(false);
            anexo4.setAplicaNobps(false);
            anexo4.setAplicaPac(false);
            anexo4.setAplicaCuotaModeradora(false);
            anexo4.setAplicaCopago(false);
            anexo4.setAplicaCuotaRecuperacion(false);
            anexo4.setAplicaOtro(false);
            anexo4.setAplicaAltocosto(false);
            anexo4.setAplicaTutela(false);
            anexo4.setAplicaTopeMaximo(false);
            anexo4.setAplicaNoRed(false);
            anexo4.setAplicaAutorizacionAutomatica(false);
            anexo4.setAplicaTiqueteBonoVale(false);
            anexo4.setAplicaCapitaRecobro(false);
            anexo4.setSemanasAfiliacion(getTotalSemanasActuales(anexo2.getAsegAfiliadosId().getFechaAfiliacionEps()));
            anexo4.setImpresionesAutorizadas(2);
            anexo4.setImpresionesRealizadas(0);
            anexo4.setObservacion("Autorizacion Automatica IVR");
            anexo4.setUsuarioCrea(USUARIO_IVR);
            anexo4.setTerminalCrea(TERMINAL_IVR);
            anexo4.setFechaHoraCrea(new Date());
            if (anexo2.getCntPrestadorSedesId().getCntPrestadoresId().getGnEmpresasList() != null
                    && !anexo2.getCntPrestadorSedesId().getCntPrestadoresId().getGnEmpresasList().isEmpty()) {
                anexo4.setGnEmpresasId(anexo2.getCntPrestadorSedesId().getCntPrestadoresId().getGnEmpresasList().get(0));
            }

        } catch (java.lang.Exception ex) {
            anexo4 = null;
        }
        return anexo4;
    }

    private AuAnexos4 castAnexo4(AuAnexo4 anexo) {
        AuAnexos4 anexo4 = new AuAnexos4();
        try {

            anexo4.setAfiliadoTipoDocumento(anexo.getAfiliadoTipoDocumento());
            //anexo4.setAsegAfiliadosId(anexo.geta);
            anexo4.setAfiliadoNumeroDocumento(anexo.getAfiliadoNumeroDocumento());
            anexo4.setAfiliadoPrimerApellido(anexo.getAfiliadoPrimerApellido());
            anexo4.setAfiliadoSegundoApellido(anexo.getAfiliadoSegundoApellido());
            anexo4.setAfiliadoPrimerNombre(anexo.getAfiliadoPrimerNombre());
            anexo4.setAfiliadoSegundoNombre(anexo.getAfiliadoSegundoNombre());
            anexo4.setAfiliadoFechaNacimiento(anexo.getAfiliadoFechaNacimiento());
            anexo4.setAfiliadoUbicacion(anexo.getAfiliadoUbicacion());
            anexo4.setAfiliadoDepartamento(anexo.getAfiliadoDepartamento());
            anexo4.setAfiliadoMunicipio(anexo.getAfiliadoMunicipio());
            anexo4.setAfiliadoDireccion(anexo.getAfiliadoDireccion());
            anexo4.setAfiliadoTelefono(anexo.getAfiliadoTelefono());
            anexo4.setAfiliadoCelular(anexo.getAfiliadoCelular());
            anexo4.setAfiliadoCorreo(anexo.getAfiliadoCorreo());
            //Prestador
            //anexo4.setCntPrestadorSedesId(anexo.getCntPrestadorSedeId());
            anexo4.setPrestadorTipoDocumento(anexo.getPrestadorTipoDocumento());
            anexo4.setPrestadorNumeroDocumento(anexo.getPrestadorNumeroDocumento());
            anexo4.setPrestadorNombre(anexo.getPrestadorNombre());
            anexo4.setPrestadorCodigoHabilitacion(anexo.getPrestadorCodigoHabilitacion());
            anexo4.setPrestadorTelefonoCita(anexo.getPrestadorTelefonoCita());
            anexo4.setPrestadorDireccion(anexo.getPrestadorDireccion());
            anexo4.setPrestadorDepartamento(anexo.getPrestadorDepartamento());
            anexo4.setPrestadorMunicipio(anexo.getPrestadorMunicipio());
            //General
            //anexo4.setAuAnexos2Id(anexo2);
            anexo4.setNumeroAutorizacion(anexo.getNumeroAutorizacion());
            anexo4.setFechaInicio(anexo.getFechaInicio());
            anexo4.setFechaFin(anexo.getFechaFin());
            anexo4.setDiasVigencia(1);
            anexo4.setFechaAutorizacion(anexo.getFechaAutorizacion());
            anexo4.setFechaAutorizacionImpresion(anexo.getFechaAutorizacionImpresion());
            anexo4.setEstado(1);
            //if (regimenAfiliado != null) {
            anexo4.setMaeRegimenId(anexo.getMaeRegimenId());
            anexo4.setMaeRegimenCodigo(anexo.getMaeRegimenCodigo());
            anexo4.setMaeRegimenValor(anexo.getMaeRegimenValor());
            //}

            anexo4.setMaeAmbitoAtencionId(0);
            anexo4.setMaeAmbitoAtencionCodigo("0");
            anexo4.setMaeAmbitoAtencionValor("0");
            //if (servicioHabilitacion != null) {
            anexo4.setMaServicioHabilitadoId(anexo.getMaServicioHabilitadoId());
            anexo4.setMaServicioHabilitadoCodigo(anexo.getMaServicioHabilitadoCodigo());
            anexo4.setMaServicioHabilitadoValor(anexo.getMaServicioHabilitadoValor());
            //}

            anexo4.setMaEspecialidadId(0);
            anexo4.setMaeGuiaManejoIntegralId(3129);
            anexo4.setMaeGuiaManejoIntegralCodigo("0");
            anexo4.setMaeGuiaManejoIntegralValor("Sin guia");
            anexo4.setAplicaFactura(false);
            anexo4.setAplicaNobps(false);
            anexo4.setAplicaPac(false);
            anexo4.setAplicaCuotaModeradora(false);
            anexo4.setAplicaCopago(false);
            anexo4.setAplicaCuotaRecuperacion(false);
            anexo4.setAplicaOtro(false);
            anexo4.setAplicaAltocosto(false);
            anexo4.setAplicaTutela(false);
            anexo4.setAplicaTopeMaximo(false);
            anexo4.setAplicaNoRed(false);
            anexo4.setAplicaAutorizacionAutomatica(false);
            anexo4.setAplicaTiqueteBonoVale(false);
            anexo4.setAplicaCapitaRecobro(false);
            anexo4.setSemanasAfiliacion(anexo.getSemanasAfiliacion());
            anexo4.setImpresionesAutorizadas(2);
            anexo4.setImpresionesRealizadas(0);
            anexo4.setObservacion("Autorizacion Automatica IVR");
            anexo4.setUsuarioCrea(USUARIO_IVR);
            anexo4.setTerminalCrea(TERMINAL_IVR);
            anexo4.setFechaHoraCrea(new Date());
            //anexo4.setGnEmpresasId(anexo.getGnEmpresaId());

        } catch (java.lang.Exception ex) {
            anexo4 = null;
        }
        return anexo4;
    }

    private AuAnexo4 crearAnexo4(AuAnexo2 au2, AuAnexo2Item auAnexos2Item) throws Exception {
        //CntPrestador prestador = au2.getCntPrestadorSede().getCntPrestador();
        AuAnexo4 au4 = null;
        try {
            au4 = new AuAnexo4();
            au4.setAuAnexo2Id(au2);
            if (au2.getCntPrestadorSede() != null) {
                try {
                    Empresa emp = getEmpresaRemoto().consultarPorPrestador(au2.getCntPrestadorSede().getCntPrestador().getId());
                    if (emp != null) {
                        au4.setGnEmpresaId(emp);
                    }
                } catch (Exception e) {
                    au4.setGnEmpresaId(null);
                }
            } else {
                au4.setGnEmpresaId(null);
            }
            au4.setAsegAfiliadoId(au2.getAsegAfiliado());
            au4.setSemanasAfiliacion(DateUtil.getTotalSemanasActuales(au2.getAsegAfiliado().getFechaAfiliacionEps()));
            au4.setObservacion(AU4_OBSERVACION);
            //Afiliados
            //Maestro tipoDocumento = bean.getHashTiposDocumentoPersona().get(au2.getAsegAfiliado().getMaeTipoDocumento());
            GnMaestros tipoDocumento = consultarMestroPorId(au2.getAsegAfiliado().getMaeTipoDocumento());
            if (tipoDocumento != null) {
                au4.setAfiliadoTipoDocumento(tipoDocumento.getValor());
            }
            au4.setAfiliadoNumeroDocumento(au2.getAsegAfiliado().getNumeroDocumento());
            au4.setAfiliadoPrimerApellido(au2.getAsegAfiliado().getPrimerApellido());
            au4.setAfiliadoSegundoApellido(au2.getAsegAfiliado().getSegundoApellido());
            au4.setAfiliadoPrimerNombre(au2.getAsegAfiliado().getPrimerNombre());
            au4.setAfiliadoSegundoNombre(au2.getAsegAfiliado().getSegundoNombre());
            au4.setAfiliadoFechaNacimiento(au2.getAsegAfiliado().getFechaNacimiento());
            au4.setAfiliadoUbicacion(au2.getAsegAfiliado().getAfiliacionUbicacion().getId());
            Ubicacion ubicacion = getUbicacionRemoto().consultar(au2.getAsegAfiliado().getAfiliacionUbicacion().getId());
            au4.setAfiliadoDepartamento(ubicacion.getUbicacionPadre().getNombre());
            au4.setAfiliadoMunicipio(ubicacion.getNombre());
            //au4.setAfiliadoDepartamento(bean.obtenerDepartamento(au2.getAsegAfiliado().getAfiliacionUbicacion().getId()));
            //au4.setAfiliadoMunicipio(bean.obtenerMunicipio(au2.getAsegAfiliado().getAfiliacionUbicacion().getId()));
            au4.setAfiliadoDireccion(au2.getAsegAfiliado().getDireccionResidencia());
//            au4.setAfiliadoTelefono(au2.getAsegAfiliado().getTelefonoFijo());
//            au4.setAfiliadoCelular(au2.getAsegAfiliado().getTelefonoMovil());
            if (au2.getAsegAfiliado().getListaAsegAfiliadoContacto() != null) {
                au4.setAfiliadoTelefono(
                        au2.getAsegAfiliado().getListaAsegAfiliadoContacto().stream()
                                .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.CODIGO_CONTACTO_TELEFONO))
                                .findFirst().orElse(new AsegAfiliadoContacto()).getNumeroContacto()
                );
                au4.setAfiliadoCelular(
                        au2.getAsegAfiliado().getListaAsegAfiliadoContacto().stream()
                                .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.CODIGO_CONTACTO_CELULAR))
                                .findFirst().orElse(new AsegAfiliadoContacto()).getNumeroContacto()
                );
            }
            au4.setAfiliadoCorreo(au2.getAsegAfiliado().getEmail());
            au4.setCntPrestadorSedeId(au2.getCntPrestadorSede());
            CntPrestador prestador = getCntPrestadorRemoto().consultar(au2.getCntPrestadorSede().getCntPrestador().getId());
            au4.setPrestadorTipoDocumento(prestador.getMaeTipoDocumentoCodigo() + "");
            au4.setPrestadorNumeroDocumento(prestador.getNumeroDocumento());
            au4.setPrestadorNombre(au2.getCntPrestadorSede().getNombreSede());
            au4.setPrestadorCodigoHabilitacion(au2.getCntPrestadorSede().getCodigoHabilitacionSede());
            au4.setPrestadorTelefonoCita(au2.getCntPrestadorSede().getTelefonoCitas());
            au4.setPrestadorDireccion(au2.getCntPrestadorSede().getDireccion());
            Ubicacion ubicacionPrestador = getUbicacionRemoto().consultar(au2.getCntPrestadorSede().getUbicacionId());
            au4.setPrestadorDepartamento(ubicacionPrestador.getUbicacionPadre().getNombre());
            au4.setPrestadorMunicipio(ubicacionPrestador.getNombre());
            //Ubicacion ubicacionPadre =getUbicacionRemoto().consultar(ubicacion.get)
            //au4.setPrestadorDepartamento(bean.obtenerDepartamento(au2.getCntPrestadorSede().getUbicacionId()));
            //au4.setPrestadorMunicipio(bean.obtenerMunicipio(au2.getCntPrestadorSede().getUbicacionId()));
            au4.setNombreAutoriza(au2.getUsuarioCrea());
            au4.setCargoActividadAutoriza("AUTORIZADOR SAVIASALUD");
            au4.setEpsTelefono("018000423683");
            au4.setEntidadPago("ALIANZA MEDELLIN ANTIOQUIA EPS S.A.S");
            String codigo = "EPS040";
            if (au2.getAsegAfiliado().getRegimen().equals("1")) {
                codigo = "EPSS40";
            }
            au4.setCodigoEntidadPago(codigo);
            au4.setAplicaFactura(false);
            au4.setAplicaNobps(false);
            au4.setAplicaPac(false);
            au4.setAplicaTopeMaximo(false);
            au4.setAplicaNoRed(false);
            au4.setAplicaAutorizacionAutomatica(false);
            au4.setAplicaTiqueteBonoVale(false);
            au4.setAplicaCapitaRecobro(false);
            Date fechaAfiliacion = au2.getAsegAfiliado().getFechaAfiliacionEps();
            Date fechaActual = new Date();
            float diff = fechaActual.getTime() - fechaAfiliacion.getTime();
            diff = diff / (24 * 60 * 60 * 1000);
            float semanas = diff / 7;
            au4.setSemanasAfiliacion((int) semanas);
            au4.setNumeroPrescripcion("" + au2.getId());
            au4.setImpresionesRealizadas(0);
            au4.setObservacion("");

            GnMaestros regimenAfiliado = consultarMaestroPorValorTipo("0" + au2.getAsegAfiliado().getRegimen(), MaestroTipo.GN_REGIMEN);
            if (regimenAfiliado != null) {
                au4.setMaeRegimenId(regimenAfiliado.getId());
                au4.setMaeRegimenCodigo(regimenAfiliado.getValor());
                au4.setMaeRegimenValor(regimenAfiliado.getNombre());
            }
            GnMaestros ambitoAtencion = consultarMaestroPorValorTipo("H", MaestroTipo.GN_AMBITO);
            if (ambitoAtencion != null) {
                au4.setMaeAmbitoAtencionId(ambitoAtencion.getId());
                au4.setMaeAmbitoAtencionCodigo(ambitoAtencion.getValor());
                au4.setMaeAmbitoAtencionValor(ambitoAtencion.getNombre());
            } else {
                au4.setMaeAmbitoAtencionId(0);
                au4.setMaeAmbitoAtencionCodigo("0");
                au4.setMaeAmbitoAtencionValor("0");
            }
//            for (Maestro reg : bean.getListaRegimen()) {
//                if (Integer.parseInt(reg.getValor()) == Integer.parseInt(au2.getAsegAfiliado().getRegimen())) {
//                    au4.setMaeRegimenId(reg.getId());
//                    au4.setMaeRegimenCodigo(reg.getValor());
//                    au4.setMaeRegimenValor(reg.getNombre());
//                    break;
//                }
//            }
//            for (Maestro amb : bean.getListaAmbitoAtencion()) {
//                if (SolicitudesUrgenciasBean.AMBITO_URGENCIA.equalsIgnoreCase(amb.getValor())) {
//                    au4.setMaeAmbitoAtencionId(amb.getId());
//                    au4.setMaeAmbitoAtencionCodigo(amb.getValor());
//                    au4.setMaeAmbitoAtencionValor(amb.getNombre());
//                    break;
//                }
//            }
//            if (au2.getTipo() == 0) { //Medica
//                Maestro manejoIntegral = bean.getHashManejoIntegral().get("0");
//                au4.setMaeGuiaManejoIntegralId(manejoIntegral.getId());
//                au4.setMaeGuiaManejoIntegralCodigo(manejoIntegral.getValor());
//                au4.setMaeGuiaManejoIntegralValor(manejoIntegral.getNombre());
//            } else {//Odontologica
//                Maestro manejoIntegral = bean.getHashManejoIntegral().get("0");
//                au4.setMaeGuiaManejoIntegralId(manejoIntegral.getId());
//                au4.setMaeGuiaManejoIntegralCodigo(manejoIntegral.getValor());
//                au4.setMaeGuiaManejoIntegralValor(manejoIntegral.getNombre());
//            }

            au4.setMaeGuiaManejoIntegralId(3129);
            au4.setMaeGuiaManejoIntegralCodigo("0");
            au4.setMaeGuiaManejoIntegralValor("Sin guia");
            au4.setAplicaFactura(false);
            au4.setAplicaNobps(false);
            au4.setAplicaPac(false);
            au4.setAplicaCuotaModeradora(false);
            au4.setAplicaCopago(false);
            au4.setAplicaCuotaRecuperacion(false);
            au4.setAplicaOtro(false);
            au4.setAplicaAltocosto(false);
            au4.setAplicaTutela(false);
            au4.setAplicaTopeMaximo(false);
            au4.setAplicaNoRed(false);
            au4.setAplicaAutorizacionAutomatica(false);
            au4.setAplicaTiqueteBonoVale(false);
            au4.setAplicaCapitaRecobro(false);
            MaServiciosHabilitacion servicioHabilitacion = consultarMaServicioHabilitacionPorCodigo("501");
            au4.setMaServicioHabilitadoId(servicioHabilitacion.getId());
            au4.setMaServicioHabilitadoCodigo(servicioHabilitacion.getCodigo() + "");
            au4.setMaServicioHabilitadoValor(servicioHabilitacion.getNombre());
            if (au2.getVersion().equals(AuConstantes.UNO)) {
                au4.setVersion(true);
                au4.setDireccionAlternativa(au2.getDireccionAlternativa());
            }
            Date fInicio = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(fInicio);
            c.add(Calendar.DATE, 1);
            Date fFin = c.getTime();
            au4.setFechaInicio(fInicio);
            au4.setFechaFin(fFin);
            au4.setDiasVigencia(1);
            au4.setPosfechada(false);
            au4.setFechaAutorizacion(fInicio);
            au4.setNumeroAutorizacion("0");
            au4.setEstado(AuAnexo4.ESTADO_AUTORIZADA_AUTOMATICO);
            au4.setEstadoJustificacion(au2.getComentarioEstado());
            au4.setObservacion("Autorizacion Automatica IVR");
            au4.setUsuarioCrea(USUARIO_IVR);
            au4.setTerminalCrea(TERMINAL_IVR);
            au4.setFechaHoraCrea(new Date());
            au4.setMedioAutorizacion(AuAnexo4.MEDIO_AUTOMATICA);

            au4.setId(getAuAnexo4Remoto().insertar(au4));
            //Guardar AuAnexos4Items
            if (!au2.getListaAuAnexo2Item().isEmpty()) {
                List<AuAnexo4Item> listaAuAnexo4Item = new ArrayList();
                //for (AuAnexo2Item auAnexos2Item : au2.getListaAuAnexo2Item()) {
                AuAnexo4Item auAnexos4Item = new AuAnexo4Item();
                auAnexos4Item.setAuAnexo4Id(new AuAnexo4(au4.getId()));
                auAnexos4Item.setTipoTecnologia(1);//Cups
                auAnexos4Item.setCantidadAutorizada(1);
                auAnexos4Item.setAuAnexo2ItemId(auAnexos2Item);

                auAnexos4Item.setMaTecnologiaId(auAnexos2Item.getMaTecnologiaId());
                auAnexos4Item.setMaTecnologiaCodigo(auAnexos2Item.getMaTecnologiaCodigo());
                auAnexos4Item.setMaTecnologiaValor(auAnexos2Item.getMaTecnologiaValor());
                String servicios = CODIGO_SERVICIO_HABILITACION_URGENCIAS + "," + CODIGO_SERVICIO_HABILITACION_SERVICIO_URGENCIAS;
                //asignar valorizacion
                auAnexos4Item = asignarTecnologiaCostoAnexo4Item(
                        auAnexos4Item, auAnexos2Item.getMaTecnologiaCodigo(), servicios, au4
                );

                //bean.auditoriaGuardar(auAnexos4Item);
                auAnexos4Item.setUsuarioCrea(USUARIO_IVR);
                auAnexos4Item.setTerminalCrea(TERMINAL_IVR);
                auAnexos4Item.setFechaHoraCrea(new Date());
                auAnexos4Item.setId(getAuAnexo4ItemRemoto().insertar(auAnexos4Item));
                listaAuAnexo4Item.add(auAnexos4Item);
                au4.setAuAnexo4ItemsList(listaAuAnexo4Item);
                //}
            }
            //Archivo adjunto
            String ruta = PropApl.getInstance().get(PropApl.AU_A4_ANEXOS);
            au4.setArchivo(AuConstantes.nombreReporteAnexo4_2(au4));
            //Armado nombre de Archivo descarga Anexo4 desde el 2
            au4.setArchivoNombre(AuConstantes.nombreArchivoReporteAnexo4(au4));
            AuReporte reporte = new AuReporte();
            String mensaje = reporte.generarReporteAnexo4(ruta, au4);
            //StreamedContent streamedContentAnexo4 = bean.generarReporteAnexo4(au4.getAuAnexo2Id().getId());
            //bean.almacenarReporteDisco(streamedContentAnexo4, nombreArchivoBuilder.toString(), ruta);
            if (!mensaje.equals("")) {
                //bean.addMensaje(mensaje);
            }
            au4.setRuta(ruta.replace("\\", "\\\\"));
            getAuAnexo4Remoto().actualizarArchivoReporte(au4);
            //historico
            Maestro mae_causa = getMaestroRemoto().consultar(AuAnexo4Historico.ESTADO_APROBADO_AUTOMATICO);
            AuAnexo4Historico historico = new AuAnexo4Historico();
            historico.setAuAnexos4Id(au4);
            historico.setEstado(au4.getEstado());
            historico.setMaeCausaId(mae_causa.getId());
            historico.setMaeCausaCodigo(mae_causa.getValor());
            historico.setMaeCausaValor(mae_causa.getNombre());
            historico.setObservacionAutorizacion(mae_causa.getNombre());
            historico.setUsuarioCrea(USUARIO_IVR);
            historico.setTerminalCrea(TERMINAL_IVR);
            historico.setFechaHoraCrea(new Date());

            getAuAnexo4HistoricoRemoto().insertar(historico);
        } catch (Exception e) {
            //Log.getInstance().error("Generación Anexo4 a partir del Anexo2", "ERROR al intentar crear el Anexo4 a partir del Anexo2", e);
            throw new Exception("ERROR al intentar crear el Anexo4");
        }
        return au4;
    }

    private AuAnexo4Item asignarTecnologiaCostoAnexo4Item(AuAnexo4Item auAnexos4Item, String codigoUrgencia, String serviciosHabilitacion, AuAnexo4 au4) throws Exception {
        CntContratoDetalle cntContratoDetalle = null;
        String modalidadContrato;
        //verifica que la sede primaria del afiliado sea la misma del anexo
        if (au4.getAsegAfiliadoId().getPrimariaPrestadorSede() != null
                && Objects.equals(au4.getAsegAfiliadoId().getPrimariaPrestadorSede().getId(), au4.getCntPrestadorSedeId().getId())) {
            modalidadContrato = CntContratoSede.MODALIDAD_CAPITA;
            //consulta valor desde contrato
            cntContratoDetalle = getContratoDetalleRemoto().consultarPorSedeTecnologiaTipoYServicioHabilitacion(
                    au4.getCntPrestadorSedeId().getId(), codigoUrgencia, serviciosHabilitacion, 1, modalidadContrato
            );
        }
        if (cntContratoDetalle == null) {
            modalidadContrato = CntContratoSede.MODALIDAD_EVENTO;
            cntContratoDetalle = getContratoDetalleRemoto().consultarPorSedeTecnologiaTipoYServicioHabilitacion(
                    au4.getCntPrestadorSedeId().getId(), codigoUrgencia, serviciosHabilitacion, 1, modalidadContrato
            );
        }

        if (cntContratoDetalle != null && cntContratoDetalle.getValorContratado() != null) {
            auAnexos4Item.setCostoServicio(cntContratoDetalle.getValorContratado());
        } else {//si no tiene valor busca por manual tarifario SOAT
            MaSoatTarifarioValor maSoatTarifarioValor = getTarifarioValorRemoto().consultarTarifarioTecnolgia(codigoUrgencia);
            if (maSoatTarifarioValor != null && maSoatTarifarioValor.getValor() != null) {
                auAnexos4Item.setCostoServicio(maSoatTarifarioValor.getValor());
            } else {
                auAnexos4Item.setCostoServicio(BigDecimal.ZERO);
            }
        }
        return auAnexos4Item;
    }

    private AuAnexo4Items castAnexo4Items(AuAnexos4 anexo4, AuAnexo2Items auAnexo2Item) {
        AuAnexo4Items anexo4Item = new AuAnexo4Items();
        anexo4Item.setAuAnexos4Id(anexo4);
        anexo4Item.setAuAnexo2ItemsId(auAnexo2Item);
        anexo4Item.setTipoTecnologia(1);
        anexo4Item.setMaTecnologiaId(auAnexo2Item.getMaTecnologiaId());
        anexo4Item.setMaTecnologiaCodigo(auAnexo2Item.getMaTecnologiaCodigo());
        anexo4Item.setMaTecnologiaValor(auAnexo2Item.getMaTecnologiaValor());
        anexo4Item.setCantidadAutorizada(1);
        anexo4Item.setCostoServicio(new BigDecimal(0.00));
        anexo4Item.setUsuarioCrea(USUARIO_IVR);
        anexo4Item.setTerminalCrea(TERMINAL_IVR);
        anexo4Item.setFechaHoraCrea(new Date());
        return anexo4Item;
    }

    private static Integer getTotalSemanasActuales(Date fechaInicial) {
        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = new Date().getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        Double totalDias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        Double totalSemanas = totalDias / 7;
        return totalSemanas.intValue();
    }

    public String getRegimen(String id) {
        String resultado;
        switch (id) {
            case "1":
                resultado = "Subsidiado";
                break;
            case "2":
                resultado = "Contributivo";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String getMaeRegimenAfiliado(String id) {
        String resultado;
        switch (id) {
            case "1":
                resultado = "Subsidiado";
                break;
            case "2":
                resultado = "Contributivo";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    private MaDiagnosticos consultarDiagnosticoPorCodigo(String codigo) throws Exception {
        MaDiagnosticos objRes = null;
        try {
            String strQuery = "FROM MaDiagnosticos m "
                    + "WHERE m.codigo ='" + codigo + "'";

            objRes = (MaDiagnosticos) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private MaTecnologias consultarTecnologiaPorCodigo(String codigo) throws Exception {
        MaTecnologias objRes = null;
        try {
            String strQuery = "FROM MaTecnologias m "
                    + "WHERE m.codigoPropio ='" + codigo + "'";

            objRes = (MaTecnologias) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private String generarReporteAnexo4(String ruta, AuAnexos4 anexo4, String tipoDocumento) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYYMMddHHmmssSSS");
        String mensaje = "";
        String nombre = "";
        try {
            nombre = "anexo4-2" + anexo4.getId() + formatoFecha.format(anexo4.getFechaHoraCrea()) + ".pdf";
            List<ReporteAnexo4> listaReportes = castearAuAnexo4ReporteAnexo4(anexo4, tipoDocumento);
            if (!listaReportes.isEmpty()) {
                InputStream is = getClass().getResourceAsStream("/reportes/Anexo4.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReportes);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                File archivo = new File(ruta, nombre);
                OutputStream destino = new FileOutputStream(archivo);
                IOUtils.copy(stream, destino);
                IOUtils.closeQuietly(stream);
                IOUtils.closeQuietly(destino);

            } else {
                mensaje = "No se encontraron datos para el reporte";
                nombre = "";
            }

        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return nombre;
    }

    //ya no se usa
    public List<ReporteAnexo4> castearAuAnexo4ReporteAnexo4(AuAnexos4 anexo4, String tipoDocumento) {
        List<ReporteAnexo4> listaReportes = new ArrayList();
        ReporteAnexo4 reporte = new ReporteAnexo4();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat horaFormat = new SimpleDateFormat("hh:mm");
        try {

            GnEmpresas savia = consultarEmpresaPorId(1);

            if (anexo4 != null) {
                anexo4.setAuAnexo4ItemsList(null);
                List<AuAnexo4Items> items = consultarAuAnexo4ItemPorIdAnexo4(anexo4.getId());

                if (!items.isEmpty()) {
                    for (AuAnexo4Items item : items) {
                        reporte = new ReporteAnexo4();
                        reporte.setStrNumeroAutorizacion(anexo4.getId().toString());
                        reporte.setDateFechaAutorizacion(anexo4.getFechaAutorizacion());
                        reporte.setStrHoraAutorizacion(horaFormat.format(anexo4.getFechaAutorizacion()));
                        reporte.setStrEntidadResponsable(savia.getRazonSocial());
                        String codigo = "EPS040";
                        if (anexo4.getAsegAfiliadosId().getRegimen().equals("1")) {
                            codigo = "EPSS40";
                        }
                        reporte.setStrCodigoEntidad(codigo);
                        reporte.setStrNombrePrestador(anexo4.getCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
                        reporte.setStrTipoDocPrestador(obtenerValorTipoDocumentoIps(anexo4.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoId()));
                        reporte.setStrNumDocPrestador(anexo4.getCntPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
                        reporte.setStrCodigoPrestador(anexo4.getCntPrestadorSedesId().getCntPrestadoresId().getCodigoMinSalud());
                        reporte.setStrCorreoPrestador(anexo4.getCntPrestadorSedesId().getCorreoElectronico());
                        reporte.setStrTelefono1Prestador(anexo4.getCntPrestadorSedesId().getTelefonoCitas());
                        reporte.setStrTelefono2Prestador(anexo4.getCntPrestadorSedesId().getTelefonoAdministrativo());

                        reporte.setStrDireccionPrestador(anexo4.getCntPrestadorSedesId().getDireccion());
                        reporte.setStrDepartamentoPrestador(obtenerDepartamento(anexo4.getCntPrestadorSedesId().getUbicacionId()));
                        reporte.setStrMunicipioPrestador(obtenerMunicipio(anexo4.getCntPrestadorSedesId().getUbicacionId()));
                        reporte.setStrPrimerApellidoPaciente(anexo4.getAsegAfiliadosId().getPrimerApellido());
                        reporte.setStrSegundoApellidoPaciente(anexo4.getAsegAfiliadosId().getSegundoApellido());
                        reporte.setStrPrimerNombrePaciente(anexo4.getAsegAfiliadosId().getPrimerNombre());
                        reporte.setStrSegundoNombrePaciente(anexo4.getAsegAfiliadosId().getSegundoNombre());
                        reporte.setStrTipoDocPaciente(tipoDocumento);
                        reporte.setStrNumDocPaciente(anexo4.getAsegAfiliadosId().getNumeroDocumento());
                        reporte.setStrFechaNacimientoPaciente(dateFormat.format(anexo4.getAsegAfiliadosId().getFechaNacimiento()));
                        reporte.setStrHoraNacimientoPaciente(horaFormat.format(anexo4.getAsegAfiliadosId().getFechaNacimiento()));
                        reporte.setStrDireccionPaciente(anexo4.getAsegAfiliadosId().getDireccionResidencia());
                        reporte.setStrTelefonoFijoPaciente(anexo4.getAsegAfiliadosId().getTelefonoFijo());
                        reporte.setStrDepartementoPaciente(anexo4.getAsegAfiliadosId().getResidenciaUbicacionId().getGnUbicacionesId().getNombre() + "     " + anexo4.getAsegAfiliadosId().getResidenciaUbicacionId().getGnUbicacionesId().getPrefijo());
                        reporte.setStrMunicipioPaciente(anexo4.getAsegAfiliadosId().getResidenciaUbicacionId().getNombre() + "     " + anexo4.getAsegAfiliadosId().getResidenciaUbicacionId().getPrefijo());
                        reporte.setStrTelefonoCelularPaciente(anexo4.getAsegAfiliadosId().getTelefonoMovil());
                        reporte.setStrCorreoPaciente(anexo4.getAsegAfiliadosId().getEmail());
                        reporte.setStrUbicacionPaciente("");
                        reporte.setStrManejoIntegral(anexo4.getMaeGuiaManejoIntegralValor());
                        reporte.setStrCodigoCups(item.getMaTecnologiaCodigo());
                        reporte.setStrCantidad("" + item.getCantidadAutorizada());
                        reporte.setStrDescripcion(item.getMaTecnologiaValor());
                        reporte.setStrObservacion(anexo4.getObservacion());
                        reporte.setStrNumeroOrigen("");
                        reporte.setDateFechaOrigen(anexo4.getFechaHoraCrea());
                        reporte.setStrHoraOrigen(horaFormat.format(anexo4.getFechaHoraCrea()));
                        reporte.setStrPorcentajeAutorizado(""); //Falta preguntar
                        reporte.setStrSemanaPaciente("" + anexo4.getSemanasAfiliacion()); //Falta preguntar
                        reporte.setStrValor(anexo4.getValorCopago() != null ? anexo4.getValorCopago().toString() : "");
                        reporte.setStrPorcentaje(""); //Falta preguntar
                        reporte.setStrAplicaCobro(anexo4.getExcentoCopago() != null ? anexo4.getExcentoCopago() ? "NO" : "SI" : "");
                        //reporte.setStrTipoCuota(""); //Falta preguntar
                        reporte.setStrNombreAutoriza(anexo4.getUsuarioCrea()); //Falta preguntar
                        reporte.setStrCargoAutoriza(""); //Falta preguntar
                        anexo4.setDiasVigencia(180);
                        if (item.getTipoTecnologia() == 2) {
                            anexo4.setDiasVigencia(30);
                        }
                        reporte.setStrDias("" + anexo4.getDiasVigencia());
                        reporte.setStrCama(""); //Falta preguntar
                        reporte.setStrServicio(anexo4.getMaServicioHabilitadoValor());
                        reporte.setStrValor(anexo4.getValorCopago() != null ? anexo4.getValorCopago().toString() : "");
                        ValidaRespuestaCopagoDTO copago = validarCalCopagoModeradora(anexo4.getAsegAfiliadosId().getId().toString(), "1", "8931", "0", "A", "0", "0");
                        if (copago != null) {
                            reporte.setStrPorcentaje("" + copago.getCodigo3());
                        } else {
                            reporte.setStrPorcentaje("0");
                        }

                        reporte.setStrCuotaModeradora(anexo4.getAplicaCuotaModeradora() == true ? "SI" : "NO");
                        reporte.setStrCopago(anexo4.getAplicaCopago() == true ? "SI" : "NO");
                        reporte.setStrCuotaRecuperacion(anexo4.getAplicaCuotaRecuperacion() == true ? "SI" : "NO");
                        reporte.setStrExcentoCuota("NO");
                        listaReportes.add(reporte);
                    }
                }
            }
        } catch (Exception e) {
        }
        return listaReportes;
    }

    private String obtenerValorTipoDocumentoIps(int id) {
        try {
            GnMaestros tipoDocIps = consultarMestroPorId(id);
            return tipoDocIps.getValor();
        } catch (Exception e) {
            return "";
        }
    }

    private GnEmpresas consultarEmpresaPorId(int id) throws Exception {
        GnEmpresas objRes = null;

        try {
            objRes = (GnEmpresas) getEntityManager().find(GnEmpresas.class,
                    id);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private String obtenerDepartamento(int id) {
        try {
            GnUbicaciones departamento = consultarUbicacionPadre(id);
            return (departamento == null) ? "" : departamento.getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    private String obtenerMunicipio(int id) {
        try {
            GnUbicaciones municipio = consultarUbicacionPorId(id);
            return (municipio == null) ? "" : municipio.getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    private GnUbicaciones consultarUbicacionPorId(int id) throws Exception {
        GnUbicaciones objRes = null;

        try {
            objRes = (GnUbicaciones) getEntityManager().find(GnUbicaciones.class,
                    id);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private GnUbicaciones consultarUbicacionPadre(int idHijo) throws Exception {
        GnUbicaciones per = null;
        String strQuery = "FROM GnUbicaciones hijo"
                + " INNER JOIN hijo.gnUbicacionesId padre"
                + " WHERE hijo.id = " + idHijo;
        try {
            Query query = getEntityManager().createQuery(strQuery);
            per = (GnUbicaciones) query.getSingleResult();
        } catch (NoResultException e) {
            per = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return per;
    }

    public void actualizarAuAnexos4ArchivoReporte(AuAnexos4 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class
            );
            String strQuery = "UPDATE AuAnexos4 a SET ";
            strQuery += " a.archivo = '" + obj.getArchivo() + "', ";
            strQuery += " a.ruta = '" + obj.getRuta() + "' ";
            strQuery += "  WHERE a.id = " + obj.getId();
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

    private List<AuAnexo4Items> consultarAuAnexo4ItemPorIdAnexo4(Integer idAnexo4) throws Exception {
        List<AuAnexo4Items> au4Items = null;
        try {
            String strQuery = "FROM AuAnexo4Items p "
                    + "WHERE p.id = '" + idAnexo4 + "' ";

            au4Items = getEntityManager().createQuery(strQuery)
                    .getResultList();

        } catch (NoResultException e) {
            au4Items = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return au4Items;
    }

    private boolean validarSolicitudAnexo9(ReferenciaAnexo9DTO solicitud, RespuestaSolicitudAnexo9DTO respuesta) {
        boolean valido = true;
        if ("".equals(solicitud.getDocumentoMedico()) || !esNumerico(solicitud.getDocumentoMedico())) {
            respuesta = generarRespuetaError("-1000", "Documento de Médico Requerido");
            valido = false;
        }
        return valido;
    }

    private RespuestaSolicitudAnexo9DTO generarRespuetaError(String rc, String msg) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        RespuestaSolicitudAnexo9DTO respuesta = new RespuestaSolicitudAnexo9DTO();
        respuesta.setCodigoRespuesta(rc);
        respuesta.setMensaje(msg);
        respuesta.setFechaTransaccion(dateFormat.format(new Date()));
        return respuesta;
    }

    private void crearAnexo2Rescate(AuAnexo2 au2) throws Exception {
        try {
            List<PeAfiliadosPrograma> programasAfiliado = getPeAfiliadosProgramaRemoto().consultarAfiliados(au2.getAsegAfiliado().getId());
            for (PeAfiliadosPrograma programaAfiliado : programasAfiliado) {
                if (programaAfiliado.getActivo() && !programaAfiliado.getCntPrestadorSede().getId().equals(CntPrestadorSede.ID_SEDE_SIN_ESPECIFICAR)) {
                    PePrograma programa = getPeProgramaRemoto().consultar(programaAfiliado.getPePrograma().getId());
                    if (programa.getAplicaRescateAnexo2Urgencia() && programa.isActivo()) {
                        AuAnexo2Rescate rescate = new AuAnexo2Rescate();
                        rescate.setAuAnexo2(au2);
                        rescate.setFuenteOrigen(AuAnexo2Rescate.FUENTE_ORIGEN_ANEXO2);
                        rescate.setPePrograma(programa);
                        rescate.setMotivoConsulta(au2.getMotivo());
                        //afiliado
                        rescate.setAsegAfiliado(au2.getAsegAfiliado());
                        rescate.setMaeAfiliadoTipoDocumentoId(au2.getAsegAfiliado().getMaeTipoDocumento());
                        rescate.setMaeAfiliadoTipoDocumentoCodigo(au2.getAsegAfiliado().getMaeTipoDocumentoCodigo());
                        rescate.setMaeAfiliadoTipoDocumentoValor(au2.getAsegAfiliado().getMaeTipoDocumentoValor());
                        rescate.setAfiliadoNumeroDocumento(au2.getAsegAfiliado().getNumeroDocumento());
                        rescate.setAfiliadoPrimerNombre(au2.getAsegAfiliado().getPrimerNombre());
                        rescate.setAfiliadoSegundoNombre(au2.getAsegAfiliado().getSegundoNombre());
                        rescate.setAfiliadoPrimerApellido(au2.getAsegAfiliado().getPrimerApellido());
                        rescate.setAfiliadoSegundoApellido(au2.getAsegAfiliado().getSegundoApellido());
                        //sedes
                        rescate.setCntPrestadorOrigen(au2.getCntPrestadorSede().getCntPrestador());
                        rescate.setCntPrestadorSedeOrigen(au2.getCntPrestadorSede());
                        CntPrestadorSede prestadorSede = getCntPrestadorSedeRemoto().consultar(programaAfiliado.getCntPrestadorSede().getId());
                        rescate.setCntPrestadorDestino(prestadorSede.getCntPrestador());
                        rescate.setCntPrestadorSedeDestino(prestadorSede);
                        if (prestadorSede.getId().equals(au2.getCntPrestadorSede().getId())) {
                            rescate.setEstado(AuAnexo2Rescate.ESTADO_NO_REQUIERE);
                        } else {
                            rescate.setEstado(AuAnexo2Rescate.ESTADO_PENDIENTE);
                        }
                        Empresa emp = getEmpresaRemoto().consultarPorPrestador(prestadorSede.getCntPrestador().getId());
                        if (emp == null) {
                            emp = new Empresa(AuConstantes.EMPRESA_SAVIA);
                        }
                        rescate.setEmpresa(emp);
                        rescate.setTipoRescate(AuTipoRescate.GESTION_RIESGO.getId());
                        rescate.setUsuarioCrea(USUARIO_IVR);
                        rescate.setTerminalCrea(TERMINAL_IVR);
                        rescate.setFechaHoraCrea(new Date());
                        rescate.setId(getAuAnexo2RescateRemoto().insertar(rescate));
                        //crear gestion
                        AuAnexo2RescateGestion gestionCambio = new AuAnexo2RescateGestion();
                        gestionCambio.setAuAnexo2Rescate(rescate);
                        gestionCambio.setTipo(AuAnexo2RescateGestion.TIPO_CAMBIO_ESTADO);
                        gestionCambio.setObservacion(rescate.getEstadoStr());
                        gestionCambio.setFechaHoraGestion(new Date());
                        gestionCambio.setUsuarioCrea(USUARIO_IVR);
                        gestionCambio.setTerminalCrea(TERMINAL_IVR);
                        gestionCambio.setFechaHoraCrea(new Date());
                        getAuAnexo2RescateGestionRemoto().insertar(gestionCambio);
                        if (rescate.getEstado() == AuAnexo2Rescate.ESTADO_PENDIENTE) {
                            List<CntPrestadorContacto> contactos = getCntPrestadorContactoRemoto()
                                    .consultarPorCntContratoSedeTipoYArea(
                                            prestadorSede.getCntPrestador().getId(),
                                            prestadorSede.getId(),
                                            AuConstantes.TIPO_CONTACTO_CORREO,
                                            AuConstantes.AREA_CONTACTO_CRUE
                                    );
                            CntPrestadorContacto correo = contactos.stream()
                                    .filter(c -> c.isActivo() && c.isAutorizaEnvio())
                                    .findFirst().orElse(null);
                            if (correo != null) {
                                String mensaje = "Ingresa Anexo 2 de paciente " + rescate.getAsegAfiliado().getNombreCompleto() + " con numero identificacion " + rescate.getAsegAfiliado().getNumeroDocumento()
                                        + " a la IPS " + rescate.getCntPrestadorSedeOrigen().getNombreSede();
                                GnCorreoEnvio envio = new GnCorreoEnvio(
                                        GnCorreoEnvio.ORIGEN_USUARIOS,
                                        correo.getContacto(),
                                        "Paciente para rescate",
                                        mensaje,
                                        GnCorreoEnvio.TIPO_TEXTO);
                                getGnCorreoEnvioRemoto().insertar(envio);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
//            throw new Exception("ERROR al intentar crear el AuAnexo2Rescate");
        }
    }

    private void crearAnexo2RescateSede(AuAnexo2 anexo2) throws Exception {
        try {
            Integer idPrestadorSedeCapita = anexo2.getAsegAfiliado().getPrimariaPrestadorSede().getId();
            Integer idPrestadorSedeOrigen = anexo2.getCntPrestadorSede().getId();

            AuAnexo2RescateSede rescateSede = getAuAnexo2RescateSedeRemoto().consultarIdSedeCapitaIdSedeOrigen(idPrestadorSedeCapita, idPrestadorSedeOrigen);
            if (rescateSede != null && rescateSede.isActivo() && rescateSede.isAplicaRescateAnexo2()
                    && anexo2.getAsegAfiliado().getModeloLiquidacion().equals(BigDecimal.ZERO.toString())) {
                /* Modelo de liquidacion capita */
                AuAnexo2Rescate rescate = new AuAnexo2Rescate();
                rescate.setAuAnexo2(anexo2);
                rescate.setFuenteOrigen(AuAnexo2Rescate.FUENTE_ORIGEN_ANEXO2);
                rescate.setMotivoConsulta(anexo2.getMotivo());
                rescate.setTipoRescate(AuTipoRescate.GESTION_CAPITA.getId());
                rescate.setAsegAfiliado(anexo2.getAsegAfiliado());
                rescate.setMaeAfiliadoTipoDocumentoId(anexo2.getAsegAfiliado().getMaeTipoDocumento());
                rescate.setMaeAfiliadoTipoDocumentoCodigo(anexo2.getAsegAfiliado().getMaeTipoDocumentoCodigo());
                rescate.setMaeAfiliadoTipoDocumentoValor(anexo2.getAsegAfiliado().getMaeTipoDocumentoValor());
                rescate.setAfiliadoNumeroDocumento(anexo2.getAsegAfiliado().getNumeroDocumento());
                rescate.setAfiliadoPrimerNombre(anexo2.getAsegAfiliado().getPrimerNombre());
                rescate.setAfiliadoSegundoNombre(anexo2.getAsegAfiliado().getSegundoNombre());
                rescate.setAfiliadoPrimerApellido(anexo2.getAsegAfiliado().getPrimerApellido());
                rescate.setAfiliadoSegundoApellido(anexo2.getAsegAfiliado().getSegundoApellido());
                rescate.setCntPrestadorOrigen(anexo2.getCntPrestadorSede().getCntPrestador());
                rescate.setCntPrestadorSedeOrigen(anexo2.getCntPrestadorSede());

                CntPrestadorSede prestadorSedeDestino = getCntPrestadorSedeRemoto().consultar(idPrestadorSedeCapita);
                rescate.setCntPrestadorDestino(prestadorSedeDestino.getCntPrestador());
                rescate.setCntPrestadorSedeDestino(prestadorSedeDestino);

                if (prestadorSedeDestino.getId().equals(anexo2.getCntPrestadorSede().getId())) {
                    rescate.setEstado(AuAnexo2Rescate.ESTADO_NO_REQUIERE);
                } else {
                    rescate.setEstado(AuAnexo2Rescate.ESTADO_PENDIENTE);
                }

                Empresa emp = getEmpresaRemoto().consultarPorPrestador(prestadorSedeDestino.getCntPrestador().getId());
                if (emp == null) {
                    emp = new Empresa(AuConstantes.EMPRESA_SAVIA);
                }
                rescate.setEmpresa(emp);

                rescate.setUsuarioCrea(USUARIO_IVR);
                rescate.setTerminalCrea(TERMINAL_IVR);
                rescate.setFechaHoraCrea(new Date());
                rescate.setId(getAuAnexo2RescateRemoto().insertar(rescate));

                /* Crear gestion */
                AuAnexo2RescateGestion gestionCambio = new AuAnexo2RescateGestion();
                gestionCambio.setAuAnexo2Rescate(rescate);
                gestionCambio.setTipo(AuAnexo2RescateGestion.TIPO_CAMBIO_ESTADO);
                gestionCambio.setObservacion(rescate.getEstadoStr());
                gestionCambio.setFechaHoraGestion(new Date());
                gestionCambio.setUsuarioCrea(USUARIO_IVR);
                gestionCambio.setTerminalCrea(TERMINAL_IVR);
                gestionCambio.setFechaHoraCrea(new Date());
                getAuAnexo2RescateGestionRemoto().insertar(gestionCambio);
                if (rescate.getEstado() == AuAnexo2Rescate.ESTADO_PENDIENTE) {
                    List<CntPrestadorContacto> contactos = getCntPrestadorContactoRemoto().consultarPorCntContratoSedeTipoYArea(
                            prestadorSedeDestino.getCntPrestador().getId(), prestadorSedeDestino.getId(), AuConstantes.TIPO_CONTACTO_CORREO, AuConstantes.AREA_CONTACTO_CRUE
                    );
                    CntPrestadorContacto correo = contactos.stream()
                            .filter(c -> c.isActivo() && c.isAutorizaEnvio())
                            .findFirst().orElse(null);
                    if (correo != null) {
                        String encabezado = "Paciente para Rescate";
                        StringBuilder mensaje = new StringBuilder();
                        mensaje.append("Ingresa Anexo 2 de paciente ");
                        mensaje.append(rescate.getAsegAfiliado().getNombreCompleto());
                        mensaje.append(" con ID ");
                        mensaje.append(rescate.getAsegAfiliado().getNumeroDocumento());
                        mensaje.append(" a la IPS ");
                        mensaje.append(rescate.getCntPrestadorSedeOrigen().getNombreSede());
                        GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_CRUE_SOLICITUDES, correo.getContacto(), encabezado, mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
                        getGnCorreoEnvioRemoto().insertar(envio);
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("ERROR: No fue posible crear el AuAnexo2Rescate para Flujo Capita. Detalle: " + e.getMessage());
        }
    }
}
