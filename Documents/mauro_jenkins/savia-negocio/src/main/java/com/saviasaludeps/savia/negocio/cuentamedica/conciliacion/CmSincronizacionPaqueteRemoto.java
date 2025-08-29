/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;


import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionPaquete;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author admin
 */
public interface CmSincronizacionPaqueteRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmSincronizacionPaquete> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmSincronizacionPaquete consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmSincronizacionPaquete obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(CmSincronizacionPaquete obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmSincronizacionPaquete eliminar(int id) throws Exception;

    /**
     * Metodoqper permite consultar paquetes por sincronizacion
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmSincronizacionPaquete> consultarPorSincronizacion(ParamConsulta paramConsulta) throws Exception;

    
}
