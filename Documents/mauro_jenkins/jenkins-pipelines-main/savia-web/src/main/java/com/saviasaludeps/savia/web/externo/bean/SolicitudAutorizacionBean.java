/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.solicitud.GsAdjunto;
import com.saviasaludeps.savia.dominio.solicitud.GsAfiliado;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.dominio.solicitud.GsZona;
import com.saviasaludeps.savia.web.externo.servicio.SolicitudAutorizacionServicioIface;
import com.saviasaludeps.savia.web.externo.servicio.SolicitudAutorizacionServicioImpl;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jaime Andres Olarte
 */
@ManagedBean
@ViewScoped
public class SolicitudAutorizacionBean extends Url {

    private GsSolicitud objeto;
    private GsAfiliado afiliado;

    private SolicitudAutorizacionServicioIface solicitudAutorizacionServicio;

    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;

    public final static char ACCION_BUSCAR_AFILLIADO_SERVICE = '0';

    public final static int IDENTIFICADOR_DEPARTMENTO_POR_DEFECTO = 2;
    public final static int IDENTIFICADOR_SOLICITUD_AUTORIZACION = 5;

    private boolean habilitarSeccionResultados;

    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;

    private boolean estaHabilitadoCampo = false;

    private UploadedFile archivoDocumento;
    private UploadedFile archivoHistoriaClini;
    private UploadedFile archivoFormula;

    private String nombreDocumento;
    private String nombreHistoriaClini;
    private String nombreFormula;
    private String mensajeRadicado;

    private boolean mostrarBorrarDocumento;
    private boolean mostrarBorrarHistoriaClini;
    private boolean mostrarBorrarFormula;

    private int tipoAdjuntoDocumento;
    private int tipoAdjuntoHistoria;
    private int tipoAdjuntoFormula;

    private Date fechaMaxima;
    
    @Autowired
    private UbicacionSingle ubicacionSingle;

    public SolicitudAutorizacionBean() {
        fechaMaxima = new Date();
        objeto = new GsSolicitud();
        afiliado = new GsAfiliado();
        objeto.setGsAfiliado(new GsAfiliado());
        objeto.setListaGsAdjuntos(new ArrayList<>());
        this.objeto = new GsSolicitud();
        this.objeto.setGsAfiliado(new GsAfiliado());
        this.listaTiposDocumento = new ArrayList<>();
        this.hashTiposDocumento =new HashMap<>();
        this.fechaMaxima = new Date();
        this.solicitudAutorizacionServicio = new SolicitudAutorizacionServicioImpl();
        this.ubicacionesRecursiva = new  HashMap<>();
        this.ubicaciones = new ArrayList<>();
        
    }

    @PostConstruct
    public void postConstruct() {
        getSolicitudAutorizacionServicio().cargaInicial(this);
        if (super.isError()) {
            PrimeFaces.current().ajax().update("frmSolicitudAutorizacion");
            PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
        }
        mostrarBorrarDocumento = false;
        mostrarBorrarFormula = false;
        mostrarBorrarHistoriaClini = false;
        getTipoAdjuntoDocumento();
        getTipoAdjuntoHistoria();
        getTipoAdjuntoFormula();
        procesoFinal();
    }

    public GsSolicitud getObjeto() {
        return objeto;
    }

    public void setObjeto(GsSolicitud objeto) {
        this.objeto = objeto;
    }

    public GsAfiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(GsAfiliado afiliado) {
        this.afiliado = afiliado;
    }

    public SolicitudAutorizacionServicioIface getSolicitudAutorizacionServicio() {
        return solicitudAutorizacionServicio;
    }

    public void setSolicitudAutorizacionServicio(SolicitudAutorizacionServicioIface solicitudAutorizacionServicio) {
        this.solicitudAutorizacionServicio = solicitudAutorizacionServicio;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public boolean isHabilitarSeccionResultados() {
        return habilitarSeccionResultados;
    }

    public void setHabilitarSeccionResultados(boolean habilitarSeccionResultados) {
        this.habilitarSeccionResultados = habilitarSeccionResultados;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public boolean isEstaHabilitadoCampo() {
        return estaHabilitadoCampo;
    }

    public void setEstaHabilitadoCampo(boolean estaHabilitadoCampo) {
        this.estaHabilitadoCampo = estaHabilitadoCampo;
    }

    public UploadedFile getArchivoDocumento() {
        return archivoDocumento;
    }

    public void setArchivoDocumento(UploadedFile archivoDocumento) {
        this.archivoDocumento = archivoDocumento;
    }

    public UploadedFile getArchivoHistoriaClini() {
        return archivoHistoriaClini;
    }

    public void setArchivoHistoriaClini(UploadedFile archivoHistoriaClini) {
        this.archivoHistoriaClini = archivoHistoriaClini;
    }

    public UploadedFile getArchivoFormula() {
        return archivoFormula;
    }

    public void setArchivoFormula(UploadedFile archivoFormula) {
        this.archivoFormula = archivoFormula;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getNombreHistoriaClini() {
        return nombreHistoriaClini;
    }

    public void setNombreHistoriaClini(String nombreHistoriaClini) {
        this.nombreHistoriaClini = nombreHistoriaClini;
    }

    public String getNombreFormula() {
        return nombreFormula;
    }

    public void setNombreFormula(String nombreFormula) {
        this.nombreFormula = nombreFormula;
    }

    public boolean isMostrarBorrarDocumento() {
        return mostrarBorrarDocumento;
    }

    public void setMostrarBorrarDocumento(boolean mostrarBorrarDocumento) {
        this.mostrarBorrarDocumento = mostrarBorrarDocumento;
    }

    public boolean isMostrarBorrarHistoriaClini() {
        return mostrarBorrarHistoriaClini;
    }

    public void setMostrarBorrarHistoriaClini(boolean mostrarBorrarHistoriaClini) {
        this.mostrarBorrarHistoriaClini = mostrarBorrarHistoriaClini;
    }

    public boolean isMostrarBorrarFormula() {
        return mostrarBorrarFormula;
    }

    public void setMostrarBorrarFormula(boolean mostrarBorrarFormula) {
        this.mostrarBorrarFormula = mostrarBorrarFormula;
    }

    public int getTipoAdjuntoDocumento() {
        tipoAdjuntoDocumento = GsAdjunto.TIPO_ADJUNTO_DOCUMENTO;
        return tipoAdjuntoDocumento;
    }

    public void setTipoAdjuntoDocumento(int tipoAdjuntoDocumento) {
        this.tipoAdjuntoDocumento = tipoAdjuntoDocumento;
    }

    public int getTipoAdjuntoHistoria() {
        tipoAdjuntoHistoria = GsAdjunto.TIPO_ADJUNTO_HISTORIA_CLINICA;
        return tipoAdjuntoHistoria;
    }

    public void setTipoAdjuntoHistoria(int tipoAdjuntoHistoria) {
        this.tipoAdjuntoHistoria = tipoAdjuntoHistoria;
    }

    public int getTipoAdjuntoFormula() {
        tipoAdjuntoFormula = GsAdjunto.TIPO_ADJUNTO_ORDEN_SERVICIO;
        return tipoAdjuntoFormula;
    }

    public void setTipoAdjuntoFormula(int tipoAdjuntoFormula) {
        this.tipoAdjuntoFormula = tipoAdjuntoFormula;
    }

    public String getMensajeRadicado() {
        return mensajeRadicado;
    }

    public void setMensajeRadicado(String mensajeRadicado) {
        this.mensajeRadicado = mensajeRadicado;
    }

    public UbicacionSingle getUbicacionSingle() {
        return ubicacionSingle;
    }

    public void setUbicacionSingle(UbicacionSingle ubicacionSingle) {
        this.ubicacionSingle = ubicacionSingle;
    }
    
    public void buscarAfiliadoServicio() {
        super.setAccion(ACCION_BUSCAR_AFILLIADO_SERVICE);
        getSolicitudAutorizacionServicio().Accion(this);
        limpiarFormularioDatosInsertar();
        PrimeFaces.current().ajax().update("frmSolicitudAutorizacion");
        PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
        procesoFinal();
    }

    public void guardar() {
        boolean mostrarMensaje = false;
        if (!validarDocumento()) {
            mostrarMensaje = true;
            this.addError("Adjunte la fórmula médica");
        }
        if (!validarHistoriaClinica()) {
            mostrarMensaje = true;
            this.addError("Adjunte la historia clinica");
        }
        if (mostrarMensaje) {
            generarMensajes();
            return;
        }
        getObjeto().setId(null);
        super.setAccion(ACCION_GUARDAR);
        getSolicitudAutorizacionServicio().Accion(this);
        if (!super.isError()) {
            mensajeRadicado = "La solicitud se radicó exitosamente con el número: " + this.getMensajes().get(0).substring(this.getMensajes().get(0).indexOf(":") + 1, this.getMensajes().get(0).length());
        } else {
            PrimeFaces.current().executeScript("cerrarMensaje('mensajeRadi');");
        }
        limpiarFormulario();
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case ACCION_BUSCAR_AFILLIADO_SERVICE:
                    PrimeFaces.current().ajax().update("frmSolicitudAutorizacion");
                    PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
                    break;
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_GUARDAR:
                    crearLog("Guardar solicitud web", getObjeto().toString());
                    nombreDocumento = "";
                    nombreFormula = "";
                    nombreHistoriaClini = "";
                    mostrarBorrarDocumento = false;
                    mostrarBorrarFormula = false;
                    mostrarBorrarHistoriaClini = false;
                    PrimeFaces.current().executeScript("abrirMensaje('mensajeRadi');");
                    PrimeFaces.current().ajax().update("frmSolicitudAutorizacion:txtMensaje");
                    break;
            }
        }
        generarMensajes();
    }

    public String getTipoDocumento(int id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void limpiarFormularioBusqueda() {
        this.getObjeto().getGsAfiliado().setDocumentoNumero("");
        this.getObjeto().getGsAfiliado().setDocumentoTipo("");
        this.getObjeto().getGsAfiliado().setFechaNacimiento(null);
        this.setHabilitarSeccionResultados(false);
        PrimeFaces.current().ajax().update("frmSolicitudAutorizacion");
//        RequestContext.getCurrentInstance().reset("frmSolicitudAutorizacion");
        PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
//        RequestContext.getCurrentInstance().reset("frmBusquedaAfiliado");
    }

    public void limpiarFormulario() {
        this.getObjeto().getGsAfiliado().setDocumentoNumero("");
        this.getObjeto().getGsAfiliado().setDocumentoTipo("");
        this.getObjeto().getGsAfiliado().setFechaNacimiento(null);
        limpiarDatosEncontrados();
        limpiarFormularioDatosInsertar();
        this.setHabilitarSeccionResultados(false);
        PrimeFaces.current().ajax().update("frmSolicitudAutorizacion");
//        RequestContext.getCurrentInstance().reset("frmSolicitudAutorizacion");
        PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
//        RequestContext.getCurrentInstance().reset("frmBusquedaAfiliado");
    }

    public void limpiarDatosEncontrados() {
        this.getObjeto().getGsAfiliado().setPrimerNombre("");
        this.getObjeto().getGsAfiliado().setPrimerApellido("");
        this.getObjeto().getGsAfiliado().setSegundoNombre("");
    }

    public void limpiarFormularioDatosInsertar() {
        this.getObjeto().setContactoTelefono("");
        this.getObjeto().setContactoCelular("");
        this.getObjeto().setContactoCorreoElectronico("");
        this.getObjeto().setNombre("");
        this.getObjeto().setDescripcion("");
        this.getObjeto().setObservacion("");
        this.getObjeto().setEstado(0);
        this.getObjeto().setNotificacion(0);
        this.getObjeto().setContactoTelefono("");
        this.getObjeto().setContactoCelular("");
        this.getObjeto().setContactoCorreoElectronico("");
        this.getObjeto().setRespuestaReferencia("");
        this.getObjeto().setFechaHoraCrea(null);
        this.getObjeto().setFechaHoraAtiende(null);
        this.getObjeto().setUsuarioAtiende("");
        this.getObjeto().setFechaHoraFinaliza(null);
        this.getObjeto().setUsuarioFinaliza("");
        this.getObjeto().setGsZona(new GsZona());
        this.getObjeto().setGsAfiliado(new GsAfiliado());
        this.getObjeto().setUsuario(new Usuario());
        this.getObjeto().setTramiteInterno("NO APLICA");
        this.getObjeto().setListaGsAdjuntos(new ArrayList<>());
        this.getObjeto().setEnviarMensaje(false);
        this.getObjeto().setEnviarCorreo(false);
        nombreDocumento = "";
        nombreFormula = "";
        nombreHistoriaClini = "";
        mostrarBorrarDocumento = false;
        mostrarBorrarFormula = false;
        mostrarBorrarHistoriaClini = false;
    }

    private boolean validarExistenciaUnTelefono() {
        boolean esValido = true;

        if ((this.getObjeto().getContactoTelefono() == null || this.getObjeto().getContactoTelefono().trim().equals(""))) {
            esValido = false;
        }
        return esValido;
    }

    public boolean validarRecibirNotificacionesCelular() {
        boolean esValido = true;
        if (this.objeto.getContactoCelular() == null || this.objeto.getContactoCelular().trim().equals("")) {
            esValido = false;
        }
        return esValido;
    }

    public boolean validarDocumento() {
        boolean esValido = false;
        for (GsAdjunto adjunto : this.objeto.getListaGsAdjuntos()) {
            if (adjunto.getTipo() == GsAdjunto.TIPO_ADJUNTO_DOCUMENTO) {
                esValido = true;
                break;
            }
        }
        return esValido;
    }

    public boolean validarHistoriaClinica() {
        boolean esValido = false;
        for (GsAdjunto adjunto : this.objeto.getListaGsAdjuntos()) {
            if (adjunto.getTipo() == GsAdjunto.TIPO_ADJUNTO_HISTORIA_CLINICA) {
                esValido = true;
                break;
            }
        }
        return esValido;
    }

    public boolean validarFormulaMedica() {
        boolean esValido = false;
        for (GsAdjunto adjunto : this.objeto.getListaGsAdjuntos()) {
            if (adjunto.getTipo() == GsAdjunto.TIPO_ADJUNTO_ORDEN_SERVICIO) {
                esValido = true;
                break;
            }
        }
        return esValido;
    }

    public boolean validarRecibirNotificacionesCorreo() {
        boolean esValido = true;
        if (this.objeto.getContactoCorreoElectronico() == null || this.objeto.getContactoCorreoElectronico().trim().equals("")) {
            esValido = false;
        }
        return esValido;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion _municipio = ubicacionesRecursiva.get(id);
        if (_municipio != null && _municipio.getUbicacionPadre() != null) {
            Ubicacion _departamento = _municipio.getUbicacionPadre();
            if (_departamento.getUbicacionPadre() != null) {
                Ubicacion _pais = _departamento.getUbicacionPadre();
                // ubicacionStr = _pais.getNombre();
            }
            ubicacionStr = _departamento.getNombre();
            ubicacionStr = _municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : this.getUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        if (ubicacionesFiltradas.size() == 1) {
            getObjeto().getGsAfiliado().setResidenciaUbicacion(ubicacionesFiltradas.get(0));
        }
        return ubicacionesFiltradas;
    }

    public void cargarArchivoDocumento(FileUploadEvent event) throws IOException {
        try{
        borrarArchivo(tipoAdjuntoDocumento);
        archivoDocumento = event.getFile();
        GsAdjunto adjuntoDocumento = new GsAdjunto();
        adjuntoDocumento.setAdjuntoStream(archivoDocumento.getInputStream());
        String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
        nombreDocumento = "Adjunto: ".concat(nombreAdjunto);
        adjuntoDocumento.setNombre(nombreAdjunto);
        int indiceExtension = nombreAdjunto.lastIndexOf(".");
        adjuntoDocumento.setExtensionAdjunto(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
        adjuntoDocumento.setTipo(GsAdjunto.TIPO_ADJUNTO_DOCUMENTO);
        this.objeto.getListaGsAdjuntos().add(adjuntoDocumento);
        mostrarBorrarDocumento = true;
        }catch(IOException e){
            System.out.println("errror"+ e.getMessage());
        }
    }

    public void cargarArchivoHistoria(FileUploadEvent event) throws IOException {
        try{
        borrarArchivo(tipoAdjuntoHistoria);
        archivoHistoriaClini = event.getFile();
        GsAdjunto adjuntoHistClin = new GsAdjunto();
        adjuntoHistClin.setAdjuntoStream(archivoHistoriaClini.getInputStream());
        String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
        nombreHistoriaClini = "Adjunto: ".concat(nombreAdjunto);
        adjuntoHistClin.setNombre(nombreAdjunto);
        int indiceExtension = nombreAdjunto.lastIndexOf(".");
        adjuntoHistClin.setExtensionAdjunto(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
        adjuntoHistClin.setTipo(GsAdjunto.TIPO_ADJUNTO_HISTORIA_CLINICA);
        this.objeto.getListaGsAdjuntos().add(adjuntoHistClin);
        mostrarBorrarHistoriaClini = true;
        
        }catch(IOException e){
            System.out.println("errror"+ e.getMessage());
        }
    }

    public void cargarArchivoFormula(FileUploadEvent event) throws IOException {
        try{
        borrarArchivo(tipoAdjuntoFormula);
        archivoFormula = event.getFile();
        GsAdjunto adjuntoFormula = new GsAdjunto();
        adjuntoFormula.setAdjuntoStream(archivoFormula.getInputStream());
        String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
        nombreFormula = "Adjunto: ".concat(nombreAdjunto);
        adjuntoFormula.setNombre(nombreAdjunto);
        int indiceExtension = nombreAdjunto.lastIndexOf(".");
        adjuntoFormula.setExtensionAdjunto(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
        adjuntoFormula.setTipo(GsAdjunto.TIPO_ADJUNTO_ORDEN_SERVICIO);
        this.objeto.getListaGsAdjuntos().add(adjuntoFormula);
        mostrarBorrarFormula = true;
        
        }catch(IOException e){
            System.out.println("errror"+ e.getMessage());
        }
    }

    public void borrarArchivo(int tipoAdjunto) {
        if (!this.objeto.getListaGsAdjuntos().isEmpty()) {
            GsAdjunto adjuntoBorrar = null;
            for (GsAdjunto adjunto : this.objeto.getListaGsAdjuntos()) {
                if (tipoAdjunto == adjunto.getTipo()) {
                    adjuntoBorrar = adjunto;
                    break;
                }
            }
            if (adjuntoBorrar != null) {
                this.objeto.getListaGsAdjuntos().remove(adjuntoBorrar);
            }
            switch (tipoAdjunto) {
                case GsAdjunto.TIPO_ADJUNTO_DOCUMENTO:
                    nombreDocumento = "";
                    mostrarBorrarDocumento = false;
                    break;
                case GsAdjunto.TIPO_ADJUNTO_HISTORIA_CLINICA:
                    nombreHistoriaClini = "";
                    mostrarBorrarHistoriaClini = false;
                    break;
                case GsAdjunto.TIPO_ADJUNTO_ORDEN_SERVICIO:
                    nombreFormula = "";
                    mostrarBorrarFormula = false;
                    break;
                default:
                    break;
            }
        }
    }
}
