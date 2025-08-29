/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.solicitud.GsAdjunto;
import com.saviasaludeps.savia.dominio.solicitud.GsAfiliado;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.web.externo.servicio.SolicitudAfiliacionServicioIface;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import javax.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author admin
 */
@ManagedBean
@ViewScoped
public class SolicitudAfiliacionBean extends Url {

    
    private GsSolicitud objeto;
    @Autowired
    private SolicitudAfiliacionServicioIface solicitudAfiliacionServicio;

    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;

    public final static char ACCION_BUSCAR_AFILLIADO_SERVICE = '0';
    public final static char ACCION_GUARDAR_AFILIADO_SERVICE = 'P';
    
    public final static int IDENTIFICADOR_DEPARTMENTO_POR_DEFECTO = 2;

    private final static Map<String, String> listaZonas;
    
    private String ipTerminalCrea;
    private String userCrea = "ActualizacionWeb";

    private boolean habilitarSeccionResultados;
    
    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    
    private boolean estaHabilitadoCampo = false;

    private boolean notificacionSMS;
    private boolean notificacionCorreo;
    
    private Date fechaMaxima;
    
   private String nombreDocumento;
   private String nombreCertificado1;
   private String nombreCertificado2;
   private String nombreCertificado3;
   
    private boolean mostrarBorrarDocumento;
    private boolean mostrarBorrarCertificado1;
    private boolean mostrarBorrarCertificado2;
    private boolean mostrarBorrarCertificado3;
    
    private int tipoAdjuntoDocumento;
    private int tipoAdjuntoCertificado;
    
    private String mensajeRadicado;
    

    static {
        listaZonas = GsAfiliado.getTipoZonas();
    }

    public SolicitudAfiliacionBean() {
        
        this.objeto = new GsSolicitud();
        this.objeto.setGsAfiliado(new GsAfiliado());
        this.listaTiposDocumento = new ArrayList<>();
        this.hashTiposDocumento =new HashMap<Integer, Maestro>();
        this.fechaMaxima = new Date();
        this.ubicacionesRecursiva = new  HashMap<Integer, Ubicacion>();
        this.ubicaciones = new ArrayList<>();
        this.mensajeRadicado = "";
        //this.objeto.setDepartamentoResidencia( String.valueOf(IDENTIFICADOR_DEPARTMENTO_POR_DEFECTO) );
    }
    
    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getSolicitudAfiliacionServicio().cargaInicial(this);
        if (super.isError()) {
          PrimeFaces.current().ajax().update("frmSolicitudAfiliado");
          PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
        }
        procesoFinal();
    }

    public GsSolicitud getObjeto() {
        return objeto;
    }

    public void setObjeto(GsSolicitud objeto) {
        this.objeto = objeto;
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

    public Map<String, String> getListaZonas() {
        return listaZonas;
    }

    public boolean isHabilitarSeccionResultados() {
        return habilitarSeccionResultados;
    }

    public void setHabilitarSeccionResultados(boolean habilitarSeccionResultados) {
        this.habilitarSeccionResultados = habilitarSeccionResultados;
    }
    
     public String getIpTerminalCrea() {
        return ipTerminalCrea;
    }

    public void setIpTerminalCrea(String ipTerminalCrea) {
        this.ipTerminalCrea = ipTerminalCrea;
    }

    public String getUserCrea() {
        return userCrea;
    }

    public void setUserCrea(String userCrea) {
        this.userCrea = userCrea;
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

    public SolicitudAfiliacionServicioIface getSolicitudAfiliacionServicio() {
        return solicitudAfiliacionServicio;
    }

    public void setSolicitudAfiliacionServicio(SolicitudAfiliacionServicioIface solicitudAfiliacionServicio) {
        this.solicitudAfiliacionServicio = solicitudAfiliacionServicio;
    }

  

    public void setNotificacionSMS(boolean notificacionSMS) {
        this.notificacionSMS = notificacionSMS;
    }
    
    public boolean getNotificacionSMS() {
        return notificacionSMS;
    }

    public boolean getNotificacionCorreo() {
        return notificacionCorreo;
    }

    public void setNotificacionCorreo(boolean notificacionCorreo) {
        this.notificacionCorreo = notificacionCorreo;
    }

    public boolean isNotificacionSMS() {
        return notificacionSMS;
    }

    public boolean isNotificacionCorreo() {
        return notificacionCorreo;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getNombreCertificado1() {
        return nombreCertificado1;
    }

    public void setNombreCertificado1(String nombreCertificado1) {
        this.nombreCertificado1 = nombreCertificado1;
    }

    public String getNombreCertificado2() {
        return nombreCertificado2;
    }

    public void setNombreCertificado2(String nombreCertificado2) {
        this.nombreCertificado2 = nombreCertificado2;
    }

    public String getNombreCertificado3() {
        return nombreCertificado3;
    }

    public void setNombreCertificado3(String nombreCertificado3) {
        this.nombreCertificado3 = nombreCertificado3;
    }

    public boolean isMostrarBorrarDocumento() {
        return mostrarBorrarDocumento;
    }

    public void setMostrarBorrarDocumento(boolean mostrarBorrarDocumento) {
        this.mostrarBorrarDocumento = mostrarBorrarDocumento;
    }

    public boolean isMostrarBorrarCertificado1() {
        return mostrarBorrarCertificado1;
    }

    public void setMostrarBorrarCertificado1(boolean mostrarBorrarCertificado1) {
        this.mostrarBorrarCertificado1 = mostrarBorrarCertificado1;
    }

    public boolean isMostrarBorrarCertificado2() {
        return mostrarBorrarCertificado2;
    }

    public void setMostrarBorrarCertificado2(boolean mostrarBorrarCertificado2) {
        this.mostrarBorrarCertificado2 = mostrarBorrarCertificado2;
    }

    public boolean isMostrarBorrarCertificado3() {
        return mostrarBorrarCertificado3;
    }

    public void setMostrarBorrarCertificado3(boolean mostrarBorrarCertificado3) {
        this.mostrarBorrarCertificado3 = mostrarBorrarCertificado3;
    }

    public int getTipoAdjuntoDocumento() {
        tipoAdjuntoDocumento = GsAdjunto.TIPO_ADJUNTO_DOCUMENTO;
        return tipoAdjuntoDocumento;
    }

    public void setTipoAdjuntoDocumento(int tipoAdjuntoDocumento) {
        this.tipoAdjuntoDocumento = tipoAdjuntoDocumento;
    }

    public int getTipoAdjuntoCertificado() {
        tipoAdjuntoCertificado = GsAdjunto.TIPO_ADJUNTO_CERTIFICADO;
        return tipoAdjuntoCertificado;
    }

    public void setTipoAdjuntoCertificado(int tipoAdjuntoCertificado) {
        this.tipoAdjuntoCertificado = tipoAdjuntoCertificado;
    }

    public String getMensajeRadicado() {
        return mensajeRadicado;
    }

    public void setMensajeRadicado(String mensajeRadicado) {
        this.mensajeRadicado = mensajeRadicado;
    }
     
    private void procesoFinal() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if (!super.isError()) {
            switch (getAccion()) {
                case ACCION_BUSCAR_AFILLIADO_SERVICE:
                case ACCION_GUARDAR_AFILIADO_SERVICE:
                    PrimeFaces.current().ajax().update("frmSolicitudAfiliado");
                    PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
                    PrimeFaces.current().executeScript("abrirMensaje('mensajeRadi');");
                    break;
                case Url.ACCION_LISTAR:
                    break;
            }
        }
        generarMensajes();
    }
    
    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public void buscarAfiliadoServicio() {
        this.setMensajeRadicado("");
        limpiarDatosEncontrados();
        limpiarFormularioDatosInsertar();
        super.setAccion(ACCION_BUSCAR_AFILLIADO_SERVICE);
        getSolicitudAfiliacionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmSolicitudAfiliado");
        PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
        procesoFinal();
    }
    
    public void guardarAfiliado() {
       ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if (!validarExistenciaTipoDocumento()) {
            this.addError("El Tipo de documento no debe ser vacío.");
            generarMensajes();
            return;
        }
             
        if (!validarExistenciaNumeroDocumento()) {
            this.addError("El Número de documento no debe ser vacío.");
            generarMensajes();
            return;
        }
       
        if (!validarExistenciaFechaNacimiento()) {
            this.addError("La fecha de nacimiento no debe ser vacia.");
            generarMensajes();
            return;
         }
        
        
        if (!validarNotificacionSMS()) {
            this.addError("La notificación SMS requiere Celular");
            generarMensajes();
            return;
        }
        
        if (!validarNotificacionCorreo()) {
            this.addError("La notificación de correo require Correo Electrónico");
            generarMensajes();
            return;
         }
        
         if (!validarExistenciaAdjunto(this.getObjeto().getListaGsAdjuntos())) {
            this.addError("Se debe agregar un anexo en el campo Anexo Documento.");
            generarMensajes();
            return;
        }
               
        super.setAccion(ACCION_GUARDAR_AFILIADO_SERVICE);
        this.setIpTerminalCrea( obtenerIpPeticion() );
        getSolicitudAfiliacionServicio().Accion(this);
        if (!super.isError()) {
             mensajeRadicado = "La solicitud se radicó exitosamente con el número: "
                    + this.getMensajes().get(0).substring(this.getMensajes().get(0).indexOf(":") + 1,
                            this.getMensajes().get(0).length());
        } else {
            PrimeFaces.current().executeScript("cerrarMensaje('mensajeRadi');");
        }
        limpiarFormularioBusqueda();
        limpiarFormulario();
        procesoFinal();
    }

    public String getTipoDocumento(int id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void limpiarFormularioBusqueda() {
        this.getObjeto().getGsAfiliado().setDocumentoNumero(null);
        this.getObjeto().getGsAfiliado().setDocumentoTipo(null);
        this.getObjeto().getGsAfiliado().setFechaNacimiento(null);
        this.setHabilitarSeccionResultados(false);
        PrimeFaces.current().ajax().update("frmSolicitudAfiliado");
        PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
    }

    public void limpiarFormulario() {
        this.getObjeto().getGsAfiliado().setDocumentoNumero(null);
        this.getObjeto().getGsAfiliado().setDocumentoTipo(null);
        this.getObjeto().getGsAfiliado().setFechaNacimiento(null);
        limpiarDatosEncontrados();
        limpiarFormularioDatosInsertar();
        this.setHabilitarSeccionResultados(false);
        this.setNotificacionCorreo(false);
        this.setNotificacionSMS(false);
        this.getObjeto().setListaGsAdjuntos(new ArrayList<>());
        PrimeFaces.current().ajax().update("frmSolicitudAfiliado");
        PrimeFaces.current().ajax().update("frmSolicitudAfiliado:panelDatosEncontradosAfiliado");
        PrimeFaces.current().ajax().update("frmSolicitudAfiliado:panelDatosDireccion");
        PrimeFaces.current().ajax().update("frmSolicitudAfiliado:panelDatosEncontradosAfiliado2");
    }
    
    public void limpiarDatosEncontrados(){
        this.getObjeto().getGsAfiliado().setPrimerNombre(null);
        this.getObjeto().getGsAfiliado().setSegundoNombre(null);
        this.getObjeto().getGsAfiliado().setPrimerApellido(null);
        this.getObjeto().getGsAfiliado().setSegundoApellido(null);
        this.getObjeto().getGsAfiliado().setSexo("");
    }
    public void limpiarFormularioDatosInsertar(){
        this.getObjeto().getGsAfiliado().setDocumentoFechaExpedicion(null);
        this.getObjeto().getGsAfiliado().setResidenciaUbicacion(null);
        this.getObjeto().getGsAfiliado().setAtencionUbicacion(null);
        this.getObjeto().getGsAfiliado().setViaDireccion("");
        this.getObjeto().getGsAfiliado().setNumeroDireccion("");
        this.getObjeto().getGsAfiliado().setOrden1Direccion("");
        this.getObjeto().getGsAfiliado().setOrientacionDireccion("");
        this.getObjeto().getGsAfiliado().setOrden2Direccion("");
        this.getObjeto().getGsAfiliado().setPlacaDireccion("");
        this.getObjeto().getGsAfiliado().setDescripcionDireccion("");
        this.getObjeto().getGsAfiliado().setResidenciaDireccion("");
        this.getObjeto().getGsAfiliado().setResidenciaZonaTipo("");
        this.getObjeto().getGsAfiliado().setAtencionUbicacionNombre("");
        this.getObjeto().getGsAfiliado().setResidenciaUbicacionNombre("");
        this.setNombreDocumento("");
        this.setNombreCertificado1("");
        this.setNombreCertificado2("");
        this.setNombreCertificado3("");
        this.getObjeto().setContactoCelular("");
        this.getObjeto().setContactoCorreoElectronico("");
        this.getObjeto().setContactoTelefono("");
        this.setNotificacionCorreo(false);
        this.setNotificacionSMS(false);
        this.getObjeto().setObservacion("");
        this.setMostrarBorrarDocumento(false);
        this.setMostrarBorrarCertificado1(false);
        this.setMostrarBorrarCertificado2(false);
        this.setMostrarBorrarCertificado3(false);
        this.setNombreDocumento("");
        this.setNombreCertificado1("");
        this.setNombreCertificado2("");
        this.setNombreCertificado3("");
        this.getObjeto().setListaGsAdjuntos(new ArrayList<>());
    }

    private String obtenerIpPeticion() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
    
        
     private boolean validarExistenciaAdjunto(List<GsAdjunto> adjuntos) {
        boolean esValido = false;
        if (adjuntos.isEmpty()) {
            return false;
        }
        
        for (GsAdjunto adjunto : adjuntos) {
            if(adjunto.getTipo() == GsAdjunto.TIPO_ADJUNTO_DOCUMENTO){
              esValido = true;
            }
        }
        return esValido;
    }

    private boolean validarExistenciaTipoDocumento(){
       boolean esValido = true;
       if( this.getObjeto().getGsAfiliado().getDocumentoTipo() == null || 
           this.getObjeto().getGsAfiliado().getDocumentoTipo() == ""){ 
           esValido = false;
       }
       return esValido;
    }
    
      private boolean validarExistenciaNumeroDocumento(){
       boolean esValido = true;
       if( (this.getObjeto().getGsAfiliado().getDocumentoNumero() == null || 
            this.getObjeto().getGsAfiliado().getDocumentoNumero() == "")){
           esValido = false;
       }
       return esValido;
    }
    
    private boolean validarExistenciaFechaNacimiento(){
       boolean esValido = true;
       if( this.getObjeto().getGsAfiliado().getFechaNacimiento() == null ){
           esValido = false;
       }
       return esValido;
    }
    
    private boolean validarNotificacionSMS(){
        boolean esValido = true;
        if (this.getNotificacionSMS()) {
            esValido = ! (this.getObjeto().getContactoCelular() == null || 
                         this.getObjeto().getContactoCelular().equals("")
                        );
        }
        return esValido;
    }
    
     private boolean validarNotificacionCorreo(){
         boolean esValido = true;
         if (this.getNotificacionCorreo()) {
             esValido = !(this.getObjeto().getContactoCorreoElectronico() == null || 
                          this.getObjeto().getContactoCorreoElectronico().equals(""));
         }
         return esValido;
    }
    
    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public void cambiarExigenciaDatosSegunVia(){
        String tipoVia ="";
        if (!"".equals(tipoVia)) {
            if (tipoVia.equalsIgnoreCase("SD")) {
                cambiarEstadoCamposSinDireccion(true);
            }else{
                cambiarEstadoCamposSinDireccion(false);
            }

        } else {
            cambiarEstadoCamposSinDireccion(false);
        }
        PrimeFaces.current().ajax().update("frmSolicitudAfiliado:contentPanelDireccion");
//        RequestContext.getCurrentInstance().reset("frmSolicitudAfiliado:contentPanelDireccion");
    }
    
    private void cambiarEstadoCamposSinDireccion(boolean tipoHabilitacion){
        setEstaHabilitadoCampo(tipoHabilitacion);
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
    
    
    public void handleFileUploadDocumento(FileUploadEvent event) throws IOException {
        try {
              borrarAdjunto(GsAdjunto.TIPO_ADJUNTO_DOCUMENTO, 1);
              InputStream input = event.getFile().getInputStream();
              String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
              int indiceExtension = nombreAdjunto.lastIndexOf(".");
              String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
              GsAdjunto ajunto = new GsAdjunto();
              ajunto.setNombre(nombreAdjunto);
              ajunto.setAdjuntoStream(input);
              ajunto.setTipo(GsAdjunto.TIPO_ADJUNTO_DOCUMENTO);
              ajunto.setExtensionAdjunto(extension);
              ajunto.setIndice(1);
              this.mostrarBorrarDocumento = true;
              this.getObjeto().getListaGsAdjuntos().add(ajunto);
              this.setNombreDocumento("Documento Anexado: " + nombreAdjunto);
              
        } catch (IOException e) {
            this.addError("Error al cargar archivo documento : " + e.getMessage());
            generarMensajes();
        }
    }
    
     public void handleFileUploadDocumentoCertificador1(FileUploadEvent event) throws IOException {
        try {
              borrarAdjunto(GsAdjunto.TIPO_ADJUNTO_CERTIFICADO, 1);
              InputStream input = event.getFile().getInputStream();
              String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
              int indiceExtension = nombreAdjunto.lastIndexOf(".");
              String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
              GsAdjunto ajunto = new GsAdjunto();
              ajunto.setNombre(nombreAdjunto);
              ajunto.setAdjuntoStream(input);
              ajunto.setTipo(GsAdjunto.TIPO_ADJUNTO_CERTIFICADO);
              ajunto.setExtensionAdjunto(extension);
              ajunto.setIndice(1);
              this.mostrarBorrarCertificado1= true;
              this.getObjeto().getListaGsAdjuntos().add(ajunto);
              this.setNombreCertificado1("Certificado 1 Anexado : " + nombreAdjunto);
        } catch (IOException e) {
            this.addError("Error al cargar adjunto certificado 1: " + e.getMessage());
            generarMensajes();
        }
    }
     
     public void handleFileUploadDocumentoCertificador2(FileUploadEvent event) throws IOException {
        try {
              borrarAdjunto(GsAdjunto.TIPO_ADJUNTO_CERTIFICADO, 2);
              InputStream input = event.getFile().getInputStream();
              String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
              int indiceExtension = nombreAdjunto.lastIndexOf(".");
              String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
              GsAdjunto ajunto = new GsAdjunto();
              ajunto.setNombre(nombreAdjunto);
              ajunto.setAdjuntoStream(input);
              ajunto.setTipo(GsAdjunto.TIPO_ADJUNTO_CERTIFICADO);
              ajunto.setExtensionAdjunto(extension);
              ajunto.setIndice(2);
              this.mostrarBorrarCertificado2= true;
              this.getObjeto().getListaGsAdjuntos().add(ajunto);
              this.setNombreCertificado2("Certificado 2 Anexado: " + nombreAdjunto);
        } catch (Exception e) {
            this.addError("Error al cargar adjunto certificado 2: " + e.getMessage());
            generarMensajes();
        }
    }
     
    public void handleFileUploadDocumentoCertificador3(FileUploadEvent event) throws IOException {
        try {
            borrarAdjunto(GsAdjunto.TIPO_ADJUNTO_CERTIFICADO, 3);
            InputStream input = event.getFile().getInputStream();
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
            GsAdjunto ajunto = new GsAdjunto();
            ajunto.setNombre(nombreAdjunto);
            ajunto.setAdjuntoStream(input);
            ajunto.setTipo(GsAdjunto.TIPO_ADJUNTO_CERTIFICADO);
            ajunto.setExtensionAdjunto(extension);
            ajunto.setIndice(3);
            this.mostrarBorrarCertificado3 = true;
            this.getObjeto().getListaGsAdjuntos().add(ajunto);
            this.setNombreCertificado3("Certificado Anexado 3 : " + nombreAdjunto);
        } catch (Exception e) {
            this.addError("Error al cargar adjunto certificado 3 : " + e.getMessage());
            generarMensajes();
        }
    }
     
        public void borrarAdjunto( int tipoAdjunto, int indice) {
        if ( !this.objeto.getListaGsAdjuntos().isEmpty() ) {
            GsAdjunto adjuntoBorrar = null;
            for (GsAdjunto adjunto : this.objeto.getListaGsAdjuntos()) {
                if ( tipoAdjunto  == adjunto.getTipo() && adjunto.getIndice() == indice) {
                    adjuntoBorrar = adjunto;
                    break;
                }
            }
            
            if(adjuntoBorrar != null){
                this.objeto.getListaGsAdjuntos().remove(adjuntoBorrar);
            }

            if(GsAdjunto.TIPO_ADJUNTO_DOCUMENTO == tipoAdjunto){
              this.setNombreDocumento("");
              this.mostrarBorrarDocumento = false;
            }
            
            if (GsAdjunto.TIPO_ADJUNTO_CERTIFICADO == tipoAdjunto) {
                if (indice == 1) {
                    this.mostrarBorrarCertificado1 = false;
                    this.nombreCertificado1 = "";
                }

                if (indice == 2) {
                    this.mostrarBorrarCertificado2 = false;
                    this.nombreCertificado2 = "";
                }

                if (indice == 3) {
                    this.mostrarBorrarCertificado3 = false;
                    this.nombreCertificado3 = "";
                }
            }
        }
    }

}
