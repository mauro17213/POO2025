/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaDevolucionRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmAuditoriaDevolucion) cargado
     * @throws java.lang.Exception
     */
    CmAuditoriaDevolucion consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmAuditoriaDevolucion)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmAuditoriaDevolucion obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmAuditoriaDevolucion)
     * @throws java.lang.Exception
     */
    void actualizar(CmAuditoriaDevolucion obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmAuditoriaDevolucion) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmAuditoriaDevolucion eliminar(int id) throws Exception;
    
    /**
     * Consulta la cantidad de CmRadicados 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception ;
    /**
     * Metodo que permite consultar lista de devoluciones
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaDevolucion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Permite buscar devoluciones segun cmdevolucionmasivaId
     * @param cmAuditoriaDevoluconesId
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaDevolucion> consultarPorCmAuditoriaDevolucionMasivaId(int cmAuditoriaDevoluconesId) throws Exception ;

    /**
     * Permite obtener auditoria devolucion por devolucion masiva id y id factura
     * @param idsFactura
     * @return
     * @throws Exception 
     */
      List<CmAuditoriaDevolucion>  consultarPorFacturaId(String idsFactura) throws Exception;

      /**
       * Permite buscar las devoluciones segun su tipo devolucion(individual, masiva)
       * @param idsFactura
       * @param tipoDevolucion(1 individual, 2 masivo)
       * @param idBusqueda
       * @return
       * @throws Exception 
       */
    List<CmAuditoriaDevolucion> consultarPorCmFacturaIdTipoDevolucion(String idsFactura, int tipoDevolucion, int idBusqueda) throws Exception;
  
}
