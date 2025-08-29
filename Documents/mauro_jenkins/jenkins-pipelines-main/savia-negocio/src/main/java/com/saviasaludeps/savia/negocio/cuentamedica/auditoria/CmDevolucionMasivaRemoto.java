/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmDevolucionMasivaN;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmDevolucionMasivaRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmDevolucionMasivaN) cargado
     * @throws java.lang.Exception
     */
    CmDevolucionMasivaN consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmDevolucionMasivaN)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmDevolucionMasivaN obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmDevolucionMasivaN)
     * @throws java.lang.Exception
     */
    void actualizar(CmDevolucionMasivaN obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmDevolucionMasivaN) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmDevolucionMasivaN eliminar(int id) throws Exception;

    /**
     * Permite obtener cantidad de auditorias 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * permite obtener la lista de auditorias 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmDevolucionMasivaN> consultarLista(ParamConsulta paramConsulta) throws Exception;
   
}
