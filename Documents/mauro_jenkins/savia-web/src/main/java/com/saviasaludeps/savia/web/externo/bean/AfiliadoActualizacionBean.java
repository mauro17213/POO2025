/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1Adjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.externo.AfiliadoActualizacion;
import com.saviasaludeps.savia.web.externo.servicio.AfiliadoActualizacionServicioIface;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.Direccion;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;
//import org.primefaces.context.RequestContext;

/**
 *
 * @author admin
 */
@ManagedBean
@ViewScoped
public class AfiliadoActualizacionBean extends Url {
    
    @Autowired
    private AfiliadoActualizacionServicioIface afiliadoActualizacionServicio;

    private AsegAfiliado objeto;
    
    private AsegAnexo1 objetoAnexo1;
    private AsegAnexo1Adjunto objetoAdjunto;
    private List<AsegAnexo1Adjunto> listaAsegAnexo1Adjuntos;    

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
    private HashMap<Integer, Maestro> hashTiposGenero;
    private List<Maestro> listaTiposGenero;
    private Date fechaMaximaCalendario;
    private UploadedFile archivoAdjunto;
    private boolean mostrarBorrarDocumento;
    private String nombreDocumento;
    public final static int ESTADO_PENDIENTE = 1;
    public final static int ESTADO_GESTIONADO = 2;
    public final static int ESTADO_RECHAZADO = 3;
    
    private Direccion direccion;
    
    //2024-04-03 jyperez nuevos campos RES 2335
    //2024-04-10 jyperez revisando sincronización de cambios
    private String numeroDocumentoPrestador;
    //autocompletar prestador_sedes
    private List<CntPrestadorSede> listaCntPrestadorSedes;
    private boolean sedeRequerida;
    private boolean direccionAlterna;

    static {
        listaZonas = AfiliadoActualizacion.getTipoZonas();
    }

    public AfiliadoActualizacionBean() {
        this.objeto = new AsegAfiliado();
        this.fechaMaximaCalendario = new Date();
        this.objetoAdjunto =new AsegAnexo1Adjunto();
        this.listaAsegAnexo1Adjuntos = new ArrayList<>();
        //this.objeto.setDepartamentoResidencia(String.valueOf(IDENTIFICADOR_DEPARTMENTO_POR_DEFECTO));
    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getAfiliadoActualizacionServicio().cargaInicial(this);
        if (super.isError()) {
            //RequestContext.getCurrentInstance().update("frmAfiliadoActualizacion");
            //RequestContext.getCurrentInstance().update("frmBusquedaAfiliado");
            PrimeFaces.current().ajax().update("frmAfiliadoActualizacion");
            PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
        }
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case ACCION_BUSCAR_AFILLIADO_SERVICE:
                    PrimeFaces.current().ajax().update("frmAfiliadoActualizacion");
                    PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
                    //RequestContext.getCurrentInstance().update("frmAfiliadoActualizacion");
                    //RequestContext.getCurrentInstance().update("frmBusquedaAfiliado");
                    break;
                case Url.ACCION_LISTAR:
                    break;
            }
        }
        generarMensajes();
    }
    
    public void validarResolucion1581() {
        //lanzamos mensaje emergente que permitirá la gestión de los datos.
        PrimeFaces.current().ajax().update("frmResolucion1581");
        PrimeFaces.current().executeScript("PF('dialogoResolucion1581').show()");
    }
    
    public void aceptarResolucion1581() {
        PrimeFaces.current().executeScript("PF('dialogoResolucion1581').hide()");
        buscarAfiliadoServicio();
    }
    
    public void rechazarResolucion1581() {
        limpiarFormularioBusqueda();
        PrimeFaces.current().executeScript("PF('dialogoResolucion1581').hide()");
    }


    public void buscarAfiliadoServicio() {
        super.setAccion(ACCION_BUSCAR_AFILLIADO_SERVICE);
        getAfiliadoActualizacionServicio().Accion(this);
        limpiarFormularioDatosInsertar();
        PrimeFaces.current().ajax().update("frmAfiliadoActualizacion");
        PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
//        RequestContext.getCurrentInstance().update("frmAfiliadoActualizacion");
//        RequestContext.getCurrentInstance().update("frmBusquedaAfiliado");
        procesoFinal();
    }

    public String obtenerTipoDocumentoPersona(String id) {
        try {
            return getHashTiposDocumento().get(Integer.parseInt(id)).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerTipoGenero(String id) {
        try {
            return hashTiposGenero.get(Integer.parseInt(id)).getNombre();
        } catch (NumberFormatException e) {
            return id;
        }
    }

    public void guardarAfiliado() {

        if (!validarExpedicionSiTipoDocCedula()) {
            this.addError("Cuando el tipo de documento del afiliado es cedula, se debe registrar su fecha de expedición.");
            generarMensajes();
            return;
        }
//
//        if (!validarReglaViaDiferenteSinDireccion()) {
//            this.addError("Para este tipo de via ingrese los campos (Nro y Placa).");
//            generarMensajes();
//            return;
//        }
//
//        if (!validarLongitudDireccion()) {
//            this.addError("El tipo de vía o dirección del afiliado es obligatoria.");
//            generarMensajes();
//            return;
//        }
//
        if (!validarExistenciaUnTelefono()) {
            this.addError("Se requiere un numero de contacto, por favor ingrese el número de teléfono fijo y/o número de celular.");
            generarMensajes();
            return;
        }
        
        if (this.listaAsegAnexo1Adjuntos.isEmpty()) {
            this.addError("Debe adjuntar el documento de identidad y/o soporte de la actualización solicitada.");
            generarMensajes();
            return;
        }

        super.setAccion(ACCION_GUARDAR_AFILIADO_SERVICE);
        this.setIpTerminalCrea(obtenerIpPeticion());
        getAfiliadoActualizacionServicio().Accion(this);
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
        this.getObjeto().setNumeroDocumento("");
        //this.getObjeto().setTipoDocumento(0);
        this.getObjeto().setFechaNacimiento(null);
        this.setHabilitarSeccionResultados(false);
        PrimeFaces.current().ajax().update("frmAfiliadoActualizacion");
        PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
//        RequestContext.getCurrentInstance().update("frmAfiliadoActualizacion");
//        RequestContext.getCurrentInstance().reset("frmAfiliadoActualizacion");
//        RequestContext.getCurrentInstance().update("frmBusquedaAfiliado");
//        RequestContext.getCurrentInstance().reset("frmBusquedaAfiliado");
    }

    public void limpiarFormulario() {
        this.getObjeto().setNumeroDocumento("");
        //this.getObjeto().setTipoDocumento(0);
        this.getObjeto().setFechaNacimiento(null);
        limpiarDatosEncontrados();
        limpiarFormularioDatosInsertar();
        this.setHabilitarSeccionResultados(false);
        PrimeFaces.current().ajax().update("frmAfiliadoActualizacion");
        PrimeFaces.current().ajax().update("frmBusquedaAfiliado");
//        RequestContext.getCurrentInstance().update("frmAfiliadoActualizacion");
//        RequestContext.getCurrentInstance().reset("frmAfiliadoActualizacion");
//        RequestContext.getCurrentInstance().update("frmBusquedaAfiliado");
//        RequestContext.getCurrentInstance().reset("frmBusquedaAfiliado");
    }

    public void limpiarDatosEncontrados() {
        this.getObjeto().setPrimerNombre("");
        this.getObjeto().setPrimerApellido("");
        this.getObjeto().setSegundoNombre("");
    }

    public void limpiarFormularioDatosInsertar() {
        this.getObjeto().setDireccionVia("");
        this.getObjeto().setDireccionNro("");
        this.getObjeto().setDireccionOrd1("");
        this.getObjeto().setDireccionOrientacion("");
        this.getObjeto().setDireccionPlaca1("");
        this.getObjeto().setDireccionOrd2("");
        this.getObjeto().setDireccionPlaca2("");
        this.getObjeto().setDireccionDescripcion("");
        this.getObjeto().setZona("");
        this.getObjeto().setTelefonoFijo("");
        this.getObjeto().setTelefonoMovil("");
        this.getObjeto().setEmail("");
        this.getObjeto().setObservacionNovedad("");
        //this.getObjeto().setFechaExpedicionCedula(null);
        //this.getObjeto().getCiudadResidencia().setId(0);
    }

    private String obtenerIpPeticion() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    private boolean validarExpedicionSiTipoDocCedula() {
        boolean esValido = true;
        if (this.getObjeto().getMaeTipoDocumento() == 401) {
            return this.getObjetoAnexo1().getFechaExpedicionCedulaNuevo() != null;
        }
        return esValido;
    }

    private boolean validarReglaViaDiferenteSinDireccion() {
        boolean esValido = true;
        if (!this.getObjetoAnexo1().getDireccionNuevo().equals("SD")
                && !this.getObjetoAnexo1().getDireccionNuevo().equals("")) {
            return !"".equals(this.getObjetoAnexo1().getDireccionNuevo());
        }
        return esValido;
    }

    private boolean validarLongitudDireccion() {
        boolean esValido = true;
        if (this.getObjetoAnexo1().getDireccionNuevo().trim().length() <= 0) {
            esValido = false;
        }
        return esValido;
    }

    private boolean validarExistenciaUnTelefono() {
        boolean esValido = true;

        if ((this.getObjetoAnexo1().getTelefonoNuevo() == null || this.getObjetoAnexo1().getTelefonoNuevo().isEmpty())) {
            if ( this.getObjetoAnexo1().getCelularNuevo()== null || this.getObjetoAnexo1().getCelularNuevo().isEmpty()) {
                esValido = false;
            }
        }
        if ( this.getObjetoAnexo1().getCelularNuevo()== null || this.getObjetoAnexo1().getCelularNuevo().isEmpty()) {
            if ((this.getObjetoAnexo1().getTelefonoNuevo() == null || this.getObjetoAnexo1().getTelefonoNuevo().isEmpty())) {
                esValido = false;
            }
        }
        return esValido;
    }
    
    public void cargarArchivoDocumento(FileUploadEvent event) throws IOException {
        setArchivoAdjunto(event.getFile());
        objetoAdjunto.setAdjuntoStream(getArchivoAdjunto().getInputStream());
        this.mostrarBorrarDocumento = true;
        if (nombreDocumento == null) {
            nombreDocumento = getArchivoAdjunto().getFileName();
        } else {
            nombreDocumento = nombreDocumento + " - " + getArchivoAdjunto().getFileName();
        }
        agregarArchivo();
    }
    
    public void borrarArchivo() {
        archivoAdjunto = null;
        listaAsegAnexo1Adjuntos =null;
        this.mostrarBorrarDocumento = false;
        nombreDocumento = "";
    }
    
    public void agregarArchivo() {
        try {

            AsegAnexo1Adjunto borrarObj = new AsegAnexo1Adjunto();
            getListaAsegAnexo1Adjuntos().remove(borrarObj);

            String nombreAdjunto = FilenameUtils.getName(archivoAdjunto.getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".") + 1;
            objetoAdjunto.setExtensión(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            objetoAdjunto.setNombre(nombreAdjunto.substring(0, indiceExtension));
            objetoAdjunto.setFechaHoraCrea(new Date());
            getListaAsegAnexo1Adjuntos().add(objetoAdjunto);
            objetoAdjunto = new AsegAnexo1Adjunto();
            
            PrimeFaces.current().ajax().update("frmCrearAdjunto:tablaAdjuntosCrear");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').hide()");
        } catch (Exception ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }

    public void crearDireccion() {
        //2024-04-03 jyperez incluimos variable para alternar el dialogo de dirección
        this.direccionAlterna = false;
        direccion = new Direccion();
        PrimeFaces.current().ajax().update("frmDireccion");
        PrimeFaces.current().executeScript("PF('dialogoDireccion').show()");
    }
    
    public void crearDireccionAlterna() {
        //2024-04-03 jyperez incluimos variable para alternar el dialogo de dirección
        this.direccionAlterna = true;
        direccion = new Direccion();
        PrimeFaces.current().ajax().update("frmDireccion");
        PrimeFaces.current().executeScript("PF('dialogoDireccion').show()");
    }

    public void retornarDireccion() {
        if (this.getDireccion().getVia().equalsIgnoreCase("SD")) {
            if (getDireccion().getDescripcion().trim().isEmpty()) {
                addError("Descripción: Este campo es obligatorio.");
                generarMensajes();
                return;
            }
        }
        if (isDireccionAlterna()) {
            this.objetoAnexo1.setDireccionAlternaContactoNuevo(direccion.getDireccion());
            PrimeFaces.current().ajax().update("frmAfiliadoActualizacion:direccionAlternaNuevo");
        } else {
            this.objetoAnexo1.setDireccionNuevo(direccion.getDireccion());
            PrimeFaces.current().ajax().update("frmAfiliadoActualizacion:direccionNuevo");
        }
        PrimeFaces.current().executeScript("PF('dialogoDireccion').hide()");
    }
    
    public void cerrarDireccion() {
        PrimeFaces.current().executeScript("PF('dialogoDireccion').hide()");
    }

    public void salir() {
        //this.objeto = null;
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap");
        session.removeAttribute("portabilidadBean");
    }

    public void cambiarExigenciaDatosSegunVia() {
        String tipoVia = this.getObjeto().getDireccionVia();
        if (!"".equals(tipoVia)) {
            if (tipoVia.equalsIgnoreCase("SD")) {
                cambiarEstadoCamposSinDireccion(true);
            } else {
                cambiarEstadoCamposSinDireccion(false);
            }

        } else {
            cambiarEstadoCamposSinDireccion(false);
        }
        PrimeFaces.current().ajax().update("frmAfiliadoActualizacion:contentPanelDireccion");
//        RequestContext.getCurrentInstance().update("frmAfiliadoActualizacion:contentPanelDireccion");
//        RequestContext.getCurrentInstance().reset("frmAfiliadoActualizacion:contentPanelDireccion");
    }

    private void cambiarEstadoCamposSinDireccion(boolean tipoHabilitacion) {
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
            getObjetoAnexo1().setResidenciaUbicacionNuevo(ubicacionesFiltradas.get(0));
        }
        return ubicacionesFiltradas;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public AsegAnexo1 getObjetoAnexo1() {
        return objetoAnexo1;
    }

    public void setObjetoAnexo1(AsegAnexo1 objetoAnexo1) {
        this.objetoAnexo1 = objetoAnexo1;
    }

    public AsegAnexo1Adjunto getObjetoAdjunto() {
        return objetoAdjunto;
    }

    public void setObjetoAdjunto(AsegAnexo1Adjunto objetoAdjunto) {
        this.objetoAdjunto = objetoAdjunto;
    }

    public List<AsegAnexo1Adjunto> getListaAsegAnexo1Adjuntos() {
        return listaAsegAnexo1Adjuntos;
    }

    public void setListaAsegAnexo1Adjuntos(List<AsegAnexo1Adjunto> listaAsegAnexo1Adjuntos) {
        this.listaAsegAnexo1Adjuntos = listaAsegAnexo1Adjuntos;
    }

    public UploadedFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(UploadedFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public HashMap<Integer, Maestro> getHashTiposGenero() {
        return hashTiposGenero;
    }

    public void setHashTiposGenero(HashMap<Integer, Maestro> hashTiposGenero) {
        this.hashTiposGenero = hashTiposGenero;
    }

    public List<Maestro> getListaTiposGenero() {
        return listaTiposGenero;
    }

    public void setListaTiposGenero(List<Maestro> listaTiposGenero) {
        this.listaTiposGenero = listaTiposGenero;
    }

    public Date getFechaMaximaCalendario() {
        return fechaMaximaCalendario;
    }

    public void setFechaMaximaCalendario(Date fechaMaximaCalendario) {
        this.fechaMaximaCalendario = fechaMaximaCalendario;
    }

    /**
     * @return the mostrarBorrarDocumento
     */
    public boolean isMostrarBorrarDocumento() {
        return mostrarBorrarDocumento;
    }

    /**
     * @param mostrarBorrarDocumento the mostrarBorrarDocumento to set
     */
    public void setMostrarBorrarDocumento(boolean mostrarBorrarDocumento) {
        this.mostrarBorrarDocumento = mostrarBorrarDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public AsegAfiliado getObjeto() {
        return objeto;
    }

    public void setObjeto(AsegAfiliado objeto) {
        this.objeto = objeto;
    }

    public AfiliadoActualizacionServicioIface getAfiliadoActualizacionServicio() {
        return afiliadoActualizacionServicio;
    }

    public void setAfiliadoActualizacionServicio(AfiliadoActualizacionServicioIface afiliadoActualizacionServicio) {
        this.afiliadoActualizacionServicio = afiliadoActualizacionServicio;
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

    public static int getESTADO_PENDIENTE() {
        return ESTADO_PENDIENTE;
    }

    public static int getESTADO_GESTIONADO() {
        return ESTADO_GESTIONADO;
    }

    public static int getESTADO_RECHAZADO() {
        return ESTADO_RECHAZADO;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the numeroDocumentoPrestador
     */
    public String getNumeroDocumentoPrestador() {
        return numeroDocumentoPrestador;
    }

    /**
     * @param numeroDocumentoPrestador the numeroDocumentoPrestador to set
     */
    public void setNumeroDocumentoPrestador(String numeroDocumentoPrestador) {
        this.numeroDocumentoPrestador = numeroDocumentoPrestador;
    }

    /**
     * @return the listaCntPrestadorSedes
     */
    public List<CntPrestadorSede> getListaCntPrestadorSedes() {
        return listaCntPrestadorSedes;
    }

    /**
     * @param listaCntPrestadorSedes the listaCntPrestadorSedes to set
     */
    public void setListaCntPrestadorSedes(List<CntPrestadorSede> listaCntPrestadorSedes) {
        this.listaCntPrestadorSedes = listaCntPrestadorSedes;
    }

    /**
     * @return the sedeRequerida
     */
    public boolean isSedeRequerida() {
        return sedeRequerida;
    }

    /**
     * @param sedeRequerida the sedeRequerida to set
     */
    public void setSedeRequerida(boolean sedeRequerida) {
        this.sedeRequerida = sedeRequerida;
    }

    /**
     * @return the direccionAlterna
     */
    public boolean isDireccionAlterna() {
        return direccionAlterna;
    }

    /**
     * @param direccionAlterna the direccionAlterna to set
     */
    public void setDireccionAlterna(boolean direccionAlterna) {
        this.direccionAlterna = direccionAlterna;
    }

}
