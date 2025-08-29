/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.auditoria.concurrente.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaCierre;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaCierreSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.auditoria.concurrente.servicio.AucCargaMasivaValorServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_VER;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
 * @author idbohorquez
 */
@ManagedBean
@ViewScoped
public class AucCargaMasivaValorBean extends Url {

    @Autowired
    private AucCargaMasivaValorServicioIface aucCargaMasivaValorServicio;

    private LazyDataModel<AucCargaCierre> lazyAucCargacierre;
    private LazyDataModel<AucCargaCierreSuceso> lazyFallos;
    private List<AucCargaCierre> registros;
    private List<AucCargaCierreSuceso> listaFallos;

    private AucCargaCierre objeto;

    public AucCargaMasivaValorBean() {
        objeto = new AucCargaCierre();
        Modulo _mod = super.validarModulo(Modulo.ID_AUC_CARGA_MASIVA_CIERRE);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyAucCargacierre = new LazyDataModel<AucCargaCierre>() {

                private List<AucCargaCierre> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AucCargaCierre> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AucCargaCierre objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AucCargaCierre getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AucCargaCierre objeto : lista) {
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
        getAucCargaMasivaValorServicio().cargaInicial(this);
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    //GETTER AND SETTERS    
    public void setAucCargaMasivaValorServicio(AucCargaMasivaValorServicioIface aucCargaMasivaValorServicio) {
        this.aucCargaMasivaValorServicio = aucCargaMasivaValorServicio;
    }

    public AucCargaMasivaValorServicioIface getAucCargaMasivaValorServicio() {
        return aucCargaMasivaValorServicio;
    }

    public AucCargaCierre getObjeto() {
        return objeto;
    }

    public void setObjeto(AucCargaCierre objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<AucCargaCierre> getLazyAucCargacierre() {
        return lazyAucCargacierre;
    }

    public void setLazyAucCargacierre(LazyDataModel<AucCargaCierre> lazyAucCargacierre) {
        this.lazyAucCargacierre = lazyAucCargacierre;
    }

    public List<AucCargaCierre> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AucCargaCierre> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AucCargaCierreSuceso> getLazyFallos() {
        return lazyFallos;
    }

    public void setLazyFallos(LazyDataModel<AucCargaCierreSuceso> lazyFallos) {
        this.lazyFallos = lazyFallos;
    }

    public List<AucCargaCierreSuceso> getListaFallos() {
        return listaFallos;
    }

    public void setListaFallos(List<AucCargaCierreSuceso> listaFallos) {
        this.listaFallos = listaFallos;
    }

    //METODOS
    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAucCargaMasivaValorServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        getAucCargaMasivaValorServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(Url.ACCION_GUARDAR);
        getAucCargaMasivaValorServicio().Accion(this);
        if (super.isError()) {
            PrimeFaces.current().ajax().update("frmCrear");
        }
        procesoFinal();
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            UploadedFile archivo = event.getFile();
            getObjeto().setAdjuntoStream(archivo.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            Date fecha = new Date();
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            String ext = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());

            String nombreArchivo = "auc_carga_cierre_" + getObjeto().FORMATO_EXEL.format(fecha) + ext;
            getObjeto().setNombreArchivo(nombreAdjunto);
            getObjeto().setArchivo(nombreArchivo);
            guardar();
        } catch (IOException ex) {
            addError("Hubo un Fallo al cargar archivo, favor contactar al adminitrador");
        }
    }

    public void refrescarFallos() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getAucCargaMasivaValorServicio().Accion(this);
        procesoFinal();
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
            getAucCargaMasivaValorServicio().Accion(this);
            // adicionamos encabezado en la primera línea
            texto += "tipo,descripción,fila,columna\n";
            if (this.getObjeto().getAucCargaFalloList().size() > 0) {
                //Stream streamDetalle = this.listaDetalleCarga.stream();
                for (AucCargaCierreSuceso fallo : getObjeto().getAucCargaFalloList()) {
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

    public void consultarSucesos(int id) {
        try {
            lazyFallos = new LazyDataModel<AucCargaCierreSuceso>() {

                private List<AucCargaCierreSuceso> listaFallos;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AucCargaCierreSuceso> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AucCargaCierreSuceso ips) {
                    return ips.getId().toString();
                }

                @Override
                public AucCargaCierreSuceso getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AucCargaCierreSuceso objeto : listaFallos) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            setObjeto(new AucCargaCierre(id));
            DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVerSucesos:tablaSucesos");
            dataTable2.setFilterBy(null);
            dataTable2.reset();
            PrimeFaces.current().resetInputs("frmVerSucesos:tablaSucesos");
            PrimeFaces.current().ajax().update("frmVerSucesos:hPanelSucesos");
            this.setParamConsulta(new ParamConsulta());
            PrimeFaces.current().ajax().update("frmVerSucesos");
            PrimeFaces.current().executeScript("PF('dialogoVerSucesos').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void descargarArchivo(AucCargaCierre carga) {
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

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_CREAR:
                PrimeFaces.current().ajax().update("frmCrear");
                PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                break;
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_GUARDAR:
                PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
                PrimeFaces.current().ajax().update("frmCargaMasivaCierre");
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmCargaMasivaCierre");
                break;
            case Url.ACCION_LISTAR:
                PrimeFaces.current().ajax().update("frmCargaMasivaCierre");
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                break;
        }
        generarMensajes();
    }

}
