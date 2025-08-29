package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Carga;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCarga;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCargaDetalle;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCargaSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuCargaMasivaSinAutorizacionServicioIface;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class AuCargaMasivaSinAutorizacionBean extends Url {
    
    @Autowired
    private AuCargaMasivaSinAutorizacionServicioIface auCargaMasivaSinAutorizacionServicio;
    private AuNoSolicitudCarga objeto;
    
    //lazy
    private List<AuNoSolicitudCarga> registros;
    private LazyDataModel<AuNoSolicitudCarga> lazyAuCargaMasivaSinAutorizaciones;
    
    private List<AuNoSolicitudCargaSuceso> listaSucesos;
    private LazyDataModel<AuNoSolicitudCargaSuceso> lazySucesos;
    private List<AuNoSolicitudCargaDetalle> listaDetalles;
    private LazyDataModel<AuNoSolicitudCargaDetalle> lazyDetalles;

    private List<Maestro> listaEstadoCarga;
    private HashMap<Integer, Maestro> hashEstadoCarga;

    public AuCargaMasivaSinAutorizacionBean() {
        this.objeto = new AuNoSolicitudCarga();
        Modulo mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_CARGA_MASIVA_SIN_AUTORIZACIONES);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (super.getConexion().getEmpresa().isAdministradora()) {
            super.getParamConsulta().setEmpresaId(null);
        } else {
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        }
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyAuCargaMasivaSinAutorizaciones = new LazyDataModel<AuNoSolicitudCarga>() {
                private List<AuNoSolicitudCarga> listaCargas;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AuNoSolicitudCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaCargas = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaCargas;
                }

                @Override
                public String getRowKey(AuNoSolicitudCarga solicitudCarga) {
                    return solicitudCarga.getId().toString();
                }

                @Override
                public AuNoSolicitudCarga getRowData(String solicitudCargaId) {
                    Integer id = Integer.valueOf(solicitudCargaId);
                    for (AuNoSolicitudCarga solicitudCarga : listaCargas) {
                        if (id.equals(solicitudCarga.getId())) {
                            return solicitudCarga;
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
        getAuCargaMasivaSinAutorizacionServicio().cargaInicial(this);
        listar();
    }

    public AuCargaMasivaSinAutorizacionServicioIface getAuCargaMasivaSinAutorizacionServicio() {
        return auCargaMasivaSinAutorizacionServicio;
    }

    public void setAuCargaMasivaSinAutorizacionServicio(AuCargaMasivaSinAutorizacionServicioIface auCargaMasivaSinAutorizacionServicio) {
        this.auCargaMasivaSinAutorizacionServicio = auCargaMasivaSinAutorizacionServicio;
    }

    public AuNoSolicitudCarga getObjeto() {
        return objeto;
    }

    public void setObjeto(AuNoSolicitudCarga objeto) {
        this.objeto = objeto;
    }

    public List<AuNoSolicitudCarga> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuNoSolicitudCarga> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuNoSolicitudCarga> getLazyAuCargaMasivaSinAutorizaciones() {
        return lazyAuCargaMasivaSinAutorizaciones;
    }

    public void setLazyAuCargaMasivaSinAutorizaciones(LazyDataModel<AuNoSolicitudCarga> lazyAuCargaMasivaSinAutorizaciones) {
        this.lazyAuCargaMasivaSinAutorizaciones = lazyAuCargaMasivaSinAutorizaciones;
    }

    public List<AuNoSolicitudCargaSuceso> getListaSucesos() {
        return listaSucesos;
    }

    public void setListaSucesos(List<AuNoSolicitudCargaSuceso> listaSucesos) {
        this.listaSucesos = listaSucesos;
    }

    public LazyDataModel<AuNoSolicitudCargaSuceso> getLazySucesos() {
        return lazySucesos;
    }

    public void setLazySucesos(LazyDataModel<AuNoSolicitudCargaSuceso> lazySucesos) {
        this.lazySucesos = lazySucesos;
    }

    public List<AuNoSolicitudCargaDetalle> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<AuNoSolicitudCargaDetalle> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public LazyDataModel<AuNoSolicitudCargaDetalle> getLazyDetalles() {
        return lazyDetalles;
    }

    public void setLazyDetalles(LazyDataModel<AuNoSolicitudCargaDetalle> lazyDetalles) {
        this.lazyDetalles = lazyDetalles;
    }

    public List<Maestro> getListaEstadoCarga() {
        return listaEstadoCarga;
    }

    public void setListaEstadoCarga(List<Maestro> listaEstadoCarga) {
        this.listaEstadoCarga = listaEstadoCarga;
    }

    public HashMap<Integer, Maestro> getHashEstadoCarga() {
        return hashEstadoCarga;
    }

    public void setHashEstadoCarga(HashMap<Integer, Maestro> hashEstadoCarga) {
        this.hashEstadoCarga = hashEstadoCarga;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAuCargaMasivaSinAutorizacionServicio().Accion(this);
//        procesoFinal();
    }
    
    public void refrescarDetalles() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        getAuCargaMasivaSinAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarSucesos() {
        super.setAccion(Url.ACCION_ADICIONAL_3);
        getAuCargaMasivaSinAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        getAuCargaMasivaSinAutorizacionServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
    }
    
    public void consultarAuditoria(int id) {
        setObjeto(new AuNoSolicitudCarga());
        getObjeto().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_VER);
        getAuCargaMasivaSinAutorizacionServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().resetInputs("frmVerAuditoria");
            PrimeFaces.current().ajax().update("frmVerAuditoria");
            PrimeFaces.current().executeScript("PF('dialogoVerAuditoria').show()");
        }
        generarMensajes();
    }
    
    public void cambiarEstado(int id) {
        setObjeto(new AuNoSolicitudCarga());
        getObjeto().setId(id);
        AuNoSolicitudCarga obj = getAuCargaMasivaSinAutorizacionServicio().consultarCarga(this);
        if(obj != null){
            if(obj.getEstado() == AuAnexo3Carga.ESTADO_ENVIO_OK){
                addError("No puede cambiar el estado de la carga");
            } 
        }
        if(!super.isError()){
            PrimeFaces.current().resetInputs("frmCambioEstadoCargaAdjunto");
            PrimeFaces.current().ajax().update("frmCambioEstadoCargaAdjunto");
            PrimeFaces.current().executeScript("PF('dialogoCambioEstadoCargaAdjunto').show()");
        }
        generarMensajes();
    }
    
    public void ventanaObservacionEstadoCarga(int estado) {
        getObjeto().setEstado(estado);
        PrimeFaces.current().resetInputs("frmObservacionEstadoCargaAdjunto");
        PrimeFaces.current().ajax().update("frmObservacionEstadoCargaAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoObservacionEstadoCargaAdjunto').show()");
    }
    
    public void guardarEstadoCarga() {
        super.setAccion(Url.ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getAuCargaMasivaSinAutorizacionServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoObservacionEstadoCargaAdjunto').hide()");
        PrimeFaces.current().executeScript("PF('dialogoCambioEstadoCargaAdjunto').hide()");
        generarMensajes();
    }
    
    public void guardar(FileUploadEvent event) throws IOException{
        try {
            UploadedFile archivo = event.getFile();
            getObjeto().setAdjuntoStream(archivo.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            getObjeto().setNombreArchivo(nombreAdjunto);
            super.setAccion(Url.ACCION_GUARDAR);
            getAuCargaMasivaSinAutorizacionServicio().Accion(this);
            if(!super.isError()){
                PrimeFaces.current().ajax().update("frmCargaMasivaSinAutorizaciones");
                PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
            }
        } catch (IOException ex) {
            Logger.getLogger(AuCargaMasivaSinAutorizacionBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (getObjeto().getAdjuntoStream() != null) {
                getObjeto().getAdjuntoStream().close();
            }
        }
        procesoFinal();
    }

    public boolean validarEstadoCarga(int estado) {
        boolean deshabilitar = true;
        if (estado == AuConstantes.ESTADO_CARGA_PROCESADO || estado == AuConstantes.ESTADO_CARGA_ABORTADO) {
            deshabilitar = false;
        }
        return deshabilitar;
    }

    public boolean validarEstadoCargaProcesando(int estado) {
        boolean deshabilitar = true;
        if (estado == AuConstantes.ESTADO_CARGA_EN_PROCESO /*|| estado == AuConstantes.ESTADO_CARGA_EN_COLA*/) {
            deshabilitar = false;
        }
        return deshabilitar;
    }

    public String obtenerEstado(int id) {
        try {
            return getHashEstadoCarga().get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public void consultarSucesos(int id) {
        try {
            setObjeto(new AuNoSolicitudCarga(id));
            cargaLazySucesos();
            //DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVerSucesos:tablaSucesos");
            //dataTable2.setFilterBy(null);
            //dataTable2.reset();
            //PrimeFaces.current().resetInputs("frmVerSucesos");
            PrimeFaces.current().ajax().update("frmVerSucesos");
            //this.setParamConsultaSucesos(new ParamConsulta());
            //this.getParamConsulta(0).setEmpresaId(super.getConexion().getEmpresa().getId());
            PrimeFaces.current().executeScript("PF('dialogoVerSucesos').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }
    
    public void cargaLazySucesos() {
        lazySucesos = new LazyDataModel<AuNoSolicitudCargaSuceso>() {

            private List<AuNoSolicitudCargaSuceso> listaSucesos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<AuNoSolicitudCargaSuceso> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarSucesos();
                listaSucesos = getListaSucesos();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return listaSucesos;
            }

            @Override
            public String getRowKey(AuNoSolicitudCargaSuceso ips) {
                return ips.getId().toString();
            }

            @Override
            public AuNoSolicitudCargaSuceso getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (AuNoSolicitudCargaSuceso objeto : listaSucesos) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }
    
    public void consultarDetalles(int id) {
        try {
            setObjeto(new AuNoSolicitudCarga(id));
            cargaLazyDetalles();
            DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVerDetalles:tablaDetalles");
            dataTable2.setFilterBy(null);
            dataTable2.reset();
            PrimeFaces.current().resetInputs("frmVerDetalles");
            PrimeFaces.current().ajax().update("frmVerDetalles");
            //this.getParamConsulta(1).setEmpresaId(super.getConexion().getEmpresa().getId());
            PrimeFaces.current().executeScript("PF('dialogoVerDetalles').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }
    
    public void cargaLazyDetalles() {
        lazyDetalles = new LazyDataModel<AuNoSolicitudCargaDetalle>() {
            private List<AuNoSolicitudCargaDetalle> listaDetalles;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(1).getCantidadRegistros();
            }

            @Override
            public List<AuNoSolicitudCargaDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarDetalles();
                listaDetalles = getListaDetalles();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
                return listaDetalles;
            }

            @Override
            public String getRowKey(AuNoSolicitudCargaDetalle detalle) {
                return detalle.getId().toString();
            }

            @Override
            public AuNoSolicitudCargaDetalle getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (AuNoSolicitudCargaDetalle objeto : listaDetalles) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }
    

    public boolean validarVerDetalles(int estado) {
        return estado == AuAnexo3Carga.ESTADO_ENVIO_OK;
    }

    public boolean validarVerSucesos(int estado) {
        return estado != AuAnexo3Carga.ESTADO_VALIDACION_NORMATIVA_PROCESO
                && estado != AuAnexo3Carga.ESTADO_ENVIO_PROCESO
                && estado != AuAnexo3Carga.ESTADO_VALIDACION_ESTRUCTURA_PROCESO;
    }

    public void descargarArchivo(AuNoSolicitudCarga carga) {
        String rutaCompleta = carga.getRuta() + carga.getArchivo();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            //String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + carga.getNombreArchivo() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            ec.setResponseContentType("application/vnd.ms-excel");
            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
            }
            fc.responseComplete();
        } catch (IOException e) {
            this.addError("El archivo ya no existe por que supero el tiempo de retención");
        }
        procesoFinal();
    }
    
     public boolean validarEstadoCambioCarga(int estado) {
        boolean validar = false;
        switch(estado){
            case AuAnexo3Carga.ESTADO_VALIDACION_ESTRUCTURA_PROCESO:
                validar = true;
                break;
            case AuAnexo3Carga.ESTADO_VALIDACION_NORMATIVA_PROCESO:
                validar = true;
                break;
            case AuAnexo3Carga.ESTADO_ENVIO_PROCESO:
                validar = true;
                break;
        }
        return validar;
    }
     
    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
                break;
            case Url.ACCION_EDITAR:
                break;
            case Url.ACCION_GUARDAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                break;
            case Url.ACCION_LISTAR:
                PrimeFaces.current().ajax().update("frmCargaMasivaSinAutorizaciones");
                break;
            case Url.ACCION_ADICIONAL_1:
                break;
            case Url.ACCION_ADICIONAL_2:
                break;
        }
        generarMensajes();
    }
}
