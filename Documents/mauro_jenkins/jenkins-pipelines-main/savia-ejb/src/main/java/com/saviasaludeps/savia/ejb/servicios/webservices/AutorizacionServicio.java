package com.saviasaludeps.savia.ejb.servicios.webservices;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.ConsultaSolicitud;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos4;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Impresiones;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.webservices.AutorizacionRemoto;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.AfiliadoAutorizacionDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.AutorizacionDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.DetalleRespuestaAutorizacionImpresionDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.SolicitudAutorizacionDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.SolicitudAutorizacionImpresionDTO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Adjunto;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Diagnostico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Entregas;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionalPrestadores;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionales;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.MaDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.MaInsumos;
import com.saviasaludeps.savia.ejb.entidades.MaMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MaPaquetes;
import com.saviasaludeps.savia.ejb.entidades.MaServiciosHabilitacion;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologias;
import com.saviasaludeps.savia.ejb.utilidades.PropApl;
import com.saviasaludeps.savia.ejb.utilidades.RemotoEJB;
import com.saviasaludeps.savia.ejb.validaciones.FuncionMySqlServicio;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3AdjuntoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3ItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4Remoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntProfesionalRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaEspecialidadRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaServicioHabilitacionRemoto;
import com.saviasaludeps.savia.solicitud.dominio.AuConstantes;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaA4AutomaticoDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaCopagoDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaGrupoAsignado;
import com.saviasaludeps.savia.solicitud.negocio.AuAnexo3SolicitudRemoto;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.AdjuntoAnexo3DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.AfiliadoSolicitudAnexo3DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.Anexo3AutorizacionesDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.Anexo3ItemDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.Anexo4DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.Anexo4ItemDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.DetalleEntregaDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.DetalleEntregaRespuestaDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.DetalleRespuestaSolicitudAnexo3DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.EntregaTecnologiaDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.EntregaTecnologiaRespuestaDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.RespuestaAutorizacionPrestador2DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.RespuestaAutorizacionPrestador3DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.RespuestaAutorizacionPrestador4DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.RespuestaEntregaServiciosDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.RespuestaSolicitudAnexo3DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.SolicitudAnexo3DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.SolicitudEntregaServiciosDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.SolicitudSolicitudAnexo3DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.TecnologiaSolicitadaAnexo3DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.SolicitudAutorizacionPrestadorDTO;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
@Remote(AutorizacionRemoto.class)
public class AutorizacionServicio extends GenericoServicio implements AutorizacionRemoto {

    //Tecnologias
    private static final String procedimiento = "1";
    private static final String medicamento = "2";
    private static final String insumo = "3";
    private static final String paquete = "4";
    private static final String agrupador = "5";
    //Estado Anex 4
    //0. Autorizado - 1. Autorizado Automatico - 2. Anulada - 3.Preautorizado - 4.Autorizado preautorizacion - 5. Aulado 
    private static final String ESTADO_0 = "Autorizado";
    private static final String ESTADO_1 = "Autorizado Automatico";
    private static final String ESTADO_2 = "Anulada";
    private static final String ESTADO_3 = "Preautorizado";
    private static final String ESTADO_4 = "Autorizado preautorizacion";
    private static final String ESTADO_5 = "Anulado Preautorización";
    private static final String ESTADO_6 = "Autorizado Pago Anticipado";
    private static final String ESTADO_7 = "Anulado Pago Anticipado";

    private final static String FN_GN_CAL_COPAGOMODERADORA = "fn_gn_cal_copagomoderadora";
    private static final String Date_REGEX
            = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    private static final String DateTime_REGEX
            = "^([0-9]{4})-([0-1][0-9])-([0-3][0-9])\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$";

    private static final Pattern Date_PATTERN = Pattern.compile(Date_REGEX);
    private static final Pattern DateTime_PATTERN = Pattern.compile(DateTime_REGEX);

    DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean dateValidator(String date) {
        Matcher matcher = Date_PATTERN.matcher(date);
        return matcher.matches();
    }

    public static boolean dateTimeValidator(String date) {
        Matcher matcher = DateTime_PATTERN.matcher(date);
        return matcher.matches();
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private MaEspecialidadRemoto getEspecialidadRemoto() throws Exception {
        return (MaEspecialidadRemoto) RemotoEJB.getEJBRemoto(("MaEspecialidadServicio"), MaEspecialidadRemoto.class.getName());
    }

    private AuAnexo4Remoto getAuAnexo4Remoto() throws Exception {
        return (AuAnexo4Remoto) RemotoEJB.getEJBRemoto("AuAnexo4Servicio", AuAnexo4Remoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private AuAnexo3AdjuntoRemoto getAuAnexo3AdjuntoRemoto() throws Exception {
        return (AuAnexo3AdjuntoRemoto) RemotoEJB.getEJBRemoto("AuAnexo3AdjuntoServicio", AuAnexo3AdjuntoRemoto.class.getName());
    }

    private CntProfesionalRemoto getProfesionalRemoto() throws Exception {
        return (CntProfesionalRemoto) RemotoEJB.getEJBRemoto("CntProfesionalServicio", CntProfesionalRemoto.class.getName());
    }

    private AuAnexo3SolicitudRemoto getAuAnexo3SolicitudRemoto() throws Exception {
        return (AuAnexo3SolicitudRemoto) RemotoEJB.getEJBSolicitudRemoto("AuAnexo3Servicio", AuAnexo3SolicitudRemoto.class.getName());
    }

    private UbicacionRemoto getUbicacionRemoto() throws Exception {
        return (UbicacionRemoto) RemotoEJB.getEJBRemoto("UbicacionServicio", UbicacionRemoto.class.getName());
    }

    private MaServicioHabilitacionRemoto getServicioHabilitacionRemoto() throws Exception {
        return (MaServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto("MaServicioHabilitacionServicio", MaServicioHabilitacionRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private MaDiagnosticoRemoto getMaDiagnosticoRemoto() throws Exception {
        return (MaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("MaDiagnosticoServicio", MaDiagnosticoRemoto.class.getName());
    }

    private AuAnexo4ItemRemoto getAuAnexo4ItemRemoto() throws Exception {
        return (AuAnexo4ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo4ItemServicio", AuAnexo4ItemRemoto.class.getName());
    }

    private AuAnexo3Remoto getAuAnexo3Remoto() throws Exception {
        return (AuAnexo3Remoto) RemotoEJB.getEJBRemoto("AuAnexo3Servicio", AuAnexo3Remoto.class.getName());
    }

    private AuAnexo3ItemRemoto getAuAnexo3ItemRemoto() throws Exception {
        return (AuAnexo3ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo3ItemServicio", AuAnexo3ItemRemoto.class.getName());
    }

    @Override
    public AfiliadoAutorizacionDTO consultarAutorizacion(SolicitudAutorizacionDTO solicitud) throws java.lang.Exception {
        AfiliadoAutorizacionDTO afiliado = new AfiliadoAutorizacionDTO();
        List<AutorizacionDTO> autorizaciones;

        try {
            GnMaestros tipoDocumento = consultarMaestroPorValorTipo(solicitud.getTipoDocumento(), "03");
            if (tipoDocumento != null) {
                String strQuery = "FROM AuAnexos3 p "
                        + "WHERE p.asegAfiliadosId.maeTipoDocumentoId ='" + tipoDocumento.getId() + "'"
                        + " AND p.asegAfiliadosId.numeroDocumento ='" + solicitud.getDocumento() + "'"
                        + " AND p.asegAfiliadosId.fechaNacimiento ='" + solicitud.getFechaNacimiento() + "'"
                        + " AND p.asegAfiliadosId.maeEstadoAfiliacionId = '134'"
                        + " AND p.maeAmbitoAtencionCodigo = 'A'"
                        + " ORDER BY p.id DESC";

                List<AuAnexos3> list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                if (!list.isEmpty()) {
                    autorizaciones = new ArrayList<>();
                    afiliado = castAfiliadoAutorizacionDTO(list.get(0).getAsegAfiliadosId());
                    for (AuAnexos3 anexo3 : list) {
                        if (anexo3.getAuAnexos4List() != null && !anexo3.getAuAnexos4List().isEmpty()) {
                            for (AuAnexos4 auAnexos4 : anexo3.getAuAnexos4List()) {
                                Long diasDuracion = Duration.between(auAnexos4.getFechaHoraCrea().toInstant(), new Date().toInstant()).toDays();
                                if (auAnexos4.getEstado() != 2 && diasDuracion < 365 && (!auAnexos4.getFechaAutorizacion().after(new Date()))) {
                                    if (auAnexos4.getAuAnexo4ItemsList() != null && !auAnexos4.getAuAnexo4ItemsList().isEmpty()) {
                                        for (AuAnexo4Items AuAnexo4Item : auAnexos4.getAuAnexo4ItemsList()) {
                                            //if (AuAnexo4Item.getMaTecnologiaCodigo().equals("890701")){
                                            if (AuAnexo4Item.getTipoTecnologia() == 1 || AuAnexo4Item.getTipoTecnologia() == 4) {
                                                autorizaciones.add(castAutorizacionDTO(AuAnexo4Item));
                                            }

                                            //}
                                        }
                                    }
                                }

                            }
                            afiliado.setAutorizaciones(autorizaciones);
                        }
                    }

                } else {
                    afiliado = null;
                }
            } else {
                afiliado = null;
            }

            if (afiliado != null && (afiliado.getAutorizaciones() == null || afiliado.getAutorizaciones().isEmpty())) {
                afiliado = null;
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

    @Override
    public AuAnexo3 consultarEstadoAutorizacion(ConsultaSolicitud solicitud) throws Exception {
        AuAnexo3 anexo3 = new AuAnexo3();
        String estado = null;
        try {
            String strQuery = "SELECT p.estado "
                    + "FROM AuAnexos3 p "
                    + "WHERE p.id = '" + solicitud.getNumeroSolicitud() + "' ";
            int estadoId = (int) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
            anexo3.setEstado(estadoId);
            anexo3.setId(Integer.parseInt(solicitud.getNumeroSolicitud()));
        } catch (NoResultException e) {
            anexo3.setId(Integer.parseInt(solicitud.getNumeroSolicitud()));
            anexo3.setJustificacionClinica("no existe registro");
            anexo3.setEstado(999);
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return anexo3;
    }

    @Override
    public List<AuAnexo3Item> consultarPorAnexo3Id(int id) throws Exception {
        List<AuAnexo3Item> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM AuAnexo3Items m "
                    + "WHERE m.auAnexos3Id.id =  " + id
                    + " ORDER BY m.id";
            List<AuAnexo3Items> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo3Items per : list) {
                listResult.add(castEntidadNegocioCorto(per));
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

    private AuAnexo3Item castEntidadNegocioCorto(AuAnexo3Items entidad) {
        AuAnexo3Item negocio = new AuAnexo3Item();
        negocio.setId(entidad.getId());
        negocio.setEstado(entidad.getEstado());
        negocio.setMaTecnologiaCodigo(entidad.getMaTecnologiaCodigo());
        return negocio;
    }

    @Override
    public DetalleRespuestaAutorizacionImpresionDTO autorizacionImpresion(SolicitudAutorizacionImpresionDTO solicitud, String rutaA2, String rutaA3) throws Exception {
        DetalleRespuestaAutorizacionImpresionDTO impresion = null;
        String marca;

        try {

            String strQuery = "FROM AuAnexos4 p "
                    + "WHERE p.auAnexos3Id ='" + solicitud.getNumeroSolicitud() + "'"
                    + " AND p.id ='" + solicitud.getAutorizacion() + "'";

            List<AuAnexos4> resp = getEntityManager().createQuery(strQuery).getResultList();
            if (resp != null && !resp.isEmpty()) {
                if (!resp.get(0).getFechaAutorizacion().after(new Date())) {
                    GnMaestros tipoDocumento = consultarMestroPorId(resp.get(0).getAsegAfiliadosId().getMaeTipoDocumentoId());
                    if (resp.get(0).getRuta() != null && resp.get(0).getArchivo() != null
                            && !resp.get(0).getRuta().equals("") && !resp.get(0).getArchivo().equals("")) {

                        AuAnexo4Impresiones impresionA4 = new AuAnexo4Impresiones();
                        impresionA4.setAuAnexos4Id(resp.get(0));
                        impresionA4.setOrigenImpresion(1);//pagina web
                        if (resp.get(0).getImpresionesRealizadas() == null || resp.get(0).getImpresionesRealizadas() < resp.get(0).getImpresionesAutorizadas()) {
                            try {
                                int ejeX = 20;
                                int ejeY = 20;
                                if (resp.get(0).getImpresionesRealizadas() == null || resp.get(0).getImpresionesRealizadas() == 0) {
                                    resp.get(0).setImpresionesRealizadas(1);
                                    marca = "Original";
                                    impresionA4.setTipoImpresion(0);
                                } else {
                                    resp.get(0).setImpresionesRealizadas(resp.get(0).getImpresionesRealizadas() + 1);

                                    marca = "Copia " + resp.get(0).getImpresionesRealizadas();
                                    impresionA4.setTipoImpresion(1);
                                }
                                impresion = new DetalleRespuestaAutorizacionImpresionDTO();
                                String ruta = resp.get(0).getRuta() + resp.get(0).getArchivo();
                                PdfReader reader = new PdfReader(ruta);
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                PdfStamper stamper = new PdfStamper(reader, baos);
                                // do stuff with stamper
                                stamper.close();
                                reader.close();
                                impresion.setAdjunto(Base64.getEncoder().encodeToString(baos.toByteArray()));
                                byte[] newBytes = generarMarcaAgua(baos.toByteArray(), marca, ejeX, ejeY);
                                impresion.setAdjunto(Base64.getEncoder().encodeToString(newBytes));
                                actualizarAuAnexos4ArchivoReporte(resp.get(0));
                                impresionA4.setUsuarioCrea("pagina web");
                                impresionA4.setTerminalCrea("127.0.0.1");
                                impresionA4.setFechaHoraCrea(new Date());
                                impresionA4.setId(insertarAuAnexo4Impresiones(impresionA4));;
                            } catch (IOException ex) {
                                //generar pdf si no lo encuentra en el fileserver
                                String ruta = resp.get(0).getAuAnexos2Id() != null ? rutaA2 : rutaA3;
                                String nombrePdf = generarReporteAnexo4(ruta, resp.get(0), tipoDocumento.getValor());
                                if (!nombrePdf.equals("")) {
                                    resp.get(0).setRuta(ruta);
                                    resp.get(0).setArchivo(nombrePdf);
                                    resp.get(0).setImpresionesRealizadas(1);
                                    actualizarAuAnexos4ArchivoReporte(resp.get(0));
                                    impresion = new DetalleRespuestaAutorizacionImpresionDTO();
                                    PdfReader reader = new PdfReader(ruta + nombrePdf);
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    PdfStamper stamper = new PdfStamper(reader, baos);
                                    // do stuff with stamper
                                    stamper.close();
                                    reader.close();
                                    byte[] newBytes = generarMarcaAgua(baos.toByteArray(), "Original", 20, 20);
                                    impresion.setAdjunto(Base64.getEncoder().encodeToString(newBytes));
                                    impresion.setAdjunto(Base64.getEncoder().encodeToString(baos.toByteArray()));
                                    //insertar en tabla AuAnexo4Impresiones
                                    if (impresionA4.getId() == null || impresionA4.getId() == 0) {
                                        impresionA4 = new AuAnexo4Impresiones();
                                        impresionA4.setAuAnexos4Id(resp.get(0));
                                        impresionA4.setOrigenImpresion(1);//pagina web
                                        impresionA4.setTipoImpresion(0);
                                        impresionA4.setUsuarioCrea("pagina web");
                                        impresionA4.setTerminalCrea("127.0.0.1");
                                        impresionA4.setFechaHoraCrea(new Date());
                                        insertarAuAnexo4Impresiones(impresionA4);
                                    }

                                }

                            }
                        } else {
                            impresion = new DetalleRespuestaAutorizacionImpresionDTO();
                            impresion.setAdjunto("La autorización número " + resp.get(0).getId() + " ya alcanzo el número de impresiones permitidas (" + resp.get(0).getImpresionesRealizadas() + ").");
                        }

                    } else {
                        //generar pdf si no tiene ruta almacenada
                        String ruta = resp.get(0).getAuAnexos2Id() != null ? rutaA2 : rutaA3;
                        String nombrePdf = generarReporteAnexo4(ruta, resp.get(0), tipoDocumento.getValor());
                        if (!nombrePdf.equals("")) {
                            resp.get(0).setRuta(ruta);
                            resp.get(0).setArchivo(nombrePdf);
                            resp.get(0).setImpresionesRealizadas(1);
                            actualizarAuAnexos4ArchivoReporte(resp.get(0));
                            impresion = new DetalleRespuestaAutorizacionImpresionDTO();
                            PdfReader reader = new PdfReader(ruta + nombrePdf);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            PdfStamper stamper = new PdfStamper(reader, baos);
                            // do stuff with stamper
                            stamper.close();
                            reader.close();
                            byte[] newBytes = generarMarcaAgua(baos.toByteArray(), "Original", 20, 20);
                            impresion.setAdjunto(Base64.getEncoder().encodeToString(newBytes));
                            impresion.setAdjunto(Base64.getEncoder().encodeToString(baos.toByteArray()));
                            //insertar en tabla AuAnexo4Impresiones
                            AuAnexo4Impresiones impresionA4 = new AuAnexo4Impresiones();
                            impresionA4.setAuAnexos4Id(resp.get(0));
                            impresionA4.setOrigenImpresion(1);//pagina web
                            impresionA4.setTipoImpresion(0);
                            impresionA4.setUsuarioCrea("pagina web");
                            impresionA4.setTerminalCrea("127.0.0.1");
                            impresionA4.setFechaHoraCrea(new Date());
                            insertarAuAnexo4Impresiones(impresionA4);
                        }
                    }
                } else {
                    impresion = new DetalleRespuestaAutorizacionImpresionDTO();
                    impresion.setAdjunto("Ocurrió un error al imprimir la autorización (pos fechado)");
                }

            } else {
                impresion = null;
            }

        } catch (NoResultException e) {
            impresion = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return impresion;
    }

    public byte[] generarMarcaAgua(byte[] bytes, String marca, int ejeX, int ejeY) {
        byte[] newBytes = null;
        try {
            Rectangle tamanoPagina;
            com.lowagie.text.pdf.PdfReader pdfLector = new com.lowagie.text.pdf.PdfReader(bytes);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            com.lowagie.text.pdf.PdfStamper estampador = new com.lowagie.text.pdf.PdfStamper(pdfLector, baos);
            int paginas = pdfLector.getNumberOfPages();
            for (int i = 1; i <= paginas; i++) {
                tamanoPagina = pdfLector.getPageSizeWithRotation(i);
                PdfContentByte canvas = estampador.getOverContent(i);
                if (marca.equals("ANULADA")) {
                    Font f = new Font();
                    f.setColor(Color.RED);
                    f.setStyle(Font.BOLD);
                    f.setSize(8);
                    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(marca, f), ejeX, ejeY, 0);
                } else {
                    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(marca), ejeY, ejeX, 0);
                }

            }
            estampador.close();
            pdfLector.close();
            newBytes = baos.toByteArray();
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(AutorizacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newBytes;
    }

    public void actualizarAuAnexos4ArchivoReporte(AuAnexos4 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos4 a SET ";
            strQuery += " a.archivo = '" + obj.getArchivo() + "', ";
            strQuery += " a.ruta = '" + obj.getRuta() + "', ";
            strQuery += " a.impresionesRealizadas = '" + obj.getImpresionesRealizadas() + "' ";
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

    private String generarReporteAnexo4(String ruta, AuAnexos4 anexo4, String tipoDocumento) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYYMMddHHmmssSSS");
        String mensaje = "";
        String nombre = "";
        try {
            nombre = "anexo4-3" + anexo4.getId() + formatoFecha.format(anexo4.getFechaHoraCrea()) + ".pdf";
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

    public List<ReporteAnexo4> castearAuAnexo4ReporteAnexo4(AuAnexos4 anexo4, String tipoDocumento) {
        List<ReporteAnexo4> listaReportes = new ArrayList();
        ReporteAnexo4 reporte = new ReporteAnexo4();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat horaFormat = new SimpleDateFormat("hh:mm");
        try {
            GnEmpresas savia = consultarEmpresaPorId(1);

            if (anexo4 != null) {
                if (!anexo4.getAuAnexo4ItemsList().isEmpty()) {
                    for (AuAnexo4Items item : anexo4.getAuAnexo4ItemsList()) {
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

    private String obtenerDepartamento(int id) {
        try {
            GnUbicaciones departamento = consultarUbicacionPadre(id);
            return departamento.getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    private String obtenerMunicipio(int id) {
        try {
            GnUbicaciones municipio = consultarUbicacionPorId(id);
            return municipio.getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    private GnUbicaciones consultarUbicacionPorId(int id) throws Exception {
        GnUbicaciones objRes = null;
        try {
            objRes = (GnUbicaciones) getEntityManager().find(GnUbicaciones.class, id);
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

    private GnEmpresas consultarEmpresaPorId(int id) throws Exception {
        GnEmpresas objRes = null;
        try {
            objRes = (GnEmpresas) getEntityManager().find(GnEmpresas.class, id);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private String obtenerValorTipoDocumentoIps(int id) {
        try {
            GnMaestros tipoDocIps = consultarMestroPorId(id);
            return tipoDocIps.getValor();
        } catch (Exception e) {
            return "";
        }
    }

    public GnMaestros consultarMestroPorId(int id) throws Exception {
        GnMaestros objRes = null;
        try {
            objRes = (GnMaestros) getEntityManager().find(GnMaestros.class, id);
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

    private AfiliadoAutorizacionDTO castAfiliadoAutorizacionDTO(AsegAfiliados afiliado) {
        AfiliadoAutorizacionDTO afiliadoDTO = new AfiliadoAutorizacionDTO();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            afiliadoDTO.setPrimerApellido(afiliado.getPrimerApellido());
            afiliadoDTO.setSegundoApellido(afiliado.getSegundoApellido());
            afiliadoDTO.setPrimerNombre(afiliado.getPrimerNombre());
            afiliadoDTO.setSegundoNombre(afiliado.getSegundoNombre());
            if (afiliado.getFechaNacimiento() != null) {
                afiliadoDTO.setFechaNacimiento(dateFormat.format(afiliado.getFechaNacimiento()));
            }

        } catch (Exception ex) {

        }
        return afiliadoDTO;
    }

    private AutorizacionDTO castAutorizacionDTO(AuAnexo4Items AuAnexo4Item) {
        AutorizacionDTO autorizacionDTO = new AutorizacionDTO();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        AuAnexos4 auAnexos4 = AuAnexo4Item.getAuAnexos4Id();
        try {
            autorizacionDTO.setCodigoTecnologia(AuAnexo4Item.getMaTecnologiaCodigo());
            if (auAnexos4.getFechaAutorizacion() != null) {
                autorizacionDTO.setFechaAutorizacion(dateFormat.format(auAnexos4.getFechaAutorizacion()));
            }

            if (auAnexos4 != null && auAnexos4.getCntPrestadorSedesId() != null) {
                autorizacionDTO.setNombreIPSAutorizada(auAnexos4.getCntPrestadorSedesId().getNombre());
            }

            autorizacionDTO.setNombreTecnologia(AuAnexo4Item.getMaTecnologiaValor());
            autorizacionDTO.setNua(auAnexos4.getId().toString());
            autorizacionDTO.setSolicitud(auAnexos4.getAuAnexos3Id().getId().toString());
            autorizacionDTO.setImpresionesRealizadas(auAnexos4.getImpresionesRealizadas());

        } catch (Exception ex) {

        }
        return autorizacionDTO;
    }

    private HashMap<Integer, GnMaestros> consultarHashMaestros() throws Exception {
        HashMap<Integer, GnMaestros> hashResult = new HashMap();
        String strQuery = "FROM GnMaestros e ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros per : list) {
                hashResult.put(per.getId(), per);
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }

    private String getRegimen(String id) {
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

    private GnMaestros consultarMaestroPorValorTipo(String valor, String tipo) throws Exception {
        GnMaestros objRes = null;
        try {
            String strQuery = "FROM GnMaestros m "
                    + "WHERE m.valor ='" + valor
                    + "' AND m.tipo='" + tipo + "'";
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

    private String getModeloLiquidacion(String valor) {
        String equivalente;
        switch (valor) {
            case "0":
                equivalente = "CAPITA";
                break;
            case "1":
                equivalente = "EVENTO";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    private List<AuAnexo4Items> consultarAnexo4ItemPorIdAnexo4(String idAnexo4) throws Exception {
        List<AuAnexo4Items> listResult = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Items p "
                    + "WHERE P.auAnexos4Id ='" + idAnexo4 + "'";
            listResult = getEntityManager().createQuery(strQuery)
                    .getResultList();

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
    public RespuestaSolicitudAnexo3DTO crearSolicitudAnexo3(SolicitudAnexo3DTO solicitudes, String rutaInforme, String usuario) throws Exception {
        RespuestaSolicitudAnexo3DTO respuesta = new RespuestaSolicitudAnexo3DTO();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        DateFormat dateFormatBD = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        respuesta.setFechaTransaccion(dateFormat.format(new Date()));
        respuesta.setConsultaSolicitudResponse(new ArrayList());
        for (Anexo3AutorizacionesDTO solicitud : solicitudes.getSolicitudAutoriaciones()) {

            DetalleRespuestaSolicitudAnexo3DTO respuestaDto = new DetalleRespuestaSolicitudAnexo3DTO();
            respuesta.setRc("0");
            String errorValid = "";
            // 2022-10-19 - Se comenta validaciones de maestros por validacion en funciones en metodos de autorizacion
            GnMaestros tipoDocumento = consultarMaestroPorValorTipo(solicitud.getTipoDocumento(), MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
            if (tipoDocumento == null) {
//                respuesta.setRc("1");
//                respuesta.setMsg("El tipo de documento del afiliado no es valido en el consecutivo #" + solicitud.getConsecutivo());
//                break;
            }
            int length = solicitudes.getCodHabilitacionPrestador().length();
            if (length > 9) {
                if (!solicitudes.getCodHabilitacionPrestador().equals(solicitud.getCodigoHabilitacionIPSSolicita().substring(0, 10))) {
                    errorValid += ("| El prestador e IPS no concuerdan verifique la informacion #" + solicitud.getConsecutivo());
                }
            } else {
                if (length < 7) {
                    errorValid += ("| El prestador e IPS no concuerdan verifique la informacion #" + solicitud.getConsecutivo());
                } else {
                    if (!solicitudes.getCodHabilitacionPrestador().equals(solicitud.getCodigoHabilitacionIPSSolicita().substring(0, 9))) {
                        errorValid += ("| El prestador e IPS no concuerdan verifique la informacion #" + solicitud.getConsecutivo());
                    }
                }
            }
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(tipoDocumento.getId(), solicitud.getDocumento());
            if (afiliado == null) {
                errorValid += ("| Afiliado no encontrado en el consecutivo #" + solicitud.getConsecutivo());
            }
//            else {
//                Maestro maestroAfiliado = getMaestroRemoto().consultar(134);
//                if (maestroAfiliado != null) {
//                    if (afiliado.getMaeEstadoAfiliacion() != maestroAfiliado.getId()) {
//                        respuesta.setRc("1");
//                        respuesta.setMsg("El afiliado no esta activo en el consecutivo #" + solicitud.getConsecutivo());
//                        break;
//                    }
//                }
//            }
            //GnMaestros tipoGenero = consultarMaestroPorValorTipo(solicitud.getSexo(), MaestroTipo.GN_SEXO);
            //if (tipoGenero == null) {
//                respuesta.setRc("1");
//                respuesta.setMsg("El genero no es valido en el consecutivo #" + solicitud.getConsecutivo());
//                break;
            //}
            MaServicioHabilitacion servicioHabilitacion = getServicioHabilitacionRemoto().consultarPorCodigo(Integer.parseInt(solicitud.getCodigoServicioAtencion()));
            if (servicioHabilitacion == null) {
//                respuesta.setRc("1");
                errorValid += ("| El servicio de atención no es valido en el consecutivo #" + solicitud.getConsecutivo());
//                break;
            }
            //GnMaestros ambito = consultarMaestroPorValorTipo(solicitud.getAmbito(), MaestroTipo.GN_AMBITO);
            //if (ambito == null) {
//                respuesta.setRc("1");
//                respuesta.setMsg("El ambito no es valido en el consecutivo #" + solicitud.getConsecutivo());
//                break;
            //}
            CntPrestadorSede sede = getCntPrestadorSedeRemoto().consultarPorCodigoHabilitacion(solicitud.getCodigoHabilitacionIPSSolicita());
            if (sede == null) {
//                respuesta.setRc("1");
                errorValid += ("| El codigo de habilitación no es valido en el consecutivo #" + solicitud.getConsecutivo());
//                break;
            }
            //GnMaestros tipoDocumentoProfesional = consultarMaestroPorValorTipo(solicitud.getTipoDocumentoMedico(), MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
            //if (tipoDocumentoProfesional == null) {
//                respuesta.setRc("1");
//                respuesta.setMsg("El tipo de documento del profesional no es valido en el consecutivo #" + solicitud.getConsecutivo());
//                break;
            //}
            GnMaestros ubicacionPac = consultarMaestroPorValorTipo(solicitud.getUbicacionPaciente(), MaestroTipo.GN_UBICACION);
            if (ubicacionPac == null) {
                //respuesta.setRc("1");
                errorValid += ("| la ubicación del paciente no es valido en el consecutivo #" + solicitud.getConsecutivo());
                //break;
            }
            Maestro tipoDiagnostico = getMaestroRemoto().consultarPorValorTipo(solicitud.getTipoDiagnostico(), MaestroTipo.AU_TIPO_DIAGNOSTICO);
            if (tipoDiagnostico == null) {
//                respuesta.setRc("1");
                errorValid += ("| El tipo diagnostico no es valido en el consecutivo #" + solicitud.getConsecutivo());
//                break;
            }

            GnMaestros finalidad = null;
            if (solicitud.getFinalidadTecnologia() != null) {
                finalidad = consultarMaestroPorValorTipo(
                        solicitud.getFinalidadTecnologia(),
                        MaestroTipo.GN_A1_FINALIDAD_TECNOLOGIA);
                if (finalidad == null) {
                    errorValid += ("| FinalidadTecnologia no es valida en el consecutivo #" + solicitud.getConsecutivo());
                }
            }

            GnMaestros modalidad = null;
            if (solicitud.getModalidadTecnologia() != null) {
                modalidad = consultarMaestroPorValorTipo(
                        solicitud.getModalidadTecnologia(),
                        MaestroTipo.GN_A1_MODALIDAD_TECNOLOGIA);
                if (modalidad == null) {
                    errorValid += ("| ModalidadTecnologia no es valida en el consecutivo #" + solicitud.getConsecutivo());
                }
            }

            //MaDiagnostico diagnosticoPrincipal = getMaDiagnosticoRemoto().consultarPorCodigo(solicitud.getDiagnosticoPrincipal());
            //if (diagnosticoPrincipal == null) {
//                respuesta.setRc("1");
//                respuesta.setMsg("El diagnostico pricipal no es valido en el consecutivo #" + solicitud.getConsecutivo());
//                break;
            //}
            //MaDiagnostico diagnosticoRelacionado1;
            //if (solicitud.getDiagnosticoRelacionado1() != null) {
            //    diagnosticoRelacionado1 = getMaDiagnosticoRemoto().consultarPorCodigo(solicitud.getDiagnosticoRelacionado2());
            //    if (diagnosticoRelacionado1 == null) {
//                    respuesta.setRc("1");
//                    respuesta.setMsg("El diagnostico relacionado 1 no es valido en el consecutivo #" + solicitud.getConsecutivo());
//                    break;
            //    }
            //}
//            MaDiagnostico diagnosticoRelacionado2;
//            if (solicitud.getDiagnosticoRelacionado2() != null) {
//                diagnosticoRelacionado2 = getMaDiagnosticoRemoto().consultarPorCodigo(solicitud.getDiagnosticoRelacionado2());
//                if (diagnosticoRelacionado2 == null) {
////                    respuesta.setRc("1");
////                    respuesta.setMsg("El diagnostico relacionado 2 no es valido en el consecutivo #" + solicitud.getConsecutivo());
////                    break;
//                }
//            }
            if (solicitud.getTecnologiaSolicitada() != null && !solicitud.getTecnologiaSolicitada().isEmpty()) {
                for (TecnologiaSolicitadaAnexo3DTO tecnologia : solicitud.getTecnologiaSolicitada()) {
                    // pertenece a una de las siguientes |1 - Procedimiento|2 - Medicamento|3 - Insumo|4 - Paquete|
                    switch (tecnologia.getTipoTecnologia()) {
                        case procedimiento:
                        case medicamento:
                        case insumo:
                        case paquete:
                        case agrupador:
                            break;
                        default:
                            //respuesta.setRc("1");
                            errorValid += ("| tipo tecnologia no valida #" + solicitud.getConsecutivo());
                        //break;
                    }

                    boolean diagnostico = false;
                    if (solicitud.getDiagnosticoPrincipal().equals(tecnologia.getDiagnosticoTecnologia())) {
                        diagnostico = true;
                    }
                    if (solicitud.getDiagnosticoRelacionado1().equals(tecnologia.getDiagnosticoTecnologia())) {
                        diagnostico = true;
                    }
                    if (solicitud.getDiagnosticoRelacionado2().equals(tecnologia.getDiagnosticoTecnologia())) {
                        diagnostico = true;
                    }
                    if (!diagnostico) {
                        //respuesta.setRc("1");
                        errorValid += ("| diagnosticoTecnologia no coincide con los reportados en la solicitud | consecutivo #" + solicitud.getConsecutivo());
                        //break;
                    }

                    if (tecnologia.getTipoTecnologia().equals(medicamento)) {
                        if (tecnologia.getFechaFormulaMedicamento() != null) {
                            if (!dateValidator(tecnologia.getFechaFormulaMedicamento())) {
                                //respuesta.setRc("1");
                                errorValid += ("| FechaFormulaMedicamento no coincide con el formato aaaa-mm-dd | consecutivo #" + solicitud.getConsecutivo());
                                //break;
                            }
                        } else {
                            //respuesta.setRc("1");
                            errorValid += ("| FechaFormulaMedicamento requerido | consecutivo #" + solicitud.getConsecutivo());
                            //break;
                        }
                        if (tecnologia.getCausaExternaMedicamento() != null) {
                            GnMaestros causaExtMed = consultarMaestroPorValorTipo(tecnologia.getCausaExternaMedicamento(), MaestroTipo.AU_A3_CAUSA_EXTERNA);
                            if (causaExtMed == null) {
                                //respuesta.setRc("1");
                                errorValid += ("| causa externa no es valido en el consecutivo #" + solicitud.getConsecutivo());
                                //break;
                            }
                        } else {
                            //respuesta.setRc("1");
                            errorValid += ("| causa externa requerido en el consecutivo #" + solicitud.getConsecutivo());
                            //break;
                        }

                        if (tecnologia.getFinalidadMedicamento() != null) {
                            GnMaestros finalidadMed = consultarMaestroPorValorTipo(tecnologia.getFinalidadMedicamento(), MaestroTipo.AU_DIAGNOSTICO_FINALIDAD);
                            if (finalidadMed == null) {
                                //respuesta.setRc("1");
                                errorValid += ("| finalidad medicamento no es valido en el consecutivo #" + solicitud.getConsecutivo());
                                //break;
                            }
                        } else {
                            //respuesta.setRc("1");
                            errorValid += ("| finalidad medicamento requerido en el consecutivo #" + solicitud.getConsecutivo());
                            //break;
                        }

                        if (tecnologia.getTipoCatastroficoMedicamento() != null) {
                            GnMaestros tipoCatrastroficoMed = consultarMaestroPorValorTipo(tecnologia.getTipoCatastroficoMedicamento(), MaestroTipo.GN_PATOLOGIA_CATASTROFICA);
                            if (tipoCatrastroficoMed == null) {
                                //respuesta.setRc("1");
                                errorValid += ("| catastrofico Medicamento no es valido en el consecutivo #" + solicitud.getConsecutivo());
                                //break;
                            }
                        } else {
                            //respuesta.setRc("1");
                            errorValid += ("| catastrofico Medicamento requerido en el consecutivo #" + solicitud.getConsecutivo());
                            //break;
                        }

                        if (tecnologia.getDosisMedicamento() == null) {
                            errorValid += ("| Dosis Medicamento requerido en el consecutivo #" + solicitud.getConsecutivo());
                        }

                        if (tecnologia.getFrecuenciaMedicamento() == null) {
                            errorValid += ("| Frecuencia Medicamento requerido en el consecutivo #" + solicitud.getConsecutivo());
                        }

                        if (tecnologia.getViaAdministracion() != null) {
                            GnMaestros viaAdministracion = consultarMaestroPorValorTipo(tecnologia.getViaAdministracion(), MaestroTipo.AU_MEDICAMENTO_VIA_ADMINISTRACION);
                            if (viaAdministracion == null) {
                                errorValid += ("| Via Administracion medicamento no es valido en el consecutivo #" + solicitud.getConsecutivo());
                            }
                        } else {
                            errorValid += ("| Via Administracion medicamento requerido en el consecutivo #" + solicitud.getConsecutivo());
                        }

                        if (tecnologia.getDuracionTratamiento() == null) {
                            errorValid += ("| Tecnologia Duracion Tratamiento requerido en el consecutivo #" + solicitud.getConsecutivo());
                        }
                    }
                }
            }
//            if (solicitud.getAdjuntos() != null && !solicitud.getAdjuntos().isEmpty()) {
//                for (AdjuntoAnexo3DTO adjunto : solicitud.getAdjuntos()) {
//                    GnMaestros tipoAdjunto = consultarMaestroPorValorTipo(adjunto.getTipo(), MaestroTipo.AU_A3_ADJUNTO_TIPO);
//                    if (tipoAdjunto == null) {
////                        respuesta.setRc("1");
////                        respuesta.setMsg("El tipo de adjunto no es valido en el adjunto " + adjunto.getAdjunto() + " en el consecutivo " + solicitud.getConsecutivo());
////                        break;
//                    }
//                }
//            }
//

//          campos auditoria
//          Date fechaCrea = null;
//            if (!dateTimeValidator(solicitudes.getFechaCrea())) {
//                errorValid += ("| verifique el formato de La Fecha Hora crea yyyy-MM-dd hh:mm:ss");
//            }else{
//                fechaCrea = dateTimeFormat.parse(solicitudes.getFechaCrea());
//            }
//
//            if (solicitudes.getTerminalCrea() == null) {
//                errorValid += ("| TerminalCrea Requerido");
//            }
//
//            if (solicitudes.getUsuarioCrea() == null) {
//                errorValid += ("| UsuarioCrea Requerido");
//            }
            if (errorValid.equals("")) {
                respuestaDto.setAfiliado(castearAfiliado(afiliado));
                AuAnexo3 anexo3 = castAuAnexos3(
                        solicitud,
                        afiliado,
                        sede,
                        usuario,
                        servicioHabilitacion,
                        modalidad,
                        finalidad);
                if (ubicacionPac != null) {
                    anexo3.setMaeUbicacionId(ubicacionPac.getId());
                    anexo3.setMaeUbicacionPaciente(ubicacionPac.getId());
                }
                AuAnexo3Diagnostico diagnosticoPri = castAuAnexo3Diagnosticos(solicitud.getDiagnosticoPrincipal(), tipoDiagnostico, true, usuario);
                anexo3.setAuAnexo3DiagnosticosList(new ArrayList<>());
                if (diagnosticoPri != null) {
                    anexo3.getAuAnexo3DiagnosticosList().add(diagnosticoPri);
                    if (solicitud.getDiagnosticoRelacionado1() != null && !solicitud.getDiagnosticoRelacionado1().isEmpty()) {
                        AuAnexo3Diagnostico diagnostico1 = castAuAnexo3Diagnosticos(solicitud.getDiagnosticoRelacionado1(), tipoDiagnostico, false, usuario);
                        anexo3.getAuAnexo3DiagnosticosList().add(diagnostico1);
                    }
                    if (solicitud.getDiagnosticoRelacionado2() != null && !solicitud.getDiagnosticoRelacionado2().isEmpty()) {
                        AuAnexo3Diagnostico diagnostico2 = castAuAnexo3Diagnosticos(solicitud.getDiagnosticoRelacionado2(), tipoDiagnostico, false, usuario);
                        anexo3.getAuAnexo3DiagnosticosList().add(diagnostico2);
                    }
                    anexo3.setAuAnexo3ItemsList(new ArrayList<>());
                    if (solicitud.getTecnologiaSolicitada() != null && !solicitud.getTecnologiaSolicitada().isEmpty()) {
                        for (TecnologiaSolicitadaAnexo3DTO tecnologia : solicitud.getTecnologiaSolicitada()) {
                            AuAnexo3Item item = castAuAnexo3Items(anexo3, tecnologia, usuario);
                            if (item != null) {
                                item.setMaDiagnosticoId(diagnosticoPri.getMaDiagnosticosId());
                                item.setMaDiagnosticoValor(diagnosticoPri.getMaDiagnosticosValor());
                                item.setMaDiagnosticoCodigo(diagnosticoPri.getMaDiagnosticosCodigo());
                                anexo3.getAuAnexo3ItemsList().add(item);
                            } else {
                                respuesta.setRc("1");
                                respuesta.setMsg("La tecnologia no existe " + tecnologia.getCodigoTecnologia());
                                break;
                            }
                        }
                    }
                }
                if (respuesta.getRc().equals("1")) {
                    break;
                } else {
                    respuesta.setMsg("Transaccion Exitosa");
                }
                anexo3.setWebService(true);
                anexo3 = getAuAnexo3SolicitudRemoto().insertar(anexo3);
                if (anexo3.getErrores().isEmpty()) {
                    try {
                        anexo3 = getAuAnexo3Remoto().consultar(anexo3.getId());
                        anexo3.setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(anexo3.getId()));
                        respuestaDto.setSolicitudes(new ArrayList<>());
                        respuestaDto.getSolicitudes().add(castAnexo3(anexo3));
                        respuestaDto.setAutorizaciones(new ArrayList<>());
                        AuAnexo4 anexo4 = getAuAnexo4Remoto().consultarPorAnexo3(anexo3.getId());
                        if (anexo4 != null) {
                            anexo4.setAuAnexo4ItemsList(getAuAnexo4ItemRemoto().consultarListaByIdAnexo4(anexo4.getId()));
                            respuestaDto.getAutorizaciones().add(castearAnexo4(anexo4));
                        }
                    } catch (Exception e) {
                    }
                    if (solicitud.getAdjuntos() != null) {
                        for (AdjuntoAnexo3DTO adjunto : solicitud.getAdjuntos()) {
                            AuAnexo3Adjunto adjuntoG = new AuAnexo3Adjunto();
                            adjuntoG.setAuAnexos3Id(anexo3);
                            Maestro maeTipoAdjunto = getMaestroRemoto().consultar(Integer.parseInt(adjunto.getTipo()));
                            adjuntoG.setMaeTipoArchivoCodigo(maeTipoAdjunto.getValor());
                            adjuntoG.setMaeTipoArchivoId(maeTipoAdjunto.getId());
                            adjuntoG.setMaeTipoArchivoValor(maeTipoAdjunto.getNombre());
                            guardarAdjunto(adjuntoG);
                        }
                    }
                } else {
                    respuesta.setRc("1");
                    String errores = "No se genero el anexo4 por los siguientes errores";
                    for (String error : anexo3.getErrores()) {
                        errores += "/n" + error;
                    }
                    respuesta.setMsg(errores);
                    break;
                }
                respuesta.getConsultaSolicitudResponse().add(respuestaDto);

            } else {
                respuesta.setRc("1");
                respuesta.setMsg("error: " + errorValid);

            }

        }
        return respuesta;
    }

    @Override
    public RespuestaEntregaServiciosDTO entregaServicios(SolicitudEntregaServiciosDTO solicitud, String usuario) throws Exception {
        RespuestaEntregaServiciosDTO respuesta = new RespuestaEntregaServiciosDTO();
        List<EntregaTecnologiaRespuestaDTO> entregasTecnologiaRespList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        DateFormat dateFormatBD = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        for (EntregaTecnologiaDTO entregaTecnologia : solicitud.getEntregaTecnologia()) {
            String strQuery = "FROM AuAnexo4Items p "
                    + "WHERE p.auAnexos4Id ='" + entregaTecnologia.getAutorizacion() + "'";

            List<AuAnexo4Items> auAnexos4ItemList = getEntityManager().createQuery(strQuery).getResultList();
            EntregaTecnologiaRespuestaDTO respEntregatecnologia = new EntregaTecnologiaRespuestaDTO();
            respEntregatecnologia.setAutorizacion(entregaTecnologia.getAutorizacion());
            List<DetalleEntregaRespuestaDTO> detalleEntregaRespList = new ArrayList<>();
            if (auAnexos4ItemList != null && !auAnexos4ItemList.isEmpty()) {
                for (DetalleEntregaDTO detalleEntregaDTO : entregaTecnologia.getDetalleEntrega()) {
                    for (AuAnexo4Items anexo4Item : auAnexos4ItemList) {
                        if (detalleEntregaDTO.getCodItemEntregado().equals(anexo4Item.getMaTecnologiaCodigo())) {
                            //buscar entregas realizadas
                            AuAnexo4Entregas entregaAnt = null;
                            if (anexo4Item.getAuAnexo4EntregasList() != null && !anexo4Item.getAuAnexo4EntregasList().isEmpty()) {
                                entregaAnt = anexo4Item.getAuAnexo4EntregasList().get(anexo4Item.getAuAnexo4EntregasList().size() - 1);
                            }

                            DetalleEntregaRespuestaDTO detalleEntregaResp = new DetalleEntregaRespuestaDTO();
                            detalleEntregaResp.setCodItemEntregado(detalleEntregaDTO.getCodItemEntregado());
                            detalleEntregaResp.setCantidadEntregada(detalleEntregaDTO.getCantidadEntregada());
                            detalleEntregaResp.setCantidadAutorizada(anexo4Item.getCantidadAutorizada());
                            AuAnexo4Entregas entrega = new AuAnexo4Entregas();
                            entrega.setAuAnexos4Id(anexo4Item.getAuAnexos4Id());
                            entrega.setAuAnexo4ItemsId(anexo4Item);
                            entrega.setFechaHoraEntrega(dateFormatBD.parse(detalleEntregaDTO.getFechaEntregaTecnologia()));
                            entrega.setCantidadAutorizada(anexo4Item.getCantidadAutorizada());
                            entrega.setCantidadEntregada(detalleEntregaDTO.getCantidadEntregada());
                            if (entregaAnt != null) {
                                if ((entregaAnt.getCantidadPendiente() < detalleEntregaDTO.getCantidadEntregada())) {
                                    //se devuelve error de cantidad solicitada mayor
                                    detalleEntregaResp.setCantidadPendiente(entregaAnt.getCantidadPendiente());
                                    detalleEntregaResp.setCodigoRespuesta(1);
                                    detalleEntregaResp.setDescripcionRespuesta("Error: La cantidad Entregada es mayor a la autorizada");
                                    detalleEntregaRespList.add(detalleEntregaResp);
                                    break;
                                } else {
                                    entrega.setTipoEntrega(obtenerTipoEntrega(detalleEntregaResp.getCantidadPendiente(), detalleEntregaResp.getCantidadEntregada()));

                                    if (entregaTecnologia.getDocumentoRecibe().equals(anexo4Item.getAuAnexos4Id().getAsegAfiliadosId().getNumeroDocumento())) {
                                        //colocar datos de afiliado en reclamo
                                        entrega.setReclamaAfiliado(true);
                                        String nombreCompletoAfiliado = anexo4Item.getAuAnexos4Id().getAsegAfiliadosId().getPrimerNombre() + anexo4Item.getAuAnexos4Id().getAsegAfiliadosId().getPrimerApellido() + anexo4Item.getAuAnexos4Id().getAsegAfiliadosId().getSegundoApellido();
                                        entrega.setNombreReclama(nombreCompletoAfiliado);
                                        entrega.setTelefonoReclama(anexo4Item.getAuAnexos4Id().getAsegAfiliadosId().getTelefonoFijo());
                                        entrega.setCelularReclama(anexo4Item.getAuAnexos4Id().getAsegAfiliadosId().getTelefonoMovil());
                                    } else {
                                        //colocar datos de reclamo que vienen del json
                                        entrega.setReclamaAfiliado(false);
                                        entrega.setNombreReclama(entregaTecnologia.getNombreUsuarioRecibe());
                                        entrega.setTelefonoReclama(entregaTecnologia.getTelefonoRecibe());
                                        entrega.setCelularReclama(entregaTecnologia.getCelularRecibe());
                                    }
                                    entrega.setUsuarioCrea(usuario);
                                    entrega.setTerminalCrea("127.0.0.1");
                                    entrega.setFechaHoraCrea(new Date());
                                    insertarAuAnexos4Entregas(entrega);
                                    detalleEntregaResp.setCantidadPendiente(entregaAnt.getCantidadPendiente() - detalleEntregaDTO.getCantidadEntregada());
                                    entrega.setCantidadPendiente(detalleEntregaResp.getCantidadPendiente());
                                    detalleEntregaResp.setCodigoRespuesta(1);
                                    detalleEntregaResp.setTipoEntrega(tipoEntregaString(entrega.getTipoEntrega()));
                                    detalleEntregaResp.setDescripcionRespuesta("Entrega registrada de manera exitosa");
                                    detalleEntregaRespList.add(detalleEntregaResp);
                                    break;
                                }

                            } else {
                                //validar si la cantidad a entregar es mayor que la autorizada
                                if (anexo4Item.getCantidadAutorizada() > detalleEntregaDTO.getCantidadEntregada()) {
                                    //se devuelve error de cantidad solicitada mayor
                                    detalleEntregaResp.setCantidadPendiente(anexo4Item.getCantidadAutorizada());
                                    detalleEntregaResp.setCodigoRespuesta(1);
                                    detalleEntregaResp.setDescripcionRespuesta("Error: La cantidad Entregada es mayor a la autorizada");
                                    detalleEntregaRespList.add(detalleEntregaResp);
                                    break;
                                } else {
                                    entrega.setTipoEntrega(obtenerTipoEntrega(detalleEntregaResp.getCantidadPendiente(), detalleEntregaResp.getCantidadEntregada()));

                                    if (entregaTecnologia.getDocumentoRecibe().equals(anexo4Item.getAuAnexos4Id().getAsegAfiliadosId().getNumeroDocumento())) {
                                        //colocar datos de afiliado en reclamo
                                        entrega.setReclamaAfiliado(true);
                                        String nombreCompletoAfiliado = anexo4Item.getAuAnexos4Id().getAsegAfiliadosId().getPrimerNombre() + anexo4Item.getAuAnexos4Id().getAsegAfiliadosId().getPrimerApellido() + anexo4Item.getAuAnexos4Id().getAsegAfiliadosId().getSegundoApellido();
                                        entrega.setNombreReclama(nombreCompletoAfiliado);
                                        entrega.setTelefonoReclama(anexo4Item.getAuAnexos4Id().getAsegAfiliadosId().getTelefonoFijo());
                                        entrega.setCelularReclama(anexo4Item.getAuAnexos4Id().getAsegAfiliadosId().getTelefonoMovil());
                                    } else {
                                        //colocar datos de reclamo que vienen del json
                                        entrega.setReclamaAfiliado(false);
                                        entrega.setNombreReclama(entregaTecnologia.getNombreUsuarioRecibe());
                                        entrega.setTelefonoReclama(entregaTecnologia.getTelefonoRecibe());
                                        entrega.setCelularReclama(entregaTecnologia.getCelularRecibe());
                                    }
                                    entrega.setUsuarioCrea(usuario);
                                    entrega.setTerminalCrea("127.0.0.1");
                                    entrega.setFechaHoraCrea(new Date());
                                    insertarAuAnexos4Entregas(entrega);
                                    detalleEntregaResp.setCantidadPendiente(anexo4Item.getCantidadAutorizada() - detalleEntregaDTO.getCantidadEntregada());
                                    entrega.setCantidadPendiente(detalleEntregaResp.getCantidadPendiente());
                                    detalleEntregaResp.setCodigoRespuesta(1);
                                    detalleEntregaResp.setTipoEntrega(tipoEntregaString(entrega.getTipoEntrega()));
                                    detalleEntregaResp.setDescripcionRespuesta("Entrega registrada de manera exitosa");
                                    detalleEntregaRespList.add(detalleEntregaResp);
                                    break;
                                }

                            }

                        }
                    }
                }
                respEntregatecnologia.setDetalleEntrega(detalleEntregaRespList);
            } else {
                respuesta.setCantidadRegistros(0);
                respuesta.setRespuesta(-1000);
                respuesta.setNUT(solicitud.getNUT());
                respuesta.setMensaje("Autorizacion no encontrada.");
                respuesta.setFechaTransaccion(dateFormat.format(new Date()));
            }
            entregasTecnologiaRespList.add(respEntregatecnologia);
            respuesta.setEntregaTecnologia(entregasTecnologiaRespList);
        }

        return respuesta;
    }

    private int insertarAuAnexos4Entregas(AuAnexo4Entregas entrega) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(entrega).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private int obtenerTipoEntrega(int pendientes, int entregados) {
        int tipoEntrega = 0;
        if (entregados == 0) {
            //sin entregas
            tipoEntrega = 3;
        } else {
            if (pendientes == 0) {
                //entrega total
                tipoEntrega = 1;
            } else {
                //entrega parcial
                tipoEntrega = 2;
            }
        }

        return tipoEntrega;
    }

    private String tipoEntregaString(int tipoEntrega) {
        switch (tipoEntrega) {
            case 1:
                return "Total";

            case 2:
                return "Parcial";
            case 3:
                return "Sin entrega";
            default:
                return "";
        }
    }

    private CntPrestadorSedes consultarPrestadorPorCodigoHabilitacion(String codigoHabilitacion) throws java.lang.Exception {
        CntPrestadorSedes ips = null;

        try {
            String strQuery = "FROM CntPrestadorSedes p "
                    + "WHERE  p.codigoHabilitacion = '" + codigoHabilitacion + "' ";

            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
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

    private AuAnexo3 castAuAnexos3(
            Anexo3AutorizacionesDTO solicitud,
            AsegAfiliado afiliado,
            CntPrestadorSede sede,
            String usuario,
            MaServicioHabilitacion servicioHabilitacion,
            GnMaestros modalidad,
            GnMaestros finalidad) throws java.lang.Exception {
        AuAnexo3 anexo3 = new AuAnexo3();

        if (sede.getCntPrestador() != null) {
            CntPrestadorSede prestador = consultarEmpresaPorCodigoHabilitacion(sede.getCodigoHabilitacionSede());
            anexo3.setCntPrestadorSedeId(prestador);
        }

        if (sede.getCntPrestador() != null) {
            Empresa empresa = consultarPorEmpresa(sede.getCntPrestador().getId());
            anexo3.setGnEmpresaId(empresa);
        }

        anexo3.setFechaSolicitud(new Date());
        if (afiliado != null) {
            anexo3.setAsegAfiliadoId(afiliado);
        }

        anexo3.setJustificacionClinica(solicitud.getJustificacionClinica());
        anexo3.setEstado(AuAnexo3.ESTADO_PENDIENTE);
        GnMaestros origenAtencion = consultarMaestroPorValorTipo(solicitud.getOrigenAtencion(), MaestroTipo.GN_ORIGEN_ATENCION);
        if (origenAtencion != null) {
            anexo3.setMaeOrigenAtencionId(origenAtencion.getId());
            anexo3.setMaeOrigenAtencionCodigo(origenAtencion.getValor());
            anexo3.setMaeOrigenAtencionValor(origenAtencion.getNombre());
        }
        GnMaestros ambitoAtencion = consultarMaestroPorValorTipo(solicitud.getAmbito(), MaestroTipo.GN_AMBITO);
        if (ambitoAtencion != null) {
            anexo3.setMaeAmbitoAtencionId(ambitoAtencion.getId());
            anexo3.setMaeAmbitoAtencionCodigo(ambitoAtencion.getValor());
            anexo3.setMaeAmbitoAtencionValor(ambitoAtencion.getNombre());
        }
        anexo3.setFuenteOrigen(3);
        CntProfesional profesional;
        GnMaestros tipoDocMed = consultarMaestroPorValorTipo(solicitud.getTipoDocumentoMedico(), MaestroTipo.GN_TIPO_DOCUMENTO_PROFESIONAL);
        if (tipoDocMed != null) {
            profesional = getProfesionalRemoto().consultarNumDocumento(tipoDocMed.getId(), solicitud.getDocumentoMedico());
            if (profesional != null) {
                anexo3.setCntProfesionaleId(new CntProfesional(profesional.getId()));
                anexo3.getCntProfesionaleId().setMaeTipoCodumentoId(profesional.getMaeTipoCodumentoId());
                anexo3.getCntProfesionaleId().setPrimerNombre(profesional.getPrimerNombre());
                anexo3.getCntProfesionaleId().setSegundoNombre(profesional.getSegundoNombre());
                anexo3.getCntProfesionaleId().setPrimerApellido(profesional.getPrimerApellido());
                anexo3.getCntProfesionaleId().setSegundoApellido(profesional.getSegundoApellido());
                anexo3.setNombreProfesional(profesional.getPrimerNombre());
                anexo3.setMaeTipoDumentoCodigo(profesional.getMaeTipoCodumentoId());
                anexo3.setDocumentoProfesional(profesional.getDocumento());
                // CntProfesionalPrestadores especialidad = consultarEspecialidaPorId(profesional.getId());
                MaEspecialidad especialidad = getEspecialidadRemoto().consultarPorCodigo(solicitud.getCodigoEspecialidadMedica());
                if (especialidad != null) {
                    anexo3.setObjetoEspecialidad(especialidad);
                }
            }
        }
        if (solicitud.getTecnologiaSolicitada() != null && !solicitud.getTecnologiaSolicitada().isEmpty()) {
            MaTecnologias tecnologia = consultarMaTecnologiaPorCodigoPropio(solicitud.getTecnologiaSolicitada().get(0).getCodigoTecnologia());
            if (tecnologia != null) {
                anexo3.setMaServicioSolicitadoId(tecnologia.getId());
                anexo3.setMaServicioSolicitadoCodigo(tecnologia.getCups());
                anexo3.setMaServicioSolicitadoValor(tecnologia.getCupsDescipcion());
                //TODO: Definir que deberia ir en el servicio habilitado
                anexo3.setMaServicioHabilitadoId(tecnologia.getId());
                anexo3.setMaServicioHabilitadoCodigo(tecnologia.getCups());
                anexo3.setMaServicioHabilitadoValor(tecnologia.getCupsDescipcion());
            }
        }
        if (solicitud.getTipoServiciosSolicitados() != null) {
            Maestro tipoServicio = getMaestroRemoto().consultarPorValorTipo(solicitud.getTipoServiciosSolicitados(), MaestroTipo.GN_TIPO_SERVICIO);
            if (servicioHabilitacion != null) {
                anexo3.setMaeTipoServicioId(tipoServicio.getId());
                anexo3.setMaeTipoServicioCodigo(String.valueOf(tipoServicio.getValor()));
                anexo3.setMaeTipoServicioValor(tipoServicio.getDescripcion());
            }
        }

        if (solicitud.getUbicacionPaciente() != null) {
            AuAnexo3 result = new AuAnexo3();
            //String valor = result.ubicacionValor(solicitud.getUbicacionPaciente());
            Maestro ubicacionPaciente = getMaestroRemoto().consultarPorValorTipo(solicitud.getUbicacionPaciente(), MaestroTipo.GN_UBICACION);
            //getMaestroRemoto
            if (ubicacionPaciente != null) {
                anexo3.setMaeUbicacionId(ubicacionPaciente.getId());
                anexo3.setMaeUbicacionCodigo(ubicacionPaciente.getValor());
                anexo3.setMaeUbicacionValor(ubicacionPaciente.getNombre());
            }
        }
        //acompañantes
        anexo3.setNombreAcompanante(solicitud.getNombreAcompaniante());
        anexo3.setTelefonoAcompanante(solicitud.getTelefonoAcompaniante());
        anexo3.setCelularAcompanente(solicitud.getCelularAcompaniante());

        if (servicioHabilitacion != null) {
            anexo3.setMaServicioHabilitadoId(servicioHabilitacion.getId());
            anexo3.setMaServicioHabilitadoCodigo("" + servicioHabilitacion.getCodigo());
            anexo3.setMaServicioHabilitadoValor(servicioHabilitacion.getNombre());
            anexo3.setMaServicioSolicitadoId(servicioHabilitacion.getId());
            anexo3.setMaServicioSolicitadoCodigo("" + servicioHabilitacion.getCodigo());
            anexo3.setMaServicioSolicitadoValor(servicioHabilitacion.getNombre());
        }

        if (solicitud.getPrioridadAtencion().equals("1")) {
            anexo3.setPrioridad(true);
        } else {
            anexo3.setPrioridad(false);
        }

        anexo3.setCargaMasiva(false);
        anexo3.setWebService(true);

        if (sede.getFechaFacturaElectronica() != null || sede.getGrupoRipsMinisterio() != null
                && sede.getGrupoRipsMinisterio().equals(AuConstantes.UNO)
                || sede.getGrupoRipsMinisterio().equals(AuConstantes.DOS)
                || sede.getGrupoRipsMinisterio().equals(AuConstantes.TRES)) {
            anexo3.setVersion(true);
            if (modalidad != null) {
                anexo3.setMaeModalidadTecnologiaId(modalidad.getId());
                anexo3.setMaeModalidadTecnologiaValor(modalidad.getNombre());
                anexo3.setMaeModalidadTecnologiaCodigo(modalidad.getValor());
            }
            if (finalidad != null) {
                anexo3.setMaeFinalidadTecnologiaId(finalidad.getId());
                anexo3.setMaeFinalidadTecnologiaValor(finalidad.getNombre());
                anexo3.setMaeFinalidadTecnologiaCodigo(finalidad.getValor());
            }
            if (solicitud.getDireccionAlternativa() != null) {
                anexo3.setDireccionAlternativa(solicitud.getDireccionAlternativa());
            }
        }

        anexo3.setUsuarioCrea(usuario);
        anexo3.setTerminalCrea("127.0.0.1");
        anexo3.setFechaHoraCrea(new Date());
        return anexo3;
    }

    private CntPrestadorSede consultarEmpresaPorCodigoHabilitacion(String codigoHabilitacion) throws Exception {
        CntPrestadorSede objRes = null;
        CntPrestadorSedes result = null;
        try {
            String strQuery = "FROM CntPrestadorSedes   m "
                    + "WHERE m.codigoHabilitacion ='" + codigoHabilitacion + "'";

            Query query = getEntityManager().createQuery(strQuery);
            result = (CntPrestadorSedes) query.getSingleResult();
            objRes = castPrestadorSedesNegocio(result);

        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private CntProfesionales consultarProfesionalPorId(int id) throws Exception {
        CntProfesionales objRes = null;
        try {
            String strQuery = "FROM CntProfesionales m "
                    + "WHERE m.id ='" + id + "'";
            objRes = (CntProfesionales) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private MaTecnologias consultarMaTecnologiaPorCodigoPropio(String codPropio) throws Exception {
        MaTecnologias objRes = null;
        try {
            String strQuery = "FROM MaTecnologias m "
                    + "WHERE m.codigoPropio ='" + codPropio + "'";
            objRes = (MaTecnologias) getEntityManager().createQuery(strQuery).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private MaMedicamentos consultarMaMedicamentosPorCum(String cum) throws Exception {
        MaMedicamentos objRes = null;
        try {
            String strQuery = "FROM MaMedicamentos m "
                    + "WHERE m.cum ='" + cum + "'";
            objRes = (MaMedicamentos) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private MaInsumos consultarMaInsumosPorCodigo(String codigo) throws Exception {
        MaInsumos objRes = null;
        try {
            String strQuery = "FROM MaInsumos m "
                    + "WHERE m.codigo ='" + codigo + "' "
                    + " AND m.automatico = FALSE "
                    + " AND m.activo = TRUE";
            objRes = (MaInsumos) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private MaPaquetes consultarMaPaquetesPorCodigo(String codigo) throws Exception {
        MaPaquetes objRes = null;
        try {
            String strQuery = "FROM MaPaquetes m "
                    + "WHERE m.codigo ='" + codigo + "'";
            objRes = (MaPaquetes) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private int insertarAuAnexos3(AuAnexos3 anexo3) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(anexo3).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private AuAnexo3Diagnostico castAuAnexo3Diagnosticos(String diagnosticoCIE10, Maestro maeTipo, boolean principal, String usuarioCrea) throws java.lang.Exception {
        AuAnexo3Diagnostico anexo3Diagnostico = new AuAnexo3Diagnostico();
        MaDiagnosticos diagnostico = consultarDiagnosticoPorCodigo(diagnosticoCIE10);
        if (diagnostico != null) {
            anexo3Diagnostico.setMaDiagnosticosId(diagnostico.getId());
            anexo3Diagnostico.setMaDiagnosticosCodigo(diagnostico.getCodigo());
            anexo3Diagnostico.setMaDiagnosticosValor(diagnostico.getNombre());
            anexo3Diagnostico.setPrincipal(principal);
            anexo3Diagnostico.setMaeTipoDiagnosticoId(maeTipo.getId());
            anexo3Diagnostico.setMaeTipoDiagnosticoCodigo(maeTipo.getValor());
            anexo3Diagnostico.setMaeTipoDiagnosticoValor(maeTipo.getNombre());
            anexo3Diagnostico.setUsuarioCrea(usuarioCrea);
            anexo3Diagnostico.setTerminalCrea("127.0.0.1");
            anexo3Diagnostico.setFechaHoraCrea(new Date());
        }
        return anexo3Diagnostico;

    }

    private AuAnexo3Item castAuAnexo3Items(AuAnexo3 anexo3, TecnologiaSolicitadaAnexo3DTO tecnologia, String usuarioCrea) throws java.lang.Exception {

        AuAnexo3Item anexo3Items = new AuAnexo3Item();
        anexo3Items.setTipoTecnologia(Integer.parseInt(tecnologia.getTipoTecnologia()));
        anexo3Items.setCantidadSolicitada(Integer.parseInt(tecnologia.getCantidadTecnologia()));
        anexo3Items.setUsuarioCrea(usuarioCrea);
        anexo3Items.setTerminalCrea("127.0.0.1");
        anexo3Items.setFechaHoraCrea(new Date());
        switch (tecnologia.getTipoTecnologia()) {
            case "1":
                MaTecnologias maTecnologia = consultarMaTecnologiaPorCodigoPropio(tecnologia.getCodigoTecnologia());
                if (maTecnologia != null) {
                    anexo3Items.setMaTecnologiaId(maTecnologia.getId());
                    anexo3Items.setMaTecnologiaCodigo(maTecnologia.getCodigoPropio());
                    anexo3Items.setMaTecnologiaValor(maTecnologia.getPropioDescripcion());
                } else {
                    anexo3Items = null;
                }
                break;
            case "2":
                MaMedicamentos maMedicamento = consultarMaMedicamentosPorCum(tecnologia.getCodigoTecnologia());
                if (maMedicamento != null) {
                    anexo3Items.setMaTecnologiaId(maMedicamento.getId());
                    anexo3Items.setMaTecnologiaCodigo(maMedicamento.getCum());
                    anexo3Items.setMaTecnologiaValor(maMedicamento.getDescripcionInvima());

                    MaDiagnostico diagnostico = getMaDiagnosticoRemoto().consultarPorCodigo(tecnologia.getDiagnosticoTecnologia());
                    if (diagnostico != null) {
                        anexo3Items.setMaDiagnosticoId(diagnostico.getId());
                        anexo3Items.setMaDiagnosticoCodigo(diagnostico.getCodigo());
                        anexo3Items.setMaDiagnosticoValor(diagnostico.getNombre());
                    }

                    GnMaestros causaExtMed = consultarMaestroPorValorTipo(tecnologia.getCausaExternaMedicamento(), MaestroTipo.AU_A3_CAUSA_EXTERNA);
                    if (causaExtMed != null) {
                        anexo3Items.setMaeCausaExternaId(causaExtMed.getId());
                        anexo3Items.setMaeCausaExternaCodigo(causaExtMed.getValor());
                        anexo3Items.setMaeCausaExternaValor(causaExtMed.getNombre());
                    }

                    GnMaestros finalidadMed = consultarMaestroPorValorTipo(tecnologia.getFinalidadMedicamento(), MaestroTipo.AU_DIAGNOSTICO_FINALIDAD);
                    if (finalidadMed != null) {
                        anexo3Items.setMaeFinalidadId(finalidadMed.getId());
                        anexo3Items.setMaeFinalidadCodigo(finalidadMed.getValor());
                        anexo3Items.setMaeFinalidadValor(finalidadMed.getNombre());
                    }

                    GnMaestros tipoCatrastroficoMed = consultarMaestroPorValorTipo(tecnologia.getTipoCatastroficoMedicamento(), MaestroTipo.GN_PATOLOGIA_CATASTROFICA);
                    if (tipoCatrastroficoMed != null) {
                        anexo3Items.setMaeTipoCatastroficoId(tipoCatrastroficoMed.getId());
                        anexo3Items.setMaeTipoCatastroficoCodigo(tipoCatrastroficoMed.getValor());
                        anexo3Items.setMaeTipoCatastroficoValor(tipoCatrastroficoMed.getNombre());
                    }
                    Date fecha = dateFormat.parse(tecnologia.getFechaFormulaMedicamento());
                    if (fecha != null) {
                        anexo3Items.setFechaFormula(fecha);
                    }
                    if (tecnologia.getDosisMedicamento() != null) {
                        anexo3Items.setDosis(new BigDecimal(tecnologia.getDosisMedicamento()));
                    }
                    if (tecnologia.getFrecuenciaMedicamento() != null) {
                        anexo3Items.setFrecuencia((tecnologia.getDosisMedicamento().toString()));
                    }

                    GnMaestros maeViaAdministracion = consultarMaestroPorValorTipo(tecnologia.getViaAdministracion(), MaestroTipo.AU_MEDICAMENTO_VIA_ADMINISTRACION);
                    if (causaExtMed != null) {
                        anexo3Items.setMaeViaAdministracionId(maeViaAdministracion.getId());
                        anexo3Items.setMaeViaAdministracionCodigo(maeViaAdministracion.getValor());
                        anexo3Items.setMaeViaAdministracionValor(maeViaAdministracion.getNombre());
                    }

                    if (tecnologia.getDuracionTratamiento() != null) {
                        anexo3Items.setDuracion(tecnologia.getDuracionTratamiento());
                    }
                } else {
                    anexo3Items = null;
                }

                break;
            case "3":
                MaInsumos maInsumos = consultarMaInsumosPorCodigo(tecnologia.getCodigoTecnologia());
                if (maInsumos != null) {
                    anexo3Items.setMaTecnologiaId(maInsumos.getId());
                    anexo3Items.setMaTecnologiaCodigo(maInsumos.getCodigo());
                    anexo3Items.setMaTecnologiaValor(maInsumos.getDescripcion());
                } else {
                    anexo3Items = null;
                }
                break;
            case "4":
                MaPaquetes maPaquetes = consultarMaPaquetesPorCodigo(tecnologia.getCodigoTecnologia());
                if (maPaquetes != null) {
                    anexo3Items.setMaTecnologiaId(maPaquetes.getId());
                    anexo3Items.setMaTecnologiaCodigo(maPaquetes.getCodigo());
                    anexo3Items.setMaTecnologiaValor(maPaquetes.getNombre());
                } else {
                    anexo3Items = null;
                }
                break;
            default:
                anexo3Items = null;
        }

        return anexo3Items;

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

    private int insertarAuAnexo4Impresiones(AuAnexo4Impresiones impresionA4) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(impresionA4).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private List<AuAnexo3> consultarAnexo3PorId(String codigo) throws Exception {
        List<AuAnexo3> listResult = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3 p "
                    + "WHERE P.AuAnexo3 ='" + codigo + "'";
            listResult = getEntityManager().createQuery(strQuery)
                    .getResultList();

        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    public static Empresa castEmpresaEntidadNegocio(GnEmpresas per) {
        Empresa obj = new Empresa();
        obj.setId(per.getId());
        obj.setNit(per.getNit());
        obj.setRazonSocial(per.getRazonSocial());
        obj.setNombreComercial(per.getNombreComercial());
        obj.setActiva(per.getActiva());
        obj.setDescripcion(per.getDescripcion());
        obj.setAdministradora(per.getAdministradora());
        obj.setCiudad(new Ubicacion(per.getGnUbicacionesId().getId()));
        obj.setReceptorUsuario(per.getReceptorUsuario());
        obj.setReceptorContrasena(per.getReceptorContrasena());
        obj.setCodigoHabilitacion(per.getCodigoHabilitacion());

        return obj;
    }

    public static AsegAfiliado castAfiliadoAnexo3(AsegAfiliados per) {
        AsegAfiliado afiliado = new AsegAfiliado();
        afiliado.setId(per.getId());
        afiliado.setIdAfiliado(per.getIdAfiliado());
        afiliado.setMaeTipoDocumento(per.getMaeTipoDocumentoId());
        afiliado.setNumeroDocumento(per.getNumeroDocumento());
        afiliado.setPrimerNombre(per.getPrimerNombre());
        afiliado.setSegundoNombre(per.getSegundoNombre());
        afiliado.setPrimerApellido(per.getPrimerApellido());
        afiliado.setSegundoApellido(per.getSegundoApellido());
        afiliado.setFechaNacimiento(per.getFechaNacimiento());
        afiliado.setNivelSisben(per.getNivelSisben());
        afiliado.setTipoBeneficiario(per.getTipoBeneficiario());
        afiliado.setCategoriaIbc(per.getCategoriaIbc());
        afiliado.setTelefonoFijo(per.getTelefonoFijo());

        return afiliado;
    }

    public static CntPrestador castPrestador(CntPrestadores pres) {
        CntPrestador prestador = new CntPrestador();
        prestador.setId(pres.getId());
        prestador.setCodigoMinSalud(pres.getCodigoMinSalud());
        prestador.setRazonSocial(pres.getRazonSocial());
        return prestador;
    }

    public Empresa consultarPorPrestador(int idPrestador) throws Exception {
        Empresa objRes = null;
        GnEmpresas result = null;
        try {
            String strQuery = "FROM GnEmpresas g "
                    + "WHERE g.activa = 1 AND g.cntPrestadoresId.id = " + idPrestador;

            Query query = getEntityManager().createQuery(strQuery);
            result = (GnEmpresas) query.getSingleResult();
            objRes = castEmpresaEntidadNegocio(result);

        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private CntPrestadorSede castPrestadorSedesNegocio(CntPrestadorSedes cntPrestadorSedes) throws Exception {
        CntPrestadorSede neg = new CntPrestadorSede();
        neg.setId(cntPrestadorSedes.getId());
        neg.setNombreSede(cntPrestadorSedes.getNombre());
        neg.setCodigoHabilitacionSede(cntPrestadorSedes.getCodigoHabilitacion());
        neg.setCodigoPrestador(cntPrestadorSedes.getCodigoPrestador());
        neg.setUbicacionId(cntPrestadorSedes.getUbicacionId());
        if (cntPrestadorSedes.getId() != null) {
            CntPrestador prestador = consultarPrestador(cntPrestadorSedes.getCodigoPrestador());
            neg.setCntPrestador(prestador);
        }

        return neg;
    }

    private CntPrestador consultarPrestador(String codigoPrestador) throws Exception {
        CntPrestador objRes = null;
        CntPrestadores result = null;
        try {
            String strQuery = "FROM CntPrestadores   m "
                    + "WHERE m.codigoMinSalud ='" + codigoPrestador + "'";
            Query query = getEntityManager().createQuery(strQuery);
            result = (CntPrestadores) query.getSingleResult();
            objRes = castPrestador(result);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private MaServiciosHabilitacion consultarMaServiciosHabilitacion(int id) throws Exception {
        MaServiciosHabilitacion objRes = null;
        try {
            String strQuery = "FROM MaServiciosHabilitacion m "
                    + "WHERE m.id ='" + id + "'";
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

    private CntProfesionalPrestadores consultarEspecialidaPorId(int id) throws Exception {
        CntProfesionalPrestadores objRes = null;
        try {
            String strQuery = "FROM CntProfesionalPrestadores m "
                    + "WHERE m.id ='" + id + "'";
            objRes = (CntProfesionalPrestadores) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    public Empresa consultarPorEmpresa(int cntPrestadorId) throws Exception {
        Empresa objRes = null;
        GnEmpresas result = null;
        try {
            String strQuery = "FROM GnEmpresas g "
                    + "WHERE g.activa = 1 AND g.cntPrestadoresId.id = " + cntPrestadorId;

            Query query = getEntityManager().createQuery(strQuery);
            result = (GnEmpresas) query.getSingleResult();
            objRes = castEmpresaEntidadNegocio(result);

        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public CntPrestadorSede consultarPrestadorPorId(int id) throws java.lang.Exception {

        CntPrestadorSede objRes = null;
        CntPrestadorSedes result = null;

        try {
            String strQuery = "FROM CntPrestadorSedes p "
                    + "WHERE  p.id = '" + id + "' ";

            Query query = getEntityManager().createQuery(strQuery);
            result = (CntPrestadorSedes) query.getSingleResult();
            objRes = castEmpresaPre(result);

        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    public static CntPrestadorSede castEmpresaPre(CntPrestadorSedes per) {
        CntPrestadorSede obj = new CntPrestadorSede();
        obj.setId(per.getId());
        obj.setCapitacion(per.getCapitacion());
        obj.setNombreSede(per.getNombre());
        obj.setCodigoHabilitacionSede(per.getCodigoHabilitacion());

        return obj;
    }

    public ValidaRespuestaDTO validarCodigoTecnologia(
            String tipoTecnologia,
            String codigoTecnologia,
            String tipoDocumento,
            String numeroDocumento) {
        ValidaRespuestaDTO neg;
        FuncionMySqlServicio funcion = new FuncionMySqlServicio();
        try {
            String strQuery = "SELECT fn_au_cod_tecnologia ("
                    + "'" + tipoTecnologia + "',"
                    + "'" + codigoTecnologia + "',"
                    + "'" + tipoDocumento + "',"
                    + "'" + numeroDocumento + "'"
                    + ")";
            neg = funcion.ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    public ValidaRespuestaGrupoAsignado validarGrupoUsuario(boolean tutela, String ambito, boolean programa, int idPrograma, int idPrestador, int tecnologiaTipo, String tecnologiaId) {
        ValidaRespuestaGrupoAsignado neg;
        FuncionMySqlServicio funcion = new FuncionMySqlServicio();
        try {
            String strQuery = "SELECT fn_au_asignar_grupo ("
                    + "" + tutela + ","
                    + "'" + ambito + "',"
                    + "" + programa + ","
                    + "" + idPrograma + ","
                    + "" + idPrestador + ","
                    + "" + tecnologiaTipo + ","
                    + "" + tecnologiaId + ")";

            neg = funcion.ejecutarAsignacionGrupo(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaGrupoAsignado();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    public ValidaRespuestaA4AutomaticoDTO validarAprobacionAutomatica(int idAfiliodo, int tipoTecnologia, int idTecnologia, int idSede, int idAnexo3) {
        ValidaRespuestaA4AutomaticoDTO neg;
        FuncionMySqlServicio funcion = new FuncionMySqlServicio();
        try {
            String strQuery = "SELECT fn_au_anexo4_automatico ("
                    + "" + idAfiliodo + ","
                    + "" + tipoTecnologia + ","
                    + "" + idTecnologia + ","
                    + "" + idSede + ","
                    + "" + idAnexo3 + ""
                    + ")";
            neg = funcion.ejecutarAprobacionAutomatica(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaA4AutomaticoDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    private void guardarAdjunto(AuAnexo3Adjunto adjunto) {
        try {
            boolean error = false;
            String ruta = PropApl.getInstance().get(PropApl.AU_A3_ADJUNTOS);
            if (ruta == null || ruta.isEmpty()) {
                error = true;
            }
            if (error) {
                return;
            }
            //Generar nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            String tipoDocumento = adjunto.getMaeTipoArchivoCodigo();
            nombreArchivo.append(tipoDocumento)
                    .append("_")
                    .append(sdf.format(new Date()));
            nombreArchivo = new StringBuilder(reemplazarCaracteresEspeciales(nombreArchivo.toString()));
            adjunto.setArchivo(nombreArchivo.append(adjunto.getExtension()).toString());
            Maestro maeTipoAdjunto = getMaestroRemoto().consultar(adjunto.getMaeTipoArchivoId());
            adjunto.setMaeTipoArchivoCodigo(maeTipoAdjunto.getValor());
            adjunto.setMaeTipoArchivoId(maeTipoAdjunto.getId());
            adjunto.setMaeTipoArchivoValor(maeTipoAdjunto.getNombre());
            //adjunto.setNombreArchivo(nombreArchivo.toString());
            adjunto.setRuta(ruta);
            File archivo = new File(ruta, adjunto.getArchivo());
            OutputStream destino = new FileOutputStream(archivo);
            IOUtils.copy(adjunto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(adjunto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            adjunto.setAdjuntoStream(null);
            adjunto.setId(getAuAnexo3AdjuntoRemoto().insertar(adjunto));
        } catch (Exception e) {
            return;
        }
    }

    public static String reemplazarCaracteresEspeciales(String palabra) {
        String[] caracteresMalos = {" ", "ñ", "|", "à", "á", "À", "Á", "è", "é", "È", "É", "ì", "í", "Ì", "Í", "ò", "ó", "Ò", "Ó", "ù", "ú", "Ù", "Ú", "\b", "/", ":", "<", "*", "?", ">", "@"};
        String[] caracteresBuenos = {"-", "n", "", "a", "a", "A", "A", "e", "e", "E", "E", "i", "i", "I", "I", "o", "o", "O", "O", "u", "u", "U", "U", "", "", "", "", "", "", "", "a"};
        for (String letraMala : caracteresMalos) {
            if (palabra.contains(letraMala)) {
                palabra = palabra.replace(letraMala, caracteresBuenos[Arrays.asList(caracteresMalos).indexOf(letraMala)]);
            }
        }
        return palabra;
    }

    private AfiliadoSolicitudAnexo3DTO castearAfiliado(AsegAfiliado afi) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        AfiliadoSolicitudAnexo3DTO afiliado = new AfiliadoSolicitudAnexo3DTO();
        afiliado.setTipoDocumento(afi.getMaeTipoDocumentoValor());
        afiliado.setDocumento(afi.getNumeroDocumento());
        afiliado.setPrimerNombre(afi.getPrimerNombre());
        afiliado.setSegundoNombre(afi.getSegundoNombre());
        afiliado.setPrimerApellido(afi.getPrimerApellido());
        afiliado.setSegundoApellido(afi.getSegundoApellido());
        afiliado.setFechaNacimiento(format.format(afi.getFechaNacimiento()));
        afiliado.setNivelSisben(afi.getNivelSisben());
        afiliado.setNivelIBC(afi.getCategoriaIbc());
        afiliado.setTelResAfil(afi.getTelefonoMovil());
        try {
            Ubicacion departamento = getUbicacionRemoto().consultar(afi.getResidenciaUbicacion().getUbicacionPadre().getId());
            if (departamento != null) {
                afiliado.setCodDepartAfil(departamento.getPrefijo());
                afiliado.setDeparAfil(departamento.getNombre());
            }
            Ubicacion ciudad = getUbicacionRemoto().consultar(afi.getResidenciaUbicacion().getId());
            if (ciudad != null) {
                afiliado.setCodCiudadAfil(ciudad.getPrefijo());
                afiliado.setCiudadAfil(ciudad.getNombre());
            }
        } catch (Exception e) {
        }
        afiliado.setTipoBeneficiario(afi.getMaeTipoAfiliadoValor());
        afiliado.setRegimen(afi.getMaeRegimenValor());
        return afiliado;
    }

    private SolicitudSolicitudAnexo3DTO castAnexo3(AuAnexo3 anexo3) {
        SolicitudSolicitudAnexo3DTO anexo3DTO = new SolicitudSolicitudAnexo3DTO();
        anexo3DTO.setNumeroSolicitud("" + anexo3.getId());
        anexo3DTO.setCodigoHabilitacionSolicita(anexo3.getCntPrestadorSedeId().getCodigoHabilitacionSede());
        anexo3DTO.setPrestadorSede(anexo3.getCntPrestadorSedeId().getNombreSede());
        anexo3DTO.setEstado(anexo3.getEstadoStr());
        anexo3DTO.setAmbito(anexo3.getMaeAmbitoAtencionCodigo());
        anexo3DTO.setServicioSolicitado(anexo3.getMaServicioSolicitadoCodigo());
        anexo3DTO.setServicioHabilitado(anexo3.getMaServicioHabilitadoCodigo());
        anexo3DTO.setProgramaEspecial(anexo3.getPeProgramaEspecialCodigo());
        anexo3DTO.setOrigenAtencion(anexo3.getMaeOrigenAtencionCodigo());
        anexo3DTO.setTipoServicio(anexo3.getMaeTipoServicioCodigo());
        anexo3DTO.setUbicacion(anexo3.getMaeUbicacionCodigo());
        anexo3DTO.setJustificacionClinica(anexo3.getJustificacionClinica());
        anexo3DTO.setResponsable("");
        anexo3DTO.setServicios(new ArrayList<>());
        anexo3.getAuAnexo3ItemsList().stream().map(item -> {
            Anexo3ItemDTO itemDTO = new Anexo3ItemDTO();
            itemDTO.setCodigoTecnologia(item.getMaTecnologiaCodigo());
            itemDTO.setCodigoDiagnostico(item.getMaDiagnosticoCodigo());
            itemDTO.setEstado(item.getEstadoStr());
            itemDTO.setTipoTecnologia(item.getTipoTecnologiaStr());
            itemDTO.setDosis("" + item.getDosis());
            itemDTO.setResponsable(item.getGnUsuarioId() != null ? item.getGnUsuarioId().getNombre() : "");
            return itemDTO;
        }).forEachOrdered(itemDTO -> {
            anexo3DTO.getServicios().add(itemDTO);
        });
        return anexo3DTO;
    }

    private Anexo4DTO castearAnexo4(AuAnexo4 anexo4) {
        Anexo4DTO anexo4DTO = new Anexo4DTO();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        anexo4DTO.setNumeroAutorizacion("" + anexo4.getId());
        anexo4DTO.setFechaAutorizacion(format.format(anexo4.getFechaAutorizacion()));
        anexo4DTO.setEstado(anexo4.getEstadoStr());
        anexo4DTO.setCodigoHabilitacion(anexo4.getCntPrestadorSedeId().getCodigoHabilitacionSede());
        anexo4DTO.setNumeroContrato("" + anexo4.getCntContratoId().getId());
        anexo4DTO.setRegimen(anexo4.getMaeRegimenCodigo());
        anexo4DTO.setServicioHabilitado(anexo4.getMaServicioHabilitadoCodigo());
        anexo4DTO.setCodigoEspecialidad(anexo4.getMaEspecialidadCodigo());
        anexo4DTO.setDescripcionEspecialidad(anexo4.getMaeEstadoMotivoValor());
        anexo4DTO.setServicios(new ArrayList<>());
        anexo4.getAuAnexo4ItemsList().stream().map(item -> {
            Anexo4ItemDTO itemDTO = new Anexo4ItemDTO();
            itemDTO.setCodigoTecnologia(item.getMaTecnologiaCodigo());
            itemDTO.setCantidad("" + item.getCantidadAutorizada());
            itemDTO.setCosto(item.getCostoServicio().toString());
            itemDTO.setEntregado(item.isEntregada() ? "Sí" : "No");
            return itemDTO;
        }).forEachOrdered(itemDTO -> {
            anexo4DTO.getServicios().add(itemDTO);
        });
        return anexo4DTO;
    }

    @Override
    public RespuestaAutorizacionPrestador2DTO consultarAutorizacionPrestador(SolicitudAutorizacionPrestadorDTO solicitud) throws java.lang.Exception {
        RespuestaAutorizacionPrestador2DTO afiliado = new RespuestaAutorizacionPrestador2DTO();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("FROM AuAnexos4 p ")
                    .append("WHERE p.asegAfiliadosId.fechaNacimiento ='")
                    .append(solicitud.getFechaNacimiento())
                    .append("'").append(" AND p.asegAfiliadosId.numeroDocumento ='")
                    .append(solicitud.getNumeroDocumento()).append("'")
                    .append(" AND p.asegAfiliadosId.maeTipoDocumentoCodigo ='")
                    .append(solicitud.getTipoDocumento()).append("'")
                    .append(" AND p.cntPrestadorSedesId.codigoHabilitacion ='")
                    .append(solicitud.getCodHabilitacionPrestador()).append("'")
                    .append(" ORDER BY p.id DESC");

            List<AuAnexos4> list = getEntityManager()
                    .createQuery(strQuery.toString())
                    .getResultList();
            if (!list.isEmpty()) {
                boolean primerReg = true;
                RespuestaAutorizacionPrestador3DTO autorizacion
                        = new RespuestaAutorizacionPrestador3DTO();
                for (AuAnexos4 regs : list) {
                    Date fechaActual = new Date();
                    if (regs.getFechaFin().after(fechaActual)) {
                        if (primerReg) {
                            afiliado.setFechaNacimiento(regs.getAsegAfiliadosId().getFechaNacimiento().toString());
                            afiliado.setPrimerNombre(regs.getAsegAfiliadosId().getPrimerNombre());
                            afiliado.setSegundoNombre(regs.getAsegAfiliadosId().getSegundoNombre());
                            afiliado.setPrimerApellido(regs.getAsegAfiliadosId().getPrimerApellido());
                            afiliado.setSegundoApellido(regs.getAsegAfiliadosId().getSegundoApellido());
                            afiliado.setAutorizaciones(new ArrayList());
                            primerReg = false;
                        }
                        autorizacion.setNua(regs.getId().toString());
                        if (regs.getFechaAutorizacion() != null) {
                            autorizacion.setFechaAutorizacion(regs.getFechaAutorizacion());
                        }
                        if (regs.getEstado() == 2
                                || regs.getEstado() == 5
                                || regs.getEstado() == 7) {
                            autorizacion.setFechaAnulacion(regs.getFechaHoraModifica());
                        }

                        switch (regs.getEstado()) {
                            case 0:
                                autorizacion
                                        .setEstadoAutorizacion(ESTADO_0);
                                break;
                            case 1:
                                autorizacion
                                        .setEstadoAutorizacion(ESTADO_1);
                                break;
                            case 2:
                                autorizacion
                                        .setEstadoAutorizacion(ESTADO_2);
                                break;
                            case 3:
                                autorizacion
                                        .setEstadoAutorizacion(ESTADO_3);
                                break;
                            case 4:
                                autorizacion
                                        .setEstadoAutorizacion(ESTADO_4);
                                break;
                            case 5:
                                autorizacion
                                        .setEstadoAutorizacion(ESTADO_5);
                                break;
                            case 6:
                                autorizacion
                                        .setEstadoAutorizacion(ESTADO_6);
                                break;
                            case 7:
                                autorizacion
                                        .setEstadoAutorizacion(ESTADO_7);
                                break;
                        }

                        if (regs.getAuAnexos3Id() != null) {
                            autorizacion.setSolicitud(regs.getAuAnexos3Id().getId().toString());
                        } else {
                            autorizacion.setSolicitud("no se asocia");
                        }

                        RespuestaAutorizacionPrestador4DTO itemA
                                = new RespuestaAutorizacionPrestador4DTO();
                        autorizacion.setItems(new ArrayList());
                        for (AuAnexo4Items item : regs.getAuAnexo4ItemsList()) {
                            itemA.setCodigoTecnologia(item.getMaTecnologiaCodigo());
                            itemA.setNombreTecnologia(item.getMaTecnologiaValor());
                            if (regs.getCntPrestadorSedesId() != null) {
                                itemA.setNombreIPSAutorizada(regs.getCntPrestadorSedesId().getNombre());
                            }
                            if (item.getAuAnexo4EntregasList() != null
                                    && item.getAuAnexo4EntregasList().size() > 0) {
                                itemA.setCantidadAutorizada(Integer.toString(item.getAuAnexo4EntregasList().get(0).getCantidadAutorizada()));
                                itemA.setCantidadEntregada(Integer.toString(item.getAuAnexo4EntregasList().get(0).getCantidadEntregada()));
                                itemA.setCantidadPendiente(Integer.toString(item.getAuAnexo4EntregasList().get(0).getCantidadPendiente()));
                            } else {
                                itemA.setCantidadAutorizada(Integer.toString(item.getCantidadAutorizada()));
                                itemA.setCantidadEntregada(Integer.toString(0));
                                itemA.setCantidadPendiente(Integer.toString(0));
                            }
                            autorizacion.getItems().add(itemA);
                            itemA = new RespuestaAutorizacionPrestador4DTO();
                        }
                        afiliado.getAutorizaciones().add(autorizacion);
                        autorizacion = new RespuestaAutorizacionPrestador3DTO();
                    }

                }
                if (afiliado.getPrimerNombre() == null) {
                    afiliado = null;
                }
            } else {
                afiliado = null;
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

}
