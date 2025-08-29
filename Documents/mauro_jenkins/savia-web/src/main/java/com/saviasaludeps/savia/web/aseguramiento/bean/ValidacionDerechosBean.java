/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegValidacionDerecho;
import com.saviasaludeps.savia.web.aseguramiento.servicio.ValidacionDerechosServicioIface;
import com.saviasaludeps.savia.web.aseguramiento.servicio.ValidacionDerechosServicioImpl;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@ManagedBean
@ViewScoped
public class ValidacionDerechosBean extends Url {

    private ValidacionDerechosServicioIface validacionDerechosServicio;
    private AsegValidacionDerecho objeto;
    private List<AsegValidacionDerecho> registros;
    private LazyDataModel<AsegValidacionDerecho> lazyValidacionDerechos;
    
    private Date fechaActual;
    
    private boolean requeridoReporte;

    public ValidacionDerechosBean() {
        this.objeto = new AsegValidacionDerecho();
        fechaActual = new Date();
        requeridoReporte = false;
        Modulo mod = super.validarModulo(Modulo.ID_ASEGURAMIENTO_VALIDACION_DERECHOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyValidacionDerechos = new LazyDataModel<AsegValidacionDerecho>() {
                private List<AsegValidacionDerecho> listaValidacionDerechos;
                
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<AsegValidacionDerecho> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaValidacionDerechos = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaValidacionDerechos;
                }

                @Override
                public String getRowKey(AsegValidacionDerecho reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public AsegValidacionDerecho getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (AsegValidacionDerecho validacionDerechos : listaValidacionDerechos) {
                        if (id.equals(validacionDerechos.getId())) {
                            return validacionDerechos;
                        }
                    }
                    return null;
                }

            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        getValidacionDerechosServicio().cargaInicial(this);
        listar();
    }

    public ValidacionDerechosServicioIface getValidacionDerechosServicio() {
        if (validacionDerechosServicio == null) {
            validacionDerechosServicio = new ValidacionDerechosServicioImpl();
        }
        return validacionDerechosServicio;
    }

    public void setValidacionDerechosServicio(ValidacionDerechosServicioIface validacionDerechosServicio) {
        this.validacionDerechosServicio = validacionDerechosServicio;
    }

    public AsegValidacionDerecho getObjeto() {
        return objeto;
    }

    public void setObjeto(AsegValidacionDerecho objeto) {
        this.objeto = objeto;
    }

    public List<AsegValidacionDerecho> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AsegValidacionDerecho> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AsegValidacionDerecho> getLazyValidacionDerechos() {
        return lazyValidacionDerechos;
    }

    public void setLazyValidacionDerechos(LazyDataModel<AsegValidacionDerecho> lazyValidacionDerechos) {
        this.lazyValidacionDerechos = lazyValidacionDerechos;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getValidacionDerechosServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getValidacionDerechosServicio().Accion(this);
        /*PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        */
        procesoFinal();
    }

    public void crear() {
        this.requeridoReporte = true;
        super.setAccion(ACCION_CREAR);
        getValidacionDerechosServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrearValidacionDerechos:panelCrearValidacionDerechos");
        PrimeFaces.current().ajax().update("frmCrearValidacionDerechos:panelCrearValidacionDerechos");
        PrimeFaces.current().executeScript("PF('dialogoCrearValidacionDerechos').show()");
        procesoFinal();
    }

    public void guardar() {
        if (this.objeto.getAdjuntoStream() != null) {
            super.setAccion(ACCION_GUARDAR);
            getValidacionDerechosServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoCrearValidacionDerechos').hide()");
            }
            procesoFinal();
        }else {
            this.addError("No se ha cargado el archivo.");
            procesoFinal();
        }
    }

    public void editar(int id) {
        
        procesoFinal();
    }

    public void modificar() {
        procesoFinal();
    }

    public void borrar(int id) {

        procesoFinal();
    }
    
    public void abortar(int id) {
        super.setAccion(ACCION_ADICIONAL_2);
        this.objeto = new AsegValidacionDerecho(id);
        getValidacionDerechosServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmValidacionDerechos");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmValidacionDerechos");
                break;
        }
        generarMensajes();
    }
        
    public String getEstado(int tipo) {
        String descripcion;
        switch(tipo) {
            case AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_EN_PROCESO:
                descripcion = "En Proceso";
            break;
            case AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_PROCESADO:
                descripcion = "Finalizado";
            break;
            case AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_ABORTADO:
                descripcion = "Cancelado";
            break;
            case AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_RECHAZADO:
                descripcion = "Rechazado";
            break;
            default:
                descripcion = "";
            break;
        }
        return descripcion;
    }
    
    public boolean validarEstadoCarga(int estado) {
        boolean deshabilitar = true;
        if (estado == AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_PROCESADO) {
            deshabilitar = false;
        }
        return deshabilitar;
    }
    
    public boolean validarEstadoCargaProcesando(int estado) {
        boolean deshabilitar = true;
        if (estado == AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_EN_PROCESO) {
            deshabilitar = false;
        }
        return deshabilitar;
    }
    
    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            UploadedFile archivo = event.getFile();
            this.objeto.setAdjuntoStream(archivo.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            this.objeto.setArchivo(nombreAdjunto.substring(0,indiceExtension));
            // llamamos al guardar
            this.guardar();
        } catch (IOException ex) {
            Logger.getLogger(CargaMasivaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public StreamedContent generarArchivoDescarga(AsegValidacionDerecho obj) {
        StreamedContent streamedContent = null;
        this.objeto = obj;
        super.setAccion(ACCION_ADICIONAL_1);
        try {
            String archivo = obj.getRuta() + obj.getArchivo();
            Path path = Paths.get(archivo);
            byte[] bytes = Files.readAllBytes(path);
            InputStream stream = new ByteArrayInputStream(bytes);
            stream.mark(0); //remember to this position!
            streamedContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(obj.getArchivo()).build();
        } catch (Exception ex) {
            streamedContent = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
            addError("Ocurrió un error descargando el archivo desde la ruta especificada.");
            generarMensajes();
        }
        procesoFinal();
        return streamedContent;
    }

    /**
     * @return the fechaActual
     */
    public Date getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    /**
     * @return the requeridoReporte
     */
    public boolean isRequeridoReporte() {
        return requeridoReporte;
    }

    /**
     * @param requeridoReporte the requeridoReporte to set
     */
    public void setRequeridoReporte(boolean requeridoReporte) {
        this.requeridoReporte = requeridoReporte;
    }
    
    public void actualizarCampo(){
        
    }

}
