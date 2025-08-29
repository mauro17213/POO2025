package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaAnuladaSuceso;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaAnulada;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuAnexo3CargaAnuladaServicioIface;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class AuAnexo3CargaAnuladaBean extends Url {
    
    @Autowired
    private AuAnexo3CargaAnuladaServicioIface auAnexo3CargaAnuladaServicio;
    private AuAnexo3CargaAnulada objeto;
    
    //lazy
    private List<AuAnexo3CargaAnulada> registros;
    private LazyDataModel<AuAnexo3CargaAnulada> lazyAuAnexo3CargaAnuladas;
    
    private List<AuAnexo3CargaAnuladaSuceso> listaSucesos;
    private LazyDataModel<AuAnexo3CargaAnuladaSuceso> lazySucesos;

    private List<Maestro> listaEstadoCarga;
    private HashMap<Integer, Maestro> hashEstadoCarga;

    public AuAnexo3CargaAnuladaBean() {
        this.objeto = new AuAnexo3CargaAnulada();
        Modulo mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_CARGA_ANULACION_SOLICITUDES);
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
            lazyAuAnexo3CargaAnuladas = new LazyDataModel<AuAnexo3CargaAnulada>() {
                private List<AuAnexo3CargaAnulada> listaCargas;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AuAnexo3CargaAnulada> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AuAnexo3CargaAnulada solicitudCarga) {
                    return solicitudCarga.getId().toString();
                }

                @Override
                public AuAnexo3CargaAnulada getRowData(String solicitudCargaId) {
                    Integer id = Integer.valueOf(solicitudCargaId);
                    for (AuAnexo3CargaAnulada solicitudCarga : listaCargas) {
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
        getAuAnexo3CargaAnuladaServicio().cargaInicial(this);
        listar();
    }

    public AuAnexo3CargaAnuladaServicioIface getAuAnexo3CargaAnuladaServicio() {
        return auAnexo3CargaAnuladaServicio;
    }

    public void setAuAnexo3CargaAnuladaServicio(AuAnexo3CargaAnuladaServicioIface auAnexo3CargaAnuladaServicio) {
        this.auAnexo3CargaAnuladaServicio = auAnexo3CargaAnuladaServicio;
    }

    public AuAnexo3CargaAnulada getObjeto() {
        return objeto;
    }

    public void setObjeto(AuAnexo3CargaAnulada objeto) {
        this.objeto = objeto;
    }

    public List<AuAnexo3CargaAnulada> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuAnexo3CargaAnulada> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuAnexo3CargaAnulada> getLazyAuAnexo3CargaAnuladas() {
        return lazyAuAnexo3CargaAnuladas;
    }

    public void setLazyAuAnexo3CargaAnuladas(LazyDataModel<AuAnexo3CargaAnulada> lazyAuAnexo3CargaAnuladas) {
        this.lazyAuAnexo3CargaAnuladas = lazyAuAnexo3CargaAnuladas;
    }

    public List<AuAnexo3CargaAnuladaSuceso> getListaSucesos() {
        return listaSucesos;
    }

    public void setListaSucesos(List<AuAnexo3CargaAnuladaSuceso> listaSucesos) {
        this.listaSucesos = listaSucesos;
    }

    public LazyDataModel<AuAnexo3CargaAnuladaSuceso> getLazySucesos() {
        return lazySucesos;
    }

    public void setLazySucesos(LazyDataModel<AuAnexo3CargaAnuladaSuceso> lazySucesos) {
        this.lazySucesos = lazySucesos;
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
        getAuAnexo3CargaAnuladaServicio().Accion(this);
    } 

    public void refrescarSucesos() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        getAuAnexo3CargaAnuladaServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        getAuAnexo3CargaAnuladaServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
    }
    
    public void consultarAuditoria(int id) {
        setObjeto(new AuAnexo3CargaAnulada());
        getObjeto().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_VER);
        getAuAnexo3CargaAnuladaServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().resetInputs("frmVerAuditoria");
            PrimeFaces.current().ajax().update("frmVerAuditoria");
            PrimeFaces.current().executeScript("PF('dialogoVerAuditoria').show()");
        }
        generarMensajes();
    }
    
    public void cambiarEstado(int id) {
        setObjeto(new AuAnexo3CargaAnulada());
        getObjeto().setId(id);
        AuAnexo3CargaAnulada obj = getAuAnexo3CargaAnuladaServicio().consultarCarga(this);
        if(obj != null){
            if(obj.getEstado() == AuAnexo3CargaAnulada.ESTADO_PROCESADO){
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
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getAuAnexo3CargaAnuladaServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoObservacionEstadoCargaAdjunto').hide()");
        PrimeFaces.current().executeScript("PF('dialogoCambioEstadoCargaAdjunto').hide()");
        generarMensajes();
    }
    
    public void guardar(FileUploadEvent event) throws IOException{
        try {
            UploadedFile archivo = event.getFile();
            getObjeto().setAdjuntoStream(archivo.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            getObjeto().setNombre(nombreAdjunto);
            super.setAccion(Url.ACCION_GUARDAR);
            getAuAnexo3CargaAnuladaServicio().Accion(this);
            if(!super.isError()){
                PrimeFaces.current().ajax().update("frmCargaMasivaAnulacionesSolicitudes");
                PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
            }
        } catch (IOException ex) {
            Logger.getLogger(AuAnexo3CargaAnuladaBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (getObjeto().getAdjuntoStream() != null) {
                getObjeto().getAdjuntoStream().close();
            }
        }
        procesoFinal();
    }
    
    public void descargarArchivo(AuAnexo3CargaAnulada carga) {
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
            setObjeto(new AuAnexo3CargaAnulada(id));
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
        lazySucesos = new LazyDataModel<AuAnexo3CargaAnuladaSuceso>() {

            private List<AuAnexo3CargaAnuladaSuceso> listaSucesos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<AuAnexo3CargaAnuladaSuceso> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
            public String getRowKey(AuAnexo3CargaAnuladaSuceso ips) {
                return ips.getId().toString();
            }

            @Override
            public AuAnexo3CargaAnuladaSuceso getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (AuAnexo3CargaAnuladaSuceso objeto : listaSucesos) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public boolean validarVerSucesos(int estado) {
        boolean validate = false;
        switch(estado){
            case AuAnexo3CargaAnulada.ESTADO_PROCESADO:
                validate = true;
                break;
            case AuAnexo3CargaAnulada.ESTADO_PROCESANDO:
                validate = true;
                break;
        }
        return validate;
    }

     public boolean validarEstadoCambioCarga(int estado) {
        boolean validar = false;
        switch(estado){
            case AuAnexo3CargaAnulada.ESTADO_PROCESADO_INCOMPLETO:
                validar = true;
                break;
        }
        return validar;
    }
     
    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
                break;
            case Url.ACCION_CREAR:
                break;
            case Url.ACCION_EDITAR:
                break;
            case Url.ACCION_GUARDAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_MODIFICAR:
                break;
            case Url.ACCION_BORRAR:
                break;
            case Url.ACCION_LISTAR:
                PrimeFaces.current().ajax().update("frmCargaMasivaAnulacionesSolicitudes");
                break;
            case Url.ACCION_ADICIONAL_1:
                break;
            case Url.ACCION_ADICIONAL_2:
                break;
        }
        generarMensajes();
    }
}
