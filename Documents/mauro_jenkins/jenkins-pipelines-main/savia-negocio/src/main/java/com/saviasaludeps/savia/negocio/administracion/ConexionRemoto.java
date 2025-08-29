/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

//import java.util.List;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.administracion.Login;

/**
 *
 * @author rpalacic
 */
public interface ConexionRemoto {
    
    /**
     * Método para realizar la conexión de un usuario en el sistema
     * @param bean
     * @return 
     */
    boolean conectar(Login bean);
    
    /**
     * Método pára realizar la desconexión de un usuario en el sistema
     * @param bean
     * @return 
     */
    boolean desconectar(Login bean);
    
    /**
     * Método para la validación de sesion activa, y usuario autorizado
     * @param bean (LoginBean) objeto con la información de vlidación
     * @return 
     * @throws java.lang.Exception 
     */
    Usuario validarConexion(Login bean) throws Exception;
    
}
