/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public interface CmSincronizacionDetalleRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmSincronizacionDetalle) cargado
     * @throws java.lang.Exception
     */
    CmSincronizacionDetalle consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmSincronizacionDetalle)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmSincronizacionDetalle obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmSincronizacionDetalle)
     * @throws java.lang.Exception
     */
    void actualizar(CmSincronizacionDetalle obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmSincronizacionDetalle) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmSincronizacionDetalle eliminar(int id) throws Exception;
    
     /**
     * Método para eliminar un registro
     *
     * @param idEncabezado
     * @return (CmSincronizacionDetalle) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    List<CmSincronizacionDetalle> consultarDetallesEncabezado(int idEncabezado) throws Exception;

    /**
     * Metodo que retorna cantidad de items encontrados segun busqueda
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que resliza consulta segun parametros dados.
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmSincronizacionDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception;

}
