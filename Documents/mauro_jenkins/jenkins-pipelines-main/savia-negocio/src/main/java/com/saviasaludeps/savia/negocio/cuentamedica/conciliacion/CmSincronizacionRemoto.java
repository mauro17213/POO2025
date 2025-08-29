/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;


import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface CmSincronizacionRemoto {
    
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
    List<CmSincronizacion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmSincronizacion consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmSincronizacion obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(CmSincronizacion obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmSincronizacion eliminar(int id) throws Exception;

    /**
     * Metodo que permite encontrar las sincronozaciones dado un numero radicado 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
   List<CmSincronizacion> consultarPorRadicado(ParamConsulta paramConsulta) throws Exception;
  
   /**
    * Metodo que permite realizar busqueda de conciliaciones segun criterio de atributos
    * @param idConciliacion
    * @return
    * @throws Exception 
    */
   int consultarCantidadPorConciliacion(int idConciliacion) throws Exception ;
   
   /**
    * Permite obtener la ultima sincronizacion realizada de una conciliacion masiva
    * @param idCmConciliacion
    * @return
    * @throws Exception 
    */
    CmSincronizacion consultarUltimaSincronizacionPorCmConciliacion(int idCmConciliacion) throws Exception ;
    
    /**
     * Permite obtener sincronizaciones por auditoria masiva
     * @param idCmAuditoriaMasiva
     * @return
     * @throws Exception 
     */
    CmSincronizacion consultarUltimaSincronizacionPorCmAuditoriaMasiva(int idCmAuditoriaMasiva) throws Exception;
    
}
