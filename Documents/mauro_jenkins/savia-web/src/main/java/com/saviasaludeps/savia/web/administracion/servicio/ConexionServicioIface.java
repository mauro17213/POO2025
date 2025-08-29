/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.GnAlerta;
import com.saviasaludeps.savia.dominio.administracion.GnMensaje;
import com.saviasaludeps.savia.dominio.administracion.Login;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.ModuloManual;
import com.saviasaludeps.savia.dominio.administracion.ModuloVersion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.web.conexion.bean.ContrasenaBean;
import java.util.List;

/**
 *I
 * @author raul-palacios
 */
public interface ConexionServicioIface {
    
    /**
     * 
     * @param obj
     * @return
     * @throws Exception 
     */
    Login validaConexion(Login obj)throws Exception;
    
    /**
     * 
     * @return 
     * @throws java.lang.Exception 
     */
    List<Empresa> consultarEmpresasActivas()throws Exception;
    
    /**
     * 
     * @param obj
     * @return 
     */
    List<Modulo> consultarModulosPermiso(Usuario obj);
    
    /**
     * 
     * @param obj
     * @return 
     */
    Modulo consultarArbolModuloPorUsuario(Usuario obj);
    
    /**
     * 
     * @param empresa
     * @return 
     */
    Empresa cambiarEmpresa(Empresa empresa);
    
    /**
     * 
     * @param bean 
     */
    void cambiarContrasena(ContrasenaBean bean);
    
    /**
     * 
     * @param obj
     * @return 
     * @throws Exception 
     */
    boolean restaurarContrasena(Login obj) throws Exception;
    
    /**
     * Método para consultar la versión actual del sistema
     * @return 
     * @throws Exception 
     */
    ModuloVersion consultarVersion() throws Exception;
    
    /**
     * Método para consultar el manual del sistema
     * @param id
     * @param tipo
     * @return
     * @throws Exception 
     */
    ModuloManual consultarManual(int id, int tipo) throws Exception;
    
    /**
     * Consulta los mensajes de la aplicacion
     * @param idEmpresa
     * @return
     * @throws Exception 
     */
    List<GnMensaje> consultarMensajes(int idEmpresa) throws Exception;
    
    /**
     * Consulta las alertas del usuario
     * @param idUsuario
     * @return
     * @throws Exception 
     */
    List<GnAlerta> consultarAlertas(int idUsuario) throws Exception;
    
    /**
     * Actualiza la alerta
     * @param alerta
     * @throws Exception 
     */
    void actualizarAlerta(GnAlerta alerta) throws Exception;
    
    /**
     * Consuta mensaje completo
     * @param idMensaje
     * @return
     * @throws Exception 
     */
    GnMensaje consultarMensaje(int idMensaje) throws Exception;
    
    /**
     * Actualiza o Crea los datos de sesion 
     * @param idSesion
     * @param ip
     * @param idUsuario 
     * @param maximo 
     * @return  
     */
    boolean actualizarSesion(String idSesion, String ip, int idUsuario, int maximo);
    
    /**
     * Cierra la sesion
     * @param idSesion
     * @param ip 
     */
    void cerrarSesion(String idSesion, String ip);
    
    /**
     * Se limpia la sesion
     * @param idSesion
     * @param idUser
     * @return 
     */
    boolean existeSesion(String idSesion, int idUser);
    
}
