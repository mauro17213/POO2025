/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmReintento;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmEnviosGlosaRemoto {

    List<CmReintento> consultarListaReintentos(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la cantidad de CmRadicados
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadReintentos(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite consultar radicados activos
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadRadicadosActivos(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permiete consultar radicados activos
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmReintento> consultarListaRadicadosActivos(ParamConsulta paramConsulta) throws Exception;

}
