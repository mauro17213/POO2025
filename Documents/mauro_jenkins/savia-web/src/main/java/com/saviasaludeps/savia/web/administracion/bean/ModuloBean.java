/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.ModuloManual;
import com.saviasaludeps.savia.dominio.administracion.ModuloVersion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.administracion.servicio.ModuloServicioImpl;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.TreeNode;
import com.saviasaludeps.savia.web.administracion.servicio.ModuloServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
@ManagedBean
@ViewScoped
public class ModuloBean extends Url {

    public ModuloBean() {
        this.objeto = new Modulo();
        Modulo _mod = super.validarModulo(Modulo.ID_MODULOS);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
        }
    }

    @PostConstruct
    public void init() {
        raizArbol = getLista();
    }

    private ModuloServicioIface moduloServicio;
    private Modulo objeto;
    private List<Modulo> registros;
    private List<Modulo> filtros;
    private TreeNode raizArbol;
    private Modulo objetoSeleccionado;
    private List<Modulo> modulosPadres;
    private ModuloVersion objetoModVer;
    private LazyDataModel<ModuloVersion> lazyModuloVersion;
    private List<ModuloVersion> listModuloVersion;
    private ModuloManual objetoModMan;
    private LazyDataModel<ModuloManual> lazyManual;
    private List<ModuloManual> listModuloManual;
    private ParamConsulta paramConsultaManual;
    private UploadedFile archivoManual;
    private UploadedFile archivoManualIps;
    private String nombreDocumento;
    private String nombreDocumentoIps;
    private boolean mostrarBorrarDocumento;
    private boolean mostrarBorrarDocumentoIps;

    public ModuloServicioIface getModuloServicio() {
        if (moduloServicio == null) {
            moduloServicio = new ModuloServicioImpl();
        }
        return moduloServicio;
    }

    public void setModuloServicio(ModuloServicioIface moduloServicio) {
        this.moduloServicio = moduloServicio;
    }

    public Modulo getObjeto() {
        return objeto;
    }

    public void setObjeto(Modulo objeto) {
        this.objeto = objeto;
    }

    public List<Modulo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Modulo> registros) {
        this.registros = registros;
    }

    public List<Modulo> getFiltros() {
        return filtros;
    }

    public void setFiltros(List<Modulo> filtros) {
        this.filtros = filtros;
    }

    public TreeNode getRaizArbol() {
        return raizArbol;
    }

    public void setRaizArbol(TreeNode raizArbol) {
        this.raizArbol = raizArbol;
    }

    public Modulo getObjetoSeleccionado() {
        return objetoSeleccionado;
    }

    public void setObjetoSeleccionado(Modulo objetoSeleccionado) {
        this.objetoSeleccionado = objetoSeleccionado;
    }

    public TreeNode getLista() {
        super.setAccion(Url.ACCION_LISTAR);
        getModuloServicio().Accion(this);
        return getRaizArbol();
    }

    public List<Modulo> getModulosPadres() {
        return modulosPadres;
    }

    public void setModulosPadres(List<Modulo> modulosPadres) {
        this.modulosPadres = modulosPadres;
    }

    public ModuloVersion getObjetoModVer() {
        return objetoModVer;
    }

    public void setObjetoModVer(ModuloVersion objetoModVer) {
        this.objetoModVer = objetoModVer;
    }

    public LazyDataModel<ModuloVersion> getLazyModuloVersion() {
        return lazyModuloVersion;
    }

    public void setLazyModuloVersion(LazyDataModel<ModuloVersion> lazyModuloVersion) {
        this.lazyModuloVersion = lazyModuloVersion;
    }

    public List<ModuloVersion> getListModuloVersion() {
        return listModuloVersion;
    }

    public void setListModuloVersion(List<ModuloVersion> listModuloVersion) {
        this.listModuloVersion = listModuloVersion;
    }

    public ModuloManual getObjetoModMan() {
        return objetoModMan;
    }

    public void setObjetoModMan(ModuloManual objetoModMan) {
        this.objetoModMan = objetoModMan;
    }

    public LazyDataModel<ModuloManual> getLazyManual() {
        return lazyManual;
    }

    public void setLazyManual(LazyDataModel<ModuloManual> lazyManual) {
        this.lazyManual = lazyManual;
    }

    public List<ModuloManual> getListModuloManual() {
        return listModuloManual;
    }

    public void setListModuloManual(List<ModuloManual> listModuloManual) {
        this.listModuloManual = listModuloManual;
    }

    public ParamConsulta getParamConsultaManual() {
        return paramConsultaManual;
    }

    public void setParamConsultaManual(ParamConsulta paramConsultaManual) {
        this.paramConsultaManual = paramConsultaManual;
    }

    public UploadedFile getArchivoManual() {
        return archivoManual;
    }

    public void setArchivoManual(UploadedFile archivoManual) {
        this.archivoManual = archivoManual;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public boolean isMostrarBorrarDocumento() {
        return mostrarBorrarDocumento;
    }

    public void setMostrarBorrarDocumento(boolean mostrarBorrarDocumento) {
        this.mostrarBorrarDocumento = mostrarBorrarDocumento;
    }

    public void refrescar() {
        refrescarVista();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getModuloServicio().Accion(this);
        generarMensajes();
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getModuloServicio().Accion(this);
        generarMensajes();
        PrimeFaces.current().resetInputs("frmEditar:panelEditar");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getModuloServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        refrescarVista();
        procesoFinal();
    }

    public void refrescarVista() {
        getLista();
        PrimeFaces.current().ajax().update("frmModulos");
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
            case Url.ACCION_EDITAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_ADICIONAL_1:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        crearLog("Listar Modulos Versión");
                        break;
                    case Url.ACCION_GUARDAR:
                        crearLog("Guardar Versión", getObjetoModVer().toString());
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_2:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        crearLog("Listar Manuales");
                        break;
                    case Url.ACCION_GUARDAR:
                        crearLog("Guardar Manuales", getObjetoModMan().toString());
                        break;
                }
                break;
        }
        generarMensajes();
    }

    public void crearVersion(int id) {
        setObjetoModVer(new ModuloVersion());
        getObjetoModVer().setModulo(new Modulo(id));
        lazyModuloVersion = new LazyDataModel<ModuloVersion>() {
            private List<ModuloVersion> moduloVersiones;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<ModuloVersion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put("id", String.valueOf(getObjetoModVer().getModulo().getId()));
                getParamConsulta().setFiltros(filtrosHash);
                refrescarModVersion();
                moduloVersiones = getListModuloVersion();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return moduloVersiones;
            }

            @Override
            public String getRowKey(ModuloVersion moduloVersion) {
                return moduloVersion.getId().toString();
            }

            @Override
            public ModuloVersion getRowData(String moduloVersionId) {
                Integer id = Integer.valueOf(moduloVersionId);
                for (ModuloVersion moduloVersion : moduloVersiones) {
                    if (id.equals(moduloVersion.getId())) {
                        return moduloVersion;
                    }
                }
                return null;
            }
        };
        PrimeFaces.current().resetInputs("frmVersion");
        PrimeFaces.current().ajax().update("frmVersion");
        PrimeFaces.current().executeScript("PF('dialogoVersion').show()");
        procesoFinal();
    }

    public void guardarVersion() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);
        getModuloServicio().Accion(this);
        int moduloId = getObjetoModVer().getModulo().getId();
        setObjetoModVer(new ModuloVersion());
        getObjetoModVer().setModulo(new Modulo(moduloId));
        PrimeFaces.current().resetInputs("frmVersion");
        PrimeFaces.current().ajax().update("frmVersion");
        procesoFinal();
    }

    public void refrescarModVersion() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR);
        getModuloServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVersion");
    }

    public void crearManual(int id, String nombre) {
        setObjetoModMan(new ModuloManual());
        getObjetoModMan().setModulo(new Modulo(id));
        getObjetoModMan().getModulo().setNombre(nombre);
        getObjetoModMan().setActual(true);
        this.setParamConsultaManual(new ParamConsulta());
        lazyManual = new LazyDataModel<ModuloManual>() {
            private List<ModuloManual> moduloManuales;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<ModuloManual> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo

                getParamConsultaManual().setPrimerRegistro(primerRegistro);
                getParamConsultaManual().setRegistrosPagina(registrosPagina);
                getParamConsultaManual().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put("id", String.valueOf(getObjetoModMan().getModulo().getId()));
                getParamConsultaManual().setFiltros(filtrosHash);
                refrescarModManual();
                moduloManuales = getListModuloManual();
                setRowCount(getParamConsultaManual().getCantidadRegistros());
                return moduloManuales;
            }

            @Override
            public String getRowKey(ModuloManual moduloManual) {
                return moduloManual.getId().toString();
            }

            @Override
            public ModuloManual getRowData(String moduloManualId) {
                Integer id = Integer.valueOf(moduloManualId);
                for (ModuloManual moduloManual : moduloManuales) {
                    if (id.equals(moduloManual.getId())) {
                        return moduloManual;
                    }
                }
                return null;
            }
        };
        PrimeFaces.current().resetInputs("frmManual");
        PrimeFaces.current().ajax().update("frmManual");
        PrimeFaces.current().executeScript("PF('dialogoManual').show()");
        procesoFinal();
    }

    public void refrescarModManual() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        getModuloServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmManual");
    }

    public void cargarArchivoDocumento(FileUploadEvent event) throws IOException {
        archivoManual = event.getFile();
        try {
            getObjetoModMan().setAdjuntoStream(archivoManual.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            nombreDocumento = "Adjunto: ".concat(nombreAdjunto);
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            getObjetoModMan().setExtensión(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            getObjetoModMan().setNombre(nombreAdjunto);
            getObjetoModMan().setTipo(ModuloManual.TIPO_MANUAL_INTERNO);
            mostrarBorrarDocumento = true;
        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }

    public void cargarArchivoDocumentoIps(FileUploadEvent event) throws IOException {
        archivoManual = event.getFile();
        try {
            getObjetoModMan().setAdjuntoStream(archivoManual.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            nombreDocumentoIps = "Adjunto: ".concat(nombreAdjunto);
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            getObjetoModMan().setExtensión(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            getObjetoModMan().setNombre(nombreAdjunto);
            getObjetoModMan().setTipo(ModuloManual.TIPO_MANUAL_EXTERNO);
            mostrarBorrarDocumentoIps = true;
        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }

    public void borrarArchivo() {
        getObjetoModMan().setAdjuntoStream(null);
        nombreDocumento = "";
        mostrarBorrarDocumento = false;
    }

    public void borrarArchivoIps() {
        getObjetoModMan().setAdjuntoStream(null);
        nombreDocumentoIps = "";
        mostrarBorrarDocumentoIps = false;
    }

    public void guardarManual() {
        if (this.objetoModMan.getAdjuntoStream() == null) {
            this.addError("Es obligatorio adjuntar el manual interno.");
            generarMensajes();
            return;
        }
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_GUARDAR);
        getModuloServicio().Accion(this);
        Modulo modulo = getObjetoModMan().getModulo();
        setObjetoModMan(new ModuloManual());
        getObjetoModMan().setModulo(modulo);
        getObjetoModMan().setActual(true);
        borrarArchivo();
        PrimeFaces.current().resetInputs("frmManual");
        PrimeFaces.current().ajax().update("frmManual");
        procesoFinal();
    }

    public void guardarManualIPS() {
        if (this.objetoModMan.getAdjuntoStream() == null) {
            this.addError("Es obligatorio adjuntar el manual de IPS.");
            generarMensajes();
            return;
        }
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_GUARDAR);
        getModuloServicio().Accion(this);
        Modulo modulo = getObjetoModMan().getModulo();
        setObjetoModMan(new ModuloManual());
        getObjetoModMan().setModulo(modulo);
        getObjetoModMan().setActual(true);
        borrarArchivoIps();
        PrimeFaces.current().resetInputs("frmManual");
        PrimeFaces.current().ajax().update("frmManual");
        procesoFinal();
    }

    public void modificarManualActual(ModuloManual manual) {
        if (manual.getTipo() == ModuloManual.TIPO_MANUAL_INTERNO) {
            super.setAccion(ACCION_ADICIONAL_3);
            super.setDoAccion(ACCION_MODIFICAR);
        } else {
            super.setAccion(ACCION_ADICIONAL_4);
            super.setDoAccion(ACCION_MODIFICAR);
        }
        setObjetoModMan(manual);
        getModuloServicio().Accion(this);
        setObjetoModMan(new ModuloManual());
        getObjetoModMan().setModulo(new Modulo(manual.getModulo().getId()));
        getObjetoModMan().getModulo().setNombre(manual.getModulo().getNombre());
        getObjetoModMan().setActual(true);
        PrimeFaces.current().resetInputs("frmManual");
        PrimeFaces.current().ajax().update("frmManual");
        procesoFinal();
    }

    public UploadedFile getArchivoManualIps() {
        return archivoManualIps;
    }

    public void setArchivoManualIps(UploadedFile archivoManualIps) {
        this.archivoManualIps = archivoManualIps;
    }

    public boolean isMostrarBorrarDocumentoIps() {
        return mostrarBorrarDocumentoIps;
    }

    public void setMostrarBorrarDocumentoIps(boolean mostrarBorrarDocumentoIps) {
        this.mostrarBorrarDocumentoIps = mostrarBorrarDocumentoIps;
    }

    public String getNombreDocumentoIps() {
        return nombreDocumentoIps;
    }

    public void setNombreDocumentoIps(String nombreDocumentoIps) {
        this.nombreDocumentoIps = nombreDocumentoIps;
    }

}
