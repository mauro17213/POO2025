/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.auditoria.concurrente.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCarga;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaFallo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.auditoria.concurrente.servicio.AucCargaMasivaServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author sgiraldov
 */
@ManagedBean
@ViewScoped
public class AucCargaMasivaBean extends Url {

    @Autowired
    private AucCargaMasivaServicioIface aucCargaMasivaServicio;
    private AucCarga objeto;
    private List<AucCarga> registros;
    private LazyDataModel<AucCarga> lazyAucCargaMasiva;

    private LazyDataModel<AucCargaFallo> lazyFallos;
    private List<AucCargaFallo> listaFallos;

    private final static SimpleDateFormat FORMATO_EXEL = new SimpleDateFormat("YYYYMMddHHmmss");

    public AucCargaMasivaBean() {
        this.objeto = new AucCarga();
        this.registros = new ArrayList<>();
        this.listaFallos = new ArrayList<>();
        Modulo _mod = super.validarModulo(Modulo.ID_AUC_CARGA_MASIVA);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyAucCargaMasiva = new LazyDataModel<AucCarga>() {

                private List<AucCarga> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AucCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(0).setPrimerRegistro(primerRegistro);
                    getParamConsulta(0).setRegistrosPagina(registrosPagina);
                    getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta(0).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(AucCarga objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AucCarga getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AucCarga objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
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
        getAucCargaMasivaServicio().cargasInicial(this);
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public AucCargaMasivaServicioIface getAucCargaMasivaServicio() {
        return aucCargaMasivaServicio;
    }

    public void setAucCargaMasivaServicio(AucCargaMasivaServicioIface aucCargaMasivaServicio) {
        this.aucCargaMasivaServicio = aucCargaMasivaServicio;
    }

    public AucCarga getObjeto() {
        return objeto;
    }

    public void setObjeto(AucCarga objeto) {
        this.objeto = objeto;
    }

    public List<AucCarga> getRegistros() {
        return registros;
    }

    public void setRegistro(List<AucCarga> registro) {
        this.registros = registro;
    }

    public LazyDataModel<AucCarga> getLazyAucCargaMasiva() {
        return lazyAucCargaMasiva;
    }

    public void setLazyAucCargaMasiva(LazyDataModel<AucCarga> lazyAucCargaMasiva) {
        this.lazyAucCargaMasiva = lazyAucCargaMasiva;
    }

    public LazyDataModel<AucCargaFallo> getLazyFallos() {
        return lazyFallos;
    }

    public void setLazyFallos(LazyDataModel<AucCargaFallo> lazyFallos) {
        this.lazyFallos = lazyFallos;
    }

    public List<AucCargaFallo> getListaFallos() {
        return listaFallos;
    }

    public void setListaFallos(List<AucCargaFallo> listaFallos) {
        this.listaFallos = listaFallos;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAucCargaMasivaServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        getAucCargaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER);
        getAucCargaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(Url.ACCION_GUARDAR);
        getAucCargaMasivaServicio().Accion(this);
        if (super.isError()) {
            PrimeFaces.current().ajax().update("frmCrear");
        }
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
                    PrimeFaces.current().ajax().update("frmCargaMasivas");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_CREAR:
                    PrimeFaces.current().ajax().update("frmCrear");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                    break;
                case Url.ACCION_VER:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;

            }
        }
        generarMensajes();
    }

    public StreamedContent generarArchivoResultados(int id) {
        StreamedContent streamedContent2 = null;
        int indiceExtension;
        String extension;
        String nombre;
        String texto = "";
        try {
            getObjeto().setId(id);
            super.setAccion(ACCION_VER);
            super.setDoAccion(ACCION_VER);
            getAucCargaMasivaServicio().Accion(this);
            // adicionamos encabezado en la primera línea
            texto += "tipo,descripción,fila,columna\n";
            if (getObjeto().getAucCargaFalloList().size() > 0) {
                //Stream streamDetalle = this.listaDetalleCarga.stream();
                for (AucCargaFallo fallo : getObjeto().getAucCargaFalloList()) {
                    texto += fallo.getTipo() + ", "
                            + fallo.getDescripcion() + ", "
                            + fallo.getFila() + ", "
                            + fallo.getColumna()
                            + "\n";
                }
                // construimos el nombre del archivo
                indiceExtension = getObjeto().getArchivo().lastIndexOf(".");
                nombre = getObjeto().getArchivo().substring(0, indiceExtension) + "_" + getObjeto().getId() + ".txt";
                //debemos generar la cadena de bytes a partir de los registros en la lista
                byte[] bytes = texto.getBytes();
                //byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(nombre).build();
            } else {
                addError("No se encontraron datos para generar el archivo");
                generarMensajes();
            }
        } catch (Exception ex) {
            streamedContent2 = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamedContent2;
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            if (this.objeto.getTipo() != 0) {
                UploadedFile archivo = event.getFile();
                getObjeto().setAdjuntoStream(archivo.getInputStream());
                String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
                Date fecha = new Date();
                int indiceExtension = nombreAdjunto.lastIndexOf(".");
                String ext = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());

                String nombreArchivo = "";
                if (this.objeto.getTipo() == AucCarga.TIPO_CENSO) {
                    nombreArchivo = "auc_carga_censo_" + FORMATO_EXEL.format(fecha) + ext;
                } else {
                    nombreArchivo = "auc_carga_seguimiento_" + FORMATO_EXEL.format(fecha) + ext;
                }
                getObjeto().setNombreArchivo(nombreAdjunto);
                getObjeto().setArchivo(nombreArchivo);
                guardar();
            } else {
                this.addError("Tipo Archivo: Este campo es obligatorio.");
                procesoFinal();
            }
        } catch (IOException ex) {
            addError("Hubo un Fallo al cargar archivo, favor contactar al adminitrador");
        }
    }

    public void consultarSucesos(int id) {
        try {
            lazyFallos = new LazyDataModel<AucCargaFallo>() {

                private List<AucCargaFallo> listaFallos;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AucCargaFallo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(1).setPrimerRegistro(primerRegistro);
                    getParamConsulta(1).setRegistrosPagina(registrosPagina);
                    getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarFallos();
                    listaFallos = getListaFallos();
                    setRowCount(getParamConsulta(1).getCantidadRegistros());
                    return listaFallos;
                }

                @Override
                public String getRowKey(AucCargaFallo ips) {
                    return ips.getId().toString();
                }

                @Override
                public AucCargaFallo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AucCargaFallo objeto : listaFallos) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            setObjeto(new AucCarga(id));
            DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVerSucesos:tablaSucesos");
            dataTable2.setFilterBy(null);
            dataTable2.reset();
            PrimeFaces.current().resetInputs("frmVerSucesos:tablaSucesos");
            //PrimeFaces.current().ajax().update("frmVerSucesos:hPanelSucesos");
            this.setParamConsulta(new ParamConsulta());
            PrimeFaces.current().ajax().update("frmVerSucesos");
            PrimeFaces.current().executeScript("PF('dialogoVerSucesos').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void refrescarFallos() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getAucCargaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public void descargarArchivo(AucCarga carga) {
        String rutaCompleta = carga.getRuta() + carga.getArchivo();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + carga.getNombreArchivo() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            ec.setResponseContentType("application/vnd.ms-excel");
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public void cargarDato() {

    }

}
