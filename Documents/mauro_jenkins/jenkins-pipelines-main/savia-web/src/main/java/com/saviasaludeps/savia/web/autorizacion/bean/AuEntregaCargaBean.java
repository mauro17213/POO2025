/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.autorizacion.AuEntregaCarga;
import com.saviasaludeps.savia.dominio.autorizacion.AuEntregaCargaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuEntregaCargaIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author iavenegas
 */
@ManagedBean
@ViewScoped
public class AuEntregaCargaBean extends Url {

    private AuEntregaCargaIface auEntregaCargaServicio;
    private AuEntregaCarga objeto;
    private List<AuEntregaCarga> registros;
    private LazyDataModel<AuEntregaCarga> lazyAuEntregaCarga;

    private LazyDataModel<AuEntregaCargaDetalle> lazyDetalle;
    private List<AuEntregaCargaDetalle> listaDetalle;

    private final static SimpleDateFormat FORMATO_EXEL = new SimpleDateFormat("YYYYMMddHHmmss");

    public AuEntregaCargaIface getAuEntregaCargaServicio() {
        return auEntregaCargaServicio;
    }

    public void setAuEntregaCargaServicio(AuEntregaCargaIface auEntregaCargaServicio) {
        this.auEntregaCargaServicio = auEntregaCargaServicio;
    }

    public AuEntregaCarga getObjeto() {
        return objeto;
    }

    public void setObjeto(AuEntregaCarga objeto) {
        this.objeto = objeto;
    }

    public List<AuEntregaCarga> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuEntregaCarga> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuEntregaCarga> getLazyAuEntregaCarga() {
        return lazyAuEntregaCarga;
    }

    public void setLazyAuEntregaCarga(LazyDataModel<AuEntregaCarga> lazyAuEntregaCarga) {
        this.lazyAuEntregaCarga = lazyAuEntregaCarga;
    }

    public LazyDataModel<AuEntregaCargaDetalle> getLazyDetalle() {
        return lazyDetalle;
    }

    public void setLazyDetalle(LazyDataModel<AuEntregaCargaDetalle> lazyDetalle) {
        this.lazyDetalle = lazyDetalle;
    }

    public List<AuEntregaCargaDetalle> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<AuEntregaCargaDetalle> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public AuEntregaCargaBean() {
        this.objeto = new AuEntregaCarga();
        Modulo _mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_CARGA_MASIVA_ENTREGA);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (super.getConexion().getEmpresa().isAdministradora()) {
            super.getParamConsulta(0).setEmpresaId(null);
        } else {
            super.getParamConsulta(0).setEmpresaId(super.getConexion().getEmpresa().getId());
        }
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyAuEntregaCarga = new LazyDataModel<AuEntregaCarga>() {

                private List<AuEntregaCarga> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AuEntregaCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AuEntregaCarga objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AuEntregaCarga getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuEntregaCarga objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
        }
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAuEntregaCargaServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        getAuEntregaCargaServicio().Accion(this);
        //2023-12-19 jyperez configuramos el campo por defecto en Procedimiento
        this.objeto.setTipoTecnologia(AuEntregaCarga.TIPO_TECNOLOGIA_PROCEDIMIENTO);
        procesoFinal();
    }

    public void guardar() {
        if (this.objeto.getAdjuntoStream() != null) {
            super.setAccion(Url.ACCION_GUARDAR);
            getAuEntregaCargaServicio().Accion(this);
            if (super.isError()) {
                PrimeFaces.current().ajax().update("frmCrear");
            }
            procesoFinal();
        } else {
            this.addError("No se ha cargado el archivo.");
            procesoFinal();
        }
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

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            if (this.objeto.getTipoTecnologia()!= 0 ) {
                UploadedFile archivo = event.getFile();
                getObjeto().setAdjuntoStream(archivo.getInputStream());
                String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
                Date fecha = new Date();
                int indiceExtension = nombreAdjunto.lastIndexOf(".");
                String ext = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());

                String nombreArchivo = "au_carga_entrega_" + FORMATO_EXEL.format(fecha) + ext;
                getObjeto().setNombre(nombreAdjunto);
                getObjeto().setArchivo(nombreArchivo);
                guardar();
            } else {
                this.addError("Tipo Tecnología: Este campo es obligatorio.");
                procesoFinal();
            }
        } catch (IOException ex) {
            addError("Hubo un Fallo al cargar archivo, favor contactar al adminitrador");
        }
    }
    
    public void cargarDato() {
        
    }

    public void consultarSucesos(int id) {
        try {
            lazyDetalle = new LazyDataModel<AuEntregaCargaDetalle>() {

                private List<AuEntregaCargaDetalle> listaFallos;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AuEntregaCargaDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(1).setPrimerRegistro(primerRegistro);
                    getParamConsulta(1).setRegistrosPagina(registrosPagina);
                    getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarDetalles();
                    listaFallos = getListaDetalle();
                    setRowCount(getParamConsulta(1).getCantidadRegistros());
                    return listaFallos;
                }

                @Override
                public String getRowKey(AuEntregaCargaDetalle ips) {
                    return ips.getId().toString();
                }

                @Override
                public AuEntregaCargaDetalle getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuEntregaCargaDetalle objeto : listaFallos) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            setObjeto(new AuEntregaCarga(id));
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

    public void refrescarDetalles() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getAuEntregaCargaServicio().Accion(this);
        procesoFinal();
    }

    public void descargarArchivo(AuEntregaCarga carga) {
        String rutaCompleta = carga.getRuta() + carga.getArchivo();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
//            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + carga.getNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            ec.setResponseContentType("application/vnd.ms-excel");
            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
                output.close();
            }
            fc.responseComplete();
        } catch (IOException e) {
            this.addError("El archivo ya no existe por que supero el tiempo de retención");
        }
        procesoFinal();
    }

}
