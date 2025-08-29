/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.utilidades;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Diagnostico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuItemBitacora;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazo;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazoItem;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteItemDireccionado;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteNegacionServicio;
import com.saviasaludeps.savia.dominio.autorizacion.ReportePreAutorizacion;
import com.saviasaludeps.savia.dominio.configuracionSistema.CsTopeCopago;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorContacto;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Diagnostico;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ItemRemoto;
import com.saviasaludeps.savia.negocio.configuracionSistema.CsTopeCopagoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorContactoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2Remoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Stiven Giraldo
 */
public class AuReporte {

    private UbicacionRemoto getUbicacionRemoto() throws Exception {
        return (UbicacionRemoto) RemotoEJB.getEJBRemoto("UbicacionServicio", UbicacionRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AuAnexo4ItemRemoto getAuAnexo4ItemRemoto() throws Exception {
        return (AuAnexo4ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo4ItemServicio", AuAnexo4ItemRemoto.class.getName());
    }

    private AuAnexo3Remoto getAuAnexo3Remoto() throws Exception {
        return (AuAnexo3Remoto) RemotoEJB.getEJBRemoto("AuAnexo3Servicio", AuAnexo3Remoto.class.getName());
    }

    private AuAnexo2Remoto getAuAnexo2Remoto() throws Exception {
        return (AuAnexo2Remoto) RemotoEJB.getEJBRemoto("AuAnexo2Servicio", AuAnexo2Remoto.class.getName());
    }

    private CntPrestadorContactoRemoto getCntPrestadorContactoRemoto() throws Exception {
        return (CntPrestadorContactoRemoto) RemotoEJB.getEJBRemoto("CntPrestadorContactoServicio", CntPrestadorContactoRemoto.class.getName());
    }

    private CsTopeCopagoRemoto getCsTopeCopagoRemoto() throws Exception {
        return (CsTopeCopagoRemoto) RemotoEJB.getEJBRemoto("CsTopeCopagoServicio", CsTopeCopagoRemoto.class.getName());
    }

    private HashMap<Integer, Ubicacion> hashUbicacionesActivas;

    public AuReporte() {
    }

    public AuReporte(HashMap<Integer, Ubicacion> hashUbicacionesActivas) {

        this.hashUbicacionesActivas = hashUbicacionesActivas;

    }

    /**
     * función para realizar la impresión del anexo4, teniendo en cuenta que no
     * se tienen configuradas en el objeto las rutas y el nombre del anexo.
     *
     * @param anexo4
     * @return
     */
    public StreamedContent generarReporteAnexo4Imprimir(AuAnexo4 anexo4) {
        StreamedContent streamContent;
        try {
            List<ReporteAnexo4> listaReportes = castearAuAnexo4ReporteAnexo4(anexo4);
            if (!listaReportes.isEmpty()) {
                InputStream is = getClass().getResourceAsStream("/reportes/Anexo4.jasper");
                if(anexo4.isVersion() == AuConstantes.VERSION_1){
                    is = getClass().getResourceAsStream("/reportes/Anexo4res2335.jasper");
                }
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReportes);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name("ReporteAnexo4.pdf").build();
            } else {
                streamContent = null;
            }
        } catch (JRException ex) {
            streamContent = null;
        }
        return streamContent;
    }

    public String generarReporteAnexo4(String ruta, AuAnexo4 anexo4) {
        String mensaje = "";
        try {
            String nombre = anexo4.getArchivo();
            List<ReporteAnexo4> listaReportes = castearAuAnexo4ReporteAnexo4(anexo4);
            if (!listaReportes.isEmpty()) {
                //jasper estan ubicado en el artefacto de savia-solicitud
                InputStream is = getClass().getResourceAsStream("/reportes/Anexo4.jasper");;
                if(anexo4.isVersion() == AuConstantes.VERSION_1){
                    is = getClass().getResourceAsStream("/reportes/Anexo4res2335.jasper");
                }
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
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    public String generarReportePreAutorizacionAnexo4(String ruta, AuAnexo4 anexo4) {
        String mensaje = "";
        try {
            String nombre = anexo4.getArchivo();
            List<ReportePreAutorizacion> listaReportes = castearAuAnexo4ReportePreAutorizacion(anexo4);
            if (!listaReportes.isEmpty()) {
                InputStream is = getClass().getResourceAsStream("/reportes/PreAutorizacion.jasper");
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
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    public List<ReporteAnexo4> castearAuAnexo4ReporteAnexo4(AuAnexo4 anexo4) {
        List<ReporteAnexo4> listaReportes = new ArrayList();
        ReporteAnexo4 reporte = new ReporteAnexo4();
        DateFormat dateFormatAgno = new SimpleDateFormat("yyyy");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat horaFormat = new SimpleDateFormat("HH:mm");
        try {
            if (anexo4.getAuAnexo4ItemsList() == null) {
                anexo4.setAuAnexo4ItemsList(getAuAnexo4ItemRemoto().consultarListaByIdAnexo4(anexo4.getId()));
            }

            Date fechaOrigen = anexo4.getFechaHoraCrea();
            if (anexo4.getAuAnexo3Id() != null && anexo4.getAuAnexo3Id().getId() != null) {
                if (anexo4.getAuAnexo3Id().getFechaHoraCrea() != null) {
                    fechaOrigen = anexo4.getAuAnexo3Id().getFechaHoraCrea();
                } else {
                    anexo4.setAuAnexo3Id(getAuAnexo3Remoto().consultar(anexo4.getAuAnexo3Id().getId()));
                    if (anexo4.getAuAnexo3Id().getFechaHoraCrea() != null) {
                        fechaOrigen = anexo4.getAuAnexo3Id().getFechaHoraCrea();
                    }
                }

            }
            if (anexo4.getAuAnexo2Id() != null && anexo4.getAuAnexo2Id().getId() != null) {
                if (anexo4.getAuAnexo2Id().getFechaHoraCrea() != null) {
                    fechaOrigen = anexo4.getAuAnexo2Id().getFechaHoraCrea();
                } else {
                    anexo4.setAuAnexo2Id(getAuAnexo2Remoto().consultar(anexo4.getAuAnexo2Id().getId()));
                    if (anexo4.getAuAnexo2Id().getFechaHoraCrea() != null) {
                        fechaOrigen = anexo4.getAuAnexo2Id().getFechaHoraCrea();
                    }
                }
            }
           
            if (!anexo4.getAuAnexo4ItemsList().isEmpty()) {
                for (AuAnexo4Item item : anexo4.getAuAnexo4ItemsList()) {
                    reporte = new ReporteAnexo4();
                    //Titulo
                    reporte.setStrNumeroAutorizacion(anexo4.getId().toString());
                    reporte.setDateFechaAutorizacion(anexo4.getFechaAutorizacion());
                    //Entidad Responsable
                    reporte.setStrEntidadResponsable(anexo4.getEntidadPago());
                    reporte.setStrCodigoEntidad(anexo4.getCodigoEntidadPago());
                    //Prestador
                    reporte.setStrNombrePrestador(anexo4.getPrestadorNombre());
                    reporte.setStrTipoDocPrestador(anexo4.getPrestadorTipoDocumento());
                    reporte.setStrNumDocPrestador(anexo4.getPrestadorNumeroDocumento());
                    reporte.setStrCodigoPrestador(anexo4.getPrestadorCodigoHabilitacion());
                    anexo4 = consultarContactosPrestador(anexo4);
                    reporte.setStrCorreoPrestador(anexo4.getCorreoElectronicoPrestador());
                    reporte.setStrTelefono1Prestador(anexo4.getTelefono1Prestador());
                    reporte.setStrTelefono2Prestador(anexo4.getTelefono2Prestador());
                    reporte.setStrDireccionPrestador(anexo4.getPrestadorDireccion());
                    reporte.setStrDepartamentoPrestador(anexo4.getPrestadorDepartamento());
                    reporte.setStrMunicipioPrestador(anexo4.getPrestadorMunicipio());
                    reporte.setStrTipoTelefono1Prestador(anexo4.getTipoTelefono1Prestador());
                    reporte.setStrTipoTelefono2Prestador(anexo4.getTipoTelefono2Prestador());
                    //Afiliado
                    reporte.setStrPrimerApellidoPaciente(anexo4.getAfiliadoPrimerApellido());
                    reporte.setStrSegundoApellidoPaciente(anexo4.getAfiliadoSegundoApellido());
                    reporte.setStrPrimerNombrePaciente(anexo4.getAfiliadoPrimerNombre());
                    reporte.setStrSegundoNombrePaciente(anexo4.getAfiliadoSegundoNombre());
                    reporte.setStrTipoDocPaciente(anexo4.getAfiliadoTipoDocumento());
                    reporte.setStrNumDocPaciente(anexo4.getAfiliadoNumeroDocumento());
                    reporte.setStrFechaNacimientoPaciente(dateFormat.format(anexo4.getAfiliadoFechaNacimiento()));
                    reporte.setStrHoraNacimientoPaciente(horaFormat.format(anexo4.getAfiliadoFechaNacimiento()));
                    reporte.setStrDireccionPaciente(anexo4.getAfiliadoDireccion());
                    reporte.setStrTelefonoFijoPaciente(anexo4.getAfiliadoTelefono());
                    reporte.setStrDepartementoPaciente(anexo4.getAfiliadoDepartamento());
                    reporte.setStrMunicipioPaciente(anexo4.getAfiliadoMunicipio());
                    reporte.setStrTelefonoCelularPaciente(anexo4.getAfiliadoCelular());
                    reporte.setStrCorreoPaciente(anexo4.getAfiliadoCorreo());
                    reporte.setStrUbicacionPaciente("");
                    if (anexo4.getAuAnexo3Id() != null && anexo4.getAuAnexo3Id().getId() != null) {
                        reporte.setStrNivelAfiliado(anexo4.getAuAnexo3Id().getAsegAfiliadoId().getMaeNivelSisbenValor());
                    }
                    
                    //Item
                    reporte.setStrManejoIntegral(anexo4.getMaeGuiaManejoIntegralValor());
                    reporte.setStrCodigoCups(item.getMaTecnologiaCodigo());
                    reporte.setStrCantidad("" + item.getCantidadAutorizada());
                    reporte.setStrDescripcion(item.getMaTecnologiaValor());
                    reporte.setStrObservacion(anexo4.getObservacion());
                    if (anexo4.getAuAnexo3Id() != null) {
                        reporte.setStrNumeroOrigen(anexo4.getAuAnexo3Id().getId().toString());
                    } else if (anexo4.getAuAnexo2Id() != null) {
                        reporte.setStrNumeroOrigen(anexo4.getAuAnexo2Id().getId().toString());
                    } else {
                        reporte.setStrNumeroOrigen("");
                    }
                    reporte.setDateFechaOrigen(fechaOrigen);
                    reporte.setStrPorcentajeAutorizado(anexo4.getPorcentajeRecuperacion() != null ? anexo4.getPorcentajeRecuperacion() + " %" : "");
                    reporte.setStrSemanaPaciente("" + anexo4.getSemanasAfiliacion());
                    reporte.setStrValor(anexo4.getValorCopago() != null ? anexo4.getValorCopago().toString() : "");
                    //reporte.setStrAplicaCobro(anexo4.getExcentoCopago() != null ? anexo4.getExcentoCopago() ? "NO" : "SI" : "");
                    //reporte.setStrTipoCuota("");
                    reporte.setStrNombreAutoriza(anexo4.getNombreAutoriza());
                    reporte.setStrCargoAutoriza(anexo4.getCargoActividadAutoriza());
                    reporte.setStrTelefonoAutoriza(anexo4.getEpsTelefono());
//                    anexo4.setDiasVigencia(180);
//                    if (item.getTipoTecnologia() == AuAnexo3Item.TIPO_TECNOLOGIA_CUM
//                            || item.getTipoTecnologia() == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO) {
//                        anexo4.setDiasVigencia(30);
//                    }
                    reporte.setStrDias("" + anexo4.getDiasVigencia());
                    reporte.setStrCama(anexo4.getAnexo3Cama());
                    reporte.setStrServicio(anexo4.getMaServicioHabilitadoValor());

                    if (anexo4.getAuAnexo2Id() != null && anexo4.getAuAnexo2Id().getListaAuAnexo2Diagnostico() != null) {
                        for (AuAnexo2Diagnostico diagno : anexo4.getAuAnexo2Id().getListaAuAnexo2Diagnostico()) {
                            if (diagno.isPrincipal()) {
                                reporte.setStrDiagnosticoPrincipal(diagno.getMaDiagnosticosCodigo() + " - " + diagno.getMaDiagnosticosValor());
                            }
                        }
                    }

                    if (anexo4.getAuAnexo3Id() != null && anexo4.getAuAnexo3Id().getAuAnexo3DiagnosticosList() != null) {
                        for (AuAnexo3Diagnostico diagno : anexo4.getAuAnexo3Id().getAuAnexo3DiagnosticosList()) {
                            if (diagno.getPrincipal()) {
                                reporte.setStrDiagnosticoPrincipal(diagno.getMaDiagnosticosCodigo() + " - " + diagno.getMaDiagnosticosValor());
                            }
                        }
                    }

                    if (anexo4.getValorCuotaModeradora() != null) {
                        BigDecimal valor = anexo4.getValorCopago().add(anexo4.getValorCuotaModeradora());
                        reporte.setStrValor(valor.toString());
                    } else {
                        reporte.setStrValor("");
                    }
                    if(anexo4.getExcentoCopago()){
                        reporte.setStrAplicaCobro("NO");
                        reporte.setStrExcentoCuota("SI");
                        reporte.setStrMotivoExentoCobro(anexo4.getMotivoExentoCobro());
                    }else{
                        reporte.setStrAplicaCobro("SI");
                        reporte.setStrExcentoCuota("NO");
                    }
                    /*if(Double.parseDouble(reporte.getStrValor()) != 0.0){
                        reporte.setStrAplicaCobro("SI");
                        reporte.setStrExcentoCuota("NO");
                    }else{
                        reporte.setStrAplicaCobro("NO");
                        reporte.setStrExcentoCuota("SI");
                        reporte.setStrMotivoExentoCobro(anexo4.getMotivoExentoCobro());
                    }*/
                    reporte.setStrPorcentaje(anexo4.getPorcentajeRecuperacion() != null ? anexo4.getPorcentajeRecuperacion() + " %" : "");
                    reporte.setStrCuotaModeradora(anexo4.isAplicaCuotaModeradora() == true ? "SI" : "NO");
                    reporte.setStrCopago(anexo4.getAplicaCopago() == null || anexo4.getAplicaCopago() == false ? "NO" : "SI");
                    reporte.setStrCuotaRecuperacion(anexo4.getAplicaCuotaRecuperacion() == null || anexo4.getAplicaCuotaRecuperacion() == false ? "NO" : "SI");
                   
                    reporte.setStrGrupoServicio(anexo4.getMaeUbicacionValor());
                    reporte.setStrDireccionAlternativa(anexo4.getDireccionAlternativa());
                    reporte.setStrModalidadTecnologia(anexo4.getMaeModalidadTecnologiaValor());
                    reporte.setStrFinalidadTecnologia(anexo4.getMaeFinalidadTecnologiaValor());
                    reporte.setStrConsecutivo(anexo4.getConsecutivoGen());
                    reporte.setStrFechaAutorizacion(dateFormat.format(anexo4.getFechaHoraCrea()));
                    reporte.setStrFechaFinAutorizacion(dateFormat.format(anexo4.getFechaFin()));
                    if (anexo4.getAuAnexo3Id() != null && anexo4.getAuAnexo3Id().getId() != null) {
                        CsTopeCopago copago = getCsTopeCopagoRemoto().consultar(dateFormatAgno.format(new Date()), String.valueOf(Integer.parseInt(anexo4.getAuAnexo3Id().getAsegAfiliadoId().getMaeRegimenCodigo())), anexo4.getAuAnexo3Id().getAsegAfiliadoId().getMaeNivelSisbenCodigo(), anexo4.getAuAnexo3Id().getAsegAfiliadoId().getMaeTipoAfiliadoCodigo(), anexo4.getAuAnexo3Id().getAsegAfiliadoId().getCategoriaIbc());
                        if(copago != null){
                            reporte.setStrTopeMaximo(String.valueOf(copago.getValorTopeEvento())); 
                        }
                    }else if(anexo4.getAuAnexo2Id() != null && anexo4.getAuAnexo2Id().getId() != null){
                        CsTopeCopago copago = getCsTopeCopagoRemoto().consultar(dateFormatAgno.format(new Date()), String.valueOf(Integer.parseInt(anexo4.getAuAnexo2Id().getAsegAfiliado().getMaeRegimenCodigo())), anexo4.getAuAnexo2Id().getAsegAfiliado().getMaeNivelSisbenCodigo(), anexo4.getAuAnexo2Id().getAsegAfiliado().getMaeTipoAfiliadoCodigo(), anexo4.getAuAnexo2Id().getAsegAfiliado().getCategoriaIbc());
                        if(copago != null){
                            reporte.setStrTopeMaximo(String.valueOf(copago.getValorTopeEvento())); 
                        }
                    }
                                    
                    listaReportes.add(reporte);
                }
            }

        } catch (Exception e) {
        }
        return listaReportes;
    }

    public List<ReportePreAutorizacion> castearAuAnexo4ReportePreAutorizacion(AuAnexo4 anexo4) {
        List<ReportePreAutorizacion> listaReportes = new ArrayList();
        ReportePreAutorizacion reporte = new ReportePreAutorizacion();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat horaFormat = new SimpleDateFormat("HH:mm");
        try {
            if (anexo4.getAuAnexo4ItemsList() == null) {
                anexo4.setAuAnexo4ItemsList(getAuAnexo4ItemRemoto().consultarListaByIdAnexo4(anexo4.getId()));
            }

            Date fechaOrigen = anexo4.getFechaHoraCrea();
            if (anexo4.getAuAnexo3Id() != null && anexo4.getAuAnexo3Id().getId() != null) {
                if (anexo4.getAuAnexo3Id().getFechaHoraCrea() != null) {
                    fechaOrigen = anexo4.getAuAnexo3Id().getFechaHoraCrea();
                } else {
                    anexo4.setAuAnexo3Id(getAuAnexo3Remoto().consultar(anexo4.getAuAnexo3Id().getId()));
                    if (anexo4.getAuAnexo3Id().getFechaHoraCrea() != null) {
                        fechaOrigen = anexo4.getAuAnexo3Id().getFechaHoraCrea();
                    }
                }

            }
            if (anexo4.getAuAnexo2Id() != null && anexo4.getAuAnexo2Id().getId() != null) {
                if (anexo4.getAuAnexo2Id().getFechaHoraCrea() != null) {
                    fechaOrigen = anexo4.getAuAnexo2Id().getFechaHoraCrea();
                } else {
                    anexo4.setAuAnexo2Id(getAuAnexo2Remoto().consultar(anexo4.getAuAnexo2Id().getId()));
                    if (anexo4.getAuAnexo2Id().getFechaHoraCrea() != null) {
                        fechaOrigen = anexo4.getAuAnexo2Id().getFechaHoraCrea();
                    }
                }
            }

            if (!anexo4.getAuAnexo4ItemsList().isEmpty()) {
                for (AuAnexo4Item item : anexo4.getAuAnexo4ItemsList()) {
                    reporte = new ReportePreAutorizacion();
                    //Titulo
                    reporte.setStrNumeroPreAutorizacion(anexo4.getId().toString());
                    reporte.setStrFechaPreAutorizacion(dateFormat.format(anexo4.getFechaAutorizacion()));
                    reporte.setStrHoraPreAutorizacion(horaFormat.format(anexo4.getFechaAutorizacion()));
                    //Entidad Responsable
                    reporte.setStrEntidadResponsable(anexo4.getEntidadPago());
                    reporte.setStrCodigoEntidad(anexo4.getCodigoEntidadPago());
                    //Prestador
                    reporte.setStrNombrePrestador(anexo4.getPrestadorNombre());
                    reporte.setStrTipoDocPrestador(anexo4.getPrestadorTipoDocumento());
                    reporte.setStrNumDocPrestador(anexo4.getPrestadorNumeroDocumento());
                    reporte.setStrCodigoPrestador(anexo4.getPrestadorCodigoHabilitacion());
                    anexo4 = consultarContactosPrestador(anexo4);
                    reporte.setStrCorreoPrestador(anexo4.getCorreoElectronicoPrestador());
                    reporte.setStrTelefono1Prestador(anexo4.getTelefono1Prestador());
                    reporte.setStrTelefono2Prestador(anexo4.getTelefono2Prestador());
                    reporte.setStrDireccionPrestador(anexo4.getPrestadorDireccion());
                    reporte.setStrDepartamentoPrestador(anexo4.getPrestadorDepartamento());
                    reporte.setStrMunicipioPrestador(anexo4.getPrestadorMunicipio());
                    reporte.setStrTipoTelefono1Prestador(anexo4.getTipoTelefono1Prestador());
                    reporte.setStrTipoTelefono2Prestador(anexo4.getTipoTelefono2Prestador());
                    //Afiliado
                    reporte.setStrPrimerApellidoPaciente(anexo4.getAfiliadoPrimerApellido());
                    reporte.setStrSegundoApellidoPaciente(anexo4.getAfiliadoSegundoApellido());
                    reporte.setStrPrimerNombrePaciente(anexo4.getAfiliadoPrimerNombre());
                    reporte.setStrSegundoNombrePaciente(anexo4.getAfiliadoSegundoNombre());
                    reporte.setStrTipoDocPaciente(anexo4.getAfiliadoTipoDocumento());
                    reporte.setStrNumDocPaciente(anexo4.getAfiliadoNumeroDocumento());
                    reporte.setStrFechaNacimientoPaciente(dateFormat.format(anexo4.getAfiliadoFechaNacimiento()));
                    reporte.setStrHoraNacimientoPaciente(horaFormat.format(anexo4.getAfiliadoFechaNacimiento()));
                    reporte.setStrDireccionPaciente(anexo4.getAfiliadoDireccion());
                    reporte.setStrTelefonoFijoPaciente(anexo4.getAfiliadoTelefono());
                    reporte.setStrDepartementoPaciente(anexo4.getAfiliadoDepartamento());
                    reporte.setStrMunicipioPaciente(anexo4.getAfiliadoMunicipio());
                    reporte.setStrTelefonoCelularPaciente(anexo4.getAfiliadoCelular());
                    reporte.setStrCorreoPaciente(anexo4.getAfiliadoCorreo());
                    reporte.setStrUbicacionPaciente("");
                    //Item
                    reporte.setStrManejoIntegral(anexo4.getMaeGuiaManejoIntegralValor());
                    reporte.setStrCodigoCups(item.getMaTecnologiaCodigo());
                    reporte.setStrCantidad("" + item.getCantidadAutorizada());
                    reporte.setStrDescripcion(item.getMaTecnologiaValor());
                    reporte.setStrObservacion(anexo4.getObservacion());
                    if (anexo4.getAuAnexo3Id() != null) {
                        reporte.setStrNumeroOrigen(anexo4.getAuAnexo3Id().getId().toString());
                    } else if (anexo4.getAuAnexo2Id() != null) {
                        reporte.setStrNumeroOrigen(anexo4.getAuAnexo2Id().getId().toString());
                    } else {
                        reporte.setStrNumeroOrigen("");
                    }
                    reporte.setStrFechaOrigen(dateFormat.format(fechaOrigen));
                    reporte.setStrHoraOrigen(horaFormat.format(fechaOrigen));
                    reporte.setStrPorcentajePreAutorizado(anexo4.getPorcentajeRecuperacion() != null ? anexo4.getPorcentajeRecuperacion() + " %" : "");
                    reporte.setStrSemanaPaciente("" + anexo4.getSemanasAfiliacion());
                    reporte.setStrValor(anexo4.getValorCopago() != null ? anexo4.getValorCopago().toString() : "");
                    //reporte.setStrAplicaCobro(anexo4.getExcentoCopago() != null ? anexo4.getExcentoCopago() ? "NO" : "SI" : "");
                    //reporte.setStrTipoCuota("");
                    reporte.setStrNombrePreAutoriza(anexo4.getNombreAutoriza());
                    reporte.setStrCargoPreAutoriza(anexo4.getCargoActividadAutoriza());
                    reporte.setStrTelefonoPreAutoriza(anexo4.getEpsTelefono());

                    reporte.setStrDias("" + anexo4.getDiasVigencia());
                    reporte.setStrCama(anexo4.getAnexo3Cama());
                    reporte.setStrServicio(anexo4.getMaServicioHabilitadoValor());

                    if (anexo4.getAuAnexo2Id() != null && anexo4.getAuAnexo2Id().getListaAuAnexo2Diagnostico() != null) {
                        for (AuAnexo2Diagnostico diagno : anexo4.getAuAnexo2Id().getListaAuAnexo2Diagnostico()) {
                            if (diagno.isPrincipal()) {
                                reporte.setStrDiagnosticoPrincipal(diagno.getMaDiagnosticosCodigo() + " - " + diagno.getMaDiagnosticosValor());
                            }
                        }
                    }

                    if (anexo4.getAuAnexo3Id() != null && anexo4.getAuAnexo3Id().getAuAnexo3DiagnosticosList() != null) {
                        for (AuAnexo3Diagnostico diagno : anexo4.getAuAnexo3Id().getAuAnexo3DiagnosticosList()) {
                            if (diagno.getPrincipal()) {
                                reporte.setStrDiagnosticoPrincipal(diagno.getMaDiagnosticosCodigo() + " - " + diagno.getMaDiagnosticosValor());
                            }
                        }
                    }

                    if (anexo4.getValorCuotaModeradora() != null) {
                        BigDecimal valor = anexo4.getValorCopago().add(anexo4.getValorCuotaModeradora());
                        reporte.setStrValor(valor.toString());
                    } else {
                        reporte.setStrValor("");
                    }
                    if(Double.parseDouble(reporte.getStrValor()) != 0.0){
                        reporte.setStrAplicaCobro("SI");
                    }else{
                        reporte.setStrAplicaCobro("NO");
                    }
                    reporte.setStrPorcentaje(anexo4.getPorcentajeRecuperacion() != null ? anexo4.getPorcentajeRecuperacion() + " %" : "");
                    reporte.setStrCuotaModeradora(anexo4.isAplicaCuotaModeradora() == true ? "SI" : "NO");
                    reporte.setStrCopago(anexo4.getAplicaCopago() == null || anexo4.getAplicaCopago() == false ? "NO" : "SI");
                    reporte.setStrCuotaRecuperacion(anexo4.getAplicaCuotaRecuperacion() == null || anexo4.getAplicaCuotaRecuperacion() == false ? "NO" : "SI");
                    reporte.setStrExcentoCuota("NO");
                    listaReportes.add(reporte);
                }
            }

        } catch (Exception e) {
        }
        return listaReportes;
    }

    private String obtenerValorTipoDocumentoIps(int id) {
        try {
            Maestro tipoDocIps = getMaestroRemoto().consultar(id);
            return tipoDocIps.getValor();
        } catch (Exception e) {
            return "";
        }
    }

    private String obtenerDepartamento(int id) {
        try {
            Ubicacion departamento;
            if (hashUbicacionesActivas != null) {
                departamento = hashUbicacionesActivas.get(id);
            } else {
                departamento = getUbicacionRemoto().consultar(id);
            }
            return departamento.getUbicacionPadre().getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    private String obtenerMunicipio(int id) {
        try {
            Ubicacion municipio;
            if (hashUbicacionesActivas != null) {
                municipio = hashUbicacionesActivas.get(id);
            } else {
                municipio = getUbicacionRemoto().consultar(id);
            }
            return municipio.getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    private String obtenerTipoDocumentoAfiliado(int id) {
        try {
            Maestro tipoDocPaciente = getMaestroRemoto().consultar(id);
            return tipoDocPaciente.getValor();
        } catch (Exception e) {
            return "";
        }
    }

    public String generarReporteNegacionServicio(String ruta, AuRechazo rechazo) {
        String mensaje = "";
        try {
            String nombre = "rechazo-" + rechazo.getId();
            List<ReporteNegacionServicio> listaReportes = castearRechazoReporteNegacion(rechazo);
            if (!listaReportes.isEmpty()) {
                InputStream is = getClass().getResourceAsStream("/reportes/Negacion_servicio.jasper");
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
            }

        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    private List<ReporteNegacionServicio> castearRechazoReporteNegacion(AuRechazo rechazo) {
        List<ReporteNegacionServicio> listaNegacion = new ArrayList();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Empresa savia = getEmpresaRemoto().consultar(1);
            for (AuRechazoItem item : rechazo.getAuRechazoItemsList()) {
                ReporteNegacionServicio negacion = new ReporteNegacionServicio();
                negacion.setStrNombreEntidad(savia.getNombreComercial());
                negacion.setStrNumeroEntidad(savia.getNit());
                negacion.setStrFechaSolicitud(dateFormat.format(rechazo.getAuAnexo3Id().getFechaSolicitud()));
                negacion.setStrFechaDiligenciamento(dateFormat.format(rechazo.getFechaHoraCrea()));
                negacion.setStrPrimerApellidoSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getPrimerApellido());
                negacion.setStrSegundoApellidoSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getSegundoApellido());
                negacion.setStrPrimerNombreSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getPrimerNombre());
                negacion.setStrSegundoNombreSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getSegundoNombre());
                negacion.setStrTipoDocSolicitante(obtenerTipoDocumentoAfiliado(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getMaeTipoDocumento()));
                negacion.setStrNumIdentificacionSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getNumeroDocumento());
                negacion.setStrFechaNacimientoSolicitante(dateFormat.format(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getFechaNacimiento()));
                negacion.setStrDireccionSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getDireccionDescripcion());
                negacion.setStrTelefonoFijoSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getTelefonoFijo());
                negacion.setStrDepartamentoSolicitante(obtenerDepartamento(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getResidenciaUbicacion().getId()));
                negacion.setStrMunicipioSolicitante(obtenerMunicipio(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getResidenciaUbicacion().getId()));
                negacion.setStrTelefonoCelularSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getTelefonoMovil());
                negacion.setStrCorreoSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getEmail());
                negacion.setStrTipoPlanSolicitante(obtenerValorRegimen(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getRegimen()));
                negacion.setStrCodigoServicio(item.getAuAnexo3ItemId().getMaTecnologiaCodigo());
                negacion.setStrDescripcionServicio(item.getAuAnexo3ItemId().getMaTecnologiaValor());
                negacion.setStrJustificacionServicio(rechazo.getMaeCausaRechazoValor());
                negacion.setStrFundamentoServicio("Resolución 3539 de 2019, expedida por Ministerio de Salud y Protección Social.");
                negacion.setStrAlternativas(rechazo.getAlternativa());
                listaNegacion.add(negacion);
            }
        } catch (Exception e) {
        }
        return listaNegacion;
    }

    private List<ReporteNegacionServicio> castearRechazoReporteNegacion(AuRechazo rechazo, String codigoTecnologia) {
        List<ReporteNegacionServicio> listaNegacion = new ArrayList();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Empresa savia = getEmpresaRemoto().consultar(1);
            rechazo.getAuRechazoItemsList().stream().filter(item -> (codigoTecnologia.equals(item.getAuAnexo3ItemId().getMaTecnologiaCodigo()) )).map(item -> {
                ReporteNegacionServicio negacion = new ReporteNegacionServicio();
                negacion.setStrNombreEntidad(savia.getNombreComercial());
                negacion.setStrNumeroEntidad(savia.getNit());
                negacion.setStrFechaSolicitud(dateFormat.format(rechazo.getAuAnexo3Id().getFechaSolicitud()));
                negacion.setStrFechaDiligenciamento(dateFormat.format(rechazo.getFechaHoraCrea()));
                negacion.setStrPrimerApellidoSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getPrimerApellido());
                negacion.setStrSegundoApellidoSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getSegundoApellido());
                negacion.setStrPrimerNombreSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getPrimerNombre());
                negacion.setStrSegundoNombreSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getSegundoNombre());
                negacion.setStrTipoDocSolicitante(obtenerTipoDocumentoAfiliado(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getMaeTipoDocumento()));
                negacion.setStrNumIdentificacionSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getNumeroDocumento());
                negacion.setStrFechaNacimientoSolicitante(dateFormat.format(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getFechaNacimiento()));
                negacion.setStrDireccionSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getDireccionDescripcion());
                negacion.setStrTelefonoFijoSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getTelefonoFijo());
                negacion.setStrDepartamentoSolicitante(obtenerDepartamento(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getResidenciaUbicacion().getId()));
                negacion.setStrMunicipioSolicitante(obtenerMunicipio(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getResidenciaUbicacion().getId()));
                negacion.setStrTelefonoCelularSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getTelefonoMovil());
                negacion.setStrCorreoSolicitante(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getEmail());
                negacion.setStrTipoPlanSolicitante(obtenerValorRegimen(rechazo.getAuAnexo3Id().getAsegAfiliadoId().getRegimen()));
                negacion.setStrCodigoServicio(item.getAuAnexo3ItemId().getMaTecnologiaCodigo());
                negacion.setStrDescripcionServicio(item.getAuAnexo3ItemId().getMaTecnologiaValor());
                return negacion;
            }).map(negacion -> {
                negacion.setStrJustificacionServicio(rechazo.getMaeCausaRechazoValor());
                return negacion;
            }).map(negacion -> {
                negacion.setStrFundamentoServicio("Resolución 3539 de 2019, expedida por Ministerio de Salud y Protección Social.");
                return negacion;
            }).map(negacion -> {
                negacion.setStrAlternativas(rechazo.getAlternativa());
                return negacion;
            }).forEachOrdered(negacion -> {
                listaNegacion.add(negacion);
            });
        } catch (Exception e) {
        }
        return listaNegacion;
    }
    
    private String obtenerValorRegimen(String regimen) {
        try {
            Maestro mae = getMaestroRemoto().consultarPorValorTipo("0" + regimen, MaestroTipo.GN_REGIMEN);
            return mae.getValor();
        } catch (Exception e) {
            return "";
        }
    }

    public StreamedContent generarReporteNegacionImprimir(AuRechazo negacion) {
        StreamedContent streamContent;
        try {
            List<ReporteNegacionServicio> listaReportes = castearRechazoReporteNegacion(negacion);
            if (!listaReportes.isEmpty()) {
                InputStream is = getClass().getResourceAsStream("/reportes/Negacion_servicio.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReportes);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name("NegacionServicio.pdf").build();
            } else {
                streamContent = null;
            }
        } catch (JRException ex) {
            streamContent = null;
        }
        return streamContent;
    }
    
    public StreamedContent generarReporteNegacionImprimir(AuRechazo negacion, String codigoTecnologia) {
        StreamedContent streamContent;
        try {
            List<ReporteNegacionServicio> listaReportes = castearRechazoReporteNegacion(negacion, codigoTecnologia);
            if (!listaReportes.isEmpty()) {
                InputStream is = getClass().getResourceAsStream("/reportes/Negacion_servicio.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReportes);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name("NegacionServicio.pdf").build();
            } else {
                streamContent = null;
            }
        } catch (JRException ex) {
            streamContent = null;
        }
        return streamContent;
    }

    public byte[] generarMarcaAgua(byte[] bytes, String marca, int ejeX, int ejeY) {
        byte[] newBytes = null;
        try {
            Rectangle tamanoPagina;
            PdfReader pdfLector = new PdfReader(bytes);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfStamper estampador = new PdfStamper(pdfLector, baos);
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
            Logger.getLogger(AuReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newBytes;
    }

    private AuAnexo4 consultarContactosPrestador(AuAnexo4 anexo4) throws Exception {
        List<CntPrestadorContacto> contactos = getCntPrestadorContactoRemoto().consultarPorCntContratoSedeYArea(anexo4.getCntPrestadorSedeId().getCntPrestador().getId(), anexo4.getCntPrestadorSedeId().getId(), AuConstantes.AREA_CONTACTO_AUTORIZACION);
        CntPrestadorContacto correo = contactos.stream()
                .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.TIPO_CONTACTO_CORREO))
                .findFirst().orElse(null);
        if (correo != null) {
            anexo4.setCorreoElectronicoPrestador(correo.getContacto());
        }
        contactos = contactos.stream()
                .filter(c -> c.isActivo() && (c.getMaeTipoContactoCodigo().equals(AuConstantes.TIPO_CONTACTO_TELEFONO_FIJO)
                || c.getMaeTipoContactoCodigo().equals(AuConstantes.TIPO_CONTACTO_TELEFONO_MOVIL)
                || c.getMaeTipoContactoCodigo().equals(AuConstantes.TIPO_CONTACTO_TELEFONO_PBX)))
                .collect(Collectors.toList());
        if (!contactos.isEmpty()) {
            anexo4.setTelefono1Prestador(contactos.get(0).getContacto());
            anexo4.setTipoTelefono1Prestador( contactos.get(0).getMaeTipoContactoValor());
            if (contactos.size() > 1) {
                anexo4.setTelefono2Prestador(contactos.get(1).getContacto());
                anexo4.setTipoTelefono2Prestador(contactos.get(1).getMaeTipoContactoValor());
            }
        }
        return anexo4;
    }

    public StreamedContent generarReporteDireccionado(AuAnexo3Item auAnexo3Item) {
        StreamedContent streamContent;
        try {
            List<ReporteItemDireccionado> listaReportes = castearAuAnexo3ReporteDireccionado(auAnexo3Item);
            if (!listaReportes.isEmpty()) {
                InputStream is = getClass().getResourceAsStream("/reportes/Anexo4.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReportes);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name("Direccionado.pdf").build();
            } else {
                streamContent = null;
            }
        } catch (JRException ex) {
            streamContent = null;
        }
        return streamContent;
    }

    public List<ReporteItemDireccionado> castearAuAnexo3ReporteDireccionado(AuAnexo3Item auAnexo3Item) {
        List<ReporteItemDireccionado> listaReportes = new ArrayList();
        ReporteItemDireccionado reporte = new ReporteItemDireccionado();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat horaFormat = new SimpleDateFormat("HH:mm");
        try {

            reporte = new ReporteItemDireccionado();
            auAnexo3Item.setAuAnexo3Id(getAuAnexo3Remoto().consultar(auAnexo3Item.getAuAnexo3Id().getId()));
            //Titulo
            Date fechaDireccionado = auAnexo3Item.getAuItemBitacorasList().stream()
                    .filter(bit -> bit.getTipo() == AuItemBitacora.ID_CAMBIO_ESTADO && bit.getDescripcion().startsWith("Direccionado"))
                    .findFirst().orElse(new AuItemBitacora())
                    .getFechaHoraCrea();

            reporte.setStrFechaDireccionado(dateFormat.format(fechaDireccionado));
            reporte.setStrHoraDireccionado(horaFormat.format(fechaDireccionado));
            //Entidad Responsable
            Empresa savia = getEmpresaRemoto().consultar(1);
            reporte.setStrEntidadResponsable(savia.getRazonSocial());
            if (auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getRegimen().equals("1")) {
                reporte.setStrCodigoEntidad(AuConstantes.CODIGO_ENTIDAD_REGIMEN_SUBSIDIADO);
            } else {
                reporte.setStrCodigoEntidad(AuConstantes.CODIGO_ENTIDAD_REGIMEN_CONTRIBUTIVO);
            }
            //Prestador
            reporte.setStrNombrePrestador("");
            reporte.setStrTipoDocPrestador("");
            reporte.setStrNumDocPrestador("");
            reporte.setStrCodigoPrestador("");
            reporte.setStrCorreoPrestador("");
            reporte.setStrTelefono1Prestador("");
            reporte.setStrTelefono2Prestador("");
            reporte.setStrDireccionPrestador("");
            reporte.setStrDepartamentoPrestador("");
            reporte.setStrMunicipioPrestador("");
            //Afiliado
            reporte.setStrPrimerApellidoPaciente(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getPrimerApellido());
            reporte.setStrSegundoApellidoPaciente(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getSegundoApellido());
            reporte.setStrPrimerNombrePaciente(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getPrimerNombre());
            reporte.setStrSegundoNombrePaciente(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getSegundoNombre());
            reporte.setStrTipoDocPaciente(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getMaeTipoDocumentoCodigo());
            reporte.setStrNumDocPaciente(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getNumeroDocumento());
            reporte.setStrFechaNacimientoPaciente(dateFormat.format(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getFechaNacimiento()));
            reporte.setStrDireccionPaciente(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getDireccionResidencia());
            reporte.setStrTelefonoFijoPaciente(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getTelefonoFijo());
            reporte.setStrDepartementoPaciente(obtenerDepartamento(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getAfiliacionUbicacion().getId()));
            reporte.setStrMunicipioPaciente(obtenerMunicipio(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getAfiliacionUbicacion().getId()));
            reporte.setStrTelefonoCelularPaciente(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getTelefonoMovil());
            reporte.setStrCorreoPaciente(auAnexo3Item.getAuAnexo3Id().getAsegAfiliadoId().getEmail());
            reporte.setStrUbicacionPaciente("");
            //Item
            reporte.setStrManejoIntegral("");//solo desde anexo2
            reporte.setStrCodigoCups(auAnexo3Item.getMaTecnologiaCodigo());
            reporte.setStrCantidad("" + auAnexo3Item.getCantidadSolicitada());
            reporte.setStrDescripcion(auAnexo3Item.getMaTecnologiaValor());

            reporte.setStrCama(auAnexo3Item.getAuAnexo3Id().getCama());
            reporte.setStrServicio(auAnexo3Item.getAuAnexo3Id().getMaServicioHabilitadoValor());

            if (auAnexo3Item.getAuAnexo3Id().getAuAnexo3DiagnosticosList() != null) {
                reporte.setStrDiagnosticoPrincipal(
                        auAnexo3Item.getAuAnexo3Id().getAuAnexo3DiagnosticosList().stream()
                                .filter(diagnostico -> diagnostico.getPrincipal())
                                .findFirst()
                                .map(diagnostico -> diagnostico.getMaDiagnosticosCodigo() + " - " + diagnostico.getMaDiagnosticosValor())
                                .orElse("")
                );
            }

            listaReportes.add(reporte);

        } catch (Exception e) {
        }
        return listaReportes;
    }
}
