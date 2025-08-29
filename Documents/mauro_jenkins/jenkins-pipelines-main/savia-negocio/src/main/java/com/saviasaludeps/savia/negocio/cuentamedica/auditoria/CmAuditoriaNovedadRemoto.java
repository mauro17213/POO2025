/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaNovedad;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaNovedadRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmAuditoriaNovedad) cargado
     * @throws java.lang.Exception
     */
    CmAuditoriaNovedad consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmAuditoriaNovedad)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmAuditoriaNovedad obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmAuditoriaNovedad)
     * @throws java.lang.Exception
     */
    void actualizar(CmAuditoriaNovedad obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmAuditoriaNovedad) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmAuditoriaNovedad eliminar(int id) throws Exception;
    
    /**
     * Consulta la cantidad de CmRadicados 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadRadicado(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que permite consultar la todalidad de auditoria novedades
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite consultar la totalidad de auditoria novedades
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaNovedad> consultarLista(ParamConsulta paramConsulta) throws Exception;

}
