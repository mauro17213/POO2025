/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmHistoricoFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmHistoricoFacturaRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmHistoricoFactura) cargado
     * @throws java.lang.Exception
     */
    CmHistoricoFactura consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmHistoricoFactura)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmHistoricoFactura obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmHistoricoFactura)
     * @throws java.lang.Exception
     */
    void actualizar(CmHistoricoFactura obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmHistoricoFactura) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmHistoricoFactura eliminar(int id) throws Exception;
    
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
    List<CmHistoricoFactura> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorFactura(ParamConsulta paramConsulta) throws Exception;
    /**
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaAdjunto> consultarListaPorFactura(ParamConsulta paramConsulta) throws Exception;

}
