/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmMarcacionMasivaIps;
import com.saviasaludeps.savia.web.utilidades.Url;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio.CmMarcacionMasivaIpsServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

@Named("cmMarcacionMasivaIpsBean")
@ViewScoped
public class CmMarcacionMasivaIpsBean extends Url {
    
    public static final char ACCION_VER_CM_MARCADO_IPS_MASIVO = 'a';
    
    @Autowired
    private CmMarcacionMasivaIpsServicioIface cmMarcacionMasivaIpsServicio;
    private CmMarcacionMasivaIps objeto;
    private List<CmMarcacionMasivaIps> registros;
    private LazyDataModel<CmMarcacionMasivaIps> lazyMarcacionMasivaIps;

    public LazyDataModel<CmMarcacionMasivaIps> getLazyMarcacionMasivaIps() {
        return lazyMarcacionMasivaIps;
    }

    public void setLazyMarcacionMasivaIps(LazyDataModel<CmMarcacionMasivaIps> lazyMarcacionMasivaIps) {
        this.lazyMarcacionMasivaIps = lazyMarcacionMasivaIps;
    }

    public List<CmMarcacionMasivaIps> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmMarcacionMasivaIps> registros) {
        this.registros = registros;
    }
    
    
    
    
    public CmMarcacionMasivaIpsBean() {
        this.objeto = new CmMarcacionMasivaIps();
        Modulo mod = super.validarModulo(Modulo.ID_CM_MARCACION_MASIVA_IPS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            limitarConsultaSegunPerfilEmpresa();

            lazyMarcacionMasivaIps = new LazyDataModel<CmMarcacionMasivaIps>() {
                
                private List<CmMarcacionMasivaIps> listaCargas;
                
                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }
                
                @Override
                public List<CmMarcacionMasivaIps> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CmMarcacionMasivaIps carga) {
                    return ""+carga.getId();
                }

                @Override
                public CmMarcacionMasivaIps getRowData(String carga) {
                    Integer id = Integer.valueOf(carga);
                    for (CmMarcacionMasivaIps cg : listaCargas) {
                        if (id.equals(cg.getId())) {
                            return cg;
                        }
                    }
                    return null;
                }

            };
        }
    }

    private void limitarConsultaSegunPerfilEmpresa() {
        if (!super.getConexion().getEmpresa().isAdministradora()) {
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        }
    }
    
    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        listar();
        
    }

    public CmMarcacionMasivaIpsServicioIface getCmMarcacionMasivaIpsServicio() {
        return cmMarcacionMasivaIpsServicio;
    }

    public void setCmMarcacionMasivaIpsServicio(CmMarcacionMasivaIpsServicioIface cmMarcacionMasivaIpsServicio) {
        this.cmMarcacionMasivaIpsServicio = cmMarcacionMasivaIpsServicio;
    }


    public CmMarcacionMasivaIps getObjeto() {
        return objeto;
    }

    public void setObjeto(CmMarcacionMasivaIps objeto) {
        this.objeto = objeto;
    }

   
    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void reiniciarPantalla() {
        super.setAccion(ACCION_LISTAR);
        this.getParamConsulta().setParametroConsulta1(null);
        procesoFinal();
    }
     
    public void refrescar() {
        super.setAccion(ACCION_LISTAR);
        getCmMarcacionMasivaIpsServicio().Accion(this);
    }
    
    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_VER:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_CREAR:
                    PrimeFaces.current().ajax().update("frmCrearMarcacionIPS");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().ajax().update("frmCrearMarcacionIPS");
                    crearLog(getObjeto().toString());
                    break;
                  case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }
    
    public void guardarMarcacionMasivaIps(FileUploadEvent event) throws Exception {
        try {
            setObjeto(new CmMarcacionMasivaIps());
            UploadedFile archivo = event.getFile();
            getObjeto().setNombreArchivo(FilenameUtils.getName(event.getFile().getFileName()));
            getObjeto().setArchivo(getObjeto().getNombreArchivo());
            getObjeto().setNombre(getObjeto().getNombreArchivo());
            getObjeto().setContenidoArchivo(archivo.getInputStream());
            super.setAccion(ACCION_GUARDAR);
            getCmMarcacionMasivaIpsServicio().Accion(this);
            if (this.getObjeto().getHayErroresEnMarcacion()) {
                generarArchivoErroresMarcacionMasiva();
            } else {
                if (!isError()) {
                    addMensaje("El proceso se ha realizado exitosamente.");
                    PrimeFaces.current().executeScript("PF('dialogoCrearMarcacionIPS').hide()");
                    PrimeFaces.current().ajax().update("frmMarcacionIpsMasiva");
                }
            }
            procesoFinal();
        } catch (Exception ex) {
            this.addError("Error al cargar archivo a conciliar :" + ex.getMessage());
            procesoFinal();
        }
    }
    
    public StreamedContent generarArchivoErroresMarcacionMasiva() {
        StreamedContent streamedContentError = null;
        String nombre;
        String texto;
        try {
            if ( this.getObjeto().getMensajeError().length() > 0) {
                texto = this.getObjeto().getMensajeError().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                nombre = this.getObjeto().getNombreArchivo() + "_error_" + sdf.format(new Date()) + ".txt";
                byte[] bytes = texto.getBytes();
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamedContentError = DefaultStreamedContent.builder().stream(() ->stream).contentType("application/txt").name(nombre).build();
            } else {
                generarMensajes();
            }
        } catch (Exception ex) {
            streamedContentError = null;
            addError("Error al mostrar errores marcación : " + ex.getMessage());
            generarMensajes();
        }
        return streamedContentError;
    }
    
    public void generarArchivoMarcacionMasivaIPS(int id) {
        this.setObjeto(new CmMarcacionMasivaIps(id));
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_CM_MARCADO_IPS_MASIVO);
        getCmMarcacionMasivaIpsServicio().Accion(this);
        String rutaCompleta = this.objeto.getRuta() + this.objeto.getArchivo();
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
            String attachmentName = "attachment; filename=\"" + this.objeto.getArchivo() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("text/plain");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        crearLog("Descargar Archivo Marcación", getObjeto().toString());
        procesoFinal();
    }

    public void limpiarMarcador(){
        setObjeto(new CmMarcacionMasivaIps());
        PrimeFaces.current().ajax().update("frmCrearMarcacionIPS");
        procesoFinal();
    }
    
    public void crear() {
        setObjeto(new CmMarcacionMasivaIps());
        PrimeFaces.current().ajax().update("frmCrearMarcacionIPS");
        PrimeFaces.current().executeScript("PF('dialogoCrearMarcacionIPS').show()");
        procesoFinal();
    }
    

}
