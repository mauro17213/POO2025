package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Carga;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaDetalle;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuCargaMasivaServicioIface;
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
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;

@ManagedBean
@ViewScoped
public class AuCargaMasivaBean extends Url {

    private AuCargaMasivaServicioIface auCargaMasivaServicio;
    private AuAnexo3Carga objeto;
    private List<AuAnexo3Carga> registros;
    private LazyDataModel<AuAnexo3Carga> lazyAuCargaMasiva;
    private LazyDataModel<AuAnexo3CargaSuceso> lazySucesos;
    private LazyDataModel<AuAnexo3CargaDetalle> lazyDetalles;
    private List<AuAnexo3CargaSuceso> listaSucesos;
    private List<AuAnexo3CargaDetalle> listaDetalles;
    private ParamConsulta paramConsultaSucesos;
    private ParamConsulta paramConsultaDetalles;
    private List<Maestro> listaEstadoCarga;
    private HashMap<Integer, Maestro> hashEstadoCarga;

    public AuCargaMasivaBean() {
        this.objeto = new AuAnexo3Carga();
        Modulo mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_CARGA_MASIVA);
        if (super.getConexion().getEmpresa().isAdministradora()) {
            super.getParamConsulta().setEmpresaId(null);
        } else {
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        }
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyAuCargaMasiva = new LazyDataModel<AuAnexo3Carga>() {
                private List<AuAnexo3Carga> listaCargas;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AuAnexo3Carga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AuAnexo3Carga portabilidad) {
                    return portabilidad.getId().toString();
                }

                @Override
                public AuAnexo3Carga getRowData(String portabilidadId) {
                    Integer id = Integer.valueOf(portabilidadId);
                    for (AuAnexo3Carga portabilidad : listaCargas) {
                        if (id.equals(portabilidad.getId())) {
                            return portabilidad;
                        }
                    }
                    return null;
                }
            };

        }
    }

    @PostConstruct
    public void postConstruct() {
        listar();
    }

    public AuCargaMasivaServicioIface getAuCargaMasivaServicio() {
        return auCargaMasivaServicio;
    }

    public void setAuCargaMasivaServicio(AuCargaMasivaServicioIface AuCargaMasivaServicio) {
        this.auCargaMasivaServicio = AuCargaMasivaServicio;
    }

    public AuAnexo3Carga getObjeto() {
        return objeto;
    }

    public void setObjeto(AuAnexo3Carga objeto) {
        this.objeto = objeto;
    }

    public List<AuAnexo3Carga> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuAnexo3Carga> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuAnexo3Carga> getLazyAuCargaMasiva() {
        return lazyAuCargaMasiva;
    }

    public void setLazyAuCargaMasiva(LazyDataModel<AuAnexo3Carga> lazyAuCargaMasiva) {
        this.lazyAuCargaMasiva = lazyAuCargaMasiva;
    }

    public HashMap<Integer, Maestro> getHashEstadoCarga() {
        return hashEstadoCarga;
    }

    public void setHashEstadoCarga(HashMap<Integer, Maestro> hashEstadoCarga) {
        this.hashEstadoCarga = hashEstadoCarga;
    }

    public LazyDataModel<AuAnexo3CargaSuceso> getLazySucesos() {
        return lazySucesos;
    }

    public void setLazySucesos(LazyDataModel<AuAnexo3CargaSuceso> lazySucesos) {
        this.lazySucesos = lazySucesos;
    }

    public ParamConsulta getParamConsultaSucesos() {
        if (paramConsultaSucesos == null) {
            paramConsultaSucesos = new ParamConsulta();
        }
        return paramConsultaSucesos;
    }

    public void setParamConsultaSucesos(ParamConsulta paramConsultaSucesos) {
        this.paramConsultaSucesos = paramConsultaSucesos;
    }

    public List<Maestro> getListaEstadoCarga() {
        return listaEstadoCarga;
    }

    public void setListaEstadoCarga(List<Maestro> listaEstadoCarga) {
        this.listaEstadoCarga = listaEstadoCarga;
    }

    public List<AuAnexo3CargaSuceso> getListaSucesos() {
        return listaSucesos;
    }

    public void setListaSucesos(List<AuAnexo3CargaSuceso> listaSucesos) {
        this.listaSucesos = listaSucesos;
    }

    public LazyDataModel<AuAnexo3CargaDetalle> getLazyDetalles() {
        return lazyDetalles;
    }

    public void setLazyDetalles(LazyDataModel<AuAnexo3CargaDetalle> lazyDetalles) {
        this.lazyDetalles = lazyDetalles;
    }

    public List<AuAnexo3CargaDetalle> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<AuAnexo3CargaDetalle> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public ParamConsulta getParamConsultaDetalles() {
        if (paramConsultaDetalles == null) {
            paramConsultaDetalles = new ParamConsulta();
        }
        return paramConsultaDetalles;
    }

    public void setParamConsultaDetalles(ParamConsulta paramConsultaDetalles) {
        this.paramConsultaDetalles = paramConsultaDetalles;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAuCargaMasivaServicio().Accion(this);
//        procesoFinal();
    }

    public void crear() {
        setObjeto(new AuAnexo3Carga());
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
    }
    
    public void consultarAuditoria(int id) {
        setObjeto(new AuAnexo3Carga());
        getObjeto().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_VER);
        getAuCargaMasivaServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().resetInputs("frmVerAuditoria");
            PrimeFaces.current().ajax().update("frmVerAuditoria");
            PrimeFaces.current().executeScript("PF('dialogoVerAuditoria').show()");
        }
        generarMensajes();
    }
    
    public void cambiarEstado(int id) {
        setObjeto(new AuAnexo3Carga());
        getObjeto().setId(id);
        AuAnexo3Carga obj = getAuCargaMasivaServicio().consultarCarga(this);
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
        getAuCargaMasivaServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoObservacionEstadoCargaAdjunto').hide()");
        PrimeFaces.current().executeScript("PF('dialogoCambioEstadoCargaAdjunto').hide()");
        generarMensajes();
    }
    
    public void guardar() {
        super.setAccion(Url.ACCION_GUARDAR);
        getAuCargaMasivaServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
        generarMensajes();
        refrescar();
    }

    public void abortar(int id) {
        super.setAccion(ACCION_ADICIONAL_2);
        setObjeto(new AuAnexo3Carga(id));
        getAuCargaMasivaServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                break;
            case Url.ACCION_GUARDAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                break;
            case Url.ACCION_LISTAR:
                PrimeFaces.current().ajax().update("frmCargaMasiva");
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                break;
            case Url.ACCION_ADICIONAL_2:
                break;
        }
        generarMensajes();
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            UploadedFile archivo = event.getFile();
            getObjeto().setAdjuntoStreamValidacion(archivo.getInputStream());
            getObjeto().setAdjuntoStreamDocumento(archivo.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            getObjeto().setNombreArchivo(nombreAdjunto);
            guardar();
        } catch (IOException ex) {
            Logger.getLogger(AuCargaMasivaBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (getObjeto().getAdjuntoStreamDocumento() != null) {
                getObjeto().getAdjuntoStreamDocumento().close();
            }
            if (getObjeto().getAdjuntoStreamValidacion() != null) {
                getObjeto().getAdjuntoStreamValidacion().close();
            }
        }
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
            lazySucesos = new LazyDataModel<AuAnexo3CargaSuceso>() {

                private List<AuAnexo3CargaSuceso> listaSucesos;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsultaSucesos().getCantidadRegistros();
                }

                @Override
                public List<AuAnexo3CargaSuceso> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsultaSucesos().setPrimerRegistro(primerRegistro);
                    getParamConsultaSucesos().setRegistrosPagina(registrosPagina);
                    getParamConsultaSucesos().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsultaSucesos().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarSucesos();
                    listaSucesos = getListaSucesos();
                    setRowCount(getParamConsultaSucesos().getCantidadRegistros());
                    return listaSucesos;
                }

                @Override
                public String getRowKey(AuAnexo3CargaSuceso ips) {
                    return ips.getId().toString();
                }

                @Override
                public AuAnexo3CargaSuceso getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuAnexo3CargaSuceso objeto : listaSucesos) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            setObjeto(new AuAnexo3Carga(id));
            DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVerSucesos:tablaSucesos");
            dataTable2.setFilterBy(null);
            dataTable2.reset();
            PrimeFaces.current().resetInputs("frmVerSucesos:tablaSucesos");
            PrimeFaces.current().ajax().update("frmVerSucesos:hPanelSucesos");
            this.setParamConsultaSucesos(new ParamConsulta());
            this.getParamConsultaSucesos().setEmpresaId(super.getConexion().getEmpresa().getId());
            PrimeFaces.current().ajax().update("frmVerSucesos");
            PrimeFaces.current().executeScript("PF('dialogoVerSucesos').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarDetalles(int id) {
        try {
            lazyDetalles = new LazyDataModel<AuAnexo3CargaDetalle>() {
                private List<AuAnexo3CargaDetalle> listaDetalles;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsultaDetalles().getCantidadRegistros();
                }

                @Override
                public List<AuAnexo3CargaDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsultaDetalles().setPrimerRegistro(primerRegistro);
                    getParamConsultaDetalles().setRegistrosPagina(registrosPagina);
                    getParamConsultaDetalles().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsultaDetalles().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarDetalles();
                    listaDetalles = getListaDetalles();
                    setRowCount(getParamConsultaDetalles().getCantidadRegistros());
                    return listaDetalles;
                }

                @Override
                public String getRowKey(AuAnexo3CargaDetalle detalle) {
                    return detalle.getId().toString();
                }

                @Override
                public AuAnexo3CargaDetalle getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuAnexo3CargaDetalle objeto : listaDetalles) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            setObjeto(new AuAnexo3Carga(id));
            DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVerDetalles:tablaDetalles");
            dataTable2.setFilterBy(null);
            dataTable2.reset();
            PrimeFaces.current().resetInputs("frmVerDetalles:tablaDetalles");
            PrimeFaces.current().ajax().update("frmVerDetalles:hPanelDetalles");
            this.setParamConsultaDetalles(new ParamConsulta());
            this.getParamConsultaDetalles().setEmpresaId(super.getConexion().getEmpresa().getId());
            PrimeFaces.current().ajax().update("frmVerDetalles");
            PrimeFaces.current().executeScript("PF('dialogoVerDetalles').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void refrescarDetalles() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getAuCargaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarSucesos() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getAuCargaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public boolean validarVerDetalles(int estado) {
        return estado == AuAnexo3Carga.ESTADO_ENVIO_OK;
    }

    public boolean validarVerSucesos(int estado) {
        return estado != AuAnexo3Carga.ESTADO_VALIDACION_NORMATIVA_PROCESO
                && estado != AuAnexo3Carga.ESTADO_ENVIO_PROCESO
                && estado != AuAnexo3Carga.ESTADO_VALIDACION_ESTRUCTURA_PROCESO;
    }

    public void descargarArchivo(AuAnexo3Carga carga) {
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
}
