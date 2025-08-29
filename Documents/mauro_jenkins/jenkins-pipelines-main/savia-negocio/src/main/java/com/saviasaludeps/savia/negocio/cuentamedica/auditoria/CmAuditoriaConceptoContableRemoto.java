/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaConceptoContableRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmRadicado) cargado
     * @throws java.lang.Exception
     */
    CmAuditoriaConceptoContable consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmRadicado)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmAuditoriaConceptoContable obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmRadicado)
     * @throws java.lang.Exception
     */
    void actualizar(CmAuditoriaConceptoContable obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmRadicado) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmAuditoriaConceptoContable eliminar(int id) throws Exception;
    
    /**
     * Metodo que permite consultar cantidad de todos los conceptos contables de auditoria
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite consultar todos los conceptos contables de auditoria
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaConceptoContable> consultarLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * etodo que permite consultar todos los conceptos contables de auditoria segun
     * detalle
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaConceptoContable> consultarListaPorDetalle(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite consultar todos los conceptos contables de auditoria segun
     * detalle
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorDetalle(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite consultar la cantidad de conceptos presentes en un detalle
     * @param idDetalle
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorIdDetalle(int idDetalle) throws Exception;

}
