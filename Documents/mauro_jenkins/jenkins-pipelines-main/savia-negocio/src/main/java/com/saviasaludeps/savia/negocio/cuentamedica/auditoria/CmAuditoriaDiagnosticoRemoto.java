/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaDiagnosticoRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmAuditoriaDiagnostico) cargado
     * @throws java.lang.Exception
     */
    CmAuditoriaDiagnostico consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmAuditoriaDiagnostico)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmAuditoriaDiagnostico obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmAuditoriaDiagnostico)
     * @throws java.lang.Exception
     */
    void actualizar(CmAuditoriaDiagnostico obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmAuditoriaDiagnostico) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmAuditoriaDiagnostico eliminar(int id) throws Exception;
    
    /**
     * Consulta la cantidad de CmRadicados 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception ;
    /**
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaDiagnostico> consultarLista(ParamConsulta paramConsulta) throws Exception;
    /**
     *  Metodo que permite consultar dado un detalle
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorDetalle(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite consultar dado un detalle
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaDiagnostico> consultarListaPorDetalle(ParamConsulta paramConsulta) throws Exception;

}
