package com.saviasaludeps.savia.web.atencionusuario.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCargaMasiva;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.web.atencionusuario.servicio.AusCargaMasivaCasosServicioIface;
import com.saviasaludeps.savia.web.atencionusuario.utilities.PropAtencionUsuario;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Stiven Giraldo
 */
@ManagedBean
@ViewScoped
public class AusCargaMasivaCasosBean extends Url{
    
    public final static String LIMITE_REGISTROS_PROCESAR = "300";
    public final static char ACCION_BUSCAR_ESPECIALIDAD = 'a';
    public final static char ACCION_BUSCAR_USUARIO = 'b';
    public final static char ACCION_VERIFICAR_USO_RADICADOS = 'c';

    
    private AusCargaMasivaCasosServicioIface ausCargaMasivaCasosServicio;
    private AusCargaMasiva objeto;
    
    private List<AusCargaMasiva> registros;
    private LazyDataModel<AusCargaMasiva> lazyCargaMasiva;
    
    private List<Maestro> listaTipoSeguimiento;
    private List<Maestro> listaTipoEstadoSolicitud;
    private List<Maestro> listaTipoEstadoPersona;
    private List<Maestro> listaTipoSolicitudOrigen;
    private List<Maestro> listaTipoEstadoServicio;
     
    private StreamedContent archivoError;
    private Boolean isError;
    
    //variable ´pr defecto para que funcione carga masiva
    private int valorDefectoPrioridad;
    private int valorDefectoUbicacion;
    private int valorDefectoSede;
    private int valorDefectoEstadoServicio;
    private int valorDefectoEspecialidadServicio;
    private int valorDefectoPrescriptoraServicio;
    private int valorDefectoDestinoServicio;
    private int valorDefectoEstadoCaso;
    private int valorDefectoEstadoPersona;
    private int valorDefectoEstadoSeguimiento;
    private int valorDefectoOrigenCaso;
    private MaEspecialidad maEspecialidadDefecto;
    
    public AusCargaMasivaCasosBean() {
        this.objeto = new AusCargaMasiva();
        Modulo _mod = super.validarModulo(Modulo.ID_AUS_CARGA_MASIVA);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyCargaMasiva = new LazyDataModel<AusCargaMasiva>() {
                private List<AusCargaMasiva> listaCargas;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AusCargaMasiva> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AusCargaMasiva carga) {
                    return carga.getId().toString();
                }

                @Override
                public AusCargaMasiva getRowData(String carga) {
                    Integer id = Integer.valueOf(carga);
                    for (AusCargaMasiva cg : listaCargas) {
                        if (id.equals(cg.getId())) {
                            return cg;
                        }
                    }
                    return null;
                }

            };
        }
    }
    
    @PostConstruct
    public void postConstruct() {
        getAusCargaMasivaCasosServicio().cargaInicial(this);
        //mostrarErrorSiFallaCargaInicial();
        listar();
    }

    public AusCargaMasivaCasosServicioIface getAusCargaMasivaCasosServicio() {
        return ausCargaMasivaCasosServicio;
    }

    public void setAusCargaMasivaCasosServicio(AusCargaMasivaCasosServicioIface ausCargaMasivaCasosServicio) {
        this.ausCargaMasivaCasosServicio = ausCargaMasivaCasosServicio;
    }

    public AusCargaMasiva getObjeto() {
        return objeto;
    }

    public void setObjeto(AusCargaMasiva objeto) {
        this.objeto = objeto;
    }

    public List<AusCargaMasiva> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AusCargaMasiva> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AusCargaMasiva> getLazyCargaMasiva() {
        return lazyCargaMasiva;
    }

    public void setLazyCargaMasiva(LazyDataModel<AusCargaMasiva> lazyCargaMasiva) {
        this.lazyCargaMasiva = lazyCargaMasiva;
    }

    public List<Maestro> getListaTipoSeguimiento() {
        return listaTipoSeguimiento;
    }

    public void setListaTipoSeguimiento(List<Maestro> listaTipoSeguimiento) {
        this.listaTipoSeguimiento = listaTipoSeguimiento;
    }

    public List<Maestro> getListaTipoEstadoSolicitud() {
        return listaTipoEstadoSolicitud;
    }

    public void setListaTipoEstadoSolicitud(List<Maestro> listaTipoEstadoSolicitud) {
        this.listaTipoEstadoSolicitud = listaTipoEstadoSolicitud;
    }

    public List<Maestro> getListaTipoEstadoPersona() {
        return listaTipoEstadoPersona;
    }

    public void setListaTipoEstadoPersona(List<Maestro> listaTipoEstadoPersona) {
        this.listaTipoEstadoPersona = listaTipoEstadoPersona;
    }

    public List<Maestro> getListaTipoSolicitudOrigen() {
        return listaTipoSolicitudOrigen;
    }

    public void setListaTipoSolicitudOrigen(List<Maestro> listaTipoSolicitudOrigen) {
        this.listaTipoSolicitudOrigen = listaTipoSolicitudOrigen;
    }

    public List<Maestro> getListaTipoEstadoServicio() {
        return listaTipoEstadoServicio;
    }

    public void setListaTipoEstadoServicio(List<Maestro> listaTipoEstadoServicio) {
        this.listaTipoEstadoServicio = listaTipoEstadoServicio;
    }

    public StreamedContent getArchivoError() {
        return archivoError;
    }

    public void setArchivoError(StreamedContent archivoError) {
        this.archivoError = archivoError;
    }

    public Boolean getIsError() {
        return isError;
    }

    public void setIsError(Boolean isError) {
        this.isError = isError;
    }

    public int getValorDefectoPrioridad() {
        return valorDefectoPrioridad;
    }

    public void setValorDefectoPrioridad(int valorDefectoPrioridad) {
        this.valorDefectoPrioridad = valorDefectoPrioridad;
    }

    public int getValorDefectoUbicacion() {
        return valorDefectoUbicacion;
    }

    public void setValorDefectoUbicacion(int valorDefectoUbicacion) {
        this.valorDefectoUbicacion = valorDefectoUbicacion;
    }

    public int getValorDefectoSede() {
        return valorDefectoSede;
    }

    public void setValorDefectoSede(int valorDefectoSede) {
        this.valorDefectoSede = valorDefectoSede;
    }

    public int getValorDefectoEstadoServicio() {
        return valorDefectoEstadoServicio;
    }

    public void setValorDefectoEstadoServicio(int valorDefectoEstadoServicio) {
        this.valorDefectoEstadoServicio = valorDefectoEstadoServicio;
    }

    public int getValorDefectoEspecialidadServicio() {
        return valorDefectoEspecialidadServicio;
    }

    public void setValorDefectoEspecialidadServicio(int valorDefectoEspecialidadServicio) {
        this.valorDefectoEspecialidadServicio = valorDefectoEspecialidadServicio;
    }

    public int getValorDefectoPrescriptoraServicio() {
        return valorDefectoPrescriptoraServicio;
    }

    public void setValorDefectoPrescriptoraServicio(int valorDefectoPrescriptoraServicio) {
        this.valorDefectoPrescriptoraServicio = valorDefectoPrescriptoraServicio;
    }

    public int getValorDefectoDestinoServicio() {
        return valorDefectoDestinoServicio;
    }

    public void setValorDefectoDestinoServicio(int valorDefectoDestinoServicio) {
        this.valorDefectoDestinoServicio = valorDefectoDestinoServicio;
    }

    public int getValorDefectoEstadoCaso() {
        return valorDefectoEstadoCaso;
    }

    public void setValorDefectoEstadoCaso(int valorDefectoEstadoCaso) {
        this.valorDefectoEstadoCaso = valorDefectoEstadoCaso;
    }

    public int getValorDefectoEstadoPersona() {
        return valorDefectoEstadoPersona;
    }

    public void setValorDefectoEstadoPersona(int valorDefectoEstadoPersona) {
        this.valorDefectoEstadoPersona = valorDefectoEstadoPersona;
    }

    public int getValorDefectoEstadoSeguimiento() {
        return valorDefectoEstadoSeguimiento;
    }

    public void setValorDefectoEstadoSeguimiento(int valorDefectoEstadoSeguimiento) {
        this.valorDefectoEstadoSeguimiento = valorDefectoEstadoSeguimiento;
    }

    public int getValorDefectoOrigenCaso() {
        return valorDefectoOrigenCaso;
    }

    public void setValorDefectoOrigenCaso(int valorDefectoOrigenCaso) {
        this.valorDefectoOrigenCaso = valorDefectoOrigenCaso;
    }

    public MaEspecialidad getMaEspecialidadDefecto() {
        return maEspecialidadDefecto;
    }

    public void setMaEspecialidadDefecto(MaEspecialidad maEspecialidadDefecto) {
        this.maEspecialidadDefecto = maEspecialidadDefecto;
    }
    
    public void refrescarPropiedadesCargaFija() {
        try {
            PropAtencionUsuario.getInstanceLimpiar();
            addMensaje("El proceso de actualización de variables carga fija se ha realizado.");
            generarMensajes();
        } catch (Exception e) {
            System.out.println("refrescarPropiedadesCargaFija():" + e.toString());
        }

    }
    
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }
    
    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAusCargaMasivaCasosServicio().Accion(this);
        //procesoFinal();
    }
    
    public void crear() {
        super.setAccion(ACCION_CREAR);
        getAusCargaMasivaCasosServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
        procesoFinal();
    }
    
    public void cargarArchivoCasos(FileUploadEvent event) {
        try {
            if (objeto.getTipo()!= null) {
                UploadedFile archivo = event.getFile();
                this.objeto.setAdjuntoStream(archivo.getInputStream());
                String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
                this.objeto.setNombre(nombreAdjunto);
                // llamamos al guardar
                this.guardar();
            } else {
                addMensaje("Debe seleccionar el tipo Archivo");
                generarMensajes();
                PrimeFaces.current().ajax().update("frmCrearAdjunto");
            }
            
        } catch (IOException ex) {
            addError("Inconveninetes al momento de leer el archivo de carga.");
        }
    }
    
    public void guardar() {
        if (this.objeto.getAdjuntoStream() != null) {
            super.setAccion(ACCION_GUARDAR);
            getAusCargaMasivaCasosServicio().Accion(this);
            PrimeFaces.current().ajax().update("frmCargaMasivaCasos");
            PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
        } else {
            this.addError("No se ha cargado el archivo.");
        }
        procesoFinal();
    }
    
    public void abortar(int id) {
        super.setAccion(ACCION_ADICIONAL_3);
        this.objeto = new AusCargaMasiva(id);
        getAusCargaMasivaCasosServicio().Accion(this);
        procesoFinal();
    }
    
    public void generarArchivoResultados(int id) {
        this.setObjeto(new AusCargaMasiva(id));
        super.setAccion(ACCION_ADICIONAL_2);
        getAusCargaMasivaCasosServicio().Accion(this);
        //String rutaCompleta = this.objeto.getRuta() + this.objeto.getArchivo();
        String rutaCompleta = this.objeto.getRuta() + this.objeto.getRespArchivo();
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
            String attachmentName = "attachment; filename=\"" + this.objeto.getNombre()+ "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("text/plain");
            } else {
                throw new Exception();
            }
            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
            }
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }
    
    public void generarArchivoOriginal(int id) {
        this.setObjeto(new AusCargaMasiva(id));
        super.setAccion(ACCION_ADICIONAL_2);
        getAusCargaMasivaCasosServicio().Accion(this);
        //String rutaCompleta = this.objeto.getRuta() + this.objeto.getArchivo();
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
            String attachmentName = "attachment; filename=\"" + this.objeto.getNombre()+ "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("text/plain");
            } else {
                throw new Exception();
            }
            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
            }
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }
    
    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmCargaMasivaCasos");
                    break;
                case Url.ACCION_GUARDAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    crearLog("Ver Carga Masiva Casos ");
                    break;
                case Url.ACCION_CREAR:
                    //crearLog("Guardar Carga M Casos", getObjeto().getAusCargaMasiva().toString());
                    super.setAccion(Url.ACCION_VER);
                    break;
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    crearLog("Refrescar Carga fijos", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmCargaMasivaCasos");
                    break;
                case Url.ACCION_ADICIONAL_2:
                    crearLog("Descarga Documento", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmCargaMasivaCasos");
                    break;
                case Url.ACCION_ADICIONAL_3:
                    crearLog("Aborto carga archivo", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmCargaMasivaCasos");
                    break;
            }
        }
        generarMensajes();
    }
}