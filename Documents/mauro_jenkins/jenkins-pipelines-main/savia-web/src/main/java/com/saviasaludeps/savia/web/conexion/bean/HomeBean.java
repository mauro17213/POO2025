/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.conexion.bean;

import com.saviasaludeps.savia.web.administracion.bean.EmpresaBean;
import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.GnAlerta;
import com.saviasaludeps.savia.dominio.administracion.GnMensaje;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.ModuloManual;
import com.saviasaludeps.savia.dominio.administracion.ModuloVersion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.web.utilidades.Conexion;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import com.saviasaludeps.savia.web.administracion.servicio.ConexionServicioIface;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author raul-palacios
 */
@ManagedBean
@ViewScoped
public class HomeBean extends Url {

    private Usuario usuario = new Usuario();
    private Empresa empresa = new Empresa();
    private ModuloVersion moduloVersion;
    private GregorianCalendar fechaHoraConexion = null;
    private List<EmpresaBean> empresas = new ArrayList();
    private List<Empresa> empresasConFiltro;
    private int moduloAbierto;

    private List<GnMensaje> listaMensajes;
    private GnMensaje objetoMensaje;
    private List<GnAlerta> listaAlertas;
    private GnAlerta objetoAlerta;
    private GnAlerta alertaSeleccionada;
    private int alertasPendientes;

    @PostConstruct
    public void init() {
        try {
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            moduloVersion = (ModuloVersion) sessionMap.get("version");
            try {
                setListaMensajes(getConexionServicio().consultarMensajes(getConexion().getEmpresa().getId()));
            } catch (Exception e) {
                setListaMensajes(new ArrayList<>());
            }
            iniciarAlertas();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            if (request.getParameter("error") != null && request.getParameter("error").equals("1")) {
                addMensaje("Se cerro una sesión abierta");
                generarMensajes();
            }
        } catch (Exception e) {
            moduloVersion = null;
        }
    }

    private ConexionServicioIface conexionServicio;

    public ConexionServicioIface getConexionServicio() {
        return conexionServicio;
    }

    public void setConexionServicio(ConexionServicioIface conexionServicio) {
        this.conexionServicio = conexionServicio;
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public GregorianCalendar getFechaHoraConexion() {
        return fechaHoraConexion;
    }

    public void setFechaHoraConexion(GregorianCalendar fechaHoraConexion) {
        this.fechaHoraConexion = fechaHoraConexion;
    }

    public ModuloVersion getModuloVersion() {
        return moduloVersion;
    }

    public void setModuloVersion(ModuloVersion moduloVersion) {
        this.moduloVersion = moduloVersion;
    }

    public GnMensaje getObjetoMensaje() {
        return objetoMensaje;
    }

    public void setObjetoMensaje(GnMensaje objetoMensaje) {
        this.objetoMensaje = objetoMensaje;
    }

    public List<GnAlerta> getListaAlertas() {
        return listaAlertas;
    }

    public void setListaAlertas(List<GnAlerta> listaAlertas) {
        this.listaAlertas = listaAlertas;
    }

    public GnAlerta getAlertaSeleccionada() {
        return alertaSeleccionada;
    }

    public void setAlertaSeleccionada(GnAlerta alertaSeleccionada) {
        this.alertaSeleccionada = alertaSeleccionada;
    }

    public List<Empresa> getEmpresasActivas() {
        List<Empresa> list = new ArrayList();
        try {
            list = getConexionServicio().consultarEmpresasActivas();
        } catch (Exception ex) {
            addError("No fue posible cargar la lista de lista de Empresdas (" + ex.getMessage() + ")");
        }
        generarMensajes();
        return list;
    }

    public List<EmpresaBean> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<EmpresaBean> empresas) {
        this.empresas = empresas;
    }

    public int getModuloAbierto() {
        return moduloAbierto;
    }

    public void setModuloAbierto(int moduloAbierto) {
        this.moduloAbierto = moduloAbierto;
    }

    public List<GnMensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<GnMensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }

    public int getAlertasPendientes() {
        return alertasPendientes;
    }

    public void setAlertasPendientes(int alertasPendientes) {
        this.alertasPendientes = alertasPendientes;
    }

    public GnAlerta getObjetoAlerta() {
        return objetoAlerta;
    }

    public void setObjetoAlerta(GnAlerta objetoAlerta) {
        this.objetoAlerta = objetoAlerta;
    }

    public List<Empresa> getEmpresasConFiltro() {
        empresasConFiltro = new ArrayList();
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Conexion conexion = (Conexion) session.getAttribute("conexion");
        for (Empresa emp : conexion.getEmpresas()) {
            if (!Objects.equals(conexion.getEmpresa().getId(), emp.getId())) {
                empresasConFiltro.add(emp);
            }
        }
        return empresasConFiltro;
    }

    public String aperturaOpcion(String _url) {
        return _url;
    }

    public String aperturaOpcion(int _id) {
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Conexion conexion = (Conexion) session.getAttribute("conexion");

        for (Modulo _mod : conexion.getModulos()) {
            if (_mod.getId() == _id) {
                return _mod.getUrl() + "?faces-redirect=true";
            }
        }
        return "/home?faces-redirect=true";
    }

    public void construccion() {
        addMessage("En Construcción", "Opción en construcción");
        generarMensajes();
    }

    public String refrescar() {
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Conexion conexion = (Conexion) session.getAttribute("conexion");
        setEmpresa(conexion.getEmpresa());
        return "/home?faces-redirect=true";
    }

    public void cambioEmpresa() {
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Conexion conexion = (Conexion) session.getAttribute("conexion");
        for (Empresa _empresa : getConexion().getEmpresas()) {
            if (Objects.equals(_empresa.getId(), getEmpresa().getId())) {
                setEmpresa(_empresa);
                conexion.setEmpresa(_empresa);
                break;
            }
        }
        try {
            setListaMensajes(getConexionServicio().consultarMensajes(conexion.getEmpresa().getId()));
        } catch (Exception e) {
            setListaMensajes(new ArrayList<>());
        }
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("conexion", conexion);
        try {
            externalContext.redirect("/savia/home.faces");
        } catch (IOException ex) {
            //TODO capturar error y mandar mensaje
        }
    }

    public String cambiarContrasena() {
        return "/contrasena?faces-redirect=true";
    }

    public String salir() {
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        getConexionServicio().cerrarSesion(getConexion().getIdSesion(), getConexion().getIp());
        session.removeAttribute("conexion");
        session.invalidate();
        return "/login?faces-redirect=true";
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public StreamedContent generarManual() {
        StreamedContent streamedContent = null;
        try {
            //Validar si es Usuario Interno o Externo
            ModuloManual moduloManual = null;
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            String moduloA = sessionMap.get("Modulo") != null ? sessionMap.get("Modulo").toString() : null;
            if (moduloAbierto == 0 && moduloA != null) {
                moduloAbierto = Integer.parseInt(moduloA);
                sessionMap.put("Modulo", null);
            }
            if (super.getConexion().getEmpresa().getId() == 1) {                
                moduloManual = getConexionServicio().consultarManual(moduloAbierto == 0 ? 1 : moduloAbierto, ModuloManual.TIPO_MANUAL_INTERNO);
            } else {
                moduloManual = getConexionServicio().consultarManual(moduloAbierto == 0 ? 1 : moduloAbierto, ModuloManual.TIPO_MANUAL_EXTERNO);
            }
            if (moduloManual.getArchivo() != null) {
                File manual = new File(moduloManual.getRuta().concat(moduloManual.getArchivo()));
                InputStream stream = new FileInputStream(manual);
                stream.mark(0); //remember to this position!
                streamedContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(moduloManual.getArchivo()).build();
            } else {
                this.addError("No se encontró manual interno para descargar en esta opción");
                generarMensajes();
                return null;
            }
        } catch (Exception ex) {
            streamedContent = null;
            this.addError("Error al descargar el manual: " + ex.toString() + ex.getMessage());
            generarMensajes();
        }
        return streamedContent;
    }

    public String actionDone() {
        return "done?faces-redirect=true";
    }

    //bean
    public String navigate(String url) {
        return url + ".xhtml?faces-redirect=true";
    }

    public void mostrarMensaje(GnMensaje mensaje) {
        try {
            setObjetoMensaje(conexionServicio.consultarMensaje(mensaje.getId()));
        } catch (Exception e) {
            setObjetoMensaje(mensaje);
        }
        PrimeFaces.current().executeScript("PF('dialogoVerMensajeHome').show()");
        PrimeFaces.current().ajax().update("frmVerMensajeHome");
    }

    public void verHomeAlertas() {
        PrimeFaces.current().ajax().update("frmHomeAlertas");
        PrimeFaces.current().executeScript("PF('dialogoHomeAlertas').show()");
    }

    public void verHomeAlerta(GnAlerta alerta) {
        setAlertaSeleccionada(alerta);
        getAlertaSeleccionada().setFechaHoraLee(new Date());
        getAlertaSeleccionada().setEstado(GnAlerta.ESTADO_LEIDO);
        try {
            getConexionServicio().actualizarAlerta(getAlertaSeleccionada());
            PrimeFaces.current().ajax().update("frmVerHomeAlerta");
            PrimeFaces.current().executeScript("PF('dialogoVerHomeAlerta').show()");
        } catch (Exception e) {
            addError("Hubo un fallo al ver la alerta, favor contactar al administrador");
            generarMensajes();
        }
    }

    public void descartar() {
        try {
            getObjetoAlerta().setFechaHoraDescarta(new Date());
            getObjetoAlerta().setEstado(GnAlerta.ESTADO_DESCARTADO);
            getConexionServicio().actualizarAlerta(getObjetoAlerta());
            iniciarAlertas();
            PrimeFaces.current().ajax().update("frmListaAlertas");
            PrimeFaces.current().ajax().update("cantidadPendientes");
            PrimeFaces.current().executeScript("PF('dialogoVerAlerta').hide()");
            
        } catch (Exception e) {
            addError("Hubo un fallo al ver la alerta, favor contactar al administrador");
            generarMensajes();
        }
    }

    public String colorMensaje(GnMensaje mensaje) {
        switch (mensaje.getPrioridad()) {
            case GnMensaje.ESTADO_URGENTE:
                return "red";
            case GnMensaje.ESTADO_ALTA:
                return "orange";
            case GnMensaje.ESTADO_MEDIA:
                return "yellow";
        }
        return "white";
    }

    public String getIconAlerta() {
        boolean leidos = true;
        if (getListaAlertas() != null) {
            for (GnAlerta alerta : getListaAlertas()) {
                if (alerta.getEstado() == GnAlerta.ESTADO_GENERADO) {
                    leidos = false;
                }
            }
        }
        if (leidos) {
            return " pi pi-envelope";
        } else {
            return "pi pi-envelope";
        }
    }

    private void iniciarAlertas() {
        try {
            setListaAlertas(getConexionServicio().consultarAlertas(getConexion().getUsuario().getId()));
            getListaAlertas().stream().filter(alerta -> (alerta.getEstado() == GnAlerta.ESTADO_GENERADO)).forEachOrdered(_item -> {
                setAlertasPendientes(getAlertasPendientes() + 1);
            });
        } catch (Exception e) {
            setListaAlertas(new ArrayList<>());
        }
    }

    public void listarAlertas() {
        iniciarAlertas();
        PrimeFaces.current().ajax().update("frmListaAlertas");
        PrimeFaces.current().executeScript("PF('dialogoListaAlertas').show()");
    }

    public void verAlerta(GnAlerta alerta) {
        setObjetoAlerta(alerta);        
        try {
            if (getObjetoAlerta().getEstado() == 1) {
                getObjetoAlerta().setEstado(GnAlerta.ESTADO_LEIDO);
                getObjetoAlerta().setFechaHoraLee(new Date());
                getConexionServicio().actualizarAlerta(getObjetoAlerta());
            }                
            PrimeFaces.current().ajax().update("frmVerAlerta");
            PrimeFaces.current().executeScript("PF('dialogoVerAlerta').show()");
        } catch (Exception e) {
            addError("Hubo un fallo al  actualizar una alerta");
        }
        
    }

}
