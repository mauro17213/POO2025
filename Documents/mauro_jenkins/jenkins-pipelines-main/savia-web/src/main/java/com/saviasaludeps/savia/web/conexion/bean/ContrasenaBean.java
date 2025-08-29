/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.conexion.bean;

import com.saviasaludeps.savia.web.administracion.servicio.ConexionServicioImpl;
import com.saviasaludeps.savia.web.utilidades.Url;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.administracion.servicio.ConexionServicioIface;

/**
 *
 * @author raul-palacios
 */
@ManagedBean
@ViewScoped
public class ContrasenaBean extends Url{
    
    private ConexionServicioIface conexionServicio;
    
    private int idUsuario=0;
    private String usuarioCorreoElectronico="";
    private String nombreUsuario="";
    private String contrasenaActual="";
    private String contrasenaNueva1="";
    private String contrasenaNueva2="";

    public ContrasenaBean() {
    }
    @PostConstruct
    public void postConstruct() {
        setIdUsuario(super.getConexion().getUsuario().getId());
        setUsuarioCorreoElectronico(super.getConexion().getUsuario().getCorreoElectronico());
        setNombreUsuario(super.getConexion().getUsuario().getNombre());
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuarioCorreoElectronico() {
        return usuarioCorreoElectronico;
    }

    public void setUsuarioCorreoElectronico(String usuarioCorreoElectronico) {
        this.usuarioCorreoElectronico = usuarioCorreoElectronico;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenaActual() {
        return contrasenaActual;
    }

    public void setContrasenaActual(String contrasenaActual) {
        this.contrasenaActual = contrasenaActual;
    }

    public String getContrasenaNueva1() {
        return contrasenaNueva1;
    }

    public void setContrasenaNueva1(String contrasenaNueva1) {
        this.contrasenaNueva1 = contrasenaNueva1;
    }

    public String getContrasenaNueva2() {
        return contrasenaNueva2;
    }

    public void setContrasenaNueva2(String contrasenaNueva2) {
        this.contrasenaNueva2 = contrasenaNueva2;
    }

    public ConexionServicioIface getConexionServicio() {
        if(conexionServicio==null)conexionServicio=new ConexionServicioImpl();
        return conexionServicio;
    }
    public void setConexionServicio(ConexionServicioIface conexionServicio) {
        this.conexionServicio = conexionServicio;
    }
    
    public void cambiarContrasena(){
        getConexionServicio().cambiarContrasena(this);
        if(!isError()){
            setContrasenaActual("");
            setContrasenaNueva1("");
            setContrasenaNueva2("");
        }
        super.crearLog("Cambio de Contraseña", toString());
        super.generarMensajes();
    }

    @Override
    public String toString() {
        return "Contraseña{" + "correoElectronico=" + usuarioCorreoElectronico + ", nombre=" + nombreUsuario + '}';
    }
    
    
    
}
