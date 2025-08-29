/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.atencionusuario.AusAdjunto;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSeguimiento;
import static com.saviasaludeps.savia.web.externo.bean.AtencionConsultaCasoBean.ACCION_BUSCAR_SOLICITUDES_CASO_SERVICE;
import static com.saviasaludeps.savia.web.atencionusuario.bean.AusCasoBean.SESION_ADJUNTO_CASOS;
import static com.saviasaludeps.savia.web.atencionusuario.bean.AusCasoBean.SESION_ADJUNTO_SEGUIMIENTOS;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusPersonaBean;
import com.saviasaludeps.savia.web.atencionusuario.utilities.PropAtencionUsuario;
import com.saviasaludeps.savia.web.externo.servicio.AtencionCreacionCasoServicioImpl;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import com.saviasaludeps.savia.web.externo.servicio.AtencionCreacionCasoServicioIface;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author pavacca
 */
@ManagedBean
@ViewScoped
public class AtencionCreacionCasoBean extends Url {

    private AusCaso objeto;
    private AtencionCreacionCasoServicioIface atencionCreacionCaso;
    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;

    private List<Maestro> listaSexo;
    private HashMap<Integer, Maestro> hashSexo;

    private List<Maestro> listaTipoEstadoPersona;
    private HashMap<Integer, Maestro> hashTipoEstadosPersona;

    private List<Maestro> listaTipoSeguimiento;
    private HashMap<Integer, Maestro> hashTipoSeguimiento;

    private List<Maestro> listaTipoSolicitud;
    private HashMap<Integer, Maestro> hashTipoSolicitud;

    private List<Maestro> listaTipoSolicitudOrigen;
    private HashMap<Integer, Maestro> hashTipoSolicitudOrigen;

    private List<Maestro> listaTipoEstadoSolicitud;
    private HashMap<Integer, Maestro> hashTipoEstadoSolicitud;

    private List<Maestro> listaTipoSolicitudPrioridad;
    private HashMap<Integer, Maestro> hashTipoSolicitudPrioridad;

    private List<Maestro> listaCanalSuperSalud;
    private HashMap<Integer, Maestro> hashCanalSuperSalud;

    private List<Maestro> listaTipoSolicitudEnteControl;
    private HashMap<Integer, Maestro> hashTipoSolicitudEnteControl;

    private List<Maestro> listaTipoSolicitudRiesgoVida;
    private HashMap<Integer, Maestro> hashTipoSolicitudRiesgoVida;

    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;

    private List<Usuario> listaUsuarios;
    private HashMap<Integer, Usuario> hashUsuarios;

    private AusPersonaBean personaBean;
    private AusPersonaTelefono personaTelefono;
    private AusSeguimiento seguimientoProcesar;

    private int contadorArchivos = 0;
    private int sizeLimitByAnexo;
    private int maxCantAnexos;

    private String nombre;
    private String usuario;

    public boolean deshabilitarCampoSeguimiento;
    public boolean deshabilitarCampoNumeroRadicado;
    public boolean campoObligatorioCorreoPersona;
    public boolean estaHabilitadoCampo;
    
    public boolean habilitarCampoDireccion ;
    public boolean habilitarCampoEmail;
    private List<AusPersonaTelefono> listaPersonaTelefono;

    @Autowired
    private UbicacionSingle ubicacionSingle;
    
    public AtencionCreacionCasoBean() {
        this.objeto = new AusCaso();
        this.objeto.setAusPersonasId(new AusPersona());
        //this.fechaMaxima = new Date();
        this.listaTiposDocumento = new ArrayList<>();
        this.hashTiposDocumento = new HashMap<>();
        this.atencionCreacionCaso = new AtencionCreacionCasoServicioImpl();
        this.personaBean = new AusPersonaBean(new AusPersona());
        this.personaTelefono = new AusPersonaTelefono();
        vaciarSesionAdjuntos(SESION_ADJUNTO_CASOS);
        this.listaPersonaTelefono = new ArrayList<>();
        asignarEstadoInicialCaso();
        this.getObjeto().getAusPersonasId().setDesHabilitarCampoPersona(true);
        setMaxCantAnexos(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.MAX_CANT_ANEXOS)));
        setSizeLimitByAnexo(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.MAX_TAM_ANEXO)));

    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getAtencionCreacionCaso().cargaInicial(this);
        vaciarSesionAdjuntos(SESION_ADJUNTO_CASOS);
        asignarResponsable();
        //personaBean = new AusPersonaBean();
        this.setTamanoPagina(10);
        if (super.isError()) {
            PrimeFaces.current().resetInputs("frmCrear");
            PrimeFaces.current().ajax().update("frmCrear");
        }
        procesoFinal();
    }

    public AusCaso getObjeto() {
        return objeto;
    }

    public void setObjeto(AusCaso objeto) {
        this.objeto = objeto;
    }

    public AtencionCreacionCasoServicioIface getAtencionCreacionCaso() {
        return atencionCreacionCaso;
    }

    public void setAtencionCreacionCaso(AtencionCreacionCasoServicioIface atencionCreacionCaso) {
        this.atencionCreacionCaso = atencionCreacionCaso;
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

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public List<Maestro> getListaSexo() {
        return listaSexo;
    }

    public void setListaSexo(List<Maestro> listaSexo) {
        this.listaSexo = listaSexo;
    }

    public HashMap<Integer, Maestro> getHashSexo() {
        return hashSexo;
    }

    public void setHashSexo(HashMap<Integer, Maestro> hashSexo) {
        this.hashSexo = hashSexo;
    }

    public List<Maestro> getListaTipoEstadoPersona() {
        return listaTipoEstadoPersona;
    }

    public void setListaTipoEstadoPersona(List<Maestro> listaTipoEstadoPersona) {
        this.listaTipoEstadoPersona = listaTipoEstadoPersona;
    }

    public HashMap<Integer, Maestro> getHashTipoEstadosPersona() {
        return hashTipoEstadosPersona;
    }

    public void setHashTipoEstadosPersona(HashMap<Integer, Maestro> hashTipoEstadosPersona) {
        this.hashTipoEstadosPersona = hashTipoEstadosPersona;
    }

    public List<Maestro> getListaTipoSeguimiento() {
        return listaTipoSeguimiento;
    }

    public void setListaTipoSeguimiento(List<Maestro> listaTipoSeguimiento) {
        this.listaTipoSeguimiento = listaTipoSeguimiento;
    }

    public HashMap<Integer, Maestro> getHashTipoSeguimiento() {
        return hashTipoSeguimiento;
    }

    public void setHashTipoSeguimiento(HashMap<Integer, Maestro> hashTipoSeguimiento) {
        this.hashTipoSeguimiento = hashTipoSeguimiento;
    }

    public List<Maestro> getListaTipoSolicitudOrigen() {
        return listaTipoSolicitudOrigen;
    }

    public void setListaTipoSolicitudOrigen(List<Maestro> listaTipoSolicitudOrigen) {
        this.listaTipoSolicitudOrigen = listaTipoSolicitudOrigen;
    }

    public HashMap<Integer, Maestro> getHashTipoSolicitudOrigen() {
        return hashTipoSolicitudOrigen;
    }

    public void setHashTipoSolicitudOrigen(HashMap<Integer, Maestro> hashTipoSolicitudOrigen) {
        this.hashTipoSolicitudOrigen = hashTipoSolicitudOrigen;
    }

    public List<Maestro> getListaTipoEstadoSolicitud() {
        return listaTipoEstadoSolicitud;
    }

    public void setListaTipoEstadoSolicitud(List<Maestro> listaTipoEstadoSolicitud) {
        this.listaTipoEstadoSolicitud = listaTipoEstadoSolicitud;
    }

    public HashMap<Integer, Maestro> getHashTipoEstadoSolicitud() {
        return hashTipoEstadoSolicitud;
    }

    public void setHashTipoEstadoSolicitud(HashMap<Integer, Maestro> hashTipoEstadoSolicitud) {
        this.hashTipoEstadoSolicitud = hashTipoEstadoSolicitud;
    }

    public List<Maestro> getListaTipoSolicitudPrioridad() {
        return listaTipoSolicitudPrioridad;
    }

    public void setListaTipoSolicitudPrioridad(List<Maestro> listaTipoSolicitudPrioridad) {
        this.listaTipoSolicitudPrioridad = listaTipoSolicitudPrioridad;
    }

    public HashMap<Integer, Maestro> getHashTipoSolicitudPrioridad() {
        return hashTipoSolicitudPrioridad;
    }

    public void setHashTipoSolicitudPrioridad(HashMap<Integer, Maestro> hashTipoSolicitudPrioridad) {
        this.hashTipoSolicitudPrioridad = hashTipoSolicitudPrioridad;
    }

    public List<Maestro> getListaCanalSuperSalud() {
        return listaCanalSuperSalud;
    }

    public void setListaCanalSuperSalud(List<Maestro> listaCanalSuperSalud) {
        this.listaCanalSuperSalud = listaCanalSuperSalud;
    }

    public HashMap<Integer, Maestro> getHashCanalSuperSalud() {
        return hashCanalSuperSalud;
    }

    public void setHashCanalSuperSalud(HashMap<Integer, Maestro> hashCanalSuperSalud) {
        this.hashCanalSuperSalud = hashCanalSuperSalud;
    }

    public List<Maestro> getListaTipoSolicitudEnteControl() {
        return listaTipoSolicitudEnteControl;
    }

    public void setListaTipoSolicitudEnteControl(List<Maestro> listaTipoSolicitudEnteControl) {
        this.listaTipoSolicitudEnteControl = listaTipoSolicitudEnteControl;
    }

    public HashMap<Integer, Maestro> getHashTipoSolicitudEnteControl() {
        return hashTipoSolicitudEnteControl;
    }

    public void setHashTipoSolicitudEnteControl(HashMap<Integer, Maestro> hashTipoSolicitudEnteControl) {
        this.hashTipoSolicitudEnteControl = hashTipoSolicitudEnteControl;
    }

    public List<Maestro> getListaTipoSolicitud() {
        return listaTipoSolicitud;
    }

    public void setListaTipoSolicitud(List<Maestro> listaTipoSolicitud) {
        this.listaTipoSolicitud = listaTipoSolicitud;
    }

    public HashMap<Integer, Maestro> getHashTipoSolicitud() {
        return hashTipoSolicitud;
    }

    public void setHashTipoSolicitud(HashMap<Integer, Maestro> hashTipoSolicitud) {
        this.hashTipoSolicitud = hashTipoSolicitud;
    }

    public List<Maestro> getListaTipoSolicitudRiesgoVida() {
        return listaTipoSolicitudRiesgoVida;
    }

    public void setListaTipoSolicitudRiesgoVida(List<Maestro> listaTipoSolicitudRiesgoVida) {
        this.listaTipoSolicitudRiesgoVida = listaTipoSolicitudRiesgoVida;
    }

    public HashMap<Integer, Maestro> getHashTipoSolicitudRiesgoVida() {
        return hashTipoSolicitudRiesgoVida;
    }

    public void setHashTipoSolicitudRiesgoVida(HashMap<Integer, Maestro> hashTipoSolicitudRiesgoVida) {
        this.hashTipoSolicitudRiesgoVida = hashTipoSolicitudRiesgoVida;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public HashMap<Integer, Usuario> getHashUsuarios() {
        return hashUsuarios;
    }

    public void setHashUsuarios(HashMap<Integer, Usuario> hashUsuarios) {
        this.hashUsuarios = hashUsuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getContadorArchivos() {
        return contadorArchivos;
    }

    public void setContadorArchivos(int contadorArchivos) {
        this.contadorArchivos = contadorArchivos;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public boolean isDeshabilitarCampoSeguimiento() {
        return deshabilitarCampoSeguimiento;
    }

    public void setDeshabilitarCampoSeguimiento(boolean deshabilitarCampoSeguimiento) {
        this.deshabilitarCampoSeguimiento = deshabilitarCampoSeguimiento;
    }

    public boolean isDeshabilitarCampoNumeroRadicado() {
        return deshabilitarCampoNumeroRadicado;
    }

    public void setDeshabilitarCampoNumeroRadicado(boolean deshabilitarCampoNumeroRadicado) {
        this.deshabilitarCampoNumeroRadicado = deshabilitarCampoNumeroRadicado;
    }

    public boolean isCampoObligatorioCorreoPersona() {
        return campoObligatorioCorreoPersona;
    }

    public void setCampoObligatorioCorreoPersona(boolean campoObligatorioCorreoPersona) {
        this.campoObligatorioCorreoPersona = campoObligatorioCorreoPersona;
    }

    public boolean isEstaHabilitadoCampo() {
        return estaHabilitadoCampo;
    }

    public void setEstaHabilitadoCampo(boolean estaHabilitadoCampo) {
        this.estaHabilitadoCampo = estaHabilitadoCampo;
    }

    public boolean isHabilitarCampoDireccion() {
        return habilitarCampoDireccion;
    }

    public void setHabilitarCampoDireccion(boolean habilitarCampoDireccion) {
        this.habilitarCampoDireccion = habilitarCampoDireccion;
    }

    public boolean isHabilitarCampoEmail() {
        return habilitarCampoEmail;
    }

    public void setHabilitarCampoEmail(boolean habilitarCampoEmail) {
        this.habilitarCampoEmail = habilitarCampoEmail;
    }
    
    
    public int getSizeLimitByAnexo() {
        return sizeLimitByAnexo;
    }

    public void setSizeLimitByAnexo(int sizeLimitByAnexo) {
        this.sizeLimitByAnexo = sizeLimitByAnexo;
    }

    public int getMaxCantAnexos() {
        return maxCantAnexos;
    }

    public AusSeguimiento getSeguimientoProcesar() {
        return seguimientoProcesar;
    }

    public void setSeguimientoProcesar(AusSeguimiento seguimientoProcesar) {
        this.seguimientoProcesar = seguimientoProcesar;
    }

    public void setMaxCantAnexos(int maxCantAnexos) {
        this.maxCantAnexos = maxCantAnexos;
    }

    public AusPersonaBean getPersonaBean() {
        return personaBean;
    }

    public void setPersonaBean(AusPersonaBean personaBean) {
        this.personaBean = personaBean;
    }

    public AusPersonaTelefono getPersonaTelefono() {
        return personaTelefono;
    }

    public void setPersonaTelefono(AusPersonaTelefono personaTelefono) {
        this.personaTelefono = personaTelefono;
    }

    public List<AusPersonaTelefono> getListaPersonaTelefono() {
        return listaPersonaTelefono;
    }

    public void setListaPersonaTelefono(List<AusPersonaTelefono> listaPersonaTelefono) {
        this.listaPersonaTelefono = listaPersonaTelefono;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAtencionCreacionCaso().Accion(this);
    }

    public UbicacionSingle getUbicacionSingle() {
        return ubicacionSingle;
    }

    public void setUbicacionSingle(UbicacionSingle ubicacionSingle) {
        this.ubicacionSingle = ubicacionSingle;
    }

    /**
     * *********************************
     *****PROCESOS SOBRE UBICACIONES***** **********************************
     */
    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion _municipio = ubicacionesRecursiva.get(id);
        if (_municipio != null && _municipio.getUbicacionPadre() != null) {
            Ubicacion _departamento = _municipio.getUbicacionPadre();
            if (_departamento.getUbicacionPadre() != null) {
                Ubicacion _pais = _departamento.getUbicacionPadre();
                ubicacionStr = _pais.getNombre();
            }
            ubicacionStr = _departamento.getNombre() + " - " + ubicacionStr;
            ubicacionStr = _municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    public void terminosCondiciones() {
        PrimeFaces.current().executeScript("PF('dialogoTerminosYcondiciones').show()");
    }
    
    public void guardarCaso() throws ParseException{
        asignarFechas();
        asignarEstadoCasoSegunSeguimiento();

        AusPersona personaModificar = getObjeto().getAusPersonasId();
        personaBean.setObjeto(this.getObjeto().getAusPersonasId());
        if (personaBean.getObjeto().getPersonaAnonima()) {
            if (getHashTiposDocumento() != null && !getHashTiposDocumento().isEmpty()) {
                personaBean.getObjeto().setId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_PERSONA_ANONIMA)));
                getAtencionCreacionCaso().consultarPersonaAnonima(personaBean);
            }
        } else {
            getAtencionCreacionCaso().consultarPersona(personaBean);
        }
        asignacionResultadosConsultaParaBeanCaso();
        if (this.getObjeto().getAusPersonasId().exitePersona()) {
            personaModificar.setId(this.getObjeto().getAusPersonasId().getId());
            if (personaModificar.getPersonaAnonima()) {
                personaModificar.setNombres(this.getObjeto().getAusPersonasId().getNombres());
                personaModificar.setApellidos(this.getObjeto().getAusPersonasId().getApellidos());
                personaModificar.setMae_tipo_documento_id(this.getObjeto().getAusPersonasId().getMae_tipo_documento_id());
                personaModificar.setMae_tipo_documento_codigo(this.getObjeto().getAusPersonasId().getMae_tipo_documento_codigo());
                personaModificar.setDocumento(this.getObjeto().getAusPersonasId().getDocumento());
                personaModificar.setFechaNacimiento(this.getObjeto().getAusPersonasId().getFechaNacimiento());
                personaModificar.setMae_sexo_id(this.getObjeto().getAusPersonasId().getMae_sexo_id());
                personaModificar.setMae_estado_id(this.getObjeto().getAusPersonasId().getMae_estado_id());
                personaModificar.setEsRegimen(this.getObjeto().getAusPersonasId().getEsRegimen());
                personaModificar.setDiscapacidad(this.getObjeto().getAusPersonasId().getDiscapacidad());
                personaModificar.setGestante(this.getObjeto().getAusPersonasId().getGestante());
                personaModificar.setPersonaUbicacion(this.getObjeto().getAusPersonasId().getPersonaUbicacion());
            }
            this.getObjeto().setAusPersonasId(personaModificar);
        }

        if (listaPersonaTelefono == null) {
            listaPersonaTelefono = new ArrayList();
        }

        if (personaModificar.getTelefonoFijo() != null
                && !personaModificar.getTelefonoFijo().equals("")) {
            AusPersonaTelefono telefonoFijo = new AusPersonaTelefono();
            telefonoFijo.setNumero(personaModificar.getTelefonoFijo());
            this.listaPersonaTelefono.add(telefonoFijo);
        }

        if (personaModificar.getNumeroCelular() != null
                && !personaModificar.getNumeroCelular().equals("")) {
            AusPersonaTelefono numeroCelular = new AusPersonaTelefono();
            numeroCelular.setNumero(personaModificar.getNumeroCelular());
            this.listaPersonaTelefono.add(numeroCelular);
        }
        asignarTelefonoParaPersona(listaPersonaTelefono);
        super.setAccion(ACCION_GUARDAR);
        getAtencionCreacionCaso().Accion(this);
    }
    
    public void guardar() throws ParseException {
        if(this.objeto.getModalidadEntrega() != null){
            if(this.objeto.getModalidadEntrega().equals(1) && 
                this.getObjeto().getAusPersonasId().getDireccion() == null){
                PrimeFaces.current().executeScript("PF('dialogoTerminosYcondiciones').hide()");
                super.addError("El campo dirección es obligatoria");
                generarMensajes();
            }else{
                guardarCaso();
            }
        }else{
            guardarCaso();
        }
        
        if (!super.isError()) {
            limpiarFormularioBusqueda();
            PrimeFaces.current().resetInputs("frmCrear");
            PrimeFaces.current().executeScript("PF('dialogoTerminosYcondiciones').hide()");
        }
        procesoFinal();
        refrescar();
    }

    public void buscarPersonaEnSistema(Integer peticionario) {

        if (this.getObjeto().getAusPersonasId().getMae_tipo_documento_id() > 0
                && this.getObjeto().getAsuPersonasId().getDocumento() != null) {
            try {
                //rEFRESCAR BÚSQUEDA DE PERSONA
                this.getObjeto().getAusPersonasId().setId(0);
                this.getObjeto().getAusPersonasId().setEsAfiliado(false);
                if (!getHashTiposDocumento().isEmpty() && getHashTiposDocumento() != null) {
                    Maestro tipoDocumento = getHashTiposDocumento().get(this.getObjeto().getAusPersonasId().getMae_tipo_documento_id());
                    if (tipoDocumento != null) {
                        this.getObjeto().getAusPersonasId().setMae_tipo_documento_id(tipoDocumento.getId());
                        this.getObjeto().getAusPersonasId().setMae_tipo_documento_codigo(tipoDocumento.getValor());
                        this.getObjeto().getAusPersonasId().setMae_tipo_documento_valor(tipoDocumento.getNombre());
                    }
                }
                //Consultar persona en BD
                //personaBean = new AusPersonaBean(this.getObjeto().getAusPersonasId());
                personaBean.setObjeto(this.getObjeto().getAusPersonasId());
                getAtencionCreacionCaso().consultarPersona(personaBean);
                //getPersonaServicio().consultarPersona(personaBean);
                asignacionResultadosConsultaParaBeanCaso();
                if (!this.getObjeto().getAusPersonasId().exitePersona()) {
                    asignacionHashsParaBeanPersona();
                    //getAusCasoServicio().buscarAfiliadoEnSomosMas(personaBean);
                    getAtencionCreacionCaso().consultarPersonaAfiliada(personaBean);/// pendiente implementacion
                    if (personaBean.getErrores().size() > 0) {
                        this.addError(personaBean.getErrores().get(0));
                        personaBean.getErrores().clear();
                    }
                    asignacionResultadosConsultaParaBeanCaso();
                }
                if (!this.getObjeto().getAusPersonasId().exitePersona() && !existePersonaAfiliada()) {
                    String documento = this.getObjeto().getAusPersonasId().getDocumento();
                    int tipoDoc = this.getObjeto().getAusPersonasId().getMae_tipo_documento_id();
                    this.getObjeto().setAusPersonasId(new AusPersona());
                    this.getObjeto().getAusPersonasId().setMae_tipo_documento_id(tipoDoc);
                    this.getObjeto().getAusPersonasId().setDocumento(documento);
                    this.listaPersonaTelefono = new ArrayList<>();
                }
                if (peticionario != 0) {
                    PrimeFaces.current().ajax().update("frmCrear");
                    PrimeFaces.current().ajax().update("frmCrear:telefonos");
                    this.getObjeto().getAusPersonasId().setDesHabilitarCampoPersona(true);
                    //PrimeFaces.current().ajax().update("frmEditar");
                }

                generarMensajes();
            } catch (Exception ex) {
                Logger.getLogger(AtencionCreacionCasoBean.class.getName()).log(Level.SEVERE, null, ex);
                addError(ex.getMessage());
                generarMensajes();
            }
        }
    }

    public void evaluarHabilitarCampoNumeroRadicadoCreat() {
        boolean desactivar = false;
        int origen = this.getObjeto().getMaeSolicitudOrigenId();
        String nombreOrigen = this.getHashTipoSolicitudOrigen().get(origen).getNombre();
        if (nombreOrigen.equals("SuperSalud")) {
            desactivar = true;
        }
        this.setDeshabilitarCampoNumeroRadicado(desactivar);
        PrimeFaces.current().ajax().update("frmCrear:panelCasos");
    }

    public List<Maestro> filtroTiposSeguimientosAceptados() {
        List<Maestro> tiposSeguimientosFiltrados = new ArrayList<>();
        if (getListaTipoSeguimiento() != null) {
            for (Maestro maestro : getListaTipoSeguimiento()) {
                //if (getIdTipoSeguimiento(AusCasoBean.DESC_SEGUIMIENTO_RADICADO) != maestro.getId()) {
                if (Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DESC_SEGUI_RADIC)) != maestro.getId()) {
                    tiposSeguimientosFiltrados.add(maestro);
                }
            }
        }

        return tiposSeguimientosFiltrados;
    }

    public void borrarAdjuntoCasoDeMemoria(int pos) {
        try {
            delAdjuntoCasoMemoria(pos);
        } catch (Exception e) {
            super.addError("No es posible borrar Adjunto");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrear:tablaAnexosCasos");
            PrimeFaces.current().ajax().update("frmEditar:tablaAnexosCasos");
        }
    }

    public void delAdjuntoCasoMemoria(int pos) {
        List<AusAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (AusAdjunto det : this.getObjeto().getAdjuntosList()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        this.getObjeto().setAdjuntosList(lista);
    }

    public void asignarFechas() throws ParseException {
        Date fechaCreacion = this.getObjeto().getFechaHoraCrea();
        //int origen = this.getObjeto().getMaeSolicitudOrigenId();
        int origen = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_ORIGEN_PAGINA_WEB));
        //int riesgoVida = this.getObjeto().getMaeSolicitudRiesgoVidalId();
        int riesgoVida = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_RIESGO_VIDA));
        if (this.hashTipoSolicitudOrigen.get(origen) != null
                && this.hashTipoSolicitudRiesgoVida.get(riesgoVida) != null) {
            String nombreOrigen = this.hashTipoSolicitudOrigen.get(origen).getNombre();
            String nombreRiesgoVida = this.hashTipoSolicitudRiesgoVida.get(riesgoVida).getNombre();
            int dias = 0;
            if (nombreRiesgoVida.equals("SIS")) {
                dias = 2;
            } else {
                if (nombreRiesgoVida.equals("Regular") && nombreOrigen.equals("SuperSalud")) {
                    dias = 5;
                } else {
                    dias = 13;
                }
            }
            Calendar fecha = Calendar.getInstance();
            if (fechaCreacion == null) {
                fechaCreacion = new Date();
            }
            fecha.setTime(fechaCreacion);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            List<Date> lista = new ArrayList();
            lista = getAtencionCreacionCaso().obtenerFechas(fechaCreacion);
            for (int i = 1; i <= dias; i++) {
                fecha.add(Calendar.DAY_OF_YEAR, 1);
                Date f1 = fecha.getTime();
                String date = formato.format(f1);
                for (Date fechaHabil : lista) {
                    String date2 = formato.format(fechaHabil);
                    if (date.equals(date2)) {
                        dias++;
                    }
                }
            }
            getObjeto().setFechaNotificacion(fechaCreacion);
            getObjeto().setFechaVencimiento(fecha.getTime());
        }

    }

    public void asignarEstadoCasoSegunSeguimiento() {
        int idEstadoSeguimiento = this.getObjeto().getSeguimientoAdicional().getMaeEstadoId();
        if (idEstadoSeguimiento > 0 && hashTipoSeguimiento != null) {
            Maestro maestro = hashTipoSeguimiento.get(idEstadoSeguimiento);
            if (maestro != null) {
                int estadoParaCaso = maestro.getId();
                if (estadoParaCaso > 0) {
                    this.getObjeto().setMaeSolicitudEstadoId(estadoParaCaso);
                }
            }
        }
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        try {

            String file = FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_caso_";
            String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
            UploadedFile archivo = event.getFile();
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            InputStream input = new ByteArrayInputStream(arreglo);

            String ruta = PropApl.getInstance().get(PropApl.AUS_RUTA_CARGA);
            if (ruta == null) {
                throw new IOException("Configura la ruta de los adjuntos  en tabla parametros");
            } else {
                File directorios = new File(ruta);
                if (!directorios.exists()) {
                    if (directorios.mkdirs()) {
                        System.out.println("Multiples directorios fueron creados");
                    } else {
                        System.out.println("Error al crear directorios");
                    }
                }
            }
            OutputStream output = new FileOutputStream(new File(ruta, filename));

            IOUtils.copy(input, output);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);

            List<AusAdjunto> listaAnexos = obtenerSesionAdjuntos(SESION_ADJUNTO_CASOS);
            if (listaAnexos == null) {
                listaAnexos = new ArrayList<>();
                this.getObjeto().getAdjuntosList().addAll(listaAnexos);
            }

            //agraegar el nuevo anexo a la lista de anexos de tutela
            AusAdjunto adjunto = new AusAdjunto();
            adjunto.setId(null);
            adjunto.setNombre(filename);
            adjunto.setArchivo(event.getFile().getFileName());
            adjunto.setRuta(ruta);
            Usuario usuarioAuditoria = getHashUsuarios().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_RESPONSABLE)));
            if (usuarioAuditoria != null) {
                adjunto.setUsuarioCrea(usuarioAuditoria.getUsuarioNombre());
                adjunto.setTerminalCrea("127.0.0.1");
                adjunto.setFechaHoraCrea(new Date());
            }
            adjunto.setPos(this.getObjeto().getAdjuntosList().size());

            listaAnexos.add(adjunto);

            this.getObjeto().getAdjuntosList().add(adjunto);

            llenarSesionAdjuntos(SESION_ADJUNTO_CASOS, listaAnexos);

            PrimeFaces.current().resetInputs("frmCrear:tablaAnexosCasos");
            PrimeFaces.current().ajax().update("frmCrear:tablaAnexosCasos");
            //PrimeFaces.current().resetInputs("frmEditar:tablaAnexosCasos");
            //PrimeFaces.current().ajax().update("frmEditar:tablaAnexosCasos");
        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto caso : " + e.getMessage());
            generarMensajes();
        }
    }

    public void handleFileUploadSeguimiento(FileUploadEvent event) throws IOException {
        try {
            String file = FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_seguimiento_";
            String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
            UploadedFile archivo = event.getFile();
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            InputStream input = new ByteArrayInputStream(arreglo);

            String ruta = PropApl.getInstance().get(PropApl.AUS_RUTA_CARGA);
            if (ruta == null) {
                throw new IOException("Configura la ruta de los adjuntos  en tabla parametros");
            }

            OutputStream output = new FileOutputStream(new File(ruta, filename));

            IOUtils.copy(input, output);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);

            List<AusAdjunto> listaAnexos = obtenerSesionAdjuntos(SESION_ADJUNTO_SEGUIMIENTOS);
            if (listaAnexos == null) {
                listaAnexos = new ArrayList<>();
            }
            //agraegar el nuevo anexo a la lista de anexos de tutela
            AusAdjunto adjunto = new AusAdjunto();
            adjunto.setId(null);
            adjunto.setNombre(filename);
            adjunto.setArchivo(file);
            adjunto.setRuta(ruta);
            this.auditoriaGuardar(adjunto);
            adjunto.setPos(listaAnexos.size());

            listaAnexos.add(adjunto);

            String tipoSeguimiento = (String) event.getComponent().getAttributes().get("tipoSeguimiento");

            if (tipoSeguimiento.equalsIgnoreCase("creacion")) {
                this.getObjeto().getSeguimientoAdicional().getAdjuntosList().add(adjunto);
            } else {
                this.getSeguimientoProcesar().getAdjuntosList().add(adjunto);
            }

            llenarSesionAdjuntos(SESION_ADJUNTO_SEGUIMIENTOS, listaAnexos);
            PrimeFaces.current().resetInputs("frmCrear:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmCrear:tablaAnexosSeguimiento");
            PrimeFaces.current().resetInputs("frmEditar:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmEditar:tablaAnexosSeguimiento");
            PrimeFaces.current().resetInputs("frmEditarSeguimiento:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmEditarSeguimiento:tablaAnexosSeguimiento");
            PrimeFaces.current().resetInputs("frmCrearSeguimiento:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmCrearSeguimiento:tablaAnexosSeguimiento");
        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto seguimiento : " + e.getMessage());
            generarMensajes();
        }
    }

    public void borrarAdjuntoSeguimientoAdicional(int pos) {
        try {
            delAdjuntoSeguimientoAdicional(pos);
        } catch (Exception e) {
            super.addError("No es posible borrar Adjunto");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrear:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmEditar:tablaAnexosSeguimiento");
        }
    }

    public void delAdjuntoSeguimientoAdicional(int pos) {
        List<AusAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (AusAdjunto det : this.getObjeto().getSeguimientoAdicional().getAdjuntosList()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        this.getObjeto().getSeguimientoAdicional().setAdjuntosList(lista);
    }

    public String convertirBytesParaMegasTamAdjunto() {
        int valor = getSizeLimitByAnexo() <= 0 ? 5242880 : getSizeLimitByAnexo();
        float MEGABYTE = 1024L * 1024L;
        float b = valor / MEGABYTE;
        return (b + " MB");
    }

    public void asignarTelefonoParaPersona(List<AusPersonaTelefono> telefonosAsignar) {
        getObjeto().getAusPersonasId().setListaTelefonos(telefonosAsignar);
    }

    public final void asignarEstadoInicialCaso() {
        //int idEstadoSol = getIdTipoEstadoSolicitud(AusCasoBean.DESC_CASO_ESTADO_RADICADO);
        int idEstadoSol = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DES_CASO_EST_RAD));
        this.getObjeto().setMaeSolicitudEstadoId(idEstadoSol);
    }

    public void asignarOrigenInicialCaso() {
        //int idEstadoSol = getIdTipoEstadoSolicitud(AusCasoBean.DESC_CASO_ESTADO_RADICADO);
        int idEstadoSol = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_ORIGEN_PAGINA_WEB));
        this.getObjeto().setMaeSolicitudOrigenId(idEstadoSol);
    }

    public void asignarPrioridadInicialCaso() {
        //int idEstadoSol = getIdTipoEstadoSolicitud(AusCasoBean.DESC_CASO_ESTADO_RADICADO);
        int idEstadoSol = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_PRIORIDAD));
        this.getObjeto().setMaeSolicitudPrioridadId(idEstadoSol);
    }

    public void asignaRiesgoVidaInicialCaso() {
        //int idEstadoSol = getIdTipoEstadoSolicitud(AusCasoBean.DESC_CASO_ESTADO_RADICADO);
        int idEstadoSol = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_RIESGO_VIDA));
        this.getObjeto().setMaeSolicitudRiesgoVidalId(idEstadoSol);
    }

    public void evaluarHabilitarCampoPorEstadoInsert() {
        Boolean anonimo = this.objeto.getAusPersonasId().getPersonaAnonima();
        if (anonimo) {
            this.getObjeto().getAusPersonasId().setDesHabilitarCampoPersona(false);
        } else {
            this.getObjeto().getAusPersonasId().setDesHabilitarCampoPersona(true);
        }
        //setLimpiarDatosAcordeEstadoServicio(desactivarEstado);
        PrimeFaces.current().ajax().update("frmCrear:pPersonaCrear");
    }

    public final void asignarResponsable() {
        //int idEstadoSol = getIdTipoEstadoSolicitud(AusCasoBean.DESC_CASO_ESTADO_RADICADO);
        if (getHashUsuarios() != null && !getHashUsuarios().isEmpty()) {
            Usuario usuarioResponsable = getHashUsuarios().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_RESPONSABLE)));
            if (usuarioResponsable != null) {
                this.setNombre(usuarioResponsable.getNombre());
                this.setUsuario(usuarioResponsable.getUsuario());
            }
        }
    }

    private void llenarSesionAdjuntos(String nombreSession, List<AusAdjunto> listaAnexos) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(nombreSession, listaAnexos);
    }

    private List<AusAdjunto> obtenerSesionAdjuntos(String nombreSesion) {
        List<AusAdjunto> listaAnexos = (List<AusAdjunto>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(nombreSesion);
        return listaAnexos;
    }

    private void asignacionResultadosConsultaParaBeanCaso() {
        this.getObjeto().setAusPersonasId(personaBean.getObjeto());
        //this.listaPersonaTelefono = personaBean.get ListaausPersonaTelefono();
        this.listaPersonaTelefono = personaBean.getObjeto().getListaTelefonos();
    }

    private boolean existePersonaAfiliada() {
        return this.getObjeto().getAusPersonasId().getEsAfiliado();
    }

    private void asignacionHashsParaBeanPersona() {
        personaBean.setHashTiposDocumento(this.getHashTiposDocumento());
//        personaBean.setHashSexo(this.getHashSexo());
//        personaBean.setHashEstadosPersona(this.getHashTipoEstadosPersona());
    }

    private void vaciarSesionAdjuntos(String nombreSession) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(nombreSession, null);
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case ACCION_BUSCAR_SOLICITUDES_CASO_SERVICE:
                    PrimeFaces.current().ajax().update("frmCrear");
                    break;
                case Url.ACCION_LISTAR:
                    break;
            }
        }
        generarMensajes();
    }

    public void buscarSolicitudesCaso() {
        //this.setMostrarTablaSolicitudes(false);
        super.setAccion(ACCION_LISTAR);
        getAtencionCreacionCaso().Accion(this);

        /*if (!super.isError()) {
              this.setMostrarTablaSolicitudes(true);
        }*/
        PrimeFaces.current().ajax().update("frmSolicitudConsultar");
//        RequestContext.getCurrentInstance().reset("frmSolicitudConsultar");
        procesoFinal();
    }

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : this.getUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        if (ubicacionesFiltradas.size() == 1) {
            getObjeto().setUbicacion(ubicacionesFiltradas.get(0));
        }
        return ubicacionesFiltradas;
    }

    public void crearTelefono() {
//        personaTelefono = new PersonaTelefono();
        PrimeFaces.current().resetInputs("frmCrearTelefono:panelCrearTelefono");
        PrimeFaces.current().ajax().update("frmCrearTelefono:panelCrearTelefono");
        PrimeFaces.current().executeScript("PF('dialogoCrearTelefono').show()");
    }

    public void adicionarTelefono() {
        try {
            boolean validar = true;
            AusPersonaTelefono obj = getPersonaTelefono();
            //Adicionar registro a la lista
            if (listaPersonaTelefono == null) {
                listaPersonaTelefono = new ArrayList();
            } else {
                for (AusPersonaTelefono telefono : listaPersonaTelefono) {
                    if (telefono.getNumero().equals(obj.getNumero())) {
                        validar = false;
                    }
                }
            }
            if (validar) {
                obj.setPos(listaPersonaTelefono.size());
                this.listaPersonaTelefono.add(obj);
            } else {
                addError("El numero de telefono ya existe");
            }

        } catch (Exception e) {
            this.addError("No es posible adicionar teléfono");
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearTelefono').hide();");
            PrimeFaces.current().ajax().update("frmCrear:telefonos");
        } else {
            this.generarMensajes();
        }
    }

    public void borrarTelefono(int pos) {
        try {
            //Retirar registro de la lista
            List<AusPersonaTelefono> lista = new ArrayList();
            int i = 0, j = 0;
            for (AusPersonaTelefono det : listaPersonaTelefono) {
                if (j != pos) {
                    det.setPos(i);
                    lista.add(det);
                    i++;
                }
                j++;
            }
            listaPersonaTelefono = lista;
        } catch (Exception e) {
            super.addError("No es posible borrar telefono");
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearTelefono').hide();");
            PrimeFaces.current().ajax().update("frmCrear:telefonos");
        }
    }

    public void limpiarFormularioBusqueda() {
        this.getObjeto().getAsuPersonasId().setDocumento(null);
        this.getObjeto().getAsuPersonasId().setMae_tipo_documento_id(0);
        this.getObjeto().getAsuPersonasId().setFechaNacimiento(null);
        this.getObjeto().getAsuPersonasId().setCorreoElectronico(null);
        this.getObjeto().getAsuPersonasId().setDireccion(null);
        this.getObjeto().getAsuPersonasId().setApellidos(null);
        this.getObjeto().getAsuPersonasId().setNombres(null);
        this.getObjeto().getAsuPersonasId().setMae_estado_id(0);
        this.getObjeto().getAsuPersonasId().setMae_sexo_id(0);
        this.getObjeto().getAsuPersonasId().setTelefonoFijo(null);
        this.getObjeto().getAsuPersonasId().setNumeroCelular(null);
        this.getObjeto().getAsuPersonasId().setPersonaUbicacion(null);
        this.getObjeto().getAsuPersonasId().setRegimen(true);
        this.getObjeto().getAsuPersonasId().setListaTelefonos(null);
        this.getObjeto().setModalidadEntrega(null);
        this.setListaPersonaTelefono(null);
        this.getObjeto().setMaeSolicitudTipoId(0);
        this.getObjeto().getSeguimientoInicial().setObservacion(null);
        this.getObjeto().setUbicacion(null);
        this.getObjeto().setAdjuntosList(null);
        //this.setHabilitarSeccionResultados(false);
        //this.setMostrarTablaSolicitudes(false);
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoTerminosYcondiciones').hide();");
    }
    
    public void campoObligatoriosDirrecionOcorreo(){
        if(this.objeto.getModalidadEntrega().equals(1)){
            this.campoObligatorioCorreoPersona = false;
            this.habilitarCampoDireccion = false;
            this.habilitarCampoEmail = true;
        }else {
            this.campoObligatorioCorreoPersona = true;
            this.habilitarCampoDireccion = true;
            this.habilitarCampoEmail = false;
        }
    }
    
    public void verDireccion() {
        if (objeto.getAusPersonasId().getDireccionVia() == null || this.objeto.getAusPersonasId().getDireccionVia().equals("SD")) {
            this.estaHabilitadoCampo = true;
        } else {
            this.estaHabilitadoCampo = false;
        }
        PrimeFaces.current().ajax().update("frmDireccion");
        PrimeFaces.current().executeScript("PF('dialogoDireccion').show()");
    }
    
    public void cambiarExigenciaDatosSegunVia() {
        String tipoVia = this.getObjeto().getAusPersonasId().getDireccionVia();
        if (tipoVia != null && !"".equals(tipoVia)) {
            if (tipoVia.equalsIgnoreCase("SD")) {
                cambiarEstadoCamposSinDireccion(true);
            } else {
                cambiarEstadoCamposSinDireccion(false);
            }

        } else {
            cambiarEstadoCamposSinDireccion(true);
        }
        PrimeFaces.current().ajax().update("frmDireccion");// validar
        PrimeFaces.current().resetInputs("frmDireccion");// validar
    }
    
    private void cambiarEstadoCamposSinDireccion(boolean tipoHabilitacion) {
        setEstaHabilitadoCampo(tipoHabilitacion);
    }
    
    /**
     * Utilidad para validar que un campo en nulo entregue una cadena vacia.
     *
     * @param campo
     * @return cadena vacia si el campo es nulo, de lo contrario el valor del
     * campo
     */
    public String retornarCadena(String campo) {
        if (campo == null || campo.trim().equals("")) {
            return "";
        } else {
            campo = campo + " ";
            return campo;
        }
    }
    
    public void actualizarDireccion() {
        // concatenamos los campos en la dirección del objeto afiliado
        String direccionCompleta;
        if (this.objeto.getAusPersonasId().getDireccionVia() != null
                && !this.objeto.getAusPersonasId().getDireccionVia().equals("")
                && !this.objeto.getAusPersonasId().getDireccionVia().equals("SD")) {
            direccionCompleta = retornarCadena(this.objeto.getAusPersonasId().getDireccionVia())
                    + retornarCadena(this.objeto.getAusPersonasId().getDireccionNro())
                    + retornarCadena(this.objeto.getAusPersonasId().getDireccionOrd1())
                    + retornarCadena(this.objeto.getAusPersonasId().getDireccionOrientacion())
                    + "# "
                    + retornarCadena(this.objeto.getAusPersonasId().getDireccionPlaca1())
                    + retornarCadena(this.objeto.getAusPersonasId().getDireccionOrd2())
                    + retornarCadena(this.objeto.getAusPersonasId().getDireccionOrientacion2())
                    + "- "
                    + retornarCadena(this.objeto.getAusPersonasId().getDireccionPlaca2())
                    + retornarCadena(this.objeto.getAusPersonasId().getDireccionDescripcion());
        } else {
            direccionCompleta = retornarCadena(this.objeto.getAusPersonasId().getDireccionVia())
                    + retornarCadena(this.objeto.getAusPersonasId().getDireccionDescripcion());
        }
        this.objeto.getAusPersonasId().setDireccion(direccionCompleta);
        // Se refresca unicamente el panel donde se encuentra el componente de la dirección
        PrimeFaces.current().ajax().update("frmCrear:direccion");
        PrimeFaces.current().executeScript("PF('dialogoDireccion').hide()");
    }
}
