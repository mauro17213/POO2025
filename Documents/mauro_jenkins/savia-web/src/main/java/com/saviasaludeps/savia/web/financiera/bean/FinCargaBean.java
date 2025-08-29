/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.financiera.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.financiera.FinCarga;
import com.saviasaludeps.savia.web.financiera.servicio.FinCargaServicioIface;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.faces.context.ExternalContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author jeperez
 */
@ManagedBean
@ViewScoped
public class FinCargaBean extends Url {
    
    
    public static final char DO_ACCION_GUARDAR_CARGA = 'a';
    public static final char DO_ACCION_GUARDAR_CANCELACION = 'b';

    @Autowired
    private FinCargaServicioIface finCargaServicio;
    private FinCarga objeto;
    private List<FinCarga> registros = new ArrayList<>();
    private LazyDataModel<FinCarga> lazyFinCargas;


    public FinCargaBean() {
        try {
            this.objeto = new FinCarga();
            Modulo _mod = super.validarModulo(Modulo.ID_FIN_CARGAS);
            if (_mod == null) {
                super.redireccionar("/savia/home.faces");
            } else {
                super.setModulo(_mod);
                inicializarTablaCargas();
            }
        } catch (Exception e) {
            super.redireccionar("/savia/home.faces");
        }

    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public FinCarga getObjeto() {
        return objeto;
    }

    public void setObjeto(FinCarga objeto) {
        this.objeto = objeto;
    }

  

    public void listar() {
        refrescar();
        procesoFinal();
    }



    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getFinCargaServicio().Accion(this);
    }

   

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    switch (getDoAccion()) {
                        case DO_ACCION_GUARDAR_CARGA:
                            crearLog("Guardar carga", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmCargaMasiva");
                            break;
                        case DO_ACCION_GUARDAR_CANCELACION:
                            crearLog("Cancelar Carga ", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmCargaMasiva");
                            break;
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmCargaMasiva");
                    break;
            }
        }
        generarMensajes();
    }
    
    public void crear() {
        super.setAccion(ACCION_CREAR);
        getFinCargaServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
        procesoFinal();
    }
    
    public void guardar() {
        if (this.getObjeto().getAdjuntoStream() != null) {
            super.setAccion(ACCION_GUARDAR);
            super.setDoAccion(DO_ACCION_GUARDAR_CARGA);
            getFinCargaServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide();");
            } else {
                PrimeFaces.current().ajax().update("frmCrearAdjunto");
            }
        } else {
            this.addError("No se ha cargado el archivo de la carga.");
        }

        procesoFinal();
    }
    
    public void guardarCancelar(int id) {
        getObjeto().setId(id);
        getObjeto().setEstado(FinCarga.ESTADO_CANCELADO);
        super.setAccion(ACCION_GUARDAR);
        super.setDoAccion(DO_ACCION_GUARDAR_CANCELACION);
        getFinCargaServicio().Accion(this);
        procesoFinal();
    }
    
    public boolean validarEstadoCargaProcesando(int estado) {
        boolean habilitar = false;
        if (estado == FinCarga.ESTADO_EN_PROCESO) {
            habilitar = true;
        }
        return habilitar;
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getFinCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getFinCargaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    
    public void inicializarTablaCargas() {
        lazyFinCargas = new LazyDataModel<FinCarga>() {

            private List<FinCarga> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<FinCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                lista = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(FinCarga objeto) {
                return objeto.getId().toString();
            }

            @Override
            public FinCarga getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (FinCarga objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };

    }

    public void descargarArchivoCarga(FinCarga carga) {
        descargarArchivo(carga.getRuta(), carga.getArchivo(), carga.getNombre());
    }

    public void descargarArchivoRespuestaCarga(FinCarga carga) {
        descargarArchivo(carga.getRespRuta(), carga.getRespArchivo(), carga.getRespArchivo());
    }

    public void descargarArchivo(String ruta, String nombreReal, String nombreDescarga) {
        try {

            String rutaCompleta = ruta + nombreReal;

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
            String attachmentName = "attachment; filename=\"" +nombreDescarga + "\"";
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
            this.addError("Ocurrio un error al intentar descargar el archivo: "+e.toString());
            PrimeFaces.current().ajax().update("frmCargaMasiva");
            generarMensajes();
        }
    }
    
    public void eventoAdicionarAdjunto(FileUploadEvent event) throws IOException {
        UploadedFile archivo = event.getFile();
        this.getObjeto().setAdjuntoStream(archivo.getInputStream());
        this.getObjeto().setNombre(FilenameUtils.getName(event.getFile().getFileName()));
        guardar();
    }

    public List<FinCarga> getRegistros() {
        return registros;
    }

    public void setRegistros(List<FinCarga> registros) {
        this.registros = registros;
    }

    public LazyDataModel<FinCarga> getLazyFinCargas() {
        return lazyFinCargas;
    }

    public void setLazyFinCargas(LazyDataModel<FinCarga> lazyFinCargas) {
        this.lazyFinCargas = lazyFinCargas;
    }

    public FinCargaServicioIface getFinCargaServicio() {
        return finCargaServicio;
    }

    public void setFinCargaServicio(FinCargaServicioIface finCargaServicio) {
        this.finCargaServicio = finCargaServicio;
    }

    

}
