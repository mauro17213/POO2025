/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CargaMasivaConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezadoResumen;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFacturaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio.CargaMasivaConciliacionServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@ManagedBean
@ViewScoped
public class CargaMasivaConciliacionBean extends Url implements Cloneable {

    private CargaMasivaConciliacionServicioIface cargaMasivaConciliacionServicio;
    public static final char ACCION_CARGAR_CONCILIACIONES = 'S';
    public static final char ACCION_BUSCAR_IPS = 'T';
    public static final char ACCION_BLOQUEAR_FACTURA = 'U';
    public static final char ACCION_GENERAR_REPORTE_ERRORES = 'W';
    public static final char ACCION_VER_ENCABEZADOS = 'X';
    public static final char ACCION_VER_CUENTAS_CONTABLES = 'Y';
    public static final char ACCION_VER_WS_CM_FACTURAS = 'a';
    public static final char ACCION_VER_CM_FACTURAS_DETALLES = 'b';
    public static final char ACCION_VER_TIPO_FUENTE_DATOS = 'c';
    public static int NUMERO_CAMPOS_POR_FILA_CONCILIACION = 6;
    public static int TIPO_CAMPO_VALOR_PAGADO_EPS = 1;
    public static int TIPO_CAMPO_VALOR_ACEPTADO_IPS = 2;
    public static final int TIPO_FUENTE_DATOS_SINCRONIZACION_ENCABEZADO = 1;
    public static final int TIPO_FUENTE_DATOS_WS_FACTURAS = 2;

    CargaMasivaConciliacion objeto;
    private Map<String, String> listaIps;
    private StringBuilder mensajeErrorFormatoConciliacion;
    private boolean hayDescargaErrores = false;
    private LazyDataModel<CmConciliacion> lazyConciliaciones;
    private List<CmConciliacion> registros;
    private List<CmSincronizacionEncabezado> registrosSincEncabezado;
    private List<com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion> listaAnexos;
    private List<CmSincronizacionDetalle> registrosSincDetalle;
    private LazyDataModel<WsCmFactura> lazyWsCmFacturas;
    private List<WsCmFactura> registrosWsCmFacturas;
    private LazyDataModel<WsCmFacturaDetalle> lazyWsCmFacturaDetalles;
    private List<WsCmFacturaDetalle> registrosWsCmFacturaDetalles;

    private List<CmFactura> facturasEncontradas;
    private CmConciliacion cmConciliacion;
    private ParamConsulta paramConsultaUtilitario;
    private String mensajeGeneral;
    private ParamConsulta paramConsultaListaConciliaciones;
    private ParamConsulta paramConsultaSincEncabezado;
    private ParamConsulta paramConsultaSincDetalle;

    //Datos para reporte conciliacion
    private ReporteConciliacion reporteConciliacion;
    private List<ReporteConciliacion> listaReporteConciliacion;

    private LazyDataModel<CmSincronizacionEncabezado> lazySincEncabezado;
    private LazyDataModel<CmSincronizacionDetalle> lazySincDetalle;

    private List<CmSincronizacionEncabezadoResumen> listaCmSincronizaconEncabezadoResumen;
    private int tipoFuenteDatos;

    public CargaMasivaConciliacionBean() {
        this.objeto = new CargaMasivaConciliacion();
        Modulo mod = super.validarModulo(Modulo.ID_FACTURA_CARGA_MASIVA_CONC);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);

            lazyConciliaciones = new LazyDataModel<CmConciliacion>() {

                private List<CmConciliacion> conciliaciones;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsultaListaConciliaciones().getCantidadRegistros();
                }

                @Override
                public List<CmConciliacion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsultaListaConciliaciones().setPrimerRegistro(primerRegistro);
                    getParamConsultaListaConciliaciones().setRegistrosPagina(registrosPagina);
                    getParamConsultaListaConciliaciones().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsultaListaConciliaciones().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    generarMensajes();
                    conciliaciones = getRegistros();
                    setRowCount(getParamConsultaListaConciliaciones().getCantidadRegistros());
                    return conciliaciones;
                }

                @Override
                public String getRowKey(CmConciliacion conciliacion) {
                    return conciliacion.getId().toString();
                }

                @Override
                public CmConciliacion getRowData(String conciliacionId) {
                    Integer id = Integer.valueOf(conciliacionId);
                    for (CmConciliacion conciliacion : conciliaciones) {
                        if (id.equals(conciliacion.getId())) {
                            return conciliacion;
                        }
                    }
                    return null;
                }

            };
        }

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        CargaMasivaConciliacionBean clone;
        try {
            clone = (CargaMasivaConciliacionBean) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("Error al clonar CargaMasivaConciliacionBean");
        }
        return clone;
    }

    @PostConstruct
    public void postConstruct() {
        getCargaMasivaConciliacionServicio().cargaInicial(this);
        listar();
    }

    public CargaMasivaConciliacionServicioIface getCargaMasivaConciliacionServicio() {
        return cargaMasivaConciliacionServicio;
    }

    public void setCargaMasivaConciliacionServicio(CargaMasivaConciliacionServicioIface cargaMasivaConciliacionServicio) {
        this.cargaMasivaConciliacionServicio = cargaMasivaConciliacionServicio;
    }

    public CargaMasivaConciliacion getObjeto() {
        return objeto;
    }

    public void setObjeto(CargaMasivaConciliacion objeto) {
        this.objeto = objeto;
    }

    public Map<String, String> getListaIps() {
        return listaIps;
    }

    public void setListaIps(Map<String, String> listaIps) {
        this.listaIps = listaIps;
    }

    public List<CmConciliacion> getListaAnexos() {
        return listaAnexos;
    }

    public void setListaAnexos(List<CmConciliacion> listaAnexos) {
        this.listaAnexos = listaAnexos;
    }

    public StringBuilder getMensajeErrorFormatoConciliacion() {
        if (mensajeErrorFormatoConciliacion == null) {
            mensajeErrorFormatoConciliacion = new StringBuilder();
        }
        return mensajeErrorFormatoConciliacion;
    }

    public void setMensajeErrorFormatoConciliacion(StringBuilder mensajeErrorFormatoConciliacion) {
        this.mensajeErrorFormatoConciliacion = mensajeErrorFormatoConciliacion;
    }

    public ParamConsulta getParamConsultaSincEncabezado() {
        if (paramConsultaSincEncabezado == null) {
            paramConsultaSincEncabezado = new ParamConsulta();
        }
        return paramConsultaSincEncabezado;
    }

    public void setParamConsultaSincEncabezado(ParamConsulta paramConsultaSincEncabezado) {
        this.paramConsultaSincEncabezado = paramConsultaSincEncabezado;
    }

    public ParamConsulta getParamConsultaSincDetalle() {
        if (paramConsultaSincDetalle == null) {
            paramConsultaSincDetalle = new ParamConsulta();
        }
        return paramConsultaSincDetalle;
    }

    public void setParamConsultaSincDetalle(ParamConsulta paramConsultaSincDetalle) {
        this.paramConsultaSincDetalle = paramConsultaSincDetalle;
    }

    public ParamConsulta getParamConsultaUtilitario() {
        if (paramConsultaUtilitario == null) {
            paramConsultaUtilitario = new ParamConsulta();
        }
        return paramConsultaUtilitario;
    }

    public void setParamConsultaUtilitario(ParamConsulta paramConsultaUtilitario) {
        this.paramConsultaUtilitario = paramConsultaUtilitario;
    }

    public ParamConsulta getParamConsultaListaConciliaciones() {
        if (paramConsultaListaConciliaciones == null) {
            paramConsultaListaConciliaciones = new ParamConsulta();
        }
        return paramConsultaListaConciliaciones;
    }

    public void setParamConsultaListaConciliaciones(ParamConsulta paramConsultaListaConciliaciones) {
        this.paramConsultaListaConciliaciones = paramConsultaListaConciliaciones;
    }

    public boolean isHayDescargaErrores() {
        return hayDescargaErrores;
    }

    public void setHayDescargaErrores(boolean hayDescargaErrores) {
        this.hayDescargaErrores = hayDescargaErrores;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public List<CmFactura> getFacturasEncontradas() {
        if (facturasEncontradas == null) {
            facturasEncontradas = new ArrayList<>();
        }
        return facturasEncontradas;
    }

    public void setFacturasEncontradas(List<CmFactura> facturasEncontradas) {
        this.facturasEncontradas = facturasEncontradas;
    }

    public CmConciliacion getCmConciliacion() {
        if (cmConciliacion == null) {
            cmConciliacion = new CmConciliacion();
        }
        return cmConciliacion;
    }

    public void setCmConciliacion(CmConciliacion cmConciliacion) {
        this.cmConciliacion = cmConciliacion;
    }

    public String getMensajeGeneral() {
        return mensajeGeneral;
    }

    public void setMensajeGeneral(String mensajeGeneral) {
        this.mensajeGeneral = mensajeGeneral;
    }

    public LazyDataModel<CmConciliacion> getLazyConciliaciones() {
        return lazyConciliaciones;
    }

    public void setLazyConciliaciones(LazyDataModel<CmConciliacion> lazyConciliaciones) {
        this.lazyConciliaciones = lazyConciliaciones;
    }

    public LazyDataModel<CmSincronizacionEncabezado> getLazySincEncabezado() {
        return lazySincEncabezado;
    }

    public void setLazySincEncabezado(LazyDataModel<CmSincronizacionEncabezado> lazySincEncabezado) {
        this.lazySincEncabezado = lazySincEncabezado;
    }

    public LazyDataModel<CmSincronizacionDetalle> getLazySincDetalle() {
        return lazySincDetalle;
    }

    public void setLazySincDetalle(LazyDataModel<CmSincronizacionDetalle> lazySincDetalle) {
        this.lazySincDetalle = lazySincDetalle;
    }

    public List<CmSincronizacionEncabezado> getRegistrosSincEncabezado() {
        return registrosSincEncabezado;
    }

    public void setRegistrosSincEncabezado(List<CmSincronizacionEncabezado> registrosSincEncabezado) {
        this.registrosSincEncabezado = registrosSincEncabezado;
    }

    public List<CmSincronizacionDetalle> getRegistrosSincDetalle() {
        return registrosSincDetalle;
    }

    public void setRegistrosSincDetalle(List<CmSincronizacionDetalle> registrosSincDetalle) {
        this.registrosSincDetalle = registrosSincDetalle;
    }

    public List<CmConciliacion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmConciliacion> registros) {
        this.registros = registros;
    }

    public List<CmSincronizacionEncabezadoResumen> getListaCmSincronizaconEncabezadoResumen() {
        if (listaCmSincronizaconEncabezadoResumen == null) {
            listaCmSincronizaconEncabezadoResumen = new ArrayList<>();
        }
        return listaCmSincronizaconEncabezadoResumen;
    }

    public void setListaCmSincronizaconEncabezadoResumen(List<CmSincronizacionEncabezadoResumen> listaCmSincronizaconEncabezadoResumen) {
        this.listaCmSincronizaconEncabezadoResumen = listaCmSincronizaconEncabezadoResumen;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCargaMasivaConciliacionServicio().Accion(this);
    }

    public LazyDataModel<WsCmFactura> getLazyWsCmFacturas() {
        return lazyWsCmFacturas;
    }

    public void setLazyWsCmFacturas(LazyDataModel<WsCmFactura> lazyWsCmFacturas) {
        this.lazyWsCmFacturas = lazyWsCmFacturas;
    }

    public List<WsCmFactura> getRegistrosWsCmFacturas() {
        return registrosWsCmFacturas;
    }

    public void setRegistrosWsCmFacturas(List<WsCmFactura> registrosWsCmFacturas) {
        this.registrosWsCmFacturas = registrosWsCmFacturas;
    }

    public LazyDataModel<WsCmFacturaDetalle> getLazyWsCmFacturaDetalles() {
        return lazyWsCmFacturaDetalles;
    }

    public void setLazyWsCmFacturaDetalles(LazyDataModel<WsCmFacturaDetalle> lazyWsCmFacturaDetalles) {
        this.lazyWsCmFacturaDetalles = lazyWsCmFacturaDetalles;
    }

    public List<WsCmFacturaDetalle> getRegistrosWsCmFacturaDetalles() {
        return registrosWsCmFacturaDetalles;
    }

    public void setRegistrosWsCmFacturaDetalles(List<WsCmFacturaDetalle> registrosWsCmFacturaDetalles) {
        this.registrosWsCmFacturaDetalles = registrosWsCmFacturaDetalles;
    }

    public int getTipoFuenteDatos() {
        return tipoFuenteDatos;
    }

    public void setTipoFuenteDatos(int tipoFuenteDatos) {
        this.tipoFuenteDatos = tipoFuenteDatos;
    }

    public void refrescarSincEncabezado() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_VER_ENCABEZADOS);
        getCargaMasivaConciliacionServicio().Accion(this);
    }

    public void refrescarSincDetalle() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_VER_CUENTAS_CONTABLES);
        getCargaMasivaConciliacionServicio().Accion(this);
    }

    public void refrescarWSFacturas() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_VER_WS_CM_FACTURAS);
        getCargaMasivaConciliacionServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarWSCmFacturaDetalles() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_VER_CM_FACTURAS_DETALLES);
        getCargaMasivaConciliacionServicio().Accion(this);
        procesoFinal();
    }

    public void ver(int id) {
        super.setAccion(ACCION_VER);
        getCargaMasivaConciliacionServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getCargaMasivaConciliacionServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrearAdjunto:panelCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto:panelCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getCargaMasivaConciliacionServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
        procesoFinal();
    }

    public StreamedContent generarArchivoErroresCargaArchivo() {
        StreamedContent streamedContentError = null;
        String nombre;
        String texto;
        try {
            if (getMensajeErrorFormatoConciliacion().length() > 0) {
                texto = getMensajeErrorFormatoConciliacion().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                nombre = this.getObjeto().getNombreArchivo() + "_error_" + sdf.format(new Date()) + ".txt";
                byte[] bytes = texto.getBytes();
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamedContentError = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(nombre).build();
            } else {
                addError("No se encontraron datos para generar el archivo error conciliacion");
                generarMensajes();
            }
        } catch (Exception ex) {
            streamedContentError = null;
            addError("Error al mostrar errores conciliacion : " + ex.getMessage());
            generarMensajes();
        }
        return streamedContentError;
    }

    public void buscarIps() {
        if ((this.getObjeto().getRazonSocial() == null
                || this.getObjeto().getRazonSocial().equals(""))
                && (this.getObjeto().getNit() == null
                || this.getObjeto().getNit().equals(""))) {
            this.addError("Para realizar la búsqueda debe ingresar Nit o Razón Social");
        }

        if (!this.isError()) {
            super.setAccion(ACCION_VER);
            super.setDoAccion(ACCION_BUSCAR_IPS);
            getCargaMasivaConciliacionServicio().Accion(this);
        } else {
            PrimeFaces.current().ajax().update("frmSubirCargaMasiva");
        }
        procesoFinal();
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_GUARDAR:
                crearLog("Conciliación Masiva", getObjeto().toString());
                this.getObjeto().setIps("");
                this.getObjeto().setNit("");
                this.getObjeto().setRazonSocial("");
                this.getObjeto().setMensajeExitoConcilicion("");
                PrimeFaces.current().ajax().update("frmConsultaCargaMasiva");
                break;
            case Url.ACCION_LISTAR:
                crearLog("Listar", getObjeto().toString());
                break;
            case CargaMasivaConciliacionBean.ACCION_BUSCAR_IPS:
                PrimeFaces.current().ajax().update("frmSubirCargaMasiva");
                break;

            case Url.ACCION_VER:
                switch (this.getDoAccion()) {
                    case CargaMasivaConciliacionBean.ACCION_CARGAR_CONCILIACIONES:
                        crearLog("Cargar Archivo Conciliación", getObjeto().toString());
                        PrimeFaces.current().ajax().update("frmSubirCargaMasiva");
                        break;
                }
                break;
            case CargaMasivaConciliacionBean.ACCION_ADICIONAL_1:
                crearLog("Generar Reporte Conciliacion", getObjeto().toString());
                break;

            case CargaMasivaConciliacionBean.ACCION_ADICIONAL_2:
                crearLog("Ejecutar Registro Sincronizacion", getObjeto().toString());
                PrimeFaces.current().ajax().update("frmConsultaCargaMasiva");
                break;

            case CargaMasivaConciliacionBean.ACCION_ADICIONAL_3:
                switch (this.getDoAccion()) {
                    case CargaMasivaConciliacionBean.ACCION_VER_ENCABEZADOS:
                        crearLog("Consulta encabezados ", getObjeto().toString());
                        PrimeFaces.current().ajax().update("frmVerSincronizacionEncabezado");
                        break;
                    case CargaMasivaConciliacionBean.ACCION_VER_CUENTAS_CONTABLES:
                        crearLog("Consulta Cuentas Contables ", getObjeto().toString());
                        PrimeFaces.current().ajax().update("frmVerCuentasContables");
                        break;
                    case ACCION_VER_CM_FACTURAS_DETALLES:
                        crearLog("Ver Ws Facturas Detalles", getObjeto().toString());
                        break;
                    case ACCION_VER_WS_CM_FACTURAS:
                        crearLog("Ver WS Facturas", getObjeto().toString());
                        break;
                }
                break;

            case CargaMasivaConciliacionBean.ACCION_ADICIONAL_4:
                crearLog("Generar Reporte Errores ", getObjeto().toString());
                break;
            case CargaMasivaConciliacionBean.ACCION_ADICIONAL_5:
                crearLog("Ver Adjuntos ", getObjeto().toString());
                break;
        }
        generarMensajes();
    }

    public void cargarArchivoConciliar(FileUploadEvent event) throws Exception {
        try {
            boolean validarCarga = true;
            if (this.getObjeto().getIps() == null) {
                this.addError("Seleccione la Ips asociada");
                validarCarga = false;
            }
            if (!validarCarga) {
                PrimeFaces.current().ajax().update("frmSubirCargaMasiva:fileUploadConciliacion");
            } else {
                UploadedFile archivo = event.getFile();
                getObjeto().setAdjuntoStream(archivo.getInputStream());
                getObjeto().setArchivoCargaConciliable(clonarArchivoConciliable(archivo.getInputStream()));
                getObjeto().setNombreArchivo(FilenameUtils.getName(event.getFile().getFileName()));
                getFacturasEncontradas().clear();
                setHayDescargaErrores(false);
                setMensajeErrorFormatoConciliacion(new StringBuilder());
                this.getObjeto().setMensajeExitoConcilicion("");
                super.setAccion(ACCION_VER);
                super.setDoAccion(ACCION_CARGAR_CONCILIACIONES);
                getCargaMasivaConciliacionServicio().Accion(this);
                if (!this.isError()) {
                    PrimeFaces.current().executeScript("PF('dialogoSubirCargaMasiva').hide()");
                    verConciliacionMasiva();
                } else {
                    if (getMensajeErrorFormatoConciliacion().length() > 0) {
                        setHayDescargaErrores(true);
                    }
                }
            }
            procesoFinal();
        } catch (Exception ex) {
            this.addError("Error al cargar archivo a conciliar :" + ex.getMessage());
            Logger.getLogger(CargaMasivaConciliacionBean.class.getName()).log(Level.SEVERE, null, ex);
            procesoFinal();
        }
    }

    private InputStream clonarArchivoConciliable(InputStream archivoStream) {
        InputStream archivoClonado;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            archivoStream.transferTo(baos);
            archivoClonado = new ByteArrayInputStream(baos.toByteArray());
        } catch (IOException ex) {
            archivoClonado = IOUtils.toInputStream("Error : " + ex.toString());
        }
        return archivoClonado;
    }

    public void cerrarVentanaConciliacionMasiva() {

        //se libera las factuas en proceso siempre y cuendo no sea un proceso
        //exitoso dado que el mismo proceso libera las facturas
        if (getReporteConciliacion() == null) {
            actualizaBloqueoFacturas(false);
        }

        PrimeFaces.current().executeScript("PF('tablaWidgetDetallesResponder').unselectAllRows();");
        getFacturasEncontradas().clear();
        this.getCmConciliacion().setValorFacturasPagadoEpsPrecalculado(null);
        this.getCmConciliacion().setValorFacturasAceptadoIpsPrecalculado(null);
        this.getCmConciliacion().setRepresentanteEps("");
        this.getCmConciliacion().setRepresentanteIps("");
        this.getCmConciliacion().setObservacion("");

        getFacturasEncontradas().clear();
        if (super.getMensajes() != null && (super.getMensajes().size() > 0 || super.isError())) {
            String mensajeExito = super.getMensajes().toString();
            super.setAccion(ACCION_GUARDAR);
            this.getListaIps().clear();
            this.getObjeto().setMensajeExitoConcilicion(mensajeExito);
            procesoFinal();
            this.setMensajeGeneral(mensajeExito);
            PrimeFaces.current().executeScript("PF('dialogoFinProcesoConciliacionMasiva').show()");
        }
        PrimeFaces.current().ajax().update("frmSubirCargaMasiva");
        PrimeFaces.current().ajax().update("frmConsultaCargaMasiva");
    }

    public void asignacionValorPorcentaje(CmFactura factura) {
        int tipoCalculoConciliacion = 0;

        BigDecimal valorPagadoEps = factura.getValorPagadoEPS() == null
                ? new BigDecimal("0") : factura.getValorPagadoEPS();
        BigDecimal valorAceptadoIps = factura.getValorAceptadoIPS() == null
                ? new BigDecimal("0") : factura.getValorAceptadoIPS();
        BigDecimal porcentajeEps = factura.getPorcentajePagadoEPS() == null
                ? new BigDecimal("0") : factura.getPorcentajePagadoEPS();
        BigDecimal porcentajeIps = factura.getPorcentajeAceptadoIPS() == null
                ? new BigDecimal("0") : factura.getPorcentajeAceptadoIPS();

        if (valorPagadoEps.compareTo(new BigDecimal("0")) > 0) {
            conversionPorcentajeRowFactura(factura, 1);
            tipoCalculoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_IND;
        }

        if (valorAceptadoIps.compareTo(new BigDecimal("0")) > 0) {
            conversionPorcentajeRowFactura(factura, 2);
            tipoCalculoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_IND;
        }

        if ((valorPagadoEps.compareTo(BigDecimal.ZERO) == 0)
                && (porcentajeEps.compareTo(new BigDecimal("0")) > 0)) {
            conversionValorRowFactura(factura, 1);
            tipoCalculoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_IND;
        }

        if ((valorAceptadoIps.compareTo(BigDecimal.ZERO) == 0)
                && (porcentajeIps.compareTo(new BigDecimal("0")) > 0)) {
            conversionValorRowFactura(factura, 2);
            tipoCalculoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_IND;
        }
        factura.setTipoCalculoConciliacion(tipoCalculoConciliacion);

        BigDecimal valorIPS = factura.getValorAceptadoIPS() == null ? new BigDecimal("0") : factura.getValorAceptadoIPS();
        BigDecimal valorEPS = factura.getValorPagadoEPS() == null ? new BigDecimal("0") : factura.getValorPagadoEPS();
        BigDecimal valorBaseComparacion = factura.getValorPendienteActual() == null
                ? new BigDecimal("0") : factura.getValorPendienteActual().setScale(2, RoundingMode.HALF_UP);

        BigDecimal valorSumaReponder = new BigDecimal("0");
        valorSumaReponder = valorSumaReponder.add(valorIPS).
                add(valorEPS).setScale(2, RoundingMode.HALF_UP);

        if (valorSumaReponder.compareTo(valorBaseComparacion) > 0) {
            this.addError("La sumatora de valor EPS e IPS no debe superar el valor pendiente actual.");
            generarMensajes();
            factura.setValorAceptadoIPS(null);
            factura.setValorPagadoEPS(null);
            factura.setPorcentajeAceptadoIPS(null);
            factura.setPorcentajePagadoEPS(null);
            factura.setTipoCalculoConciliacion(0);
        }
    }

    public void onRowEditFactura(RowEditEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        asignacionValorPorcentaje(factura);
        actualizarSumatoriaValoresConciliacionMasiva();
    }

    public void conversionPorcentajeRowFactura(CmFactura factura, int tipoCampo) {

        BigDecimal valorLimite = factura.getValorPendienteActual();
        BigDecimal valorPropuesto = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? factura.getValorPagadoEPS()
                : factura.getValorAceptadoIPS();
        BigDecimal promedio;
        BigDecimal procentajeCalculado = new BigDecimal("0");

        if (valorPropuesto != null && valorLimite != null) {
            if (valorPropuesto.compareTo(valorLimite) > 0) {
                String msgError = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                        ? "El valor pagado EPS no puede ser mayor que valor pendiente actual"
                        : "El valor aceptado IPS no puede ser mayor que valor pendiente actual";
                this.addError(msgError);

                if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
                    factura.setValorPagadoEPS(null);
                } else {
                    factura.setValorAceptadoIPS(null);
                }
            } else {
                promedio = valorPropuesto.divide(valorLimite, 5, RoundingMode.CEILING);
                procentajeCalculado = promedio.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
            }
        }

        if (procentajeCalculado.equals(new BigDecimal(BigInteger.ZERO))) {
            procentajeCalculado = null;
            generarMensajes();
        }

        if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
            factura.setPorcentajePagadoEPS(procentajeCalculado);
        } else {
            factura.setPorcentajeAceptadoIPS(procentajeCalculado);
        }
    }

    public void conversionValorRowFactura(CmFactura factura, int tipoCampo) {

        BigDecimal valorFinal = new BigDecimal("0");

        BigDecimal porcentaje = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? factura.getPorcentajePagadoEPS()
                : factura.getPorcentajeAceptadoIPS();

        BigDecimal valorLimite = factura.getValorPendienteActual();

        if (porcentaje != null && valorLimite != null && !porcentaje.equals(new BigDecimal(BigInteger.ZERO))) {
            BigDecimal promedio = porcentaje.divide(new BigDecimal(100), 5, RoundingMode.CEILING);
            valorFinal = promedio.multiply(valorLimite).setScale(4, RoundingMode.HALF_UP);
        }

        if (valorFinal.equals(new BigDecimal(BigInteger.ZERO))) {
            valorFinal = null;
        }

        if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
            factura.setValorPagadoEPS(valorFinal);
        } else {
            factura.setValorAceptadoIPS(valorFinal);
        }
    }

    public void limpiarFormularioResponderConciliacion() {
        this.getCmConciliacion().setValorPagadoEps(null);
        this.getCmConciliacion().setPorcentajePagadoEps(null);
        this.getCmConciliacion().setValorAceptadoIps(null);
        this.getCmConciliacion().setPorcentajeAceptadoIps(null);
        this.getCmConciliacion().setObservacion(null);
        this.getCmConciliacion().setTipoEstadoRespuesta(0);
    }

    public void verConciliacionMasiva() {
        CmConciliacion conciliacion = this.getCmConciliacion();
        conciliacion.setValorFacturasGlosaInicialPrecalculado(BigDecimal.ZERO);
        conciliacion.setValorFacturasPendienteActualPrecalculado(BigDecimal.ZERO);
        conciliacion.setValorFacturasPrecalculado(BigDecimal.ZERO);
        conciliacion.setValorFacturasPendienteActualMasivo(BigDecimal.ZERO);
        conciliacion.setValorFacturasAceptadoIpsPrecalculado(BigDecimal.ZERO);
        conciliacion.setValorFacturasPagadoEpsPrecalculado(BigDecimal.ZERO);
        conciliacion.setRepresentanteEps("");
        conciliacion.setRepresentanteIps("");
        conciliacion.setObservacion("");
        setReporteConciliacion(null);

        for (CmFactura factura : getFacturasEncontradas()) {
            if (factura.getValorPendienteActual() != null
                    && factura.getValorPendienteActual().compareTo(BigDecimal.ZERO) > 0) {

                conciliacion.setValorFacturasPendienteActualPrecalculado(
                        conciliacion.getValorFacturasPendienteActualPrecalculado().
                                add(factura.getValorPendienteActual()).
                                setScale(4, RoundingMode.HALF_UP)
                );

                conciliacion.setValorFacturasPendienteActualMasivo(
                        conciliacion.getValorFacturasPendienteActualMasivo().
                                add(factura.getValorPendienteActual()).
                                setScale(4, RoundingMode.HALF_UP)
                );

                conciliacion.setValorFacturasAceptadoIpsPrecalculado(
                        conciliacion.getValorFacturasAceptadoIpsPrecalculado().
                                add(factura.getValorAceptadoIPS()).
                                setScale(4, RoundingMode.HALF_UP)
                );

                conciliacion.setValorFacturasPagadoEpsPrecalculado(
                        conciliacion.getValorFacturasPagadoEpsPrecalculado().
                                add(factura.getValorPagadoEPS()).
                                setScale(4, RoundingMode.HALF_UP)
                );
                asignacionValorPorcentaje(factura);
            }
        }

        actualizaBloqueoFacturas(true);
        this.setDoAccion(ACCION_CARGAR_CONCILIACIONES);
        if (!this.isError()) {
            PrimeFaces.current().resetInputs("frmResponderConciliaciones");
            PrimeFaces.current().ajax().update("frmResponderConciliaciones");
            PrimeFaces.current().executeScript("PF('dialogoGestionarConciliaciones').show()");
        }
        procesoFinal();
    }

    public void verReponderConciliacionMasiva() {
        this.getCmConciliacion().setTipoEstadoRespuesta(0);
        this.getCmConciliacion().setObservacion("");
        PrimeFaces.current().resetInputs("frmReponderConciliacionMasiva");
        PrimeFaces.current().ajax().update("frmReponderConciliacionMasiva");
        PrimeFaces.current().executeScript("PF('dialogoRegistrarConciliacionMasiva').show()");
    }

    public void responderConciliacionMasiva() {
        boolean validacionProceso = true;
        CmConciliacion conciliacion = this.getCmConciliacion();

        BigDecimal valorIPSTotal = conciliacion.getValorFacturasAceptadoIpsPrecalculado() == null
                ? new BigDecimal("0") : conciliacion.getValorFacturasAceptadoIpsPrecalculado();
        BigDecimal valorEPSTotal = conciliacion.getValorFacturasPagadoEpsPrecalculado() == null
                ? new BigDecimal("0") : conciliacion.getValorFacturasPagadoEpsPrecalculado();
        BigDecimal porcentajeIPS = conciliacion.getPorcentajeAceptadoIps() == null
                ? new BigDecimal("0") : conciliacion.getPorcentajeAceptadoIps();
        BigDecimal porcentajeEPS = conciliacion.getPorcentajePagadoEps() == null
                ? new BigDecimal("0") : conciliacion.getPorcentajePagadoEps();
        BigDecimal valorSumaReponder = new BigDecimal("0");

        String responsableEPS = conciliacion.getRepresentanteEps() == null ? "" : conciliacion.getRepresentanteEps();
        String responsableIPS = conciliacion.getRepresentanteIps() == null ? "" : conciliacion.getRepresentanteIps();
        this.getObjeto().setRepresentanteEps(responsableEPS);
        this.getObjeto().setRepresentanteIps(responsableIPS);

        if (conciliacion.getValorFacturasPendienteActualPrecalculado().compareTo(new BigDecimal("0")) == 0) {
            this.addError("El valor suma pendiente actual debe ser mayor a cero.");
            validacionProceso = false;
        }

        if (validacionProceso && (responsableEPS.equals("")
                || responsableIPS.equals(""))) {
            this.addError("Para realizar el proceso se debe ingresar el representante EPS y representante IPS ");
            validacionProceso = false;
        }

        if (validacionProceso && !validarLongitudRepresentantes(responsableEPS, responsableIPS)) {
            this.addError("La longitud del campo representante EPS y representante IPS deben ser mayor a 7 dígitos.");
            validacionProceso = false;
        }

        if (validacionProceso) {
            int facturasVerificadas = 0;
            int cantidadFacturasVerificar = getFacturasEncontradas().size();
            for (CmFactura factura : getFacturasEncontradas()) {
                if (CmFactura.TIPO_CALCULO_CONCILIACION_IND == factura.getTipoCalculoConciliacion()
                        || CmFactura.TIPO_CALCULO_CONCILIACION_MAS == factura.getTipoCalculoConciliacion()
                        || CmFactura.TIPO_CALCULO_CONCILIACION_CON == factura.getTipoCalculoConciliacion()) {
                    facturasVerificadas += 1;
                }
            }

            if (facturasVerificadas != cantidadFacturasVerificar) {
                this.addError("Todas las facturas deben tener el cálculo EPS o Aceptado IPS para poder continuar.");
                validacionProceso = false;
            }
        }

        if (validacionProceso) {
            conciliacion.setValorAceptadoIps(valorIPSTotal);
            conciliacion.setValorPagadoEps(valorEPSTotal);
            conciliacion.setPorcentajeAceptadoIps(porcentajeIPS);
            conciliacion.setPorcentajePagadoEps(porcentajeEPS);
            BigDecimal valorBaseComparacion = conciliacion.getValorFacturasPendienteActualPrecalculado();
            valorBaseComparacion = valorBaseComparacion == null ? new BigDecimal("0") : valorBaseComparacion.setScale(2, RoundingMode.HALF_UP);
            valorSumaReponder = valorSumaReponder.add(valorIPSTotal).
                    add(valorEPSTotal).setScale(2, RoundingMode.HALF_UP);
            if (valorSumaReponder.compareTo(valorBaseComparacion) > 0) {
                this.addError("La sumatoria de los valores ingresados no debe superar el valor suma pendiente actual.");
                validacionProceso = false;
            }
        }

        if (validacionProceso) {
            super.setAccion(ACCION_GUARDAR);
            getCargaMasivaConciliacionServicio().Accion(this);
        }

        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoRegistrarConciliacionMasiva').hide();");
            PrimeFaces.current().executeScript("PF('dialogoGestionarConciliaciones').hide();");
        } else {
            PrimeFaces.current().executeScript("PF('dialogoRegistrarConciliacionMasiva').hide();");
            PrimeFaces.current().resetInputs("frmResponderConciliaciones");
            PrimeFaces.current().ajax().update("frmResponderConciliaciones");
            procesoFinal();
        }
    }

    public void actualizarSumatoriaValoresConciliacionMasiva() {
        CmConciliacion conciliacion = this.getCmConciliacion();
        conciliacion.setValorFacturasAceptadoIpsPrecalculado(BigDecimal.ZERO);
        conciliacion.setValorFacturasPagadoEpsPrecalculado(BigDecimal.ZERO);
        conciliacion.setValorFacturasPendienteActualMasivo(BigDecimal.ZERO);

        for (CmFactura factura : getFacturasEncontradas()) {
            BigDecimal valorAceptadoIps = factura.getValorAceptadoIPS() == null
                    ? new BigDecimal(BigInteger.ZERO)
                    : factura.getValorAceptadoIPS();
            BigDecimal valorPagadoEps = factura.getValorPagadoEPS() == null
                    ? new BigDecimal(BigInteger.ZERO)
                    : factura.getValorPagadoEPS();

            conciliacion.setValorFacturasAceptadoIpsPrecalculado(
                    conciliacion.getValorFacturasAceptadoIpsPrecalculado().
                            add(valorAceptadoIps).
                            setScale(4, RoundingMode.HALF_UP)
            );

            conciliacion.setValorFacturasPagadoEpsPrecalculado(
                    conciliacion.getValorFacturasPagadoEpsPrecalculado().
                            add(valorPagadoEps).
                            setScale(4, RoundingMode.HALF_UP)
            );

            if (factura.getTipoCalculoConciliacion() != CmFactura.TIPO_CALCULO_CONCILIACION_IND) {
                conciliacion.setValorFacturasPendienteActualMasivo(
                        conciliacion.getValorFacturasPendienteActualMasivo().
                                add(factura.getValorPendienteActual()).
                                setScale(4, RoundingMode.HALF_UP)
                );
            }
        }
        PrimeFaces.current().resetInputs("frmResponderConciliaciones:panelgFacturaSumatoria");
        PrimeFaces.current().ajax().update("frmResponderConciliaciones:panelgFacturaSumatoria");
    }

    private void actualizaBloqueoFacturas(boolean bloquear) {
        this.setParamConsultaUtilitario(new ParamConsulta());
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_BLOQUEAR_FACTURA);
        String idFacturas = "";
        StringBuilder sb = new StringBuilder();
        for (CmFactura cmFactura : getFacturasEncontradas()) {
            sb.append(cmFactura.getId()).append(",");
        }
        if (sb.length() > 0) {
            idFacturas = sb.deleteCharAt(sb.length() - 1).toString();
            int idUsuarioSolicita = this.getConexion().getUsuario().getId();
            int idAsignacionUsuario = bloquear ? idUsuarioSolicita : 0;
            this.getParamConsultaUtilitario().setParametroConsulta1(idAsignacionUsuario);
            this.getParamConsultaUtilitario().setParametroConsulta2(idUsuarioSolicita);
            this.getParamConsultaUtilitario().setParametroConsulta3(idFacturas);
            getCargaMasivaConciliacionServicio().Accion(this);
        }
    }

    public boolean validarLongitudRepresentantes(String representanteEPS, String representanteIPS) {
        boolean esValidaLongitud = true;
        if ((representanteEPS != null && representanteIPS != null)) {
            if ((representanteEPS.length() <= 7) || (representanteIPS.length() <= 7)) {
                esValidaLongitud = false;
            }
        }
        return esValidaLongitud;
    }

    public ReporteConciliacion getReporteConciliacion() {
        return reporteConciliacion;
    }

    public void setReporteConciliacion(ReporteConciliacion reporteConciliacion) {
        this.reporteConciliacion = reporteConciliacion;
    }

    public List<ReporteConciliacion> getListaReporteConciliacion() {
        return listaReporteConciliacion;
    }

    public void setListaReporteConciliacion(List<ReporteConciliacion> listaReporteConciliacion) {
        this.listaReporteConciliacion = listaReporteConciliacion;
    }

    public StreamedContent generarReporteConciliacion() {
        StreamedContent streamedContent2 = null;
        InputStream is = null;
        try {
            listaReporteConciliacion = new ArrayList();
            super.setAccion(ACCION_ADICIONAL_1);
            getCargaMasivaConciliacionServicio().Accion(this);
            if (listaReporteConciliacion.size() > 0) {

                //Estrucutra de JasperReport
                is = getClass().getResourceAsStream("/reportes/conciliaciones.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReporteConciliacion);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name("Reporte.pdf").build();

            } else {
                this.addError("Error, no hay datos correctamente procesados(enviados a sap) para generar el reporte.");
                generarMensajes();
            }

        } catch (JRException ex) {
            streamedContent2 = null;
            this.addError("Error Reporte: " + ex.toString() + ex.getMessage());
            generarMensajes();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(CargaMasivaConciliacionBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return streamedContent2;
    }

    public StreamedContent generarReportePorConciliacion(CmConciliacion cmConciliacion) {
        StreamedContent streamedContent;
        int idConciliacion = cmConciliacion.getId();
        ReporteConciliacion reporte = new ReporteConciliacion();
        reporte.setId(idConciliacion);
        int idCmRadicado = Optional.ofNullable(cmConciliacion.getCmRadicado().getId()).orElse(0);
        reporte.setIntRadicacion(idCmRadicado);
        this.getObjeto().setIdConciliacionMasiva(idConciliacion);
        this.setReporteConciliacion(reporte);
        streamedContent = generarReporteConciliacion();
        if (streamedContent != null) {
            procesoFinal();
        }
        this.getObjeto().setIdConciliacionMasiva(null);
        return streamedContent;
    }

    public boolean habilitarDescargaReporteConciliacion(CmConciliacion objeto) {
        boolean habilitarGeneracion = false;
        if (objeto.getEstadoProceso() != null && CmConciliacion.ESTADO_REGISTRADA == objeto.getEstadoProceso()) {
            habilitarGeneracion = true;
        }
        return habilitarGeneracion;
    }

    public void verFormularioCargaMasiva() {
        getObjeto().setIdConciliacionMasiva(null);
        setObjeto(new CargaMasivaConciliacion());
        setListaIps(new HashMap<>());
        hayDescargaErrores = false;
        PrimeFaces.current().ajax().update("frmSubirCargaMasiva");
        PrimeFaces.current().executeScript("PF('dialogoSubirCargaMasiva').show()");
    }

    public void ejecutartRegistroSincronizacion(int idConciliacionMasiva) {
        this.getObjeto().setIdConciliacionMasiva(idConciliacionMasiva);
        super.setAccion(ACCION_ADICIONAL_2);
        getCargaMasivaConciliacionServicio().Accion(this);
        procesoFinal();
    }

    public String asignarColorFila(CmConciliacion cmConciliacion) {
        String color = "";
        if (cmConciliacion.isFacturasPendientes()) {
            color = "red";
        }
        return color;
    }
    
    public String asignarStyleSegunEstado(CmConciliacion cmConciliacion) {
        String style = "";
        if (cmConciliacion.isFacturasPendientes()) {
            style = "pi pi-circle-on red_circle";
        }
        return style;
    }
    
    public String asignarStyleSegunEstadoParaWsFactura(WsCmFactura wsFactura) {
        String style = "";
        if (wsFactura.getEstado() == WsCmFactura.ESTADO_RECONSTRUCCION) {
            style = "pi pi-circle-on red_circle";
        }
        return style;
    }

    public boolean habilitarDescargaReporteErrores(CmConciliacion cmConciliacion) {
        boolean habilitarGeneracion = false;
        if (cmConciliacion.isFacturasPendientes()) {
            habilitarGeneracion = true;
        }
        return habilitarGeneracion;
    }

    public void generarReporteErrores(CmConciliacion objeto) {
        this.getObjeto().setIdConciliacionMasiva(objeto.getId());
        CmRadicado radicado = Optional.ofNullable(objeto.getCmRadicado()).orElse(new CmRadicado());
        this.getObjeto().setIdRadicado(radicado.getId());
        super.setAccion(ACCION_ADICIONAL_4);
        getCargaMasivaConciliacionServicio().Accion(this);
        procesoFinal();
        this.getObjeto().setIdConciliacionMasiva(null);
    }

    public void verSincronizaciones(int idRadicado) {
        idRadicado = Optional.ofNullable(idRadicado).orElse(0);

        if (idRadicado > 0) {
            getObjeto().setIdRadicado(idRadicado);
            super.setAccion(ACCION_ADICIONAL_3);
            super.setDoAccion(ACCION_VER_TIPO_FUENTE_DATOS);
            getCargaMasivaConciliacionServicio().Accion(this);
            
            switch (getTipoFuenteDatos()) {
                case TIPO_FUENTE_DATOS_SINCRONIZACION_ENCABEZADO:
                    verSincronizacionEncabezado(idRadicado);
                    break;
                case TIPO_FUENTE_DATOS_WS_FACTURAS:
                    verWsCmFacturas(idRadicado);
                    break;
                default:
                    this.addMensaje("No sea han generado en estos momentos ws facturas para enviar a sap.");
                    this.generarMensajes();
                    break;
            }        
        } else {
            this.addError("Para ver ws facturas es necesario una radicación en envio sap, "
                    + "la cual no esta disponible en este momento");
            procesoFinal();
        }

    }

    public void verSincronizacionEncabezado(int idRadicado) {
        ParamConsulta param = new ParamConsulta();
        setParamConsultaSincEncabezado(param);
        getObjeto().setIdRadicado(idRadicado);
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_VER_ENCABEZADOS);
        inicializarTablaSincronizacionEncabezado();
        getCargaMasivaConciliacionServicio().Accion(this);
        if (!this.isError()) {
            PrimeFaces.current().resetInputs("frmVerSincronizacionEncabezado:tablaRegistrosEncabezado");
            PrimeFaces.current().ajax().update("frmVerSincronizacionEncabezado");
            PrimeFaces.current().executeScript("PF('dialogoVerSincronizacionEncabezado').show()");
        }
        procesoFinal();
    }

    public void verCuentasContables(int idSincronizazionE) {
        ParamConsulta param = new ParamConsulta();
        param.setParametroConsulta1(idSincronizazionE);
        setParamConsultaSincDetalle(param);
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_VER_CUENTAS_CONTABLES);
        inicializarTablaSincronizacionDetalles();
        getCargaMasivaConciliacionServicio().Accion(this);
        if (!this.isError()) {
            PrimeFaces.current().ajax().update("frmVerCuentasContables");
            PrimeFaces.current().executeScript("PF('dialogoVerCuentasContables').show()");
        }
        procesoFinal();
    }

    public void verWsCmFacturas(int idCmRadicado) {
        inicializarTablaWsFacturas(idCmRadicado);
        limpiarFiltrosTablas("frmVerWsFacturas:tablaWsFacturas");
        PrimeFaces.current().resetInputs("frmVerWsFacturas");
        PrimeFaces.current().ajax().update("frmVerWsFacturas");
        PrimeFaces.current().executeScript("PF('dialogoVerWsFacturas').show()");
        procesoFinal();
    }

    public void verWsCmFacturaDetalles(int idCmFactura) {
        inicializarTablaWsFacturaDetalles(idCmFactura);
        limpiarFiltrosTablas("frmVerWsFacturaDetalles:tablaWsFacturaDetalles");
        PrimeFaces.current().resetInputs("frmVerWsFacturaDetalles");
        PrimeFaces.current().ajax().update("frmVerWsFacturaDetalles");
        PrimeFaces.current().executeScript("PF('dialogoVerWsFacturaDetalles').show()");
        procesoFinal();
    }

    public void inicializarTablaSincronizacionEncabezado() {
        lazySincEncabezado = new LazyDataModel<CmSincronizacionEncabezado>() {
            private List<CmSincronizacionEncabezado> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaSincEncabezado().getCantidadRegistros();
            }

            @Override
            public List<CmSincronizacionEncabezado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaSincEncabezado().setPrimerRegistro(primerRegistro);
                getParamConsultaSincEncabezado().setRegistrosPagina(registrosPagina);
                getParamConsultaSincEncabezado().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaSincEncabezado().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarSincEncabezado();
                lista = getRegistrosSincEncabezado();
                setRowCount(getParamConsultaSincEncabezado().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmSincronizacionEncabezado sincronizacionEncabezado) {
                return sincronizacionEncabezado.getId().toString();
            }

            @Override
            public CmSincronizacionEncabezado getRowData(String encabezadoId) {
                Integer id = Integer.valueOf(encabezadoId);
                for (CmSincronizacionEncabezado cmsincronizacionEn : lista) {
                    if (id.equals(cmsincronizacionEn.getId())) {
                        return cmsincronizacionEn;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaSincronizacionDetalles() {
        lazySincDetalle = new LazyDataModel<CmSincronizacionDetalle>() {
            private List<CmSincronizacionDetalle> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaSincDetalle().getCantidadRegistros();
            }

            @Override
            public List<CmSincronizacionDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaSincDetalle().setPrimerRegistro(primerRegistro);
                getParamConsultaSincDetalle().setRegistrosPagina(registrosPagina);
                getParamConsultaSincDetalle().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaSincDetalle().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarSincDetalle();
                lista = getRegistrosSincDetalle();
                setRowCount(getParamConsultaSincDetalle().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmSincronizacionDetalle sincronizacionD) {
                return sincronizacionD.getId().toString();
            }

            @Override
            public CmSincronizacionDetalle getRowData(String personaId) {
                Integer id = Integer.valueOf(personaId);
                for (CmSincronizacionDetalle sincronizacionD : lista) {
                    if (id.equals(sincronizacionD.getId())) {
                        return sincronizacionD;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaWsFacturas(int idCmRadicado) {

        getParamConsulta(0).setParametroConsulta1(idCmRadicado);

        lazyWsCmFacturas = new LazyDataModel<WsCmFactura>() {
            private List<WsCmFactura> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<WsCmFactura> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarWSFacturas();
                lista = getRegistrosWsCmFacturas();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(WsCmFactura respuesta) {
                return respuesta.getId().toString();
            }

            @Override
            public WsCmFactura getRowData(String respuestaId) {
                Integer id = Integer.valueOf(respuestaId);
                for (WsCmFactura wsTransaccion : lista) {
                    if (id.equals(wsTransaccion.getId())) {
                        return wsTransaccion;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaWsFacturaDetalles(int idWsCmFactura) {

        getParamConsulta(1).setParametroConsulta1(idWsCmFactura);

        lazyWsCmFacturaDetalles = new LazyDataModel<WsCmFacturaDetalle>() {
            private List<WsCmFacturaDetalle> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<WsCmFacturaDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarWSCmFacturaDetalles();
                lista = getRegistrosWsCmFacturaDetalles();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(WsCmFacturaDetalle respuesta) {
                return respuesta.getId().toString();
            }

            @Override
            public WsCmFacturaDetalle getRowData(String respuestaId) {
                Integer id = Integer.valueOf(respuestaId);
                for (WsCmFacturaDetalle wsTransaccion : lista) {
                    if (id.equals(wsTransaccion.getId())) {
                        return wsTransaccion;
                    }
                }
                return null;
            }
        };
    }

    public void verAdjuntos(int id) {
        getObjeto().setIdConciliacionMasiva(id);
        super.setAccion(ACCION_ADICIONAL_5);
        getCargaMasivaConciliacionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerAdjuntos:pAdjuntos");
        PrimeFaces.current().executeScript("PF('dialogoVerAdjuntos').show()");
        procesoFinal();
    }

    public void descargarAdjunto(com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion adj) throws IOException {
        String rutaCompleta = adj.getArchivoRuta() + adj.getArchivoNombre();
        FileInputStream fis = null;
        OutputStream output = null;
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + adj.getArchivo() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("application/txt");
            } else {
                throw new Exception();
            }
            output = ec.getResponseOutputStream();
            output.write(exportContent);
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (output != null) {
                output.close();
            }
        }
        crearLog("Conciliacion - Descargar Adjuntos", getObjeto().toString());
        procesoFinal();
    }

    private void limpiarFiltrosTablas(String nombrefrmTabla) {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(nombrefrmTabla);
        dataTable.reset();
    }

}
