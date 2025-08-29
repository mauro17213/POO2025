/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaDetalleRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmAuditoriaDetalle) cargado
     * @throws java.lang.Exception
     */
    CmAuditoriaDetalle consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmRadicado)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmRadicado obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmRadicado)
     * @throws java.lang.Exception
     */
    void actualizar(CmRadicado obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmRadicado) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmRadicado eliminar(int id) throws Exception;
    
    /**
     * Consulta la cantidad de CmRadicados 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadRadicado(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    List<CmAuditoriaDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadDetallesPorFactura(ParamConsulta paramConsulta) throws Exception;

    List<CmAuditoriaDetalle> consultarListaDetallesPorFactura(ParamConsulta paramConsulta) throws Exception;


}
