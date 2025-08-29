/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmInforme;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio.ReporteCuentasMedicasServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author admin
 */
@Named("reporteCuentasMedicasBean")
@ViewScoped
public class ReporteCuentasMedicasBean extends Url {

    @Autowired
    private ReporteCuentasMedicasServicioIface reporteCuentasMedicasServicio;
    private CmInforme objeto;
    public static final int PERIODO_MAXIMO_BUSQUEDA_RADICACION = 6;
    public static final int PERIODO_MAXIMO_BUSQUEDA_CUENTAS_MEDICAS = 12;

    private LazyDataModel<CmInforme> lazyInformes;
    private List<CmInforme> registros;
    private ParamConsulta paramConsultaUtilitario;

    private String mensajeGeneral;

    public CmInforme getObjeto() {
        return objeto;
    }

    public void setObjeto(CmInforme objeto) {
        this.objeto = objeto;
    }

    public ReporteCuentasMedicasServicioIface getReporteCuentasMedicasServicio() {
        return reporteCuentasMedicasServicio;
    }

    public void setReporteCuentasMedicasServicio(ReporteCuentasMedicasServicioIface reporteCuentasMedicasServicio) {
        this.reporteCuentasMedicasServicio = reporteCuentasMedicasServicio;
    }

    public LazyDataModel<CmInforme> getLazyInformes() {
        return lazyInformes;
    }

    public void setLazyInformes(LazyDataModel<CmInforme> lazyInformes) {
        this.lazyInformes = lazyInformes;
    }

    public List<CmInforme> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmInforme> registros) {
        this.registros = registros;
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

    public String getMensajeGeneral() {
        return mensajeGeneral;
    }

    public void setMensajeGeneral(String mensajeGeneral) {
        this.mensajeGeneral = mensajeGeneral;
    }

    public ReporteCuentasMedicasBean() {
        this.objeto = new CmInforme();
        Modulo mod = super.validarModulo(Modulo.ID_FACTURA_REPORTES);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());

        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyInformes = new LazyDataModel<CmInforme>() {
                private List<CmInforme> informes;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsultaUtilitario().getCantidadRegistros();
                }

                @Override
                public List<CmInforme> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsultaUtilitario().setPrimerRegistro(primerRegistro);
                    getParamConsultaUtilitario().setRegistrosPagina(registrosPagina);
                    getParamConsultaUtilitario().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsultaUtilitario().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    generarMensajes();
                    informes = getRegistros();
                    setRowCount(getParamConsultaUtilitario().getCantidadRegistros());
                    return informes;
                }

                @Override
                public String getRowKey(CmInforme conciliacion) {
                    return conciliacion.getId().toString();
                }

                @Override
                public CmInforme getRowData(String conciliacionId) {
                    Integer id = Integer.valueOf(conciliacionId);
                    for (CmInforme informe : informes) {
                        if (id.equals(informe.getId())) {
                            return informe;
                        }
                    }
                    return null;
                }
            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        listar();
    }

    @PreDestroy
    public void preDestroy() {
        this.objeto = null;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getReporteCuentasMedicasServicio().Accion(this);
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
                crearLog("Reporte C Medicas", getObjeto().toString());
                PrimeFaces.current().ajax().update("frmConsultaInformes");
                break;
            case Url.ACCION_CREAR:
                crearLog("Crear Reporte CM", getObjeto().toString());
                PrimeFaces.current().ajax().update("frmConsultaInformes");
                break;
            case Url.ACCION_LISTAR:
                crearLog("Listar Reportes CM", getObjeto().toString());
                PrimeFaces.current().ajax().update("frmConsultaInformes");
                break;
        }
        generarMensajes();
    }

    public void mostrarFormularioSegunTipoReporte() {
        CmInforme informe = new CmInforme();
        informe.setTipo(this.getObjeto().getTipo());
        this.setObjeto(informe);
        PrimeFaces.current().ajax().update("frmGenerarReporte:panelBuscarReportes");
        PrimeFaces.current().resetInputs("frmGenerarReporte:panelBuscarReportes");
    }

    public void generarReporte() {
        if (validar()) {
            super.setAccion(ACCION_CREAR);
            getReporteCuentasMedicasServicio().Accion(this);
        }
        if (!this.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoGenerarReporte').hide()");
        }
        procesoFinal();
    }

    public void limpiarFormularioBusqueda() {
        this.setObjeto(new CmInforme());
        PrimeFaces.current().ajax().update("frmGenerarReporte:panelBuscarReportes");
        PrimeFaces.current().resetInputs("frmGenerarReporte:panelBuscarReportes");
    }

    public void clearViewScopedBeans() {
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("reporteCuentasMedicasBean");
    }

    public void verMensajeGeneral(String mensajeGeneral) {
        this.setMensajeGeneral(mensajeGeneral);
        PrimeFaces.current().ajax().update("frmGeneral");
        PrimeFaces.current().resetInputs("frmGeneral");
        PrimeFaces.current().executeScript("PF('dlgGeneral').show()");
    }

    public boolean validar() {
        boolean esValida = true;

        if (this.getObjeto().getFechaDesde() != null && this.getObjeto().getFechaHasta() != null) {
            this.getParamConsulta().setParametroConsulta1(this.getObjeto().getFechaDesde());
            this.getParamConsulta().setParametroConsulta2(this.getObjeto().getFechaHasta());
        } else {
            esValida = false;
            this.addError("Por favor ingrese las fechas de radicación.");
        }

        if (this.getObjeto().getNit() != null && !this.getObjeto().getNit().equals("")) {
            if (this.getObjeto().getNit().trim().length() > 4) {
                this.getParamConsulta().setParametroConsulta3(this.getObjeto().getNit());
            } else {
                esValida = false;
                this.addError("El Nit debe tener más de 4 caracteres.");
            }
        }

        if (this.getObjeto().getIps() != null && !this.getObjeto().getIps().equals("")) {
            if (this.getObjeto().getIps().trim().length() > 4) {
                this.getParamConsulta().setParametroConsulta4(this.getObjeto().getIps());
            } else {
                esValida = false;
                this.addError("La Ips debe tener más de 4 caracteres.");
            }
        }

        return esValida;
    }

    public boolean validarFechaInicioMenor() {
        boolean isValido = true;
        if (this.getObjeto().getFechaDesde().after(this.getObjeto().getFechaHasta())) {
            this.addError("La fecha inicial de radicación debe ser menor que fecha final de radicación");
            isValido = false;
        }
        return isValido;
    }

    public boolean validarExisenciaNitIps() {
        boolean isValido = true;
        if (this.getObjeto().getNit().equals("") && this.getObjeto().getIps().equals("")) {
            this.addError(" Para realizar la búsqueda debe incluir el Nit o Ips. ");
            isValido = false;
        }
        return isValido;
    }

    public boolean validarAnios() {
        boolean isValido = true;
        Calendar fechaInicioCalendar = new GregorianCalendar();
        fechaInicioCalendar.setTime(this.getObjeto().getFechaDesde());
        Calendar fechaFinCalendar = new GregorianCalendar();
        fechaFinCalendar.setTime(this.getObjeto().getFechaHasta());

        int diferenciaAnios = fechaFinCalendar.get(Calendar.YEAR) - fechaInicioCalendar.get(Calendar.YEAR);
        int diferenciaMeses = diferenciaAnios * 12
                + fechaFinCalendar.get(Calendar.MONTH) - fechaInicioCalendar.get(Calendar.MONTH);

        if (isValido && PERIODO_MAXIMO_BUSQUEDA_CUENTAS_MEDICAS < diferenciaMeses) {
            isValido = false;
            this.addError(" El período de búsqueda no debe superar los " + PERIODO_MAXIMO_BUSQUEDA_CUENTAS_MEDICAS + " meses. ");
        }

        return isValido;
    }

    public void verGeneracionInforme() {
        this.setObjeto(new CmInforme());
        PrimeFaces.current().resetInputs("frmGenerarReporte");
        PrimeFaces.current().ajax().update("frmGenerarReporte");
        PrimeFaces.current().executeScript("PF('dialogoGenerarReporte').show()");
        procesoFinal();
    }

    public boolean habilitarDescarga(CmInforme obj) {
        boolean deshabilitar = false;
        int estado = obj.getEstado();
        if (estado == CmInforme.ESTADO_PROCESO
                || estado == CmInforme.ESTADO_ERROR || obj.getRegistros() <= 0) {
            deshabilitar = true;
        }
        return deshabilitar;
    }

    public boolean validarEstadoError(int estado) {
        boolean deshabilitar = true;
        if (estado != CmInforme.ESTADO_ERROR) {
            deshabilitar = false;
        }
        return deshabilitar;
    }

    public StreamedContent generarArchivoReporte(CmInforme obj) {
        StreamedContent streamedContent = null;
        try {
            String archivo = obj.getRuta() + obj.getArchivo();
            Path path = Paths.get(archivo);
            byte[] bytes = Files.readAllBytes(path);
            InputStream stream = new ByteArrayInputStream(bytes);
            stream.mark(0);
            streamedContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(obj.getArchivo()).build();
        } catch (IOException ex) {
            streamedContent = null;
            addError("Ocurrió un error descargando el archivo desde la ruta especificada. {" + ex.toString() + "}");
            generarMensajes();
        }
        return streamedContent;
    }

}
