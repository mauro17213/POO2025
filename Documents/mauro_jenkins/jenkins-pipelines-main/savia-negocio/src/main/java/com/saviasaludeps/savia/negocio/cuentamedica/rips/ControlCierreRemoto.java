/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmControlCierre;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jramirer
 */
public interface ControlCierreRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<CmControlCierre> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmControlCierre consultar(int id) throws Exception;

    /**
     *
     * @param per
     * @param id
     * @return (ControlCierre) cargado
     * @throws java.lang.Exception
     */

    int insertar(CmControlCierre per) throws Exception;

    /**
     *
     * @param per (ControlCierre)
     * @throws java.lang.Exception
     */
    void actualizar(CmControlCierre per) throws Exception;
    /**
     *
     * @param id
     * @return (ControlCierre) Objetop eliminado
     * @throws java.lang.Exception
     */
    CmControlCierre eliminar(int id) throws Exception;

   
    HashMap<Integer, String> consultarHash();

    /**
     * Permite consultar si una fecha desde o cierre coincide con un cierre establecido
     * @param paramConsulta: param1:fecha desde o cierre, param2: id modalidad, param3: cobertura cierre, 
     *       param4:fecha apertura,  param5: id cierre - opcional, 
     * @return
     * @throws Exception 
     */
    List<CmControlCierre> consultarPresenciaFechaEnIntervalo(ParamConsulta paramConsulta) throws Exception;
}
