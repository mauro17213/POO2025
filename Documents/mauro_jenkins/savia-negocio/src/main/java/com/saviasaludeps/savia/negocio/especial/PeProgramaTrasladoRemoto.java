/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeProgramaTraslado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;


/**
 *
 * @author Jhohan Lopez
 */
public interface PeProgramaTrasladoRemoto {
    
      /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta Parametros de la consulta de registros
     * @return Lista de registros consultados
     * @throws Exception 
     */
    List<PeProgramaTraslado> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
     /**
     * Inserta un registro PeCargaVariables
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(PeProgramaTraslado obj) throws Exception;
    
}
