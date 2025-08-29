/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmFacturaEstado;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmFacturaEstadoRemoto {

    /**
     * Permite consultar un registro por ID
     *
     * @param id
     * @return (CmFacturaEstado) cargado
     * @throws java.lang.Exception
     */
    CmFacturaEstado consultar(int id) throws Exception;

    /**
     * Permite crear una nuevo registro
     *
     * @param obj (CmAuditoriaAdjunto)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmFacturaEstado obj) throws Exception;

    /**
     * Permite actualizar la información de un registro
     *
     * @param obj (CmFacturaEstado)
     * @throws java.lang.Exception
     */
    void actualizar(CmFacturaEstado obj) throws Exception;

    /**
     * Permite eliminar un registro
     *
     * @param id
     * @return (CmFacturaEstado) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmFacturaEstado eliminar(int id) throws Exception;

    /**
     * perimte insertar la factura en estado proceso auditoria
     * @param listFacturaEstados
     * @throws Exception 
     */
        void insertarEstadoMasivo(List<CmFacturaEstado> listFacturaEstados) throws Exception;
    /**
     * permite borrar la factura estado en proceso auditoria
     * @param listFacturaEstados
     * @param estado
     * @throws Exception 
     */
    void borrarFacturasEstado(List<CmFacturaEstado> listFacturaEstados, int estado) throws Exception;

    /**
     * permite buscar si una factura esta en proceso para un usuario
     * @param estado
     * @param usuario
     * @param listFacturaEstados
     * @return
     * @throws Exception 
     */
    List<CmFacturaEstado> buscarFacturasNoPermitidasPorUsuario(int estado, String usuario, List<CmFacturaEstado> listFacturaEstados) throws Exception;

    /**
     * Permite buscar si la facturas es usada por un usuario determinado
     * @param estado
     * @param usuario
     * @param listFacturaEstados
     * @return
     * @throws Exception 
     */
     List<CmFacturaEstado> buscarFacturasUsadasPorUsuario(int estado, String usuario, List<CmFacturaEstado> listFacturaEstados) throws Exception;
    

}
