/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaCierreRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmAuditoriaCierre) cargado
     * @throws java.lang.Exception
     */
    CmAuditoriaCierre consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmAuditoriaCierre)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmAuditoriaCierre obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmAuditoriaCierre)
     * @throws java.lang.Exception
     */
    void actualizar(CmAuditoriaCierre obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmAuditoriaAutorizacion) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmAuditoriaCierre eliminar(int id) throws Exception;
    
    /**
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite buscar auditoria cierre
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaCierre> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
   /**
    * Permite consular los cieres de una auditoria masiva
    * @param cmAuditoriaMasivaId
    * @return
    * @throws Exception 
    */
    List<CmAuditoriaCierre> consultarPorCmAuditoriaMasivaId(int cmAuditoriaMasivaId) throws Exception;

    /**
     * Permite obtener objeto si corresponde a un determinado idfactura
     * @param idCmFactura
     * @return
     * @throws Exception 
     */
    CmAuditoriaCierre consultarPorIdCmFactura(int idCmFactura) throws Exception;

}
