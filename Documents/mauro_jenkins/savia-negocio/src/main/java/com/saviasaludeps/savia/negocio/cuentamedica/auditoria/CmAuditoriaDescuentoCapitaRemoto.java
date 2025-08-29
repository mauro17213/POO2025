/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaDescuentoCapitaRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmAuditoriaCapitaDescuento) cargado
     * @throws java.lang.Exception
     */
    CmAuditoriaCapitaDescuento consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmAuditoriaCapitaDescuento)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmAuditoriaCapitaDescuento obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmAuditoriaAutorizacion)
     * @throws java.lang.Exception
     */
    void actualizar(CmAuditoriaCapitaDescuento obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmAuditoriaAutorizacion) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmAuditoriaCapitaDescuento eliminar(int id) throws Exception;
    
    /**
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaCapitaDescuento> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorDetalle(ParamConsulta paramConsulta) throws Exception;
    /**
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaCapitaDescuento> consultarListaPorDetalle(ParamConsulta paramConsulta) throws Exception;

}
