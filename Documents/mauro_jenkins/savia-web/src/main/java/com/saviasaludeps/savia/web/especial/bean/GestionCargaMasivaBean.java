/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.especial.PeCarga;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.web.especial.servicio.GestionCargaMasivaIface;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

/**
 *
 * @author Jaime Andres Olarte
 */
@ManagedBean
@ViewScoped
public class GestionCargaMasivaBean extends Url {
    
    private GestionCargaMasivaIface gestionarCargaMasivaServicio;
    
    private List<PePrograma> listaPePrograma;
    private HashMap<Integer, PePrograma> hashPePrograma;
    private PeCarga objeto;
    private List<PeCarga> registros;
    private LazyDataModel<PeCarga> lazyCargaMasiva;
    private Integer idPrograma;
    private List<Maestro> listaEstadoCarga;

    public GestionCargaMasivaBean() {
        this.objeto = new PeCarga();
        Modulo mod = super.validarModulo(Modulo.ID_PROGRAMA_ESPECIAL_CARGAS_MASIVAS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            if (!super.getConexion().getEmpresa().isAdministradora()) {
                super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            }
            lazyCargaMasiva = new LazyDataModel<PeCarga>() {
                private List<PeCarga> listaCargas;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<PeCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(PeCarga portabilidad) {
                    return portabilidad.getId().toString();
                }

                @Override
                public PeCarga getRowData(String portabilidadId) {
                    Integer id = Integer.valueOf(portabilidadId);
                    for (PeCarga portabilidad : listaCargas) {
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
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getGestionarCargaMasivaServicio().cargaInicial(this);
        listar();
    }

    public GestionCargaMasivaIface getGestionarCargaMasivaServicio() {
        return gestionarCargaMasivaServicio;
    }

    public void setGestionarCargaMasivaServicio(GestionCargaMasivaIface gestionarCargaMasivaServicio) {
        this.gestionarCargaMasivaServicio = gestionarCargaMasivaServicio;
    }

    public PeCarga getObjeto() {
        return objeto;
    }

    public void setObjeto(PeCarga objeto) {
        this.objeto = objeto;
    }

    public List<PeCarga> getRegistros() {
        return registros;
    }

    public void setRegistros(List<PeCarga> registros) {
        this.registros = registros;
    }

    public LazyDataModel<PeCarga> getLazyCargaMasiva() {
        return lazyCargaMasiva;
    }

    public void setLazyCargaMasiva(LazyDataModel<PeCarga> lazyCargaMasiva) {
        this.lazyCargaMasiva = lazyCargaMasiva;
    }

    public List<PePrograma> getListaPePrograma() {
        return listaPePrograma;
    }

    public void setListaPePrograma(List<PePrograma> listaPePrograma) {
        this.listaPePrograma = listaPePrograma;
    }

    public HashMap<Integer, PePrograma> getHashPePrograma() {
        return hashPePrograma;
    }

    public void setHashPePrograma(HashMap<Integer, PePrograma> hashPePrograma) {
        this.hashPePrograma = hashPePrograma;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public List<Maestro> getListaEstadoCarga() {
        return listaEstadoCarga;
    }

    public void setListaEstadoCarga(List<Maestro> listaEstadoCarga) {
        this.listaEstadoCarga = listaEstadoCarga;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGestionarCargaMasivaServicio().Accion(this);
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            if (objeto.getIdPrograma() != null) {
                UploadedFile archivo = event.getFile();
                this.objeto.setAdjuntoStream(archivo.getInputStream());
                this.objeto.setNombre(FilenameUtils.getName(event.getFile().getFileName()));
                // llamamos al guardar
                if (this.objeto.getAdjuntoStream() != null) {
                    super.setAccion(ACCION_GUARDAR);
                    getGestionarCargaMasivaServicio().Accion(this);
                    PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
                } else {
                    this.addError("No se ha cargado el archivo.");
                }
            } else {
                addMensaje("Debe seleccionar el programa especial al cual realizara la carga ");
                PrimeFaces.current().ajax().update("frmCrearAdjunto");
            }            
        } catch (IOException ex) {
            addError(ex.toString());
        }
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getGestionarCargaMasivaServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
        procesoFinal();
    }

    public void _guardar() {
        if (this.objeto.getAdjuntoStream() != null) {
            super.setAccion(ACCION_GUARDAR);
            getGestionarCargaMasivaServicio().Accion(this);
            PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
        } else {
            this.addError("No se ha cargado el archivo.");
        }
        procesoFinal();
    }

    public String getEstado(Integer tipo) {
        return PeConstantes.obtenerEstadoCargaMasivaStr(tipo);
    }

    public void abortar(int id) {
        super.setAccion(ACCION_ADICIONAL_2);
        this.objeto = new PeCarga(id);
        getGestionarCargaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public void generarArchivoResultados(int idcarga) {
        this.setObjeto(new PeCarga(idcarga));
        super.setAccion(ACCION_ADICIONAL_1);
        getGestionarCargaMasivaServicio().Accion(this);
        if (objeto.getRespExiste()) {
            StringBuilder rutaCompleta = new StringBuilder(this.objeto.getRespRuta()).append(this.objeto.getRespArchivo());
            descargarArchivo(rutaCompleta.toString(), this.objeto.getRespArchivo());
        } else {
            this.addError("El archivo de resultados no existe en la ruta de descarga.");
        }        
        procesoFinal();
    }
    
    public void generarArchivoCarga(int idcarga) {
        this.setObjeto(new PeCarga(idcarga));
        super.setAccion(ACCION_ADICIONAL_1);
        getGestionarCargaMasivaServicio().Accion(this);
        if (objeto.getExiste()) {
            StringBuilder rutaCompleta = new StringBuilder(this.objeto.getRuta()).append(this.objeto.getArchivo());
            descargarArchivo(rutaCompleta.toString(), this.objeto.getArchivo());
        } else {
            this.addError("El archivo de carga no existe en la ruta de descarga.");
        }       
        procesoFinal();
    }
    
    private void descargarArchivo(String rutaCompleta, String nombreArchivo){
        try {
            File file = new File(rutaCompleta.toString());
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + nombreArchivo + "\"";
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
    }

    public boolean validarEstadoCarga(int estado) {
        boolean deshabilitar = true;
        if (estado == PeConstantes.ESTADO_PROCESADO || estado == PeConstantes.ESTADO_CANCELADO) {
            deshabilitar = false;
        }
        return deshabilitar;
    }

    public boolean validarEstadoCargaProcesando(int estado) {
        boolean deshabilitar = true;
        if (estado == PeConstantes.ESTADO_EN_PROCESO) {
            deshabilitar = false;
        }
        return deshabilitar;
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
                PrimeFaces.current().ajax().update("frmCargaMasiva");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmCargaMasiva");
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmCargaMasiva");
                break;
        }
        generarMensajes();
    }

}
