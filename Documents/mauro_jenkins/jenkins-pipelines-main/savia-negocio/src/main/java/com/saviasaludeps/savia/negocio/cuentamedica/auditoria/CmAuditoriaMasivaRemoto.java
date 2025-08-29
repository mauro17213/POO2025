/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMasivaN;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaMasivaRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmAuditoriaAdjunto) cargado
     * @throws java.lang.Exception
     */
    CmAuditoriaMasivaN consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmAuditoriaAdjunto)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmAuditoriaMasivaN obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmAuditoriaAdjunto)
     * @throws java.lang.Exception
     */
    void actualizar(CmAuditoriaMasivaN obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmAuditoriaAdjunto) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmAuditoriaMasivaN eliminar(int id) throws Exception;

    /**
     * Permite obtener cantidad de auditorias 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * permite obtener la lista de auditorias 
     * @param paramConsulta : getParam1(): parceo de atributos cmradicado y calculo encabezados procesados
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaMasivaN> consultarLista(ParamConsulta paramConsulta) throws Exception;
   
}
