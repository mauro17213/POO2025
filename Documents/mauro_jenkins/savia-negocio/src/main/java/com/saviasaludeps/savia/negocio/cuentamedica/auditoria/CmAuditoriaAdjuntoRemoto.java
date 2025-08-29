/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaAdjuntoRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmAuditoriaAdjunto) cargado
     * @throws java.lang.Exception
     */
    CmAuditoriaAdjunto consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmAuditoriaAdjunto)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmAuditoriaAdjunto obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmAuditoriaAdjunto)
     * @throws java.lang.Exception
     */
    void actualizar(CmAuditoriaAdjunto obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmAuditoriaAdjunto) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmAuditoriaAdjunto eliminar(int id) throws Exception;
    
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
    List<CmAuditoriaAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * 
     * @param paramConsulta: getParam1(): consultar adjuntos de factura, getParam2(): todos los adjuntos presentes
     * getParam3(): consultar adjuntos de detalle, getParam4(): tipo archivo ( 0 factura, 1 detalle)
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorDetalle(ParamConsulta paramConsulta) throws Exception;
    /**
     * 
     * @param paramConsulta
     * getParam1(): consultar adjuntos de factura, getParam2(): todos los adjuntos presentes
     * getParam3(): consultar adjuntos de detalle, getParam4(): tipo archivo ( 0 factura, 1 detalle)
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaAdjunto> consultarListaPorDetalle(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite eliminar un adjunto segun la factura
     * @param id
     * @param idFactura
     * @return
     * @throws Exception 
     */
    CmAuditoriaAdjunto eliminarSegunFactura(int id, int idFactura) throws Exception;

}
