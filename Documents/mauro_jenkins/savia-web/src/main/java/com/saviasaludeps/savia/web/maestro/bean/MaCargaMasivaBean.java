/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.maestro.MaCarga;
import com.saviasaludeps.savia.dominio.maestro.MaDetalleCarga;
import com.saviasaludeps.savia.web.maestro.servicio.MaCargaMasivaServicioIface;
import com.saviasaludeps.savia.web.maestro.servicio.MaCargaMasivaServicioImpl;
import com.saviasaludeps.savia.web.maestro.utilidades.MaestroParametro;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@ManagedBean
@ViewScoped
public class MaCargaMasivaBean extends Url {

    private MaCargaMasivaServicioIface cargaMasivaServicio;
    private MaCarga objeto;
    private List<MaCarga> registros;
    private LazyDataModel<MaCarga> lazyCargaMasiva;
    
    private List<MaDetalleCarga> listaDetalleCarga;
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    public MaCargaMasivaBean() {
        this.objeto = new MaCarga();
        Modulo mod = super.validarModulo(Modulo.ID_MAESTRO_CARGA_MASIVA);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyCargaMasiva = new LazyDataModel<MaCarga>() {
                private List<MaCarga> listaCargas;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<MaCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(MaCarga portabilidad) {
                    return portabilidad.getId().toString();
                }

                @Override
                public MaCarga getRowData(String portabilidadId) {
                    Integer id = Integer.valueOf(portabilidadId);
                    for (MaCarga portabilidad : listaCargas) {
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
        getCargaMasivaServicio().cargaInicial(this);
        listar();
    }

    public MaCargaMasivaServicioIface getCargaMasivaServicio() {
        if (cargaMasivaServicio == null) {
            cargaMasivaServicio = new MaCargaMasivaServicioImpl();
        }
        return cargaMasivaServicio;
    }

    public void setCargaMasivaServicio(MaCargaMasivaServicioIface cargaMasivaServicio) {
        this.cargaMasivaServicio = cargaMasivaServicio;
    }

    public MaCarga getObjeto() {
        return objeto;
    }

    public void setObjeto(MaCarga objeto) {
        this.objeto = objeto;
    }

    public List<MaCarga> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaCarga> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaCarga> getLazyCargaMasiva() {
        return lazyCargaMasiva;
    }

    public void setLazyCargaMasiva(LazyDataModel<MaCarga> lazyCargaMasiva) {
        this.lazyCargaMasiva = lazyCargaMasiva;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCargaMasivaServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getCargaMasivaServicio().Accion(this);
        /*PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        */
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getCargaMasivaServicio().Accion(this);
        this.objeto.setTipo(MaestroParametro.MATECNOLOGIAS);
        PrimeFaces.current().resetInputs("frmCrearAdjunto:panelCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto:panelCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
        procesoFinal();
    }

    public void guardar() {
        if (this.objeto.getAdjuntoStream() != null) {
            super.setAccion(ACCION_GUARDAR);
            getCargaMasivaServicio().Accion(this);
            PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
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
        this.objeto = new MaCarga(id);
        getCargaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public void generarCertificado(int id) {
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
                PrimeFaces.current().ajax().update("frmCargaMasiva");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                crearLog("Descargar Archivo",getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmCargaMasiva");
                break;
        }
        generarMensajes();
    }
    
    public String getTipoArchivo(int tipo) {
        String descripcion;
        switch(tipo) {
            case 1:
                descripcion = "Ma-tecnologías";
            break;
            case 2:
                descripcion = "Ma-medicamentos";
            break;
            case 3:
                descripcion = "Ma-insumos";
            break;
            case 4:
                descripcion = "Ma-paquetes";
            break;
            case 5:
                descripcion = "Ma-especialidades";
            break;
            case 6:
                descripcion = "Ma-diagnósticos";
            break;
            case 7:
                descripcion = "Ma-servicios de habilitación";
            break;
            case 8:
                descripcion = "Ma-relación insumos mipres";
            break;
            default:
                descripcion = "";
            break;
        }
        
        return descripcion;
    }
    
    public String getEstado(int tipo) {
        String descripcion;
        switch(tipo) {
            case MaestroParametro.ESTADO_EN_COLA:
                descripcion = "En Cola";
            break;
            case MaestroParametro.ESTADO_PROCESANDO:
                descripcion = "En Proceso";
            break;
            case MaestroParametro.ESTADO_PROCESADO:
                descripcion = "Procesado";
            break;
            case MaestroParametro.ESTADO_ABORTADO:
                descripcion = "Cancelado";
            break;
            default:
                descripcion = "";
            break;
        }
        return descripcion;
    }
    
    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            if (this.objeto.getTipo() != 0 ) {
                UploadedFile archivo = event.getFile();
                this.objeto.setAdjuntoStream(archivo.getInputStream());
                String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
                //int indiceExtension = nombreAdjunto.lastIndexOf(".");
                //String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
                this.objeto.setNombre(nombreAdjunto);
                // llamamos al guardar
                this.guardar();
            } else {
                this.addError("Tipo Archivo: Este campo es obligatorio.");
                procesoFinal();
            }
        } catch (IOException ex) {
            Logger.getLogger(MaCargaMasivaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean validarEstadoCarga(int estado) {
        boolean deshabilitar = true;
        if (estado == MaestroParametro.ESTADO_PROCESADO || estado == MaestroParametro.ESTADO_ABORTADO) {
            deshabilitar = false;
        }
        return deshabilitar;
    }
    
    public boolean validarEstadoCargaProcesando(int estado) {
        boolean deshabilitar = true;
        if (estado == MaestroParametro.ESTADO_PROCESANDO || estado == MaestroParametro.ESTADO_EN_COLA) {
            deshabilitar = false;
        }
        return deshabilitar;
    }
    
    public StreamedContent generarArchivoResultados(int id) {
        StreamedContent streamedContent2 = null;
        int indiceExtension;
        String extension;
        String nombre;
        String texto = "" ;
        try {
            listaDetalleCarga = new ArrayList<>();
            getObjeto().setId(id);
            super.setAccion(ACCION_ADICIONAL_1);
            getCargaMasivaServicio().Accion(this);
            // adicionamos encabezado en la primera línea
            switch(getObjeto().getTipo()) {
                case MaestroParametro.MAMEDICAMENTOS:
                    texto = MaestroParametro.ENCABEZADO_IMPRESION_ARCHIVO_MEDICAMENTOS;
                    break;
                case MaestroParametro.MAINSUMOS:
                    texto = MaestroParametro.ENCABEZADO_IMPRESION_ARCHIVO_INSUMOS;
                    break;
                case MaestroParametro.MATECNOLOGIAS:
                    texto = MaestroParametro.ENCABEZADO_IMPRESION_ARCHIVO_TECNOLOGIAS;
                    break;
                case MaestroParametro.MAPAQUETES:
                    texto = MaestroParametro.ENCABEZADO_IMPRESION_ARCHIVO_PAQUETES;
                    break;
                case MaestroParametro.MAESPECIALIDADES:
                    texto = MaestroParametro.ENCABEZADO_IMPRESION_ARCHIVO_ESPECIALIDADES;
                    break;
                case MaestroParametro.MADIAGNOSTICOS:
                    texto = MaestroParametro.ENCABEZADO_IMPRESION_ARCHIVO_DIAGNOSTICOS;
                    break;
                case MaestroParametro.MASERVICIOSHABILITACION:
                    texto = MaestroParametro.ENCABEZADO_IMPRESION_ARCHIVO_SERV_HABILITACION;
                    break;
                case MaestroParametro.MARELACIONINSUMOSMIPRES:
                    texto = MaestroParametro.ENCABEZADO_IMPRESION_ARCHIVO_RE_INSUMOS_MIPRES;
                    break;

            }
            
            if(getListaDetalleCarga().size() > 0){
                //Stream streamDetalle = this.listaDetalleCarga.stream();
                for (MaDetalleCarga dc: this.listaDetalleCarga) {
                    texto = texto + dc.toString();
                }
                // construimos el nombre del archivo
                indiceExtension = this.objeto.getArchivo().lastIndexOf(".");
                extension = this.objeto.getArchivo().substring(indiceExtension, this.objeto.getArchivo().length());
                nombre = this.objeto.getArchivo().substring(0, indiceExtension) + "_" + this.objeto.getId() + extension;
                //debemos generar la cadena de bytes a partir de los registros en la lista
                byte[] bytes = texto.getBytes();
                //byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(nombre).build();
            }else{
                addError("No se encontraron datos para generar el archivo");
                generarMensajes();
                PrimeFaces.current().resetInputs("frmCrearAdjunto:panelCrearAdjunto");
                PrimeFaces.current().ajax().update("frmCrearAdjunto:panelCrearAdjunto");
            }
        } catch (Exception ex) {
            streamedContent2 = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamedContent2;
    }
    
    public void descargarArchivoResultados(MaCarga obj) {
        setObjeto(obj);
        String rutaCompleta = getObjeto().getRuta() + getObjeto().getRespArchivo();
        super.setAccion(ACCION_ADICIONAL_1);
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
            String attachmentName = "attachment; filename=\"" + getObjeto().getRespNombre()+ "\"";
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
            this.addError("Ocurrio un error al intentar descargar el archivo de respuesta.");
        }
        procesoFinal();
    }
    
    public void descargarArchivo(MaCarga obj) {
        setObjeto(obj);
        String rutaCompleta = getObjeto().getRuta() + getObjeto().getArchivo();
        super.setAccion(ACCION_ADICIONAL_1);
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
            String attachmentName = "attachment; filename=\"" + getObjeto().getNombre()+ "\"";
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
            this.addError("Ocurrio un error al intentar descargar el archivo.");
        }
        procesoFinal();
    }

    /**
     * @return the listaDetalleCarga
     */
    public List<MaDetalleCarga> getListaDetalleCarga() {
        return listaDetalleCarga;
    }

    /**
     * @param listaDetalleCarga the listaDetalleCarga to set
     */
    public void setListaDetalleCarga(List<MaDetalleCarga> listaDetalleCarga) {
        this.listaDetalleCarga = listaDetalleCarga;
    }

    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumento() {
        return hashTipoDocumento;
    }

    public void setHashTipoDocumento(HashMap<Integer, Maestro> hashTipoDocumento) {
        this.hashTipoDocumento = hashTipoDocumento;
    }
    
    public void cargarDato() {
        
    }

}
