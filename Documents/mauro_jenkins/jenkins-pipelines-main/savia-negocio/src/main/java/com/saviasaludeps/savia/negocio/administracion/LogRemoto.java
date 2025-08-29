/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.Log;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface LogRemoto {
    
    /**
     * Consultar la cantidad de registros para la lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadTodos(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar los perfiles con filtros
     * @param paramConsulta
     * @return 
     * @throws Exception 
     */
    List<Log> consultarTodos(ParamConsulta paramConsulta) throws Exception;
        
    /**
     * Método para consultar un perfil por ID
     * @param id
     * @return (Perfiles) cargado
     * @throws java.lang.Exception
     */
    Log consultar(int id) throws Exception;
    
    /**
     * Método para crear un nuev maestro
     * @param obj (Maestros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(Log obj) throws Exception;
    
    /**
     * Consultar ultimos registros
     * @param empresa (String) Empresa correspondiente
     * @param cant (int) número de registros a consultar
     * @return
     * @throws Exception 
     */
    public List<Log> consultarUltimos(String empresa, int cant);
    
}
