/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.utilidades;

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
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazo;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazoItem;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteNegacionServicio;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Diagnostico;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ItemRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2Remoto;
import com.saviasaludeps.savia.ejb.utilidades.RemotoEJB;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.IOUtils;

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

    

    public String generarReporteAnexo4(String ruta, AuAnexo4 anexo4) {
        String mensaje = "";
        try {
            String nombre = anexo4.getArchivo();
            List<ReporteAnexo4> listaReportes = castearAuAnexo4ReporteAnexo4(anexo4);
            if (!listaReportes.isEmpty()) {
                InputStream is = getClass().getResourceAsStream("/reportes/Anexo4.jasper");
//                if(anexo4.isVersion() == true){
//                    is = getClass().getResourceAsStream("/reportes/Anexo4res2335.jasper");
//                }
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
                    reporte.setStrHoraAutorizacion(horaFormat.format(anexo4.getFechaAutorizacion()));
                    //Entidad Responsable
                    reporte.setStrEntidadResponsable(anexo4.getEntidadPago());
                    reporte.setStrCodigoEntidad(anexo4.getCodigoEntidadPago());
                    //Prestador
                    reporte.setStrNombrePrestador(anexo4.getPrestadorNombre());
                    reporte.setStrTipoDocPrestador(anexo4.getPrestadorTipoDocumento());
                    reporte.setStrNumDocPrestador(anexo4.getPrestadorNumeroDocumento());
                    reporte.setStrCodigoPrestador(anexo4.getPrestadorCodigoHabilitacion());
                    reporte.setStrCorreoPrestador(""); //correo
                    reporte.setStrTelefono1Prestador(anexo4.getCntPrestadorSedeId().getTelefonoCitas());
                    reporte.setStrTelefono2Prestador(anexo4.getCntPrestadorSedeId().getTelefonoAdministrativo());
                    reporte.setStrDireccionPrestador(anexo4.getPrestadorDireccion());
                    reporte.setStrDepartamentoPrestador(anexo4.getPrestadorDepartamento());
                    reporte.setStrMunicipioPrestador(anexo4.getPrestadorMunicipio());
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
                    } else {
                        reporte.setStrNumeroOrigen("");
                    }
                    reporte.setDateFechaOrigen(fechaOrigen);
                    reporte.setStrHoraOrigen(horaFormat.format(fechaOrigen));
                    reporte.setStrPorcentajeAutorizado(anexo4.getPorcentajeRecuperacion() + " %");
                    reporte.setStrSemanaPaciente("" + anexo4.getSemanasAfiliacion());
                    reporte.setStrValor(anexo4.getValorCopago() != null ? anexo4.getValorCopago().toString() : "");
                    reporte.setStrAplicaCobro(anexo4.getExcentoCopago() != null ? anexo4.getExcentoCopago() ? "NO" : "SI" : "");
                    //reporte.setStrTipoCuota("");
                    reporte.setStrNombreAutoriza(anexo4.getNombreAutoriza());
                    reporte.setStrCargoAutoriza(anexo4.getCargoActividadAutoriza());
                    reporte.setStrTelefonoAutoriza(anexo4.getEpsTelefono());
                    anexo4.setDiasVigencia(180);
                    if (item.getTipoTecnologia() == 2) {
                        anexo4.setDiasVigencia(30);
                    }
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
                    reporte.setStrPorcentaje(anexo4.getPorcentajeRecuperacion() != null ? anexo4.getPorcentajeRecuperacion() + " %" : "");
                    reporte.setStrCuotaModeradora(anexo4.isAplicaCuotaModeradora() == true ? "SI" : "NO");
                    reporte.setStrCopago(anexo4.getAplicaCopago() == null || anexo4.getAplicaCopago() == false ? "NO" : "SI");
                    reporte.setStrCuotaRecuperacion(anexo4.getAplicaCuotaRecuperacion() == null || anexo4.getAplicaCuotaRecuperacion() == false ? "NO" : "SI");
                    reporte.setStrExcentoCuota("NO");
//                    reporte.setStrGrupoServicio(anexo4.getMaeUbicacionValor());
//                    reporte.setStrDireccionAlternativa(anexo4.getDireccionAlternativa());
//                    reporte.setStrModalidadTecnologia(anexo4.getMaeModalidadTecnologiaValor());
//                    reporte.setStrFinalidadTecnologia(anexo4.getMaeFinalidadTecnologiaValor());
//                    reporte.setStrConsecutivo(anexo4.getConsecutivo());
//                    reporte.setStrFechaAutorizacion(dateFormat.format(anexo4.getFechaHoraCrea()));
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
            Ubicacion departamento = getUbicacionRemoto().consultar(id);
            return departamento.getUbicacionPadre().getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    private String obtenerMunicipio(int id) {
        try {
            Ubicacion municipio = getUbicacionRemoto().consultar(id);
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
                negacion.setStrFundamentoServicio("");
                negacion.setStrAlternativas("");
                listaNegacion.add(negacion);
            }
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
}
